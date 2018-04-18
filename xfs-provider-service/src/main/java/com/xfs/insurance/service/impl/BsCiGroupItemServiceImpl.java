package com.xfs.insurance.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.insurance.dao.BsCiGroupItemDao;
import com.xfs.insurance.dto.CiProduct;
import com.xfs.insurance.model.BsCiGroup;
import com.xfs.insurance.model.BsCiGroupItem;
import com.xfs.insurance.service.BsCiGroupItemService;

@Service
public class BsCiGroupItemServiceImpl implements BsCiGroupItemService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BsCiGroupItemServiceImpl.class);
	
	@Autowired
	private BsCiGroupItemDao bsCiGroupItemDao;
	
	public int save(ContextInfo cti, BsCiGroupItem vo ){
		return bsCiGroupItemDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BsCiGroupItem vo ){
		return bsCiGroupItemDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BsCiGroupItem vo ){
		return bsCiGroupItemDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BsCiGroupItem vo ){
		return bsCiGroupItemDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BsCiGroupItem vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bsCiGroupItemDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BsCiGroupItem> datas = bsCiGroupItemDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BsCiGroupItem> findAll(BsCiGroupItem vo){
		
		List<BsCiGroupItem> datas = bsCiGroupItemDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/06 18:33:04

	/**
	 * 查询组合险子条目心胸
	 *
	 * @param groupId 组合险编码
	 * @return 查询结果
	 */
	@Override
	public List<CiProduct> findGroupItemsDetail(Integer groupId) {
		BsCiGroup group = new BsCiGroup();
		group.setGroupId(groupId);
		return bsCiGroupItemDao.findGroupItemsDetail(group);
	}

	/**
	 * 删除组合险信息
	 *
	 * @param groupId 组合险编码
	 * @return 删除条数
	 */
	@Override
	public int deleteAllGroupItems(ContextInfo cti,Integer groupId) {
		BsCiGroup group = new BsCiGroup();
		group.setGroupId(groupId);
		return bsCiGroupItemDao.deleteAllGroupItems(cti,group);
	}

    /**
     * 查询组合险子条目心胸
     *
     * @param prodIds 商品编码列表
     * @return 查询结果
     */
    @Override
    public List<CiProduct> findAllGroupItemsDetail(List<Integer> prodIds) {

        return bsCiGroupItemDao.findAllGroupItemsDetail(prodIds);
    }

}
