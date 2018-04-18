package com.xfs.sp.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.sp.model.CmContractSign;

/**
 * CmContractSignService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/10 10:44:44
 */
public interface CmContractSignService {
	
	public int save(ContextInfo cti, CmContractSign vo);
	public int insert(ContextInfo cti, CmContractSign vo);
	public int remove(ContextInfo cti, CmContractSign vo);
	public int update(ContextInfo cti, CmContractSign vo);
	public PageModel findPage(PageInfo pi, CmContractSign vo);
	public List<CmContractSign> findAll(CmContractSign vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/10 10:44:44
	
	
	
}
