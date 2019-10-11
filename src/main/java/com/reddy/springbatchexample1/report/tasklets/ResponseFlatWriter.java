package com.reddy.springbatchexample1.report.tasklets;

import org.beanio.StreamFactory;
import org.beanio.spring.BeanIOFlatFileItemWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.FileSystemResource;

import com.reddy.springbatchexample.utils.FileUtils;
import com.reddy.springbatchexample1.model.Line;
import com.reddy.springbatchexample1.model.ResponseEntity;
import com.reddy.springbatchexample1.utils.CsvWriterUtil;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.List;

public class ResponseFlatWriter implements Tasklet, StepExecutionListener {

	private static final String OUTPUT_FILE = "output/out.txt";

	private final Logger logger = LoggerFactory.getLogger(ResponseFlatWriter.class);

	private List<String[]> responseData;
	ExecutionContext executionContext;

	@Override
	public void beforeStep(StepExecution stepExecution) {
		executionContext = stepExecution.getJobExecution().getExecutionContext();
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
		BeanIOFlatFileItemWriter<ResponseEntity> writer = new BeanIOFlatFileItemWriter<>();
		writer.setResource(new FileSystemResource(OUTPUT_FILE));

		// All job repetitions should "append" to same output file
		writer.setAppendAllowed(true);
		String lineSeparator = "\r\n";
		writer.setLineSeparator(lineSeparator);
        StreamFactory factory = StreamFactory.newInstance();
		writer.setStreamFactory(factory);
		// Name field values sequence based on object properties
	
		writer.open(executionContext);
		writer.write(list);
		return RepeatStatus.FINISHED;
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		logger.debug("Succesfully complted process ");
		stepExecution.getJobExecution().getExecutionContext().clearDirtyFlag();
		return ExitStatus.COMPLETED;
	}
}