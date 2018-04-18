
package com.xfs.base.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.base.dao.CsRegistHistoryDao;
import com.xfs.base.model.CsRegistHistory;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * CsRegistHistoryServiceImpl 登记历史业务实现类
 * @author 	: wangxs@xinfushe.com
 * @date 	: 2016-11-11 15:18
 * @version 	: V1.0
 */
@Service
public class CsRegistHistoryServiceImpl implements CsRegistHistoryService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(CsRegistHistoryServiceImpl.class);
	
	@Autowired
	private CsRegistHistoryDao csRegistHistoryDao;
	
	public int save(ContextInfo cti, CsRegistHistory vo ){
		return csRegistHistoryDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  CsRegistHistory vo ){
		return csRegistHistoryDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  CsRegistHistory vo ){
		return csRegistHistoryDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  CsRegistHistory vo ){
		return csRegistHistoryDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, CsRegistHistory vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = csRegistHistoryDao.countFreeFind(vo);
		pm.setTotal(total);
		List<CsRegistHistory> datas = csRegistHistoryDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<CsRegistHistory> findAll(CsRegistHistory vo){
		
		List<CsRegistHistory> datas = csRegistHistoryDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/29 17:32:17

    /**
     *  查询渠道注册的企业
     *  @param    ：  channel 渠道id
     *  @param    ：  sname   员工代码
     *  @return    :   BsQuestionAnswer
     *  @createDate   : 16-11-14 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-14 上午10:47
     *  @updateAuthor  :
     */
	public List<Map<String, Object>> findRegistCorps(String channel, String sname) {
		return csRegistHistoryDao.findRegistCorps(channel, sname);
	}
	
}
