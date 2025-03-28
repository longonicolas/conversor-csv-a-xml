package csvtoxml.config;

import com.thoughtworks.xstream.XStream;
import csvtoxml.entities.*;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
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

import java.io.File;
import java.util.*;

@Configuration
public class BatchConfiguration {

    private FlatFileItemReader<Row> readerForFile(File csvFile) {
        FlatFileItemReader<Row> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource(csvFile.getAbsolutePath()));
        reader.setName("csvReader-" + csvFile.getName());
        reader.setLinesToSkip(1); // Saltar encabezado
        reader.setLineMapper(lineMapper());
        return reader;
    }

    private StaxEventItemWriter<TestCase> writerForFile(File csvFile, XStreamMarshaller marshaller) {
        String fileNameWithoutExt = csvFile.getName().replaceFirst("[.][^.]+$", "");
        String outputFilePath = System.getProperty("user.dir") + File.separator + fileNameWithoutExt + ".xml";

        return new StaxEventItemWriterBuilder<TestCase>()
                .name("TestWriter-" + csvFile.getName())
                .marshaller(marshaller)
                .resource(new FileSystemResource(outputFilePath))
                .rootTagName("test-cases")
                .overwriteOutput(true)
                .build();
    }

    private LineMapper<Row> lineMapper() {
        DefaultLineMapper<Row> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("funcion", "tipo", "script", "prueba", "resultado", "formato", "ambiente", "evidencia");

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
        aliases.put("label", Labels.class);
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
        marshaller.setAnnotatedClasses(TestCase.class, Labels.class, Label.class);
        marshaller.setAliases(aliases);

        XStream xStream = marshaller.getXStream();
        xStream.allowTypes(new Class[]{TestCase.class, Labels.class, Label.class, ArrayList.class});

        return marshaller;
    }

    private Step stepForFile(File csvFile, JobRepository jobRepository, PlatformTransactionManager transactionManager, XStreamMarshaller marshaller) {
        return new StepBuilder("csv-step-" + csvFile.getName(), jobRepository)
                .<Row, TestCase>chunk(1, transactionManager)
                .reader(readerForFile(csvFile))
                .processor(processor())
                .writer(writerForFile(csvFile, marshaller))
                .build();
    }

    @Bean
    public Job runJob(JobRepository jobRepository, PlatformTransactionManager transactionManager, XStreamMarshaller tradeMarshaller) {
        File directory = new File(System.getProperty("user.dir"));
        File[] csvFiles = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".csv"));

        if (csvFiles == null || csvFiles.length == 0) {
            throw new RuntimeException("No se encontró ningún archivo CSV en el directorio actual.");
        }

        System.out.println("Archivos CSV detectados: " + Arrays.toString(csvFiles));

        JobBuilder jobBuilder = new JobBuilder("importRows", jobRepository);
        var flowBuilder = jobBuilder.flow(stepForFile(csvFiles[0], jobRepository, transactionManager, tradeMarshaller));

        // Encadenar pasos para cada archivo CSV (excepto el primero, ya añadido)
        for (int i = 1; i < csvFiles.length; i++) {
            flowBuilder.next(stepForFile(csvFiles[i], jobRepository, transactionManager, tradeMarshaller));
        }

        return flowBuilder
                .end()
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(10);
        return asyncTaskExecutor;
    }

    public List<String> getOutputFilePaths() {
        File directory = new File(System.getProperty("user.dir"));
        File[] csvFiles = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".csv"));

        if (csvFiles == null || csvFiles.length == 0) {
            return Collections.emptyList();
        }

        List<String> outputFilePaths = new ArrayList<>();
        for (File csvFile : csvFiles) {
            String fileNameWithoutExt = csvFile.getName().replaceFirst("[.][^.]+$", "");
            String outputFilePath = System.getProperty("user.dir") + File.separator + fileNameWithoutExt + ".xml";
            outputFilePaths.add(outputFilePath);
        }
        return outputFilePaths;
    }
}