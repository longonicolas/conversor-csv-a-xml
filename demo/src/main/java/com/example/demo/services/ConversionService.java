package com.example.demo.services;

import java.io.IOException;

public interface ConversionService{
    public void setFile(String file);
    public void readFile()throws IOException;
}
