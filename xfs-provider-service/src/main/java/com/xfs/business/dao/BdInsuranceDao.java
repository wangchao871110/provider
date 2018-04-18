package com.xfs.business.dao;

import java.util.*;

import org.springframework.stereotype.Repository;

import com.xfs.business.model.BdInsurance;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * BdInsuranceDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/25 12:02:59
 */
@Repository
public class BdInsuranceDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BD_INSURANCE.CountFindAllBD_INSURANCE", null );
        return ret.intValue();
	}

	public BdInsurance findByPK(BdInsurance obj) {
		Object ret = queryForObject("BD_INSURANCE.FindByPK", obj );
		if(ret !=null)
			return (BdInsurance)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BdInsurance> freeFind(BdInsurance obj) {
		return queryForList("BD_INSURANCE.FreeFindBD_INSURANCE", obj );
	}

	public int countFreeFind(BdInsurance obj) {
        Integer ret = (Integer) queryForObject("BD_INSURANCE.CountFreeFindBD_INSURANCE", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BdInsurance> freeFind(BdInsurance obj, int pageIndex, int pageSize) {
		return getPaginatedList("BD_INSURANCE.FreeFindBD_INSURANCE", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BdInsurance> freeFind(BdInsurance obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdInsurance){
	    	_hashmap = ((BdInsurance)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BD_INSURANCE.FreeFindBD_INSURANCEOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BdInsurance> freeFind(BdInsurance obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdInsurance){
	    	_hashmap = ((BdInsurance)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BD_INSURANCE.FreeFindBD_INSURANCEOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BdInsurance> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BdInsurance> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BdInsurance oneObj = (BdInsurance)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BdInsurance vo) {
        BdInsurance obj = (BdInsurance) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BdInsurance obj) {

		obj.fixup();
        return insert(cti, "BD_INSURANCE.InsertBD_INSURANCE", obj );
	}

	public int update(ContextInfo cti, BdInsurance obj) {

		obj.fixup();
		return update(cti, "BD_INSURANCE.UpdateBD_INSURANCE", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BdInsurance obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BD_INSURANCE.DeleteBD_INSURANCE", obj );

	}

	public int removeObjectTree(ContextInfo cti, BdInsurance obj) {

        obj.fixup();
        BdInsurance oldObj = ( BdInsurance )queryForObject("BD_INSURANCE.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BD_INSURANCE.DeleteBD_INSURANCE", oldObj );

	}

	public boolean exists(BdInsurance vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BdInsurance obj = (BdInsurance) vo;

        keysFilled = keysFilled && ( null != obj.getInsuranceId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BD_INSURANCE.CountBD_INSURANCE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/25 12:02:59


	/**
	 * 	通过地区查询配置险种
	 *  @param   [areaId] 地区belongcity
	 * @return    : java.util.List<com.xfs.business.model.BdInsurance>
	 *  @createDate   : 10:37 2016/11/15
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 10:37 2016/11/15
	 *  @updateAuthor  :
	 */
	public List<BdInsurance> findBdInsuranceByAreaId(Integer areaId){

		Map<String,Object> statment = new HashMap<>();
		statment.put("areaId",areaId);
		return queryForList("FreeFindBD_INSURANCE_BY_AREA_Ordered",statment);
	}

	/**通过地区、户口类型、参保日期 反查险种
	*  @param   areaId, livingType, period]
	* @return    : java.util.List<com.xfs.business.model.BdInsurance>
	*  @createDate   : 2016/12/2 21:05
	*  @author          : yangfw@xinfushe.com
	*  @version         : v1.0
	*  @updateDate     : 2016/12/2 21:05
	*  @updateAuthor  :
	*/
	public List<BdInsurance> findBdInsuranceByAreaIdAndLivingType(Integer areaId,String livingType,String period){
		Map<String,Object> statment = new HashMap<>();
		statment.put("areaId",areaId);
		statment.put("livingType",livingType);
		statment.put("period",period);
		return queryForList("BD_INSURANCE.FreeFindBD_INSURANCE_BY_AREA_LIVINGTYPE_Ordered",statment);
	}
}
