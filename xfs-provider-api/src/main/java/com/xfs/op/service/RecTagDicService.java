package com.xfs.op.service;
import java.util.List;
import java.util.Map;

import com.xfs.common.page.PageModel;
import com.xfs.common.ContextInfo;
import com.xfs.op.model.RecTagDic;

/**
 * RecTagDicService
 * @author:yangfw@xinfushe.com
 * @date:2017/02/22 10:59:03
 */
public interface RecTagDicService {
	
	public int save(ContextInfo cti, RecTagDic vo);
	public int insert(ContextInfo cti, RecTagDic vo);
	public int remove(ContextInfo cti, RecTagDic vo);
	public int update(ContextInfo cti, RecTagDic vo);
	public PageModel findPage(RecTagDic vo);
	public List<RecTagDic> findAll(RecTagDic vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/02/22 10:59:03
	/**
	 * 通过多类型查询标签
	 *  @param   parem
	 * @return    : java.util.List<com.xfs.op.model.RecTagDic>
	 *  @createDate   : 2017/2/23 19:50
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2017/2/23 19:50
	 *  @updateAuthor  :
	 */
	public List<RecTagDic> findTagByTypes(Map<String,Object> parem);

	/**
	 * 查询用户分类标签
	 *  @param   parem
	 * @return    : java.util.List<com.xfs.op.model.RecTagDic>
	 *  @createDate   : 2017/3/1 10:39
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2017/3/1 10:39
	 *  @updateAuthor  :
	 */
	public List<RecTagDic> findUserTagById(Map<String,Object> parem);
}
