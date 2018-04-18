package com.xfs.cp.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.cp.model.CpArea;

/**
 * CpAreaService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/10 16:19:46
 */
public interface CpAreaService {
	
	public int save(ContextInfo cti, CpArea vo);
	public int insert(ContextInfo cti, CpArea vo);
	public int remove(ContextInfo cti, CpArea vo);
	public int update(ContextInfo cti, CpArea vo);
	public PageModel findPage(PageInfo pi, CpArea vo);
	public List<CpArea> findAll(CpArea vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/10 16:19:46
	/**
	 * 
	 * @method comments: 获取发包城市
	 * @author   name  : wangchao  
	 * @create   time  : 2016年9月26日 下午8:05:41 
	 * @param cpArea
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月26日 下午8:05:41 wangchao 创建
	 *
	 */
	public List<CpArea> findCityByPackage(CpArea cpArea);
	/**
	 * 跟拼音获取ID
	 *  @param areaPinyin
	 *  @return 
	 *	@return			: CpArea 
	 *  @createDate		: 2016年11月21日 下午2:08:49
	 *  @author			: wangchao 
	 *  @version		: v1.0
	 *  @updateDate		: 2016年11月21日 下午2:08:49
	 *  @updateAuthor  	:
	 */
	public CpArea getAreaByPinyin(String areaPinyin);
	
}
