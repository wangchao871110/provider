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
import com.xfs.cp.dao.CpImpressDao;
import com.xfs.cp.model.CpImpress;
import com.xfs.cp.service.CpImpressService;

@Service
public class CpImpressServiceImpl implements CpImpressService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(CpImpressServiceImpl.class);
	
	@Autowired
	private CpImpressDao cpImpressDao;
	
	public int save(ContextInfo cti, CpImpress vo ){
		return cpImpressDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  CpImpress vo ){
		return cpImpressDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  CpImpress vo ){
		return cpImpressDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  CpImpress vo ){
		return cpImpressDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, CpImpress vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cpImpressDao.countFreeFind(vo);
		pm.setTotal(total);
		List<CpImpress> datas = cpImpressDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<CpImpress> findAll(CpImpress vo){
		
		List<CpImpress> datas = cpImpressDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 10:55:19
	
	/**
	 * 添加机构印象
	 */
	@Override
	public Result saveImpress(ContextInfo user, CpImpress cpImpress, Result result) {
		if (user != null) {
			CpImpress impress = new CpImpress();
			int countNum = cpImpressDao.countFreeFind(impress);
			if(countNum > 20){
				result.setMsg("服务机构印象最多20个，名额已经被别人占用，您来晚了！");
			}else{
				cpImpress.setCreateBy(user.getUserId());
				cpImpress.setCreateDt(new Date());
				cpImpress.setImpressNum(0);
				if(cpImpressDao.save(user,cpImpress) > 0){
					result.setSuccess(true);
					result.setMsg("添加成功！");
				}else{
					result.setMsg("添加失败！");
				}
			}
		}else{
			result.setMsg("你还没有登录系统，请先登录系统在添加印象！");
		}
		return result;
	}
	
}
