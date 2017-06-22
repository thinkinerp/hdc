package com.intfocus.hdk.controller;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.intfocus.hdk.dao.CashMapper;
import com.intfocus.hdk.dao.EquipmentMapper;
import com.intfocus.hdk.dao.InstallMapper;
import com.intfocus.hdk.dao.PrinterMapper;
import com.intfocus.hdk.dao.ProjectMapper;
import com.intfocus.hdk.dao.ShopsMapper;
import com.intfocus.hdk.util.ComUtil;
import com.intfocus.hdk.vo.Cash;
import com.intfocus.hdk.vo.Equipment;
import com.intfocus.hdk.vo.Install;
import com.intfocus.hdk.vo.Printer;
import com.intfocus.hdk.vo.Project;
import com.intfocus.hdk.vo.Shops;

@Controller
@RequestMapping("/install")
public class InstallController implements ApplicationContextAware {
    private final static Logger log =  Logger.getLogger(InstallController.class);
    private static ApplicationContext applicationContext; 
    @Resource
    private InstallMapper installmapper ;
    
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
    
    @RequestMapping(value = "test" ,method=RequestMethod.POST)
    @ResponseBody
    public String test(HttpServletResponse res , HttpServletRequest req ,HttpSession session,
    		              @RequestParam String files)  {
	   
	   ComUtil.savePicture(files, req.getSession().getServletContext().getRealPath("upload"));
	   return  "{'messsage':'success'}";
   
   }
    @RequestMapping(value = "submit" , method=RequestMethod.POST)
    @ResponseBody
 public String submit(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		              ,  Install install,Printer printer,Cash cash,Equipment equipment ,
    		              @RequestParam(value = "files", required = false) String files 
    		              ,String userName ,String userNum) throws Exception {
    	
    	log.info("userName:"+userName+"userNum:"+userNum);
    	Map<String,String> rs = null ;
    	try{
	    	if(null != files && !"".equalsIgnoreCase(files)){
	    		rs = new HashMap<String,String>();
	    		rs = ComUtil.savePicture(files, req.getSession().getServletContext().getRealPath("upload"));
	    		if(!"ok".equalsIgnoreCase(rs.get("message"))){
	    			return "{'message':'"+rs.get("message")+"'}";
	    		}
	    	}
	    	Map<String, String> where = new HashMap<String,String>();
		   where.put("proName",install.getProId());
		   
			List<Project> projects = projectMapper.selectByWhere(where );
			Project i = projects.get(0);
			install.setProId(i.getProId());	
			equipment.setProId(i.getProId());
			
			install.setAttachment_url(null != rs ? rs.get("urls") :null );
	    	installmapper.insertSelective(install);
	    	printerMapper.insertSelective(printer);
	    	cashMapper.insertSelective(cash);
	    	equipmentMapper.insertSelective(equipment);
	    	return "{'message':'success'}";
	  }catch(Exception e){
		  e.printStackTrace();
		  return "{'message':'fail'}";
	  }
	  
    }
   @InitBinder("install")    
   public void initBinder1(WebDataBinder binder) {    
           binder.setFieldDefaultPrefix("install.");    
  } 
   @InitBinder("printer")    
   public void initBinder2(WebDataBinder binder) {    
	   binder.setFieldDefaultPrefix("printer.");    
   } 
   @InitBinder("cash")    
   public void initBinder3(WebDataBinder binder) {    
	   binder.setFieldDefaultPrefix("cash.");    
   } 
   @InitBinder("equipment")    
   public void initBinder4(WebDataBinder binder) {    
	   binder.setFieldDefaultPrefix("equipment.");    
   } 
   
   
   
   @RequestMapping(value = "gotoModify" , method=RequestMethod.GET)
   public String gotoModify(HttpServletResponse res , HttpServletRequest req ,HttpSession session
		   , Install install ){
	   
	   JSONObject json = new JSONObject();
	   Map<String, String> where = new HashMap<String,String>();
	   where.put("installId", install.getInstallId());
	   List<Install> installs = installmapper.selectByWhere(where);
	   String path = req.getSession().getServletContext().getRealPath("upload");
	   if(null != installs && installs.size() > 0 ){
		   
		   Install i = installs.get(0);
		   if( null != i.getAttachmentUrl() && !"".equalsIgnoreCase(i.getAttachmentUrl())){
			   i.setAttachment_url(i.getAttachmentUrl().replace(path.substring(0,path.indexOf("upload")), "/hdk/"));
		   }
		   json.put("install", i);
		   where.clear();
		   //找到门店
		   where.put("shopId", installs.get(0).getShopId());
		   List<Shops> shopss = shopsMapper.selectByWhere(where);
		   
		   if(null != shopss && shopss.size()>0){
			   json.put("shop",shopss.get(0));
		   }
		   
		   //找到收款机
		   where.clear();
		   where.put("cashId", installs.get(0).getCashId());
		   List<Cash> cashes = cashMapper.selectByWhere(where);
		   
		   if(null != cashes && cashes.size() > 0 ){
			   json.put("cash",cashes.get(0));
		   }
		   //打印机
		   where.clear();
		   where.put("printerId", installs.get(0).getPrinterId());
		   List<Printer> printers = printerMapper.selectByWhere(where);
		   
		   if(null != printers && printers.size() > 0 ){
			   json.put("printer",printers.get(0));
		   }
		   //采集点
		   where.clear();
		   where.put("eqId", installs.get(0).getEqId());
		   List<Equipment> equipments = equipmentMapper.selectByWhere(where);
		   
		   if(null != equipments && equipments.size() > 0 ){
			   json.put("equipment",equipments.get(0));
		   }
		   //项目
		   where.clear();
		   where.put("proId", installs.get(0).getProId());
		   List<Project> projects = projectMapper.selectByWhere(where);
		   
		   if(null != projects && projects.size() > 0 ){
			   json.put("project",projects.get(0));
		   }
		   
		   
	   }
	   return "forward:/installDetails.jsp?allThing=" + json.toJSONString();
   }
    @RequestMapping(value = "getSome" , method=RequestMethod.POST)
    @ResponseBody
    public void getSome(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    		              , Install install ){
    	
    }
    @RequestMapping(value = "modify" , method=RequestMethod.POST)
    @ResponseBody
    public String modify(HttpServletResponse res , HttpServletRequest req ,HttpSession session
    ,  Install install,Printer printer,Cash cash,Equipment equipmengt ,String files 
    ,String userName ,String userNum){
    	try{
    		
        	Map<String,String> rs = null ;
    	    	if(null != files && !"".equalsIgnoreCase(files)){
    	    		rs = new HashMap<String,String>();
    	    		rs = ComUtil.savePicture(files, req.getSession().getServletContext().getRealPath("upload"));
    	    		if(!"ok".equalsIgnoreCase(rs.get("message"))){
    	    			return "{'message':'"+rs.get("message")+"'}";
    	    		}
    	    	}
    		install.modifyAtachement(rs.get("urls"));
			installmapper.updateByPrimaryKeySelective(install);
			printerMapper.updateByPrimaryKeySelective(printer);
			cashMapper.updateByPrimaryKeySelective(cash);
			equipmentMapper.updateByPrimaryKeySelective(equipmengt);

    	}catch(Exception e){
			e.printStackTrace();
			return "fail" ;
    	}
		return "{'message':'success'}" ;
    }
	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		applicationContext = ctx;
	}   
    
	
}