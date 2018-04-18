package com.xfs.base.service;

import java.util.List;

import com.xfs.base.model.SysMailtemplate;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * SysMailtemplateService
 *
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2015/12/08 17:21:48
 */
public interface SysMailtemplateService {

    public void save(ContextInfo cti, SysMailtemplate vo);

    public void insert(ContextInfo cti, SysMailtemplate vo);

    public void remove(ContextInfo cti, SysMailtemplate vo);

    public void update(ContextInfo cti, SysMailtemplate vo);

    public PageModel findPage(PageInfo pi, SysMailtemplate vo);

    public List<SysMailtemplate> findAll(SysMailtemplate vo);

    public SysMailtemplate findByPk(SysMailtemplate mailtemplate);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    public SysMailtemplate findByPk(Integer id);

    /**
     * 根据code获取邮件模板
     *
     * @param code
     * @return
     */
    public SysMailtemplate findByCode(String code);

}
