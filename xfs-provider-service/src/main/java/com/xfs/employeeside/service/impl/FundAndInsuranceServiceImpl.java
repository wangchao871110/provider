package com.xfs.employeeside.service.impl;
/**
 * @author hongjie
 * @date 2017/3/15.12:04
 */

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.xfs.base.model.BsArea;
import com.xfs.base.service.BsAreaService;
import com.xfs.bill.dto.InsureEmpDetail;
import com.xfs.bill.service.SpsBillService;
import com.xfs.common.ContextInfo;
import com.xfs.common.Result;
import com.xfs.common.constant.IStaticVarConstant;
import com.xfs.common.util.DateUtil;
import com.xfs.corp.model.CmCorp;
import com.xfs.corp.model.CmEmployee;
import com.xfs.corp.service.CmCorpService;
import com.xfs.corp.service.CmEmployeeService;
import com.xfs.employeeside.dao.FundAndInsuranceDao;
import com.xfs.employeeside.model.FundAndInsurance;
import com.xfs.employeeside.service.FundAndInsuranceService;
import com.xfs.sp.model.SpsScheme;
import com.xfs.sp.service.SpsSchemeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 公积金服务接口实现
 *
 * @author
 * @create 2017-03-15 12:04
 **/
@Service
public class FundAndInsuranceServiceImpl implements FundAndInsuranceService,IStaticVarConstant {

    @Autowired
    private CmCorpService cmCorpService;

    @Autowired
    private FundAndInsuranceDao fundAndInsuranceDao;

    @Autowired
    private SpsBillService spsBillService;

    @Autowired
    private BsAreaService bsAreaService;


    @Autowired
    private CmEmployeeService cmEmployeeService;


    @Autowired
    private SpsSchemeService spsSchemeService;

    //    final String FUNC_URL = "http://wap.xinfushe.cn/h5/index.html?hideinsurance=1#/hf-search";
//    final String INSURANCE_URL = "http://wap.xinfushe.cn/h5/index.html?hideinsurance=1#/ins-search";
    String FUNC_URL = "http://wap.xinfushe.cn/h5/index.html?channel=XINFUSHEEMP&city={city}&key={key}#/hf-search";
    String INSURANCE_URL = "http://wap.xinfushe.cn/h5/index.html?channel=XINFUSHEEMP&city={city}&key={key}#/ins-search";


    // http://wap.xinfushe.cn/h5/index.html?channel=XINFUSHEEMP&city={city}&key={key}#/detail/

    //  http://wap.xinfushe.cn/h5/index.html?channel=XINFUSHEEMP&city={city}&key={key}#/hf-detail

    /**
     * @param
     * @return
     * @Title:
     * @createDate 2017/6/13 17:08
     * @Auther:zhanghongjie【hongjievip6688@163.com】
     * @version : v1.0
     * @updateDate :
     * @updateAuthor :
     */
    @Override
    public Result queryFund(Integer empId) {
        Result result = Result.createResult().setSuccess(true);
        FundAndInsurance tempFundAndInsurance = new FundAndInsurance();
        tempFundAndInsurance.setEmpId(empId);
        tempFundAndInsurance.setInsuranceFundType("FUND");
        CmEmployee emp = new CmEmployee();
        emp.setEmpId(empId);
        emp = cmEmployeeService.findByPK(emp);
        if (emp == null) {
            result.setError("员工信息不存在，请确认！");
            result.setSuccess(false);
            return result;
        }
        CmCorp cmCorp = new CmCorp();
        cmCorp.setCpId(emp.getCpId());
        cmCorp = cmCorpService.findOneByCorpId(cmCorp);
        if (cmCorp == null) {
            result.setError("员工公司信息不存在，请确认！");
            result.setSuccess(false);
            return result;
        }

        List fundInfo = new ArrayList();
        //  获取开始 缴纳月份， 和结束缴纳月份
        String startMonth = fundAndInsuranceDao.queryFundAndInsuranceStartOfSelfService(tempFundAndInsurance);
        String endMonth = fundAndInsuranceDao.queryFundAndInsuranceLastOfSelfService(tempFundAndInsurance);
        int start = Integer.valueOf(StringUtils.substring(startMonth, 0, 4));
        int end = Integer.valueOf(StringUtils.substring(endMonth, 0, 4));

        TreeMap dates = new TreeMap();
        for (int i = start; i <= end; i++) {
            dates.put(i + "", "2");
        }
        List<String> dateList = getMonthBetween(dates);

        for (String date : dateList) {
            Map insuranceInfoMap = new HashMap();
            if (date.compareTo(DateUtil.getCurYearMonthStr()) > 0) {
                insuranceInfoMap.put("fundPayment", 0);
                insuranceInfoMap.put("insuredMonth", date);
                fundInfo.add(insuranceInfoMap);
            } else {
                ContextInfo cti = new ContextInfo();
                cti.setOrgId(cmCorp.getCpId());
                cti.setAuthority("ALL");
                List<InsureEmpDetail> insureEmpDetails = spsBillService.queryEmpBillPayment(cti, empId, date);
                BigDecimal payment = new BigDecimal(0);
                for (InsureEmpDetail vo : insureEmpDetails) {
                    if (vo.getInsureName().equals("公积金")) {
                        BigDecimal bd = null;
                        if (StringUtils.isEmpty(vo.getTotalPayment())) {
                            bd = new BigDecimal(0);
                        } else {
                            bd = new BigDecimal(vo.getTotalPayment());
                        }
                        payment = payment.add(bd);
                    }
                }
                insuranceInfoMap.put("fundPayment", payment);
                insuranceInfoMap.put("insuredMonth", date);
                fundInfo.add(insuranceInfoMap);
            }
        }
        result.setDataValue("fundInfo", fundInfo);
        return result;
    }


