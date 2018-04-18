package com.xfs.corp.dto;

import com.xfs.bill.model.BdBillrule;
import com.xfs.business.model.BsArearatio;
import com.xfs.common.util.Arith;
import com.xfs.corp.model.CmEmployeeInsurance;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by quanjiahua on 17/7/28.
 */
public class EmpListDto implements java.io.Serializable {

    private Integer empId;
    private String name;
    private String areaId;
    private String areaName;
    private String livingType;
    private String identityType;
    private String identityCode;
    private String mobile;
    private String endDate;
    private String beginDate;
    private Date createDt;
    private Date modifyDt;
    private String insurance_salary;
    private String fund_salary;
    private String insEmpPaybase;
    private String fundEmpPaybase;
    private String insCorpPaybase;
    private String fundCorpPaybase;
    private String insuranceState;
    private String fundState;
    private String empPayment = "--";
    private String corpPayment = "--";
    private String total = "--";
    private String isNew;
    private String price = "--";

    private List<EmplnsFundDetailDto> insurancelist;

    public List<EmplnsFundDetailDto> getInsurancelist() {
        return insurancelist;
    }

    public void setInsurancelist(List<EmplnsFundDetailDto> insurancelist) {

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
            Calendar nextPeriod = Calendar.getInstance();
            String findMonth = format.format(nextPeriod.getTime());

            if (insurancelist!=null && insurancelist.size()>0){
                BigDecimal empPay = new BigDecimal(0.00);
                BigDecimal corpPay = new BigDecimal(0.00);
                for (EmplnsFundDetailDto emplnsFundDetailDto : insurancelist) {

                    if (BsArearatio.billingCycle_MONTH_ANNUALY.equals(emplnsFundDetailDto.getBillingCycle())
                            || BsArearatio.billingCycle_YEAR_ANNUALY.equals(emplnsFundDetailDto.getBillingCycle())) {
                        // 缴费参保月份等于年缴险种月份 或者 新参保员工
                        if (((format.parse(findMonth).getMonth() + 1) == emplnsFundDetailDto.getPayPeriod())
                                || findMonth.equals(emplnsFundDetailDto.getBeginPeriod())) {
                            // 年缴不足一年按年等同于月缴 不需要处理 年缴不足一年按月
                            if (BsArearatio.billingCycle_MONTH_ANNUALY.equals(emplnsFundDetailDto.getBillingCycle())) {
                                if (StringUtils.isNotBlank(emplnsFundDetailDto.getBeginPeriod())) {
                                    // 缴费月份-入职月份 如果 小于等于0 则 +12
                                    int months = emplnsFundDetailDto.getPayPeriod() - format.parse(emplnsFundDetailDto.getBeginPeriod()).getMonth() - 1;
                                    if (months <= 0) {
                                        months += 12;
                                    }
                                    emplnsFundDetailDto.setCorpPayment(Arith.div(emplnsFundDetailDto.getCorpPayment(), new BigDecimal(12.00 / months)));
                                    emplnsFundDetailDto.setCorpAddition(Arith.div(emplnsFundDetailDto.getCorpAddition(), new BigDecimal(12.00 / months)));
                                    emplnsFundDetailDto.setEmpPayment(Arith.div(emplnsFundDetailDto.getEmpPayment(), new BigDecimal(12.00 / months)));
                                    emplnsFundDetailDto.setEmpAddition(Arith.div(emplnsFundDetailDto.getEmpAddition(), new BigDecimal(12.00 / months)));
                                }
                            }
                        } else {
                            emplnsFundDetailDto.setCorpPayment(BigDecimal.ZERO);
                            emplnsFundDetailDto.setEmpPayment(BigDecimal.ZERO);
                        }
                    }

                    empPay = empPay.add(emplnsFundDetailDto.getEmpPayment());
                    corpPay = corpPay.add(emplnsFundDetailDto.getCorpPayment());

                }
                this.empPayment = empPay.setScale(2, RoundingMode.UP)+"";
                this.corpPayment = corpPay.setScale(2, RoundingMode.UP)+"";
                this.total = empPay.add(new BigDecimal(this.corpPayment)).setScale(2, RoundingMode.UP)+"";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.insurancelist = insurancelist;
    }

    public String getIsNew() {
        return isNew;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew;
    }

    public String getCorpPayment() {
        return corpPayment;
    }

    public String getEmpPayment() {
        return empPayment;
    }

    public String getTotal() {
        return total;
    }

    public String getAreaName() {
        return areaName;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getInsuranceState() {
        return insuranceState;
    }

    public Integer getEmpId() {
        return empId;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public String getFund_salary() {
        return fund_salary;
    }

    public String getFundState() {
        return fundState;
    }

    public Date getModifyDt() {
        return modifyDt;
    }

    public String getInsurance_salary() {
        return insurance_salary;
    }

    public String getAreaId() {
        return areaId;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getIdentityType() {
        return identityType;
    }

    public String getLivingType() {
        return livingType;
    }

    public String getFundCorpPaybase() {
        return fundCorpPaybase;
    }

    public String getFundEmpPaybase() {
        return fundEmpPaybase;
    }

    public String getInsCorpPaybase() {
        return insCorpPaybase;
    }

    public String getInsEmpPaybase() {
        return insEmpPaybase;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public void setInsuranceState(String insuranceState) {
        this.insuranceState = insuranceState;
    }

    public void setModifyDt(Date modifyDt) {
        this.modifyDt = modifyDt;
    }

    public void setFundState(String fundState) {
        this.fundState = fundState;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setFund_salary(String fund_salary) {
        this.fund_salary = fund_salary;
    }

    public void setFundCorpPaybase(String fundCorpPaybase) {
        this.fundCorpPaybase = fundCorpPaybase;
    }

    public void setFundEmpPaybase(String fundEmpPaybase) {
        this.fundEmpPaybase = fundEmpPaybase;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public void setInsCorpPaybase(String insCorpPaybase) {
        this.insCorpPaybase = insCorpPaybase;
    }

    public void setLivingType(String livingType) {
        this.livingType = livingType;
    }

    public void setInsEmpPaybase(String insEmpPaybase) {
        this.insEmpPaybase = insEmpPaybase;
    }

    public void setInsurance_salary(String insurance_salary) {
        this.insurance_salary = insurance_salary;
    }

    public void setTotal(String total) {
        try {
            this.total = new BigDecimal(total).setScale(2, RoundingMode.DOWN)+"";
        } catch (Exception e) {
            this.total = "--";
        }
    }

    public void setCorpPayment(String corpPayment) {
        try {
            this.corpPayment = new BigDecimal(corpPayment).setScale(2, RoundingMode.DOWN)+"";
        } catch (Exception e) {
            this.corpPayment = "--";
        }
    }

    public void setEmpPayment(String empPayment) {
        try {
            this.empPayment = new BigDecimal(empPayment).setScale(2, RoundingMode.DOWN)+"";
        } catch (Exception e) {
            this.empPayment = "--";
        }
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
