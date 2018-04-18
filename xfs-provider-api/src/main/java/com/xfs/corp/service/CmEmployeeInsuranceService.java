package com.xfs.corp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.xfs.business.model.BsRatioCalcInterface;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.corp.dto.EmplnsFundDetailDto;
import com.xfs.corp.model.CmEmployee;
import com.xfs.corp.model.CmEmployeeInsurance;

/**
 * @author 	: yangfangwei
 * @date 	: 2016-11-10 14:10:00
 * @version 	: V1.0
 */
public interface CmEmployeeInsuranceService {
	
	public int save(ContextInfo cti, BsRatioCalcInterface vo);
	public int insert(ContextInfo cti, CmEmployeeInsurance vo);
	public int remove(ContextInfo cti, CmEmployeeInsurance vo);
	public int update(ContextInfo cti, CmEmployeeInsurance vo);
	public int updateCmEmployeeInsurance(ContextInfo cti, CmEmployeeInsurance vo);
	public PageModel findPage(PageInfo pi, BsRatioCalcInterface vo);
	public List<CmEmployeeInsurance> findAll(BsRatioCalcInterface vo);




    //温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/03 16:26:34


	List<CmEmployeeInsurance> findEmployeeInsuranceByEmployee(List<Integer> empIds,List<Integer> insuranceIds);
	List<CmEmployeeInsurance> findEmployeeInsuranceByEmpIdsAndPeriod(List<Integer> empIds, String bperiod,
			String eperiod, List<Integer> insuranceIds);
	/**
	 * 查询社保数据
	 *  @param month
	 *  @param tenantId
	 *  @return 
	 *	@return 			: Result 
	 *  @createDate  	: 2017年5月22日 下午4:35:24
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年5月22日 下午4:35:24
	 *  @updateAuthor  :
	 */
	public Result findAllInsurance(String month, String tenantId);
	/**
	 * 获取个人社保缴费详情
	 *  @param month
	 *  @param tenantId
	 *  @param empId
	 *  @return 
	 *	@return 			: Result 
	 *  @createDate  	: 2017年5月23日 上午11:12:01
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年5月23日 上午11:12:01
	 *  @updateAuthor  :
	 */
	public Result findOneInsurance(String month, String tenantId, String empId);
	public Result loopAdjustEmp(ContextInfo cti, CmEmployee emp, CmEmployeeInsurance empIns, ArrayList<CmEmployeeInsurance> oldInss, String beginPeriod, Integer taskid);

	public List<EmplnsFundDetailDto> findEmpInsFundDetail(CmEmployee cmEmployee, String month, String insuranceFundType);

	List<Map<String,Object>> FindEmployeeInsuranceMapByEmp(CmEmployeeInsurance employeeInsurance, Integer areaId, String insurance);
	Result adjustEmployeeInsurance(ContextInfo cti,Integer spId, List<CmEmployee> emps, String insuranceOrFund, String beginPeriod, Integer taskid);
	Result adjustEmployeeInsurance(ContextInfo cti, Integer spId, List<CmEmployee> emps, String insuranceOrFund, String beginPeriod,String endPeriod, Integer taskid, List<Integer> insuranceIds,String payType);
	Result adjustEmployeeInsurance(ContextInfo cti,Integer spId, CmEmployee emp, String insuranceOrFund, String beginPeriod, Integer taskid);
	Result adjustEmployeeInsurance(ContextInfo cti,Integer spId, CmEmployee emp, String insuranceOrFund, String beginPeriod, Integer taskid,String payType);
	Result adjustEmployeeInsurance(ContextInfo cti,Integer spId, CmEmployee emp, String insuranceOrFund, String beginPeriod,String endPeriod, Integer taskid,String payType);
	void endEmployeeInsuranceByEmpIdsAndPeriod(ContextInfo cti,List<Integer> empIds, String insuranceOrFund, String period, Integer taskid);

	List<CmEmployeeInsurance> findEmployeeInsuranceByEmpIdsAndPeriod(List<Integer> empIds, String period,List<Integer> insuranceIds);
	void cancelEmployeeInsuranceByBeginTaskId(ContextInfo cti,Integer taskid);
	void cancelEmployeeInsuranceByBeginTaskId(ContextInfo cti,Integer taskid,String payType);
	void resetEmployeeInsuranceByEndTaskId(ContextInfo cti,Integer taskid);
	void endEmployeeInsuranceByEmpIdsAndPeriod(ContextInfo cti, List<Integer> empIds, String insuranceOrFund, String endPeriod, Integer endTaskId, List<Integer> insuranceIds);


}
