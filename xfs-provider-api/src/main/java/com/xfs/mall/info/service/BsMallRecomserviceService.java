package com.xfs.mall.info.service;

import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.mall.info.model.BsMallRecomservice;

/** 
 * @author  : duanax@xinfushe.com
 * @date  : 2016/11/9 0009 下午 8:30
 * @version  : V1.0
 */
public interface BsMallRecomserviceService {
	
	public int save(ContextInfo cti, BsMallRecomservice vo);
	public int insert(ContextInfo cti, BsMallRecomservice vo);
	public int remove(ContextInfo cti, BsMallRecomservice vo);
	public int update(ContextInfo cti, BsMallRecomservice vo);
	public PageModel findPage(PageInfo pi, BsMallRecomservice vo);
	public List<BsMallRecomservice> findAll(BsMallRecomservice vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/07 11:08:12
	/**
	 *  返回推荐服务商列表
	 *  @param   vo
	 * @return    : PageModel
	 *  @createDate   : 2016/11/9 0009 下午 8:23
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/9 0009 下午 8:23
	 *  @updateAuthor  :
	 */
	public PageModel findServicePage(PageInfo pi, BsMallRecomservice vo);
	/**
	 *  通过主键查找推荐服务商
	 *  @param   id
	 * @return    : Map
	 *  @createDate   : 2016/11/9 0009 下午 8:23
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/9 0009 下午 8:23
	 *  @updateAuthor  :
	 */
	public Map findRecomByPK(Integer id);
	/**
	 *  商城首页推荐的服务商列表
	 * @return    : List<Map<String, Object>>
	 *  @createDate   : 2016/11/9 0009 下午 8:24
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/9 0009 下午 8:24
	 *  @updateAuthor  :
	 */
	public List<Map<String, Object>> findRecomServices();
}
