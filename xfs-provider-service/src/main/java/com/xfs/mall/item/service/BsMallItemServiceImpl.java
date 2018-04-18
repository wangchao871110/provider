package com.xfs.mall.item.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.mall.item.dao.BsMallItemDao;
import com.xfs.mall.item.dao.BsMallItemareaDao;
import com.xfs.mall.item.model.BsMallItem;
import com.xfs.user.model.SysUser;

@Service
public class BsMallItemServiceImpl implements BsMallItemService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BsMallItemServiceImpl.class);
	
	@Autowired
	private BsMallItemDao bsMallItemDao;
	
	public int save(ContextInfo cti, BsMallItem vo ){
		return bsMallItemDao.save(cti, vo);
	}
	@Autowired
	private BsMallItemareaDao bsMallItemareaDao;
	public int insert(ContextInfo cti,  BsMallItem vo ){
		return bsMallItemDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BsMallItem vo ){
		return bsMallItemDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BsMallItem vo ){
		return bsMallItemDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BsMallItem vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bsMallItemDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BsMallItem> datas = bsMallItemDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BsMallItem> findAll(BsMallItem vo){
		
		List<BsMallItem> datas = bsMallItemDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/07 11:08:15

	public List<BsMallItem> findOneByItemId(BsMallItem vo) {
		List<BsMallItem> datas = bsMallItemDao.findOneByItemId(vo);
		return datas;
	}

	public Result saveData(ContextInfo cti, BsMallItem vo) {
		Result result = Result.createResult().setSuccess(false);
		Date date = new Date();
		if (null != cti && null != vo && vo.getAreaIds() != null && vo.getAreaIds().size() > 0) {
			Integer ins = 0;
			if (null == vo.getItemId()) {
				vo.setCreateBy(cti.getUserId());
				vo.setCreateDt(date);
				vo.setDr(0);// 默认为启用状态
				if (null == vo.getPerMonth()) {
					vo.setPerMonth("N");
					vo.setPerMonthPrice(BigDecimal.ZERO);
				}
				if (null == vo.getOnce()) {
					vo.setOnce("N");
					vo.setOncePrice(BigDecimal.ZERO);
				}
				ins = this.insert(cti, vo);
			} else {
				if (null == vo.getPerMonth()) {
					vo.setPerMonth("N");
					vo.setPerMonthPrice(BigDecimal.ZERO);
				}
				if (null == vo.getOnce()) {
					vo.setOnce("N");
					vo.setOncePrice(BigDecimal.ZERO);
				}
				vo.setModifyBy(cti.getUserId());
				vo.setModifyDt(date);
				ins = this.update(cti, vo);
			}
			int ups = bsMallItemareaDao.updateByListAndItemId(cti, vo.getAreaIds(), vo.getItemId());
			if (0 < ins && ups > 0) {
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
			}
			return result;
		} else {
			result.setError("保存失败！");
			return result;
		}
	}

	@Override
	public List<BsMallItem> findMallItemWithoutCi(BsMallItem mallItem) {
		return bsMallItemDao.findMallItemWithoutCi(mallItem);
	}

	/**
	 * 根据主键查找
	 * @param itemId 主键
	 * @return 查询结果
	 */
	@Override
	public BsMallItem findByPk(Integer itemId) {
		BsMallItem mallItem = new BsMallItem();
		mallItem.setItemId(itemId);
		return bsMallItemDao.findByPK( mallItem);
	}


	public PageModel queryPageByBs(PageInfo pi, BsMallItem vo){

		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bsMallItemDao.queryItemListCount(vo);
		pm.setTotal(total);
		List<BsMallItem> datas = bsMallItemDao.queryMallItemList(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;

	}
	
}
