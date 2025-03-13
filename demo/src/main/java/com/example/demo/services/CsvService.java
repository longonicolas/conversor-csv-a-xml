package com.example.demo.services;

import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class CsvService implements ICsvService {


    String file;
    BufferedReader reader;
    String line;

    public CsvService() {
        this.file = "src/main/resources/csvFiles/csv_demo.csv";
        this.reader = null;
        this.line = "";
    }

    public void readFile() throws IOException {
        System.out.println("Ruta absoluta del archivo: " + new File(file).getAbsolutePath());
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

                for (String index : data) {
                    System.out.println(index);
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            reader.close();
        }
    }

}
