package com.xfs.mall.info.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.mall.info.model.SpsMallServicecity;

/** 
 * @author  : duanax@xinfushe.com
 * @date  : 2016/11/9 0009 下午 8:30
 * @version  : V1.0
 */
public interface SpsMallServicecityService {
	
	public int save(ContextInfo cti, SpsMallServicecity vo);
	public int insert(ContextInfo cti, SpsMallServicecity vo);
	public int remove(ContextInfo cti, SpsMallServicecity vo);
	public int update(ContextInfo cti, SpsMallServicecity vo);
	public PageModel findPage(PageInfo pi, SpsMallServicecity vo);
	public List<SpsMallServicecity> findAll(SpsMallServicecity vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/05/24 16:11:04
	
	
	
}
