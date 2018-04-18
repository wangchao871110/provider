package com.xfs.ts.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.xfs.common.ContextInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.ts.dao.SpsTaskBotDao;
import com.xfs.ts.model.SpsTsOpresult;


@Service
public class SpsTaskBotServiceImpl implements SpsTaskBotService {

	@Autowired
	private SpsTaskBotDao taskbotDao;
	
	
	@Override
	public List<HashMap<String, String>> findCityCodes() {
		return taskbotDao.findCityCodes();
	}

	@Override
	public List<HashMap<String, String>> findCityRoBots(String cityCode, int btype) {
		return taskbotDao.findCityRoBots(cityCode, btype);
	}

	@Override
	public List<HashMap<String, String>> findConvert(int scheme_id, int btype) {
		return taskbotDao.findConvert(scheme_id, btype);
	}

	@Override
	public List<HashMap<String, String>> findAutoPage(String cityCode, int btype) {
		return taskbotDao.findAutoPage(cityCode, btype);
	}

	@Override
	public List<HashMap<String, String>> findAutoStep(int pageId) {
		return taskbotDao.findAutoStep(pageId);
	}

	@Override
	public List<HashMap<String, String>> findVersion() {
		return taskbotDao.findVersion();
	}

	@Override
	public List<HashMap<String, Object>> findCpid(String code) {
		return taskbotDao.findCpid(code);
	}

	@Override
	public List<HashMap<String,Object>> findCpInfoByCpid(String areaCode, String scheme_id){
		return taskbotDao.findCpInfoByCpid(areaCode, scheme_id);
	}

	@Override
	public List<HashMap<String,Object>> findTypes() {
		return taskbotDao.findTypes();
	}

	@Override
	public List<HashMap<String, String>> findAutoPageStep(String cityCode, int btype) {
		return taskbotDao.findAutoPageStep(cityCode,btype);
	}

	@Override
	public List<HashMap<String, Object>> findCpidByPass(String username,String password,String insuranceFundType,String code) {
		return taskbotDao.findCpidByPass(username,password,insuranceFundType,code);
	}
	@Override
	public List<HashMap<String,Object>> findCpidBySpName(String mobile,String corp_name){
		return taskbotDao.findCpidBySpName(mobile,corp_name);
	}

	@Override
	public int findTaskCount(int bstypeid,String mobile,String corp_name){
		return taskbotDao.findTaskCount(bstypeid,mobile,corp_name);
	}

	@Override
	public  List<HashMap<String,Object>> findTodoCount(Integer spId) {
		return taskbotDao.findTodoCount(spId);
	}

	@Override
	public void saveOpResult(ContextInfo cti, SpsTsOpresult opResult) {
		//操作月份
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		opResult.setMonth(dateFormat.format(new Date()).toString());
		opResult.setCreateDt(new Date());
		opResult.setUpdateDt(new Date());
		//把其余的记录，lastFlag 更新为N
		taskbotDao.updateOpResult(cti, opResult);
		
		opResult.setIsLastFlag("Y");
		taskbotDao.insertOpResult(cti, opResult);
	}
}
