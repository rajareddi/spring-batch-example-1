package com.reddy.springbatchexample1.utils;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.ChannelSftp.LsEntrySelector;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileLsEntryFilter implements LsEntrySelector {
	private @Getter @Setter String rootDir;
	private @Getter @Setter Set<LsEntry> result;
	@Override
	public int select(LsEntry entry) {
		if (!entry.getFilename().equals(".") && !entry.getFilename().equals("..")) {
			result.add(entry);
		}
		return CONTINUE;
	}
}
