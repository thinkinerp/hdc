package com.intfocus.hdk.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intfocus.hdk.dao.PortalReportMapper;
import com.intfocus.hdk.service.PortalReportService;
import com.intfocus.hdk.vo.PortalReport;

@Service("portalReportService")
public class PortalReportServiceImpl implements PortalReportService {
	@Autowired
	private PortalReportMapper portalReportMapper;
	
	@Override
	public PortalReport queryObject(Integer id){
		return portalReportMapper.queryObject(id);
	}

	@Override
	public PortalReport queryObjectByCode(String code) {
		return portalReportMapper.queryObjectByCode(code);
	}

	@Override
	public List<PortalReport> queryList(Map<String, Object> map){
		return portalReportMapper.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return portalReportMapper.queryTotal(map);
	}
	
	@Override
	public void save(PortalReport portalReport){
		portalReportMapper.save(portalReport);
	}
	
	@Override
	public void update(PortalReport portalReport){
		portalReportMapper.update(portalReport);
	}
	
	@Override
	public void delete(Integer id){
		portalReportMapper.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		portalReportMapper.deleteBatch(ids);
	}

	@Override
	public void deleteBatchByCodes(String[] codes) {
		portalReportMapper.deleteBatchByCodes(codes);
	}
}
