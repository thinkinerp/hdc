package com.intfocus.hdk.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.intfocus.hdk.dao.PrinterMapper;
import com.intfocus.hdk.vo.Printer;

@Controller
@RequestMapping("/printer")
public class PrinterController implements ApplicationContextAware {
    private final static Logger log =  Logger.getLogger(PrinterController.class);
    private static ApplicationContext applicationContext; 
    
    
    @Resource
    private PrinterMapper pm ;
    
    
    @RequestMapping(value = "submit" , method=RequestMethod.POST)
    @ResponseBody
    public void submit(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		              , Printer printer ){
    	
    }
    
    
    @RequestMapping(value = "getSome" , method=RequestMethod.POST)
    @ResponseBody
    public String getSome(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		              , Printer printer ){
    	Map<String,String> where = new HashMap<String,String>();
    	where.put("surveyId", printer.getSurId());
		List<Printer> printers = pm.selectByWhere(where);
		return "printer_getSome("+JSONObject.toJSONString(printers)+")";	
    }
    @RequestMapping(value = "modify" , method=RequestMethod.POST)
    @ResponseBody
    public void modify(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		, Printer printer ){
    	
    }
    
	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		applicationContext = ctx;
	}   

}