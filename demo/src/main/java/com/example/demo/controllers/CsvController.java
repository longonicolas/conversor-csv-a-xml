package com.example.demo.controllers;

import com.example.demo.dtos.CsvDTO;
import com.example.demo.services.ICsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/CSV")
public class CsvController {

    @Autowired
    private ICsvService csvService;


    /*
    @PostMapping
    public ResponseEntity<CsvDTO> cargarCSV(@RequestBody CsvInputDTO csvInputDTODTO) {
        csvService.crearObjetoCSV(csvInputDTO);
    }
    */


}
