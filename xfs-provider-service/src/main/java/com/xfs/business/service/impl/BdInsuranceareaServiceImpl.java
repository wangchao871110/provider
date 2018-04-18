package com.xfs.business.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.base.model.BsArea;
import com.xfs.base.service.BsAreaService;
import com.xfs.business.dao.BdInsuranceareaDao;
import com.xfs.business.model.BdInsurancearea;
import com.xfs.business.service.BdInsuranceareaService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * 地区保险服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-11 15:01
 * @version 	: V1.0
 */
@Service
public class BdInsuranceareaServiceImpl implements BdInsuranceareaService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BdInsuranceareaServiceImpl.class);
	
	@Autowired
	private BdInsuranceareaDao bdInsuranceareaDao;
	@Autowired
	private BsAreaService bsAreaService;
	
	public int save(ContextInfo cti, BdInsurancearea vo ){
		return bdInsuranceareaDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BdInsurancearea vo ){
		return bdInsuranceareaDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BdInsurancearea vo ){
		return bdInsuranceareaDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BdInsurancearea vo ){
		return bdInsuranceareaDao.update(cti, vo);
	}
	

	public PageModel findPage(PageInfo pi, BdInsurancearea vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bdInsuranceareaDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BdInsurancearea> datas = bdInsuranceareaDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BdInsurancearea> findAll(BdInsurancearea vo){
		
		List<BdInsurancearea> datas = bdInsuranceareaDao.freeFind(vo);
		return datas;
		
	}


	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/05/16 16:36:51

	/**
	 *  获取区域下的社保项目
	 *  @param   spid
	 *  @param   cmid
	 *  @param   bill_id
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate  	: 2016-11-10 18:16
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-10 18:16
	 *  @updateAuthor  :
	 */
	@Override
	public List<Map<String, Object>> queryEmInsuranceAreaList(Integer spid,Integer cmid,Integer bill_id) {
		return bdInsuranceareaDao.queryEmInsuranceAreaList(spid,cmid,null,bill_id);
	}

	/**
	 *  获取区域下的社保项目
	 *  @param   cmid
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate  	: 2016-11-10 18:17
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-10 18:17
	 *  @updateAuthor  :
	 */
	@Override
	public List<Map<String, Object>> queryEmInsuranceAreaList(Integer cmid) {
		return bdInsuranceareaDao.queryEmInsuranceAreaList(cmid,null);
	}

	/**
	 *  根据地区ID获取社保信息
	 *  @param   areaid
	 *	@return 			: java.util.List<com.xfs.business.model.BdInsurancearea>
	 *  @createDate  	: 2016-11-10 18:18
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-10 18:18
	 *  @updateAuthor  :
	 */
	@Override
	public List<BdInsurancearea> findBdInsuranceareaByAreaid(Integer areaid) {
		List<BdInsurancearea> result = bdInsuranceareaDao.findBdInsuranceareaByAreaid(areaid);
		if (result == null || result.size() == 0) {
			BsArea qry = new BsArea();
			qry.setAreaId(areaid);
			qry = bsAreaService.findbyPK(qry);
			result = bdInsuranceareaDao.findBdInsuranceareaByAreaid(qry.getParent());
		}
		return result;
	}

	/**
	 *  根据地区ID获取社保信息
	 *  @param   areaid
	 *	@return 			: java.util.List<com.xfs.business.model.BdInsurancearea>
	 *  @createDate  	: 2016-11-10 18:18
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-10 18:18
	 *  @updateAuthor  :
	 */
	@Override
	public List<BdInsurancearea> findBdInsuranceareaByAreaidAndInsuranceid(Integer areaid) {
		return bdInsuranceareaDao.findBdInsuranceareaByAreaidAndInsuranceid(areaid);
	}

	/**
	 *  根据地区 与类型 查询险种
	 *  @param   area_id, insuFundType
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate  	: 2016-11-10 18:19
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-10 18:19
	 *  @updateAuthor  :
	 */
	@Override
	public List<Map<String,Object>> findInsurancesByAreaIdAndType(Integer area_id,String insuFundType){
		return bdInsuranceareaDao.findInsurancesByAreaIdAndType(area_id,insuFundType);
	}
}
