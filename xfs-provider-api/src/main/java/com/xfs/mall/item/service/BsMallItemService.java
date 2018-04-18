package com.xfs.mall.item.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.mall.item.model.BsMallItem;
import com.xfs.user.model.SysUser;

/**
 * BsMallItemService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/06/07 11:08:15
 */
public interface BsMallItemService {
	
	public int save(ContextInfo cti, BsMallItem vo);
	public int insert(ContextInfo cti, BsMallItem vo);
	public int remove(ContextInfo cti, BsMallItem vo);
	public int update(ContextInfo cti, BsMallItem vo);
	public PageModel findPage(PageInfo pi, BsMallItem vo);
	public List<BsMallItem> findAll(BsMallItem vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/07 11:08:15

	/**
	 * 查询一个项目详情
	 */
	public List<BsMallItem> findOneByItemId(BsMallItem vo);

	/**
	 * 新增 编辑修改 保存
	 */
	public Result saveData(ContextInfo cti, BsMallItem vo);

	List<BsMallItem> findMallItemWithoutCi(BsMallItem mallItem);

	/**
	 *根据主键查找
	 * @param itemId 主键
	 * @return
	 */
	BsMallItem findByPk(Integer itemId);

	public PageModel queryPageByBs(PageInfo pi, BsMallItem vo);
	
}
