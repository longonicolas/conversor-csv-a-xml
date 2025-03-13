package com.example.demo;

import com.example.demo.services.CsvService;
import com.example.demo.services.FileService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws IOException {

		SpringApplication.run(DemoApplication.class, args);

		FileService fileConv = new FileService("src/main/resources/csvFiles/csv_demo.xslx");
		fileConv.execute();

	}

}
