package com.intfocus.hdk.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Writer;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.intfocus.hdk.dao.ToolMapper;
import com.intfocus.hdk.util.ExcelReader;

@Controller
@RequestMapping("/upload")
public class ImportSome {

	@Resource
	ToolMapper toolMapper;	
	

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String user(HttpServletRequest request,  
            HttpServletResponse response,@RequestParam("file_upload") CommonsMultipartFile file , @RequestParam("tableName") String tableName){
		
          String filePath  = request.getSession().getServletContext().getRealPath("/") + "/uploadXML/" ;
		  JSONObject rs = new JSONObject();	 
		  int row = 0 ;
		  List<String> sqllist = null ;
      	try {
      		// MultipartFile是对当前上传的文件的封装，当要同时上传多个文件时，可以给定多个MultipartFile参数(数组)
      		if (!file.isEmpty()) {
      			
      		 String type = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
      		 String filename = System.currentTimeMillis() + type;		 
      		
			  File destFile = new File(filePath + filename);
			  FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);// 复制临时文件到指定目录下

               ExcelReader excelReader = new ExcelReader();
               
               // 对读取Excel表格内容测试
               InputStream f = new BufferedInputStream( new FileInputStream(filePath + filename));
//               String str = excelReader.checkCols(f, tableName);
//               if(!"".equalsIgnoreCase(str)){
//             		request.setAttribute("message", "您传的文档中缺少以下几列数据：" + str );
//            	   return "fail";
//               }
      
               sqllist = excelReader.sqlGennerater(f, tableName);
               for(String sql : sqllist){
            	   row++;
            	   toolMapper.excuteOneByOne(sql);
               }
      		}
      	} catch (Exception e) {
      		e.printStackTrace();
      		request.setAttribute("message", "第" + row + "行出现错误，请参考下面的信息" +  sqllist.get(row- 1) );
      		return "fail";
		}
		return "success";
		
	}
}
