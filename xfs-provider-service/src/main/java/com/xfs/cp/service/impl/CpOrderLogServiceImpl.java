package com.xfs.cp.service.impl;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.base.model.SysUploadfile;
import com.xfs.base.service.SysUploadfileService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.corp.dto.EmployeeDto;
import com.xfs.corp.service.CmEmployeeService;
import com.xfs.cp.dao.CpOrderLogDao;
import com.xfs.cp.model.CpOrder;
import com.xfs.cp.model.CpOrderLog;
import com.xfs.cp.service.CpOrderLogService;
import com.xfs.common.util.StringUtils;
import com.xfs.enums.OrderStatusType;

@Service
public class CpOrderLogServiceImpl implements CpOrderLogService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(CpOrderLogServiceImpl.class);
	
	@Autowired
	private CpOrderLogDao cpOrderLogDao;

	@Autowired
	private CmEmployeeService cmEmployeeService;

	@Autowired
	private SysUploadfileService sysUploadfileService;
	
	public int save(ContextInfo cti, CpOrderLog vo ){
		return cpOrderLogDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  CpOrderLog vo ){
		return cpOrderLogDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  CpOrderLog vo ){
		return cpOrderLogDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  CpOrderLog vo ){
		return cpOrderLogDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, CpOrderLog vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cpOrderLogDao.countFreeFind(vo);
		pm.setTotal(total);
		List<CpOrderLog> datas = cpOrderLogDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<CpOrderLog> findAll(CpOrderLog vo){
		
		List<CpOrderLog> datas = cpOrderLogDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 19:24:41


	public void saveLog(ContextInfo cti, Integer status, String orderId, Integer userId, Integer stage) {
		Date currDate = new Date();
		CpOrderLog log = new CpOrderLog();
		log.setStatus(status);
		log.setCpOrderId(orderId);
		log.setOperationTime(currDate);
		log.setCreateBy(userId);
		log.setCreateDt(currDate);
		log.setModifyBy(userId);
		log.setModifyDt(currDate);
		log.setStage(stage);
		this.save(cti,log);
	}

	public List<CpOrderLog> findCurrStageLogByOrderPks(List<Integer> ids) {
		return cpOrderLogDao.findCurrStageLogByOrderPks(ids);
	}

	public List<CpOrderLog> findStep4NeedPayOrder() {

		return cpOrderLogDao.findStep4NeedPayOrder();
	}
	@Override
	public CpOrderLog findOneCpOrderLog(CpOrderLog logVo) {
		return cpOrderLogDao.findOneCpOrderLog(logVo);
	}
	@Override
	public int updateByCpOrderLogId(ContextInfo cti, CpOrderLog cpOrderLog) {
		return cpOrderLogDao.updateByCpOrderLogId(cti, cpOrderLog);
	}
	@Override
	public Result userToSAAS(ContextInfo sysUser, CpOrder order) throws Exception {
		Result result=new Result();
		List<EmployeeDto> employeeDtos=new ArrayList<>();
		
		CpOrderLog cpOrderLog=new CpOrderLog();
		cpOrderLog.setCpOrderId(order.getOrderId());
		cpOrderLog.setStatus(OrderStatusType.CREATE_ORDER_EDIT.getValue());
		cpOrderLog.setDr(0);
		cpOrderLog=this.findOneCpOrderLog(cpOrderLog);
		
		String[] fileIds=new String[0];
		
		if(!StringUtils.isBlank(cpOrderLog.getFileId())){
			fileIds=cpOrderLog.getFileId().split(",");
			for (String fileId : fileIds) {
				InputStream in=null;
				SysUploadfile vo = new SysUploadfile();
				vo.setId(Integer.parseInt(fileId));
				SysUploadfile uploadFile = sysUploadfileService.findByPK(vo);
				//String filePath = sysUploadfileService.getPhysicsFile(Integer.parseInt(fileId));
				if(uploadFile.getFilename().indexOf(".xlsx")>0 || uploadFile.getFilename().indexOf(".xls")>0){
					in = sysUploadfileService.downloadFile(uploadFile.getSavename());
					if(null == in){
						continue;
					}
					try {
						Workbook workbook = null;
				        try {
				            workbook = new XSSFWorkbook(in);
				        } catch (Exception ex) {
				            workbook = new HSSFWorkbook(in);
				        }
				        Sheet sheet = workbook.getSheetAt(0);
				        EmployeeDto employeeDto=null;
				        for (int i = 2; i < 1002; i++) {
				        	Row row=sheet.getRow(i);
				        	if(row!=null){
				        		employeeDto=new EmployeeDto();
				        		//员工姓名
					    		employeeDto.getCmEmployee().setName(row.getCell(0)==null?"":row.getCell(0).toString().trim());
					    		//证件类型
					    		employeeDto.getCmEmployee().setIdentityType(row.getCell(1)==null?"":row.getCell(1).toString().trim());
					    		//证件号码
					    		employeeDto.getCmEmployee().setIdentityCode(row.getCell(2)==null?"":row.getCell(2).toString().trim());
					    		//参保地
					    		employeeDto.setAreaName(row.getCell(3)==null?"":row.getCell(3).toString().trim());
					    		//户口性质
					    		employeeDto.getCmEmployee().setInsuranceLivingType(row.getCell(4)==null?"":row.getCell(4).toString().trim());
					    		//社保缴费工资
					    		if(StringUtils.isNumeric(row.getCell(5)==null?"NUll":row.getCell(5).toString().trim())){
					    			employeeDto.getCmEmployee().setInsuranceSalary(new BigDecimal(row.getCell(5).toString().trim()));
					    		}else{
					    			employeeDto.getCmEmployee().setInsuranceSalary(new BigDecimal("1000"));
					    		}
					    		//公积金缴费工资
					    		if(StringUtils.isNumeric(row.getCell(6)==null?"NUll":row.getCell(6).toString().trim())){
					    			employeeDto.getCmEmployee().setFundSalary(new BigDecimal(row.getCell(6).toString().trim()));
					    		}else{
					    			employeeDto.getCmEmployee().setFundSalary(new BigDecimal("1000"));
					    		}
					    		
					    		if(!"".equals(employeeDto.getCmEmployee().getName()) && !"".equals(employeeDto.getCmEmployee().getIdentityCode())){
					    			employeeDtos.add(employeeDto);
					    		}
					    		
				        	}else{
				        		break;
				        	}
						}
				        in.close();
					}catch (Exception e) {
						e.printStackTrace();
						in.close();
					}
				} 
			}
			if(employeeDtos.size()>0){
				result=cmEmployeeService.batchSaveEmployee(sysUser, employeeDtos, order.getASpId(), order.getBSpId());
			}
			
		}
		
		return result;
	}
	
	
}
