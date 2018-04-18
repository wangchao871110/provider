package com.xfs.youshutong.dao;/**
 * @author hongjie
 * @date 2017/5/18.15:45
 */

import com.xfs.common.util.BaseSqlMapDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 税筹
 * @author
 * @create 2017-05-18 15:45
 **/
@Repository
public class RaiseTaxesDao extends BaseSqlMapDao {

    public List<Map> getUserInfo(Map param) {
        return queryForList("RAISE_TAXES.getUserInfo", param);
    }

    public List<Map> getOrderInfo(Map param) {
        return queryForList("RAISE_TAXES.getOrderInfo", param);
    }

    public List<Map> getCustomeInfo(Map param) {
        return queryForList("RAISE_TAXES.getCustomeInfo", param);
    }

}
