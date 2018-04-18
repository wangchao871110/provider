package com.xfs.corp.service;
import com.xfs.business.model.BdBstype;
import com.xfs.common.ContextInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.corp.model.CmEmployee;
import com.xfs.corp.model.CmEmployeeConfig;

import java.util.List;

/**
 * 员工端配置接口
 * @author 	: wangchao
 * @date 	: 2017年6月7日 上午9:58:09
 * @version 	: V1.0
 */
public interface CmEmployeeConfigService {
	
	public int save(ContextInfo cti, CmEmployeeConfig vo );
	public int insert(ContextInfo cti, CmEmployeeConfig vo );
	public int remove(ContextInfo cti, CmEmployeeConfig vo );
	public int update(ContextInfo cti, CmEmployeeConfig vo );
	public PageModel findPage(ContextInfo cti, CmEmployeeConfig vo);
	public List<CmEmployeeConfig> findAll(CmEmployeeConfig vo);
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/06/07 09:54:56
	
	/**
	 * 获取员工端配置
	 *  @param cti
	 *  @param vo
	 *  @return 
	 *	@return 			: CmEmployeeConfig 
	 *  @createDate  	: 2017年6月7日 上午10:35:42
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年6月7日 上午10:35:42
	 *  @updateAuthor  :
	 */
	public CmEmployeeConfig findFempConfigDetails(ContextInfo cti, CmEmployeeConfig vo);
	
	/**
	 * 保存员工端配置信息
	 *  @param cti
	 *  @param cmEmployeeConfig 
	 *	@return 			: int 
	 *  @createDate  	: 2017年6月7日 上午10:35:46
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年6月7日 上午10:35:46
	 *  @updateAuthor  :
	 */
	public int saveEmpConfig(ContextInfo cti, CmEmployeeConfig cmEmployeeConfig);
	
	/**
	 * 获取员工服务
	 *  @param areaId
	 *  @param bsType 业务类型：0：常规业务，1：扩展业务，2：增值业务
	 *  @return 
	 *	@return 			: List<BdBstype> 
	 *  @createDate  	: 2017年6月7日 下午2:33:13
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年6月7日 下午2:33:13
	 *  @updateAuthor  :
	 */
	public List<BdBstype> findEmpServiceByAreaId(Integer areaId,Integer bsType);
	
	/**
	 * 获取增值服务
	 *  @param areaId
	 *  @return 
	 *	@return 			: List<BdBstype> 
	 *  @createDate  	: 2017年6月7日 下午2:33:17
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年6月7日 下午2:33:17
	 *  @updateAuthor  :
	 */
	public List<BdBstype> findAddServiceByAreaId(Integer areaId);


	/**
	 * 查询某个 公司 开放员工端的 城市数量
	 * @param cpId 公司id
	 * @return
	 * @createDate  	: 2017年6月7日17:28:29
	 * @author         	: zhanghj
	 * @version        	: v1.0
	 * @updateDate    	: 2017年6月7日17:28:37
	 * @updateAuthor  :
	 */
	public List<CmEmployeeConfig> findAllCorpOpenCity(Integer cpId);
	
	/**
	 * 
	 *  @param result
	 *  @param emp
	 *  @return 
	 *	@return 			: Result 
	 *  @createDate  	: 2017年10月26日 上午9:25:28
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年10月26日 上午9:25:28
	 *  @updateAuthor  :
	 */
	public Result findInJobItem(ContextInfo cti,Result result, CmEmployee emp);


}
