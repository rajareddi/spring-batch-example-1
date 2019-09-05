package com.reddy.springbatchexample1.sftp;

import java.io.File;
import java.util.LinkedHashSet;
import java.util.Set;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.reddy.springbatchexample1.report.tasklets.RemotePullFile;
import com.reddy.springbatchexample1.utils.FileLsEntryFilter;


/**
 * 
 * @author javagists.com
 *
 */
public class DownloadFileSFTP {

	public static void main(String[] args) throws Exception {

		JSch jsch = new JSch();
		Session session = null;
		try {
			session = jsch.getSession("adobeteam", "ftp.csg-cc.com", 22);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword("adobeteam@123");
			session.connect();

			Channel channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp sftpChannel = (ChannelSftp) channel;
			String directory = "/home/sftpusers/adobeteam/workforce2/encrypt";
			FileLsEntryFilter fileFilter = new FileLsEntryFilter();
			Set set = new LinkedHashSet<>();
			/*createList(directory, sftpChannel, fileFilter, set);
			set.stream().forEach(e -> {
				System.out.print(e);
			});*/
			for (Object entry : sftpChannel.ls("/home/sftpusers/adobeteam/workforce2/encrypt")) {
			    System.out.println(entry);
			}
			sftpChannel.get("/home/sftpusers/adobeteam/workforce2/encrypt/SampleUserRecords1000.csv",
					"./testDownload.txt");
			sftpChannel.exit();
			session.disconnect();
		} catch (JSchException e) {
			e.printStackTrace();
		} catch (SftpException e) {
			e.printStackTrace();
		}

	}

	public static void createList(String directory, ChannelSftp sftpChannel, FileLsEntryFilter fileFilter, Set result)
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

}