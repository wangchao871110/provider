package com.xfs.ts.service;

import java.util.HashMap;
import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.ts.model.SpsTsOpresult;

public interface SpsTaskBotService {

	/**
	 * 获得城市编码列表
	 * @return
	 */
	public List<HashMap<String,String>> findCityCodes();
	
	/**
	 * 获得城市业务处理器
	 * @return
	 */
	public List<HashMap<String,String>> findCityRoBots(String cityCode, int btype);
	
	/**
	 * 字段转换器
	 * @return
	 */
	public List<HashMap<String,String>> findConvert(int scheme_id, int btype);
	
	/**
	 * 页面处理器
	 * @param cityCode
	 * @param btype
	 * @return
	 */
	public List<HashMap<String,String>> findAutoPage(String cityCode, int btype);
	
	
	/**
	 * 页面步骤处理器
	 * @param pageId
	 * @return
	 */
	public List<HashMap<String,String>> findAutoStep(int pageId);
	
	
	/**
	 * 版本
	 * @return
	 */
	public List<HashMap<String,String>> findVersion();
	
	
	/**
	 * 根据城市名称获取企业id列表
	 * @param code
	 * @return cp_id,corp_name
	 */
	public List<HashMap<String,Object>> findCpid(String code);

	/**
	 * 根据城市以及cpid获取服务商id等信息
	 * @param areaCode
	 * @param cpid
	 * @return
	 */
	public List<HashMap<String,Object>> findCpInfoByCpid(String areaCode, String cpid);

	public List<HashMap<String,Object>> findTypes();

	public List<HashMap<String, String>> findAutoPageStep(String cityCode, int btype);

	/**
	 * 获取spid
	 * @param username
	 * @param password
	 * @param insuranceFundType
	 * @param code
     * @return
     */
	public List<HashMap<String, Object>> findCpidByPass(String username, String password, String insuranceFundType, String code);

	/**
	 * 获取cpid
	 * @param mobile
	 * @param corp_name
     * @return
     */
	public List<HashMap<String,Object>> findCpidBySpName(String mobile, String corp_name);

	/**
	 * 根据业务类型获取任务单个数
	 * @param bstypeid
	 * @param mobile
	 * @param corp_name
     * @return
     */
	public int findTaskCount(int bstypeid, String mobile, String corp_name);
	

	
	public  List<HashMap<String,Object>> findTodoCount(Integer spd);

	public void saveOpResult(ContextInfo cti, SpsTsOpresult opResult);
}
