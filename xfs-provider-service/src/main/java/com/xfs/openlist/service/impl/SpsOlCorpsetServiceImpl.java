package com.xfs.openlist.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.openlist.dao.SpsOlCorpsetDao;
import com.xfs.openlist.model.SpsOlCorpset;
import com.xfs.openlist.service.SpsOlCorpsetService;

@Service
public class SpsOlCorpsetServiceImpl implements SpsOlCorpsetService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsOlCorpsetServiceImpl.class);
	
	@Autowired
	private SpsOlCorpsetDao spsOlCorpsetDao;
	
	public int save(ContextInfo cti, SpsOlCorpset vo ){
		return spsOlCorpsetDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsOlCorpset vo ){
		return spsOlCorpsetDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsOlCorpset vo ){
		return spsOlCorpsetDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsOlCorpset vo ){
		return spsOlCorpsetDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsOlCorpset vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsOlCorpsetDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsOlCorpset> datas = spsOlCorpsetDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsOlCorpset> findAll(SpsOlCorpset vo){
		
		List<SpsOlCorpset> datas = spsOlCorpsetDao.freeFind(vo);
		return datas;
		
	}



	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/04/09 16:45:56

    /**
     * 根据服务商ID获取 openlist企业联系人设置
     *  @param    : sp_id 服务商id
     *  @return    :  List<Map<String, Object>>
     *  @createDate   : 16-11-13 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-13 上午10:47
     *  @updateAuthor  :
     */
	@Override
	public List<Map<String, Object>> findCorpsetList(int sp_id) {
		return spsOlCorpsetDao.findCorpsetList(sp_id);
	}

    /**
     *  根据员工信息查询企业联系人信息
     *  @param    : mobile 服务商id
     *  @param    ：cpid  企业id
     *  @return    :  Map<String,Object>
     *  @createDate   : 16-11-13 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-13 上午10:47
     *  @updateAuthor  :
     */
	@Override
	public Map<String, Object> findCorpsetByCmPerson(String mobile,Integer cpid) {
		return spsOlCorpsetDao.findCorpsetByCmPerson(mobile,cpid);
	}

}
