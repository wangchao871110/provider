package com.xfs.youshutong.model;/**
 * @author hongjie
 * @date 2017/6/9.15:02
 */

/**
 *
 * @author
 * @create 2017-06-09 15:02
 **/
public class session {
    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMdn() {
        return mdn;
    }

    public void setMdn(String mdn) {
        this.mdn = mdn;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String _id;
    private String createTime;
    private String mdn;
    private String token;
    private String userId;

}
