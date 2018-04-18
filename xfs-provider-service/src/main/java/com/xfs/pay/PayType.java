package com.xfs.pay;
/**
 * 支付方式
 * @author lifq
 *
 * 2016年5月14日  下午2:37:16
 */
public enum PayType {
	/**
	 * 阿里支付
	 */
	ALI_PC_PAY("ALIPAY","PC"),
	ALI_WAP_PAY("ALIPAY","WAP"),
	ALI_APP_PAY("ALIPAY","APP"),

	/**
	 * 畅捷支付
	 */
	CHAN_PC_PAY("CHANPAY","PC"),
	CHAN_WAP_PAY("CHANPAY","WAP"),
	CHAN_APP_PAY("CHANPAY","APP"),

	/**
	 * 微信支付
	 */
	WX_PC_PAY("WXPAY","PC"),
	WX_WAP_PAY("WXPAY","WAP"),
	WX_APP_PAY("WXPAY","APP"),

	/**
	 * 薪福社
	 */
	XFS_PAY("XFS","ALL");

	private String payType;//支付类型
	private String visitType;//访问类型

	private PayType(String payType,String visitType){
		this.payType = payType;
		this.visitType = visitType;
	}

	public String getVisitType() {
		return visitType;
	}

	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public static PayType queryPayType(String payType,String visitType){
		for (PayType type : PayType.values()){
			if(type.getPayType().equals(payType) && type.getVisitType().equals(visitType))
				return type;
		}
		return ALI_PC_PAY;
	}
}
