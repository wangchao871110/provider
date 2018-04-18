package com.xfs.business.service.impl;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.business.dao.SpsTaskHistoryDao;
import com.xfs.business.model.SpsTaskHistory;
import com.xfs.business.service.SpsTaskHistoryService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.user.model.SysUser;

@Service
public class SpsTaskHistoryServiceImpl implements SpsTaskHistoryService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsTaskHistoryServiceImpl.class);
	
	@Autowired
	private SpsTaskHistoryDao spsTaskHistoryDao;
	
	public int save(ContextInfo cti, SpsTaskHistory vo ){
		return spsTaskHistoryDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsTaskHistory vo ){
		return spsTaskHistoryDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsTaskHistory vo ){
		return spsTaskHistoryDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsTaskHistory vo ){
		return spsTaskHistoryDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsTaskHistory vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsTaskHistoryDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsTaskHistory> datas = spsTaskHistoryDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsTaskHistory> findAll(SpsTaskHistory vo){
		
		List<SpsTaskHistory> datas = spsTaskHistoryDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/03 16:29:21
	/**
	 * 2016-08 任务管理 
	 * 
	 * 任务单详情页面
	 * 
	 * 保存按钮 新增或者修改
	 * 
	 * zhangxiyan
	 */
	@Override
	public Result detailsave(ContextInfo cti,SpsTaskHistory vo) {
		Result result = Result.createResult().setSuccess(false);
		Date date = new Date();
			vo.setCreateBy(cti.getUserId());
			vo.setCreateDt(date);
			vo.setDr(0);
			vo.setOperate("执行保存操作！");
			Integer ins = spsTaskHistoryDao.insert(cti, vo);
			if (ins >0) {
				result.setSuccess(true);
			} else {
				result.setError("保存失败！请联系管理员！");
			}
			return result;
	}
	
	/**
	 * 2016-08 任务管理 
	 * 
	 * 任务单详情页面
	 * 
	 * 查看操作历史记录
	 * 
	 * zhangxiyan
	 */
	@Override
	public List<Map<String, Object>> seehistory(SpsTaskHistory vo) {
		List<Map<String, Object>> listMap = spsTaskHistoryDao.seehistory(vo);
		return listMap;
	}

	@Override
	public Integer queryHistoryCount(ContextInfo cti,Integer stateFileId){
		return spsTaskHistoryDao.queryHistoryCount(cti,stateFileId);
	}
	
}
