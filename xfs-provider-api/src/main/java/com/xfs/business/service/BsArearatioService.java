package com.xfs.business.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.xfs.business.model.BsArearatio;
import com.xfs.business.model.BsArearatiover;
import com.xfs.business.model.BsRatioCalcInterface;
import com.xfs.business.model.InsuranceFundPeopleType;
import com.xfs.business.model.InsuranceFundType;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;

/**
 * BsArearatioService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/21 20:03:17
 */
public interface BsArearatioService {
	
	public int save(ContextInfo cti, BsArearatio vo);
	public int insert(ContextInfo cti, BsArearatio vo);
	public int remove(ContextInfo cti, BsArearatio vo);
	public int update(ContextInfo cti, BsArearatio vo);
	public PageModel findPage(PageInfo pi, BsArearatio vo);
	public List<BsArearatio> findAll(BsArearatio vo);
    public BsArearatio findByPK(Integer pk) ;
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/21 20:03:17
    public Map<String, Object> findAreaRatioDetail(BsArearatio obj);
	BsArearatiover getVerByPeriod(String period, BsArearatio ratio);
	/*
	 * 根据比例规则计算
	 * 基数、比例id、期间必须提供
	 */
	Result calc(ContextInfo cti,BsRatioCalcInterface calcEntity, String period ,String endPeriod);
	
	/**
     * 根据areaId查询 险种信息
     *
     * @author lifq
     *
     * 2016年9月26日  下午2:20:48
     */
    public List<Map<String, Object>> findRatiosByAreaId(Map<String, Object> paraMap);
	Result calc(ContextInfo cti,List<? extends BsRatioCalcInterface> calcEntities, String period);


    public List findByQueryList(Map map);
    public void deleteRatio(ContextInfo cti,Integer ratioId);
    public Result updateRule(ContextInfo cti,BsArearatio ratio, String[] code, String[] beginPeriod, String[] endPeriod,
                             BigDecimal[] corpBaseLower, BigDecimal[] corpBaseUpper, BigDecimal[] psnBaseLower,
                             BigDecimal[] psnBaseUpper, BigDecimal[] corpRatio, BigDecimal[] psnRatio, BigDecimal[] corpAddition,
                             BigDecimal[] psnAddition);
    public Result saveRule(ContextInfo cti,BsArearatio ratio, String[] code, String[] beginPeriod, String[] endPeriod,
                           BigDecimal[] corpBaseLower, BigDecimal[] corpBaseUpper, BigDecimal[] psnBaseLower,
                           BigDecimal[] psnBaseUpper, BigDecimal[] corpRatio, BigDecimal[] psnRatio, BigDecimal[] corpAddition,
                           BigDecimal[] psnAddition);
    /*
     * 根据
     */
    public List findInsuranceIdsByAreaId(Integer areaId);
	/**
	 * 批量修改比例查询 比例
	 *  @param   beginMonth, areaId, insuranceId, livingType, ratioId
	 * @return    : java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate   : 17:02 2016/11/15
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 17:02 2016/11/15
	 *  @updateAuthor  :
	 */
	public List<Map<String,Object>> findRatioByBatchQuery(String beginMonth,Integer areaId,Integer insuranceId,String livingType,Integer ratioId,Integer ratioIdNe,Integer dr);

	/**
	 * 查询比例所属险种类型
	 *  @param   ratioId
	 * @return    : java.lang.String
	 *  @createDate   : 10:35 2016/11/18
	 *  @author          : yfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 10:35 2016/11/18
	 *  @updateAuthor  :
	 */
	public String findRatioInsuranceType(Integer ratioId);

	/**
	 * 查询地区社保基数下限中最高的值 小于3500 取3500
	 *  @param   areaId 地区id
	 * @return    : java.lang.Integer
	 *  @createDate   : 2016/11/28 18:15
	 *  @author          : konglc@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/11/28 18:15
	 *  @updateAuthor  :
	 */
	public BigDecimal findInsuBaseLower(Integer areaId);
	BsArearatio findDefaultRatio(Integer areaId, Integer insuranceId, String livingType, String period);

	/**
	 *  查询地区社保基数下限中最高的值
	 *  @param
	 * @return    :
	 *  @createDate   : 2016/12/7 20:57
	 *  @author          : gaoyf@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/12/7 20:57
	 *  @updateAuthor  :
	 */
	public BigDecimal queryInsuMin(Integer areaId);

	/**
	 *  查询地区社保基数上限中最高的值
	 *  @param
	 * @return    :
	 *  @createDate   : 2016/12/7 20:57
	 *  @author          : gaoyf@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/12/7 20:57
	 *  @updateAuthor  :
	 */
	public BigDecimal queryInsuMax(Integer areaId);


	public BigDecimal queryFundMin(Integer areaId);

	public BigDecimal queryFundMax(Integer areaId);
	/**
	 * 获取薪福邦小助手数据
	 *  @param areaId
	 *  @return 
	 *	@return 			: List<InsuranceFundPeopleType> 
	 *  @createDate  	: 2017年1月17日 下午1:39:37
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年1月17日 下午1:39:37
	 *  @updateAuthor  :
	 */
	public List<InsuranceFundPeopleType> findInsuranceFundPeopleType(Integer areaId);
	/**
	 * 获取薪福邦小助手户口类型数据
	 *  @param valueOf
	 *  @param peopleTypeCode
	 *  @return 
	 *	@return 			: List<InsuranceFundType> 
	 *  @createDate  	: 2017年1月18日 上午10:59:10
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年1月18日 上午10:59:10
	 *  @updateAuthor  :
	 */
	public List<InsuranceFundType> findInsuranceFundType(Integer valueOf, String peopleTypeCode);

}
