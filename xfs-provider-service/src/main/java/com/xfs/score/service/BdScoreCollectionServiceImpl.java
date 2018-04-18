package com.xfs.score.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.score.dao.BdScoreCollectionDao;
import com.xfs.score.model.BdScoreCollection;


@Service
public class BdScoreCollectionServiceImpl implements BdScoreCollectionService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BdScoreCollectionServiceImpl.class);
	
	@Autowired
	private BdScoreCollectionDao bdScoreCollectionDao;
	
	public int save(ContextInfo cti, BdScoreCollection vo ){
		return bdScoreCollectionDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BdScoreCollection vo ){
		return bdScoreCollectionDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BdScoreCollection vo ){
		return bdScoreCollectionDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BdScoreCollection vo ){
		return bdScoreCollectionDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BdScoreCollection vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bdScoreCollectionDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BdScoreCollection> datas = bdScoreCollectionDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BdScoreCollection> findAll(BdScoreCollection vo){
		
		List<BdScoreCollection> datas = bdScoreCollectionDao.freeFind(vo);
		return datas;
		
	}


	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/21 11:40:19


	@Override
	public void collectionScore(ContextInfo cti, String calc_version,String type) {
		//计算总值
		List<Map<String,Object>> bsddList = bdScoreCollectionDao.getBdScoreCollectionList(calc_version,type);
		String scoreTargetId = "0";
		String scoreTargetType = "0";
		BigDecimal total = new BigDecimal(0);
		BdScoreCollection bcVo = new BdScoreCollection();
		for(Map<String,Object> vo: bsddList) {
			BigDecimal score = new BigDecimal(0);
			if (scoreTargetId.equals("0") || (scoreTargetId.equals(vo.get("score_target_id")+"")&&scoreTargetType.equals(vo.get("score_target_type")+""))) {
				scoreTargetId = vo.get("score_target_id")+"";
				scoreTargetType = vo.get("score_target_type")+"";
				score = new BigDecimal(vo.get("score")+"");
				score = score.multiply(new BigDecimal(vo.get("weight")+""));
				total = total.add(score);
				bcVo.setScoreTargetId(Integer.parseInt(vo.get("score_target_id")+""));
				bcVo.setScoreTargetType(vo.get("score_target_type")+"");
			}else {
				saveScoreCollection(cti, bcVo,total,calc_version);
				bcVo = new BdScoreCollection();
				total = new BigDecimal(0);
				scoreTargetId = vo.get("score_target_id")+"";
				scoreTargetType = vo.get("score_target_type")+"";
				score = new BigDecimal(vo.get("score")+"");
				score = score.multiply(new BigDecimal(vo.get("weight")+""));
				total = total.add(score);
				bcVo.setScoreTargetId(Integer.parseInt(vo.get("score_target_id")+""));
				bcVo.setScoreTargetType(vo.get("score_target_type")+"");
			}
		}
		saveScoreCollection(cti, bcVo,total,calc_version);
	}



	public void saveScoreCollection(ContextInfo cti, BdScoreCollection bdVo,BigDecimal total,String calc_version ){
		BdScoreCollection obj = bdScoreCollectionDao.findBdScoreCollection(bdVo);
		if(obj==null){
			bdVo.setCalVersion(calc_version);
			bdVo.setCreateDt(new Date());
			bdVo.setScore( total.divide(new BigDecimal(1),1,BigDecimal.ROUND_HALF_UP)); //保留小数点后一位
			bdScoreCollectionDao.insert(cti,bdVo);
		}else {
			obj.setCalVersion(calc_version);
			obj.setModifyDt(new Date());
			obj.setScore( total.divide(new BigDecimal(1),1,BigDecimal.ROUND_HALF_UP)); //保留小数点后一位
			bdScoreCollectionDao.update(cti, obj);
		}
	}
}
