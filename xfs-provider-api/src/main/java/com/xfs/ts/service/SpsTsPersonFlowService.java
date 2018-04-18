package com.xfs.ts.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.ts.model.SpsTsPersonFlow;

/**
 * SpsTsPersonFlowService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/06/06 13:41:11
 */
public interface SpsTsPersonFlowService {
	
	public int save(ContextInfo cti, SpsTsPersonFlow vo);
	public int insert(ContextInfo cti, SpsTsPersonFlow vo);
	public int remove(ContextInfo cti, SpsTsPersonFlow vo);
	public int update(ContextInfo cti, SpsTsPersonFlow vo);
	public PageModel findPage(PageInfo pi, SpsTsPersonFlow vo);
	public List<SpsTsPersonFlow> findAll(SpsTsPersonFlow vo);
	public int count(String accId, String stime);
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/06 13:41:11

	public SpsTsPersonFlow getSpsTsPersonFlow(SpsTsPersonFlow vo);
	

	/**
	 * 解析下行实缴数据
     */
	public void analysisPaidBill(ContextInfo cti);
}
