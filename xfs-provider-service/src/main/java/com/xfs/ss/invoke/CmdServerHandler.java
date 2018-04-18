package com.xfs.ss.invoke;


import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.alibaba.fastjson.JSONObject;



/**
 * 
 * 
 * 命令处理类
 * 
 */
public class CmdServerHandler extends IoHandlerAdapter  {
	
    private final static Logger log = Logger.getLogger(CmdServerHandler.class);

	public CmdServerHandler() {
		 
	}

	
	@Override
    public void messageReceived(IoSession session, Object message) throws Exception {
    	log.info("SessionId:"+session.getId()+"messageReceived:"+message );
    	if(message == null ){
    		return;
    	}
    	
    	if("keep".equals(message.toString())){
    		return;
    	}
    	
    	JSONObject dataMap= (JSONObject) JSONObject.parse(message.toString());
    	String spkey =dataMap.getString("spkey");
    	SessionPool.getInstance().putSpkey(spkey, session.getId());
    }

	
    /**
     * session 打开的时候放到map中
     */
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		log.info("SessionId:"+session.getId()+"sessionOpened" );
		SessionPool.getInstance().putSession(session);
	}
	
	

	@Override
    public void sessionClosed(IoSession session) throws Exception {
		log.info("SessionId:"+session.getId()+"sessionClosed" );
		SessionPool.getInstance().removeSession(session.getId());
		session.close(true);
    }

}
