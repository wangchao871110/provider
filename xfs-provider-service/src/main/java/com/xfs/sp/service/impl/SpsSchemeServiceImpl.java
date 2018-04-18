package com.xfs.sp.service.impl;

import java.util.*;

import com.xfs.aop.task.cs.TaskBotApi;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.corp.model.CmCorp;
import com.xfs.corp.model.CmEmployee;
import com.xfs.corp.service.CmCorpService;
import com.xfs.corp.service.CmEmployeeService;
import com.xfs.sp.dao.SpCmRelationDao;
import com.xfs.sp.dao.SpsSchemeDao;
import com.xfs.sp.dao.SpsSchemeEmpDao;
import com.xfs.sp.dao.SpsSchemeItemDao;
import com.xfs.sp.model.SpCmRelation;
import com.xfs.sp.model.SpsAccount;
import com.xfs.sp.model.SpsScheme;
import com.xfs.sp.model.SpsSchemeEmp;
import com.xfs.sp.model.SpsSchemeInsurance;
import com.xfs.sp.model.SpsSchemeItem;
import com.xfs.sp.service.SpsAccountService;
import com.xfs.sp.service.SpsSchemeService;

/**
 * 方案服务类
 *
 * @author : yangfw@xinfushe.com
 * @version : V1.0
 * @date : 2016-11-11 11:09
 */
@Service
public class SpsSchemeServiceImpl implements SpsSchemeService {

    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(SpsSchemeServiceImpl.class);

    @Autowired
    private SpsSchemeDao spsSchemeDao;
    @Autowired
    private SpsSchemeItemDao spsSchemeItemDao;
    @Autowired
    private SpsSchemeEmpDao spsSchemeEmpDao;
    @Autowired
    private SpCmRelationDao spCmRelationDao;
    @Autowired
    private CmEmployeeService cmEmployeeService;
    @Autowired
    private SpsAccountService spsAccountService;
    @Autowired
    private CmCorpService cmCorpService;

    public int save(ContextInfo cti, SpsScheme vo) {
        return spsSchemeDao.save(cti, vo);
    }

    public int insert(ContextInfo cti, SpsScheme vo) {
        return spsSchemeDao.insert(cti, vo);
    }

    public int remove(ContextInfo cti, SpsScheme vo) {
        return spsSchemeDao.remove(cti, vo);
    }

    public int update(ContextInfo cti, SpsScheme vo) {
        return spsSchemeDao.update(cti, vo);
    }

