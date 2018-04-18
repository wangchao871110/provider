package com.xfs.bs.service;

import java.util.List;

import com.xfs.base.model.BsSaasfeestandard;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.sp.dto.BsSaasfeestandardDto;

/**
 * BsSaasfeestandardService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/06/15 19:00:17
 */
public interface BsSaasfeestandardService {
	
	public int save(ContextInfo cti, BsSaasfeestandard vo);
	public int insert(ContextInfo cti, BsSaasfeestandard vo);
	public int remove(ContextInfo cti, BsSaasfeestandard vo);
	public int update(ContextInfo cti, BsSaasfeestandard vo);
	public PageModel findPage(PageInfo pi, BsSaasfeestandard vo);
	public List<BsSaasfeestandard> findAll(BsSaasfeestandard vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/15 19:00:17
	
	public BsSaasfeestandard findReckonStandardFee(BsSaasfeestandard obj);

	/**
	 * 查询缴费范围业务类
	 * @return
     */
	public List<BsSaasfeestandardDto> queryDtoList();
}
