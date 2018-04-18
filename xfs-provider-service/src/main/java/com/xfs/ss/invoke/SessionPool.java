package com.xfs.ss.invoke;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.apache.mina.core.session.IoSession;

public class SessionPool {

	private static HashMap<Long, IoSession> sessionMap = new HashMap<Long, IoSession>();

	private static HashMap<String, Long> spkeyMap = new HashMap<String, Long>();

	private static SessionPool pool = null;

	private SessionPool() {

	}

	public static SessionPool getInstance() {
		if (pool == null) {
			pool = new SessionPool();
		}
		return pool;
	}

	
	/**
	 * 客户端连接的时候放到Session 池子里里面session
	 * @param session
	 */
	public void putSession(IoSession session) {
		sessionMap.put(session.getId(), session);
	}

	/**
	 * 根据SessionID 得到Session
	 * @param sessionId
	 * @return
	 */
	public IoSession getSession(Long sessionId) {
		return sessionMap.get(sessionId);
	}

	
	/**
	 * 某个服务商对应的SessionID
	 * @param spkey
	 * @param sessionId
	 */
	public void putSpkey(String spkey, Long sessionId) {
		spkeyMap.put(spkey, sessionId);
	}

	
	
	/**
	 * 得到SessionID
	 * @param spkey
	 * @return
	 */
	public Long getSessionId(String spkey) {
		return spkeyMap.get(spkey);
	}

	
	
	/**
	 * 删除某个Session
	 * @param sessionId
	 */
	public void removeSession(Long sessionId) {
		sessionMap.remove(sessionId);
		Set<String> keySet = spkeyMap.keySet();
		Iterator<String> it = keySet.iterator();
		if(it !=null){
			String removeKey = null;
			while(it.hasNext()){
				String key = it.next();
				if(sessionId == spkeyMap.get(key)){
					removeKey= key;
					break;
				}
			}
			
			if(removeKey != null){
				spkeyMap.remove(removeKey);
			}
		}
//		spkeyMap.remove(key)
	}
	
	
	public  HashMap<String, Long>  getOnlineSp(){
		return spkeyMap;
	}
	
	

}
