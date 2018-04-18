package com.xfs.base.dao;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.base.model.BsSysStateReport;
import com.xfs.common.ContextInfo;
import com.xfs.common.constant.IStaticVarConstant;
import com.xfs.common.util.BaseSqlMapDao;


/** 
 * @author 	: wangxs@xinfushe.com
 * @date 	: 2016-12-14 12:00
 * @version 	: V1.0
 */
@Repository
public class BsSysStateReportDao extends BaseSqlMapDao implements IStaticVarConstant {
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BS_SYS_STATEREPORT.CountFindAllBS_SYS_STATEREPORT", null );
        return ret.intValue();
	}

	public BsSysStateReport findByPK(BsSysStateReport obj) {
		Object ret = queryForObject("BS_SYS_STATEREPORT.FindByPK", obj );
		if(ret !=null)
			return (BsSysStateReport)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BsSysStateReport> freeFind(BsSysStateReport obj) {
		return queryForList("BS_SYS_STATEREPORT.FreeFindBS_SYS_STATEREPORT", obj );
	}

	public int countFreeFind(BsSysStateReport obj) {
        Integer ret = (Integer) queryForObject("BS_SYS_STATEREPORT.CountFreeFindBS_SYS_STATEREPORT", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BsSysStateReport> freeFind(BsSysStateReport obj, int pageIndex, int pageSize) {
		return getPaginatedList("BS_SYS_STATEREPORT.FreeFindBS_SYS_STATEREPORT", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BsSysStateReport> freeFind(BsSysStateReport obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsSysStateReport){
	    	_hashmap = ((BsSysStateReport)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BS_SYS_STATEREPORT.FreeFindBS_SYS_STATEREPORTOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BsSysStateReport> freeFind(BsSysStateReport obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsSysStateReport){
	    	_hashmap = ((BsSysStateReport)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BS_SYS_STATEREPORT.FreeFindBS_SYS_STATEREPORTOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BsSysStateReport> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BsSysStateReport> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BsSysStateReport oneObj = (BsSysStateReport)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BsSysStateReport vo) {
        BsSysStateReport obj = (BsSysStateReport) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BsSysStateReport obj) {

		obj.fixup();
        return insert(cti, "BS_SYS_STATEREPORT.InsertBS_SYS_STATEREPORT", obj );
	}

	public int update(ContextInfo cti, BsSysStateReport obj) {

		obj.fixup();
		return update(cti, "BS_SYS_STATEREPORT.UpdateBS_SYS_STATEREPORT", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BsSysStateReport obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BS_SYS_STATEREPORT.DeleteBS_SYS_STATEREPORT", obj );

	}

	public int removeObjectTree(ContextInfo cti, BsSysStateReport obj) {

        obj.fixup();
        BsSysStateReport oldObj = ( BsSysStateReport )queryForObject("BS_SYS_STATEREPORT.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BS_SYS_STATEREPORT.DeleteBS_SYS_STATEREPORT", oldObj );

	}

	public boolean exists(BsSysStateReport vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BsSysStateReport obj = (BsSysStateReport) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BS_SYS_STATEREPORT.CountBS_SYS_STATEREPORT", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/23 14:33:27

	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/05 10:52:21

    public Map findBsSysStateReport(BsSysStateReport obj){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("owner", obj.getOwner());
        map.put("dr", 0);
        map.put("ownerType",obj.getOwnerType());
        map.put("attributeName", obj.getAttributeName());//新手指导
        return (Map)queryForObject("BS_SYS_STATEREPORT.findReportByUserId",map);
    }

    public Map findBsSysStateReport(Integer userId){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("owner", userId);
        map.put("dr", 0);
        map.put("ownerType",CMCORPTYPE_SERVICE);
        map.put("attributeName","VERSIONMESSAGE");//新手指导
       // map.put("attributeValue","1");//1已经操作过新手指导
        return (Map)queryForObject("BS_SYS_STATEREPORT.findReportByUserId",map);
    }


    public int insertVersionMessage(ContextInfo cti,BsSysStateReport obj) {

        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        _hashmap.put("owner", obj.getOwner());
        _hashmap.put("ownerType", obj.getOwnerType());
        _hashmap.put("attributeName", obj.getAttributeName());
        _hashmap.put("attributeValue", obj.getAttributeValue());
        _hashmap.put("createBy", cti==null?null:cti.getUserId());
        _hashmap.put("cpId", obj.getCpId());
        _hashmap.put("areaId", obj.getAreaId());
        _hashmap.put("createDt",new Date());
        _hashmap.put("dr", 0);
        if(null != obj.getId()){
            _hashmap.put("id", obj.getId());
            return update(cti, "BS_SYS_STATEREPORT.Update_Version_Message", _hashmap);
        }else{
            return insert(cti, "BS_SYS_STATEREPORT.insertNewGuidance", _hashmap);
        }
    }

    public int updateVersionMessage(ContextInfo cti, BsSysStateReport obj) {
        return update(cti, "BS_SYS_STATEREPORT.Update_Version_Message", obj);
    }

    /**
     * 查询是否弹出消息框
     *  @param obj
     *  @return 
     *	@return 			: BsSysStateReport 
     *  @createDate  	: 2017年4月17日 上午11:31:22
     *  @author         	: wangchao 
     *  @version        	: v1.0
     *  @updateDate    	: 2017年4月17日 上午11:31:22
     *  @updateAuthor  :
     */
	public List<BsSysStateReport> findIsAlert(BsSysStateReport obj) {
		return queryForList("BS_SYS_STATEREPORT.findIsAlert", obj);
	}

	
}
