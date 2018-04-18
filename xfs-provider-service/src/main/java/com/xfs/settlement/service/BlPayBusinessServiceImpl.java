package com.xfs.settlement.service;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.util.StringUtils;
import com.xfs.pay.Pay;
import com.xfs.pay.PayType;
import com.xfs.settlement.dao.BlPayBusinessDao;
import com.xfs.settlement.dto.BasePay;
import com.xfs.settlement.dto.RespXfsPay;
import com.xfs.settlement.model.BlPayBusiness;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xfs.common.page.PageModel;
import com.xfs.common.page.SystemContext;

/**
 * 支付业务服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-16 10:43
 * @version 	: V1.0
 */
@Service
public class BlPayBusinessServiceImpl implements BlPayBusinessService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BlPayBusinessServiceImpl.class);
	
	@Autowired
	private BlPayBusinessDao blPayBusinessDao;
	
	public int save(ContextInfo cti, BlPayBusiness vo ){
		return blPayBusinessDao.save(cti,vo);
	}
	public int insert(ContextInfo cti, BlPayBusiness vo ){
		return blPayBusinessDao.insert(cti,vo);
	}
	public int remove(ContextInfo cti, BlPayBusiness vo ){
		return blPayBusinessDao.remove(cti,vo);
	}
	public int update(ContextInfo cti, BlPayBusiness vo ){
		return blPayBusinessDao.update(cti,vo);
	}
	public PageModel findPage(PageInfo pi, BlPayBusiness vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		Integer total = blPayBusinessDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BlPayBusiness> datas = blPayBusinessDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BlPayBusiness> findAll(BlPayBusiness vo){
		
		List<BlPayBusiness> datas = blPayBusinessDao.freeFind(vo);
		return datas;
		
	}


	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/11/16 10:28:38

	/**
	 *  根据应用appId 获取业务参数
	 *  @param   basePay
	 *	@return 			: com.xfs.settlement.model.BlPayBusiness
	 *  @createDate  	: 2016-11-17 21:06
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-17 21:06
	 *  @updateAuthor  :
	 */
	@Override
	public RespXfsPay queryBusinPayByAppIdAndPayID(BasePay basePay){
		return blPayBusinessDao.queryBusinPayByAppIdAndPayID(basePay);
	}

	/**
	 *  根据应用appId 获取业务参数
	 *  @param   basePay
	 *	@return 			: com.xfs.settlement.model.BlPayBusiness
	 *  @createDate  	: 2016-11-17 21:06
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-17 21:06
	 *  @updateAuthor  :
	 */
	public RespXfsPay queryBusinPayByAppId(BasePay basePay){
		return blPayBusinessDao.queryBusinPayByAppId(basePay);
	}

	/**
	 *  根据应用名称 获取业务信息
	 *  @param   appName
	 *	@return 			: com.xfs.settlement.dto.RespXfsPay
	 *  @createDate  	: 2016-11-26 13:21
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-26 13:21
	 *  @updateAuthor  :
	 */
	@Override
	public RespXfsPay queryBusinPayByAppName(String appName){
		return blPayBusinessDao.queryBusinPayByAppName(appName);
	}

	/**
	 *  根据业务ID查询业务信息
	 *  @param   busId
	 *	@return 			: com.xfs.settlement.dto.RespXfsPay
	 *  @createDate  	: 2016-12-12 21:18
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-12-12 21:18
	 *  @updateAuthor  :
	 */
	@Override
	public RespXfsPay queryBusinByBusId(Integer busId){
		return blPayBusinessDao.queryBusinByBusId(busId);
	}

	/**
	 *  检查请求参数信息
	 *  @param   basePay
	 *	@return 			: com.xfs.common.Result
	 *  @createDate  	: 2016-12-12 21:21
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-12-12 21:21
	 *  @updateAuthor  :
	 */
	@Override
	public Result checkReqBody(BasePay basePay){
		Result result = Result.createResult().setSuccess(false);
		if(StringUtils.isBlank(basePay.getAppId()))
			result.setError("应用appId不能为空！");
		else
			result.setSuccess(true);
		return result;
	}

	/**
	 *  校验签名信息
	 *  @param   basePay
	 *	@return 			: com.xfs.common.Result
	 *  @createDate  	: 2016-12-12 21:22
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-12-12 21:22
	 *  @updateAuthor  :
	 */
	@Override
	public Result verificateReqBodySign(BasePay basePay){
		RespXfsPay blPayBusiness = queryBusinPayByAppId(basePay);
		//校验签名
		TypeReference<Map<String, String>> ref = new TypeReference<Map<String, String>>() {};
		Map<String, String> params = JSON.parseObject(JSON.toJSONString(basePay), ref);
		Result result = Pay.verifyNotify(params, blPayBusiness, PayType.XFS_PAY);
		if(result.isSuccess()){
			result.setDataValue("blPayBusiness",blPayBusiness);
		}
		return result;
	}
}
