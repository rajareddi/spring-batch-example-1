package com.reddy.springbatchexample1.utils;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.beanio.BeanReader;
import org.beanio.StreamFactory;

public class BeanIoMain
{  
    public void readCSVFileUsingBeanIo()
    {
        StreamFactory factory = StreamFactory.newInstance();
        factory.loadResource("person.xml");
        
        //read it from the classpath : src/main/resources
        InputStream in = BeanIoMain.class.getClass().getResourceAsStream("/data/person.csv");
        BeanReader reader = factory.createReader("persons", new InputStreamReader(in));
        Object record = null;
        List<Person> persons = new ArrayList<Person>();
        
        // read records from "input.csv"
        while ((record = reader.read()) != null)
        {
            if ("header".equals(reader.getRecordName()))
            {
                
                @SuppressWarnings("unchecked")
                Map<String, Object> header = (Map<String, Object>) record;
                System.out.println(header.get("fileDate"));
            }
            else if ("detail".equals(reader.getRecordName()))
            {
                Person person = (Person) record;
                persons.add(person);
            }
        }
        
        System.out.println(persons);
    }
    
    public static void main(String[] args)
    {
        new BeanIoMain().readCSVFileUsingBeanIo();
    }
}
