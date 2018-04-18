package com.xfs.insurance.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.insurance.dto.CpCiProdDetail;
import com.xfs.insurance.model.CpCiProd;

/**
 * CpCiProdService
 *
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/21 15:45:17
 */
public interface CpCiProdService {

    public int save(ContextInfo cti, CpCiProd vo);

    public int insert(ContextInfo cti, CpCiProd vo);

    public int remove(ContextInfo cti, CpCiProd vo);

    public int update(ContextInfo cti, CpCiProd vo);

    public PageModel findPage(PageInfo pi, CpCiProd vo);

    public List<CpCiProd> findAll(CpCiProd vo);


    //温馨提示：
    //以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    //2016/09/21 15:45:17

    /**
     * 商保商品上架
     * @param vo 商保商品
     * @return 操作结果
     */
    public int ciProdIn(ContextInfo cti,CpCiProd vo);

    public int ciProdOut(ContextInfo cti,Integer ciCode);

	public List<CpCiProdDetail> findCiProd();

	public CpCiProdDetail findCiProdById(Integer prodId);
}
