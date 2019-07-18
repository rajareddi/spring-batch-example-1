package com.techprimers.springbatchexample1.config;

import com.techprimers.springbatchexample1.model.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

	@Bean
	@Qualifier(value = "ETL-Load")
	public Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
			@Qualifier("multiResourceItemReader") ItemReader<User> multiResourceItemReader,
			ItemProcessor<User, User> itemProcessor,
			@Qualifier("consoleItemWriter") ItemWriter<User> consoleItemWriter) {

		Step step = stepBuilderFactory.get("ETL-file-load").<User, User> chunk(100).reader(multiResourceItemReader)
				.processor(itemProcessor).writer(consoleItemWriter).build();

		return jobBuilderFactory.get("ETL-Load").incrementer(new RunIdIncrementer()).start(step).build();
	}

	@Bean
	public FlatFileItemReader<User> itemReader(@Value("${input}") Resource resource) {
		FlatFileItemReader<User> flatFileItemReader = new FlatFileItemReader<>();
		flatFileItemReader.setResource(resource);
		flatFileItemReader.setName("CSV-Reader");
		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(lineMapper());
		return flatFileItemReader;
	}

	@Bean
	public LineMapper<User> lineMapper() {

		DefaultLineMapper<User> defaultLineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames(new String[] { "id", "name", "dept", "salary" });

		BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(User.class);

		defaultLineMapper.setLineTokenizer(lineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);

		return defaultLineMapper;
	}

	private Resource outputResource = new FileSystemResource("output/outputData.csv");
	@Value("${input2}")
	private Resource[] inputResources;

	/*
	 * @Bean(name = "newWrite") public FlatFileItemWriter<User> writer() { //
	 * Create writer instance FlatFileItemWriter<User> writer = new
	 * FlatFileItemWriter<>();
	 * 
	 * // Set output file location writer.setResource(outputResource);
	 * 
	 * // All job repetitions should "append" to same output file
	 * writer.setAppendAllowed(true);
	 * 
	 * // Name field values sequence based on object properties
	 * writer.setLineAggregator(new DelimitedLineAggregator<User>() { {
	 * setDelimiter(","); setFieldExtractor(new
	 * BeanWrapperFieldExtractor<User>() { { setNames(new String[] { "id",
	 * "firstName", "lastName" }); } }); } }); return writer; }
	 */

	@Bean
	@Qualifier(value = "multiResourceItemReader")
	public MultiResourceItemReader<User> multiResourceItemReader() {
		MultiResourceItemReader<User> resourceItemReader = new MultiResourceItemReader<User>();
		resourceItemReader.setResources(inputResources);
		resourceItemReader.setDelegate(reader());
		return resourceItemReader;
	}

	@Bean
	public FlatFileItemReader<User> reader() {
		// Create reader instance
		FlatFileItemReader<User> reader = new FlatFileItemReader<User>();

		// Set number of lines to skips. Use it if file has header rows.
		reader.setLinesToSkip(1);

		// Configure how each line will be parsed and mapped to different values
		reader.setLineMapper(lineMapper());
		return reader;
	}
}
