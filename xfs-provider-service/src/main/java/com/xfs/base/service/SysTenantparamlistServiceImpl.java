package com.xfs.base.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.base.dao.SysTenantparamlistDAO;
import com.xfs.base.model.SysTenantparamlist;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

@Service
public class SysTenantparamlistServiceImpl implements SysTenantparamlistService {

    private static final Logger log = Logger.getLogger(SysTenantparamlistServiceImpl.class);
    @Autowired
    private SysTenantparamlistDAO sysTenantparamlistDAO;

    public void save(ContextInfo cti, SysTenantparamlist vo) {
        sysTenantparamlistDAO.save(cti, vo);
    }

    public void insert(ContextInfo cti, SysTenantparamlist vo) {
        sysTenantparamlistDAO.insert(cti, vo);
    }

    public void remove(ContextInfo cti, SysTenantparamlist vo) {
        sysTenantparamlistDAO.remove(cti, vo);
    }

    public void update(ContextInfo cti, SysTenantparamlist vo) {
        sysTenantparamlistDAO.update(cti, vo);
    }

    public PageModel findPage(PageInfo pi, SysTenantparamlist vo) {

        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = sysTenantparamlistDAO.countFreeFind(vo);
        pm.setTotal(total);
        List<SysTenantparamlist> datas = sysTenantparamlistDAO.freeFind(vo, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;

    }

    public List<SysTenantparamlist> findAll(SysTenantparamlist vo) {

        List<SysTenantparamlist> datas = sysTenantparamlistDAO.freeFind(vo);
        return datas;

    }

    @Override
    public SysTenantparamlist findByPK(SysTenantparamlist vo) {
        return sysTenantparamlistDAO.findByPK(vo);
    }

    @Override
    public SysTenantparamlist findByPK(Integer id) {
        SysTenantparamlist vo = new SysTenantparamlist();
        vo.setId(id);
        return sysTenantparamlistDAO.findByPK(vo);
    }

    @Override
    public SysTenantparamlist findByCode(String paramCode) {
        SysTenantparamlist vo = new SysTenantparamlist();
        vo.setParamCodeEq(paramCode);
        List<SysTenantparamlist> datas = sysTenantparamlistDAO.freeFind(vo);
        if (null != datas && datas.size() != 0)
            return datas.get(0);
        else
            return null;
    }
    @Override
    public List<SysTenantparamlist> findByPartCode(String paramCode) {
        SysTenantparamlist vo = new SysTenantparamlist();
        vo.setParamCode(paramCode);
        List<SysTenantparamlist> datas = sysTenantparamlistDAO.freeFind(vo);
        if (null != datas && datas.size() != 0)
            return datas;
        else
            return null;
    }
}
