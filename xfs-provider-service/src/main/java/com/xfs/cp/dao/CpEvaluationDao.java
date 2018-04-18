package com.xfs.cp.dao;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.cp.model.CpEvaluation;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * CpEvaluationDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/08 10:55:21
 */
@Repository
public class CpEvaluationDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CP_EVALUATION.CountFindAllCP_EVALUATION", null );
        return ret.intValue();
	}

	public CpEvaluation findByPK(CpEvaluation obj) {
		Object ret = queryForObject("CP_EVALUATION.FindByPK", obj );
		if(ret !=null)
			return (CpEvaluation)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CpEvaluation> freeFind(CpEvaluation obj) {
		return queryForList("CP_EVALUATION.FreeFindCP_EVALUATION", obj );
	}

	public int countFreeFind(CpEvaluation obj) {
        Integer ret = (Integer) queryForObject("CP_EVALUATION.CountFreeFindCP_EVALUATION", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<CpEvaluation> freeFind(CpEvaluation obj, int pageIndex, int pageSize) {
		return getPaginatedList("CP_EVALUATION.FreeFindCP_EVALUATION", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<CpEvaluation> freeFind(CpEvaluation obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CpEvaluation){
	    	_hashmap = ((CpEvaluation)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CP_EVALUATION.FreeFindCP_EVALUATIONOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<CpEvaluation> freeFind(CpEvaluation obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CpEvaluation){
	    	_hashmap = ((CpEvaluation)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CP_EVALUATION.FreeFindCP_EVALUATIONOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<CpEvaluation> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<CpEvaluation> it = objColl.iterator();
            while ( it.hasNext() ) {
            	CpEvaluation oneObj = (CpEvaluation)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, CpEvaluation vo) {
        CpEvaluation obj = (CpEvaluation) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, CpEvaluation obj) {

		obj.fixup();
        return insert(cti, "CP_EVALUATION.InsertCP_EVALUATION", obj );
	}

	public int update(ContextInfo cti, CpEvaluation obj) {

		obj.fixup();
		return update(cti, "CP_EVALUATION.UpdateCP_EVALUATION", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, CpEvaluation obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "CP_EVALUATION.DeleteCP_EVALUATION", obj );

	}

	public int removeObjectTree(ContextInfo cti, CpEvaluation obj) {

        obj.fixup();
        CpEvaluation oldObj = ( CpEvaluation )queryForObject("CP_EVALUATION.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "CP_EVALUATION.DeleteCP_EVALUATION", oldObj );

	}

	public boolean exists(CpEvaluation vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CpEvaluation obj = (CpEvaluation) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CP_EVALUATION.CountCP_EVALUATION", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 10:55:21
	
	/**
	 * 
	 * @method comments: 根据服务商ID获取用户点评和合作满意度
	 * @author   name  : wangchao  
	 * @create   time  : 2016年9月12日 下午3:35:13 
	 * @param cpEvaluation
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月12日 下午3:35:13 wangchao 创建
	 *
	 */
	public CpEvaluation findEvaluation(CpEvaluation cpEvaluation) {
		return (CpEvaluation) queryForObject("CP_EVALUATION.findEvaluation", cpEvaluation );
	}


	public Integer queryVoucherCount(CpEvaluation cpEvaluation){
		Integer ret = (Integer) queryForObject("CP_EVALUATION.FreeFindAccount_Count", cpEvaluation );
		return ret.intValue();
	}

	public List<CpEvaluation> queryVoucherList(CpEvaluation cpEvaluation,Integer pageIndex,Integer pageSize){
		return (List<CpEvaluation>)getPaginatedList("CP_EVALUATION.FreeFindAccount_Manage", cpEvaluation, pageIndex, pageSize );
	}
}
