package com.intfocus.hdk.vo;
/*
 * @
 * */
public class ColProperty {
	String excelCol ;
	String col ;
	String defaults ;
	String ctype ;
	String foreign ;
	int      excelIndex ;
	String  primary ;
	public String getExcelCol() {
		return excelCol;
	}
	public void setExcelCol(String excelCol) {
		this.excelCol = excelCol;
	}
	public String getCol() {
		return col;
	}
	public void setCol(String col) {
		this.col = col;
	}
	public String getDefaults() {
		return defaults;
	}
	public void setDefaults(String defaults) {
		this.defaults = defaults;
	}
	public String getCtype() {
		return ctype;
	}
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	public String getForeign() {
		return foreign;
	}
	public void setForeign(String foreign) {
		this.foreign = foreign;
	}
	public int getExcelIndex() {
		return excelIndex;
	}
	public void setExcelIndex(int excelIndex) {
		this.excelIndex = excelIndex;
	}
	public String getPrimary() {
		return primary;
	}
	public void setPrimary(String primary) {
		this.primary = primary;
	}
}
