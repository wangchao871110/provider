package com.xfs.finance.dao;

import com.xfs.common.ContextInfo;
import com.xfs.common.IContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.finance.model.BlReceiptInfo;
import com.xfs.finance.vo.BlReceiptDetailVo;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

/**
 * BlReceiptInfoDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/12/27 14:51:15
 */
@Repository
public class BlReceiptInfoDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BL_RECEIPT_INFO.CountFindAllBL_RECEIPT_INFO", null );
        return ret.intValue();
	}

	public BlReceiptInfo findByPK(Integer pk) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("pk", pk );
		Object ret = queryForObject("BL_RECEIPT_INFO.FindByPK", _hashmap );
		if(ret !=null)
			return (BlReceiptInfo)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BlReceiptInfo> freeFind(BlReceiptInfo obj) {
		return queryForList("BL_RECEIPT_INFO.FreeFindBL_RECEIPT_INFO", obj );
	}

	public int countFreeFind(BlReceiptInfo obj) {
        Integer ret = (Integer) queryForObject("BL_RECEIPT_INFO.CountFreeFindBL_RECEIPT_INFO", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BlReceiptInfo> freeFind(BlReceiptInfo obj, int pageIndex, int pageSize) {
		return getPaginatedList("BL_RECEIPT_INFO.FreeFindBL_RECEIPT_INFO", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BlReceiptInfo> freeFind(BlReceiptInfo obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BlReceiptInfo){
	    	_hashmap = ((BlReceiptInfo)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BL_RECEIPT_INFO.FreeFindBL_RECEIPT_INFOOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BlReceiptInfo> freeFind(BlReceiptInfo obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BlReceiptInfo){
	    	_hashmap = ((BlReceiptInfo)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BL_RECEIPT_INFO.FreeFindBL_RECEIPT_INFOOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(IContextInfo info,Collection<BlReceiptInfo> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BlReceiptInfo> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BlReceiptInfo oneObj = (BlReceiptInfo)it.next();
				i += save(info, oneObj );
            }
        }
        return i;
	}

	public int save(IContextInfo info,BlReceiptInfo vo) {
        BlReceiptInfo obj = (BlReceiptInfo) vo;

        if(exists( obj ) ) {
            return update(info, obj );
        } else {
            return insert(info, obj );
        }
	}

	public int insert(IContextInfo info, BlReceiptInfo obj) {

		obj.fixup();
        return insert(info,"BL_RECEIPT_INFO.InsertBL_RECEIPT_INFO", obj );
	}

	public int update(IContextInfo info,BlReceiptInfo obj) {

		obj.fixup();
		return update(info, "BL_RECEIPT_INFO.UpdateBL_RECEIPT_INFO", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(IContextInfo info,BlReceiptInfo obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(info,"BL_RECEIPT_INFO.DeleteBL_RECEIPT_INFO", obj );

	}

	public int removeObjectTree(IContextInfo info,BlReceiptInfo obj) {

        obj.fixup();
        BlReceiptInfo oldObj = ( BlReceiptInfo )queryForObject("BL_RECEIPT_INFO.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(info, "BL_RECEIPT_INFO.DeleteBL_RECEIPT_INFO", oldObj );

	}

	public boolean exists(BlReceiptInfo vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BlReceiptInfo obj = (BlReceiptInfo) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BL_RECEIPT_INFO.CountBL_RECEIPT_INFO", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}

	public int updateReceiptInfByCheck(ContextInfo contextInfo, BlReceiptInfo receiptInfo) {
		receiptInfo.fixup();
		return update(contextInfo,"BL_RECEIPT_INFO.UPDATERECEIPTINFBYCHECK",receiptInfo);
	}

    //温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/12/27 14:51:15
	
	public void batchInsert(IContextInfo info, List items){
		insert(info, "BL_RECEIPT_INFO.batchInsert", items);
	}

	public List<BlReceiptDetailVo> findAccountDetails(Map<String,Object> param){
        return queryForList("BL_RECEIPT_INFO.findAccountDetails",param);
    }

    public Integer findAccountDetailsTotal(Map<String,Object> param){
        return (Integer) queryForObject("BL_RECEIPT_INFO.findAccountIdsTotal",param);
    }

    public BigDecimal getTotalAmount(){
		return (BigDecimal) queryForObject("BL_RECEIPT_INFO.QUERY_TOTAL_AMOUNT",null);
	}

	public List<Map> queryAllUnchecked(){
        return queryForList("BL_RECEIPT_INFO.QUERY_ALL_UNCHECKED",null);
    }

    public List<Map> queryAmountNoMatch(){
		return queryForList("BL_RECEIPT_INFO.QUERY_AMOUNT_NO_MATCH",null);
	}

	public List<Map> queryAccountNoMatch(){
		return queryForList("BL_RECEIPT_INFO.QUERY_ACCOUNT_NO_MATCH",null);
	}

	public List<BlReceiptInfo> queryByTradeCode(List<String> list){
		return queryForList("BL_RECEIPT_INFO.QUERY_BY_TRADE_CODE",list);
	}

	public List<Integer> findAccountIds(Map<String,Object> param){
		return queryForList("BL_RECEIPT_INFO.findAccountIds",param);
	}

	public Integer findAccountIdsTotal(Map<String,Object> param){
		return (Integer) queryForObject("BL_RECEIPT_INFO.findAccountIdsTotal",param);
	}
}
