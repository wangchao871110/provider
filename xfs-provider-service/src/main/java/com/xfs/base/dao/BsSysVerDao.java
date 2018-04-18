package com.xfs.base.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xfs.common.constant.IStaticVarConstant;
import org.springframework.stereotype.Repository;

import com.xfs.common.util.BaseSqlMapDao;


/** 
 * @author   : wangxs@xinfushe.com
 * @date 	: 2016-12-14 12:00
 * @version 	: V1.0
 */
@Repository
public class BsSysVerDao extends BaseSqlMapDao implements IStaticVarConstant{
	

	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/05 10:52:21

    public List<Map<String,Object>> freeFindBsSysVer(){
        HashMap<String,Object> _hashMap = new HashMap<String,Object>();
        _hashMap.put("owner_type",CMCORPTYPE_SERVICE);
        _hashMap.put("dr", 0);
        return  ( List<Map<String,Object>>)queryForList("BS_SYS_VER.FreeFindBS_SYS_VER", _hashMap);
    }





    public Integer countBsSysStateReport(Integer userId){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("owner", userId);
        map.put("dr", 0);
        map.put("ownerType",CMCORPTYPE_SERVICE);
        map.put("attributeName","VERSIONMESSAGE");//新手指导
       // map.put("attributeValue","1");//1已经操作过新手指导
        Integer ret = (Integer) queryForObject("BS_SYS_STATE_REPORT.checkNewGuidanceByUserId",map);
        return ret.intValue();
    }
	
}
