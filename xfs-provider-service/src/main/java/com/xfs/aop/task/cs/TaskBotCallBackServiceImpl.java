package com.xfs.aop.task.cs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xfs.business.enums.TaskStateFiled;
import com.xfs.business.model.ApplyOnline;
import com.xfs.business.model.SpsTask;
import com.xfs.business.service.SpsTaskService;
import com.xfs.common.ContextInfo;
import com.xfs.common.Result;
import com.xfs.common.constant.IStaticVarConstant;
import com.xfs.common.redies.RedisUtil;
import com.xfs.common.redies.keychain.IRedisKeys;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

/**
 * T 执行回调队列线程
 * Created by konglc on 2017-09-06.
 */
@Component
public class TaskBotCallBackServiceImpl implements IStaticVarConstant,IRedisKeys {

    private static final Logger log = Logger.getLogger(TaskBotCallBackServiceImpl.class);

    @Autowired
    CorpTaskAspectService corpTaskAspectService;

    @Autowired
    SpsTaskService spsTaskService;

    public void init(){
        //启动线程
        HandleTaskCallBackThread thread = new HandleTaskCallBackThread();
        thread.start();
        log.info("===============================================处理网申回调线程启动成功");
    }

    /**
     * 循环处理回调队列信息
     */
    class HandleTaskCallBackThread extends Thread {

        @Override
        public void run() {
            Result result = Result.createResult().setSuccess(true);
            while (true){
                try{
                    ApplyOnline applyOnline = RedisUtil.poll(TASK_CALL_BACK_QUEUE_NAME, ApplyOnline.class);
                    if(null != applyOnline){
                        SpsTask st = new SpsTask();
                        if("NETWORK_EXCEPTION".equals(applyOnline.getNotifyType())){
                            st.setType(TaskStateFiled.ERROR_APPLICATION.getTaskType());
                            st.setStateFiledId(TaskStateFiled.ERROR_APPLICATION.getStateFiledId());
                            st.setStateFiledName(TaskStateFiled.ERROR_APPLICATION.getStateFiledName());
                            st.setTaskNos(applyOnline.getTaskNo().split(","));
                            if(StringUtils.isBlank(applyOnline.getErrorMsg())){
                                applyOnline.setErrorMsg("网络异常");
                            }
                            st.setErrmsg("{\"notifyType\":\"NETWORK_EXCEPTION\",\"errorMsg\":\""+applyOnline.getErrorMsg()+"\"}");
                            spsTaskService.updateByBatchByTaskNos(null,st);
                            continue;
                        }else{
                            st.setTaskNo(applyOnline.getTaskNo());
                            List<SpsTask> spsTaskList = spsTaskService.findAll(st);
                            if(null != spsTaskList && !spsTaskList.isEmpty()){
                                st = spsTaskList.get(0);
                                st.setErrmsg(StringUtils.isNotBlank(applyOnline.getErrorMsg()) ? "{\"notifyType\":\""+applyOnline.getNotifyType()+"\",\"errorMsg\":\""+applyOnline.getErrorMsg()+"\"}":"");
                                if("SUCCESS".equals(applyOnline.getState())){
                                    st.setType(TaskStateFiled.COMPLETED_APPLICATION.getTaskType());
                                    st.setStateFiledId(TaskStateFiled.COMPLETED_APPLICATION.getStateFiledId());
                                    st.setStateFiledName(TaskStateFiled.COMPLETED_APPLICATION.getStateFiledName());
                                    RedisUtil.incr(SYN_ORG_DATA_APPLY_SUCCESS_TOTAL + applyOnline.getAnalysisNum());
                                    /**
                                     * 根据批次号自动统计数量
                                     */
                                    RedisUtil.setKeyExpireTime(SYN_ORG_DATA_APPLY_SUCCESS_TOTAL+applyOnline.getAnalysisNum(),1800);
                                }else{
                                    st.setType(TaskStateFiled.ERROR_APPLICATION.getTaskType());
                                    st.setStateFiledId(TaskStateFiled.ERROR_APPLICATION.getStateFiledId());
                                    st.setStateFiledName(TaskStateFiled.ERROR_APPLICATION.getStateFiledName());
                                    /**
                                     * 根据批次号自动统计数量
                                     */
                                    RedisUtil.incr(SYN_ORG_DATA_APPLY_FAIL_TOTAL+applyOnline.getAnalysisNum());
                                    RedisUtil.setKeyExpireTime(SYN_ORG_DATA_APPLY_FAIL_TOTAL+applyOnline.getAnalysisNum(),1800);
                                }
                            }
                            TypeReference<Map<String,Object>> ref = new TypeReference<Map<String,Object>>(){};
                            Map<String,Object> bussinessMap = JSON.parseObject(st.getJson(),ref);
                            st.setCallBack(true);
                            ContextInfo cti = new ContextInfo();
                            cti.setOrgId(st.getCpId());
                            cti.setUserId(st.getCreateBy());
                            corpTaskAspectService.saveTask(cti,st,bussinessMap,result);
                            log.info("接收到TaskBot回调，回调结果：" + result.isSuccess() + (result.isSuccess() ? "" : result.getError())+"RespBody:"+JSON.toJSONString(applyOnline));
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
