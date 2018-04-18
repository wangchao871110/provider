package com.xfs.corp.dao;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.corp.model.CmEmployeeConfig;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * 员工端配置
 * @author 	: wangchao
 * @date 	: 2017年6月7日 上午9:58:51
 * @version 	: V1.0
 */
@Repository
public class CmEmployeeConfigDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CM_EMPLOYEE_CONFIG.CountFindAllCM_EMPLOYEE_CONFIG", null );
        return ret.intValue();
	}

	public CmEmployeeConfig findByPK(Integer pk) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("pk", pk );
		Object ret = queryForObject("CM_EMPLOYEE_CONFIG.FindByPK", _hashmap );
		if(ret !=null)
			return (CmEmployeeConfig)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CmEmployeeConfig> freeFind(CmEmployeeConfig obj) {
		return queryForList("CM_EMPLOYEE_CONFIG.FreeFindCM_EMPLOYEE_CONFIG", obj );
	}

	public int countFreeFind(CmEmployeeConfig obj) {
        Integer ret = (Integer) queryForObject("CM_EMPLOYEE_CONFIG.CountFreeFindCM_EMPLOYEE_CONFIG", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<CmEmployeeConfig> freeFind(CmEmployeeConfig obj, int pageIndex, int pageSize) {
		return getPaginatedList("CM_EMPLOYEE_CONFIG.FreeFindCM_EMPLOYEE_CONFIG", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<CmEmployeeConfig> freeFind(CmEmployeeConfig obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CmEmployeeConfig){
	    	_hashmap = ((CmEmployeeConfig)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CM_EMPLOYEE_CONFIG.FreeFindCM_EMPLOYEE_CONFIGOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<CmEmployeeConfig> freeFind(CmEmployeeConfig obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CmEmployeeConfig){
	    	_hashmap = ((CmEmployeeConfig)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CM_EMPLOYEE_CONFIG.FreeFindCM_EMPLOYEE_CONFIGOrdered", _hashmap, pageIndex, pageSize );
	}

	public int save(ContextInfo cti, CmEmployeeConfig vo) {
        CmEmployeeConfig obj = (CmEmployeeConfig) vo;

        if(exists( obj ) ) {
            return update(cti, obj );
        } else {
            return insert(cti, obj );
        }
	}

	public int insert(ContextInfo cti, CmEmployeeConfig obj) {

		obj.fixup();
        return insert(cti,"CM_EMPLOYEE_CONFIG.InsertCM_EMPLOYEE_CONFIG", obj );
	}

	public int update(ContextInfo cti, CmEmployeeConfig obj) {

		obj.fixup();
		return update(cti, "CM_EMPLOYEE_CONFIG.UpdateCM_EMPLOYEE_CONFIG", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, CmEmployeeConfig obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "CM_EMPLOYEE_CONFIG.DeleteCM_EMPLOYEE_CONFIG", obj );

	}

	public boolean exists(CmEmployeeConfig vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CmEmployeeConfig obj = (CmEmployeeConfig) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CM_EMPLOYEE_CONFIG.CountCM_EMPLOYEE_CONFIG", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}

	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/06/07 09:54:56
	
	/**
	 * 获取员工端配置
	 *  @param vo
	 *  @return 
	 *	@return 			: CmEmployeeConfig 
	 *  @createDate  	: 2017年6月7日 上午10:52:01
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年6月7日 上午10:52:01
	 *  @updateAuthor  :
	 */
	public CmEmployeeConfig findFempConfigDetails(CmEmployeeConfig vo) {
		Object ret = queryForObject("CM_EMPLOYEE_CONFIG.findFempConfigDetails", vo);
		if (ret != null)
			return (CmEmployeeConfig) ret;
		else
			return null;
	}


	/**
	 * @Title: 查询某个 公司 开放员工端的 城市数量
	 * @param   cpId 公司id
	 * @return
	 * @createDate 2017/6/7 18:06
	 * @Auther:zhanghongjie【hongjievip6688@163.com】
	 * @version        : v1.0
	 * @updateDate    	:
	 * @updateAuthor  :
	*/
	public List<CmEmployeeConfig> findAllCorpOpenCity(Integer cpId){
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("cpId", cpId );
		return queryForList("CM_EMPLOYEE_CONFIG.findAllCorpOpenCity", _hashmap);
	}
	
}
