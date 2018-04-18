package com.xfs.op.dao;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.common.ContextInfo;
import com.xfs.op.model.RecUserTag;

/**
 * RecUserTagDao
 * @author:yangfw@xinfushe.com
 * @date:2017/02/22 10:59:03
 */
@Repository
public class RecUserTagDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("REC_USER_TAG.CountFindAllREC_USER_TAG", null );
        return ret.intValue();
	}

	public RecUserTag findByPK(RecUserTag obj) {
		Object ret = queryForObject("REC_USER_TAG.FindByPK", obj );
		if(ret !=null)
			return (RecUserTag)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<RecUserTag> freeFind(RecUserTag obj) {
		return queryForList("REC_USER_TAG.FreeFindREC_USER_TAG", obj );
	}

	public int countFreeFind(RecUserTag obj) {
        Integer ret = (Integer) queryForObject("REC_USER_TAG.CountFreeFindREC_USER_TAG", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<RecUserTag> freeFind(RecUserTag obj, int pageIndex, int pageSize) {
		return getPaginatedList("REC_USER_TAG.FreeFindREC_USER_TAG", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<RecUserTag> freeFind(RecUserTag obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof RecUserTag){
	    	_hashmap = ((RecUserTag)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("REC_USER_TAG.FreeFindREC_USER_TAGOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<RecUserTag> freeFind(RecUserTag obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof RecUserTag){
	    	_hashmap = ((RecUserTag)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("REC_USER_TAG.FreeFindREC_USER_TAGOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti,Collection<RecUserTag> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<RecUserTag> it = objColl.iterator();
            while ( it.hasNext() ) {
            	RecUserTag oneObj = (RecUserTag)it.next();
				i += save(cti, oneObj );
            }
        }
        return i;
	}

	public int save(ContextInfo cti,RecUserTag vo) {
        RecUserTag obj = (RecUserTag) vo;

        if(exists( obj ) ) {
            return update( cti, obj );
        } else {
            return insert( cti, obj );
        }
	}

	public int insert(ContextInfo cti,RecUserTag obj) {

		obj.fixup();
        return insert(cti,"REC_USER_TAG.InsertREC_USER_TAG", obj );
	}

	public int update(ContextInfo cti,RecUserTag obj) {

		obj.fixup();
		return update(cti, "REC_USER_TAG.UpdateREC_USER_TAG", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti,RecUserTag obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "REC_USER_TAG.DeleteREC_USER_TAG", obj );

	}

	public int removeObjectTree(ContextInfo cti,RecUserTag obj) {

        obj.fixup();
        RecUserTag oldObj = ( RecUserTag )queryForObject("REC_USER_TAG.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete( cti,"REC_USER_TAG.DeleteREC_USER_TAG", oldObj );

	}

	public boolean exists(RecUserTag vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        RecUserTag obj = (RecUserTag) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("REC_USER_TAG.CountREC_USER_TAG", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/02/22 10:59:03

	/**
	 * 插入用户默认标签
	 * @param cti
	 * @param recUserTag
	 * @return
	 */
	public int insertDefUserTag(ContextInfo cti,RecUserTag recUserTag){
		return insert(cti,"REC_USER_TAG.Insert_Default_REC_USER_TAG",recUserTag);
	}

	/**
	 * 查询用户tagid集合
	 * @param recUserTag
	 * @return
	 */
	public String findUserTagGroupConcat(RecUserTag recUserTag){
		return (String)queryForObject("REC_USER_TAG.FindUserTagIdGroupConcat",recUserTag);
	}
	/**
	 * 通过用户id删除标签
	 *  @param   cti, recUserTag
	 * @return    : java.lang.Integer
	 *  @createDate   : 2017/2/23 20:01
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2017/2/23 20:01
	 *  @updateAuthor  :
	 */
	public Integer deleteTagByUserId(ContextInfo cti ,RecUserTag recUserTag){
		return delete(cti,"REC_USER_TAG.DeleteREC_USER_TAG_BY_USERID",recUserTag);
	}
}
