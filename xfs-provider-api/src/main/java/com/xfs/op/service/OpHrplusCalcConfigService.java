package com.xfs.op.service;
import java.util.List;
import com.xfs.common.page.PageModel;
import com.xfs.common.ContextInfo;
import com.xfs.op.model.OpHrplusCalcConfig;

/**
 * OpHrplusCalcConfigService
 * @author:yangfw@xinfushe.com
 * @date:2017/04/06 16:45:09
 */
public interface OpHrplusCalcConfigService {
	
	public int save(ContextInfo cti, OpHrplusCalcConfig vo);
	public int insert(ContextInfo cti, OpHrplusCalcConfig vo);
	public int remove(ContextInfo cti, OpHrplusCalcConfig vo);
	public int update(ContextInfo cti, OpHrplusCalcConfig vo);
	public PageModel findPage(OpHrplusCalcConfig vo);
	public List<OpHrplusCalcConfig> findAll(OpHrplusCalcConfig vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/04/06 16:45:09
	
	
	
}
