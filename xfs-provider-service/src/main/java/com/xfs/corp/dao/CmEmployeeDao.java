package com.xfs.corp.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.xfs.corp.dto.EmpListDto;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.xfs.base.model.SysDictitem;
import com.xfs.business.dto.BatchAdjBaseQuery;
import com.xfs.business.dto.BatchRatioEmpQuery;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.corp.dto.BatchReduceEmplQuery;
import com.xfs.corp.dto.CSCmEmployeeDto;
import com.xfs.corp.model.CmEmployee;
import com.xfs.sp.model.SpsAccount;
import com.xfs.sp.model.SpsScheme;

/**
 * @author 	: yangfangwei
 * @date 	: 2016-11-9 14:10:00
 * @version 	: V1.0
 */
@Repository
public class CmEmployeeDao extends BaseSqlMapDao {

    public int countFindAll() {
        Integer ret = (Integer) queryForObject("CM_EMPLOYEE.CountFindAllCM_EMPLOYEE", null);
        return ret.intValue();
    }

    public CmEmployee findByPK(CmEmployee obj) {
        Object ret = queryForObject("CM_EMPLOYEE.FindByPK", obj);
        if (ret != null)
            return (CmEmployee) ret;
        else
            return null;
    }

    public CmEmployee findEmpAndJsonByPK(CmEmployee obj) {
        Object ret = queryForObject("CM_EMPLOYEE.FindEmpAndJsonByPK", obj);
        if (ret != null)
            return (CmEmployee) ret;
        else
            return null;
    }

    @SuppressWarnings("unchecked")
    public List<CmEmployee> freeFind(CmEmployee obj) {
        return queryForList("CM_EMPLOYEE.FreeFindCM_EMPLOYEE", obj);
    }

    public List<CmEmployee> findEmpByIdCard(CmEmployee obj) {
        return queryForList("CM_EMPLOYEE.findEmpByIdCard", obj);
    }

    public int countFreeFind(CmEmployee obj) {
        Integer ret = (Integer) queryForObject("CM_EMPLOYEE.FindByCpIdCount", obj);
        return ret.intValue();
    }

    @SuppressWarnings("unchecked")
    public List<CmEmployee> freeFind(CmEmployee obj, int pageIndex, int pageSize) {
        return getPaginatedList("CM_EMPLOYEE.FindByCpId", obj, pageIndex, pageSize);
    }

    @SuppressWarnings("unchecked")
    public List<CmEmployee> freeFind(CmEmployee obj, String orderByColName) {
        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        if (obj instanceof CmEmployee) {
            _hashmap = ((CmEmployee) obj).toHashMap();
        }
        _hashmap.put("_orderBy", orderByColName);

        return queryForList("CM_EMPLOYEE.FreeFindCM_EMPLOYEEOrdered", _hashmap);
    }

    @SuppressWarnings("unchecked")
    public List<CmEmployee> freeFind(CmEmployee obj, int pageIndex, int pageSize, String orderByColName) {
        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        if (obj instanceof CmEmployee) {
            _hashmap = ((CmEmployee) obj).toHashMap();
        }
        _hashmap.put("_orderBy", orderByColName);

        return getPaginatedList("CM_EMPLOYEE.FreeFindCM_EMPLOYEEOrdered", _hashmap, pageIndex, pageSize);
    }

    public int saveAll(ContextInfo cti, Collection<CmEmployee> objColl) {
        int i = 0;
        if (objColl != null) {
            Iterator<CmEmployee> it = objColl.iterator();
            while (it.hasNext()) {
                CmEmployee oneObj = (CmEmployee) it.next();
                i += save(cti, oneObj);
            }
        }
        return i;
    }

    public int save(ContextInfo cti, CmEmployee vo) {
        CmEmployee obj = (CmEmployee) vo;

        if (exists(obj)) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
    }

    public int insert(ContextInfo cti, CmEmployee obj) {

        obj.fixup();
        return insert(cti, "CM_EMPLOYEE.InsertCM_EMPLOYEE", obj);
    }

    public int update(ContextInfo cti, CmEmployee obj) {

        obj.fixup();
        return update(cti, "CM_EMPLOYEE.UpdateCM_EMPLOYEE", obj);

    }

    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
    public int remove(ContextInfo cti, CmEmployee obj) {

        if (obj == null) {
            return 0;
        }

        obj.fixup();

        return update(cti, "CM_EMPLOYEE.Lgoic_del_CM_EMPLOYEE", obj);

    }

