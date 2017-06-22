package com.intfocus.hdk.dao;

import java.util.List;
import java.util.Map;

import com.intfocus.hdk.vo.Shops;

public interface ShopsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Shops record);

    int insertSelective(Shops record);

    Shops selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Shops record);

    int updateByPrimaryKey(Shops record);
    
    List<Shops> selectByWhere(Map<String,String> where);
    
    List<Shops> selectForCombobox(Map<String,String>where);
}