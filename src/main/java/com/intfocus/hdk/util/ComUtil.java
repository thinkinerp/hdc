package com.intfocus.hdk.util;

import java.io.File;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.util.Base64Utils;

import com.alibaba.fastjson.JSONArray;

public class ComUtil {
	
	public static String getRandomFileName(){
  
         return UUID.randomUUID().toString();// 当前时间  
        
	}

	public static String getRandomFileNameWithsuffix(String oFileName){
		if(null != oFileName && !"".equalsIgnoreCase(oFileName)){
			return getRandomFileName() + oFileName.substring(oFileName.indexOf("."));
		}else{
			return null;
		}
	}
	
	public static String[] remove( String target , String[] list ){
		String[] rs = list.clone() ;
		for(int i = 0 ; i < rs.length;i++){
			if(target.equalsIgnoreCase(rs[i])){
				rs[i] = rs[list.length -1];
				rs = Arrays.copyOf(rs, rs.length - 1);
				break;
			}
		}
		return rs ;
	}
	
	public static Map<String,String> savePicture(String files ,String url ){
	       String dataPrix = "";
	       String data = "";
	       Map<String,String> result = new HashMap<String,String>();
	       JSONArray  jsonArray  = JSONArray.parseArray(files);
	       String [] d = null ; 
	       List<String> urls = new ArrayList<String>();
		   if(null != jsonArray && 0 != jsonArray.size()){ 
			   for(int i =0 ; i < jsonArray.size();i = i +1){
				   if(!jsonArray.getString(i).startsWith("data:image")){
					   continue;
				   }
				    d = jsonArray.getString(i).split("base64,"); 
				    if(d != null && d.length == 2){
				    	
				    	dataPrix =d[0];
				    	data = d[1];
				    }else{
				    	result.put("message", "上传失败，数据不合法");
				    	return result;
				    }
	               
	               String suffix = "";
	               if("data:image/jpeg;".equalsIgnoreCase(dataPrix)){//data:image/jpeg,base64编码的jpeg图片数据
	                   suffix = ".jpg";
	               } else if("data:image/x-icon;".equalsIgnoreCase(dataPrix) || "data:image/vnd.microsoft.icon;".equalsIgnoreCase(dataPrix)){//data:image/x-icon,base64编码的icon图片数据
	                   suffix = ".ico";
	               } else if("data:image/gif;".equalsIgnoreCase(dataPrix)){//data:image/gif;base64,base64编码的gif图片数据
	                   suffix = ".gif";
	               } else if("data:image/png;".equalsIgnoreCase(dataPrix)){//data:image/png;base64,base64编码的png图片数据
	                   suffix = ".png";
	               } else {
	            	   
	            	   result.put("message", "图片格式不支持");
	            	   
	            	   return result;
	               }
	               
	               String tempFileName = ComUtil.getRandomFileName()+ suffix;
	               byte[] bs = Base64Utils.decodeFromString(data);
	               try{
	                   //使用apache提供的工具类操作流
	            	   urls.add(url+"/" +tempFileName);
	                   FileUtils.writeByteArrayToFile(new File( url, tempFileName), bs);  
	               }catch(Exception ee){
	                  ee.printStackTrace();
	                  result.put("message", "图片格式不支持");
	                  return result;
	               }
			   }
		  }
		   result.put("urls", org.apache.commons.lang.StringUtils.join(urls.toArray(),","));
		   result.put("message", "ok");
		   return result;
	}
	public static String dateFormat(String  date){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			if(null != date && !"".equalsIgnoreCase(date)){
				return sdf.format( sdf.parse(date));
			}else{
				return null ;
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
			
			return null ;
		}
	}
	public static String dateFormat(String  date, String formatter){
		
		SimpleDateFormat sdf = new SimpleDateFormat(formatter);
		
		try {
			if(null != date && !"".equalsIgnoreCase(date)){
				return sdf.format( sdf.parse(date));
			}else{
				return null ;
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
			
			return null ;
		}
	}
	public static String dateFormat(Date  date, String formatter){
		
		SimpleDateFormat sdf = new SimpleDateFormat(formatter);
		
			if(null != date ){
				return sdf.format( date);
			}else{
				return null ;
			}
		
	}
	public static Long dateCompare(String date1 , String date2){
		
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    try {
		Date dt1 = df.parse(date1);
		Date dt2 = df.parse(date2);
		long d = dt1.getTime() - dt2.getTime();
		return (long)d/60000;
	} catch (ParseException e) {
		e.printStackTrace();
		return null ;
	}
			
	}
	
    public static boolean reflect(Object obj) {  
        if (obj == null)  
            return false;  
        Field[] fields = obj.getClass().getDeclaredFields();  
        String[] types1={"int","java.lang.String","boolean","char","float","double","long","short","byte"};  
        String[] types2={"Integer","java.lang.String","java.lang.Boolean","java.lang.Character","java.lang.Float","java.lang.Double","java.lang.Long","java.lang.Short","java.lang.Byte"};  
        for (int j = 0; j < fields.length; j++) {  
            fields[j].setAccessible(true);  
            // 字段值  
            for(int i=0;i<types1.length;i++){  
                if(fields[j].getType().getName()  
                        .equalsIgnoreCase(types1[i])|| fields[j].getType().getName().equalsIgnoreCase(types2[i])){  
                    try {  
                    	if(null != fields[j].get(obj)){
                    		return true;  
                    	}
                    } catch (Exception e) {  
                        e.printStackTrace();  
                    }   
                }  
            }  
        }  
        return false;
    }  

}
