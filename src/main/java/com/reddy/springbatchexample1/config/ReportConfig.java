package com.reddy.springbatchexample1.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.reddy.springbatchexample1.report.tasklets.ConsoleWriter;
import com.reddy.springbatchexample1.report.tasklets.JdbcDataReader;
import com.reddy.springbatchexample1.report.tasklets.MargeProcessor;
import com.reddy.springbatchexample1.report.tasklets.SFTPFileReader;

@Configuration
@EnableBatchProcessing
public class ReportConfig {

	/*
	 * @Autowired
	 * 
	 * @Qualifier("reportJob") private JobBuilderFactory jobs;
	 * 
	 * @Autowired
	 * 
	 * @Qualifier(value="report_steps") private StepBuilderFactory steps;
	 */

	@Autowired
	private DataSource dataSource;

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
	public SFTPFileReader sftpFileReader() {
		return new SFTPFileReader();
	}

	@Bean
	@Qualifier(value = "reportGeneretor")
	public Job reportJob() throws Exception {
		return new JobBuilderFactory(this.jobRepository()).get("reportGeneretor").start(readSFTPFIle())
				.next(readDataFromDB()).next(mergeData()).next(writeSFTpFile()).build();
	}

	private Step writeSFTpFile() throws Exception {
		return new StepBuilderFactory(this.jobRepository(), transactionManager()).get("reportWriter")
				.tasklet(reportWriter()).build();
	}

	private ConsoleWriter reportWriter() {

		return new ConsoleWriter();
	}

	private Step mergeData() throws Exception {
		return new StepBuilderFactory(this.jobRepository(), transactionManager()).get("mergeLogic")
				.tasklet(mergeSftpAndDBdata()).build();
	}

	private MargeProcessor mergeSftpAndDBdata() {
		return new MargeProcessor();
	}

	private Step readDataFromDB() throws Exception {
		return new StepBuilderFactory(this.jobRepository(), transactionManager()).get("readDataFromDB")
				.tasklet(databaseReader()).build();
	}

	private JdbcDataReader databaseReader() {
		JdbcDataReader jJdbcDataReader = new JdbcDataReader();
		jJdbcDataReader.setDataSource(dataSource);
		return jJdbcDataReader;
	}

	private Step readSFTPFIle() throws Exception {
		return new StepBuilderFactory(this.jobRepository(), transactionManager()).get("readSFTPFile")
				.tasklet(sftpFileReader()).build();
	}

}