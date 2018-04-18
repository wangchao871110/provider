package com.xfs.sp.service;

import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.sp.model.SpsServicearea;

/**
 * SpsServiceareaService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/09 14:08:08
 */
public interface SpsServiceareaService {
	
	public int save(ContextInfo cti, SpsServicearea vo);
	public int insert(ContextInfo cti, SpsServicearea vo);
	public int remove(ContextInfo cti, SpsServicearea vo);
	public int update(ContextInfo cti, SpsServicearea vo);
	public PageModel findPage(PageInfo pi, SpsServicearea vo);
	public List<SpsServicearea> findAll(SpsServicearea vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/09 14:08:08

	public List<Map> FreeFindAreaBySpId(Integer spId);
	
	/**
	 * 服务商的服务区域
	 *
	 * @param vo
	 * @return
	 */
	public List<Map<String, String>> findServiceAreas(SpsServicearea vo);
	public List<Map<String, Object>> findServiceAreasBySpId(String spId);

	public List<Map> freeFindGROUP(String spId);
	public List<Map> freeFindCOOPAndINNER(String spId);


	public List<Map> freeFindSpsServiceArea(SpsServicearea vo);

	public  int removeSpsServiceArea(ContextInfo cti,String spId,String areaId);

	public Result saveSpsServiceArea(ContextInfo cti, SpsServicearea vo);

	public Map<String,String> findSpsServicearea(SpsServicearea vo);


}
