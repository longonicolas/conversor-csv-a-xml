package csvtoxml.entities;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import javax.xml.bind.annotation.XmlElement;

@XStreamAlias("test-case")
public class TestCase {
    @XStreamAlias("start")
    @XStreamAsAttribute
    private String start;

    @XStreamAsAttribute
    private String status;

    @XStreamAlias("end")
    @XStreamAsAttribute
    private String end;

    @XmlElement(name = "funcion")
    private String funcion;

    @XmlElement(name = "tipo")
    private String tipo;

    @XmlElement(name = "script")
    private String script;

    @XmlElement(name = "prueba")
    private String prueba;

    @XmlElement(name = "resultado")
    private String resultado;

    @XmlElement(name = "formato")
    private String formato;

    @XmlElement(name = "ambiente")
    private String ambiente;

    @XmlElement(name = "evidencia")
    private String evidencia;

    @XStreamAlias("labels")
    private Labels labels;

    private Parameters parameters;

    public TestCase() {
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStop(String stop) {
        this.end = stop;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public void setScript(String script) {
        this.script = script;
    }

    public void setPrueba(String prueba) {
        this.prueba = prueba;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    public void setEvidencia(String evidencia) {
        this.evidencia = evidencia;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public void setLabels(Labels labels) {
        this.labels = labels;
    }
}
