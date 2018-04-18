package com.xfs.cp.service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.cp.model.CpRemarkLog;

import java.util.List;

/**
 * Created by zhao on 2016/12/12.
 */
public interface CpRemarkLogService {

    public int save(ContextInfo cti, CpRemarkLog vo);
    public int insert(ContextInfo cti,CpRemarkLog vo);
    public int remove(ContextInfo cti,CpRemarkLog vo);
    public int update(ContextInfo cti,CpRemarkLog vo);
    public List findAll(CpRemarkLog vo);


    //温馨提示：
    //以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    //2016/12/09 18:44:18

    public PageModel findSysUser(PageInfo pi, CpRemarkLog vo);
}
