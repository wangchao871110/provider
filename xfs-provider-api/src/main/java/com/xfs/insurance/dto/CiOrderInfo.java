package com.xfs.insurance.dto;


import com.xfs.insurance.model.SpsCiOrder;

/**
 * @author lihon
 * @create 2016/9/9 18:24
 */
public class CiOrderInfo extends SpsCiOrder {

    private Integer itemId;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }
}
