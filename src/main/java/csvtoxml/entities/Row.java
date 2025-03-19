package csvtoxml.entities;



import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "row")
@Data
@NoArgsConstructor
public class Row {

    private String funcion;

    private String tipo;

    private String script;

    private String prueba;

    private String resultado;

    private String formato;

    private String ambiente;

    private String evidencia;


    @XmlElement
    public String getFuncion() {
        return funcion;
    }

    @XmlElement
    public String getTipo() {
        return tipo;
    }

    @XmlElement
    public String getScript() {
        return script;
    }

    @XmlElement
    public String getPrueba() {
        return prueba;
    }

    @XmlElement
    public String getResultado() {
        return resultado;
    }

    @XmlElement
    public String getFormato() {
        return formato;
    }

    @XmlElement
    public String getAmbiente() {
        return ambiente;
    }

    @XmlElement
    public String getEvidencia() {
        return evidencia;
    }
}
