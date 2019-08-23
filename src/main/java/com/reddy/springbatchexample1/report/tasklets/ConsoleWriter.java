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

import java.util.List;

public class ConsoleWriter implements Tasklet, StepExecutionListener {

	private final Logger logger = LoggerFactory.getLogger(ConsoleWriter.class);

	private List<String> lines;
	private FileUtils fu;

	@Override
	public void beforeStep(StepExecution stepExecution) {
		ExecutionContext executionContext = stepExecution.getJobExecution().getExecutionContext();
		this.lines = (List<String>) executionContext.get("outPutList");

	}

	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
		for (String line : lines) {
			System.out.println("Merged OUTPUT " + line);
			logger.debug("Wrote line " + line.toString());
		}
		return RepeatStatus.FINISHED;
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		logger.debug("Succesfully complted process ");
		stepExecution.getJobExecution().getExecutionContext().clearDirtyFlag();
		return ExitStatus.COMPLETED;
	}
}