package com.xfs.business.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.business.dao.BdBstypeDao;
import com.xfs.business.model.BdBstype;
import com.xfs.business.service.BdBstypeService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * 业务类型服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-11 14:54
 * @version 	: V1.0
 */
@Service
public class BdBstypeServiceImpl implements BdBstypeService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BdBstypeServiceImpl.class);
	
	@Autowired
	private BdBstypeDao bdBstypeDao;
	
	public int save(ContextInfo cti, BdBstype vo ){
		return bdBstypeDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BdBstype vo ){
		return bdBstypeDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BdBstype vo ){
		return bdBstypeDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BdBstype vo ){
		return bdBstypeDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BdBstype vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = 999;//pi.getPagesize();
		Integer total = bdBstypeDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BdBstype> datas = bdBstypeDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}

	@Override
	public PageModel findAllList(PageInfo pi, BdBstype vo){

		PageModel pm = new PageModel(pi);
		Integer total = bdBstypeDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BdBstype> datas = bdBstypeDao.freeFind(vo);
		pm.setDatas(datas);
		return pm;

	}
	public List<BdBstype> findAll(BdBstype vo){
		
		List<BdBstype> datas = bdBstypeDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/05/24 11:25:05

	/**
	 *  获取业务类型
	 *  @param   bdBstype
	 *	@return 			: com.xfs.business.model.BdBstype
	 *  @createDate  	: 2016-11-11 14:57
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:57
	 *  @updateAuthor  :
	 */
	@Override
	public BdBstype findBdBstypeByKey(BdBstype bdBstype) {
		return bdBstypeDao.findByPK(bdBstype);
	}

	/**
	 *  根据地区获取业务类型列表
	 *  @param   areaId
	 *	@return 			: com.xfs.common.page.PageModel
	 *  @createDate  	: 2016-11-11 14:57
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:57
	 *  @updateAuthor  :
	 */
	@Override
	public PageModel findBdBsTypeByAreaIdPM(PageInfo pi, int areaId) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = 999;//pi.getPagesize();
		Integer total = bdBstypeDao.countBsTypeByAreaId(areaId);
		pm.setTotal(total);
		List<BdBstype> datas = bdBstypeDao.findBdBsTypeByAreaId(areaId, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}

	/**
	 *  根据条件查询业务类型
	 *  @param   ob
	 *	@return 			: java.util.List<com.xfs.business.model.BdBstype>
	 *  @createDate  	: 2016-11-11 14:58
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:58
	 *  @updateAuthor  :
	 */
	@Override
	public List<BdBstype> findBstypeList(BdBstype ob) {
		return bdBstypeDao.findBstypeList(ob);
	}

	/**
	 *  获取业务类型
	 *  @param   bstypeId
	 *	@return 			: com.xfs.business.model.BdBstype
	 *  @createDate  	: 2016-11-11 14:59
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:59
	 *  @updateAuthor  :
	 */
	@Override
	public BdBstype findByPK(Integer bstypeId) {
		BdBstype bstype = new BdBstype();
		bstype.setBstypeId(bstypeId);
		return bdBstypeDao.findByPK(bstype);
	}

	/**
	 *  根据地区获取业务类型列表
	 *  @param   areaId
	 *	@return 			: java.util.List<com.xfs.business.model.BdBstype>
	 *  @createDate  	: 2016-11-11 14:59
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:59
	 *  @updateAuthor  :
	 */
	@Override
	public List<BdBstype> findBdBstypeListByAreaId(Integer areaId) {
		List<BdBstype> bdBstypes = bdBstypeDao.findBdBstypeListByAreaId(areaId);
		return bdBstypes;
	}
}
