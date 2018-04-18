package com.xfs.business.service.impl;

import java.awt.print.Book;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.xfs.acc.dto.ApplyMessage;
import com.xfs.aop.task.sps.TaskAspectService;
import com.xfs.aop.task.verify.TaskDataParseFactory;
import com.xfs.aop.task.verify.UpdatePhoneDataParseImpl;
import com.xfs.base.dao.BdBusinessfieldDao;
import com.xfs.base.dao.BsAreaDao;
import com.xfs.base.dao.SysDictitemDAO;
import com.xfs.base.dto.BusinessField;
import com.xfs.base.model.BdBusinessfield;
import com.xfs.base.model.BsArea;
import com.xfs.base.model.BsSysStateReport;
import com.xfs.base.model.SysDictitem;
import com.xfs.base.model.SysMessage;
import com.xfs.base.service.BdBusinessfieldService;
import com.xfs.base.service.BsAreaService;
import com.xfs.base.service.BsSysStateReportService;
import com.xfs.base.service.SysDictitemService;
import com.xfs.base.service.SysUploadfileService;
import com.xfs.business.dao.SpsTaskDao;
import com.xfs.business.dao.SpsTaskHistoryDao;
import com.xfs.business.enums.BsType;
import com.xfs.business.enums.TaskStateFiled;
import com.xfs.business.model.BdBsareatype;
import com.xfs.business.model.BdBstype;
import com.xfs.business.model.BdBstypeareafield;
import com.xfs.business.model.SpsFixedpointhospital;
import com.xfs.business.model.SpsTask;
import com.xfs.business.model.SpsTaskHistory;
import com.xfs.business.service.BdBsareatypeService;
import com.xfs.business.service.BdBstypeService;
import com.xfs.business.service.BdBstypeareafieldService;
import com.xfs.business.service.SpsFixedpointhospitalService;
import com.xfs.business.service.SpsTaskService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.exception.BusinessException;
import com.xfs.common.page.PageModel;
import com.xfs.common.redies.RedisUtil;
import com.xfs.common.redies.keychain.IRedisKeys;
import com.xfs.common.util.Constant;
import com.xfs.common.util.DateUtil;
import com.xfs.common.util.FileUtil;
import com.xfs.common.util.IdcardValidator;
import com.xfs.common.util.MobileValidator;
import com.xfs.common.util.ZipUtil;
import com.xfs.corp.dao.CmCorpDao;
import com.xfs.corp.dao.CmEmployeeDao;
import com.xfs.corp.dto.EmplInsuranceDto;
import com.xfs.corp.model.CmCorp;
import com.xfs.corp.model.CmEmployee;
import com.xfs.corp.model.CmEmployeeInsurance;
import com.xfs.corp.service.CmCorpService;
import com.xfs.corp.service.CmEmployeeService;
import com.xfs.employeeside.dto.SpsStateDto;
import com.xfs.sp.dto.SpsTaskDto;
import com.xfs.sp.model.SpsScheme;
import com.xfs.sp.service.SpsAccountRatioService;
import com.xfs.sp.service.SpsSchemeService;
import com.xfs.sps.service.TaskDataParseInterface;
import com.xfs.sps.utils.ExportExcelUtil;
import com.xfs.sps.utils.ImportExcelUtil;
import com.xfs.task.dto.EmpDetailVo;
import com.xfs.task.dto.FlowChartDto;
import com.xfs.task.dto.FlowChartVo;
import com.xfs.task.dto.HandlePersonnelDto;
import com.xfs.task.dto.HandlePersonnelListVo;
import com.xfs.task.dto.ItemWarnDto;
import com.xfs.task.dto.ItemWarnVo;
import com.xfs.task.dto.OtherBusinessVo;
import com.xfs.task.dto.TaskCenterDto;
import com.xfs.task.dto.TaskDto;
import com.xfs.task.dto.TaskMapVo;
import com.xfs.task.dto.TaskVo;
import com.xfs.ts.service.SpsTaskBotService;
import com.xfs.user.dao.SysUserDao;
import com.xfs.user.model.SysUser;
import com.xfs.user.service.SysFunctionDataService;

@Service
public class SpsTaskServiceImpl implements SpsTaskService, IRedisKeys {

    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(SpsTaskServiceImpl.class);

    @Autowired
    private SysUploadfileService sysUploadfileService;

    @Autowired
    private SpsTaskDao spsTaskDao;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SpsTaskHistoryDao spsTaskHistoryDao;

    @Autowired
    private TaskAspectService taskAspectService;

    @Autowired
    CmCorpService cmCorpService;

    @Autowired
    private BdBsareatypeService bdBsareatypeService;

    @Autowired
    private BdBusinessfieldService bdBusinessfieldService;

    @Autowired
    private BdBstypeService bdBstypeService;

    @Autowired
    private SysDictitemDAO sysDictitemDAO;

    @Autowired
    private BsAreaService bsAreaService;
    @Autowired
    SpsTaskBotService taskBotService;
    @Autowired
    BdBstypeareafieldService bdBstypeareafieldService;
    @Autowired
    private CmEmployeeService cmEmployeeService;
    @Autowired
    private CmEmployeeDao cmEmployeeDao;
    @Autowired
    private CmCorpDao cmCorpDao;
    @Autowired
    private BsAreaDao bsAreaDao;
    @Autowired
    private SysDictitemService sysDictitemService;
    @Autowired
    private SysFunctionDataService sysFunctionDataService;
    @Autowired
    private SpsSchemeService spsSchemeService;
    @Autowired
    private SpsAccountRatioService spsAccountRatioService;
    @Autowired
    private SpsFixedpointhospitalService spsFixedpointhospitalService;
    @Autowired
    private BsSysStateReportService bsSysStateReportService;
    @Autowired
    private BdBusinessfieldDao bdBusinessfieldDao;

    /**
     * 系统 业务类型
     */
    public static String SYS_BS_TYPE = "sys_bs_type";

    public int save(ContextInfo cti, SpsTask vo) {
        return spsTaskDao.save(cti, vo);
    }

    public int insert(ContextInfo cti, SpsTask vo) {
        return spsTaskDao.insert(cti, vo);
    }

    public int remove(ContextInfo cti, SpsTask vo) {
        return spsTaskDao.remove(cti, vo);
    }

    public int update(ContextInfo cti, SpsTask vo) {
        return spsTaskDao.update(cti, vo);
    }

