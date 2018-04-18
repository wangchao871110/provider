package com.xfs.business.service;

import java.util.List;

import com.xfs.business.model.BsArearatioscope;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * BsArearatioscopeService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/21 20:04:25
 */
public interface BsArearatioscopeService {
	
	public int save(ContextInfo cti, BsArearatioscope vo);
	public int insert(ContextInfo cti, BsArearatioscope vo);
	public int remove(ContextInfo cti, BsArearatioscope vo);
	public int update(ContextInfo cti, BsArearatioscope vo);
	public PageModel findPage(PageInfo pi, BsArearatioscope vo);
	public List<BsArearatioscope> findAll(BsArearatioscope vo);
	
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/21 20:04:25
	
	/**
	 * 改变使用情况
	 * @param arearatioscope
	 */
	public void changeScope(ContextInfo cti, BsArearatioscope arearatioscope);
	
}
