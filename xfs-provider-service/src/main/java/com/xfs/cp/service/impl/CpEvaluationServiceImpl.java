package com.xfs.cp.service.impl;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.cp.dao.CpEvaluationDao;
import com.xfs.cp.model.CpEvaluation;
import com.xfs.cp.service.CpEvaluationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CpEvaluationServiceImpl implements CpEvaluationService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(CpEvaluationServiceImpl.class);
	
	@Autowired
	private CpEvaluationDao cpEvaluationDao;
	
	public int save(ContextInfo cti, CpEvaluation vo ){
		return cpEvaluationDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  CpEvaluation vo ){
		return cpEvaluationDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  CpEvaluation vo ){
		return cpEvaluationDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  CpEvaluation vo ){
		return cpEvaluationDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, CpEvaluation vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cpEvaluationDao.countFreeFind(vo);
		pm.setTotal(total);
		List<CpEvaluation> datas = cpEvaluationDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<CpEvaluation> findAll(CpEvaluation vo){
		
		List<CpEvaluation> datas = cpEvaluationDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 10:55:21
	/**
	 * 根据服务商ID获取用户点评和合作满意度
	 */
	@Override
	public CpEvaluation findEvaluation(CpEvaluation cpEvaluation) {
		return cpEvaluationDao.findEvaluation(cpEvaluation);
	}
	@Override
	public Result saveEvaluation(ContextInfo user, CpEvaluation cpEvaluation, Result result,String score) {
		cpEvaluation.setSpId(user.getOrgId());
		cpEvaluation.setCreateDt(new Date());
		String[] evvluation = score.split(",");
		cpEvaluation.setProfessionNum(Double.valueOf(evvluation[0]));
		cpEvaluation.setAttitudeNum(Double.valueOf(evvluation[1]));
		cpEvaluation.setEfficiencyNum(Double.valueOf(evvluation[2]));
		cpEvaluation.setPowerNum(Double.valueOf(evvluation[3]));
		cpEvaluation.setCooperationStar((cpEvaluation.getProfessionNum()+cpEvaluation.getAttitudeNum()+cpEvaluation.getPowerNum()+cpEvaluation.getEfficiencyNum())/4);
		if(null != cpEvaluation.getAnonymous()){
			cpEvaluation.setAnonymous("0");
		}else{
			cpEvaluation.setAnonymous("1");
		}
		if(cpEvaluationDao.save(user,cpEvaluation) > 0){
			result.setSuccess(true);
			result.setMsg("评价成功！");
		}else{
			result.setMsg("评价失败！");
		}
		return result;
	}


	@Override
	public PageModel queryVoucherList(PageInfo pi,CpEvaluation vo){
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cpEvaluationDao.queryVoucherCount(vo);
		pm.setTotal(total);
		List<CpEvaluation> cpEvaluations = cpEvaluationDao.queryVoucherList(vo, pageIndex, pageSize);
		pm.setDatas(cpEvaluations);
		return pm;

	}
}
