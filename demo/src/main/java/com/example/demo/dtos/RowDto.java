package com.example.demo.dtos;

public class RowDto {
        private String funcion;
        private String tipo;
        private String script;
        private String prueba;
        private String resultado;
        private String fechaHora;
        private String ambiente;
        private String evidencia;

        // Constructor
        public RowDto(String funcion, String tipo, String script, String prueba, String resultado, String fechaHora, String ambiente, String evidencia) {
            this.funcion = funcion;
            this.tipo = tipo;
            this.script = script;
            this.prueba = prueba;
            this.resultado = resultado;
            this.fechaHora = fechaHora;
            this.ambiente = ambiente;
            this.evidencia = evidencia;
        }

        // Getters
        public String getFuncion() { return funcion; }
        public String getTipo() { return tipo; }
        public String getScript() { return script; }
        public String getPrueba() { return prueba; }
        public String getResultado() { return resultado; }
        public String getFechaHora() { return fechaHora; }
        public String getAmbiente() { return ambiente; }
        public String getEvidencia() { return evidencia; }


}
