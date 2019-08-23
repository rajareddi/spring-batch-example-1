package com.reddy.springbatchexample1.report.tasklets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.reddy.springbatchexample.utils.FileUtils;
import com.reddy.springbatchexample1.model.Line;
import com.reddy.springbatchexample1.model.User;
import com.reddy.springbatchexample1.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

@Component
public class JdbcDataReader implements Tasklet, StepExecutionListener {

	private final Logger logger = LoggerFactory.getLogger(JdbcDataReader.class);

	List<User> result = new ArrayList<User>();

	private DataSource dataSource;
	private String sql;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {

		logger.debug("JdbcDataReader initialized.");
	}

	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
		JdbcTemplate myJDBC = new JdbcTemplate();
		myJDBC.setDataSource(dataSource);
		result = myJDBC.query("SELECT * FROM   public.\"User\";", new UserMapper());
		System.out.println("Number of records effected: " + result);
		return RepeatStatus.FINISHED;
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		stepExecution.getJobExecution().getExecutionContext().put("userList", this.result);
		logger.debug("Lines Reader ended.");
		return ExitStatus.COMPLETED;
	}
}