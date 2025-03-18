package csvtoxml.controllers;

import csvtoxml.entities.Row;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @Autowired
    private FlatFileItemReader<Row> reader;

    @Value("${file.upload-dir}") // DIRECTORIO CONFIGURADO EN application.properties
    private String uploadDir;

    @PostMapping("/importRows")
    public ResponseEntity<String> importCsvToDB(@RequestParam("file") MultipartFile file) throws IOException {

        /*
        String fixedPath = "C:/Users/nicolas.longo/Desktop/csv-a-xml-accenture-bbva/src/main/resources" + file.getOriginalFilename();
        File fixedFile = new File(fixedPath);
        file.transferTo(fixedFile);*/

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("El archivo es requerido y no puede estar vacío.");
        }

        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File fixedFile = new File(uploadDir + File.separator + file.getOriginalFilename());
        file.transferTo(fixedFile);

        reader.setResource(new FileSystemResource(fixedFile));

        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis())
                .toJobParameters();

        try {
          JobExecution run = jobLauncher.run(job, jobParameters);
            return ResponseEntity.ok(run.getStatus().toString());
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException |
                 JobParametersInvalidException e) {
            throw new RuntimeException(e);
        }
    }

}