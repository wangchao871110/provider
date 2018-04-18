package com.xfs.base.service;

import java.util.List;

import com.xfs.base.model.SysTenantparamlist;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * SysTenantparamlistService
 *
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2015/12/14 12:28:00
 */
public interface SysTenantparamlistService {

    public void save(ContextInfo cti, SysTenantparamlist vo);

    public void insert(ContextInfo cti, SysTenantparamlist vo);

    public void remove(ContextInfo cti, SysTenantparamlist vo);

    public void update(ContextInfo cti, SysTenantparamlist vo);

    public PageModel findPage(PageInfo pi, SysTenantparamlist vo);

    public List<SysTenantparamlist> findAll(SysTenantparamlist vo);

    public SysTenantparamlist findByPK(SysTenantparamlist vo);

    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    public SysTenantparamlist findByPK(Integer id);

    /**
     * 根据code获取
     *
     * @param paramCode
     * @return
     */
    public SysTenantparamlist findByCode(String paramCode);

	List<SysTenantparamlist> findByPartCode(String paramCode);

}
