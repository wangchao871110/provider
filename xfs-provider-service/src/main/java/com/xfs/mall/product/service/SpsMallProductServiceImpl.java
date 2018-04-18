package com.xfs.mall.product.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.insurance.dao.BsCiGroupDao;
import com.xfs.insurance.dao.BsCiProductDao;
import com.xfs.insurance.dao.SpsCiAgentPriceDao;
import com.xfs.insurance.dto.CiProduct;
import com.xfs.insurance.dto.CiScheme;
import com.xfs.insurance.model.BsCiGroup;
import com.xfs.insurance.model.SpsCiAgentPrice;
import com.xfs.mall.product.dao.SpsMallProductDao;
import com.xfs.mall.product.dao.SpsMallProductItemDao;
import com.xfs.mall.product.dao.SpsMallProductareaDao;
import com.xfs.mall.product.model.SpsMallProduct;
import com.xfs.mall.product.model.SpsMallProductItem;
import com.xfs.mall.product.model.SpsMallProductarea;
import com.xfs.sp.dao.SpServiceDao;
import com.xfs.sp.dao.SpsServiceareaDao;
import com.xfs.user.model.SysUser;

@Service
public class SpsMallProductServiceImpl implements SpsMallProductService {

    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(SpsMallProductServiceImpl.class);

    @Autowired
    private SpsMallProductDao spsMallProductDao;

    @Autowired
    private SpsMallProductItemDao spsMallProductItemDao;

    @Autowired
    SpServiceDao spServiceDao;

    @Autowired
    SpsMallProductareaDao spsMallProductareaDao;

    public int save(ContextInfo cti, SpsMallProduct vo) {

        return spsMallProductDao.save(cti, vo);
    }

    public int insert(ContextInfo cti, SpsMallProduct vo) {

        return spsMallProductDao.insert(cti, vo);
    }

    public int remove(ContextInfo cti, SpsMallProduct vo) {

        return spsMallProductDao.remove(cti, vo);
    }

    public int update(ContextInfo cti, SpsMallProduct vo) {

        return spsMallProductDao.update(cti, vo);
    }

