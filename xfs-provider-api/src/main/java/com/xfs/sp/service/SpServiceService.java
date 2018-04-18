package com.xfs.sp.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xfs.base.model.BsArea;
import com.xfs.base.model.BsEcontractApply;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.mall.info.model.SpsMallInfo;
import com.xfs.mall.product.model.SpsMallProduct;
import com.xfs.serviceBill.dto.ServiceBillVo;
import com.xfs.sp.model.SpService;
import com.xfs.sp.model.SpsChangedoc;

/**
 * SpServiceService
 * @author:quanjiahua<quanjiahua@163.com>
 * 
 * @date:2016/04/11 15:31:40
 */
public interface SpServiceService {

	public int save(ContextInfo cti, SpService vo);

	public int insert(ContextInfo cti, SpService vo);

	public int remove(ContextInfo cti, SpService vo);

	public int update(ContextInfo cti, SpService vo);

	public PageModel findPage(PageInfo pi, SpService vo);

	public List<SpService> findAll(SpService vo);

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/04/11 15:31:40

	public SpService findByPk(Integer spId);

	/**
	 * 查询OpenList联系人信息
	 * 
	 * @param spId
	 * @return
	 */
	public SpService findOLContacts(Integer spId);

	/**
	 * 根据员工信息反查服务商信息
	 * 
	 * @return
	 */
	public SpService findOLContactsByCmPerson(String mobile);

	Result spJoinSubmit(ContextInfo cti, SpService sp, String password);

	Result uploadImage(ContextInfo cti,SpService sp, MultipartHttpServletRequest multiRequest);

	public Result cutImage(ContextInfo cti,String fileid, int x, int y, int width, int height, boolean flag);

    Result cutImage(ContextInfo cti,String fileId, int x, int y, int width, int height);

    boolean findSpServiceByName(SpService spService);
    public List<String> findAllSpServiceName(SpService spService);
	public Map findAreaIdBySpId(String spId);
	
	/*模糊查询spId和spName*/
	public List<SpService> freeFindSpidSpName(SpService spService);

	//初始化sp角色权限
	void initSpRole(ContextInfo cti, Integer user_id);

    /**
     * 查询优质服务商列表
     *
     * @return
     */
    public List<SpService> findMallSpServies(BsArea area);

    /**
     * 查询服务机构列表
     *
     * @param areaIds
     * @return
     */
    public List<Map<String, Object>> findMallSpServices(String areaIds);

    /**
     * 服务机构列表
     *
     * @param areaId
     * @param categoryId
     * @param certificationType
     * @param searchWord
     * @return
     */
    public PageModel findServicesByCondition(PageInfo pi, String areaId, String categoryId, String certificationType, String yearRange, String searchWord);

    public Map<String, Object> findMallSpServiceByProductId(SpsMallProduct vo);


    public SpService findByPK(SpService vo);

    //分页排序
    public PageModel findPageOrder(PageInfo pi, SpService vo);

    //分页排序，地区连接查询
    public PageModel findAndareaPageOrder(PageInfo pi, SpService vo);


    SpService findByPK(Integer spid);

    //分页排序，服务商审核
    PageModel findSpServiceByState(PageInfo pi,String serviceState,String enabledMall, String spname);

    Result refuseSpService(ContextInfo cti,SpService vo, String reason);

    Result signSpService(ContextInfo cti,SpService vo);

    Result passAudit(ContextInfo cti,SpService vo);
    Result noPassAudit(ContextInfo cti,SpService vo);


    Result stopSpService(ContextInfo cti,SpService vo);
    Result stopMall(ContextInfo cti,SpService vo);
    Result activeMall(ContextInfo cti,SpService vo);

    Result activeSpService(ContextInfo cti,SpService vo);

    Result spJoinSubmit(ContextInfo cti,SpService sp);

    public int countFreeFindByStatus();

    public PageModel findSpServiceByRecom(PageInfo pi, String spName);

    public Map<String,Object> CountFreeInfoBySpId(Integer spId);

    public void updateSpService(ContextInfo cti,SpService vo);

    /**
     *
     * @method comments: 根据ID获取服务商信息
     * @author   name  : wangchao
     * @create   time  : 2016年9月10日 下午6:40:20
     * @param saasSpId
     * @return
     * @throws
     * @modify   list  : 修改时间和内容
     * 2016年9月10日 下午6:40:20 wangchao 创建
     *
     */
    public SpService findSpServiceById(Integer saasSpId);
    /**
     * @Title: saveSpORMallinfo 
     * @Description: 服务商编辑页面保存
     * @param @param request
     * @param @param vo
     * @param @param spsMallInfo
     * @param @return    设定文件 
     * @return Result    返回类型 
     * @throws
      */
    public Result saveSpORMallinfo(HttpServletRequest request,SpService vo, SpsMallInfo spsMallInfo);

    public Result saasspsUpdate(HttpServletRequest request, SpService vo, SpsChangedoc spsChangedoc,
			BsEcontractApply apply, SpsMallInfo spsMallInfo, String state, String successCaseName, String successCase,
			String orderType,String busiLicenseFile1);


    public SpService findSpByInventedCorp(Integer inventCpId);
    
    /**
	 * 获取服务商对账列表
	 *  @param vo
	 *  @param pageIndex
	 *  @param pageSize
	 *  @return 
	 *	@return 			: List<ServiceBillVo> 
	 *  @createDate  	: 2017年7月10日 下午3:30:38
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年7月10日 下午3:30:38
	 *  @updateAuthor  :
	 */
	public List<ServiceBillVo> findServiceBillList(ServiceBillVo vo, int pageIndex, int pageSize);

	/**
	 * 获取服务商对账总数
	 *  @param vo
	 *  @return 
	 *	@return 			: Integer 
	 *  @createDate  	: 2017年7月10日 下午3:30:42
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年7月10日 下午3:30:42
	 *  @updateAuthor  :
	 */
	public Integer findServiceBillListCount(ServiceBillVo vo);

	/**
	 * 获取对账服务商
	 *  @param vo
	 *  @return 
	 *	@return 			: List<ServiceBillVo> 
	 *  @createDate  	: 2017年7月12日 下午2:08:03
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年7月12日 下午2:08:03
	 *  @updateAuthor  :
	 */
	public List<ServiceBillVo> findServiceBillCount(ServiceBillVo vo);
	
	/**
	 * 根据 服务商名称 查询，进行重复校验
	 *
	 * @author lifq
	 *
	 * 2017年8月7日  下午1:51:52
	 */
	public List<Map<String,Object>> findSpByName(Map<String,Object> paraMap);
    
}
