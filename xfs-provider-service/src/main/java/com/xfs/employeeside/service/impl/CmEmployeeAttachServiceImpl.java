package com.xfs.employeeside.service.impl;

import com.xfs.common.ContextInfo;
import com.xfs.employeeside.dao.CmEmployeeAttachDao;
import com.xfs.employeeside.model.CmEmployeeAttach;
import com.xfs.employeeside.service.CmEmployeeAttachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CmEmployeeAttachServiceImpl 服务层接口实现类
 *
 * @date:2017/03/17 11:35:04
 */
@Service
public class CmEmployeeAttachServiceImpl implements CmEmployeeAttachService {

    @Autowired
    private CmEmployeeAttachDao cmEmployeeAttachDao;

    @Override
    public int countFindAll() {
        return cmEmployeeAttachDao.countFindAll();
    }

    @Override
    public List<CmEmployeeAttach> freeFind(CmEmployeeAttach obj) {
        return cmEmployeeAttachDao.freeFind(obj);
    }

    @Override
    public int countFreeFind(CmEmployeeAttach obj) {
        return cmEmployeeAttachDao.countFreeFind(obj);
    }

    @Override
    public CmEmployeeAttach findByPK(CmEmployeeAttach obj) {
        return cmEmployeeAttachDao.findByPK(obj);
    }

    @Override
    public int insert(ContextInfo info, CmEmployeeAttach obj) {
        return cmEmployeeAttachDao.insert(info, obj);
    }

    @Override
    public int update(ContextInfo info, CmEmployeeAttach obj) {
        return cmEmployeeAttachDao.update(info, obj);
    }

    @Override
    public int remove(ContextInfo info, CmEmployeeAttach obj) {
        return cmEmployeeAttachDao.remove(info, obj);
    }


}
