package com.xfs.bs.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.xfs.base.model.SysTenantparam;
import com.xfs.base.service.SysTenantparamService;
import com.xfs.common.util.SpringContextUtil;

/**
 * 租户参数 信息
 * 
 * @author quanjiahua
 * @date 2015-12-16 上午11:46:42
 */
public class SysTenantParamUtil {

	// 记录日志
	private static Logger logger = Logger.getLogger(SysTenantParamUtil.class);
	public static Map<String, String> tenantParamMap = null;
	public static SysTenantparamService sysTenantparamService = null;

	private synchronized static void init() {
		sysTenantparamService = (SysTenantparamService) SpringContextUtil.getBean(SysTenantparamService.class);
	}

	static {
		init();
	}

	/**
	 * 初始化 租户参数信息
	 * 
	 * @author quanjiahua
	 * @date 2015-12-16 上午11:46:42
	 */
	public static void findList() {
		logger.info("加载 租户参数 load sys_tenantparam ");
		tenantParamMap = new HashMap<String, String>();
		// 查询 租户参数信息
		List<SysTenantparam> list = sysTenantparamService.findAll(new SysTenantparam());
		if (null != list && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				SysTenantparam param = list.get(i);
				String tid = param.getTid();
				String param_code = param.getParamCode();
				String param_value = param.getDefValue();
				// 存放到Map
				tenantParamMap.put(tid+"_"+param_code, param_value);
			}
		}

	}

	/**
	 * 根据 code和tid 查询 代码值
	 *
	 * 
	 * @author quanjiahua
	 * @date 2015-12-16 上午11:46:42
	 */
	public static String getParaValue(String param_code ,String tid) {
		if (null != tenantParamMap && tenantParamMap.size() > 0) {
			return tenantParamMap.get(tid+"_"+param_code);
		} else {
			findList();
			if (null != tenantParamMap && tenantParamMap.size() > 0) {
				return tenantParamMap.get(tid+"_"+param_code);
			} else {
				logger.info("无法取得 代码：" + param_code + "的值，请检查是否配置成功！");
				return "";
			}

		}
	}
}
