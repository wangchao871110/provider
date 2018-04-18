package com.xfs.ss.exception;

import com.alibaba.fastjson.JSON;
import com.xfs.ss.packet.Data;
import com.xfs.ss.packet.Head;

/**
 * 名称： 接口层异常类
 * 功能：
 * 作者: 张智  zhangzhi.bj@gmail.com
 * 时间: 14-4-29  上午10:46
 */
public class CmdException extends  Exception{
    private int code;
    private String msg;

    private String ext;

    private String json;

    public CmdException(){

    }

    public CmdException(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void append(String ext){
        this.ext = ext;
    }

    public String getJson(){
        if(json == null ){
            Data resp = new Data();
            Head head = new Head();
            resp.setHead(head);
            head.setSt(code);
            if(ext==null) {
                head.setMsg(msg);
            }else {
                head.setMsg(msg + ext);
            }
            this.json = JSON.toJSONString(resp);
        }
        return json;
    }

}
