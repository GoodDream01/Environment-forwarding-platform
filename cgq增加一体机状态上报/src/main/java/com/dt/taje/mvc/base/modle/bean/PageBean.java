package com.dt.taje.mvc.base.modle.bean;

import java.util.List;

public class PageBean {
	private Integer currentPage = 1;//当前页数
	private Integer pageSize = 15;//每页记录数
	private Integer pageCount = 1;//总页数;
	private Integer recordCount = 0;//总记录数
	private String sqlStr = "";//查询的SQL语句
	private List dataList = null;//查询得到的数据集
	private boolean isLimit = true;//是否分页
	private String dwLx="1";
	
	public Integer getCurrentPage() {
		if(currentPage<1){
			return 0;
		}else if(currentPage>this.getPageCount()){
			return this.getPageCount();
		}else{
			return currentPage;
		}
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageCount() {
		if(this.getPageSize()>0){
			return ((this.getRecordCount()+this.getPageSize()-1)/this.getPageSize());
		}else{
			return 1;
		}
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public Integer getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(Integer recordCount) {
		this.recordCount = recordCount;
	}
	public String getSqlStr() {
		return sqlStr;
	}
	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr;
	}
	public List getDataList() {
		return dataList;
	}
	public void setDataList(List dataList) {
		this.dataList = dataList;
	}
	public boolean getIsLimit() {
		return isLimit;
	}
	public void setIsLimit(boolean isLimit) {
		this.isLimit = isLimit;
	}	
	
	public String getDwLx() {
		return dwLx;
	}
	public void setDwLx(String dwLx) {
		this.dwLx = dwLx;
	}
	public String getTotalSql(){
		return "select count(*) num from ("+this.getSqlStr()+") a";
	}
	public String getLimitSql(){
		//return "select * from (select row_.*,rownum as my_rownum from ("+this.getSqlStr()+") row_ where rownum <="+(this.getCurrentPage()+1)*this.getPageSize()+") where my_rownum>"+(this.getCurrentPage())*this.getPageSize()+"";
    	long offset = this.getPageSize() * (this.getCurrentPage()+1 - 1) ;
    	long limit = this.getPageSize();
        StringBuffer stringBuffer = new StringBuffer(this.getSqlStr().replaceAll(";", ""));
        stringBuffer.append(" LIMIT ").append(offset).append(", ").append(limit);  
        return stringBuffer.toString();  
	}
}
