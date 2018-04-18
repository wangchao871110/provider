package com.xfs.cp.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.cp.dto.UserDto;
import com.xfs.cp.model.CpBusiness;
import com.xfs.cp.model.CpService;
import com.xfs.mall.item.model.BsMallItemCategory;
import com.xfs.user.model.SysUser;

/**
 * CpServiceService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/08 19:24:39
 */
public interface CpServiceService {

	public int save(ContextInfo cti, CpService vo);
	public int insert(ContextInfo cti, CpService vo);
	public int remove(ContextInfo cti, CpService vo);
	public int update(ContextInfo cti, CpService vo);
	public PageModel findPage(PageInfo pi, CpService vo);
	public List<CpService> findAll(CpService vo);

	/**
	 *
	 * @method comments: 个人中心 获取公司信息
	 * @author   name  : baoyu
	 * @param cpService
	 * @create   time  : 2016年9月8日 下午4:20:14
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月8日 下午4:20:14 baoyu 创建
	 *
	 */
	public CpService getCpServiceById(CpService cpService);
	/**
	 *
	 * @method comments: 个人中心 获取公司信息
	 * @author   name  : baoyu
	 * @param sp_id : 服务商id
	 * @create   time  : 2016年9月8日 下午4:20:14
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月8日 下午4:20:14 baoyu 创建
	 *
	 */
	public CpService getCpServiceById(Integer sp_id);

	/**
	 *
	 * @method comments: 保存公司信息（服务商信息）
	 * @author   name  : baoyu
	 * @create   time  : 2016年9月8日 下午8:28:11
	 * @param cpService
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月8日 下午8:28:11 baoyu 创建
	 *
	 */
	public int saveSpInfo(ContextInfo ci,CpService cpService);

	/**
	 *
	 * @method comments: 获取所有主营业务
	 * @author   name  : baoyu
	 * @create   time  : 2016年9月9日 上午10:35:30
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月9日 上午10:35:30 baoyu 创建
	 *
	 */
	public List<BsMallItemCategory> getAllBusiness();

	/**
	 *
	 * @method comments: 获取服务商选择的主营业务
	 * @author   name  : baoyu
	 * @create   time  : 2016年9月9日 上午10:49:10
	 * @param cpService
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月9日 上午10:49:10 baoyu 创建
	 *
	 */
	public List<CpBusiness> getBusinessById(CpService cpService);

	/**
	 *
	 * @method comments: 用户中心 统一获取左侧服务商信息（当前排名，发展指数，同业认证）
	 * @author   name  : baoyu
	 * @create   time  : 2016年9月11日 上午1:24:46
	 * @param cpServiceLeft
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月11日 上午1:24:46 baoyu 创建
	 *
	 */
	public CpService getSpLeftBaseInfo(CpService cpServiceLeft);

	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 19:24:39

	/**
	 *
	 * @method comments: 获取服务商排名
	 * @author   name  : wangchao
	 * @create   time  : 2016年9月11日 下午4:42:24
	 * @param cpService
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月11日 下午4:42:24 wangchao 创建
	 *
	 */
	public PageModel findRankingPage(PageInfo pi,CpService cpService, int pageIndex, int pageSize);

	/**
	 *
	 * @method comments: 获取选择的一级主营业务
	 * @author   name  : baoyu
	 * @create   time  : 2016年9月12日 上午10:16:11
	 * @param cpService
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月12日 上午10:16:11 baoyu 创建
	 *
	 */
	public List<Map<String, Object>> getBusinessAById(CpService cpService);



	/**
	 *
	 * @method comments: 获取特色服务
	 * @author   name  : baoyu
	 * @create   time  : 2016年9月12日 上午10:33:15
	 * @param cpService
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月12日 上午10:33:15 baoyu 创建
	 *
	 */
	public List<Map<String, Object>> spSpecialBusinessList(CpService cpService);

	/**
	 *
	 * @method comments: 获取二级主营业务
	 * @author   name  : baoyu
	 * @create   time  : 2016年9月12日 上午11:24:13
	 * @param string
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月12日 上午11:24:13 baoyu 创建
	 *
	 */
	public List<Map<String, Object>> getBusinessBById(Map<String, Object> content_id);

