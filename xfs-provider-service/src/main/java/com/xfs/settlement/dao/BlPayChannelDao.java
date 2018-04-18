package com.xfs.settlement.dao;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.xfs.common.ContextInfo;
import com.xfs.settlement.dto.BasePay;
import com.xfs.settlement.dto.RespPay;
import com.xfs.settlement.dto.RespPayChannel;
import com.xfs.settlement.model.BlPayChannel;
import org.springframework.stereotype.Repository;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * BlPayChannelDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/11/16 10:28:40
 */
@Repository
public class BlPayChannelDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BL_PAY_CHANNEL.CountFindAllBL_PAY_CHANNEL", null );
        return ret.intValue();
	}

	public BlPayChannel findByPK(BlPayChannel obj) {
		Object ret = queryForObject("BL_PAY_CHANNEL.FindByPK", obj );
		if(ret !=null)
			return (BlPayChannel)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BlPayChannel> freeFind(BlPayChannel obj) {
		return queryForList("BL_PAY_CHANNEL.FreeFindBL_PAY_CHANNEL", obj );
	}

	public int countFreeFind(BlPayChannel obj) {
        Integer ret = (Integer) queryForObject("BL_PAY_CHANNEL.CountFreeFindBL_PAY_CHANNEL", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BlPayChannel> freeFind(BlPayChannel obj, int pageIndex, int pageSize) {
		return getPaginatedList("BL_PAY_CHANNEL.FreeFindBL_PAY_CHANNEL", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BlPayChannel> freeFind(BlPayChannel obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BlPayChannel){
	    	_hashmap = ((BlPayChannel)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BL_PAY_CHANNEL.FreeFindBL_PAY_CHANNELOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BlPayChannel> freeFind(BlPayChannel obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BlPayChannel){
	    	_hashmap = ((BlPayChannel)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BL_PAY_CHANNEL.FreeFindBL_PAY_CHANNELOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti,Collection<BlPayChannel> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BlPayChannel> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BlPayChannel oneObj = (BlPayChannel)it.next();
				i += save(cti, oneObj );
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BlPayChannel vo) {
        BlPayChannel obj = (BlPayChannel) vo;

        if(exists( obj ) ) {
            return update(cti,obj );
        } else {
            return insert(cti, obj );
        }
	}

	public int insert(ContextInfo cti,BlPayChannel obj) {

		obj.fixup();
        return insert(cti,"BL_PAY_CHANNEL.InsertBL_PAY_CHANNEL", obj );
	}

	public int update(ContextInfo cti,BlPayChannel obj) {

		obj.fixup();
		return update(cti, "BL_PAY_CHANNEL.UpdateBL_PAY_CHANNEL", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti,BlPayChannel obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BL_PAY_CHANNEL.DeleteBL_PAY_CHANNEL", obj );

	}

	public int removeObjectTree(ContextInfo cti,BlPayChannel obj) {

        obj.fixup();
        BlPayChannel oldObj = ( BlPayChannel )queryForObject("BL_PAY_CHANNEL.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete( cti,"BL_PAY_CHANNEL.DeleteBL_PAY_CHANNEL", oldObj );

	}

	public boolean exists(BlPayChannel vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BlPayChannel obj = (BlPayChannel) vo;

        keysFilled = keysFilled && ( null != obj.getPayId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BL_PAY_CHANNEL.CountBL_PAY_CHANNEL", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/11/16 10:28:40

	/**
	 *  获取业务支付渠道列表
	 *  @param   basePay
	 *	@return 			: java.util.List<com.xfs.settlement.dto.RespPayChannel>
	 *  @createDate  	: 2016-11-19 10:09
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-19 10:09
	 *  @updateAuthor  :
	 */
	public List<RespPayChannel> queryBusinPayChannels(BasePay basePay){
		return queryForList("BL_PAY_CHANNEL.QUERY_BUSIN_PAY_CHANNELS",basePay);
	}

	/**
	 *  获取业务支付渠道
	 *  @param   basePay
	 *	@return 			: java.util.List<com.xfs.settlement.dto.RespPayChannel>
	 *  @createDate  	: 2016-11-19 10:09
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-19 10:09
	 *  @updateAuthor  :
	 */
	public RespPayChannel queryBusinPayChannel(BasePay basePay){
		return (RespPayChannel)queryForObject("BL_PAY_CHANNEL.QUERY_BUSIN_PAY_CHANNEL",basePay);
	}

	/**
	 *  获取第三方支付信息
	 *  @param   basePay
	 *	@return 			: com.xfs.settlement.dto.RespPay
	 *  @createDate  	: 2016-11-21 17:44
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-21 17:44
	 *  @updateAuthor  :
	 */
	public RespPay queryBusinPayInfo(BasePay basePay){
		return (RespPay) queryForObject("BL_PAY_CHANNEL.QUERY_BUSIN_PAY_INFO",basePay);
	}

	/**
	 * 获取畅捷通支付参数
	 * @param basePay
	 * @return
	 */
	public RespPay queryChanPayInfo(BasePay basePay){
		return (RespPay) queryForObject("BL_PAY_CHANNEL.QUERY_BUSIN_PAY_CHAN_PAY_INFO",basePay);
	}
	/**
	 *  根据订单号获取支付渠道信息
	 *  @param   order_id
	 *	@return 			: com.xfs.settlement.dto.RespPay
	 *  @createDate  	: 2016-11-21 21:45
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-21 21:45
	 *  @updateAuthor  :
	 */
	public RespPay queryBusinPayInfo(String order_id){
		Map<String,Object> obj = new HashMap<>();
		obj.put("order_id",order_id);
		return (RespPay) queryForObject("BL_PAY_CHANNEL.QUERY_BUSIN_PAY_INFO_BY_ORDERID",obj);
	}
}
