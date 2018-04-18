package com.xfs.aop.task.cs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.xfs.analysis.ExcelAnalysisConfigService;
import com.xfs.analysis.model.AnalysisConfig;
import com.xfs.analysis.model.AnalysisData;
import com.xfs.analysis.model.AnalysisResult;
import com.xfs.analysis.social.NewInsuranceImpl;
import com.xfs.analysis.social.SyncEmpDataImpl;
import com.xfs.base.model.BsSysStateReport;
import com.xfs.base.service.BsSysStateReportService;
import com.xfs.business.enums.TaskExecuteType;
import com.xfs.business.enums.TaskStateFiled;
import com.xfs.business.model.ApplySyncData;
import com.xfs.business.model.SpsTask;
import com.xfs.common.ContextInfo;
import com.xfs.common.Result;
import com.xfs.common.constant.IStaticVarConstant;
import com.xfs.common.redies.RedisUtil;
import com.xfs.common.redies.keychain.IRedisKeys;
import com.xfs.common.util.IdcardValidator;
import com.xfs.corp.model.CmEmployee;
import com.xfs.corp.model.CmEmployeeOrg;
import com.xfs.corp.service.CmEmployeeOrgService;
import com.xfs.corp.service.CmEmployeeService;
import com.xfs.pay.wxpay.sign.MD5Util;
import com.xfs.pay.xfspay.sign.MD5;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 官网数据同步
 * Created by konglc on 2017-09-19.
 */
@Component
public class TaskBotSyncDataServiceImpl  implements IStaticVarConstant,IRedisKeys {

    private static final Logger log = Logger.getLogger(TaskBotSyncDataServiceImpl.class);

    /**
     * 社保类型
     */
    private static String INSURANCE_TYPE = "INSURANCE";

    @Autowired
    SyncEmpDataImpl syncEmpDataImpl;

    @Autowired
    CmEmployeeService cmEmployeeService;

    @Autowired
    CmEmployeeOrgService cmEmployeeOrgService;

    @Autowired
    ExcelAnalysisConfigService excelAnalysisConfigService;

    @Autowired
    BsSysStateReportService bsSysStateReportService;

    public void init(){
        //启动线程
        TaskBotSyncDataServiceImpl.HandleTaskSyncDataThread thread = new TaskBotSyncDataServiceImpl.HandleTaskSyncDataThread();
        thread.start();
        log.info("===============================================处理官网数据同步线程启动成功");
    }

