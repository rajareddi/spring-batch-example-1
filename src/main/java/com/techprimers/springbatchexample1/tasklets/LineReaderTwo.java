package com.techprimers.springbatchexample1.tasklets;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class LineReaderTwo implements Tasklet, StepExecutionListener {
	private final Logger logger = LoggerFactory.getLogger(LinesReader.class);
	String constent;

	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		logger.info("Step 2 excetution");
		return RepeatStatus.FINISHED;
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		stepExecution.getJobExecution().getExecutionContext().put("constent", constent);
		logger.debug("Added constent value in step  2");
		return ExitStatus.COMPLETED;
	}

	@Override
	public void beforeStep(StepExecution arg0) {
		constent = new Date().toString();

	}

}
