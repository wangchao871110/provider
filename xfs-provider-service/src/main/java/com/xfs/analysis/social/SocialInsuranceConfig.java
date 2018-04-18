package com.xfs.analysis.social;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2017-03-30 17:42
 */
@Component
public class SocialInsuranceConfig {
    /**
     * 实现类集合
     */
    private List<SocialInsurance> socialInsurances = new ArrayList<>();

    /**
     * 增加委托事件对象
     * @param socialInsurance
     */
    public void addEventObject(SocialInsurance socialInsurance){
        socialInsurances.add(socialInsurance);
    }

    /**
     * 获取所有委托事件
     * @return
     */
    public List<SocialInsurance> queryEventList(){
        return socialInsurances;
    }
}
