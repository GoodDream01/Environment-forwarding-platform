package com.dt.taje.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dt.taje.utils.zdry.ZdrykDownload;

public class ZdkDownlodJob implements StatefulJob {
	private final static Logger logger = LoggerFactory.getLogger(ZdkDownlodJob.class);
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		logger.info("==========定时下载重点人员库信息：开始============");
		try {
			ZdrykDownload.downLoad();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("==========定时下载重点人员库信息：结束============");
		
	}
}
