package com.xfs.pay.wxpay.entity;

import java.util.Arrays;


public class WXPreInfo {

	private String appid;
	
	private String body;
	
	private String mch_id;
	
	private String nonce_str;
	
	private String notify_url;
	
	private String out_trade_no;
	
	private String spbill_create_ip;
	
	private String total_fee;
	
	private String trade_type;
	
	private String sign;//签名
	
	private String return_code;//返回code
	
	private String return_msg;
	
	private String result_code;
	
	private String prepay_id;
	
	private String openid;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	
	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getPrepay_id() {
		return prepay_id;
	}

	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@Override
	public String toString() {
		String[] tmpArr = {"appid","body","mch_id","nonce_str","notify_url","out_trade_no","spbill_create_ip","total_fee","trade_type","openid"};
		Arrays.sort(tmpArr);
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i<tmpArr.length;i++){
			boolean isHave = false;
			if(tmpArr[i].equals("appid")){
				isHave = true;
				sb.append("appid=").append(this.getAppid());
			}
			if(tmpArr[i].equals("body")){
				isHave = true;
				sb.append("body=").append(this.getBody());
			}
			if(tmpArr[i].equals("mch_id")){
				isHave = true;
				sb.append("mch_id=").append(this.getMch_id());
			}
			if(tmpArr[i].equals("nonce_str")){
				isHave = true;
				sb.append("nonce_str=").append(this.getNonce_str());
			}
			if(tmpArr[i].equals("notify_url")){
				isHave = true;
				sb.append("notify_url=").append(this.getNotify_url());
			}
			if(tmpArr[i].equals("out_trade_no")){
				isHave = true;
				sb.append("out_trade_no=").append(this.getOut_trade_no());
			}
			if(tmpArr[i].equals("spbill_create_ip")){
				isHave = true;
				sb.append("spbill_create_ip=").append(this.getSpbill_create_ip());
			}
			if(tmpArr[i].equals("total_fee")){
				isHave = true;
				sb.append("total_fee=").append(this.getTotal_fee());
			}
			if(tmpArr[i].equals("trade_type")){
				isHave = true;
				sb.append("trade_type=").append(this.getTrade_type());
			}
			if(tmpArr[i].equals("openid") && null != this.getOpenid() && !"".equals(this.getOpenid())){
				isHave = true;
				sb.append("openid=").append(this.getOpenid());
			}
			if(isHave){
				sb.append("&");
			}
		}
		return sb.append("key=").toString();
	}
	
	
}