	/**
	 *
	 * @method comments: 编辑页面 点击 主营业务 获取二级主营业务
	 * @author   name  : baoyu
	 * @create   time  : 2016年9月12日 下午2:59:36
	 * @param whereMap
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月12日 下午2:59:36 baoyu 创建
	 *
	 */
	public List<Map<String, Object>> getBusinessBByCategoryId(Map<String, Object> whereMap);

	/**
	 *
	 * @method comments: 根据ID获取服务啥详情
	 * @author   name  : wangchao
	 * @create   time  : 2016年9月12日 上午10:31:51
	 * @param cpService
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月12日 上午10:31:51 wangchao 创建
	 *
	 */
	public CpService findCpServiceDetailById(CpService cpService);
	/**
	 *
	 * @method comments: 获取排名
	 * @author   name  : wangchao
	 * @create   time  : 2016年9月12日 上午11:04:07
	 * @param cpService
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月12日 上午11:04:07 wangchao 创建
	 *
	 */
	public Integer findOrderById(CpService cpService);

	/**
	 *
	 * @method comments: 获取平均成立年限
	 * @author   name  : baoyu
	 * @create   time  : 2016年9月12日 下午9:36:07
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月12日 下午9:36:07 baoyu 创建
	 *
	 */
	public Double getEstablishedAvgByCpId(Integer cpId);

	/**
	 *
	 * @method comments: 主营业务大类数/平均主营业务大类数；
	 * @author   name  : baoyu
	 * @create   time  : 2016年9月12日 下午10:23:14
	 * @param cpId
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月12日 下午10:23:14 baoyu 创建
	 *
	 */
	public Double getRateBusinessByCpId(Integer cpId);

	/**
	 *
	 * @method comments: 服务城市数/平均服务城市数*100
	 * @author   name  : baoyu
	 * @create   time  : 2016年9月12日 下午10:47:19
	 * @param cpId
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月12日 下午10:47:19 baoyu 创建
	 *
	 */
	public Double getRateServiceAreaByCpId(Integer cpId);

	/**
	 *
	 * @method comments: 成交总金额
	 * @author   name  : baoyu
	 * @create   time  : 2016年9月12日 下午11:04:52
	 * @param cpId
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月12日 下午11:04:52 baoyu 创建
	 *
	 */
	public Double getTransactionPriceByCpId(Integer cpId);

	/**
	 *
	 * @method comments: 接单总数
	 * @author   name  : baoyu
	 * @create   time  : 2016年9月12日 下午11:16:14
	 * @param cpId
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月12日 下午11:16:14 baoyu 创建
	 *
	 */
	public int getReciveOrderByCpId(Integer cpId);

	/**
	 *
	 * @method comments: 浏览次数
	 * @author   name  : baoyu
	 * @create   time  : 2016年9月12日 下午11:32:44
	 * @param cpId
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月12日 下午11:32:44 baoyu 创建
	 *
	 */
	public Double getRateBrowseNumByCpId(Integer cpId);

	/**
	 *
	 * @method comments: 关注人数
	 * @author   name  : baoyu
	 * @create   time  : 2016年9月12日 下午11:38:59
	 * @param cpId
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月12日 下午11:38:59 baoyu 创建
	 *
	 */
	public Double getRateAttentionNumByCpId(Integer cpId);

	/**
	 *
	 * @method comments: 同业认证
	 * @author   name  : baoyu
	 * @create   time  : 2016年9月12日 下午11:42:54
	 * @param cpId
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月12日 下午11:42:54 baoyu 创建
	 *
	 */
	public Double getRateIndustryNumByCpId(Integer cpId);

	/**
	 *
	 * @method comments: 服务效率
	 * @author   name  : baoyu
	 * @create   time  : 2016年9月12日 下午11:57:41
	 * @param cpId
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月12日 下午11:57:41 baoyu 创建
	 *
	 */
	public Double getEfficiencyNumByCpId(Integer cpId);

	/**
	 *
	 * @method comments: 服务态度
	 * @author   name  : baoyu
	 * @create   time  : 2016年9月12日 下午11:59:11
	 * @param cpId
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月12日 下午11:59:11 baoyu 创建
	 *
	 */
	public Double getAttitudeNumByCpId(Integer cpId);

	/**
	 *
	 * @method comments: 服务能力
	 * @author   name  : baoyu
	 * @create   time  : 2016年9月13日 上午12:00:30
	 * @param cpId
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月13日 上午12:00:30 baoyu 创建
	 *
	 */
	public Double getPowerNumByCpId(Integer cpId);

