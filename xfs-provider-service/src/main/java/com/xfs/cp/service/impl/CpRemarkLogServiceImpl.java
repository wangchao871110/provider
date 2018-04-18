package com.xfs.cp.service.impl;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.cp.dao.CpRemarkLogDao;
import com.xfs.cp.model.CpRemarkLog;
import com.xfs.cp.service.CpRemarkLogService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by zhao on 2016/12/12.
 */
@Service
public class CpRemarkLogServiceImpl implements CpRemarkLogService {



    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(CpRemarkLogServiceImpl.class);


    @Autowired
    private CpRemarkLogDao cpRemarkLogDao;

    public int save(ContextInfo cti, CpRemarkLog vo ){
        return cpRemarkLogDao.save(cti, vo);
    }
    public int insert(ContextInfo cti,CpRemarkLog vo ){
        return cpRemarkLogDao.insert(cti, vo);
    }
    public int remove(ContextInfo cti,CpRemarkLog vo ){
        return cpRemarkLogDao.remove(cti, vo);
    }
    public int update(ContextInfo cti,CpRemarkLog vo ){
        return cpRemarkLogDao.update(cti, vo);
    }

    public List<CpRemarkLog> findAll(CpRemarkLog vo){

        List datas = cpRemarkLogDao.freeFind(vo);
        return datas;

    }



    //温馨提示：
    //以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    //2016/12/09 18:44:18


    public PageModel findSysUser(PageInfo pi, CpRemarkLog vo){
        PageModel pm = new PageModel(pi);
        Integer pageIndex = pi.getOffset();
        Integer pageSize = pi.getPagesize();
        List<Map<String, Object>> datas = cpRemarkLogDao.findSysUser(vo,pageIndex,pageSize);
        pm.setDatas(datas);
        return pm;

    }
}
