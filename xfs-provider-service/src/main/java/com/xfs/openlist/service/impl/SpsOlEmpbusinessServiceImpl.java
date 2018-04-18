package com.xfs.openlist.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.openlist.dao.SpsOlEmpbusinessDao;
import com.xfs.openlist.model.SpsOl;
import com.xfs.openlist.model.SpsOlEmpbusiness;
import com.xfs.openlist.service.SpsOlCorpsetService;
import com.xfs.openlist.service.SpsOlEmpbusinessService;
import com.xfs.openlist.service.SpsOlService;
import com.xfs.sp.model.SpService;
import com.xfs.sp.service.SpServiceService;


@Service
public class SpsOlEmpbusinessServiceImpl implements SpsOlEmpbusinessService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsOlEmpbusinessServiceImpl.class);
	
	@Autowired
	private SpsOlEmpbusinessDao spsOlEmpbusinessDao;

	@Autowired
	private SpsOlService spsOlService;

	@Autowired
	private SpsOlCorpsetService spsOlCorpsetService;

	@Autowired
	private SpServiceService spServiceService;
	
	public int save(ContextInfo cti, SpsOlEmpbusiness vo ){
		return spsOlEmpbusinessDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsOlEmpbusiness vo ){
		return spsOlEmpbusinessDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsOlEmpbusiness vo ){
		return spsOlEmpbusinessDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsOlEmpbusiness vo ){
		return spsOlEmpbusinessDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsOlEmpbusiness vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsOlEmpbusinessDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsOlEmpbusiness> datas = spsOlEmpbusinessDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsOlEmpbusiness> findAll(SpsOlEmpbusiness vo){
		
		List<SpsOlEmpbusiness> datas = spsOlEmpbusinessDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/05 20:38:18


	/**
	 * 添加
	 * @param vo
	 * @return
     */
	public Result addEmpbusiness(ContextInfo cti,SpsOlEmpbusiness vo){
		boolean isok = false;
		Result result = Result.createResult().setSuccess(false);
		vo.setIsdeal(0);//未处理的
		vo.setCreateDt(new Date());
		//查看当前是否存在相同的未处理业务
		SpsOlEmpbusiness olEmpbusiness = new SpsOlEmpbusiness();
		olEmpbusiness.setIsdeal(0);
		olEmpbusiness.setBstypeId(vo.getBstypeId());
		if(null != vo.getCpId())
			olEmpbusiness.setCpId(vo.getCpId());
		else
		    olEmpbusiness.setCorpNameEq(vo.getCorpName());
		olEmpbusiness.setNameEq(vo.getName());
		olEmpbusiness.setMobileEq(vo.getMobile());

		List<SpsOlEmpbusiness> olEmpbusinesses = findAll(olEmpbusiness);
		if(null == olEmpbusinesses || olEmpbusinesses.isEmpty()){
			isok = insert(cti,vo) > 0 ? true : false;
			/**
			 * 使用模板统计
			 */
			if(isok){
				/**
				 * 使用模板统计
				 */
				SpsOl ol = spsOlService.findByPK(vo.getOlId());
				if (null != ol) {
					SpsOl spsOl = new SpsOl();
					spsOl.setOlId(ol.getOlId());
					spsOl.setUsedCount(null == ol.getUsedCount() ? 0 : ol.getUsedCount() + 1);
					spsOlService.update(cti,spsOl);
				}
			}
		}else{
			isok = true;
		}
		/**
		 * 返回联系信息
		 */
		SpService spService = null;
		if(isok){//成功
			result.setSuccess(true);
			if(null != vo.getCpId()){
				Map<String, Object> corpset = spsOlCorpsetService.findCorpsetByCmPerson(null,vo.getCpId());
				if (null != corpset) {
					if (String.valueOf(corpset.get("is_check")).equals("0")) {// 企业不核对
						// 获取服务商联系方式
						spService = spServiceService.findOLContacts(Integer.parseInt(String.valueOf(corpset.get("sp_id"))));
					} else {
						result.setDataValue("corpset", corpset);
					}
				}
			}else if(null != vo.getSpId()){
				spService = spServiceService.findOLContacts(vo.getSpId());
			}
			result.setDataValue("spinfo", spService);
		}
		return result;
	}
	
	
}
