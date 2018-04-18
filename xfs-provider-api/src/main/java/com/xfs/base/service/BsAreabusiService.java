package com.xfs.base.service;

import java.util.List;
import java.util.Map;

import com.xfs.base.model.BsAreabusi;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * BsAreabusiService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/06/11 16:39:16
 */
public interface BsAreabusiService {
	
	public int save(ContextInfo cti, BsAreabusi vo);
	public int insert(ContextInfo cti, BsAreabusi vo);
	public int remove(ContextInfo cti, BsAreabusi vo);
	public int update(ContextInfo cti, BsAreabusi vo);
	public PageModel findPage(PageInfo pi, BsAreabusi vo);
	public List<BsAreabusi> findAll(BsAreabusi vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/11 16:39:16

	public List<BsAreabusi> findOpenCityAll();

	public  BsAreabusi getBsAreabusi(BsAreabusi bsAreabusi);

	public BsAreabusi findBsAreabusiMax();

	
	/**
	 * 通过业务类型查询城市列表
	 *
	 * @param vo
	 * @return
	 */
	public List<Map<String, Object>> findAreasByBusi(BsAreabusi vo);
	
}
