package com.ll.mb.domain.rebate.rebate.batch;


import org.springframework.batch.core.Job;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MakeRebateDataJobTestConfig {
    @Bean
    public JobLauncherTestUtils makeRebateDataJobLauncherTestUtils(Job makeRebateDataJob) {
        JobLauncherTestUtils utils = new JobLauncherTestUtils();
        utils.setJob(makeRebateDataJob);
        return utils;
    }
}
