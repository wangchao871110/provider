package com.xfs.insurance.service;


import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.insurance.dto.CiProduct;
import com.xfs.insurance.model.BsCiGroupItem;

/**
 * BsCiGroupItemService
 *
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/06 18:33:04
 */
public interface BsCiGroupItemService {

    public int save(ContextInfo cti, BsCiGroupItem vo);

    public int insert(ContextInfo cti, BsCiGroupItem vo);

    public int remove(ContextInfo cti, BsCiGroupItem vo);

    public int update(ContextInfo cti, BsCiGroupItem vo);

    public PageModel findPage(PageInfo pi, BsCiGroupItem vo);

    public List<BsCiGroupItem> findAll(BsCiGroupItem vo);


    //温馨提示：
    //以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    //2016/09/06 18:33:04

    /**
     * 查询组合险子条目心胸
     *
     * @param groupId 组合险编码
     * @return 查询结果
     */
    public List<CiProduct> findGroupItemsDetail(Integer groupId);

    /**
     * 删除组合险信息
     *
     * @param groupId 组合险编码
     * @return 删除条数
     */
    public int deleteAllGroupItems(ContextInfo cti,Integer groupId);
	
		/**
	 * 查询组合险子条目心胸
	 *
	 * @param prodIds 商品编码列表
	 * @return 查询结果
	 */
	public List<CiProduct> findAllGroupItemsDetail(List<Integer> prodIds);

}
