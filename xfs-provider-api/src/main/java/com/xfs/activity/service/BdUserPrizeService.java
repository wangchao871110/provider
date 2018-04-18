package com.xfs.activity.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.xfs.activity.model.BdUserPrize;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
/**
 * 
* @ClassName: BdUserPrizeService 
* @Description: 服务商中奖奖品
* @author duanax@xinfushe.com
* @date 2016年11月15日 上午11:29:31 
*
 */
public interface BdUserPrizeService {

    public int save(ContextInfo cti, BdUserPrize vo);

    public int insert(ContextInfo cti, BdUserPrize vo);

    public int remove(ContextInfo cti, BdUserPrize vo);

    public int update(ContextInfo cti, BdUserPrize vo);

    public PageModel findPage(PageInfo pi, BdUserPrize vo);

    public List<BdUserPrize> findAll(BdUserPrize vo);


    //温馨提示：
    //以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    //2016/10/10 17:44:33

    /**
     * 获取用户指定类型的奖品
     *
     * @param orgId     组织编码
     * @param prizeType 奖品类型
     * @return 奖品列表
     */
    public List<Map> getPrizeByType(String userType, Integer orgId, String prizeType);
    
    /**
     * 
    * @Title: findUserPrizeById 
    * @Description:根据组织id和奖品类型获取角色奖品
    * @param  orgId
    * @param  prizeType
    * @param  id
    * @return Map    返回类型
    * @throws
     */
    public Map findUserPrizeById(String userType, Integer orgId, String prizeType, Integer id);

    public BdUserPrize findByPk(Integer id);
    
    /**
     * 
    * @Title: queryUserPrizeList 
    * @Description:获取组织中奖奖品列表
    * @param  orgId
    * @param  prizeType
    * @param  id
    * @param   设定文件 
    * @return Map    返回类型 
    * @throws
     */
    public List<Map<String, Object>> queryUserPrizeList(BdUserPrize vo);
    
    public Map<String, Object> queryUserPrize(BdUserPrize vo);

    /**
	 * 查询我的优惠券
	 * @param vo
	 * @return
	 */
	public List<Map<String, Object>> queryUserPriceByBUP(BdUserPrize vo);

    /**
     * 使用或回退优惠券
     *
     * @param cti
     * @param userType
     * @param orgId
     * @param prizeId
     * @param outTradeOrder
     * @param monetary
     * @param optType 操作类型 0：使用，1：回退
     * @return
     * @throws Exception
     */
    public Result saveUserPrizeRecord(ContextInfo cti, String userType, Integer orgId, Integer prizeId, String outTradeOrder, BigDecimal monetary, int optType);

}
