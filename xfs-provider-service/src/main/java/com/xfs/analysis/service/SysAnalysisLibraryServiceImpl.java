package com.xfs.analysis.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xfs.analysis.dao.SysAnalysisLibraryDao;
import com.xfs.analysis.dto.SysAnalysisLibrary;
import com.xfs.analysis.model.AnalysisTitle;
import com.xfs.common.ContextInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.page.PageModel;
import com.xfs.common.page.SystemContext;


@Service
public class SysAnalysisLibraryServiceImpl implements SysAnalysisLibraryService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SysAnalysisLibraryServiceImpl.class);
	
	@Autowired
	private SysAnalysisLibraryDao sysAnalysisLibraryDao;
	
	public int save(ContextInfo cti, SysAnalysisLibrary vo ){
		return sysAnalysisLibraryDao.save(cti,vo);
	}
	public int insert(ContextInfo cti, SysAnalysisLibrary vo ){
		return sysAnalysisLibraryDao.insert(cti,vo);
	}
	public int remove(ContextInfo cti, SysAnalysisLibrary vo ){
		return sysAnalysisLibraryDao.remove(cti,vo);
	}
	public int update(ContextInfo cti, SysAnalysisLibrary vo ){
		return sysAnalysisLibraryDao.update(cti,vo);
	}
	public PageModel findPage(SysAnalysisLibrary vo){
		
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		Integer total = sysAnalysisLibraryDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SysAnalysisLibrary> datas = sysAnalysisLibraryDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SysAnalysisLibrary> findAll(SysAnalysisLibrary vo){
		
		List<SysAnalysisLibrary> datas = sysAnalysisLibraryDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/03/21 15:26:29

	/**
	 *  获取当前解析业务下的所有操作对应的字段信息列表
	 *  @param   analysisType
	 *	@return 			: java.util.Map<java.lang.String,java.util.List<com.xfs.analysis.model.AnalysisTitle>>
	 *  @createDate  	: 2017-03-22 11:25
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-03-22 11:25
	 *  @updateAuthor  :
	 */
	public Map<String,List<AnalysisTitle>> findAllBusTypeLibrary(String analysisType){
		List<Map<String,Object>> bustypelibs = sysAnalysisLibraryDao.findAllBusTypeLibrary(analysisType);
		Map<String,List<AnalysisTitle>> blibs = new HashMap<>();
		for(Map<String,Object> lib : bustypelibs){
			if(null == blibs.get(String.valueOf(lib.get("bus_type"))))
				blibs.put(String.valueOf(lib.get("bus_type")),new ArrayList<AnalysisTitle>());
			AnalysisTitle title = new AnalysisTitle();
			title.setName(String.valueOf(lib.get("name")));
			title.setTitle(String.valueOf(lib.get("title")));
			title.setSimilar(String.valueOf(lib.get("similar")));
			title.setRequisite(Integer.parseInt(String.valueOf(lib.get("requisite"))));
			title.setShow(Integer.parseInt(String.valueOf(lib.get("show"))));
			title.setRank(Integer.parseInt(String.valueOf(lib.get("rank"))));
			title.setBusTypeId(Integer.parseInt(String.valueOf(lib.get("bus_type_id"))));
			blibs.get(String.valueOf(lib.get("bus_type"))).add(title);
		}
		return blibs;
	}
	
}
