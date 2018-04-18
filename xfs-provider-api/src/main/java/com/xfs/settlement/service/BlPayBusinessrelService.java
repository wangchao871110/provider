package com.xfs.settlement.service;
import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.settlement.model.BlPayBusinessrel;

/**
 * 支付渠道与业务关系服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-16 10:43
 * @version 	: V1.0
 */
public interface BlPayBusinessrelService {
	
	public int save(ContextInfo cti, BlPayBusinessrel vo);
	public int insert(ContextInfo cti,BlPayBusinessrel vo);
	public int remove(ContextInfo cti,BlPayBusinessrel vo);
	public int update(ContextInfo cti,BlPayBusinessrel vo);
	public PageModel findPage(PageInfo pi, BlPayBusinessrel vo);
	public List<BlPayBusinessrel> findAll(BlPayBusinessrel vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/11/16 10:28:39
	
}
