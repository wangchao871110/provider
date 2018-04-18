package com.xfs.youshutong.dao;/**
 * @author hongjie
 * @date 2017/5/18.12:03
 */

import com.xfs.common.util.BaseSqlMapDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 福利 友互通
 *
 * @author
 * @create 2017-05-18 12:03
 **/
@Repository
public class WelfarePlatformDao extends BaseSqlMapDao {


    public List<Map> getUserInfo(Map param) {
        return queryForList("WELFAREPLATFORM.getUserInfo", param);

    }

    public List<Map> getOrderInfo(Map param) {
        return queryForList("WELFAREPLATFORM.getOrderInfo", param);
    }

    // 日志


    public List<Map> getUserLoginInfo(Map param) {
        return queryForList("WELFAREPLATFORM.getUserLoginInfo", param);
    }


    public void makeLoginData(Map param) {

        update(null, "WELFAREPLATFORM.updateLoginData", param);
        update(null, "WELFAREPLATFORM.updateLoginDataRadio", param);
    }


}
