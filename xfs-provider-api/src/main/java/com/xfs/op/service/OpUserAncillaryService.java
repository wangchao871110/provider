package com.xfs.op.service;
import java.util.List;
import java.util.Map;

import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.common.ContextInfo;
import com.xfs.op.model.OpUserAncillary;

/**
 * OpUserAncillaryService
 * @author:yangfw@xinfushe.com
 * @date:2017/01/05 20:57:18
 */
public interface OpUserAncillaryService {
	
	public int save(ContextInfo cti, OpUserAncillary vo);
	public int insert(ContextInfo cti, OpUserAncillary vo);
	public int remove(ContextInfo cti, OpUserAncillary vo);
	public int update(ContextInfo cti, OpUserAncillary vo);
	public PageModel findPage(OpUserAncillary vo);
	public List<OpUserAncillary> findAll(OpUserAncillary vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/01/05 20:57:18

	/**  查询用户信息 如果昵称为空则取企业名称
	 *  @param   obj
	 * @return    : java.util.Map<java.lang.String,java.lang.Object>
	 *  @createDate   : 2017/1/8 14:34
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2017/1/8 14:34
	 *  @updateAuthor  :
	 */
	public Map<String,Object> getUserInfo(OpUserAncillary obj);
	/**
	 * 保存用户信息
	 *  @param   cti, obj]
	 * @return    : java.lang.Integer
	 *  @createDate   : 2017/1/8 15:00
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2017/1/8 15:00
	 *  @updateAuthor  :
	 */
	public Integer saveUserInfo(ContextInfo cti,OpUserAncillary obj);


	/**
	 * 保存昵称
	 *  @param   cti, nickName]
	 * @return    : com.xfs.common.Result
	 *  @createDate   : 2017/1/17 16:23
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2017/1/17 16:23
	 *  @updateAuthor  :
	 */
	public Result saveNickName(ContextInfo cti, String nickName);
}
