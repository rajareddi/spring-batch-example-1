package com.reddy.springbatchexample1.report.tasklets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

import com.reddy.springbatchexample.utils.FileUtils;
import com.reddy.springbatchexample1.model.Line;
import com.reddy.springbatchexample1.utils.CsvWriterUtil;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.List;

public class ConsoleWriter implements Tasklet, StepExecutionListener {

	private final Logger logger = LoggerFactory.getLogger(ConsoleWriter.class);

	private List<String[]> responseData;

	@Override
	public void beforeStep(StepExecution stepExecution) {
		ExecutionContext executionContext = stepExecution.getJobExecution().getExecutionContext();
		this.responseData = (List<String[]>) executionContext.get("RESPONSE");

	}

	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
		Path path = Paths.get("response.txt");
		Files.deleteIfExists(path);
		path = Files.createFile(path);

		for (String[] line : responseData) {
			System.out.println("Merged OUTPUT " + line);
			logger.debug("Wrote line " + line.toString());
		}
		CsvWriterUtil.csvWriterAll(responseData, path);
		return RepeatStatus.FINISHED;
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		logger.debug("Succesfully complted process ");
		stepExecution.getJobExecution().getExecutionContext().clearDirtyFlag();
		return ExitStatus.COMPLETED;
	}
}