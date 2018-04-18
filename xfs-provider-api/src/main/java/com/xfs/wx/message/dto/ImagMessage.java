package com.xfs.wx.message.dto;

/**
 * 图片消息
 * @author : luyong@xinfushe.com
 * @version : V1.0
 * @date : 2017/10/13 16:41
 */
public class ImagMessage extends WxMessage implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String msgtype = "image";


    private Image image ;


    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
