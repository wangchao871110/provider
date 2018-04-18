package com.xfs.cp.service;

import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.cp.model.CpQuotedPrice;

/**
 * CpQuotedPriceService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/08 19:24:41
 */
public interface CpQuotedPriceService {
	
	public int save(ContextInfo cti, CpQuotedPrice vo);
	public int insert(ContextInfo cti, CpQuotedPrice vo);
	public int remove(ContextInfo cti, CpQuotedPrice vo);
	public int update(ContextInfo cti, CpQuotedPrice vo);
	public PageModel findPage(PageInfo pi, CpQuotedPrice vo);
	public List<CpQuotedPrice> findAll(CpQuotedPrice vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 19:24:41

	public PageModel findPageList(PageInfo pi,CpQuotedPrice obj);

	public int findPageListCount(CpQuotedPrice obj);

	public int updateStatus(ContextInfo cti, CpQuotedPrice obj) ;

	public int updateIsRead(ContextInfo cti, CpQuotedPrice obj) ;
	/**
	 * 
	 * @method comments: 保存报价
	 * @author   name  : wangchao  
	 * @create   time  : 2016年9月21日 下午3:34:06 
	 * @param request
	 * @param cpQuotedPrice
	 * @param result
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月21日 下午3:34:06 wangchao 创建
	 *
	 */
	public Result saveQuoted(ContextInfo user, CpQuotedPrice cpQuotedPrice, Result result);
	
	/**
	 * 获取抢包机构
	 *  @param packageId
	 *  @return 
	 *	@return			: List<Map<String,Object>> 
	 *  @createDate		: 2016年12月1日 下午2:22:52
	 *  @author			: wangchao 
	 *  @version		: v1.0
	 *  @updateDate		: 2016年12月1日 下午2:22:52
	 *  @updateAuthor  	:
	 */
	public List<Map<String, Object>> findQuotedPriceCp(int packageId);

	/**
	 * 获取某一个抢包机构
	 *  @param packageId
	 *  @return 
	 *	@return			: List<Map<String,Object>> 
	 *  @createDate		: 2016年12月1日 下午2:22:52
	 *  @author			: wangchao 
	 *  @version		: v1.0
	 *  @updateDate		: 2016年12月1日 下午2:22:52
	 *  @updateAuthor  	:
	 */
	public Map<String, Object> getQuotedPriceCpInfo(Map<String, Object> whereMap);

    /**
     * 获取某一个抢包机构
     */
    public PageModel findQuotedPrice(PageInfo pi,int packageId);
    /**
     * 根据服务商ID获取接包数量
     *  @param cpQuotedPrice
     *  @return 
     *	@return 			: int 
     *  @createDate  	: 2017年2月14日 下午4:33:12
     *  @author         	: wangchao 
     *  @version        	: v1.0
     *  @updateDate    	: 2017年2月14日 下午4:33:12
     *  @updateAuthor  :
     */
	public int findPackageNumber(CpQuotedPrice cpQuotedPrice);
	
}
