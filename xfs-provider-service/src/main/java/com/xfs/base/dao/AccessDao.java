package com.xfs.base.dao;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.xfs.common.util.BaseSqlMapDao;

/**
 * 获取权限dao层
 * 
 * @author wangdx
 */
@Repository
public class AccessDao extends BaseSqlMapDao {

    public Integer access(String tableName, String where) {

        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        _hashmap.put("tableName", tableName);
        _hashmap.put("where", where);
        return (Integer) queryForObject("ACCESS.accessCheck", _hashmap);
    }

}
