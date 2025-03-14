package com.example.demo;

import com.example.demo.services.CsvReaderStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws Exception {

		SpringApplication.run(DemoApplication.class, args);



	}

}
