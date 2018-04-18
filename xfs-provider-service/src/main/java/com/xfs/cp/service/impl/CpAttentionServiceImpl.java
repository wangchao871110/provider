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
import com.xfs.cp.dao.CpAttentionDao;
import com.xfs.cp.dao.CpServiceDao;
import com.xfs.cp.model.CpAttention;
import com.xfs.cp.model.CpService;
import com.xfs.cp.service.CpAttentionService;

@Service
public class CpAttentionServiceImpl implements CpAttentionService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(CpAttentionServiceImpl.class);
	
	@Autowired
	private CpAttentionDao cpAttentionDao;
	@Autowired
	private CpServiceDao cpServiceDao;
	
	public int save(ContextInfo cti, CpAttention vo ){
		return cpAttentionDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  CpAttention vo ){
		return cpAttentionDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  CpAttention vo ){
		return cpAttentionDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  CpAttention vo ){
		return cpAttentionDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, CpAttention vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cpAttentionDao.countFreeFind(vo);
		pm.setTotal(total);
		List<CpAttention> datas = cpAttentionDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<CpAttention> findAll(CpAttention vo){
		
		List<CpAttention> datas = cpAttentionDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 10:55:17
	
	@Override
	public Result attention(ContextInfo user,CpAttention cpAttention, Result result) {
		if (user != null) {
			if(user.getOrgId().equals(cpAttention.getAttentionSpidBy())){
				result.setMsg("登录单位和浏览单位是一个单位，不能相互关注！");
			}else{
				cpAttention.setAttentionSpid(user.getOrgId());
				cpAttention.setAttentionSpidBy(cpAttention.getAttentionSpidBy());
				cpAttention.setDr(0);
				List<CpAttention> cpAttentions = cpAttentionDao.freeFind(cpAttention);
				if(cpAttentions.size() == 0){
					cpAttention.setAttentionSpid(user.getOrgId());
					cpAttention.setCreateBy(user.getUserId());
					cpAttention.setCreateDt(new Date());
					if(cpAttentionDao.save(user,cpAttention) > 0){
						result.setSuccess(true);
						result.setMsg("关注成功！");
						// 更新关注次数
						CpService cpService = new CpService();
						cpService.setId(cpAttention.getAttentionSpidBy());
						cpService = cpServiceDao.findByPK(cpService);
						cpService.setAttentionNum(null != cpService.getAttentionNum()?cpService.getAttentionNum()+1:1);
						cpServiceDao.update(user,cpService);
					}else{
						result.setMsg("关注失败！");
					}
				}else{
					result.setMsg("您已经关注过该单位！");
				}
			}
		}else{
			result.setMsg("请先登录系统在关注！");
		}
		return result;
	}
	
}
