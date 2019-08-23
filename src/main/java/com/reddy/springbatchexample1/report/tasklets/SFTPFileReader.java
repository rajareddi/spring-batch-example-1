package com.reddy.springbatchexample1.report.tasklets;

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
import com.reddy.springbatchexample.utils.FileUtils;
import com.reddy.springbatchexample1.model.Line;
import com.reddy.springbatchexample1.sftp.DemoSFTPChannelFactory;
import com.reddy.springbatchexample1.sftp.DemoSFTPClientConfig;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class SFTPFileReader implements Tasklet, StepExecutionListener {

	@Autowired
	DemoSFTPChannelFactory demoSFTPChannelFactory;
	@Autowired
	private DemoSFTPClientConfig ftpConfig;
	private final Logger logger = LoggerFactory.getLogger(SFTPFileReader.class);

	ChannelSftp sftpChannel;
	private Logger log;
	private List<String> LineList;
	private String lpwd;

	@Override
	public void beforeStep(StepExecution stepExecution) {
		try {
			sftpChannel = demoSFTPChannelFactory.createSftpChannel();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("Lines Reader initialized.");
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
		LineList = org.apache.commons.io.FileUtils.readLines(new File(pathToCreate.toString()));

		return RepeatStatus.FINISHED;
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		stepExecution.getJobExecution().getExecutionContext().put("lines", this.LineList);
		logger.debug("Lines Reader ended.");
		return ExitStatus.COMPLETED;
	}
}