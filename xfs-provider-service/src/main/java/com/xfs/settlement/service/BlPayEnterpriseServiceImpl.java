package com.xfs.settlement.service;
import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.http.HttpClientUtil;
import com.xfs.pay.PayConfig;
import com.xfs.pay.PayContants;
import com.xfs.settlement.dao.BlPayEnterpriseDao;
import com.xfs.settlement.dto.ReqMerchant;
import com.xfs.settlement.dto.ReqMerchantStatus;
import com.xfs.settlement.model.BlPayEnterprise;
import org.apache.ibatis.type.TypeReference;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.page.PageModel;
import com.xfs.common.page.SystemContext;


/**
 * 畅捷通开户服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-17 16:38
 * @version 	: V1.0
 */
@Service
public class BlPayEnterpriseServiceImpl implements BlPayEnterpriseService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BlPayEnterpriseServiceImpl.class);
	
	@Autowired
	private BlPayEnterpriseDao blPayEnterpriseDao;
	
	public int save(ContextInfo cti, BlPayEnterprise vo ){
		return blPayEnterpriseDao.save(cti,vo);
	}
	public int insert(ContextInfo cti,  BlPayEnterprise vo ){
		return blPayEnterpriseDao.insert(cti,vo);
	}
	public int remove(ContextInfo cti,  BlPayEnterprise vo ){
		return blPayEnterpriseDao.remove(cti,vo);
	}
	public int update(ContextInfo cti,  BlPayEnterprise vo ){
		return blPayEnterpriseDao.update(cti,vo);
	}
	public PageModel findPage(PageInfo pi,BlPayEnterprise vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		Integer total = blPayEnterpriseDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BlPayEnterprise> datas = blPayEnterpriseDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BlPayEnterprise> findAll(BlPayEnterprise vo){
		
		List<BlPayEnterprise> datas = blPayEnterpriseDao.freeFind(vo);
		return datas;
		
	}



	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/11/17 16:32:49

	/**
	 *  根据拥有者ID 和 拥有者类型获取子商户信息
	 *  @param   reqMerchant
	 *	@return 			: com.xfs.settlement.model.BlPayEnterprise
	 *  @createDate  	: 2016-11-19 16:29
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-19 16:29
	 *  @updateAuthor  :
	 */
	@Override
	public BlPayEnterprise queryEnterpriseByOwnId(ReqMerchant reqMerchant) {
		return blPayEnterpriseDao.queryEnterpriseByOwnId(reqMerchant);
	}

	/**
	 *  创建子商户信息
	 *  @param   reqMerchant
	 *	@return 			: int
	 *  @createDate  	: 2016-11-19 16:29
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-19 16:29
	 *  @updateAuthor  :
	 */
	@Override
	public Result insertEnterprise(ReqMerchant reqMerchant,Result result){
		BlPayEnterprise enterprise = new BlPayEnterprise();
		enterprise.setOwner(reqMerchant.getOwner());
		enterprise.setOwnerType(reqMerchant.getOwnerType());
		enterprise.setAddress(reqMerchant.getAddress());
		enterprise.setCompanyNatddure(reqMerchant.getCompany_nature());
		enterprise.setCounterman(reqMerchant.getCounterman());
		enterprise.setEmail(reqMerchant.getEmail());
		enterprise.setEnterpriseName(reqMerchant.getEnterprise_name());
		enterprise.setIdCardNo(reqMerchant.getId_card_no());
		enterprise.setLegalPerson(reqMerchant.getLegal_person());
		enterprise.setLoginName(reqMerchant.getLogin_name());
		enterprise.setMemberId(reqMerchant.getMemberId());
		enterprise.setPartnerId(reqMerchant.getPartnerId());
		enterprise.setRegisteredCapital(reqMerchant.getRegistered_capital());
		enterprise.setUid(reqMerchant.getUid());
		enterprise.setTelephone(reqMerchant.getTelephone());
		enterprise.setState("STOP");
		int row = insert(null,enterprise);
		if(row <= 0){
			result.setSuccess(false);
			result.setError("该用户创建子商户信息失败");
		}else {
			result.setDataValue("enterprid",row);
		}
		return result;
	}

	/**
	 * 修改子商户实名审核状态
	 * @param reqMerchantStatus
	 * @param result
	 * @return
	 */
	public Result updateEnterprise(ReqMerchantStatus reqMerchantStatus, Result result){
		BlPayEnterprise enterprise = new BlPayEnterprise();
		enterprise.setMemberId(reqMerchantStatus.getIdentity_no());
		enterprise.setState("USE");
		int row = blPayEnterpriseDao.updateEnterprise(enterprise);
		if(row <= 0){
			result.setSuccess(false);
			result.setError("修改子商户状态失败");
		}
		return result;
	}
	
	/**
	 * 根据spId查询是否开通子账户
	 * @param spid
	 * @author         	: zhengdan@xinfushe.com
	 * @return
	 */
	@Override
	public Map<String, Object> queryEnterAccount(Integer spid) {
		// TODO Auto-generated method stub
		return blPayEnterpriseDao.queryEnterAccount(spid);
	}

}
