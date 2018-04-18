package com.xfs.cp.service;

import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.cp.model.CpImpressLog;

/**
 * CpImpressLogService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/08 10:55:20
 */
public interface CpImpressLogService {
	
	public int save(ContextInfo cti, CpImpressLog vo);
	public int insert(ContextInfo cti, CpImpressLog vo);
	public int remove(ContextInfo cti, CpImpressLog vo);
	public int update(ContextInfo cti, CpImpressLog vo);
	public PageModel findPage(PageInfo pi, CpImpressLog vo);
	public List<CpImpressLog> findAll(CpImpressLog vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 10:55:20
	
	/**
	 * 
	 * @method comments: 根据服务商ID获取前3个印象
	 * @author   name  : wangchao  
	 * @create   time  : 2016年9月12日 上午11:18:55 
	 * @param cpImpressLog
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月12日 上午11:18:55 wangchao 创建
	 *
	 */
	public  List<Map<String, Object>> findByCpId(CpImpressLog cpImpressLog);
	
	/**
	 * 
	 * @method comments: 注释
	 * @author   name  : wangchao  
	 * @create   time  : 2016年9月12日 下午4:30:23 
	 * @param sysUser
	 * @param cpImpressLog
	 * @param result
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月12日 下午4:30:23 wangchao 创建
	 *
	 */
	public Result saveImpressLog(ContextInfo user, CpImpressLog cpImpressLog, Result result);
	
}
