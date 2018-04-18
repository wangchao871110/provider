package com.xfs.bs.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.base.dao.BsSaasfeestandardDao;
import com.xfs.base.model.BsSaasfeestandard;
import com.xfs.bs.service.BsSaasfeestandardService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.sp.dto.BsSaasfeestandardDto;
import com.xfs.common.util.NumberUtil;

@Service
public class BsSaasfeestandardServiceImpl implements BsSaasfeestandardService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BsSaasfeestandardServiceImpl.class);
	
	@Autowired
	private BsSaasfeestandardDao bsSaasfeestandardDao;
	
	public int save(ContextInfo cti, BsSaasfeestandard vo ){
		return bsSaasfeestandardDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BsSaasfeestandard vo ){
		return bsSaasfeestandardDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BsSaasfeestandard vo ){
		return bsSaasfeestandardDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BsSaasfeestandard vo ){
		return bsSaasfeestandardDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BsSaasfeestandard vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bsSaasfeestandardDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BsSaasfeestandard> datas = bsSaasfeestandardDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BsSaasfeestandard> findAll(BsSaasfeestandard vo){
		
		List<BsSaasfeestandard> datas = bsSaasfeestandardDao.freeFind(vo);
		return datas;
		
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/15 19:00:17
	
	@Override
	public BsSaasfeestandard findReckonStandardFee(BsSaasfeestandard obj) {
		return bsSaasfeestandardDao.findReckonStandardFee(obj);
	}	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/14 14:00:13
	@Override
	public List<BsSaasfeestandardDto> queryDtoList(){
		List<BsSaasfeestandard> list = bsSaasfeestandardDao.freeFind(new BsSaasfeestandard());
		return convertToList(list);
	}

	/**
	 * 实体类转换业务类
	 * @param list
	 * @return
     */
	private List<BsSaasfeestandardDto> convertToList(List<BsSaasfeestandard> list){
		List<BsSaasfeestandardDto> dtoList = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(list)){
			for(BsSaasfeestandard saas: list){
				BsSaasfeestandardDto dto = new BsSaasfeestandardDto();
				BeanUtils.copyProperties(saas,dto);
				if(saas.getQuarterFee() != null && saas.getQuarterFee().doubleValue() > 0){
					if(saas.getHalfFee() != null){
						if(saas.getHalfFee().doubleValue() > 0){
							//半年折扣率 = 半年费/(季费*2)*100；
							BigDecimal halfRate = NumberUtil.mul(NumberUtil.div(saas.getHalfFee(),NumberUtil.mul(saas.getQuarterFee(),new BigDecimal(2)),4),new BigDecimal(100));
							dto.setHalfDiscountRate(halfRate);
						}else{
							dto.setHalfDiscountRate(BigDecimal.ZERO);
						}
					}
					if(saas.getYearFee() != null){
						if(saas.getYearFee().doubleValue() > 0){
							//年折扣率 = 年费/(季费*4)*100；
							BigDecimal yearRate = NumberUtil.mul(NumberUtil.div(saas.getYearFee(),NumberUtil.mul(saas.getQuarterFee(),new BigDecimal(4)),4),new BigDecimal(100));
							dto.setYearDiscountRate(yearRate);
						}else{
							dto.setYearDiscountRate(BigDecimal.ZERO);
						}
					}
					if(saas.getThreeFee() != null){
						if(saas.getThreeFee().doubleValue() > 0){
							//3年折扣率 = 年费/(季费*12)*100；
							BigDecimal threeRate = NumberUtil.mul(NumberUtil.div(saas.getThreeFee(),NumberUtil.mul(saas.getQuarterFee(),new BigDecimal(12)),4),new BigDecimal(100));
							dto.setThreeDiscountRate(threeRate);
						}else{
							dto.setThreeDiscountRate(BigDecimal.ZERO);
						}
					}
				}
				dtoList.add(dto);
			}
		}
		return dtoList;
	}
	
}
