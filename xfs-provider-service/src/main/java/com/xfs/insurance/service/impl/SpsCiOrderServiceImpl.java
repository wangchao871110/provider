package com.xfs.insurance.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.corp.model.CmCorp;
import com.xfs.insurance.dao.SpsCiAgentPriceDao;
import com.xfs.insurance.dao.SpsCiOrderDao;
import com.xfs.insurance.dto.CiOrderInfo;
import com.xfs.insurance.dto.CiProduct;
import com.xfs.insurance.dto.CiScheme;
import com.xfs.insurance.model.SpsCiOrder;
import com.xfs.insurance.service.SpsCiOrderService;
import com.xfs.mall.order.dao.SpsMallOrderAreaDao;
import com.xfs.mall.order.dao.SpsMallOrderDao;
import com.xfs.mall.order.dao.SpsMallOrderItemDao;
import com.xfs.mall.order.model.SpsMallOrderArea;
import com.xfs.mall.order.model.SpsMallOrderItem;
import com.xfs.mall.order.service.SpsMallOrderService;
import com.xfs.mall.product.dao.SpsMallProductDao;
import com.xfs.mall.product.model.SpsMallProduct;
import com.xfs.sp.dao.SpsServiceareaDao;
import com.xfs.user.model.SysUser;

@Service
public class SpsCiOrderServiceImpl implements SpsCiOrderService {

    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(SpsCiOrderServiceImpl.class);

    @Autowired
    private SpsCiOrderDao spsCiOrderDao;

    public int save(ContextInfo cti, SpsCiOrder vo) {
        return spsCiOrderDao.save(cti, vo);
    }

    public int insert(ContextInfo cti, SpsCiOrder vo) {
        return spsCiOrderDao.insert(cti, vo);
    }

    public int remove(ContextInfo cti, SpsCiOrder vo) {
        return spsCiOrderDao.remove(cti, vo);
    }

    public int update(ContextInfo cti, SpsCiOrder vo) {
        return spsCiOrderDao.update(cti, vo);
    }

