package com.xfs.insurance.service;



import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.insurance.dto.CiGroup;
import com.xfs.insurance.model.BsCiGroup;

/**
 * BsCiGroupService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/06 18:33:04
 */
public interface BsCiGroupService {
	
	public int save(ContextInfo cti, BsCiGroup vo);
	public int insert(ContextInfo cti, BsCiGroup vo);
	public int remove(ContextInfo cti, BsCiGroup vo);
	public int update(ContextInfo cti, BsCiGroup vo);
	public PageModel findPage(PageInfo pi, BsCiGroup vo);
	public List<BsCiGroup> findAll(BsCiGroup vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/06 18:33:04

	/**
	 * 组合险发布
	 * @param ciGroup 组合险信息
	 * @return
	 */
	boolean publishCiGroup(ContextInfo cti,CiGroup ciGroup);

	/**
	 * 修改组合险信息
	 * @param ciGroup 组合险信息
	 * @return 修改结果
	 */
	boolean modifyCiGroup(ContextInfo cti,CiGroup ciGroup);

	/**
	 * 通过主键查询商保组合信息
	 *
	 * @param groupId
	 * @return
	 */
	BsCiGroup findByPk(Integer groupId);

	/**
	 * 查找含有代理信息的组合列表
	 *
	 * @param ciGroup 查询条件
	 * @return
	 */
	List<CiGroup> findAllCIGroupWithSp(CiGroup ciGroup);

}
