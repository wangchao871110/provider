package com.xfs.bill.service;

import java.util.List;

import com.xfs.bill.model.SpsBillMargin;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * 账单差额服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-10 14:58
 * @version 	: V1.0
 */
public interface SpsBillMarginService {
	
	public int save(ContextInfo cti, SpsBillMargin vo);
	public int insert(ContextInfo cti, SpsBillMargin vo);
	public int remove(ContextInfo cti, SpsBillMargin vo);
	public int update(ContextInfo cti, SpsBillMargin vo);
	public PageModel findPage(PageInfo pi, SpsBillMargin vo);
	public List<SpsBillMargin> findAll(SpsBillMargin vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/12 11:05:29



}
