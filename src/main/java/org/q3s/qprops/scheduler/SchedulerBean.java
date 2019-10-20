package org.q3s.qprops.scheduler;

import javax.annotation.PostConstruct;

import org.q3s.qprops.services.PropService;
import org.q3s.qprops.utils.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerBean {

	@Autowired
	private PropService service;
	
	@PostConstruct
	public void init() throws Exception {
//		cron();
	}
	
	@Scheduled(cron = "0 30 * * * *")
	public void cron() throws Exception{
		LoggerUtils.info("ejecutando cron");
		service.process();
		LoggerUtils.info("finalizando ejecución cron");			
	}
	
}
