package com.xfs.ss.packet.service;

import com.xfs.ss.packet.Body;

/**
 * 名称：
 * 功能：
 * 作者: 张智  jgszz@163.com
 * 时间: 16-5-16  上午11:36
 */
public class ServicceServerBody extends Body {

    private int cid;

    private int type;

    private boolean isAll;

    private int inOrFun;

    private String usbport;

    public String getUsbport() {
        return usbport;
    }

    public void setUsbport(String usbport) {
        this.usbport = usbport;
    }
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isAll() {
        return isAll;
    }

    public void setAll(boolean isAll) {
        this.isAll = isAll;
    }

    public int getInOrFun() {
        return inOrFun;
    }

    public void setInOrFun(int inOrFun) {
        this.inOrFun = inOrFun;
    }
}
