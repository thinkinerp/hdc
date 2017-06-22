package com.intfocus.hdk.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.intfocus.hdk.dao.UserMapper;
import com.intfocus.hdk.vo.User;

@Controller
@RequestMapping("/user")
public class UserController {
    private final static Logger log =  Logger.getLogger(CashController.class);

    @Resource
    UserMapper userMapper ;
    
    @Resource
    private UserMapper usermapper ;
            @RequestMapping(value = "submit" , method=RequestMethod.POST)
    @ResponseBody
    public void submit(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		              ,  User user ){
    	
    }
    
	@RequestMapping(value = "getDepartment" , method=RequestMethod.GET)
	@ResponseBody
	public void getDepartment(HttpServletResponse res , HttpServletRequest req ){
		
		Writer writer = null ;
		
		try{
		writer =  res.getWriter();
		writer.write("getDepartment("+JSONObject.toJSONString(usermapper.getDepartment())+")");
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
    @RequestMapping(value = "getSome" , method=RequestMethod.GET)
    @ResponseBody
    public void getSome(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		              , User user ){
    	
    	Map<String, String> where = new HashMap<String,String>();
    	where.put("name", user.getName());
		Writer writer = null ;
		
		try{
			writer = res.getWriter();
			writer.write("getSome("+JSONObject.toJSONString(usermapper.selectByWhere(where ))+")");
		} catch (IOException e) {
			e.printStackTrace();
		}	
    	
    }
    @RequestMapping(value = "modify" , method=RequestMethod.POST)
    @ResponseBody
    public void modify(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		, User user){
    	
    }
    
    
}
