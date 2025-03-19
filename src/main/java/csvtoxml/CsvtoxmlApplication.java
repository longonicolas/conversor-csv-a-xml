package csvtoxml;

import csvtoxml.config.BatchConfiguration;
import csvtoxml.entities.Row;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.WritableResource;

import java.util.Collections;

@SpringBootApplication
public class CsvtoxmlApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsvtoxmlApplication.class, args);
	}

	Row row = new Row("Calculo Impuestos", "Automático", "SELECT * FROM impuestos",
			"Verificación IVA", "Correcto", "CSV", "Producción", "evidencia123.jpg");

}

