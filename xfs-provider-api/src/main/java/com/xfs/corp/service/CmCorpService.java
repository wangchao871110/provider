package com.xfs.corp.service;

import java.util.List;
import java.util.Map;

import com.xfs.bill.model.SpsBill;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.corp.dto.CorpInfo;
import com.xfs.corp.model.CmCorp;
import com.xfs.sp.model.SpCmRelation;
import com.xfs.user.model.SysUser;

/**
 * @author 	: yangfangwei
 * @date 	: 2016-11-8 14:10:00
 * @version 	: V1.0
 */
public interface CmCorpService {

	public String REGIST_SOURCE = "source";

	public int save(ContextInfo cti, CmCorp vo);

	public int insert(ContextInfo cti, CmCorp vo);

	public int remove(ContextInfo cti, CmCorp vo);

	public int update(ContextInfo cti, CmCorp vo);

	public List<CmCorp> findAll(CmCorp vo);

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/04/11 16:09:14
	// 根据corpId 查询企业数据

	CmCorp findByPK(Integer cpId);

	public CmCorp findOneByCorpId(CmCorp vo);

	public Result insertCorp(ContextInfo cti, CmCorp vo);

	// 根据企业ID 删除企业关系表中数据
	public Result removeCorp(ContextInfo cti, CmCorp vo);

	// 根据企业ID 删除企业关系表中数据
	public Result removeCorpNew(ContextInfo cti, CmCorp vo);

	// 删除客户之前查信客户的相关的信息 luyong 删除校验
	public Result removeCorpChek(ContextInfo cti, CmCorp vo);

	public void saveCorp(ContextInfo cti,CmCorp cmCorp, SpCmRelation cmRelation);

	public Result editCorp(ContextInfo cti, Integer cid, Integer orgId);

	public Result checkCorpInfo(CmCorp corp);

	/**
	 * 读取导入文件sheet标题行信息
	 * @param fileId
	 * @return
	 * @throws Exception
     */
	public Result readImportFileInfo(Integer fileId)  throws Exception;

	/**
	 * 读取文件与实体对应关系
	 * @param fileId
	 * @param sheetName
	 * @return
	 * @throws Exception
     */
	public Result readTemplateRealtion(Integer fileId, String sheetName) throws Exception;

	/**
	 * 导入企业
	 * @param fileId
	 * @param curUser
	 * @param selectContent
	 * @param sheetName
     * @return
     */
	public Result importCorpData(ContextInfo cti, Integer fileId, String selectContent, String sheetName);
	/**
	 * 自动导入更新员工
	 * @param cpId
	 * @return
	 */
	public Result autoUpsertEmployee(ContextInfo cti, Integer cpId, Integer spId);

	/**
	 * 查询服务商下企业
	 * @param vo
	 * @return
     */
	public List<CmCorp> findCorpBySpId(CmCorp vo, ContextInfo cti);

	/**
	 * 根据企业ID和服务商ID定位企业
	 * @param vo
	 * @return
	 */
	public CmCorp findOneByCorpIdAndSpId(CmCorp vo);
	/**
	 * 创建企业账号
	 *
	 * @author lifq
	 *
	 * 2016年7月29日  下午3:47:04
	 */
	public Result createEmpUser(ContextInfo cti, Integer cpId);
	/**
	 * 查询 手机号 是否存在
	 *
	 * @author lifq
	 *
	 * 2016年8月14日  下午5:16:07
	 */
	public List<Map<String,Object>> findExistMobile(String mobile, String cpId);

	/**
	 * 根据spId获取企业列表
	 * @param spId
	 * @return
	 */
	public List<CmCorp> findCorpBySpId(Integer spId, ContextInfo cti);

	/**
	 * 不带数据权限获取企业列表
	 * @param spId
	 * @param user
	 * @return
	 */
	List<CmCorp> findCorpBySpIdWithOutAuth(Integer spId, SysUser user);
	
	/**
	 * 查询小库信息（分页查询）
	 *
	 * @author lifq
	 *
	 * 2016年9月23日  上午11:17:06
	 */
	public PageModel findSingleAccountPage(PageInfo pi, Map<String, String> paraMap,
                                           ContextInfo cti);

