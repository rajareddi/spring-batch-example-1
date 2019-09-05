package com.reddy.springbatchexample1.report.tasklets;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.SftpException;
import com.reddy.springbatchexample1.sftp.SFTPConnectionManager;
import com.reddy.springbatchexample1.sftp.SFTPSummaryClientConfig;
import com.reddy.springbatchexample1.utils.CsvReaderUtil;
import com.reddy.springbatchexample1.utils.FileLsEntryFilter;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SFTPsummaryFileReader implements Tasklet, StepExecutionListener {

	@Autowired
	SFTPConnectionManager sftpConnectionManager;
	@Autowired
	private SFTPSummaryClientConfig ftpConfig;
	private final Logger logger = LoggerFactory.getLogger(SFTPsummaryFileReader.class);

	ChannelSftp sftpChannel;
	private List<String[]> LineList;
	private String lpwd;

	@Override
	public void beforeStep(StepExecution stepExecution) {
		String name = stepExecution.getStepName();
		logger.info("name: " + name);

		try {
			if (name != null && name.equalsIgnoreCase("readSFTPSummaryFIle")) {
				sftpChannel = sftpConnectionManager.getSftpChannel(ftpConfig);
			} else {

				sftpChannel = sftpConnectionManager.getSftpChannel();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("Summary Reader initialized.");
	}

	public void createList(String directory, ChannelSftp sftpChannel, FileLsEntryFilter fileFilter, Set result)
			throws SftpException {
		sftpChannel.ls(directory, fileFilter);
		Set<LsEntry> list = fileFilter.getResult();
		for (final LsEntry file : list) {
			if (".".equals(file.getFilename()) || "..".equals(file.getFilename())) {
				continue;
			}
			if (file.getAttrs().isDir()) {
				fileFilter.setRootDir(directory + File.separator + file.getFilename());
				fileFilter.setResult(new LinkedHashSet<>());
				createList(directory + File.separator + file.getFilename(), sftpChannel, fileFilter, result);
			} else {
				result.add(new RemotePullFile(file, directory));
			}
		}
	}

	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

		String fileFullPath = "test";
		final Path pathToCreate = Paths.get(ftpConfig.getLocal_src_dir(), fileFullPath).toAbsolutePath();
		if (sftpChannel != null && sftpChannel.isConnected()) {
			Path parent = pathToCreate.getParent();
			if (parent != null) {
				try {
					Files.createDirectories(parent);
				} catch (IOException e) {
					log.info("Parent Directory exists, continued...");
				}
			}
			// lpwd = sftpChannel.lpwd();
			// sftpChannel.lcd(ftpConfig.getLocal_dst_dir());
			sftpChannel.get(ftpConfig.getRemote_src_dir(), pathToCreate.toString(), null, ChannelSftp.OVERWRITE);
			int exitStatus = sftpChannel.getExitStatus();
			/*
			 * if (getDelayedExitStatus(exitStatus) == -1) { log.debug(
			 * "GET file operation completed with a wrong exit status"); }
			 */
			sftpChannel.exit();
		} else {
			log.warn("Unable to connect to SFTP server, Channel not created");
			log.info("Failed to GET {}", fileFullPath);
			sftpChannel.exit();
		}
		// LineList = org.apache.commons.io.FileUtils.readLines(new
		// File(pathToCreate.toString()));
		Reader reader = new FileReader(new File(pathToCreate.toString()));
		LineList = CsvReaderUtil.readAll(reader);

		return RepeatStatus.FINISHED;
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		stepExecution.getJobExecution().getExecutionContext().put("SUMMARYREPORT", this.LineList);
		logger.debug("Lines Reader ended.");
		sftpConnectionManager.releaseChannel(sftpChannel);
		return ExitStatus.COMPLETED;
	}
}