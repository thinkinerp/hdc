package com.intfocus.hdk.service;


import com.intfocus.hdk.vo.ReportMeasureRelation;

import java.util.List;
import java.util.Map;

/**
 * @author zhanghai
 * @email walk_hai@163.com
 * @date 2017-05-22 16:17:07
 */
public interface ReportMeasureRelationService {

    ReportMeasureRelation queryObject(Long id);

    List<ReportMeasureRelation> queryList(Map<String, Object> map);

    /**
     * 根据报表编码获取所有列
     *
     * @param reportcode
     * @return
     */
    List<ReportMeasureRelation> queryListByReportCode(String reportcode);

    int queryTotal(Map<String, Object> map);

    void save(ReportMeasureRelation reportMeasureRelation);

    void update(ReportMeasureRelation reportMeasureRelation);

    void delete(Long id);

    void deleteBatch(Long[] ids);

}
