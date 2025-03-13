package com.example.demo.services;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import java.io.File;

public class ToXmlService {

    public void createDocument(){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();

            Document documento = implementation.createDocument(null, "xml", null);
            documento.setXmlVersion("1.0");

            Element files = documento.createElement("files");
            Element file1 = documento.createElement("file");

            Element test = documento.createElement("test");
            Text resultt = documento.createTextNode("pass");
            test.appendChild(resultt);
            file1.appendChild(test);

            Element time = documento.createElement("time");
            Text textTime = documento.createTextNode("04.02.2025 17:03");
            time.appendChild(textTime);
            file1.appendChild(time);

            Element tipo1 = documento.createElement("tipo");
            Text textTipo1 = documento.createTextNode("Caso 1");
            tipo1.appendChild(textTipo1);
            file1.appendChild(tipo1);

            files.appendChild(file1);

            Element file2 = documento.createElement("file");

            Element tipo2 = documento.createElement("tipo");
            Text textTipo2 = documento.createTextNode("Caso 2");
            tipo2.appendChild(textTipo2);
            file2.appendChild(tipo2);

            Element time2 = documento.createElement("time");
            Text textTime2 = documento.createTextNode("08.02.2025 17:03");
            time2.appendChild(textTime2);
            file2.appendChild(time2);

            files.appendChild(file2);


            documento.getDocumentElement().appendChild(files);

            Source source = new DOMSource(documento);
            Result result = new StreamResult(new File("newxml8.xml"));

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

}
