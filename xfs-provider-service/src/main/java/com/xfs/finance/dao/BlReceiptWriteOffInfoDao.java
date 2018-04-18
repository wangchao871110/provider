package com.xfs.finance.dao;


import com.xfs.common.IContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.finance.model.BlReceiptWriteOffInfo;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * BlReceiptWriteOffInfoDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/12/27 14:51:16
 */
@Repository
public class BlReceiptWriteOffInfoDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BL_RECEIPT_WRITE_OFF_INFO.CountFindAllBL_RECEIPT_WRITE_OFF_INFO", null );
        return ret.intValue();
	}

	public BlReceiptWriteOffInfo findByPK(Integer pk) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("pk", pk );
		Object ret = queryForObject("BL_RECEIPT_WRITE_OFF_INFO.FindByPK", _hashmap );
		if(ret !=null)
			return (BlReceiptWriteOffInfo)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BlReceiptWriteOffInfo> freeFind(BlReceiptWriteOffInfo obj) {
		return queryForList("BL_RECEIPT_WRITE_OFF_INFO.FreeFindBL_RECEIPT_WRITE_OFF_INFO", obj );
	}

	public int countFreeFind(BlReceiptWriteOffInfo obj) {
        Integer ret = (Integer) queryForObject("BL_RECEIPT_WRITE_OFF_INFO.CountFreeFindBL_RECEIPT_WRITE_OFF_INFO", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BlReceiptWriteOffInfo> freeFind(BlReceiptWriteOffInfo obj, int pageIndex, int pageSize) {
		return getPaginatedList("BL_RECEIPT_WRITE_OFF_INFO.FreeFindBL_RECEIPT_WRITE_OFF_INFO", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BlReceiptWriteOffInfo> freeFind(BlReceiptWriteOffInfo obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BlReceiptWriteOffInfo){
	    	_hashmap = ((BlReceiptWriteOffInfo)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BL_RECEIPT_WRITE_OFF_INFO.FreeFindBL_RECEIPT_WRITE_OFF_INFOOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BlReceiptWriteOffInfo> freeFind(BlReceiptWriteOffInfo obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BlReceiptWriteOffInfo){
	    	_hashmap = ((BlReceiptWriteOffInfo)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BL_RECEIPT_WRITE_OFF_INFO.FreeFindBL_RECEIPT_WRITE_OFF_INFOOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(IContextInfo info,Collection<BlReceiptWriteOffInfo> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BlReceiptWriteOffInfo> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BlReceiptWriteOffInfo oneObj = (BlReceiptWriteOffInfo)it.next();
				i += save(info,oneObj );
            }
        }
        return i;
	}

	public int save(IContextInfo info,BlReceiptWriteOffInfo vo) {
        BlReceiptWriteOffInfo obj = (BlReceiptWriteOffInfo) vo;

        if(exists( obj ) ) {
            return update(info, obj );
        } else {
            return insert(info, obj );
        }
	}

	public int insert(IContextInfo info, BlReceiptWriteOffInfo obj) {

		obj.fixup();
        return insert(info,"BL_RECEIPT_WRITE_OFF_INFO.InsertBL_RECEIPT_WRITE_OFF_INFO", obj );
	}

	public int update(IContextInfo info,BlReceiptWriteOffInfo obj) {

		obj.fixup();
		return update(info,"BL_RECEIPT_WRITE_OFF_INFO.UpdateBL_RECEIPT_WRITE_OFF_INFO", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(IContextInfo info,BlReceiptWriteOffInfo obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(info, "BL_RECEIPT_WRITE_OFF_INFO.DeleteBL_RECEIPT_WRITE_OFF_INFO", obj );

	}

	public int removeObjectTree(IContextInfo info,BlReceiptWriteOffInfo obj) {

        obj.fixup();
        BlReceiptWriteOffInfo oldObj = ( BlReceiptWriteOffInfo )queryForObject("BL_RECEIPT_WRITE_OFF_INFO.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(info, "BL_RECEIPT_WRITE_OFF_INFO.DeleteBL_RECEIPT_WRITE_OFF_INFO", oldObj );

	}

	public boolean exists(BlReceiptWriteOffInfo vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BlReceiptWriteOffInfo obj = (BlReceiptWriteOffInfo) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BL_RECEIPT_WRITE_OFF_INFO.CountBL_RECEIPT_WRITE_OFF_INFO", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}

    public List<Map> checkRecordDetail(BlReceiptWriteOffInfo vo) {
		return queryForList("BL_RECEIPT_WRITE_OFF_INFO.CHECK_RECORD_DETAIL",vo);
    }

    //温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/12/27 14:51:16

}
