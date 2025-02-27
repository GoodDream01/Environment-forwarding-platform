package com.dt.taje.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dt.taje.utils.zdry.ZdrySecondBd;
import com.dt.taje.utils.zdry.ZdrykDownload;

public class ZdkSecondBdJob implements StatefulJob {
	private final static Logger logger = LoggerFactory.getLogger(ZdkSecondBdJob.class);
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		logger.info("==========定时重点人员二次比对：开始============");
		try {
			ZdrySecondBd.SecondBd();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("==========定时重点人员二次比对：结束============");
		
	}
}
