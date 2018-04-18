package com.xfs.base.dao;

import com.xfs.common.util.BaseSqlMapDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wangxuesong
 * Date: 16-12-15
 * Time: 下午2:13
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class BsSysVerFuncDAO extends BaseSqlMapDao {

    //温馨提示：
    //以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写



    //2016/09/05 10:52:21




    public List<Map<String,Object>> freeFindByVersionId(Integer versionId, String funcType){
        HashMap<String,Object> _hashMap = new HashMap<String,Object>();
        _hashMap.put("ver_id", versionId);
        _hashMap.put("func_type", funcType);
        _hashMap.put("dr", 0);
        return  ( List<Map<String,Object>>)queryForList("BS_SYS_VERFUNC.FreeFindBS_SYS_VER_FUNC", _hashMap);
    }
}
