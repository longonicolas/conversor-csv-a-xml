package csvtoxml.services;

import csvtoxml.entities.Row;
import org.springframework.stereotype.Service;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

@Service
public class ToXmlService {

    public void createDocument(List<Row> rows) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.newDocument();

            // Nodo raíz
            Element rootElement = documento.createElement("rows");
            documento.appendChild(rootElement);

            // Iterar sobre la lista de Rows y agregar cada uno al XML
            for (Row row : rows) {
                Element rowElement = documento.createElement("row");

                // Crear y agregar elementos
                rowElement.appendChild(createElement(documento, "Funcion", row.getFuncion()));
                rowElement.appendChild(createElement(documento, "Tipo", row.getTipo()));
                rowElement.appendChild(createElement(documento, "Script", row.getScript()));
                rowElement.appendChild(createElement(documento, "Prueba", row.getPrueba()));
                rowElement.appendChild(createElement(documento, "Resultado", row.getResultado()));
                rowElement.appendChild(createElement(documento, "Ambiente", row.getAmbiente()));
                rowElement.appendChild(createElement(documento, "Evidencia", row.getEvidencia()));

                // Agregar la fila al nodo raíz
                rootElement.appendChild(rowElement);
            }

            // Guardar el documento XML en un archivo
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            Source source = new DOMSource(documento);
            Result result = new StreamResult(new File("rows.xml"));
            transformer.transform(source, result);

            System.out.println("Archivo XML generado con éxito: rows.xml");

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private Element createElement(Document doc, String tagName, String value) {
        Element element = doc.createElement(tagName);
        element.appendChild(doc.createTextNode(value));
        return element;
    }
}