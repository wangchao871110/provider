package com.xfs.sp.service;

import java.util.List;

import com.xfs.bill.model.SpsBill;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.sp.model.SpsSchemeEmp;

/**
 * SpsSchemeEmpService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/03 16:26:38
 */
public interface SpsSchemeEmpService {
	
	public int save(ContextInfo cti, SpsSchemeEmp vo);
	public int insert(ContextInfo cti, SpsSchemeEmp vo);
	public int remove(ContextInfo cti, SpsSchemeEmp vo);
	public int update(ContextInfo cti, SpsSchemeEmp vo);
	public PageModel findPage(PageInfo pi, SpsSchemeEmp vo);
	public List<SpsSchemeEmp> findAll(SpsSchemeEmp vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/03 16:26:38
	/**
	 * 根据主键查询 实体
	 *
	 * @author lifq
	 *
	 * 2016年8月4日  上午9:58:03
	 */
	public SpsSchemeEmp findByPK(SpsSchemeEmp vo);
	List<SpsSchemeEmp> findSchemeEmpByEmpIds(Integer spId, List<Integer> empIds);
    public int removeByEmp(ContextInfo cti, SpsSchemeEmp vo);
	/**
	 * 通过员工id与方案id修改状态
	 * @param vo
	 * @return
	 */
	public int updateStateByEmpAndScheme(ContextInfo cti, SpsSchemeEmp vo);
	void addSchemeEmp(ContextInfo cti, Integer empid, Integer schemeId);
	public int updateByEmp(ContextInfo cti, SpsSchemeEmp obj);
	/**
	 * 根据empIds 更新状态
	 *
	 * @author lifq
	 *
	 * 2017年8月1日  下午5:16:07
	 */
	public int updateStateByEmpIds(ContextInfo cti,Integer cpId,String empIds);
	
	/**
	 * 根据数据权限获取方案下的服务人员
	 *  @param cti
	 *  @param cpId
	 *  @param spId
	 *  @return 
	 *	@return 			: List<SpsBill> 
	 *  @createDate  	: 2017年8月9日 上午10:31:15
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年8月9日 上午10:31:15
	 *  @updateAuthor  :
	 */
	public List<SpsSchemeEmp> findEmpByScheme(ContextInfo cti, Integer cpId, Integer spId);

}
