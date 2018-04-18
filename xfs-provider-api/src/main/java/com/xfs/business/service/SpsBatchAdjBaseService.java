package com.xfs.business.service;

import com.xfs.business.dto.SpsBatchAdjBaseVo;
import com.xfs.business.model.SpsBatchAdjBase;
import com.xfs.common.ContextInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;

import java.util.List;
import java.util.Map;

/**
 * SpsBatchAdjBaseService
 * @author:yangfw@xinfushe.com
 * @date:2016/12/26 21:14:54
 */
public interface SpsBatchAdjBaseService {
	
	public int save(ContextInfo cti, SpsBatchAdjBase vo);
	public int insert(ContextInfo cti,SpsBatchAdjBase vo);
	public int remove(ContextInfo cti,SpsBatchAdjBase vo);
	public int update(ContextInfo cti,SpsBatchAdjBase vo);
	public PageModel findPage(SpsBatchAdjBase vo);
	public List<SpsBatchAdjBase> findAll(SpsBatchAdjBase vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/12/26 21:14:54


	/**
	 * 导入更新基数
	 *  @param   cti, fileId
	 * @return    : com.xfs.common.Result
	 *  @createDate   : 2016/12/27 20:09
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/12/27 20:09
	 *  @updateAuthor  :
	 */
	public Result importExcel(ContextInfo cti, Integer fileId);

	/**
	 *  批量调整最低基数
	 *  @param   cti, sheelMap
	 *	@return 			: com.xfs.common.Result
	 *  @createDate  	: 2017-01-09 20:40
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-01-09 20:40
	 *  @updateAuthor  :
	 */
	public Result batchAdjLowerBase(ContextInfo cti,Map<Integer, Map<String, String>> sheelMap);
	/**
	 * 分页查询vo数据
	 *  @param   vo
	 * @return    : com.xfs.common.page.PageModel
	 *  @createDate   : 2016/12/28 11:07
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/12/28 11:07
	 *  @updateAuthor  :
	 */
	public PageModel findVoPage(ContextInfo cit,SpsBatchAdjBaseVo vo);

	/**
	 * 查看人员详情数据  emojson 转 list<map>
	 *  @param   id
	 * @return    : com.xfs.business.dto.SpsBatchAdjBaseVo
	 *  @createDate   : 2016/12/29 10:39
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/12/29 10:39
	 *  @updateAuthor  :
	 */
	public SpsBatchAdjBaseVo findByIdForDetail(Integer id) throws Exception;
}
