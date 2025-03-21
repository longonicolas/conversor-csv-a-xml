package csvtoxml.entities;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

import java.util.ArrayList;
import java.util.List;

@XStreamAlias("test-cases")
@XmlAccessorType(XmlAccessType.FIELD)
public class TestSuite {

    @XStreamImplicit(itemFieldName = "test-case") // Indica que es una lista de <test-case>
    private List<TestCase> testCases = new ArrayList<>();

    public TestSuite() {
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
    }

    public void addTestCase(TestCase testCase) {
        this.testCases.add(testCase);
    }
}
