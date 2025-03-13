package com.example.demo.services;

import java.io.FileInputStream;
import java.io.IOException;


public class XlsxService implements ConversionService{

    String file;
    @Override
    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public void readFile() throws IOException {
        System.out.println("Se convirito el archivo" + file + " a .xml");
    }


//    public void readFile(){
//        readXlsxFile("src/main/resources/csvFiles/worksheet.xlsx");
//    }

//    private void readXlsxFile(String file) {
//
//        try {
//
//            XSSFWorkbook work = new XSSFWorkbook(new FileInputStream(file));
//        }
//        catch (Exception e) {
//
//        }
//    }


}
