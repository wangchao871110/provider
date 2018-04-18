package com.xfs.youshutong.dao;/**
 * @author hongjie
 * @date 2017/5/17.16:36
 */

import com.xfs.common.util.BaseSqlMapDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 * @author
 * @create 2017-05-17 16:36
 **/
@Repository
public class InsuranceSaasDao  extends BaseSqlMapDao {
    public List<Map> getUserInfo(Map param) {
        return queryForList("INSURANCE_SAAS.getUserInfo", param);
    }

    public List<Map> getOrderInfo(Map param) {
        return queryForList("INSURANCE_SAAS.getOrderInfo", param);
    }

    public List<Map> getCustomeInfo(Map param) {
        return queryForList("INSURANCE_SAAS.getCustomeInfo", param);
    }
    public List<Map> getUserLoginInfo(Map param){
        return queryForList("INSURANCE_SAAS.getUserLoginInfo", param);
    }

    public void makeLoginData(Map param) {
        update(null, "INSURANCE_SAAS.updateLoginData", param);
        update(null, "INSURANCE_SAAS.updateLoginDataRadio", param);
    }
}
