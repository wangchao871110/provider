package com.xfs.wx.message.dto;

/**
 * 文件消息
 * @author : luyong@xinfushe.com
 * @version : V1.0
 * @date : 2017/10/13 16:48
 */
public class FileMessage extends WxMessage implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    //消息类型，此时固定为：text
    private String msgtype = "file";


    private File file;

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
