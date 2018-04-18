package com.xfs.sp.service;

import java.math.BigDecimal;
import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.sp.model.CmContractAtt;
import com.xfs.user.model.SysUser;

/**
 * CmContractAttService
 * @author:wangchao
 * @date:2016/08/02 10:14:24
 */
public interface CmContractAttService {
	
	public int save(ContextInfo cti, CmContractAtt vo);
	public int insert(ContextInfo cti, CmContractAtt vo);
	public int remove(ContextInfo cti, CmContractAtt vo);
	public int update(ContextInfo cti, CmContractAtt vo);
	public PageModel findPage(PageInfo pi, CmContractAtt vo);
	public List<CmContractAtt> findAll(CmContractAtt vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/02 10:14:24
	//保存合同附件
	public void saveContractAtt(ContextInfo cti,CmContractAtt contractAtt,String collaborator, Integer[] contractAttItemId, String[] contractAttItemType, BigDecimal[] contractAttItemPrice);
	
}
