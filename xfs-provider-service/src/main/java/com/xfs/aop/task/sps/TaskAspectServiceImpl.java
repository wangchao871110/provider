package com.xfs.aop.task.sps;

import java.util.Map;

import com.xfs.common.ContextInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.business.model.SpsTask;
import com.xfs.business.service.SpsTaskService;
import com.xfs.common.Result;
import com.xfs.common.util.TaskNoNum;

/**
 * Created by konglc on 2016-08-15.
 * 任务单切面调用类
 */
@Service
public class TaskAspectServiceImpl implements TaskAspectService {

	@Autowired
	private SpsTaskService spsTaskService;

	@Override
	public void saveTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParams, Result result) {
		if(!StringUtils.equals("COMPLETED", spsTask.getType())&&!StringUtils.equals("CLOSED", spsTask.getType())){
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
			Integer count = spsTaskService.freeFindCount(queryNo);
			if(count > 0){
				result.setSuccess(false).setError("实作单号重复");
				return;
			}
			spsTask.setTaskNo(taskNo);
		}
		spsTaskService.savaTask(info,spsTask,businessParams,result);
	}

}
