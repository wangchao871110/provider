package com.xfs.op.dao;
import java.util.*;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.common.ContextInfo;
import com.xfs.op.model.OpUserAncillary;

/**
 * OpUserAncillaryDao
 * @author:yangfw@xinfushe.com
 * @date:2017/01/05 20:57:18
 */
@Repository
public class OpUserAncillaryDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("OP_USER_ANCILLARY.CountFindAllOP_USER_ANCILLARY", null );
        return ret.intValue();
	}

	public OpUserAncillary findByPK(OpUserAncillary obj) {
		Object ret = queryForObject("OP_USER_ANCILLARY.FindByPK", obj );
		if(ret !=null)
			return (OpUserAncillary)ret;
		else 
			return null;
	}

	@SuppressWarnings("unchecked")
	public List<OpUserAncillary> freeFind(OpUserAncillary obj) {
		return queryForList("OP_USER_ANCILLARY.FreeFindOP_USER_ANCILLARY", obj );
	}

	public int countFreeFind(OpUserAncillary obj) {
        Integer ret = (Integer) queryForObject("OP_USER_ANCILLARY.CountFreeFindOP_USER_ANCILLARY", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<OpUserAncillary> freeFind(OpUserAncillary obj, int pageIndex, int pageSize) {
		return getPaginatedList("OP_USER_ANCILLARY.FreeFindOP_USER_ANCILLARY", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<OpUserAncillary> freeFind(OpUserAncillary obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof OpUserAncillary){
	    	_hashmap = ((OpUserAncillary)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("OP_USER_ANCILLARY.FreeFindOP_USER_ANCILLARYOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<OpUserAncillary> freeFind(OpUserAncillary obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof OpUserAncillary){
	    	_hashmap = ((OpUserAncillary)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("OP_USER_ANCILLARY.FreeFindOP_USER_ANCILLARYOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti,Collection<OpUserAncillary> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<OpUserAncillary> it = objColl.iterator();
            while ( it.hasNext() ) {
            	OpUserAncillary oneObj = (OpUserAncillary)it.next();
				i += save(cti, oneObj );
            }
        }
        return i;
	}

	public int save(ContextInfo cti,OpUserAncillary vo) {
        OpUserAncillary obj = (OpUserAncillary) vo;

        if(exists( obj ) ) {
            return update( cti, obj );
        } else {
            return insert( cti, obj );
        }
	}

	public int insert(ContextInfo cti,OpUserAncillary obj) {

		obj.fixup();
        return insert(cti,"OP_USER_ANCILLARY.InsertOP_USER_ANCILLARY", obj );
	}

	public int update(ContextInfo cti,OpUserAncillary obj) {

		obj.fixup();
		return update(cti, "OP_USER_ANCILLARY.UpdateOP_USER_ANCILLARY", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti,OpUserAncillary obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "OP_USER_ANCILLARY.DeleteOP_USER_ANCILLARY", obj );

	}

	public int removeObjectTree(ContextInfo cti,OpUserAncillary obj) {

        obj.fixup();
        OpUserAncillary oldObj = ( OpUserAncillary )queryForObject("OP_USER_ANCILLARY.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete( cti,"OP_USER_ANCILLARY.DeleteOP_USER_ANCILLARY", oldObj );

	}

	public boolean exists(OpUserAncillary vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        OpUserAncillary obj = (OpUserAncillary) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("OP_USER_ANCILLARY.CountOP_USER_ANCILLARY", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/01/05 20:57:18

	/**  查询用户信息 如果昵称为空则取企业名称
	 *  @param   obj
	 * @return    : java.util.Map<java.lang.String,java.lang.Object>
	 *  @createDate   : 2017/1/8 14:34
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2017/1/8 14:34
	 *  @updateAuthor  :
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Object> getUserInfo(OpUserAncillary obj) {
		List<Map<String,Object>> users = queryForList("OP_USER_ANCILLARY.FreeFindOP_USER_Info", obj );
		if(CollectionUtils.isNotEmpty(users)){
			return users.get(0);
		}
		return null;
	}
	/**
	 * 检查昵称是否重复
	 *  @param   [obj]
	 * @return    : java.lang.Integer
	 *  @createDate   : 2017/1/17 16:18
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2017/1/17 16:18
	 *  @updateAuthor  :
	 */
	public Integer checkNickNameAlread(OpUserAncillary obj){
		return (Integer)queryForObject("OP_USER_ANCILLARY.Check_NickName_Alread",obj);
	}
	
}
