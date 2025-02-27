package com.dt.common.interceptor.pagination.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.Alias;  

/** 
 * @author reacher 
 * 
 */
/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
@Alias("PageBounds")
public class PageBounds extends RowBounds {  
	public static final int DEF_COUNT = 20;//默认页每页显示条数
      
    private int pageSize=20;//每页记录数 
  
    private int pageNo=1;//当前页码  
  
    private int totalCount=0;//数据总条数  
      
    private int totalPage;//总页数  
    
    private boolean firstPage;//是否是第一页
    
    private boolean lastPage;//是否是第一页
    
    private int nextPage;//上一页
    
    private int prePage;//下一页
    
    private Map<String,String> paramMap;//查询参数
    
    private String actionUrl;//form表单中的action的值
    
    private String button;//分页按钮
    
    private List<?> list;//记录，分页内容
    
    private boolean isLimit = true;//是否分页
    
    
	/**
	 * 检查页码 checkPageNo
	 * 
	 * @param pageNo
	 * @return if pageNo==null or pageNo<1 then return 1 else return pageNo
	 */
	public static int cpn(Integer pageNo) {
		return (pageNo == null || pageNo < 1) ? 1 : pageNo;
	}
	
	/**
	 * 调整页码，使不超过最大页数
	 */
	public void adjustPageNo() {
		if (pageNo == 1) {
			return;
		}
		int tp = getTotalPage();
		if (pageNo > tp) {
			pageNo = tp;
		}
	}
  
    public PageBounds() {  

    }  
  
    /**
     * 构造器
     * @param pageSize 每页记录数
     */
    public PageBounds(int pageSize) {  
		setPageSize(pageSize);
    }  
    
    /**
     * 构造器
     * @param pageNo 当前页码
     * @param pageSize 每页记录数
     */
    public PageBounds(int pageNo,int pageSize) {  
    	setPageNo(pageNo);
		setPageSize(pageSize);
    } 
    
    /**
     * 构造器
     * @param pageNo 当前页码
     * @param pageSize 每页记录数
     * @param isLimit 是否分页
     */
    public PageBounds(int pageNo,int pageSize,boolean isLimit) {  
    	setPageNo(pageNo);
		setPageSize(pageSize);
		setIsLimit(isLimit);
    }
    
	/**
	 * 每页记录数
	 * 
	 * @return
	 */
    public int getPageSize() {  
        return pageSize;  
    }  
	/**
	 * if pageSize< 1 then pageSize=DEF_COUNT
	 * 
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		if (pageSize < 1) {
			this.pageSize = DEF_COUNT;
		} else {
			this.pageSize = pageSize;
		}
	}  
	/**
	 * 获得页码
	 */
    public int getPageNo() {  
        return pageNo;  
    }  
	/**
	 * if pageNo < 1 then pageNo=1
	 * 
	 * @param pageNo
	 */
	public void setPageNo(int pageNo) {
		if (pageNo < 1) {
			this.pageNo = 1;
		} else {
			this.pageNo = pageNo;
		}
	}
  
	/**
	 * 总共几条数据
	 */
    public int getTotalCount() {  
        return totalCount;  
    }  
	/**
	 * if totalCount<0 then totalCount=0
	 * 
	 * @param totalCount
	 */
    public void setTotalCount(int totalCount) {  
		if (totalCount < 0) {
			this.totalCount = 0;
		} else {
			this.totalCount = totalCount;
		}
    }  
	/**
	 * 总共几页
	 */
    public int getTotalPage() {  
		int totalPage = totalCount / pageSize;
		if (totalPage == 0 || totalCount % pageSize != 0) {
			totalPage++;
		}
		return totalPage;
    }  
    
	/**
	 * 是否第一页
	 */
	public boolean isFirstPage() {
		return pageNo <= 1;
	}

	/**
	 * 是否最后一页
	 */
	public boolean isLastPage() {
		return pageNo >= getTotalPage();
	}

	/**
	 * 下一页页码
	 */
	public int getNextPage() {
		if (isLastPage()) {
			return pageNo;
		} else {
			return pageNo + 1;
		}
	}

