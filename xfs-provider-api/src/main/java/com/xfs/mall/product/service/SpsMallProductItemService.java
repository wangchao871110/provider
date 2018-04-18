package com.xfs.mall.product.service;

import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.mall.product.model.SpsMallProduct;
import com.xfs.mall.product.model.SpsMallProductItem;

/** 
 * @author  : duanax@xinfushe.com
 * @date  : 2016/11/10 0010 上午 11:10
 * @version  : V1.0
 */
public interface SpsMallProductItemService {
	
	public int save(ContextInfo cti, SpsMallProductItem vo);
	public int insert(ContextInfo cti, SpsMallProductItem vo);
	public int remove(ContextInfo cti, SpsMallProductItem vo);
	public int update(ContextInfo cti, SpsMallProductItem vo);
	public PageModel findPage(PageInfo pi, SpsMallProductItem vo);
	public List<SpsMallProductItem> findAll(SpsMallProductItem vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/10 15:11:16
	
	/**  
	 *  根据产品Id得到该产品的预制服务项目
	 *  @param   productId
	 * @return    : List<Map<String, Object>>
	 *  @createDate   : 2016/11/10 0010 上午 11:09
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/10 0010 上午 11:09
	 *  @updateAuthor  :
	 */
	public List<Map<String, Object>> findAllByProductId(Integer productId);

	/**
	 *  根据产品ID和区域ID获取服务项
	 *  @param   productId
	 *  @param   areaId
	 * @return    : List<Map<String, Object>>
	 *  @createDate   : 2016/11/10 0010 上午 10:27
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/10 0010 上午 10:27
	 *  @updateAuthor  :
	 */
	public List<Map<String, Object>> findAllByProductIdAndAreaId(Integer productId, Integer areaId);
	/**
	 *  根据产品id查询产品的服务项
	 *  @param   vo
	 * @return    : List<Map<String, Object>>
	 *  @createDate   : 2016/11/10 0010 上午 10:26
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/10 0010 上午 10:26
	 *  @updateAuthor  :
	 */
	public List<Map<String, Object>> findProduectItems(SpsMallProduct vo);

	/**
	 *  根据产品ids查询产品的服务项
	 *  @param   productIds
	 * @return    : List<SpsMallProductItem>
	 *  @createDate   : 2016/11/10 0010 上午 10:24
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/10 0010 上午 10:24
	 *  @updateAuthor  :
	 */
	public List<SpsMallProductItem> findProduectItemsByProductIds(String productIds);
	
}