	/**
	 * <p>Title: bs查询账目企业</p>
	 * <p>Description: bs查询账目企业</p>
	 * ${tags}
	 */
	public PageModel queryCorpAccountList(PageInfo pi, CmCorp cmCorp);
	/**
	 * <p>Title: bs查询企业</p>
	 * <p>Description: bs查询企业</p>
	 * ${tags}
	 */
	public List<CmCorp> findAllToBs(CmCorp vo);
	/**
	 * <p>Title: bs无条件查询企业总数</p>
	 * <p>Description: bs无条件查询企业总数</p>
	 * ${tags}
	 */
	public int countFindAll();
	/**
	 * <p>Title: bs通过创建日期查询企业</p>
	 * <p>Description: bs通过创建日期查询企业</p>
	 * ${tags}
	 */
	public List<Map<String, Object>> countByDay(String stringDate);

	/**
	 * <p>Title: 条件查询企业</p>
	 * <p>Description: 条件查询企业</p>
	 * ${tags}
	 */
	public PageModel findAllCropList(PageInfo pi, CmCorp cmCorp);

	/**
	 * <p>Title: 企业HR 开通账号 给手机号发验证码 并校验手机</p>
	 * <p>Description: 企业HR 开通账号 给手机号发验证码 并校验手机</p>
	 * ${tags}
	 */
	public Result getCode(CorpInfo corpInfo);
	/**
	 * <p>Title: 条件查询企业</p>
	 * <p>Description: 条件查询企业 返回map类型集合</p>
	 * ${tags}
	 */
	public List<Map<String, Object>> FindAllMap(CmCorp obj);

	/**
	 * <p>Title: 分页查询企业</p>
	 * <p>Description: 分页查询企业</p>
	 * ${tags}
	 */
	public PageModel findPage(ContextInfo cti, PageInfo pi, CmCorp vo) ;
	/**
	 * <p>Title: bs端注册企业并更新关联表</p>
	 * <p>Description: bs端注册企业并更新关联表</p>
	 * ${tags}
	 */
	public Result register(CorpInfo corpInfo, String source);

	public Result registerUser(ContextInfo cti, CmCorp cmCorp, SysUser sysUser);

	public CmCorp registerCorp(CorpInfo corpInfo,String channel,String sname,String source);

	public void insertFunctionData(ContextInfo cti,Integer userId,Integer dataId,String dataType,String userType);

	/**
	 * <p>Title: cs端校验企业信息</p>
	 * <p>Description: cs端校验企业信息</p>
	 * ${tags}
	 */
	public Result checkCmCorp(CorpInfo corpInfo);

	/**
	 * <p>Title: cs端更新 公司信息（带校验）</p>
	 * <p>Description: cs端更新 公司信息（带校验）</p>
	 * ${tags}
	 */
	public void updateCmcorp(ContextInfo cti, CmCorp cmCorp);

	/**
	 * <p>Title: cs端通过名称查询企业</p>
	 * <p>Description: cs端通过名称查询企业</p>
	 * ${tags}
	 */
	public CmCorp findByCorpName(CmCorp cmCorpParam);

	/**
	 * <p>Title: cs端查询企业</p>
	 * <p>Description: cs端查询企业联查地区</p>
	 * ${tags}
	 */
	public CmCorp findOneByCorpIdToCs(CmCorp vo);

	/**
	 * <p>Title: cs端注册企业并更新关联表</p>
	 * <p>Description: cs端注册企业并更新关联表</p>
	 * ${tags}
	 */
	public Result register(CorpInfo corpInfo, String channel, String sname, String source) throws Exception;

	/**
	 * <p>Title: cs端注册企业并更新关联表</p>
	 * <p>Description: cs端注册企业并更新关联表</p>
	 * ${tags}
	 */
	public Result register(CorpInfo corpInfo, SysUser sysUser, String channel, String sname, String source) throws Exception;

