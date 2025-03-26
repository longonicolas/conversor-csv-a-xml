package csvtoxml.config;

import csvtoxml.entities.*;
import org.springframework.batch.item.ItemProcessor;

import java.util.List;

public class RowProcessor implements ItemProcessor<Row, TestCase> {

    @Override
    public TestCase process(Row row) {
        return generateTestCases(row);
    }

    private TestCase generateTestCases(Row row) {
        TestCase testCase = new TestCase();

        testCase.setFuncion(row.getFuncion());
        testCase.setTipo(row.getTipo());
        testCase.setScript(row.getScript());
        testCase.setPrueba(row.getPrueba());
        testCase.setResultado(row.getResultado());
        testCase.setFormato(row.getFormato());
        testCase.setAmbiente(row.getAmbiente());
        testCase.setEvidencia(row.getEvidencia());

        testCase.setLabels(getLabels());
        testCase.setParameters(getParameters());

        testCase.setStatus("pass");
        testCase.setStart("738264273468344");
        testCase.setStop("347923498237333");

        return testCase;
    }

    private Labels getLabels() {
        List<Label> labels = List.of(
                new Label("lenguaje", "javascript"),
                new Label("framework", "wdio"),
                new Label("thread", "0-5"),
                new Label("feature", "Portada")
        );

        Labels labelOutput = new Labels(labels);

        return labelOutput;
    }

    private Parameters getParameters() {
        List<Parameter> parameters = List.of(
                new Parameter("argument", "browser", "chrome-128.0.6613.84"),
                new Parameter("environment-variable", "environment-variable",
                        "{\"sessionId\": \"f7ba271906e335d04c3515bd84798e15\", \"testId\": \"f7ba271906e335d04c3515bd84798e15\", \"tags\": [\"@portada\", \"@Tier1\", \"@PRT001\"]}"),
                new Parameter("environment-variable", "_capabilities",
                        "{\"acceptInsecureCerts\": false, \"acis:options\": {\"version\": \"7.3.7\", \"hostname\": \"REC-DSK-LNX@93\", \"user\": \"0012385\", \"platform\": \"linux\", \"cwd\": \"/home/RECONQUISTA/0012385/runner-tmp/repos/ar-senda-automation/automation\", \"nodeVersion\": \"v18.20.4\"}, \"browserName\": \"chrome\", \"browserVersion\": \"128.0.6613.84\", \"chrome\": {\"chromedriverVersion\": \"128.0.6613.137\", \"userDataDir\": \"/tmp/.org.chromium.Chromium.Q2aVgw\"}, \"fedcm:accounts\": true, \"goog:chromeOptions\": {\"debuggerAddress\": \"localhost:42861\"}, \"networkConnectionEnabled\": false, \"pageLoadStrategy\": \"normal\", \"platformName\": \"linux\", \"proxy\": {}, \"sauce:options\": {}, \"se:bidEnabled\": false, \"se:cdp\": \"ws://172.17.0.1:4444/session/f7ba271906e335d04c3515bd84798e15/se/cdp\", \"se:cdpVersion\": \"128.0.6613.84\", \"setWindowRect\": true, \"strictFileInteractability\": false, \"timeouts\": {\"implicit\": 0, \"pageLoad\": 300000, \"script\": 30000}, \"unhandledPromptBehavior\": \"dismiss and notify\", \"webauthn:extension:credBlob\": true, \"webauthn:extension:largeBlob\": true, \"webauthn:extension:minPinLength\": true, \"webauthn:extension:prf\": true}")
        );

        Parameters parameters1 = new Parameters(parameters);

        return parameters1;
    }
}
