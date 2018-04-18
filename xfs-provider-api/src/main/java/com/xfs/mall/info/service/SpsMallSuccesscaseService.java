package com.xfs.mall.info.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.mall.info.model.SpsMallSuccesscase;

/** 
 * @author  : duanax@xinfushe.com
 * @date  : 2016/11/9 0009 下午 8:29
 * @version  : V1.0
 */
public interface SpsMallSuccesscaseService {
	
	public int save(ContextInfo cti, SpsMallSuccesscase vo);
	public int insert(ContextInfo cti, SpsMallSuccesscase vo);
	public int remove(ContextInfo cti, SpsMallSuccesscase vo);
	public int update(ContextInfo cti, SpsMallSuccesscase vo);
	public PageModel findPage(PageInfo pi, SpsMallSuccesscase vo);
	public List<SpsMallSuccesscase> findAll(SpsMallSuccesscase vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/27 11:28:03

	/**  
	 *  通过spId删除成功案例
	 *  @param   spId
	 * @return    : int
	 *  @createDate   : 2016/11/9 0009 下午 8:29
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/9 0009 下午 8:29
	 *  @updateAuthor  :
	 */
	public int removeSuccessCaseBySpId(ContextInfo cti, int spId);
	/**  
	 *  批量插入成功案例
	 *  @param   list
	 * @return    : int
	 *  @createDate   : 2016/11/9 0009 下午 8:29
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/9 0009 下午 8:29
	 *  @updateAuthor  :
	 */
	public int batchInsert(ContextInfo cti, List<SpsMallSuccesscase> list);
}
