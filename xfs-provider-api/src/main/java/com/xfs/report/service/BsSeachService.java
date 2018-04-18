package com.xfs.report.service;

import java.util.HashMap;
import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.report.model.BsSeach;

public interface BsSeachService {

	public int save(ContextInfo cti, BsSeach vo );
	public int insert(ContextInfo cti,  BsSeach vo );
	public int remove(ContextInfo cti,  BsSeach vo );
	public int update(ContextInfo cti,  BsSeach vo );
	public PageModel findPage(PageInfo pi, BsSeach vo);
	public List<BsSeach> findAll(BsSeach vo);


	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/11/10 14:48:42

	public BsSeach findBsSeachById(BsSeach vo);

	List<HashMap<String, Object>> findAllSeach();

	PageModel findTableData(PageInfo pi, Integer seachId);

	HashMap<String,String> findSeachCondition(Integer seachId);

    HashMap<String,Object> findSeachConditionType(Integer seachId);

	public HashMap<String,List<String>> findSeachPullCondition(Integer seachId);

	PageModel findTableData(PageInfo pi, Integer seachId, List<String> conList, List<String> valList,String orderValue);

	List<HashMap<String, Object>> findData(PageInfo pi, Integer seachId, List<String> conList, List<String> valList,String orderValue);

	/**
	 * quanjh
	 * @param sqltext
     * @return
     */
	public PageModel findDataBySqlText(PageInfo pi, String sqltext);
}
