package com.intfocus.hdk.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intfocus.hdk.dao.PortalRouteReportMapper;
import com.intfocus.hdk.service.PortalRouteReportService;
import com.intfocus.hdk.vo.PortalRouteReport;

@Service("portalRouteReportService")
public class PortalRouteReportServiceImpl implements PortalRouteReportService {
	@Autowired
	private PortalRouteReportMapper portalRouteReportMapper;
	
	@Override
	public PortalRouteReport queryObject(Integer id){
		return portalRouteReportMapper.queryObject(id);
	}
	
	@Override
	public List<PortalRouteReport> queryList(Map<String, Object> map){
		return portalRouteReportMapper.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return portalRouteReportMapper.queryTotal(map);
	}
	
	@Override
	public void save(PortalRouteReport portalRouteReport){
		portalRouteReportMapper.save(portalRouteReport);
	}
	
	@Override
	public void update(PortalRouteReport portalRouteReport){
		portalRouteReportMapper.update(portalRouteReport);
	}
	
	@Override
	public void delete(Integer id){
		portalRouteReportMapper.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] codes){
		portalRouteReportMapper.deleteBatch(codes);
	}
	
}
