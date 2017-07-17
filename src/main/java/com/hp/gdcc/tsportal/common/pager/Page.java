package com.hp.gdcc.tsportal.common.pager;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 对分页的基本数据进行一个简单的封装
 */
public class Page<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -885320565369046744L;
	
	private int pageNum = 1;// 页码，默认是第一页
	private int numPerPage = 10;// 每页显示的记录数，默认是10
	private int maxPageSize = 10;
	private int minPageSize = 10;
	private int spaceSize = 1;
	private int totalRecord;// 总记录数
	private int totalPage;// 总页数
	private int index = 0;
	private static String orderRegex = "^\\s*([A-Za-z_$\\d\\.]+\\(\\s*)?([A-Za-z_$]+[A-Za-z_$\\d]*\\.)?([A-Za-z_$]+[A-Za-z_$\\d]*)(\\s*\\))?((\\s+(?i)asc)|(\\s+(?i)desc))?\\s*$";
	private String order;//排序规则
	private List<T> results;// 对应的当前页记录
	private T paramsObject;
	private Map<String, Object> paramsMap = new HashMap<String, Object>();// 其他的参数我们把它分装成一个Map对象
	private boolean isGetTotal = true; // 是否需要查询总数

	public boolean isGetTotal() {
		return isGetTotal;
	}

	public void setGetTotal(boolean isGetTotal) {
		this.isGetTotal = isGetTotal;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = 1 > pageNum ? 1 : pageNum;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}
	
	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		if(order == null){
			this.order = order;
		}
		//排序必须满足命名规则
		if(order.trim().matches(orderRegex)){
			this.order = order;
		}
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		if (numPerPage > 1000) {
			numPerPage = 20;
		}
		this.numPerPage = numPerPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		// 在设置总页数的时候计算出对应的总页数，在下面的三目运算中加法拥有更高的优先级，所以最后可以不加括号。
		int totalPage = totalRecord % numPerPage == 0 ? totalRecord / numPerPage : totalRecord / numPerPage + 1;
		this.setTotalPage(totalPage);
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	public T getParamsObject() {
		return paramsObject;
	}

	public void setParamsObject(T paramsObject) {
		this.paramsObject = paramsObject;
	}

	public Map<String, Object> getParamsMap() {
		return paramsMap;
	}

	public void setParamsMap(Map<String, Object> paramsMap) {
		this.paramsMap = paramsMap;
	}

	public void initMaxMinSpacePageSize(int maxPageSize, int minPageSize, int spaceSize) {
		this.maxPageSize = maxPageSize;
		this.minPageSize = minPageSize;
		this.spaceSize = spaceSize;
	}

	/**
	 * 初始化后调用该方法（如果totalRecord不为0，输入页码大于实际页码，默认1再查询）
	 */
	public String getPageHTML() {
		return "<script page type=\"text/javascript\">pageLoad({'index':" + index + ",'countAll':" + totalRecord + ",'noncePage':" + pageNum + ",'pageSize':" + numPerPage + ",'maxPageSize':" + maxPageSize + ",'minPageSize':" + minPageSize + ",'spaceSize':" + spaceSize + ",'pageCount':" + totalPage + "})</script>";
	}

	public String getPageJSON() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("data", JSONArray.fromObject(results).toString());
		map.put("html", getPageHTML());
		return JSONObject.fromObject(map).toString();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Page [pageNum=").append(pageNum).append(", numPerPage=").append(numPerPage).append(", results=").append(results).append(", totalPage=").append(totalPage).append(", totalRecord=").append(totalRecord).append("]");
		return builder.toString();
	}
	
	public String toCacheableKey() {
		return "Page [pageNum=" + pageNum + ", numPerPage=" + numPerPage + ", order=" + order + ", paramsObject="
				+ paramsObject + ", paramsMap=" + paramsMap + "]";
	}

}
