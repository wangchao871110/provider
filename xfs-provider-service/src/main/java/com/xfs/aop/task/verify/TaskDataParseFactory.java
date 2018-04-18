package com.xfs.aop.task.verify;

import com.xfs.sps.service.TaskDataParseInterface;

/**
 * 任务单 工厂类
 * @author lifq
 *
 * 2016年5月20日  下午2:48:32
 */
public class TaskDataParseFactory {
	
	private static final TaskDataParseInterface updatePhoneDataParseObj = new UpdatePhoneDataParseImpl();
	private static final TaskDataParseInterface newInsuranceParseObj = new NewInsuranceParseImpl();
	private static final TaskDataParseInterface incrementParseObj = new IncrementParseImpl();
	private static final TaskDataParseInterface subtractParseObj = new SubtractParseImpl();
	private static final TaskDataParseInterface updateHospitalParseObj = new UpdateHospitalParseImpl();
	/**
	 * 默认转换处理 实现类
	 */
	private static final TaskDataParseInterface defaultDataParseObj = new DefaultDataParseImpl();
	
	
	/**
	 * 获取 业务转换 实例
	 *
	 * @author lifq
	 *
	 * 2016年5月20日  下午3:09:39
	 */
	public static TaskDataParseInterface getInstance(Integer bstypeId){
		TaskDataParseInterface obj = null;
		switch (bstypeId) {
		case 1:
			obj =  updatePhoneDataParseObj;
			break;
		case 2:
			obj =  newInsuranceParseObj;
			break;
		case 3:
			obj =  incrementParseObj;
			break;
		case 4:
			obj =  subtractParseObj;
			break;
		case 7:
			obj =  updateHospitalParseObj;
			break;
		case 10:
			obj =  incrementParseObj;
			break;
		default:
			obj = defaultDataParseObj;
			break;
		}
		
		return obj;
	}

}
