package com.xfs.activity.dao;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.activity.model.MoonUserTicket;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.common.util.StringUtils;

/**
 * MoonUserTicketDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/06 11:20:21
 */
@Repository
public class MoonUserTicketDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("MOON_USER_TICKET.CountFindAllMOON_USER_TICKET", null );
        return ret.intValue();
	}

	public MoonUserTicket findByPK(MoonUserTicket obj) {
		Object ret = queryForObject("MOON_USER_TICKET.FindByPK", obj );
		if(ret !=null)
			return (MoonUserTicket)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<MoonUserTicket> freeFind(MoonUserTicket obj) {
		return queryForList("MOON_USER_TICKET.FreeFindMOON_USER_TICKET", obj );
	}

	public int countFreeFind(MoonUserTicket obj) {
        Integer ret = (Integer) queryForObject("MOON_USER_TICKET.CountFreeFindMOON_USER_TICKET", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<MoonUserTicket> freeFind(MoonUserTicket obj, int pageIndex, int pageSize) {
		return getPaginatedList("MOON_USER_TICKET.FreeFindMOON_USER_TICKET", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<MoonUserTicket> freeFind(MoonUserTicket obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof MoonUserTicket){
	    	_hashmap = ((MoonUserTicket)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("MOON_USER_TICKET.FreeFindMOON_USER_TICKETOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<MoonUserTicket> freeFind(MoonUserTicket obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof MoonUserTicket){
	    	_hashmap = ((MoonUserTicket)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("MOON_USER_TICKET.FreeFindMOON_USER_TICKETOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<MoonUserTicket> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<MoonUserTicket> it = objColl.iterator();
            while ( it.hasNext() ) {
            	MoonUserTicket oneObj = (MoonUserTicket)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, MoonUserTicket vo) {
        MoonUserTicket obj = (MoonUserTicket) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, MoonUserTicket obj) {

		obj.fixup();
        return insert(cti, "MOON_USER_TICKET.InsertMOON_USER_TICKET", obj );
	}

	public int update(ContextInfo cti, MoonUserTicket obj) {

		obj.fixup();
		return update(cti, "MOON_USER_TICKET.UpdateMOON_USER_TICKET", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, MoonUserTicket obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "MOON_USER_TICKET.DeleteMOON_USER_TICKET", obj );

	}

	public int removeObjectTree(ContextInfo cti, MoonUserTicket obj) {

        obj.fixup();
        MoonUserTicket oldObj = ( MoonUserTicket )queryForObject("MOON_USER_TICKET.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "MOON_USER_TICKET.DeleteMOON_USER_TICKET", oldObj );

	}

	public boolean exists(MoonUserTicket vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        MoonUserTicket obj = (MoonUserTicket) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("MOON_USER_TICKET.CountMOON_USER_TICKET", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/06 11:20:21

	public int updateState(ContextInfo cti, MoonUserTicket obj) {

		obj.fixup();
		return update(cti, "MOON_USER_TICKET.UpdateState", obj );

	}
	public int insertAll(ContextInfo cti, MoonUserTicket obj,String code1,String code2,String code3,String code4) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("userId",obj.getUserId());
		_hashmap.put("password",obj.getPassword());
		_hashmap.put("code",obj.getCode());
		_hashmap.put("code1",code1);
		_hashmap.put("code2",code2);
		_hashmap.put("code3",code3);
		_hashmap.put("code4",code4);
		_hashmap.put("createDt",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		return insert(cti, "MOON_USER_TICKET.InsertUser", _hashmap );
	}
	/**
	 * 获取用户优惠券列表
	 *
	 * @param userId
	 * @param ticketType
	 * @return
	 */
	public List<Map<String, Object>> findMoonUserTickets(Integer userId, String ticketType) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		if (userId != null) {
			_hashmap.put("userId", userId);
		}
		if (!StringUtils.isBlank(ticketType)) {
			_hashmap.put("ticketType", ticketType);
		}
		return queryForList("MOON_USER_TICKET.FindMOON_USER_TICKET", _hashmap);
	}
}
