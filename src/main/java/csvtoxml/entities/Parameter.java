package csvtoxml.entities;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("parameter")
public class Parameter {
    @XStreamAlias("kind")
    @XStreamAsAttribute
    String kind;
    @XStreamAlias("name")
    @XStreamAsAttribute
    String name;
    @XStreamAlias("value")
    @XStreamAsAttribute
    String value;

    public Parameter(String kind, String name, String value) {
        this.kind = kind;
        this.name = name;
        this.value = value;
    }
}
