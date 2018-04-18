package com.xfs.bill.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xfs.bill.dto.InsureEmpDetail;
import com.xfs.bill.dto.Management;
import com.xfs.bill.dto.SpsBillDto;
import com.xfs.bill.model.SpsBill;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.common.util.Constant;
import com.xfs.common.util.StringUtils;
import com.xfs.serviceBill.dto.ServiceBillVo;
import com.xfs.user.service.SysFunctionDataService;


/**
 * SpsBillDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/05/16 15:32:00
 */
@Repository
public class SpsBillDao extends BaseSqlMapDao {
	
	@Autowired
	private SysFunctionDataService sysFunctionDataService;
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("SPS_BILL.CountFindAllSPS_BILL", null );
        return ret.intValue();
	}

	public SpsBill findByPK(SpsBill obj) {
		Object ret = queryForObject("SPS_BILL.FindByPK", obj );
		if(ret !=null)
			return (SpsBill)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBill> freeFind(SpsBill obj) {
		return queryForList("SPS_BILL.FreeFindSPS_BILL", obj );
	}

	public int countFreeFind(SpsBill obj) {
        Integer ret = (Integer) queryForObject("SPS_BILL.CountFreeFindSPS_BILL", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBill> freeFind(SpsBill obj, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_BILL.FreeFindSPS_BILL", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBill> freeFind(SpsBill obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsBill){
	    	_hashmap = ((SpsBill)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("SPS_BILL.FreeFindSPS_BILLOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpsBill> freeFind(SpsBill obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof SpsBill){
	    	_hashmap = ((SpsBill)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("SPS_BILL.FreeFindSPS_BILLOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<SpsBill> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<SpsBill> it = objColl.iterator();
            while ( it.hasNext() ) {
            	SpsBill oneObj = (SpsBill)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, SpsBill vo) {
        SpsBill obj = (SpsBill) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, SpsBill obj) {

		obj.fixup();
        return insert(cti, "SPS_BILL.InsertSPS_BILL", obj );
	}

	public int update(ContextInfo cti, SpsBill obj) {

		obj.fixup();
		return update(cti, "SPS_BILL.UpdateSPS_BILL", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, SpsBill obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "SPS_BILL.DeleteSPS_BILL", obj );

	}

	public int removeObjectTree(ContextInfo cti, SpsBill obj) {

        obj.fixup();
        SpsBill oldObj = ( SpsBill )queryForObject("SPS_BILL.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "SPS_BILL.DeleteSPS_BILL", oldObj );

	}

	public boolean exists(SpsBill vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        SpsBill obj = (SpsBill) vo;

        keysFilled = keysFilled && ( null != obj.getBillId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("SPS_BILL.CountSPS_BILL", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/05/16 15:32:00

	public SpsBill queryBillDetail(SpsBill vo){
		SpsBill spsBill = (SpsBill)queryForObject("SPS_BILL.QUERY_CORP_BILL_DETAIL_BY_CPID", vo );
		return spsBill;
	}

	/**
	 * 根据条件获取账单个数
	 * @param bill
	 * @return
     */
	public Integer queryBillListCount(String cmName,SpsBill bill,ContextInfo cti){
        // 数据权限
        String authority = sysFunctionDataService.getDataAuthorityByUser(cti.getUserId(), cti.getUserType(), Constant.DATATYPE);
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("cmName",cmName);
		obj.put("day",bill.getBillDay());
		obj.put("period",bill.getPeriod());
		obj.put("state",bill.getBillState());
		obj.put("spid",bill.getSpId());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Calendar calendar = Calendar.getInstance();
		String nowDate = format.format(calendar.getTime());
		obj.put("nowDate",nowDate);
		obj.put("authority",authority);
		Integer ret = (Integer) queryForObject("SPS_BILL.QUERY_ALL_BILL_LIST_COUNT", obj );
		return ret.intValue();
	}

	public Integer queryExistReation(SpsBill bill,Integer sp_id){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("cpId",bill.getCpId());
		obj.put("spId",bill.getSpId());
		obj.put("billId",bill.getBillId());
		obj.put("parent_sp_id",sp_id);
		Integer ret = (Integer) queryForObject("SPS_BILL.QUERY_EXIST_RELATION_BILL", obj );
		return ret.intValue();
	}

	/**
	 *  根据条件获取账单列表
	 * @param bill
	 * @return
     */
	public List<Map<String, Object>> queryBillList(String cmName,SpsBill bill,Integer pageIndex,Integer pagerSize,ContextInfo cti){
		// 数据权限
        String authority = sysFunctionDataService.getDataAuthorityByUser(cti.getUserId(), cti.getUserType(), Constant.DATATYPE);
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("cmName",cmName);
		obj.put("day",bill.getBillDay());
		obj.put("period",bill.getPeriod());
		obj.put("state",bill.getBillState());
		obj.put("spid",bill.getSpId());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Calendar calendar = Calendar.getInstance();
		String nowDate = format.format(calendar.getTime());
		obj.put("nowDate",nowDate);
		obj.put("authority",authority);
		return getPaginatedList("SPS_BILL.QUERY_ALL_BILL_LIST",obj,pageIndex,pagerSize);
	}

	/**
	 * 获取企业账单基本信息
	 * @param spid
	 * @param cmid
     * @return
     */
	public Map<String,Object> queryCmBillDetail(Integer spid,Integer cmid,Integer bill_id){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("spid",spid);
		obj.put("cmid",cmid);
		obj.put("bill_id",bill_id);
		return (Map<String,Object>)queryForObject("SPS_BILL.QUERY_BILL_DETAIL",obj);
	}

	/**
	 * 删除企业该月份下的账单
	 * @param spid
	 * @param cmid
	 * @param period
     * @return
     */
	public Integer delCmBillByMonth(ContextInfo cti, Integer spid,Integer cmid,String period){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("spid",spid);
		obj.put("cmid",cmid);
		obj.put("period",period);
		return delete(cti, "SPS_BILL.DEL_CM_BILL_BY_MONTH",obj);
	}

	/**
	 * 获取账单支付收款数量
	 * @param cmName
	 * @param bill
     * @return
     */
	public Integer queryBillPaymentListCount(String cmName,SpsBill bill,ContextInfo cti ){
		// 数据权限
        String authority = sysFunctionDataService.getDataAuthorityByUser(cti.getUserId(), cti.getUserType(), Constant.DATATYPE);
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("cmName",cmName);
		obj.put("period",bill.getPeriod());
		obj.put("spid",bill.getSpId());
        obj.put("feePayStatus",bill.getFeePayStatus());
		obj.put("authority",authority);
		Integer ret = (Integer) queryForObject("SPS_BILL.QUERY_BILL_PAYMENT_LIST_COUNT", obj );
		return ret.intValue();
	}

	/**
	 * 获取账单支付收款列表
	 * @param cmName
	 * @param bill
	 * @param pageIndex
	 * @param pagerSize
     * @return
     */
	public List<Map<String, Object>> queryBillPaymentList(String cmName,SpsBill bill,Integer pageIndex,Integer pagerSize,ContextInfo cti ){
		Map<String,Object> obj = new HashMap<String,Object>();
		// 数据权限
        String authority = sysFunctionDataService.getDataAuthorityByUser(cti.getUserId(), cti.getUserType(), Constant.DATATYPE);
	
		obj.put("cmName",cmName);
		obj.put("period",bill.getPeriod());
		obj.put("spid",bill.getSpId());
		obj.put("feePayStatus",bill.getFeePayStatus());
		obj.put("authority",authority);
		return getPaginatedList("SPS_BILL.QUERY_BILL_PAYMENT_LIST",obj,pageIndex,pagerSize);
	}

	/**
	 * 查询协作合作伙伴数量---委托应收账单
	 * @param sp_id
	 * @param area_id
	 * @param bill_month
     * @return
     */
	public Integer queryPrepayCooperationCount(Integer sp_id,Integer area_id, String bill_month,String spname,ContextInfo cti){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("bill_month",bill_month);
		obj.put("area_id",area_id);
		obj.put("sp_id",sp_id);
		obj.put("spname",spname);
		// 数据权限
        String authority = sysFunctionDataService.getDataAuthorityByUser(cti.getUserId(), cti.getUserType(), Constant.DATATYPE);
		obj.put("authority",authority);
		Integer ret = (Integer) queryForObject("SPS_BILL.QUERY_BILL_COOPER_CP_COUNT", obj );
		return ret.intValue();
	}

	/**
	 * 查询协作合作伙伴列表---委托应收账单
	 * @param sp_id
	 * @param area_id
	 * @param bill_month
     * @return
     */
	public List<Map<String,Object>> queryPrepayCooperationList(Integer sp_id,Integer area_id, String bill_month,String spname,Integer pageIndex,Integer pagerSize,ContextInfo cti){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("bill_month",bill_month);
		obj.put("area_id",area_id);
		obj.put("sp_id",sp_id);
		obj.put("spname",spname);
		// 数据权限
        String authority = sysFunctionDataService.getDataAuthorityByUser(cti.getUserId(), cti.getUserType(), Constant.DATATYPE);
		obj.put("authority",authority);
		return getPaginatedList("SPS_BILL.QUERY_BILL_COOPER_CP_LIST",obj,pageIndex,pagerSize);
	}

	/**
	 * 查询委托客户数量
	 * @param sp_id
	 * @param parent_sp_id
	 * @param bill_month
	 * @param fee_pay_status
	 * @param cp_name
     * @return
     */
	public Integer queryDeputeCorpCount(Integer sp_id,Integer parent_sp_id,String bill_month,Integer fee_pay_status,String cp_name){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("bill_month",bill_month);
		obj.put("parent_sp_id",parent_sp_id);
		obj.put("sp_id",sp_id);
		obj.put("cp_name",cp_name);
		obj.put("fee_pay_status",fee_pay_status);
		Integer ret = (Integer) queryForObject("SPS_BILL.QUERY_BILL_DEPUTE_CP_COUNT", obj );
		return ret.intValue();
	}

	/**
	 * 查询委托客户数量
	 * @param sp_id
	 * @param parent_sp_id
	 * @param bill_month
	 * @param fee_pay_status
	 * @param cp_name
	 * @return
	 */
	public Integer queryDeputeCorpCount2(Integer sp_id,Integer parent_sp_id,String bill_month,Integer fee_pay_status,String cp_name,ContextInfo cti){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("bill_month",bill_month);
		obj.put("parent_sp_id",parent_sp_id);
		obj.put("sp_id",sp_id);
		obj.put("cp_name",cp_name);
		obj.put("fee_pay_status",fee_pay_status);
		// 数据权限
        String authority = sysFunctionDataService.getDataAuthorityByUser(cti.getUserId(), cti.getUserType(), Constant.DATATYPE);
		obj.put("authority",authority);
		Integer ret = (Integer) queryForObject("SPS_BILL.QUERY_BILL_DEPUTE_CP_COUNT2", obj );
		return ret.intValue();
	}

	/**
	 * 查询委托客户列表
	 * @param sp_id
	 * @param parent_sp_id
	 * @param bill_month
	 * @param fee_pay_status
	 * @param cp_name
	 * @param pageIndex
     * @param pagerSize
     * @return
     */
	public List<Map<String,Object>> queryDeputeCorpList(Integer sp_id,Integer parent_sp_id,String bill_month,Integer fee_pay_status,String cp_name,Integer pageIndex,Integer pagerSize){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("bill_month",bill_month);
		obj.put("parent_sp_id",parent_sp_id);
		obj.put("sp_id",sp_id);
		obj.put("cp_name",cp_name);
		obj.put("fee_pay_status",fee_pay_status);
		return getPaginatedList("SPS_BILL.QUERY_BILL_DEPUTE_CP_LIST",obj,pageIndex,pagerSize);
	}

	/**
	 * 查询委托客户列表
	 * @param sp_id
	 * @param parent_sp_id
	 * @param bill_month
	 * @param fee_pay_status
	 * @param cp_name
	 * @param pageIndex
	 * @param pagerSize
	 * @return
	 */
	public List<Map<String,Object>> queryDeputeCorpList2(Integer sp_id,Integer parent_sp_id,String bill_month,Integer fee_pay_status,String cp_name,ContextInfo cti,Integer pageIndex,Integer pagerSize){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("bill_month",bill_month);
		obj.put("parent_sp_id",parent_sp_id);
		obj.put("sp_id",sp_id);
		obj.put("cp_name",cp_name);
		obj.put("fee_pay_status",fee_pay_status);
		// 数据权限
        String authority = sysFunctionDataService.getDataAuthorityByUser(cti.getUserId(), cti.getUserType(), Constant.DATATYPE);
		obj.put("authority",authority);
		return getPaginatedList("SPS_BILL.QUERY_BILL_DEPUTE_CP_LIST2",obj,pageIndex,pagerSize);
	}


	/**
	 * 查询委托客户下的员工数量
	 * @param sp_id
	 * @param parent_sp_id
	 * @param cp_id
	 * @param bill_month
	 * @param scheme_id
	 * @param area_id
     * @param searchWord
     * @return
     */
	public Integer queryDeputeCorpEmpCount(Integer sp_id,Integer parent_sp_id,Integer cp_id,String bill_month,Integer scheme_id,Integer area_id,String searchWord){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("bill_month",bill_month);
		obj.put("parent_sp_id",parent_sp_id);
		obj.put("sp_id",sp_id);
		obj.put("searchWord",searchWord);
		obj.put("scheme_id",scheme_id);
		obj.put("cp_id",cp_id);
		obj.put("area_id",area_id);
		Integer ret = (Integer) queryForObject("SPS_BILL.QUERY_BILL_DEPUTE_EMP_COUNT", obj );
		return ret.intValue();
	}

	/**
	 * 查询委托客户员工列表
	 * @param sp_id
	 * @param parent_sp_id
	 * @param cp_id
	 * @param bill_month
	 * @param scheme_id
	 * @param area_id
	 * @param searchWord
	 * @param pageIndex
     * @param pagerSize
     * @return
     */
	public List<Map<String,Object>> queryDeputeCorpEmpList(Integer sp_id,Integer parent_sp_id,Integer cp_id,String bill_month,Integer scheme_id,Integer area_id,String searchWord,Integer pageIndex,Integer pagerSize){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("bill_month",bill_month);
		obj.put("parent_sp_id",parent_sp_id);
		obj.put("sp_id",sp_id);
		obj.put("searchWord",searchWord);
		obj.put("scheme_id",scheme_id);
		obj.put("cp_id",cp_id);
		obj.put("area_id",area_id);
		return getPaginatedList("SPS_BILL.QUERY_BILL_DEPUTE_EMP_LIST",obj,pageIndex,pagerSize);
	}

	/**
	 * 查询协作合作伙伴数量---委托实缴账单
	 * @param sp_id
	 * @param area_id
	 * @param bill_month
	 * @return
	 */
	public Integer queryPaiddCooperationCount(Integer sp_id,Integer area_id, String bill_month,String spname){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("bill_month",bill_month);
		obj.put("area_id",area_id);
		obj.put("sp_id",sp_id);
		obj.put("spname",spname);
		Integer ret = (Integer) queryForObject("SPS_BILL.QUERY_BILL_COOPER_CP_COUNT", obj );
		return ret.intValue();
	}

	/**
	 * 查询协作合作伙伴列表---委托实缴账单
	 * @param sp_id
	 * @param area_id
	 * @param bill_month
	 * @return
	 */
	public List<Map<String,Object>> queryPaiddCooperationList(Integer sp_id,Integer area_id, String bill_month,String spname,Integer pageIndex,Integer pagerSize){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("bill_month",bill_month);
		obj.put("area_id",area_id);
		obj.put("sp_id",sp_id);
		obj.put("spname",spname);
		return getPaginatedList("SPS_BILL.QUERY_BILL_PAYMENT_LIST",obj,pageIndex,pagerSize);
	}

	/**
	 * 账目核销-修改实收官费和实收服务费
	 * @param spsBill 
	 * @return
	 */
	public int modifyFeeOfOfficeAndService(ContextInfo cti, SpsBill spsBill) {
		
		return update(cti, "SPS_BILL.modifyFeeOfOfficeAndService", spsBill);
	}

	/**
	 * 财务处理 实缴账单列表
	 * @param whereMap
	 * @param pageSize 
	 * @param pageIndex 
	 * @return
	 */
	public List<Map<String,Object>> queryBillPiadList(Map<String, Object> whereMap, Integer pageIndex, Integer pageSize) {
        ContextInfo cti = (ContextInfo)whereMap.get("cti");
		// 数据权限
        String authority = sysFunctionDataService.getDataAuthorityByUser(cti.getUserId(), cti.getUserType(), Constant.DATATYPE);
        whereMap.put("authority", authority);
		return getPaginatedList("SPS_BILL.QUERY_BILL_PIAD_LIST",whereMap,pageIndex,pageSize);
	}

	/**
	 * 财务处理 实缴账单列表个数
	 * @param whereMap
	 * @return
	 */
	public Integer queryBillPiadListCount(Map<String, Object> whereMap) {
        ContextInfo cti = (ContextInfo)whereMap.get("cti");
		// 数据权限
        String authority = sysFunctionDataService.getDataAuthorityByUser(cti.getUserId(), cti.getUserType(), Constant.DATATYPE);
        whereMap.put("authority", authority);
		return (Integer) queryForObject("SPS_BILL.QUERY_BILL_PIAD_LIST_COUNT", whereMap);
	}

	/**
	 * 财务处理 实缴账单列表 实缴详情个数
	 * @param whereMap
	 * @return
	 */
	public Integer queryPaidDetailListCount(Map<String, Object> whereMap) {
		return (Integer) queryForObject("SPS_BILL.queryPaidDetailListCount", whereMap);
	}

	/**
	 * 财务处理 实缴账单列表 实缴详情
	 * @param whereMap
	 * @return
	 */
	public List<Map<String, Object>> queryPaidDetailList(Map<String, Object> whereMap, Integer pageIndex,Integer pageSize) {
		return getPaginatedList("SPS_BILL.queryPaidDetailList",whereMap,pageIndex,pageSize);
	}

	/**
	 * 财务处理 实缴账单列表 差额详情个数
	 * @param whereMap
	 * @return
	 */
	public Integer queryMarginDetailListCount(Map<String, Object> whereMap) {
		return (Integer) queryForObject("SPS_BILL.queryMarginDetailListCount", whereMap);
	}

	/**
	 * 财务处理 实缴账单列表 差额详情
	 * @param whereMap
	 * @return
	 */
	public List<Map<String, Object>> queryMarginDetailList(Map<String, Object> whereMap, Integer pageIndex,Integer pageSize) {
		return getPaginatedList("SPS_BILL.queryMarginDetailList",whereMap,pageIndex,pageSize);
	}


	/**
	 *
	 * @param sp_id
	 * @param bill_month
     * @return
     */
	public List<Map<String,Object>> checkSpBillStatus(Integer sp_id,Integer cp_id,String bill_month,String bill_type){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("bill_month",bill_month);
		obj.put("sp_id",sp_id);
		obj.put("cp_id",cp_id);
		obj.put("bill_type",bill_type);
		return (List<Map<String,Object>>)queryForList("SPS_BILL.CHECK_BILL_STATS_BY_SP", obj );
	}

	public Map<String,Object> queryCmBillMargeDetail(Integer spid,Integer cmid,Integer bill_id){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("cmid",cmid);
		obj.put("bill_id",bill_id);
		obj.put("spid",spid);
		return (Map<String,Object>)queryForObject("SPS_BILL.QUERY_BILL_DETAIL_MARGE",obj);
	}

	/**
	 * 财务处理 实缴账单 修改实缴凭证
	 * @param whereMap
	 * @return
	 */
	public int modifyPaidEvidence(ContextInfo cti, Map<String, Object> whereMap) {
		return (Integer) update(cti, "SPS_BILL.modifyPaidEvidence", whereMap);
	}

	/**
	 * 协作平台 委托实缴账单列表个数
	 * @param whereMap
	 * @return
	 */
	public Integer queryDeputePiadListCount(Map<String, Object> whereMap,ContextInfo user) {
		// 数据权限
        String authority = sysFunctionDataService.getDataAuthorityByUser(user.getUserId(), user.getUserType(), Constant.DATATYPE);
        whereMap.put("authority",authority);
		return (Integer) queryForObject("SPS_BILL.queryDeputePiadListCount", whereMap);
	}

	/**
	 * 协作平台 委托实缴账单列表
	 * @param whereMap
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<Map<String, Object>> queryDeputePiadList(Map<String, Object> whereMap
			, Integer pageIndex,Integer pageSize,ContextInfo cti ) {
		// 数据权限
        String authority = sysFunctionDataService.getDataAuthorityByUser(cti.getUserId(), cti.getUserType(), Constant.DATATYPE);
        whereMap.put("authority",authority);
		return getPaginatedList("SPS_BILL.queryDeputePiadList",whereMap,pageIndex,pageSize);
	}

	/**
	 * 协作平台 委托实缴账单 详情列表个数
	 * @param whereMap
	 * @return
	 */
	public Integer queryDeputePaidDetailListCount(Map<String, Object> whereMap,ContextInfo cti) {
		String authority = sysFunctionDataService.getDataAuthorityByUser(cti.getUserId(), cti.getUserType(), Constant.DATATYPE);
        whereMap.put("authority",authority);
		return (Integer) queryForObject("SPS_BILL.queryDeputePaidDetailListCount", whereMap);
	}

	/**
	 * 协作平台 委托实缴账单 详情列表 
	 * @param whereMap
	 * @return
	 */
	public List<Map<String, Object>> queryDeputePaidDetailList(Map<String, Object> whereMap, ContextInfo  user,Integer pageIndex,Integer pageSize) {
		String authority = sysFunctionDataService.getDataAuthorityByUser(user.getUserId(), user.getUserType(), Constant.DATATYPE);
        whereMap.put("authority",authority);
		return getPaginatedList("SPS_BILL.queryDeputePaidDetailList",whereMap,pageIndex,pageSize);
	}

	/**
	 * 协作平台 委托实缴账单 详情 实缴详情个数
	 * @param whereMap
	 * @return
	 */
	public Integer queryPaidDetailOfDeputeListCount(Map<String, Object> whereMap) {
		return (Integer) queryForObject("SPS_BILL.queryPaidDetailOfDeputeListCount", whereMap);
	}

	/**
	 * 协作平台 委托实缴账单 详情 实缴详情
	 * @param whereMap
	 * @return
	 */
	public List<Map<String, Object>> queryPaidDetailOfDeputeList(Map<String, Object> whereMap, Integer pageIndex,Integer pageSize) {
		return getPaginatedList("SPS_BILL.queryPaidDetailOfDeputeList",whereMap,pageIndex,pageSize);
	}

	/**
	 * 协作平台 委托实缴账单 详情 差额详情个数
	 * @param whereMap
	 * @return
	 */
	public Integer queryMarginDetailOfDeputeListCount(Map<String, Object> whereMap) {
		return (Integer) queryForObject("SPS_BILL.queryMarginDetailOfDeputeListCount", whereMap);
	}

	/**
	 * 协作平台 委托实缴账单 详情 差额详情
	 * @param whereMap
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<Map<String, Object>> queryMarginDetailOfDeputeList(Map<String, Object> whereMap, Integer pageIndex,Integer pageSize) {
		return getPaginatedList("SPS_BILL.queryMarginDetailOfDeputeList",whereMap,pageIndex,pageSize);
	}
	/**
	 *  查询 账单数量
	 *
	 * @author lifq
	 *
	 * 2016年8月25日  上午11:14:37
	 */
	public HashMap<String,Object> queryBillCount(HashMap<String,Object> parameterMap){
		HashMap<String,Object> map = (HashMap<String, Object>) queryForObject("SPS_BILL.queryBillCount", parameterMap);
		return map;
	}
	
	/**
	 *  查询 账单金额
	 *
	 * @author lifq
	 *
	 * 2016年8月25日  上午11:14:37
	 */
	public HashMap<String,Object> queryBillMoney(HashMap<String,Object> parameterMap){
		HashMap<String,Object> map = (HashMap<String, Object>) queryForObject("SPS_BILL.queryBillMoney", parameterMap);
		return map;
	}
	
	/**
	 *  查询 服务费
	 *
	 * @author lifq
	 *
	 * 2016年8月25日  上午11:14:37
	 */
	public HashMap<String,Object> queryServiceMoney(HashMap<String,Object> parameterMap){
		HashMap<String,Object> map = (HashMap<String, Object>) queryForObject("SPS_BILL.queryServiceMoney", parameterMap);
		return map;
	}
	/**
	 * 查询 年收入统计
	 *
	 * @author lifq
	 *
	 * 2016年8月25日  下午8:02:35
	 */
	public List<Map<String,Object>> queryYearMoney(HashMap<String,Object> parameterMap){
		return queryForList("SPS_BILL.queryYearMoney", parameterMap);
	}

	/**
	 * 根据委托账单ID修改总包账单状态
	 * @param bill_id
	 * @return
     */
	public Integer updateBillByDeputeBillId(ContextInfo cti, Integer bill_id,Integer spId){
		HashMap<String,Object> obj = new HashMap<>();
		obj.put("bill_id",bill_id);
		obj.put("spId",spId);
		return update(cti, "SPS_BILL.UpdateSPS_BILLBYDEPUTE_BILLID",obj);
	}

	public Integer updateByDeputeBillId(ContextInfo cti, SpsBill bill){
		return update(cti, "SPS_BILL.UpdateDEPUTE_BILLID",bill);
	}

	/**
	 * 查询总包账单状态
	 * @param bill_id
	 * @param spId
     * @return
     */
	public Integer queryBillByDeputeBillIdCount(ContextInfo cti, Integer bill_id,Integer spId){
		HashMap<String,Object> obj = new HashMap<>();
		obj.put("bill_id",bill_id);
		obj.put("spId",spId);
		return  (Integer) queryForObject("SPS_BILL.QUERY_SPS_BILLBYDEPUTE_BILLID_COUNT",obj);
	}

	public Integer updateBillSend(ContextInfo cti, SpsBill spsBill){
		return update(cti, "SPS_BILL.UPDATE_SPS_BILL_SEND",spsBill);
	}


	//关联官费查询
	public List<Management> FindManage(SpsBill vo, Integer pageIndex, Integer pageSize)
	{
		if(StringUtils.isBlank(vo.getPeriod()))
			vo.setPeriod(null);
		if(StringUtils.isBlank(vo.getBillNum()))
			vo.setBillNum(null);
		if(null == vo.getExpendStatus())
			vo.setExpendStatus(null);
		return getPaginatedList("SPS_BILL.FreeFindAccount_Manage", vo,pageIndex,pageSize);

	}

	public Integer FindManageCount(SpsBill vo)
	{
		if(StringUtils.isBlank(vo.getPeriod()))
			vo.setPeriod(null);
		if(StringUtils.isBlank(vo.getBillNum()))
			vo.setBillNum(null);
		if(null == vo.getExpendStatus())
			vo.setExpendStatus(null);
		Integer ret = (Integer) queryForObject("SPS_BILL.FreeFindAccount_Count", vo );
		return ret.intValue();
	}

	/**
	 * 获取请款总金额
	 * @return
	 */
	public Map<String,Object> queryExpAmount(){
		return (Map<String,Object>)queryForObject("SPS_BILL.QUERY_EXP_AMOUNT", null );
	}

	/**
	 * 获取付款总金额
	 * @return
	 */
	public Map<String,Object> queryPayAmount(){
		return (Map<String,Object>)queryForObject("SPS_BILL.QUERY_PAY_AMOUNT", null );
	}


	//服务费管理
	/**
	 * 服务费查询
	 * @return
	 */
	public List<Management> FindServiceManage(SpsBill vo,Integer pageIndex,Integer pageSize)
	{
		if(StringUtils.isBlank(vo.getPeriod()))
			vo.setPeriod(null);
		if(StringUtils.isBlank(vo.getBillNum()))
			vo.setBillNum(null);
		if(null==vo.getExpendServiceStatus())
			vo.setExpendServiceStatus(null);
		return getPaginatedList("SPS_BILL.FreeFindService_Manage", vo,pageIndex,pageSize);

	}
	//总条数
	public Integer FindServiceManageCount(SpsBill vo)
	{
		if(StringUtils.isBlank(vo.getPeriod()))
			vo.setPeriod(null);
		if(StringUtils.isBlank(vo.getBillNum()))
			vo.setBillNum(null);
		if(null==vo.getExpendServiceStatus())
			vo.setExpendServiceStatus(null);
		Integer ret = (Integer) queryForObject("SPS_BILL.FreeFindService_Count", vo );
		return ret.intValue();
	}
	/**
	 * 获取服务费请款总金额
	 * @return
	 */
	public Map<String,Object> queryServiceExpAmount(){
		return (Map<String,Object>)queryForObject("SPS_BILL.QUERY_SERVICE_EXP_AMOUNT", null );
	}

	/**
	 * 获取服务费付款总金额
	 * @return
	 */
	public Map<String,Object> queryServicePayAmount(){
		return (Map<String,Object>)queryForObject("SPS_BILL.QUERY_SERVICE_PAY_AMOUNT", null );
	}

	/**
	 * 分润总金额
	 * @return
	 */
	public Map<String,Object> queryServiceGainAmount(){
		return (Map<String,Object>)queryForObject("SPS_BILL.QUERY_SERVICE_GAIN_AMOUNT", null );
	}


	/**
	 * 根据条件获取账单个数
	 * @param bill
	 * @return
	 */
	public Integer queryBillListCount(SpsBill bill){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("period",bill.getPeriod());
		obj.put("cpid",bill.getCpId());
		obj.put("fee_pay_status",bill.getFeePayStatus());
		Integer ret = (Integer) queryForObject("SPS_BILL.QUERY_CORP_ALL_BILL_LIST_COUNT", obj );
		return ret.intValue();
	}

	/**
	 *  根据条件获取账单列表
	 * @param bill
	 * @return
	 */
	public List<Map<String, Object>> queryBillList(SpsBill bill,Integer pageIndex,Integer pagerSize){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("period",bill.getPeriod());
		obj.put("cpid",bill.getCpId());
		obj.put("bill_id",bill.getBillId());
		obj.put("fee_pay_status",bill.getFeePayStatus());
		return getPaginatedList("SPS_BILL.QUERY_CORP_ALL_BILL_LIST",obj,pageIndex,pagerSize);
	}

	/**
	 * 获取企业账单基本信息
	 * @param cpid
	 * @return
	 */
	public Map<String,Object> queryCmBillDetail(Integer cpid,Integer bill_id){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("cmid",cpid);
		obj.put("bill_id",bill_id);
		return (Map<String,Object>)queryForObject("SPS_BILL.QUERY_CORP_BILL_DETAIL",obj);
	}

	/**
	 * 获取支付账单下的分包账单
	 * @param billNum
	 * @return
	 */
	public List<Map<String, Object>> queryDeputeBillList(String billNum){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("billNum",billNum);
		return queryForList("SPS_BILL.QUERY_PAY_BILL_SP_BILL_LIST",obj);
	}

	/**
	 * 获取账单支付收款详情
	 * @param bill
	 * @return
	 */
	public Map<String, Object> queryCorpBillPaymentDetail(SpsBill bill){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("cpid",bill.getCpId());
		obj.put("billid",bill.getBillId());
		return (Map<String,Object>)queryForObject("SPS_BILL.QUERY_CORP_BILL_PAYMENT_DETAIL",obj);
	}


	/**
	 * 获取账单支付收款详情
	 * @param bill
	 * @return
	 */
	public Map<String, Object> queryBillPaymentDetail(SpsBill bill){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("cpid",bill.getCpId());
		obj.put("billid",bill.getBillId());
		obj.put("spid",bill.getSpId());
		return (Map<String,Object>)queryForObject("SPS_BILL.QUERY_BILL_PAYMENT_DETAIL",obj);
	}

	/**
	 * 企业实缴列表数据
	 *
	 * @param bill
	 * @param pageIndex
	 * @param pagerSize
	 * @return
	 */
	public List<Map<String, Object>> findSpsBillByBilltype(SpsBill bill, Integer pageIndex,Integer pagerSize) {
		return getPaginatedList("SPS_BILL.FIND_SPSBILL_BY_BILLTYPE", bill, pageIndex, pagerSize);
	}

	/**
	 * 实缴账单详情
	 *
	 * @param bill
	 * @return
	 */
	public Map<String, Object> findPaidBillDetail(SpsBill bill) {
		return (Map<String,Object>)queryForObject("SPS_BILL.FIND_PAIDBILL_DETAIL", bill);
	}

	/**
	 * 获取业务联系人
	 * @param cpId
	 * @return
	 */
	public Map<String,Object> queryBillSpan(Integer cpId){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("cpId",cpId);
		return (Map<String,Object>)queryForObject("SPS_BILL.QUERY_BILL_COLLABORATOR", obj);

	}

	public List<SpsBill> freeFindOrderBy(SpsBill obj, String orderByColName) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		if(obj instanceof SpsBill){
			_hashmap = ((SpsBill)obj).toHashMap();
		}
		_hashmap.put("_orderBy", orderByColName );

		return queryForList("SPS_BILL.FreeFindSPS_BILLOrdered", _hashmap);
	}

	/**
	 * 获取企业最新应付账单月份
	 * @param cpId
	 * @return
	 */
	public String queryMaxBillMonth(Integer cpId){
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("cpId",cpId);
		return (String)queryForObject("SPS_BILL.QUERY_CORP_MAX_BILL_MONTH", _hashmap);
	}
    /**
     *
     * @Title: findPbillInfo
     * @Description: 查询实缴账单明细
     * @param @param vo
     * @param @param pageIndex
     * @param @param pagerSize
     * @param @return    设定文件
     * @return List<Map<String,Object>>    返回类型
     * @throws
     */
    public List<Map<String , Object>> findPbillInfo(SpsBill vo, Integer pageIndex,Integer pagerSize){
        return getPaginatedList("SPS_BILL.FINDCMbillInfo", vo, pageIndex, pagerSize);
    }

    public Integer countPbillInfo(SpsBill vo){
        Integer ret = (Integer) queryForObject("SPS_BILL.CountFINDCMbillInfo", vo );
        return ret.intValue();
    }

	/**
	 *  根据账单编号获取账单信息
	 *  @param   billNum
	 *	@return 			: com.xfs.bill.model.SpsBill
	 *  @createDate  	: 2016-11-24 11:25
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-24 11:25
	 *  @updateAuthor  :
	 */
	public SpsBill queryBillByBillNum(String billNum){
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("billNum",billNum);
		return (SpsBill)queryForObject("SPS_BILL.QUERY_SPS_BILL_BY_BILLNUM", _hashmap);
	}

	/**
	 * 获取没有核销的账单数量
	 * @param obj
	 * @return
	 */
	public Integer countNoSureBill(SpsBill obj){
		return (Integer)queryForObject("SPS_BILL.countNoSureBill", obj);
	}


	public Integer countCollection(HashMap<String,Object> _hashmap){
		return (Integer)queryForObject("SPS_BILL.countCollection",_hashmap);
	}

	public List<Map<String,Object>> queryCollectionList(HashMap<String,Object> _hashmap, Integer pageIndex,Integer pagerSize){
		return  getPaginatedList("SPS_BILL.queryCollectionList", _hashmap, pageIndex, pagerSize);
	}

	public SpsBill findByBillNum(SpsBill obj) {
		Object ret = queryForObject("SPS_BILL.findByBillNum", obj );
		if(ret !=null)
			return (SpsBill)ret;
		else 
			return null;
	}


	public int countFreeFindByHr(ContextInfo cti,SpsBill obj) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("cpId", obj.getCpId() );
		_hashmap.put("period", obj.getPeriod() );
		_hashmap.put("status", obj.getStatus() );
		_hashmap.put("authority", cti.getAuthority());
		Integer ret = (Integer) queryForObject("SPS_BILL.CountFreeFindSPS_BILL_BY_HR", cti );
		return ret.intValue();
	}

	@SuppressWarnings("unchecked")
	public List<SpsBillDto> freeFindByHr(ContextInfo cti,SpsBill obj, int pageIndex, int pageSize) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("cpId", obj.getCpId() );
		_hashmap.put("period", obj.getPeriod() );
		_hashmap.put("status", obj.getStatus() );
		_hashmap.put("authority", cti.getAuthority());
		return getPaginatedList("SPS_BILL.FreeFindSPS_BILL_BY_HR", cti, pageIndex, pageSize );
	}

	public List<SpsBillDto> queryTodoBillList(ContextInfo cti){
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("cpId", cti.getOrgId() );
		_hashmap.put("authority", cti.getAuthority());
		return queryForList("SPS_BILL.QUERY_TODO_BILL_LIST", _hashmap);
	}


	public List<Map<String,Object>> queryBillTrend(ContextInfo cti,Integer areaId,List<String> months){
		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("cpId",cti.getOrgId());
		obj.put("areaId",areaId);
		obj.put("months",months);
		obj.put("authority", cti.getAuthority());
		return queryForList("SPS_BILL.QUERY_BILL_TREND",obj);
	}


	public List<InsureEmpDetail> queryEmpBillInsMonth(Integer cpId,Integer empId,String period){
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("cpId", cpId );
		_hashmap.put("empId", empId );
		_hashmap.put("period", period );
		return queryForList("SPS_BILL.QUERY_EMP_BILL_BY_INSMONTH", _hashmap);
	}

	public List<InsureEmpDetail> queryEmpBillMonth(ContextInfo cti, Integer empId, String period){
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("cpId", cti.getOrgId() );
		_hashmap.put("empId", empId );
		_hashmap.put("period", period );
		_hashmap.put("authority", cti.getAuthority());
		return queryForList("SPS_BILL.QUERY_EMP_BILL_BY_BILLMONTH", _hashmap);
	}

	public List<InsureEmpDetail> queryEmpBillPayment(ContextInfo cti, Integer empId, String period){
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("cpId", cti.getOrgId() );
		_hashmap.put("empId", empId );
		_hashmap.put("period", period );
		_hashmap.put("authority", cti.getAuthority());
		return queryForList("SPS_BILL.QUERY_EMP_BILL_BY_PAYMENT", _hashmap);
	}

	public Map<String, Object> findBillDetailOne(ContextInfo cti,Integer billId) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("cpId", cti.getOrgId() );
		_hashmap.put("billId", billId );
		_hashmap.put("authority", cti.getAuthority());
		return (Map<String,Object>)queryForObject("SPS_BILL.QUERY_BILL_DETAIL_STEP_ONE", _hashmap);
	}

	public Map<String, Object> findBillDetailSed(ContextInfo cti,Integer billId) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("cpId", cti.getOrgId() );
		_hashmap.put("billId", billId );
		_hashmap.put("authority", cti.getAuthority());
		return (Map<String,Object>)queryForObject("SPS_BILL.QUERY_BILL_DETAIL_STEP_SED", _hashmap);
	}

	public Map<String, Object> findBillDetailThird(ContextInfo cti,Integer billId) {
		HashMap<String,Object> _hashmap = new HashMap<String,Object>();
		_hashmap.put("cpId", cti.getOrgId() );
		_hashmap.put("billId", billId );
		_hashmap.put("authority", cti.getAuthority());
		return (Map<String,Object>)queryForObject("SPS_BILL.QUERY_BILL_DETAIL_STEP_THIRD", _hashmap);
	}


	public Integer updateBillReview(ContextInfo cti, SpsBill spsBill){
		return update(cti, "SPS_BILL.UpdateDEPUTE_BILLID",spsBill);
	}

	/**
	 * 获取服务商对比统计
	 *  @param billId
	 *  @return 
	 *	@return 			: Map<String,Object> 
	 *  @createDate  	: 2017年7月12日 下午2:10:57
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年7月12日 下午2:10:57
	 *  @updateAuthor  :
	 */
	public Map<String, Object> findBillCount(ServiceBillVo vo) {
		return (Map<String,Object>)queryForObject("SPS_BILL.findBillCount", vo);
	}

	/**
	 * 根据企业ID、服务商ID和月份获取参保城市
	 *  @param vo
	 *  @return 
	 *	@return 			: List<Map<String,Object>> 
	 *  @createDate  	: 2017年7月14日 下午2:09:45
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年7月14日 下午2:09:45
	 *  @updateAuthor  :
	 */
	public List<Map<String, Object>> findServiceBillDetailsArea(ServiceBillVo vo) {
		return queryForList("SPS_BILL.findServiceBillDetailsArea",vo);
	}

	/**
	 * 获取服务商对比详情统计
	 *  @param cpId
	 *  @param spId
	 *  @param schemeIds
	 *  @param spsName
	 *  @param areaId
	 *  @param authority
	 *  @return 
	 *	@return 			: Map<String,Object> 
	 *  @createDate  	: 2017年7月17日 下午2:47:46
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年7月17日 下午2:47:46
	 *  @updateAuthor  :
	 */
	public Map<String, Object> findBillDetailsCount(ServiceBillVo vo) {
		return (Map<String,Object>)queryForObject("SPS_BILL.findBillDetailsCount", vo);
	}

	/**
	 * 服务商对账详情数据列表总数
	 *  @param vo
	 *  @return 
	 *	@return 			: Integer 
	 *  @createDate  	: 2017年7月19日 上午11:05:50
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年7月19日 上午11:05:50
	 *  @updateAuthor  :
	 */
	public Integer findServiceBillDetailsListCount(ServiceBillVo vo) {
		Integer ret = (Integer) queryForObject("SPS_BILL.findServiceBillDetailsListCount", vo );
		return ret.intValue();
	}

	/**
	 * 服务商对账详情数据列表
	 *  @param vo
	 *  @param pageIndex
	 *  @param pageSize
	 *  @return 
	 *	@return 			: List<Map<String,Object>> 
	 *  @createDate  	: 2017年7月19日 上午11:05:53
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年7月19日 上午11:05:53
	 *  @updateAuthor  :
	 */
	public List<Map<String, Object>> findServiceBillDetailsList(ServiceBillVo vo, int pageIndex, int pageSize) {
		return getPaginatedList("SPS_BILL.findServiceBillDetailsList", vo, pageIndex, pageSize );
	}

	/**
	 * 服务商对账详情异常数据列表
	 *  @param vo
	 *  @return 
	 *	@return 			: List<Map<String,Object>> 
	 *  @createDate  	: 2017年7月19日 下午1:54:17
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年7月19日 下午1:54:17
	 *  @updateAuthor  :
	 */
	public List<Map<String, Object>> findServiceBillDetailsErrorList(ServiceBillVo vo) {
		return queryForList("SPS_BILL.findServiceBillDetailsErrorList",vo);
	}

	/**
	 * 服务商对账详情数据列表
	 *  @param vo
	 *  @return 
	 *	@return 			: List<Map<String,Object>> 
	 *  @createDate  	: 2017年7月21日 下午2:22:50
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年7月21日 下午2:22:50
	 *  @updateAuthor  :
	 */
	public List<Map<String, Object>> serviceBillDetailsExport(ServiceBillVo vo) {
		return queryForList("SPS_BILL.findServiceBillDetailsList", vo);
	}

	/**
	 * 服务商个人对账详情
	 *  @param vo
	 *  @return 
	 *	@return 			: List<Map<String,Object>> 
	 *  @createDate  	: 2017年7月27日 下午3:48:27
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年7月27日 下午3:48:27
	 *  @updateAuthor  :
	 */
	public List<Map<String, Object>> findEmpInsurance(ServiceBillVo vo) {
		return queryForList("SPS_BILL.findEmpInsurance", vo);
	}

}
