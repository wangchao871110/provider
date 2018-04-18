package com.xfs.bill.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.xfs.bill.model.SpsBillitem;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * 账单费用项目服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-10 14:58
 * @version 	: V1.0
 */
public interface SpsBillitemService {
	
	public int save(ContextInfo cti, SpsBillitem vo);
	public int insert(ContextInfo cti, SpsBillitem vo);
	public int remove(ContextInfo cti, SpsBillitem vo);
	public int update(ContextInfo cti, SpsBillitem vo);
	public PageModel findPage(PageInfo pi, SpsBillitem vo);
	public List<SpsBillitem> findAll(SpsBillitem vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/05/16 15:32:12

	/**
	 *  获取企业账单项目
	 *  @param spid	服务商ID
	 *  @param cmid	企业ID
	 *  @param bill_id 账单ID
	 *  @param	period	账单月份
	 *	@return 			: java.util.List<com.xfs.bill.model.SpsBillitem>
	 *  @createDate  	: 2016-11-09 11:37
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 11:37
	 *  @updateAuthor  :
	 */
	public List<SpsBillitem> queryCmBillitem(Integer spid, Integer cmid, Integer bill_id,String period);

	/**
	 *  获取代扣代缴/补缴服务费/公积金代扣代缴/服务费
	 *  @param	spid	服务商ID
	 *  @param	cmid	企业ID
	 *	@return 			: java.util.Map<java.lang.String,java.lang.Object>
	 *  @createDate  	: 2016-11-09 11:36
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 11:36
	 *  @updateAuthor  :
	 */
	public Map<String,Object> queryFee(Integer spid, Integer cmid);

	/**
	 *  获取企业账单服务项汇总列表
	 *  @param  spid	服务商ID
	 *  @param	cmid	企业ID
	 *  @param	bill_id	账单ID
	 *  @param	period	账单月份
	 *  @param  marginOfficeFee 官费差额
	 *  @param  marginService  服务费差额
	 *	@return 			: java.util.List<java.util.List<java.lang.Object>>
	 *  @createDate  	: 2016-11-09 11:34
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 11:34
	 *  @updateAuthor  :
	 */
	public List<List<Object>> querySumCmBillByItem(Integer spid, Integer cmid, Integer bill_id, String period, BigDecimal marginOfficeFee,BigDecimal marginService);

}
