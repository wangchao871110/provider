package com.xfs.op.service;
import java.util.List;
import com.xfs.common.page.PageModel;
import com.xfs.common.ContextInfo;
import com.xfs.op.model.RecUserTag;

/**
 * RecUserTagService
 * @author:yangfw@xinfushe.com
 * @date:2017/02/22 10:59:03
 */
public interface RecUserTagService {
	
	public int save(ContextInfo cti, RecUserTag vo);
	public int insert(ContextInfo cti, RecUserTag vo);
	public int remove(ContextInfo cti, RecUserTag vo);
	public int update(ContextInfo cti, RecUserTag vo);
	public PageModel findPage(RecUserTag vo);
	public List<RecUserTag> findAll(RecUserTag vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/02/22 10:59:03

	/**
	 * 插入用户默认标签
	 * @param cti
	 * @param recUserTag
	 * @return
	 */
	public int insertDefUserTag(ContextInfo cti,RecUserTag recUserTag);
	/**
	 * 查询用户tagid集合
	 * @param recUserTag
	 * @return
	 */
	public String findUserTagGroupConcat(RecUserTag recUserTag);

	/**
	 * 通过用户id删除标签
	 *  @param   cti, recUserTag
	 * @return    : java.lang.Integer
	 *  @createDate   : 2017/2/23 20:01
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2017/2/23 20:01
	 *  @updateAuthor  :
	 */
	public Integer deleteTagByUserId(ContextInfo cti ,RecUserTag recUserTag);


	/**
	 * 批量保存用户标签
	 *  @param   cti, recUserTag
	 * @return    : java.lang.Integer
	 *  @createDate   : 2017/2/23 20:01
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2017/2/23 20:01
	 *  @updateAuthor  :
	 */
	public Integer saveAll(ContextInfo cti, List<RecUserTag> recUserTags);


	public Integer findCountAll(RecUserTag vo);
}
