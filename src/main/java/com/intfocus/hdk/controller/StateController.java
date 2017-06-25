package com.intfocus.hdk.controller;
import java.io.IOException;
import java.io.Writer;
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
import com.intfocus.hdk.dao.StateMapper;
import com.intfocus.hdk.vo.State;

@Controller
@RequestMapping("/state")
public class StateController implements ApplicationContextAware {
    private final static Logger log =  Logger.getLogger(StateController.class);
    
    
    @Resource
    private StateMapper statemapper ;
	@RequestMapping(value = "submit" , method=RequestMethod.POST)
    @ResponseBody
    public Integer submit(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		              ,  State state ){
            	try{
            	statemapper.insertSelective(state);
            	}catch(Exception e){
            		e.printStackTrace();
            		return 0;
            	}
            	return 1;
    }
    
    
    @RequestMapping(value = "getSome" , method=RequestMethod.GET)
    @ResponseBody
    public void getSome(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		              , State state ,String time ,String callback,String isAll){
    	Map<String, String> where = new HashMap<String,String>();
    	
    	where.put("ownerTable", state.getOwnerTable());
    	if(null != isAll){
    		where.put("isAll", isAll);
    	}
		List<State> states = statemapper.selectByWhere(where );
		Writer w;
		try {
			w = res.getWriter();
			w.write(callback+"("+JSONObject.toJSONString(states)+")");
//			w.write(callbackparam+"("+JSONObject.toJSONString(states)+")");
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
    }
    @RequestMapping(value = "modify" , method=RequestMethod.POST)
    @ResponseBody
    public void modify(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		, State state){
    	
    }
    private static ApplicationContext applicationContext; 
	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		applicationContext = ctx;
	}   

}