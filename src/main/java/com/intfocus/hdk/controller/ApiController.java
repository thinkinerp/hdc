package com.intfocus.hdk.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.intfocus.hdk.service.PortalDataSourceService;
import com.intfocus.hdk.service.PortalExecuteSqlService;
import com.intfocus.hdk.service.PortalProcedureService;
import com.intfocus.hdk.service.PortalReportService;
import com.intfocus.hdk.serviceImpl.ApiServiceImpl;
import com.intfocus.hdk.util.ConstantsUtil;
import com.intfocus.hdk.util.HttpContextUtils;
import com.intfocus.hdk.util.R;
import com.intfocus.hdk.util.SQLFilter;
import com.intfocus.hdk.vo.ParamVo;
import com.intfocus.hdk.vo.PortalDataSource;
import com.intfocus.hdk.vo.PortalExecuteSql;
import com.intfocus.hdk.vo.PortalProcedure;
import com.intfocus.hdk.vo.PortalReport;

/**
 * 报表表统一入口 张海 2017.05.11
 */
@Controller
@RequestMapping("/api")
public class ApiController {

	@Resource
	ApiServiceImpl apiService;

	@Resource
	PortalReportService portalReportService;
	@Resource
	PortalProcedureService portalProcedureService;
	@Resource
	PortalDataSourceService portalDataSourceService;
	@Resource
	PortalExecuteSqlService portalExecuteSqlService;

	@RequestMapping(value = "custom")
	@ResponseBody
	public void portalCustom(HttpServletRequest req, HttpServletResponse response, String reportCustomCode,
			Integer draw, Integer start, Integer length, String wher, String callback) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Integer recordsTotal = 0;
		try {
			String parameter = null;

			List<ParamVo> params = new ArrayList<ParamVo>();
			JSONArray ja = JSONArray.parseArray(wher);
			Iterator<Object> it = ja.iterator();
			while (it.hasNext()) {
				JSONObject ob = (JSONObject) it.next();
				ParamVo pv = null;
				if (null != ob.getString("key") && null != ob.getString("value") && null != ob.getString("operator")) {
					pv = new ParamVo();
					pv.setKey(ob.getString("key"));
					pv.setOperator(ob.getString("operator"));
					pv.setValue(ob.getString("value"));
					params.add(pv);
				}
			}

			// 找到排序字段
			// 在写 SQL 语句的时候，写字段的时候，要写表的全称 + "." + 字段名的形式

			// 首先 yongHuiReportCustomCode 获得报表的 DataSource SQL语句 report信息
			PortalReport report = portalReportService.queryObjectByCode(SQLFilter.sqlInject(reportCustomCode));
			if (null == report) {
				R.error("报表编号不存在,请输入正确的");
			}
			parameter = HttpContextUtils.getWhere(params, req);
			if (null != start && null != length) {
				parameter = parameter + " limit " + start + " , " + length;
			}
			List<Map<String, Object>> dataList = null;
			PortalDataSource portalDataSource = null;
			if (report.getExecuteType() == ConstantsUtil.ExecuteType.PROCEDURE) {
				// 储存过程方式
				PortalProcedure portalPro = portalProcedureService.queryObjectByProcode(report.getExecuteCode());
				portalDataSource = portalDataSourceService.queryObjectByCode(portalPro.getDataSourceCode());
				dataList = apiService.getListResultListMapByPro(report, portalPro, portalDataSource, parameter);
			} else if (report.getExecuteType() == ConstantsUtil.ExecuteType.EXECUTESQL) {
				// sql语句方式
				PortalExecuteSql portalExecuteSql = portalExecuteSqlService
						.queryObjectBySqlcode(report.getExecuteCode());
				portalDataSource = portalDataSourceService.queryObjectByCode(portalExecuteSql.getDataSourceCode());
				dataList = apiService.getListResultListMapBySql(report, portalExecuteSql, portalDataSource, parameter);
				recordsTotal = apiService.getCountBySql(report, portalExecuteSql, portalDataSource);
			}
			// 调用 apiContrallor 的接口获取数据
			list = dataList;
			
		} catch (Exception e) {
			e.printStackTrace();
			R.error("执行统一报表程序异常");
		}
		Writer w = null;
		try {
			w = response.getWriter();
			Iterator<Map.Entry<String, Object>> entries = null;
			for (Map<String, Object> m : list) {

				entries = m.entrySet().iterator();

				while (entries.hasNext()) {

					Map.Entry<String, Object> entry = entries.next();
					if (null == entry.getValue()) {
						entry.setValue(" ");
					}
				}
			}

			w.write(callback + "(" + JSONObject.toJSONString(R.success(list, start, draw, recordsTotal, list.size()))
					+ ")");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}