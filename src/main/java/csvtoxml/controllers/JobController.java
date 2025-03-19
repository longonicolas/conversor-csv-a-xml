package csvtoxml.controllers;

import csvtoxml.entities.Row;
import csvtoxml.repositories.RowRepository;
import csvtoxml.services.GenerateRowListService;
import csvtoxml.services.RowProcessorService;
import csvtoxml.services.ToXmlService;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Value("${app.base-dir}")
    private String baseDir;

    @Autowired
    RowProcessorService rowProcessorService;

    @Autowired
    private StaxEventItemWriter<Row> itemWriter;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private GenerateRowListService generateRowList;

    @Autowired
    private RowRepository rowRepository;

    @Autowired
    private ToXmlService toXmlService;

    @Autowired
    private Job job;

    @Autowired
    private FlatFileItemReader<Row> reader;

    @PostMapping("/importRows")
    public ResponseEntity<String> importCsvToDB(@RequestParam("file") MultipartFile file) throws IOException {

        String fixedPath = Paths.get(baseDir, file.getOriginalFilename()).toString();
        File fixedFile = new File(fixedPath);
        file.transferTo(fixedFile);

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("El archivo es requerido y no puede estar vacío.");
        }


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

    @GetMapping("/getRows")
    public Boolean getAllRows() throws Exception {
//        generateRowList = new GenerateRowListService(rowRepository,toXmlService);
//        List<Row> rows = generateRowList.getAllRows();
//        rowProcessorService = new RowProcessorService(itemWriter);
        rowProcessorService.processRow();
        return true;
    }


}