package com.intfocus.hdk.controller;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/equipment")
public class EquipmentController implements ApplicationContextAware {
    private final static Logger log =  Logger.getLogger(EquipmentController.class);
    private static ApplicationContext applicationContext; 
	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		applicationContext = ctx;
	}   

}