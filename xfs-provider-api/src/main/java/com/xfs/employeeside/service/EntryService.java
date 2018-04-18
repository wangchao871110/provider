package com.xfs.employeeside.service;

import com.xfs.common.Result;

/**
 * 入职
 *
 * @author hongjie
 * @date 2017/3/15.18:08
 */
public interface EntryService {

    /**
     * 根据公司名称， 获取公司 所在的城市列表
     *
     * @param cpId
     * @return
     */
    Result getCityList(Integer cpId);

    /**
     * 获取 需要填写的字段名称
     *
     * @param areaId
     * @param bsTypeId
     * @return
     */
    Result getNeedField(Integer areaId, Integer bsTypeId);


    /**
     * 获取 参保类型
     *
     * @param areaId
     * @return
     */
    Result getInsuranceType(Integer areaId);

    //  Result getInsuranceLivingTypeByAreaId(Integer areaId);


}
