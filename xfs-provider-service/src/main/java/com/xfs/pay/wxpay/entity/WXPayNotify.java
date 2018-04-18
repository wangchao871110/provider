package com.xfs.pay.wxpay.entity;

import java.util.Arrays;

/**
 * 
* @ClassName: WXPayNotify
* @Description: 微信支付完成 异步通知
* @author konglingchao
* @date 2015-11-20 上午10:03:29
 */
public class WXPayNotify {
	
	private String return_code;//SUCCESS/FAIL 
	private String return_msg;//返回信息
	private String appid;//公共帐号ID
	private String mch_id;//商户号
	private String device_info;//设备号
	private String nonce_str;//随机串
	private String sign;//签名
	private String result_code;//业务结果
	private String err_code;//错误代码
	private String err_code_des;//错误代码描述
	private String openid;//用户标识
	private String is_subscribe;//是否关注公共帐号
	private String trade_type;//交易类型
	private String bank_type;//付款银行
	private String total_fee;//总金额
	private String fee_type;//货币种类
	private String cash_fee;//现金支付金额
	private String cash_fee_type;//现金支付货币类型
	private String coupon_fee;//代金券或立减优惠金额
	private String coupon_count;//代金券或立减优惠使用数量
	private String coupon_id_$n;//代金券或立减优惠ID
	private String coupon_fee_$n;//单个代金券或立减优惠支付金额
	private String transaction_id;//微信支付订单号
	private String out_trade_no;//商户订单号
	private String attach;//商家数据包
	private String time_end;//支付完成时间
	
