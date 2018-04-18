package com.xfs.cp.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.cp.dao.CpIndustryDao;
import com.xfs.cp.dao.CpServiceDao;
import com.xfs.cp.model.CpIndustry;
import com.xfs.cp.model.CpService;
import com.xfs.cp.service.CpIndustryService;

@Service
public class CpIndustryServiceImpl implements CpIndustryService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(CpIndustryServiceImpl.class);
	
	@Autowired
	private CpIndustryDao cpIndustryDao;
	@Autowired
	private CpServiceDao cpServiceDao;
	
	public int save(ContextInfo cti, CpIndustry vo ){
		return cpIndustryDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  CpIndustry vo ){
		return cpIndustryDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  CpIndustry vo ){
		return cpIndustryDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  CpIndustry vo ){
		return cpIndustryDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, CpIndustry vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cpIndustryDao.countFreeFind(vo);
		pm.setTotal(total);
		List<CpIndustry> datas = cpIndustryDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<CpIndustry> findAll(CpIndustry vo){
		
		List<CpIndustry> datas = cpIndustryDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 10:55:20
	
	@Override
	public Result industry(ContextInfo user, CpIndustry cpIndustry, Result result) {
		if (user != null) {
			if(user.getOrgId().equals(cpIndustry.getCaSpidBy())){
				result.setMsg("登录单位和帮认证单位是一个单位，不能相互帮认证！");
			}else{
				cpIndustry.setCaSpid(user.getOrgId());
				cpIndustry.setDr(0);
				List<CpIndustry> cpIndustrys = cpIndustryDao.freeFind(cpIndustry);
				if(cpIndustrys.size() == 0){
					cpIndustry.setCreateBy(user.getUserId());
					cpIndustry.setCreateDt(new Date());
					if(cpIndustryDao.save(user,cpIndustry) > 0){
						result.setSuccess(true);
						result.setMsg("帮认证成功！");
						// 更新帮认证次数
						CpService cpService = new CpService();
						cpService.setId(cpIndustry.getCaSpidBy());
						cpService = cpServiceDao.findByPK(cpService);
						cpService.setIndustryNum(null != cpService.getIndustryNum()?cpService.getIndustryNum()+1:1);
						cpServiceDao.update(user,cpService);
					}else{
						result.setMsg("帮认证失败！");
					}
				}
			}
		}else{
			result.setMsg("请先登录系统在帮认证！");
		}
		return result;
	}
	
	
}
