package com.xfs.corp.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.corp.dao.CmCorpDao;
import com.xfs.corp.dao.CmDepartmentDao;
import com.xfs.corp.dao.CmEmployeeDao;
import com.xfs.corp.dto.CmDepartmentTree;
import com.xfs.corp.model.CmCorp;
import com.xfs.corp.model.CmDepartment;
import com.xfs.corp.service.CmDepartmentService;
import com.xfs.user.model.SysUser;

/**
 * @author 	: yangfangwei
 * @date 	: 2016-11-10 14:10:00
 * @version 	: V1.0
 */
@Service
public class CmDepartmentServiceImpl implements CmDepartmentService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(CmDepartmentServiceImpl.class);
	
	@Autowired
	private CmDepartmentDao cmDepartmentDao;
	@Autowired
	private CmEmployeeDao cmEmployeeDao;
	@Autowired
	private CmCorpDao cmCorpDao;
	public int save(ContextInfo cti, CmDepartment vo ){
		return cmDepartmentDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  CmDepartment vo ){
		return cmDepartmentDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  CmDepartment vo ){
		return cmDepartmentDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  CmDepartment vo ){
		return cmDepartmentDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, CmDepartment vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cmDepartmentDao.countFreeFind(vo);
		pm.setTotal(total);
		List<CmDepartment> datas = cmDepartmentDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<CmDepartment> findAll(CmDepartment vo){
		
		List<CmDepartment> datas = cmDepartmentDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/22 11:03:55

	/**
	 * 查询树形集合
	 * @return
     */
	public List<CmDepartmentTree> findTreeList(ContextInfo cti){
		CmDepartment query = new CmDepartment();
		query.setOrgId(cti.getOrgId());
		query.setDr(0);
		//机构集合
		List<CmDepartment> list = this.cmDepartmentDao.freeFind(query);
		//转换集合
		List<CmDepartmentTree> convertList = new ArrayList<>();
		//树形集合
		List<CmDepartmentTree> treeList = new ArrayList<>();
		//转换类型
		if(CollectionUtils.isNotEmpty(list)){
			for(CmDepartment dep: list){
				CmDepartmentTree depTree = new CmDepartmentTree();
				BeanUtils.copyProperties(dep,depTree);
				convertList.add(depTree);
			}

		}
		//构建树形集合 找爸爸方法
		for(CmDepartmentTree dep: convertList){
			boolean isTop = true;
			for(CmDepartmentTree dep1: convertList){
				if(dep.getParentId() == dep1.getDepId()){
					isTop = false;
					if(CollectionUtils.isEmpty(dep1.getChildren())){
						dep1.setChildren(new ArrayList<CmDepartmentTree>());
					}
					dep1.getChildren().add(dep);
					break;
				}
			}
			if(isTop){
				treeList.add(dep);
			}
		}
		//排序
		Collections.sort(treeList);
		CmCorp corp = new CmCorp();
		corp.setCpId(cti.getOrgId());
		corp = cmCorpDao.findByPK(corp);
		//默认未分配机构0
		CmDepartmentTree departmentTree = new CmDepartmentTree();
		departmentTree.setDepId(0);
		departmentTree.setDepName("未分配");
		departmentTree.setParentId(-1);
		treeList.add(0,departmentTree);
		//默认公司名称机构
		CmDepartmentTree departmentTree1 = new CmDepartmentTree();
		departmentTree1.setDepId(-1);
		departmentTree1.setDepName(corp.getCorpName());
		treeList.add(0,departmentTree1);
		return treeList;
	}

	/**
	 * 新增部门
	 * @param cti
	 * @param department
     * @return
     */
	public Result add(ContextInfo cti,CmDepartment department){
		Result result = new Result();
		//防止查询到其他企业部门
		if(department.getParentId() != -1){
			if(cmDepartmentDao.findByIdAndOrg(department.getParentId(),cti.getOrgId()) == null){
				result.setSuccess(false).setError("父级部门不存在");
				return result;
			}
		}
		//验证部门名称是否重复
		CmDepartment query = new CmDepartment();
		query.setOrgId(cti.getOrgId());
		query.setDepNameEq(department.getDepName());
		query.setDr(0);
		if(CollectionUtils.isNotEmpty(cmDepartmentDao.freeFind(query))){
			result.setSuccess(false).setError("部门名称已存在");
			return result;
		}
		//初始化字段
		department.setOrgId(cti.getOrgId());
		department.setDr(0);
		department.setCreateBy(cti.getUserId());
		department.setCreateDt(new Date());
		//新增
		if(cmDepartmentDao.insert(cti, department) > 0){
			result.setSuccess(true).setError("新增成功");
		}else{
			result.setSuccess(false).setError("新增失败");
		}
		return result;
	}

	/**
	 * 通过id 删除部门
	 * @param cti
	 * @param depId
     * @return
     */
	public Result deleteDepById(ContextInfo cti,Integer depId){
		Result result = new Result();
		//只能操作本机构部门
		CmDepartment query = new CmDepartment();
		query.setDepId(depId);
		query.setOrgId(cti.getOrgId());
		query.setDr(0);
		List<CmDepartment> list = cmDepartmentDao.freeFind(query);
		if(CollectionUtils.isEmpty(list)){
			result.setSuccess(false).setError("部门不存在");
			return result;
		}
		//部门下不能存在子部门
		query = new CmDepartment();
		query.setParentId(depId);
		query.setDr(0);
		list = cmDepartmentDao.freeFind(query);
		if(CollectionUtils.isNotEmpty(list)){
			result.setSuccess(false).setError("该部门下存在子部门");
			return result;
		}
		//部门下不能存在员工
		if(cmEmployeeDao.countEmplByDepId(depId)>0){
			result.setSuccess(false).setError("该部门下存在员工");
			return result;
		}
		CmDepartment department = new CmDepartment();
		department.setDepId(depId);
		department.setDr(1);
		department.setModifyBy(cti.getUserId());
		department.setModifyDt(new Date());
		if(cmDepartmentDao.update(cti, department)>0){
			result.setSuccess(true).setError("删除成功");
		}else{
			result.setSuccess(false).setError("删除失败");
		}
		return result;
	}

	/**
	 * 修改部门信息
	 * @param cti
	 * @param department
     * @return
     */
	public Result updateDepartment(ContextInfo cti, CmDepartment department){
		Result result = new Result();
		//只能操作本机构部门
		CmDepartment query = new CmDepartment();
		query.setDepId(department.getDepId());
		query.setOrgId(cti.getOrgId());
		query.setDr(0);
		List<CmDepartment> list = cmDepartmentDao.freeFind(query);
		if(CollectionUtils.isEmpty(list)){
			result.setSuccess(false).setError("部门不存在");
			return result;
		}
		if(department.getParentId() != -1){
			if(cmDepartmentDao.findByIdAndOrg(department.getParentId(),cti.getOrgId()) == null){
				result.setSuccess(false).setError("父级部门不存在");
				return result;
			}
		}
		List<Integer> ids = this.querySubOrgIdForOrg(department.getDepId());
		ids.add(department.getDepId());
		if (ids.contains(department.getParentId())) {
			result.setSuccess(false).setError("父级部门不能是自己或子部门");
			return result;
		}
		//验证部门名称是否重复
		query = new CmDepartment();
		query.setDepNameEq(department.getDepName());
		query.setOrgId(cti.getOrgId());
		query.setDr(0);
		list =  cmDepartmentDao.freeFind(query);
		if(CollectionUtils.isNotEmpty(list)){
			if(list.size() > 1){
				result.setSuccess(false).setError("部门名称已存在");
				return result;
			}
			if(!list.get(0).getDepId().equals(department.getDepId())){
				result.setSuccess(false).setError("部门名称已存在");
				return result;
			}
		}
		department.setModifyBy(cti.getUserId());
		department.setModifyDt(new Date());
		if(cmDepartmentDao.update(cti, department)>0){
			result.setSuccess(true).setError("修改成功");
		}else{
			result.setSuccess(false).setError("修改失败");
		}
		return result;
	}

	/**
	 * 查询该部门下子部门
	 * @param depId
	 * @return
     */
	public List<Integer> querySubOrgIdForOrg(Integer depId) {
		List<Integer> ids = new ArrayList<Integer>();
		if (StringUtils.isEmpty(depId)) {
			return ids;
		}
		this.recursiveQuerySubOrgId(ids, depId);
		return ids;
	}

	/**
	 * 递归查询子机构id
	 *
	 * @param ids
	 * @param depId
	 */
	private void recursiveQuerySubOrgId(List<Integer> ids, Integer depId) {
		if (StringUtils.isEmpty(depId)) {
			return;
		}
		CmDepartment query = new CmDepartment();
		query.setParentId(depId);
		query.setDr(0);
		List<CmDepartment> list = this.cmDepartmentDao.freeFind(query);
		if (!CollectionUtils.isEmpty(list)) {
			for (CmDepartment org : list) {
				ids.add(org.getDepId());
				this.recursiveQuerySubOrgId(ids, org.getDepId());
			}
		}
	}
}
