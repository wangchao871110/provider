package com.xfs.mall.item.service;

import static com.xfs.common.redies.keychain.IRedisKeys.EXPIRE_DAY;
import static com.xfs.common.redies.keychain.IRedisKeys.MALL_ITEM_CATEGORY_MAP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.common.redies.RedisUtil;
import com.xfs.common.util.StringUtils;
import com.xfs.mall.item.dao.BsMallItemCategoryDao;
import com.xfs.mall.item.model.BsMallItemCategory;

@Service
public class BsMallItemCategoryServiceImpl implements BsMallItemCategoryService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BsMallItemCategoryServiceImpl.class);
	
	@Autowired
	private BsMallItemCategoryDao bsMallItemCategoryDao;
	
	public int save(ContextInfo cti, BsMallItemCategory vo ){
		return bsMallItemCategoryDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BsMallItemCategory vo ){
		return bsMallItemCategoryDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BsMallItemCategory vo ){
		return bsMallItemCategoryDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BsMallItemCategory vo ){
		return bsMallItemCategoryDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BsMallItemCategory vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bsMallItemCategoryDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BsMallItemCategory> datas = bsMallItemCategoryDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BsMallItemCategory> findAll(BsMallItemCategory vo){
		
		List<BsMallItemCategory> datas = bsMallItemCategoryDao.freeFind(vo);
		return datas;
		
	}

	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/07 11:08:17

	/**
	 * 获取所有分类（子父级）
	 *
	 * @return
	 */
	public List<BsMallItemCategory> findAll() {
//		String key = "bs_mall_item_category";
//		Object o = RedisUtil.get(key);
//		if (o != null && o instanceof List) {
//			return (List)o;
//		}
		BsMallItemCategory vo = new BsMallItemCategory();
		vo.setDr(0);
		List<BsMallItemCategory> list = bsMallItemCategoryDao.freeFind(vo, "pid ASC, orderby ASC, category_id ASC");

		List<BsMallItemCategory> categories = new ArrayList<>();
		for (BsMallItemCategory category: list) {
			recurseCategory(categories, category);
		}
//		RedisUtil.set(key, categories, 0);
		return categories;
	}

	/**
	 * 递归处理分类子父级
	 *
	 * @param categories
	 * @param category
     */
	private void recurseCategory(List<BsMallItemCategory> categories, BsMallItemCategory category) {
		if (categories == null) {
			return;
		}
		if (category.getPid() == null || category.getPid() == 0) {
			categories.add(category);
			return;
		}
		for (BsMallItemCategory itemCategory: categories) {
			if (itemCategory.getCategoryId().equals(category.getPid())) {
				List<BsMallItemCategory> children = itemCategory.getChildren();
				if (children == null) {
					children = new ArrayList<>();
					itemCategory.setChildren(children);
				}
				children.add(category);
				return;
			}
			recurseCategory(itemCategory.getChildren(), category);
		}
	}

	public List<BsMallItemCategory> findAllSecondCategory(){

		List<BsMallItemCategory> datas = bsMallItemCategoryDao.findAllSecondCategory();
		return datas;

	}

	/**
	 * 项目分类
	 */
	@Override
	public List<BsMallItemCategory> findOneLevAll(BsMallItemCategory vo) {
		List<BsMallItemCategory> datas = bsMallItemCategoryDao.findOneLevAll(vo);
		return datas;
	}

	/**
	 * 查询商保目录
	 *
	 * @return
	 */
	@Override
	public List<BsMallItemCategory> findCICategory() {

		return bsMallItemCategoryDao.findCiCategory();
	}

	/**
	 * 根据拼音获取类型
	 *
	 * @param pinyin
	 * @return
	 */
	public BsMallItemCategory getCategoryByPinyin(String pinyin) {
		if (StringUtils.isBlank(pinyin)) {
			return null;
		}
		Object o = RedisUtil.get(MALL_ITEM_CATEGORY_MAP);
		if (o != null && o instanceof Map) {
			return (BsMallItemCategory)((Map) o).get(pinyin);
		}
		BsMallItemCategory vo = new BsMallItemCategory();
		vo.setDr(0);
		List<BsMallItemCategory> categories = findAll(vo);
		if (categories == null || categories.isEmpty()) {
			return null;
		}
		Map<String, BsMallItemCategory> categoryMap = new HashMap<>();
		for (BsMallItemCategory category: categories) {
			if (StringUtils.isBlank(category.getPinyin())) {
				continue;
			}
			categoryMap.put(category.getPinyin(), category);
		}
		RedisUtil.set(MALL_ITEM_CATEGORY_MAP, categoryMap, EXPIRE_DAY);
		return categoryMap.get(pinyin);
	}



	public List<Map> freeFindChildrenCategory(){
		return bsMallItemCategoryDao.freeFindChildrenCategory();
	}

	/**
	 * 查询商保目录
	 *
	 * @return
	 */
	@Override
	public List<BsMallItemCategory> SPS_findCICategory() {

		BsMallItemCategory mallItemCategory = new BsMallItemCategory();
		mallItemCategory.setCategoryTypeEq("CI");
		List<BsMallItemCategory> list = bsMallItemCategoryDao.freeFind(mallItemCategory);
		for (BsMallItemCategory category : list) {
			if (Integer.valueOf(0).equals(category.getPid())) {
				list.remove(category);
				break;
			}
		}
		return list;
	}
}
