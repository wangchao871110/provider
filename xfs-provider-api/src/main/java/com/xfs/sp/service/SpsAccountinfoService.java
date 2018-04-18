package com.xfs.sp.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.exception.BusinessException;
import com.xfs.common.page.PageModel;
import com.xfs.sp.model.SpsAccountinfo;
import com.xfs.user.model.SysUser;

/**
 * SpsAccountinfoService
 * @author:quanjiahua<quanjiahua@163.com>
 * 
 * @date:2016/04/19 15:34:17
 */
public interface SpsAccountinfoService {

	public int save(ContextInfo cti, SpsAccountinfo vo);

	public int insert(ContextInfo cti, SpsAccountinfo vo);

	public int remove(ContextInfo cti, SpsAccountinfo vo);

	public int update(ContextInfo cti, SpsAccountinfo vo);

	public PageModel findPage(PageInfo pi, SpsAccountinfo vo) throws BusinessException;

	public List<SpsAccountinfo> findAll(SpsAccountinfo vo);

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/04/19 15:34:17

	public Result importUpdateAccExcel(ContextInfo cti, Integer fileId,  String selectContent) throws BusinessException;

	public Result readExcelTitle(Integer fileId, ContextInfo cti);

}
