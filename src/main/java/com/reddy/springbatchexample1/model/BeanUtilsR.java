package com.reddy.springbatchexample1.model;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.reddy.springbatchexample1.SpringBatchExample1Application;

@SpringBootApplication
public class BeanUtilsR {
	private static final String OUTPUT_FILE = "output/out.txt";
	
	
	public void perform() throws Exception {
		JobParameters params = new JobParametersBuilder().addString("JobID", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();
		//jobLauncher.run(job, params);
	}

	
	public FlatFileItemWriter<ResponseEntity>  writer() {
		Path path = Paths.get("response2.txt");
		List<ResponseEntity> list = new ArrayList<>();
		ResponseEntity responseEntity = new ResponseEntity();
		responseEntity.setAccount_id("1234");
		responseEntity.setEntity_id("En_123");
		responseEntity.setEvent_id("EnV_123");
		list.add(responseEntity);

		ResponseEntity responseEntity2 = new ResponseEntity();
		responseEntity2.setAccount_id("21234");
		responseEntity2.setEntity_id("En2_123");
		responseEntity2.setEvent_id("EnV2_123");
		list.add(responseEntity2);
		FlatFileItemWriter<ResponseEntity> writer = new FlatFileItemWriter<>();
		writer.setResource(new FileSystemResource(OUTPUT_FILE));

		// All job repetitions should "append" to same output file
		writer.setAppendAllowed(true);

		// Name field values sequence based on object properties
		writer.setLineAggregator(new DelimitedLineAggregator<ResponseEntity>() {
			{
				setDelimiter(",");
				setFieldExtractor(new BeanWrapperFieldExtractor<ResponseEntity>() {
					{
						setNames(new String[] { "id", "firstName", "lastName" });
					}
				});
			}
		});
		return writer;
	}

}
