package com.xfs.aop.task.verify;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xfs.business.model.SpsFixedpointhospital;
import com.xfs.business.service.SpsFixedpointhospitalService;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.SpringContextUtil;

/**
 * 定点医院 修改
 * 
 * @author lifq
 *
 * 2016年4月21日 下午2:58:51
 */
public class UpdateHospitalParseImpl extends BaseTaskDataParse {

	public static SpsFixedpointhospitalService spsFixedpointhospitalService;

	private synchronized static void init() {
		spsFixedpointhospitalService = (SpsFixedpointhospitalService) SpringContextUtil
				.getBean(SpsFixedpointhospitalService.class);
	}

	static {
		init();
	}


	private Map<String, String> hosiptalName_FullNameMap = null;
	private Map<String, String> hosiptalFullName_NameMap = null;
	private Map<String, String> hosiptalFullName_CodeMap = null;
	
	private Map<String, String> hosiptalFullName_hospitalType = null;
	private Map<String, String> hosiptalName_hospitalType = null;
	private Map<String, SpsFixedpointhospital> hosiptalCodeMap = null;


	/**
	 * 导入前 校验
	 *
	 * @author lifq
	 *
	 *         2016年4月20日 下午8:49:08
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
	 *         2016年4月22日 下午5:07:13
	 */
	@Override
	public void delBeforeSave(ContextInfo cti,Map<String, String> curMap,Map<String,Object> paraMap) {
//		for (int i = 1; i <= 5; i++) {
//			String curHospital = "hospital" + i;
//			if (getHosiptalName_FullNameMap().containsKey(curMap.get(curHospital))) {
//				String fullName = getHosiptalName_FullNameMap().get(curMap.get(curHospital));
//				curMap.put(curHospital, fullName);
//			}
//			curMap.put(curHospital + "_code", getHosiptalFullName_CodeMap().get(curMap.get(curHospital)));
//		}

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


	/**
	 * 获取医院信息 ，只查询一次
	 *
	 * @author lifq
	 *
	 *         2016年4月22日 上午11:18:10
	 */
	private void initHospitalMap() {
		hosiptalName_FullNameMap = new HashMap<String, String>();
		hosiptalFullName_NameMap = new HashMap<String, String>();
		hosiptalFullName_CodeMap = new HashMap<String, String>();
		hosiptalName_hospitalType = new HashMap<String, String>();
		hosiptalFullName_hospitalType = new HashMap<String, String>();
		hosiptalCodeMap = new HashMap<String, SpsFixedpointhospital>();
		SpsFixedpointhospital vo = new SpsFixedpointhospital();
		vo.setAreaId(2);
		List<SpsFixedpointhospital> list = spsFixedpointhospitalService.findAll(vo);
		if (null != list && list.size() > 0) {
			for (SpsFixedpointhospital obj : list) {
				hosiptalFullName_NameMap.put(obj.getFullName(), obj.getAbbreviation());
				hosiptalName_FullNameMap.put(obj.getAbbreviation(), obj.getFullName());
				hosiptalFullName_CodeMap.put(obj.getFullName(), obj.getHospitalCode());
				hosiptalFullName_hospitalType.put(obj.getFullName(), obj.getHospitalType());
				hosiptalName_hospitalType.put(obj.getAbbreviation(), obj.getHospitalType());
				hosiptalCodeMap.put(obj.getHospitalCode(),obj);
			}
		}
	}

	/**
	 * 获取 医院 map(全名--》简称)
	 *
	 * @author lifq
	 *
	 *         2016年4月22日 下午1:45:33
	 */
	public Map<String, String> getHosiptalFullName_NameMap() {
		if (null == hosiptalFullName_NameMap) {
			initHospitalMap();
		}
		return hosiptalFullName_NameMap;
	}

	/**
	 * 获取 医院 map(简称--》全名)
	 *
	 * @author lifq
	 *
	 *         2016年4月22日 下午1:45:33
	 */
	public Map<String, String> getHosiptalName_FullNameMap() {
		if (null == hosiptalName_FullNameMap) {
			initHospitalMap();
		}
		return hosiptalName_FullNameMap;
	}

	/**
	 * 获取 医院 map(全名--》编码)
	 *
	 * @author lifq
	 *
	 *         2016年4月22日 下午1:45:33
	 */
	public Map<String, String> getHosiptalFullName_CodeMap() {
		if (null == hosiptalFullName_CodeMap) {
			initHospitalMap();
		}
		return hosiptalFullName_CodeMap;
	}
	
	/**
	 * 全名--》 医院类型
	 *
	 * @author lifq
	 *
	 * 2016年4月25日  下午1:54:45
	 */
	public Map<String, String> getHosiptalFullName_hospitalType() {
		if (null == hosiptalFullName_hospitalType) {
			initHospitalMap();
		}
		return hosiptalFullName_hospitalType;
	}
	
	/**
	 * 简称--》 医院类型
	 *
	 * @author lifq
	 *
	 * 2016年4月25日  下午1:54:45
	 */
	public Map<String, String> getHosiptalName_hospitalType() {
		if (null == hosiptalName_hospitalType) {
			initHospitalMap();
		}
		return hosiptalName_hospitalType;
	}


	public Map<String, SpsFixedpointhospital> getHosiptalCodeMap() {
		if (null == hosiptalCodeMap) {
			initHospitalMap();
		}
		return hosiptalCodeMap;
	}


}
