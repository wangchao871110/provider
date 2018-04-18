package com.xfs.insurance.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.insurance.model.BsCiScheme;

/**
 * BsCiSchemeService
 * @author:quanjiahua<quanjiahua@163.com>
 * 
 * @date:2016/08/29 14:04:27
 */
public interface BsCiSchemeService {

    public int save(ContextInfo cti, BsCiScheme vo);

    public int insert(ContextInfo cti, BsCiScheme vo);

    public int remove(ContextInfo cti, BsCiScheme vo);

    public int update(ContextInfo cti, BsCiScheme vo);

    public PageModel findPage(PageInfo pi, BsCiScheme vo);

    public List<BsCiScheme> findAll(BsCiScheme vo);

    // 温馨提示：
    // 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    // 2016/08/29 14:04:27

}
