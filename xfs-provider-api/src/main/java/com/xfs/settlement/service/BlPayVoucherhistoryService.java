package com.xfs.settlement.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.settlement.model.BlPayVoucherhistory;

/**
 * 支付凭证历史记录服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-09 17:40
 * @version 	: V1.0
 */
public interface BlPayVoucherhistoryService {
	
	public int save(ContextInfo cti, BlPayVoucherhistory vo);
	public int insert(ContextInfo cti, BlPayVoucherhistory vo);
	public int remove(ContextInfo cti, BlPayVoucherhistory vo);
	public int update(ContextInfo cti, BlPayVoucherhistory vo);
	public PageModel findPage(PageInfo pi, BlPayVoucherhistory vo);
	public List<BlPayVoucherhistory> findAll(BlPayVoucherhistory vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/12 21:08:04
	
	
	
}
