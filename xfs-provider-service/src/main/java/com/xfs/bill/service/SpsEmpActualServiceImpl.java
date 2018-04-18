package com.xfs.bill.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.bill.dao.SpsEmpActualDao;
import com.xfs.bill.model.SpsEmpActual;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.corp.model.CmEmployee;
import com.xfs.sp.dto.ActualInfo;
import com.xfs.user.model.SysUser;

/**
 * 实缴账单员工服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-10 14:59
 * @version 	: V1.0
 */
@Service
public class SpsEmpActualServiceImpl implements SpsEmpActualService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsEmpActualServiceImpl.class);
	
	@Autowired
	private SpsEmpActualDao spsEmpActualDao;
	
	public int save(ContextInfo cti, SpsEmpActual vo ){
		return spsEmpActualDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsEmpActual vo ){
		return spsEmpActualDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsEmpActual vo ){
		return spsEmpActualDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsEmpActual vo ){
		return spsEmpActualDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsEmpActual vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsEmpActualDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsEmpActual> datas = spsEmpActualDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsEmpActual> findAll(SpsEmpActual vo){
		
		List<SpsEmpActual> datas = spsEmpActualDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/12 11:05:28

	/**
	 *  根据 period ，emp_id 标记删除
	 *  @param   spsEmpActual
	 *	@return 			: int
	 *  @createDate  	: 2016-11-10 14:59
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-10 14:59
	 *  @updateAuthor  :
	 */
	@Override
	public int updateDrOfEmpActual(ContextInfo cti, SpsEmpActual spsEmpActual) {
		return spsEmpActualDao.update(cti, "SPS_EMP_ACTUAL.updateDrOfEmpActual", spsEmpActual);
	}

	/**
	 *  查保险和个人信息
	 *  @param   vo, user
	 *	@return 			: com.xfs.common.page.PageModel
	 *  @createDate  	: 2016-11-10 15:00
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-10 15:00
	 *  @updateAuthor  :
	 */
	@Override
	public PageModel findActualEmployee(PageInfo pi, CmEmployee vo, ContextInfo cti) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total=spsEmpActualDao.countFreeFind(vo,cti);
		pm.setTotal(total);
		List<ActualInfo> datas = spsEmpActualDao.findActualEmployee(vo,cti, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}

	/**
	 *  查社保的社保库类型和缴费地
	 *  @param   CmEmployee
	 *	@return 			: java.util.List<com.xfs.sp.dto.ActualInfo>
	 *  @createDate  	: 2016-11-10 15:02
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-10 15:02
	 *  @updateAuthor  :
	 */
	@Override
	public List<ActualInfo> FindActualInsurance(CmEmployee obj) {
		return spsEmpActualDao.FindActualInsurance(obj);
	}

	/**
	 *  查询员工实缴详情
	 *  @param   insurance_accid
	 *  @param   fund_accid
	 *  @param   period
	 *  @param   payType
	 *  @param   empIds
	 *  @param   insuranceOrFund
	 *	@return 			: java.util.List<com.xfs.bill.model.SpsEmpActual>
	 *  @createDate  	: 2016-11-10 15:04
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-10 15:04
	 *  @updateAuthor  :
	 */
	@Override
	public List<SpsEmpActual> findSpsEmpActualWithDetail(Integer insurance_accid, Integer fund_accid, String period, String payType, List<Integer> empIds, String insuranceOrFund) {
		return spsEmpActualDao.findSpsEmpActualWithDetail(insurance_accid, fund_accid, period, payType,empIds, insuranceOrFund);
	}

	/**
	 *  公积金的社保库类型和缴费地
	 *  @param   obj
	 *	@return 			: java.util.List<com.xfs.sp.dto.ActualInfo>
	 *  @createDate  	: 2016-11-10 15:06
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-10 15:06
	 *  @updateAuthor  :
	 */
	@Override
	public List<ActualInfo> FindActualFund(CmEmployee obj) {
		return spsEmpActualDao.FindActualFund(obj);
	}

	/**
	 *  汇总公积金的社保库类型和缴费地
	 *  @param   obj
	 *	@return 			: java.util.List<com.xfs.sp.dto.ActualInfo>
	 *  @createDate  	: 2016-11-10 15:07
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-10 15:07
	 *  @updateAuthor  :
	 */
	@Override
	public List<ActualInfo> FindActualSum(CmEmployee obj) {
		return spsEmpActualDao.FindActualSum(obj);
	}

	/**
	 *  实缴账单 获取个人实缴明细 如果存在，使用实缴明细作为月缴
	 *  @param   insuranceOrFund, insurance_accid, fund_accid, period, payType
	 *	@return 			: int
	 *  @createDate  	: 2016-11-10 15:08
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-10 15:08
	 *  @updateAuthor  :
	 */
	@Override
	public int countByAccIdAndPeriod(String insuranceOrFund, Integer insurance_accid, Integer fund_accid, String period, String payType) {
		return spsEmpActualDao.countByAccIdAndPeriod(insuranceOrFund,insurance_accid,fund_accid,period,payType);
	}
	
}
