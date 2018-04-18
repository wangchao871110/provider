package com.xfs.activity.service;

import java.util.List;

import com.xfs.activity.model.BdPrize;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * 
* @ClassName: BdPrizeService 
* @Description: 活动奖品
* @author duanax@xinfushe.com
* @date 2016年11月15日 上午11:28:15 
*
 */
public interface BdPrizeService {
	
	public int save(ContextInfo cti, BdPrize vo);
	public int insert(ContextInfo cti, BdPrize vo);
	public int remove(ContextInfo cti, BdPrize vo);
	public int update(ContextInfo cti, BdPrize vo);
	public PageModel findPage(PageInfo pi, BdPrize vo);
	public List<BdPrize> findAll(BdPrize vo);
	
	public BdPrize findByPK(Integer id);
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/10/17 10:59:57
	
	
	
}
