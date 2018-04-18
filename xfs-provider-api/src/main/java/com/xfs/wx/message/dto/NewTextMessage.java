package com.xfs.wx.message.dto;

public class NewTextMessage extends WxMessage implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    //消息类型，此时固定为：text
    private String msgtype = "text";

    //消息内容，最长不超过2048个字节
    private NewText text;

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

	public NewText getText() {
		return text;
	}

	public void setText(NewText text) {
		this.text = text;
	}
}
