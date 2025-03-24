package csvtoxml.entities;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XStreamAlias("test-case")
public class TestCase {
    @XStreamAlias("test-cases")
    @XStreamImplicit
    private List<RowOutput> testCases;

    @XStreamAlias("start")
    @XStreamAsAttribute
    private String start;

    @XStreamAlias("status")
    @XStreamAsAttribute
    private String status;

    @XStreamAlias("stop")
    @XStreamAsAttribute
    private String stop;

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
    @XStreamImplicit
    private List<Label> labels;

    @XStreamImplicit(itemFieldName = "parameter")
    List<Parameter> parameters;
    public TestCase() {
    }

    public List<RowOutput> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<RowOutput> testCases) {
        this.testCases = testCases;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
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

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }
}
