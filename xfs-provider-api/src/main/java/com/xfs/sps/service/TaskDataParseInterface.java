package com.xfs.sps.service;

import com.xfs.common.ContextInfo;

import java.util.Map;

import com.xfs.common.ContextInfo;

/**
 * 任务单 数据转换接口
 * @author lifq
 *
 * 2016年4月9日  上午9:10:36
 */
public interface TaskDataParseInterface {
	
	/**
	 * 导入前 校验
	 *
	 * @author lifq
	 *
	 * 2016年4月20日  下午8:49:08
	 */
	public String checkBeforeImport(ContextInfo cti, Map<String, String> curMap, Map<String, Object> paraMap);
	
	/**
	 * 保存前 处理map对象
	 *
	 * @author lifq
	 *
	 * 2016年4月22日  下午5:07:13
	 */
	public void delBeforeSave(ContextInfo cti,Map<String, String> curMap, Map<String, Object> paraMap);

	/**
	 * 保存前转换json
	 *
	 * @author wuzhe
	 *
	 * 2016年10月21日
	 */
	public String parseJson(ContextInfo cti,Map<String, String> curMap, Map<String, Object> paraMap);

}
