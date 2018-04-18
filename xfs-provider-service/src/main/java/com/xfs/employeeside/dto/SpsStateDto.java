package com.xfs.employeeside.dto;

/**
 * 某个任务类型的状态
 *
 * @author hongjie
 * @create 2017-03-22 10:40
 **/
public class SpsStateDto {

    private Integer id;
    private Integer bdBsareatypeId;
    private Integer sysDicitemId;
    private Integer step;
    private String code;
    private String name;
    private String time;
    private String location;
    private String state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBdBsareatypeId() {
        return bdBsareatypeId;
    }

    public void setBdBsareatypeId(Integer bdBsareatypeId) {
        this.bdBsareatypeId = bdBsareatypeId;
    }

    public Integer getSysDicitemId() {
        return sysDicitemId;
    }

    public void setSysDicitemId(Integer sysDicitemId) {
        this.sysDicitemId = sysDicitemId;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


}
