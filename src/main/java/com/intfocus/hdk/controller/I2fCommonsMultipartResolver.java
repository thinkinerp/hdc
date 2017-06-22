package com.intfocus.hdk.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.commons.CommonsFileUploadSupport;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
@Component
public class I2fCommonsMultipartResolver extends CommonsMultipartResolver {  
    
    protected CommonsFileUploadSupport.MultipartParsingResult parseRequest(HttpServletRequest request)throws MultipartException{  
    String encoding = determineEncoding(request);  
    FileUpload fileUpload = this.prepareFileUpload(encoding,request);  
        try {  
            List fileItems = ((ServletFileUpload) fileUpload).parseRequest(request);  
            return parseFileItems(fileItems, encoding);  
              
        } catch (FileUploadBase.SizeLimitExceededException ex) {  
            throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(),  
                    ex);  
        } catch (FileUploadException ex) {  
            throw new MultipartException(  
                    "Could not parse multipart servlet request", ex);  
        }  
     
  }  
      
    protected FileUpload prepareFileUpload(String encoding,HttpServletRequest request) {  
        FileUpload fileUpload = getFileUpload();  
        FileUpload actualFileUpload = fileUpload;  
        // Use new temporary FileUpload instance if the request specifies  
        // its own encoding that does not match the default encoding.  
        if (encoding != null && !encoding.equals(fileUpload.getHeaderEncoding())) {  
            actualFileUpload = newFileUpload(getFileItemFactory());  
            actualFileUpload.setHeaderEncoding(encoding);  
            boolean isAddProduct = request.getRequestURI().contains("/hdk/image") ;  
            if(isAddProduct){     
                actualFileUpload.setSizeMax(512 * 1024 * 10);//重新设置文件限制5M  
            }else{  
                actualFileUpload.setSizeMax(fileUpload.getSizeMax());  
            }  
        }  
        
        System.out.println("拦截图片上传");
        
        return actualFileUpload;  
    }  
  
}