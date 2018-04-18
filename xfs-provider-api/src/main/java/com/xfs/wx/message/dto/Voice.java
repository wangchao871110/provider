package com.xfs.wx.message.dto;

/**
 * 语音
 *
 * @author ivhhs
 * @date 2014.10.16
 */
public class Voice implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    // 媒体文件id
    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}