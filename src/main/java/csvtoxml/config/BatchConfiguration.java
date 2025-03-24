package csvtoxml.config;

import com.thoughtworks.xstream.XStream;
import csvtoxml.entities.*;

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
import org.springframework.batch.item.xml.builder.StaxEventItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.*;

@Configuration
public class BatchConfiguration {


    @Bean
    public FlatFileItemReader<Row> reader() {
        FlatFileItemReader<Row> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource("src/main/resources/worksheet1.csv"));
        reader.setName("csvReader");
        reader.setLinesToSkip(1);
        reader.setLineMapper(lineMapper());
        //reader.setStrict(false);
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
    public XStreamMarshaller tradeMarshaller() {
        Map<String, Class<?>> aliases = new HashMap<>();
        aliases.put("test-cases", TestCase.class);
        aliases.put("test-case", ArrayList.class);
        aliases.put("label", Label.class);
        aliases.put("labels", ArrayList.class);
        aliases.put("parameter", Parameter.class);
        aliases.put("parameters", ArrayList.class);
        aliases.put("funcion", String.class);
        aliases.put("tipo", String.class);
        aliases.put("script", String.class);
        aliases.put("prueba", String.class);
        aliases.put("resultado", String.class);
        aliases.put("formato", String.class);
        aliases.put("ambiente", String.class);
        aliases.put("evidencia", String.class);

        XStreamMarshaller marshaller = new XStreamMarshaller();
        marshaller.setAnnotatedClasses(TestCase.class, Label.class);
        marshaller.setAliases(aliases);

        XStream xStream = marshaller.getXStream();
        xStream.allowTypes(new Class[]{TestCase.class, Label.class, ArrayList.class});

        return marshaller;
    }


    @Bean
    public StaxEventItemWriter<TestCase> xmlWriter() {
        return new StaxEventItemWriterBuilder<TestCase>()
                .name("TestWriter")
                .marshaller(tradeMarshaller()) // Convierte Row en XML
                .resource(new FileSystemResource("output.xml")) // Archivo de salida
                .rootTagName("test-cases") // Etiqueta raíz del XML
                .overwriteOutput(true) // Sobrescribe si el archivo ya existe
                .build();
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
        return new StepBuilder("csv-step",jobRepository)
                .<Row, TestCase>chunk(1, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(xmlWriter())
                //.taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor(){
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(10);
        return asyncTaskExecutor;
    }



}




