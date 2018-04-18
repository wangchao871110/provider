package com.xfs.insurance.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.insurance.dao.CpCiProdDao;
import com.xfs.insurance.dto.CiScheme;
import com.xfs.insurance.dto.CpCiProdDetail;
import com.xfs.insurance.model.CpCiProd;
import com.xfs.insurance.service.CpCiProdService;

@Service
public class CpCiProdServiceImpl implements CpCiProdService {

    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(CpCiProdServiceImpl.class);

    @Autowired
    private CpCiProdDao cpCiProdDao;

    public int save(ContextInfo cti, CpCiProd vo) {

        return cpCiProdDao.save(cti, vo);
    }

    public int insert(ContextInfo cti, CpCiProd vo) {

        return cpCiProdDao.insert(cti, vo);
    }

    public int remove(ContextInfo cti, CpCiProd vo) {

        return cpCiProdDao.remove(cti, vo);
    }

    public int update(ContextInfo cti, CpCiProd vo) {

        return cpCiProdDao.update(cti, vo);
    }

    public PageModel findPage(PageInfo pi, CpCiProd vo) {

        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = cpCiProdDao.countFreeFind(vo);
        pm.setTotal(total);
        List<CpCiProd> datas = cpCiProdDao.freeFind(vo, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;

    }

    public List<CpCiProd> findAll(CpCiProd vo) {

        List<CpCiProd> datas = cpCiProdDao.freeFind(vo);
        return datas;

    }

    // 温馨提示：
    // 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    // 2016/09/21 15:45:17


    /**
     * 商保商品上架
     *
     * @param vo 商保商品
     * @return 操作结果
     */
    @Override
    public int ciProdIn(ContextInfo cti,CpCiProd vo) {
        vo.setDr(0);
        CpCiProd prod = new CpCiProd();
        prod.setCiCode(vo.getCiCode());
        List<CpCiProd> list = cpCiProdDao.freeFind(prod);
        if (null == list || list.isEmpty()) {
            return cpCiProdDao.insert(cti, vo);
        } else {
            vo.setId(list.get(0).getId());
            return cpCiProdDao.update(cti, vo);
        }
    }

    /**
     * 商保产品下架
     *
     * @param ciCode 商保产品编码
     * @return
     */
    @Override
    public int ciProdOut(ContextInfo cti,Integer ciCode) {
        CpCiProd prod = new CpCiProd();
        prod.setCiCode(ciCode);
        List<CpCiProd> list = cpCiProdDao.freeFind(prod);
        if (null == list || list.isEmpty()) {
            return 1;
        } else {
            prod = list.get(0);
            prod.setDr(1);
            return cpCiProdDao.update(cti,prod);
        }
    }

    /**
     * 查询商保产品
     *
     * @return 商保产品列表
     */
    @Override
    public List<CpCiProdDetail> findCiProd() {

        List<CpCiProdDetail> list = cpCiProdDao.findCiProd();
        List<Integer> itemIds = new ArrayList<>();
        for (CpCiProdDetail product : list) {
            itemIds.add(product.getItemId());
        }
        if (CollectionUtils.isNotEmpty(itemIds)) {
            List<CiScheme> schemes = cpCiProdDao.findCiScheme(itemIds);
            for (CiScheme scheme : schemes) {
                for (CpCiProdDetail product : list) {
                    if (product.getItemId().equals(scheme.getMallItemId())) {
                        product.addScheme(scheme);
                    }
                }
            }
        }
        return list;
    }

    /**
     * 根据商品编码查询商品详情
     *
     * @param prodId 商品编码
     * @return 商品信息
     */
    @Override
    public CpCiProdDetail findCiProdById(Integer prodId) {

        CpCiProdDetail product = cpCiProdDao.findCiProdById(prodId);
        List<Integer> list = new ArrayList<>();
        list.add(prodId);
        List<CiScheme> schemes = cpCiProdDao.findCiScheme(list);
        product.setSchemeList(schemes);
        return product;
    }

}