    private List getAreaIds() {
        List<Integer> areaIds = new ArrayList<>();
        areaIds.add(2);
        areaIds.add(100);
        areaIds.add(502);
        areaIds.add(801);
        return areaIds;
    }

    @Override
    public Result queryLastFundAndInsurance(Integer empId) {
        Result result = Result.createResult().setSuccess(true);
        FundAndInsurance tempFundAndInsurance = new FundAndInsurance();
        tempFundAndInsurance.setEmpId(empId);
        CmEmployee emp = new CmEmployee();
        emp.setEmpId(empId);
        emp = cmEmployeeService.findByPK(emp);
        if (emp == null) {
            result.setError("员工信息不存在，请确认！");
            result.setSuccess(false);
            return result;
        }
        CmCorp cmCorp = new CmCorp();
        cmCorp.setCpId(emp.getCpId());
        cmCorp = cmCorpService.findOneByCorpId(cmCorp);
        if (cmCorp == null) {
            result.setError("员工公司信息不存在，请确认！");
            result.setSuccess(false);
            return result;
        }

        SpsScheme spsScheme = spsSchemeService.findSchemeByEmpId(empId);

        Integer areaId = spsScheme.getAreaId();
        if (areaId == null) {
            result.setSuccess(false);
            result.setError("员工对应的方案信息不存在!");
            return result;
        }


        if (cmCorp.equals(CMCORPTYPE_SERVICE)) {
            // 如果是北京的，则进行跳转 ，
            setFundResult(result, areaId);
            tempFundAndInsurance.setInsuranceFundType("FUND");
            FundAndInsurance fundAndInsurance = fundAndInsuranceDao.queryFundAndInsuranceLast(tempFundAndInsurance);
            if (fundAndInsurance != null) {
                result.setDataValue("fundMonth", fundAndInsurance.getInsuredMonth() == null ? "" : fundAndInsurance.getInsuredMonth());
            } else {
                result.setDataValue("fundMonth", "");
            }

            setInsuranceResult(result, areaId);
            tempFundAndInsurance.setInsuranceFundType("INSURANCE");
            FundAndInsurance fundAndInsurance2 = fundAndInsuranceDao.queryFundAndInsuranceLast(tempFundAndInsurance);
            if (fundAndInsurance2 != null) {
                result.setDataValue("insuredMonth", fundAndInsurance2.getInsuredMonth() == null ? "" : fundAndInsurance2.getInsuredMonth());
            } else {
                result.setDataValue("insuredMonth", "");
            }

            return result;
        } else {

            setFundResult(result, areaId);

            tempFundAndInsurance.setInsuranceFundType("FUND");
            String fundMonth = fundAndInsuranceDao.queryFundAndInsuranceLastOfSelfService(tempFundAndInsurance);
            if (StringUtils.isNotEmpty(fundMonth)) {
                result.setDataValue("fundMonth", fundMonth);
            }


            setInsuranceResult(result, areaId);

            tempFundAndInsurance.setInsuranceFundType("INSURANCE");
            String insurMonth = fundAndInsuranceDao.queryFundAndInsuranceLastOfSelfService(tempFundAndInsurance);
            if (StringUtils.isNotEmpty(insurMonth)) {
                result.setDataValue("insuredMonth", insurMonth);
            }

            return result;
        }
    }

