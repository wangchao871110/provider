package com.xfs.wx.message.dto;

/**
 * @author : luyong@xinfushe.com
 * @version : V1.0
 * @date : 2017/11/23 19:22
 */
public class NewText implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private String content ;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
