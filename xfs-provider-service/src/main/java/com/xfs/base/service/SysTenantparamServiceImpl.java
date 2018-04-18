package com.xfs.base.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.base.dao.SysTenantparamDAO;
import com.xfs.base.dao.SysTenantparamlistDAO;
import com.xfs.base.model.SysTenantparam;
import com.xfs.base.model.SysTenantparamlist;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

@Service
public class SysTenantparamServiceImpl implements SysTenantparamService {

    private static final Logger log = Logger.getLogger(SysTenantparamServiceImpl.class);
    @Autowired
    private SysTenantparamDAO sysTenantparamDAO;
    @Autowired
    private SysTenantparamlistDAO sysTenantparamlistDAO;

    public void save(ContextInfo cti, SysTenantparam vo) {

        SysTenantparamlist paramlist = new SysTenantparamlist();
        paramlist.setId(vo.getPlistid());
        paramlist.setParamCode(vo.getParamCode());
        paramlist.setTid(vo.getTid());
        paramlist.setRemark(vo.getRemark());
        paramlist.setParamValue(vo.getDefValue());
        sysTenantparamlistDAO.save(cti, paramlist);
        vo.setDefValue(null);
        sysTenantparamDAO.save(cti, vo);
    }

    public void insert(ContextInfo cti, SysTenantparam vo) {
        sysTenantparamDAO.insert(cti, vo);
    }

    public void remove(ContextInfo cti, SysTenantparam vo) {
        sysTenantparamDAO.remove(cti, vo);
    }

    public void update(ContextInfo cti, SysTenantparam vo) {
        sysTenantparamDAO.update(cti, vo);
    }

    public PageModel findPage(PageInfo pi, SysTenantparam vo) {

        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = sysTenantparamDAO.countFreeFind(vo);
        pm.setTotal(total);
        List<SysTenantparam> datas = sysTenantparamDAO.freeFind(vo, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;

    }

    @Override
    public List<SysTenantparam> findAll(SysTenantparam vo) {
        List<SysTenantparam> datas = sysTenantparamDAO.findAll(vo);
        return datas;
    }

    @Override
    public PageModel findByTid(PageInfo pi, SysTenantparam vo) {
        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = sysTenantparamDAO.countFreeFindByTid(vo);
        pm.setTotal(total);
        List<SysTenantparam> datas = sysTenantparamDAO.findByTid(vo, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;
    }

    @Override
    public SysTenantparam findByPK(SysTenantparam vo) {
        return sysTenantparamDAO.findByPK(vo);
    }

    @Override
    public SysTenantparam findByTidAndId(SysTenantparam vo) {
        return sysTenantparamDAO.findByTidAndId(vo);
    }
}
