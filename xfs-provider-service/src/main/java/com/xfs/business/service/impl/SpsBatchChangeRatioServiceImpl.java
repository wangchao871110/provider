package com.xfs.business.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xfs.aop.task.sps.TaskAspectService;
import com.xfs.base.model.BsArea;
import com.xfs.base.service.BsAreaService;
import com.xfs.business.dao.BdInsuranceDao;
import com.xfs.business.dao.BsArearatioDao;
import com.xfs.business.dao.SpsBatchChangeRatioDao;
import com.xfs.business.dto.BatchRatioEmpQuery;
import com.xfs.business.dto.SpsBatchChangeRatioVo;
import com.xfs.business.model.BdInsurance;
import com.xfs.business.model.SpsBatchChangeRatio;
import com.xfs.business.model.SpsTask;
import com.xfs.business.service.BdInsuranceService;
import com.xfs.business.service.SpsBatchChangeRatioService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.exception.BusinessException;
import com.xfs.common.page.PageModel;
import com.xfs.common.page.SystemContext;
import com.xfs.common.util.Constant;
import com.xfs.corp.service.CmEmployeeService;
import com.xfs.common.util.NumberUtil;
import com.xfs.user.service.SysFunctionDataService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class SpsBatchChangeRatioServiceImpl implements SpsBatchChangeRatioService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsBatchChangeRatioServiceImpl.class);
	
	@Autowired
	private SpsBatchChangeRatioDao spsBatchChangeRatioDao;

	@Autowired
	private CmEmployeeService cmEmployeeService;
	@Autowired
	private BsArearatioDao bsArearatioDao;
	@Autowired
	TaskAspectService taskAspectService;
    @Autowired
    private BdInsuranceService bdInsuranceService;
	@Autowired
	private SysFunctionDataService sysFunctionDataService;
	
	public int save(ContextInfo info, SpsBatchChangeRatio vo ){
		return spsBatchChangeRatioDao.save(info,vo);
	}
	public int insert(ContextInfo info, SpsBatchChangeRatio vo ){
		return spsBatchChangeRatioDao.insert(info,vo);
	}
	public int remove(ContextInfo info, SpsBatchChangeRatio vo ){
		return spsBatchChangeRatioDao.remove(info,vo);
	}
	public int update(ContextInfo info, SpsBatchChangeRatio vo ){
		return spsBatchChangeRatioDao.update(info,vo);
	}
	public PageModel findPage(PageInfo info, SpsBatchChangeRatio vo){
		
		PageModel pm = new PageModel(info);
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		Integer total = spsBatchChangeRatioDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsBatchChangeRatio> datas = spsBatchChangeRatioDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsBatchChangeRatio> findAll(SpsBatchChangeRatio vo){
		
		List<SpsBatchChangeRatio> datas = spsBatchChangeRatioDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/11/15 16:30:31

	/**
	 * 业务类分页查询
	 *  @param   info, vo]
	 * @return    : com.xfs.common.page.PageModel
	 *  @createDate   : 11:32 2016/11/17
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 11:32 2016/11/17
	 *  @updateAuthor  :
	 */
	@Override
	public PageModel findVoPage(ContextInfo cti,PageInfo info, SpsBatchChangeRatio vo){
		PageModel pm = new PageModel(info);
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		// 获取数据权限
		vo.setAuthority(sysFunctionDataService.getDataAuthorityByUser(cti.getUserId(), cti.getUserType(), Constant.DATATYPE));
		Integer total = spsBatchChangeRatioDao.countFreeFindVo(vo);
		pm.setTotal(total);
		List<SpsBatchChangeRatioVo> datas = spsBatchChangeRatioDao.freeFindVo(vo, pageIndex, pageSize," bcr.create_dt desc");
		ratioNameConvert(datas);
		pm.setDatas(datas);
		return pm;

	}
	/**
	 * 转换比例快照json
	 *  @param   datas
	 * @return    : void
	 *  @createDate   : 11:53 2016/11/17
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 11:53 2016/11/17
	 *  @updateAuthor  :
	 */
	private void ratioNameConvert(List<SpsBatchChangeRatioVo> datas){
		if(CollectionUtils.isNotEmpty(datas)){
			for(SpsBatchChangeRatioVo vo:datas){
				if(StringUtils.isNotBlank(vo.getRatioJson())){
					TypeReference<Map<String,Map<String,String>>> ref = new TypeReference<Map<String,Map<String,String>>>(){};
					Map<String,Map<String,String>> curMap = JSON.parseObject(vo.getRatioJson(),ref);
					String corpRatio = curMap.get("old") != null ? curMap.get("old").get("corp_ratio") : null;
					if(StringUtils.isNotBlank(corpRatio)){
						BigDecimal bd = new BigDecimal(corpRatio);
						curMap.get("old").put("corp_ratio",NumberUtil.div(NumberUtil.mul(bd,new BigDecimal(100)),new BigDecimal(1),4).toString());
					}
					String psnRatio = curMap.get("old") != null ? curMap.get("old").get("psn_ratio") : null;
					if(StringUtils.isNotBlank(psnRatio)){
						BigDecimal bd = new BigDecimal(psnRatio);
						curMap.get("old").put("psn_ratio",NumberUtil.div(NumberUtil.mul(bd,new BigDecimal(100)),new BigDecimal(1),4).toString());
					}
					String newcorpRatio = curMap.get("new") != null ? curMap.get("new").get("corp_ratio") : null;
					if(StringUtils.isNotBlank(newcorpRatio)){
						BigDecimal bd = new BigDecimal(newcorpRatio);
						curMap.get("new").put("corp_ratio",NumberUtil.div(NumberUtil.mul(bd,new BigDecimal(100)),new BigDecimal(1),4).toString());
					}
					String newpsnRatio = curMap.get("new") != null ? curMap.get("new").get("psn_ratio") : null;
					if(StringUtils.isNotBlank(newpsnRatio)){
						BigDecimal bd = new BigDecimal(newpsnRatio);
						curMap.get("new").put("psn_ratio",NumberUtil.div(NumberUtil.mul(bd,new BigDecimal(100)),new BigDecimal(1),4).toString());
					}
					vo.setOldRatioMap(curMap.get("old"));
					vo.setNewRatioMap(curMap.get("new"));
				}
			}
		}
	}
	/**
	 * 处理批量更新人员比例
	 *  @param   ratioEmpQuery 查询条件, user 登陆用户
	 * @return    : com.xfs.common.Result
	 *  @createDate   : 16:53 2016/11/16
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 16:53 2016/11/16
	 *  @updateAuthor  :
	 */
	@Override
	public Result handlleBatchRatio(ContextInfo info,BatchRatioEmpQuery ratioEmpQuery){

		Result result = new Result();
		//比例快照
		Map<String,Object> ratioJson = new HashMap<>();
		Map<String,Object> query = new HashMap<>();
		query.put("areaId",ratioEmpQuery.getAreaId());
		query.put("insuranceId",ratioEmpQuery.getInsuranceId());
		query.put("livingType",ratioEmpQuery.getInsuranceIivingType());
		query.put("ratioId",ratioEmpQuery.getRatioId());
		if(ratioEmpQuery.getRatioId() != null){
			List<Map<String,Object>> oldRatio = bsArearatioDao.findRatioByBatchQuery(query);
			if(CollectionUtils.isEmpty(oldRatio)){
				result.setSuccess(false).setError("未找到原比例");
				return result;
			}
			ratioJson.put("old",oldRatio.get(0));
		}
		query.put("ratioId",ratioEmpQuery.getNewRatioId());
		query.put("beginMonth",ratioEmpQuery.getBeginPeriod());
		if(ratioEmpQuery.getNewRatioId() != null){
			List<Map<String,Object>> newRatio = bsArearatioDao.findRatioByBatchQuery(query);
			if(CollectionUtils.isEmpty(newRatio)){
				result.setSuccess(false).setError("未找到原比例");
				return result;
			}
			ratioJson.put("new",newRatio.get(0));
		}else {
			result.setSuccess(false).setError("请选择新比例");
			return result;
		}

        //取比例险种类型
        if(ratioEmpQuery.getRatioId() != null){
            ratioEmpQuery.setInsuranceType(bsArearatioDao.findRatioInsuranceType(ratioEmpQuery.getRatioId()));
        }else{//无原比例取险种
            if(ratioEmpQuery.getInsuranceId() != null){
                BdInsurance insurance = new BdInsurance();
                insurance.setInsuranceId(ratioEmpQuery.getInsuranceId());
                insurance = bdInsuranceService.findByPK(insurance);
                if(insurance != null){
                    ratioEmpQuery.setInsuranceType(insurance.getInsuranceFundType());
                }
            }
        }
        if(StringUtils.isNotBlank(ratioEmpQuery.getChk_emp_ids())){
        	String tes = ratioEmpQuery.getChk_emp_ids();
        	tes  = tes.substring(0,tes.length()-1);
        	ratioEmpQuery.setChk_emp_ids(tes);
        }
        String authority = sysFunctionDataService.getDataAuthorityByUser(info.getUserId(), info.getUserType(), Constant.DATATYPE);
        ratioEmpQuery.setAuthority(authority);
		List<Map<String,Object>> datas = cmEmployeeService.findBatchRatioEmp(ratioEmpQuery);

		if(CollectionUtils.isEmpty(datas)){
			result.setSuccess(false).setError("未查询到可替换员工");
			return result;
		}
		// 调用切面 执行算费
		for(Map<String,Object> emp:datas){
			SpsTask spstask = new SpsTask();
			spstask.setEmpId((Integer)emp.get("emp_id"));
			spstask.setSpId(info.getOrgId());
			if("INSURANCE".equals(ratioEmpQuery.getInsuranceType())){
				spstask.setBstypeId(33);
			}else {
				spstask.setBstypeId(34);
			}
			spstask.setSchemeId((Integer)emp.get("scheme_id"));
			emp.put("oldRatioId",ratioEmpQuery.getRatioId());
			emp.put("newRatioId",ratioEmpQuery.getNewRatioId());
			emp.put("beginMonth",ratioEmpQuery.getBeginPeriod());
			emp.put("insuranceType",ratioEmpQuery.getInsuranceType());
			emp.put("insuranceId",ratioEmpQuery.getInsuranceId());
			spstask.setJson(JSON.toJSONString(emp));
			taskAspectService.saveTask(info, spstask, null, result);
			if (!result.isSuccess()) {
                BusinessException e = new BusinessException(result.getError());
                log.error("批量操作员工比例执行任务单失败，spstask:"+JSON.toJSONString(spstask),e);
				throw e;
			}
		}
		//保存批量操作记录
		SpsBatchChangeRatio spsBatchChangeRatio = new SpsBatchChangeRatio();
		spsBatchChangeRatio.setAreaId(ratioEmpQuery.getAreaId());
		spsBatchChangeRatio.setSpId(ratioEmpQuery.getSpId());
		spsBatchChangeRatio.setInsuranceId(ratioEmpQuery.getInsuranceId());
		spsBatchChangeRatio.setOldRatioId(ratioEmpQuery.getRatioId());
		spsBatchChangeRatio.setNewRatioId(ratioEmpQuery.getNewRatioId());
		spsBatchChangeRatio.setBeginMonth(ratioEmpQuery.getBeginPeriod());
		spsBatchChangeRatio.setEmpJson(JSON.toJSONString(datas));
		spsBatchChangeRatio.setRatioJson(JSON.toJSONString(ratioJson));
		spsBatchChangeRatio.setChangeCount(datas.size());
		spsBatchChangeRatio.setCreateBy(info.getUserId());
		spsBatchChangeRatio.setCreateDt(new Date());
		if(spsBatchChangeRatioDao.save(info,spsBatchChangeRatio) < 1){
            BusinessException e = new BusinessException("操作失败");
            log.error("批量操作员工比例失败，spsBatchChangeRatio:"+JSON.toJSONString(spsBatchChangeRatio),e);
            throw e;
		}
		return result;
	}
	/**
	 * 通过批处理id查询处理人员信息
	 *  @param   bcrId 批次id
	 * @return    : java.util.List<java.util.Map<java.lang.String,java.lang.String>>
	 *  @createDate   : 15:16 2016/11/17
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 15:16 2016/11/17
	 *  @updateAuthor  :
	 */
	@Override
	public List<Map<String,String>> findChangeEmplDetail(Integer bcrId){

		SpsBatchChangeRatio query = new SpsBatchChangeRatio();
		query.setId(bcrId);
		query = spsBatchChangeRatioDao.findByPK(query);
		if(query != null && StringUtils.isNotBlank(query.getEmpJson())){
			TypeReference<List<Map<String,String>>> ref = new TypeReference<List<Map<String,String>>>(){};
			return JSON.parseObject(query.getEmpJson(),ref);
		}
		return null;
	}
}
