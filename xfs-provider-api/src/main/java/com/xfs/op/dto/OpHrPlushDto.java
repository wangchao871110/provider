package com.xfs.op.dto;/**
 * @author hongjie
 * @date 2017/4/26.14:37
 */

import java.util.Date;

/**
 * hr头条评论
 *
 * @author zhanghj
 * @create 2017-04-26 14:37
 **/
public class OpHrPlushDto {
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUpvoteNum() {
        return upvoteNum;
    }

    public void setUpvoteNum(Integer upvoteNum) {
        this.upvoteNum = upvoteNum;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public String getParentHeadImg() {
        return parentHeadImg;
    }

    public void setParentHeadImg(String parentHeadImg) {
        this.parentHeadImg = parentHeadImg;
    }

    public String getParentContent() {
        return parentContent;
    }

    public void setParentContent(String parentContent) {
        this.parentContent = parentContent;
    }

    public String getParentNickName() {
        return parentNickName;
    }

    public void setParentNickName(String parentNickName) {
        this.parentNickName = parentNickName;
    }

    public Date getParentCreateDt() {
        return parentCreateDt;
    }

    public void setParentCreateDt(Date parentCreateDt) {
        this.parentCreateDt = parentCreateDt;
    }

    private Integer id;
    // 用户id
    private Integer userId;
    //用户名
    private String nickName;
    //头像
    private String headImg;
    //评论内容
    private String content;
    // 点赞 数量
    private Integer upvoteNum;
    // 评论时间
    private Date createDt;

    //父级评论头像
    private String parentHeadImg;
    //父级评论
    private String parentContent;
    //父级用户名
    private String parentNickName;
    // 父级 评论时间
    private Date parentCreateDt;

    private String mongoArticleId;

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMongoArticleId() {
        return mongoArticleId;
    }

    public void setMongoArticleId(String mongoArticleId) {
        this.mongoArticleId = mongoArticleId;
    }
}
