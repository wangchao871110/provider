package com.xfs.op.dao;
import java.util.*;

import org.springframework.stereotype.Repository;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.common.ContextInfo;
import com.xfs.op.model.RecTagDic;

/**
 * RecTagDicDao
 * @author:yangfw@xinfushe.com
 * @date:2017/02/22 10:59:03
 */
@Repository
public class RecTagDicDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("REC_TAG_DIC.CountFindAllREC_TAG_DIC", null );
        return ret.intValue();
	}

	public RecTagDic findByPK(RecTagDic obj) {
		Object ret = queryForObject("REC_TAG_DIC.FindByPK", obj );
		if(ret !=null)
			return (RecTagDic)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<RecTagDic> freeFind(RecTagDic obj) {
		return queryForList("REC_TAG_DIC.FreeFindREC_TAG_DIC", obj );
	}

	public int countFreeFind(RecTagDic obj) {
        Integer ret = (Integer) queryForObject("REC_TAG_DIC.CountFreeFindREC_TAG_DIC", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<RecTagDic> freeFind(RecTagDic obj, int pageIndex, int pageSize) {
		return getPaginatedList("REC_TAG_DIC.FreeFindREC_TAG_DIC", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<RecTagDic> freeFind(RecTagDic obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof RecTagDic){
	    	_hashmap = ((RecTagDic)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("REC_TAG_DIC.FreeFindREC_TAG_DICOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<RecTagDic> freeFind(RecTagDic obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof RecTagDic){
	    	_hashmap = ((RecTagDic)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("REC_TAG_DIC.FreeFindREC_TAG_DICOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti,Collection<RecTagDic> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<RecTagDic> it = objColl.iterator();
            while ( it.hasNext() ) {
            	RecTagDic oneObj = (RecTagDic)it.next();
				i += save(cti, oneObj );
            }
        }
        return i;
	}

	public int save(ContextInfo cti,RecTagDic vo) {
        RecTagDic obj = (RecTagDic) vo;

        if(exists( obj ) ) {
            return update( cti, obj );
        } else {
            return insert( cti, obj );
        }
	}

	public int insert(ContextInfo cti,RecTagDic obj) {

		obj.fixup();
        return insert(cti,"REC_TAG_DIC.InsertREC_TAG_DIC", obj );
	}

	public int update(ContextInfo cti,RecTagDic obj) {

		obj.fixup();
		return update(cti, "REC_TAG_DIC.UpdateREC_TAG_DIC", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti,RecTagDic obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "REC_TAG_DIC.DeleteREC_TAG_DIC", obj );

	}

	public int removeObjectTree(ContextInfo cti,RecTagDic obj) {

        obj.fixup();
        RecTagDic oldObj = ( RecTagDic )queryForObject("REC_TAG_DIC.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete( cti,"REC_TAG_DIC.DeleteREC_TAG_DIC", oldObj );

	}

	public boolean exists(RecTagDic vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        RecTagDic obj = (RecTagDic) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("REC_TAG_DIC.CountREC_TAG_DIC", obj );
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
	 * 通过多类型查询标签
	 *  @param   parem
	 * @return    : java.util.List<com.xfs.op.model.RecTagDic>
	 *  @createDate   : 2017/2/23 19:50
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2017/2/23 19:50
	 *  @updateAuthor  :
	 */
	public List<RecTagDic> findTagByTypes(Map<String,Object> parem){

		return queryForList("REC_TAG_DIC.FindTagByTypes", parem);
	}

	/**
	 * 查询用户分类标签
	 *  @param   parem
	 * @return    : java.util.List<com.xfs.op.model.RecTagDic>
	 *  @createDate   : 2017/3/1 10:39
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2017/3/1 10:39
	 *  @updateAuthor  :
	 */
	public List<RecTagDic> findUserTagById(Map<String,Object> parem){
		return queryForList("REC_TAG_DIC.FindUserTagById", parem);
	}
}
