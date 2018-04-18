package com.xfs.user.service.impl;


import java.util.List;

import com.xfs.common.ContextInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.user.dao.SysFunctionDao;
import com.xfs.user.model.SysFunction;
import com.xfs.user.model.SysRelRoleFunc;
import com.xfs.user.service.SysFunctionService;

@Service
public class SysFunctionServiceImpl implements SysFunctionService {
	
	@Autowired
	private SysFunctionDao functionDAO;

	public List<SysFunction> getFuctionList(SysFunction obj) {
		return functionDAO.freeFind(obj, "PRIORITY DESC");
	}

	@Autowired
	private SysFunctionDao sysFunctionDAO;

	public void save(ContextInfo cti, SysFunction vo ){
		sysFunctionDAO.save(cti, vo);
	}
	public void insert(ContextInfo cti, SysFunction vo ){
		sysFunctionDAO.insert(cti, vo);
	}
	public void remove(ContextInfo cti,  SysFunction vo ){
		sysFunctionDAO.remove(cti, vo);
	}
	public void update(ContextInfo cti,  SysFunction vo ){
		sysFunctionDAO.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SysFunction vo){
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = sysFunctionDAO.countFreeFind(vo);
		pm.setTotal(total);
		List<SysRelRoleFunc> datas = sysFunctionDAO.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;

	}
	public List findAll(SysFunction vo){

		List datas = sysFunctionDAO.freeFind(vo);
		return datas;

	}

	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/13 17:28:23

	public SysFunction queryLastSysFunction(SysFunction obj){
		return sysFunctionDAO.findLastFunction(obj);
	}


}
