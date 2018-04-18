package com.xfs.mall.product.service;

import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.mall.product.model.BsMallRecomproduct;

/** 
 * @author  : duanax@xinfushe.com
 * @date  : 2016/11/9 0009 下午 8:30
 * @version  : V1.0
 */
public interface BsMallRecomproductService {
	
	public int save(ContextInfo cti, BsMallRecomproduct vo);
	public int insert(ContextInfo cti, BsMallRecomproduct vo);
	public int remove(ContextInfo cti, BsMallRecomproduct vo);
	public int update(ContextInfo cti, BsMallRecomproduct vo);
	public PageModel findPage(PageInfo pi, BsMallRecomproduct vo);
	public List<BsMallRecomproduct> findAll(BsMallRecomproduct vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/07 11:08:04
	/**
	 *  返回推荐产品列表
	 *  @param   vo
	 * @return    : PageModel
	 *  @createDate   : 2016/11/9 0009 下午 8:23
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/9 0009 下午 8:23
	 *  @updateAuthor  :
	 */
	public PageModel findProductPage(PageInfo pi, BsMallRecomproduct vo);
	/**
	 *  通过主键查找推荐产品
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
	 *  商城首页推荐的商品列表
	 * @return    : List<Map<String, Object>>
	 *  @createDate   : 2016/11/9 0009 下午 8:24
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/9 0009 下午 8:24
	 *  @updateAuthor  :
	 */
	public List<Map<String, Object>> findRecomProducts();
}
