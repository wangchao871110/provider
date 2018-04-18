package com.xfs.business.service;

import com.xfs.business.model.BdBsareatypeProcess;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

import java.util.List;

/**
 * 业务流程详细
 * Created by zhao on 2017/3/21.
 */
public interface BdBsareatypeProcessService {

    public int save(ContextInfo cti, BdBsareatypeProcess vo);
    public int insert(ContextInfo cti, BdBsareatypeProcess vo);
    public int remove(ContextInfo cti, BdBsareatypeProcess vo);
    public int update(ContextInfo cti, BdBsareatypeProcess vo);
    public PageModel findPage(PageInfo pi, BdBsareatypeProcess vo);
    public List<BdBsareatypeProcess> findAll(BdBsareatypeProcess vo);

    public List<BdBsareatypeProcess> findAlWithDictitem(BdBsareatypeProcess vo);
}
