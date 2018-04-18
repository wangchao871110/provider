package com.xfs.insurance.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.insurance.dto.CiProduct;
import com.xfs.insurance.dto.CiScheme;
import com.xfs.insurance.model.BsCiProduct;

/**
 * BsCiProductService
 *
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/29 14:04:27
 */
public interface BsCiProductService {

    public int save(ContextInfo cti, BsCiProduct vo);

    public int insert(ContextInfo cti, BsCiProduct vo);

    public int remove(ContextInfo cti, BsCiProduct vo);

    public int update(ContextInfo cti, BsCiProduct vo);

    public PageModel findPage(PageInfo pi, BsCiProduct vo);

    public List<BsCiProduct> findAll(BsCiProduct vo);

    // 温馨提示：
    // 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    // 2016/08/29 14:04:27

    /**
     * 创建商保产品条目
     *
     * @param product 产品
     * @return 创建结果
     */
    boolean createCiProduct(ContextInfo cti,CiProduct product);

    /**
     * 查询商保产品
     *
     * @param ciProduct 查询条件
     * @return 结果列表
     */
    PageModel findCiProd(PageInfo pi,CiProduct ciProduct);

    /**
     * 查询单个商保产品信息
     *
     * @param itemId 产品编码
     * @return 产品信息
     */
    CiProduct findCiProductByitemId(Integer itemId);

    /**
     * 更新商保信息
     *
     * @param product 商保信息
     * @return 更新结果
     */
    boolean udpateCiProduct(ContextInfo cti,CiProduct product);

    /**
     * 查询可用户组合险的商保单品
     *
     * @param ciProduct 查询条件
     * @return 结果列表
     */
    List<CiProduct> findCiProductForGroup(CiProduct ciProduct);

    /**
     * 根据编码查询商保信息
     *
     * @param itemId 商保编码
     * @return 商保信息
     */
    CiProduct findCiProdById(Integer itemId);

    /**
     * 查询商保组合条目详情
     *
     * @param itemId 商保编码
     * @param prodId 商品编码
     * @return
     */
    List<CiScheme> findCiGroupItemDetail(Integer itemId, Integer prodId);

    /**
     * 服务商商保列表
     *
     * @param ciProduct
     * @return
     */
    List<CiProduct> findCiProdForAgent(CiProduct ciProduct);
}
