package csvtoxml.config;


import javax.sql.DataSource;

import csvtoxml.entities.Row;

import org.springframework.batch.core.*;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfiguration {


    @Bean
    public FlatFileItemReader<Row> reader() {
        FlatFileItemReader<Row> reader = new FlatFileItemReader<>();
        //reader.setResource(new FileSystemResource("src/main/resources/worksheet1.csv"));
        reader.setName("csvReader");
        reader.setLinesToSkip(1);
        reader.setLineMapper(lineMapper());
        return reader;
    }

    private LineMapper<Row> lineMapper() {
        DefaultLineMapper<Row> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("funcion","tipo","script","prueba","resultado","formato","ambiente","evidencia");

        BeanWrapperFieldSetMapper<Row> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Row.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;

    }

    @Bean
    public RowProcessor processor() {
        return new RowProcessor();
    }

    @Bean
    public StaxEventItemWriter<Row> xmlWriter() {
        StaxEventItemWriter<Row> writer = new StaxEventItemWriter<>();
        writer.setResource(new FileSystemResource("output.xml"));

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(Row.class);

        writer.setMarshaller(marshaller);
        writer.setRootTagName("rows");
        writer.setOverwriteOutput(true);

        return writer;
    }

    @Bean
    public Job runJob(JobRepository jobRepository,PlatformTransactionManager transactionManager){
        return new JobBuilder("importRows",jobRepository)
                .flow(step1(jobRepository,transactionManager))
                .end()
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository,PlatformTransactionManager transactionManager)  {
        return new StepBuilder("csv-step",jobRepository).<Row, Row>chunk(10,transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(xmlWriter())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor(){
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(10);
        return asyncTaskExecutor;
    }



}
