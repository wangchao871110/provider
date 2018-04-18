package com.xfs.aop.task.cs;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.xfs.business.enums.BsType;
import com.xfs.business.enums.TaskExecuteType;
import com.xfs.business.model.SpsTask;
import com.xfs.common.ContextInfo;
import com.xfs.common.Result;
import com.xfs.corp.model.CmEmployee;

/**
 * 员工入职
 * @author 	: wangchao
 * @date 	: 2017年9月28日 下午4:59:43
 * @version 	: V1.0
 */
@Service
public class EmpEntryParseImpl extends BaseTaskDataParse {

    @Override
    public boolean checkBussinessByTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame) {
        if (BsType.INCREMENT_INSUR_ENTRY.code().equals(spsTask.getBstypeId())) {
            return true;
        }
        return false;
    }
    
    @Override
    public boolean checkDataValidity(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame,
    		CmEmployee employee, Result result) {
    	return true;
    }
    
    @Override
    public boolean beforeSaveTask(ContextInfo info, SpsTask spsTask, Map<String, Object> businessParame, CmEmployee cmEmployee, TaskExecuteType taskExecuteType, Result result){
		return true;
    }
    
    @Override
    public void afterSaveTask(ContextInfo info,SpsTask spsTask, Map<String, Object> businessParame, CmEmployee cmEmployee, TaskExecuteType taskExecuteType, Result result){
    	
    }

}
