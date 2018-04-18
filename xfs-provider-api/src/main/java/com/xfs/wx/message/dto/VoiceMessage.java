package com.xfs.wx.message.dto;

/**
 * 语音消息
 * @author : luyong@xinfushe.com
 * @version : V1.0
 * @date : 2017/10/13 16:48
 */
public class VoiceMessage extends WxMessage implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    //消息类型，此时固定为：text
    private String msgtype = "voice";

    private Voice voice;

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public Voice getVoice() {
        return voice;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
    }
}
