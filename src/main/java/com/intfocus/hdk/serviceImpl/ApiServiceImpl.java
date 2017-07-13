package com.intfocus.hdk.serviceImpl;

import com.intfocus.hdk.service.ApiDataBaseSqlService;
import com.intfocus.hdk.service.ApiService;
import com.intfocus.hdk.util.StringUtils;
import com.intfocus.hdk.vo.PortalDataSource;
import com.intfocus.hdk.vo.PortalExecuteSql;
import com.intfocus.hdk.vo.PortalProcedure;
import com.intfocus.hdk.vo.PortalReport;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.intfocus.hdk.util.StringUtils.getSqlByParameter;

/**
 * 实现ApiService接口
 * Created by 张海 on 2017/05/11
 */
@Service("apiService")
public class ApiServiceImpl implements ApiService {

    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private ApiDataBaseSqlService apiDataBaseSqlService;

    /**
     * 调用存储过程获取结果集
     *
     * @param parameter 请求参数 如：aa=AAA@@bb=CC@@dd=DD
     * @return
     */
    @Override
    public List<Map<String, Object>> getListResultListMapByPro(PortalReport report, PortalProcedure portalPro, PortalDataSource portalDataSource, String parameter) {
        String proParameter = StringUtils.getParameter(parameter, portalPro.getParameter());
        StringBuffer sb = new StringBuffer();
        sb.append("{call ");
        if (!StringUtils.isEmpty(portalPro.getProdb())) {
            sb.append(portalPro.getProdb() + ".");
        }
        if(proParameter == null){
            sb.append(portalPro.getProname() + " ()}");
        }else{
            sb.append(portalPro.getProname() + " (" + proParameter + ")}");
        }
        log.info("请求的参数：parameter" + parameter);
        log.info("执行的存储过程:" + sb.toString());
        return apiDataBaseSqlService.queryCallPro(sb.toString(), portalDataSource);
    }

    /**
     * 执行sql获取结果集
     *
     * @param parameter 请求参数 如：aa=AAA@@bb=CC@@dd=DD
     * @return
     */
    @Override
    public List<Map<String, Object>> getListResultListMapBySql(PortalReport report, PortalExecuteSql portalExecuteSql, PortalDataSource portalDataSource, String parameter) {
        // 封装执行的sql语句
        String sql = portalExecuteSql.getExecuteSql() + parameter;
        log.info("请求的参数：parameter" + parameter);
        log.info("执行的sql:" + sql);
        return apiDataBaseSqlService.queryExecuteSql(sql, portalDataSource);
    }

	public Integer  getCountBySql(PortalReport report,
			PortalExecuteSql portalExecuteSql, PortalDataSource portalDataSource) {
		String sql = portalExecuteSql.getExecuteSql() ;
		List<Map<String, Object>> count =  apiDataBaseSqlService.queryExecuteSql( "select count(1) as row_count from ( " + sql + " ) as a ", portalDataSource);
		return (count.size() > 0  && null != count.get(0))? Integer.parseInt(count.get(0).get("row_count").toString()):0;
	}

}
