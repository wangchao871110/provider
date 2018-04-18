package com.xfs.insurance.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.insurance.dao.BsCiGroupDao;
import com.xfs.insurance.dao.BsCiGroupItemDao;
import com.xfs.insurance.dao.BsCiSchemeDao;
import com.xfs.insurance.dto.CiGroup;
import com.xfs.insurance.dto.CiProduct;
import com.xfs.insurance.dto.CiScheme;
import com.xfs.insurance.model.BsCiGroup;
import com.xfs.insurance.model.BsCiGroupItem;
import com.xfs.insurance.service.BsCiGroupService;

@Service
public class BsCiGroupServiceImpl implements BsCiGroupService {

    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(BsCiGroupServiceImpl.class);

    @Autowired
    private BsCiGroupDao bsCiGroupDao;

    public int save(ContextInfo cti, BsCiGroup vo) {
        return bsCiGroupDao.save(cti, vo);
    }

    public int insert(ContextInfo cti, BsCiGroup vo) {
        return bsCiGroupDao.insert(cti, vo);
    }

    public int remove(ContextInfo cti, BsCiGroup vo) {
        return bsCiGroupDao.remove(cti, vo);
    }

    public int update(ContextInfo cti, BsCiGroup vo) {
        return bsCiGroupDao.update(cti, vo);
    }

    public PageModel findPage(PageInfo pi, BsCiGroup vo) {

        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = bsCiGroupDao.countFreeFind(vo);
        pm.setTotal(total);
        List<BsCiGroup> datas = bsCiGroupDao.freeFind(vo, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;

    }

    public List<BsCiGroup> findAll(BsCiGroup vo) {

        List<BsCiGroup> datas = bsCiGroupDao.freeFind(vo);
        return datas;

    }


    //温馨提示：
    //以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    //2016/09/06 18:33:04
    @Autowired
    private BsCiGroupItemDao ciGroupItemDao;
    @Autowired
    private BsCiSchemeDao ciSchemeDao;

    /**
     * 组合险发布
     *
     * @param ciGroup 组合险信息
     * @return
     */
    @Override
    public boolean publishCiGroup(ContextInfo cti,CiGroup ciGroup) {
        ciGroup.setDr(0);
        bsCiGroupDao.insert(cti,ciGroup);
        for (Integer itemId : ciGroup.getChildItems()) {
            BsCiGroupItem item = new BsCiGroupItem();
            item.setGroupId(ciGroup.getGroupId());
            item.setMallItemId(itemId);
            ciGroupItemDao.insert(cti,item);
        }
        return true;
    }

    /**
     * 修改组合险信息
     *
     * @param ciGroup 组合险信息
     * @return 修改结果
     */
    @Override
    public boolean modifyCiGroup(ContextInfo cti,CiGroup ciGroup) {
        bsCiGroupDao.update(cti,ciGroup);
        ciGroupItemDao.deleteAllGroupItems(cti,ciGroup);
        for (Integer itemId : ciGroup.getChildItems()) {
            BsCiGroupItem item = new BsCiGroupItem();
            item.setMallItemId(itemId);
            item.setGroupId(ciGroup.getGroupId());
            ciGroupItemDao.insert(cti,item);
        }
        return true;
    }

    /**
     * 通过主键查询商保组合信息
     *
     * @param groupId
     * @return
     */
    @Override
    public BsCiGroup findByPk(Integer groupId) {
        BsCiGroup group = new BsCiGroup();
        group.setGroupId(groupId);
        return bsCiGroupDao.findByPK(group);
    }

    /**
     * 查找含有代理信息的组合列表
     *
     * @param ciGroup 查询条件
     * @return
     */
    @Override
    public List<CiGroup> findAllCIGroupWithSp(CiGroup ciGroup) {

        List<CiGroup> list = bsCiGroupDao.findAllCIGroupWithSp(ciGroup);
        Map<Integer, CiGroup> map = new HashMap<>();
        List<Integer> prodIdList = new ArrayList<>();
        prodIdList.add(-1);
        for (CiGroup group : list) {
            map.put(group.getGroupId(), group);
            if (null != group.getProductId()) {
                prodIdList.add(group.getProductId());
            }
        }
        List<Integer> itemIdList = new ArrayList<>();
        if (!prodIdList.isEmpty()) {
            List<CiProduct> ciProducts = ciGroupItemDao.findAllGroupItemsDetail(prodIdList);
            for (CiProduct product : ciProducts) {
                if (null != map.get(product.getGroupId())) {
                    map.get(product.getGroupId()).addItem(product);
                }
                if ("CI".equals(product.getCategoryType())) {
                    itemIdList.add(product.getItemId());
                }
            }
            if (!itemIdList.isEmpty()) {
                List<CiScheme> schemeList = ciSchemeDao.findBsCiSchemeByMallItemIds(itemIdList);
                for (CiScheme scheme : schemeList) {
                    for (CiProduct product : ciProducts) {
                        if (product.getItemId().equals(scheme.getMallItemId())) {
                            product.addCiScheme(scheme);
                        }
                    }
                }
            }
        }
        return list;
    }
}
