package com.xfs.corp.service;
import java.util.List;
import java.util.Map;

import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.common.ContextInfo;
import com.xfs.corp.model.CmCorpOpenApp;

/**
 * CmCorpOpenAppService
 * @author:yangfw@xinfushe.com
 * @date:2017/08/01 14:40:13
 */
public interface CmCorpOpenAppService {
	
	public int save(ContextInfo cti, CmCorpOpenApp vo);
	public int insert(ContextInfo cti, CmCorpOpenApp vo);
	public int remove(ContextInfo cti, CmCorpOpenApp vo);
	public int update(ContextInfo cti, CmCorpOpenApp vo);
	public PageModel findPage(CmCorpOpenApp vo);
	public List<CmCorpOpenApp> findAll(CmCorpOpenApp vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/08/01 14:40:13
	/**
	 *  保存应用开通信息
	 *  @param
	 * @return    :
	 *  @createDate   : 2017/8/1 16:10
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2017/8/1 16:10
	 *  @updateAuthor  :
	 */
	public Result saveInfo(ContextInfo cti, CmCorpOpenApp vo);

	/**
	 * 获取当前企业开通应用信息
	 * @param vo
	 * @return
	 */
	public Map<String,CmCorpOpenApp> queryCurrCmCorpApps(CmCorpOpenApp vo);
}
