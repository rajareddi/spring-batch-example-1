package com.reddy.springbatchexample.sftp;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import ciphercloud.crp.util.BakedInKey;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DemoSFTPChannelFactory {

	private static final String SFTP = "sftp";
	private JSch privateKeyJsch = null;
	private JSch passwordJsch = null;
	@Autowired
	private DemoSFTPClientConfig ftpConfig;

	@PostConstruct
	public void initSession() {
		privateKeyJsch = new JSch();
		passwordJsch = new JSch();
	}

	public ChannelSftp createSftpChannel() throws Exception {
		String authType = ftpConfig.getAuth_type();
		if ("password".equalsIgnoreCase(authType)) {
			return createPasswordChannel();
		} else if ("privateKey".equalsIgnoreCase(authType)) {
			return createPrivateKeyChannel();
		}
		return null;
	}

	private ChannelSftp createPasswordChannel() throws Exception {
		return createChannel(createPasswordSession());
	}

	private ChannelSftp createPrivateKeyChannel() throws Exception {
		return createChannel(createPrivateKeySession());
	}

	private Session createPrivateKeySession() throws Exception {
		try {
			String username = ftpConfig.getUser();
			String host = ftpConfig.getHost();
			byte[] privateKey = getPrivateKey(ftpConfig.getKey_string());
			int port = getPortInt(ftpConfig.getPort());
			privateKeyJsch.addIdentity("ccftpKey", privateKey, null, null);
			Session session = privateKeyJsch.getSession(username, host, port);
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect(10000);
			return session;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	private Session createPasswordSession() throws Exception {
		try {
			String username = ftpConfig.getUser();
			String password = ftpConfig.getPassword();// .BakedInKey.decrypt(ftpConfig.getPassword());
			String host = ftpConfig.getHost();
			int port = getPortInt(ftpConfig.getPort());
			Session session = passwordJsch.getSession(username, host, port);
			session.setPassword(password);
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect(10000);
			return session;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	private ChannelSftp createChannel(Session session) throws Exception {
		ChannelSftp sftpChannel = null;
		try {
			sftpChannel = (ChannelSftp) session.openChannel(SFTP);
			sftpChannel.connect(120000);
		} catch (JSchException e) {
			throw new Exception(e);
		}
		return sftpChannel;
	}

	private int getPortInt(String port) throws NumberFormatException {
		try {
			return Integer.parseInt(port);
		} catch (NumberFormatException e) {
			throw e;
		}
	}

	private byte[] getPrivateKey(String priKey) {
		return priKey.getBytes();
	}
}