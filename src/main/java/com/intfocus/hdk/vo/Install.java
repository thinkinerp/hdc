package com.intfocus.hdk.vo;

import java.util.Arrays;
import java.util.Date;

import com.alibaba.fastjson.JSONArray;
import com.intfocus.hdk.util.ComUtil;

public class Install {
    private Integer id;

    private String installId;
    private String attachmentUrl;
    
    private String installStation;

    private String proId;

    private String shopId;

    private String cashId;

    private String printerId;

    private String eqId;

    private String installData;

    private String installTime;

    private String installUser;

    private String installNetwork;

    private String installRemote;

    private String installEndtime;

    private String createdAt;

    private String updatedAt;

    private String installRemarks;

    public  void modifyAtachement(String files,String picUrls,String path){
    	
    	if(null != picUrls){
    		
    		JSONArray filesArray = JSONArray.parseArray(files); 
    		String[] filesArray2 = null ;
    		String[] temp = null;
    		if(null !=  this.attachmentUrl && !"".equalsIgnoreCase(this.attachmentUrl)){
	    		filesArray2 = this.attachmentUrl.split(",");
	    		temp = filesArray2.clone();
	    		for (int i = 0; i < filesArray2.length; i++) {
	    			if(!filesArray.contains( filesArray2[i])){
	    				temp = ComUtil.remove(filesArray2[i], temp);
	    			}
				}
    		}
    		if(null != temp && 0 < temp.length ){
    			this.attachmentUrl =   org.apache.commons.lang.StringUtils.join(temp,",") + ("".equalsIgnoreCase(picUrls)?"" :"," + picUrls);
    		}else{
    			this.attachmentUrl =picUrls;
    		}

    	}
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInstallId() {
        return installId;
    }

    public void setInstallId(String installId) {
        this.installId = installId == null ? null : installId.trim();
    }

    public String getInstallStation() {
        return installStation;
    }

    public void setInstallStation(String installStation) {
        this.installStation = installStation == null ? null : installStation.trim();
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

    public String getCashId() {
        return cashId;
    }

    public void setCashId(String cashId) {
        this.cashId = cashId == null ? null : cashId.trim();
    }

    public String getPrinterId() {
        return printerId;
    }

    public void setPrinterId(String printerId) {
        this.printerId = printerId == null ? null : printerId.trim();
    }

    public String getEqId() {
        return eqId;
    }

    public void setEqId(String eqId) {
        this.eqId = eqId == null ? null : eqId.trim();
    }

    public String getInstallData() {
        return installData;
    }

    public void setInstallData(String installData) {
        this.installData = installData == null ? null : installData.trim();
    }

    public String getInstallTime() {
        return ComUtil.dateFormat(installTime, "yyyy-MM-dd");
    }

    public void setInstallTime(String installTime) {
        this.installTime = installTime;
    }

    public String getInstallUser() {
        return installUser;
    }

    public void setInstallUser(String installUser) {
        this.installUser = installUser == null ? null : installUser.trim();
    }

    public String getInstallNetwork() {
        return installNetwork;
    }

    public void setInstallNetwork(String installNetwork) {
        this.installNetwork = installNetwork == null ? null : installNetwork.trim();
    }

    public String getInstallRemote() {
        return installRemote;
    }

    public void setInstallRemote(String installRemote) {
        this.installRemote = installRemote == null ? null : installRemote.trim();
    }

    public String getInstallEndtime() {
        return installEndtime;
    }

    public void setInstallEndtime(String installEndtime) {
        this.installEndtime = installEndtime;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getInstallRemarks() {
        return installRemarks;
    }

    public void setInstallRemarks(String installRemarks) {
        this.installRemarks = installRemarks == null ? null : installRemarks.trim();
    }

	public String getAttachmentUrl() {
		return attachmentUrl;
	}

	public void setAttachment_url(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}
}