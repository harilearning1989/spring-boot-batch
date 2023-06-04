package com.web.batch.config;

import com.web.batch.constants.JobConstants;
import com.web.batch.dtos.EmployeeDTO;
import com.web.batch.listener.JobListener;
import com.web.batch.listener.ReadListener;
import com.web.batch.listener.StepListener;
import com.web.batch.listener.WriteListener;
import com.web.batch.models.Employee;
import com.web.batch.step.str.StringProcessor;
import com.web.batch.step.str.StringReader;
import com.web.batch.step.str.StringWriter;
import jakarta.annotation.Resource;
import org.springframework.batch.core.*;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SpringBootBatchConfig {

    //https://github.com/ldauvilaire/sample-spring-boot-batch/blob/master/src/main/java/net/ldauvilaire/sample/batch/job/FirstJobConfiguration.java

    @Value("${first.chunck.size}")
    protected Integer chunckSize;

    @Resource(name= JobConstants.FIRST_JOB_ITEM_READER_ID)
    protected ItemReader<EmployeeDTO> reader;
    @Resource(name=JobConstants.FIRST_JOB_ITEM_PROCESSOR_ID)
    protected ItemProcessor<EmployeeDTO, Employee> processor;

    @Resource(name=JobConstants.FIRST_JOB_ITEM_WRITER_ID)
    protected ItemWriter<Employee> writer;

    @Resource(name=JobConstants.FIRST_JOB_EXECUTION_LISTENER_ID)
    protected JobExecutionListener listener;
    
    /**
     * Step consist of an ItemReader, ItemProcessor and an ItemWriter.
     *
     * @return step
     */
    @Bean
    public Step employeeStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new StepBuilder("employeeStep", jobRepository)
                .<EmployeeDTO, Employee>chunk(1, platformTransactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    /**
     * A Job is made up of many steps and each step is
     * a READ-PROCESS-WRITE task or a single operation task (tasklet).
     *
     * @return job
     */
    @Bean
    public Job employeeJob(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new JobBuilder("employeeJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .listener(new StepListener())
                .listener(new JobListener())
                .listener(new ReadListener<>())
                .listener(new WriteListener<>())
                .flow(employeeStep(jobRepository, platformTransactionManager))
                .end()
                .build();
    }

    /**
     * Step consist of an ItemReader, ItemProcessor and an ItemWriter.
     *
     * @return step
     */
    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new StepBuilder("step1", jobRepository)
                .<String, String>chunk(1, platformTransactionManager)
                .reader(new StringReader())
                .processor(new StringProcessor())
                .writer(new StringWriter())
                .listener(employeeStepListener())
                .listener(employeeReadListener())
                .listener(employeeWriteListener())
                .listener(employeeJobListener())
                .build();
    }

    /**
     * Step consist of an ItemReader, ItemProcessor and an ItemWriter.
     *
     * @return step
     */
    @Bean
    public Step step2(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new StepBuilder("step2", jobRepository)
                .<String, String>chunk(1, platformTransactionManager)
                .reader(new StringReader())
                .processor(new StringProcessor())
                .writer(new StringWriter())
                .listener(employeeStepListener())
                .listener(employeeReadListener())
                .listener(employeeWriteListener())
                .listener(employeeJobListener())
                .build();
    }

    /**
     * Step consist of an ItemReader, ItemProcessor and an ItemWriter.
     *
     * @return step
     */
    @Bean
    public Step step3(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new StepBuilder("step3", jobRepository)
                .<String, String>chunk(1, platformTransactionManager)
                .reader(new StringReader())
                .processor(new StringProcessor())
                .writer(new StringWriter())
                .listener(employeeStepListener())
                .listener(employeeReadListener())
                .listener(employeeWriteListener())
                .listener(employeeJobListener())
                .build();
    }

    /**
     * Step consist of an ItemReader, ItemProcessor and an ItemWriter.
     *
     * @return step
     */
    @Bean
    public Step step4(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new StepBuilder("step4", jobRepository)
                .<String, String>chunk(1, platformTransactionManager)
                .reader(new StringReader())
                .processor(new StringProcessor())
                .writer(new StringWriter())
                .listener(employeeStepListener())
                .listener(employeeReadListener())
                .listener(employeeWriteListener())
                .listener(employeeJobListener())
                .build();
    }

    /**
     * A Job is made up of many steps and each step is
     * a READ-PROCESS-WRITE task or a single operation task (tasklet).
     *
     * @return job
     */
    @Bean
    public Job job1(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new JobBuilder("job1", jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(step1(jobRepository, platformTransactionManager))
                .end()
                .build();
    }

    /**
     * A Job is made up of many steps and each step is
     * a READ-PROCESS-WRITE task or a single operation task (tasklet).
     *
     * @return job
     */
    @Bean
    public Job job2(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new JobBuilder("job2", jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(step2(jobRepository, platformTransactionManager))
                .end()
                .build();
    }

    /**
     * A Job is made up of many steps and each step is
     * a READ-PROCESS-WRITE task or a single operation task (tasklet).
     *
     * @return job
     */
    @Bean
    public Job job3(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new JobBuilder("job3", jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(step3(jobRepository, platformTransactionManager))
                .end()
                .build();
    }

    /**
     * A Job is made up of many steps and each step is
     * a READ-PROCESS-WRITE task or a single operation task (tasklet).
     *
     * @return job
     */
    @Bean
    public Job simpleJob(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new JobBuilder("step4", jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(step3(jobRepository, platformTransactionManager))
                .end()
                .build();
    }

    @Bean
    public ItemReadListener<Employee> employeeReadListener() {
        return new ReadListener<>();
    }

    @Bean
    public StepExecutionListener employeeStepListener() {
        return new StepListener();
    }

    @Bean
    public ItemWriteListener<Employee> employeeWriteListener() {
        return new WriteListener<>();
    }

    @Bean
    public JobExecutionListener employeeJobListener() {
        return new JobListener();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(15);
        taskExecutor.setMaxPoolSize(20);
        taskExecutor.setQueueCapacity(30);
        return taskExecutor;
    }

}
