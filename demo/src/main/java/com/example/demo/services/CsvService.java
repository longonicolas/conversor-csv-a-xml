package com.example.demo.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CsvService implements ConversionService {

    String file = "";
    BufferedReader reader;
    String line = "";

    public void setFile(String file) {
        this.file = file;
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
