package com.xfs.report.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.report.dao.BsSeachDao;
import com.xfs.report.model.BsSeach;
@Service
public class BsSeachServiceImpl implements BsSeachService {


	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BsSeachServiceImpl.class);

	@Autowired
	BsSeachDao bsSeachDao =null;

	public int save(ContextInfo cti, BsSeach vo ){
		return bsSeachDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BsSeach vo ){
		return bsSeachDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BsSeach vo ){
		return bsSeachDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BsSeach vo ){
		return bsSeachDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BsSeach vo){

		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bsSeachDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BsSeach> datas = bsSeachDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;

	}
	public List<BsSeach> findAll(BsSeach vo){

		List<BsSeach> datas = bsSeachDao.freeFind(vo);
		return datas;

	}


	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/11/10 14:48:42


	@Override
	public BsSeach findBsSeachById(BsSeach vo) {
		return bsSeachDao.findByPK(vo);
	}

	@Override
	public List<HashMap<String, Object>> findAllSeach() {
		return bsSeachDao.findAllSeach();
	}

	@Override
	public PageModel findTableData(PageInfo pi, Integer seachId) {
		List<HashMap<String, Object>>  list = bsSeachDao.findSeach(seachId);
		if(list == null || list.isEmpty()){
			return null;
		}
		HashMap<String, Object> seach  = list.get(0);
		String sqltext = (String) seach.get("sqltext");
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bsSeachDao.countData(sqltext);
		pm.setTotal(total);
		List<HashMap<String, Object>> tableData = bsSeachDao.findTableData(sqltext, pageIndex,pageSize);
		pm.setDatas(tableData);
		return pm;
	}

	@Override
	public HashMap<String,String> findSeachCondition(Integer seachId) {
		List<HashMap<String, Object>>  list = bsSeachDao.findSeach(seachId);
		HashMap<String, Object> map = list.get(0);
		String columns = (String) map.get("columns");
		String condition_name = (String) map.get("condition_name");
		if(columns!=null && condition_name !=null){
			String cols[] = columns.split(",");
			String col_names[] = condition_name.split(",");
			HashMap<String, String> colMap = new LinkedHashMap<>();
			for(int i =0;i<cols.length;i++){
				colMap.put(cols[i], col_names[i]);
			}
			return colMap;
		}
		return null;
	}

    @Override
    public HashMap<String,Object> findSeachConditionType(Integer seachId) {
        List<HashMap<String, Object>>  list = bsSeachDao.findSeach(seachId);
        HashMap<String,Object> hashMap = new LinkedHashMap<>();
        HashMap<String, Object> map = list.get(0);
        String columns = (String) map.get("columns");
        String conditionName = (String) map.get("condition_name");
        String conditionType = (String) map.get("condition_type");
        if(columns!=null && conditionName !=null&conditionType!=null){
            String cols[] = columns.split(",");
            String col_names[] = conditionName.split(",");
            String colTypes[] = conditionType.split(",");
            HashMap<String, String> colMap = new LinkedHashMap<>();
            for(int i =0;i<cols.length;i++){
                BsSeach bsSeach = new BsSeach();
                bsSeach.setColumns(cols[i]);
                bsSeach.setConditionType(colTypes[i]);
                bsSeach.setConditionName(col_names[i]);
                hashMap.put(cols[i],bsSeach);
            }
            return hashMap;
        }
        return null;
    }

	@Override
	public PageModel findTableData(PageInfo pi, Integer seachId, List<String> conList,List<String> valList ,String orderValue ) {
		List<HashMap<String, Object>>  list = bsSeachDao.findSeach(seachId);
		if(list == null || list.isEmpty()){
			return null;
		}
		HashMap<String, Object> seach  = list.get(0);

		String sqltext = (String) seach.get("sqltext");
		String before=sqltext.substring(0,sqltext.indexOf("1=1")+3);
		String after=sqltext.substring(sqltext.indexOf("1=1")+3,sqltext.length());
		StringBuffer conn =new StringBuffer();

		if(seach.get("condition")!=null){
			String condition_sql = seach.get("condition").toString();
			String con_sql[] = condition_sql.split(",");

			for(int i=0;i<valList.size();i++){
				if(valList.get(i) !=null && valList.get(i).length() >0){
					con_sql [i] = con_sql[i].replaceAll(conList.get(i), valList.get(i));
					conn.append(" and "+ con_sql [i]);
				}
			}
		}

		if(isempty(valList)){
			sqltext=before+conn+after;
		}
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bsSeachDao.countData(sqltext);
		pm.setTotal(total);
        if(orderValue!=null){
            String head ="SELECT * from(";
            String behind = ")orderTable "+ orderValue;
            sqltext=head+sqltext+behind;
        }
		List<HashMap<String, Object>> tableData = bsSeachDao.findTableData(sqltext, pageIndex,pageSize);
		pm.setDatas(tableData);
		return pm;
	}

	@Override
	public HashMap<String,List<String>> findSeachPullCondition(Integer seachId) {
		List<HashMap<String, Object>>  list = bsSeachDao.findSeach(seachId);
		HashMap<String, Object> map = list.get(0);
		String conditionPullValue = (String)map.get("condition_pull_value");
		HashMap<String, List<String>> colMap = new LinkedHashMap<>();
		if(conditionPullValue!=null ){
			String conditionPullValues[] = conditionPullValue.split("&");
			for(int i =0;i<conditionPullValues.length;i++){
				String pullValues[] =  conditionPullValues[i].split(":");
				if(pullValues.length>1){
					String pullValue = pullValues[1];
					String pullValuess[] = pullValue.split(",");
					List<String> pullValueList = new ArrayList<>();
					for(int k = 0;k<pullValuess.length;k++){
						pullValueList.add(pullValuess[k]);
					}
					colMap.put(pullValues[0],pullValueList);
				}
			}
		}
		return colMap;
	}


	public List<HashMap<String, Object>> findData(PageInfo pi, Integer seachId, List<String> conList,List<String> valList,String orderValue) {
		List<HashMap<String, Object>>  dataList = new ArrayList<>();
		List<HashMap<String, Object>>  list = bsSeachDao.findSeach(seachId);
		if(list == null || list.isEmpty()){

			return dataList;
		}
		HashMap<String, Object> seach  = list.get(0);

		String sqltext = (String) seach.get("sqltext");
		String before=sqltext.substring(0,sqltext.indexOf("1=1")+3);
		String after=sqltext.substring(sqltext.indexOf("1=1")+3,sqltext.length());
		StringBuffer conn =new StringBuffer();
		if(seach.get("condition")!=null){
			String condition_sql = seach.get("condition").toString();
			String con_sql[] = condition_sql.split(",");

			for(int i=0;i<valList.size();i++){
				if(valList.get(i) !=null && valList.get(i).length() >0){

					con_sql [i] = con_sql[i].replaceAll(conList.get(i), valList.get(i));
					conn.append(" and "+ con_sql [i]);
				}
			}
		}

		if(isempty(valList)){
			sqltext=before+conn+after;
		}

		PageModel pm = new PageModel(pi);
		Integer total = bsSeachDao.countData(sqltext);
		if(orderValue!=null){
			String head ="SELECT * from(";
			String behind = ")orderTable "+ orderValue;
			sqltext=head+sqltext+behind;
		}

		List<HashMap<String, Object>> tableData = bsSeachDao.findTableData(sqltext);

		return tableData;
	}

	private boolean isempty(List valList){
		for(int i=0;i<valList.size();i++){
			if(valList.get(i) !=null && valList.get(i).toString().length() >0){
				return true;

			}
		}
		return  false;
	}

	/**
	 * quanjh
	 * @param sqltext
	 * @return
	 */
	public PageModel findDataBySqlText(PageInfo pi, String sqltext){

		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bsSeachDao.countData(sqltext);
		pm.setTotal(total);
		pm.setDatas( bsSeachDao.findTableData(sqltext, pageIndex, pageSize) );

		return pm;
	}

}
