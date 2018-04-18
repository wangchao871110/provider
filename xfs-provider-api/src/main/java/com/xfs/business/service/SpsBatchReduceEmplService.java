package com.xfs.business.service;

import com.xfs.business.dto.BatchRatioEmpQuery;
import com.xfs.business.model.SpsBatchChangeRatio;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;

import java.util.List;
import java.util.Map;

/**
 * 批量减员
 * SpsBatchReduceEmplService
 * @author:yangfw@xinfushe.com
 * @date:2016/12/16 16:30:31
 */
public interface SpsBatchReduceEmplService {

	public Result handleBatchReduceEmpl(ContextInfo cti,String insuranceFeeMoon,
										String insuranceOpMoon, String fundFeeMoon, String fundOpMoon, String decreasereason,List<Map<String,String>> empInfo);
}
