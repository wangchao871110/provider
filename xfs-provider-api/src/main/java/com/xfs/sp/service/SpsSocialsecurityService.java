package com.xfs.sp.service;

import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.sp.model.SpsSocialsecurity;

/**
 * SpsSocialsecurityService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/04/20 21:09:23
 */
public interface SpsSocialsecurityService {
	
	public int save(ContextInfo cti, SpsSocialsecurity vo);
	public int insert(ContextInfo cti, SpsSocialsecurity vo);
	public int remove(ContextInfo cti, SpsSocialsecurity vo);
	public int update(ContextInfo cti, SpsSocialsecurity vo);
	public PageModel findPage(PageInfo pi, SpsSocialsecurity vo);
	public List<SpsSocialsecurity> findAll(SpsSocialsecurity vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/04/20 21:09:23

	/**
	 * 根据条件查询社保报表
	 *
	 * @param map
	 * @return
	 */
	PageModel findPage(PageInfo pi, Map<String, Object> map);

	/**
	 * 查询待处理或者异常任务
	 *
	 * @return
	 */
	List<Map<String, Object>> freeFindUnSuccessReg();
	
	

	/**
	 *  定时查询制定查询报表
	 * @return
     */
	public boolean queryMadeSocialReport();


	/**
	 * 每月执行一次查询社保明细
	 *
	 * @return
	 */
	List<Map<String, Object>> onceFindForSS(String cityCode, String insuranceFundType);

}
