package com.intfocus.hdk.controller;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.intfocus.hdk.util.ComUtil;

@Controller
@RequestMapping("/image")
public class ImageUploadController {

    @RequestMapping(value = "recerverImag" ,method=RequestMethod.POST)
    @ResponseBody
    public String recerverImag(HttpServletResponse res , HttpServletRequest req ,HttpSession session
           ) throws IOException  {
    	
    	  JSONObject result = new JSONObject();
    	  MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
    	   CommonsMultipartFile orginalFile = (CommonsMultipartFile) multipartRequest
    	     .getFile("files");// 表单中对应的文件名；
    		//ComUtil.savePicture(files, req.getSession().getServletContext().getRealPath("upload"));
    	   if (orginalFile != null && !orginalFile.isEmpty()) {// 如果有文章中带有附件
    		    String filename = orginalFile.getOriginalFilename();
    		    filename =  ComUtil.getRandomFileNameWithsuffix(filename);
    		    result.put("fileName", filename);
    		    DataOutputStream out = new DataOutputStream(new FileOutputStream(
    		    		req.getSession().getServletContext().getRealPath("upload")+"/" + filename));// 存放文件的绝对路径
    		    InputStream is = null;// 附件输入流
    		    try {
    		     is = orginalFile.getInputStream();
    		     byte[] b=new byte[is.available()];
    		     is.read(b);
    		     out.write(b);
    		     result.put("message", "success");
    		    } catch (IOException exception) {
    		     exception.printStackTrace();
    		     result.put("messsage", "fail");
    		    } finally {
    		     if (is != null) {
    		      is.close();
    		     }
    		     if (out != null) {
    		      out.close();
    		     }
    		    }

    		   }
    		return result.toJSONString();
    }
}
