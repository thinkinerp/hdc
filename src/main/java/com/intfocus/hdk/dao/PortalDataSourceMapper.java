package com.intfocus.hdk.dao;

import com.intfocus.hdk.vo.PortalDataSource;
import org.apache.ibatis.annotations.Param;

/**
 * jdbc数据源
 * Created by 张海 on 2017/05/12
 */
public interface PortalDataSourceMapper extends BaseMapper<PortalDataSource> {

    /**
     * 根据编码查询
     *
     * @param code
     * @return
     */
    PortalDataSource queryObjectByCode(@Param("code") String code);

    void deleteBatchByCodes( String[] code);
}
