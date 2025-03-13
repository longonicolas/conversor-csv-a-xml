package com.example.demo;

import com.example.demo.services.CsvService;
import com.example.demo.services.XlsxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws IOException {

		SpringApplication.run(DemoApplication.class, args);

		/*
		CsvService csvService;
		csvService = new CsvService();
		csvService.readFile();
		*/

		XlsxService service;
		service = new XlsxService();
		service.readFile();


	}

}
