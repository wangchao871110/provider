package com.xfs.analysis.service;
import java.util.List;
import java.util.Map;

import com.xfs.analysis.dto.SysAnalysisLibrary;
import com.xfs.analysis.model.AnalysisTitle;
import com.xfs.common.ContextInfo;
import com.xfs.common.page.PageModel;

/**
 * SysAnalysisLibraryService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2017/03/21 15:26:29
 */
public interface SysAnalysisLibraryService {
	
	public int save(ContextInfo cti, SysAnalysisLibrary vo);
	public int insert(ContextInfo cti,SysAnalysisLibrary vo);
	public int remove(ContextInfo cti,SysAnalysisLibrary vo);
	public int update(ContextInfo cti,SysAnalysisLibrary vo);
	public PageModel findPage(SysAnalysisLibrary vo);
	public List<SysAnalysisLibrary> findAll(SysAnalysisLibrary vo);
	
	
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
	public Map<String,List<AnalysisTitle>> findAllBusTypeLibrary(String analysisType);
}
