package com.example.demo.steps;

import com.example.demo.entities.Row;
import com.example.demo.services.ToXmlService;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.util.List;

public class itemConvertStep implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        ToXmlService toXmlService = new ToXmlService();

        List<Row> rowList = (List<Row>) chunkContext.getStepContext()
                .getStepExecution()
                .getJobExecution()
                .getExecutionContext()
                .get("RowList");

        toXmlService.createDocument(rowList);

        return RepeatStatus.FINISHED;
    }

}
