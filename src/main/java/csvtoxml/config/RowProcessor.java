
package csvtoxml.config;

import csvtoxml.entities.Label;
import csvtoxml.entities.Row;
import csvtoxml.entities.RowOutput;
import csvtoxml.entities.TestSuite;
import org.springframework.batch.item.ItemProcessor;

import java.util.ArrayList;
import java.util.List;

public class RowProcessor implements ItemProcessor<Row, TestSuite> {
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

        TestSuite testSuite = new TestSuite(rowOutput);

        return testSuite;
    }
}
