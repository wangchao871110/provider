package com.xfs.base.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.sp.model.SpsExtdatahead;

/**
 * SpsExtdataheadService
 * @author:quanjiahua<quanjiahua@163.com>
 * 
 * @date:2016/05/13 11:44:25
 */
public interface SpsExtdataheadService {

	public int save(ContextInfo cti, SpsExtdatahead vo);

	public int insert(ContextInfo cti, SpsExtdatahead vo);

	public int remove(ContextInfo cti, SpsExtdatahead vo);

	public int update(ContextInfo cti, SpsExtdatahead vo);

	public PageModel findPage(PageInfo pi, SpsExtdatahead vo);

	public List<SpsExtdatahead> findAll(SpsExtdatahead vo);

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/05/13 11:44:25
	/**
	 * 根据excel id 查询sheet标题
	 */
	public List<SpsExtdatahead> findHeadBySheetIdExttabIdName(SpsExtdatahead vo);
}
