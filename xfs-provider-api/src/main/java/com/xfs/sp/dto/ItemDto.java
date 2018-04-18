package com.xfs.sp.dto;

/**
 * Created with IntelliJ IDEA.
 * User: wangxuesong
 * Date: 16-7-27
 * Time: 下午9:27
 * To change this template use File | Settings | File Templates.
 */
public class ItemDto {

    private Integer itemId;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    private String itemName;
}
