package com.xfs.base.service;

import java.util.List;
import java.util.Map;

import com.xfs.base.dto.BusinessField;
import com.xfs.base.model.BdBusinessfield;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * 地区业务字段配置服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-11 14:25
 * @version 	: V1.0
 */
public interface BdBusinessfieldService {
	
	public int save(ContextInfo cti, BdBusinessfield vo);
	public int insert(ContextInfo cti, BdBusinessfield vo);
	public int remove(ContextInfo cti, BdBusinessfield vo);
	public int update(ContextInfo cti, BdBusinessfield vo);
	public PageModel findPage(PageInfo pi, BdBusinessfield vo);
	public List<BdBusinessfield> findAll(BdBusinessfield vo);
	public PageModel findAllList(PageInfo pi, BdBusinessfield vo);
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/05/24 11:25:08

	/**
	 * 获取业务字段信息
	 *  @param   vo
	 *	@return 			: com.xfs.base.model.BdBusinessfield
	 *  @createDate  	: 2016-11-11 14:22
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:22
	 *  @updateAuthor  :
	 */
	public BdBusinessfield findBdBussinessFieldByKey(BdBusinessfield vo);

	/**
	 *  分页获取地区业务类型字段
	 *  @param   areaId, bstypeId
	 *	@return 			: com.xfs.common.page.PageModel
	 *  @createDate  	: 2016-11-11 14:21
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:21
	 *  @updateAuthor  :
	 */
	public PageModel findbsTypeAreaFieldPage(PageInfo pi, int areaId, int bstypeId);

	/**
	 *  根据地区/业务获取动态控件组
	 *  @param   areaId, bsTypeId
	 *	@return 			: java.util.List<com.xfs.base.dto.BusinessField>
	 *  @createDate  	: 2016-11-11 14:17
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:17
	 *  @updateAuthor  :
	 */
	public List<BusinessField> queryBusinessFields(Integer areaId, Integer[] bsTypeId);

	/**
	 *  根据地区/业务类型获取动态控件组
	 *  @param   areaId, bsTypeId
	 *	@return 			: java.util.Map<java.lang.String,com.xfs.base.dto.BusinessField>
	 *  @createDate  	: 2016-11-11 14:20
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:20
	 *  @updateAuthor  :
	 */
	public Map<String,BusinessField> queryBusinessFields(Integer areaId, Integer bsTypeId);

	/**
	 *  特殊查询 增员必须的参保类型
	 *  @param   areaId
	 *	@return 			: com.xfs.base.dto.BusinessField
	 *  @createDate  	: 2016-11-11 14:16
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:16
	 *  @updateAuthor  :
	 */
	public BusinessField findLivingType(Integer areaId);

	/**
	 *  根据地区/业务类型获取动态控件组
	 *  @param   areaId, bstypeId
	 *	@return 			: java.util.List<com.xfs.base.model.BdBusinessfield>
	 *  @createDate  	: 2016-11-11 14:23
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:23
	 *  @updateAuthor  :
	 */
	public List<BdBusinessfield> bdBusinessFieldList(Integer areaId, Integer bstypeId);


    public List<BdBusinessfield> queryBusinessFieldsBySource(BdBusinessfield vo);
    /**
     * 根据code获取字段信息
     *  @param key
     *  @return 
     *	@return 			: BdBusinessfield 
     *  @createDate  	: 2017年3月16日 下午3:44:50
     *  @author         	: wangchao 
     *  @version        	: v1.0
     *  @updateDate    	: 2017年3月16日 下午3:44:50
     *  @updateAuthor  :
     */
	public BdBusinessfield findNameByCode(String key);


}
