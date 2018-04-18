package com.xfs.cp.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.cp.model.CpAccount;


/**
 * CpAccountDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/11 21:59:58
 */
@Repository
public class CpAccountDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CP_ACCOUNT.CountFindAllCP_ACCOUNT", null );
        return ret.intValue();
	}

	public CpAccount findByPK(CpAccount obj) {
		Object ret = queryForObject("CP_ACCOUNT.FindByPK", obj );
		if(ret !=null)
			return (CpAccount)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CpAccount> freeFind(CpAccount obj) {
		return queryForList("CP_ACCOUNT.FreeFindCP_ACCOUNT", obj );
	}

	public int countFreeFind(CpAccount obj) {
        Integer ret = (Integer) queryForObject("CP_ACCOUNT.CountFreeFindCP_ACCOUNT", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<CpAccount> freeFind(CpAccount obj, int pageIndex, int pageSize) {
		return getPaginatedList("CP_ACCOUNT.FreeFindCP_ACCOUNT", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<CpAccount> freeFind(CpAccount obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CpAccount){
	    	_hashmap = ((CpAccount)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CP_ACCOUNT.FreeFindCP_ACCOUNTOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<CpAccount> freeFind(CpAccount obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CpAccount){
	    	_hashmap = ((CpAccount)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CP_ACCOUNT.FreeFindCP_ACCOUNTOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<CpAccount> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<CpAccount> it = objColl.iterator();
            while ( it.hasNext() ) {
            	CpAccount oneObj = (CpAccount)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, CpAccount vo) {
        CpAccount obj = (CpAccount) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, CpAccount obj) {

		obj.fixup();
        return insert(cti, "CP_ACCOUNT.InsertCP_ACCOUNT", obj );
	}

	public int update(ContextInfo cti, CpAccount obj) {

		obj.fixup();
		return update(cti, "CP_ACCOUNT.UpdateCP_ACCOUNT", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, CpAccount obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "CP_ACCOUNT.DeleteCP_ACCOUNT", obj );

	}

	public int removeObjectTree(ContextInfo cti, CpAccount obj) {

        obj.fixup();
        CpAccount oldObj = ( CpAccount )queryForObject("CP_ACCOUNT.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "CP_ACCOUNT.DeleteCP_ACCOUNT", oldObj );

	}

	public boolean exists(CpAccount vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CpAccount obj = (CpAccount) vo;

        keysFilled = keysFilled && ( null != obj.getSpId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CP_ACCOUNT.CountCP_ACCOUNT", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}

	/**
	 * 
	 * @method comments: 获取账户信息
	 * @author   name  : baoyu  
	 * @create   time  : 2016年9月11日 下午10:05:21 
	 * @param cpAccount
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月11日 下午10:05:21 baoyu 创建
	 *
	 */
	public CpAccount getAccountById(CpAccount cpAccount) {
		return (CpAccount) queryForObject("CP_ACCOUNT.getAccountById",cpAccount);
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/11 21:59:58
	
	
	
}
