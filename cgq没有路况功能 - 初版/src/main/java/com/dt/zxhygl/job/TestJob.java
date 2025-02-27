package com.dt.zxhygl.job;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestJob implements Job {
	private static Logger logger = Logger.getLogger(TestJob.class);
	
    public void execute(JobExecutionContext context)
        throws JobExecutionException {

    	logger.info("定时任务执行......");

        
    }
    
}

