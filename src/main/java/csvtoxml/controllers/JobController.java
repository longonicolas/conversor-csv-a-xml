package csvtoxml.controllers;

import csvtoxml.entities.Row;
import csvtoxml.services.RowProcessorService;
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
    private Job job;

    @Autowired
    private FlatFileItemReader<Row> reader;

    @PostMapping("/importRows")
    public ResponseEntity<String> importCsvToDB() throws IOException {
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
        rowProcessorService.processRow();
        return true;
    }


}