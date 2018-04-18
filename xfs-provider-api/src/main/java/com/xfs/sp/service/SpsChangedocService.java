package com.xfs.sp.service;

import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.sp.model.SpsChangedoc;

/**
 * SpsChangedocService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/12 14:07:19
 */
public interface SpsChangedocService {
	
	public int save(ContextInfo cti, SpsChangedoc vo);
	public int insert(ContextInfo cti, SpsChangedoc vo);
	public int remove(ContextInfo cti, SpsChangedoc vo);
	public int update(ContextInfo cti, SpsChangedoc vo);
	public PageModel findPage(PageInfo pi, SpsChangedoc vo);
	public List<SpsChangedoc> findAll(SpsChangedoc vo);
	
	public SpsChangedoc findByPK(SpsChangedoc vo);
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/12 14:07:19
	
	//bs
	/**
	 * 
	* @Title: finspsChange 
	* @Description: 根据order类型查询mall和saas状态
	* @param  vo
	* @param  设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws
	 */
	public List<Map<String, Object>> finspsChange(SpsChangedoc vo);
}