    public PageModel findPage(PageInfo pi, SpsScheme vo) {

        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = spsSchemeDao.countFreeFind(vo);
        pm.setTotal(total);
        List<SpsScheme> datas = spsSchemeDao.freeFind(vo, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;

    }

    public List<SpsScheme> findAll(SpsScheme vo) {

        List<SpsScheme> datas = spsSchemeDao.freeFind(vo);
        return datas;

    }

    // 温馨提示：
    // 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    // 2016/08/03 16:26:35

    /**
     * 根据主键查询 实体
     *
     * @author lifq
     * <p>
     * 2016年8月4日 上午9:58:03
     */
    public SpsScheme findByPK(SpsScheme vo) {
        return spsSchemeDao.findByPK(vo);
    }

    /**
     * 查询 方案
     *
     * @author lifq
     * <p>
     * 2016年8月4日 上午11:42:41
     */
    public List<Map<String, Object>> findSchemes(Integer sp_id, Integer cp_id) {
        List<Map<String, Object>> datas = spsSchemeDao.findSchemes(sp_id, cp_id);
        return datas;
    }

    /**
     * 查询 方案
     *
     * @author lifq
     * <p>
     * 2016年8月4日 上午11:42:41
     */
    public List<Map<String, Object>> findSchemesState(Integer sp_id, Integer cp_id) {
        List<Map<String, Object>> datas = spsSchemeDao.findSchemesState(sp_id, cp_id);
        return datas;
    }

    /**
     * 保存方案
     *
     * @author lifq
     * <p>
     * 2016年8月4日 下午8:28:44
     */
    public Result saveScheme(ContextInfo cti, SpsScheme scheme, Integer type, Integer spId, String[] itemIdArr, String[] feeTypeArr, String[] itemPriceArr, String selectedInsuranceId) {
        Result result = Result.createResult().setSuccess(false);
        if (type == 1) {// 保存方案
            int res = 0;
            if (null == scheme.getSchemeId()) {// 新建
                scheme.setSchemeType("DIY");// 自己处理
                scheme.setIspackage("N");// 未分包
                scheme.setState("USE");
                scheme.setSpId(spId);
                scheme.setDr(0);
                res = spsSchemeDao.insert(cti, scheme);
            } else {// 更新方案
                res = spsSchemeDao.update(cti, scheme);
            }
            if (res > 0) {
                SpsSchemeItem schemeItem = new SpsSchemeItem();
                schemeItem.setSchemeId(scheme.getSchemeId());
                // 删除原有项目
                List<SpsSchemeItem> itemList = spsSchemeItemDao.freeFind(schemeItem);
                if (null != itemList && itemList.size() > 0) {
                    for (int i = 0; i < itemList.size(); i++) {
                        SpsSchemeItem item = itemList.get(i);
                        spsSchemeItemDao.remove(cti, item);
                    }
                }
                // 删除新增项目
                if (null != itemIdArr && itemIdArr.length > 0) {

                    for (int i = 0; i < itemIdArr.length; i++) {
                        SpsSchemeItem newItem = new SpsSchemeItem();
                        newItem.setSchemeId(scheme.getSchemeId());
                        newItem.setItemId(Integer.valueOf(itemIdArr[i]));
                        newItem.setFeetype(feeTypeArr[i]);
                        newItem.setPrice(com.xfs.common.util.StringUtils.toBigDecimal(itemPriceArr[i]));
                        newItem.setDr(0);
                        spsSchemeItemDao.insert(cti, newItem);
                    }
                }
                result.setSuccess(true);
                result.setDataValue("schemeId", scheme.getSchemeId());
                return result;
            }

        } else if (type == 2) {
            // 保存方案
            spsSchemeDao.update(cti, scheme);
            if (StringUtils.isNotBlank(selectedInsuranceId)) {
                Map<String, String> selectedMap = new HashMap<String, String>();
                String[] arr = selectedInsuranceId.split(",");
                if (null != arr && arr.length > 0) {
                    for (int i = 0; i < arr.length; i++) {
                        if (StringUtils.isNotBlank(arr[i])) {
                            selectedMap.put(arr[i], arr[i]);
                        }
                    }
                }
                // 删除原有险种
                // SpsSchemeInsurance schemeInsurance = new
                // SpsSchemeInsurance();
                // schemeInsurance.setSchemeId(scheme.getSchemeId());
                // List<SpsSchemeInsurance> insuranceList =
                // spsSchemeInsuranceDao.freeFind(schemeInsurance);
                // if(null!=insuranceList && insuranceList.size()>0){
                // for(int i=0;i<insuranceList.size();i++){
                // spsSchemeInsuranceDao.remove(insuranceList.get(i));
                // }
                // }
                // 添加 新险种
                // String []insuranceIdArr =
                // request.getParameterValues("insuranceId");
                // String []corpRatioArr =
                // request.getParameterValues("corpRatio");
                // String []empRatioArr =
                // request.getParameterValues("empRatio");
                // if(null!=insuranceIdArr && insuranceIdArr.length>0){
                // for(int i=0;i<insuranceIdArr.length;i++){
                // String insuranceId = insuranceIdArr[i];
                // if(selectedMap.containsKey(insuranceId)){//保存选中的险种
                // SpsSchemeInsurance newSchemeInsurance = new
                // SpsSchemeInsurance();
                // newSchemeInsurance.setSchemeId(scheme.getSchemeId());
                // newSchemeInsurance.setInsuranceId(Integer.valueOf(insuranceId));
                //
                // if(StringUtils.isNotBlank(corpRatioArr[i])){
                // newSchemeInsurance.setCorpRatio(com.xfs.common.util.StringUtils.toBigDecimal(corpRatioArr[i]).divide(new
                // BigDecimal(100)));
                // }
                // if(StringUtils.isNotBlank(empRatioArr[i])){
                // newSchemeInsurance.setEmpRatio(com.xfs.common.util.StringUtils.toBigDecimal(empRatioArr[i]).divide(new
                // BigDecimal(100)));
                // }
                // spsSchemeInsuranceDao.insert(cti,newSchemeInsurance);
                // }
                // }
                // }
            }
            //// isCoveredAll =1 :调用 鹏哥算费，覆盖 所以员工信息
            // if("1".equals(request.getParameter("isCoveredAll"))){
            // List<CmEmployee> list =
            //// cmEmployeeService.findEmpEntityBySchemeId(scheme.getSchemeId());
            // if(null!=list &&list.size()>0){
            // List<CmEmployee> todoList =
            //// cmEmployeeInsuranceService.getEmpInsuranceFromScheme(spId,
            //// list, null, DateUtil.getCurYearMonthStr());
            // // 当前年月beginPeriod
            // cmEmployeeInsuranceService.updateEmployeeInsuranceByCurStatus(spId,
            //// todoList, null, DateUtil.getCurYearMonthStr());
            // }
            // }
            result.setSuccess(true);
            return result;
        }
        // else if(type == 3){
        // String selectedEmpId = request.getParameter("selEmpId");
        // if(StringUtils.isNotBlank(selectedEmpId)){
        // //删除原有员工 信息
        // SpsSchemeEmp schemeEmp = new SpsSchemeEmp();
        // schemeEmp.setSchemeId(scheme.getSchemeId());
        // List<SpsSchemeEmp> empList = spsSchemeEmpDao.freeFind(schemeEmp);
        // if(null!=empList && empList.size()>0){
        // for(int i=0;i<empList.size();i++){
        // spsSchemeEmpDao.remove(empList.get(i));
        // }
        // }
        // //添加新员工
        // String []arr = selectedEmpId.split(",");
        // if(null!=arr && arr.length>0){
        // for(int i=0;i<arr.length;i++){
        // if(StringUtils.isNotBlank(arr[i])){
        // SpsSchemeEmp schemeEmpVo = new SpsSchemeEmp();
        // schemeEmpVo.setSchemeId(scheme.getSchemeId());
        // schemeEmpVo.setEmpId(Integer.valueOf(arr[i]));
        // schemeEmpVo.setState("USE");
        // schemeEmpVo.setDr(0);
        // spsSchemeEmpDao.insert(cti,schemeEmpVo);
        // }
        // }
        // }
        // }
        // result.setSuccess(true);
        // return result;
        // }
        return result;
    }

    /**
     * 复制方案
     *
     * @author lifq
     * <p>
     * 2016年8月5日 上午9:47:15
     */
    public Result copyScheme(ContextInfo cti, SpsScheme scheme) {
        Result result = Result.createResult().setSuccess(false);
        // 复制方案
        SpsScheme oldScheme = spsSchemeDao.findByPK(scheme);

        // 查询该服务商、企业下 所以方案信息
        Map<String, String> existSchemeNameMap = new HashMap<String, String>();
        List<Map<String, Object>> schemeList = spsSchemeDao.findSchemes(oldScheme.getSpId(), oldScheme.getCpId());
        if (null != schemeList && schemeList.size() > 0) {
            for (Map<String, Object> curVo : schemeList) {
                existSchemeNameMap.put(null == curVo.get("name") ? "" : curVo.get("name").toString(), "");
            }
        }

        Integer oldSchemeId = oldScheme.getSchemeId();
        oldScheme.setSchemeId(null);
        oldScheme.setIsdefault("N");
        oldScheme.setState("USE");// 状态不能复制
        oldScheme.setIspackage("N");
        oldScheme.setSchemeType("DIY");// 类型 不能复制
        String newName = "";
        for (int i = 1; i < 100; i++) {
            newName = oldScheme.getName() + "（" + i + "）";
            if (!existSchemeNameMap.containsKey(newName)) {
                break;
            }
        }
        oldScheme.setName(newName);
        int res = spsSchemeDao.insert(cti, oldScheme);
        Integer newSchemeId = oldScheme.getSchemeId();

        if (res > 0) {
            // 复制 方案项目
            SpsSchemeItem schemeItem = new SpsSchemeItem();
            schemeItem.setSchemeId(oldSchemeId);
            List<SpsSchemeItem> itemList = spsSchemeItemDao.freeFind(schemeItem);
            if (null != itemList && itemList.size() > 0) {
                for (int i = 0; i < itemList.size(); i++) {
                    SpsSchemeItem item = itemList.get(i);
                    item.setId(null);
                    item.setSchemeId(newSchemeId);
                    spsSchemeItemDao.insert(cti, item);
                }
            }
            // 复制 方案险种
            SpsSchemeInsurance schemeInsurace = new SpsSchemeInsurance();
            schemeInsurace.setSchemeId(oldSchemeId);
            // List<SpsSchemeInsurance> insuranceList =
            // spsSchemeInsuranceDao.freeFind(schemeInsurace);
            // if(null!= insuranceList && insuranceList.size()>0){
            // for(int i=0;i<insuranceList.size();i++){
            // SpsSchemeInsurance insurance = insuranceList.get(i);
            // insurance.setId(null);
            // insurance.setSchemeId(newSchemeId);
            // spsSchemeInsuranceDao.insert(cti,insurance);
            // }
            // }
            result.setSuccess(true);
        }
        return result;
    }

    /**
     * 停用方案
     *
     * @author lifq
     * <p>
     * 2016年8月5日 上午9:47:15
     */
    public Result stopScheme(ContextInfo cti, SpsScheme scheme) {
        Result result = Result.createResult().setSuccess(false);
        // 查询 方案下 是否有员工
        SpsSchemeEmp schemeEmp = new SpsSchemeEmp();
        schemeEmp.setSchemeId(scheme.getSchemeId());
        schemeEmp.setState("USE");
        schemeEmp.setDr(0);
        List<SpsSchemeEmp> empList = spsSchemeEmpDao.freeFind(schemeEmp);
        if (null != empList && empList.size() > 0) {
            result.setError("该方案下还有员工，不能停用！");
            return result;
        }
        SpsScheme curScheme = spsSchemeDao.findByPK(scheme);
        curScheme.setState("STOP");
        // curScheme.setExpireDate(DateUtil.getCurYearMonthStr());//停用时候 更新截止日期
        // 停用方案
        int res = spsSchemeDao.update(cti, curScheme);
        if (res > 0) {
            // SpsSchemeEmp schemeEmp = new SpsSchemeEmp();
            // schemeEmp.setSchemeId(scheme.getSchemeId());
            // List<SpsSchemeEmp> empList = spsSchemeEmpDao.freeFind(schemeEmp);
            // if(null!=empList && empList.size()>0){
            // for(int i=0;i<empList.size();i++){
            // SpsSchemeEmp emp = empList.get(i);
            // emp.setState("STOP");
            // spsSchemeEmpDao.update(emp);
            // }
            // }
            result.setSuccess(true);
        }
        return result;
    }

    /**
     * 保存 分包方案
     *
     * @author lifq
     * <p>
     * 2016年8月10日 下午8:32:01
     */
    public Result saveDeputeScheme(ContextInfo cti, SpsScheme scheme, Integer type, Integer parentSpId, Integer sysSpId, String[] itemIdArr, String[] feeTypeArr, String[] itemPriceArr, String selectedInsuranceId, String selectedEmpId) {
        Result result = Result.createResult().setSuccess(false);
        SpsScheme originScheme = null;
        if (type == 1) {// 保存方案
            int res = 0;
            if (null == scheme.getSchemeId()) {// 新增
                scheme.setState("USE");
                scheme.setParentSpId(sysSpId);// sysSpId 当前用户spId
                scheme.setDr(0);
                scheme.setSpId(parentSpId);
                // 查询 原来方案信息
                SpsScheme vo = new SpsScheme();
                vo.setSchemeId(scheme.getParentId());
                originScheme = spsSchemeDao.findByPK(vo);
                scheme.setSchemeType("ENTRUSTED");// 受托：ENTRUSTED
                scheme.setCpId(originScheme.getCpId());
                scheme.setIspackage("N");// 未分包
                // 公积金、社保库 类型带过去
                scheme.setInsuranceType(originScheme.getInsuranceType());
                scheme.setFundType(originScheme.getFundType());
                // 新增 分包方案
                res = spsSchemeDao.save(cti, scheme);

                // 更新 原来方案信息
                originScheme.setSchemeType("DEPUTE");// 委托：DEPUTE
                originScheme.setIspackage("Y");// 已分包
                originScheme.setParentSpId(scheme.getSpId());// 20160829与孔令潮确认，分包时候也更新
                // 原方案的ParentSpId和ParentId
                originScheme.setParentId(scheme.getSchemeId());
                spsSchemeDao.update(cti, originScheme);

                // 拷贝原方案的 企业和员工
                SpCmRelation cmRelation = new SpCmRelation();
                cmRelation.setCpId(scheme.getCpId());
                cmRelation.setSpId(scheme.getSpId());
                List<SpCmRelation> relList = spCmRelationDao.freeFind(cmRelation);
                if (null != relList && relList.size() > 0) {
                    // cmRelation = relList.get(0);
                } else {
                    cmRelation.setSource("ENTRUSTED");
                    spCmRelationDao.save(cti, cmRelation);
                }

                SpsSchemeEmp schemeEmp = new SpsSchemeEmp();
                schemeEmp.setSchemeId(originScheme.getSchemeId());
                List<SpsSchemeEmp> empList = spsSchemeEmpDao.freeFind(schemeEmp);
                if (null != empList && empList.size() > 0) {
                    for (int i = 0; i < empList.size(); i++) {
                        SpsSchemeEmp schemeEmpVo = empList.get(i);
                        schemeEmpVo.setId(null);// 新增关联
                        schemeEmpVo.setSchemeId(scheme.getSchemeId());
                        schemeEmpVo.setEmpId(schemeEmpVo.getEmpId());
                        schemeEmpVo.setState("USE");
                        schemeEmpVo.setDr(0);
                        spsSchemeEmpDao.insert(cti, schemeEmpVo);
                    }
                }
                // 原有险种
                // SpsSchemeInsurance schemeInsurance = new
                // SpsSchemeInsurance();
                // schemeInsurance.setSchemeId(originScheme.getSchemeId());
                // List<SpsSchemeInsurance> insuranceList =
                // spsSchemeInsuranceDao.freeFind(schemeInsurance);
                // if(null!=insuranceList && insuranceList.size()>0){
                // for(int i=0;i<insuranceList.size();i++){
                // SpsSchemeInsurance insuranceVo = insuranceList.get(i);
                // insuranceVo.setId(null);
                // insuranceVo.setSchemeId(scheme.getSchemeId());
                // spsSchemeInsuranceDao.insert(cti,insuranceVo);
                // }
                // }

            } else {// 更新
                SpsScheme vo = new SpsScheme();
                vo.setSchemeId(scheme.getSchemeId());
                vo = spsSchemeDao.findByPK(vo);
                SpsScheme oriVo = new SpsScheme();
                oriVo.setSchemeId(vo.getParentId());
                originScheme = spsSchemeDao.findByPK(oriVo);
                res = spsSchemeDao.save(cti, scheme);
            }

            if (res > 0) {
                SpsSchemeItem schemeItem = new SpsSchemeItem();
                schemeItem.setSchemeId(scheme.getSchemeId());
                // 删除原有项目
                List<SpsSchemeItem> itemList = spsSchemeItemDao.freeFind(schemeItem);
                if (null != itemList && itemList.size() > 0) {
                    for (int i = 0; i < itemList.size(); i++) {
                        SpsSchemeItem item = itemList.get(i);
                        spsSchemeItemDao.remove(cti, item);
                    }
                }
                // 删除新增项目
                if (null != itemIdArr && itemIdArr.length > 0) {
                    for (int i = 0; i < itemIdArr.length; i++) {
                        SpsSchemeItem newItem = new SpsSchemeItem();
                        newItem.setSchemeId(scheme.getSchemeId());
                        newItem.setItemId(Integer.valueOf(itemIdArr[i]));
                        newItem.setFeetype(feeTypeArr[i]);
                        newItem.setPrice(com.xfs.common.util.StringUtils.toBigDecimal(itemPriceArr[i]));
                        newItem.setDr(0);
                        spsSchemeItemDao.insert(cti, newItem);
                    }
                }

                result.setSuccess(true);
                result.setDataValue("schemeId", scheme.getSchemeId());
                return result;
            }

        } else if (type == 2) {
            // 更新方案
            spsSchemeDao.update(cti, scheme);
            if (StringUtils.isNotBlank(selectedInsuranceId)) {
                Map<String, String> selectedMap = new HashMap<String, String>();
                String[] arr = selectedInsuranceId.split(",");
                if (null != arr && arr.length > 0) {
                    for (int i = 0; i < arr.length; i++) {
                        if (StringUtils.isNotBlank(arr[i])) {
                            selectedMap.put(arr[i], arr[i]);
                        }
                    }
                }
                // 删除原有险种
                // SpsSchemeInsurance schemeInsurance = new
                // SpsSchemeInsurance();
                // schemeInsurance.setSchemeId(scheme.getSchemeId());
                // List<SpsSchemeInsurance> insuranceList =
                // spsSchemeInsuranceDao.freeFind(schemeInsurance);
                // if(null!=insuranceList && insuranceList.size()>0){
                // for(int i=0;i<insuranceList.size();i++){
                // spsSchemeInsuranceDao.remove(insuranceList.get(i));
                // }
                // }
                // 添加 新险种
                // String []insuranceIdArr =
                // request.getParameterValues("insuranceId");
                // String []corpRatioArr =
                // request.getParameterValues("corpRatio");
                // String []empRatioArr =
                // request.getParameterValues("empRatio");
                // if(null!=insuranceIdArr && insuranceIdArr.length>0){
                // for(int i=0;i<insuranceIdArr.length;i++){
                // String insuranceId = insuranceIdArr[i];
                // if(selectedMap.containsKey(insuranceId)){//保存选中的险种
                // SpsSchemeInsurance newSchemeInsurance = new
                // SpsSchemeInsurance();
                // newSchemeInsurance.setSchemeId(scheme.getSchemeId());
                // newSchemeInsurance.setInsuranceId(Integer.valueOf(insuranceId));
                // if(StringUtils.isNotBlank(corpRatioArr[i])){
                // newSchemeInsurance.setCorpRatio(com.xfs.common.util.StringUtils.toBigDecimal(corpRatioArr[i]).divide(new
                // BigDecimal(100)));
                // }
                // if(StringUtils.isNotBlank(empRatioArr[i])){
                // newSchemeInsurance.setEmpRatio(com.xfs.common.util.StringUtils.toBigDecimal(empRatioArr[i]).divide(new
                // BigDecimal(100)));
                // }
                // spsSchemeInsuranceDao.insert(cti,newSchemeInsurance);
                // }
                // }
                // }
            }
            result.setSuccess(true);
            return result;
        } else if (type == 3) {
            if (StringUtils.isNotBlank(selectedEmpId)) {
                // 删除原有员工 信息
                SpsSchemeEmp schemeEmp = new SpsSchemeEmp();
                schemeEmp.setSchemeId(scheme.getSchemeId());
                List<SpsSchemeEmp> empList = spsSchemeEmpDao.freeFind(schemeEmp);
                if (null != empList && empList.size() > 0) {
                    for (int i = 0; i < empList.size(); i++) {
                        spsSchemeEmpDao.remove(cti, empList.get(i));
                    }
                }
                // 添加新员工
                String[] arr = selectedEmpId.split(",");
                if (null != arr && arr.length > 0) {
                    for (int i = 0; i < arr.length; i++) {
                        if (StringUtils.isNotBlank(arr[i])) {
                            SpsSchemeEmp schemeEmpVo = new SpsSchemeEmp();
                            schemeEmpVo.setSchemeId(scheme.getSchemeId());
                            schemeEmpVo.setEmpId(Integer.valueOf(arr[i]));
                            schemeEmpVo.setState("USE");
                            schemeEmpVo.setDr(0);
                            spsSchemeEmpDao.insert(cti, schemeEmpVo);
                        }
                    }
                }
            }
            result.setSuccess(true);
            return result;
        }
        return result;
    }

    /**
     * 查看 分包方案
     *
     * @author lifq
     * <p>
     * 2016年8月10日 下午9:26:12
     */
    public String viewPackageScheme(Integer schemeId, Integer spId) {
        SpsScheme vo = new SpsScheme();
        vo.setParentId(schemeId);
        vo.setParentSpId(spId);
        List<SpsScheme> list = spsSchemeDao.freeFind(vo);
        if (null != list && list.size() > 0) {
            return list.get(0).getSchemeId() + "";
        }
        return "";
    }

    @Override
    public List<SpsScheme> findSchemeByIds(Integer sp_id, List<Integer> ids) {
        return spsSchemeDao.findSchemesByIds(sp_id, ids);
    }

    /**
     * 转方案
     *
     * @author lifq
     * <p>
     * 2016年8月11日 下午9:18:08
     */
    public Result transferScheme(ContextInfo cti, Integer fromSchemeId, Integer toSchemeId, Integer spId, String selEmpId) {
        Result result = Result.createResult().setSuccess(false);
        //不换方案，忽略
        if (toSchemeId.equals(fromSchemeId)) {
            result.setSuccess(true);
            return result;
        }
        // 1.校验 两个方案 库是否相同
        SpsScheme fromVo = new SpsScheme();
        fromVo.setSchemeId(fromSchemeId);
        SpsScheme fromScheme = spsSchemeDao.findByPK(fromVo);

        SpsScheme toVo = new SpsScheme();
        toVo.setSchemeId(toSchemeId);
        SpsScheme toScheme = spsSchemeDao.findByPK(toVo);

        List<Integer> empids = new ArrayList<>();
        // 处理 选择的员工
        Map<Integer, Integer> dealEmpMap = new HashMap<Integer, Integer>();
        if (StringUtils.isNotBlank(selEmpId)) {
            String[] empArr = selEmpId.split(",");
            if (null != empArr && empArr.length > 0) {
                for (int i = 0; i < empArr.length; i++) {
                    if (StringUtils.isNotBlank(empArr[i])) {
                        empids.add(Integer.parseInt(empArr[i]));
                        dealEmpMap.put(Integer.valueOf(empArr[i]), 0);
                    }
                }
            }
        }
        //TODO 换方案需要判断员工是否在缴
        List<CmEmployee> emps = cmEmployeeService.findEmpListWithDetailByEmpids(empids);
        if ((null != fromScheme.getInsuranceAccountId()
                && !fromScheme.getInsuranceAccountId().equals(toScheme.getInsuranceAccountId()))
                || (null != fromScheme.getFundAccountId()
                && !fromScheme.getFundAccountId().equals(toScheme.getFundAccountId()))) {
            for (CmEmployee emp : emps) {
                if ("ON".equals(emp.getInsuranceState()) ||
                        "DECREASING".equals(emp.getInsuranceState()) ||
                        "INCREASING".equals(emp.getInsuranceState()) ||
                        "ON".equals(emp.getFundState()) ||
                        "DECREASING".equals(emp.getFundState()) ||
                        "INCREASING".equals(emp.getFundState())) {
                    result.setError("原方案与待转入方案不属于同一个大户（单立户），需要先减员再增员到此方案！");
                    return result;
                }
            }
        }

        SpsSchemeEmp empVo = new SpsSchemeEmp();
        empVo.setSchemeId(fromSchemeId);
        empVo.setState("USE");
        empVo.setDr(0);
        List<Integer> empIds = new ArrayList<Integer>();
        List<SpsSchemeEmp> empList = spsSchemeEmpDao.freeFind(empVo);
        if (null != empList && empList.size() > 0) {
            for (int i = 0; i < empList.size(); i++) {
                SpsSchemeEmp vo = empList.get(i);
                if (dealEmpMap.containsKey(vo.getEmpId())) {// 选中的员工
                    // vo.setState("STOP");
                    spsSchemeEmpDao.remove(cti, vo);// 删除原有
                    vo.setSchemeId(toSchemeId);
                    vo.setId(null);
                    vo.setState("USE");
                    spsSchemeEmpDao.insert(cti, vo);// 新增到新的方案
                    empIds.add(vo.getEmpId());
                }
            }
        }

        // 处理 分包方案中 员工信息
        if (null != fromScheme.getParentId() && null != toScheme.getParentId()) {
            SpsSchemeEmp partnerEmpVo = new SpsSchemeEmp();
            partnerEmpVo.setSchemeId(fromScheme.getParentId());
            partnerEmpVo.setState("USE");
            partnerEmpVo.setDr(0);
            List<SpsSchemeEmp> partnerEmpList = spsSchemeEmpDao.freeFind(partnerEmpVo);
            if (null != partnerEmpList && partnerEmpList.size() > 0) {
                for (int j = 0; j < partnerEmpList.size(); j++) {
                    SpsSchemeEmp vo = partnerEmpList.get(j);
                    if (dealEmpMap.containsKey(vo.getEmpId())) {// 选中的员工
                        spsSchemeEmpDao.remove(cti, vo);// 删除原有
                        vo.setSchemeId(toScheme.getParentId());
                        vo.setId(null);
                        vo.setState("USE");
                        spsSchemeEmpDao.insert(cti, vo);// 新增到新的方案
                    }
                }
            }
        }
        result.setSuccess(true);
        return result;
    }

    @Override
    public SpsScheme findByParentSchemeId(Integer spId, Integer schemeId) {
        SpsScheme vo = new SpsScheme();
        vo.setParentId(schemeId);
        vo.setParentSpId(spId);
        List<SpsScheme> list = spsSchemeDao.freeFind(vo);
        if (null != list && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 启用方案
     *
     * @author lifq
     * <p>
     * 2016年8月24日 上午9:30:49
     */
    public Result useScheme(ContextInfo cti, SpsScheme scheme) {
        Result result = Result.createResult().setSuccess(false);
        SpsScheme curScheme = spsSchemeDao.findByPK(scheme);
        curScheme.setState("USE");
        // curScheme.setExpireDate("");//启用时候 更新截止日期
        // 停用方案
        int res = spsSchemeDao.update(cti, curScheme);
        if (res > 0) {
            result.setSuccess(true);
        } else {
            result.setError("方案启用失败！");
        }
        return result;
    }

    /**
     * 根据spId查询所有方案所属城市
     *
     * @author wuzhe
     */
    public List<Map<String, Object>> findAllSchemeArea(Map<String, Object> paraMap) {
        return spsSchemeDao.findAllSchemeArea(paraMap);
    }

    /**
     * 根据查询条件获取企业账单列表
     *
     * @param cpId         企业id
     * @param schemeTypeIn 方案类型
     * @param state        方案状态
     * @return : SpsScheme
     * @createDate : 2016-11-11 11:41
     * @author : yfw@xinfushe.com
     * @version : v1.0
     * @updateDate : 2016-11-11 11:41
     * @updateAuthor :
     */
    @Override
    public List<SpsScheme> freeFindInType(Integer cpId, String[] schemeTypeIn, String state) {
        Map<String, Object> param = new HashMap<>();
        param.put("cpId", cpId);
        param.put("schemeTypeIn", schemeTypeIn);
        param.put("stateEq", state);
        return spsSchemeDao.freeFindInType(param);
    }

    /**
     * 获取所有企业账单日
     *
     * @param
     * @return : java.util.List<com.xfs.sp.model.SpsScheme>
     * @createDate : 2016-11-23 11:15
     * @author : konglc@xinfushe.com
     * @version : v1.0
     * @updateDate : 2016-11-23 11:15
     * @updateAuthor :
     */
    public List<SpsScheme> queryAllCorpsByBillDay(String billState) {
        return spsSchemeDao.queryAllCorpsByBillDay(billState);
    }

    public List<SpsScheme> findInsurance(SpsScheme vo) {

        List<SpsScheme> datas = spsSchemeDao.freeFindInsurance(vo);
        return datas;

    }

    /**
     * 根据条件查询方案(查询出id最小的方案)
     *
     * @param spsScheme
     * @return
     */
    public List<SpsScheme> findSchemeListByConditions(SpsScheme spsScheme) {
        return spsSchemeDao.findSchemeListByConditions(spsScheme);
    }

    /**
     * 根据企业ID和城市获取最小方案
     *
     * @param scheme
     * @return
     * @createDate : 2017年3月17日 下午1:39:57
     * @author : wangchao
     * @version : v1.0
     * @updateDate : 2017年3月17日 下午1:39:57
     * @updateAuthor :
     */
    @Override
    public SpsScheme findMinSchemeByCityIdAndCpId(ContextInfo cti,SpsScheme scheme) {
        return spsSchemeDao.findMinSchemeByCityIdAndCpId(scheme);
    }


    /**
     * 根据员工获取员工方案
     *
     * @param empId
     * @return
     */
    @Override
    public SpsScheme findSchemeByEmpId(Integer empId) {
        return spsSchemeDao.findSchemeByEmpId(empId);
    }

    /**
     * 获取当前企业下的方案列表
     *
     * @param cti
     * @return : java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @createDate : 2017-03-23 14:33
     * @author : konglc@xinfushe.com
     * @version : v1.0
     * @updateDate : 2017-03-23 14:33
     * @updateAuthor :
     */
    @Override
    public List<SpsScheme> findSchemeByCpId(ContextInfo cti) {
        return spsSchemeDao.findSchemeByCpId(cti);
    }

    /**
     * 获取当前企业下是否支持网申帐号
     *
     * @return : java.util.Map<java.lang.String,java.util.Map<java.lang.String,java.lang.Object>>
     * @createDate : 2017-04-05 18:35
     * @author : konglc@xinfushe.com
     * @version : v1.0
     * @updateDate : 2017-04-05 18:35
     * @updateAuthor :
     */
    public Map<String, Map<String, Object>> querySchemeAccountAreaType(ContextInfo cti) {
        List<Map<String, Object>> schemeAreaList = spsSchemeDao.querySchemeAccountAreaList(cti);
        Map<String, Map<String, Object>> schemeAreaMap = new HashMap<>();
        if (null != schemeAreaList && !schemeAreaList.isEmpty()) {
            for (Map<String, Object> schemeArea : schemeAreaList) {
                String key = String.valueOf(schemeArea.get("scheme_id")) + "_" + String.valueOf(schemeArea.get("area_id"));
                if (null == schemeAreaMap.get(key))
                    schemeAreaMap.put(key, schemeArea);
            }
        }
        return schemeAreaMap;
    }

    /**
     * 删除账号
     *  @param cti
     *  @param schemeId 
     *  @createDate  	: 2017年4月19日 下午8:29:56
     *  @author         	: wangchao
     *  @version        	: v1.0
     *  @updateDate    	: 2017年4月19日 下午8:29:56
     *  @updateAuthor  	:
     */
	@Override
	public boolean removeAcc(ContextInfo cti, Integer schemeId) {
		// 方案下是否存在用户
		List<Map<String, Object>> emList = cmEmployeeService.findEmpBySchemeId(schemeId, cti.getOrgId());
		if(null != emList && emList.size() > 0){
			return false;
		}
		SpsScheme spsScheme = new SpsScheme();
		spsScheme.setSchemeId(schemeId);
		spsScheme = spsSchemeDao.findByPK(spsScheme);
		// 删除社保
		SpsAccount spsAccountIns = new SpsAccount();
		spsAccountIns.setAccId(spsScheme.getInsuranceAccountId());
		spsAccountIns.setDr(1);
		spsAccountService.update(cti, spsAccountIns);
		// 删除公积金
		SpsAccount spsAccountFund = new SpsAccount();
		spsAccountFund.setAccId(spsScheme.getFundAccountId());
		spsAccountFund.setDr(1);
		spsAccountService.update(cti, spsAccountFund);
		// 删除方案
		spsScheme.setDr(1);
		spsSchemeDao.update(cti, spsScheme);
		return true;
	}

	/**
	 * 根据企业和城市名称获取方案
	 *  @param scheme
	 *  @return 
	 *  @createDate  	: 2017年5月18日 下午3:43:20
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年5月18日 下午3:43:20
	 *  @updateAuthor  	:
	 */
	@Override
	public SpsScheme findMinSchemeByCpIdAndCityNmae(SpsScheme scheme) {
		return spsSchemeDao.findMinSchemeByCpIdAndCityNmae(scheme);
	}

	/**
	 * 根据权限、企业ID、服务商ID和方案ID获取参保城市
	 *  @param authority
	 *  @param cpId
	 *  @param spId
	 *  @param schemeIds
	 *  @return 
	 *  @createDate  	: 2017年7月14日 下午2:29:16
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年7月14日 下午2:29:16
	 *  @updateAuthor  	:
	 */
	@Override
	public List<Map<String, Object>> findServiceBillDetailsArea(String authority, Integer cpId, Integer spId,
			String[] schemeIds) {
		return spsSchemeDao.findServiceBillDetailsArea( authority, cpId, spId, schemeIds);
	}
	/**
	 *  根据 城市+供应商名称 查询方案信息
	 *
	 * @author lifq
	 *
	 * 2017年7月20日  上午9:43:16
	 */
	public List<Map<String,Object>> findSchemeByName(Map<String,Object> paraMap){
		return spsSchemeDao.findSchemeByName(paraMap);
	}

    /**
     * 根据 服务商获取相关 方案id
     * @param paraMap
     * @author quanjh
     * @return
     */
    public List<Map<String,Object>> findSchemeBySpIds(Map<String,Object> paraMap){
        return spsSchemeDao.findSchemeBySpIds(paraMap);
    }

    /**
     * 企业下所有外包方案
     * @param cti
     * @return
     */
    public Map<String,SpsScheme> findAllSpSchemeByCpId(ContextInfo cti){
        Map<String,SpsScheme> schemeMap = new HashMap<>();
        List<SpsScheme> schemes = spsSchemeDao.findAllSpSchemeByCpId(cti);
        if(null != schemes && !schemes.isEmpty()){
            for(SpsScheme scheme : schemes){
                schemeMap.put(String.valueOf(scheme.getSchemeId()),scheme);
            }
        }
        return schemeMap;
    }
    /**
	  * 查询 有权限的 方案（排除自服务方案）
	  *
	  * @author lifq
	  *
	  * 2017年8月8日  下午5:53:03
	  */
	 public List<Map<String,Object>> findSpSchemeByAuthority(Map<String,Object> paraMap){
		 return spsSchemeDao.findSpSchemeByAuthority(paraMap);
	 }

	 /**
	  * 根据企业ID和城市获取方案  按自服务倒序
	  *  @param spsScheme
	  *  @return 
	  *  @createDate  	: 2017年8月23日 下午4:39:20
	  *  @author         	: wangchao
	  *  @version        	: v1.0
	  *  @updateDate    	: 2017年8月23日 下午4:39:20
	  *  @updateAuthor  	:
	  */
	@Override
	public List<SpsScheme> findSchemeByCityIdAndCpIdOderBySpId(SpsScheme spsScheme) {
		return spsSchemeDao.findSchemeByCityIdAndCpIdOderBySpId(spsScheme);
	}

    /**
     * 获取当前企业下自服务所有方案列表
     * @param cti
     * @return
     */
    public Map<String,List<Map<String,Object>>> findSelfSchemeByCpId(ContextInfo cti){
        List<Map<String,Object>> selfSchemes = spsSchemeDao.findSelfSchemeByCpId(cti);
        Map<String,List<Map<String,Object>>> selfSchemeMap = new LinkedHashMap<>();
        if(null != selfSchemes && !selfSchemes.isEmpty()){
            for(Map<String,Object> ob : selfSchemes){
                if(null == selfSchemeMap.get(String.valueOf(ob.get("areaName"))))
                    selfSchemeMap.put(String.valueOf(ob.get("areaName")),new ArrayList<Map<String,Object>>());
                selfSchemeMap.get(String.valueOf(ob.get("areaName"))).add(ob);
            }
        }
        return selfSchemeMap;
    }
}
