package com.xfs.op.service.impl;

import com.xfs.common.ContextInfo;
import com.xfs.common.page.PageModel;
import com.xfs.common.page.SystemContext;
import com.xfs.op.dao.OpHeadlineUserDao;
import com.xfs.op.model.OpHeadlineUser;
import com.xfs.op.service.OpHeadlineUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpHeadlineUserServiceImpl implements OpHeadlineUserService {

    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(OpHeadlineUserServiceImpl.class);

    @Autowired
    private OpHeadlineUserDao opHeadlineUserDao;

    public int save(ContextInfo cti, OpHeadlineUser vo) {
        return opHeadlineUserDao.save(cti, vo);
    }

    public int insert(ContextInfo cti, OpHeadlineUser vo) {
        return opHeadlineUserDao.insert(cti, vo);
    }

    public int remove(ContextInfo cti, OpHeadlineUser vo) {
        return opHeadlineUserDao.remove(cti, vo);
    }

    public int update(ContextInfo cti, OpHeadlineUser vo) {
        return opHeadlineUserDao.update(cti, vo);
    }

    public PageModel findPage(OpHeadlineUser vo) {

        PageModel pm = new PageModel();
        int pageIndex = SystemContext.getOffset();
        int pageSize = SystemContext.getPagesize();
        Integer total = opHeadlineUserDao.countFreeFind(vo);
        pm.setTotal(total);
        List<OpHeadlineUser> datas = opHeadlineUserDao.freeFind(vo, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;

    }

    public List<OpHeadlineUser> findAll(OpHeadlineUser vo) {

        List<OpHeadlineUser> datas = opHeadlineUserDao.freeFind(vo);
        return datas;

    }


    //温馨提示：
    //以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    //2017/02/22 10:59:03


    @Override
    public OpHeadlineUser getHeadlineUserByOpenId(OpHeadlineUser obj) {
        return opHeadlineUserDao.getHeadlineUserByOpenId(obj);
    }

    /**
     * 根据 openId 更新用户信息
     *
     * @param cti
     * @param obj
     * @return
     * @crateDt 2017年4月26日
     */
    @Override
    public int updateHeadlineUserByOpenId(ContextInfo cti, OpHeadlineUser obj) {
        return opHeadlineUserDao.updateHeadlineUserByOpenId(cti, obj);
    }

}
