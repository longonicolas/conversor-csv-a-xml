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
}

