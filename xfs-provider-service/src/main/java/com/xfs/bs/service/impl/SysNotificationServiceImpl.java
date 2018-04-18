package com.xfs.bs.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.base.model.SysNotification;
import com.xfs.bs.dao.SysNotificationDAO;
import com.xfs.bs.service.SysNotificationService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

@Service
public class SysNotificationServiceImpl implements SysNotificationService {
	@Autowired
	private SysNotificationDAO sysNotificationDAO;

	public SysNotification findByPK(SysNotification vo) {
		return sysNotificationDAO.findByPK(vo);
	}

	public PageModel freeFind(PageInfo pi, SysNotification vo) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		int total = sysNotificationDAO.countFreeFind(vo);
		pm.setTotal(total);
		List<SysNotification> list = sysNotificationDAO.freeFind(vo, pageIndex, pageSize, "is_proceed desc, create_dt desc");
		pm.setDatas(list);
		return pm;
	}

	public int countUnProceedNoti() {
		SysNotification vo = new SysNotification();
		vo.setIsProceed("N");
		return sysNotificationDAO.countFreeFind(vo);
	}
	
	public void insert(ContextInfo cti, SysNotification vo) {
		sysNotificationDAO.insert(cti, vo);
	}

	public void update(ContextInfo cti, SysNotification vo) {
		sysNotificationDAO.update(cti, vo);
	}
	
	public void delete(ContextInfo cti, SysNotification vo) {
		sysNotificationDAO.remove(cti, vo);
	}
	
	public void insertNotification(ContextInfo cti, String functionName, String desc) {
		SysNotification noti = new SysNotification();
		noti.setCreateDt(new Date());
		noti.setFunctionName(functionName);
		noti.setIsProceed("N");
		noti.setDescription(desc);
		sysNotificationDAO.insert(cti, noti);
	}
}
