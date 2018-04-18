package com.xfs.wx.message.dto;

/**
 * 视频消息
 * @author : luyong@xinfushe.com
 * @version : V1.0
 * @date : 2017/10/13 16:48
 */
public class VideoMessage extends WxMessage implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    //消息类型，此时固定为：text
    private String msgtype = "video";


    private Video video;

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}
