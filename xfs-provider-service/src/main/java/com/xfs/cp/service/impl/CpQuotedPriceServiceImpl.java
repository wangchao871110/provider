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
import com.xfs.cp.dao.CpQuotedPriceDao;
import com.xfs.cp.model.CpQuotedPrice;
import com.xfs.cp.model.CpRelation;
import com.xfs.cp.service.CpQuotedPriceService;
import com.xfs.cp.service.CpRelationService;

@Service
public class CpQuotedPriceServiceImpl implements CpQuotedPriceService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(CpQuotedPriceServiceImpl.class);
	
	@Autowired
	private CpQuotedPriceDao cpQuotedPriceDao;
	@Autowired
	private CpRelationService cpRelationService;

	public int save(ContextInfo cti, CpQuotedPrice vo ){
		return cpQuotedPriceDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  CpQuotedPrice vo ){
		return cpQuotedPriceDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  CpQuotedPrice vo ){
		return cpQuotedPriceDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  CpQuotedPrice vo ){
		return cpQuotedPriceDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, CpQuotedPrice vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cpQuotedPriceDao.countFreeFind(vo);
		pm.setTotal(total);
		List<CpQuotedPrice> datas = cpQuotedPriceDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<CpQuotedPrice> findAll(CpQuotedPrice vo){
		
		List<CpQuotedPrice> datas = cpQuotedPriceDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 19:24:41

	public PageModel findPageList(PageInfo pi,CpQuotedPrice obj) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = 10;
		Integer total = cpQuotedPriceDao.findPageListCount(obj);
		pm.setTotal(total);
		List<CpQuotedPrice> datas = cpQuotedPriceDao.findPageList(obj, pageIndex, pageSize);
		for(CpQuotedPrice c : datas){
			c.setProjectCount(c.getProject().split(",").length);
			if(c.getStatus().intValue()==0||c.getStatus().intValue()==1){
				CpRelation relVo = new CpRelation();
				relVo.setASpId(c.getSendSpid());
				relVo.setBSpId(c.getQuotedSpid());
				relVo.setDr(0);
				List<CpRelation>  cpRelations = cpRelationService.findAll(relVo);
				if(cpRelations.size()>0){
					c.setRelationId(cpRelations.get(0).getId());
				}
				Date currentDate = new Date();
				if (c.getBeginTime() != null && c.getEndTime() != null) {
					if (c.getPackageStatus().intValue() == 0) {
						c.setTimeDisplay("未开始");
					}
					if(c.getPackageStatus().intValue() == 1){
						if (currentDate.getTime() > c.getEndTime().getTime()){
							c.setTimeDisplay("已结束");
						}else{
							long l = c.getEndTime().getTime() -  currentDate.getTime();
							long day = l/86400000;
							long hour = l%86400000/3600000;
							long minute = l%86400000%3600000/60000;
							c.setTimeDisplay(day+"天"+hour+"时"+minute+"分钟");
						}
					}
				}
			}

		}
		pm.setDatas(datas);
		return pm;
	}

	public int findPageListCount(CpQuotedPrice obj) {
		Integer total = cpQuotedPriceDao.findPageListCount(obj);
		return total.intValue();
	}

	public int updateStatus(ContextInfo ci,CpQuotedPrice obj) {
		return cpQuotedPriceDao.updateStatus(ci,obj);
	}
	public int updateIsRead(ContextInfo ci,CpQuotedPrice obj) {
		return cpQuotedPriceDao.updateIsRead(ci,obj);
	}
	@Override
	public Result saveQuoted(ContextInfo user, CpQuotedPrice cpQuotedPrice, Result result) {
		if (user != null) {
			if(cpQuotedPrice.getSendSpid().equals(user.getOrgId())){
				result.setMsg("发包单位和抢包单位是一个单位，不能抢包！");
			}else{
				CpQuotedPrice vo = new CpQuotedPrice();
				vo.setQuotedSpid(user.getOrgId());
				vo.setPackageId(cpQuotedPrice.getPackageId());
				//vo.setStatus(0);
				vo.setDr(0);
				List<CpQuotedPrice> list = cpQuotedPriceDao.freeFind(vo);
				if(list.size() > 0){
					result.setMsg("您已经报价，不能重复报价！");
				}else{
					vo.setStatus(3);
					list = cpQuotedPriceDao.freeFind(vo);
					if(list.size() > 0){
						result.setMsg("您已经被拒绝，不能在报价！");
					}else{
						cpQuotedPrice.setQuotedSpid(user.getOrgId());
						cpQuotedPrice.setStatus(0);
						cpQuotedPrice.setQuotedTime(new Date());
						cpQuotedPrice.setCreateDt(new Date());
						cpQuotedPrice.setCreateBy(user.getUserId());
						if(cpQuotedPriceDao.save(user,cpQuotedPrice) > 0){
							result.setSuccess(true);
							result.setMsg("报价成功！");
						}else{
							result.setMsg("报价失败！");
						}
					}
				}
			}
		}else{
			result.setMsg("你还没有登录系统，请先登录系统在进行报价！");
		}
		return result;
	}
	@Override
	public List<Map<String, Object>> findQuotedPriceCp(int packageId) {
		return cpQuotedPriceDao.findQuotedPriceCp(packageId);
	}

	@Override
	public Map<String, Object> getQuotedPriceCpInfo(Map<String, Object> whereMap) {
		return cpQuotedPriceDao.getQuotedPriceCpInfo(whereMap);
	}


	@Override
	public PageModel findQuotedPrice(PageInfo pi,int packageId) {
		PageModel pm = new PageModel(pi);
		Integer pageIndex = pi.getOffset();
		Integer pageSize = pi.getPagesize();
		Integer total = cpQuotedPriceDao.findQuotedPriceCpCount(packageId);
		pm.setTotal(total);
		List<Map<String, Object>> datas = cpQuotedPriceDao.findQuotedPriceCp(packageId);
		pm.setDatas(datas);
		return pm;
	}
	@Override
	public int findPackageNumber(CpQuotedPrice cpQuotedPrice) {
		return cpQuotedPriceDao.findPackageNumber(cpQuotedPrice);
	}
}
