package com.xfs.base.service;


import com.xfs.base.model.SysLogs;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

public interface SysLogsService {	
	public PageModel getSysLogs(PageInfo pi, SysLogs sysLogs);
	public void remove(ContextInfo cti, SysLogs vo);
	public void saveLogs(String functionName, String desc, ContextInfo sysUser, String ip);
}
