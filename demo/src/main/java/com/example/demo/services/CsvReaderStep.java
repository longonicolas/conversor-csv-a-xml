package com.example.demo.services;

import com.example.demo.entities.Row;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;


@Slf4j
public class CsvReaderStep implements Tasklet {

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        log.info("Lectura de archivo");

        Reader reader = new FileReader(resourceLoader.getResource("classpath:csvFiles/worksheet1.csv").getFile());

        CSVParser parser = new CSVParserBuilder()
                .withSeparator(',')
                .build();

        CSVReader csvReader = new CSVReaderBuilder(reader)
                .withCSVParser(parser)
                .build();

        List<Row> rowList = new ArrayList<>();
        String[] actualLine;

        while ((actualLine = csvReader.readNext() ) != null) {
            Row row = new Row();
            row.setFuncion(actualLine[0]);
            row.setTipo(actualLine[1]);
            row.setScript(actualLine[2]);
            row.setPrueba(actualLine[3]);
            row.setResultado(actualLine[4]);
            row.setFormato(actualLine[5]);
            row.setAmbiente(actualLine[6]);
            row.setEvidencia(actualLine[7]);

            rowList.add(row);
        }

        recorrerFila(rowList);

        csvReader.close();
        reader.close();

        chunkContext.getStepContext()
                .getStepExecution()
                .getJobExecution()
                .getExecutionContext()
                .put("rowList", rowList);

        log.info("fin lectura");


        return RepeatStatus.FINISHED;
    }

    public void recorrerFila(List<Row> filas) {
        for (Row fila : filas) {
            System.out.println(fila);
        }
    }
}
