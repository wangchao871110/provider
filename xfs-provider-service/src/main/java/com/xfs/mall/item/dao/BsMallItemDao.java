package com.xfs.mall.item.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.mall.item.model.BsMallItem;

/**
 * BsMallItemDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/06/07 11:08:15
 */
@Repository
public class BsMallItemDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BS_MALL_ITEM.CountFindAllBS_MALL_ITEM", null );
        return ret.intValue();
	}

	public BsMallItem findByPK(BsMallItem obj) {
		Object ret = queryForObject("BS_MALL_ITEM.FindByPK", obj );
		if(ret !=null)
			return (BsMallItem)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BsMallItem> freeFind(BsMallItem obj) {
		return queryForList("BS_MALL_ITEM.FreeFindBS_MALL_ITEM", obj );
	}

	public int countFreeFind(BsMallItem obj) {
        Integer ret = (Integer) queryForObject("BS_MALL_ITEM.CountFreeFindBS_MALL_ITEM", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BsMallItem> freeFind(BsMallItem obj, int pageIndex, int pageSize) {
		return getPaginatedList("BS_MALL_ITEM.FreeFindBS_MALL_ITEM", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BsMallItem> freeFind(BsMallItem obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsMallItem){
	    	_hashmap = ((BsMallItem)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BS_MALL_ITEM.FreeFindBS_MALL_ITEMOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BsMallItem> freeFind(BsMallItem obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsMallItem){
	    	_hashmap = ((BsMallItem)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BS_MALL_ITEM.FreeFindBS_MALL_ITEMOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BsMallItem> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BsMallItem> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BsMallItem oneObj = (BsMallItem)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BsMallItem vo) {
        BsMallItem obj = (BsMallItem) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BsMallItem obj) {

		obj.fixup();
        return insert(cti, "BS_MALL_ITEM.InsertBS_MALL_ITEM", obj );
	}

	public int update(ContextInfo cti, BsMallItem obj) {

		obj.fixup();
		return update(cti, "BS_MALL_ITEM.UpdateBS_MALL_ITEM", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BsMallItem obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BS_MALL_ITEM.DeleteBS_MALL_ITEM", obj );

	}

	public int removeObjectTree(ContextInfo cti, BsMallItem obj) {

        obj.fixup();
        BsMallItem oldObj = ( BsMallItem )queryForObject("BS_MALL_ITEM.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BS_MALL_ITEM.DeleteBS_MALL_ITEM", oldObj );

	}

	public boolean exists(BsMallItem vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BsMallItem obj = (BsMallItem) vo;

        keysFilled = keysFilled && ( null != obj.getItemId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BS_MALL_ITEM.CountBS_MALL_ITEM", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/07 11:08:15



	/**
	 * 项目列表List
	 *
	 * @param obj
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<BsMallItem> queryMallItemList(BsMallItem obj, int pageIndex, int pageSize) {
		return getPaginatedList("BS_MALL_ITEM.BS_FreeFindBS_MALL_ITEM", obj, pageIndex, pageSize);
	}

	/**
	 * 项目列表Count
	 *
	 * @param obj
	 * @return
	 */
	public int queryItemListCount(BsMallItem obj) {
		Integer ret = (Integer) queryForObject("BS_MALL_ITEM.BS_CountFreeFindBS_MALL_ITEM", obj);
		return ret.intValue();
	}

	/**
	 * 项目详情
	 *
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<BsMallItem> findOneByItemId(BsMallItem obj) {
		return queryForList("BS_MALL_ITEM.BS_FreeFindBS_MALL_ITEM", obj);
	}

	public List<BsMallItem> findMallItemWithoutCi(BsMallItem mallItem){
		return queryForList("BS_MALL_ITEM.findMALL_ITEM_WITHOUT_CI", mallItem);
	}
	
}
