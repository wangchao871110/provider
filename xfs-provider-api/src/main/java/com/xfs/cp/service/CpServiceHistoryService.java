package com.xfs.cp.service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.cp.model.CpServiceHistory;

import java.util.List;

/**
 * Created by zhao on 2017/1/5.
 *
 */
public interface CpServiceHistoryService {

    public int save(ContextInfo cti, CpServiceHistory vo );
    public int insert(ContextInfo cti, CpServiceHistory vo );
    public int remove(ContextInfo cti, CpServiceHistory vo );
    public int update(ContextInfo cti, CpServiceHistory vo );
    public PageModel findPage(PageInfo pi,CpServiceHistory vo);
    public List<CpServiceHistory> findAll(CpServiceHistory vo);


    //温馨提示：
    //以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    //2017/01/05 14:51:32

    public int backupsForCpService(ContextInfo cti, CpServiceHistory vo);

    public List<CpServiceHistory> findYearOrMonth(CpServiceHistory vo);

}
