package com.intfocus.hdk.controller;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.intfocus.hdk.dao.CashMapper;
import com.intfocus.hdk.dao.EquipmentMapper;
import com.intfocus.hdk.dao.MessageMapper;
import com.intfocus.hdk.dao.PrinterMapper;
import com.intfocus.hdk.dao.ProblemMapper;
import com.intfocus.hdk.dao.ProjectMapper;
import com.intfocus.hdk.dao.ShopsMapper;
import com.intfocus.hdk.util.ComUtil;
import com.intfocus.hdk.vo.Equipment;
import com.intfocus.hdk.vo.Message;
import com.intfocus.hdk.vo.Problem;

@Controller
@RequestMapping("/problem")
public class ProblemController implements ApplicationContextAware {
    private final static Logger log =  Logger.getLogger(ProblemController.class);
    private static ApplicationContext applicationContext; 
    
    @Resource
    private ProblemMapper problemMapper ;
    
    @Resource
    private PrinterMapper printerMapper;
    @Resource
    private CashMapper cashMapper;
    @Resource
    private EquipmentMapper equipmentMapper;
    @Resource
    private ProjectMapper projectMapper;
    
    @Resource
    private ShopsMapper shopsMapper;
    @Resource
    private MessageMapper messageMapper ; 
    
    @RequestMapping(value = "getEquipmentList " , method=RequestMethod.GET)
    @ResponseBody     
    public void getEquipmentList (HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		, Problem problem ){
    		Map<String, String> where = new HashMap<String ,String>();
    		where.put("proName",problem.getProName());
    		where.put("shopName",problem.getShopName());
    		List<Equipment> es = equipmentMapper.selectByWhere(where);
    		
			Writer w = null;
			try {
				w = res.getWriter();
				w.write("problem_getEquipmentList("+JSONObject.toJSONString(es)+")");
			} catch (IOException e) {
				e.printStackTrace();
			}
			

    }
    
