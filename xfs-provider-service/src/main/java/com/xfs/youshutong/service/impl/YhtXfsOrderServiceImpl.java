package com.xfs.youshutong.service.impl;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.youshutong.dao.YhtXfsOrderDao;
import com.xfs.youshutong.model.YhtXfsOrder;
import com.xfs.youshutong.service.YhtXfsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * YhtXfsOrderServiceImpl 服务层接口实现类
 *
 * @date:2017/05/22 15:53:46
 */
@Service
public class YhtXfsOrderServiceImpl implements YhtXfsOrderService {

    @Autowired
    private YhtXfsOrderDao yhtXfsOrderDao;

    @Override
    public int countFindAll() {
        return yhtXfsOrderDao.countFindAll();
    }

    @Override
    public List<YhtXfsOrder> freeFind(YhtXfsOrder obj) {
        return yhtXfsOrderDao.freeFind(obj);
    }

    @Override
    public int countFreeFind(YhtXfsOrder obj) {
        return yhtXfsOrderDao.countFreeFind(obj);
    }

    @Override
    public YhtXfsOrder findByPK(YhtXfsOrder obj) {
        return yhtXfsOrderDao.findByPK(obj);
    }

    @Override
    public int insert(ContextInfo cti, YhtXfsOrder obj) {
        return yhtXfsOrderDao.insert(cti, obj);
    }

    @Override
    public int update(ContextInfo cti, YhtXfsOrder obj) {
        return yhtXfsOrderDao.update(cti, obj);
    }

    @Override
    public int remove(ContextInfo cti, YhtXfsOrder obj) {
        return yhtXfsOrderDao.remove(cti, obj);
    }

    public PageModel findYhtXfsOrder(PageInfo pi, YhtXfsOrder yhtXfsOrder){
        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = yhtXfsOrderDao.countFreeFind(yhtXfsOrder);
        pm.setTotal(total);
        List<YhtXfsOrder> datas = yhtXfsOrderDao.findYhtXfsOrder(yhtXfsOrder,pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;
    }

}
