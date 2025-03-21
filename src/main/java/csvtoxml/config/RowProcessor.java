
package csvtoxml.config;

import csvtoxml.entities.*;
import org.springframework.batch.item.ItemProcessor;

import java.util.ArrayList;
import java.util.List;

public class RowProcessor implements ItemProcessor<Row, TestSuite> {

    private final TestSuite testSuite = new TestSuite(); // Se mantiene para agregar TestCases

    @Override
    public TestSuite process(Row row) throws Exception {

        RowOutput rowOutput = new RowOutput();

        rowOutput.setFuncion(row.getFuncion());
        rowOutput.setTipo(row.getTipo());
        rowOutput.setScript(row.getScript());
        rowOutput.setPrueba(row.getPrueba());
        rowOutput.setResultado(row.getResultado());
        rowOutput.setFormato(row.getFormato());
        rowOutput.setAmbiente(row.getAmbiente());
        rowOutput.setEvidencia(row.getEvidencia());

        List<Label> labels = new ArrayList<>();
        Label label1 = new Label("lenguaje", "javascript");
        Label label2 = new Label("framework", "wdio");
        Label label3 = new Label("thread", "0-5");
        Label label4 = new Label("feature", "Portada");
        labels.add(label1);
        labels.add(label2);
        labels.add(label3);
        labels.add(label4);
        rowOutput.setLabels(labels);

        // Crea los test case y los agrega a testSuite
        TestCase testCase = new TestCase(rowOutput);
        testSuite.addTestCase(testCase);

        return testSuite;
    }
}
