package com.xfs.op.service.impl;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.page.PageModel;
import com.xfs.common.page.SystemContext;
import com.xfs.common.ContextInfo;

import com.xfs.op.service.RecUserTagService;
import com.xfs.op.dao.RecUserTagDao;
import com.xfs.op.model.RecUserTag;

@Service
public class RecUserTagServiceImpl implements RecUserTagService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(RecUserTagServiceImpl.class);
	
	@Autowired
	private RecUserTagDao recUserTagDao;
	
	public int save(ContextInfo cti, RecUserTag vo ){
		return recUserTagDao.save(cti,vo);
	}
	public int insert(ContextInfo cti, RecUserTag vo ){
		return recUserTagDao.insert(cti,vo);
	}
	public int remove(ContextInfo cti, RecUserTag vo ){
		return recUserTagDao.remove(cti,vo);
	}
	public int update(ContextInfo cti, RecUserTag vo ){
		return recUserTagDao.update(cti,vo);
	}
	public PageModel findPage(RecUserTag vo){
		
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		Integer total = recUserTagDao.countFreeFind(vo);
		pm.setTotal(total);
		List<RecUserTag> datas = recUserTagDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<RecUserTag> findAll(RecUserTag vo){
		
		List<RecUserTag> datas = recUserTagDao.freeFind(vo);
		return datas;
		
	}
	/**
	 * 总数统计
	 *  @param   vo
	 * @return    : java.lang.Integer
	 *  @createDate   : 2017/3/14 15:55
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2017/3/14 15:55
	 *  @updateAuthor  :
	 */
	@Override
	public Integer findCountAll(RecUserTag vo){
		return recUserTagDao.countFreeFind(vo);
	}

	@Override
	public int insertDefUserTag(ContextInfo cti, RecUserTag recUserTag) {
		return recUserTagDao.insertDefUserTag(cti,recUserTag);
	}


	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/02/22 10:59:03


	/**
	 * 查询用户tagid集合
	 * @param recUserTag
	 * @return
	 */
	@Override
	public String findUserTagGroupConcat(RecUserTag recUserTag){
		return recUserTagDao.findUserTagGroupConcat(recUserTag);
	}

	@Override
	public Integer deleteTagByUserId(ContextInfo cti, RecUserTag recUserTag) {
		return recUserTagDao.deleteTagByUserId(cti,recUserTag);
	}

	@Override
	public Integer saveAll(ContextInfo cti, List<RecUserTag> recUserTags) {
		return recUserTagDao.saveAll(cti,recUserTags);
	}
}
