package com.xfs.mall.info.service;

import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.mall.info.model.SpsMallServicerange;

/** 
 * @author  : duanax@xinfushe.com
 * @date  : 2016/11/9 0009 下午 8:30
 * @version  : V1.0
 */
public interface SpsMallServicerangeService {
	
	public int save(ContextInfo cti, SpsMallServicerange vo);
	public int insert(ContextInfo cti, SpsMallServicerange vo);
	public int remove(ContextInfo cti, SpsMallServicerange vo);
	public int update(ContextInfo cti, SpsMallServicerange vo);
	public PageModel findPage(PageInfo pi, SpsMallServicerange vo);
	public List<SpsMallServicerange> findAll(SpsMallServicerange vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/23 11:04:37

	/**  
	 *  查询服务范围
	 *  @param   spId
	 * @return    : List<Map<String, Object>>
	 *  @createDate   : 2016/11/9 0009 下午 8:28
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/9 0009 下午 8:28
	 *  @updateAuthor  :
	 */
	public List<Map<String, Object>> findSpsMallCategories(Integer spId);
	
}