    @RequestMapping(value = "submit" , method=RequestMethod.POST)
    @ResponseBody     
    public String submit(HttpServletResponse res , HttpServletRequest req ,HttpSession session
            , Problem problem ,String callback , String files
            ,String userName ,String userNum){
    	JSONObject rs = new JSONObject();
    	try{

    		if(null != files && !"".equalsIgnoreCase(files)){
				Map<String,String> result = ComUtil.savePicture(files, req.getSession().getServletContext().getRealPath("upload"));
				
				if(!"ok".equalsIgnoreCase(result.get("message"))){
					rs.put("message",  result.get("message"));
					return rs.toJSONString();
				}
				
				problem.setProblemEnclosure(result.get("urls"));
    		}
    		problemMapper.insertSelective(problem);
    		rs.put("message", "success");
	    	return rs.toJSONString();
	  }catch(Exception e){
		  e.printStackTrace();
		  rs.put("message","success" );
		  return "{'message':'fail'}";
	  }
    }
    @RequestMapping(value = "modify" , method=RequestMethod.POST)
    @ResponseBody     
    public String modify(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		, Problem problem ,String callback ,String files
    		,String userName ,String userNum){
       JSONObject rs = new JSONObject();
    	try{
    		if(null != files && !"".equalsIgnoreCase(files)){
				Map<String,String> result = ComUtil.savePicture(files, req.getSession().getServletContext().getRealPath("upload"));
				
				if(!"ok".equalsIgnoreCase(result.get("message"))){
					rs.put("message", result.get("message"));
					return rs.toJSONString();
				}
				problem.modifyAtachement((result.get("urls")));
    		}
    		problemMapper.updateByPrimaryKeySelective(problem);
    		rs.put("message", "success");
    		return rs.toJSONString();
    	}catch(Exception e){
    		e.printStackTrace();
    		rs.put("message", "fail");
    		return rs.toJSONString();
    	}
    }
    @InitBinder("problem")    
    public void initBinder1(WebDataBinder binder) {    
            binder.setFieldDefaultPrefix("problem.");    
   } 
    @RequestMapping(value = "getCount" , method=RequestMethod.GET)
    @ResponseBody    
    public void getCount(HttpServletResponse res , HttpServletRequest req ,HttpSession session
            , Problem problem  ){
    	Map<String,String> where = new HashMap<String,String>();
    	
    	where.put("proName", problem.getProName());
    	
    	List<Problem> problems = problemMapper.getCount(where);
    	Writer w = null;
		try {
			w = res.getWriter();
			w.write("problem_getCount("+JSONObject.toJSONString(problems)+")");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @RequestMapping(value = "getSome" , method=RequestMethod.GET)
    @ResponseBody
    public void getSome(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		              , Problem problem  ){
    	
    	Map<String,String> where = new HashMap<String,String>();
    	
    	where.put("proName", problem.getProName());
    	where.put("problemObject", problem.getProblemObject());
    	where.put("state", problem.getState());
    	
    	List<Problem> problems = problemMapper.selectByWhere(where);
    	Writer w = null;
		try {
			w = res.getWriter();
			w.write("problem_getSome("+JSONObject.toJSONString(problems)+")");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
        @RequestMapping(value = "gotoModify" , method=RequestMethod.GET) 
    public void gotoModify(HttpServletResponse res , HttpServletRequest req ,HttpSession session
            , Problem problem ,String callback){
    	Writer w = null;
    	try {	
			w = res.getWriter();
			Map<String,String> where = new HashMap<String,String>();
			String path = req.getSession().getServletContext().getRealPath("upload");
			
			where.put("proName", problem.getProName());
			where.put("problemObject", problem.getProblemObject());
			where.put("state", problem.getState());
			 where.put("problemId", problem.getProblemId());
			
			List<Problem> problems = problemMapper.selectByWhere(where);
			if(0  == problems.size()){
				w.write(callback + "({'message':'无此问题'})");
			}
			
			Problem p = problems.get(0);
			if(null != p.getProblemEnclosure() && !"".equalsIgnoreCase(p.getProblemEnclosure())){
				p.setProblemEnclosure(p.getProblemEnclosure().replace(path.substring(0,path.indexOf("upload")), "/hdk/"));
			}

			w.write( callback + "("+JSONObject.toJSONString(p)+")");
		} catch (IOException e) {
			e.printStackTrace();
			try {
				w.write(callback + "({'message':'fail'})");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
    }
        @RequestMapping(value = "saveMessage" , method=RequestMethod.GET)
        @ResponseBody
        public String saveMessage(HttpServletResponse res , HttpServletRequest req ,HttpSession session
        		, Message message ,String callback){
        	
        	       try{
        	    	   messageMapper.insertSelective(message); 
        	       }catch(Exception e ){
        	    	   e.printStackTrace();
        	    	  return  callback + "({'message':'fail'})";
        	       }
    			 return callback + "({'message':'success'})";
        }
        @RequestMapping(value = "getMessageSome" , method=RequestMethod.GET)
        @ResponseBody
        public void getMessageSome(HttpServletResponse res , HttpServletRequest req ,HttpSession session
        		, Message message ,String callback){
        	Writer w = null ;
        	try{
        		 w = res.getWriter();
        		
        		Map<String,String> where = new HashMap<String,String>();
        		where.put("problemId", message.getProblemId());
        		where.put("mesUser", message.getMesUser());
    			w.write( callback + "("+JSONObject.toJSONString(messageMapper.selectByWhere(where ))+")");
        	}catch(Exception e ){
        		e.printStackTrace();
        		  try {
        			  if(w != null ){
        				  w.write("{'message':'fail'}");
        			  }else{
						 w = res.getWriter();
						 w.write("{'message':'fail'}");
        			  }
				} catch (IOException e1) {
					e1.printStackTrace();
				}
        	}
        }
         
	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		applicationContext = ctx;
	}   

}