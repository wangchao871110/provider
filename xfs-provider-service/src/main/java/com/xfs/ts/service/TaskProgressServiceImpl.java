package com.xfs.ts.service;

import com.alibaba.fastjson.JSON;
import com.xfs.ss.dao.TaskProgressDAO;
import com.xfs.ss.invoke.SessionPool;
import com.xfs.ss.packet.Data;
import com.xfs.ss.packet.service.ServicceServerBody;
import com.xfs.ss.util.ICommand;
import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class TaskProgressServiceImpl implements TaskProgressService {

	@Autowired
	private TaskProgressDAO progressDao;

	@Override
	public boolean isStarted(String spkey) {
		Long sessionId = SessionPool.getInstance().getSessionId(spkey);
		if (sessionId == null || sessionId <= 0) {
			return false;
		}
		IoSession ioSession = SessionPool.getInstance().getSession(sessionId);
		if (ioSession == null) {
			return false;
		}
		return true;

	}

	/**
	 * 迭代执行每个服务商
	 *
	 * @param spid
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public void eachOne(Integer spid) {
		// 查询是否某个服务商有正在处理的 DOING 任务
		HashMap<String, Object> condition = new HashMap<>();
		condition.put("state", "DOING");
		condition.put("spid", spid);
		List<HashMap> progressList = progressDao.findProgressList(condition);
		if (progressList != null && progressList.size() > 0) {
			HashMap<String, Object> map = progressList.get(0);
			if (map != null) {
				if (isStarted((String) map.get("mobile"))) {
					updateProgress(map);
				}
			}
		}else {
			condition = new HashMap<>();
			condition.put("state", "TODO");
			condition.put("spid", spid);
			progressList = progressDao.findProgressList(condition);
			if (progressList != null && progressList.size() > 0) {
				HashMap<String, Object> map = progressList.get(0);
				if (isStarted((String) map.get("mobile"))) {
					startExcuteTask(map);
				}
			}
		}
	}




	// 执行某个任务
	private void startExcuteTask(HashMap<String, Object> row) {
		sendNotice2Client(row);
		HashMap<String, Object> map = new HashMap<>();
		map.put("id", row.get("id"));
		map.put("updatetime", new Date());
		map.put("state", "DOING");
		progressDao.updateProgressTask(null, map);
	}

	/**
	 * 发送消息到客户端
	 *
	 * @param row
	 */
	@Override
	public void sendNotice2Client(HashMap<String, Object> row) {
		String mobile = (String) row.get("mobile");
		Long sessionId = SessionPool.getInstance().getSessionId(mobile);
		if (sessionId == null || sessionId <= 0) {
			return;
		}
		IoSession ioSession = SessionPool.getInstance().getSession(sessionId);
		if (ioSession != null) {
			Data<ServicceServerBody> login = new Data<ServicceServerBody>();
			login.setBody(new ServicceServerBody());
			login.getBody().setCid((Integer) row.get("cid"));
			//添加企业端口
			String  port= (String)row.get("usbport");
			login.getBody().setUsbport((String) row.get("usbport"));
			login.getBody().setAll(true);
			String type = (String) row.get("type");
			if ("INSURANCE".equals(type)) {
				login.getBody().setInOrFun(1);
			} else if ("FUND".equals(type)) {
				login.getBody().setInOrFun(2);
			}
			login.getHead().setCmd(ICommand.CMD_EXCUTE);
			ioSession.write(JSON.toJSONString(login));
		}
	}

	/**
	 * 发送单个业务类型消息指令到客户端
	 *
	 * @param row
	 */
	@Override
	public boolean sendTaskClient(HashMap<String, Object> row) {
		String mobile = (String) row.get("mobile");
		Long sessionId = SessionPool.getInstance().getSessionId(mobile);
		if (sessionId == null || sessionId <= 0) {
			return false;
		}
		IoSession ioSession = SessionPool.getInstance().getSession(sessionId);
		if (ioSession != null) {
			Data<ServicceServerBody> login = new Data<ServicceServerBody>();
			login.setBody(new ServicceServerBody());
			login.getBody().setCid((Integer) row.get("cid"));
			//添加企业端口
			login.getBody().setUsbport((String) row.get("usbport"));
			login.getBody().setAll(false);
			login.getBody().setType((Integer) row.get("bstypeId"));
			String type = (String) row.get("type");
			if ("INSURANCE".equals(type)) {
				login.getBody().setInOrFun(1);
			} else if ("FUND".equals(type)) {
				login.getBody().setInOrFun(2);
			}
			login.getHead().setCmd(ICommand.CMD_EXCUTE);
			ioSession.write(JSON.toJSONString(login));
			return true;
		}
		return false;
	}

	/**
	 * 发送登录指令到客户端
	 *
	 * @param row
	 */
	@Override
	public boolean sendLoginClient(HashMap<String, Object> row) {
		String mobile = (String) row.get("mobile");
		Long sessionId = SessionPool.getInstance().getSessionId(mobile);
		if (sessionId == null || sessionId <= 0) {
			return false;
		}
		IoSession ioSession = SessionPool.getInstance().getSession(sessionId);
		if (ioSession != null) {
			Data<ServicceServerBody> login = new Data<ServicceServerBody>();
			login.setBody(new ServicceServerBody());
			login.getBody().setCid((Integer) row.get("cid"));
			//添加企业端口
			login.getBody().setUsbport((String) row.get("usbport"));
			login.getBody().setAll(true);
			String type = (String) row.get("type");
			if ("INSURANCE".equals(type)) {
				login.getBody().setInOrFun(1);
			} else if ("FUND".equals(type)) {
				login.getBody().setInOrFun(2);
			}
			login.getHead().setCmd(ICommand.CMD_LOGIN);
			ioSession.write(JSON.toJSONString(login));
			return true;
		}
		return false;
	}

	@Override
	public List<HashMap<String, Integer>> findAllSpService() {
		return progressDao.findAllSpService();
	}

	/**
	 * 更新某个任务的进度
	 *
	 * 其算法是：如果有10个人待处理
	 *
	 * 总共需要的时间 10个人乘以 5分钟*60秒 等于 3000秒
	 *
	 * 除以5秒 =600次
	 *
	 * 600次才能到100
	 *
	 * 取出数据库的值加上这个进度 每次的进度 100/600
	 *
	 */

	private void updateProgress(HashMap<String, Object> row) {
		progressDao.addProgress(null, ((Integer) (row.get("id"))));
	}

}