	/**
	 *
	 * @method comments: 服务专业度
	 * @author   name  : baoyu
	 * @create   time  : 2016年9月13日 上午12:02:10
	 * @param cpId
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月13日 上午12:02:10 baoyu 创建
	 *
	 */
	public Double getProfessionNumByCpId(Integer cpId);

	/**
	 *
	 * @method comments: 获取所有省
	 * @author   name  : baoyu
	 * @create   time  : 2016年9月13日 上午12:51:32
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月13日 上午12:51:32 baoyu 创建
	 *
	 */
	public List<Map<String, Object>> getProvinceList();

	/**
	 *
	 * @method comments: 获取所有城市
	 * @author   name  : baoyu
	 * @create   time  : 2016年9月13日 上午12:57:10
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月13日 上午12:57:10 baoyu 创建
	 *
	 */
	public List<Map<String, Object>> getCityList();
	/**
	 *
	 * @method comments: 获取所有县区
	 * @author   name  : baoyu
	 * @create   time  : 2016年9月13日 上午12:57:37
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月13日 上午12:57:37 baoyu 创建
	 *
	 */
	public List<Map<String, Object>> getAreaList();

	/**
	 *
	 * @method comments: 根据省id获取对应的市
	 * @author   name  : baoyu
	 * @create   time  : 2016年9月13日 上午1:18:29
	 * @param whereMap
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月13日 上午1:18:29 baoyu 创建
	 *
	 */
	public List<Map<String, Object>> getCityByFather(Map<String, Object> whereMap);
	/**
	 *
	 * @method comments: 根据市id获取对应的县
	 * @author   name  : baoyu
	 * @create   time  : 2016年9月13日 上午1:35:39
	 * @param whereMap
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月13日 上午1:35:39 baoyu 创建
	 *
	 */
	public List<Map<String, Object>> getAreaByFather(Map<String, Object> whereMap);

	/**
	 *
	 * @method comments: 注册资本
	 * @author   name  : baoyu
	 * @create   time  : 2016年9月13日 上午11:33:27
	 * @param cpId
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月13日 上午11:33:27 baoyu 创建
	 *
	 */
	public Double getRateRegisteredCapitalByCpId(Integer cpId);
	/**
	 *
	 * @method comments: 获取同城前两名服务机构
	 * @author   name  : wangchao
	 * @create   time  : 2016年9月13日 下午5:08:21
	 * @param city
	 * @param id
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月13日 下午5:08:21 wangchao 创建
	 *
	 */
	public List<CpService> findCpServiceByCity(String city, Integer id);
	/**
	 *
	 * @method comments: 获取首页数据
	 * @author   name  : wangchao
	 * @create   time  : 2016年9月18日 下午8:27:32
	 * @param cpService
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月18日 下午8:27:32 wangchao 创建
	 *
	 */
	public List<CpService> findIndexList(CpService cpService, int pageIndex, int pageSize);

	
	/**
	 * 
	 * @method comments: 接单成功率
	 * @author   name  : baoyu  
	 * @create   time  : 2016年9月26日 下午5:56:23 
	 * @param cpId
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月26日 下午5:56:23 baoyu 创建
	 *
	 */
	public Double getReciveOrderRateByCpId(Integer cpId);
	
	/**
	 * 
	 * @method comments: 投诉情况
	 * @author   name  : baoyu  
	 * @create   time  : 2016年9月26日 下午6:05:41   
	 * @param cpId
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月26日 下午6:05:41 baoyu 创建
	 *
	 */
	public int getComplainNumByCpId(Integer cpId);


	Result uploadImage(CpService cp, ContextInfo sysUser, MultipartHttpServletRequest multiRequest);


	//GridFSDBFile readFileById(String fileId);

	public Result cutImage(String fileid, int x, int y, int width, int height,ContextInfo sysUser,boolean flag);
	
	/**
	 * 
	 * @method comments: 验证填写的公司名称是否被其他公司注册过
	 * @author   name  : baoyu  
	 * @create   time  : 2016年10月9日 下午5:12:58 
	 * @param cpService
	 * @return 
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年10月9日 下午5:12:58 baoyu 创建
	 *
	 */
	public int getCpServiceExpId(CpService cpService);
	
