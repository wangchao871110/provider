package com.xfs.business.enums;

/**
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2017-06-08 10:35
 */
public enum  TaskStateFiled {
	// 任务单状态
	TODO_DSH_APPLICATION("TODO", 7001, "待信息审核"),
	TODO_BSDD_APPLICATION("TODO", 7002, "待报送当地"),
	TODO_SJCL_APPLICATION("TODO", 7003, "待收集材料"),
	TODO_TJZF_APPLICATION("TODO", 7004, "待提交政府"),
	TODO_TJSQ_APPLICATION("TODO", 7200, "提交申请"),
	TODO_ONLINE_APPLICATION("TODO", 7143, "网申中"),
	TODO_WAITING_APPLICATION("TODO", 7144, "等待申报"),
    SUBMIT_WAITING_APPLICATION("SUBMIT", 7144, "等待申报"),
    SUBMIT_ONLINE_APPLICATION("SUBMIT", 7143, "网申中"),
    COMPLETED_APPLICATION("COMPLETED", 7009, "办理成功"),
    ERROR_APPLICATION("ERROR", 7000, "办理异常"),
    CLOSED_APPLICATION("CLOSED", -1, "关闭");

    private String taskType;
    private Integer stateFiledId;
    private String stateFiledName;

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public Integer getStateFiledId() {
        return stateFiledId;
    }

    public void setStateFiledId(Integer stateFiledId) {
        this.stateFiledId = stateFiledId;
    }

    public String getStateFiledName() {
        return stateFiledName;
    }

    public void setStateFiledName(String stateFiledName) {
        this.stateFiledName = stateFiledName;
    }

    public static Integer getTaskStateFiledId(String stateFiledName){
        for (TaskStateFiled c : TaskStateFiled.values()) {
            if (c.getStateFiledName().equals(stateFiledName)) {
                return c.getStateFiledId();
            }
        }
        return null;
    }

    TaskStateFiled(String taskType, Integer stateFiledId, String stateFiledName){
        this.taskType = taskType;
        this.stateFiledId = stateFiledId;
        this.stateFiledName = stateFiledName;
    }

}
