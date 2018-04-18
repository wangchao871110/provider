package com.xfs.insurance.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.insurance.dao.BsCiProductDao;
import com.xfs.insurance.dao.BsCiSchemeDao;
import com.xfs.insurance.dao.SpsCiAgentPriceDao;
import com.xfs.insurance.dto.CiProduct;
import com.xfs.insurance.dto.CiScheme;
import com.xfs.insurance.model.BsCiProduct;
import com.xfs.insurance.service.BsCiProductService;
import com.xfs.mall.item.dao.BsMallItemDao;
import com.xfs.mall.item.dao.BsMallItemareaDao;
import com.xfs.mall.item.model.BsMallItemarea;

@Service
public class BsCiProductServiceImpl implements BsCiProductService {

    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(BsCiProductServiceImpl.class);

    @Autowired
    private BsCiProductDao bsCiProductDao;

    public int save(ContextInfo cti, BsCiProduct vo) {

        return bsCiProductDao.save(cti, vo);
    }

    public int insert(ContextInfo cti, BsCiProduct vo) {

        return bsCiProductDao.insert(cti, vo);
    }

    public int remove(ContextInfo cti, BsCiProduct vo) {

        return bsCiProductDao.remove(cti, vo);
    }

    public int update(ContextInfo cti, BsCiProduct vo) {

        return bsCiProductDao.update(cti, vo);
    }

    public PageModel findPage(PageInfo pi, BsCiProduct vo) {

        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = bsCiProductDao.countFreeFind(vo);
        pm.setTotal(total);
        List<BsCiProduct> datas = bsCiProductDao.freeFind(vo, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;

    }

    public List<BsCiProduct> findAll(BsCiProduct vo) {

        List<BsCiProduct> datas = bsCiProductDao.freeFind(vo);
        return datas;

    }

    // 温馨提示：
    // 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    // 2016/08/29 14:04:27

    /**
     * 承保方案数据库操作类
     */
    @Autowired
    private BsCiSchemeDao ciSchemeDao;

    /**
     * 商城服务项数据库操作类
     */
    @Autowired
    private BsMallItemDao mallItemDao;

    /**
     * 商城服务项区域数据库操作类
     */
    @Autowired
    private BsMallItemareaDao mallItemareaDao;
	
	    @Autowired
    private BsCiSchemeDao schemeDao;
    @Autowired
    private SpsCiAgentPriceDao ciAgentPriceDao;

    /**
     * 创建商保产品条目
     *
     * @param product
     *            产品
     * @return 创建结果
     */
    @Override
    public boolean createCiProduct(ContextInfo cti,CiProduct product) {

        mallItemDao.save(cti,product);
        BsMallItemarea mallItemarea = new BsMallItemarea();
        mallItemarea.setAreaId(-1);
        mallItemarea.setItemId(product.getItemId());
        mallItemareaDao.insert(cti,mallItemarea);
        product.setMallItemId(product.getItemId());
        bsCiProductDao.insert(cti,product);
        for (CiScheme scheme : JSON.parseArray(product.getSchemes(), CiScheme.class)) {
            scheme.setMallItemId(product.getItemId());
            scheme.setSchemeInsure(JSON.toJSONString(scheme.getInsuredInfo()));
            ciSchemeDao.insert(cti,scheme);
        }
        return true;
    }

    /**
     * 查询商保产品
     * 
     * @param ciProduct
     *            查询条件
     * @return 结果列表
     */
    public PageModel findCiProd(PageInfo pi,CiProduct ciProduct) {

        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = bsCiProductDao.countPaginatedCiProd(ciProduct);
        pm.setTotal(total);
        List<CiProduct> datas = bsCiProductDao.findPaginatedCiProd(ciProduct, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;

    }

    /**
     * 查询单个商保产品信息
     *
     * @param itemId
     *            产品编码
     * @return 产品信息
     */
    @Override
    public CiProduct findCiProductByitemId(Integer itemId) {

        return bsCiProductDao.findCiProductByitemId(itemId);
    }

    /**
     * 更新商保信息
     *
     * @param product
     *            商保信息
     * @return 更新结果
     */
    @Override
    public boolean udpateCiProduct(ContextInfo cti,CiProduct product) {

        mallItemDao.update(cti,product);
        bsCiProductDao.update(cti,product);
        CiScheme ciScheme = new CiScheme();
        ciScheme.setMallItemId(product.getItemId());
        ciSchemeDao.deleteCiSchemeByItemId(cti,product.getItemId());
        for (CiScheme scheme : JSON.parseArray(product.getSchemes(), CiScheme.class)) {
            scheme.setMallItemId(product.getItemId());
            scheme.setSchemeInsure(JSON.toJSONString(scheme.getInsuredInfo()));
            ciSchemeDao.insert(cti,scheme);
        }
        return true;
    }

    /**
     * 查询可用户组合险的商保单品
     *
     * @param ciProduct 查询条件
     * @return 结果列表
     */
    @Override
    public List<CiProduct> findCiProductForGroup(CiProduct ciProduct) {
        return bsCiProductDao.findCiProductForGroup(ciProduct);
    }
	
	  /**
     * 根据编码查询商保信息
     *
     * @param itemId 商保编码
     * @return 商保信息
     */
    @Override
    public CiProduct findCiProdById(Integer itemId) {

        CiProduct ciProduct = new CiProduct();
        ciProduct.setItemId(itemId);
        ciProduct = bsCiProductDao.findCiProdById(ciProduct);
        return ciProduct;
    }

    public List<CiScheme> findCiGroupItemDetail(Integer itemId, Integer prodId) {
        return bsCiProductDao.findCiGroupItemDetail(itemId, prodId);
    }
	
	    /**
     * 查询商保产品
     *
     * @param ciProduct 查询条件
     * @return 结果列表
     */
    @Override
    public List<CiProduct> findCiProdForAgent(CiProduct ciProduct) {

        List<CiProduct> list = bsCiProductDao.findCiProd(ciProduct);
        if (null != list && !list.isEmpty()) {
            List<Integer> itemIds = new ArrayList<>();//未代理列表
            Map<Integer, CiProduct> cache = new HashMap<>();
            for (CiProduct product : list) {
                if (null != product.getProductId()) {
                    product.setSchemeList(ciAgentPriceDao.findCiItemPrice(product));
                } else {
                    itemIds.add(product.getItemId());
                }
                cache.put(product.getItemId(), product);
            }
            if (itemIds.isEmpty()) {
                return list;
            }
            List<CiScheme> schemeList = schemeDao.findBsCiSchemeByMallItemIds(itemIds);
            for (CiScheme ciScheme : schemeList) {
                cache.get(ciScheme.getMallItemId()).addCiScheme(ciScheme);
            }

        }
        return list;
    }
}
