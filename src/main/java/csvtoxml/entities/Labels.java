package csvtoxml.entities;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

@XStreamAlias("labels")
public class Labels {
    @XStreamAlias("labels")
    @XStreamImplicit(itemFieldName = "label")
    List<Label> labels;

    public Labels(List<Label> labels) {
        this.labels = labels;
    }
}
