package csvtoxml.entities;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "row")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Row {
    private String funcion;
    private String tipo;
    private String script;
    private String prueba;
    private String resultado;
    private String formato;
    private String ambiente;
    private String evidencia;

    public Row() {
    }

    public String getFuncion() {
        return funcion;
    }

    public String getTipo() {
        return tipo;
    }

    public String getScript() {
        return script;
    }

    public String getPrueba() {
        return prueba;
    }

    public String getResultado() {
        return resultado;
    }

    public String getFormato() {
        return formato;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public String getEvidencia() {
        return evidencia;
    }
}


