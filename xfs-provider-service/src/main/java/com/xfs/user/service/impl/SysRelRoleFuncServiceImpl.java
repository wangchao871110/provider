package com.xfs.user.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.user.dao.SysRelRoleFuncDao;
import com.xfs.user.model.SysRelRoleFunc;
import com.xfs.user.service.SysRelRoleFuncService;


@Service
public class SysRelRoleFuncServiceImpl implements SysRelRoleFuncService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SysRelRoleFuncServiceImpl.class);
	
	@Autowired
	private SysRelRoleFuncDao sysRelRoleFuncDao;
	
	public int save(ContextInfo cti, SysRelRoleFunc vo ){
		return sysRelRoleFuncDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SysRelRoleFunc vo ){
		return sysRelRoleFuncDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SysRelRoleFunc vo ){
		return sysRelRoleFuncDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SysRelRoleFunc vo ){
		return sysRelRoleFuncDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SysRelRoleFunc vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = sysRelRoleFuncDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SysRelRoleFunc> datas = sysRelRoleFuncDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SysRelRoleFunc> findAll(SysRelRoleFunc vo){
		
		List<SysRelRoleFunc> datas = sysRelRoleFuncDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/13 17:28:23
    public void delete(ContextInfo cti, SysRelRoleFunc vo) {

        sysRelRoleFuncDao.remove(cti, vo);

    }

    public void deleteByRoleId(ContextInfo cti, Integer roleId){
        sysRelRoleFuncDao.deleteByRoleId(cti, roleId);
    }

    public SysRelRoleFunc findByPK(SysRelRoleFunc obj) {

        return sysRelRoleFuncDao.findByPK(obj);
    }
	
	
}
