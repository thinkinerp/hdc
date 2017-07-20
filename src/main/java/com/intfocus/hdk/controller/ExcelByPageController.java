package com.intfocus.hdk.controller;

import java.io.File;
import java.io.OutputStream;
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
 * 报表下载  黄增磊 2017/07/19
 */
@Controller
@RequestMapping("/api")
public class ExcelByPageController {
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
	
	@RequestMapping(value = "export")
	@ResponseBody
	public void portalCustom(HttpServletRequest req, HttpServletResponse response, String reportCustomCode,
			Integer draw, Integer start, Integer length, String wher, String callback) {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		@SuppressWarnings("unused")
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
			
			//下载报表到本地桌面
			HSSFWorkbook hwb = getExportExcel(list, report);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String time = format.format(new Date());
			
			String fileName = time + "_ProjectReport.xls";
			fileName =  StringUtils.replace(fileName, " ","");
			
			response.setContentType("application/x-msdownload");       
			response.setHeader("Content-disposition", "attachment;filename="+fileName);       
			   
			OutputStream ouputStream  = response.getOutputStream();
		    hwb.write(ouputStream);       
		    ouputStream.flush();       
		    ouputStream.close();   
			
		} catch (Exception e) {
			e.printStackTrace();
			R.error("同一报表下载异常");
		}
	}
	
	@SuppressWarnings("unchecked")
	public HSSFWorkbook getExportExcel(List<Map<String,Object>> list, PortalReport report){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = null;
		SAXBuilder builder = new SAXBuilder();
		
		Document doc = null;
		try {
			String xmlFileName = getResourcePath(report.getClass());
			doc = builder.build(new File(xmlFileName));
			Element root = doc.getRootElement();
			;
			sheet = wb.createSheet(); //创建excel文件
			
			//标题样式
			HSSFCellStyle styleTitle = wb.createCellStyle();
			styleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER); //居中
			
			HSSFFont fontTitle = wb.createFont();
			fontTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);	//加粗
			fontTitle.setFontName("宋体");
			fontTitle.setFontHeight((short)200);
			styleTitle.setFont(fontTitle);
			
			//表内容样式
			HSSFCellStyle styleText = wb.createCellStyle();
			styleText.setAlignment(HSSFCellStyle.ALIGN_CENTER);	//居中
			
			HSSFFont fontText = wb.createFont();
			fontText.setFontName("宋体");
			fontText.setFontHeight((short)200);
			styleText.setFont(fontText);
			
			int rownum = 0;
			List<Element> trs = null;
			
			
			//设置表头
			Element thead = root.getChild("thead");
			trs = thead.getChildren("tr");
			for(int i = 0; i < trs.size(); i++) {
				Element tr = trs.get(i);
				HSSFRow rowtTitle = sheet.createRow(rownum);
				List<Element> ths = tr.getChildren("th");
				for(int j = 0; j < ths.size(); j++) {
					Element th = ths.get(j);
					String cellName = th.getAttributeValue("value");
					HSSFCell cell = rowtTitle.createCell(j);
					if (cellName != null) {
						cell.setCellValue(cellName);
						cell.setCellStyle(styleTitle);
					}
				}
			}
			
			//设置值
			Element tbody = root.getChild("tbody");
			Element tr = tbody.getChild("tr");
			List<Element> tds = tr.getChildren("td");
			for(int i = 0; i < list.size(); i++) {
				Map<String,Object> map = list.get(i);
				HSSFRow row = sheet.createRow(++rownum); //每个 Map 创建一行
				for(int j = 0; j < tds.size(); j++) {
					HSSFCell cell = row.createCell(j);
					cell.setCellStyle(styleText);
					sheet.autoSizeColumn(j); //自动适应列宽
					String column = tds.get(j).getAttributeValue("column");
					setValue(cell, map, column);
				}
			}
			R.success(report.getTitle() + "生成成功！");
			return wb;
		} catch (Exception e) {
			e.printStackTrace();
			R.error(report.getTitle() + "生成失败！");
		}
		
		return wb;
	}
	
	public static void setValue(HSSFCell cell, Map<String,Object> map, String column) {
		Object type= null;
		if (map.containsKey(column)) {
			type = map.get(column);
		}
		
		if (type instanceof Boolean) {
			cell.setCellValue((Boolean)map.get(column));
		}else if (type instanceof Byte) {
			cell.setCellValue((Byte)map.get(column));
		}else if (type instanceof Short) {
			cell.setCellValue((Short)map.get(column));
		}else if (type instanceof Character) {
			cell.setCellValue((Character)map.get(column));
		}else if (type instanceof Integer) {
			cell.setCellValue((Integer)map.get(column));
		}else if (type instanceof Long) {
			cell.setCellValue((Long)map.get(column));
		}else if (type instanceof Float) {
			cell.setCellValue((Float)map.get(column));
		}else if (type instanceof Double) {
			cell.setCellValue((Double)map.get(column));
		}else if (type instanceof String) {
			cell.setCellValue((String)map.get(column));
		}else if (type instanceof Date) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			cell.setCellValue(format.format((Date)map.get(column)));
		}
	}
	
	public static String getResourcePath(Class<? extends PortalReport> clazz) {
        String className = clazz.getName(); 
        String classNamePath = className.replace(".", "/") + ".class"; 
        URL is = clazz.getClassLoader().getResource(classNamePath);
        String path = is.getFile();
        path = StringUtils.replace(path, "%20", " ");
        path = StringUtils.replace(path, StringUtils.replace(className , clazz.getPackage().getName() + ".", "") + ".class", "") ;
        String[] str = className.split("\\.");
        path = path + str[str.length - 1] + ".xml";
        
        return path; // 文件本地地址
    }
	
	
}