	/**
	 * 上一页页码
	 */
	public int getPrePage() {
		if (isFirstPage()) {
			return pageNo;
		} else {
			return pageNo - 1;
		}
	}
	
/////////////////////////////////////////////////////////////////////////////////////
	
	
  

    
	/**
	 * 生成查询参数的隐藏域
	 * @param map
	 * @return
	 */
	public static String getUrlHiddenInput(Map map){
		String urlparam = "";
		if(map!=null){
			Iterator it = map.keySet().iterator();
			while(it.hasNext()){
				String key = (String)it.next();
				String value = map.get(key).toString();
				urlparam += "<input type='hidden' name='"+key+"' value='"+value+"'/>";
			}
		}
		return urlparam;
	}

	public String getUrlHiddenInput(){
		Map<String,String> map = getParamMap();
		if(map==null){
			map = new HashMap<String, String>();
		}
		if(map.get("pageNo")==null){
			map.put("pageNo", String.valueOf(getPageNo()));
		}
		String urlparam = getUrlHiddenInput(map);
		return urlparam;
	}

	/**
	 * 取得查询参数
	 * @return
	 */
	public Map<String, String> getParamMap() {
		return paramMap;
	}

	/**
	 * 设置查询参数
	 * @param paramMap
	 */
	public void setParamMap(Map<String, String> paramMap) {
		this.paramMap = paramMap;
	}

	
	public String getActionUrl() {
		return actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	/**
	 * 取得分页按钮
	 * @return
	 */
	public String getButton() {
		String disabled = " disabled=\"disabled\" ";
		String firstDisabled = "";
		String preDisabled = "";
		String nextDisabled = "";
		String lastDisabled = "";
		
		if(isFirstPage()){
			firstDisabled = disabled;
			preDisabled = disabled;
		}
		
		if(isLastPage()){
			nextDisabled = disabled;
			lastDisabled = disabled;
		}
		
		String html = "";
		html += "<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td align=\"center\" class=\"pn-sp\">";
		html += "共 "+getTotalCount()+" 条&nbsp;";
		html += "每页"+getPageSize()+"条&nbsp;";
		html += "<input class=\"page-button\" type=\"button\" value=\"首 页\" onclick=\"_gotoPage('1');\" "+firstDisabled+"/>";
		html += "<input class=\"page-button\" type=\"button\" value=\"上一页\" onclick=\"_gotoPage('"+getPrePage()+"');\" "+preDisabled+"/>";
		html += "<input class=\"page-button\" type=\"button\" value=\"下一页\" onclick=\"_gotoPage('"+getNextPage()+"');\" "+nextDisabled+"/>";
		html += "<input class=\"page-button\" type=\"button\" value=\"尾 页\" onclick=\"_gotoPage('"+getTotalPage()+"');\" "+lastDisabled+"/>&nbsp;";
		html += "当前  "+getPageNo()+"/"+getTotalPage()+" 页 &nbsp;转到第<input type=\"text\" id=\"_goPs\" style=\"width:50px\" onfocus=\"this.select();\" onkeypress=\"if(event.keyCode==13){$('#_goPage').click();return false;}\"  onkeyup=\"value=value.replace(/[^\\d]/g,'')\"/>页";
		html += "<input class=\"page-go\" id=\"_goPage\" type=\"button\" value=\"转\" onclick=\"_gotoPage($('#_goPs').val());\"/>";
		html += "</td></tr></table>";
		html += "<script type=\"text/javascript\">";
		html += "function _gotoPage(pageNo) {";
		html += "try{";
		html += "var tableForm = getTableForm();";
		html += "$(\"input[name=pageNo]\").val(pageNo);";
		html += "tableForm.action=\""+actionUrl+"\";";
		html += "tableForm.onsubmit=null;";
		html += "tableForm.submit();";
		html += "} catch(e) {";
		html += "alert('_gotoPage(pageNo)方法出错');";
		html += "}";
		html += "}";
		html += "</script>";
		
		return html;
	}


	/**
	 * 取得分页记录
	 * @return
	 */
	public List<?> getList() {
		return list;
	}

	/**
	 * 设置分页
	 * @param list
	 */
	public void setList(List<?> list) {
		this.list = list;
	}

	public boolean getIsLimit() {
		return isLimit;
	}
	public void setIsLimit(boolean isLimit) {
		this.isLimit = isLimit;
	}	
	
	
  
} 
