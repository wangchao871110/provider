package com.xfs.activity.service.impl;

import com.xfs.activity.dao.MoonUserDao;
import com.xfs.activity.model.MoonUser;
import com.xfs.activity.service.MoonUserService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoonUserServiceImpl implements MoonUserService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(MoonUserServiceImpl.class);
	
	@Autowired
	private MoonUserDao moonUserDao;
	
	public int save(ContextInfo cti, MoonUser vo ){
		return moonUserDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  MoonUser vo ){
		return moonUserDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  MoonUser vo ){
		return moonUserDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  MoonUser vo ){
		return moonUserDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, MoonUser vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = moonUserDao.countFreeFind(vo);
		pm.setTotal(total);
		List<MoonUser> datas = moonUserDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<MoonUser> findAll(MoonUser vo){
		
		List<MoonUser> datas = moonUserDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/06 11:20:20
	public MoonUser queryPic( MoonUser vo ){
		return moonUserDao.queryPic(vo);
	}
	public int updateUserState(ContextInfo cti, MoonUser vo ){
		return moonUserDao.updateUserState(cti, vo);
	}

	public int updateRemark(ContextInfo cti, MoonUser vo ){
		return moonUserDao.updateRemark(cti, vo);
	}
	
//	@Override
//	public String uploadFile(File file) {
//		try {
//			return AliyunImageUtil.uploadPicture(file, "images/cs/");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "";
//	}
//	@Override
//	public GridFSDBFile readFileById(String fileId) {
//		return mongoDao.readFileById(fileId);
//	}
}
