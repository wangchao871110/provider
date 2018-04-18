package com.xfs.bill.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.xfs.bill.model.SpsBill;
import com.xfs.common.util.Arith;
import com.xfs.common.util.Config;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.bill.dao.SpsBillitemDao;
import com.xfs.bill.model.SpsBillitem;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.corp.service.CmEmployeeService;

/**
 * 账单费用项目服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-10 15:09
 * @version 	: V1.0
 */
@Service
public class SpsBillitemServiceImpl implements SpsBillitemService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsBillitemServiceImpl.class);
	
	@Autowired
	private SpsBillitemDao spsBillitemDao;

	@Autowired
	private CmEmployeeService cmEmployeeService;
	
	public int save(ContextInfo cti, SpsBillitem vo ){
		return spsBillitemDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsBillitem vo ){
		return spsBillitemDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsBillitem vo ){
		return spsBillitemDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsBillitem vo ){
		return spsBillitemDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsBillitem vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsBillitemDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsBillitem> datas = spsBillitemDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsBillitem> findAll(SpsBillitem vo){
		
		List<SpsBillitem> datas = spsBillitemDao.freeFind(vo);
		return datas;
		
	}

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
	@Override
	public List<SpsBillitem> queryCmBillitem(Integer spid, Integer cmid,Integer bill_id,String period) {
		return spsBillitemDao.queryCmBillitem(spid,cmid,bill_id,period);
	}

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
	@Override
	public Map<String,Object> queryFee(Integer spid, Integer cmid) {
		Map<String,Object> feemap = cmEmployeeService.sumCmEmployeeFee(spid,cmid);
		feemap.put("合计",new BigDecimal(0.00));
		for(Map.Entry<String,Object> entry : feemap.entrySet()){
			BigDecimal fee = new BigDecimal(String.valueOf(entry.getValue()));
			BigDecimal sum_fee = new BigDecimal(String.valueOf(feemap.get("合计")));
			feemap.put("合计",sum_fee.add(fee));
		}
		return feemap;
	}

	/**
	 *  获取企业账单服务项汇总列表
	 *  @param  spid	服务商ID
	 *  @param	cmid	企业ID
	 *  @param	bill_id	账单ID
	 *  @param	period	账单月份
	 *	@return 			: java.util.List<java.util.List<java.lang.Object>>
	 *  @createDate  	: 2016-11-09 11:34
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 11:34
	 *  @updateAuthor  :
	 */
	@Override
	public List<List<Object>> querySumCmBillByItem(Integer spid, Integer cmid, Integer bill_id,String period,BigDecimal marginOfficeFee,BigDecimal marginService) {
		List<SpsBillitem> spsBillitems = queryCmBillitem(spid, cmid, bill_id,period);
		List<List<Object>> dataMap = new ArrayList<>();
		if (null != spsBillitems && !spsBillitems.isEmpty()) {
			BigDecimal total_fee = BigDecimal.ZERO;
			for (SpsBillitem item : spsBillitems) {
				List<Object> row = new ArrayList<>();
				row.add(item.getName());
				row.add(null == item.getFee() ? "" : String.valueOf(item.getFee()));
				total_fee = total_fee.add(com.xfs.common.util.StringUtils.toBigDecimal(String.valueOf(item.getFee())));
				row.add("");
				dataMap.add(row);
			}
			/**
			 * 上月余额
			 */
			List<Object> margin_row = new ArrayList<>();
			margin_row.add("上月余额");
			total_fee = Arith.sub(total_fee,marginOfficeFee,marginService);
			margin_row.add(String.valueOf(Arith.add(marginOfficeFee,marginService)));
			margin_row.add("");
			dataMap.add(margin_row);

			/**
			 * 费用总计
			 */
			List<Object> row = new ArrayList<>();
			row.add("费用总计");
			row.add(String.valueOf(total_fee));
			row.add("");
			dataMap.add(row);
		}
		return dataMap;
	}
}
