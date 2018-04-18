package com.xfs.corp.enums;

/**
 * 员工参保状态
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2017-01-17 15:14
 */
public enum InsuranceState {

    INCREASING("增员中"),ON("在缴中"),DECREASING("减员中"),DECREASED("已减员"),OFF("未缴纳"),UNDEAL("暂不处理");

    private String des;//描述

    private InsuranceState(String _des){
        this.des = _des;
    }

    @Override
    public String toString() {
        return String.valueOf (this.des);
    }
}
