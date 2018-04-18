package com.xfs.base.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.exception.BusinessException;
import com.xfs.common.page.PageModel;
import com.xfs.sp.model.SpsExtdata;
import com.xfs.user.model.SysUser;

/**
 * SpsExtdataService
 * @author:quanjiahua<quanjiahua@163.com>
 * 
 * @date:2016/05/13 11:44:25
 */
public interface SpsExtdataService {

	public int save(ContextInfo cti, SpsExtdata vo);

	public int insert(ContextInfo cti, SpsExtdata vo);

	public int remove(ContextInfo cti, SpsExtdata vo);

	public int update(ContextInfo cti, SpsExtdata vo);

	public PageModel findPage(PageInfo pi, SpsExtdata vo);

	public List<SpsExtdata> findAll(SpsExtdata vo);

	// Mongo里取数据
	public PageModel findExcelDataPage(PageInfo pi, Integer excelId, Integer sheetId, String str, String stri);

	Result importExcel(ContextInfo cti, Integer fileId, String str) throws BusinessException;

}
