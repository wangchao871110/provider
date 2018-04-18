package com.xfs.corp.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.corp.dto.CmDepartmentTree;
import com.xfs.corp.model.CmDepartment;
import com.xfs.user.model.SysUser;

/**
 * @author 	: yangfangwei
 * @date 	: 2016-11-10 14:10:00
 * @version 	: V1.0
 */
public interface CmDepartmentService {
	
	public int save(ContextInfo cti, CmDepartment vo);
	public int insert(ContextInfo cti, CmDepartment vo);
	public int remove(ContextInfo cti, CmDepartment vo);
	public int update(ContextInfo cti, CmDepartment vo);
	public PageModel findPage(PageInfo pi, CmDepartment vo);
	public List<CmDepartment> findAll(CmDepartment vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/22 11:03:55

	public List<CmDepartmentTree> findTreeList(ContextInfo cti);
	public Result add(ContextInfo cti, CmDepartment department);
	public Result deleteDepById(ContextInfo cti, Integer depId);
	public Result updateDepartment(ContextInfo cti, CmDepartment department);
}
