package com.techprimers.springbatchexample1.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.techprimers.springbatchexample1.tasklets.LineReaderTwo;
import com.techprimers.springbatchexample1.tasklets.LinesProcessor;
import com.techprimers.springbatchexample1.tasklets.LinesReader;
import com.techprimers.springbatchexample1.tasklets.LinesWriter;

@Configuration
@EnableBatchProcessing
public class TaskletsConfig {

	@Autowired
	private JobBuilderFactory jobs;

	@Autowired
	private StepBuilderFactory steps;

	@Bean
	public JobLauncherTestUtils jobLauncherTestUtils() {
		return new JobLauncherTestUtils();
	}

	@Bean
	public JobRepository jobRepository() throws Exception {
		MapJobRepositoryFactoryBean factory = new MapJobRepositoryFactoryBean();
		factory.setTransactionManager(transactionManager());
		return (JobRepository) factory.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new ResourcelessTransactionManager();
	}

	@Bean
	public JobLauncher jobLauncher() throws Exception {
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(jobRepository());
		return jobLauncher;
	}

	@Bean
	public LinesReader linesReader() {
		return new LinesReader();
	}

	@Bean
	public LinesProcessor linesProcessor() {
		return new LinesProcessor();
	}

	@Bean
	public LinesWriter linesWriter() {
		return new LinesWriter();
	}

	@Bean
	protected Step readLines() {
		return steps.get("readLines").tasklet(linesReader()).build();
	}

	@Bean
	protected Step processLines() {
		return steps.get("processLines").tasklet(linesProcessor()).build();
	}

	@Bean
	protected Step writeLines() {
		return steps.get("writeLines").tasklet(linesWriter()).build();
	}

	@Bean
	@Qualifier(value = "taskletsJob")
	public Job job() {
		return jobs.get("taskletsJob").start(readLines()).next(lineReaderTwo()).next(processLines()).next(writeLines())
				.build();
	}

	@Bean
	protected Step lineReaderTwo() {
		return steps.get("lineReaderTwo").tasklet(linesReaderTwo()).build();
	}

	@Bean
	public Tasklet linesReaderTwo() {
		return new LineReaderTwo();
	}

}