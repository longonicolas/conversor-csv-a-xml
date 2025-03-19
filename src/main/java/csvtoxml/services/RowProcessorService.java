package csvtoxml.services;

import csvtoxml.entities.Row;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;

@Service
public class RowProcessorService {

    @Autowired
    private StaxEventItemWriter<Row> itemWriter;

    public RowProcessorService(StaxEventItemWriter<Row> itemWriter) {
        this.itemWriter = itemWriter;
    }

    public void processRow() throws Exception {
        Row row1 = new Row("funcion1", "tipoA", "scriptTest", "pruebaX", "OK", "JSON", "Producción", "captura1.png");
        Row row2 = new Row("funcion2", "tipoB", "scriptDev", "pruebaY", "FAIL", "XML", "Desarrollo", "captura2.png");
        Row row3 = new Row("funcion3", "tipoC", "scriptProd", "pruebaZ", "OK", "CSV", "Testing", "captura3.png");
        Row row4 = new Row("funcion4", "tipoD", "scriptFinal", "pruebaW", "FAIL", "TXT", "QA", "captura4.png");

        System.out.println("🔹 Procesando los objetos Row...");

        // 🔹 Crear un Chunk con las 4 filas
        Chunk<Row> chunk = new Chunk<>(List.of(row1, row2, row3, row4));

        ExecutionContext executionContext = new ExecutionContext();
        itemWriter.open(executionContext);
        itemWriter.write(chunk);
        itemWriter.close();

        System.out.println("✅ Archivo XML generado correctamente.");
    }
}