    public int removeObjectTree(ContextInfo cti, CmEmployee obj) {

        obj.fixup();
        CmEmployee oldObj = (CmEmployee) queryForObject("CM_EMPLOYEE.FindByPK", obj);
        if (oldObj == null) {
            return 0;
        }

        return delete(cti, "CM_EMPLOYEE.DeleteCM_EMPLOYEE", oldObj);

    }

    public boolean exists(CmEmployee vo) {

        boolean keysFilled = true;
        boolean ret = false;
        CmEmployee obj = (CmEmployee) vo;

        keysFilled = keysFilled && (null != obj.getEmpId());

        if (keysFilled) {
            Integer retInt = (Integer) queryForObject("CM_EMPLOYEE.CountCM_EMPLOYEE", obj);
            if (retInt != null && retInt.intValue() > 0) {
                ret = true;
            }
        }

        return ret;
    }

    // 温馨提示：
    // 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    // 2016/04/08 15:03:51
    public int countFindAllByCpId(Integer orgId) {
        Integer ret = (Integer) queryForObject("CM_EMPLOYEE.CountFindAllCM_EMPLOYEE", null);
        return ret.intValue();
    }

    public Map<String, Object> freeFindOneByEmpId(CmEmployee obj) {
        return (Map<String, Object>) queryForObject("CM_EMPLOYEE.FreeFindCM_EMPLOYEEByEmpId", obj);
    }

