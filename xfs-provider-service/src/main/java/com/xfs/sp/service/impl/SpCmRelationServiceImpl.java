package com.xfs.sp.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.sp.dao.SpCmRelationDao;
import com.xfs.sp.model.SpCmRelation;
import com.xfs.sp.service.SpCmRelationService;
@Service
public class SpCmRelationServiceImpl implements SpCmRelationService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpCmRelationServiceImpl.class);
	
	@Autowired
	private SpCmRelationDao spCmRelationDao;
	
	public int save(ContextInfo cti, SpCmRelation vo ){
		return spCmRelationDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpCmRelation vo ){
		return spCmRelationDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpCmRelation vo ){
		return spCmRelationDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpCmRelation vo ){
		return spCmRelationDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpCmRelation vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spCmRelationDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpCmRelation> datas = spCmRelationDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpCmRelation> findAll(SpCmRelation vo){
		
		List<SpCmRelation> datas = spCmRelationDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/16 18:00:05

	/**
	 * 通过企业id获取关系数据列表
	 *
	 * @param vo
	 * @return
	 */
	public List<Map<String, Object>> freeFindByCpId(SpCmRelation vo) {
		return spCmRelationDao.freeFindByCpId(vo);
	}
	
	public SpCmRelation findBySpIdAndCpId(Integer spid, Integer cpid) {
		SpCmRelation rels = spCmRelationDao.findBySpidAndCpid(spid, cpid);
		return rels;
	}
	
	/**
	 * 根据订单日获取企业列表
	 * @return
     */
	@Override
	public List<SpCmRelation> queryAllCorpsByBillDay() {
		return spCmRelationDao.queryAllCropsByBillDay();
	}
	
	/**
	 * 根据服务商id和区域编号获取企业列表
	 *
	 * @param spId
	 * @param areaCode
	 * @return
	 */
	public List<Map<String, Object>> FindCorpsBySpId(Integer spId, String areaCode) {
		return spCmRelationDao.findCorpsBySpId(spId, areaCode);
	}
}
