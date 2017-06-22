package com.intfocus.hdk.dao;

import java.util.List;
import java.util.Map;

import com.intfocus.hdk.vo.Problem;

public interface ProblemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Problem record);

    int insertSelective(Problem record);

    Problem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Problem record);

    int updateByPrimaryKeyWithBLOBs(Problem record);

    int updateByPrimaryKey(Problem record);
    
    List<Problem> getCount( Map<String,String>where);
    
    List<Problem> selectByWhere(Map<String,String>where);
    
    List<Map<String,String>> codeUnique(Map<String,String>where);
}