package com.java.springboot.jobFactory;

import com.java.springboot.operate.DataOp;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.stereotype.Service;

@Service
public class Maker {
    public static JobDetail buildJobDetail(Class<DataOp> jobClass) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put(jobClass.getSimpleName(), "group1");
        return JobBuilder
                .newJob(DataOp.class)
                .withIdentity(jobClass.getSimpleName(), null)
                .setJobData(jobDataMap)
                .storeDurably(true)
                .build();
    }

    public static Trigger buildTrigger(Class<DataOp> jobClass) {
        CronScheduleBuilder cScheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ?");
        return TriggerBuilder
                .newTrigger()
                .forJob(jobClass.getSimpleName())
                .withIdentity(jobClass.getSimpleName(), null)
                .withSchedule(cScheduleBuilder)
                .build();
    }
}
