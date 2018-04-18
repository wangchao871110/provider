package com.xfs.ss.service;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.xfs.common.ContextInfo;
import org.apache.log4j.Logger;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.filter.logging.MdcInjectionFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.xfs.ss.dao.TaskProgressDAO;
import com.xfs.ss.invoke.CmdServerHandler;
import com.xfs.ss.invoke.SessionPool;
import com.xfs.ss.packet.Body;
import com.xfs.ss.packet.Data;
import com.xfs.ss.packet.service.ServicceServerBody;
import com.xfs.ss.util.ICommand;

/**
 * 
 * @author Shuai
 *
 */
@Service
@Scope("prototype")
public class CmdServerService {
	
	private static final Logger logger = Logger.getLogger(CmdServerService.class);
	
	
	@Autowired
	private TaskProgressDAO progressDao;
	/**
	 * 
	 * 启动Tomcat 的时候启动Mina服务器
	 * 
	 * 
	 */
//	@PostConstruct
	
	static {

		NioSocketAcceptor acceptor = new NioSocketAcceptor();
		DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
		MdcInjectionFilter mdcInjectionFilter = new MdcInjectionFilter();
		chain.addLast("mdc", mdcInjectionFilter);
		chain.addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory()));
		try {
			chain.addLast("logger", new LoggingFilter());
			acceptor.setHandler(new CmdServerHandler());
//			String sport = SocketConf.getProperty("port");
//			int port = 0;
//			try {
//				port = Integer.parseInt(sport);
//			} catch (Exception e) {
//				logger.error(e.getMessage());
//			}
			acceptor.bind(new InetSocketAddress(9527));
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}

	
	}
	
	
	
	public void startSocketServer() {

	 

	}
	
	/**
	 *  根据服务商的Key得到一个服务商的Session，然后往Session中写入指令
	 *  写指令的时候带着参数
	 * @param cid
	 * @param btype
	 * @param spKey
	 * @return
	 */
    public 	String  excuteTask(Integer cid,int btype,String spKey ){
    	Long sessionId = SessionPool.getInstance().getSessionId(spKey);
    	if(sessionId == null ||sessionId <= 0){
    		return "false";
    	}
    	IoSession ioSession = SessionPool.getInstance().getSession(sessionId);
    	if(ioSession != null){
    		  Data<ServicceServerBody> login = new Data<ServicceServerBody>();
    		  login.setBody(new ServicceServerBody());
    		  login.getBody().setCid(cid);
    		  login.getBody().setType(btype);
    	      login.getHead().setCmd(ICommand.CMD_EXCUTE);
    	      ioSession.write(JSON.toJSONString(login));
    	}
        return "true";	
    }
    
    public String  stopTaskBot(String spKey ){
    	

		Long sessionId = SessionPool.getInstance().getSessionId(spKey);
    	
    	if(sessionId == null ||sessionId <= 0){
    		//没用安装啊
    		return "N";
    	}
    	IoSession ioSession = SessionPool.getInstance().getSession(sessionId);
    	if(ioSession == null){
    		//没用登录啊
    		 return "N";
    	}
    	  Data<Body> login = new Data<Body>();
	      login.getHead().setCmd(ICommand.CMD_STOP);
	      ioSession.write(JSON.toJSONString(login));
		return "Y";
	
    }
    
    
    public String  isInstalled(String spkey){
    	Long sessionId = SessionPool.getInstance().getSessionId(spkey);
    	if(sessionId == null ||sessionId <= 0){
    		return "N";
    	}
    	IoSession ioSession = SessionPool.getInstance().getSession(sessionId);
    	if(ioSession == null){
    		return "N";
    	}
    	return  "Y";
    }
    
    
	public String isStarted(String spkey) {
		Long sessionId = SessionPool.getInstance().getSessionId(spkey);
    	if(sessionId == null ||sessionId <= 0){
    		return "N";
    	}
    	IoSession ioSession = SessionPool.getInstance().getSession(sessionId);
    	if(ioSession == null){
    		return "N";
    	}
    	return  "Y";
    
	}

	
    
    public String excuteAllTask(Integer cid,String spKey,int inOrFun){
    	
    	Long sessionId = SessionPool.getInstance().getSessionId(spKey);
    	if(sessionId == null ||sessionId <= 0){
    		return "false";
    	}
    	IoSession ioSession = SessionPool.getInstance().getSession(sessionId);
    	if(ioSession != null){
    		  Data<ServicceServerBody> login = new Data<ServicceServerBody>();
    		  ServicceServerBody body = new ServicceServerBody();
    		  login.setBody(body);
    		  login.getBody().setCid(cid);
    		  login.getBody().setAll(true);
    		  login.getBody().setInOrFun(inOrFun);
    	      login.getHead().setCmd(ICommand.CMD_EXCUTE);
    	      ioSession.write(JSON.toJSONString(login));
    	}
        return "true";	
    }

	
	/**
	 * 启动TaskBot 的Frame 界面
	 * @param spKey
	 * @return
	 */
	public String startTaskBot(String spKey) {
		Long sessionId = SessionPool.getInstance().getSessionId(spKey);
    	
    	if(sessionId == null ||sessionId <= 0){
    		//没用安装啊
    		return "N";
    	}
    	IoSession ioSession = SessionPool.getInstance().getSession(sessionId);
    	if(ioSession == null){
    		//没用登录啊
    		 return "N";
    	}
    	  Data<Body> login = new Data<Body>();
	      login.getHead().setCmd(ICommand.CMD_START);
	      ioSession.write(JSON.toJSONString(login));
		return "Y";
	}

	public void finished(ContextInfo cti, String cid, String type) {
		
		if("1".equals(type)){
			type = "INSURANCE";
		}else if("2".equals(type)){
			type = "FUND";
		}
		logger.error("无缘无故被终止掉了cid"+cid);
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("cid", Integer.parseInt(cid));
		map.put("type", type);
		map.put("updatetime", new Date());
		map.put("progress", 1);
		map.put("state", "DONE");
		progressDao.finished(cti, map);
		
	}

	public List<String> list() {
		HashMap<String, Long> map  =SessionPool.getInstance().getOnlineSp();
		if(map == null){
			return new ArrayList<String>();
		}
		Iterator<String> it = map.keySet().iterator();
		List<String> list = new ArrayList<String>();
		while(it.hasNext()){
			list.add(it.next());
		}
		return list;
		
	}

    
    
}
