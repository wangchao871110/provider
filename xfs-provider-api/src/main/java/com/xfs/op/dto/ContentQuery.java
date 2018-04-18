package com.xfs.op.dto;

/**
 * 文章查询类
 * Created by yangf on 2017/3/10.
 */
public class ContentQuery implements java.io.Serializable{
    //文章类型
    private Integer tagId;
    //推荐状态 0否 1是
    private Integer recState;
    //发布状态 0未发布 1待发布 2已发布
    private Integer relState;
    //编辑状态 0未编辑 1已编辑
    private Integer updateState;
    private String title;

    //add by luyong 开始时间(创建时间)
    private String startCreateTime;
    //add by luyong 结束时间(创建时间)
    private String endCreateTime;

    //add by luyong 开始时间(修改时间)
    private String startModTime;
    //add by luyong 结束时间(修改时间)
    private String endModTime;


    private String modifyName;

    private String createName;

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getModifyName() {
        return modifyName;
    }

    public void setModifyName(String modifyName) {
        this.modifyName = modifyName;
    }

    public String getStartCreateTime() {
        return startCreateTime;
    }

    public void setStartCreateTime(String startCreateTime) {
        this.startCreateTime = startCreateTime;
    }

    public String getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(String endCreateTime) {
        this.endCreateTime = endCreateTime;
    }

    public String getStartModTime() {
        return startModTime;
    }

    public void setStartModTime(String startModTime) {
        this.startModTime = startModTime;
    }

    public String getEndModTime() {
        return endModTime;
    }

    public void setEndModTime(String endModTime) {
        this.endModTime = endModTime;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getRecState() {
        return recState;
    }

    public void setRecState(Integer recState) {
        this.recState = recState;
    }

    public Integer getRelState() {
        return relState;
    }

    public void setRelState(Integer relState) {
        this.relState = relState;
    }

    public Integer getUpdateState() {
        return updateState;
    }

    public void setUpdateState(Integer updateState) {
        this.updateState = updateState;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
