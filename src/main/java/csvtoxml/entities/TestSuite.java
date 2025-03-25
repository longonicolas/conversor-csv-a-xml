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

            // Verificar si el archivo tiene un encabezado XML
            String xmlHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

            // Asegurarnos de que el encabezado XML está al principio
            if (!content.startsWith(xmlHeader)) {
                throw new IllegalArgumentException("El archivo no contiene un encabezado XML válido.");
            }

            // El contenido sin el encabezado XML
            String xmlBody = content.substring(xmlHeader.length()).trim();

            // Crear el nuevo contenido con <test-suite> envuelto
            String wrappedContent = xmlHeader + "\n" + "<test-suite>\n" + xmlBody + "\n</test-suite>";

            // Escribir el contenido envuelto en el archivo de salida
            Files.write(Paths.get(outputPath), wrappedContent.getBytes());

            System.out.println("Archivo envuelto correctamente y guardado en: " + outputPath);
        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}
