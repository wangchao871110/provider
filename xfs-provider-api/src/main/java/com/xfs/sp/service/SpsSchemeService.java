package com.xfs.sp.service;

import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.sp.model.SpsScheme;


 /**
 * 方案接口类
 * @author 	: yangfw@xinfushe.com
 * @date 	: 2016-11-11 11:09
 * @version 	: V1.0
 */
public interface SpsSchemeService {
	
	public int save(ContextInfo cti, SpsScheme vo);
	public int insert(ContextInfo cti, SpsScheme vo);
	public int remove(ContextInfo cti, SpsScheme vo);
	public int update(ContextInfo cti, SpsScheme vo);
	public PageModel findPage(PageInfo pi, SpsScheme vo);
	public List<SpsScheme> findAll(SpsScheme vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/03 16:26:35
	/**
	 * 根据主键查询 实体
	 *
	 * @author lifq
	 *
	 * 2016年8月4日  上午9:58:03
	 */
	public SpsScheme findByPK(SpsScheme vo);
	
	/**
	 * 查询 方案
	 *
	 * @author lifq
	 *
	 * 2016年8月4日  上午11:42:41
	 */
	public List<Map<String,Object>> findSchemes(Integer sp_id, Integer cp_id);
	
	/**
	 * 保存方案
	 *
	 * @author lifq
	 *
	 * 2016年8月4日  下午8:28:44
	 */
	public Result saveScheme(ContextInfo cti, SpsScheme scheme, Integer type, Integer spId,String[] itemIdArr,String[] feeTypeArr,String[] itemPriceArr,String selectedInsuranceId);
	/**
	 * 复制方案
	 *
	 * @author lifq
	 *
	 * 2016年8月5日  上午9:47:15
	 */
	public Result copyScheme(ContextInfo cti, SpsScheme scheme);
	/**
	 * 停用方案
	 *
	 * @author lifq
	 *
	 * 2016年8月5日  上午9:47:15
	 */
	public Result stopScheme(ContextInfo cti, SpsScheme scheme);
	List<SpsScheme> findSchemeByIds(Integer sp_id, List<Integer> ids);
	
	/**
	 * 保存 分包方案
	 *
	 * @author lifq
	 *
	 * 2016年8月10日  下午8:32:01
	 */
	public Result saveDeputeScheme(ContextInfo cti, SpsScheme scheme, Integer type, Integer parentSpId, Integer sysSpId,String[] itemIdArr,String[] feeTypeArr,String[] itemPriceArr,String selectedInsuranceId,String selectedEmpId);
	/**
	 * 查看 分包方案
	 *
	 * @author lifq
	 *
	 * 2016年8月10日  下午9:26:12
	 */
	public String viewPackageScheme(Integer schemeId, Integer spId);


	/**
	 * 转方案
	 *
	 * @author lifq
	 *
	 * 2016年8月11日  下午9:18:08
	 */
	public Result transferScheme(ContextInfo cti, Integer fromSchemeId, Integer toSchemeId, Integer spId, String selEmpId);
	//查询 正常使用的 方案
    public List<Map<String,Object>> findSchemesState(Integer sp_id, Integer cp_id);
    
    public SpsScheme findByParentSchemeId(Integer spId, Integer schemeId);
    /**
     * 启用方案
     *
     * @author lifq
     *
     * 2016年8月24日  上午9:30:49
     */
    public Result useScheme(ContextInfo cti, SpsScheme scheme);


	/**
	 * 根据spId查询所有方案所属城市
	 *
	 * @author wuzhe
	 *
	 * 2016年10月18日  上午10:48:36
	 */
	public List<Map<String, Object>> findAllSchemeArea(Map<String, Object> paraMap);

	 /**
	  *  根据查询条件获取企业账单列表
	  *  @param   cpId	企业id
	  *  @param   schemeTypeIn 方案类型
	  *  @param   state 方案状态
	  *	@return 			: SpsScheme
	  *  @createDate  	: 2016-11-11 11:41
	  *  @author         	: yfw@xinfushe.com
	  *  @version        	: v1.0
	  *  @updateDate    	: 2016-11-11 11:41
	  *  @updateAuthor  :
	  */
	 public List<SpsScheme> freeFindInType(Integer cpId,String[] schemeTypeIn,String state);

	 /**
	  *  获取所有企业账单日
	  *  @param
	  *	@return 			: java.util.List<com.xfs.sp.model.SpsScheme>
	  *  @createDate  	: 2016-11-23 11:15
	  *  @author         	: konglc@xinfushe.com
	  *  @version        	: v1.0
	  *  @updateDate    	: 2016-11-23 11:15
	  *  @updateAuthor  :
	  */
	 public List<SpsScheme> queryAllCorpsByBillDay(String billState);


     public List<SpsScheme> findInsurance(SpsScheme vo) ;

	 /**
	  * 根据条件查询出方案列表,group by areaId
	  * @param spsScheme
	  * @return
	  */
	 public List<SpsScheme> findSchemeListByConditions(SpsScheme spsScheme);
	 
	 /**
	  * 根据企业ID和城市获取最小方案
	  *  @param scheme
	  *  @return 
	  *	@return 			: SpsScheme 
	  *  @createDate  	: 2017年3月17日 下午1:38:11
	  *  @author         	: wangchao 
	  *  @version        	: v1.0
	  *  @updateDate    	: 2017年3月17日 下午1:38:11
	  *  @updateAuthor  :
	  */
	public SpsScheme findMinSchemeByCityIdAndCpId(ContextInfo cti,SpsScheme scheme);

	 /**
	  *  获取当前企业下的方案列表
	  *  @param   cpId
	  *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	  *  @createDate  	: 2017-03-23 14:33
	  *  @author         	: konglc@xinfushe.com
	  *  @version        	: v1.0
	  *  @updateDate    	: 2017-03-23 14:33
	  *  @updateAuthor  :
	  */
	 public List<SpsScheme> findSchemeByCpId(ContextInfo cti);

	 /**
	  *  获取当前企业下是否支持网申帐号
	  *  @param   [cpId]
	  *	@return 			: java.util.Map<java.lang.String,java.util.Map<java.lang.String,java.lang.Object>>
	  *  @createDate  	: 2017-04-05 18:35
	  *  @author         	: konglc@xinfushe.com
	  *  @version        	: v1.0
	  *  @updateDate    	: 2017-04-05 18:35
	  *  @updateAuthor  :
	  */
	 public Map<String,Map<String,Object>> querySchemeAccountAreaType(ContextInfo cti);

    /**
     * 根据员工id 获取方案信息
     *
     * @param empId
     * @return
     */
    public SpsScheme findSchemeByEmpId(Integer empId);
    
    /**
     * 删除账号
     *  @param cti
     *  @param schemeId 
     *	@return 			: void 
     *  @createDate  	: 2017年4月19日 下午8:29:33
     *  @author         	: wangchao 
     *  @version        	: v1.0
     *  @updateDate    	: 2017年4月19日 下午8:29:33
     *  @updateAuthor  :
     */
	public boolean removeAcc(ContextInfo cti, Integer schemeId);
	
	/**
	 * 根据企业和城市名称获取方案
	 *  @param scheme
	 *  @return 
	 *	@return 			: SpsScheme 
	 *  @createDate  	: 2017年5月18日 下午3:42:28
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年5月18日 下午3:42:28
	 *  @updateAuthor  :
	 */
	public SpsScheme findMinSchemeByCpIdAndCityNmae(SpsScheme scheme);
	
	/**
	 * 根据权限、企业ID、服务商ID和方案ID获取参保城市
	 *  @param authority
	 *  @param cpId
	 *  @param spId
	 *  @param schemeIds
	 *  @return 
	 *	@return 			: List<Map<String,Object>> 
	 *  @createDate  	: 2017年7月18日 上午10:59:59
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年7月18日 上午10:59:59
	 *  @updateAuthor  :
	 */
	List<Map<String, Object>> findServiceBillDetailsArea(String authority, Integer cpId, Integer spId,
			String[] schemeIds);
	
	/**
	 *  根据 城市+供应商名称 查询方案信息
	 *
	 * @author lifq
	 *
	 * 2017年7月20日  上午9:43:16
	 */
	public List<Map<String,Object>> findSchemeByName(Map<String,Object> paraMap);

	 /**
	  * 根据 服务商获取相关 方案id
	  * @param paraMap
	  * @author quanjh
	  * @return
	  */
	 public List<Map<String,Object>> findSchemeBySpIds(Map<String,Object> paraMap);

	 /**
	  * 企业下所有外包方案
	  * @param cti
	  * @return
	  */
	 public Map<String,SpsScheme> findAllSpSchemeByCpId(ContextInfo cti);
	 
	 /**
	  * 查询 有权限的 方案（排除自服务方案）
	  *
	  * @author lifq
	  *
	  * 2017年8月8日  下午5:53:03
	  */
	 public List<Map<String,Object>> findSpSchemeByAuthority(Map<String,Object> paraMap);
	 
	 /**
	  * 根据企业ID和城市获取方案  按自服务倒序
	  *  @param spsScheme
	  *  @return 
	  *	@return 			: List<SpsScheme> 
	  *  @createDate  	: 2017年8月23日 下午4:38:54
	  *  @author         	: wangchao 
	  *  @version        	: v1.0
	  *  @updateDate    	: 2017年8月23日 下午4:38:54
	  *  @updateAuthor  :
	  */
	 public List<SpsScheme> findSchemeByCityIdAndCpIdOderBySpId(SpsScheme spsScheme);

	 /**
	  * 获取当前企业下自服务所有方案列表
	  * @param cti
	  * @return
	  */
	 public Map<String,List<Map<String,Object>>> findSelfSchemeByCpId(ContextInfo cti);
}
