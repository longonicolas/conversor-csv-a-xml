package com.example.demo.config;

import com.example.demo.services.CsvReaderStep;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfiguration {

    @Bean
    public CsvReaderStep csvReaderStep(){
        return new CsvReaderStep();
    }
}

