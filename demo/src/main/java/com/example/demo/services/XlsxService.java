package com.example.demo.services;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;


public class XlsxService implements ConversionService{

    String file;

    public XlsxService() {
        super();
    }

    @Override
    public void setFile(String file) {
        this.file = file;
    }

    public void readFile(){
        readXlsxFile(file);
    }

    private void readXlsxFile(String file) {

        try {
            XSSFWorkbook work = new XSSFWorkbook(new FileInputStream(file));

            XSSFSheet sheet = work.getSheetAt(0);
            int totalRows = sheet.getLastRowNum(); //TOTAL FILAS



            for (int i = 0; i <= totalRows; i++) {

                XSSFRow row = sheet.getRow(i);
                if (row == null) continue;

                int totalColumns = row.getLastCellNum(); // TOTAL COLUMNAS DE FILA
                StringBuilder celda = new StringBuilder();


                for (int j = 0; j < totalColumns; j++) {
                    XSSFCell cell = row.getCell(j);

                    if (cell != null) {
                        celda.append(cell).append(" | ");
                    }
                }
                System.out.println(celda);

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


}
