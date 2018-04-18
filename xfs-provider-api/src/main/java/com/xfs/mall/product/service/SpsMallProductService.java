package com.xfs.mall.product.service;

import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.insurance.dto.CiScheme;
import com.xfs.mall.product.model.SpsMallProduct;
import com.xfs.mall.product.model.SpsMallProductItem;
import com.xfs.user.model.SysUser;

/**
 * SpsMallProductService
 * @author:quanjiahua<quanjiahua@163.com>
 * 
 * @date:2016/06/07 11:06:12
 */
public interface SpsMallProductService {

	public int save(ContextInfo cti, SpsMallProduct vo);

	public int insert(ContextInfo cti, SpsMallProduct vo);

	public int remove(ContextInfo cti, SpsMallProduct vo);

	public int update(ContextInfo cti, SpsMallProduct vo);

	public PageModel findPage(PageInfo pi, SpsMallProduct vo);

	public List<SpsMallProduct> findAll(SpsMallProduct vo);

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/06/07 11:06:12

	/**
	 *  根据产品名称和服务机构名称查询产品
	 *  @param
	 * @return    :
	 *  @createDate   : 2016/11/10 0010 上午 11:13
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/10 0010 上午 11:13
	 *  @updateAuthor  :
	 */
	public PageModel findSpsProductByRecom(PageInfo pi, String productName, String spName);
	/**
	 *  op端通过服务商名称查询产品列表
	 *  @param   map
	 * @return    : PageModel
	 *  @createDate   : 2016/11/10 0010 上午 11:57
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/10 0010 上午 11:57
	 *  @updateAuthor  :
	 */
	public PageModel findAllmsgPage(PageInfo pi, Map map);
	/**
	 *  商铺服务商品列表
	 *  @param   vo
	 * @return    : List<Map<String, Object>>
	 *  @createDate   : 2016/11/10 0010 上午 11:12
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/10 0010 上午 11:12
	 *  @updateAuthor  :
	 */
	public List<Map<String, Object>> findMallProducts(SpsMallProduct vo);

	/**
	 *  条件搜索产品列表
	 * @param areaIds
	 * @param categoryIds
	 * @param spId
	 * @param searchWord
	 * @return    : PageModel
	 *  @createDate   : 2016/11/10 0010 上午 11:10
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/10 0010 上午 11:10
	 *  @updateAuthor  :
	 */
	public PageModel findProductsByCondition(PageInfo pi, String areaIds, String categoryIds, String spId, String searchWord);

	/**
	 *  查询产品详情
	 *  @param   vo
	 * @return    : Map<String, Object>
	 *  @createDate   : 2016/11/10 0010 上午 11:11
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/10 0010 上午 11:11
	 *  @updateAuthor  :
	 */
	public Map<String, Object> findProductDetail(SpsMallProduct vo);

	/**
	 *  通过spId查询产品
	 *  @param   vo
	 * @return    : List<Map<String, Object>>
	 *  @createDate   : 2016/11/10 0010 上午 11:32
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/10 0010 上午 11:32
	 *  @updateAuthor  :
	 */
	public List<Map<String, Object>> findMallProductsBySpId(SpsMallProduct vo);

	/**
	 *  查询产品
	 *  @param   vo
	 * @return    : List<SpsMallProduct>
	 *  @createDate   : 2016/11/10 0010 下午 4:43
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/10 0010 下午 4:43
	 *  @updateAuthor  :
	 */
	public List<SpsMallProduct> freeFind(SpsMallProduct vo);

	/**
	 *  通过spid和areaid查找产品
	 *  @param   vo
	 * @return    : List<Map<String, Object>>
	 *  @createDate   : 2016/11/10 0010 上午 11:39
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/10 0010 上午 11:39
	 *  @updateAuthor  :
	 */
	public List<Map<String, Object>> findAllBySpIdAreaId(SpsMallProduct vo);

	/**
	 *  Sps保存产品数据
	 *  @param   vo
	 *  @param   submitArray
	 *  @param   chooseAreas
	 * @return    :
	 *  @createDate   : 2016/11/10 0010 上午 11:36
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/10 0010 上午 11:36
	 *  @updateAuthor  :
	 */
	public Result saveProductData(ContextInfo cti, SpsMallProduct vo, String submitArray, String chooseAreas);
	/**
	 *  通过主键查找产品
	 *  @param   vo
	 * @return    : SpsMallProduct
	 *  @createDate   : 2016/11/10 0010 上午 11:37
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/10 0010 上午 11:37
	 *  @updateAuthor  :
	 */
	public SpsMallProduct findByPK(SpsMallProduct vo);
	/**
	 *  保存商保产品
	 *  @param   itemId
	 *  @param   list
	 * @return    : Result
	 *  @createDate   : 2016/11/10 0010 上午 11:37
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/10 0010 上午 11:37
	 *  @updateAuthor  :
	 */
	public Result saveCiProduct(ContextInfo cti, Integer itemId, List<CiScheme> list);
	/**
	 *  保存商保组合产品
	 *  @param   itemId
	 *  @param   list
	 * @return    : Result
	 *  @createDate   : 2016/11/10 0010 上午 11:38
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/10 0010 上午 11:38
	 *  @updateAuthor  :
	 */
	public Result saveGroupProd(ContextInfo cti, Integer itemId, List<SpsMallProductItem> list);
}
