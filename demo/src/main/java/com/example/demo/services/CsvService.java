package com.example.demo.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

        List<List<String>> matriz = new ArrayList<>();

        //System.out.println("Ruta absoluta del archivo: " + new File(file).getAbsolutePath()); //log
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

                List<String> fila = new ArrayList<>();

                for (String cell : data) {
                    fila.add(cell.trim());
                    System.out.println(cell);
                }
                matriz.add(fila);
                System.out.println();
            }

            recorrerMatriz(matriz);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //springbatch

    public void recorrerMatriz(List<List<String>> matriz) {
        for (List<String> fila : matriz) {
            for (String celda : fila) {
                System.out.printf("%-30s", celda); // 30 caracteres de ancho por celda
            }
            System.out.println();
        }
    }


}
