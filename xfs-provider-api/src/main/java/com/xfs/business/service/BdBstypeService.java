package com.xfs.business.service;

import java.util.List;

import com.xfs.business.model.BdBstype;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * 业务类型服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-11 14:54
 * @version 	: V1.0
 */
public interface BdBstypeService {
	
	public int save(ContextInfo cti, BdBstype vo);
	public int insert(ContextInfo cti, BdBstype vo);
	public int remove(ContextInfo cti, BdBstype vo);
	public int update(ContextInfo cti, BdBstype vo);
	public PageModel findPage(PageInfo pi, BdBstype vo);
	public List<BdBstype> findAll(BdBstype vo);
	public PageModel findAllList(PageInfo pi, BdBstype vo);

	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/05/24 11:25:05

	/**
	 *  获取业务类型
	 *  @param   bdBstype
	 *	@return 			: com.xfs.business.model.BdBstype
	 *  @createDate  	: 2016-11-11 14:57
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:57
	 *  @updateAuthor  :
	 */
	public BdBstype findBdBstypeByKey(BdBstype bdBstype);

	/**
	 *  根据地区获取业务类型列表
	 *  @param   areaId
	 *	@return 			: com.xfs.common.page.PageModel
	 *  @createDate  	: 2016-11-11 14:57
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:57
	 *  @updateAuthor  :
	 */
	public PageModel findBdBsTypeByAreaIdPM(PageInfo pi, int areaId);

	/**
	 *  根据条件查询业务类型
	 *  @param   ob
	 *	@return 			: java.util.List<com.xfs.business.model.BdBstype>
	 *  @createDate  	: 2016-11-11 14:58
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:58
	 *  @updateAuthor  :
	 */
	public List<BdBstype> findBstypeList(BdBstype ob);

	/**
	 *  获取业务类型
	 *  @param   bstypeId
	 *	@return 			: com.xfs.business.model.BdBstype
	 *  @createDate  	: 2016-11-11 14:59
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:59
	 *  @updateAuthor  :
	 */
	BdBstype findByPK(Integer bstypeId);

	/**
	 *  根据地区获取业务类型列表
	 *  @param   areaId
	 *	@return 			: java.util.List<com.xfs.business.model.BdBstype>
	 *  @createDate  	: 2016-11-11 14:59
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:59
	 *  @updateAuthor  :
	 */
	List<BdBstype> findBdBstypeListByAreaId(Integer areaId);
}
