package com.xfs.insurance.dto;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.xfs.insurance.model.BsCiGroup;

/**
 * @author lihon
 * @create 2016/9/5 21:11
 */
public class CiGroup extends BsCiGroup {
    /**
     * 子条目编码
     */
    private List<Integer> childItems;

    /**
     * 子服务项编码
     */
    List<CiProduct> itemLists;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 服务商编码
     */
    Integer spId;

    private Integer productId;


    public List<Integer> getChildItems() {

        return childItems;
    }

    public void setChildItems(List<Integer> childItems) {

        this.childItems = childItems;
    }

    public List<CiProduct> getItemLists() {

        return itemLists;
    }

    public void setItemLists(List<CiProduct> itemLists) {

        this.itemLists = itemLists;
    }

    public Integer getSpId() {
        return spId;
    }

    public void setSpId(Integer spId) {
        this.spId = spId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public void addItem(CiProduct ciProduct) {
        if (null == itemLists) {
            itemLists = new ArrayList<>();
        }
        itemLists.add(ciProduct);
    }
}
