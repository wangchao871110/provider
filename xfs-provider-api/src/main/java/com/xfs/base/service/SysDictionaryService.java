package com.xfs.base.service;

import java.util.List;

import com.xfs.base.model.SysDictionary;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * SysDictionaryService
 *
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2015/12/10 17:19:50
 */
public interface SysDictionaryService {

    public void save(ContextInfo cti, SysDictionary vo);

    public void insert(ContextInfo cti, SysDictionary vo);

    public void remove(ContextInfo cti, SysDictionary vo);

    public void update(ContextInfo cti, SysDictionary vo);

    public PageModel findPage(PageInfo pi, SysDictionary vo);

    public List<SysDictionary> findAll(SysDictionary vo);

    public SysDictionary findByPK(SysDictionary vo);

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return
     */
    public SysDictionary findByPK(Integer id);

    public  SysDictionary findSysDicByName(String name);

}
