package com.xfs.activity.service;

import java.util.List;

import com.xfs.activity.model.BsMallBanner;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * 
* @ClassName: BsMallBannerService 
* @Description: banner
* @author duanax@xinfushe.com
* @date 2016年11月15日 上午11:29:31 
*
 */
public interface BsMallBannerService {
	
	public int save(ContextInfo cti, BsMallBanner vo);
	public int insert(ContextInfo cti, BsMallBanner vo);
	public int remove(ContextInfo cti, BsMallBanner vo);
	public int update(ContextInfo cti, BsMallBanner vo);
	public PageModel findPage(PageInfo pi, BsMallBanner vo);
	public List<BsMallBanner> findAll(BsMallBanner vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/07 11:07:57
	
	public BsMallBanner findByPK(BsMallBanner vo);
	
	/**
	 * 商城首页banner列表
	 *
	 * @return
	 */
	public List<BsMallBanner> findMallBanners();
}
