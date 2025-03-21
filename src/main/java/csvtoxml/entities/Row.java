package csvtoxml.entities;



import lombok.Data;

//@XmlRootElement(name = "row")
//@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Row {

    //@XmlElement(name = "funcion")
    private String funcion;

    //@XmlElement(name = "tipo")
    private String tipo;

    //@XmlElement(name = "script")
    private String script;

    //@XmlElement(name = "prueba")
    private String prueba;

    //@XmlElement(name = "resultado")
    private String resultado;

    //@XmlElement(name = "formato")
    private String formato;

    //@XmlElement(name = "ambiente")
    private String ambiente;

    //@XmlElement(name = "evidencia")
    private String evidencia;

    public Row() {
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getPrueba() {
        return prueba;
    }

    public void setPrueba(String prueba) {
        this.prueba = prueba;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    public String getEvidencia() {
        return evidencia;
    }

    public void setEvidencia(String evidencia) {
        this.evidencia = evidencia;
    }
}