package com.xfs.wx.message.dto;

/**
 * 文本卡片消息
 * @author : luyong@xinfushe.com
 * @version : V1.0
 * @date : 2017/10/13 16:48
 */
public class TextcardMessage extends WxMessage implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    //消息类型，此时固定为：text
    private String msgtype = "textcard";


    private Textcard textcard = new Textcard();

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public Textcard getTextcard() {
        return textcard;
    }

    public void setTextcard(Textcard textcard) {
        this.textcard = textcard;
    }
}
