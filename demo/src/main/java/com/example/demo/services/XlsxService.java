package com.example.demo.services;

import java.io.FileInputStream;


public class XlsxService implements IXslxService{


    public void readXlsx(){
        readXlsxFile("src/main/resources/csvFiles/worksheet.xlsx");
    }

    private void readXlsxFile(String file) {

        try {

            XSSFWorkbook work = new XSSFWorkbook(new FileInputStream(file));
        }
        catch (Exception e) {

        }
    }


}
