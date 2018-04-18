package com.xfs.mall.info.service;

import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.mall.info.model.SpsMallInfo;
import com.xfs.sp.model.SpService;
import com.xfs.user.model.SysUser;

/**
 * SpsMallInfoService
 * @author:quanjiahua<quanjiahua@163.com>
 * 
 * @date:2016/06/07 11:06:19
 */
public interface SpsMallInfoService {

	public int save(ContextInfo cti, SpsMallInfo vo);

	public int insert(ContextInfo cti, SpsMallInfo vo);

	public int remove(ContextInfo cti, SpsMallInfo vo);

	public int update(ContextInfo cti, SpsMallInfo vo);

	public PageModel findPage(PageInfo pi, SpsMallInfo vo);

	public List<SpsMallInfo> findAll(SpsMallInfo vo);

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/06/07 11:06:19
	
	//bs
	public List<SpsMallInfo> findBySpId(SpsMallInfo vo);

	public SpsMallInfo findByPK(SpsMallInfo vo);
	
	/**
	 * 
	* @Title: saveData 
	* @Description: 更新时间
	* @param @param spsMallInfo
	* @param @param spService
	* @param @param flag
	* @param @param sys
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @throws
	 */
	public Result saveData(ContextInfo cti, SpsMallInfo spsMallInfo, SpService spService, String flag, SysUser sys);

	/**
	 * 
	* @Title: cannelsign 
	* @Description: 取消签约装态
	* @param @param spsMallInfo
	* @param @param spService
	* @param @param sys
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @throws
	 */
	public Result cannelsign(ContextInfo cti, SpsMallInfo spsMallInfo, SpService spService);
	
	//cs
	/**
	 * 商铺首页详情
	 *
	 * @param vo
	 * @return
	 */
	public Map<String, Object> findMallInfo(SpsMallInfo vo);

	/**
	 * 机构评分详情
	 *
	 * @param vo
	 * @return
	 */
	public List<Map<String, Object>> findMallScoreDetai(SpsMallInfo vo);
	
	
	//sps

	public Result saveData(ContextInfo cti, SpsMallInfo spsMallInfo, SpService spService, String flag, SysUser sys, String[] categoryId, String[] cpName, String[] successCase);

}
