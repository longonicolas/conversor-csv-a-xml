package com.example.demo;

import com.example.demo.services.CsvService;
import com.example.demo.services.FileService;
import com.example.demo.services.ToXmlService;
import com.example.demo.services.XlsxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws IOException {

		SpringApplication.run(DemoApplication.class, args);


		FileService file1 = new FileService("src/main/resources/csvFiles/worksheet1.csv");
		file1.execute();

		//FileService file2 = new FileService("src/main/resources/csvFiles/worksheet.xlsx");
		//file2.execute();

		//ToXmlService toXml = new ToXmlService();
		//toXml.createDocument();


	}

}