    public PageModel findPage(PageInfo pi, SpsMallProduct vo) {

        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = spsMallProductDao.countFreeFind(vo);
        pm.setTotal(total);
        if ("".equals(vo.getProductNameEq())) {
            vo.setProductNameEq(null);
        }
        if ("".equals(vo.getServiceType())) {
            vo.setServiceType(null);
        }
        if ("".equals(vo.getState())) {
            vo.setState(null);
        }
        List<SpsMallProduct> datas = spsMallProductDao.freeFind(vo, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;

    }

    public List<SpsMallProduct> findAll(SpsMallProduct vo) {

        List<SpsMallProduct> datas = spsMallProductDao.freeFind(vo);
        return datas;

    }

    // 温馨提示：
    // 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    // 2016/06/07 11:06:12

    public List<SpsMallProduct> freeFind(SpsMallProduct vo) {

        return spsMallProductDao.freeFind(vo);
    }

    // @Override
    public List<Map<String, Object>> findAllBySpIdAreaId(SpsMallProduct vo) {

        List<Map<String, Object>> datas = spsMallProductDao.findAllBySpIdAreaId(vo);
        return datas;
    }

    public Result saveProductData(ContextInfo cti, SpsMallProduct vo, String itemsArray, String chooseAreas) {

        Result result = Result.createResult().setSuccess(true);
        Date date = new Date();
        boolean flag = true;
        if (null != cti && null != cti.getUserId()) {
            // 修改 产品
            if (null != vo && null != vo.getProductId()) {
                vo.setModifyBy(cti.getUserId());
                vo.setModifyDt(date);
                int i = vo.getPerMonthPrice().compareTo(BigDecimal.ZERO);
                if (i == 1) {
                    vo.setPerMonth("Y");
                } else {
                    vo.setPerMonth("N");
                    vo.setPerMonthPrice(BigDecimal.ZERO);
                }
                int j = vo.getOncePrice().compareTo(BigDecimal.ZERO);
                if (j == 1) {
                    vo.setOnce("Y");
                } else {
                    vo.setOnce("N");
                    vo.setOncePrice(BigDecimal.ZERO);
                }
                vo.setServiceType("B");
                vo.setState("ON");
                Integer ups = this.update(cti, vo);
                if (ups < 0) {
                    result.setError("修改失败！");
                    result.setSuccess(false);
                    flag = false;
                }
            } else {
                // 新增 产品
                vo.setSpId(cti.getOrgId());
                // 去服务商信息表里查询 当前服务商所在城市ID
                if (null != vo.getSpId()) {
                    vo.setCreateBy(cti.getUserId());
                }
                // 当人月价格不为空时 给 人月 方式设置为Y
                int i = vo.getPerMonthPrice().compareTo(BigDecimal.ZERO);
                if (i == 1) {
                    vo.setPerMonth("Y");
                } else {
                    vo.setPerMonth("N");
                    vo.setPerMonthPrice(BigDecimal.ZERO);
                }
                int j = vo.getOncePrice().compareTo(BigDecimal.ZERO);
                if (j == 1) {
                    vo.setOnce("Y");
                } else {
                    vo.setOnce("N");
                    vo.setOncePrice(BigDecimal.ZERO);
                }
                vo.setCreateDt(date);
                vo.setServiceType("B");
                vo.setState("ON");
                vo.setDr(0);
                Integer ins = this.insert(cti, vo);
                if (ins < 0) {
                    result.setError("新增失败！");
                    result.setSuccess(false);
                    flag = false;
                    return result;
                }
                // 更新order by 字段
                this.updateOrderByByProdcutId(cti, vo.getProductId());
            }
            if (flag && !StringUtil.isBlank(itemsArray)) {
                // 删除产品下所有项目内容
                Integer rem = spsMallProductItemDao.removeByProductId(cti, vo.getProductId());
                // 删除产品下所有服务区域
                Integer remm = spsMallProductareaDao.removeByProductId(cti, vo.getProductId());
                if (0 <= rem && 0 <= remm) {
                    JSONArray array = JSON.parseArray(itemsArray);
                    List<SpsMallProductItem> itemsList = new ArrayList<SpsMallProductItem>();
                    for (int i = 0; i < array.size(); i++) {
                        SpsMallProductItem spsMallProductItem = new SpsMallProductItem();
                        String str = array.get(i).toString();
                        JSONObject o = JSON.parseObject(str);
                        String itemId = (String) o.get("itemId");
                        String unit = (String) o.get("unit");
                        if (o.get("price") instanceof Integer) {
                            Integer price = (Integer) o.get("price");
                            spsMallProductItem.setPrice(new BigDecimal(price));
                        } else {
                            BigDecimal price = (BigDecimal) o.get("price");
                            spsMallProductItem.setPrice(price);
                        }
                        String isrequired = (String) o.get("isrequired");
                        spsMallProductItem.setProductId(vo.getProductId());
                        spsMallProductItem.setItemId(Integer.parseInt(itemId));
                        spsMallProductItem.setFeetype(unit);
                        spsMallProductItem.setIsRequired(isrequired);
                        spsMallProductItem.setModifyBy(cti.getUserId());
                        spsMallProductItem.setModifyDt(date);
                        spsMallProductItem.setDr(0);
                        itemsList.add(spsMallProductItem);
                    }
                    Integer inss = spsMallProductItemDao.addBatch(cti, itemsList);
                    // 保存产品的直营和联盟区域
                    List<SpsMallProductarea> proAreaList = new ArrayList<SpsMallProductarea>();
                    if (StringUtil.isBlank(chooseAreas)) {
                        result.setError("服务区域不能为空！");
                        result.setSuccess(false);
                        return result;
                    }
                    String subChoose = chooseAreas;
                    if (StringUtil.isBlank(subChoose)) {
                        result.setError("服务区域不能为空！");
                        result.setSuccess(false);
                        return result;
                    }
                    String[] areaArr = subChoose.split(",");
                    for (String str : areaArr) {
                        SpsMallProductarea spsMallProductarea = new SpsMallProductarea();
                        spsMallProductarea.setProductId(vo.getProductId());
                        spsMallProductarea.setAreaId(Integer.parseInt(str));
                        spsMallProductarea.setDr(0);
                        proAreaList.add(spsMallProductarea);
                    }
                    Integer otherIns = spsMallProductareaDao.addBatch(cti, proAreaList);
                    if (0 > inss || 0 > otherIns) {
                        result.setError("保存失败！");
                        result.setSuccess(false);
                    }
                } else {
                    result.setError("保存失败！");
                    result.setSuccess(false);
                }
            }
        } else {
            result.setError("保存失败！");
        }
        return result;
    }

    public int updateOrderByByProdcutId(ContextInfo cti, Integer productId) {

        SpsMallProduct sps = new SpsMallProduct();
        sps.setOrderby(productId);
        return spsMallProductDao.updateOrderByByProductId(cti, sps);
    }

    @Autowired
    private BsCiProductDao ciProductDao;

    @Autowired
    private SpsCiAgentPriceDao ciAgentPriceDao;

    @Autowired
    private SpsServiceareaDao spsServiceareaDao;
    @Autowired
    private BsCiGroupDao ciGroupDao;

    @Override
    public Result saveCiProduct(ContextInfo cti, Integer itemId, List<CiScheme> list) {

        Result result = new Result().setSuccess(true);
        CiProduct ciProduct = ciProductDao.findCiProdById(itemId);
        if (null == ciProduct) {
            result.setError("产品不存在，不能代理");
            return result;
        }
        Integer spsProdId = saveCiProduct(cti, ciProduct, list.get(0));
        Integer prodItemId = saveCiMallItem(cti, ciProduct, spsProdId);
        saveCiProductArea(cti, spsProdId, cti.getOrgId().toString());
        saveCiPrice(cti, prodItemId, list);
        return result;
    }

    private void saveCiPrice(ContextInfo cti, Integer prodItemId, List<CiScheme> list) {
        List<SpsCiAgentPrice> priceList = new ArrayList<>();
        SpsCiAgentPrice ciAgentPrice;
        for (CiScheme scheme : list) {
            ciAgentPrice = new SpsCiAgentPrice();
            ciAgentPrice.setSchemePrice(scheme.getSchemePrice());
            ciAgentPrice.setProdItemId(prodItemId);
            ciAgentPrice.setSchemeId(scheme.getId());
            priceList.add(ciAgentPrice);
        }
        ciAgentPriceDao.saveAll(cti, priceList);
    }

    /**
     * 商品表数据入库
     *
     * @param ciProduct 商保产品信息
     * @param ciScheme  承保方案（多个方案是默认选择第一个,此处的价格仅供列表页展示使用）
     * @return 商品编码
     */
    private int saveCiProduct(ContextInfo cti, CiProduct ciProduct, CiScheme ciScheme) {

        SpsMallProduct mallProduct = new SpsMallProduct();
        mallProduct.setDr(0);
        mallProduct.setProductName(ciProduct.getItemName());
        mallProduct.setState("ON");
        mallProduct.setSpId(cti.getOrgId());
        mallProduct.setOnce("N");
        mallProduct.setOncePrice(BigDecimal.ZERO);
        mallProduct.setPerMonth("Y");
        mallProduct.setPerMonthPrice(ciScheme.getSchemePrice());
        mallProduct.setAgentCiId(ciProduct.getItemId());
        mallProduct.setServiceType("B");
        spsMallProductDao.insert(cti, mallProduct);
        return mallProduct.getProductId();
    }

    /**
     * @param ciProduct 商保产品信息
     * @param prodId    商品编码
     */
    private Integer saveCiMallItem(ContextInfo cti, CiProduct ciProduct, Integer prodId) {

        SpsMallProductItem spsMallProductItem = new SpsMallProductItem();
        spsMallProductItem.setDr(0);
        spsMallProductItem.setItemId(ciProduct.getItemId());
        spsMallProductItem.setProductId(prodId);
        spsMallProductItemDao.insert(cti, spsMallProductItem);
        return spsMallProductItem.getId();
    }

    /**
     * 保存商品的地域信息
     *
     * @param prodId 商品编码
     */
    private void saveCiProductArea(ContextInfo cti, Integer prodId, String spId) {

        List<SpsMallProductarea> list = new ArrayList<>();
        List<Map> coopAndInnerList = spsServiceareaDao.freeFindCOOPAndINNER(spId);
        List<Map> groupList = spsServiceareaDao.freeFindGROUP(spId);
        if (coopAndInnerList.size() == 0 && groupList.size() == 0) {
            Map map = spServiceDao.findAreaIdBySpId(spId);
            coopAndInnerList.add(map);
        }
        coopAndInnerList.addAll(groupList);
        SpsMallProductarea spsMallProductarea = null;
        for (Map map : coopAndInnerList) {
            spsMallProductarea = new SpsMallProductarea();
            spsMallProductarea.setProductId(prodId);
            spsMallProductarea.setAreaId(-1);
            spsMallProductarea.setDr(0);
            spsMallProductarea.setAreaId((Integer) map.get("areaId"));
            list.add(spsMallProductarea);
        }
        spsMallProductareaDao.saveAll(cti, list);
    }


    @Override
    public Result saveGroupProd(ContextInfo cti, Integer groupId, List<SpsMallProductItem> list) {
        Result result = new Result();
        BsCiGroup group = new BsCiGroup();
        group.setGroupId(groupId);
        group = ciGroupDao.findByPK(group);
        if (null == group) {
            result.setSuccess(false);
            result.setError("商保组合不存在");
            return result;
        }
        Integer prodId = saveCiGroupProd(cti, group, list);
        saveGroupMallItem(cti, list, prodId);
        saveCiProductArea(cti, prodId, cti.getOrgId().toString());
        return result;
    }

    private Integer saveCiGroupProd(ContextInfo cti, BsCiGroup group, List<SpsMallProductItem> list) {
        SpsMallProduct product = new SpsMallProduct();
        product.setDr(0);
        product.setSpId(cti.getOrgId());
        product.setAgentGroupId(group.getGroupId());
        product.setProductName(group.getGroupName());
        product.setState("ON");
        product.setOnce("N");
        product.setOncePrice(BigDecimal.ZERO);
        product.setPerMonth("Y");
        BigDecimal price = new BigDecimal(0);
        for (SpsMallProductItem item : list) {
            price = price.add(item.getPrice());
        }
        product.setPerMonthPrice(price);
        product.setServiceType("B");
        spsMallProductDao.insert(cti, product);
        return product.getProductId();
    }

    /**
     * @param list   条目
     * @param prodId 商品编码
     */
    private void saveGroupMallItem(ContextInfo cti, List<SpsMallProductItem> list, Integer prodId) {

        for (SpsMallProductItem item : list) {
            item.setProductId(prodId);
            item.setDr(0);
            item.setFeetype("PER_MONTH");
            item.setIsRequired("Y");
        }
        spsMallProductItemDao.saveAll(cti, list);
    }
	
	/**
	 * 商铺服务商品列表
	 *
	 * @param vo
	 * @return
     */
	public List<Map<String, Object>> findMallProducts(SpsMallProduct vo) {
		return spsMallProductDao.findMallProducts(vo);
	}

	/**
	 * 条件搜索产品列表
	 *
	 * @param areaIds
	 * @param categoryIds
	 * @param spId
	 * @param searchWord
	 * @return
	 */
	public PageModel findProductsByCondition(PageInfo pi, String areaIds, String categoryIds, String spId, String searchWord) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsMallProductDao.findCountByCondition(areaIds, categoryIds, spId, searchWord);
		pm.setTotal(total);
		List<Map<String, Object>> datas = spsMallProductDao.findListByCondition(areaIds, categoryIds, spId, searchWord, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}

	/**
	 * 查询产品详情
	 *
	 * @param vo
	 * @return
	 */
	public Map<String, Object> findProductDetail(SpsMallProduct vo) {
		return spsMallProductDao.findProductDetail(vo);
	}

	public List<Map<String, Object>> findMallProductsBySpId(SpsMallProduct vo) {
		return spsMallProductDao.findMallProductsBySpId(vo);
	}
	
	@Override
	public PageModel findSpsProductByRecom(PageInfo pi, String productName,String spName) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsMallProductDao.countSpsProductByRecom(productName);
		pm.setTotal(total);
		List<SpsMallProduct> datas = spsMallProductDao.findSpsProductByRecom(productName,spName, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}
	
	@Override
	public SpsMallProduct findByPK(SpsMallProduct spsMallProduct){
		return spsMallProductDao.findByPK(spsMallProduct);
	}
	
	@Override
	public PageModel findAllmsgPage(PageInfo pi, Map vo) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsMallProductDao.findAllCount(vo);
		pm.setTotal(total);
		List<Map<String, Object>> datas = spsMallProductDao.findAllmsg(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}

}
