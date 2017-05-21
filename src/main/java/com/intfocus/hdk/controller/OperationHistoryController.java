package com.intfocus.hdk.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.intfocus.hdk.dao.Operation_historyMapper;
import com.intfocus.hdk.vo.Operation_history;

@Controller
@RequestMapping("/operation")
public class OperationHistoryController implements ApplicationContextAware {
    private final static Logger log =  Logger.getLogger(OperationHistoryController.class);
    
    @Resource
    private Operation_historyMapper ohm ;

    private static ApplicationContext applicationContext; 
    
    public String getSome(HttpServletRequest req , HttpServletResponse rep , String operationDate  
    		){
    	
    	return null;
    }
    @RequestMapping(value="save",method=RequestMethod.GET)
    public void save(HttpServletRequest req , HttpServletResponse rep 
    		,Operation_history	his){
    	
    	ohm.insertSelective(his);
    }
    
	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		applicationContext = ctx;
	}   

}