    public PageModel findPage(PageInfo pi, SpsCiOrder vo) {

        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = spsCiOrderDao.countFreeFind(vo);
        pm.setTotal(total);
        List<SpsCiOrder> datas = spsCiOrderDao.freeFind(vo, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;

    }

    public List<SpsCiOrder> findAll(SpsCiOrder vo) {

        List<SpsCiOrder> datas = spsCiOrderDao.freeFind(vo);
        return datas;

    }


    //温馨提示：
    //以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    //2016/09/10 11:59:05
    @Autowired
    private SpsMallProductDao productDao;
    @Autowired
    private SpsCiAgentPriceDao agentPriceDao;
    @Autowired
    private SpsMallOrderDao spsMallOrderDao;
    @Autowired
    private SpsServiceareaDao spsServiceareaDao;
    @Autowired
    private SpsMallOrderService mallOrderService;
    @Autowired
    private SpsMallOrderItemDao mallOrderItemDao;
    @Autowired
    private SpsMallOrderAreaDao mallOrderAreaDao;

    /**
     * 创建商保订单
     *  @param orderInfo  CiOrderInfo对象
     *  @param cti       SysUser对象
     *  @param corp       CmCorp对象
     *  @return 
     *  @createDate  	 : 2016年11月15日 下午12:05:47
     *  @author          : 
     *  @version         : v1.0
     *  @updateDate    	 : 2016年11月15日 下午12:05:47
     *  @updateAuthor  	 :
     */
    @Override
    public Result createCiOrder(ContextInfo cti,CiOrderInfo orderInfo, CmCorp corp) {
        initCiOrderInfo(cti, orderInfo, corp);
        spsMallOrderDao.insert(cti,orderInfo);
        spsCiOrderDao.insert(cti,orderInfo);
        mallOrderItemDao.insert(cti,getCiOrderItemInfo(orderInfo));
        mallOrderAreaDao.saveAll(cti,initCiOrderAreas(orderInfo));
        return new Result();
    }


    /**
     * 初始化商保订单信息
     *  @param orderInfo  CiOrderInfo对象
     *  @param cti       SysUser对象
     *  @param corp       CmCorp对象
     *	@return 		: void 
     *  @createDate  	: 2016年11月15日 下午12:00:03
     *  @author         : NDD 
     *  @version        : v1.0
     *  @updateDate    	: 2016年11月15日 下午12:00:03
     *  @updateAuthor   :
     */
    private void initCiOrderInfo(ContextInfo cti, CiOrderInfo orderInfo, CmCorp corp) {
        SpsMallProduct product = new SpsMallProduct();
        product.setProductId(orderInfo.getProductId());
        product = productDao.findByPK(product);
        orderInfo.setOrderNo(mallOrderService.getOrderNo());
        orderInfo.setBusiLicenseNum(corp.getBusiLicenseNum());
        orderInfo.setCustomerId(cti.getOrgId());
        orderInfo.setSpId(product.getSpId());
        orderInfo.setCusomerName(corp.getCorpName());
        orderInfo.setPerMonth("Y");
        orderInfo.setOrderType("B");
        orderInfo.setOrderState("SIGN");
        orderInfo.setDr(0);
        orderInfo.setItemId(product.getAgentCiId());
        CiProduct ciProduct = new CiProduct();
        ciProduct.setProductId(product.getProductId());
        ciProduct.setItemId(product.getAgentCiId());
        List<CiScheme> list = agentPriceDao.findCiItemPrice(ciProduct);
        for (CiScheme scheme : list) {
            if (scheme.getId().equals(orderInfo.getSchemeId())) {
                orderInfo.setPerMonthPrice(scheme.getSchemePrice());
                orderInfo.setTotalPrice(scheme.getSchemePrice().multiply(new BigDecimal(orderInfo.getAmount())));
                orderInfo.setPerMonthPrice(scheme.getSchemePrice().multiply(new BigDecimal(orderInfo.getAmount())));
            }
        }
    }

    /**
     * 
     *  @param orderInfo  CiOrderInfo对象
     *  @return 
     *	@return 		: SpsMallOrderItem 
     *  @createDate  	: 2016年11月15日 下午5:45:46
     *  @author         : NDD 
     *  @version        : v1.0
     *  @updateDate    	: 2016年11月15日 下午5:45:46
     *  @updateAuthor   :
     */
    private SpsMallOrderItem getCiOrderItemInfo(CiOrderInfo orderInfo) {
        SpsMallOrderItem mallOrderItem = new SpsMallOrderItem();
        mallOrderItem.setProductId(orderInfo.getProductId());
        mallOrderItem.setItemId(orderInfo.getItemId());
        mallOrderItem.setFeetype("PER_MONTH");
        mallOrderItem.setOrderId(orderInfo.getOrderId());
        mallOrderItem.setPrice(orderInfo.getPerMonthPrice());
        mallOrderItem.setDr(0);
        return mallOrderItem;

    }

    /**
     * 
     *  @param orderInfo  CiOrderInfo对象
     *  @return 
     *	@return 			: java.util.List<com.xfs.mall.order.model.SpsMallOrderArea> 
     *  @createDate  	    : 2016年11月15日 下午5:46:52
     *  @author         	: 
     *  @version        	: v1.0
     *  @updateDate    	    : 2016年11月15日 下午5:46:52
     *  @updateAuthor       :
     */
    private List<SpsMallOrderArea> initCiOrderAreas(CiOrderInfo orderInfo) {
        List<SpsMallOrderArea> list = new ArrayList<>();
        List<Map<String, Object>> areaList = spsServiceareaDao.findServiceAreasBySpId(orderInfo.getSpId().toString());
        for (Map<String, Object> area : areaList) {
            SpsMallOrderArea spsMallOrderArea = new SpsMallOrderArea();
            spsMallOrderArea.setOrderId(orderInfo.getOrderId());
            spsMallOrderArea.setProductId(orderInfo.getProductId());
            spsMallOrderArea.setDr(0);
            spsMallOrderArea.setAreaId(Integer.parseInt(area.get("areaId").toString()));
            list.add(spsMallOrderArea);
        }
        return list;
    }


}
