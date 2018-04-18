package com.xfs.youshutong.dao;/**
 * @author hongjie
 * @date 2017/5/17.9:22
 */

import com.xfs.common.util.BaseSqlMapDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 查社保，友数通
 *
 * @author
 * @create 2017-05-17 9:22
 **/
@Repository
public class SearchInsuranceDao extends BaseSqlMapDao {

    public List<Map> getUserInfo(Map param) {
        return queryForList("SEARCH_INSURANCE.getUserInfo", param);
    }


    public List<Map> getOrderInfo(Map param) {
        return queryForList("SEARCH_INSURANCE.getOrderInfo", param);
    }

    public List<Map> getAllOrderUserId(Map param) {
        return queryForList("SEARCH_INSURANCE.getAllOrderUserId", param);
    }







}
