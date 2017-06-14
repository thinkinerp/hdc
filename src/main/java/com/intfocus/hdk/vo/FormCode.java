package com.intfocus.hdk.vo;

import java.util.Date;

public class FormCode {
    private Integer id;

    private String formType;

    private Integer codeMax;

    private String datestime;
 
    private Date updatedAt;

    private Date createdAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType == null ? null : formType.trim();
    }

    public Integer getCodeMax() {
        return codeMax;
    }

    public void setCodeMax(Integer codeMax) {
        this.codeMax = codeMax;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

	public String getDatestime() {
		return datestime;
	}

	public void setDatestime(String datestime) {
		this.datestime = datestime;
	}
}