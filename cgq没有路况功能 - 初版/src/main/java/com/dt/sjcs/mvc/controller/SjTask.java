package com.dt.sjcs.mvc.controller;

import com.dt.sjcs.mvc.service.SjpzService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@EnableScheduling
@Component
public class SjTask {

	@Resource
	private SjpzService sjpzservice;

	@Scheduled(cron = "0 0 3 * * ?")
	public void sjzl() {
		sjpzservice.insertcopy();
		sjpzservice.deletesjpz();
	}

}
