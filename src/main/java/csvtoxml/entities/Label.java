package csvtoxml.entities;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("label")
public class Label {

    @XStreamAlias("name")
    @XStreamAsAttribute
    private String nombre;

    @XStreamAlias("value")
    @XStreamAsAttribute
    private String valor;

    public Label(String nombre, String valor) {
        this.nombre = nombre;
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}

