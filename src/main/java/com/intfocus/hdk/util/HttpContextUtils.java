package com.intfocus.hdk.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.intfocus.hdk.vo.ParamVo;

public class HttpContextUtils {

    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    
    
    /**
     * 根据参数动态生成where 语句
     */
    public static String getWhere(List<ParamVo> paramters , HttpServletRequest request) {
        Map<String , String[]> params = request.getParameterMap();
    	Iterator it = params.keySet().iterator();
    	
    	String orderBy = " order by ";
    	StringBuffer sb = new StringBuffer();
    	sb.append(" where 1=1 ");
    	//先把排序部分拼装完成
    	for(int i =0 ; i < params.size();i++){
    		String[] o = params.get("order["+i+"][column]");
    		if(null != o){
    			orderBy = orderBy + o[0] + " " + params.get("order["+i+"][column]")[0] + params.get("order["+i+"][dir]")[0];
    		}else{
    			break;
    		}
    	}
    	for(ParamVo p : paramters){
    		sb.append(" and " +p.getKey())
    		    .append("  ")
    		    .append(p.getOperator())
    		    .append("  ")
    		    .append(ComUtil.isInteger(p.getValue().toString()) ? p.getValue() : "'" + p.getValue() +"'")
    		    .append("  ");
    	}
    	String str = sb.toString();
    	if (StringUtils.isEmpty(str)) {
    		return null;
    	}
    	return str;
    }
    /**
     * 封装请求参数
     */
    public static String getRequestParameter(HttpServletRequest request) {
        Map params = request.getParameterMap();
        Iterator it = params.keySet().iterator();
        StringBuffer sb = new StringBuffer();
        while (it.hasNext()) {
            String paramName = (String) it.next();
            String paramValue = request.getParameter(paramName);
            //处理得到的参数名与值
            if (!"yongHuiReportCustomCode".equals(paramName)) {
                sb.append(paramName + "=" + paramValue + "@@");
            }
        }

        String str = sb.toString();
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        return str.substring(0, str.length() - 2);
    }

    /**
     * 记录日志用，获取请求里面的全部参数
     * @param request
     * @return
     */
    public static String getParameterForLog(HttpServletRequest request) {
        Map params = request.getParameterMap();
        Iterator it = params.keySet().iterator();
        StringBuffer sb = new StringBuffer();
        while (it.hasNext()) {
            String paramName = (String) it.next();
            String paramValue = request.getParameter(paramName);
            //处理得到的参数名与值
                sb.append(paramName + "=" + paramValue + "@@");
        }

        String str = sb.toString();
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        return str.substring(0, str.length() - 2);
    }

}
