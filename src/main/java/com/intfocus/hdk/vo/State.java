package com.intfocus.hdk.vo;


public class State {
    private Integer id;

    private String staId;

    private String staName;
    
    private String parentId;

    private String ownerTable;

    private String createdAt;

    private String updatedAt;
    private String isDefault;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStaId() {
        return staId;
    }

    public void setStaId(String staId) {
        this.staId = staId == null ? null : staId.trim();
    }

    public String getStaName() {
        return staName;
    }

    public void setStaName(String staName) {
        this.staName = staName == null ? null : staName.trim();
    }

    public String getOwnerTable() {
        return ownerTable;
    }

    public void setOwnerTable(String ownerTable) {
        this.ownerTable = ownerTable == null ? null : ownerTable.trim();
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

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}