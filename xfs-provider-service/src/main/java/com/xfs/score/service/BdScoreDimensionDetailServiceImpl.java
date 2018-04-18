package com.xfs.score.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.common.util.StringUtils;
import com.xfs.score.dao.BdScoreDimensionDetailDao;
import com.xfs.score.model.BdScoreDimensionDetail;


@Service
public class BdScoreDimensionDetailServiceImpl implements BdScoreDimensionDetailService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BdScoreDimensionDetailServiceImpl.class);
	
	@Autowired
	private BdScoreDimensionDetailDao bdScoreDimensionDetailDao;
	
	public int save(ContextInfo cti, BdScoreDimensionDetail vo ){
		return bdScoreDimensionDetailDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BdScoreDimensionDetail vo ){
		return bdScoreDimensionDetailDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BdScoreDimensionDetail vo ){
		return bdScoreDimensionDetailDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BdScoreDimensionDetail vo ){
		return bdScoreDimensionDetailDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BdScoreDimensionDetail vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bdScoreDimensionDetailDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BdScoreDimensionDetail> datas = bdScoreDimensionDetailDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BdScoreDimensionDetail> findAll(BdScoreDimensionDetail vo){
		
		List<BdScoreDimensionDetail> datas = bdScoreDimensionDetailDao.freeFind(vo);
		return datas;
		
	}


	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/21 11:40:20

	@Override
	public String queryCurrCalcVersion(String type) {
		//使用当前日期
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();
		String curr_version = bdScoreDimensionDetailDao.queryCurrVersion(type);
		if(StringUtils.isBlank(curr_version)){
			curr_version = format.format(calendar.getTime())+"0001";
		}else{
			String cal_versionTime = format.format(calendar.getTime());
			if(!curr_version.contains(cal_versionTime)){
				curr_version = format.format(calendar.getTime())+"0001";
			}else {
				curr_version = String.valueOf(Long.parseLong(curr_version) + 1);
			}
		}
		return  curr_version;
	}

}
