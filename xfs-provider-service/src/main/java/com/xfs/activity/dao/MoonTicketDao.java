package com.xfs.activity.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.activity.model.MoonTicket;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * MoonTicketDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/06 11:20:21
 */
@Repository
public class MoonTicketDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("MOON_TICKET.CountFindAllMOON_TICKET", null );
        return ret.intValue();
	}

	public MoonTicket findByPK(MoonTicket obj) {
		Object ret = queryForObject("MOON_TICKET.FindByPK", obj );
		if(ret !=null)
			return (MoonTicket)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<MoonTicket> freeFind(MoonTicket obj) {
		return queryForList("MOON_TICKET.FreeFindMOON_TICKET", obj );
	}

	public int countFreeFind(MoonTicket obj) {
        Integer ret = (Integer) queryForObject("MOON_TICKET.CountFreeFindMOON_TICKET", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<MoonTicket> freeFind(MoonTicket obj, int pageIndex, int pageSize) {
		return getPaginatedList("MOON_TICKET.FreeFindMOON_TICKET", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<MoonTicket> freeFind(MoonTicket obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof MoonTicket){
	    	_hashmap = ((MoonTicket)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("MOON_TICKET.FreeFindMOON_TICKETOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<MoonTicket> freeFind(MoonTicket obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof MoonTicket){
	    	_hashmap = ((MoonTicket)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("MOON_TICKET.FreeFindMOON_TICKETOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<MoonTicket> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<MoonTicket> it = objColl.iterator();
            while ( it.hasNext() ) {
            	MoonTicket oneObj = (MoonTicket)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, MoonTicket vo) {
        MoonTicket obj = (MoonTicket) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, MoonTicket obj) {

		obj.fixup();
        return insert(cti, "MOON_TICKET.InsertMOON_TICKET", obj );
	}

	public int update(ContextInfo cti, MoonTicket obj) {

		obj.fixup();
		return update(cti, "MOON_TICKET.UpdateMOON_TICKET", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, MoonTicket obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "MOON_TICKET.DeleteMOON_TICKET", obj );

	}

	public int removeObjectTree(ContextInfo cti, MoonTicket obj) {

        obj.fixup();
        MoonTicket oldObj = ( MoonTicket )queryForObject("MOON_TICKET.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "MOON_TICKET.DeleteMOON_TICKET", oldObj );

	}

	public boolean exists(MoonTicket vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        MoonTicket obj = (MoonTicket) vo;

        keysFilled = keysFilled && ( null != obj.getTicketId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("MOON_TICKET.CountMOON_TICKET", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/06 11:20:21
	
	
	
}
