package com.xfs.sp.service;

import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.sp.model.CmContractAttitem;

/**
 * CmContractAttitemService
 * @author:wangchao
 * @date:2016/08/02 10:14:25
 */
public interface CmContractAttitemService {
	
	public int save(ContextInfo cti, CmContractAttitem vo);
	public int insert(ContextInfo cti, CmContractAttitem vo);
	public int remove(ContextInfo cti, CmContractAttitem vo);
	public int update(ContextInfo cti, CmContractAttitem vo);
	public PageModel findPage(PageInfo pi, CmContractAttitem vo);
	public List<CmContractAttitem> findAll(CmContractAttitem vo);
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/02 10:14:25
	
	public List<Map<String, Object>> findAllByContractAttIdAndAreaId(Integer contractAttId, Integer areaId);
	public List<Map<String, Object>> findContractAttItem(Integer productId, Integer areaId, String itemId);
	
}
