package com.xfs.wx.message.dto;

/**
 * 图文消息（mpnews）
 * mpnews类型的图文消息，跟普通的图文消息一致，唯一的差异是图文内容存储在企业微信。
 * 多次发送mpnews，会被认为是不同的图文，阅读、点赞的统计会被分开计算。
 * @author : luyong@xinfushe.com
 * @version : V1.0
 * @date : 2017/10/13 16:48
 */
public class MpnewsMessage extends WxMessage implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    //消息类型，此时固定为：text
    private String msgtype = "file";


    private Mpnews mpnews;

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public Mpnews getMpnews() {
        return mpnews;
    }

    public void setMpnews(Mpnews mpnews) {
        this.mpnews = mpnews;
    }
}
