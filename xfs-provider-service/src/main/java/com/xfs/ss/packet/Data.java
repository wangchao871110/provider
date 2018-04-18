package com.xfs.ss.packet;


/**
 * 名称：
 * 功能：
 * 作者: 张智  zhangzhi.bj@gmail.com
 * 时间: 14-4-28  下午6:18
 */
public class Data<T extends  Body> {

    private Head head;
    private T body;

    public Data(){
        head = new Head();
    }


    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