    private void setInsuranceResult(Result result, Integer areaId) {
        if (getAreaIds().contains(areaId)) {
            String url = INSURANCE_URL;
            result.setDataValue("insuranceIsNeedRedirct", 1);
            url = url.replace("{city}", getNameByAreaId(areaId));
            result.setDataValue("insuranceRedirctUrl", url);
        } else {
            result.setDataValue("insuranceIsNeedRedirct", 0);
            result.setDataValue("insuranceRedirctUrl", "");
        }
    }

    private void setFundResult(Result result, Integer areaId) {
        if (areaId == 2) {
            String url = FUNC_URL;
            result.setDataValue("fundIsNeedRedirct", 1);
            url = url.replace("{city}", getNameByAreaId(areaId));
            result.setDataValue("fundRedirctUrl", url);
        } else {
            result.setDataValue("fundIsNeedRedirct", 0);
            result.setDataValue("fundRedirctUrl", "");
        }
    }

    private String getNameByAreaId(Integer areaId) {
        BsArea vo = new BsArea();
        vo.setAreaId(areaId);
        vo = bsAreaService.findbyPK(vo);
        if (vo != null) {
            String cityName = vo.getName();
            if (cityName.endsWith("市")) {
                cityName = cityName.substring(0, cityName.length() - 1);
            }
            return cityName;
        }
        return "";
    }


