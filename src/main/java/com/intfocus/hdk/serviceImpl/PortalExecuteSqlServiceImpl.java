package com.intfocus.hdk.serviceImpl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intfocus.hdk.dao.PortalExecuteSqlMapper;
import com.intfocus.hdk.service.PortalExecuteSqlService;
import com.intfocus.hdk.vo.PortalExecuteSql;

@Service("portalExecuteSqlService")
public class PortalExecuteSqlServiceImpl implements PortalExecuteSqlService {
	@Autowired
	private PortalExecuteSqlMapper portalExecuteSqlMapper;
	
	@Override
	public PortalExecuteSql queryObject(Integer id){
		return portalExecuteSqlMapper.queryObject(id);
	}

	@Override
	public PortalExecuteSql queryObjectBySqlcode(String sqlcode){
		return portalExecuteSqlMapper.queryObjectBySqlcode(sqlcode);
	}
	
	@Override
	public List<PortalExecuteSql> queryList(Map<String, Object> map){
		return portalExecuteSqlMapper.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return portalExecuteSqlMapper.queryTotal(map);
	}
	
	@Override
	public void save(PortalExecuteSql portalExecuteSql){
		portalExecuteSqlMapper.save(portalExecuteSql);
	}
	
	@Override
	public void update(PortalExecuteSql portalExecuteSql){
		portalExecuteSqlMapper.update(portalExecuteSql);
	}
	
	@Override
	public void delete(Integer id){
		portalExecuteSqlMapper.delete(id);
	}
	
	@Override
	public void deleteBatchBySqlcodes(String[] sqlcodes){
		portalExecuteSqlMapper.deleteBatchBySqlcodes(sqlcodes);
	}
	
}
