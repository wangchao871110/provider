package com.xfs.cp.service.impl;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.cp.dao.CpRelationDao;
import com.xfs.cp.model.CpRelation;
import com.xfs.cp.service.CpRelationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CpRelationServiceImpl implements CpRelationService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(CpRelationServiceImpl.class);
	
	@Autowired
	private CpRelationDao cpRelationDao;
	
	public int save(ContextInfo cti, CpRelation vo ){
		return cpRelationDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  CpRelation vo ){
		return cpRelationDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  CpRelation vo ){
		return cpRelationDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  CpRelation vo ){
		return cpRelationDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, CpRelation vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cpRelationDao.countFreeFind(vo);
		pm.setTotal(total);
		List<CpRelation> datas = cpRelationDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<CpRelation> findAll(CpRelation vo){
		
		List<CpRelation> datas = cpRelationDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 19:24:41
	
	@Override
	public Result linkRelation(ContextInfo user, CpRelation cpRelation, Result result) {
		if (user != null) {
			if(user.getOrgId().equals(cpRelation.getBSpId())){
				result.setMsg("登录单位和浏览单位是一个单位，不能建立关系！");
			}else{
 //				SpService spService = spServiceService.findByPk(cpRelation.getBSpId());
 //				if("UNAUDITED".equals(spService.getServiceState()) || "REGISTERED".equals(spService.getServiceState())){
 //	 				result.setMsg("您还没有认证SaaS系统，不能抢包，登录SaaS系统进行认证！");
 //	 	 			result.setSuccess(false);
 //	 			}else{
 	 				cpRelation.setASpId(user.getOrgId());
 					cpRelation.setDr(0);
 					List<CpRelation> cpRelations = cpRelationDao.freeFind(cpRelation);
 					if(cpRelations.size() == 0){
 						cpRelation.setStatus(0);
 						cpRelation.setCreateBy(user.getUserId());
 						cpRelation.setCreateDt(new Date());
 						if(cpRelationDao.save(user,cpRelation) > 0){
 							result.setSuccess(true);
 							result.setMsg("合作建立成功，请等待对方同意！");
 						}else{
 							result.setMsg("合作建立失败！");
 						}
 					}else{
 						result.setMsg("合作关系已经建立或者待签约!");
 					}
				//}
 			}
		}else{
			result.setMsg("请先登录系统在执行建立合作！");
		}
		return result;
	}

	@Override
	public CpRelation getCpRelationById(CpRelation cpRelation) {
		return cpRelationDao.findByPK(cpRelation);
	}

	public PageModel getBSpListPage(PageInfo pi,CpRelation vo){

		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = 10;
		Integer total = cpRelationDao.getBSpListCount(vo);
		pm.setTotal(total);
		List<CpRelation> datas = cpRelationDao.getBSpList(vo, pageIndex, pageSize);
		for(CpRelation o : datas){
			if(o.getSpCity()!=null){
				String[] arr = o.getSpCity().split(",");
				if(arr.length==2){
					o.setSpCity(arr[1].split("-")[1]);
				}
			}
		}
		pm.setDatas(datas);
		return pm;

	}

	public PageModel getASpListPage(PageInfo pi,CpRelation vo){

		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = 10;
		Integer total = cpRelationDao.getASpListCount(vo);
		pm.setTotal(total);
		List<CpRelation> datas = cpRelationDao.getASpList(vo, pageIndex, pageSize);
		for(CpRelation o : datas){
			if(o.getSpCity()!=null){
				String[] arr = o.getSpCity().split(",");
				if(arr.length==2){
					o.setSpCity(arr[1].split("-")[1]);
				}
			}
		}
		pm.setDatas(datas);
		return pm;

	}

	@Override
	public PageModel FindManage(PageInfo pi,CpRelation vo){
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cpRelationDao.FindManageCount(vo);
		pm.setTotal(total);
		List<CpRelation> cpRelations =cpRelationDao.FindManage(vo,pageIndex,pageSize);
		pm.setDatas(cpRelations);
		return pm;
	}

	@Override
	public Integer findPerson(CpRelation cpRelation){
		return cpRelationDao.findPerson(cpRelation);
	}


	@Override
	public Integer finTask(CpRelation cpRelation){
		return cpRelationDao.finTask(cpRelation);
	}
}
