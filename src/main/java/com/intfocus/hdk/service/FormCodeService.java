package com.intfocus.hdk.service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.intfocus.hdk.dao.FormCodeMapper;
import com.intfocus.hdk.util.ComUtil;
import com.intfocus.hdk.vo.FormCode;

@Component
public class FormCodeService {
    @Resource
    private FormCodeMapper formCodeMapper;
	public synchronized String getFormCode( String formType , String proCode){
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date()) ;  	
    	Map<String, String> where = new HashMap<String,String>();
    	where.put("formType",formType );
		FormCode formCode =  formCodeMapper.selectByWhere(where );	
		if(null != formCode && !date.equals(formCode.getDatestime())){
			formCode.setDatestime(date);
			formCode.setCodeMax(1);
			formCodeMapper.updateByPrimaryKey(formCode);
		}
		formCodeMapper.updateMaxCode(formType);
		return proCode + ComUtil.dateFormat(new Date(), "yyMMdd") + ((formCode.getCodeMax() < 10000) ? 
																				new DecimalFormat("0000").format(formCode.getCodeMax()) :formCode.getCodeMax());
	}
	
}
