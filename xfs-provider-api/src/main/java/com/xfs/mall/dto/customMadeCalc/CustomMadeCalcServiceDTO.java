package com.xfs.mall.dto.customMadeCalc;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/12/29.
 */
public class CustomMadeCalcServiceDTO {

    public int id;
    public String code;
    public String name;
    public int num;
    public BigDecimal price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

}
