package csvtoxml.config;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ResourceFileReader {

    public static List<String> getResourceFiles() {
        List<String> fileNames = new ArrayList<>();
        try {
            // Usa el ClassLoader para obtener la ruta de "resources"
            Path resourcePath = Paths.get(Objects.requireNonNull(
                    ResourceFileReader.class.getClassLoader().getResource("")
            ).toURI());

            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(resourcePath)) {
                fileNames = StreamSupport.stream(directoryStream.spliterator(), false)
                        .map(Path::getFileName)
                        .map(Path::toString)
                        .collect(Collectors.toList());
            }
        } catch (URISyntaxException | IOException | NullPointerException e) {
            System.err.println("Error al obtener los archivos de resources: " + e.getMessage());
        }
        return fileNames;
    }

    public static void main(String[] args) {
        List<String> files = getResourceFiles();
        System.out.println("Archivos en resources: " + files);
    }
}
