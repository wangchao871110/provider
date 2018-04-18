package com.xfs.insurance.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.insurance.dao.BsCiSchemeDao;
import com.xfs.insurance.model.BsCiScheme;
import com.xfs.insurance.service.BsCiSchemeService;

@Service
public class BsCiSchemeServiceImpl implements BsCiSchemeService {

    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(BsCiSchemeServiceImpl.class);

    @Autowired
    private BsCiSchemeDao bsCiSchemeDao;

    public int save(ContextInfo cti, BsCiScheme vo) {

        return bsCiSchemeDao.save(cti, vo);
    }

    public int insert(ContextInfo cti, BsCiScheme vo) {

        return bsCiSchemeDao.insert(cti, vo);
    }

    public int remove(ContextInfo cti, BsCiScheme vo) {

        return bsCiSchemeDao.remove(cti, vo);
    }

    public int update(ContextInfo cti, BsCiScheme vo) {

        return bsCiSchemeDao.update(cti, vo);
    }

    public PageModel findPage(PageInfo pi, BsCiScheme vo) {

        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = bsCiSchemeDao.countFreeFind(vo);
        pm.setTotal(total);
        List<BsCiScheme> datas = bsCiSchemeDao.freeFind(vo, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;

    }

    public List<BsCiScheme> findAll(BsCiScheme vo) {

        List<BsCiScheme> datas = bsCiSchemeDao.freeFind(vo);
        return datas;

    }

    // 温馨提示：
    // 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    // 2016/08/29 14:04:27

}
