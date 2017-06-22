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
import com.intfocus.hdk.dao.ShopsMapper;
import com.intfocus.hdk.vo.Shops;

@Controller
@RequestMapping("/shops")
public class ShopController implements ApplicationContextAware {
    private final static Logger log =  Logger.getLogger(ShopController.class);
    private static ApplicationContext applicationContext; 
    
    @Resource
    private ShopsMapper shopsmapper ;
            @RequestMapping(value = "submit" , method=RequestMethod.POST)
    @ResponseBody
    public void submit(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		              ,  Shops shops ){
    	
    }
            
            
            @RequestMapping(value = "selectForCombobox" , method=RequestMethod.GET)
            @ResponseBody
            public void selectForCombobox(HttpServletResponse res , HttpServletRequest req ,HttpSession session
            		, Shops shops ){
            	Map<String, String> where = new HashMap<String,String>();
            	where.put("shopName", shops.getShopName());
            	where.put("installStation", shops.getInstallStation());
            	where.put("eqType", shops.getEqType());
            	where.put("proName", shops.getProId());
        		List<Shops> shopss = shopsmapper.selectForCombobox(where );
        		Writer w;
        		try {
        			w = res.getWriter();
        			w.write("shops_selectForCombobox("+JSONObject.toJSONString(shopss)+")");
        		} catch (IOException e) {
        			e.printStackTrace();
        		}

            }
    
    @RequestMapping(value = "getSome" , method=RequestMethod.GET)
    @ResponseBody
    public void getSome(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		              , Shops shops ){
		
    	Map<String, String> where = new HashMap<String,String>();
    	where.put("shopName", shops.getShopName());
    	where.put("installStation", shops.getInstallStation());
    	where.put("eqType", shops.getEqType());
    	where.put("proName", shops.getProId());
		List<Shops> shopss = shopsmapper.selectByWhere(where );
		Writer w;
		try {
			w = res.getWriter();
			w.write("shops_getSome("+JSONObject.toJSONString(shopss)+")");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @RequestMapping(value = "modify" , method=RequestMethod.POST)
    @ResponseBody
    public void modify(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		, Shops shops){
    	
    }
    
	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		applicationContext = ctx;
	}   

}