    private static List<String> getMonthBetween(TreeMap date) {

        String minDate = "";
        String maxDate = "";
        ArrayList<String> result = new ArrayList<String>();
        Set test = date.descendingKeySet();
        Iterator<String> it = test.iterator();
        while (it.hasNext()) {
            String year = it.next().toString();
            minDate = year + "-01";
            maxDate = year + "-12";
            ArrayList<String> temp = new ArrayList<String>();
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

                Calendar min = Calendar.getInstance();
                Calendar max = Calendar.getInstance();

                min.setTime(sdf.parse(minDate));
                min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

                max.setTime(sdf.parse(maxDate));
                max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

                Calendar curr = min;
                while (curr.before(max)) {
                    temp.add(sdf.format(curr.getTime()));
                    curr.add(Calendar.MONTH, 1);
                }
                result.addAll(temp);
            } catch (Exception e) {
                e.printStackTrace();
                return result;
            }

        }
        return result;

    }

    @Override
    public Result getFundDetailByMonth(Integer empId, String year, String month) {
        Result result = Result.createResult().setSuccess(true);
        FundAndInsurance tempFundAndInsurance = new FundAndInsurance();
        tempFundAndInsurance.setEmpId(empId);
        tempFundAndInsurance.setInsuredMonth(year + "-" + month);
        tempFundAndInsurance.setInsuranceFundType("FUND");
        CmEmployee emp = new CmEmployee();
        emp.setEmpId(empId);
        emp = cmEmployeeService.findByPK(emp);
        if (emp == null) {
            result.setError("员工信息不存在，请确认！");
            result.setSuccess(false);
            return result;
        }
        CmCorp cmCorp = new CmCorp();
        cmCorp.setCpId(emp.getCpId());
        cmCorp = cmCorpService.findOneByCorpId(cmCorp);
        if (cmCorp == null) {
            result.setError("员工公司信息不存在，请确认！");
            result.setSuccess(false);
            return result;
        }
        ContextInfo cti = new ContextInfo();
        cti.setOrgId(cmCorp.getCpId());
        cti.setAuthority("ALL");
        List<InsureEmpDetail> insureEmpDetails = spsBillService.queryEmpBillPayment(cti, empId, year + "-" + month);
        if (CollectionUtils.isNotEmpty(insureEmpDetails)) {
            for (InsureEmpDetail insureEmpDetail : insureEmpDetails) {
                if (insureEmpDetail.getInsureName().equals("公积金")) {
                    result.setDataValue("compPay", insureEmpDetail.getCorpPayment() == null ? "" : insureEmpDetail.getCorpPayment());
                    result.setDataValue("itemName", insureEmpDetail.getInsureName() == null ? "" : insureEmpDetail.getInsureName());
                    result.setDataValue("personPay", insureEmpDetail.getEmpPayment() == null ? "" : insureEmpDetail.getEmpPayment());
                    result.setDataValue("year", year);
                    result.setDataValue("month", month);
                    result.setDataValue("payTatal", insureEmpDetail.getTotalPayment() == null ? "" : insureEmpDetail.getTotalPayment());
                    result.setDataValue("fundBase", emp.getFundSalary());
                }
            }
        }
        return result;
    }

    @Override
    public Result queryInsurance(Integer empId) {
        FundAndInsurance tempFundAndInsurance = new FundAndInsurance();
        tempFundAndInsurance.setEmpId(empId);
        tempFundAndInsurance.setInsuranceFundType("INSURANCE");
        Result result = Result.createResult().setSuccess(true);
        List insuranceInfo = new ArrayList();
        CmEmployee emp = new CmEmployee();
        emp.setEmpId(empId);
        emp = cmEmployeeService.findByPK(emp);
        if (emp == null) {
            result.setError("员工信息不存在，请确认！");
            result.setSuccess(false);
            return result;
        }
        CmCorp cmCorp = new CmCorp();
        cmCorp.setCpId(emp.getCpId());
        cmCorp = cmCorpService.findOneByCorpId(cmCorp);
        if (cmCorp == null) {
            result.setError("员工公司信息不存在，请确认！");
            result.setSuccess(false);
            return result;
        }

        //  获取开始 缴纳月份， 和结束缴纳月份
        String startMonth = fundAndInsuranceDao.queryFundAndInsuranceStartOfSelfService(tempFundAndInsurance);
        String endMonth = fundAndInsuranceDao.queryFundAndInsuranceLastOfSelfService(tempFundAndInsurance);
        int start = Integer.valueOf(StringUtils.substring(startMonth, 0, 4));
        int end = Integer.valueOf(StringUtils.substring(endMonth, 0, 4));

        TreeMap dates = new TreeMap();
        for (int i = start; i <= end; i++) {
            dates.put(i + "", "2");
        }
        List<String> dateList = getMonthBetween(dates);

        for (String date : dateList) {
            Map insuranceInfoMap = new HashMap();
            if (date.compareTo(DateUtil.getCurYearMonthStr()) > 0) {
                insuranceInfoMap.put("insurancePayment", 0);
                insuranceInfoMap.put("insuredMonth", date);
                insuranceInfo.add(insuranceInfoMap);
            } else {
                ContextInfo cti = new ContextInfo();
                cti.setOrgId(cmCorp.getCpId());
                cti.setAuthority("ALL");
                List<InsureEmpDetail> insureEmpDetails = spsBillService.queryEmpBillPayment(cti, empId, date);
                BigDecimal payment = new BigDecimal(0);
                for (InsureEmpDetail vo : insureEmpDetails) {
                    if (!vo.getInsureName().equals("服务费") && !vo.getInsureName().contains("公积金")) {
                        BigDecimal bd = null;
                        if (StringUtils.isEmpty(vo.getTotalPayment())) {
                            bd = new BigDecimal(0);
                        } else {
                            bd = new BigDecimal(vo.getTotalPayment());
                        }
                        payment = payment.add(bd);
                    }
                }
                insuranceInfoMap.put("insurancePayment", payment);
                insuranceInfoMap.put("insuredMonth", date);
                insuranceInfo.add(insuranceInfoMap);
            }
        }
        result.setDataValue("insuranceInfo", insuranceInfo);
        return result;
    }


    public static void sort(List<Map<String, Object>> list) {
        Collections.sort(list, new Comparator<Map<String, Object>>() {
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                return o1.get("insuredMonth").toString().compareTo(o2.get("insuredMonth").toString());
            }
        });
    }


    @Override
    public Result getInsuranceDetailByMonth(Integer empId, String year, String month) {
        Result result = Result.createResult().setSuccess(true);
        FundAndInsurance tempFundAndInsurance = new FundAndInsurance();
        tempFundAndInsurance.setEmpId(empId);
        tempFundAndInsurance.setInsuredMonth(year + "-" + month);
        tempFundAndInsurance.setInsuranceFundType("INSURANCE");
        List detailInfo = new ArrayList();
        CmEmployee emp = new CmEmployee();
        emp.setEmpId(empId);
        emp = cmEmployeeService.findByPK(emp);
        if (emp == null) {
            result.setError("员工信息不存在，请确认！");
            result.setSuccess(false);
            return result;
        }
        CmCorp cmCorp = new CmCorp();
        cmCorp.setCpId(emp.getCpId());
        cmCorp = cmCorpService.findOneByCorpId(cmCorp);
        if (cmCorp == null) {
            result.setError("员工公司信息不存在，请确认！");
            result.setSuccess(false);
            return result;
        }
        BigDecimal compPayTotal = new BigDecimal(0);
        BigDecimal personPayTotal = new BigDecimal(0);
        BigDecimal payTatal = new BigDecimal(0);

        ContextInfo cti = new ContextInfo();
        cti.setOrgId(cmCorp.getCpId());
        cti.setAuthority("ALL");
        List<InsureEmpDetail> insureEmpDetails = spsBillService.queryEmpBillPayment(cti, empId, year + "-" + month);
        if (CollectionUtils.isNotEmpty(insureEmpDetails)) {
            for (InsureEmpDetail insureEmpDetail : insureEmpDetails) {
                if (!insureEmpDetail.getInsureName().equals("服务费") && !insureEmpDetail.getInsureName().contains("公积金")) {
                    Map temp = new HashMap();
                    temp.put("itemName", insureEmpDetail.getInsureName());
                    temp.put("compPay", insureEmpDetail.getCorpPayment());
                    if (StringUtils.isEmpty(insureEmpDetail.getCorpPayment())) {
                        compPayTotal = compPayTotal.add(new BigDecimal(0));
                    } else {
                        compPayTotal = compPayTotal.add(new BigDecimal(insureEmpDetail.getCorpPayment()));
                    }
                    temp.put("personPay", insureEmpDetail.getEmpPayment());
                    if (StringUtils.isEmpty(insureEmpDetail.getCorpPayment())) {
                        personPayTotal = personPayTotal.add(new BigDecimal(0));
                    } else {
                        personPayTotal = personPayTotal.add(new BigDecimal(insureEmpDetail.getEmpPayment()));
                    }
                    BigDecimal total = null;
                    if (StringUtils.isEmpty(insureEmpDetail.getCorpPayment())) {
                        if (StringUtils.isEmpty(insureEmpDetail.getEmpPayment())) {
                            total = new BigDecimal(0).add(new BigDecimal(0));
                        } else {
                            total = new BigDecimal(0).add(new BigDecimal(insureEmpDetail.getEmpPayment()));
                        }
                    } else {
                        if (StringUtils.isEmpty(insureEmpDetail.getEmpPayment())) {
                            total = new BigDecimal(insureEmpDetail.getCorpPayment()).add(new BigDecimal(0));
                        } else {
                            total = new BigDecimal(insureEmpDetail.getCorpPayment()).add(new BigDecimal(insureEmpDetail.getEmpPayment()));
                        }
                    }
                    temp.put("itemPayTotal", total);
                    payTatal = payTatal.add(total);
                    detailInfo.add(temp);
                }
            }
            result.setDataValue("detailInfo", detailInfo);
            result.setDataValue("year", year);
            result.setDataValue("month", month);
            result.setDataValue("compPayTotal", compPayTotal);
            result.setDataValue("personPayTotal", personPayTotal);
            result.setDataValue("payTatal", payTatal);
            result.setDataValue("insuranceBase", emp.getInsuranceSalary());
        }
        return result;

    }

    public static void main(String[] args) throws Exception {

        List df = getMonthBetween(null);
        List<Map<String, Object>> dfs = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("insuredMonth", "2016-06");
        dfs.add(map1);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("insuredMonth", "2016-09");
        dfs.add(map2);

        Map<String, Object> map3 = new HashMap<>();
        map3.put("insuredMonth", "2017-06");
        dfs.add(map3);

        Map<String, Object> map4 = new HashMap<>();
        map4.put("insuredMonth", "2016-02");
        dfs.add(map4);

        sort(dfs);

        List insuranceInfo = new ArrayList();
        for (int i = 0; i < 20; i++) {
            Map insuranceInfoMap = new HashMap();
            insuranceInfoMap.put("insurancePayment", "sadf" + i);
            insuranceInfoMap.put("insuredMonth", "sdffdf" + i);
            insuranceInfo.add(insuranceInfoMap);
        }

        System.out.println(JSON.toJSONString(insuranceInfo));
    }
}
