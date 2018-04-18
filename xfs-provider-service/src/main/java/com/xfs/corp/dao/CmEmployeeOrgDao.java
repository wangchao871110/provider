package com.xfs.corp.dao;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.corp.model.CmCorp;
import com.xfs.corp.model.CmEmployee;
import com.xfs.corp.model.CmEmployeeOrg;
import org.springframework.stereotype.Repository;
import com.xfs.common.util.BaseSqlMapDao;


/**
 * CmEmployeeOrgDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2017/09/20 16:10:56
 */
@Repository
public class CmEmployeeOrgDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CM_EMPLOYEE_ORG.CountFindAllCM_EMPLOYEE_ORG", null );
        return ret.intValue();
	}

	public CmEmployeeOrg findByPK(Integer pk) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("pk", pk );
		Object ret = queryForObject("CM_EMPLOYEE_ORG.FindByPK", _hashmap );
		if(ret !=null)
			return (CmEmployeeOrg)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CmEmployeeOrg> freeFind(CmEmployeeOrg obj) {
		return queryForList("CM_EMPLOYEE_ORG.FreeFindCM_EMPLOYEE_ORG", obj );
	}

	public int countFreeFind(CmEmployeeOrg obj) {
        Integer ret = (Integer) queryForObject("CM_EMPLOYEE_ORG.CountFreeFindCM_EMPLOYEE_ORG", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<CmEmployeeOrg> freeFind(CmEmployeeOrg obj, int pageIndex, int pageSize) {
		return getPaginatedList("CM_EMPLOYEE_ORG.FreeFindCM_EMPLOYEE_ORG", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<CmEmployeeOrg> freeFind(CmEmployeeOrg obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CmEmployeeOrg){
	    	_hashmap = ((CmEmployeeOrg)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CM_EMPLOYEE_ORG.FreeFindCM_EMPLOYEE_ORGOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<CmEmployeeOrg> freeFind(CmEmployeeOrg obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CmEmployeeOrg){
	    	_hashmap = ((CmEmployeeOrg)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CM_EMPLOYEE_ORG.FreeFindCM_EMPLOYEE_ORGOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti,Collection<CmEmployeeOrg> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<CmEmployeeOrg> it = objColl.iterator();
            while ( it.hasNext() ) {
            	CmEmployeeOrg oneObj = (CmEmployeeOrg)it.next();
				i += save(cti, oneObj );
            }
        }
        return i;
	}

	public int save(ContextInfo cti,CmEmployeeOrg vo) {
        CmEmployeeOrg obj = (CmEmployeeOrg) vo;

        if(exists( obj ) ) {
            return update(cti, obj );
        } else {
            return insert(cti, obj );
        }
	}

	public int insert(ContextInfo cti, CmEmployeeOrg obj) {

		obj.fixup();
        return insert(cti,"CM_EMPLOYEE_ORG.InsertCM_EMPLOYEE_ORG", obj );
	}

	public int update(ContextInfo cti,CmEmployeeOrg obj) {

		obj.fixup();
		return update(cti, "CM_EMPLOYEE_ORG.UpdateCM_EMPLOYEE_ORG", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti,CmEmployeeOrg obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "CM_EMPLOYEE_ORG.DeleteCM_EMPLOYEE_ORG", obj );

	}

	public int removeObjectTree(ContextInfo cti,CmEmployeeOrg obj) {

        obj.fixup();
        CmEmployeeOrg oldObj = ( CmEmployeeOrg )queryForObject("CM_EMPLOYEE_ORG.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete( cti,"CM_EMPLOYEE_ORG.DeleteCM_EMPLOYEE_ORG", oldObj );

	}

	public boolean exists(CmEmployeeOrg vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CmEmployeeOrg obj = (CmEmployeeOrg) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CM_EMPLOYEE_ORG.CountCM_EMPLOYEE_ORG", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/09/20 16:10:56

	/**
	 * 获取同步列表总数
	 * @param vo
	 * @return
	 */
	public Integer querySynDataListCount(CmEmployeeOrg vo){
		Integer ret = (Integer) queryForObject("CM_EMPLOYEE_ORG.querySynDataListCount", vo );
		return ret.intValue();
	}

	/**
	 * 系统自动匹配人数
	 * @param vo
	 * @return
	 */
	public Integer querySynSystemDataList(CmEmployeeOrg vo){
		Integer ret = (Integer) queryForObject("CM_EMPLOYEE_ORG.querySynDataSystemListCount", vo );
		return ret.intValue();
	}

	/**
	 * 获取同步列表
	 * @param vo
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<CmEmployeeOrg> querySynDataList(CmEmployeeOrg vo,Integer pageIndex, Integer pageSize){
		return getPaginatedList("CM_EMPLOYEE_ORG.querySynDataList", vo, pageIndex, pageSize );
	}

	/**
	 * 获取未匹配同步数据列表
	 * @param vo
	 * @return
	 */
	public List<CmEmployeeOrg> queryUnEmpIdSynDataList(CmEmployeeOrg vo){
		return queryForList("CM_EMPLOYEE_ORG.queryUnEmpIdSynDataList", vo);
	}


	/**
	 * 获取需要保存的同步结果
	 * @param vo
	 * @return
	 */
	public List<CmEmployee> querySaveSynOrgData(CmEmployeeOrg vo, CmCorp cmCorp){
		vo.setSignMark(cmCorp.getEmployeeSource());
		return queryForList("CM_EMPLOYEE_ORG.querySaveSynOrgData", vo);
	}

	public int updateOrgData(ContextInfo cti,CmEmployeeOrg obj) {

		obj.fixup();
		return update(cti, "CM_EMPLOYEE_ORG.UpdateCM_EMPLOYEE_ORG_EXP", obj );

	}


	public int updateOrgDataMark(ContextInfo cti,CmEmployeeOrg obj) {

		obj.fixup();
		return update(cti, "CM_EMPLOYEE_ORG.UpdateCM_EMPLOYEE_ORG_MARK", obj );

	}

}
