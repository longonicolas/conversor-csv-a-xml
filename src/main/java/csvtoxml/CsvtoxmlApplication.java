package csvtoxml;
import csvtoxml.entities.TestSuite;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CsvtoxmlApplication {

	private static final String FILE_PATH = "src/main/resources/output.xml";
	private static final String OUTPUT_PATH = "src/main/resources/output.xml";;

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

				// Solo ejecutar la envoltura si el Job finaliza con éxito
				if (run.getStatus() == BatchStatus.COMPLETED) {
					TestSuite.wrapWithTestSuite(FILE_PATH, OUTPUT_PATH);
					System.out.println("Proceso finalizado correctamente.");
				} else {
					System.err.println("El Job no finalizó correctamente. No se ejecutará la envoltura.");
				}

			} catch (JobExecutionAlreadyRunningException | JobRestartException |
					 JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
				throw new RuntimeException("Error ejecutando el Job", e);
			}
		};
	}
}
