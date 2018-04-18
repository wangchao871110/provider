package com.xfs.op.service;
import java.util.List;
import com.xfs.common.page.PageModel;
import com.xfs.common.ContextInfo;
import com.xfs.op.model.OpHeadlineShareRecord;

/**
 * OpHeadlineShareRecordService
 * @author:yangfw@xinfushe.com
 * @date:2017/03/09 10:56:41
 */
public interface OpHeadlineShareRecordService {
	
	public int save(ContextInfo cti, OpHeadlineShareRecord vo);
	public int insert(ContextInfo cti, OpHeadlineShareRecord vo);
	public int remove(ContextInfo cti, OpHeadlineShareRecord vo);
	public int update(ContextInfo cti, OpHeadlineShareRecord vo);
	public PageModel findPage(OpHeadlineShareRecord vo);
	public List<OpHeadlineShareRecord> findAll(OpHeadlineShareRecord vo);
	
	
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
	public Boolean createRecord(ContextInfo cti,Integer shareId,String contentId,String audienceIds);
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
	public Boolean updateRecord(ContextInfo cti,Integer shareId,String contentId,String audienceIds);
}
