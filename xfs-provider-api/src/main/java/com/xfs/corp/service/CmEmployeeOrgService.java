package com.xfs.corp.service;
import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.corp.model.CmCorp;
import com.xfs.corp.model.CmEmployee;
import com.xfs.corp.model.CmEmployeeOrg;

/**
 * CmEmployeeOrgService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2017/09/20 16:10:56
 */
public interface CmEmployeeOrgService {
	
	public int save(ContextInfo cti, CmEmployeeOrg vo);
	public int insert(ContextInfo cti,CmEmployeeOrg vo);
	public int remove(ContextInfo cti,CmEmployeeOrg vo);
	public int update(ContextInfo cti,CmEmployeeOrg vo);
	public PageModel findPage(PageInfo pi, CmEmployeeOrg vo);
	public List<CmEmployeeOrg> findAll(CmEmployeeOrg vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/09/20 16:10:56

	/**
	 * 获取已经同步的人员信息
	 * @param vo
	 * @return
	 */
	public CmEmployeeOrg queryEmployeeOrg(CmEmployeeOrg vo);

	/**
	 * 获取同步列表--包含匹配和未匹配人员
	 * @param pi
	 * @param vo
	 * @return
	 */
	public PageModel querySynDataList(PageInfo pi, CmEmployeeOrg vo);

	/**
	 * 系统自动匹配人数
	 * @param pi
	 * @param vo
	 * @return
	 */
	public int querySynSystemDataList(CmEmployeeOrg vo);

	/**
	 * 获取未匹配同步数据列表
	 * @param vo
	 * @return
	 */
	public List<CmEmployeeOrg> queryUnEmpIdSynDataList( CmEmployeeOrg vo);

	/**
	 * 获取需要保存的同步数据
	 * @param vo
	 * @return
	 */
	public void saveSynOrgData(ContextInfo cti, CmCorp cmCorp, CmEmployeeOrg vo, Result result);


	public int updateOrgData(ContextInfo cti,CmEmployeeOrg vo);

	/**
	 * 修改当前同步数据状态
	 * @param cti
	 * @param vo
	 * @return
	 */
	public int updateOrgDataMark(ContextInfo cti,CmEmployeeOrg vo);
}
