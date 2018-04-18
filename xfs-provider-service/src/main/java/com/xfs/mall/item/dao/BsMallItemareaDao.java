package com.xfs.mall.item.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.mall.item.model.BsMallItemarea;
import com.xfs.user.model.SysUser;

/**
 * BsMallItemareaDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/09 14:08:11
 */
@Repository
public class BsMallItemareaDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BS_MALL_ITEMAREA.CountFindAllBS_MALL_ITEMAREA", null );
        return ret.intValue();
	}

	public BsMallItemarea findByPK(BsMallItemarea obj) {
		Object ret = queryForObject("BS_MALL_ITEMAREA.FindByPK", obj );
		if(ret !=null)
			return (BsMallItemarea)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BsMallItemarea> freeFind(BsMallItemarea obj) {
		return queryForList("BS_MALL_ITEMAREA.FreeFindBS_MALL_ITEMAREA", obj );
	}

	public int countFreeFind(BsMallItemarea obj) {
        Integer ret = (Integer) queryForObject("BS_MALL_ITEMAREA.CountFreeFindBS_MALL_ITEMAREA", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BsMallItemarea> freeFind(BsMallItemarea obj, int pageIndex, int pageSize) {
		return getPaginatedList("BS_MALL_ITEMAREA.FreeFindBS_MALL_ITEMAREA", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BsMallItemarea> freeFind(BsMallItemarea obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsMallItemarea){
	    	_hashmap = ((BsMallItemarea)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BS_MALL_ITEMAREA.FreeFindBS_MALL_ITEMAREAOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BsMallItemarea> freeFind(BsMallItemarea obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsMallItemarea){
	    	_hashmap = ((BsMallItemarea)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BS_MALL_ITEMAREA.FreeFindBS_MALL_ITEMAREAOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BsMallItemarea> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BsMallItemarea> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BsMallItemarea oneObj = (BsMallItemarea)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BsMallItemarea vo) {
        BsMallItemarea obj = (BsMallItemarea) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BsMallItemarea obj) {

		obj.fixup();
        return insert(cti, "BS_MALL_ITEMAREA.InsertBS_MALL_ITEMAREA", obj );
	}

	public int update(ContextInfo cti, BsMallItemarea obj) {

		obj.fixup();
		return update(cti, "BS_MALL_ITEMAREA.UpdateBS_MALL_ITEMAREA", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BsMallItemarea obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BS_MALL_ITEMAREA.DeleteBS_MALL_ITEMAREA", obj );

	}

	public int removeObjectTree(ContextInfo cti, BsMallItemarea obj) {

        obj.fixup();
        BsMallItemarea oldObj = ( BsMallItemarea )queryForObject("BS_MALL_ITEMAREA.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BS_MALL_ITEMAREA.DeleteBS_MALL_ITEMAREA", oldObj );

	}

	public boolean exists(BsMallItemarea vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BsMallItemarea obj = (BsMallItemarea) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BS_MALL_ITEMAREA.CountBS_MALL_ITEMAREA", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/09 14:08:11

	/**
	 * 根据itemid删除
	 * @param vo
	 * @return
	 */
	public int removeByItemId(ContextInfo cti, int itemId){
		return delete(cti, "BS_MALL_ITEMAREA.DeleteByItemIdBS_MALL_ITEMAREA", itemId);
	}
	/**
	 * 根据itemid保存areaid列表
	 */
	public int saveByListItemId(ContextInfo cti, List<Integer> areas, int itemId){
		List<BsMallItemarea> relations = new ArrayList<BsMallItemarea>();
		for (int i : areas){
			BsMallItemarea b = new BsMallItemarea();
			b.setAreaId(i);
			b.setItemId(itemId);
			b.setCreateBy(cti.getUserId());
			b.setCreateDt(new Date());
			relations.add(b);
		}
		return saveAll(cti, relations);
	}
	/**
	 * 通过itemid更新
	 * @param areas 需要添加的areaid
	 * @param itemId itemid
	 * @param sys
	 * @return
	 */
	public int updateByListAndItemId(ContextInfo cti, List<Integer> areas, int itemId){
		removeByItemId(cti, itemId);
		return saveByListItemId(cti, areas, itemId);
	}
	/**
	 * 通过itemId查找list
	 * @param itemId
	 * @return
	 */
	public List<BsMallItemarea> findByItemId(int itemId){
		return queryForList("BS_MALL_ITEMAREA.FindByItemIdBS_MALL_ITEMAREA", itemId);
	}
	/**
	 * 通过areaId查找list
	 * @param itemId
	 * @return
	 */
	public List<BsMallItemarea> findByAreaId(int areaId){
		return queryForList("BS_MALL_ITEMAREA.FindByAreaIdBS_MALL_ITEMAREA", areaId);
	}

	public List<Map> FreeFindItems(String chooseCityId,String categoryId,String searchWord){
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("chooseCityId",chooseCityId);
		_hashmap.put("categoryId",categoryId);
		_hashmap.put("searchWord",searchWord);
		return queryForList("BS_MALL_ITEMAREA.FreeFindItems", _hashmap );
	}
	
}
