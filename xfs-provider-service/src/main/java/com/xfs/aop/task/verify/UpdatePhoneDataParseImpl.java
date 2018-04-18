package com.xfs.aop.task.verify;

import com.xfs.common.ContextInfo;

import java.util.Map;

import com.xfs.common.ContextInfo;

/**
 * 更新手机号 数据转换 实现类
 * 
 * @author lifq
 *
 * 2016年4月9日  上午9:58:35
 */
public class UpdatePhoneDataParseImpl extends BaseTaskDataParse {
	
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
		return super.parseJson(cti,curMap,paraMap);
	}

}
