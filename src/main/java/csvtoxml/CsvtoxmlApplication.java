package csvtoxml;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CsvtoxmlApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsvtoxmlApplication.class, args);
	}

	@Bean
	CommandLineRunner startBatchJob(JobLauncher jobLauncher, Job job) {
		return args -> {
			JobParameters jobParameters = new JobParametersBuilder()
					.addLong("startAt", System.currentTimeMillis())
					.toJobParameters();

			try {
				JobExecution run = jobLauncher.run(job, jobParameters);
				System.out.println("Job Status: " + run.getStatus());
			} catch (JobExecutionAlreadyRunningException | JobRestartException |
					 JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
				throw new RuntimeException(e);
			}
		};
	}
}
