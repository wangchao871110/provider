package com.xfs.business.dao;

import com.xfs.business.model.SpsFixedpointhospital;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * SpsFixedpointhospitalDao
 *
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/04/20 12:05:11
 */
@Repository
public class SpsFixedpointhospitalDao extends BaseSqlMapDao {

    public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_FIXEDPOINTHOSPITAL.CountFindAllSPS_FIXEDPOINTHOSPITAL", null);
        return ret.intValue();
    }

    public SpsFixedpointhospital findByPK(SpsFixedpointhospital obj) {
        Object ret = queryForObject("SPS_FIXEDPOINTHOSPITAL.FindByPK", obj);
        if (ret != null)
            return (SpsFixedpointhospital) ret;
        else
            return null;
    }

    @SuppressWarnings("unchecked")
    public List<SpsFixedpointhospital> freeFind(SpsFixedpointhospital obj) {
        return queryForList("SPS_FIXEDPOINTHOSPITAL.FreeFindSPS_FIXEDPOINTHOSPITAL", obj);
    }

    public int countFreeFind(SpsFixedpointhospital obj) {
        Integer ret = (Integer) queryForObject("SPS_FIXEDPOINTHOSPITAL.CountFreeFindSPS_FIXEDPOINTHOSPITAL", obj);
        return ret.intValue();
    }

    @SuppressWarnings("unchecked")
    public List<SpsFixedpointhospital> freeFind(SpsFixedpointhospital obj, int pageIndex, int pageSize) {
        return getPaginatedList("SPS_FIXEDPOINTHOSPITAL.FreeFindSPS_FIXEDPOINTHOSPITAL", obj, pageIndex, pageSize);
    }

    @SuppressWarnings("unchecked")
    public List<SpsFixedpointhospital> freeFind(SpsFixedpointhospital obj, String orderByColName) {
        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        if (obj instanceof SpsFixedpointhospital) {
            _hashmap = ((SpsFixedpointhospital) obj).toHashMap();
        }
        _hashmap.put("_orderBy", orderByColName);

        return queryForList("SPS_FIXEDPOINTHOSPITAL.FreeFindSPS_FIXEDPOINTHOSPITALOrdered", _hashmap);
    }

    @SuppressWarnings("unchecked")
    public List<SpsFixedpointhospital> freeFind(SpsFixedpointhospital obj, int pageIndex, int pageSize, String orderByColName) {
        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        if (obj instanceof SpsFixedpointhospital) {
            _hashmap = ((SpsFixedpointhospital) obj).toHashMap();
        }
        _hashmap.put("_orderBy", orderByColName);

        return getPaginatedList("SPS_FIXEDPOINTHOSPITAL.FreeFindSPS_FIXEDPOINTHOSPITALOrdered", _hashmap, pageIndex, pageSize);
    }

    public int saveAll(ContextInfo cti, Collection<SpsFixedpointhospital> objColl) {
        int i = 0;
        if (objColl != null) {
            Iterator<SpsFixedpointhospital> it = objColl.iterator();
            while (it.hasNext()) {
                SpsFixedpointhospital oneObj = (SpsFixedpointhospital) it.next();
                i += save(cti, oneObj);
            }
        }
        return i;
    }

    public int save(ContextInfo cti, SpsFixedpointhospital vo) {
        SpsFixedpointhospital obj = (SpsFixedpointhospital) vo;

        if (exists(obj)) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
    }

    public int insert(ContextInfo cti, SpsFixedpointhospital obj) {

        obj.fixup();
        return insert(cti, "SPS_FIXEDPOINTHOSPITAL.InsertSPS_FIXEDPOINTHOSPITAL", obj);
    }

    public int update(ContextInfo cti, SpsFixedpointhospital obj) {

        obj.fixup();
        return update(cti, "SPS_FIXEDPOINTHOSPITAL.UpdateSPS_FIXEDPOINTHOSPITAL", obj);

    }

    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
    public int remove(ContextInfo cti, SpsFixedpointhospital obj) {

        if (obj == null) {
            return 0;
        }

        obj.fixup();

        return delete(cti, "SPS_FIXEDPOINTHOSPITAL.DeleteSPS_FIXEDPOINTHOSPITAL", obj);

    }

    public int removeObjectTree(ContextInfo cti, SpsFixedpointhospital obj) {

        obj.fixup();
        SpsFixedpointhospital oldObj = (SpsFixedpointhospital) queryForObject("SPS_FIXEDPOINTHOSPITAL.FindByPK", obj);
        if (oldObj == null) {
            return 0;
        }


        return delete(cti, "SPS_FIXEDPOINTHOSPITAL.DeleteSPS_FIXEDPOINTHOSPITAL", oldObj);

    }

    public boolean exists(SpsFixedpointhospital vo) {

        boolean keysFilled = true;
        boolean ret = false;
        SpsFixedpointhospital obj = (SpsFixedpointhospital) vo;

        keysFilled = keysFilled && (null != obj.getId());

        if (keysFilled) {
            Integer retInt = (Integer) queryForObject("SPS_FIXEDPOINTHOSPITAL.CountSPS_FIXEDPOINTHOSPITAL", obj);
            if (retInt != null && retInt.intValue() > 0) {
                ret = true;
            }
        }

        return ret;
    }

    //温馨提示：
    //以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    //2016/04/20 12:05:11


    public List<SpsFixedpointhospital> freetenfive() {
        return queryForList("FreeFindSPS_FIXEDPOINTHOSPITAL_free", new SpsFixedpointhospital());
    }


    //查询医院50条
    @SuppressWarnings("unchecked")
    public List<SpsFixedpointhospital> freeFindOr(SpsFixedpointhospital obj, int pageIndex, int pageSize) {
        return getPaginatedList("SPS_FIXEDPOINTHOSPITAL.FreeFindSPS_FIXEDPOINTHOSPITAL_meng", obj, pageIndex, pageSize);
    }

    public int countFreeFindOr(SpsFixedpointhospital obj) {
        Integer ret = (Integer) queryForObject("SPS_FIXEDPOINTHOSPITAL.CountFreeFindSPS_FIXEDPOINTHOSPITAL_meng", obj);
        return ret.intValue();
    }

    public List<SpsFixedpointhospital> getFixHosptialByRegionId(Integer areaCode) {
        return queryForList("SPS_FIXEDPOINTHOSPITAL.getFixHosptialByRegionId", areaCode);
    }
    public List<SpsFixedpointhospital> getFixHosptialByHospitalCodes(List<String> hospitalCodes) {
        Map<String,Object> obj = new HashMap<String,Object>();
        obj.put("hospitalCodes",hospitalCodes);
        return queryForList("SPS_FIXEDPOINTHOSPITAL.getFixHosptialByHospitalCodes", hospitalCodes);
    }
    
    /**
     * @Title: 根据地区id， 和 医院类型， 查看医院
     * @param   areaCode 地区类型
     * @param  hosptialType 医院类型
     * @return 
     * @createDate 2017/6/12 11:57
     * @Auther:zhanghongjie【hongjievip6688@163.com】
     * @version        : v1.0
     * @updateDate    	: 
     * @updateAuthor  :
    */public List<SpsFixedpointhospital> getHospitalByAreaIdAndType(String condition,Integer reginCode,Integer areaCode,String hosptialType,Integer offset, Integer pagesize) {
        Map<String,Object> obj = new HashMap<String,Object>();
        obj.put("areaCode",areaCode);
        obj.put("hosptialType",hosptialType);
        obj.put("condition",condition);
        obj.put("reginCode",reginCode);
        return getPaginatedList("SPS_FIXEDPOINTHOSPITAL.getHospitalByAreaIdAndType", obj,offset.intValue(), pagesize.intValue());

    }


    /**
     * @Title: 根据地区id， 和 医院类型， 查看医院
     * @param   areaCode 地区类型
     * @param  hosptialType 医院类型
     * @return
     * @createDate 2017/6/12 11:57
     * @Auther:zhanghongjie【hongjievip6688@163.com】
     * @version        : v1.0
     * @updateDate    	:
     * @updateAuthor  :
     */public Integer getHospitalByAreaIdAndTypeCount(String condition,Integer reginCode,Integer areaCode,String hosptialType) {
        Map<String,Object> obj = new HashMap<String,Object>();
        obj.put("areaCode",areaCode);
        obj.put("hosptialType",hosptialType);
        obj.put("condition",condition);
        obj.put("reginCode",reginCode);
        Integer ret = (Integer) queryForObject("SPS_FIXEDPOINTHOSPITAL.getHospitalByAreaIdAndTypeCount",obj);
        return ret.intValue();

    }



    /**
     * @Title: 根据条件查询
     * @param   condition 查询条件
     * @return 
     * @createDate 2017/6/12 11:59
     * @Auther:zhanghongjie【hongjievip6688@163.com】
     * @version        : v1.0
     * @updateDate    	: 
     * @updateAuthor  :
    */
    public List<SpsFixedpointhospital> getHospitalByCondition(String condition){

        Map<String,Object> obj = new HashMap<String,Object>();
        obj.put("condition",condition);
        return queryForList("SPS_FIXEDPOINTHOSPITAL.getHospitalByCondition", obj);
    }


}
