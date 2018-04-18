package com.xfs.op.service;
import java.util.List;
import java.util.Map;

import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.common.ContextInfo;
import com.xfs.op.model.OpHrplusAdvert;
import com.xfs.op.model.OpHrplusHotWords;

/**
 * OpHrplusHotWordsService
 * @author:yangfw@xinfushe.com
 * @date:2017/03/09 10:56:41
 */
public interface OpHrplusHotWordsService {
	
	public int save(ContextInfo cti, OpHrplusHotWords vo);
	public int insert(ContextInfo cti, OpHrplusHotWords vo);
	public int remove(ContextInfo cti, OpHrplusHotWords vo);
	public int update(ContextInfo cti, OpHrplusHotWords vo);
	public PageModel findPage(OpHrplusHotWords vo);
	public List<OpHrplusHotWords> findAll(OpHrplusHotWords vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/03/09 10:56:41



    /**
     * 使用主键
     * @param obj
     * @return
     */
    public OpHrplusHotWords findByPK(OpHrplusHotWords obj);

    /**
     *  热词 批量查询
     *  @param   pageInfo, query
     * @return    : com.xfs.common.page.PageModel
     *  @createDate   : 2017/1/12 14:21
     *  @author          : yangfw@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2017/1/12 14:21
     *  @updateAuthor  :
     */
    public PageModel findManagePage(PageInfo pageInfo, Map<String, Object> query);
	
}
