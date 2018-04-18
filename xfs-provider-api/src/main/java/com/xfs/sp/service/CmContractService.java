package com.xfs.sp.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.mall.order.model.SpsMallOrder;
import com.xfs.sp.model.CmContract;
import com.xfs.user.model.SysUser;

/**
 * CmContractService
 * @author:wangchao
 * @date:2016/08/02 15:23:53
 */
public interface CmContractService {
	
	public int save(ContextInfo cti, CmContract vo);
	public int insert(ContextInfo cti, CmContract vo);
	public int remove(ContextInfo cti, CmContract vo);
	public int update(ContextInfo cti, CmContract vo);
	public PageModel findPage(PageInfo pi, CmContract vo);
	public List<CmContract> findAll(CmContract vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/02 15:23:53

	public PageModel cs_findPage(PageInfo pi,CmContract vo);

	/**
	 * 签署合同
	 *
	 * @param cti
	 * @param contract
	 * @param order
	 * @param result
	 * @return
	 * @throws Exception
	 */
	public Result signContract(ContextInfo cti, CmContract contract, SpsMallOrder order, Result result) throws Exception;
	
	/**
	 * 查询 合同信息
	 *
	 * @author lifq
	 *
	 * 2016年8月2日  下午9:02:31
	 */
	public List<Map<String,Object>> findContracts(Integer sp_id, Integer cp_id);
	/**
	 * 保存 附件合同
	 *
	 * @author lifq
	 *
	 * 2016年7月29日  下午3:47:04
	 */
	public Result saveContract(ContextInfo cti, CmContract contract);
	/**
	 * 删除合同记录
	 *
	 * @author lifq
	 *
	 * 2016年8月4日  上午9:55:23
	 */
	public Result delContract(ContextInfo cti, Integer contract_id);
	/**
	 * 根据主键查询 实体
	 *
	 * @author lifq
	 *
	 * 2016年8月4日  上午9:58:03
	 */
	public CmContract findByPK(CmContract vo);
	
	/**
	 * 保存合同
	 * @param cti
	 * @param contrat
	 */
	public Result saveConteact(ContextInfo cti, CmContract contrat, Result result);
	/**
	 * 合同发起
	 * @param order
	 * @param contractId
	 * @return
	 */
	public Result startContract(ContextInfo cti, SpsMallOrder order, Integer contractId, Result result,String outputtemplate);

	/**
	 * 获取合同模板HTML
	 *
	 * @param contract
	 * @return
	 */
	public String getContractTemplateHtml(CmContract contract);
	/**
	 * 合同签章回调
	 * @param result_code
	 */
	public boolean fadadaCallback(ContextInfo cti,String result_code,String transaction_id,String download_url,String viewpdf_url,String result_desc);
	/**
	 * 获取合同
	 * @param cti
	 * @param model
	 * @param contrat
	 * @param contrat
	 */
	public void getContract(ContextInfo cti, Model model, CmContract contrat);
	/**
	 * 取消合同
	 * @param model
	 * @param contrat
	 * @param result
	 * @return
	 */
	public Result cancelContract(ContextInfo cti, Model model, CmContract contrat, Result result);

	/**
	 * 保存线下合同
	 *
	 * @param cti
	 * @param contract
	 * @param orderState
	 * @param result
	 * @return
	 * @throws Exception
	 */
	public Result saveOfflineContract(ContextInfo cti, CmContract contract, String orderState, Result result) throws Exception;
}
