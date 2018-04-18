package com.xfs.openlist.service;

import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.openlist.model.SpsOlCorpset;

/**
 * SpsOlCorpsetService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/04/09 16:45:56
 */
public interface SpsOlCorpsetService {
	
	public int save(ContextInfo cti, SpsOlCorpset vo);
	public int insert(ContextInfo cti, SpsOlCorpset vo);
	public int remove(ContextInfo cti, SpsOlCorpset vo);
	public int update(ContextInfo cti, SpsOlCorpset vo);
	public PageModel findPage(PageInfo pi, SpsOlCorpset vo);
	public List<SpsOlCorpset> findAll(SpsOlCorpset vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/04/09 16:45:56

    /**
     * 根据服务商ID获取 openlist企业联系人设置
     *  @param    : sp_id 服务商id
     *  @return    :  List<Map<String, Object>>
     *  @createDate   : 16-11-13 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-13 上午10:47
     *  @updateAuthor  :
     */
	public List<Map<String,Object>> findCorpsetList(int sp_id);

    /**
     *  根据员工信息查询企业联系人信息
     *  @param    : mobile 服务商id
     *  @param    ：cpid  企业id
     *  @return    :  Map<String,Object>
     *  @createDate   : 16-11-13 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-13 上午10:47
     *  @updateAuthor  :
     */
	public Map<String,Object> findCorpsetByCmPerson(String mobile, Integer cpid);
	
}
