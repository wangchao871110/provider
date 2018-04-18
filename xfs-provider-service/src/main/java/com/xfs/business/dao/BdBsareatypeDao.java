package com.xfs.business.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.xfs.business.dto.BsTypeAreaFiledDto;
import org.springframework.stereotype.Repository;

import com.xfs.business.dto.BsAreaTypeVo;
import com.xfs.business.model.BdBsareatype;
import com.xfs.business.model.BdBstype;
import com.xfs.business.model.SpsTask;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * BdBsareatypeDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/05/24 11:25:00
 */
@Repository
public class BdBsareatypeDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BD_BSAREATYPE.CountFindAllBD_BSAREATYPE", null );
        return ret.intValue();
	}

	public BdBsareatype findByPK(BdBsareatype obj) {
		Object ret = queryForObject("BD_BSAREATYPE.FindByPK", obj );
		if(ret !=null)
			return (BdBsareatype)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BdBsareatype> freeFind(BdBsareatype obj) {
		return queryForList("BD_BSAREATYPE.FreeFindBD_BSAREATYPE", obj );
	}

	public int countFreeFind(BdBsareatype obj) {
        Integer ret = (Integer) queryForObject("BD_BSAREATYPE.CountFreeFindBD_BSAREATYPE", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BdBsareatype> freeFind(BdBsareatype obj, int pageIndex, int pageSize) {
		return getPaginatedList("BD_BSAREATYPE.FreeFindBD_BSAREATYPE", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BdBsareatype> freeFind(BdBsareatype obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdBsareatype){
	    	_hashmap = ((BdBsareatype)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BD_BSAREATYPE.FreeFindBD_BSAREATYPEOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BdBsareatype> freeFind(BdBsareatype obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BdBsareatype){
	    	_hashmap = ((BdBsareatype)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BD_BSAREATYPE.FreeFindBD_BSAREATYPEOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BdBsareatype> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BdBsareatype> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BdBsareatype oneObj = (BdBsareatype)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BdBsareatype vo) {
        BdBsareatype obj = (BdBsareatype) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BdBsareatype obj) {

		obj.fixup();
        return insert(cti, "BD_BSAREATYPE.InsertBD_BSAREATYPE", obj );
	}

	public int update(ContextInfo cti, BdBsareatype obj) {

		obj.fixup();
		return update(cti, "BD_BSAREATYPE.UpdateBD_BSAREATYPE", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BdBsareatype obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BD_BSAREATYPE.DeleteBD_BSAREATYPE", obj );

	}

	public int removeObjectTree(ContextInfo cti, BdBsareatype obj) {

        obj.fixup();
        BdBsareatype oldObj = ( BdBsareatype )queryForObject("BD_BSAREATYPE.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BD_BSAREATYPE.DeleteBD_BSAREATYPE", oldObj );

	}

	public boolean exists(BdBsareatype vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BdBsareatype obj = (BdBsareatype) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BD_BSAREATYPE.CountBD_BSAREATYPE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/05/24 11:25:00

	/**
	 * 查询模板属性配置
	 * @param obj
	 * @param pageIndex
	 * @param pageSize
     * @return
     */
	@SuppressWarnings("unchecked")
	public List<BsAreaTypeVo> freeFindVo(BdBsareatype obj, int pageIndex, int pageSize) {
		return getPaginatedList("BD_BSAREATYPE.findBsareatypeVo", obj, pageIndex, pageSize );
	}

	/**
	 * 通过id查询业务类型地区 VO
	 * @param obj
	 * @return
     */
	public BsAreaTypeVo findVoByPK(BdBsareatype obj) {
		Object ret = queryForObject("BD_BSAREATYPE.FindVoByPK", obj );
		if(ret !=null)
			return (BsAreaTypeVo)ret;
		else
			return null;
	}

	/**
	 *  业务类型地区 VO
	 * @param bdBsareatype
	 * @return
     */
	public BdBsareatype findBdBsAreaType(BdBsareatype bdBsareatype){
		Object ret = queryForObject("BD_BSAREATYPE.findBdBsAreaType", bdBsareatype );
		if(ret !=null)
			return (BdBsareatype)ret;
		else
			return null;
	}

	public int  insertBsTypeArea(ContextInfo cti, List<BdBsareatype> bsareatypes){
		return insert(cti, "BD_BSAREATYPE.insertBsTypeArea",bsareatypes);

	}

	/**
	 * 根据城市id 查询 业务类型信息
	 *
	 * @author lifq
	 *
	 * 2016年5月21日  下午5:36:10
	 */
	public List<Map<String,Object>> findBstypeByCity(BdBsareatype vo){
		return queryForList("BD_BSAREATYPE.findBstypeByCity",vo);
	}

	/**
	 * 根据城市id 类型 查询 业务类型信息
	 *
	 * @return
	 */
	public List<Map<String,Object>> findBstypeByCityAndType(Integer areaId,String insuType,String bstypeIn){
		Map<String,Object> param = new HashMap<>();
		param.put("areaId",areaId);
		param.put("insuType",insuType);
		param.put("bstypeIn",bstypeIn);
		return queryForList("BD_BSAREATYPE.findBstypeByCityAndType",param);
	}

	/**
	 * 根据城市id 类型 查询 业务类型信息
	 *
	 * @return
	 */
	public List<Map<String,Object>> findBstypeByCityAndNotType(Integer areaId,Integer[] typeNotIn){
		Map<String,Object> param = new HashMap<>();
		param.put("areaId",areaId);
		param.put("typeNotIn",typeNotIn);
		return queryForList("BD_BSAREATYPE.findBstypeByCityAndNotType",param);
	}


	/**
	 * 根据城市id查询业务类型、待处理总数
	 *
	 * @param spId
	 * @param vo
	 * @return
	 */
	public List<Map<String, Object>> getBstypeByArea(Integer spId, BdBsareatype vo, BdBstype bstype) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		if(vo != null){
			_hashmap = vo.toHashMap();
		}
		if (bstype != null) {
			_hashmap.put("insuranceFundType", bstype.getInsuranceFundType());
		}
		_hashmap.put("spId", spId);
		return queryForList("BD_BSAREATYPE.getBstypeByArea", _hashmap);
	}

	public List<Map<String, Object>> getBstypeByArea2(Integer spId, SpsTask task, BdBstype bstype) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		if (bstype != null) {
			_hashmap.put("insuranceFundType", bstype.getInsuranceFundType());
		}
		_hashmap.put("spId", spId);
		_hashmap.put("schemeId", task.getSchemeId());
		_hashmap.put("onlineStatus", task.getOnlineStatus());
		return queryForList("BD_BSAREATYPE.getBstypeByArea2", _hashmap);
	}

	/**
	 *  根据企业方案所在地区获取业务对应的字段信息
	 *  @param   cpId
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate  	: 2017-03-23 15:53
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-03-23 15:53
	 *  @updateAuthor  :
	 */
	public List<BsTypeAreaFiledDto> findBdBsAreaTypeBySchemeAreaId(Integer cpId, String bstypeId) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("cpId",cpId);
		_hashmap.put("bstypeId",bstypeId);
		return queryForList("BD_BSAREATYPE.findBdBsAreaTypeBySchemeAreaId", _hashmap);
	}

	public List<BsTypeAreaFiledDto> findBdBsAreaTypeByAreaId() {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		return queryForList("BD_BSAREATYPE.findBdBsAreaTypeByAreaId", _hashmap);
	}


	/**
	 *  获取当前企业对应方案地区下业务字段值信息
	 *  @param   cpId, code
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate  	: 2017-03-23 15:52
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-03-23 15:52
	 *  @updateAuthor  :
	 */
	public List<Map<String, Object>> findBdBsAreaTypeFiledBySchemeAreaId(Integer cpId, String code,Integer schemeId) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("cpId",cpId);
		_hashmap.put("code",code);
		_hashmap.put("schemeId",schemeId);
		return queryForList("BD_BSAREATYPE.findBdBsAreaTypeFiledBySchemeAreaId", _hashmap);
	}

	/**
	 * 根据网申结束日期获取用户信息
	 * @param endDays 结束日期
	 * @return
     */
	public List<Map<String, Object>> findUserByEndDay(String endDays){
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("endDays",endDays);
		return queryForList("BD_BSAREATYPE.findUserByEndDay", _hashmap);
	}
}
