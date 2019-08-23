package com.reddy.springbatchexample1.sftp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DemoSFTPConnectionManager {
	@Autowired private DemoSFTPChannelFactory sftpChannelFactory;

	public synchronized ChannelSftp getSftpChannel() {
		ChannelSftp sftpChannel = null;
		try {
			sftpChannel = sftpChannelFactory.createSftpChannel();
		} catch (Exception e) {
			log.warn("WARNING : Unable to create SFTP Channel. Please verify Connection properties and restart.");
			log.debug(e.getMessage(),e);
		}
		return sftpChannel;
	}
	
	public synchronized void releaseChannel(ChannelSftp sftpChannel) {
		try {
			if (sftpChannel != null) {
				Session session = sftpChannel.getSession();
				if (!sftpChannel.isClosed()) {
					sftpChannel.quit();
				}
				if ( session != null && session.isConnected() ) {
					session.disconnect();
				}
			}
		} catch (JSchException e) {
			log.warn("WARNING : Unable release Channel / Session. System performance may degrade, restart if required. ");
			log.debug(e.getMessage(),e);
		}
	}
}
