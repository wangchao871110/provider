package com.xfs.insurance.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xfs.insurance.model.CpCiProd;

/**
 * Created by lihon on 2016/11/1.
 */
public class CpCiProdDetail extends CpCiProd {

    private static final Map<String, String> chargeWayNames = new HashMap<>();

    static {
        chargeWayNames.put("1", "人/月");
        chargeWayNames.put("2", "人/年");
    }

    /**
     * 承保方案列表
     */
    private List<CiScheme> schemeList;

    /**
     * 保险公司名称
     */
    private String insurerName;

    public String getChargeWayName() {

        return chargeWayNames.get(super.getChargeWay());
    }

    public void addScheme(CiScheme scheme) {

        if (null == schemeList) {
            schemeList = new ArrayList<>();
        }
        schemeList.add(scheme);
    }

    public List<CiScheme> getSchemeList() {

        return schemeList;
    }

    public void setSchemeList(List<CiScheme> schemeList) {

        this.schemeList = schemeList;
    }

    public String getInsurerName() {

        return insurerName;
    }

    public void setInsurerName(String insurerName) {

        this.insurerName = insurerName;
    }

}
