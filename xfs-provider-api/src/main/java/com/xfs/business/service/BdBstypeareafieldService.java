package com.xfs.business.service;

import java.util.List;
import java.util.Map;

import com.xfs.business.model.BdBstypeareafield;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;

/** 
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-11 14:46
 * @version 	: V1.0
 */
public interface BdBstypeareafieldService {
	
	public int save(ContextInfo cti, BdBstypeareafield vo);
	public int insert(ContextInfo cti, BdBstypeareafield vo);
	public int remove(ContextInfo cti, BdBstypeareafield vo);
	public int update(ContextInfo cti, BdBstypeareafield vo);
	public PageModel findPage(PageInfo pi, BdBstypeareafield vo);
	public List<BdBstypeareafield> findAll(BdBstypeareafield vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/05/24 11:25:05

	/**
	 *  通过城市类型id查询字段范围
	 *  @param   areaTypeId
	 *	@return 			: com.xfs.common.Result
	 *  @createDate  	: 2016-11-11 14:49
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:49
	 *  @updateAuthor  :
	 */
	public Result finAllFieldByAreaType(Integer areaTypeId);

	/**
	 *  保存模板设置字段
	 *  @param   areaTypeId, fields
	 *	@return 			: com.xfs.common.Result
	 *  @createDate  	: 2016-11-11 14:49
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:49
	 *  @updateAuthor  :
	 */
	public Result saveBsTypeAreaField(ContextInfo cti, Integer areaTypeId, String fields);

	/**
	 *  获取业务字段详情
	 *  @param   vo
	 *	@return 			: com.xfs.business.model.BdBstypeareafield
	 *  @createDate  	: 2016-11-11 14:50
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:50
	 *  @updateAuthor  :
	 */
	public BdBstypeareafield findBdBstypeareafield(BdBstypeareafield vo);

	/**
	 *  删除业务字段
	 *  @param   vo
	 *	@return 			: int
	 *  @createDate  	: 2016-11-11 14:50
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:50
	 *  @updateAuthor  :
	 */
	public int deleteBdBstypeareafields(ContextInfo cti, BdBstypeareafield vo);

	/**
	 *  获取业务字段列表
	 *  @param   vo
	 *	@return 			: java.util.List<com.xfs.business.model.BdBstypeareafield>
	 *  @createDate  	: 2016-11-11 14:50
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:50
	 *  @updateAuthor  :
	 */
	public List<BdBstypeareafield> findBdBstypeareafieldList(BdBstypeareafield vo);

	/**
	 *  查询 某个业务、某个区域下 字段信息
	 *  @param   vo
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.String>>
	 *  @createDate  	: 2016-11-11 14:48
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:48
	 *  @updateAuthor  :
	 */
	public List<Map<String,String>> findBsTypeField(BdBstypeareafield vo);


	/**
	 *  根据 类型名称 查询 业务类型字段
	 *  @param   vo
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.String>>
	 *  @createDate  	: 2016-11-11 14:47
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:47
	 *  @updateAuthor  :
	 */
	public List<Map<String,String>> findBsTypeFieldByName(BdBstypeareafield vo);

	/**
	 *  根据编码获取业务字段列表
	 *  @param   code
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.String>>
	 *  @createDate  	: 2016-11-11 14:47
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:47
	 *  @updateAuthor  :
	 */
	public List<Map<String,String>> findBsTypeFieldList(String code);
}
