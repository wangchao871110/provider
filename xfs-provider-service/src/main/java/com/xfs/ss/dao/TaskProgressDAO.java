package com.xfs.ss.dao;


import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;



@Repository
public class TaskProgressDAO extends BaseSqlMapDao {

	
	
	public List<HashMap> findProgressList(HashMap<String, Object> condition) {
	      return queryForList("SpTaskProgress.findProgressList", condition);
	}

	public List<HashMap<String, Integer>> findAllSpService() {
		 HashMap<String,Object> map = new HashMap<>();
		 return queryForList("SpTaskProgress.findAllSpService", map);
	}

	
	
	public void updateProgressTask(ContextInfo cti, HashMap<String, Object> row) {
        update(cti, "SpTaskProgress.updateProgressTask", row );
	}
	

	public void addProgress(ContextInfo cti, Integer id) {
		 HashMap<String,Object> map = new HashMap<>();
		 map.put("id", id);
		   update(cti, "SpTaskProgress.addProgress", map );
	}

	public void finished(ContextInfo cti, HashMap<String, Object> map) {
		 update(cti, "SpTaskProgress.finished", map );
	}

}
