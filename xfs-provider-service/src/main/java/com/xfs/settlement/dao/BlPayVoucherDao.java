package com.xfs.settlement.dao;

import com.xfs.bill.dto.PayVoucher;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.finance.vo.BlPayVoucherDto;
import com.xfs.settlement.dto.BasePay;
import com.xfs.settlement.model.BlAlreadyCheckPayVoucher;
import com.xfs.settlement.model.BlPayVoucher;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

/**
 * BlPayVoucherDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/12 21:08:02
 */
@Repository
public class BlPayVoucherDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BL_PAY_VOUCHER.CountFindAllBL_PAY_VOUCHER", null );
        return ret.intValue();
	}

	public BlPayVoucher findByPK(BlPayVoucher obj) {
		Object ret = queryForObject("BL_PAY_VOUCHER.FindByPK", obj );
		if(ret !=null)
			return (BlPayVoucher)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BlPayVoucher> freeFind(BlPayVoucher obj) {
		return queryForList("BL_PAY_VOUCHER.FreeFindBL_PAY_VOUCHER", obj );
	}

	public int countFreeFind(BlPayVoucher obj) {
        Integer ret = (Integer) queryForObject("BL_PAY_VOUCHER.CountFreeFindBL_PAY_VOUCHER", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BlPayVoucher> freeFind(BlPayVoucher obj, int pageIndex, int pageSize) {
		return getPaginatedList("BL_PAY_VOUCHER.FreeFindBL_PAY_VOUCHER", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BlPayVoucher> freeFind(BlPayVoucher obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BlPayVoucher){
	    	_hashmap = ((BlPayVoucher)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BL_PAY_VOUCHER.FreeFindBL_PAY_VOUCHEROrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BlPayVoucher> freeFind(BlPayVoucher obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BlPayVoucher){
	    	_hashmap = ((BlPayVoucher)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BL_PAY_VOUCHER.FreeFindBL_PAY_VOUCHEROrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BlPayVoucher> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BlPayVoucher> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BlPayVoucher oneObj = (BlPayVoucher)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BlPayVoucher vo) {
        BlPayVoucher obj = (BlPayVoucher) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BlPayVoucher obj) {

		obj.fixup();
        return insert(cti, "BL_PAY_VOUCHER.InsertBL_PAY_VOUCHER", obj );
	}

	public int update(ContextInfo cti, BlPayVoucher obj) {

		obj.fixup();
		return update(cti, "BL_PAY_VOUCHER.UpdateBL_PAY_VOUCHER", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BlPayVoucher obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BL_PAY_VOUCHER.DeleteBL_PAY_VOUCHER", obj );

	}

	public int removeObjectTree(ContextInfo cti, BlPayVoucher obj) {

        obj.fixup();
        BlPayVoucher oldObj = ( BlPayVoucher )queryForObject("BL_PAY_VOUCHER.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BL_PAY_VOUCHER.DeleteBL_PAY_VOUCHER", oldObj );

	}

	public boolean exists(BlPayVoucher vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BlPayVoucher obj = (BlPayVoucher) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BL_PAY_VOUCHER.CountBL_PAY_VOUCHER", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/12 21:08:02

	/**
	 *  根据交易号获取凭证信息
	 *  @param   basePay
	 *	@return 			: com.xfs.settlement.model.BlPayVoucher
	 *  @createDate  	: 2016-11-19 14:52
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-19 14:52
	 *  @updateAuthor  :
	 */
	public BlPayVoucher queryVocherByOutTradeOrder(BasePay basePay){
		Object obj = queryForObject("BL_PAY_VOUCHER.QUERY_VOUCHER_BY_OUT_TRADE_ORDER",basePay);
		if(null != obj){
			BlPayVoucher vo = (BlPayVoucher)obj;
			return vo;
		}
		return null;
	}

	/**
	 *  根据交易号获取凭证信息
	 *  @param   voucher
	 *	@return 			: com.xfs.settlement.model.BlPayVoucher
	 *  @createDate  	: 2016-11-19 14:52
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-19 14:52
	 *  @updateAuthor  :
	 */
	public BlPayVoucher queryVocherByOrderId(BlPayVoucher voucher){
		Object obj = queryForObject("BL_PAY_VOUCHER.QUERY_VOUCHER_BY_ORDER_ID",voucher);
		if(null != obj){
			BlPayVoucher vo = (BlPayVoucher)obj;
			return vo;
		}
		return null;
	}

	/**
	 * 获取列表数量
	 * @param voucher
	 * @return
	 */
	public Integer queryVoucherCount(PayVoucher voucher){
		Integer ret = (Integer) queryForObject("BL_PAY_VOUCHER.QUERY_VOUCHER_COUNT", voucher );
		return ret.intValue();
	}

	/**
	 * 获取列表
	 * @param voucher
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<Map<String,Object>> queryVoucherList(PayVoucher voucher,Integer pageIndex,Integer pageSize){
		return (List<Map<String,Object>>)getPaginatedList("BL_PAY_VOUCHER.QUERY_VOUCHER_LIST", voucher, pageIndex, pageSize );
	}

	/**
	 * 汇总线下充值总金额
	 * @return
	 */
	public Map<String,Object> queryVoucherOffLineAmount(){
		return (Map<String,Object>)queryForObject("BL_PAY_VOUCHER.QUERY_VOUCHER_OFFLINE_AMOUNT",null);
	}

	/**
	 * 汇总线上充值总金额
	 * @return
	 */
	public Map<String,Object> queryVoucherOnLineAmount(){
		return (Map<String,Object>)queryForObject("BL_PAY_VOUCHER.QUERY_VOUCHER_ONLINE_AMOUNT",null);
	}

	/**
	 * 获取支付详情
	 * @param voucher
	 * @return
	 */
	public Map<String,Object> queryVoucherInfoById(BlPayVoucher voucher){
		return (Map<String,Object>)queryForObject("BL_PAY_VOUCHER.QUERY_VOUCHER_INFO_BY_ID",voucher);
	}

	/**
	 * 查询充值列表数量
	 * @param voucher
	 * @return
	 */
	public Integer queryRechargeCount(BlPayVoucher voucher){
		Integer ret = (Integer) queryForObject("BL_PAY_VOUCHER.QUERY_VOUCHER_RECHARGE_COUNT", voucher );
		return ret.intValue();
	}

	/**
	 *
	 * @param voucher
	 * @return
	 */
	public List<BlPayVoucher> queryRechargeList(BlPayVoucher voucher,Integer pageIndex,Integer pageSize){
		return getPaginatedList("BL_PAY_VOUCHER.QUERY_VOUCHER_RECHARGE_LIST", voucher,pageIndex,pageSize );
	}

	public BigDecimal queryAllPayedOnline(){
		return (BigDecimal)queryForObject("BL_PAY_VOUCHER.QUERY_ALL_PAYED_ONLINE", null);
	}


	/**
	 * 查询带有已核销订单
	 * @param blPayVoucher
	 * @return
	 */
	public List<BlAlreadyCheckPayVoucher> queryVoucherByTradeNoAndBusId(BlPayVoucher blPayVoucher) {
		return queryForList("BL_PAY_VOUCHER.QUERY_VOUCHER_WITH_CHECK",blPayVoucher);
	}

	public int updateOrderByCheck(ContextInfo contextInfo, BlAlreadyCheckPayVoucher payVoucher) {

		payVoucher.fixup();
		return update(contextInfo, "BL_PAY_VOUCHER.UPDATEORDERBYCHECK", payVoucher);
	}

	public List<BlPayVoucherDto> queryOnlinePayData(Map param){
		return queryForList("BL_PAY_VOUCHER.QUERY_ONLINE_PAY_DATA",param);
	}

	public Integer queryOnlinePayDataTotal(Map param){
		return (Integer) queryForObject("BL_PAY_VOUCHER.QUERY_ONLINE_PAY_DATA_TOTAL",param);
	}

	public Map checkRecordDetail(Map<String, String> params) {
		return (Map) queryForObject("BL_PAY_VOUCHER.CHECK_RECORD_DETAIL",params);
	}
}
