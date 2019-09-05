package com.reddy.springbatchexample1.sftp;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Setter
@Getter
public abstract class SftpClinetConfig {

	private String host;

	private String port;

	private String user;

	private String password;

	private String auth_type;

	private String key_string;
	private String key_location;

	private String poll_enabled;

	private String poll_interval;

	private String remote_src_dir;

	private String remote_dst_dir;

	private String local_src_dir;

	private String local_dst_dir;

	private String ignored_file_ext;
	private String ignored_file;

	private String max_history_lines;

	private String enable_remote = Boolean.TRUE.toString();

}