    /**
     * 根据服务商ID, openid, 手机号 定位当前用户
     *
     * @param vo
     * @param sp_id
     * @return
     * @auth konglc
     */
    public CmEmployee findPersonByOpenIdOrMobile(CmEmployee vo, Integer sp_id) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        if (null != vo.getOpenid() && !"".equals(vo.getOpenid()))
            hashMap.put("openid", vo.getOpenid());
        if (null != vo.getMobile() && !"".equals(vo.getMobile()))
            hashMap.put("mobile", vo.getMobile());
        hashMap.put("sp_id", sp_id);
        List<CmEmployee> cmEmployees = queryForList("FreeFind_EMPLOYEEOpenIdOrMobile", hashMap);
        if (null != cmEmployees && !cmEmployees.isEmpty())
            return cmEmployees.get(0);
        return null;
    }

    /**
     * 根据企业ID 查询当前企业下所有员工的身份证号
     */
    public List<CmEmployee> findEmpIdCardByCpId(CmEmployee vo) {
        return queryForList("CM_EMPLOYEE.CM_EMPLOYEEIdCardByCpId", vo);
    }

    /**
     * 获取企业人员社保费用类型 社保代扣代缴/公积金代扣代缴/服务费用
     *
     * @param spid
     * @param cmid
     * @return
     */
    public Map<String, Object> sumCmEmployeeFee(Integer spid, Integer cmid) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("spid", spid);
        obj.put("cmid", cmid);
        return (Map<String, Object>) queryForObject("CM_EMPLOYEE.CM_EMPLOYEE_SUM_FEE", obj);
    }

    /**
     * 获取企业人员列表
     *
     * @param spid
     * @param cmid
     * @return
     */
    public List<CmEmployee> queryCmEmployeeFeeList(Integer spid, Integer cmid) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("spid", spid);
        obj.put("cmid", cmid);
        return queryForList("CM_EMPLOYEE.CM_EMPLOYEE_FEE_LIST", obj);
    }

    /**
     * 新增员工之前查询 sp_cm_relation sps_account 查询社保&公积金 (状态、缴纳方式、参保地)
     */
    public Map<String, Object> findInsFunByCpId(Integer cpId, Integer spId) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("spId", spId);
        obj.put("cpId", cpId);
        return (Map<String, Object>) queryForObject("CM_EMPLOYEE.findInsFunByCpId", obj);
    }

    /**
     * 新增员工 如身份证号已存在 修改员工信息
     */
    public List<CmEmployee> findIdCardBySpIdCpId(CmEmployee cmp) {
        return queryForList("CM_EMPLOYEE.findIdCardBySpIdCpId", cmp);
    }

    /**
     * 获取所有服务商下所维护的员工
     *
     * @return
     */
    public List<Map<String, Object>> findAllSpEmployee() {
        return queryForList("CM_EMPLOYEE.findAllSpEmployee", null);
    }

    /**
     * 根据企业ID 修改企业旗下所有员工
     */
    public int updateByCpId(ContextInfo cti, CmEmployee obj) {

        obj.fixup();
        return update(cti, "CM_EMPLOYEE.UpdateCM_EMPLOYEEByCpId", obj);

    }

    /**
     * 根据库id获取所有库下的员工
     *
     * @param vo
     * @return
     */
    public List<CmEmployee> findEmployeeByAccount(SpsAccount vo) {
        return queryForList("CM_EMPLOYEE.FreeFindCM_EMPLOYEE_BYACCOUNT", vo);
    }

    @SuppressWarnings("unchecked")
	public List<Map<String,Object>> findEmployeeList(CmEmployee vo, String schemeType
    		, String keyWord, int pageIndex, int pageSize,Integer spId,String authority) {
        Map<String,Object> obj = new HashMap<String,Object>();
        
        
        obj.put("searchType", vo.getSearchType());
        obj.put("fundState", vo.getFundState());
        obj.put("insuranceState", vo.getInsuranceState());
        obj.put("areaId", vo.getAreaId());
        obj.put("valid", vo.getValid());
        obj.put("keyWord", keyWord);
        obj.put("schemeType", schemeType);
        obj.put("spId", spId);
        obj.put("cpId", vo.getCpId());
        obj.put("authority", authority);
        
//        obj.put("createBy", vo.getCreateBy());
//        obj.put("cpId", vo.getCpId());
//        obj.put("schemeType", schemeType);
//        obj.put("keyWord", keyWord);
//        obj.put("spId", spId);
//        obj.put("authority", authority);
//        obj.put("dr", vo.getDr());
        return getPaginatedList("CM_EMPLOYEE.Find_Employee_List_By_Condition", obj, pageIndex, pageSize);
    }

    public Integer findEmployeeCount(CmEmployee vo, String schemeType, String keyWord
    		,Integer spId,String authority) {
        Map<String,Object> obj = new HashMap<String,Object>();
//        obj.put("createBy", vo.getCreateBy());
//        obj.put("name", vo.getNameEq());
//        obj.put("cpId", vo.getCpId());
//        obj.put("schemeType", schemeType);
//        obj.put("keyWord", keyWord);
//        obj.put("spId", spId);
//        obj.put("authority", authority);
//        obj.put("dr", vo.getDr());
        
        obj.put("searchType", vo.getSearchType());
        obj.put("fundState", vo.getFundState());
        obj.put("insuranceState", vo.getInsuranceState());
        obj.put("areaId", vo.getAreaId());
        obj.put("valid", vo.getValid());
        obj.put("keyWord", keyWord);
        obj.put("schemeType", schemeType);
        obj.put("spId", spId);
        obj.put("cpId", vo.getCpId());
        obj.put("authority", authority);
        Integer ret = (Integer) queryForObject("CM_EMPLOYEE.Find_Employee_List_By_Condition_Count", obj);
        return ret.intValue();
    }

    public Map<String,Object> FindEmployeeBasicInfo(Integer empId, Integer spId){
        HashMap<String,Object> _hashMap = new HashMap<String,Object>();
        _hashMap.put("spId",spId);
        _hashMap.put("empId",empId);
        return  (Map<String, Object>)queryForObject("CM_EMPLOYEE.Find_Employee_Basic_Info", _hashMap);
    }

    public Map<String,Object> findEmployeeInsureInfo(Integer empId){
        HashMap<String,Object> _hashMap = new HashMap<String,Object>();
        _hashMap.put("empId",empId);
        return  (Map<String, Object>)queryForObject("CM_EMPLOYEE.Find_Employee_Insure_Info", _hashMap);
    }

    public Map<String,Object> findEmployeeInfoByPK(Integer empId){
        HashMap<String,Object> _hashMap = new HashMap<String,Object>();
        _hashMap.put("empId",empId);
        return  (Map<String, Object>)queryForObject("CM_EMPLOYEE.Find_Employee_Info_By_PK", _hashMap);
    }

    /**
     * 根据方案id  查询 员工信息
     *
     * @author lifq
     *
     * 2016年8月9日  下午5:06:17
     */
    public List<Map<String,Object>> findEmpBySchemeId(Integer scheme_id,Integer cp_id){
        HashMap<String,Object> _hashMap = new HashMap<String,Object>();
        _hashMap.put("scheme_id",scheme_id);
        _hashMap.put("cp_id",cp_id);
        return queryForList("CM_EMPLOYEE.findEmpBySchemeId", _hashMap);
	}


    /**
     * 获取员工信息
     * @param spId
     * @param cpId
     * @param empId
     * @return
     */
    public CmEmployee queryEmpInfo(Integer spId,Integer cpId,Integer empId){
        Map<String,Object> obj = new HashMap<String,Object>();
        obj.put("spId",spId);
        obj.put("cpId",cpId);
        obj.put("empId",empId);
        return (CmEmployee)queryForObject("CM_EMPLOYEE.QUERY_EMPLOYEE_INFO",obj);
    }


    public List<CmEmployee> findEmpWithDetailByEmpids(List<Integer> empIds, String period, String insuranceOrFund,String payType) {
        HashMap<String,Object> _hashMap = new HashMap<String,Object>();
        _hashMap.put("empIds",empIds);
        _hashMap.put("period", period);
        _hashMap.put("insuranceOrFund", insuranceOrFund);
        _hashMap.put("payType", payType);
        return  queryForList("CM_EMPLOYEE.find_CM_EMPLIST_WithDetail_BY_EMPIDS", _hashMap);
    }
    
    public boolean existsEmp(CmEmployee vo){
    	 boolean keysFilled = true;
    	 Object ret = queryForObject("CM_EMPLOYEE.FindByPkAndCpIdAndDr", vo);
         if (ret == null){
        	 keysFilled = false;
         }
         return keysFilled;
    }

    public List<CmEmployee> queryAllEmpBySpId(Integer spId){
        Map<String,Object> obj = new HashMap<String,Object>();
        obj.put("sp_id",spId);
        return queryForList("CM_EMPLOYEE.QUERY_ALL_EMPLOYEE_INFO_BY_SPID",obj);
    }
    /**
     * 查询 社保、公积金 人数
     *
     * @author lifq
     *
     * 2016年8月16日  下午9:37:53
     */
    public List<Map<String,Object>> queryInsurNumBySpId(Map<String,Object> map){
    	HashMap<String,Object> _hashMap = new HashMap<String,Object>();
        _hashMap.put("spId",map.get("spId"));
        _hashMap.put("state",map.get("state"));
        return queryForList("CM_EMPLOYEE.queryInsurNumBySpId", _hashMap);
    }
    /**
     * 根据方案id 查询员工实体
     *
     * @author lifq
     *
     * 2016年8月24日  下午4:35:57
     */
        public List<CmEmployee> findEmpEntityBySchemeId(Integer schemeId){
            HashMap<String,Object> _hashMap = new HashMap<String,Object>();
            _hashMap.put("schemeId",schemeId);
            return queryForList("CM_EMPLOYEE.findEmpEntityBySchemeId", _hashMap);
        }

        public List<SysDictitem> findSysDicitemByScheme(SpsScheme scheme){
            return queryForList("SYS_DICTITEM.findDicitemBySchemeId", scheme);
    }

    /**
     * 条件查询总数
     * @param cmEmployee
     * @return
     */
    public Integer CountFreeFindCmEmployee(CmEmployee cmEmployee){
        return (Integer) queryForObject("CM_EMPLOYEE.CountFreeFindCM_EMPLOYEE", cmEmployee);

    }

    /* --------- cs 合并 start --------*/

    /**
     * <p>Title: bs询社保在缴公积金在缴总人数</p>
     * <p>Description: bs询社保在缴公积金在缴总人数</p>
     * ${tags}
     */
    public int countFindAllEmployee(){
        Integer ret = (Integer) queryForObject("CM_EMPLOYEE.COUNTFINDALLEMPLOYEE_CORP", null );
        return ret.intValue();
    }
    /**
     * <p>Title: bs按创建日期查询社保在缴公积金在缴总人数</p>
     * <p>Description: bs按创建日期查询社保在缴公积金在缴总人数</p>
     * ${tags}
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> countByDayEmployee(String stringDate){
        return queryForList("CM_EMPLOYEE.COUNTBYDAYEMPLOYEE", stringDate);
    }

    /**
     * <p>Title: cs分页查询人员总数</p>
     * <p>Description: cs分页查询人员总数</p>
     * ${tags}
     */
    @SuppressWarnings("unchecked")
    public List<CmEmployee> freeFindToCs(CmEmployee obj, int pageIndex, int pageSize) {
        return getPaginatedList("CM_EMPLOYEE.FreeFindCM_EMPLOYEE", obj, pageIndex, pageSize);
    }


    /**
     * <p>Title: cs通过id删除人员</p>
     * <p>Description: cs通过id删除人员</p>
     * ${tags}
     */
    public int removeToCs(ContextInfo cti, CmEmployee obj) {

        if (obj == null) {
            return 0;
        }

        obj.fixup();

        return delete(cti, "CM_EMPLOYEE.DeleteCM_EMPLOYEE", obj);

    }


    /**
     * 获取企业人员社保费用类型 社保代扣代缴/公积金代扣代缴/服务费用
     *
     * @param cmid
     * @return
     */
    public Map<String, Object> sumCmEmployeeFee(Integer cmid) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("cmid", cmid);
        return (Map<String, Object>) queryForObject("CM_EMPLOYEE.CM_EMPLOYEE_SUM_FEE_CS", obj);
    }

    /**
     * 查询部门下人数
     * @param depId
     * @return
     */
    public Integer countEmplByDepId(Integer depId){
        Integer retInt = (Integer) queryForObject("CM_EMPLOYEE.CountEmplByDepId", depId);
        return retInt == null ? 0 : retInt;
    }

    /**
     * 新增员工 如身份证号已存在 修改员工信息
     */
    public int checkEmpIdCardAndName(CmEmployee cmp) {
        Integer ret = (Integer) queryForObject("CM_EMPLOYEE.checkEmpIdCardAndName", cmp);
        return ret.intValue();
    }


    /**
     * 页面查询
     * @param obj
     * @param pageIndex
     * @param pageSize
     * @param orderByColName
     * @return
     */
    public List<CmEmployee> freeFindPage(CSCmEmployeeDto obj, int pageIndex, int pageSize, String orderByColName) {
        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        _hashmap = obj.toHashMap();
        _hashmap.put("_orderBy", orderByColName);

        return getPaginatedList("CM_EMPLOYEE.FreeFindCM_EMPLOYEEOrdered1", _hashmap, pageIndex, pageSize);
    }

    /**
     * 页面查询count
     * @param obj
     * @return
     */
    public int countPageFind(CSCmEmployeeDto obj) {
        Integer ret = (Integer) queryForObject("CM_EMPLOYEE.CountFreeFindCM_EMPLOYEE1", obj);
        return ret.intValue();
    }

    /**
     * 修改员工所属部门
     * @param empIds
     * @param depId
     * @param orgId
     * @return
     */
    public int updateDepId(ContextInfo cti, String empIds,Integer depId,Integer orgId) {
        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        _hashmap.put("empId", empIds);
        _hashmap.put("depId", depId);
        _hashmap.put("cpId", orgId);
        return update(cti, "CM_EMPLOYEE.UpdateEmplDepIdByEmpAndCp", _hashmap);

    }


    public List<CmEmployee> findEmpWithDetailByEmpids(List<Integer> empIds) {
        HashMap<String,Object> _hashMap = new HashMap<String,Object>();
        _hashMap.put("empIds",empIds);
        return  queryForList("CM_EMPLOYEE.find_CM_EMPLIST_WithDetail_BY_EMPIDS_CS", _hashMap);
    }

    /**
     * 查询每月离职在职人数
     * @param param
     * @return
     */
    public Integer freeCountByMap(Map<String,Object> param){
        return (Integer)queryForObject("CM_EMPLOYEE.CountFreeFindCM_EMPLOYEE2", param);
    }
        /* --------- cs 合并 end --------*/
    /**
     * 批量更改比例查询人员
     *  @param   ratioEmpQuery  查询参数
     * @return    : java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     *  @createDate   : 14:43 2016/11/16
     *  @author          : yangfw@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 14:43 2016/11/16
     *  @updateAuthor  :
     */
    public List<Map<String,Object>> findEmpByBatchRatio(BatchRatioEmpQuery ratioEmpQuery){

        return queryForList("CM_EMPLOYEE.FindEmpByBatchRatio",ratioEmpQuery);
    }
    /**
     * 批量更改比例查询人员 总数
     *  @param   ratioEmpQuery  查询参数
     * @return    : java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     *  @createDate   : 14:43 2016/11/16
     *  @author          : yangfw@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 14:43 2016/11/16
     *  @updateAuthor  :
     */
    public Integer findEmpByBatchRatioCount(BatchRatioEmpQuery ratioEmpQuery){

        return (Integer)queryForObject("CM_EMPLOYEE.FindEmpByBatchRatioCount",ratioEmpQuery);
    }
    /**
     * 批量更改比例查询人员 分页查询
     *  @param   ratioEmpQuery  查询参数
     * @return    : java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     *  @createDate   : 14:43 2016/11/16
     *  @author          : yangfw@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 14:43 2016/11/16
     *  @updateAuthor  :
     */
    public List<Map<String,Object>> findEmpByBatchRatioPage(BatchRatioEmpQuery ratioEmpQuery,Integer pageIndex,Integer pageSize){

        return getPaginatedList("CM_EMPLOYEE.FindEmpByBatchRatio",ratioEmpQuery,pageIndex,pageSize);
    }
    /**
     * 批量更改比例查询人员
     */
    public List<Map<String,Object>> findEmpByBatchRatioPage(BatchRatioEmpQuery ratioEmpQuery){

        return queryForList("CM_EMPLOYEE.FindEmpByBatchRatio",ratioEmpQuery);
    }
    
    
    public HashMap<String,Object> findEmployeeServiceChargeMap(CmEmployee cmp){
    	return (HashMap<String, Object>) queryForObject("CM_EMPLOYEE.FreeFindCM_EMPLOYEE_SERVICE_CHARGE",cmp);
    }

    /**
     * 批量减员查询列表
     *  @param   query, pageIndex, pageSize]
     * @return    : java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     *  @createDate   : 2016/12/15 15:12
     *  @author          : yangfw@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2016/12/15 15:12
     *  @updateAuthor  :
     */
    public List<Map<String,Object>> findBatchReduceEmplList(BatchReduceEmplQuery query, int pageIndex, int pageSize) {
        return getPaginatedList("CM_EMPLOYEE.FreeFindBatchReduceEmployee", query, pageIndex, pageSize);
    }
    /**
     * 批量减员查询总数
     *  @param   query
     * @return    : java.lang.Integer
     *  @createDate   : 2016/12/15 15:13
     *  @author          : yangfw@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2016/12/15 15:13
     *  @updateAuthor  :
     */
    public Integer findBatchReduceEmplCount(BatchReduceEmplQuery query) {
        Integer ret = (Integer) queryForObject("CM_EMPLOYEE.FreeFindBatchReduceEmployeeCount", query);
        return ret.intValue();
    }
    /**
     * 查询员工所属方案最后受理日期
     *  @param   empIds 员工id
     * @return    : java.lang.Integer
     *  @createDate   : 2016/12/16 11:59
     *  @author          : yangfw@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2016/12/16 11:59
     *  @updateAuthor  :
     */
    public Integer findEmplSchemeMaxEndDate(List<Integer> empIds) {
        Map<String,Object> query = new HashMap();
        query.put("empIds",empIds);
        Integer ret = (Integer) queryForObject("CM_EMPLOYEE.FindEmplsSchemeMaxEndDate", query);
        return ret.intValue();
    }

    /**
     * 批量基数调整exportData
     *  @param   query
     * @return    : java.util.Map<java.lang.String,java.lang.Object>
     *  @createDate   : 2016/12/26 19:10
     *  @author          : yangfw@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2016/12/26 19:10
     *  @updateAuthor  :
     */
    public List<Map<String,Object>> findEmplsByBatchAdjBaseExportData(BatchAdjBaseQuery query) {
        return  (List<Map<String,Object>>) queryForList("CM_EMPLOYEE.FindEmplsByBatchAdjBaseExportData", query);
    }

    /**
     * 通过企业id 证件号码与姓名 查询员工
     *  @param   query
     * @return    : com.xfs.corp.model.CmEmployee
     *  @createDate   : 2016/12/27 15:11
     *  @author          : yangfw@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2016/12/27 15:11
     *  @updateAuthor  :
     */
    public CmEmployee findCpEmplByNameAndIdentityCode(CmEmployee query){
        List<CmEmployee> employees = queryForList("CM_EMPLOYEE.FindCpEmplByNameAndIdentityCode",query);
        if(CollectionUtils.isNotEmpty(employees)){
            return employees.get(0);
        }
        return null;
    }

    /**
     *  批量调整最低基数人员险种列表个数
     *  @param   query
     *  @param
     *	@return 			: java.lang.Integer
     *  @createDate  	: 2017-01-09 15:59
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-01-09 15:59
     *  @updateAuthor  :
     */
    public List<Map<String,Object>> findEmplsByBatchAdjLowerBaseList(BatchAdjBaseQuery query,Integer pageIndex, Integer pageSize) {
        //return  (List<Map<String,Object>>) getPaginatedList("CM_EMPLOYEE.findEmplsByBatchAdjLowerBaseList", query,pageIndex,pageSize);
        return  (List<Map<String,Object>>) queryForList("CM_EMPLOYEE.findEmplsByBatchAdjLowerBaseList", query);
    }

    /**
     *  批量调整最低基数人员险种列表个数
     *  @param   query
     *	@return 			: java.lang.Integer
     *  @createDate  	: 2017-01-09 15:59
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-01-09 15:59
     *  @updateAuthor  :
     */
    public Integer findEmplsByBatchAdjLowerBaseCount(BatchAdjBaseQuery query) {
        Integer ret = (Integer) queryForObject("CM_EMPLOYEE.findEmplsByBatchAdjLowerBaseCount", query);
        return ret.intValue();
    }

    public List<CmEmployee> findEmpNameAndIdentitycodeList(CmEmployee cmEmployee) {
        return   queryForList("CM_EMPLOYEE.findEmpNameAndIdentitycodeList", cmEmployee);
    }

    public List<CmEmployee> findEmpNameList(ContextInfo cti,CmEmployee cmEmployee) {
        Map<String, Object> map = new HashMap<>();
        map.put("cpId", cti.getOrgId());
        map.put("name", cmEmployee.getName());
        map.put("authority", cti.getAuthority());
        return   queryForList("CM_EMPLOYEE.findEmpNameList", map);
    }

    public Map<String, Object> findEmpTotal(Map<String, Object> map){
        return (Map<String, Object>)queryForObject("CM_EMPLOYEE.findEmpTotal", map);
    }

    public List<Map<String, Object>> findEmpTotalArea(Map<String, Object> map){
        return (List<Map<String, Object>>)queryForList("CM_EMPLOYEE.findEmpTotalArea", map);
    }

    public List<Map<String,Object>> findEmpList(CmEmployee cmEmployee, int pageIndex, int pageSize){
        return (List<Map<String,Object>>)getPaginatedList("CM_EMPLOYEE.findEmpList", cmEmployee, pageIndex, pageSize);
    }

    public List<Map<String,Object>> findEmpList(CmEmployee cmEmployee){
        return (List<Map<String,Object>>)queryForList("CM_EMPLOYEE.findEmpList", cmEmployee);
    }

    public Integer findEmpListCount(CmEmployee cmEmployee) {
        Integer ret = (Integer) queryForObject("CM_EMPLOYEE.findEmpListCount", cmEmployee);
        return ret.intValue();
    }

    public List<Map<String,Object>> paymentTrendList(CmEmployee cmEmployee, int pageIndex, int pageSize){
        return (List<Map<String,Object>>)getPaginatedList("CM_EMPLOYEE.paymentTrendList", cmEmployee, pageIndex, pageSize);
    }

    public Integer paymentTrendListCount(CmEmployee cmEmployee) {
        Integer ret = (Integer) queryForObject("CM_EMPLOYEE.paymentTrendListCount", cmEmployee);
        return ret.intValue();
    }

    public List<Map<String,Object>> empTrendList(CmEmployee cmEmployee, int pageIndex, int pageSize){
        return (List<Map<String,Object>>)getPaginatedList("CM_EMPLOYEE.empTrendList", cmEmployee, pageIndex, pageSize);
    }

    public Integer empTrendListCount(CmEmployee cmEmployee) {
        Integer ret = (Integer) queryForObject("CM_EMPLOYEE.empTrendListCount", cmEmployee);
        return ret.intValue();
    }

    public Map<String, Object> findEmpInfo(CmEmployee cmEmployee){
        return (Map<String, Object>)queryForObject("CM_EMPLOYEE.findEmpInfo", cmEmployee);
    }

    public Integer deleteEmps(ContextInfo cti,Integer cpId, String empIds){

        Map<String, Object> map = new HashMap<>();
        map.put("cpId", cpId);
        map.put("empIds", empIds);
        map.put("authority",cti.getAuthority());

        return update(cti, "CM_EMPLOYEE.deleteEmps", map);
    }
    /**
     * 查询 调基人员列表
     *	
     * @author lifq
     *
     * 2017年6月7日  上午10:47:00
     */
    public List<Map<String, Object>> adjustList(CmEmployee cmEmployee, int pageIndex, int pageSize){
        return  (List<Map<String,Object>>) getPaginatedList("CM_EMPLOYEE.adjustList", cmEmployee, pageIndex, pageSize);
    }

    /**
     * 条件查询总数
     * @param cmEmployee
     * @return
     */
    public Integer adjustCount(CmEmployee cmEmployee){
        return (Integer) queryForObject("CM_EMPLOYEE.adjustCount", cmEmployee);

    }

    /**
     * 导出 调基人员列表
     *
     * @author lifq
     *
     * 2017年6月7日  上午10:36:29
     */
    public List<Map<String,Object>> exportAdjust(ContextInfo cti,String empids){
    	Map<String, Object> map = new HashMap<>();
    	map.put("cpId", cti.getOrgId());
        map.put("empids", empids);
        map.put("authority", cti.getAuthority());
    	return  (List<Map<String,Object>>) queryForList("CM_EMPLOYEE.exportAdjust", map);
    }
    /**
     * 公积金调基列表
     *
     * @author lifq
     *
     * 2017年6月29日  上午11:11:30
     */
    public List<Map<String, Object>> fundAdjustList(CmEmployee cmEmployee, int pageIndex, int pageSize){
        return  (List<Map<String,Object>>) getPaginatedList("CM_EMPLOYEE.fundAdjustList", cmEmployee, pageIndex, pageSize);
    }

    /**
     * 公积金调基列表总数
     * @param cmEmployee
     * @return
     */
    public Integer fundAdjustListCount(CmEmployee cmEmployee){
        return (Integer) queryForObject("CM_EMPLOYEE.fundAdjustListCount", cmEmployee);

    }

    /**
     * 导出公积金调基数据
     *
     * @author lifq
     *
     * 2017年6月29日  上午11:44:05
     */
    public List<Map<String,Object>> exportFundAdjust(ContextInfo cti,String empids){
    	Map<String, Object> map = new HashMap<>();
    	map.put("cpId", cti.getOrgId());
        map.put("empids", empids);
        map.put("authority", cti.getAuthority());
    	return  (List<Map<String,Object>>) queryForList("CM_EMPLOYEE.exportFundAdjust", map);
    }
    /**
     * 查询 服务商人员列表 记录总数
     *
     * @author lifq
     *
     * 2017年7月21日  上午11:46:04
     */
    public Integer findSpEmpListCount(Map<String,Object> paraMap){
        return (Integer) queryForObject("CM_EMPLOYEE.findSpEmpListCount", paraMap);

    }
    /**
     * 查询 服务商人员列表 (分页)
     *
     * @author lifq
     *
     * 2017年7月21日  上午11:46:04
     */
    public List<Map<String,Object>> findSpEmpList(Map<String,Object> paraMap,int pageIndex, int pageSize){
        return getPaginatedList("CM_EMPLOYEE.findSpEmpList", paraMap, pageIndex, pageSize );

    }
    /**
     * 查询 服务商人员列表 （不分页查询）
     *
     * @author lifq
     *
     * 2017年7月21日  下午4:27:11
     */
    public List<Map<String,Object>> findSpEmpList(Map<String,Object> paraMap){
    	return  (List<Map<String,Object>>) queryForList("CM_EMPLOYEE.findSpEmpList", paraMap);

    }

    public List<CmEmployee> queryCorpAllEmps(ContextInfo cti){
        Map<String, Object> map = new HashMap<>();
        map.put("cpId", cti.getOrgId());
        map.put("authority", cti.getAuthority());
        return  queryForList("CM_EMPLOYEE.queryCorpAllEmps", map);
    }

    /**
     * 获取人员相关的 服务商列表名称
     */
    public List<Map<String, Object>> findSpServiceByEmpCpId(Map<String,Object> paraMap){
        return  (List<Map<String,Object>>) queryForList("CM_EMPLOYEE.findSpServiceByEmpCpId", paraMap);
    }

    /**
     * 参保人员趋势图
     * @param cti
     * @param areaId
     * @param months
     * @return
     */
    public List<Map<String,Object>> queryEmpTrend(ContextInfo cti,Integer areaId,List<String> months){
        Map<String,Object> obj = new HashMap<String,Object>();
        obj.put("cpId",cti.getOrgId());
        obj.put("areaId",areaId);
        obj.put("months",months);
        obj.put("authority", cti.getAuthority());
        return queryForList("CM_EMPLOYEE.QUERY_EMP_TREND",obj);
    }

    /**
     * 根据企业微信ID和企业ID获取人员信息
     *  @param query
     *  @return 
     *	@return 			: CmEmployee 
     *  @createDate  	: 2017年10月10日 下午3:31:02
     *  @author         	: wangchao 
     *  @version        	: v1.0
     *  @updateDate    	: 2017年10月10日 下午3:31:02
     *  @updateAuthor  :
     */
	public CmEmployee finByWechatId(CmEmployee query) {
		Object ret = queryForObject("CM_EMPLOYEE.findByWechatId", query);
        if (ret != null)
            return (CmEmployee) ret;
        else
            return null;
	}

    /**
     * 获取可以同步的员工信息
     * @param cpId
     * @param synEmpIds
     * @return
     */
    public List<CmEmployee> queryUnRelationEmps(Integer cpId,String[] synEmpIds){
        Map<String, Object> map = new HashMap<>();
        map.put("cpId", cpId);
        map.put("synEmpIds", synEmpIds);
        return  queryForList("CM_EMPLOYEE.queryUnRelationEmps", map);
    }
}
