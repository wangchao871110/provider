package com.xfs.op.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * 文章类
 * Created by yangf on 2017/3/11.
 */
public class SysCrawlerResult implements java.io.Serializable{
    @JSONField(name="_id")
    private String _id;
    private String pubTime;
    private String tagId;
    private String areaId;
    private String url;
    private String siteUrl;
    private String author;
    private String title;
    private String areaName;
    private String covImg;
    private String context;
    private String siteListurl;
    private String createDt;
    private String siteName;
    private String describe;
    //推荐状态 0否 1是
    private Integer recState;
    //发布状态 0未发布 1待发布 2已发布
    private Integer relState;
    //编辑状态 0未编辑 1已编辑
    private Integer updateState;
    //头条发布日期
    private String pubDate;
    private String modifyBy;
    private String modifyName;
    private String tagName;

    private String modefyDt;

    private String readNum;

    private String createBy;

    private String createName;

    private String showXfsFlag;

    //add by luyong  --start
    private String advertUrl; //广告url

    private String advertImg; //广告图片

    public String getAdvertUrl() {
        return advertUrl;
    }

    public void setAdvertUrl(String advertUrl) {
        this.advertUrl = advertUrl;
    }

    public String getAdvertImg() {
        return advertImg;
    }

    public void setAdvertImg(String advertImg) {
        this.advertImg = advertImg;
    }


    //add by luyong  --  end


    public String getShowXfsFlag() {
        return showXfsFlag;
    }

    public void setShowXfsFlag(String showXfsFlag) {
        this.showXfsFlag = showXfsFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getReadNum() {
        return readNum;
    }

    public void setReadNum(String readNum) {
        this.readNum = readNum;
    }

    public String getModefyDt() {
        return modefyDt;
    }

    public void setModefyDt(String modefyDt) {
        this.modefyDt = modefyDt;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getModifyName() {
        return modifyName;
    }

    public void setModifyName(String modifyName) {
        this.modifyName = modifyName;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCovImg() {
        return covImg;
    }

    public void setCovImg(String covImg) {
        this.covImg = covImg;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getSiteListurl() {
        return siteListurl;
    }

    public void setSiteListurl(String siteListurl) {
        this.siteListurl = siteListurl;
    }

    public String getCreateDt() {
        return createDt;
    }

    public void setCreateDt(String createDt) {
        this.createDt = createDt;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
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
}
