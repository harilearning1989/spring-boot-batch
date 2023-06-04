package com.web.batch.step.employee;

import com.web.batch.constants.JobConstants;
import com.web.batch.dtos.EmployeeDTO;
import com.web.batch.models.Employee;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component(JobConstants.FIRST_JOB_ITEM_PROCESSOR_ID)
public class EmployeeProcessor implements ItemProcessor<EmployeeDTO, Employee> {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public Employee process(EmployeeDTO item) throws Exception {
        if(StringUtils.isNumeric(item.getZip())){
            final Employee transformed = new Employee(item.getFirstName(),item.getLastName(),
                    item.getCompanyName(), item.getAddress(), item.getCity(), item.getCounty(), item.getState(),Integer.parseInt(item.getZip()));
            return transformed;
        }
        return null;
    }
}
