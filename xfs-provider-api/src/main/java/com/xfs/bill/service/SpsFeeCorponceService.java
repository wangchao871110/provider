package com.xfs.bill.service;

import java.util.List;
import java.util.Map;

import com.xfs.bill.model.SpsFeeCorponce;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.user.model.SysUser;

/**
 * 企业单次费用服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-10 14:58
 * @version 	: V1.0
 */
public interface SpsFeeCorponceService {
	
	public int save(ContextInfo cti, SpsFeeCorponce vo);
	public int insert(ContextInfo cti, SpsFeeCorponce vo);
	public int remove(ContextInfo cti, SpsFeeCorponce vo);
	public int update(ContextInfo cti, SpsFeeCorponce vo);
	public PageModel findPage(PageInfo pi, SpsFeeCorponce vo);
	public List<SpsFeeCorponce> findAll(SpsFeeCorponce vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/04 11:35:45

	/**
	 *  获取单次费用列表
	 *  @param   corp_name	企业名称
	 *  @param	 period		月份
	 *  @param	 sp_id	服务商ID
	 *  @param	 cti	用户信息
	 *	@return 			: com.xfs.common.page.PageModel
	 *  @createDate  	: 2016-11-09 16:48
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 16:48
	 *  @updateAuthor  :
	 */
	public PageModel queryOnceFeeList(PageInfo pi, String corp_name, String period, Integer sp_id, ContextInfo cti);

	/**
	 *  获取企业单次费用列表
	 *  @param   sp_id	服务商ID
	 *  @param	 cp_id  企业ID
	 *  @param   shceme_id 方案ID
	 *  @param   period  月份
	 *	@return 			:PageModel
	 *  @createDate  	: 2016-11-09 16:51
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 16:51
	 *  @updateAuthor  :
	 */
	public PageModel queryCropOnceFeeList(PageInfo pi, Integer sp_id, Integer cp_id, Integer shceme_id, String period);

	/**
	 *  获取员工单次费用列表
	 *  @param sp_id   服务商ID
	 *  @param cp_id  企业ID
	 *  @param shceme_id 方案ID
	 *  @param area_id 地区
	 *  @param period  月份
	 *	@return 			: PageModel
	 *  @createDate  	: 2016-11-09 16:52
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 16:52
	 *  @updateAuthor  :
	 */
	public PageModel queryEmpOnceFeeList(PageInfo pi, Integer sp_id, Integer cp_id, Integer shceme_id, Integer area_id, String period, String search_word);

	/**
	 *  获取服务商下的方案列表
	 *  @param   sp_id  服务商ID
	 *	@return 			: List<Map<String,Object>>
	 *  @createDate  	: 2016-11-09 16:55
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 16:55
	 *  @updateAuthor  :
	 */
	public List<Map<String,Object>> querySchemesBySpId(Integer sp_id, Integer cp_id);

	/**
	 *  查询员工一次性费用调整记录
	 *  @param   corponce 企业单次费用
	 *	@return 			: List<SpsFeeCorponce>
	 *  @createDate  	: 2016-11-09 16:55
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 16:55
	 *  @updateAuthor  :
	 */
	public List<SpsFeeCorponce> queryCorpOnceItems(SpsFeeCorponce corponce);


	/**
	 *  逻辑删除费用列表
	 *  @param   corponce
	 *	@return 			: int
	 *  @createDate  	: 2016-11-09 16:56
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 16:56
	 *  @updateAuthor  :
	 */
	public int delCorpOnceItems(ContextInfo cti, SpsFeeCorponce corponce);

	/**
	 *  创建员工费用跳转列表
	 *  @param spId	服务商ID
	 *  @param corponces 企业单次费用
	 *	@return 			: Result
	 *  @createDate  	: 2016-11-09 16:57
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 16:57
	 *  @updateAuthor  :
	 */
	public Result createCorpItems(ContextInfo cti, Integer spId, SpsFeeCorponce[] corponces);

	/**
	 *  获取子账单调整项目
	 *  @param sp_id	服务商
	 *  @param p_sp_id	分包服务商
	 *  @param cp_id	企业ID
	 *  @param period	月份
	 *	@return 			: List<SpsFeeCorponce>
	 *  @createDate  	: 2016-11-09 16:57
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 16:57
	 *  @updateAuthor  :
	 */
	public List<SpsFeeCorponce> queryChildBillCorpOnceItems(Integer sp_id, String p_sp_id, Integer cp_id, String period);
}