	@Override
	public String toString() {
		String[] tmpArr = {"return_code","return_msg","appid","mch_id","device_info","nonce_str","result_code","err_code",
				"err_code_des","openid","is_subscribe","trade_type","bank_type","total_fee",
				"fee_type","cash_fee","cash_fee_type","coupon_fee","coupon_count","coupon_id_$n","coupon_fee_$n",
				"transaction_id","out_trade_no","attach","time_end"};
		Arrays.sort(tmpArr);
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i<tmpArr.length;i++){
			boolean isValue = false;
			if(tmpArr[i].equals("return_code") && null != this.getReturn_code() && !"".equals(this.getReturn_code())){
				sb.append("return_code=").append(this.getReturn_code());
				isValue = true;
			}
			if(tmpArr[i].equals("return_msg") && null != this.getReturn_msg() && !"".equals(this.getReturn_msg())){
				sb.append("return_msg=").append(this.getReturn_msg());
				isValue = true;
			}
			if(tmpArr[i].equals("appid") && null != this.getAppid() && !"".equals(this.getAppid())){
				sb.append("appid=").append(this.getAppid());
				isValue = true;
			}
			if(tmpArr[i].equals("mch_id") && null != this.getMch_id() && !"".equals(this.getMch_id())){
				sb.append("mch_id=").append(this.getMch_id());
				isValue = true;
			}
			if(tmpArr[i].equals("device_info") && null != this.getDevice_info() && !"".equals(this.getDevice_info())){
				sb.append("device_info=").append(this.getDevice_info());
				isValue = true;
			}
			if(tmpArr[i].equals("nonce_str") && null != this.getNonce_str() && !"".equals(this.getNonce_str())){
				sb.append("nonce_str=").append(this.getNonce_str());
				isValue = true;
			}
			if(tmpArr[i].equals("result_code") && null != this.getResult_code() && !"".equals(this.getResult_code())){
				sb.append("result_code=").append(this.getResult_code());
				isValue = true;
			}
			if(tmpArr[i].equals("err_code_des") && null != this.getErr_code_des() && !"".equals(this.getErr_code_des())){
				sb.append("err_code_des=").append(this.getErr_code_des());
				isValue = true;
			}
			if(tmpArr[i].equals("err_code") && null != this.getErr_code() && !"".equals(this.getErr_code())){
				sb.append("err_code=").append(this.getErr_code());
				isValue = true;
			}
			if(tmpArr[i].equals("openid") && null != this.getOpenid() && !"".equals(this.getOpenid())){
				sb.append("openid=").append(this.getOpenid());
				isValue = true;
			}
			if(tmpArr[i].equals("is_subscribe") && null != this.getIs_subscribe() && !"".equals(this.getIs_subscribe())){
				sb.append("is_subscribe=").append(this.getIs_subscribe());
				isValue = true;
			}
			if(tmpArr[i].equals("trade_type") && null != this.getTrade_type() && !"".equals(this.getTrade_type())){
				sb.append("trade_type=").append(this.getTrade_type());
				isValue = true;
			}
			if(tmpArr[i].equals("bank_type") && null != this.getBank_type() && !"".equals(this.getBank_type())){
				sb.append("bank_type=").append(this.getBank_type());
				isValue = true;
			}
			if(tmpArr[i].equals("total_fee") && null != this.getTotal_fee() && !"".equals(this.getTotal_fee())){
				sb.append("total_fee=").append(this.getTotal_fee());
				isValue = true;
			}
			if(tmpArr[i].equals("fee_type") && null != this.getFee_type() && !"".equals(this.getFee_type())){
				sb.append("fee_type=").append(this.getFee_type());
				isValue = true;
			}
			if(tmpArr[i].equals("cash_fee") && null != this.getCash_fee() && !"".equals(this.getCash_fee())){
				sb.append("cash_fee=").append(this.getCash_fee());
				isValue = true;
			}
			if(tmpArr[i].equals("cash_fee_type") && null != this.getCash_fee_type() && !"".equals(this.getCash_fee_type())){
				sb.append("cash_fee_type=").append(this.getCash_fee_type());
				isValue = true;
			}
			if(tmpArr[i].equals("coupon_fee") && null != this.getCoupon_fee() && !"".equals(this.getCoupon_fee())){
				sb.append("coupon_fee=").append(this.getCoupon_fee());
				isValue = true;
			}
			if(tmpArr[i].equals("coupon_count") && null != this.getCoupon_count() && !"".equals(this.getCoupon_count())){
				sb.append("coupon_count=").append(this.getCoupon_count());
				isValue = true;
			}
			if(tmpArr[i].equals("coupon_id_$n") && null != this.getCoupon_id_$n() && !"".equals(this.getCoupon_id_$n())){
				sb.append("coupon_id_$n=").append(this.getCoupon_id_$n());
				isValue = true;
			}
			if(tmpArr[i].equals("coupon_fee_$n") && null != this.getCoupon_fee_$n() && !"".equals(this.getCoupon_fee_$n())){
				sb.append("coupon_fee_$n=").append(this.getCoupon_fee_$n());
				isValue = true;
			}
			if(tmpArr[i].equals("transaction_id") && null != this.getTransaction_id() && !"".equals(this.getTransaction_id())){
				sb.append("transaction_id=").append(this.getTransaction_id());
				isValue = true;
			}
			if(tmpArr[i].equals("out_trade_no") && null != this.getOut_trade_no() && !"".equals(this.getOut_trade_no())){
				sb.append("out_trade_no=").append(this.getOut_trade_no());
				isValue = true;
			}
			if(tmpArr[i].equals("attach") && null != this.getAttach() && !"".equals(this.getAttach())){
				sb.append("attach=").append(this.getAttach());
				isValue = true;
			}
			if(tmpArr[i].equals("time_end") && null != this.getTime_end() && !"".equals(this.getTime_end())){
				sb.append("time_end=").append(this.getTime_end());
				isValue = true;
			}
			if(isValue){
				sb.append("&");
			}
		}
		return sb.append("key=").toString();
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
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getDevice_info() {
		return device_info;
	}
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getResult_code() {
		return result_code;
	}
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	public String getErr_code() {
		return err_code;
	}
	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	public String getErr_code_des() {
		return err_code_des;
	}
	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getIs_subscribe() {
		return is_subscribe;
	}
	public void setIs_subscribe(String is_subscribe) {
		this.is_subscribe = is_subscribe;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public String getBank_type() {
		return bank_type;
	}
	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public String getCash_fee() {
		return cash_fee;
	}
	public void setCash_fee(String cash_fee) {
		this.cash_fee = cash_fee;
	}
	public String getCash_fee_type() {
		return cash_fee_type;
	}
	public void setCash_fee_type(String cash_fee_type) {
		this.cash_fee_type = cash_fee_type;
	}
	public String getCoupon_fee() {
		return coupon_fee;
	}
	public void setCoupon_fee(String coupon_fee) {
		this.coupon_fee = coupon_fee;
	}
	public String getCoupon_count() {
		return coupon_count;
	}
	public void setCoupon_count(String coupon_count) {
		this.coupon_count = coupon_count;
	}
	public String getCoupon_id_$n() {
		return coupon_id_$n;
	}
	public void setCoupon_id_$n(String coupon_id_$n) {
		this.coupon_id_$n = coupon_id_$n;
	}
	public String getCoupon_fee_$n() {
		return coupon_fee_$n;
	}
	public void setCoupon_fee_$n(String coupon_fee_$n) {
		this.coupon_fee_$n = coupon_fee_$n;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getTime_end() {
		return time_end;
	}
	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}
	
	
}
