package com.reddy.springbatchexample1.report.tasklets;

import java.util.ArrayList;
import java.util.List;

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

import com.reddy.springbatchexample1.model.User;

public class MargeProcessor implements Tasklet, StepExecutionListener {

	private final Logger logger = LoggerFactory.getLogger(MargeProcessor.class);

	private List<String[]> requestData;
	List<User> result;

	List<String[]> summaryData;
	List<User> dbData;

	List<String[]> outPut = new ArrayList<>();

	// Pending
	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
		outPut = new ArrayList<>();
		for (String[] line : requestData) {
			System.out.println(line.toString());
			outPut.add(line);
		}

		for (String[] line : summaryData) {
			System.out.println(line);
		}
		outPut = requestData;
		return RepeatStatus.FINISHED;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void beforeStep(StepExecution stepExecution) {

		ExecutionContext executionContext = stepExecution.getJobExecution().getExecutionContext();
		String name = stepExecution.getStepName();
		logger.debug("name: " + name);
		this.requestData = (List<String[]>) executionContext.get("REQUESTSOURCE");
		this.summaryData = (List<String[]>) executionContext.get("SUMMARYREPORT");
		this.dbData = (List<User>) executionContext.get("DBDATA");

		logger.debug("Lines Processor initialized.");
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		logger.debug("Lines Processor ended.");
		stepExecution.getJobExecution().getExecutionContext().put("RESPONSE", this.outPut);
		return ExitStatus.COMPLETED;
	}
}