    /**
     * 循环处理回调队列信息
     */
    class HandleTaskSyncDataThread extends Thread {
        @Override
        public void run() {
            /**
             * 初始化数据
             */
            AnalysisConfig analysisConfig = excelAnalysisConfigService.analysisConfigSuggester("SOCIAL");
            Map<String,Object> data = new HashMap<>();
            while (true){
                try{
                    ApplySyncData applySyncData = RedisUtil.poll(TASK_SYNC_DATA_QUEUE_NAME, ApplySyncData.class);
                    if(null != applySyncData){
                        long fail = 0;//失败数量
                        long complate = 0;//成功数量
                        data.clear();
                        JSONObject ob = JSON.parseObject(applySyncData.getEmpJson());
                        JSONArray jsonArray = new JSONArray();
                        jsonArray.add(ob);
                        ContextInfo cti = new ContextInfo();
                        cti.setOrgId(applySyncData.getCpId());
                        cti.setUserId(applySyncData.getUserId());
                        cti.setAuthority("ALL");
                        ob.put("schemeId",applySyncData.getSchemeId());
                        ob.put("userId",applySyncData.getUserId());
                        try{
                            AnalysisResult analysisResult = null;
                            if(INSURANCE_TYPE.equals(applySyncData.getInsuranceFundType())){
                                analysisResult = syncEmpDataImpl.analysis(cti,jsonArray,"社保人员同步",null,analysisConfig);
                            }else {
                                analysisResult = syncEmpDataImpl.analysis(cti,jsonArray,"公积金人员同步",null,analysisConfig);
                            }
                            log.info("同步官网数据===="+JSON.toJSONString(analysisResult));

                            /**
                             * 判断解析后列表是否为空
                             */
                            if(null != analysisResult.getDataList() && !analysisResult.getDataList().isEmpty() && (null != analysisResult.getDataList().get(0).get("identityCode") && StringUtils.isNotBlank(analysisResult.getDataList().get(0).get("identityCode").getAnalysisValue()))){
                                for(Map.Entry<String,AnalysisData> analysis : analysisResult.getDataList().get(0).entrySet()){
                                    if("insuranceLiveType".equals(analysis.getKey())){
                                        data.put("insuranceLivingType",analysis.getValue().getAnalysisValue());
                                    }else{
                                        data.put(analysis.getKey(),analysis.getValue().getAnalysisValue());
                                    }
                                }
                                //序列化实体
                                TypeReference<CmEmployeeOrg> empRef = new TypeReference<CmEmployeeOrg>(){};
                                data.put("photo",ob.getString("photo"));
                                String json = JSON.toJSONString(data);
                                CmEmployeeOrg synCmEmployeeOrg = JSON.parseObject(json,empRef);
                                /**
                                 * 1、匹配现有库中人员信息
                                 */
                                synCmEmployeeOrg.setSignMark(ob.getString("signMark"));
                                synCmEmployeeOrg.setInsuranceFundType(applySyncData.getInsuranceFundType());
                                //判断当前人员身份证位数
                                if(StringUtils.isNotBlank(synCmEmployeeOrg.getIdentityCode()) && synCmEmployeeOrg.getIdentityCode().length() ==15){
                                    synCmEmployeeOrg.setIdentityCode(IdcardValidator.convertIdcarBy15bit(synCmEmployeeOrg.getIdentityCode()));
                                }
                                CmEmployeeOrg cmEmployeeOrg = cmEmployeeOrgService.queryEmployeeOrg(synCmEmployeeOrg);
                                /**
                                 * 2、匹配之前同步未匹配到的人员信息
                                 */
                                if(null != cmEmployeeOrg) {
                                    //if(null == cmEmployeeOrg.getEmpId()){
                                        CmEmployee queryVo = new CmEmployee();
                                        queryVo.setCpId(cmEmployeeOrg.getCpId());
                                        queryVo.setName(cmEmployeeOrg.getName());
                                        queryVo.setIdentityCode(cmEmployeeOrg.getIdentityCode());
                                        CmEmployee employee = cmEmployeeService.queryExistEmpByInfo(queryVo,cmEmployeeOrg);
                                        if(null != employee) {
                                            cmEmployeeOrg.setRepeat(1);
                                            cmEmployeeOrg.setEmpId(employee.getEmpId());
                                        }else{
                                            cmEmployeeOrg.setEmpId(null);//未匹配的取消关联关系
                                        }
                                    //}
                                    /**
                                     * 3、将结果放入数据库
                                     */
                                    cmEmployeeOrg.setSchemeId(applySyncData.getSchemeId());
                                    cmEmployeeOrg.setHeadPhoto(ob.getString("photo"));
                                    cmEmployeeOrg.setJson(json);
                                    cmEmployeeOrg.setAnalysisNum(applySyncData.getAnalysisNum());
                                    if(null != cmEmployeeOrg.getId()){
                                        cmEmployeeOrgService.updateOrgData(cti,cmEmployeeOrg);
                                    }else{
                                        cmEmployeeOrgService.insert(cti,cmEmployeeOrg);
                                    }
                                    RedisUtil.hput(SYNC_ORG_EMPLOYEE_LOCAL_CACHE + "_" + cmEmployeeOrg.getCpId(),cmEmployeeOrg.getName()+"_"+cmEmployeeOrg.getIdentityCode() + "_" + cmEmployeeOrg.getInsuranceFundType(),cmEmployeeOrg);
                                }
                            }
                            /**
                             * 计数
                             */
                            complate = RedisUtil.incr(SYN_ORG_DATA_APPLY_SUCCESS_TOTAL + applySyncData.getAnalysisNum());
                        }catch (Exception e){
                            fail = RedisUtil.incr(SYN_ORG_DATA_APPLY_FAIL_TOTAL + applySyncData.getAnalysisNum());
                            e.printStackTrace();
                        }finally {
                            /**
                             * 根据批次号自动统计数量
                             */
                            RedisUtil.setKeyExpireTime(SYN_ORG_DATA_APPLY_SUCCESS_TOTAL+applySyncData.getAnalysisNum(),1800);
                            /**
                             * 更改同步状态
                             */
                            Object reqRealTimeProgressBody = RedisUtil.get(SYN_ORG_DATA_CHECK_KEY+applySyncData.getAnalysisNum());
                            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(reqRealTimeProgressBody));
                            long total = jsonObject.getLong("total");
                            if((fail + complate) == total){
                                BsSysStateReport report = new BsSysStateReport();
                                report.setOwnerType("CUSTOMER");
                                report.setOwner(String.valueOf(cti.getUserId()));
                                report.setAttributeName("SYNC_ORG_DATA_MARK");
                                report.setCpId(cti.getOrgId());
                                Map<String,Object> qReport = bsSysStateReportService.findBsSysStateReport(report);
                                if(null != qReport){
                                    String attribute_value = String.valueOf(qReport.get("attribute_value"));
                                    if(StringUtils.isNotBlank(attribute_value) && !attribute_value.contains("_CLOSE")){
                                        report.setId(Integer.parseInt(String.valueOf(qReport.get("id"))));
                                        report.setAttributeValue(applySyncData.getAnalysisNum()+"_COMPLAY");
                                        bsSysStateReportService.insertVersionMessage(cti,report);
                                    }
                                }
                            }
                        }
                    }else {
                        Thread.sleep(1000);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }


}
