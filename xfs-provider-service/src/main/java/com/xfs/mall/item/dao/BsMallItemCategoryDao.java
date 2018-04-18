package com.xfs.mall.item.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.mall.item.model.BsMallItemCategory;

/**
 * BsMallItemCategoryDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/06/07 11:08:17
 */
@Repository
public class BsMallItemCategoryDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BS_MALL_ITEM_CATEGORY.CountFindAllBS_MALL_ITEM_CATEGORY", null );
        return ret.intValue();
	}

	public BsMallItemCategory findByPK(BsMallItemCategory obj) {
		Object ret = queryForObject("BS_MALL_ITEM_CATEGORY.FindByPK", obj );
		if(ret !=null)
			return (BsMallItemCategory)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BsMallItemCategory> freeFind(BsMallItemCategory obj) {
		return queryForList("BS_MALL_ITEM_CATEGORY.FreeFindBS_MALL_ITEM_CATEGORY", obj );
	}

	public int countFreeFind(BsMallItemCategory obj) {
        Integer ret = (Integer) queryForObject("BS_MALL_ITEM_CATEGORY.CountFreeFindBS_MALL_ITEM_CATEGORY", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BsMallItemCategory> freeFind(BsMallItemCategory obj, int pageIndex, int pageSize) {
		return getPaginatedList("BS_MALL_ITEM_CATEGORY.FreeFindBS_MALL_ITEM_CATEGORY", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BsMallItemCategory> freeFind(BsMallItemCategory obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsMallItemCategory){
	    	_hashmap = ((BsMallItemCategory)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BS_MALL_ITEM_CATEGORY.FreeFindBS_MALL_ITEM_CATEGORYOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BsMallItemCategory> freeFind(BsMallItemCategory obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsMallItemCategory){
	    	_hashmap = ((BsMallItemCategory)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BS_MALL_ITEM_CATEGORY.FreeFindBS_MALL_ITEM_CATEGORYOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BsMallItemCategory> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BsMallItemCategory> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BsMallItemCategory oneObj = (BsMallItemCategory)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BsMallItemCategory vo) {
        BsMallItemCategory obj = (BsMallItemCategory) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BsMallItemCategory obj) {

		obj.fixup();
        return insert(cti, "BS_MALL_ITEM_CATEGORY.InsertBS_MALL_ITEM_CATEGORY", obj );
	}

	public int update(ContextInfo cti, BsMallItemCategory obj) {

		obj.fixup();
		return update(cti, "BS_MALL_ITEM_CATEGORY.UpdateBS_MALL_ITEM_CATEGORY", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BsMallItemCategory obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BS_MALL_ITEM_CATEGORY.DeleteBS_MALL_ITEM_CATEGORY", obj );

	}

	public int removeObjectTree(ContextInfo cti, BsMallItemCategory obj) {

        obj.fixup();
        BsMallItemCategory oldObj = ( BsMallItemCategory )queryForObject("BS_MALL_ITEM_CATEGORY.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BS_MALL_ITEM_CATEGORY.DeleteBS_MALL_ITEM_CATEGORY", oldObj );

	}

	public boolean exists(BsMallItemCategory vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BsMallItemCategory obj = (BsMallItemCategory) vo;

        keysFilled = keysFilled && ( null != obj.getCategoryId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BS_MALL_ITEM_CATEGORY.CountBS_MALL_ITEM_CATEGORY", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/07 11:08:17

	public List<BsMallItemCategory> findAllSecondCategory() {
		return queryForList("BS_MALL_ITEM_CATEGORY.findAllSecondCategory", null );
	}


	/**
	 * 查询一级 项目分类列表
	 *
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<BsMallItemCategory> findOneLevAll(BsMallItemCategory vo) {
		return queryForList("BS_MALL_ITEM_CATEGORY.findOneLevAll", vo);
	}

	/**
	 * 查询商保分类
	 * @return 商保分类信息
	 */
	public List<BsMallItemCategory> findCiCategory(){
		return queryForList("BS_MALL_ITEM_CATEGORY.findCiCategory", null);
	}

	/**
	 *
	 * @method comments: 根据多个服务项目ID获取服务项目名称
	 * @author   name  : wangchao
	 * @create   time  : 2016年9月11日 下午9:03:57
	 * @param categoryId
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月11日 下午9:03:57 wangchao 创建
	 *
	 */
	public String findNameInId(String categoryId) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("categoryId", categoryId );
		String ret = (String) queryForObject("BS_MALL_ITEM_CATEGORY.findNameInId", _hashmap );
		return ret;
	}

	public String findNameBySpId(Integer spId) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("spId", spId );
		String ret = (String) queryForObject("BS_MALL_ITEM_CATEGORY.findNameBySpId", _hashmap );
		return ret;
	}

	public List<Map> freeFindChildrenCategory() {
		return queryForList("BS_MALL_ITEM_CATEGORY.FreeFindChildrenCategory",null);
	}

	
}
