package csvtoxml.entities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestSuite {
    public static void wrapWithTestSuite(String inputPath, String outputPath) {
        try {
            // Leer todo el contenido del archivo XML original
            String content = new String(Files.readAllBytes(Paths.get(inputPath)));

            String xmlHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

            if (!content.startsWith(xmlHeader)) {
                throw new IllegalArgumentException("El archivo no contiene un encabezado XML válido.");
            }

            String xmlBody = content.substring(xmlHeader.length()).trim();

            String wrappedContent = xmlHeader + "\n" +
                    "<test-suite ns2=\"12345\" start=\"73782437828733\" stop=\"5345345544\">\n" +
                    "    <name>NSW</name>\n" +
                    "    <title>NSW</title>\n" +
                    xmlBody + "\n" +
                    "</test-suite>";

            Files.write(Paths.get(outputPath), wrappedContent.getBytes());

            System.out.println("Archivo envuelto correctamente y guardado en: " + outputPath);
        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}