package com.xfs.bs.service;

import com.xfs.base.model.SysNotification;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;


/**
 * SysNotificationService
 * @author:Lusir<lusire@gmail.com>
 */
public interface SysNotificationService {
	public SysNotification findByPK(SysNotification vo);
	public PageModel freeFind(PageInfo pi, SysNotification vo);
	public void insert(ContextInfo cti, SysNotification vo);
	public void update(ContextInfo cti, SysNotification vo);
	public void delete(ContextInfo cti, SysNotification vo);
	public void insertNotification(ContextInfo cti, String functionName, String desc);
	public int countUnProceedNoti();
}
