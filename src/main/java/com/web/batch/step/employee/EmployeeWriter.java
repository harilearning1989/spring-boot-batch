package com.web.batch.step.employee;

import com.web.batch.constants.JobConstants;
import com.web.batch.models.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;


@Component(JobConstants.FIRST_JOB_ITEM_WRITER_ID)
public class EmployeeWriter implements ItemWriter<Employee> {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public void write(Chunk<? extends Employee> employees) throws Exception {
        for (Employee item : employees) {
            System.out.println("EmployeeWriter::"+item);
        }
    }
}
