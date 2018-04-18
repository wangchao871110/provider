package com.xfs.wx.message.dto;

/**
 * 图文消息
 *
 * @author ivhhs
 * @date 2014.10.16
 */
public class NewsMessage extends WxMessage implements java.io.Serializable {

    private static final long serialVersionUID = 1L;


    //消息类型，此时固定为：text
    private String msgtype = "news";

    private News news;

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }
}