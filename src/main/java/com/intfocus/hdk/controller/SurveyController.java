
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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.intfocus.hdk.dao.CashMapper;
import com.intfocus.hdk.dao.PrinterMapper;
import com.intfocus.hdk.dao.ProjectMapper;
import com.intfocus.hdk.dao.ShopsMapper;
import com.intfocus.hdk.dao.SurveyMapper;
import com.intfocus.hdk.util.ComUtil;
import com.intfocus.hdk.vo.Cash;
import com.intfocus.hdk.vo.Printer;
import com.intfocus.hdk.vo.Shops;
import com.intfocus.hdk.vo.Survey;

@Controller
@RequestMapping("/survey")
public class SurveyController implements ApplicationContextAware {
    private final static Logger log =  Logger.getLogger(SurveyController.class);
    private static ApplicationContext applicationContext; 
    @Resource
    private SurveyMapper surveymapper ;
    @Resource
    private ProjectMapper projectMapper ;
    @Resource
    private ShopsMapper shopsMapper ;
    @Resource
    private PrinterMapper printerMapper ;
    @Resource
    private CashMapper cashMapper ;
    
    @RequestMapping(value = "modify" , method=RequestMethod.POST)
    @ResponseBody
    public String modify(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		, Survey survey , Printer printer , Cash cash , Shops shops,String callback, String files
    		,String userName ,String userNum){
    	
		try {
			   if("".equalsIgnoreCase(files)){	
					Map<String,String> result = ComUtil.savePicture(files, req.getSession().getServletContext().getRealPath("upload"));
					if(!"ok".equalsIgnoreCase(result.get("message"))){
						return result.get("message");
					}
					survey.modifyAtachement(((result.get("urls")).toString()));
			   }

				surveymapper.updateByPrimaryKeyWithBLOBs(survey);
				printerMapper.updateByPrimaryKeySelective(printer);
				cashMapper.updateByPrimaryKeySelective(cash);
				shopsMapper.updateByPrimaryKeySelective(shops);
		}catch(Exception e){
			e.printStackTrace();
//			return callback+"({'message':'fail'})";
			return "{'message':'fail'}";
		}
//		return callback+"({'message':'success'})";
		return "{'message':'success'}";
    	
    	
    	
    }
     @RequestMapping(value = "getList" , method=RequestMethod.GET)
    @ResponseBody
    public void getList(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		              ,  Survey survey ){
    	
    }   
    
    @RequestMapping(value = "getSome" , method=RequestMethod.GET)
    @ResponseBody
    public void getSome(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		              , Survey survey ){
    	
    	Map<String,String> where = new HashMap<String,String>();
		where.put("proName", survey.getProId());	
		where.put("shopName", survey.getShopId());	
		where.put("shopStation", survey.getShopMerStation());
    	try {
			Writer w = res.getWriter();

		if("正序".equalsIgnoreCase(survey.getOrder())){
			where.put("order", "asc");
		}else if("倒序".equalsIgnoreCase(survey.getOrder())){
			where.put("order", "desc");
		}
    	List<Survey> surveys = surveymapper.selectByWhere(where);
    	
		w.write( "getSome("+JSONObject.toJSONString(surveys) +")");	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    
    @RequestMapping(value = "gotoModify" , method=RequestMethod.GET)
    public void gotoModify(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		              , String surId , String callback ){
    	

    	// 取出相关的信息
    	JSONObject json = new JSONObject();
    	Map<String, String> where = new HashMap<String,String>();
    	where.put("surId", surId);
		// 调研
    	List<Survey> surveys = surveymapper.selectByWhere(where ); 
    	Survey survey1 = surveys.get(0);
    	json.put("survey", survey1);
    	// 收银机 
    	List<Printer> printers = printerMapper.selectByWhere(where ); 
    	Printer printer = printers.get(0);
    	json.put("printer", printer);
    	

    	
    	// 打印机
    	List<Cash> cashes = cashMapper.selectByWhere(where ); 
    	Cash cash = cashes.get(0);
    	json.put("cash", cash);
    	
    	//门店
    	List<Shops> shops = shopsMapper.selectByWhere(where ); 
    	Shops shop = shops.get(0);
    	json.put("shops", shop);
    	where.clear();  	
    	Writer w = null;
		try {
			w = res.getWriter();
			w.write( callback + "("+json.toJSONString()+")");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	@RequestMapping(value = "submit" , method=RequestMethod.POST)
    @ResponseBody
    public String submit(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		, Survey survey , Printer printer , Cash cash , Shops shops ,String callback, String files
    		,String userName ,String userNum){
    	
	   if("".equalsIgnoreCase(files)){	
		Map<String,String> result = ComUtil.savePicture(files, req.getSession().getServletContext().getRealPath("upload"));
		
		
		if(!"ok".equalsIgnoreCase(result.get("message"))){
			return result.get("message");
		}
		survey.setAttachmentUrl((result.get("urls")).toString());
	   }
		try {

				surveymapper.insertSelective(survey);
				printerMapper.insertSelective(printer);
				cashMapper.insertSelective(cash);
				
				Map<String, String> where = new HashMap<String , String >();
				where.put("shopId", shops.getShopId());
				Shops s = new Shops();
				List<Shops> shopss = shopsMapper.selectByWhere(where );
				if(0 < shopss.size()){
					s = shopss.get(0);	
				}
			    shops.setId(s.getId());
				shopsMapper.updateByPrimaryKeySelective(shops);
		}catch(Exception e){
			e.printStackTrace();
//			return callback+"({'message':'fail'})";
			return "{'message':'fail'}";
		}
//		return callback+"({'message':'success'})";
		return "{'message':'success'}";
    }
	
	   @InitBinder("survey")    
	   public void initBinder1(WebDataBinder binder) {    
	           binder.setFieldDefaultPrefix("survey.");    
	  } 
	   @InitBinder("printer")    
	   public void initBinder2(WebDataBinder binder) {    
		   binder.setFieldDefaultPrefix("printer.");    
	   } 
	   @InitBinder("cash")    
	   public void initBinder3(WebDataBinder binder) {    
		   binder.setFieldDefaultPrefix("cash.");    
	   } 
	   @InitBinder("shops")    
	   public void initBinder4(WebDataBinder binder) {    
		   binder.setFieldDefaultPrefix("shops.");    
	   } 
	   
	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		applicationContext = ctx;
	}   

}