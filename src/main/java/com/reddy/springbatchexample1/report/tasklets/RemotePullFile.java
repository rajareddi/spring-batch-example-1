package com.reddy.springbatchexample1.report.tasklets;

import com.jcraft.jsch.ChannelSftp.LsEntry;

public class RemotePullFile {
	LsEntry file;
	String directory;

	public RemotePullFile(LsEntry file, String directory) {
		this.file = file;
		this.directory = directory;
	}

}
