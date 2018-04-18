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
import com.xfs.common.util.StringUtils;
import com.xfs.score.dao.BdScoreDimensionDetailDao;
import com.xfs.score.dao.BdScoreItemDao;
import com.xfs.score.dao.BdScoreItemDetailDao;
import com.xfs.score.model.BdScoreItemDetail;


@Service
public class BdScoreItemDetailServiceImpl implements BdScoreItemDetailService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BdScoreItemDetailServiceImpl.class);
	
	@Autowired
	private BdScoreItemDetailDao bdScoreItemDetailDao;

	@Autowired
	private BdScoreItemDao bdScoreItemDao;

    @Autowired BdScoreDimensionDetailDao bdScoreDimensionDetailDao;
	
	public int save(ContextInfo cti, BdScoreItemDetail vo ){
		return bdScoreItemDetailDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BdScoreItemDetail vo ){
		return bdScoreItemDetailDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BdScoreItemDetail vo ){
		return bdScoreItemDetailDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BdScoreItemDetail vo ){
		return bdScoreItemDetailDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BdScoreItemDetail vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bdScoreItemDetailDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BdScoreItemDetail> datas = bdScoreItemDetailDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BdScoreItemDetail> findAll(BdScoreItemDetail vo){
		
		List<BdScoreItemDetail> datas = bdScoreItemDetailDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/21 11:40:20


	public String insertBdScoreItemDetail(ContextInfo cti, String calc_version,String type){

        //计算总值
        List<Map<String,Object>> bsddList = bdScoreDimensionDetailDao.getBdScoreDimensionDetailList(calc_version,type);
        String itemId = "0";
        String scoreTargetId = "0";
        String scoreTargetType = "0";

        BigDecimal total = new BigDecimal(0);
        BdScoreItemDetail bsidVo = new BdScoreItemDetail();
        for(Map<String,Object> vo: bsddList) {
            BigDecimal score = new BigDecimal(0);
            if (itemId.equals("0") || (itemId.equals(vo.get("item_id")+"")&&scoreTargetId.equals(vo.get("score_target_id")+"")&&scoreTargetType.equals(vo.get("score_target_type")+""))) {
                itemId = vo.get("item_id")+"";
                scoreTargetId = vo.get("score_target_id")+"";
                scoreTargetType = vo.get("score_target_type")+"";
                score = getScore(vo.get("score")+"",vo.get("dimWeight")+"",vo.get("dimDefaultScore")+"");
                total = total.add(score);
                bsidVo.setCalVersion(calc_version);
                bsidVo.setCreateDt(new Date());
                bsidVo.setItemId(Integer.parseInt(vo.get("item_id")+""));
                bsidVo.setItemName(vo.get("item_name")+"");
                bsidVo.setScore( total.divide(new BigDecimal(1),3,BigDecimal.ROUND_HALF_UP)); //保留小数点后一位
                bsidVo.setScoreTargetId(Integer.parseInt(vo.get("score_target_id")+""));
                bsidVo.setScoreTargetType(vo.get("score_target_type")+"");
                bsidVo.setWeight(new BigDecimal(vo.get("itemWeight")+""));
            }else {
                System.out.println("==========="+bsidVo.getItemId() + "===" + bsidVo.getScore() + "===" +bsidVo.getScoreTargetId());
                bdScoreItemDetailDao.insert(cti, bsidVo);
                bsidVo = new BdScoreItemDetail();
                total = new BigDecimal(0);
                itemId = vo.get("item_id")+"";
                scoreTargetId = vo.get("score_target_id")+"";
                scoreTargetType = vo.get("score_target_type")+"";
                score = getScore(vo.get("score")+"",vo.get("dimWeight")+"",vo.get("dimDefaultScore")+"");
                total = total.add(score);
                bsidVo.setCalVersion(calc_version);
                bsidVo.setCreateDt(new Date());
                bsidVo.setItemId(Integer.parseInt(vo.get("item_id")+""));
                bsidVo.setItemName(vo.get("item_name")+"");
                bsidVo.setScore( total.divide(new BigDecimal(1),3,BigDecimal.ROUND_HALF_UP)); //保留小数点后一位
                bsidVo.setScoreTargetId(Integer.parseInt(vo.get("score_target_id")+""));
                bsidVo.setScoreTargetType(vo.get("score_target_type")+"");
                bsidVo.setWeight(new BigDecimal(vo.get("itemWeight")+""));
            }
        }
        bdScoreItemDetailDao.insert(cti,bsidVo);
		return calc_version;
	}


	public BigDecimal getScore(String score,String dimWeight,String dimDefaultScore){
	    BigDecimal scoreVal = new BigDecimal(0);
	    if(StringUtils.isBlank(score)&&!StringUtils.isBlank(dimDefaultScore)) {
            scoreVal = new BigDecimal(dimDefaultScore).multiply(new BigDecimal(dimWeight));
        }else {
            scoreVal = new BigDecimal(score).multiply(new BigDecimal(dimWeight));
        }
        return scoreVal;
    }
	
}
