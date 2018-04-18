package com.xfs.base.model;

import java.io.Serializable;
/**
 * BsCiScheme
 * @author:guopeng
 * @date:2016/09/03 11:44:03	
 */
public class BsCompanyInfoDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	private String detailType;

	public String getDetailType() {
		return detailType;
	}

	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}
	
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	private int order;

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
	private String json;

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	
	public static final String DetailType_Execution = "EXECUTION";
	public static final String DetailType_Auction = "AUCTION";
	public static final String DetailType_Abnormal = "ABNORMAL";
	public static final String DetailType_OverDueTax = "OVERDUETAX";
}
