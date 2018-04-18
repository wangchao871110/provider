package com.xfs.business.dao;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.business.model.BsArearatio;
import com.xfs.business.model.InsuranceFundPeopleType;
import com.xfs.business.model.InsuranceFundType;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * BsArearatioDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/21 20:03:17
 */
@Repository
public class BsArearatioDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BS_AREARATIO.CountFindAllBS_AREARATIO", null );
        return ret.intValue();
	}

	public BsArearatio findByPK(Integer pk) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
        _hashmap.put("pk", pk );
		Object ret = queryForObject("BS_AREARATIO.FindByPK", _hashmap );
		if(ret !=null)
			return (BsArearatio)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BsArearatio> freeFind(BsArearatio obj) {
		return queryForList("BS_AREARATIO.FreeFindBS_AREARATIO", obj );
	}

	public int countFreeFind(BsArearatio obj) {
        Integer ret = (Integer) queryForObject("BS_AREARATIO.CountFreeFindBS_AREARATIO", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BsArearatio> freeFind(BsArearatio obj, int pageIndex, int pageSize) {
		return getPaginatedList("BS_AREARATIO.FreeFindBS_AREARATIO", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BsArearatio> freeFind(BsArearatio obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsArearatio){
	    	_hashmap = ((BsArearatio)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BS_AREARATIO.FreeFindBS_AREARATIOOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BsArearatio> freeFind(BsArearatio obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsArearatio){
	    	_hashmap = ((BsArearatio)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BS_AREARATIO.FreeFindBS_AREARATIOOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BsArearatio> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BsArearatio> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BsArearatio oneObj = (BsArearatio)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BsArearatio vo) {
        BsArearatio obj = (BsArearatio) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BsArearatio obj) {

		obj.fixup();
        return insert(cti, "BS_AREARATIO.InsertBS_AREARATIO", obj );
	}

	public int update(ContextInfo cti, BsArearatio obj) {

		obj.fixup();
		return update(cti, "BS_AREARATIO.UpdateBS_AREARATIO", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BsArearatio obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BS_AREARATIO.DeleteBS_AREARATIO", obj );

	}

	public int removeObjectTree(ContextInfo cti, BsArearatio obj) {

        obj.fixup();
        BsArearatio oldObj = ( BsArearatio )queryForObject("BS_AREARATIO.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BS_AREARATIO.DeleteBS_AREARATIO", oldObj );

	}

	public boolean exists(BsArearatio vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BsArearatio obj = (BsArearatio) vo;

        keysFilled = keysFilled && ( null != obj.getRatioId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BS_AREARATIO.CountBS_AREARATIO", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/21 20:03:17


    public Map<String,Object> findAreaRatioDetail(BsArearatio bsArearatio){
        HashMap<String,Object> _hashMap = new HashMap<String,Object>();
        _hashMap.put("ratioId", bsArearatio.getRatioId());
        return  (Map<String, Object>)queryForObject("BS_AREARATIO.find_AreaRatio_Detail_Info", _hashMap);
    }
	
	public BsArearatio findWithVers(Integer ratioId) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("ratioId", ratioId);
		Object ret = queryForObject("BS_AREARATIO.Find_BS_AREARATIO_with_vers", _hashmap );
		if(ret !=null)
			return (BsArearatio)ret;
		else
			return null;
	}
	
	/**
     * 根据areaId查询 险种信息
     *
     * @author lifq
     *
     * 2016年9月26日  下午2:20:48
     */
    public List<Map<String, Object>> findRatiosByAreaId(Map<String,Object> paraMap){
    	return queryForList("BS_AREARATIO.findRatiosByAreaId", paraMap);
    }


    /**
     * 根据区域id获取社保规则数目
     * @author wangxs
     * @param cityId
     *            城市id
     */
    public Integer countFreeFindRuleByAreaId(Map map) {
        Integer ret = (Integer) queryForObject("BS_AREARATIO.CountFreeFindBS_RULEByAreaId", map);
        return ret.intValue();
    }

    /**
     * 根据区域id查询社保规则比例
     */
    @SuppressWarnings("unchecked")
	/*public List<Map<String, Object>> findRatioListByQueryList(Map map, int pageIndex, int pageSize){
		return getPaginatedList("BS_AREARATIO.FreeFindBS_RuleList_By_QueryList", map, pageIndex, pageSize);
	}*/
    public List<Map<String, Object>> findRatioListByQueryList(Map map){
        return queryForList("BS_AREARATIO.FreeFindBS_RuleList_By_QueryList", map);
    }

    public List<BsArearatio> findRatio(BsArearatio bsArearatio) {
        return queryForList("BS_AREARATIO.FindBS_AREARATIO", bsArearatio );
    }

    public List findInsuranceIdsByAreaId(Integer areaId) {
        List list = queryForList("BS_AREARATIO.FindInsuranceIdsByAreaId", areaId);
        return list;
    }
	/**
	 * 通过 参数查询比例
	 *  @param   map 查询参数 beginMonth,areaId,insuranceId,livingType,ratioId(有值则排除)
	 * @return    : java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate   : 16:55 2016/11/15
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 16:55 2016/11/15
	 *  @updateAuthor  :
	 */
	public List<Map<String, Object>> findRatioByBatchQuery(Map map){

		return queryForList("BS_AREARATIO.FindRatioJoinByQuery", map);
	}

	/**
	 * 查询比例所属险种类型
	 *  @param   ratioId
	 * @return    : java.lang.String
	 *  @createDate   : 10:35 2016/11/18
	 *  @author          : yfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 10:35 2016/11/18
	 *  @updateAuthor  :
	 */
	public String findRatioInsuranceType(Integer ratioId){
		return (String)queryForObject("BS_AREARATIO.FindRatioInsuranceType",ratioId);
	}
	/**
	 * 查询地区社保基数下限中最高的值 小于3500 取3500
	 *  @param   areaId 地区id
	 * @return    : java.lang.Integer
	 *  @createDate   : 2016/11/28 18:15
	 *  @author          : konglc@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/28 18:15
	 *  @updateAuthor  :
	 */
	public BigDecimal findInsuBaseLower(Integer areaId){
		Map param = new HashMap();
		param.put("areaId",areaId);
		Integer baseLower = (Integer)queryForObject("BS_AREARATIO.FindInsuBaseLower",param);
		return  baseLower != null ? new BigDecimal(baseLower) : null;
	}

	public Integer findDefaultRatioId(Integer areaId, Integer insuranceId, String livingType, String period) {
        HashMap<String,Object> _hashMap = new HashMap<String,Object>();
        _hashMap.put("areaId", areaId);
        _hashMap.put("insuranceId", insuranceId);
        _hashMap.put("livingType", livingType);
        _hashMap.put("period", period);
		return (Integer)queryForObject("BS_AREARATIO.findDefaultRatioId",_hashMap);
	}


	/**
	 *	查询地区社保基数下限中最高的值
	 *  @param
	 * @return    :
	 *  @createDate   : 2016/12/7 20:59
	 *  @author          : gaoyf@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/12/7 20:59
	 *  @updateAuthor  :
	 */
	public BigDecimal queryInsuMin(Integer areaId){
		Map param = new HashMap();
		param.put("areaId",areaId);
		return  new BigDecimal((Integer)queryForObject("BS_AREARATIO.queryInsuMin",param));
	}

	/**
	 *  查询地区社保基数上限中最高的值
	 *  @param
	 * @return    :
	 *  @createDate   : 2016/12/7 20:59
	 *  @author          : gaoyf@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/12/7 20:59
	 *  @updateAuthor  :
	 */
	public BigDecimal queryInsuMax(Integer areaId){
		Map param = new HashMap();
		param.put("areaId",areaId);
		return  new BigDecimal((Integer)queryForObject("BS_AREARATIO.queryInsuMax",param));
	}

	/**
	 *	查询地区社保基数公积金下限中最高的值
	 *  @param
	 * @return    :
	 *  @createDate   : 2016/12/7 20:59
	 *  @author          : gaoyf@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/12/7 20:59
	 *  @updateAuthor  :
	 */
	public BigDecimal queryFundMin(Integer areaId){
		Map param = new HashMap();
		param.put("areaId",areaId);
		return  new BigDecimal((Integer)queryForObject("BS_AREARATIO.queryFundMin",param));
	}

	/**
	 *  查询地区社保基数公积金上限中最高的值
	 *  @param
	 * @return    :
	 *  @createDate   : 2016/12/7 20:59
	 *  @author          : gaoyf@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/12/7 20:59
	 *  @updateAuthor  :
	 */
	public BigDecimal queryFundMax(Integer areaId){
		Map param = new HashMap();
		param.put("areaId",areaId);
		return  new BigDecimal((Integer)queryForObject("BS_AREARATIO.queryFundMax",param));
	}

	/**
	 * 获取薪福邦小助手数据
	 *  @param areaId
	 *  @return 
	 *	@return 			: List<InsuranceFundPeopleType> 
	 *  @createDate  	: 2017年1月17日 下午1:45:12
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年1月17日 下午1:45:12
	 *  @updateAuthor  :
	 */
	public List<InsuranceFundPeopleType> findInsuranceFundPeopleType(Integer areaId) {
		Map<String,Object> paraMap = new HashMap<>();
		paraMap.put("areaId", areaId);
		return queryForList("BS_AREARATIO.findInsuranceFundPeopleType", paraMap);
	}

	/**
	 * 获取薪福邦小助手户口类型数据
	 *  @param areaId
	 *  @param peopleTypeCode
	 *  @return 
	 *	@return 			: List<InsuranceFundType> 
	 *  @createDate  	: 2017年1月18日 上午11:01:21
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年1月18日 上午11:01:21
	 *  @updateAuthor  :
	 */
	public List<InsuranceFundType> findInsuranceFundType(Integer areaId, String peopleTypeCode) {
		Map<String,Object> paraMap = new HashMap<>();
		paraMap.put("areaId", areaId);
		paraMap.put("peopleTypeCode", peopleTypeCode);
		return queryForList("BS_AREARATIO.findInsuranceFundType", paraMap);
	}
}
