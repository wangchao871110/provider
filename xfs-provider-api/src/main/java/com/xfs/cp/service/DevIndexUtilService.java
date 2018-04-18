package com.xfs.cp.service;

public interface DevIndexUtilService {
	/**
	 * 合计指数
	 *  @return 
	 *	@return 			: double 
	 *  @createDate  	: 2017年1月9日 下午5:43:59
	 *  @author         	: songby 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年1月9日 下午5:43:59
	 *  @updateAuthor  :
	 */
	public double sumDevIndex(Integer cpId);
	
	/**
	 * 工商信息验证
	 *  @param cpId
	 *  @return 
	 *	@return 			: double 
	 *  @createDate  	: 2017年1月9日 下午6:20:39
	 *  @author         	: songby 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年1月9日 下午6:20:39
	 *  @updateAuthor  :
	 */
	public double companyInformation(Integer cpId);
	
	/**
	 * 保证金
	 *  @param cpId
	 *  @return 
	 *	@return 			: double 
	 *  @createDate  	: 2017年1月9日 下午6:21:05
	 *  @author         	: songby 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年1月9日 下午6:21:05
	 *  @updateAuthor  :
	 */
	public double cooperativeRelationshi(Integer cpId);
	
	/**
	 * 平台成交情况
	 *  @param cpId
	 *  @return 
	 *	@return 			: double 
	 *  @createDate  	: 2017年1月9日 下午6:21:41
	 *  @author         	: songby 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年1月9日 下午6:21:41
	 *  @updateAuthor  :
	 */
	public double transaction(Integer cpId);
	
	/**
	 * 服务评价
	 *  @param cpId
	 *  @return 
	 *	@return 			: double 
	 *  @createDate  	: 2017年1月9日 下午6:23:31
	 *  @author         	: songby 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年1月9日 下午6:23:31
	 *  @updateAuthor  :
	 */
	public double evaluate(Integer cpId);
	
	/**
	 * 被关注度
	 *  @param cpId
	 *  @return 
	 *	@return 			: double 
	 *  @createDate  	: 2017年1月9日 下午6:24:05
	 *  @author         	: songby 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年1月9日 下午6:24:05
	 *  @updateAuthor  :
	 */
	public double concern(Integer cpId);
}
