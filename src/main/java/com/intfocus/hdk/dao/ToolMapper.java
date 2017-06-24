package com.intfocus.hdk.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ToolMapper {

	int excuteBatche(@Param("list") List<String> list  );

	int excuteOneByOne(String sql);
}
