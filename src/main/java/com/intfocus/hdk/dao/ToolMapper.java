package com.intfocus.hdk.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ToolMapper {

	int excute(@Param("list") List<String> list  );
}
