package com.xfs.bill.service;

import java.util.List;

import com.xfs.bill.model.SpsEmpActualdetail;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * 实缴账单员工社保服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-10 14:58
 * @version 	: V1.0
 */
public interface SpsEmpActualdetailService {
	
	public int save(ContextInfo cti, SpsEmpActualdetail vo);
	public int insert(ContextInfo cti, SpsEmpActualdetail vo);
	public int remove(ContextInfo cti, SpsEmpActualdetail vo);
	public int update(ContextInfo cti, SpsEmpActualdetail vo);
	public PageModel findPage(PageInfo pi, SpsEmpActualdetail vo);
	public List<SpsEmpActualdetail> findAll(SpsEmpActualdetail vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/12 11:05:28
	
	
	
}
