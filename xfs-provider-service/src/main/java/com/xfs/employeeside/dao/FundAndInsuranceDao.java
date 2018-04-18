package com.xfs.employeeside.dao;/**
 * @author hongjie
 * @date 2017/3/15.14:41
 */

import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.common.util.DateUtil;
import com.xfs.employeeside.model.FundAndInsurance;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公积金和社保查询dao
 *
 * @author
 * @create 2017-03-15 14:41
 **/
@Repository
public class FundAndInsuranceDao extends BaseSqlMapDao {

    public List<FundAndInsurance> queryFundAndInsurance(FundAndInsurance obj) {

        Object ret = queryForList("FundAndInsurance.queryFundAndInsurance", obj);
        if (ret != null)
            return (List<FundAndInsurance>) ret;
        else
            return null;
    }


    public FundAndInsurance queryFundAndInsuranceLast(FundAndInsurance fundAndInsurance) {
        Map obj = new HashMap();
        obj.put("empId", fundAndInsurance.getEmpId());
        obj.put("insuranceFundType", fundAndInsurance.getInsuranceFundType());
        obj.put("currentMonth", DateUtil.getPreMonthStr(1));

        Object ret = queryForObject("FundAndInsurance.queryFundAndInsuranceLast", obj);
        if (ret != null)
            return (FundAndInsurance) ret;
        else
            return null;
    }

    public String queryFundAndInsuranceLastOfSelfService(FundAndInsurance fundAndInsurance) {
        Map obj = new HashMap();
        obj.put("empId", fundAndInsurance.getEmpId());
        obj.put("insuranceFundType", fundAndInsurance.getInsuranceFundType());
        Object ret = queryForObject("FundAndInsurance.queryFundAndInsuranceLastOfSelfService", obj);
        if (ret != null)
            return (String) ret;
        else
            return null;
    }


    public String queryFundAndInsuranceStartOfSelfService(FundAndInsurance fundAndInsurance) {
        Map obj = new HashMap();
        obj.put("empId", fundAndInsurance.getEmpId());
        obj.put("insuranceFundType", fundAndInsurance.getInsuranceFundType());
        Object ret = queryForObject("FundAndInsurance.queryFundAndInsuranceStartOfSelfService", obj);
        if (ret != null)
            return (String) ret;
        else
            return null;
    }


    public List<FundAndInsurance> getFundAndInsuranceDetailByMonth(FundAndInsurance obj) {
        Object ret = queryForList("FundAndInsurance.getFundAndInsuranceDetailByMonth", obj);
        if (ret != null)
            return (List<FundAndInsurance>) ret;
        else
            return null;
    }

}
