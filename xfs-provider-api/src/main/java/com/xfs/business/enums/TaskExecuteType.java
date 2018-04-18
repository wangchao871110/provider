package com.xfs.business.enums;

/**
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2017-06-14 09:43
 */
public enum  TaskExecuteType {

    AUTO_COMPLATE_ADDEXECUTE(true,"ADDANDEXECUTE"),//自动完成任务单，向T添加任务单并要求T执行
    AUTO_COMPLATE_ADD(true,"ADD"),//自动完成任务单，向T添加任务单
    NO_COMPLATE_ADDEXECUTE(false,"ADDANDEXECUTE"),//不自动执行完成，向T添加任务单并要求T执行
    NO_COMPLATE_ADD(false,"ADD");//不自动执行完成，指向T添加任务单

    private boolean isAutoComplate;//是否自动执行
    private String executeType;//ADD  ADDANDEXECUTE

    public boolean isAutoComplate() {
        return isAutoComplate;
    }

    public void setAutoComplate(boolean autoComplate) {
        isAutoComplate = autoComplate;
    }

    public String getExecuteType() {
        return executeType;
    }

    public void setExecuteType(String executeType) {
        this.executeType = executeType;
    }

    TaskExecuteType(boolean isAutoComplate,String executeType){
        this.isAutoComplate = isAutoComplate;
        this.executeType = executeType;
    }
}
