package com.intfocus.hdk.dao;

import com.intfocus.hdk.vo.ReportMeasureRelation;

import java.util.List;

/**
 * @author zhanghai
 * @email walk_hai@163.com
 * @date 2017-05-22 16:17:07
 */
public interface ReportMeasureRelationMapper extends BaseMapper<ReportMeasureRelation> {

    /**
     * 根据报表编码获取所有列
     * @param reportcode
     * @return
     */
    List<ReportMeasureRelation> queryListByReportCode(String reportcode);

}
