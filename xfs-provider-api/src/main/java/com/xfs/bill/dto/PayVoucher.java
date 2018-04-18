package com.xfs.bill.dto;

import java.io.Serializable;

/**
 * Created by Administrator on 2016-08-25.
 */
public class PayVoucher  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String searchWord;
    private Integer status;
    private String begin_month;
    private String end_month;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSearchWord() {
        return searchWord;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBegin_month() {
        return begin_month;
    }

    public void setBegin_month(String begin_month) {
        this.begin_month = begin_month;
    }

    public String getEnd_month() {
        return end_month;
    }

    public void setEnd_month(String end_month) {
        this.end_month = end_month;
    }
}
