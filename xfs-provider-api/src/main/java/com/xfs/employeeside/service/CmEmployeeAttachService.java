package com.xfs.employeeside.service;


import com.xfs.common.ContextInfo;
import com.xfs.employeeside.model.CmEmployeeAttach;

import java.util.List;

/**
 * CmEmployeeAttachService 服务层接口
 * @date 2017/03/17 11:35:04
 */
public interface CmEmployeeAttachService {

	/**
	 * 查询总数
	 * @return 记录数
	 */
	public int countFindAll();
	
	/**
	 * 根据条件查询
	 * @param 	obj  员工附件表实体
	 * @return   员工附件表列表
	 */
	public List<CmEmployeeAttach> freeFind(CmEmployeeAttach obj);
	
	/**
	 * 根据条件查询的数量
	 * @param 	obj  员工附件表实体
	 * @return 	返回查询到的数量
	 */
	public int countFreeFind(CmEmployeeAttach obj);
	
	/**
	 * 根据主键查询对象
	 * @param 	obj  员工附件表实体
	 * @return   员工附件表实体
	 */
	public CmEmployeeAttach findByPK(CmEmployeeAttach obj);
	
	/**
	 * 添加对象
	 * @param 	obj  员工附件表实体
	 * @return   影响的记录数
	 */
	public int insert(ContextInfo info, CmEmployeeAttach obj);
	
	/**
	 * 根据主键修改对象
	 * @param 	obj  员工附件表实体
	 * @return   影响的记录数
	 */
	public int update(ContextInfo info,CmEmployeeAttach obj);
	
	/**
	 * 根据主键删除对象
	 * @param 	obj  员工附件表实体
	 * @return   影响的记录数
	 */
	public int remove(ContextInfo info,CmEmployeeAttach obj);

	
}