    public PageModel findPage(PageInfo pi, SpsTask vo, ContextInfo cti) {

        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        vo.setDr(0);
        // 数据权限
        String authority = sysFunctionDataService.getDataAuthorityByUser(cti.getUserId(), cti.getUserType(), Constant.DATATYPE);
        vo.setAuthority(authority);
        Integer total = spsTaskDao.countFreeFind(vo);
        pm.setTotal(total);
        List<Map<String, Object>> datas = spsTaskDao.freeFindAllList(vo, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;

    }

    public PageModel findPage(PageInfo pi, SpsTask vo) {

        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = spsTaskDao.countFreeFind(vo);
        pm.setTotal(total);
        List<SpsTask> datas = spsTaskDao.freeFind(vo, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;

    }

    public List<SpsTask> findAll(SpsTask vo) {

        List<SpsTask> datas = spsTaskDao.freeFind(vo);
        return datas;

    }

    public List<SpsTask> findAll(SpsTask vo, String orderByColName) {

        List<SpsTask> datas = spsTaskDao.freeFind(vo, orderByColName);
        return datas;

    }


    //温馨提示：
    //以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    //2016/04/08 11:21:48

    /**
     * findByPK
     *
     * @param vo
     * @return
     */
    @Override
    public SpsTask findByPK(SpsTask vo) {
        return spsTaskDao.findByPK(vo);
    }

    /**
     * 根据服务商Id查询  下面企业信息
     *
     * @author lifq
     * <p>
     * 2016年4月8日  下午1:55:34
     */
    public List<Map<String, Object>> findTaskCount(Integer serviceId) {
        List<Map<String, Object>> datas = spsTaskDao.findTaskCount(serviceId);
        return datas;
    }

    /**
     * 导入 更新手机号 excel
     *
     * @author lifq
     *
     * 2016年4月9日  下午2:26:05
     */
    /*
    public Result importUpdatePhoneExcel(Integer fileId,Integer empId,SysUser curUser) throws BusinessException{
    	Result result = Result.createResult().setSuccess(false);
    	if(null==fileId){
			result.setError("fileId不能为空！");
			return result;
		}
    	//文件根目录
    	String fileRootPath = Config.getProperty("fileRootPath");
		
		SysUploadfile vo = new SysUploadfile();
		vo.setId(Integer.valueOf(fileId));
		SysUploadfile uploadFile = sysUploadfileService.findByPK(vo);
		
		if(null==uploadFile){
			result.setError("文件不存在！");
			return result;
		}
		String filePath = fileRootPath + File.separator + uploadFile.getFilepath() + File.separator +uploadFile.getSavename();
	    // 构造 XSSFWorkbook 对象，strPath 传入文件路径   
		List<Map<String,String>> dataList = new ArrayList<Map<String,String>>();
		List<Map<String,String>> errorDataList = new ArrayList<Map<String,String>>();
		boolean isError = false;
	    OPCPackage pkg;
	    XSSFWorkbook xwb = null;
		try {
			pkg = OPCPackage.open(filePath);
			xwb = new XSSFWorkbook(pkg);
			 // 读取表格内容  
		    XSSFSheet sheet = xwb.getSheetAt(0);   
		    XSSFRow row = null;   
		    //处理标题行
	        row = sheet.getRow(0); 
	        String cell0Str = row.getCell(0).toString();
	        String cell1Str = row.getCell(1).toString();
	        String cell2Str = row.getCell(2).toString();
	        if(UpdatePhoneDataParseImpl.name.equals(cell0Str) && UpdatePhoneDataParseImpl.cardNO.equals(cell1Str)
	        		&& UpdatePhoneDataParseImpl.mobile.equals(cell2Str)){}else{
	        	result.setError("模板错误，请下载最新模板！");
				return result;
	        }
	        //处理内容行
	        for(int r=1;r<=sheet.getLastRowNum();r++){
	        	row = sheet.getRow(r);
	        	if(null==row){
	        		continue;
	        	}
	        	//处理列
	        	
	        	Map<String,String> curMap = new HashMap<String,String>();
	        	String error = "";
	        	curMap.put("row", r+1+"");
	        	if(null == row.getCell(0)){
	        		error += "姓名为空!";
	        	}else{
	        		curMap.put("name", row.getCell(0).toString());
	        	}
	        	
	        	if(null == row.getCell(1)){
	        		error += "身份证号为空!";
	        	}else{
	        		curMap.put("cardNO", row.getCell(1).toString());
	        		if(!IdcardValidator.is18Idcard(row.getCell(1).toString())){
	        			error += "身份证号格式错误!";
	        		}
	        		
	        	}
        		
	        	if(null == row.getCell(2)){
	        		error += "电话为空!";
	        	}else{
	        		curMap.put("mobile", row.getCell(2).toString());
	        		if(!MobileValidator.isMobileNO(row.getCell(2).toString())){
	        			error += "电话格式错误!";
	        		}
	        	}
	        	if(!"".equals(error)){
	        		curMap.put("error", error);
	        		errorDataList.add(curMap);
	        		isError = true;
	        	}
	        	
	        	dataList.add(curMap);
	        }
	        //关闭
	        pkg.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("excel读取出错！");
		}
		 
        if(isError){
        	result.setDataValue("errorDataList", errorDataList);
        	result.setDataValue("errorNum", errorDataList.size());
        }else{//数据校验通过后 开始导入系统
        	
        	if(null!=dataList && dataList.size()>0){
        		Date date = new Date();
        		for(int i=0;i<dataList.size();i++){
        			Map<String,String> curMap = dataList.get(i);
        			curMap.remove("row");
        			String json = JSON.toJSONString(curMap);
        			SpsTask curVo = new SpsTask();
        			curVo.setEmpId(empId);
        			curVo.setSpId(curUser.getOrgId());//spid
        			curVo.setJson(json);
        			curVo.setType("TODO");//待处理
        			curVo.setBstypeId(1);//1:更新手机号
        			curVo.setCreateBy(curUser.getUserId());
        			curVo.setCreateDt(date);
        			spsTaskDao.save(curVo);
        		}
        		
        	}
        	
        	result.setSuccess(true);
        	result.setDataValue("successNum", dataList.size());
        }
    	return result;
		
	}
	*/

    /**
     * 保存 fastlist 传入的数据
     *
     * @author lifq
     * <p>
     * 2016年4月9日  下午3:50:38
     */
    public void saveTaskFromFastList(ContextInfo cti, Integer userId, Integer cpId, Integer spId, Integer bstypeId, String json) throws BusinessException {
        IdcardValidator iv = new IdcardValidator();
        if (1 == bstypeId) {//1:更新手机号
            //校验
            Map<String, String> jsonMap = JSON.parseObject(json, Map.class);
            String idcard = jsonMap.get("身份证号");
            if (!iv.isValidate18Idcard(idcard)) {
                throw new BusinessException("身份证格式错误！");
            }
            String mobile = jsonMap.get("电话");
            if (!MobileValidator.isMobileNO(mobile)) {
                throw new BusinessException("电话格式错误！");
            }


            //保存
            SpsTask curVo = new SpsTask();
            curVo.setCpId(cpId);
            curVo.setSpId(spId);//spid
            curVo.setJson(json.replaceAll("姓名", "name").replaceAll("电话", "mobile").replaceAll("身份证号", "cardNO"));
            curVo.setType("TODO");//待处理
            curVo.setBstypeId(1);//1:更新手机号
            curVo.setCreateBy(userId);
            curVo.setCreateDt(new Date());
            spsTaskDao.save(cti, curVo);
        } else if (7 == bstypeId) {//1:更新定点医院

            //校验
            Map<String, String> jsonMap = JSON.parseObject(json, Map.class);
            String idcard = jsonMap.get("cardNO");
            if (!iv.isValidate18Idcard(idcard)) {
                throw new BusinessException("身份证格式错误！");
            }

            //保存
            SpsTask curVo = new SpsTask();
            curVo.setCpId(cpId);
            curVo.setSpId(spId);//spid
            TaskDataParseInterface obj = new UpdatePhoneDataParseImpl();
            curVo.setJson(json);
            curVo.setType("TODO");//待处理
            curVo.setBstypeId(7);//7:更新定点医院
            curVo.setCreateBy(userId);
            curVo.setCreateDt(new Date());
            spsTaskDao.save(cti, curVo);

        }//可以继续 扩展...
    }

    /**
     * 查询企业列表
     *
     * @author lifq
     * <p>
     * 2016年4月9日  下午5:33:26
     */
    public List<Map<String, Object>> findEmpList(String type, Integer spId, Integer bstypeId) throws BusinessException {
        List<Map<String, Object>> datas = spsTaskDao.findEmpList(type, spId, bstypeId);
        return datas;
    }

    /**
     * 获取（未完成业务）企业列表
     *
     * @param type
     * @param spId
     * @return
     * @throws BusinessException
     */

    public List<Map<String, Object>> findOrgList(String type, Integer spId) throws BusinessException {
        List<Map<String, Object>> datas = spsTaskDao.findOrgList(type, spId);
        return datas;
    }

    /**
     * 查询 企业下 任务单信息
     *
     * @author lifq
     * <p>
     * 2016年4月9日  下午5:39:36
     */
    public List<Map<String, Object>> findEmpTaskList(Integer empId, String type, Integer spId, Integer bstypeId) throws BusinessException {
        List<Map<String, Object>> datas = spsTaskDao.findEmpTaskList(empId, type, spId, bstypeId);
        return datas;
    }

    /**
     * 查询 企业下 任务单json 字段
     *
     * @author lifq
     * 2016-08 增加执行年月
     * 2016年4月9日  下午5:39:36
     */
    public List<Map<String, Object>> findEmpTaskJsonList(Integer scheme_id, String type, Integer spId, Integer bstypeId) throws BusinessException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String extcuteDate = sdf.format(date);//Taskbot 调用 查询为处理任务单 执行月份为条件 且为当前月份

        List<Map<String, Object>> datas = spsTaskDao.findEmpTaskJsonList(scheme_id, type, spId, bstypeId, extcuteDate);
        //查询某个公司所在地区所对应的字段转换器 TODO 确认是否删除转换器
//		List<HashMap<String,String>> list = taskBotService.findConvert(scheme_id, bstypeId);
//		if (list.size() > 0) {
//			
//			String city = list.get(0).get("city");
//			
//			// 对每一行进行循环
//			for (Map<String, Object> map : datas) {
//				List<String[]> res = new ArrayList<>();
//				JSONObject psninfo = JSONObject.fromObject(map.get("json"));
//				Set<String> sets = psninfo.keySet();
//				for (String key : sets) {
//					String elementName = covert2TaskBot(list, key);
//					if (!StringUtils.isEmpty(elementName)) {
//						String[] oldnewKey = new String[2];
//						oldnewKey[0] = key;
//						oldnewKey[1] = elementName;
//						res.add(oldnewKey);
//					}
//				}
//				for (String[] oldnewKeys : res) {
//					Object value = TaskBotDict.getTrueValue(oldnewKeys[0],psninfo.get(oldnewKeys[0]),city,bstypeId);
//					psninfo.element(oldnewKeys[1], value);
//					
//					if(!oldnewKeys[0].equals(oldnewKeys[1])){
//						psninfo.remove(oldnewKeys[0]);
//					}
//				}
//				map.put("json", psninfo);
//			}
//		}
        return datas;
    }


    /**
     * 转换为taskbot 识别的字段
     *
     * @return
     */
    private String covert2TaskBot(List<HashMap<String, String>> list, String key) {
        for (HashMap<String, String> map : list) {
            String name = map.get("platform_name");
            if (key.equals(name)) {
                return map.get("element_name");
            }
        }
        return null;
    }


    /**
     * 查询 企业下 任务单 列表 （分页）
     *
     * @author lifq
     * <p>
     * 2016年4月11日  上午9:25:12
     */
    public PageModel findEmpPersonPage(PageInfo pi, Integer empId, String type, Integer spId, Integer bstypeId) {
        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = spsTaskDao.findEmpPersonPageCount(empId, type, spId, bstypeId);
        pm.setTotal(total);
        List datas = spsTaskDao.findEmpPersonPageData(empId, type, spId, bstypeId, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;

    }


    /**
     * 更新 任务表 人员信息
     *
     * @author lifq
     * <p>
     * 2016年4月11日  上午10:21:53
     */
    public void updatePsnInfo(ContextInfo cti, String type, Integer imgId, Integer taskId, String mark) throws BusinessException {

		/*
          20170320 修改任务单历史纪录记录
		 */
//		SpsTask vo = new SpsTask();
//		vo.setTaskId(taskId);
//		vo.setDr(0);
//		//与帅哥、玉凤讨论结果：调用该方法时 传入 taskId，后面 根据taskId更新，并处理员工表 信息   modify by lifq 20160530
//		//根据 主键查询
//		SpsTask  task = spsTaskDao.findByPK(vo);
//		Date date = new Date();
//		if(null!=task){
//			SpsTaskHistory sth = new SpsTaskHistory();
//			sth.setOperatePic(imgId);
//			sth.setCreateDt(date);
//			sth.setTaskId(taskId);
//			task.setErrmsg(mark);//文本消息
//			task.setPicId(imgId);
//			task.setOnlineStatus(type);// 2016-08 by zhangxiyan (taskbot  更改的是网申状态 而不是单据状态)
//			if(StringUtils.equals("SUCCESS", type)){
//				task.setType("SUCCESS");//2016-08 鹏哥确定 taskbot 如果处理失败 网申状态改为FAIL  单据状态不改变 还是待处理
//				sth.setOperate(mark+"将任务单状态操作为"+type+"！");
//			}else if("COMPLETED".equals(type)){
//				task.setType(type);
//			}else {
//				task.setType(type);
//			}
//
//			task.setModifyDt(date);
//			int res = spsTaskDao.update(cti,task);
//			if(res>0){//更新成功后 操作人员表 task历史记录表
//				//TODO saas批量执行操作
//				updatetoComplete(cti, task, sth);
//				spsTaskHistoryDao.insert(cti,sth);
//			}
//		}


    }


    /**
     * 查询 企业下 列表
     *
     * @author lifq
     * <p>
     * 2016年4月11日  上午9:25:12
     */
    public PageModel findEmpPage(PageInfo pi, Integer spId) {
        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = spsTaskDao.findEmpPageCount(spId);
        pm.setTotal(total);
        List datas = spsTaskDao.findEmpPageData(spId, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;
    }

    /**
     * 批量更新 任务状态
     *
     * @author lifq
     * <p>
     * 2016年4月11日  下午5:09:06
     */
    public void updateTaskStatus(ContextInfo cti, String selectedValue, String type, Integer userId) throws BusinessException {
        if (StringUtils.isBlank(selectedValue)) {
            throw new BusinessException("请选择需要操作的数据！");
        }
        String[] arr = selectedValue.split(",");
        if (null != arr && arr.length > 0) {
            for (int i = 0; i < arr.length; i++) {
                String taskId = arr[i];
                if (StringUtils.isBlank(taskId)) {
                    continue;
                }
                //更新
                SpsTask obj = new SpsTask();
                obj.setTaskId(Integer.valueOf(taskId));
                obj.setType(type);
                obj.setModifyBy(userId);
                obj.setModifyDt(new Date());
                if (type.equals("TODO")) {
                    obj.setOnlineStatus("TODO");
                } else if (type.equals("CLOSED")) {
                    obj.setOnlineStatus("COMPLETED");
                } else {
                    obj.setOnlineStatus(type);
                }
                spsTaskDao.update(cti, obj);
                SpsTask task = spsTaskDao.findByPK(obj);
                // 处理员工信息
                cmEmployeeService.dealEmpAfterTask(cti, task);
            }
        }
    }


    public void downLoadExcel(Integer spId, HttpServletRequest request, HttpServletResponse response) throws BusinessException, Exception {
        final String fileRootPath = request.getServletContext().getRealPath("/") + File.separator + "tmpfile"
                + File.separator;
        File dir = new File(fileRootPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        //获取数据
        SpsTask spsTask = new SpsTask();
        spsTask.setSpId(spId);
//		List<SpsTask> spsTaskList = spsTaskDao.freeFind(spsTask);

        List<Map<String, Object>> mapResult = spsTaskDao.findSpsTaskBotList(spsTask);

        // 导出
        ExportExcelUtil<Book> obj = new ExportExcelUtil<Book>();
        //获取日期的字符串
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String ex_date = dateFormat.format(new Date());
        String fileUUID = spId + "_" + ex_date;// UUIDUtil.randomUUID();
        OutputStream out = new FileOutputStream(fileRootPath + File.separator + fileUUID + ".xls");
        obj.exportExcelFromMySql("sheet1", out, mapResult, sysUploadfileService);
        out.close();

        String filePath = fileRootPath + File.separator + fileUUID + ".xls";
        File excelFile = new File(filePath);
        response.setContentType("application/octet-stream");
        request.setCharacterEncoding("UTF-8");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        long fileLength = new File(filePath).length();

        response.setHeader("Content-disposition", "attachment; filename="
                + new String((fileUUID + ".xls").getBytes("utf-8"), "ISO8859-1"));
        response.setHeader("Content-Length", String.valueOf(fileLength));

        bis = new BufferedInputStream(new FileInputStream(filePath));
        bos = new BufferedOutputStream(response.getOutputStream());
        byte[] buff = new byte[2048];
        int bytesRead;
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
            bos.write(buff, 0, bytesRead);
        }
        bis.close();
        bos.close();
    }
//	/**
//	 * 通用 导入excel
//	 *
//	 * @author lifq
//	 *
//	 * 2016年4月20日  下午2:57:46
//	 */
//	public Result importExcel(Integer fileId,Integer cpId,Integer spId,SysUser curUser,String selectContent,Integer bstypeId,String sheetName,Integer areaId) throws Exception{
//		 Result result = Result.createResult().setSuccess(false);
//		 if (null == fileId) {
//			 result.setError("fileId不能为空！");
//			 return result;
//		 }
//		 if (null == selectContent) {
//			 result.setError("对应关系不能为空！");
//			 return result;
//		 }
//
//		 String filePath = sysUploadfileService.getPhysicsFile(fileId);
//	 	 if (StringUtils.isBlank(filePath)) {
//		 	 result.setError("文件不存在！");
//		 	 return result;
//	 	 }
//
//	 	 //根据 excel中业务类型 查询 字段信息
//	 	 List<Map<String, String>> fieldList = getFieldsByType(bstypeId, sheetName, filePath, areaId);
//	 	 Map<String,String> linkedHashMap = new LinkedHashMap<String,String>();
//	 	 if(bstypeId==0){
//	 		linkedHashMap.put("业务类型", SpsTaskServiceImpl.SYS_BS_TYPE);
//	 	 }
//	 	 if(null!=fieldList && fieldList.size()>0){
//	 		 for(Map<String,String> curMap:fieldList){
//	 			linkedHashMap.put(curMap.get("name"), curMap.get("code"));
//	 		 }
//	 	 }
//
//		 Map<String, String> colNameMap = new HashMap<String, String>();
//		 String selectArr[] = selectContent.split(",");
//			 if(null != selectArr) {
//				 for (int i = 0; i < selectArr.length; i++) {
//					 String itemStr = selectArr[i];
//					 String[] itemArr = itemStr.split("=");
//					 if (null != itemArr  && itemArr.length>1 ) {
//						 colNameMap.put(itemArr[0], itemArr[1]);
//					 }
//			 }
//		 }
//		Map<String,Map<String, String>>  sheet_orm = new HashMap<String,Map<String, String>>();
//		sheet_orm.put(sheetName, colNameMap);
//	    Map<Integer, Map<String, String>> resObj = null;
//		try {
//			resObj = ImportExcelUtil.importExcel(filePath, linkedHashMap, sheet_orm);
//		} catch (Exception e1) {
//			e1.printStackTrace();
//			throw new BusinessException("读取excel异常！");
//		}
//
//		//paraMap
//		Map<String,Object> paraMap = new HashMap<String,Object>();
//		//查询 字典信息，供校验使用
//	    List<Map<String, String>> dictList = sysDictitemService.findDictItemsByAreaId(areaId);
//	 	if(null!= dictList && dictList.size()>0){
//	 		 Map<String,String> dictMap = new HashMap<String,String>();
//	 		 Map<String,Map<String,String>> dictItemMap = new HashMap<String,Map<String,String>>();
//	 		 //生成dictMap
//	 		 for(int i=0;i<dictList.size();i++){
//	 			 Map<String,String> tmpMap = dictList.get(i);
//	 			 if(!dictMap.containsKey(tmpMap.get("dictcode"))){
//	 				dictMap.put(tmpMap.get("dictcode"), tmpMap.get("dictname"));
//	 			 }
//	 		 }
//	 		 //遍历dictMap,生成dictItemMap
//	 		 for(Map.Entry<String, String> entry:dictMap.entrySet()){
//	 			 String dictcode = entry.getKey();
//	 			 Map<String,String> itemMap = new HashMap<String,String>();
//	 			 for(int i=0;i<dictList.size();i++){
//	 				Map<String,String> tMap = dictList.get(i);
//	 				if(null!=dictcode && dictcode.equals(tMap.get("dictcode"))){
//	 					itemMap.put(tMap.get("itemname"), tMap.get("itemcode"));
//	 				}
//	 			 }
//	 			dictItemMap.put(dictcode, itemMap);
//	 		 }
//	 		//字段的 字典列表
//	 		paraMap.put("dictMap", dictMap);
//	 		paraMap.put("dictItemMap", dictItemMap);
//	 	 }
//		 //查询 业务类型
//	 	 Map<String, String> bsTypeMap  = getBsType(areaId,paraMap);
//	 	 paraMap.put("bsTypeMap", bsTypeMap);//TODO:校验
//		 paraMap.put("fieldList", fieldList);
//		 paraMap.put("isNeedDelField", bstypeId==0?"1":"0");//是否 去除多余字段
//		 paraMap.put("areaId", areaId);//城市
//
//		 if(2 == areaId){//北京 新参保时 需上传zip文件
//			 if(0==bstypeId || 2==bstypeId){// 混合业务类型 查询 企业信息，以支持 新参保,上传zip文件
//				 String excelPath = filePath.substring(0, filePath.length()-4);
//				 List<File>  fileLists = FileUtil.getSubFiles(new File(excelPath));
//				 //查询企业邮编
//				 CmCorp cmCorp = new CmCorp();
//				 cmCorp.setCpId(cpId);
//				 CmCorp empObj = cmCorpService.findOneByCorpId(cmCorp);
//				 if(null!=empObj && StringUtils.isNotBlank(empObj.getCpZipcode())){//企业邮编
//					 paraMap.put("residentzip", empObj.getCpZipcode());
//				 }else{
//					 paraMap.put("residentzip", "000000");
//				 }
//				 paraMap.put("excelPath", excelPath);
//				 paraMap.put("fileLists", fileLists);
//			 }
//		 }
//
//		 //构建 存放数据的list
//		 List<Map<String,String>> dataList = new ArrayList<Map<String,String>>();
//		 List<Map<String,String>> errorDataList = new ArrayList<Map<String,String>>();
//		 boolean isError = false;
//		 if(null!=resObj && !resObj.isEmpty()){
//			 for(Map.Entry<Integer,Map<String,String>> entry: resObj.entrySet()){
//				 Integer r = entry.getKey();
//				 Map<String,String> curMap = entry.getValue();
//				 //校验信息
//				 String error = "";
//				 if(bstypeId == 0){//混合业务类型
//					 String bsTypeName = curMap.get(SpsTaskServiceImpl.SYS_BS_TYPE);
//					 if(bsTypeMap.containsKey(bsTypeName)){
//						 Integer tempBsType = Integer.valueOf(bsTypeMap.get(bsTypeName));
//						 //处理curMap
//						 curMap.put(SpsTaskServiceImpl.SYS_BS_TYPE, tempBsType+"");
//						 TaskDataParseInterface dataParseObj = TaskDataParseFactory.getInstance(tempBsType);
//			        	 error += dataParseObj.checkBeforeImport(curMap, paraMap);
//					 }else{
//						 error += "业务类型错误";
//					 }
//				 }else{//单个业务类型
//					 TaskDataParseInterface dataParseObj = TaskDataParseFactory.getInstance(bstypeId);
//					 //处理curMap
//					 curMap.put(SpsTaskServiceImpl.SYS_BS_TYPE, bstypeId+"");
//		        	 error += dataParseObj.checkBeforeImport(curMap, paraMap);
//				 }
//				 //添加到 错误list
//	        	 if(!"".equals(error)){
//	        		 curMap.put("row", r.toString());
//	        		 curMap.put("error", error);
//	        		 errorDataList.add(curMap);
//	        		 isError = true;
//	        	 }
//	        	 dataList.add(curMap);
//			 }
//		 }
//
//		 if(isError){
//        	result.setDataValue("errorDataList", errorDataList);
//        	result.setDataValue("errorNum", errorDataList.size());
//        	return result;
//         }
//
//		 if(null == dataList || dataList.size()<1){
//			 result.setDataValue("errorDataList", errorDataList);
//	         	result.setDataValue("errorNum", errorDataList.size());
//	         	return result;
//		 }
//
//		 //查询 待办、失败的 任务单，有则 更新，没有新增
//		 SpsTask tempVo = new SpsTask();
//		 tempVo.setAreaId(areaId);
//		 tempVo.setDr(0);
//		 tempVo.setCpId(cpId);
//		 tempVo.setSpId(spId);
//		 if(0==bstypeId){
//			 tempVo.setBstypeId(null);
//		 }else{
//			 tempVo.setBstypeId(bstypeId);
//		 }
//		 List<SpsTask> existsTaskList = spsTaskDao.findTo_Deal_SPS_TASK(tempVo);
//		 Map<String,SpsTask> existsTaskMap = new HashMap<String,SpsTask>();
//
//		 if(null!=existsTaskList && existsTaskList.size()>0){
//			 for(SpsTask taskVo :existsTaskList){
//				 existsTaskMap.put(taskVo.getName()+"_"+taskVo.getIdentityCode()+"_"+taskVo.getBstypeId(), taskVo);
//			 }
//		 }
//
//		//数据校验通过后 开始导入系统
// 		Date date = new Date();
// 		for(int i=0;i<dataList.size();i++){
// 			//遍历
// 			Map<String,String> curMap = dataList.get(i);
// 			Integer tempBsType = Integer.valueOf(curMap.get(SpsTaskServiceImpl.SYS_BS_TYPE));
//			TaskDataParseInterface dataParseObj = TaskDataParseFactory.getInstance(tempBsType);
//			dataParseObj.delBeforeSave(curMap,paraMap);
// 			SpsTask curVo = null;
// 			//判断 是否存在 待处理，有则 更新
// 			if(existsTaskMap.containsKey(curMap.get("name")+"_"+curMap.get("identity_code")+"_"+tempBsType)){
// 				curVo = existsTaskMap.get(curMap.get("name")+"_"+curMap.get("identity_code")+"_"+tempBsType);
// 				curVo.setType("TODO");//待处理
// 				curMap.remove(SpsTaskServiceImpl.SYS_BS_TYPE);//清除  SYS_BS_TYPE
// 	 			String json = JSON.toJSONString(curMap);
// 	 			curVo.setJson(json);
// 				curVo.setModifyDt(date);
// 				curVo.setModifyBy(curUser.getUserId());
// 				spsTaskDao.update(curVo);
// 			}else{//新增
// 				curVo = new SpsTask();
// 				curVo.setBstypeId(tempBsType);
// 				curVo.setCpId(cpId);
// 	 			curVo.setSpId(curUser.getOrgId());//spid
// 	 			curVo.setAreaId(areaId);
// 	 			curVo.setDr(0);
// 	 			curVo.setType("TODO");//待处理
// 	 			curVo.setCreateBy(curUser.getUserId());
// 	 			curVo.setCreateDt(date);
// 	 			curVo.setName(curMap.get("name"));
// 	 			curVo.setMobile(curMap.get("mobile"));
// 	 			curVo.setIdentityCode(curMap.get("identity_code"));
// 	 			curMap.remove(SpsTaskServiceImpl.SYS_BS_TYPE);//清除  SYS_BS_TYPE
// 	 			String json = JSON.toJSONString(curMap);
// 	 			curVo.setJson(json);
// 	 			spsTaskDao.save(curVo);
// 			}
//
// 		}
//	 	result.setSuccess(true);
//	 	result.setDataValue("successNum", dataList.size());
//	 	return result;
//
//
//	}


    /**
     * 通用 导入excel
     *
     * @author wuzhe
     * <p>
     * 2016年10月12日
     */
    public Result importExcel(ContextInfo info, Integer fileId, Integer cpId, Integer spId, String selectContent, Integer bstypeId, String sheetName, Integer areaId, Integer curSchemeId, String month, Result result) throws Exception {
        result.setSuccess(false);
        List<Map<String, String>> errorDataList = new ArrayList<Map<String, String>>(0);
        result.setDataValue("errorDataList", errorDataList);
        if (null == fileId) {
            result.setError("fileId不能为空！");
            return result;
        }
        if (null == selectContent) {
            result.setError("对应关系不能为空！");
            return result;
        }
        String filePath = sysUploadfileService.getPhysicsFile(fileId);
        if (StringUtils.isBlank(filePath)) {
            result.setError("文件不存在！");
            return result;
        }

        BdBstype bstype = bdBstypeService.findByPK(bstypeId);
        SpsScheme scheme = new SpsScheme();
        List<Map<String, Object>> ratioList = new ArrayList<>(0);
        if (bstypeId == 2 || bstypeId == 3 || bstypeId == 10) {
            scheme = imGetSpsScheme(curSchemeId, scheme);
            if (scheme == null) {
                result.setError("方案未找到！");
                return result;
            }
            ratioList = imGetRatiosList(month, scheme, bstype);
            if (ratioList.size() == 0) {
                result.setError("方案险种未配置！");
                return result;
            }
        }
        //根据 excel中业务类型 查询 字段信息
        Map<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        List<Map<String, String>> fieldList = imGetFieldsMaps(bstypeId, sheetName, areaId, filePath, linkedHashMap);

        Map<String, String> colNameMap = new HashMap<String, String>();
        imSetColNameMap(selectContent, colNameMap);

        Map<String, Map<String, String>> sheet_orm = new HashMap<String, Map<String, String>>();
        sheet_orm.put(sheetName, colNameMap);
        Map<Integer, Map<String, String>> resObj = null;
        try {
            resObj = ImportExcelUtil.importExcel(filePath, linkedHashMap, sheet_orm);
        } catch (Exception e1) {
            e1.printStackTrace();
            throw new BusinessException("读取excel异常！");
        }

        //paraMap
        Map<String, Object> paraMap = new HashMap<String, Object>();
        //查询 业务类型
        Map<String, String> bsTypeMap = getBsType(areaId, paraMap);
        imSetParaMap(cpId, bstypeId, areaId, filePath, fieldList, paraMap, bsTypeMap, bstype, ratioList, month);

        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
        boolean isError = false;
        isError = imCheckBeforeError(bstypeId, errorDataList, resObj, paraMap, bsTypeMap, dataList, isError, info);

        if (isError) {
            result.setDataValue("errorNum", errorDataList.size());
            return result;
        }
        if (null == dataList || dataList.size() < 1) {
            result.setDataValue("errorNum", errorDataList.size());
            result.setError("无导入数据");
            return result;
        }

        //查询 待办、失败的 任务单，有则返回失败
        Map<String, SpsTask> existsTaskMap = new HashMap<String, SpsTask>();
        imGetExitsTask(cpId, spId, bstypeId, areaId, existsTaskMap);

        //数据校验通过后 开始导入系统
        Date date = new Date();
        for (int i = 0; i < dataList.size(); i++) {
            //遍历
            Map<String, String> curMap = dataList.get(i);
            Integer tempBsType = Integer.valueOf(curMap.get(SpsTaskServiceImpl.SYS_BS_TYPE));
            TaskDataParseInterface dataParseObj = TaskDataParseFactory.getInstance(tempBsType);
            dataParseObj.delBeforeSave(info, curMap, paraMap);
            //判断 是否存在 待处理，有则 更新
            if (existsTaskMap.containsKey(curMap.get("name") + "_" + curMap.get("identity_code") + "_" + tempBsType)) {
                curMap.put("row", curMap.get("row"));
                curMap.put("error", "有待处理任务单");
                errorDataList.add(curMap);
                result.setDataValue("errorDataList", errorDataList);
                result.setDataValue("errorNum", errorDataList.size());
                isError = true;
            } else {//新增
                if (!isError) {
                    imSaveTask(info, cpId, areaId, curSchemeId, month, result, bstype, ratioList, date, curMap, tempBsType, paraMap, dataParseObj);
                    if (!result.isSuccess()) {
                        result.setSuccess(false);
                        curMap.put("row", curMap.get("row"));
                        curMap.put("error", result.getError());
                        errorDataList.add(curMap);
                        result.setDataValue("errorNum", errorDataList.size());
                        throw new BusinessException("保存任务单错误" + result.getError());
                    }
                }
            }

        }
        if (isError) {
            result.setSuccess(false);
        } else {
            result.setSuccess(true);
            result.setDataValue("successNum", dataList.size());
        }
        return result;
    }

    private void imSaveTask(ContextInfo cti, Integer cpId,
                            Integer areaId,
                            Integer curSchemeId,
                            String month,
                            Result result,
                            BdBstype bstype,
                            List<Map<String, Object>> ratioList,
                            Date date,
                            Map<String, String> curMap,
                            Integer tempBsType,
                            Map<String, Object> paraMap,
                            TaskDataParseInterface dataParseObj) {
        SpsTask curVo = new SpsTask();
        curVo.setBstypeId(tempBsType);
        curVo.setCpId(cpId);
        curVo.setSpId(cti.getOrgId());//spid
        curVo.setAreaId(areaId);
        curVo.setDr(0);
        curVo.setCreateBy(cti.getUserId());
        curVo.setCreateDt(date);
        curVo.setName(curMap.get("name"));
        curVo.setMobile(curMap.get("mobile"));
        curVo.setIdentityCode(curMap.get("identity_code"));
        curVo.setType("TODO");
        CmEmployee vo = new CmEmployee();
        vo.setIdentityCode(curMap.get("identity_code"));
        vo.setName(curMap.get("name"));
        vo.setDr(0);
        List<CmEmployee> emps = cmEmployeeService.findAll(vo);
        CmEmployee employee = null;
        if (emps.size() > 0) {
            employee = emps.get(0);
            curVo.setEmpId(employee.getEmpId());
        } else {
            curVo.setEmpId(-1);
        }
        if (tempBsType == 1 || tempBsType == 2 || tempBsType == 3 || tempBsType == 10) {
            curVo.setSchemeId(curSchemeId);
        } else {
            if (employee != null) {
                curVo.setSchemeId(employee.getSchemeId());
            }
        }
        curVo.setBeginDate(month);
        String json = dataParseObj.parseJson(cti, curMap, paraMap);
        curVo.setJson(json);
        taskAspectService.saveTask(cti, curVo, null, result);
    }

    private void imSetParaMap(Integer cpId,
                              Integer bstypeId,
                              Integer areaId,
                              String filePath,
                              List<Map<String, String>> fieldList,
                              Map<String, Object> paraMap,
                              Map<String, String> bsTypeMap,
                              BdBstype bstype,
                              List<Map<String, Object>> ratioList,
                              String month) {
        paraMap.put("month", month);
        paraMap.put("bstype", bstype);
        paraMap.put("ratioList", ratioList);
        paraMap.put("bsTypeMap", bsTypeMap);
        paraMap.put("fieldList", fieldList);
        paraMap.put("isNeedDelField", bstypeId == 0 ? "1" : "0");//是否 去除多余字段
        paraMap.put("areaId", areaId);//城市
        //查询地区字典信息，供校验使用
        List<Map<String, String>> dictList = sysDictitemService.findDictItemsByAreaId(areaId);
        if (null != dictList && dictList.size() > 0) {
            Map<String, String> dictMap = new HashMap<String, String>();
            Map<String, Map<String, String>> dictItemMap = new HashMap<String, Map<String, String>>();
            //生成dictMap
            for (int i = 0; i < dictList.size(); i++) {
                Map<String, String> tmpMap = dictList.get(i);
                if (!dictMap.containsKey(tmpMap.get("dictcode"))) {
                    dictMap.put(tmpMap.get("dictcode"), tmpMap.get("dictname"));
                }
            }
            //遍历dictMap,生成dictItemMap
            for (Map.Entry<String, String> entry : dictMap.entrySet()) {
                String dictcode = entry.getKey();
                Map<String, String> itemMap = new HashMap<String, String>();
                for (int i = 0; i < dictList.size(); i++) {
                    Map<String, String> tMap = dictList.get(i);
                    if (null != dictcode && dictcode.equals(tMap.get("dictcode"))) {
                        itemMap.put(tMap.get("itemname"), tMap.get("itemcode"));
                    }
                }
                dictItemMap.put(dictcode, itemMap);
            }
            //字段的 字典列表
            paraMap.put("dictMap", dictMap);
            paraMap.put("dictItemMap", dictItemMap);
        }

        if (2 == areaId) {//北京 新参保时 需上传zip文件
            if (0 == bstypeId || 2 == bstypeId) {// 混合业务类型 查询 企业信息，以支持 新参保,上传zip文件
                String excelPath = filePath.substring(0, filePath.length() - 4);
                List<File> fileLists = FileUtil.getSubFiles(new File(excelPath));
//				 //查询企业邮编
//				 CmCorp cmCorp = new CmCorp();
//				 cmCorp.setCpId(cpId);
//				 CmCorp empObj = cmCorpService.findOneByCorpId(cmCorp);
//				 if(null!=empObj && StringUtils.isNotBlank(empObj.getCpZipcode())){//企业邮编
//					 paraMap.put("residentzip", empObj.getCpZipcode());
//				 }else{
//					 paraMap.put("residentzip", "000000");
//				 }
                paraMap.put("excelPath", excelPath);
                paraMap.put("fileLists", fileLists);
            }
        }
        // 数据字典集合增加证件类型89
        Map<String, Map<String, String>> dictItemMap = (Map<String, Map<String, String>>) paraMap.get("dictItemMap");
        Map<String, String> IDTypeMap = new HashMap<String, String>(0);
        SysDictitem queryDict = new SysDictitem();
        queryDict.setDictionary(89);//IDType
        List<SysDictitem> dictitems = sysDictitemService.findAll(queryDict);
        if (CollectionUtils.isNotEmpty(dictitems)) {
            for (SysDictitem dictitem : dictitems) {
                IDTypeMap.put(dictitem.getCode(), dictitem.getName());
            }
        }
        dictItemMap.put("IDType", IDTypeMap);
        Map<String, SpsFixedpointhospital> hosiptalNameMap = new HashMap<String, SpsFixedpointhospital>(0);
        Map<String, SpsFixedpointhospital> hosiptalFullNameMap = new HashMap<String, SpsFixedpointhospital>(0);
        SpsFixedpointhospital vo = new SpsFixedpointhospital();
        vo.setAreaId(2);
        List<SpsFixedpointhospital> list = spsFixedpointhospitalService.findAll(vo);
        if (null != list && list.size() > 0) {
            for (SpsFixedpointhospital obj : list) {
                hosiptalFullNameMap.put(obj.getFullName(), obj);
                hosiptalNameMap.put(obj.getAbbreviation(), obj);
            }
        }
        paraMap.put("hosiptalNames", hosiptalNameMap);
        paraMap.put("hosiptalFullNames", hosiptalFullNameMap);
    }

    private SpsScheme imGetSpsScheme(Integer curSchemeId, SpsScheme scheme) {
        scheme.setSchemeId(curSchemeId);
        scheme = spsSchemeService.findByPK(scheme);
        SpsScheme fbScheme = null;
        if ("DEPUTE".equals(scheme.getSchemeType())) {
            fbScheme = new SpsScheme();
            fbScheme.setParentId(scheme.getSchemeId());
            List<SpsScheme> list = spsSchemeService.findAll(fbScheme);
            if (CollectionUtils.isNotEmpty(list)) {
                scheme = list.get(0);
            } else {
                scheme = null;
            }
        }
        return scheme;
    }

    private List<Map<String, Object>> imGetRatiosList(String month, SpsScheme scheme, BdBstype bstype) {
        Integer accountId = 0;
        if (bstype.getInsuranceFundType().equals("INSURANCE")) {
            accountId = scheme.getInsuranceAccountId();
        }
        if (bstype.getInsuranceFundType().equals("FUND")) {
            accountId = scheme.getFundAccountId();
        }
        Map<String, Object> ratioParaMap = new HashMap<String, Object>();
        ratioParaMap.put("account_id", accountId);
        ratioParaMap.put("ins_fund_type", bstype.getInsuranceFundType());
        ratioParaMap.put("begin_date", month.replace("-", ""));
        return spsAccountRatioService.findRatiosByAccountId(ratioParaMap);
    }


    private List<Map<String, String>> imGetFieldsMaps(Integer bstypeId, String sheetName, Integer areaId, String filePath, Map<String, String> linkedHashMap) throws Exception {
        List<Map<String, String>> fieldList = getFieldsByType(bstypeId, sheetName, filePath, areaId);
        if (bstypeId == 0) {
            linkedHashMap.put("业务类型", SpsTaskServiceImpl.SYS_BS_TYPE);
        }
        if (null != fieldList && fieldList.size() > 0) {
            Iterator<Map<String, String>> it = fieldList.iterator();
            while (it.hasNext()) {
                Map<String, String> curMap = it.next();
                if ("SHOW".equals(curMap.get("model_is_hidden"))) {
                    linkedHashMap.put(curMap.get("name"), curMap.get("code"));
                } else {
                    it.remove();
                }
            }

        }
        return fieldList;
    }

    private void imSetColNameMap(String selectContent, Map<String, String> colNameMap) {
        String selectArr[] = selectContent.split(",");
        if (null != selectArr) {
            for (int i = 0; i < selectArr.length; i++) {
                String itemStr = selectArr[i];
                String[] itemArr = itemStr.split("=");
                if (null != itemArr && itemArr.length > 1) {
                    colNameMap.put(itemArr[0], itemArr[1]);
                }
            }
        }
    }

    private boolean imCheckBeforeError(Integer bstypeId, List<Map<String, String>> errorDataList, Map<Integer
            , Map<String, String>> resObj, Map<String, Object> paraMap, Map<String, String> bsTypeMap
            , List<Map<String, String>> dataList, boolean isError, ContextInfo ci) {
        if (null != resObj && !resObj.isEmpty()) {
            for (Map.Entry<Integer, Map<String, String>> entry : resObj.entrySet()) {
                Integer r = entry.getKey();
                Map<String, String> curMap = entry.getValue();
                curMap.put("row", r.toString());
                //校验信息
                String error = "";
                if (bstypeId == 0) {//混合业务类型
                    String bsTypeName = curMap.get(SpsTaskServiceImpl.SYS_BS_TYPE);
                    if (bsTypeMap.containsKey(bsTypeName)) {
                        Integer tempBsType = Integer.valueOf(bsTypeMap.get(bsTypeName));
                        //处理curMap
                        curMap.put(SpsTaskServiceImpl.SYS_BS_TYPE, tempBsType + "");
                        TaskDataParseInterface dataParseObj = TaskDataParseFactory.getInstance(tempBsType);
                        error += dataParseObj.checkBeforeImport(ci, curMap, paraMap);
                    } else {
                        error += "业务类型错误";
                    }
                } else {//单个业务类型
                    TaskDataParseInterface dataParseObj = TaskDataParseFactory.getInstance(bstypeId);
                    //处理curMap
                    curMap.put(SpsTaskServiceImpl.SYS_BS_TYPE, bstypeId + "");
                    error += dataParseObj.checkBeforeImport(ci, curMap, paraMap);
                }
                //添加到 错误list
                if (!"".equals(error)) {
                    curMap.put("row", r.toString());
                    curMap.put("error", error);
                    errorDataList.add(curMap);
                    isError = true;
                } else {
                    dataList.add(curMap);
                }
            }
        }
        return isError;
    }

    private void imGetExitsTask(Integer cpId, Integer spId, Integer bstypeId, Integer areaId, Map<String, SpsTask> existsTaskMap) {
        SpsTask tempVo = new SpsTask();
        tempVo.setAreaId(areaId);
        tempVo.setDr(0);
        tempVo.setCpId(cpId);
        tempVo.setSpId(spId);
        if (0 == bstypeId) {
            tempVo.setBstypeId(null);
        } else {
            tempVo.setBstypeId(bstypeId);
        }
        List<SpsTask> existsTaskList = spsTaskDao.findTo_Deal_SPS_TASK(tempVo);
        if (null != existsTaskList && existsTaskList.size() > 0) {
            for (SpsTask taskVo : existsTaskList) {
                existsTaskMap.put(taskVo.getName() + "_" + taskVo.getIdentityCode() + "_" + taskVo.getBstypeId(), taskVo);
            }
        }
    }


    /**
     * 根据 excel中业务类型 查询 字段信息
     *
     * @author lifq
     * <p>
     * 2016年5月24日  上午11:19:06
     */
    private List<Map<String, String>> getFieldsByType(Integer bstypeId, String sheetName, String filePath, Integer areaId)
            throws Exception {
        List<Map<String, String>> fieldList = null;
        if (bstypeId == 0) {//0: 支持所有业务类型
            List<String> bsTypeList = ImportExcelUtil.readExcelBsType(filePath, sheetName);
            if (null == bsTypeList) {
                return null;
            }
            String bsTypeNameStr = com.xfs.common.util.StringUtils.convertInStr(bsTypeList);
            BdBstypeareafield fieldVO = new BdBstypeareafield();
            fieldVO.setAreaId(areaId);//2:北京
            fieldVO.setBstypeId(bstypeId);
            fieldVO.setRequiredEq(bsTypeNameStr);//
            fieldList = bdBstypeareafieldService.findBsTypeFieldByName(fieldVO);
        } else {
            BdBstypeareafield fieldVO = new BdBstypeareafield();
            fieldVO.setAreaId(areaId);//2:北京
            fieldVO.setBstypeId(bstypeId);
            //查询 字段信息
            fieldList = bdBstypeareafieldService.findBsTypeField(fieldVO);
        }
        return fieldList;
    }

    /**
     * 读取excel
     *
     * @author lifq
     * <p>
     * 2016年5月24日  上午9:32:12
     */
    public Result readTemplateRealtion(Integer curBsTypeId, Integer fileId, String sheetName, Integer areaId) throws Exception {
        Result result = Result.createResult().setSuccess(false);
        if (null == fileId) {
            result.setError("fileId不能为空！");
            return result;
        }
        String filePath = sysUploadfileService.getPhysicsFile(fileId);
        if (StringUtils.isBlank(filePath)) {
            result.setError("文件不存在！");
            return result;
        }
        //根据 业务类型 查询 字段信息
        List<Map<String, String>> mapList = getFieldsByType(curBsTypeId, sheetName, filePath, areaId);
        Map<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        if (curBsTypeId == 0) {
            linkedHashMap.put("业务类型", SpsTaskServiceImpl.SYS_BS_TYPE);
        }
        if (null != mapList && mapList.size() > 0) {
            for (Map<String, String> curMap : mapList) {
                if ("SHOW".equals(curMap.get("model_is_hidden"))) {
                    linkedHashMap.put(curMap.get("name"), curMap.get("code"));
                }
            }
        }
        List<Map<String, String>> sysfields = new ArrayList<Map<String, String>>();
        sysfields.add(linkedHashMap);

        Map<String, Map<String, Map<String, Object>>> resObj = ImportExcelUtil.readExcelTitle(filePath, sysfields, null, null, null, sheetName);
        result.setDataValue("sheetData", resObj);
//	 	 result.setDataValue("isShowSelectSheet", resObj.size()<=1?"0":"1");先去掉这个，如果只有一个sheet时  显示默认选中

        result.setSuccess(true);
        return result;
    }

    /**
     * 读取excel 标题
     *
     * @author lifq
     * <p>
     * 2016年5月24日  上午9:36:12
     */
    public Result readTemplateTitle(Integer curBsTypeId, Integer fileId, Integer areaId) throws Exception {
        Result result = Result.createResult().setSuccess(false);
        if (null == fileId) {
            result.setError("fileId不能为空！");
            return result;
        }
        String filePath = sysUploadfileService.getPhysicsFile(fileId);
        if (StringUtils.isBlank(filePath)) {
            result.setError("文件不存在！");
            return result;
        }
        //查询 城市下 业务类型
        Map<String, String> bsTypeMap = getBsType(areaId, null);
        //查询 excel中 sheet页 级 标题行
        Map<String, List<String>> sheetData = ImportExcelUtil.readExcelTitle(filePath);
        //判断前台是否 显示 选择 业务类型
        Map<String, String> sheetShowBsType = new HashMap<String, String>();
        if (null != sheetData && !sheetData.isEmpty()) {
            for (Map.Entry<String, List<String>> entry : sheetData.entrySet()) {
                String sheetName = entry.getKey();
                List<String> titleList = entry.getValue();
                if (null == titleList || titleList.isEmpty()) {//标题行为空时 直接跳过
                    continue;
                }
                if (bsTypeMap.containsKey(sheetName)) {
                    sheetShowBsType.put(sheetName, bsTypeMap.get(sheetName).toString());
                } else if (null != titleList && "业务类型".equals(titleList.get(0))) {
                    sheetShowBsType.put(sheetName, "0");//0: 混合业务类型
                } else {
                    sheetShowBsType.put(sheetName, "-1");
                }

            }
        }
        result.setDataValue("sheetData", sheetData);
        result.setDataValue("sheetShowBsType", sheetShowBsType);//前端 判断是否显示 业务类型选项, -1时 需要选择，否则不选
        result.setSuccess(true);
        return result;
    }

    /**
     * 查询 城市下 业务类型
     *
     * @author lifq
     * <p>
     * 2016年5月24日  下午5:34:22
     */
    private Map<String, String> getBsType(Integer areaId, Map<String, Object> paraMap) {
        BdBsareatype areaTypeVO = new BdBsareatype();
        areaTypeVO.setAreaId(areaId);
        //查询 业务类型信息
        List<Map<String, Object>> bsTypeList = bdBsareatypeService.findBstypeByCity(areaTypeVO);
        //放到paraMap
        if (null != paraMap) {
            paraMap.put("bsTypeList", bsTypeList);
        }
        Map<String, String> bsTypeMap = new HashMap<String, String>();
        if (null != bsTypeList && bsTypeList.size() > 0) {
            for (Map<String, Object> curMap : bsTypeList) {
                bsTypeMap.put(null == curMap.get("name") ? "" : curMap.get("name").toString(),
                        null == curMap.get("bstype_id") ? "" : curMap.get("bstype_id").toString());
            }
        }
        return bsTypeMap;
    }

    /**
     * 根据服务商id和区域id获取企业列表、任务汇总信息
     *
     * @param vo
     * @return
     */
    public PageModel findTaskAndCorps(PageInfo pi, SpsTask vo, String searchWord, ContextInfo cti) {
        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = spsTaskDao.findTaskAndCorpCount(vo, searchWord, cti);
        pm.setTotal(total);
        List<Map<String, Object>> datas = spsTaskDao.findTaskAndCorps(vo, searchWord, pageIndex, pageSize, cti);
        pm.setDatas(datas);
        return pm;
    }

    /**
     * 条件搜索任务列表
     *
     * @param bstype
     * @param vo
     * @param searchWord
     * @return
     */
    public PageModel findTasksByCondition(PageInfo pi, BdBstype bstype, SpsTask vo, String searchWord) {
        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = spsTaskDao.findTaskCountByCondition(bstype, vo, searchWord);
        pm.setTotal(total);
        List<Map<String, Object>> datas = spsTaskDao.findTasksByCondition(bstype, vo, searchWord, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;
    }

    /**
     * 根据条件检索任务单
     *
     * @param vo
     * @param searchWord
     * @return
     */
    public PageModel findTasksByCondition(PageInfo pi, SpsTask vo, String searchWord) {
        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        if (StringUtils.isNotBlank(vo.getTypeEq())) {
            //已完成
            if (vo.getTypeEq().equals("1")) {
                vo.setType("'COMPLETED'");
                vo.setJson(null);
            } else if (vo.getTypeEq().equals("2")) {//已关闭
                vo.setType("'ERROR','CLOSED'");
                vo.setJson(null);
            } else if (vo.getTypeEq().equals("3")) {//等待客服处理
                vo.setType("'SUBMIT'");
                vo.setJson(null);
            } else if (vo.getTypeEq().equals("4")) {//等待客服处理
                vo.setType("'TODO'");
                vo.setJson(null);
            }
//			else if(vo.getTypeEq().equals("3")){//增员中
//				vo.setType("TODO");
//				vo.setJson("2,3,22,23,10,15,28");
//			}else if(vo.getTypeEq().equals("4")){//减员中
//				vo.setType("TODO");
//				vo.setJson("4,24,11,16");
//			}
        }
        Integer total = spsTaskDao.findTaskCountByCondition(vo, searchWord);
        pm.setTotal(total);
        List<Map<String, Object>> datas = spsTaskDao.findTasksByCondition(vo, searchWord, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;
    }


    /**
     * 条件搜索所有任务列表
     *
     * @param bstype
     * @param vo
     * @param searchWord
     * @return
     */
    public List<Map<String, Object>> findALLTasksByCondition(BdBstype bstype, SpsTask vo, String searchWord) {
        return spsTaskDao.findALLTasksByCondition(bstype, vo, searchWord);
    }


    @Override
    public HashMap<String, Object> findProgressData(Integer scheme_id, String type) {
        return spsTaskDao.findProgressData(scheme_id, type);
    }


    @Override
    public void startTask(ContextInfo cti, Integer scheme_id, String type, String mobile, Integer spid) {
        //查询进度表里面待执行的任务
        HashMap data = spsTaskDao.findTaskProgress(scheme_id, type);
        //如果没有的话就插入，如果有的话，就更新
        if (data == null || data.isEmpty()) {
            spsTaskDao.insertTaskProgress(cti, scheme_id, type, mobile, spid);
        } else {
            spsTaskDao.updateTaskProgress(cti, scheme_id, type, "TODO");
        }
    }

    @Override
    public void stopTask(ContextInfo cti, Integer scheme_id, String type, String mobile, Integer spid) {
        spsTaskDao.updateTaskProgress(cti, scheme_id, type, "UNDO");

    }

    /**
     * 2016-08 新需求 任务管理
     * 根据任务单ID 查询任务单详情
     */
    @Override
    public HashMap<String, Object> findDetailByTaskId(SpsTask spsTask) {
        SpsTaskHistory sth = new SpsTaskHistory();
        sth.setTaskId(spsTask.getTaskId());
        //任务单里员工的证件类型 字典表里 dictionary=89  identity_type
        List<HashMap<String, Object>> docList = sysDictitemDAO.findEmpDocType();
        //任务单历史记录最后一条
        HashMap<String, Object> historyMap = spsTaskHistoryDao.findHisttoryByTaskId(sth);
        //任务单详情
        HashMap<String, Object> detailMap = spsTaskDao.findDetailByTaskId(spsTask);
        //地区的户口类型
        BusinessField businessField = bdBusinessfieldService.findLivingType(Integer.parseInt(detailMap.get("area_id").toString()));
        Map<String, String> livingTypeMap = new HashedMap();
        if (businessField != null && CollectionUtils.isNotEmpty(businessField.getOptions())) {
            for (SysDictitem dictitem : businessField.getOptions()) {
                livingTypeMap.put(dictitem.getCode(), dictitem.getName());
            }
        }
        if (null != detailMap && null != detailMap.get("insurance_living_type")) {
            detailMap.put("insurance_living_type_name", livingTypeMap.get(detailMap.get("insurance_living_type")));
        }
        HashMap<String, String> docTypeMap = new HashMap<String, String>();
        for (HashMap<String, Object> hashMap : docList) {
            docTypeMap.put(hashMap.get("code").toString(), hashMap.get("name").toString());
        }
        //任务单中存 证件类型名称
        if (null != detailMap.get("identity_type")) {
            detailMap.put("identity_type_name", docTypeMap.get(detailMap.get("identity_type")));
        } else {
            detailMap.put("identity_type_name", "");
        }
        //任务单历史最后一条不为null 把 历史记录中 照片 和 描述 存到任务单详情map中
        if (null != historyMap) {
            detailMap.put("operate_pic", null != historyMap.get("operate_pic") ? historyMap.get("operate_pic") : null);//操作截图ID
            detailMap.put("operate_des", null != historyMap.get("operate_des") ? historyMap.get("operate_des") : null);//操作描述
        }
        return detailMap;
    }

    /**
     * 2016-08 新需求 任务管理
     * 根据任务单ID 执行完成任务单按钮操作
     */
    @Override
    public Result updatetoComplete(ContextInfo cti, SpsTask spsTask, SpsTaskHistory sth) {
        Result result = Result.createResult().setSuccess(false);
        //根据task_id 查询任务单
        spsTask = spsTaskDao.findTaskDetailByTaskId(spsTask);
        if (null != spsTask.getDr() && 0 != spsTask.getDr()) {
            result.setError("完成任务单失败！该任务单已失效！");
            return result;
        }
        spsTask.setType("COMPLETED");//任务单状态----完成
        spsTask.setOnlineStatus("COMPLETED");//任务单状态----完成
        Result results = Result.createResult().setSuccess(false);
        // 社保(2=增员,3=转入、4=减员、29=补缴) 、公积金(10=增加、11=减少、30=补缴)
        if (2 != spsTask.getBstypeId() && 3 != spsTask.getBstypeId() && 4 != spsTask.getBstypeId() && 10 != spsTask.getBstypeId() && 11 != spsTask.getBstypeId() && 29 != spsTask.getBstypeId() && 30 != spsTask.getBstypeId()) {
            spsTask.setDr(0);
            Integer ups = this.update(cti, spsTask);
            if (0 < ups) {
                results.setSuccess(true).setError("更新成功！");
            }
        } else {
            spsTask.setDr(0);
            taskAspectService.saveTask(cti, spsTask, null, results);
        }
        if (results.isSuccess()) {
            SysMessage sysMessage = this.sendMessage(spsTask, cti);
            RedisUtil.push(COMMON_XFS_MESSAGE_QUEUE, sysMessage);
            result.setSuccess(true);
        } else {
            result.setError(results.getError());
            return result;
        }
        return result;
    }

    /**
     * 2016-08 新需求 任务管理
     * 根据任务单ID 执行终止任务单按钮操作
     */
    @Override
    public Result updatetoColsed(ContextInfo cti, SpsTask spsTask, SpsTaskHistory sth) {
        Result result = Result.createResult().setSuccess(false);
        //根据task_id 查询任务单
        spsTask = spsTaskDao.findTaskDetailByTaskId(spsTask);
        if (null != spsTask.getDr() && 0 != spsTask.getDr()) {
            result.setError("完成任务单失败！该任务单已失效！");
            return result;
        }
        spsTask.setType("CLOSED");//任务单状态----终止
        Result results = Result.createResult().setSuccess(false);
        // 社保(2=增员,3=转入、4=减员、29=补缴) 、公积金(10=增加、11=减少、30=补缴)
        if (2 != spsTask.getBstypeId() && 3 != spsTask.getBstypeId() && 4 != spsTask.getBstypeId() && 10 != spsTask.getBstypeId() && 11 != spsTask.getBstypeId() && 29 != spsTask.getBstypeId() && 30 != spsTask.getBstypeId()) {
            spsTask.setDr(0);
            Integer ups = this.update(cti, spsTask);
            if (0 < ups) {
                results.setSuccess(true).setError("更新成功！");
            }
        } else {
            taskAspectService.saveTask(cti, spsTask, null, results);
        }
        if (results.isSuccess()) {
            SysMessage sysMessage = this.sendMessage(spsTask, cti);
            RedisUtil.push(COMMON_XFS_MESSAGE_QUEUE, sysMessage);
            result.setSuccess(true);
        } else {
            result.setError(results.getError());
            return result;
        }
        return result;
    }

    /**
     * 任务单执行完成 终止
     * 发送消息通知
     */
    public SysMessage sendMessage(SpsTask spsTask, ContextInfo cti) {
        SysMessage sysMessage = new SysMessage();
        // 数据权限
        String authority = sysFunctionDataService.getDataAuthorityByUser(cti.getUserId(), cti.getUserType(), Constant.DATATYPE);
        spsTask.setAuthority(authority);
        List<Map<String, Object>> taskList = spsTaskDao.freeFindAllList(spsTask, 0, 0);
        SysUser modifyUser = sysUserDao.findUserByUserId(cti.getUserId());
        for (Map<String, Object> taskMap : taskList) {
            String insFunType = String.valueOf(taskMap.get("insurance_fund_type"));
            String taskType = String.valueOf(spsTask.getType());
            sysMessage.setTodoUser(Integer.parseInt(String.valueOf(taskMap.get("create_by"))));//任务单创建人ID
            sysMessage.setTodoUserType(String.valueOf(taskMap.get("user_type")));//任务单创建人用户类型
            if (null != taskMap.get("org_id") && !"null".equalsIgnoreCase(String.valueOf(taskMap.get("org_id")))) {
                sysMessage.setTodoOrg(Integer.parseInt(String.valueOf(taskMap.get("org_id"))));//代办人orgid
            }
            sysMessage.setType("TASK");
            String insFunTypeName = "";
            String taskTypeName = "";
            if (StringUtils.equals("INSURANCE", insFunType)) {
                insFunTypeName = "社保";
            }
            if (StringUtils.equals("FUND", insFunType)) {
                insFunTypeName = "公积金";
            }
            if (StringUtils.equals("COMPLETED", taskType)) {
                taskTypeName = "任务单已完成！";
            }
            if (StringUtils.equals("CLOSED", taskType)) {
                taskTypeName = "任务单已终止！";
            }
            String title = String.valueOf(taskMap.get("task_no")) + insFunTypeName + "-" + String.valueOf(taskMap.get("bstype_name")) + String.valueOf(taskMap.get("emp_name") + "-" + taskTypeName);
            sysMessage.setTitle(title);//处理标题后添加到 message里
            sysMessage.setDataId(spsTask.getTaskId());//数据ID
            sysMessage.setSendUser(modifyUser.getUserId());
            sysMessage.setSendUserType(modifyUser.getUserType());
            sysMessage.setSendOrg(modifyUser.getOrgId());
            sysMessage.setCreateBy(modifyUser.getUserId());
        }
        return sysMessage;
    }

    @Override
    public PageModel freeFindAllListByEmp(PageInfo pi, SpsTask spsTask) {
        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = spsTaskDao.freeFindAllListCountByEmp(spsTask);
        pm.setTotal(total);
        List<Map<String, Object>> datas = spsTaskDao.freeFindAllListByEmp(spsTask, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;
    }

    @Override
    public Map<String, Object> findSpsTaskByEmp(SpsTask spsTask, String insuranceFondType) {
        return spsTaskDao.findSpsTaskByEmp(spsTask, insuranceFondType);

    }


    @Override
    public List<Map<String, Object>> findSpsTaskListByEmp(SpsTask spsTask, String insuranceFondType) {
        return spsTaskDao.findSpsTaskListByEmp(spsTask, insuranceFondType);

    }


    @Override
    public void downLoadTemplet(Integer areaId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        final String fileRootPath = request.getServletContext().getRealPath("/") + File.separator + "tmpfile"
                + File.separator;
        File dir = new File(fileRootPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        BsArea ba = new BsArea();
        ba.setAreaId(areaId);
        BsArea bsArea = bsAreaService.findbyPK(ba);
        String cityName = null;
        if (bsArea != null) {
            cityName = bsArea.getName();
        } else {
            BsArea ca = new BsArea();
            if (request.getAttribute("areaCode") != null) {
                ca.setCode(request.getAttribute("areaCode").toString());
                bsArea = bsAreaService.findAll(ca).get(0);
                if (bsArea != null) {
                    areaId = bsArea.getAreaId();
                    cityName = bsArea.getName();
                }
            }
        }

        ExportExcelUtil<Book> obj = new ExportExcelUtil<Book>();
        //获取日期的字符串
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String ex_date = dateFormat.format(new Date());
        String fileUUID = cityName;
        //获取数据
        List<BdBstype> bdBstypes = bdBstypeService.findBdBstypeListByAreaId(areaId);
        Map<String, List<BdBusinessfield>> mapFund = new HashMap<>();
        Map<String, List<BdBusinessfield>> mapInsurance = new HashMap<>();
        List<BdBstype> bdBstypeListFund = new ArrayList<>();
        List<BdBstype> bdBstypeListInsurance = new ArrayList<>();
        for (BdBstype bstype : bdBstypes) {
            String InsuranceFundType = bstype.getInsuranceFundType();
            if (InsuranceFundType.equals("INSURANCE")) {
                List<BdBusinessfield> bdBusinessfields = bdBusinessfieldService.bdBusinessFieldList(areaId, bstype.getBstypeId());
                bdBstypeListInsurance.add(bstype);
                mapInsurance.put(bstype.getName(), bdBusinessfields);
            } else if (InsuranceFundType.equals("FUND")) {
                List<BdBusinessfield> bdBusinessfields = bdBusinessfieldService.bdBusinessFieldList(areaId, bstype.getBstypeId());
                bdBstypeListFund.add(bstype);
                mapFund.put(bstype.getName(), bdBusinessfields);
            }
        }
        List<File> files = new ArrayList<>();
        File fileFund = null;
        File fileInsurance = null;
        if (bdBstypeListFund.size() > 0) {
            OutputStream outFund = new FileOutputStream(fileRootPath + File.separator + fileUUID + "公积金" + ".xlsx");
            obj.exportTaskBotExcelFromMySql(bdBstypeListFund, areaId, outFund, mapFund, sysDictitemDAO);
            outFund.close();
            String filePathFund = fileRootPath + File.separator + fileUUID + "公积金" + ".xlsx";
            fileFund = new File(filePathFund);
            files.add(fileFund);
        }
        if (bdBstypeListInsurance.size() > 0) {
            OutputStream outInsurance = new FileOutputStream(fileRootPath + File.separator + fileUUID + "社保" + ".xlsx");
            obj.exportTaskBotExcelFromMySql(bdBstypeListInsurance, areaId, outInsurance, mapInsurance, sysDictitemDAO);
            outInsurance.close();
            String filePathInsurance = fileRootPath + File.separator + fileUUID + "社保" + ".xlsx";
            fileInsurance = new File(filePathInsurance);
            files.add(fileInsurance);
        }
        File[] fileArr = files.toArray(new File[files.size()]);
        String zipPath = ZipUtil.zip(fileArr, fileRootPath + File.separator + fileUUID + ".zip");
//		File zipFile = new File(zipPath);
        response.setContentType("application/octet-stream");
        request.setCharacterEncoding("UTF-8");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        long fileLength = new File(zipPath).length();
        String userAgent = request.getHeader("User-Agent");
        //针对IE或者以IE为内核的浏览器：
        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
            fileUUID = java.net.URLEncoder.encode(fileUUID, "UTF-8");
        }
        response.setHeader("Content-disposition", "attachment; filename="
                + new String((fileUUID + ".zip").getBytes("utf-8"), "ISO8859-1"));
        response.setHeader("Content-Length", String.valueOf(fileLength));

        bis = new BufferedInputStream(new FileInputStream(zipPath));
        bos = new BufferedOutputStream(response.getOutputStream());
        byte[] buff = new byte[2048];
        int bytesRead;
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
            bos.write(buff, 0, bytesRead);
        }
        bis.close();
        bos.close();
    }

    /**
     * 通过多类型与状态查询任务单
     *
     * @param empId
     * @param bstypeId
     * @param state
     * @return
     */
    public List<SpsTask> findTaskByBsTypeAndState(Integer empId, Integer[] bstypeId, String state, String[] states) {
        return spsTaskDao.findTaskByBsTypeAndState(empId, bstypeId, state, states);
    }

    @Override
    public List<SpsStateDto> getAllStateBy(Integer bsTypeId, Integer areaId, String orgType) {
        return spsTaskDao.getAllStateBy(bsTypeId, areaId, orgType);
    }

    /**
     * 创建任务单
     *
     * @param spsTask
     * @throws BusinessException
     */
    public void savaTask(ContextInfo cti, SpsTask spsTask, Map<String, Object> businessParams, Result result) throws BusinessException {
        if (null != spsTask.getTaskId()) {
            if (spsTaskDao.update(cti, spsTask) > 0) {
                result.setSuccess(true).setMsg("更新成功");
            } else {
                result.setSuccess(false).setError("提交失败！");
            }
        } else {
            if (spsTaskDao.insert(cti, spsTask) > 0) {
                result.setSuccess(true).setMsg("提交成功");
            } else {
                result.setSuccess(false).setError("提交失败！");
            }
        }
    }

    /**
     * 2016-08
     * 任务管理详情
     * 任务单历史记录增加
     */
    public Result addSpsTaskHistory(ContextInfo cti, SpsTask spsTask) {
        Result result = Result.createResult().setSuccess(false);
        //spstask表更新成功 写入历史记录表一条
        SpsTaskHistory spsTaskHistory = new SpsTaskHistory();
        BeanUtils.copyProperties(spsTask, spsTaskHistory);
        spsTaskHistory.setOperate("将任务单状态处理为" + spsTask.getType());
        spsTaskHistory.setDr(0);
        Integer ins = spsTaskHistoryDao.insert(cti, spsTaskHistory);
        if (0 < ins) {
            result.setSuccess(true);
        } else {
            result.setError("历史记录增加失败！");
        }
        return result;
    }

    /**
     * 查询 年度 服务人数
     *
     * @author lifq
     * <p>
     * 2016年8月26日  上午11:33:18
     */
    public List<Map<String, Object>> queryYearPersonNum(HashMap<String, Object> parameterMap) {
        return spsTaskDao.queryYearPersonNum(parameterMap);
    }

    /**
     * 查询总数
     *
     * @param spsTask
     * @return
     */
    public Integer freeFindCount(SpsTask spsTask) {
        return spsTaskDao.countFreeFindOld(spsTask);
    }

    //cs

    /**
     * 查询社保公积金保险情况
     *
     * @return
     */
    public List<EmplInsuranceDto> getInsuranceInfo(ContextInfo cti, Integer empId) {
        List<EmplInsuranceDto> taskList = new ArrayList<>();
        CmCorp cmCorp = new CmCorp();
        cmCorp.setCpId(cti.getOrgId());
        cmCorp = cmCorpDao.findByPK(cmCorp);
        CmEmployee employee = new CmEmployee();
        employee.setEmpId(empId);
        employee = cmEmployeeDao.findByPK(employee);
        if (employee == null) {
            return null;
        }
        //参保地
        String insAddress = null;
        BsArea areaQuery = new BsArea();
        areaQuery.setAreaId(cmCorp.getCpAddressArea());

        BsArea area = bsAreaDao.findByPK(areaQuery);
        if (area != null) {
            insAddress = area.getName();
            areaQuery.setAreaId(area.getParent());
            area = bsAreaDao.findByPK(areaQuery);
            if (area != null) {
                insAddress = area.getName() + "-" + insAddress;
                areaQuery.setAreaId(area.getParent());
                area = bsAreaDao.findByPK(areaQuery);
                if (area != null) {
                    insAddress = area.getName() + "-" + insAddress;
                }
            }
        }
        //社保
        EmplInsuranceDto insurance = new EmplInsuranceDto();
        insurance.setInsuranceName("社保");
        String bstype = "2,3,4,22,23,24";
        List<SpsTask> list = spsTaskDao.findTaskInBsType(empId, bstype, null, new String[]{"SUBMIT", "TODO", "COMPLETED"}, 0, 1);
        if (!CollectionUtils.isEmpty(list)) {
            SpsTask ins = list.get(0);
            if (ins.getType().equals("TODO")) {
                if (ins.getBstypeId() == 2 || ins.getBstypeId() == 3 || ins.getBstypeId() == 22 || ins.getBstypeId() == 23) {
                    insurance.setType("增员中");
                } else if (ins.getBstypeId() == 4 || ins.getBstypeId() == 24) {
                    insurance.setType("减员中");
                }
            } else if (ins.getType().equals("COMPLETED")) {
                if (ins.getBstypeId() == 2 || ins.getBstypeId() == 3 || ins.getBstypeId() == 22 || ins.getBstypeId() == 23) {
                    insurance.setType("在缴中");
                } else if (ins.getBstypeId() == 4 || ins.getBstypeId() == 24) {
                    insurance.setType("未缴纳");
                }
            }
            insurance.setCardinalNum(employee.getInsuranceSalary());
            insurance.setStartTime(DateUtil.getDateStr(ins.getCreateDt(), "yyyy-MM"));
            insurance.setCityName(insAddress);
            insurance.setTaskId(ins.getTaskId());
        } else {
//			if("ON".equals(employee.getInsuranceState())){
//				insurance.setType("在缴中");
//			}else if("OFF".equals(employee.getInsuranceState())){
//				insurance.setType("未缴纳");
//			}else if("INCREASING".equals(employee.getInsuranceState())){
//				insurance.setType("增员中");
//			}else if("DECREASING".equals(employee.getInsuranceState())){
//				insurance.setType("减员中");
//			}
            if ("INCREASING".equals(employee.getInsuranceState())) {
                insurance.setType("增员中");
            } else if ("UNDEAL".equals(employee.getInsuranceState())) {
                insurance.setType("暂不处理");
            } else if ("ON".equals(employee.getInsuranceState())) {
                insurance.setType("在缴中");
            } else if ("DECREASING".equals(employee.getInsuranceState())) {
                insurance.setType("减员中");
            } else if ("DECREASED".equals(employee.getInsuranceState())) {
                insurance.setType("已减员");
            } else if ("OFF".equals(employee.getInsuranceState())) {
                insurance.setType("未缴纳");
            }
        }

        //公积金
        EmplInsuranceDto fund = new EmplInsuranceDto();
        fund.setInsuranceName("公积金");
        bstype = "10,11,15,16,28";
        List<SpsTask> fundList = spsTaskDao.findTaskInBsType(empId, bstype, null, new String[]{"SUBMIT", "TODO", "COMPLETED"}, 0, 1);
        if (!CollectionUtils.isEmpty(fundList)) {
            SpsTask ins = fundList.get(0);
            if (ins.getType().equals("TODO")) {
                if (ins.getBstypeId() == 10 || ins.getBstypeId() == 15 || ins.getBstypeId() == 28) {
                    fund.setType("增员中");
                } else if (ins.getBstypeId() == 11 || ins.getBstypeId() == 16) {
                    fund.setType("减员中");
                }
            } else if (ins.getType().equals("COMPLETED")) {
                if (ins.getBstypeId() == 10 || ins.getBstypeId() == 15 || ins.getBstypeId() == 28) {
                    fund.setType("在缴中");
                } else if (ins.getBstypeId() == 11 || ins.getBstypeId() == 16) {
                    fund.setType("未缴纳");
                }
            }
            fund.setCardinalNum(employee.getFundSalary());
            fund.setStartTime(DateUtil.getDateStr(ins.getCreateDt(), "yyyy-MM"));
            fund.setCityName(insAddress);
            fund.setTaskId(ins.getTaskId());
        } else {
//			if("ON".equals(employee.getFundState())){
//				fund.setType("在缴中");
//			}else if("OFF".equals(employee.getFundState())){
//				fund.setType("未缴纳");
//			}else if("INCREASING".equals(employee.getInsuranceState())){
//				fund.setType("增员中");
//			}else if("DECREASING".equals(employee.getInsuranceState())){
//				fund.setType("减员中");
//			}
            if ("INCREASING".equals(employee.getFundState())) {
                fund.setType("增员中");
            } else if ("UNDEAL".equals(employee.getFundState())) {
                fund.setType("暂不处理");
            } else if ("ON".equals(employee.getFundState())) {
                fund.setType("在缴中");
            } else if ("DECREASING".equals(employee.getFundState())) {
                fund.setType("减员中");
            } else if ("DECREASED".equals(employee.getFundState())) {
                fund.setType("已减员");
            } else if ("OFF".equals(employee.getFundState())) {
                fund.setType("未缴纳");
            }
        }
        taskList.add(insurance);
        taskList.add(fund);
        return taskList;
    }

    @Override
    public SpsTaskDto findTaskByType(SpsTask vo) throws BusinessException {
        return spsTaskDao.findTaskByType(vo);
    }

    /**
     * 更新usbport
     *
     * @param schemeId 方案名称
     * @param usbport  更新后的usbport
     * @return
     */
    public Integer updateUsbport(ContextInfo cti, String schemeId, String usbport, String type) {
        return spsTaskDao.updateUsbport(cti, schemeId, usbport, type);
    }

    @Override
    public Result upDealPl(ContextInfo cti, String reduceJsons, String flag) {
        Result result = Result.createResult().setSuccess(false);
        String reduceJson = "";
        String failReson = "";
        boolean completeflag = !StringUtils.isBlank(flag) && StringUtils.equals(flag, "comlpeted");
        boolean failflag = !StringUtils.isBlank(flag) && StringUtils.equals(flag, "closed");
        //批量按钮处理任务单
        List<Map<String, String>> taskIds = new ArrayList<Map<String, String>>();
        if (!StringUtils.isBlank(reduceJsons)) {
            if (failflag) {
                reduceJson = StringUtils.split(reduceJsons, "@")[0];
                failReson = StringUtils.split(reduceJsons, "@")[1];
            } else {
                reduceJson = reduceJsons;
            }
            TypeReference<List<Map<String, String>>> ref = new TypeReference<List<Map<String, String>>>() {
            };
            taskIds = JSON.parseObject(reduceJson, ref);
        }
        if (CollectionUtils.isNotEmpty(taskIds)) {
            for (Map<String, String> map : taskIds) {
                SpsTask spsTaskJson = new SpsTask();
                SpsTaskHistory sth = new SpsTaskHistory();
                spsTaskJson.setTaskId(Integer.parseInt(map.get("task_id").toString()));
                spsTaskJson.setBstypeId(Integer.parseInt(map.get("bstype_id").toString()));
                spsTaskJson.setSpId(Integer.parseInt(map.get("sp_id").toString()));
                //执行完成
                if (completeflag) {
                    updatetoComplete(cti, spsTaskJson, sth);
                }
                //执行终止
                if (failflag) {
                    sth.setOperateDes(failReson);
                    updatetoColsed(cti, spsTaskJson, sth);
                }
            }
            result.setSuccess(true);
        }
        return result;
    }

    @Override
    public TaskVo findInsuranceFundByMonth(ContextInfo cti,TaskDto vo) {
    	vo.setAuthority(cti.getAuthority());
        return spsTaskDao.findInsuranceFundByMonth(vo);
    }

    @Override
    public List<TaskMapVo> findInsuranceFundMapByCity(TaskDto vo) {
        return spsTaskDao.findInsuranceFundMapByCity(vo);
    }

    @Override
    public List<ItemWarnVo> findItemWarnByCpId(ContextInfo cti,ItemWarnDto vo) {
    	vo.setAuthority(cti.getAuthority());
        // 业务提醒
        List<ItemWarnVo> endDatelist = spsTaskDao.findItemWarnEndDateByCpId(vo);
//        List<ItemWarnVo> returnList = new ArrayList<ItemWarnVo>();
//        Collections.sort(returnList, new Comparator<ItemWarnVo>() {
//            @Override
//            public int compare(ItemWarnVo o1, ItemWarnVo o2) {
//                return o1.getEndDate().compareTo(o2.getEndDate());
//            }
//        });
        return endDatelist;
    }

    @Override
    public List<FlowChartVo> findEmployeeFlowChartByCityId(FlowChartDto vo, String year) {
        List<FlowChartVo> returnVo = new ArrayList<FlowChartVo>();
        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM");
            SimpleDateFormat yyyyFormat = new SimpleDateFormat("yyyy");

            if (com.xfs.common.util.StringUtils.isBlank(year)){
                year = cal.get(Calendar.YEAR) + "";
            }
            String upYear = year + "-01";
            String preMonth = year + "-12";
            vo.setBeginPeriod(preMonth);
            vo.setEndPeriod(upYear);

            // 根据企业ID和城市ID获取十四个月的人员数据
            List<CmEmployeeInsurance> list = spsTaskDao.findEmployeeFlowChartByCityId(vo);

            Date d1 = dft.parse(upYear);// 起始日期
            Date d2 = dft.parse(preMonth);// 结束日期
            cal.setTime(d1);
            List<String> monthList = new ArrayList<String>();// 当前月份往前推一年
            for(int i = 0; i < 12 ; i++) {//判断是否到结束日期
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
                String str = sdf.format(cal.getTime());
                monthList.add(str);
                cal.add(Calendar.MONTH, 1);//进行当前日期月份加1
            }

            FlowChartVo flowChartVo = null;
            for (int i = 0; i < monthList.size(); i++) {
                int inNumber = 0;// 在缴人数
                int addNumber = 0;// 增员人数
                int subNumber = 0;// 减员人数
                flowChartVo = new FlowChartVo();
                Date nDate = dft.parse(monthList.get(i));// 当前要统计的日期
                for (CmEmployeeInsurance cmEmployeeInsurance : list) {
                    Date bDate = dft.parse(cmEmployeeInsurance.getBeginPeriod());// 起始日期
                    Date eDate = dft.parse(cmEmployeeInsurance.getEndPeriod());// 结束日期
                    if (bDate.equals(nDate)) {// 如果起始日期和当前要统计的日期相等
                        addNumber++;
                    }
                    if (eDate.equals(nDate)) {// 如果结束日期和当前要统计的日期相等
                        subNumber++;
                    }
                    if (nDate.before(eDate) && bDate.before(nDate)) {// 当前日期在结束日期之前并且起始日期在当前要统计的日期之前
                        inNumber++;
                    }
                }
                flowChartVo.setAddNumber(addNumber);
                flowChartVo.setInNumber(inNumber);
                flowChartVo.setSubNumber(subNumber);
                flowChartVo.setMonth(monthList.get(i));
                returnVo.add(flowChartVo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnVo;
    }

    /**
     * 员工流动分析列表
     * @param cti
     * @param pi
     * @param areaId
     * @param beginPeriod
     * @return
     */
    public PageModel findEmployeeFlowChartByCityIdList(ContextInfo cti, PageInfo pi, Integer areaId, String beginPeriod){
        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = spsTaskDao.findEmployeeFlowChartByCityIdListCount(cti, areaId, beginPeriod);
        pm.setTotal(total);
        pm.setDatas(spsTaskDao.findEmployeeFlowChartByCityIdList(cti, areaId, beginPeriod, pageIndex, pageSize));
        return pm;
    }

    @Override
    public PageModel findHandlePersonnelList(PageInfo pi, HandlePersonnelDto vo) {
        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Map<String,Object> totalMap = spsTaskDao.findHandlePersonnelListCount(vo);
        pm.setTotal(Integer.parseInt(String.valueOf(totalMap.get("c_value"))));
        List<HandlePersonnelListVo> datas = spsTaskDao.findHandlePersonnelList(vo, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;
    }

    @Override
    public List<OtherBusinessVo> findOtherBusinessStateByMonth(TaskDto vo) {
        // 获取其他业务办理状态
        List<Map<String, Object>> otherBusinessStateMapList = spsTaskDao.findOtherBusinessStateByMonth(vo);
        List<OtherBusinessVo> returnVoList = new ArrayList<OtherBusinessVo>();
        OtherBusinessVo otherBusinessVo = null;
        String name = "";
        int todoNumber = 0;// 代办人数
        int completedNumber = 0;// 成功人数
        int errorNumber = 0;// 异常人数
        for (Map<String, Object> otherBusinessStateMap : otherBusinessStateMapList) {
            if ("".equals(name)) {
                name = otherBusinessStateMap.get("name").toString();
            }
            if (!name.equals(otherBusinessStateMap.get("name").toString())) {
                otherBusinessVo = new OtherBusinessVo();
                otherBusinessVo.setName(name);
                otherBusinessVo.setCompletedNumber(completedNumber);
                otherBusinessVo.setErrorNumber(errorNumber);
                otherBusinessVo.setTodoNumber(todoNumber);
                returnVoList.add(otherBusinessVo);
                name = otherBusinessStateMap.get("name").toString();
                todoNumber = 0;
                completedNumber = 0;
                errorNumber = 0;
            }
            if ("办理异常".equals(otherBusinessStateMap.get("stateFiledName").toString())) {
                errorNumber = Integer.parseInt(otherBusinessStateMap.get("countNumber").toString());
            } else if ("办理成功".equals(otherBusinessStateMap.get("stateFiledName").toString())) {
                completedNumber = Integer.parseInt(otherBusinessStateMap.get("countNumber").toString());
            } else if ("待信息审核".equals(otherBusinessStateMap.get("stateFiledName").toString())) {
                todoNumber = Integer.parseInt(otherBusinessStateMap.get("countNumber").toString());
            }
        }
        return returnVoList;
    }

    /**
     * 修改任务单根据taskbotID
     *
     * @param spsTask
     * @return : int
     * @createDate : 2017-03-20 10:34
     * @author : konglc@xinfushe.com
     * @version : v1.0
     * @updateDate : 2017-03-20 10:34
     * @updateAuthor :
     */
    @Override
    public int updateTaskByOutId(SpsTask spsTask) {

        return spsTaskDao.updateTaskByOutId(spsTask);
    }

    @Override
    public SpsTask getSpsTaskByCondition(SpsTask spsTask) {
        return spsTaskDao.getSpsTaskByCondition(spsTask);
    }

    /**
     * 社保公积金申报进度
     *
     * @param vo
     * @return
     * @createDate : 2017年3月31日 下午2:37:18
     * @author : wangchao
     * @version : v1.0
     * @updateDate : 2017年3月31日 下午2:37:18
     * @updateAuthor :
     */
    @Override
    public TaskVo findInsuranceFundProgress(TaskDto vo) {
        return spsTaskDao.findInsuranceFundProgress(vo);
    }

    /**
     *  根据人员删除任务单
     *  @param   cpId, empIds
     *	@return 			: int
     *  @createDate  	: 2017-05-31 14:50
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-05-31 14:50
     *  @updateAuthor  :
     */
    @Override
    public int delTaskByEmpIds(Integer cpId,String empIds){
       return spsTaskDao.delTaskByEmpIds(cpId,empIds);
    }

    @Override
    public int delTaskByEmpIds(Integer cpId,String empIds,Integer bstypeId){
        return spsTaskDao.delTaskByEmpIds(cpId,empIds,bstypeId);
    }

    @Override
    public String getUseHostptialByEmpId(Integer empId) {
        return spsTaskDao.getUseHostptialByEmpId(empId);
    }

    public List<Map<String, Object>> findTaskByEmpId(SpsTask vo){ return spsTaskDao.findTaskByEmpId(vo); }

    @Override
    public void insertByBatch(ContextInfo cti,List<SpsTask> spsTasks){
        spsTaskDao.insertByBatch(cti,spsTasks);
    }

    @Override
    public void updateByBatch(ContextInfo cti,SpsTask spsTask){
        spsTaskDao.updateByBatch(cti,spsTask);
    }

    @Override
    public void updateByBatchByTaskNos(ContextInfo cti,SpsTask spsTask){
        spsTaskDao.updateByBatchByTaskNos(cti,spsTask);
    }


    @Override
    public List<SpsTask> queryByBatch(ContextInfo cti,String[] taskIds, List<ApplyMessage> loginList,Integer stateFiledId){
        return spsTaskDao.queryByBatch(cti,taskIds,loginList,stateFiledId);
    }

	@Override
	public Integer findBusinessNumber(SpsTask spsTask) {
		return spsTaskDao.findBusinessNumber(spsTask);
	}
	/**
	 * 查询 已完成的调基任务单
	 *
	 * @author lifq
	 *
	 * 2017年8月14日  下午4:41:46
	 */
	public List<SpsTask> queryCompletedAdjustTask(SpsTask vo){
		return spsTaskDao.queryCompletedAdjustTask(vo);
	}

    public List<SpsTask> findByEmpId(Integer empId, Integer bstypeId, String type){
        SpsTask vo = new SpsTask();
        vo.setEmpId(empId);
        vo.setBstypeId(bstypeId);
        vo.setType(type);
	    return spsTaskDao.findByEmpId(vo);
    }

    /**
     * 补全任务单历史
     * @param vo
     */
    public void repairTaskReport(SpsTask vo){
        spsTaskDao.repairTaskReport(vo);
    }

    /**
     * 任务单趋势图
     * @param cti
     * @param areaId
     * @param months
     * @return
     */
    public List<Map<String,Object>> queryTaskTrend(ContextInfo cti,Integer areaId,List<String> months){

        return spsTaskDao.queryTaskTrend(cti, areaId, months);
    }

    /**
     * 任务单分析列表
     * @param cti
     * @param areaId
     * @return
     */
    public PageModel queryTaskTrendList(ContextInfo cti,PageInfo pi,Integer areaId){
        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = spsTaskDao.queryTaskTrendListCount(cti, areaId);
        pm.setTotal(total);
        List<Map<String, Object>> datas = spsTaskDao.queryTaskTrendList(cti, areaId, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;
    }

    /**
     * 获取任务中心数据
     *  @param pi
     *  @param vo
     *  @return 
     *  @createDate  	: 2017年9月19日 下午5:46:02
     *  @author         	: wangchao
     *  @version        	: v1.0
     *  @updateDate    	: 2017年9月19日 下午5:46:02
     *  @updateAuthor  	:
     */
    @Override
    public PageModel findTaskCenterList(ContextInfo cti,PageInfo pi, TaskCenterDto vo) {
        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = spsTaskDao.findTaskCenterListCount(vo);
        pm.setTotal(total);
        
        // 获取退出任务中心时间
        Date outTaskCenterTime = findOutTaskCenterTime(cti, vo.getType());
        vo.setOutTaskCenterTime(outTaskCenterTime);
        
        List<Map<String, Object>> datas = spsTaskDao.findTaskCenterList(vo, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;
    }
	
	/**
	 * 获取服务商任务数量
	 * @param cti
	 *  @param param
	 *  @return 
	 *  @createDate  	: 2017年9月20日 上午10:50:23
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年9月20日 上午10:50:23
	 *  @updateAuthor  	:
	 */
	@Override
	public Map<String, Object> findTaskNumber(ContextInfo cti,TaskCenterDto param) {
		Map<String, Object> taskNumber = new HashMap<>();
        // 获取等待处理任务数量
        Integer waitNumber = this.findTaskCenterListCount(cti,0);
        taskNumber.put("waitNumber", waitNumber);
        // 获取处理中任务数量
        Integer todoNumber = this.findTaskCenterListCount(cti,1);
        taskNumber.put("todoNumber", todoNumber);
        // 获取处理完成任务数量
        Integer completedNumber = this.findTaskCenterListCount(cti,2);
        taskNumber.put("completedNumber", completedNumber);
        param.setOutTaskCenterTime(null);
        // 获取网申任务数量
        param.setTaskType("SELFSERVICE");
        Integer selfserviceNumber = spsTaskDao.findTaskCenterListCount(param);
        taskNumber.put("selfserviceNumber", selfserviceNumber);
        // 获取服务商任务数量
        param.setTaskType("SERVICE");
        Integer serviceNumber = spsTaskDao.findTaskCenterListCount(param);
        taskNumber.put("serviceNumber", serviceNumber);
        // 获取入职任务数量
        param.setTaskType("ENTRY");
        Integer entryNumber = spsTaskDao.findTaskCenterListCount(param);
        taskNumber.put("entryNumber", entryNumber);
		return taskNumber;
	}
	
	/**
	 * 菜单是否显示新任务提醒
	 *  @param cti
	 *  @return 
	 *  @createDate  	: 2017年11月2日 下午1:58:19
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年11月2日 下午1:58:19
	 *  @updateAuthor  	:
	 */
	@Override
	public boolean findNewTaskremind(ContextInfo cti) {
		// 获取等待处理任务数量
        Integer waitNumber = this.findTaskCenterListCount(cti,0);
        if(waitNumber > 0){
        	return true;
        }
        // 获取处理中任务数量
        Integer todoNumber = this.findTaskCenterListCount(cti,1);
        if(todoNumber > 0){
        	return true;
        }
        // 获取处理完成任务数量
        Integer completedNumber = this.findTaskCenterListCount(cti,2);
        if(completedNumber > 0){
        	return true;
        }
		return false;
	}
	
	/**
	 * 统计任务数量
	 *  @param cti
	 *  @param type
	 *  @return 
	 *	@return 			: Integer 
	 *  @createDate  	: 2017年11月2日 下午1:55:39
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年11月2日 下午1:55:39
	 *  @updateAuthor  :
	 */
	public Integer findTaskCenterListCount(ContextInfo cti,int type) {
		// 获取等待处理任务数量
	    TaskCenterDto vo = new TaskCenterDto();
	    Date outTaskCenterTime = findOutTaskCenterTime(cti, type);
	    vo.setOutTaskCenterTime(outTaskCenterTime);
	    vo.setAuthority(cti.getAuthority());
	    vo.setTaskType("ALL");
	    vo.setType(type);
	    vo.setCpId(cti.getOrgId());
	    return spsTaskDao.findTaskCenterListCount(vo);
	}

	/**
	 * 获取离开任务中心时间
	 *  @param cti
	 *  @param type
	 *  @return 
	 *	@return 			: Date 
	 *  @createDate  	: 2017年11月2日 下午2:02:27
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年11月2日 下午2:02:27
	 *  @updateAuthor  :
	 */
	public Date findOutTaskCenterTime(ContextInfo cti, int type) {
		// 获取退出任务中心时间
	    BsSysStateReport obj = new BsSysStateReport();
	    obj.setAttributeName("OUTTASKCENTERTIME_"+type);
	    obj.setAttributeValue("1");
	    obj.setOwner(cti.getUserId() + "");
	    obj.setOwnerType(cti.getUserType());
	    obj.setCpId(cti.getOrgId());
	    List<BsSysStateReport> bsSysStateReports = bsSysStateReportService.findAll(obj);
	    Date outTaskCenterTime = null;
		if(bsSysStateReports.size() > 0){
			if(null != bsSysStateReports.get(0).getModifyDt()){
				outTaskCenterTime = bsSysStateReports.get(0).getModifyDt();
			}else{
				outTaskCenterTime = bsSysStateReports.get(0).getCreateDt();
			}
		}
		return outTaskCenterTime;
	}
	/**
	 * 任务中心--获取人员详细信息
	 *  @param cti
	 *  @param taskId
	 *  @return 
	 *  @createDate  	: 2017年9月20日 下午3:35:02
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年9月20日 下午3:35:02
	 *  @updateAuthor  	:
	 */
	@Override
	public EmpDetailVo findEmpDetail(ContextInfo cti, Integer taskId) {
		EmpDetailVo empDetailVo = new EmpDetailVo();
		// 获取任务单
		SpsTask spsTask = new SpsTask();
		spsTask.setTaskId(taskId);
		spsTask = spsTaskDao.findByPK(spsTask);
		if(null != spsTask){
			// 获取JSON参数
	    	JSONObject maps = JSON.parseObject(spsTask.getJson());
			empDetailVo.setTaskId(spsTask.getTaskId());
			empDetailVo.setEmpId(spsTask.getEmpId());
			// 设置业务类型
			empDetailVo.setTaskType(this.findTaskType(spsTask));
			empDetailVo.setType(spsTask.getType());
			empDetailVo.setErrmsg(spsTask.getErrmsg());
			empDetailVo.setFiledId(spsTask.getStateFiledId());
			empDetailVo.setFiledName(spsTask.getStateFiledName());
			empDetailVo.setUserName(spsTask.getName());
			empDetailVo.setCode(spsTask.getIdentityCode());
			empDetailVo.setCtiyId(spsTask.getAreaId());
			// 设置城市
			BsArea bsArea = new BsArea();
			bsArea.setAreaId(spsTask.getAreaId());
			empDetailVo.setCtiyName(bsAreaService.findByPK(bsArea).getName());
			
			// 任务单业务类型
	        Integer[] bstypeId = new Integer[1];
	    	if(null != maps.getInteger("bstypeId")
	    			&& BsType.INCREMENT_INSUR_ENTRY.getCode().equals(spsTask.getBstypeId())){
	    		bstypeId[0]=maps.getInteger("bstypeId");
	    	}else{
	    		bstypeId[0]=spsTask.getBstypeId();
	    	}
			// 设置保险类型和网申业务
			if(null != spsTask.getBstypeId()){
				BdBstype bdBstype = bdBstypeService.findByPK(bstypeId[0]);
				if("INSURANCE".equals(bdBstype.getInsuranceFundType())){
					empDetailVo.setInsFundType("社保");
				}else if("FUND".equals(bdBstype.getInsuranceFundType())){
					empDetailVo.setInsFundType("公积金");
				}
				empDetailVo.setBstypeName(bdBstype.getName());
			}else{
				empDetailVo.setInsFundType("--");
				empDetailVo.setBstypeName("--");
			}
			// 获取员工方案
			if(BsType.INCREMENT_INSUR_ENTRY.getCode().equals(spsTask.getBstypeId())){
				SpsScheme spsScheme = new SpsScheme();
	        	spsScheme.setSchemeId(maps.getInteger("schemeId"));
	        	spsScheme.setDr(0);
	        	spsScheme = spsSchemeService.findByPK(spsScheme);
	        	if(null != spsScheme){
	        		empDetailVo.setSchemeId(spsScheme.getSchemeId());
	        		empDetailVo.setSchemeName(spsScheme.getName());
		  	        if(-999 != spsScheme.getSpId()){
		  	        	empDetailVo.setSchemeType(1);
		  	        }
	        	}
	        	if(null!=maps.getInteger("isFund")){
	        		empDetailVo.setIsFund(maps.getInteger("isFund"));
	        	}else{
	        		empDetailVo.setIsFund(1);
	        	}
	        	if(null!=maps.getInteger("isIns")){
	        		empDetailVo.setIsIns(maps.getInteger("isIns"));
	        	}else {
	        		empDetailVo.setIsIns(1);
				}
			}else{
				if(null != spsTask.getEmpId()){
					CmEmployee cmEmployee = new CmEmployee();
			        cmEmployee.setCpId(cti.getOrgId());
			        cmEmployee.setName(empDetailVo.getUserName());
			        cmEmployee.setIdentityCode(empDetailVo.getCode());
			        cmEmployee = cmEmployeeService.findCpEmplByNameAndIdentityCode(cmEmployee);
			        // 设置默认方案、方案类型、是否缴纳公积金和是否缴纳社保
			        if (null != cmEmployee) {
			        	SpsScheme spsScheme = new SpsScheme();
			        	spsScheme.setSchemeId(cmEmployee.getSchemeId());
			        	spsScheme.setDr(0);
			        	spsScheme = spsSchemeService.findByPK(spsScheme);
			        	if(null != spsScheme){
			        		empDetailVo.setSchemeId(spsScheme.getSchemeId());
			        		empDetailVo.setSchemeName(spsScheme.getName());
				  	        if(-999 != spsScheme.getSpId()){
				  	        	empDetailVo.setSchemeType(1);
				  	        }
			        	}
			        	if(StringUtils.isNotBlank(cmEmployee.getInsuranceState()) 
			        			&& ("INCREASING".equals(cmEmployee.getInsuranceState()) || "ON".equals(cmEmployee.getInsuranceState()) 
			        					|| "DECREASING".equals(cmEmployee.getInsuranceState()))){
			        		empDetailVo.setIsIns(1);
			        	}
			        	if(StringUtils.isNotBlank(cmEmployee.getFundState()) 
			        			&& ("INCREASING".equals(cmEmployee.getFundState()) || "ON".equals(cmEmployee.getFundState()) 
			        					|| "DECREASING".equals(cmEmployee.getFundState()))){
			        		empDetailVo.setIsFund(1);
			        	}
					}
				}
			}
	        // 社保默认户口性质和城市下所有户口性质
	        Map<String,Object> paraMap = new HashMap<>();
	        paraMap.put("area_id", empDetailVo.getCtiyId());
	        List<Map<String, Object>> liveTypeMap = sysDictitemService.findLiveTypeByAreaId(paraMap);
	        List<SysDictitem> liveType = new ArrayList<>();
	        for (int i = 0; i < liveTypeMap.size(); i++) {
	        	SysDictitem sysDictitem = new SysDictitem();
	        	sysDictitem.setAreaId(Integer.parseInt(String.valueOf(liveTypeMap.get(i).get("area_id"))));
	        	sysDictitem.setCode(String.valueOf(liveTypeMap.get(i).get("code")));
	        	sysDictitem.setName(String.valueOf(liveTypeMap.get(i).get("name")));
	        	liveType.add(sysDictitem);
			}
	        empDetailVo.setLiveType(liveType);
	        // 根据企业ID和城市获取方案  按自服务倒序
	        SpsScheme spsScheme = new SpsScheme();
	        spsScheme.setAuthority(cti.getAuthority());
	        spsScheme.setCpId(cti.getOrgId());
	        spsScheme.setAreaId(empDetailVo.getCtiyId());
	        List<SpsScheme> spsSchemes = spsSchemeService.findSchemeByCityIdAndCpIdOderBySpId(spsScheme);
	        List<SpsScheme> selfSchemeList = new ArrayList<>();
	        List<SpsScheme> entrustSchemeList = new ArrayList<>();
	        for (int i = 0; i < spsSchemes.size(); i++) {
				if(-999 == spsSchemes.get(i).getSpId()){
					selfSchemeList.add(spsSchemes.get(i));
				}else{
					entrustSchemeList.add(spsSchemes.get(i));
				}
			}
	        empDetailVo.setSelfScheme(selfSchemeList);
	        empDetailVo.setEntrustScheme(entrustSchemeList);
			
			List<BusinessField> bdBusinessfieldList = bdBusinessfieldDao.queryBusinessFields(spsTask.getAreaId(), bstypeId);
//			if (bstypeId[0] == BsType.MODIFY_HOSPITAL.code()) {
//	            String df = maps.get("hospital").toString();
//	            JSONArray hospital = JSONObject.parseArray(df);
//	            maps.remove("hospital");
//	            setDefaultValue(bdBusinessfieldList, maps);
//	            for (int i = 0; i < hospital.size(); i++) {
//            		for (BusinessField businessField : bdBusinessfieldList) {
//                		if (businessField.getCode().equals("hospital" + (i + 1))) {
//                			com.alibaba.fastjson.JSONObject hospitalJson = com.alibaba.fastjson.JSONObject.parseObject(hospital.get(i).toString());
//                            businessField.setDefaultValue(hospitalJson.getString("name"));
//	                    }
//            		}
//	            }
//	        } else {
	             setDefaultValue(bdBusinessfieldList, maps,spsTask.getBstypeId());
	        //}
			empDetailVo.setBdBusinessfieldList(bdBusinessfieldList);
        	// 公积金社保缴纳日期
			if(null!=maps.getString("beginDate")){
				empDetailVo.setDefaultInsFundDate(maps.getString("beginDate"));
			}
			// 消息ID
        	if(null!=maps.get("message_id")){
        		empDetailVo.setMessageId(Integer.valueOf(maps.getString("message_id")));
        	}
        	// 中间表ID
        	if(null!=maps.get("audit_id")){
        		empDetailVo.setAuditId(Integer.valueOf(maps.getString("audit_id")));
        	}
        	// 户口性质
        	if(null!=maps.get("insuranceLiveType")){
        		empDetailVo.setTypeCode(maps.getString("insuranceLiveType"));
        		empDetailVo.setTypeName(maps.getString("insuranceLiveType_name"));
        	}
        	// 社保基数
        	if(null!=maps.getBigDecimal("insuranceSalary")){
        		empDetailVo.setInsuranceSalary(maps.getBigDecimal("insuranceSalary"));
        	}
    	    if(null!=maps.getBigDecimal("fundSalary")){
    	    	empDetailVo.setFund(maps.getBigDecimal("fundSalary"));
    	    }
	        // 参保月份
	        List<Map<String, String>> insuranceFundDate = this.findInsuranceFundDate(cti, empDetailVo.getCtiyId());
	        empDetailVo.setInsFundDate(insuranceFundDate);
	        if(null == empDetailVo.getDefaultInsFundDate()){
	        	empDetailVo.setDefaultInsFundDate(this.findDefaultDate(cti, empDetailVo.getCtiyId()));
	        }
		}
		return empDetailVo;
	}
	
	/**
     * 设置默认值
     *  @param bdBusinessfieldList
     *  @param object 
     *	@return 			: void 
     *  @createDate  	: 2017年10月26日 下午5:37:41
     *  @author         	: wangchao 
     *  @version        	: v1.0
     *  @updateDate    	: 2017年10月26日 下午5:37:41
     *  @updateAuthor  :
     */
    private void setDefaultValue(List<BusinessField> bdBusinessfieldList, JSONObject object,Integer bstypeId) {
        for (BusinessField businessField : bdBusinessfieldList) {
        	if("oldHos".equals(businessField.getCode())
        			|| "newHos".equals(businessField.getCode())
        			|| ("insuranceSalary".equals(businessField.getCode()) 
        					&& (BsType.INCREMENT_INSUR_ENTRY.code().equals(bstypeId) || BsType.NEW_INSURANCE.code().equals(bstypeId))) 
        			){
        		businessField.setPageIsHidden("HIDDEN");
        		continue;
        	}
            if (object.containsKey(businessField.getCode())) {
                if (businessField.getType().equals("PULL")) {
                    if (object.containsKey(businessField.getCode() + "_name")) {
                        businessField.setDefaultValue(object.get(businessField.getCode() + "_name").toString());
                    }
                } else {
                	businessField.setDefaultValue(object.get(businessField.getCode()).toString());
                }
            }
        }
    }
	
	/**
	 * 获取业务类型
	 *  @param spsTask 
	 *  @return 
	 *	@return 			: String 
	 *  @createDate  	: 2017年9月20日 下午4:41:58
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年9月20日 下午4:41:58
	 *  @updateAuthor  :
	 */
	private String findTaskType(SpsTask spsTask){
		String taskType = "";
		if(TaskStateFiled.TODO_DSH_APPLICATION.getStateFiledId() == spsTask.getStateFiledId()){
			taskType += "入职任务（待完善）";
		}else if(TaskStateFiled.TODO_WAITING_APPLICATION.getStateFiledId() == spsTask.getStateFiledId()){
			taskType += "网申任务（待网申）";
		}else if(TaskStateFiled.ERROR_APPLICATION.getStateFiledId() == spsTask.getStateFiledId()){
			taskType += "网申任务（网申失败）";
		}else if(TaskStateFiled.TODO_ONLINE_APPLICATION.getStateFiledId() == spsTask.getStateFiledId() && "TODO".equals(spsTask.getType())){
			taskType += "网申任务（网申中）";
		}else if(TaskStateFiled.COMPLETED_APPLICATION.getStateFiledId() == spsTask.getStateFiledId()){
			taskType += "网申任务（网申成功）";
		}else if(TaskStateFiled.SUBMIT_WAITING_APPLICATION.getStateFiledId() == spsTask.getStateFiledId()){
			taskType += "服务商任务（等待申报）";
		}else if(TaskStateFiled.SUBMIT_ONLINE_APPLICATION.getStateFiledId() == spsTask.getStateFiledId()){
			taskType += "服务商任务（网申中）";
		}
		return taskType;
	}
	
	/**
	 * 
	 *  @param areaId 
	 *  @createDate  	: 2017年3月17日 上午9:51:27
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年3月17日 上午9:51:27
	 *  @updateAuthor  	:
	 */
	private List<Map<String, String>> findInsuranceFundDate(ContextInfo cti,Integer areaId) {
		// 根据企业ID和城市获取最小方案
		SpsScheme scheme = new SpsScheme();
		scheme.setCpId(cti.getOrgId());
		scheme.setAreaId(areaId);
		scheme.setAuthority(cti.getAuthority());
		scheme.setSpId(-999);
		scheme = spsSchemeService.findMinSchemeByCityIdAndCpId(cti,scheme);
		
		BsArea bsArea = new BsArea();
        bsArea.setAreaId(areaId);
        bsArea = bsAreaService.findbyPK(bsArea);
        // 新参
        BdBsareatype bdBsareatype2 = new BdBsareatype();
        bdBsareatype2.setAreaId(areaId);
        bdBsareatype2.setBstypeId(2);
        List<BdBsareatype> fList = bdBsareatypeService.findAll(bdBsareatype2);
        if (fList.size() > 0) {
        	bdBsareatype2 = fList.get(0);
        }
        // 增加
        BdBsareatype bdBsareatype10 = new BdBsareatype();
        bdBsareatype10.setAreaId(bsArea.getBelongCity());
        bdBsareatype10.setBstypeId(10);
        List<BdBsareatype> list = bdBsareatypeService.findAll(bdBsareatype10);
        if (list.size() > 0) {
        	bdBsareatype10 = list.get(0);
        }
        return this.findDate(scheme,bdBsareatype2,bdBsareatype10);
	}
	
	/**
	 * 获取公积金社保缴纳日期
	 *  @param spsScheme
	 *  @param bdBsareatype2
	 *  @param bdBsareatype10
	 *  @return 
	 *	@return 			: Map<String,String> 
	 *  @createDate  	: 2017年3月17日 下午2:41:07
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年3月17日 下午2:41:07
	 *  @updateAuthor  :
	 */
	private List<Map<String, String>> findDate(SpsScheme spsScheme,BdBsareatype bdBsareatype2,BdBsareatype bdBsareatype10){
		List<Map<String, String>> insuranceFundDateMapList = new ArrayList<>();
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
	        Calendar currentMoon = Calendar.getInstance();
	        int currentDay = currentMoon.get(Calendar.DAY_OF_MONTH);// 当前日期
	        // 社保
	        String insuranceDate = "";
	        if ("NEXTMONTH".equals(bdBsareatype2.getEffect())) {
	        	currentMoon.add(Calendar.MONTH, 1);
	        } 
	        if (currentDay > spsScheme.getEndDate()) {
	        	currentMoon.add(Calendar.MONTH, 1);
	        }
	        insuranceDate = format.format(currentMoon.getTime());
	        // 公积金
	        String fundDate = "";
	        if ("NEXTMONTH".equals(bdBsareatype2.getEffect())) {
	        	currentMoon.add(Calendar.MONTH, 1);
	        } 
	        if (currentDay > spsScheme.getEndDate()) {
	        	currentMoon.add(Calendar.MONTH, 1);
	        }
	        fundDate = format.format(currentMoon.getTime());
	        Date iDate = format.parse(insuranceDate);
	        Date fDate = format.parse(fundDate);
	        // 获取最小的月份
	        String currentMonth = fundDate;
	        if(iDate.before(fDate)) {
	        	currentMonth = insuranceDate;
	        }
	        Map<String, String> insuranceFundDateMap = new HashMap<String, String>();
	        insuranceFundDateMap.put("insuranceDate", insuranceDate); 
	        insuranceFundDateMap.put("fundDate", fundDate);
	        insuranceFundDateMap.put("insuranceFundDate", currentMonth);
	        insuranceFundDateMapList.add(insuranceFundDateMap);
	        // 延续一个月
	        insuranceFundDateMap = new HashMap<String, String>();
	        currentMoon.setTime(format.parse(currentMonth));
	        currentMoon.add(Calendar.MONTH, 1);
	        String nextOneMoon = format.format(currentMoon.getTime());
	        insuranceFundDateMap.put("insuranceFundDate", nextOneMoon);
	        
	        currentMoon.setTime(format.parse(insuranceDate));
	        currentMoon.add(Calendar.MONTH, 1);
	        insuranceDate = format.format(currentMoon.getTime());
	        insuranceFundDateMap.put("insuranceDate", insuranceDate); 
	        
	        currentMoon.setTime(format.parse(fundDate));
	        currentMoon.add(Calendar.MONTH, 1);
	        fundDate = format.format(currentMoon.getTime());
	        insuranceFundDateMap.put("fundDate", fundDate);
	        insuranceFundDateMapList.add(insuranceFundDateMap);
	        
	        // 延续两个月
	        insuranceFundDateMap = new HashMap<String, String>();
	        currentMoon.setTime(format.parse(nextOneMoon));
	        currentMoon.add(Calendar.MONTH, 1);
	        String nextTwoMoon = format.format(currentMoon.getTime());
	        insuranceFundDateMap.put("insuranceFundDate", nextTwoMoon);
	        
	        currentMoon.setTime(format.parse(insuranceDate));
	        currentMoon.add(Calendar.MONTH, 1);
	        insuranceDate = format.format(currentMoon.getTime());
	        insuranceFundDateMap.put("insuranceDate", insuranceDate); 
	        
	        currentMoon.setTime(format.parse(fundDate));
	        currentMoon.add(Calendar.MONTH, 1);
	        fundDate = format.format(currentMoon.getTime());
	        insuranceFundDateMap.put("fundDate", fundDate);
	        insuranceFundDateMapList.add(insuranceFundDateMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return insuranceFundDateMapList;
	}
	
	/**
	 * 获取默认月份
	 *  @param cti
	 *  @param areaId
	 *  @return 
	 *	@return 			: String 
	 *  @createDate  	: 2017年5月12日 下午3:15:09
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年5月12日 下午3:15:09
	 *  @updateAuthor  :
	 */
	private String findDefaultDate(ContextInfo cti,Integer areaId){
		try {
			SpsScheme scheme = new SpsScheme();
			scheme.setCpId(cti.getOrgId());
			scheme.setAreaId(areaId);
			scheme.setAuthority(cti.getAuthority());
			scheme.setSpId(-999);
			scheme = spsSchemeService.findMinSchemeByCityIdAndCpId(cti,scheme);
			
			BsArea bsArea = new BsArea();
	        bsArea.setAreaId(areaId);
	        bsArea = bsAreaService.findbyPK(bsArea);
	        // 新参
	        BdBsareatype bdBsareatype2 = new BdBsareatype();
	        bdBsareatype2.setAreaId(areaId);
	        bdBsareatype2.setBstypeId(2);
	        List<BdBsareatype> fList = bdBsareatypeService.findAll(bdBsareatype2);
	        if (fList.size() > 0) {
	        	bdBsareatype2 = fList.get(0);
	        }
	        // 增加
	        BdBsareatype bdBsareatype10 = new BdBsareatype();
	        bdBsareatype10.setAreaId(bsArea.getBelongCity());
	        bdBsareatype10.setBstypeId(10);
	        List<BdBsareatype> list = bdBsareatypeService.findAll(bdBsareatype10);
	        if (list.size() > 0) {
	        	bdBsareatype10 = list.get(0);
	        }
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
	        Calendar currentMoon = Calendar.getInstance();
	        int currentDay = currentMoon.get(Calendar.DAY_OF_MONTH);// 当前日期
	        // 社保
	        String insuranceDate = "";
	        if ("NEXTMONTH".equals(bdBsareatype2.getEffect())) {
	        	currentMoon.add(Calendar.MONTH, 1);
	        } 
	        if (currentDay > scheme.getEndDate()) {
	        	currentMoon.add(Calendar.MONTH, 1);
	        }
	        insuranceDate = format.format(currentMoon.getTime());
	        // 公积金
	        String fundDate = "";
	        if ("NEXTMONTH".equals(bdBsareatype2.getEffect())) {
	        	currentMoon.add(Calendar.MONTH, 1);
	        } 
	        if (currentDay > scheme.getEndDate()) {
	        	currentMoon.add(Calendar.MONTH, 1);
	        }
	        fundDate = format.format(currentMoon.getTime());
	        Date iDate = format.parse(insuranceDate);
	        Date fDate = format.parse(fundDate);
	        // 获取最小的月份
	        String currentMonth = fundDate;
	        if(iDate.before(fDate)) {
	        	currentMonth = insuranceDate;
	        }
	        return currentMonth;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 参保城市
	 *  @param cti
	 *  @param type
	 *  @return 
	 *  @createDate  	: 2017年9月29日 下午2:32:56
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年9月29日 下午2:32:56
	 *  @updateAuthor  	:
	 */
	@Override
	public List<Map<String, Object>> findQueryConditionAreaData(ContextInfo cti, Integer type) {
		return spsTaskDao.findQueryConditionAreaData(cti,type);
	}

	/**
	 * 保险类型
	 *  @param cti
	 *  @param type
	 *  @return 
	 *  @createDate  	: 2017年9月29日 下午2:32:59
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年9月29日 下午2:32:59
	 *  @updateAuthor  	:
	 */
	@Override
	public List<Map<String, Object>> findQueryConditionInsFundTypeData(ContextInfo cti, Integer type) {
		return spsTaskDao.findQueryConditionInsFundTypeData(cti,type);
	}

	/**
	 * 业务类型
	 *  @param cti
	 *  @param type
	 *  @return 
	 *  @createDate  	: 2017年9月29日 下午2:33:03
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年9月29日 下午2:33:03
	 *  @updateAuthor  	:
	 */
	@Override
	public List<Map<String, Object>> findQueryConditionTaskTypeData(ContextInfo cti, Integer type) {
		return spsTaskDao.findQueryConditionTaskTypeData(cti,type);
	}

	/**
	 * 业务状态
	 *  @param cti
	 *  @param type
	 *  @return 
	 *  @createDate  	: 2017年9月29日 下午2:33:06
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年9月29日 下午2:33:06
	 *  @updateAuthor  	:
	 */
	@Override
	public List<Map<String, Object>> findQueryConditionTaskStatusData(ContextInfo cti, Integer type) {
		return spsTaskDao.findQueryConditionTaskStatusData(cti,type);
	}

    /**
     * 查找员工 按多类型查找 按创建时间倒序
     *
     * @param bsType    任务类型
     * @param type      任务状态
     * @return
     */
    public List<SpsTask> findTaskInBsType(Integer empId, String bsType, String type, String[] typeIn){
        return spsTaskDao.findTaskInBsType(empId, bsType, type, typeIn);
    }
}

