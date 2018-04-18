package com.xfs.cp.service;
import java.util.Date;
import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.cp.model.CpPackage;

/**
 * CpPackageService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/08 10:55:22
 */
public interface CpPackageService {
	
	public int save(ContextInfo cti, CpPackage vo);
	public int insert(ContextInfo cti, CpPackage vo);
	public int remove(ContextInfo cti, CpPackage vo);
	public int update(ContextInfo cti, CpPackage vo);
	public PageModel findPage(PageInfo pi, CpPackage vo);
	public List<CpPackage> findAll(CpPackage vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 10:55:22

	public PageModel getASpListPage(PageInfo pi,CpPackage vo);
	/**
	 * 
	 * @method comments: 获取表列表
	 * @author   name  : wangchao  
	 * @create   time  : 2016年9月20日 下午3:56:12 
	 * @param cpPackage
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月20日 下午3:56:12 wangchao 创建
	 *
	 */
	public PageModel findPackagePage(PageInfo pi,CpPackage cpPackage, int pageIndex, int pageSize);
	
	
	

	public CpPackage findById(CpPackage obj);
	
	/**
	 * 
	 * @method comments: 获取首页包
	 * @author   name  : wangchao  
	 * @create   time  : 2016年9月21日 下午5:13:58 
	 * @param cpPackage
	 * @param i
	 * @param j
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月21日 下午5:13:58 wangchao 创建
	 *
	 */
	public List<CpPackage> findIndexList(CpPackage cpPackage, int i, int j);
	
	/**
	 * 
	 * @method comments: H5包列表
	 * @author   name  : baoyu  
	 * @create   time  : 2016年10月12日 下午5:42:42 
	 * @param cpPackage
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年10月12日 下午5:42:42 baoyu 创建
	 *
	 */
	public PageModel findPackagePage(PageInfo pi,CpPackage cpPackage);

    /**
     * 查询我的发包中被更多人抢包需要查看的发包条数
     * @autor luyong
     * @since 16-10-20 create
     * @param vo
     * @return
     */
    public Integer getASpNoReadCount(CpPackage vo);

	/**
	 *
	 * @method comments: 获取包
	 * @author   name  : baoyu
	 * @create   time  : 2016年9月24日 下午6:56:17
	 * @param cpPackage
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月24日 下午6:56:17 baoyu 创建
	 *
	 */
	public PageModel queryPackagePage(PageInfo pi,CpPackage cpPackage);
	/**
	 * 获取发包数量
	 *  @param id
	 *  @param time
	 *  @param date
	 *  @return
	 *	@return			: int
	 *  @createDate		: 2016年11月8日 下午3:33:41
	 *  @author			: wangchao
	 *  @version		: v1.0
	 *  @updateDate		: 2016年11月8日 下午3:33:41
	 *  @updateAuthor  	:
	 */
	public int findPackageNumber(Integer id, Date time, Date date);

	/**
	 * 根据包编号获取包信息
	 *  @param obj
	 *  @return 
	 *	@return			: CpPackage 
	 *  @createDate		: 2016年12月1日 下午2:26:10
	 *  @author			: wangchao 
	 *  @version		: v1.0
	 *  @updateDate		: 2016年12月1日 下午2:26:10
	 *  @updateAuthor  	:
	 */
	public CpPackage findByPackageNum(CpPackage obj);
	
	/**
	 * 修改包状态根据包id和发包的服务商id（防止其他用户修改此包）
	 *  @param sysUser
	 *  @param cpPackage
	 *  @return 
	 *	@return 			: int 
	 *  @createDate  	: 2016年12月12日 下午8:02:25
	 *  @author         	: songby 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016年12月12日 下午8:02:25
	 *  @updateAuthor  :
	 */
	public int updateByIdAndSpId(ContextInfo sysUser, CpPackage cpPackage);
	
	/**
	 * 已结束，已撤销删除(已有抢单)
	 *  @param sysUser
	 *  @param cpPackage
	 *  @return 
	 *	@return 			: boolean 
	 *  @createDate  	: 2016年12月12日 下午9:25:27
	 *  @author         	: songby 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016年12月12日 下午9:25:27
	 *  @updateAuthor  :
	 */
	public boolean updateByIdAndSpIdOfQuoted(ContextInfo sysUser, Integer cpPackageId);
	
	/**
	 * 未发布删除
	 *  @param sysUser
	 *  @param cpPackage
	 *  @return 
	 *	@return 			: int 
	 *  @createDate  	: 2016年12月13日 下午2:35:26
	 *  @author         	: songby 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016年12月13日 下午2:35:26
	 *  @updateAuthor  :
	 */
	public int delNPul(ContextInfo sysUser, CpPackage cpPackage);

}
