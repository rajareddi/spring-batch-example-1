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

import com.reddy.springbatchexample1.model.Line;
import com.reddy.springbatchexample1.model.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class MargeProcessor implements Tasklet, StepExecutionListener {

	private final Logger logger = LoggerFactory.getLogger(MargeProcessor.class);

	private List<String> lines;
	List<User> result;

	List<String> outPut = new ArrayList<>();

	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
		outPut = new ArrayList<>();
		for (String line : lines) {
			outPut.add(line.replace("002", "OOOOO2"));
		}
		return RepeatStatus.FINISHED;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void beforeStep(StepExecution stepExecution) {
		ExecutionContext executionContext = stepExecution.getJobExecution().getExecutionContext();
		this.lines = (List<String>) executionContext.get("lines");
		this.result = (List<User>) executionContext.get("userList");
		logger.debug("Lines Processor initialized.");
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		logger.debug("Lines Processor ended.");
		stepExecution.getJobExecution().getExecutionContext().put("outPutList", this.outPut);
		return ExitStatus.COMPLETED;
	}
}