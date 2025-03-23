
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

        List<Parameter> parameters = getParameters();
        rowOutput.setParameters(parameters);

        testSuite.setTestCase(rowOutput);

        return testSuite;
    }

    private List<Parameter> getParameters() {
        Parameter parameter1 = new Parameter("argument","browser","chrome-128.0.6613.84");
        Parameter parameter2 = new Parameter("environment-variable","environment-variable","{\"sessionId\": \"f7ba271906e335d04c3515bd84798e15\", \"testId\": \"f7ba271906e335d04c3515bd84798e15\", \"tags\": [\"@portada\", \"@Tier1\", \"@PRT001\"]}");
        Parameter parameter3 = new Parameter("environment-variable","_capabilities","{\"acceptInsecureCerts\": false, \"acis:options\": {\"version\": \"7.3.7\", \"hostname\": \"REC-DSK-LNX@93\", \"user\": \"0012385\", \"platform\": \"linux\", \"cwd\": \"/home/RECONQUISTA/0012385/runner-tmp/repos/ar-senda-automation/automation\", \"nodeVersion\": \"v18.20.4\"}, \"browserName\": \"chrome\", \"browserVersion\": \"128.0.6613.84\", \"chrome\": {\"chromedriverVersion\": \"128.0.6613.137\", \"userDataDir\": \"/tmp/.org.chromium.Chromium.Q2aVgw\"}, \"fedcm:accounts\": true, \"goog:chromeOptions\": {\"debuggerAddress\": \"localhost:42861\"}, \"networkConnectionEnabled\": false, \"pageLoadStrategy\": \"normal\", \"platformName\": \"linux\", \"proxy\": {}, \"sauce:options\": {}, \"se:bidEnabled\": false, \"se:cdp\": \"ws://172.17.0.1:4444/session/f7ba271906e335d04c3515bd84798e15/se/cdp\", \"se:cdpVersion\": \"128.0.6613.84\", \"setWindowRect\": true, \"strictFileInteractability\": false, \"timeouts\": {\"implicit\": 0, \"pageLoad\": 300000, \"script\": 30000}, \"unhandledPromptBehavior\": \"dismiss and notify\", \"webauthn:extension:credBlob\": true, \"webauthn:extension:largeBlob\": true, \"webauthn:extension:minPinLength\": true, \"webauthn:extension:prf\": true}}");
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(parameter1);
        parameters.add(parameter2);
        parameters.add(parameter3);
        return parameters;
    }
}
