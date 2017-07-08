package com.intfocus.hdk.dao;

import com.intfocus.hdk.vo.PortalExecuteSql;
import org.apache.ibatis.annotations.Param;

/**
 * 执行的sql语句
 * Created by 张海 on 2017/05/13
 */
public interface PortalExecuteSqlMapper extends BaseMapper<PortalExecuteSql> {

    /**
     * 根据编码查询
     *
     * @param sqlcode sql语句唯一编码
     * @return
     */
    PortalExecuteSql queryObjectBySqlcode(@Param("sqlcode") String sqlcode);

    int deleteBatchBySqlcodes(String[] sqlcods);

}
