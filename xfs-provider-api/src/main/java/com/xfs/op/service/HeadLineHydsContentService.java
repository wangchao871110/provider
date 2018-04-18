package com.xfs.op.service;
import java.util.List;
import java.util.Map;

import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.common.ContextInfo;
import com.xfs.op.model.HeadLineHydsContent;

/**
 * HeadLineHydsContentService
 * @author:yangfw@xinfushe.com
 * @date:2017/05/02 16:19:11
 */
public interface HeadLineHydsContentService {
	
	public int save(ContextInfo cti, HeadLineHydsContent vo);
	public int insert(ContextInfo cti, HeadLineHydsContent vo);
	public int remove(ContextInfo cti, HeadLineHydsContent vo);
	public int update(ContextInfo cti, HeadLineHydsContent vo);
	public PageModel findPage(HeadLineHydsContent vo);
	public List<HeadLineHydsContent> findAll(HeadLineHydsContent vo);

    public HeadLineHydsContent findByPK(HeadLineHydsContent obj);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/05/02 16:19:11




    /**
     * 分页查询
     * @return
     */
    public PageModel findBySearchQueryMap(PageInfo pageInfo, HeadLineHydsContent query);



}
