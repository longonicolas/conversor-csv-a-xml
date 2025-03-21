package csvtoxml.entities;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

import java.util.List;

@XStreamAlias("test-cases")
@XmlAccessorType(XmlAccessType.FIELD)
public class TestSuite {
    @XmlElement(name = "funcion")
    RowOutput row;

    public TestSuite(RowOutput row) {
        this.row = row;
    }

    public RowOutput getRows() {
        return row;
    }

    public void setRows(RowOutput rows) {
        this.row = rows;
    }
}
