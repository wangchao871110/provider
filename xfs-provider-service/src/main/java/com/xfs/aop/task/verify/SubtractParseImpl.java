package com.xfs.aop.task.verify;

import com.xfs.common.ContextInfo;

import java.util.Map;

import com.xfs.common.ContextInfo;

/**
 * 减员
 * @author lifq
 *
 * 2016年4月21日  下午2:58:51
 */
public class SubtractParseImpl extends BaseTaskDataParse {
	

	/**
	 * 导入前 校验
	 *
	 * @author lifq
	 *
	 * 2016年4月20日  下午8:49:08
	 */
	@Override
	public String checkBeforeImport(ContextInfo cti, Map<String,String> curMap, Map<String,Object> paraMap) {
		String err = super.checkBeforeImport(cti,curMap, paraMap);//必录项 校验
		return err;
	}
	
	/**
	 * 保存前 处理map对象
	 *
	 * @author lifq
	 *
	 * 2016年4月22日  下午5:07:13
	 */
	@Override
	public void delBeforeSave(ContextInfo cti,Map<String,String> curMap,Map<String,Object> paraMap){
		
	}

	/**
	 * 保存前转换json
	 *
	 * @author wuzhe
	 *
	 * 2016年10月21日
	 */
	@Override
	public String parseJson(ContextInfo cti,Map<String,String> curMap,Map<String,Object> paraMap){
		String month  = paraMap.get("month").toString();
		curMap.put("endDate", month);
		return super.parseJson(cti,curMap,paraMap);
	}

}
