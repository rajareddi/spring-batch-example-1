package com.reddy.springbatchexample1.sftp;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Component
@ConfigurationProperties("sftp.inbound.source2")
@Setter
@Getter
@Validated
public class SFTPSummaryClientConfig extends SftpClinetConfig {

	@NotBlank
	private String host;

	@NotBlank
	private String port;

	@NotBlank
	private String user;

	private String password;

	@NotBlank
	private String auth_type;

	private String key_string;
	private String key_location;

	/*@NotBlank
	private String poll_enabled;

	@NotBlank
	private String poll_interval;*/

	@NotBlank
	private String remote_src_dir;

	@NotBlank
	private String remote_dst_dir;

	@NotBlank
	private String local_src_dir;

	@NotBlank
	private String local_dst_dir;

	private String ignored_file_ext;
	private String ignored_file;

	private String max_history_lines;

	/** The enable_remote polling indicator. Default enabled. */
	private String enable_remote = Boolean.TRUE.toString();

}