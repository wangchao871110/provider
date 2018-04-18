package com.xfs.mall.item.service;

import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.mall.item.model.BsMallItemCategory;

/**
 * BsMallItemCategoryService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/06/07 11:08:17
 */
public interface BsMallItemCategoryService {
	
	public int save(ContextInfo cti, BsMallItemCategory vo);
	public int insert(ContextInfo cti, BsMallItemCategory vo);
	public int remove(ContextInfo cti, BsMallItemCategory vo);
	public int update(ContextInfo cti, BsMallItemCategory vo);
	public PageModel findPage(PageInfo pi, BsMallItemCategory vo);
	public List<BsMallItemCategory> findAll(BsMallItemCategory vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/07 11:08:17

	/**
	 * 获取所有分类（子父级）
	 *
	 * @return
	 */
	public List<BsMallItemCategory> findAll();
	public List<BsMallItemCategory> findAllSecondCategory();

	/**
	 * 项目分类
	 *
	 * @return
	 */
	public List<BsMallItemCategory> findOneLevAll(BsMallItemCategory vo);

	/**
	 * 查询商保目录
	 *
	 * @return
	 */
	List<BsMallItemCategory> findCICategory();

	/**
	 * 根据拼音获取类型
	 *
	 * @param pinyin
	 * @return
	 */
	public BsMallItemCategory getCategoryByPinyin(String pinyin);

	public List<Map> freeFindChildrenCategory();

	List<BsMallItemCategory> SPS_findCICategory();
}
