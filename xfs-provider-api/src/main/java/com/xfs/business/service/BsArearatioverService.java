package com.xfs.business.service;

import java.util.List;

import com.xfs.business.model.BsArearatiover;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * BsArearatioverService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/21 20:04:09
 */
public interface BsArearatioverService {
	
	public int save(ContextInfo cti, BsArearatiover vo);
	public int insert(ContextInfo cti, BsArearatiover vo);
	public int remove(ContextInfo cti, BsArearatiover vo);
	public int update(ContextInfo cti, BsArearatiover vo);
	public PageModel findPage(PageInfo pi, BsArearatiover vo);
	public List<BsArearatiover> findAll(BsArearatiover vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/21 20:04:09
	
	/*
	 * 获取对时间排序的规则版本
	 */
	public List<BsArearatiover> findVersions(BsArearatiover arearatiover);
	
}
