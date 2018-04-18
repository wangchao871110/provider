package com.xfs.base.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.base.dao.SysMailtemplateDAO;
import com.xfs.base.model.SysMailtemplate;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

@Service
public class SysMailtemplateServiceImpl implements SysMailtemplateService {

    private static final Logger log = Logger.getLogger(SysMailtemplateServiceImpl.class);
    @Autowired
    private SysMailtemplateDAO sysMailtemplateDAO;

    public void save(ContextInfo cti, SysMailtemplate vo) {
        sysMailtemplateDAO.save(cti, vo);
    }

    public void insert(ContextInfo cti, SysMailtemplate vo) {
        sysMailtemplateDAO.insert(cti, vo);
    }

    public void remove(ContextInfo cti, SysMailtemplate vo) {
        sysMailtemplateDAO.remove(cti, vo);
    }

    public void update(ContextInfo cti, SysMailtemplate vo) {
        sysMailtemplateDAO.update(cti, vo);
    }

    public PageModel findPage(PageInfo pi, SysMailtemplate vo) {

        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = sysMailtemplateDAO.countFreeFind(vo);
        pm.setTotal(total);
        List<SysMailtemplate> datas = sysMailtemplateDAO.freeFind(vo, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;

    }

    public List<SysMailtemplate> findAll(SysMailtemplate vo) {

        List<SysMailtemplate> datas = sysMailtemplateDAO.freeFind(vo);
        return datas;

    }

    public SysMailtemplate findByPk(SysMailtemplate mailtemplate) {

        return sysMailtemplateDAO.findByPK(mailtemplate);
    }

    @Override
    public SysMailtemplate findByPk(Integer id) {
        SysMailtemplate mailtemplate = new SysMailtemplate();
        mailtemplate.setId(id);
        return sysMailtemplateDAO.findByPK(mailtemplate);
    }

    @Override
    public SysMailtemplate findByCode(String code) {
        SysMailtemplate mailtemplate = new SysMailtemplate();
        mailtemplate.setTemplatecodeEq(code);
        List<SysMailtemplate> datas = sysMailtemplateDAO.freeFind(mailtemplate);
        if (null != datas && datas.size() != 0)
            return datas.get(0);
        else
            return null;
    }
}
