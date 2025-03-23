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

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
