package com.example.demo.config;

import com.example.demo.steps.CsvReaderStep;
import com.example.demo.services.ToXmlService;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;

import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableBatchProcessing
public class BatchConfiguration {


    

    @Bean
    public CsvReaderStep csvReaderStep(){
        return new CsvReaderStep();
    }

}

