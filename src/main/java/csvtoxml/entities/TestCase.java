package csvtoxml.entities;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XStreamAlias("test-case")
@XmlAccessorType(XmlAccessType.FIELD)
public class TestCase {
    @XmlElement(name = "row")
    private RowOutput row;

    public TestCase(RowOutput row) {
        this.row = row;
    }

    public TestCase() {}

    public RowOutput getRow() {
        return row;
    }

    public void setRow(RowOutput row) {
        this.row = row;
    }
}