	/**
	 * <p>Title: cs端更新认证前置信息</p>
	 * <p>Description: cs端更新认证前置信息</p>
	 * ${tags}
	 */
	public Result updateInfo(ContextInfo cti, CmCorp cmCorp);
	/**
	 * <p>Title: cs端企业认证</p>
	 * <p>Description: cs端企业认证</p>
	 * ${tags}
	 */
	public Result saveLegalize(ContextInfo cti, CmCorp cmCorp);
	/**
	 * <p>Title: cs端更新企业Logo</p>
	 * <p>Description: cs端更新企业Logo</p>
	 * ${tags}
	 */
	public Result updateCorpLogo(ContextInfo cti, Integer logo);
	/**
	 * <p>Title: cs端首页信息</p>
	 * <p>Description: cs端首页信息</p>
	 * ${tags}
	 */
	public Result indexInit(Integer cpId);
	/**
	 * <p>Title: cs端首页任务单信息</p>
	 * <p>Description: cs端首页任务单信息</p>
	 * ${tags}
	 */
	public Map<String,Object> taskStatistical(Integer cpId);

	public CmCorp findByPk(CmCorp corp);

	/**
	 * 福利平台更新企业信息
	 * @param cti
	 * @param cmCorp
	 * @return
	 */
	public Result updateInfoWelfare(ContextInfo cti, CmCorp cmCorp);

    /**
     * @Title: findBillInfo
     * @Description: 查找该企业实缴账单
     * @param @param pi
     * @param @param vo
     * @param @return    设定文件
     * @return PageModel    返回类型
     * @throws
     */
    public PageModel findBillInfo(PageInfo pi,SpsBill vo);

	/**
	 *  H5 HR服务包登录获取验证码
	 *  @param
	 * @return    :
	 *  @createDate   : 2016/12/5 20:36
	 *  @author          : gaoyf@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/12/5 20:36
	 *  @updateAuthor  :
	 */
	public Result getPackageCode(CorpInfo corpInfo);

    public PageModel findPageBillTemp(ContextInfo cti,PageInfo pi, CmCorp vo);

    public List freeFindCorpIdsBySpId(ContextInfo cti, CmCorp vo);

	/**
	 *  为服务机构
	 *  @param   sendPageSpId 发包方
	 *  @param   receivePageSpId 接包方
	 *  @param   receivePageUserId 接包人ID
	 *  @param   scoure  来源业务标识
	 *	@return 			: com.xfs.common.Result
	 *  @createDate  	: 2016-12-23 16:27
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-12-23 16:27
	 *  @updateAuthor  :
	 */
	public Result openSpInventedCorp(ContextInfo cti,Integer sendPageSpId,Integer receivePageSpId,Integer receivePageUserId,String scoure);

	/**
	 *  修改虚拟企业信息
	 *  @param   cti
	 *  @param   spId
	 *	@return 			: com.xfs.common.Result
	 *  @createDate  	: 2016-12-30 10:53
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-12-30 10:53
	 *  @updateAuthor  :
	 */
	public Result modifySpInventedCorp(ContextInfo cti,Integer spId);
	
	public Result updateIfStop(ContextInfo cti,CmCorp cmCorp);


	public CmCorp findCmCorpByTenantId(String tenantId);

	/**
	 *  获取未同步的企业列表
	 *  @param
	 *	@return 			: java.util.Map<java.lang.String,com.xfs.corp.model.CmCorp>
	 *  @createDate  	: 2017-05-22 15:17
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-05-22 15:17
	 *  @updateAuthor  :
	 */
	public Map<String,CmCorp> queryAllUnSynList();

	public Result insertUserAndCity(ContextInfo cti, CmCorp cmCorp, SysUser sysUser) throws Exception;


	/**
	 * @Title: 获取公司的所有信息 ， 并且获取公司的人数， 包括saas  和福利的
	 * @param    cmCorp 要查询的条件
	 * @return 
	 * @createDate 2017/8/8 10:06
	 * @Auther:zhanghongjie【hongjievip6688@163.com】
	 * @version        : v1.0
	 * @updateDate    	: 
	 * @updateAuthor  :
	*/
	public  List<Map> getAllCompInfoByCondition(CmCorp cmCorp);



	public  CmCorp getCorpByCpIdOrTenantId(String id);

	public List<CmCorp> checkNologinCorpList();

}
