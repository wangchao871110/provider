package com.xfs.aop.task.cs;

import com.xfs.business.enums.TaskExecuteType;
import com.xfs.business.model.SpsTask;
import com.xfs.business.service.SpsTaskService;
import com.xfs.common.ContextInfo;
import com.xfs.common.Result;
import com.xfs.common.constant.IStaticVarConstant;
import com.xfs.common.util.TaskNoNum;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by konglc on 2016-08-15.
 * 任务单切面调用类
 */
@Service
public class CorpTaskAspectServiceImpl implements CorpTaskAspectService {

    @Autowired
    private SpsTaskService spsTaskService;

	@Override
	public void saveTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParams, Result result){
		((CorpTaskAspectService) AopContext.currentProxy()).saveTask(info, spsTask, businessParams, result,TaskExecuteType.NO_COMPLATE_ADDEXECUTE);
	}

    @Override
    public void saveTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParams, Result result,TaskExecuteType taskExecuteType) {
		if(null == spsTask)
			return;
		boolean isNew = false;
		if (null == spsTask.getTaskId()){
			//产生实做单号
			createTaskNo(spsTask,result);
			isNew = true;
		}

		spsTaskService.savaTask(info,spsTask,businessParams,result);

		/**
		 * 自服务任务单
		 * 补全任务单之前流程记录
		 */
		if(isNew && spsTask.getCompanyType().equals(IStaticVarConstant.CMCORPTYPE_SELFSERVICE)){
			spsTaskService.repairTaskReport(spsTask);
		}
    	//记录历史任务单
		spsTaskService.addSpsTaskHistory(info,spsTask);
    }

	/**
	 * 生成实做单号
	 * @param spsTask
	 * @param result
	 */
    private void createTaskNo(SpsTask spsTask,Result result){
		Integer index = 0;
		String taskNo = null;
		SpsTask queryNo = new SpsTask();
		while (index < 50){
			taskNo = TaskNoNum.makeOrderNum();
			if(taskNo == null){
				result.setSuccess(false).setError("未获取到实作单号");
				return;
			}
			queryNo.setTaskNo(taskNo);
			if(spsTaskService.freeFindCount(queryNo) < 1){
				break;
			}
			index++;
		}
		spsTask.setTaskNo(taskNo);
	}
}
