package com.java.springboot.schF;

import com.java.springboot.jobFactory.Maker;
import com.java.springboot.operate.DataOp;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Configuration;
// import org.springframework.stereotype.Service;

@Configuration
public class Sch {
    public void runJob() {
        JobDetail jobDetail = Maker.buildJobDetail(DataOp.class);
        Trigger trigger = Maker.buildTrigger(DataOp.class);
        try {
            Scheduler sch = StdSchedulerFactory.getDefaultScheduler();
            sch.start();
            sch.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