	/**
	 * 
	 * @method comments: 获取H5页面排行榜
	 * @author   name  : wangchao  
	 * @create   time  : 2016年10月11日 下午9:11:03 
	 * @param cpService
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年10月11日 下午9:11:03 wangchao 创建
	 *
	 */
	public PageModel findH5RankingPage(PageInfo pi,CpService cpService);
	
	/**
	 * 
	 * @method comments: 获取派单数
	 * @author   name  : wangchao  
	 * @create   time  : 2016年10月11日 下午9:11:03 
	 * @param cpService
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年10月11日 下午9:11:03 wangchao 创建
	 *
	 */
	public Integer getCreatPackageCount(Integer cpId);
	/**
	 * 
	 * @method comments: 获取派单次数/最高派单数*100
	 * @author   name  : wangchao
	 * @create   time  : 2016年10月11日 下午9:11:03
	 * @param cpService
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年10月11日 下午9:11:03 wangchao 创建
	 *
	 */
	public Double getRateCreatPackageCount(Integer cpId);
	/**
	 *
	 * @method comments: 获取保证金
	 * @author   name  : wangchao
	 * @create   time  : 2016年10月11日 下午9:11:03
	 * @param cpService
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年10月11日 下午9:11:03 wangchao 创建
	 *
	 */
	public Double getRiskMarginOfSpsMallInfo(Integer cpId);
	/**
	 *
	 * @method comments: 获取咨询量（服务商-免费电话咨询的个数）
	 * @author   name  : wangchao
	 * @create   time  : 2016年10月11日 下午9:11:03
	 * @param cpService
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年10月11日 下午9:11:03 wangchao 创建
	 *
	 */
	public Integer getCpCustomerPhoneCount(Integer cpId);
	/**
	 *
	 * @method comments: 更新浏览次数
	 * @author   name  : wangchao  
	 * @create   time  : 2016年11月3日 下午1:25:41 
	 * @param id
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年11月3日 下午1:25:41 wangchao 创建
	 *
	 */
	public Integer updateBrowseNum(ContextInfo ci,CpService cps);

	/**
	 *
	 * @method comments: 根据市名获取城市
	 * @author   name  :  baoyu
	 * @create   time  : 2016年11月3日 下午1:25:41
	 * @param id
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年11月3日 下午1:25:41 wangchao 创建
	 *
	 */
	public List<Map<String, Object>> getCityByName(String cityOfCpAddress);

	public List<Map<String, Object>> FindCpServiceMap(CpService obj);
	/**
	 * 注册服务商
	 *  @param userDto
	 *	@return			: SysUser
	 *  @createDate		: 2016年11月16日 下午8:55:33
	 *  @author			: wangchao
	 *  @version		: v1.0
	 *  @updateDate		: 2016年11月16日 下午8:55:33
	 *  @updateAuthor  	:
	 */
	public SysUser registUser(UserDto userDto);
	/**
	 * 创建服务商
	 *  @param sysUser
	 *  @return
	 *	@return			: CpService
	 *  @createDate		: 2016年11月22日 上午9:20:37
	 *  @author			: wangchao
	 *  @version		: v1.0
	 *  @updateDate		: 2016年11月22日 上午9:20:37
	 *  @updateAuthor  	:
	 */
	public CpService saveCpService(SysUser sysUser);

	/**
	 * 增加指数更新
	 *  @return
	 */
	public int updateCustomIndex(ContextInfo cti, CpService vo);


	/**
	 * 根据id查找CpService
	 *  @return
	 */
	public CpService findByPK(CpService vo);
	
	/**
	 * Areacode获取area_id
	 *  @param string
	 *  @return 
	 *	@return 			: Object 
	 *  @createDate  	: 2016年12月23日 下午1:54:59
	 *  @author         	: songby 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016年12月23日 下午1:54:59
	 *  @updateAuthor  :
	 */
	public Integer getAreaByAreacode(String areacode);
	
	/**
	 * 查询对比服务商数据
	 *  @param packageId
	 *  @return 
	 *	@return 			: List<CpService> 
	 *  @createDate  	: 2017年2月14日 下午2:52:44
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年2月14日 下午2:52:44
	 *  @updateAuthor  :
	 */
	public List<CpService> cpServiceContrast(int packageId,ContextInfo sysUser);

}
