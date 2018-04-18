package com.xfs.ss.exception;

/**
 * 名称：
 * 功能：
 * 作者: 张智  zhangzhi.bj@gmail.com
 * 时间: 14-5-4  上午11:24
 */
public class CmdExceptionFactory {

    public static CmdException CMD_ERR_OK = new CmdException(200,"");
    public static CmdException CMD_ERR_SESSION = new CmdException(901,"你长时间未操作，请重试。");
    public static CmdException CMD_ERR_CMD = new CmdException(404,"指令错误。");
    
}
