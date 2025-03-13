package com.example.demo.services;

import java.io.IOException;
import java.util.Objects;

public class FileService {
    String file;
    ConversionService service;

    public FileService(String file) {
        this.file = file;
        setService();
    }

    public String getFileExtension() {
        int lastIndex = file.lastIndexOf(".");
        return (lastIndex == -1 || lastIndex == file.length() - 1) ? "" : file.substring(lastIndex + 1);
    }

    public void setService(){
        if(Objects.equals(getFileExtension(), "csv")){
            this.service = new CsvService();
        }
        if(Objects.equals(getFileExtension(), "xslx")){
            this.service = new XlsxService();
        }
    }

    public void execute() throws IOException {
        service.setFile(file);
        service.readFile();
    }
}
