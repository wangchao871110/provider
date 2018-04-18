package com.xfs.base.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.sp.model.SpsExtdatasheet;

/**
 * SpsExtdatasheetService
 * @author:quanjiahua<quanjiahua@163.com>
 * 
 * @date:2016/05/17 19:55:26
 */
public interface SpsExtdatasheetService {

	public int save(ContextInfo cti, SpsExtdatasheet vo);

	public int insert(ContextInfo cti, SpsExtdatasheet vo);

	public int remove(ContextInfo cti, SpsExtdatasheet vo);

	public int update(ContextInfo cti, SpsExtdatasheet vo);

	public PageModel findPage(PageInfo pi, SpsExtdatasheet vo);

	public List<SpsExtdatasheet> findAll(SpsExtdatasheet vo);

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/05/17 19:55:26
	public LinkedHashMap<SpsExtdatasheet, Object> findSheetByExcelId(Integer excelId);
}
