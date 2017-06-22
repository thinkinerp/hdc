package com.intfocus.hdk.dao;

import java.util.List;
import java.util.Map;

import com.intfocus.hdk.vo.Project;

public interface ProjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);

	List<Project> selectByWhere(Map<String ,String> where);
	
	int setAllNotIsLast();
	
	List<Map<String, String>> selectProjectCount();
	
	List<Project> getCheck(Map<String,String> where);

	List<Project> getCashCount(Map<String,String> where);

	List<Project> getEquipment(Map<String,String> where);

	List<Project> getProjectProblem(Map<String,String> where);
	
	List<Project> getProjectSummarise(Map<String,String> where);
	
}