package com.xfs.corp.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xfs.common.Result;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.page.PageModel;
import com.xfs.common.page.SystemContext;
import com.xfs.common.ContextInfo;

import com.xfs.corp.service.CmCorpOpenAppService;
import com.xfs.corp.dao.CmCorpOpenAppDao;
import com.xfs.corp.model.CmCorpOpenApp;

@Service
public class CmCorpOpenAppServiceImpl implements CmCorpOpenAppService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(CmCorpOpenAppServiceImpl.class);
	
	@Autowired
	private CmCorpOpenAppDao cmCorpOpenAppDao;
	
	public int save(ContextInfo cti, CmCorpOpenApp vo ){
		return cmCorpOpenAppDao.save(cti,vo);
	}
	public int insert(ContextInfo cti, CmCorpOpenApp vo ){
		return cmCorpOpenAppDao.insert(cti,vo);
	}
	public int remove(ContextInfo cti, CmCorpOpenApp vo ){
		return cmCorpOpenAppDao.remove(cti,vo);
	}
	public int update(ContextInfo cti, CmCorpOpenApp vo ){
		return cmCorpOpenAppDao.update(cti,vo);
	}
	public PageModel findPage(CmCorpOpenApp vo){
		
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		Integer total = cmCorpOpenAppDao.countFreeFind(vo);
		pm.setTotal(total);
		List<CmCorpOpenApp> datas = cmCorpOpenAppDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<CmCorpOpenApp> findAll(CmCorpOpenApp vo){
		
		List<CmCorpOpenApp> datas = cmCorpOpenAppDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/08/01 14:40:13
	
/**
 *  保存应用开通信息
 *  @param
 * @return    :
 *  @createDate   : 2017/8/1 16:10
 *  @author          : yangfw@xinfushe.com
 *  @version         : v1.0
 *  @updateDate     : 2017/8/1 16:10
 *  @updateAuthor  :
 */
	public Result saveInfo(ContextInfo cti,CmCorpOpenApp vo){
		Result result = new Result();
		CmCorpOpenApp query = new CmCorpOpenApp();
		query.setCpId(vo.getCpId());
		query.setAppType(vo.getAppType());
		List<CmCorpOpenApp> list = cmCorpOpenAppDao.freeFind(query);
		if(CollectionUtils.isNotEmpty(list)){
			vo.setId(list.get(0).getId());
			cmCorpOpenAppDao.update(cti,vo);
		}else {
			cmCorpOpenAppDao.insert(cti,vo);
		}
		return  result;
	}

	/**
	 * 获取当前企业开通应用信息
	 * @param vo
	 * @return
	 */
	public Map<String,CmCorpOpenApp> queryCurrCmCorpApps(CmCorpOpenApp vo){
		List<CmCorpOpenApp> apps = cmCorpOpenAppDao.queryCurrCmCorpApps(vo);
		Map<String,CmCorpOpenApp> appMap = new HashMap<>();
		if(null != apps && !apps.isEmpty()){
			for (CmCorpOpenApp cmCorpOpenApp : apps){
				appMap.put(cmCorpOpenApp.getAppType(),cmCorpOpenApp);
			}
		}
		return appMap;
	}
	
}
