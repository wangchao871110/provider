package com.xfs.op.service.impl;
import java.util.List;

import com.xfs.common.Result;
import com.xfs.op.dao.OpHeadlineShareRecordDao;
import com.xfs.op.model.OpHeadlineShareRecord;
import com.xfs.op.model.OpHeadlineUser;
import com.xfs.op.service.OpHeadlineShareRecordService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.apache.zookeeper.Op;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.page.PageModel;
import com.xfs.common.page.SystemContext;
import com.xfs.common.ContextInfo;


@Service
public class OpHeadlineShareRecordServiceImpl implements OpHeadlineShareRecordService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(OpHeadlineShareRecordServiceImpl.class);
	
	@Autowired
	private OpHeadlineShareRecordDao opHeadlineShareRecordDao;
	
	public int save(ContextInfo cti, OpHeadlineShareRecord vo ){
		return opHeadlineShareRecordDao.save(cti,vo);
	}
	public int insert(ContextInfo cti, OpHeadlineShareRecord vo ){
		return opHeadlineShareRecordDao.insert(cti,vo);
	}
	public int remove(ContextInfo cti, OpHeadlineShareRecord vo ){
		return opHeadlineShareRecordDao.remove(cti,vo);
	}
	public int update(ContextInfo cti, OpHeadlineShareRecord vo ){
		return opHeadlineShareRecordDao.update(cti,vo);
	}
	public PageModel findPage(OpHeadlineShareRecord vo){
		
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		Integer total = opHeadlineShareRecordDao.countFreeFind(vo);
		pm.setTotal(total);
		List<OpHeadlineShareRecord> datas = opHeadlineShareRecordDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<OpHeadlineShareRecord> findAll(OpHeadlineShareRecord vo){
		
		List<OpHeadlineShareRecord> datas = opHeadlineShareRecordDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/03/09 10:56:41
	/**
	 * 创建分享几率
	 *  @param   cti, shareId, audienceIds
	 * @return    : java.lang.Boolean
	 *  @createDate   : 2017/3/9 14:28
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2017/3/9 14:28
	 *  @updateAuthor  :
	 */
	@Override
	public Boolean createRecord(ContextInfo cti,Integer shareId,String contentId,String audienceIds){
		OpHeadlineShareRecord shareRecord = new OpHeadlineShareRecord();
		shareRecord.setShareUserId(shareId);
		shareRecord.setAudienceIds(audienceIds);
		shareRecord.setContentId(contentId);
		shareRecord.setDr(0);
		return opHeadlineShareRecordDao.save(cti,shareRecord) > 0;
	}
	/**
	 * 修改记录
	 *  @param   cti, shareId, audienceIds
	 * @return    : java.lang.Boolean
	 *  @createDate   : 2017/3/9 14:36
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2017/3/9 14:36
	 *  @updateAuthor  :
	 */
	@Override
	public Boolean updateRecord(ContextInfo cti,Integer shareId,String contentId,String audienceIds){
		OpHeadlineShareRecord query = new OpHeadlineShareRecord();
		query.setShareUserId(shareId);
		query.setContentId(contentId);
		query.setDr(0);
		List<OpHeadlineShareRecord> list = opHeadlineShareRecordDao.freeFind(query);
		if(CollectionUtils.isEmpty(list)){
			return this.createRecord(cti,shareId,contentId,audienceIds);
		}
		OpHeadlineShareRecord vo = list.get(0);
		vo.setAudienceIds((vo.getAudienceIds() == null ? "" : vo.getAudienceIds())+audienceIds+",");
		return  opHeadlineShareRecordDao.update(cti,vo) > 0;
	}
}
