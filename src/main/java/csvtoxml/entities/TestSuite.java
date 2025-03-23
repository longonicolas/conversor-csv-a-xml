package csvtoxml.entities;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@XStreamAlias("test-cases")
@XmlAccessorType(XmlAccessType.FIELD)
public class TestSuite {
    @XmlElement(name = "name")
    private String name = "DWP";

    @XmlElement(name = "title")
    private String title = "DWP";

    private RowOutput testCase;

    public TestSuite() {
    }

    public RowOutput getTestCase() {
        return testCase;
    }

    public void setTestCase(RowOutput testCase) {
        this.testCase = testCase;
    }
}
