package csvtoxml.entities;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

@XStreamAlias("parameters")
public class Parameters {
    @XStreamAlias("parameters")
    @XStreamImplicit(itemFieldName = "parameter")
    List<Parameter> parameters;

    public Parameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }
}
