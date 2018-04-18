package com.xfs.base.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.base.dao.SysDictionaryDAO;
import com.xfs.base.model.SysDictionary;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

@Service
public class SysDictionaryServiceImpl implements SysDictionaryService {

    private static final Logger log = Logger.getLogger(SysDictionaryServiceImpl.class);
    @Autowired
    private SysDictionaryDAO sysDictionaryDAO;

    public void save(ContextInfo cti, SysDictionary vo) {
        sysDictionaryDAO.save(cti, vo);
    }

    public void insert(ContextInfo cti, SysDictionary vo) {
        sysDictionaryDAO.insert(cti, vo);
    }

    public void remove(ContextInfo cti, SysDictionary vo) {
        sysDictionaryDAO.remove(cti, vo);
    }

    public void update(ContextInfo cti, SysDictionary vo) {
        sysDictionaryDAO.update(cti, vo);
    }

    public PageModel findPage(PageInfo pi, SysDictionary vo) {

        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = sysDictionaryDAO.countFreeFind(vo);
        pm.setTotal(total);
        List<SysDictionary> datas = sysDictionaryDAO.freeFind(vo, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;

    }

    public List<SysDictionary> findAll(SysDictionary vo) {

        List<SysDictionary> datas = sysDictionaryDAO.freeFind(vo);
        return datas;

    }

    @Override
    public SysDictionary findByPK(SysDictionary vo) {
        return sysDictionaryDAO.findForItemByPK(vo);
    }

    @Override
    public SysDictionary findByPK(Integer id) {
        SysDictionary vo = new SysDictionary();
        vo.setId(id);
        return sysDictionaryDAO.findForItemByPK(vo);
    }


    @Override
    public SysDictionary findSysDicByName(String name) {
        SysDictionary vo = new SysDictionary();
        vo.setName(name);
        return sysDictionaryDAO.findSysDicByName(vo);
    }
}
