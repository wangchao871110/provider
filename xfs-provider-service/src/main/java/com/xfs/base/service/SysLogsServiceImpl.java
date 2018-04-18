package com.xfs.base.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.base.dao.SysLogsDAO;
import com.xfs.base.model.SysLogs;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.common.util.StringUtils;

@Service
public class SysLogsServiceImpl implements SysLogsService {
	private static final Logger log = Logger.getLogger(SysLogsServiceImpl.class);
	@Autowired
	private SysLogsDAO sysLogsDAO;

	public PageModel getSysLogs(PageInfo pi, SysLogs sysLogs) {

		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = sysLogsDAO.countFreeFind(sysLogs);
		pm.setTotal(total);
		// order by createdDt;
		List<SysLogs> datas = sysLogsDAO.freeFind(sysLogs, pageIndex, pageSize, "CDATE DESC");
		pm.setDatas(datas);
		return pm;
	}

	public void remove(ContextInfo cti, SysLogs vo) {
		sysLogsDAO.remove(cti, vo);
	}

	public void saveLogs(String functionName, String desc, ContextInfo user, String ip) {
		String userName = StringUtils.isBlank(user.getRealName()) ? user.getUserName()
				: user.getUserName() + "(" + user.getRealName() + ")";
		SysLogs sysLogs = new SysLogs();
		sysLogs.setUserId(user.getUserId());
		sysLogs.setUserName(userName);
		sysLogs.setIp(ip);
//		sysLogs.setIp(request.getRemoteAddr());
		sysLogs.setCdate(new Date());
		sysLogs.setFunctionName(functionName);
		String description = desc;
		sysLogs.setDescription(description);
		sysLogsDAO.insert(new ContextInfo(user), sysLogs);
	}

}
