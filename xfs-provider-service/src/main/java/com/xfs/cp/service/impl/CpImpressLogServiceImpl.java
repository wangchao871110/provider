package com.xfs.cp.service.impl;

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
import com.xfs.cp.dao.CpImpressDao;
import com.xfs.cp.dao.CpImpressLogDao;
import com.xfs.cp.model.CpImpress;
import com.xfs.cp.model.CpImpressLog;
import com.xfs.cp.service.CpImpressLogService;

@Service
public class CpImpressLogServiceImpl implements CpImpressLogService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(CpImpressLogServiceImpl.class);
	
	@Autowired
	private CpImpressLogDao cpImpressLogDao;
	
	@Autowired
	private CpImpressDao cpImpressDao;
	
	public int save(ContextInfo cti, CpImpressLog vo ){
		return cpImpressLogDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  CpImpressLog vo ){
		return cpImpressLogDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  CpImpressLog vo ){
		return cpImpressLogDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  CpImpressLog vo ){
		return cpImpressLogDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, CpImpressLog vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cpImpressLogDao.countFreeFind(vo);
		pm.setTotal(total);
		List<CpImpressLog> datas = cpImpressLogDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<CpImpressLog> findAll(CpImpressLog vo){
		
		List<CpImpressLog> datas = cpImpressLogDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 10:55:20
	
	@Override
	public  List<Map<String, Object>> findByCpId(CpImpressLog cpImpressLog) {
		List<Map<String, Object>> datas = cpImpressLogDao.findByCpId(cpImpressLog);
		return datas;
	}
	@Override
	public Result saveImpressLog(ContextInfo user, CpImpressLog cpImpressLog, Result result) {
		if (user != null) {
			cpImpressLog.setSpId(user.getOrgId());
			cpImpressLog.setCreateDt(new Date());
			if(cpImpressLogDao.save(user,cpImpressLog) > 0){
				String[] cpImpressId = cpImpressLog.getImpressId().split(",");
				for (int i = 0; i < cpImpressId.length; i++) {
					CpImpress cpImpress = new CpImpress();
					cpImpress.setId(Integer.valueOf(cpImpressId[i]));
					cpImpress = cpImpressDao.findByPK(cpImpress);
					cpImpress.setImpressNum(cpImpress.getImpressNum()+1);
					cpImpressDao.update(user,cpImpress);
				}
				result.setSuccess(true);
				result.setMsg("点评成功！");
			}else{
				result.setMsg("点评失败！");
			}
		}else{
			result.setMsg("你还没有登录系统，请先登录系统在对服务商进行点评！");
		}
		return result;
	}
	
}
