package com.xfs.bs.service;

import java.util.List;

import com.xfs.base.model.BsSppayrecord;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.user.model.SysUser;

/**
 * BsSppayrecordService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/06/15 11:14:40
 */
public interface BsSppayrecordService {
	
	public int save(ContextInfo cti, BsSppayrecord vo);
	public int insert(ContextInfo cti, BsSppayrecord vo);
	public int remove(ContextInfo cti, BsSppayrecord vo);
	public int update(ContextInfo cti, BsSppayrecord vo);
	public PageModel findPage(PageInfo pi, BsSppayrecord vo);
	public List<BsSppayrecord> findAll(BsSppayrecord vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/15 11:14:40
	
	public PageModel findLisBySpid(PageInfo pi, BsSppayrecord vo);
	
	public BsSppayrecord findMaxDateBySpId(BsSppayrecord vo);
	
	public BsSppayrecord getTotleStandareFee(BsSppayrecord vo);
	/**
	 * 查询计量计费展示结果
	 * @param user
	 * @return
     */
	public Result querySpPayForIndex(ContextInfo cti);
}
