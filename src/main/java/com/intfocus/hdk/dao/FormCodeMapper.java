package com.intfocus.hdk.dao;

import java.util.Map;

import com.intfocus.hdk.vo.FormCode;

public interface FormCodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FormCode record);

    int insertSelective(FormCode record);

    FormCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FormCode record);

    int updateByPrimaryKey(FormCode record);
    
    FormCode selectByWhere(Map<String,String>where);
    
   int updateMaxCode(String fromType); 
}