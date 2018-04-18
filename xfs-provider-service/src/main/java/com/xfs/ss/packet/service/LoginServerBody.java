package com.xfs.ss.packet.service;

import com.xfs.ss.packet.Body;

/**
 * 名称：
 * 功能：
 * 作者: 张智  jgszz@163.com
 * 时间: 16-5-16  上午11:36
 */
public class LoginServerBody extends Body {
    private String spkey;
    private String validcode;

    public String getSpkey() {
        return spkey;
    }

    public void setSpkey(String spkey) {
        this.spkey = spkey;
    }

    public String getValidcode() {
        return validcode;
    }

    public void setValidcode(String validcode) {
        this.validcode = validcode;
    }
}
