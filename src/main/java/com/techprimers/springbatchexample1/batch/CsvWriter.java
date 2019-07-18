package com.techprimers.springbatchexample1.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.techprimers.springbatchexample1.model.User;

public class CsvWriter implements ItemWriter<User> {

	private Resource outputResource = new FileSystemResource("output/outputData.txt");

	@Override
	public void write(List<? extends User> arg0) throws Exception {
		// Create writer instance
		FlatFileItemWriter<User> writer = new FlatFileItemWriter<>();

		// Set output file location
		writer.setResource(outputResource);

		// All job repetitions should "append" to same output file
		writer.setAppendAllowed(true);

		// Name field values sequence based on object properties
		writer.setLineAggregator(new DelimitedLineAggregator<User>() {
			{
				setDelimiter(",");
				setFieldExtractor(new BeanWrapperFieldExtractor<User>() {
					{
						setNames(new String[] { "id", "name", "dept", "salary", "time" });
					}
				});
			}
		});
	}

}
