package com.xfs.base.service;

import java.util.List;

import com.xfs.base.model.SysTenantparam;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * SysTenantparamService
 *
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2015/12/14 12:28:00
 */
public interface SysTenantparamService {

    public void save(ContextInfo cti, SysTenantparam vo);

    public void insert(ContextInfo cti, SysTenantparam vo);

    public void remove(ContextInfo cti, SysTenantparam vo);

    public void update(ContextInfo cti, SysTenantparam vo);

    public PageModel findPage(PageInfo pi, SysTenantparam vo);

    public List<SysTenantparam> findAll(SysTenantparam vo);

    public PageModel findByTid(PageInfo pi, SysTenantparam vo);

    public SysTenantparam findByPK(SysTenantparam vo);

    public SysTenantparam findByTidAndId(SysTenantparam vo);

}
