package com.example.demo.entities;

import lombok.*;

//import java.text.SimpleDateFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Row {

    public String funcion;
    public String tipo;
    public String script;
    public String prueba;
    public String resultado;
    //public SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    public String formato;
    public String ambiente;
    public String evidencia;
}
