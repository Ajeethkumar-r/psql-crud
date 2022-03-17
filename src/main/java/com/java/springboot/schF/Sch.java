package com.java.springboot.schF;

import com.java.springboot.jobFactory.Maker;
import com.java.springboot.operate.DataOp;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;

@Service
public class Sch {
    public void runJob() {
        JobDetail jobDetail = Maker.buildJobDetail(DataOp.class);
        Trigger trigger = Maker.buildTrigger(DataOp.class);
        try {
            Scheduler sch = StdSchedulerFactory.getDefaultScheduler();
            sch.scheduleJob(jobDetail, trigger);
            sch.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
