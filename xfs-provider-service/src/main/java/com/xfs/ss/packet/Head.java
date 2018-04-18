package com.xfs.ss.packet;

/**
 * 名称：
 * 功能：
 * 作者: 张智  zhangzhi.bj@gmail.com
 * 时间: 14-4-28  下午6:05
 */
public class Head {
    private String i;
    private String cmd;
    private String spkey;
    private String sign;
    private int st=200;
    private String msg="ok";

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getSpkey() {
        return spkey;
    }

    public void setSpkey(String spkey) {
        this.spkey = spkey;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getSt() {
        return st;
    }

    public void setSt(int st) {
        this.st = st;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
