package com.example.demo.steps;

import com.example.demo.dtos.RowDto;
import com.example.demo.services.ToXmlService;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class itemConvertStep implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        ToXmlService toXmlService = new ToXmlService();

        List<RowDto> personList = (List<RowDto>) chunkContext.getStepContext()
                .getStepExecution()
                .getJobExecution()
                .getExecutionContext()
                .get("RowList");

        toXmlService.createDocument(personList);

        return RepeatStatus.FINISHED;
    }

}
