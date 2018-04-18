package com.xfs.base.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.base.model.SecWx;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * SecWxDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/01/25 15:21:28
 */
@Repository
public class SecWxDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SEC_WX.CountFindAllSEC_WX", null );
        return ret.intValue();
	}

	/**
	 * 根据id查询 微信号管理表
	 * 
	 *  @param obj
	 *  @return 
	 *	@return 			: SecWx 
	 *  @createDate  	: 2016年11月15日 上午10:46:57
	 *  @author         	:  
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016年11月15日 上午10:46:57
	 *  @updateAuthor  :
	 */
	public SecWx findByPK(SecWx obj) {
		Object ret = queryForObject("SEC_WX.FindByPK", obj );
		if(ret !=null)
			return (SecWx)ret;
		else 
			return null;
	}
	
	/**
	 * 根据各字段条件带模糊查询  微信号管理表 
	 * 
	 *  @param obj
	 *  @return 
	 *	@return 			: List<SecWx> 
	 *  @createDate  	: 2016年11月15日 上午10:47:27
	 *  @author         	: 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016年11月15日 上午10:47:27
	 *  @updateAuthor  :
	 */
	@SuppressWarnings("unchecked")
	public List<SecWx> freeFind(SecWx obj) {
		return queryForList("SEC_WX.FreeFindSEC_WX", obj );
	}

	/**
	 * 根据各字段条件带模糊查询  微信号管理表 记录数
	 * 
	 *  @param obj
	 *  @return 
	 *	@return 			: int 
	 *  @createDate  	: 2016年11月15日 上午10:47:52
	 *  @author         	: 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016年11月15日 上午10:47:52
	 *  @updateAuthor  :
	 */
	public int countFreeFind(SecWx obj) {
        Integer ret = (Integer) queryForObject("SEC_WX.CountFreeFindSEC_WX", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SecWx> freeFind(SecWx obj, int pageIndex, int pageSize) {
		return getPaginatedList("SEC_WX.FreeFindSEC_WX", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SecWx> freeFind(SecWx obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SecWx){
	    	_hashmap = ((SecWx)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SEC_WX.FreeFindSEC_WXOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SecWx> freeFind(SecWx obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SecWx){
	    	_hashmap = ((SecWx)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SEC_WX.FreeFindSEC_WXOrdered", _hashmap, pageIndex, pageSize );
	}

	public void saveAll(ContextInfo cti, Collection<SecWx> objColl) {
        if ( objColl != null ) {
            Iterator<SecWx> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SecWx oneObj = (SecWx)it.next();
				save(cti, oneObj );
            }
        }
	}
	
	public int saveAll1(ContextInfo cti, Collection<SecWx> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SecWx> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SecWx oneObj = (SecWx)it.next();
				i += save1(cti, oneObj );
            }
        }
        return i;
	}

	public void save(ContextInfo cti, SecWx vo) {
        SecWx obj = (SecWx) vo;

        if(exists( obj ) ) {
            update(cti, obj );
        } else {
            insert(cti, obj );
        }
	}
	
	public int save1(ContextInfo cti, SecWx vo) {
        SecWx obj = (SecWx) vo;

        if(exists( obj ) ) {
            return update1(cti, obj );
        } else {
            return insert1(cti, obj );
        }
	}

	public void insert(ContextInfo cti, SecWx obj) {

		obj.fixup();
        insert(cti, "SEC_WX.InsertSEC_WX", obj );
	}
	
	public int insert1(ContextInfo cti, SecWx obj) {

		obj.fixup();
        return insert(cti, "SEC_WX.InsertSEC_WX", obj );
	}

	public void update(ContextInfo cti, SecWx obj) {

		obj.fixup();
		update(cti, "SEC_WX.UpdateSEC_WX", obj );

	}
	
	public int update1(ContextInfo cti, SecWx obj) {

		obj.fixup();
		return update(cti, "SEC_WX.UpdateSEC_WX", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public void remove(ContextInfo cti, SecWx obj) {

        if ( obj == null ) { 
            return; 
        }
		
		obj.fixup();

        delete(cti, "SEC_WX.DeleteSEC_WX", obj );

	}
	
	public int remove1(ContextInfo cti, SecWx obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SEC_WX.DeleteSEC_WX", obj );

	}

	public void removeObjectTree(ContextInfo cti, SecWx obj) {

        obj.fixup();
        SecWx oldObj = ( SecWx )queryForObject("SEC_WX.FindByPK", obj );
        if ( oldObj == null ) { 
            return; 
        }


        delete(cti, "SEC_WX.DeleteSEC_WX", oldObj );

	}
	
	public int removeObjectTree1(ContextInfo cti, SecWx obj) {

        obj.fixup();
        SecWx oldObj = ( SecWx )queryForObject("SEC_WX.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SEC_WX.DeleteSEC_WX", oldObj );

	}

	public boolean exists(SecWx vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SecWx obj = (SecWx) vo;

        keysFilled = keysFilled && ( null != obj.getWxId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SEC_WX.CountSEC_WX", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/01/25 15:21:28
	
	
	
}
