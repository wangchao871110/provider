package com.xfs.ts.dao;

import java.util.HashMap;
import java.util.List;

import com.xfs.common.ContextInfo;
import org.springframework.stereotype.Repository;

import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.ts.model.SpsTsOpresult;

@Repository
public class SpsTaskBotDao extends BaseSqlMapDao {
	
	/**
	 * 获得城市编码列表
	 * @return
	 */
	public List<HashMap<String,String>> findCityCodes(){
		return queryForList("SpsTaskBot.findCityCodes", new HashMap());
	}
	
	/**
	 * 获得城市业务处理器
	 * @return
	 */
	public List<HashMap<String,String>> findCityRoBots(String cityCode,int btype){
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("city", cityCode);
		hashMap.put("btype", btype);
		return queryForList("SpsTaskBot.findCityRoBots", hashMap);
	}
	
	/**
	 * 字段转换器
	 * @return
	 */
	public List<HashMap<String,String>> findConvert(int scheme_id,int btype){
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("scheme_id", scheme_id);
		hashMap.put("btype", btype);
		return queryForList("SpsTaskBot.findConvert", hashMap );
	}
	
	/**
	 * 页面处理器
	 * @param cityCode
	 * @param btype
	 * @return
	 */
	public List<HashMap<String,String>> findAutoPage(String cityCode,int btype){
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("city", cityCode);
		hashMap.put("btype", btype);
		return queryForList("SpsTaskBot.findAutoPage", hashMap );
	}
	
	/**
	 * 页面步骤处理器
	 * @param pageId
	 * @return
	 */
	public List<HashMap<String,String>> findAutoStep(int pageId){
		return queryForList("SpsTaskBot.findAutoStep", pageId );
	}
	
	/**
	 * 版本
	 * @return
	 */
	public List<HashMap<String,String>> findVersion(){
		return queryForList("SpsTaskBot.findVersion", new HashMap() );
	}

	/**
	 * 根据城市名称获取企业id列表
	 * @param code
	 * @return
	 */
	public List<HashMap<String,Object>> findCpid(String code){
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("name", code);
		return queryForList("SpsTaskBot.findCpid", hashMap );
	}

	/**
	 * 根据城市以及cpid获取服务商id等信息
	 * @param areaCode
	 * @param cpid
	 * @return
	 */
	public List<HashMap<String,Object>> findCpInfoByCpid(String areaCode, String scheme_id){
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("areaCode", areaCode);
		hashMap.put("scheme_id", scheme_id);
		return queryForList("SpsTaskBot.findCpInfoByCpid", hashMap );
	}

	public List<HashMap<String,Object>> findTypes() {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		return queryForList("SpsTaskBot.findTypes", hashMap );
	}

	public List<HashMap<String, String>> findAutoPageStep(String cityCode, int btype) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("city", cityCode);
		hashMap.put("btype", btype);
		return queryForList("SpsTaskBot.findAutoPageStep", hashMap );
	}

	/**
	 * 根据城市名称获取企业id列表
	 * @param code
	 * @return
	 */
	public List<HashMap<String,Object>> findCpidByPass(String username,String password,String insuranceFundType,String code){
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("reg_num", username);
		hashMap.put("reg_numpass", password);
		hashMap.put("insurance_fund_type", insuranceFundType);
		hashMap.put("code", code);
		return queryForList("SpsTaskBot.findCpidByPass", hashMap );
	}

	public int findTaskCount(int bstypeid,String mobile,String corp_name) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("bstypeid", bstypeid);
		hashMap.put("mobile", mobile);
		hashMap.put("corp_name", corp_name);
		Integer ret = (Integer) queryForObject("SpsTaskBot.findTaskCount", hashMap );
		return ret.intValue();
	}

	public List<HashMap<String,Object>> findCpidBySpName(String mobile,String corp_name){
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("mobile", mobile);
		hashMap.put("corp_name", corp_name);
		return queryForList("SpsTaskBot.findCpidBySpName", hashMap );
	}
	
	
	public  List<HashMap<String,Object>> findTodoCount(Integer spId) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("sp_id", spId);
		return queryForList("SpsTaskBot.findTodoCount", hashMap );
	}

	//lastFlag 更新为N
	public void updateOpResult(ContextInfo cti, SpsTsOpresult opResult) {
        update(cti, "SpsTaskBot.updateOpResult", opResult );
		
	}

	//插入一条新的result
	public void insertOpResult(ContextInfo cti, SpsTsOpresult opResult) {
		insert(cti, "SpsTaskBot.insertOpResult", opResult);
		
	}
	

}
