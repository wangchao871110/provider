package com.xfs.settlement.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.xfs.common.Result;
import com.xfs.settlement.dto.ReqBindBank;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.settlement.dao.BlPayAccountDao;
import com.xfs.settlement.model.BlPayAccount;

/**
 * 支付帐号服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-10 15:16
 * @version 	: V1.0
 */
@Service
public class BlPayAccountServiceImpl implements BlPayAccountService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BlPayAccountServiceImpl.class);
	
	@Autowired
	private BlPayAccountDao blPayAccountDao;
	
	public int save(ContextInfo cti, BlPayAccount vo ){
		return blPayAccountDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BlPayAccount vo ){
		return blPayAccountDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BlPayAccount vo ){
		return blPayAccountDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BlPayAccount vo ){
		return blPayAccountDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BlPayAccount vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = blPayAccountDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BlPayAccount> datas = blPayAccountDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BlPayAccount> findAll(BlPayAccount vo){
		
		List<BlPayAccount> datas = blPayAccountDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/12 11:05:30

	/**
	 *  获取服务商下的支付方式列表
	 *  @param   account
	 *	@return 			: java.util.List<com.xfs.settlement.model.BlPayAccount>
	 *  @createDate  	: 2016-11-09 17:35
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 17:35
	 *  @updateAuthor  :
	 */
	@Override
	public List<BlPayAccount> querySpsPayAccount(BlPayAccount account) {
		return blPayAccountDao.freeFind(account);
	}

	/**
	 *  获取支付帐号详情
	 *  @param   obj
	 *	@return 			: com.xfs.settlement.model.BlPayAccount
	 *  @createDate  	: 2016-11-09 17:36
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 17:36
	 *  @updateAuthor  :
	 */
	@Override
	public BlPayAccount findByPK(BlPayAccount obj) {
		return blPayAccountDao.findByPK(obj);
	}

	/**
	 *  编辑支付帐号
	 *  @param   whereMapOffic
	 *  @param   whereMapService
	 *	@return 			: int
	 *  @createDate  	: 2016-11-09 17:37
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 17:37
	 *  @updateAuthor  :
	 */
	@Override
	public int editAccount(ContextInfo cti,Map<String, Object> whereMapOffic,Map<String, Object> whereMapService) {
		//标记删除
		blPayAccountDao.modityAccount(cti,whereMapOffic);
		int count=0;
		//isConsistent==0表示 服务费账户与官费账户相同，1服务费账户与官费账户不相同
		if(whereMapOffic.get("isConsistent").equals("0")){
			
			whereMapOffic.put("account_type","office");
			blPayAccountDao.addAccount(cti,whereMapOffic);
			
			whereMapOffic.put("account_type","service");
			count=blPayAccountDao.addAccount(cti,whereMapOffic);
		}else{
			
			whereMapOffic.put("account_type","office");
			blPayAccountDao.addAccount(cti,whereMapOffic);
			
			whereMapService.put("account_type","service");
			count=blPayAccountDao.addAccount(cti,whereMapService);
		}
		
		return count;
	}

	/**
	 *  获取支付帐号详情
	 *  @param   whereMap
	 *	@return 			: java.util.Map<java.lang.String,java.lang.Object>
	 *  @createDate  	: 2016-11-09 17:38
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 17:38
	 *  @updateAuthor  :
	 */
	@Override
	public Map<String, Object> getAccountInfo(Map<String, Object> whereMap) {
		return blPayAccountDao.getAccountInfo(whereMap);
	}


	/**
	 *  根据交易号获取支付账号信息列表
	 *  @param   payType
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate  	: 2016-11-09 17:38
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 17:38
	 *  @updateAuthor  :
	 */
	@Override
	public List<Map<String,Object>> queryPayAccountList(String payType){
		return blPayAccountDao.queryPayAccountList(payType);
	}

	/**
	 *  绑定商户银行卡信息
	 *  @param
	 * 	@return    :
	 *  @createDate   : 2016/11/29 11:52
	 *  @author          : gaoyf@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/29 11:52
	 *  @updateAuthor  :
	 */
	@Override
	public Result insertBindBankInfo(ReqBindBank reqBindBank, Result result,ContextInfo cti) {
		BlPayAccount bl = new BlPayAccount();
		bl.setOwner(reqBindBank.getOwner());
		bl.setOwnertType(reqBindBank.getOwnerType());
		bl.setAccountName(reqBindBank.getAccount_name());
		bl.setAccountBank(reqBindBank.getBank_name());
		bl.setAccountBranchBank(reqBindBank.getBank_branch());  //是银行名称还是银行编号
		bl.setAccountNo(reqBindBank.getBank_account_no());
		bl.setState(reqBindBank.getState());
		bl.setIsdefault(reqBindBank.getIsdefault());
		bl.setAccountAddress(reqBindBank.getProvince() + " " + reqBindBank.getCity()); //是银行名称还是银行编号
		bl.setAccountMobile(reqBindBank.getAccountMobile());
		bl.setAccountType(reqBindBank.getAccountType());
		bl.setDr(reqBindBank.getDr());
		bl.setEnterpriseId(reqBindBank.getEnterpriseId());
		bl.setAccountType(reqBindBank.getCard_attribute());
		bl.setLicenseNo(reqBindBank.getLicense_no());
		bl.setCreateDt(new Date());
		bl.setDr(0);
		result.setSuccess(blPayAccountDao.insert(cti, bl)>0);
		return result;
	}
}
