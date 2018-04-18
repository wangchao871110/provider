package com.xfs.cp.service.impl;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.base.model.BsArea;
import com.xfs.base.service.BsAreaService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.common.util.StringUtils;
import com.xfs.cp.dao.CpPackageDao;
import com.xfs.cp.dao.CpQuotedPriceDao;
import com.xfs.cp.model.CpPackage;
import com.xfs.cp.model.CpQuotedPrice;
import com.xfs.cp.service.CpPackageService;

@Service
public class CpPackageServiceImpl implements CpPackageService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(CpPackageServiceImpl.class);
	
	@Autowired
	private CpPackageDao cpPackageDao;
	@Autowired
	private CpQuotedPriceDao cpQuotedPriceDao;
	@Autowired
	private BsAreaService bsAreaService;

	public int save(ContextInfo cti, CpPackage vo ){
		return cpPackageDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  CpPackage vo ){
		return cpPackageDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  CpPackage vo ){
		return cpPackageDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  CpPackage vo ){
		return cpPackageDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, CpPackage vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cpPackageDao.countFreeFind(vo);
		pm.setTotal(total);
		List<CpPackage> datas = cpPackageDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<CpPackage> findAll(CpPackage vo){
		
		List<CpPackage> datas = cpPackageDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 10:55:22
	public PageModel getASpListPage(PageInfo pi,CpPackage vo){
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = 20;
		Integer total = cpPackageDao.findASpListCount(vo);
		pm.setTotal(total);
		List<CpPackage> datas = cpPackageDao.findASpList(vo, pageIndex, pageSize);
		for(CpPackage c: datas) {
			if(!StringUtils.isBlank(c.getCityName())){
				String[] arr = c.getCityName().split(",");
				String name = "";
				for(int i=0; i<arr.length; i++){
					if(name.equals("")){
						name+= arr[i].split("-").length<2?arr[i].split("-")[0]:arr[i].split("-")[1];
					}else{
						name+= ","+(arr[i].split("-").length<2?arr[i].split("-")[0]:arr[i].split("-")[1]);
					}
				}
				c.setCityName(name);
			}
			CpQuotedPrice price = new CpQuotedPrice();
			price.setPackageId(c.getId());
			price.setSendSpid(c.getSpId());
		    price.setDr(0);
			int count = cpQuotedPriceDao.findPageListCount(price);
			c.setQuotedCount(count);
			price.setStatus(1);
			Map<String, Object> quotedSp = cpQuotedPriceDao.findQuotedSp(price);
			if(quotedSp!=null){
				c.setQuotedSpName(quotedSp.get("name").toString());
			}
			c.setProjectCount(c.getProject().split(",").length);
			Date currentDate = new Date();
			if (c.getBeginTime() != null && c.getEndTime() != null) {
				if (c.getStatus().intValue() == 0) {
					c.setTimeDisplay("未开始");
				}
				if(c.getStatus().intValue() == 1){
					if (currentDate.getTime() > c.getEndTime().getTime()) {
						c.setTimeDisplay("已结束");
					} else {
						long l = c.getEndTime().getTime() - currentDate.getTime();
						long day = l / 86400000;
						long hour = l % 86400000 / 3600000;
						long minute = l % 86400000 % 3600000 / 60000;
						c.setTimeDisplay(day + "天" + hour + "时" + minute + "分钟");
					}
				}
			}
			c.setBeginTime(new Date());
		}
		pm.setDatas(datas);
		return pm;
	}

	public CpPackage findById(CpPackage obj) {
		return cpPackageDao.findByPK(obj);
	}
	
	/**
	 * 获取包列表
	 */
	@Override
	public PageModel findPackagePage(PageInfo pi,CpPackage cpPackage, int pageIndex, int pageSize) {
		PageModel pm = new PageModel(pi);
		try {
			// 1天内
			if(0 == cpPackage.getDateSta() && 1 == cpPackage.getDateEnd()){
				cpPackage.setDateEnd(1*24);
			}
			// 1到2天
			else if(1 == cpPackage.getDateSta() && 2 == cpPackage.getDateEnd()){
				cpPackage.setDateSta(1*24);
				cpPackage.setDateEnd(2*24);
			}
			// 2到3天
			else if(2 == cpPackage.getDateSta() && 3 == cpPackage.getDateEnd()){
				cpPackage.setDateSta(2*24);
				cpPackage.setDateEnd(3*24);
			}
			// 3到5天
			else if(3 == cpPackage.getDateSta() && 5 == cpPackage.getDateEnd()){
				cpPackage.setDateSta(3*24);
				cpPackage.setDateEnd(5*24);	
			}
			// 5天以上
			else if(5 == cpPackage.getDateSta()){
				cpPackage.setDateSta(5*24);
			}
			if(null == cpPackage.getOrderBy()){
				cpPackage.setOrderBy("create_dt desc");
			}
			Integer total = cpPackageDao.findPackagePageCount(cpPackage);
			pm.setPagesize(pageSize);
			pm.setTotal(total);
			List<CpPackage> datas = cpPackageDao.findPackagePage(cpPackage, pageIndex, pageSize);
			for (int i = 0; i < datas.size(); i++) {
				datas.get(i).setBeginTime(new Date());
			}
			pm.setDatas(datas);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pm;
	}
	@Override
	public List<CpPackage> findIndexList(CpPackage cpPackage, int pageIndex, int pageSize) {
		List<CpPackage> datas = cpPackageDao.freeFind(cpPackage, pageIndex, pageSize, "price");
		return datas;
	}
	
	/**
	 * 获取包列表
	 */
	@Override
	public PageModel findPackagePage(PageInfo pi,CpPackage cpPackage) {
		PageModel pm = new PageModel(pi);
		try {
			if(null != cpPackage.getDateSta() && null != cpPackage.getDateEnd()){
				// 1天内
				if(0 == cpPackage.getDateSta() && 1 == cpPackage.getDateEnd()){
					cpPackage.setDateEnd(1*24);
				}
				// 1到2天
				else if(1 == cpPackage.getDateSta() && 2 == cpPackage.getDateEnd()){
					cpPackage.setDateSta(1*24);
					cpPackage.setDateEnd(2*24);
				}
				// 2到3天
				else if(2 == cpPackage.getDateSta() && 3 == cpPackage.getDateEnd()){
					cpPackage.setDateSta(2*24);
					cpPackage.setDateEnd(3*24);
				}
				// 3到5天
				else if(3 == cpPackage.getDateSta() && 5 == cpPackage.getDateEnd()){
					cpPackage.setDateSta(3*24);
					cpPackage.setDateEnd(5*24);	
				}
				// 5天以上
				else if(5 == cpPackage.getDateSta()){
					cpPackage.setDateSta(5*24);
				}
			}
			// 区域拼音
			if(null != cpPackage.getAreaPinyin() && !"0".equals(cpPackage.getAreaPinyin()) && !"".equals(cpPackage.getAreaPinyin())){
				BsArea bsArea = bsAreaService.getAreaByPinyin(cpPackage.getAreaPinyin());
				cpPackage.setCity(bsArea.getAreacode().toString());
			}
			Integer total = cpPackageDao.findPackagePageCount(cpPackage);
			int pageIndex = pi.getOffset();
			int pageSize = pi.getPagesize();
			pm.setTotal(total);
			if(null == cpPackage.getOrderBy()){
				cpPackage.setOrderBy("create_dt desc");
			}
			List<CpPackage> datas = cpPackageDao.findPackagePage(cpPackage, pageIndex, pageSize);
			for (int i = 0; i < datas.size(); i++) {
				datas.get(i).setBeginTime(new Date());
			}
			pm.setDatas(datas);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pm;
	}

    /**
     * 查询我的发包中被更多人抢包需要查看的发包条数
     * @autor luyong
     * @since 16-10-20 create
     * @param vo
     * @return
     */
    public Integer getASpNoReadCount(CpPackage vo){
        return cpPackageDao.findASpListCount(vo);
    }
    @Override
	public PageModel queryPackagePage(PageInfo pi,CpPackage cpPackage) {
		PageModel pm = new PageModel(pi);
		Integer pageIndex = pi.getOffset();
		Integer pageSize = pi.getPagesize();
		Integer total = cpPackageDao.queryPackagePageCount(cpPackage);
		pm.setTotal(total);
		List<Map<String, Object>> datas = cpPackageDao.queryPackagePage(cpPackage, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}
	@Override
	public int findPackageNumber(Integer id,Date createDtFrom,Date createDtTo) {
		CpPackage cpPackage = new CpPackage();
		cpPackage.setSpId(id);
		cpPackage.setCreateDtFrom(createDtFrom);
		cpPackage.setCreateDtTo(createDtTo);
		return cpPackageDao.findPackageNumber(cpPackage);
	}
	@Override
	public CpPackage findByPackageNum(CpPackage obj) {
		return cpPackageDao.findByPackageNum(obj);
	}
	@Override
	public int updateByIdAndSpId(ContextInfo sysUser, CpPackage cpPackage) {
		return cpPackageDao.updateByIdAndSpId(sysUser,cpPackage);
	}
	@Override
	public boolean updateByIdAndSpIdOfQuoted(ContextInfo sysUser, Integer packageId) {
		boolean b=true;
		CpPackage cpPackage=new CpPackage();
		cpPackage.setId(packageId);
		cpPackage.setSpId(sysUser.getOrgId());
		if(cpPackageDao.updateByIdAndSpId(sysUser,cpPackage)>0){
			CpQuotedPrice cpQuotedPrice=new CpQuotedPrice();
			cpQuotedPrice.setSendSpid(sysUser.getOrgId());
			cpQuotedPrice.setPackageId(packageId);
			cpQuotedPriceDao.updateByIdAndSpId(sysUser,cpQuotedPrice);
		}else{
			b=false;
		}
		return b;
	}
	@Override
	public int delNPul(ContextInfo sysUser, CpPackage cpPackage) {
		return cpPackageDao.delNPul(sysUser,cpPackage);
	}

}
