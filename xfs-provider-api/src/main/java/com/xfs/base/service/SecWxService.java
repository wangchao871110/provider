package com.xfs.base.service;

import java.util.List;

import com.xfs.base.model.SecWx;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * SecWxService
 * @author:quanjiahua<quanjiahua@163.com>
 * 
 * @date:2016/01/25 15:21:28
 */
public interface SecWxService {

	public void save(ContextInfo cti, SecWx vo);
	
	public int save1(ContextInfo cti, SecWx vo);

	public void insert(ContextInfo cti, SecWx vo);

	public int insert1(ContextInfo cti, SecWx vo);
	
	public void remove(ContextInfo cti, SecWx vo);
	
	public int remove1(ContextInfo cti, SecWx vo);

	public void update(ContextInfo cti, SecWx vo);
	
	public int update1(ContextInfo cti, SecWx vo);

	public PageModel findPage(PageInfo pi, SecWx vo);

	public List<SecWx> findAll(SecWx vo);
	
	public SecWx findByPK1(SecWx vo);

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/01/25 15:21:28
	public SecWx findByPK(SecWx vo);

}
