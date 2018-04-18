package com.xfs.mall.product.service;

import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.mall.product.model.SpsMallProductarea;

/**
 * @author  : duanax@xinfushe.com
 * @date  : 2016/11/10 0010 上午 11:29
 * @version  : V1.0
 */
public interface SpsMallProductareaService {
	
	public int save(ContextInfo cti, SpsMallProductarea vo);
	public int insert(ContextInfo cti, SpsMallProductarea vo);
	public int remove(ContextInfo cti, SpsMallProductarea vo);
	public int update(ContextInfo cti, SpsMallProductarea vo);
	public PageModel findPage(PageInfo pi, SpsMallProductarea vo);
	public List<SpsMallProductarea> findAll(SpsMallProductarea vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/09 14:08:05

	/**
	 *  查找产品的服务区域
	 *  @param   vo
	 * @return    : List<Map<String, String>>
	 *  @createDate   : 2016/11/10 0010 上午 11:29
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/10 0010 上午 11:29
	 *  @updateAuthor  :
	 */
	public List<Map<String, String>> findProductAreas(SpsMallProductarea vo);
	/**
	 *  查找产品的联盟服务区域
	 *  @param   productId
	 *  @param   spId
	 * @return    : List<Map>
	 *  @createDate   : 2016/11/10 0010 上午 11:29
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/10 0010 上午 11:29
	 *  @updateAuthor  :
	 */
	public List<Map> freeFindGROUP(String productId, String spId);
	/**
	 *  查找产品的直营服务区域
	 *  @param   productId
	 *  @param   spId
	 * @return    : List<Map>
	 *  @createDate   : 2016/11/10 0010 上午 11:29
	 *  @author          : duanax@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/10 0010 上午 11:29
	 *  @updateAuthor  :
	 */
	public List<Map> freeFindCOOPAndINNER(String productId, String spId);
	
}
