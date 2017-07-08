package com.intfocus.hdk.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 返回数据
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    /**
     * 请求结果信息
     *
     * @param code   响应码
     * @param data   响应数据
     * @param msg 相信信息说明
     */
    private R(int code, Object data, String msg) {
        setCode(code);
        setMsg(msg);
        setData(data);
    }
    private R(int code, Object data, String msg,Integer start, Integer draw, Integer recordsTotal , Integer recordsFiltered) {
    	setCode(code);
    	setMsg(msg);
    	setData(data);
    	setStart(start);
    	setDraw(draw);
    	setRecordsTotal(recordsTotal);
    	setRecordsFiltered(recordsFiltered);
    }

    /**
     * 请求失败
     *
     * @return
     */
    public static R error() {
        return newInstance(ConstantsUtil.CommonCode.ERROR_CODE, "", ConstantsUtil.CommonMessage.ERROR_MESSAGE);
    }

    /**
     * 请求失败
     *
     * @param msg 自定义异常信息
     * @return
     */
    public static R error(String msg) {
        return newInstance(ConstantsUtil.CommonCode.ERROR_CODE, "", msg);
    }

    /**
     * 请求失败
     *
     * @param code   自定义异常编码
     * @param msg 自定义异常信息
     * @return
     */
    public static R error(int code, String msg) {
        return newInstance(code, "", msg);
    }

    /**
     * 请求成功
     *
     * @param data 响应数据
     * @return
     */
    public static R success(Object data) {
        return newInstance(ConstantsUtil.CommonCode.SUCCESS_CODE, data, ConstantsUtil.CommonMessage.SUCCESS_MESSAGE);
    }

    /**
     * 请求成功
     * @param recordsTotal 
     * @param draw 
     * @param start 
     * @param list 
     *
     * @return
     */
    public static R success(List<Map<String, Object>> list, Integer start, Integer draw, Integer recordsTotal , Integer recordsFiltered) {
        return newInstance(ConstantsUtil.CommonCode.SUCCESS_CODE, list, ConstantsUtil.CommonMessage.SUCCESS_MESSAGE,start,draw,recordsTotal,recordsFiltered);
    }

    /**
     * 自定义结果信息
     *
     * @param key   属性名称
     * @param value 结果数据
     * @return
     */
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    /**
     * 设置响应结果状态码
     *
     * @param code 请求结果状态码
     * @return
     */
    public R setCode(int code) {
        put("code", code);
        return this;
    }

    /**
     * 设置响应数据
     *
     * @param data 结果数据
     * @return
     */
    public R setData(Object data) {
        put("data", data);
        return this;
    }
    /**
     * 设置响应数据
     *
     * @param start 开始位置
     * @return
     */
    public R setStart(Integer start) {
    	put("start", start);
    	return this;
    }
    /**
     * 设置响应数据
     *
     * @param draw 当前页
     * @return
     */
    public R setDraw(Integer draw) {
    	put("draw", draw);
    	return this;
    }
    /**
     * 设置响应数据
     *
     * @param Integer recordsTotal  总记录数
     * @return
     */
    public R setRecordsTotal(Integer recordsTotal) {
    	put("recordsTotal", recordsTotal);
    	return this;
    }
    /**
     * 设置响应数据
     *
     * @param Integer recordsFiltered 总记录数
     * @return
     */
    public R setRecordsFiltered(Integer recordsFiltered) {
    	put("recordsFiltered", recordsFiltered);
    	return this;
    }

    /**
     * 设置响应结果信息
     *
     * @param msg 请求结果信息说明
     * @return
     */
    public R setMsg(String msg) {
        if (null == msg) {
            put("msg", "");
        } else {
            put("msg", msg);
        }
        return this;
    }

    public static R newInstance(int code, Object data, String msg) {
        return new R(code, data, msg);
    }
    public static R newInstance(int code, Object data, String msg,Integer start, Integer draw, Integer recordsTotal , Integer recordsFiltered) {
    	return new R(code, data, msg,start , draw , recordsTotal , recordsFiltered);
    }

}
