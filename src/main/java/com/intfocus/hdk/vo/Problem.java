package com.intfocus.hdk.vo;

import java.util.Arrays;

import com.alibaba.fastjson.JSONArray;
import com.intfocus.hdk.util.ComUtil;

public class Problem {
    private Integer id;

    private String problemId;

    private String state;

    private String proId;
    
    private String proName;

    private String shopName;
    
    private String shopPostion;
    
    private String shopId;

    private String eqId;
    
    private String eqType;
    
    private String eqStyle;

    private String mesId;

    private String problemType;

    private String problemObject;

    private String problemDepartment;

    private String problemUser;

    private String problemHappen;

    private String problemPut;

    private String problemEstimate;

    private String problemResloveTime;

    private String problemResolveUser;

    private String problemDetails;

    private String problemEnclosure;

    private String problemTable;

    private String createdAt;

    private String updatedAt;

    private String problemPlan;
    
    private Integer Count;
    
    

    public  void modifyAtachement(String files ,String picUrls){
    	
    	if(null != picUrls){
    		JSONArray filesArray = JSONArray.parseArray(files); 
    		String[] filesArray2 = null ;
    		if(null !=  this.problemEnclosure  && !"".equalsIgnoreCase(this.problemEnclosure)){
	    		filesArray2 = this.problemEnclosure.split(",");
	    		
	    		for (int i = 0; i < filesArray2.length; i++) {
	    			if(!filesArray.contains(filesArray2[i])){
	    				filesArray2[i] = filesArray2[filesArray2.length -1];
	    				filesArray2 = Arrays.copyOf(filesArray2, filesArray2.length);
	    			}
				}
    		}
    		if(null != filesArray2 && 0 < filesArray2.length ){
    			this.problemEnclosure =   org.apache.commons.lang.StringUtils.join(filesArray2,",") +"," + picUrls;
    		}else{
    			this.problemEnclosure =picUrls;
    		}
    	}
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId == null ? null : problemId.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId == null ? null : proId.trim();
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }

    public String getEqId() {
        return eqId;
    }

    public void setEqId(String eqId) {
        this.eqId = eqId == null ? null : eqId.trim();
    }

    public String getMesId() {
        return mesId;
    }

    public void setMesId(String mesId) {
        this.mesId = mesId == null ? null : mesId.trim();
    }

    public String getProblemType() {
        return problemType;
    }

    public void setProblemType(String problemType) {
        this.problemType = problemType == null ? null : problemType.trim();
    }

    public String getProblemObject() {
        return problemObject;
    }

    public void setProblemObject(String problemObject) {
        this.problemObject = problemObject == null ? null : problemObject.trim();
    }

    public String getProblemDepartment() {
        return problemDepartment;
    }

    public void setProblemDepartment(String problemDepartment) {
        this.problemDepartment = problemDepartment == null ? null : problemDepartment.trim();
    }

    public String getProblemUser() {
        return problemUser;
    }

    public void setProblemUser(String problemUser) {
        this.problemUser = problemUser == null ? null : problemUser.trim();
    }

    public String getProblemHappen() {
        return ComUtil.dateFormat(problemHappen, "yyyy-MM-dd");
    }

    public void setProblemHappen(String problemHappen) {
        this.problemHappen = problemHappen;
    }

    public String getProblemPut() {
        return ComUtil.dateFormat(problemPut, "yyyy-MM-dd");
    }

    public void setProblemPut(String problemPut) {
        this.problemPut = problemPut;
    }

    public String getProblemEstimate() {
        return ComUtil.dateFormat(problemEstimate, "yyyy-MM-dd");
    }

    public void setProblemEstimate(String problemEstimate) {
        this.problemEstimate = problemEstimate;
    }

    public String getProblemResloveTime() {
        return ComUtil.dateFormat(problemResloveTime, "yyyy-MM-dd");
    }

    public void setProblemResloveTime(String problemResloveTime) {
        this.problemResloveTime = problemResloveTime;
    }

    public String getProblemResolveUser() {
        return problemResolveUser;
    }

    public void setProblemResolveUser(String problemResolveUser) {
        this.problemResolveUser = problemResolveUser == null ? null : problemResolveUser.trim();
    }

    public String getProblemDetails() {
        return problemDetails;
    }

    public void setProblemDetails(String problemDetails) {
        this.problemDetails = problemDetails == null ? null : problemDetails.trim();
    }

    public String getProblemEnclosure() {
        return problemEnclosure;
    }

    public void setProblemEnclosure(String problemEnclosure) {
        this.problemEnclosure = problemEnclosure == null ? null : problemEnclosure.trim();
    }

    public String getProblemTable() {
        return problemTable;
    }

    public void setProblemTable(String problemTable) {
        this.problemTable = problemTable == null ? null : problemTable.trim();
    }

    public String getCreatedAt() {
        return ComUtil.dateFormat(createdAt, "yyyy-MM-dd");
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return ComUtil.dateFormat(updatedAt);
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getProblemPlan() {
        return problemPlan;
    }

    public void setProblemPlan(String problemPlan) {
        this.problemPlan = problemPlan == null ? null : problemPlan.trim();
    }

	public Integer getCount() {
		return Count;
	}

	public void setCount(Integer count) {
		Count = count;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopPostion() {
		return shopPostion;
	}

	public void setShopPostion(String shopPostion) {
		this.shopPostion = shopPostion;
	}

	public String getEqType() {
		return eqType;
	}

	public void setEqType(String eqType) {
		this.eqType = eqType;
	}

	public String getEqStyle() {
		return eqStyle;
	}

	public void setEqStyle(String eqStyle) {
		this.eqStyle = eqStyle;
	}
}