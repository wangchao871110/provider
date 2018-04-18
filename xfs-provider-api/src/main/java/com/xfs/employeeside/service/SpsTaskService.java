package com.xfs.employeeside.service;

import com.xfs.common.ContextInfo;
import com.xfs.employeeside.model.CmEmployeeAudit;
import com.xfs.employeeside.model.SpsTask;

import java.util.List;

/**
 * SpsTaskService 服务层接口
 * @date 2017/03/13 14:37:25
 */
public interface SpsTaskService {

	/**
	 * 查询总数
	 * @return 记录数
	 */
	public int countFindAll();
	
	/**
	 * 根据条件查询
	 * @param 	obj  调度任务表实体
	 * @return   调度任务表列表
	 */
	public List<SpsTask> freeFind(SpsTask obj);
	
	/**
	 * 根据条件查询的数量
	 * @param 	obj  调度任务表实体
	 * @return 	返回查询到的数量
	 */
	public int countFreeFind(SpsTask obj);
	
	/**
	 * 根据主键查询对象
	 * @param 	obj  调度任务表实体
	 * @return   调度任务表实体
	 */
	public SpsTask findByPK(SpsTask obj);
	
	/**
	 * 添加对象
	 * @param 	obj  调度任务表实体
	 * @return   影响的记录数
	 */
	public int insert(ContextInfo cti ,SpsTask obj);
	
	/**
	 * 根据主键修改对象
	 * @param 	obj  调度任务表实体
	 * @return   影响的记录数
	 */
	public int update(ContextInfo cti ,SpsTask obj);
	
	/**
	 * 根据主键删除对象
	 * @param 	obj  调度任务表实体
	 * @return   影响的记录数
	 */
	public int remove(ContextInfo cti ,SpsTask obj);

	/**
	 * 添加入职任务单
	 *  @param cmEmployeeAudit
	 *  @param messageId
	 *  @return 
	 *	@return 			: boolean 
	 *  @createDate  	: 2017年11月6日 上午10:58:36
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年11月6日 上午10:58:36
	 *  @updateAuthor  :
	 */
	public int addEntryTask(ContextInfo cti,CmEmployeeAudit cmEmployeeAudit, Integer messageId);

}
