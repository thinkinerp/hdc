package com.intfocus.hdk.vo;

import com.intfocus.hdk.util.ComUtil;


public class Project {
    private Integer id;

    private String proId;
    
    private String proCustomerRelationship;
    
    private String proName;

    private String proEdition;

    private String proStation;

    private String proManagerPro;

    private String proManagerAcc;

    private String proEngineer;

    private String proCoordination;

    private String proCoordinationPhone;

    private String proUpdataTime;

    private Integer proTodal;
    
    private Integer leftNum;

    private Integer proNeed;

    private Integer proAlready;
    
    private String  proAlreadyPer;
    
    private String proCheckPer;
    
    private Integer proCheck;

    private Integer proNot;

    private String createdAt;

    private String updatedAt;
    
    private Integer isLast;
    
    private String problemObject ;
    
    private String problemType;
    
    private String Count;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId == null ? null : proId.trim();
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName == null ? null : proName.trim();
    }

    public String getProEdition() {
        return proEdition;
    }

    public void setProEdition(String proEdition) {
        this.proEdition = proEdition == null ? null : proEdition.trim();
    }

    public String getProStation() {
        return proStation;
    }

    public void setProStation(String proStation) {
        this.proStation = proStation == null ? null : proStation.trim();
    }

    public String getProManagerPro() {
        return proManagerPro;
    }

    public void setProManagerPro(String proManagerPro) {
        this.proManagerPro = proManagerPro == null ? null : proManagerPro.trim();
    }

    public String getProManagerAcc() {
        return proManagerAcc;
    }

    public void setProManagerAcc(String proManagerAcc) {
        this.proManagerAcc = proManagerAcc == null ? null : proManagerAcc.trim();
    }

    public String getProEngineer() {
        return proEngineer;
    }

    public void setProEngineer(String proEngineer) {
        this.proEngineer = proEngineer == null ? null : proEngineer.trim();
    }

    public String getProCoordination() {
        return proCoordination;
    }

    public void setProCoordination(String proCoordination) {
        this.proCoordination = proCoordination == null ? null : proCoordination.trim();
    }

    public String getProCoordinationPhone() {
        return proCoordinationPhone;
    }

    public void setProCoordinationPhone(String proCoordinationPhone) {
        this.proCoordinationPhone = proCoordinationPhone == null ? null : proCoordinationPhone.trim();
    }

    public String getProUpdataTime() {
        return ComUtil.dateFormat(proUpdataTime);
    }

    public void setProUpdataTime(String proUpdataTime) {
        this.proUpdataTime = proUpdataTime;
    }

    public Integer getProTodal() {
        return proTodal;
    }

    public void setProTodal(Integer proTodal) {
        this.proTodal = proTodal;
    }

    public Integer getProNeed() {
        return proNeed;
    }

    public void setProNeed(Integer proNeed) {
        this.proNeed = proNeed;
    }

    public Integer getProAlready() {
        return proAlready;
    }

    public void setProAlready(Integer proAlready) {
        this.proAlready = proAlready;
    }

    public Integer getProCheck() {
        return proCheck;
    }

    public void setProCheck(Integer proCheck) {
        this.proCheck = proCheck;
    }

    public Integer getProNot() {
        return proNot;
    }

    public void setProNot(Integer proNot) {
        this.proNot = proNot;
    }

    public String getCreatedAt() {
        return createdAt;
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

	public Integer getIsLast() {
		return isLast;
	}

	public void setIsLast(Integer isLast) {
		this.isLast = isLast;
	}

	public String getProblemObject() {
		return problemObject;
	}

	public void setProblemObject(String problemObject) {
		this.problemObject = problemObject;
	}

	public String getProblemType() {
		return problemType;
	}

	public void setProblemType(String problemType) {
		this.problemType = problemType;
	}

	public String getCount() {
		return Count;
	}

	public void setCount(String count) {
		Count = count;
	}

	public String getProAlreadyPer() {
		return proAlreadyPer;
	}

	public void setProAlreadyPer(String proAlreadyPer) {
		this.proAlreadyPer = proAlreadyPer;
	}

	public String getProCheckPer() {
		return proCheckPer;
	}

	public void setProCheckPer(String proCheckPer) {
		this.proCheckPer = proCheckPer;
	}

	public Integer getLeftNum() {
		return leftNum;
	}

	public void setLeftNum(Integer leftNum) {
		this.leftNum = leftNum;
	}

	public String getProCustomerRelationship() {
		return proCustomerRelationship;
	}

	public void setProCustomerRelationship(String proCustomerRelationship) {
		this.proCustomerRelationship = proCustomerRelationship;
	}



}