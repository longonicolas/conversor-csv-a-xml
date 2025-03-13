package com.example.demo.controllers;

import com.example.demo.services.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/CSV")
public class CsvController {

    @Autowired
    private ConversionService csvService;


    /*
    @PostMapping
    public ResponseEntity<CsvDTO> cargarCSV(@RequestBody CsvInputDTO csvInputDTODTO) {
        csvService.crearObjetoCSV(csvInputDTO);
    }
    */


}
