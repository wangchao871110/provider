package com.xfs.bill.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.xfs.bill.model.SpsEmpActualdetail;
import com.xfs.common.util.StringUtils;
import com.xfs.enums.AreaInsuranceType;

/**
 * Created by konglc on 2016-08-15.
 * 实缴账单
 */
public class PaidBillDto implements Serializable{

    private static final long serialVersionUID = 1L;

    private Map<String,SpsEmpActualdetail> spsEmpActualdetailMap = new HashMap<>();

    private static String PENSION = "1";

    private static String MEDICINE = "2";

    private static String INJURY = "3";

    private static String UNEMPLOYMENT = "4";

    private static String MATERNITY = "5";

    private static String FUND = "6";

    public PaidBillDto() {
        SpsEmpActualdetail pension = new SpsEmpActualdetail();
        pension.setInsuranceId(1);
        spsEmpActualdetailMap.put(PENSION,pension);

        SpsEmpActualdetail medicine = new SpsEmpActualdetail();
        medicine.setInsuranceId(2);
        spsEmpActualdetailMap.put(MEDICINE,medicine);

        SpsEmpActualdetail injury = new SpsEmpActualdetail();
        injury.setInsuranceId(3);
        spsEmpActualdetailMap.put(INJURY,injury);

        SpsEmpActualdetail unemployment = new SpsEmpActualdetail();
        unemployment.setInsuranceId(4);
        spsEmpActualdetailMap.put(UNEMPLOYMENT,unemployment);

        SpsEmpActualdetail maternity = new SpsEmpActualdetail();
        maternity.setInsuranceId(5);
        spsEmpActualdetailMap.put(MATERNITY,maternity);

        SpsEmpActualdetail fund = new SpsEmpActualdetail();
        fund.setInsuranceId(6);
        spsEmpActualdetailMap.put(FUND,fund);
    }

    @PropertyMapping(AreaInsuranceType.NAME)
    private String name;

    @PropertyMapping(AreaInsuranceType.IDENTITYCODE)
    private String identityCode;

    @PropertyMapping(AreaInsuranceType.PERIOD)
    private String period;

    @PropertyMapping(AreaInsuranceType.PAYTYPE)
    private String payType = "MONTH";

    @PropertyMapping(AreaInsuranceType.FUNDACCID)
    private Integer fundAccid;

    @PropertyMapping(AreaInsuranceType.INSURANCEACCID)
    private Integer insuranceAccid;

    private Integer empId;

    @PropertyMapping(AreaInsuranceType.INSUREDMONTH)
    private String insuredMonth;

    private String insuranceLivingType;

    private String insuranceState = "ON";

    private String fundState = "ON";

    private Integer source = 1;//

    @PropertyMapping(AreaInsuranceType.HAVEPENSION)
    private String havePension;//是否存在养老保险

    @PropertyMapping(AreaInsuranceType.HAVEMEDICINE)
    private String haveMedicine;//是否存在医疗保险

    @PropertyMapping(AreaInsuranceType.HAVEINJURY)
    private String haveInjury;//是否存在工伤保险

    @PropertyMapping(AreaInsuranceType.HAVEUNEMPLOYMENT)
    private String haveUnemployment;//是否存在失业保险

    @PropertyMapping(AreaInsuranceType.HAVEMATERNITY)
    private String haveMaternity;//是否存在生育保险

    @PropertyMapping(AreaInsuranceType.HAVEFUND)
    private String haveFund;//是否存在公积金



    @PropertyMapping(AreaInsuranceType.PENSIONPAYBASE)
    private String pensionPayBase;

    @PropertyMapping(AreaInsuranceType.PENSIONCORPRATIO)
    private String pensionCorpRatio;

    @PropertyMapping(AreaInsuranceType.PENSIONCORPPAYMENT)
    private String pensionCorpPayment;

    @PropertyMapping(AreaInsuranceType.PENSIONEMPRATIO)
    private String pensionEmpRatio;

    @PropertyMapping(AreaInsuranceType.PENSIONEMPPAYMENT)
    private String pensionEmpPayment;


    @PropertyMapping(AreaInsuranceType.MEDICINEPAYBASE)
    private String medicinePayBase;

    @PropertyMapping(AreaInsuranceType.MEDICINECORPRATIO)
    private String medicineCorpRatio;

    @PropertyMapping(AreaInsuranceType.MEDICINECORPPAYMENT)
    private String medicineCorpPayment;

    @PropertyMapping(AreaInsuranceType.MEDICINEEMPRATIO)
    private String medicineEmpRatio;

    @PropertyMapping(AreaInsuranceType.MEDICINEEMPPAYMENT)
    private String medicineEmpPayment;


    @PropertyMapping(AreaInsuranceType.INJURYPAYBASE)
    private String injuryPayBase;

    @PropertyMapping(AreaInsuranceType.INJURYCORPRATIO)
    private String injuryCorpRatio;

    @PropertyMapping(AreaInsuranceType.INJURYCORPPAYMENT)
    private String injuryCorpPayment;



    @PropertyMapping(AreaInsuranceType.UNEMPLOYMENTPAYBASE)
    private String unemploymentPayBase;

    @PropertyMapping(AreaInsuranceType.UNEMPLOYMENTCORPRATIO)
    private String unemploymentCorpRatio;

    @PropertyMapping(AreaInsuranceType.UNEMPLOYMENTCORPPAYMENT)
    private String unemploymentCorpPayment;

    @PropertyMapping(AreaInsuranceType.UNEMPLOYMENTEMPRATIO)
    private String unemploymentEmpRatio;

    @PropertyMapping(AreaInsuranceType.UNEMPLOYMENTEMPPAYMENT)
    private String unemploymentEmpPayment;


    @PropertyMapping(AreaInsuranceType.MATERNITYPAYBASE)
    private String maternityPayBase;

    @PropertyMapping(AreaInsuranceType.MATERNITYCORPRATIO)
    private String maternityCorpRatio;

    @PropertyMapping(AreaInsuranceType.MATERNITYCORPPAYMENT)
    private String maternityCorpPayment;


    @PropertyMapping(AreaInsuranceType.FUNDPAYBASE)
    private String fundPayBase;

    @PropertyMapping(AreaInsuranceType.FUNDCORPRATIO)
    private String fundCorpRatio;

    @PropertyMapping(AreaInsuranceType.FUNDCORPPAYMENT)
    private String fundCorpPayment;

    @PropertyMapping(AreaInsuranceType.FUNDEMPRATIO)
    private String fundEmpRatio;

    @PropertyMapping(AreaInsuranceType.FUNDEMPPAYMENT)
    private String fundEmpPayment;

    @PropertyMapping(AreaInsuranceType.FUNDAREANAME)
    private String fundAreaName;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) throws ParseException {
    	//对输入的日期格式化如2016-7，格式化成2016-07
        if(!StringUtils.isBlank(period)){
            if(period.indexOf("-") > 0){
                Date date = new SimpleDateFormat("yyyy-MM").parse(period);
                this.period = new SimpleDateFormat("yyyy-MM").format(date);
            }else{
                Date date = new SimpleDateFormat("yyyyMM").parse(period);
                this.period = new SimpleDateFormat("yyyy-MM").format(date);
            }
        }else{
            this.period = period;
        }
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        if(StringUtils.isBlank(payType) || payType.contains("汇缴") || payType.contains("月核定"))
            this.payType = "MONTH";
        else
            this.payType = "PATCH";
    }

    public String getPensionPayBase() {
        return pensionPayBase;
    }

    public void setPensionPayBase(String pensionPayBase) {
        if(StringUtils.isBlank(pensionPayBase))
            pensionPayBase = "0";
        pensionPayBase = pensionPayBase.replace(",","");
        this.spsEmpActualdetailMap.get(PENSION).setPayBase(new BigDecimal(pensionPayBase));
        this.spsEmpActualdetailMap.get(PENSION).setHave(true);
        this.pensionPayBase = pensionPayBase;
    }

    public String getPensionCorpRatio() {
        return pensionCorpRatio;
    }

    public void setPensionCorpRatio(String pensionCorpRatio) {
        if(StringUtils.isBlank(pensionCorpRatio))
            pensionCorpRatio = "0";
        if(pensionCorpRatio.contains("%")){
            pensionCorpRatio = pensionCorpRatio.replaceAll("%", "");
            Float f = Float.valueOf(pensionCorpRatio);
            pensionCorpRatio = String.valueOf(f/100);
        }
        BigDecimal corpRatio = new BigDecimal(pensionCorpRatio);
        corpRatio.setScale(4,BigDecimal.ROUND_CEILING);
        this.spsEmpActualdetailMap.get(PENSION).setCorpRatio(corpRatio);
        this.spsEmpActualdetailMap.get(PENSION).setHave(true);
        this.pensionCorpRatio = pensionCorpRatio;
    }

    public String getPensionCorpPayment() {
        return pensionCorpPayment;
    }

    public void setPensionCorpPayment(String pensionCorpPayment) {
        if(StringUtils.isBlank(pensionCorpPayment))
            pensionCorpPayment = "0";
        pensionCorpPayment = pensionCorpPayment.replace(",","");
        BigDecimal corpPayment = new BigDecimal(pensionCorpPayment);
        corpPayment.setScale(4,BigDecimal.ROUND_CEILING);
        this.spsEmpActualdetailMap.get(PENSION).setCorpPayment(corpPayment);
        this.spsEmpActualdetailMap.get(PENSION).setHave(true);
        this.pensionCorpPayment = pensionCorpPayment;
    }

    public String getPensionEmpRatio() {
        return pensionEmpRatio;
    }

    public void setPensionEmpRatio(String pensionEmpRatio) {
        if(StringUtils.isBlank(pensionEmpRatio))
            pensionEmpRatio = "0";
        if(pensionEmpRatio.contains("%")){
            pensionEmpRatio = pensionEmpRatio.replaceAll("%", "");
            Float f = Float.valueOf(pensionEmpRatio);
            pensionEmpRatio = String.valueOf(f/100);
        }
        BigDecimal empRatio = new BigDecimal(pensionEmpRatio);
        empRatio.setScale(4,BigDecimal.ROUND_CEILING);
        this.spsEmpActualdetailMap.get(PENSION).setEmpRatio(empRatio);
        this.spsEmpActualdetailMap.get(PENSION).setHave(true);
        this.pensionEmpRatio = pensionEmpRatio;
    }

    public String getPensionEmpPayment() {
        return pensionEmpPayment;
    }

    public void setPensionEmpPayment(String pensionEmpPayment) {
        if(StringUtils.isBlank(pensionEmpPayment))
            pensionEmpPayment = "0";
        pensionEmpPayment = pensionEmpPayment.replace(",","");

        BigDecimal empPayment = new BigDecimal(pensionEmpPayment);
        empPayment.setScale(4,BigDecimal.ROUND_CEILING);
        this.spsEmpActualdetailMap.get(PENSION).setEmpPayment(empPayment);
        this.spsEmpActualdetailMap.get(PENSION).setHave(true);
        this.pensionEmpPayment = pensionEmpPayment;
    }

    public String getMedicinePayBase() {
        return medicinePayBase;
    }

    public void setMedicinePayBase(String medicinePayBase) {
        if(StringUtils.isBlank(medicinePayBase))
            medicinePayBase = "0";
        medicinePayBase = medicinePayBase.replace(",","");
        BigDecimal payBase = new BigDecimal(medicinePayBase);
        payBase.setScale(4,BigDecimal.ROUND_CEILING);
        this.spsEmpActualdetailMap.get(MEDICINE).setPayBase(payBase);
        this.spsEmpActualdetailMap.get(MEDICINE).setHave(true);
        this.medicinePayBase = medicinePayBase;
    }

    public String getMedicineCorpRatio() {
        return medicineCorpRatio;
    }

    public void setMedicineCorpRatio(String medicineCorpRatio) {
        if(StringUtils.isBlank(medicineCorpRatio))
            medicineCorpRatio = "0";
        if(medicineCorpRatio.contains("%")){
            medicineCorpRatio = medicineCorpRatio.replaceAll("%", "");
            Float f = Float.valueOf(medicineCorpRatio);
            medicineCorpRatio = String.valueOf(f/100);
        }
        BigDecimal corpRatio = new BigDecimal(medicineCorpRatio);
        corpRatio.setScale(4,BigDecimal.ROUND_CEILING);
        this.spsEmpActualdetailMap.get(MEDICINE).setCorpRatio(corpRatio);
        this.spsEmpActualdetailMap.get(MEDICINE).setHave(true);
        this.medicineCorpRatio = medicineCorpRatio;
    }

    public String getMedicineCorpPayment() {
        return medicineCorpPayment;
    }

    public void setMedicineCorpPayment(String medicineCorpPayment) {
        if(StringUtils.isBlank(medicineCorpPayment))
            medicineCorpPayment = "0";
        medicineCorpPayment = medicineCorpPayment.replace(",","");
        medicineCorpPayment = medicineCorpPayment.replace(" ","");
        BigDecimal corpPayment = new BigDecimal(medicineCorpPayment);
        corpPayment.setScale(4,BigDecimal.ROUND_CEILING);
        this.spsEmpActualdetailMap.get(MEDICINE).setCorpPayment(corpPayment);
        this.spsEmpActualdetailMap.get(MEDICINE).setHave(true);
        this.medicineCorpPayment = medicineCorpPayment;
    }

    public String getMedicineEmpRatio() {
        return medicineEmpRatio;
    }

    public void setMedicineEmpRatio(String medicineEmpRatio) {
        if(StringUtils.isBlank(medicineEmpRatio))
            medicineEmpRatio = "0";
        if(medicineEmpRatio.contains("%")){
            medicineEmpRatio = medicineEmpRatio.replaceAll("%", "");
            Float f = Float.valueOf(medicineEmpRatio);
            medicineEmpRatio = String.valueOf(f/100);
        }
        BigDecimal empRatio = new BigDecimal(medicineEmpRatio);
        empRatio.setScale(4,BigDecimal.ROUND_CEILING);
        this.spsEmpActualdetailMap.get(MEDICINE).setEmpRatio(empRatio);
        this.spsEmpActualdetailMap.get(MEDICINE).setHave(true);
        this.medicineEmpRatio = medicineEmpRatio;
    }

    public String getMedicineEmpPayment() {
        return medicineEmpPayment;
    }

    public void setMedicineEmpPayment(String medicineEmpPayment) {
        if(StringUtils.isBlank(medicineEmpPayment))
            medicineEmpPayment = "0";
        medicineEmpPayment = medicineEmpPayment.replace(",","");
        medicineEmpPayment = medicineEmpPayment.replace("<br />","");
        BigDecimal empPayment = new BigDecimal(medicineEmpPayment);
        empPayment.setScale(4,BigDecimal.ROUND_CEILING);
        this.spsEmpActualdetailMap.get(MEDICINE).setEmpPayment(empPayment);
        this.spsEmpActualdetailMap.get(MEDICINE).setHave(true);
        this.medicineEmpPayment = medicineEmpPayment;
    }

    public String getInjuryPayBase() {
        return injuryPayBase;
    }

    public void setInjuryPayBase(String injuryPayBase) {
        if(StringUtils.isBlank(injuryPayBase))
            injuryPayBase = "0";
        injuryPayBase = injuryPayBase.replace(",","");
        BigDecimal payBase = new BigDecimal(injuryPayBase);
        payBase.setScale(4,BigDecimal.ROUND_CEILING);
        this.spsEmpActualdetailMap.get(INJURY).setPayBase(payBase);
        this.spsEmpActualdetailMap.get(INJURY).setHave(true);
        this.injuryPayBase = injuryPayBase;
    }

    public String getInjuryCorpRatio() {
        return injuryCorpRatio;
    }

    public void setInjuryCorpRatio(String injuryCorpRatio) {
        if(StringUtils.isBlank(injuryCorpRatio))
            injuryCorpRatio = "0";
        if(injuryCorpRatio.contains("%")){
            injuryCorpRatio = injuryCorpRatio.replaceAll("%", "");
            Float f = Float.valueOf(injuryCorpRatio);
            injuryCorpRatio = String.valueOf(f/100);
        }
        BigDecimal corpRatio = new BigDecimal(injuryCorpRatio);
        corpRatio.setScale(4,BigDecimal.ROUND_CEILING);
        this.spsEmpActualdetailMap.get(INJURY).setCorpRatio(corpRatio);
        this.spsEmpActualdetailMap.get(INJURY).setHave(true);
        this.injuryCorpRatio = injuryCorpRatio;
    }

    public String getInjuryCorpPayment() {
        return injuryCorpPayment;
    }

    public void setInjuryCorpPayment(String injuryCorpPayment) {
        if(StringUtils.isBlank(injuryCorpPayment))
            injuryCorpPayment = "0";
        injuryCorpPayment = injuryCorpPayment.replace(",","");
        BigDecimal corpPayment = new BigDecimal(injuryCorpPayment);
        corpPayment.setScale(4,BigDecimal.ROUND_CEILING);
        this.spsEmpActualdetailMap.get(INJURY).setCorpPayment(corpPayment);
        this.spsEmpActualdetailMap.get(INJURY).setHave(true);
        this.injuryCorpPayment = injuryCorpPayment;
    }

    public String getUnemploymentPayBase() {
        return unemploymentPayBase;
    }

    public void setUnemploymentPayBase(String unemploymentPayBase) {
        if(StringUtils.isBlank(unemploymentPayBase))
            unemploymentPayBase = "0";
        unemploymentPayBase = unemploymentPayBase.replace(",","");
        BigDecimal payBase = new BigDecimal(unemploymentPayBase);
        payBase.setScale(4,BigDecimal.ROUND_CEILING);
        this.spsEmpActualdetailMap.get(UNEMPLOYMENT).setPayBase(payBase);
        this.spsEmpActualdetailMap.get(UNEMPLOYMENT).setHave(true);
        this.unemploymentPayBase = unemploymentPayBase;
    }

    public String getUnemploymentCorpRatio() {
        return unemploymentCorpRatio;
    }

    public void setUnemploymentCorpRatio(String unemploymentCorpRatio) {
        if(StringUtils.isBlank(unemploymentCorpRatio))
            unemploymentCorpRatio = "0";
        if(unemploymentCorpRatio.contains("%")){
            unemploymentCorpRatio = unemploymentCorpRatio.replaceAll("%", "");
            Float f = Float.valueOf(unemploymentCorpRatio);
            unemploymentCorpRatio = String.valueOf(f/100);
        }
        BigDecimal corpRatio = new BigDecimal(unemploymentCorpRatio);
        corpRatio.setScale(4,BigDecimal.ROUND_CEILING);
        this.spsEmpActualdetailMap.get(UNEMPLOYMENT).setCorpRatio(corpRatio);
        this.spsEmpActualdetailMap.get(UNEMPLOYMENT).setHave(true);
        this.unemploymentCorpRatio = unemploymentCorpRatio;
    }

    public String getUnemploymentCorpPayment() {
        return unemploymentCorpPayment;
    }

    public void setUnemploymentCorpPayment(String unemploymentCorpPayment) {
        if(StringUtils.isBlank(unemploymentCorpPayment))
            unemploymentCorpPayment = "0";
        unemploymentCorpPayment = unemploymentCorpPayment.replace(",","");
        unemploymentCorpPayment = unemploymentCorpPayment.replace("<br />","");
        BigDecimal corpPayment = new BigDecimal(unemploymentCorpPayment);
        corpPayment.setScale(4,BigDecimal.ROUND_CEILING);
        this.spsEmpActualdetailMap.get(UNEMPLOYMENT).setCorpPayment(corpPayment);
        this.spsEmpActualdetailMap.get(UNEMPLOYMENT).setHave(true);
        this.unemploymentCorpPayment = unemploymentCorpPayment;
    }

    public String getUnemploymentEmpRatio() {
        return unemploymentEmpRatio;
    }

    public void setUnemploymentEmpRatio(String unemploymentEmpRatio) {
        if(StringUtils.isBlank(unemploymentEmpRatio))
            unemploymentEmpRatio = "0";
        if(unemploymentEmpRatio.contains("%")){
            unemploymentEmpRatio = unemploymentEmpRatio.replaceAll("%", "");
            Float f = Float.valueOf(unemploymentEmpRatio);
            unemploymentEmpRatio = String.valueOf(f/100);
        }
        BigDecimal empRatio = new BigDecimal(unemploymentEmpRatio);
        empRatio.setScale(4,BigDecimal.ROUND_CEILING);
        this.spsEmpActualdetailMap.get(UNEMPLOYMENT).setEmpRatio(empRatio);
        this.spsEmpActualdetailMap.get(UNEMPLOYMENT).setHave(true);
        this.unemploymentEmpRatio = unemploymentEmpRatio;
    }

    public String getUnemploymentEmpPayment() {
        return unemploymentEmpPayment;
    }

    public void setUnemploymentEmpPayment(String unemploymentEmpPayment) {
        if(StringUtils.isBlank(unemploymentEmpPayment))
            unemploymentEmpPayment = "0";
        unemploymentEmpPayment = unemploymentEmpPayment.replace(",","");
        BigDecimal empPayment = new BigDecimal(unemploymentEmpPayment);
        empPayment.setScale(4,BigDecimal.ROUND_CEILING);
        this.spsEmpActualdetailMap.get(UNEMPLOYMENT).setEmpPayment(empPayment);
        this.spsEmpActualdetailMap.get(UNEMPLOYMENT).setHave(true);
        this.unemploymentEmpPayment = unemploymentEmpPayment;
    }

    public String getMaternityPayBase() {
        return maternityPayBase;
    }

    public void setMaternityPayBase(String maternityPayBase) {
        if(StringUtils.isBlank(maternityPayBase))
            maternityPayBase = "0";
        maternityPayBase = maternityPayBase.replace(",","");
        BigDecimal payBase = new BigDecimal(maternityPayBase);
        payBase.setScale(4,BigDecimal.ROUND_CEILING);
        this.spsEmpActualdetailMap.get(MATERNITY).setPayBase(payBase);
        this.spsEmpActualdetailMap.get(MATERNITY).setHave(true);
        this.maternityPayBase = maternityPayBase;
    }

    public String getMaternityCorpRatio() {
        return maternityCorpRatio;
    }

    public void setMaternityCorpRatio(String maternityCorpRatio) {
        if(StringUtils.isBlank(maternityCorpRatio))
            maternityCorpRatio = "0";
        if(maternityCorpRatio.contains("%")){
            maternityCorpRatio = maternityCorpRatio.replaceAll("%", "");
            Float f = Float.valueOf(maternityCorpRatio);
            maternityCorpRatio = String.valueOf(f/100);
        }
        BigDecimal corpRatio = new BigDecimal(maternityCorpRatio);
        corpRatio.setScale(4,BigDecimal.ROUND_CEILING);
        this.spsEmpActualdetailMap.get(MATERNITY).setCorpRatio(corpRatio);
        this.spsEmpActualdetailMap.get(MATERNITY).setHave(true);
        this.maternityCorpRatio = maternityCorpRatio;
    }

    public String getMaternityCorpPayment() {
        return maternityCorpPayment;
    }

    public void setMaternityCorpPayment(String maternityCorpPayment) {
        if(StringUtils.isBlank(maternityCorpPayment))
            maternityCorpPayment = "0";
        maternityCorpPayment = maternityCorpPayment.replace(",","");
        BigDecimal corpPayment = new BigDecimal(maternityCorpPayment);
        corpPayment.setScale(4,BigDecimal.ROUND_CEILING);
        this.spsEmpActualdetailMap.get(MATERNITY).setCorpPayment(corpPayment);
        this.spsEmpActualdetailMap.get(MATERNITY).setHave(true);
        this.maternityCorpPayment = maternityCorpPayment;
    }

    public String getFundPayBase() {
        return fundPayBase;
    }

    public void setFundPayBase(String fundPayBase) {
        if(StringUtils.isBlank(fundPayBase))
            fundPayBase = "0";
        fundPayBase = fundPayBase.replace(",","");
        BigDecimal payBase = new BigDecimal(fundPayBase);
        payBase.setScale(4,BigDecimal.ROUND_CEILING);
        this.spsEmpActualdetailMap.get(FUND).setPayBase(payBase);
        this.spsEmpActualdetailMap.get(FUND).setHave(true);
        this.fundPayBase = fundPayBase;
    }

    public String getFundCorpRatio() {
        return fundCorpRatio;
    }

    public void setFundCorpRatio(String fundCorpRatio) {
        if(StringUtils.isBlank(fundCorpRatio))
            fundCorpRatio = "0";
        if(fundCorpRatio.contains("%")  || Double.parseDouble(fundCorpRatio) > 0){
            fundCorpRatio = fundCorpRatio.replaceAll("%", "");
            Float f = Float.valueOf(fundCorpRatio);
            fundCorpRatio = String.valueOf(f/100);
        }
        BigDecimal corpRatio = new BigDecimal(fundCorpRatio);
        corpRatio.setScale(4,BigDecimal.ROUND_CEILING);
        this.spsEmpActualdetailMap.get(FUND).setCorpRatio(corpRatio);
        this.spsEmpActualdetailMap.get(FUND).setHave(true);
        this.fundCorpRatio = fundCorpRatio;
    }

    public String getFundCorpPayment() {
        return fundCorpPayment;
    }

    public void setFundCorpPayment(String fundCorpPayment) {
        if(StringUtils.isBlank(fundCorpPayment))
            fundCorpPayment = "0";
        fundCorpPayment = fundCorpPayment.replace(",","");
        BigDecimal corpPayment = new BigDecimal(fundCorpPayment);
        corpPayment.setScale(4,BigDecimal.ROUND_CEILING);
        this.spsEmpActualdetailMap.get(FUND).setCorpPayment(corpPayment);
        this.spsEmpActualdetailMap.get(FUND).setHave(true);
        this.fundCorpPayment = fundCorpPayment;
    }

    public String getFundEmpRatio() {
        return fundEmpRatio;
    }

    public void setFundEmpRatio(String fundEmpRatio) {
        if(StringUtils.isBlank(fundEmpRatio))
            fundEmpRatio = "0";
        if(fundEmpRatio.contains("%")|| Double.parseDouble(fundEmpRatio) > 0){
            fundEmpRatio = fundEmpRatio.replaceAll("%", "");
            Float f = Float.valueOf(fundEmpRatio);
            fundEmpRatio = String.valueOf(f/100);
        }
        BigDecimal empRatio = new BigDecimal(fundEmpRatio);
        empRatio.setScale(4,BigDecimal.ROUND_CEILING);
        this.spsEmpActualdetailMap.get(FUND).setEmpRatio(empRatio);
        this.spsEmpActualdetailMap.get(FUND).setHave(true);
        this.fundEmpRatio = fundEmpRatio;
    }

    public String getFundEmpPayment() {
        return fundEmpPayment;
    }

    public void setFundEmpPayment(String fundEmpPayment) {
        if(StringUtils.isBlank(fundEmpPayment))
            fundEmpPayment = "0";
        fundEmpPayment = fundEmpPayment.replace(",","");
        BigDecimal payment = new BigDecimal(fundEmpPayment);
        payment.setScale(4,BigDecimal.ROUND_CEILING);
        this.spsEmpActualdetailMap.get(FUND).setEmpPayment(payment);
        this.spsEmpActualdetailMap.get(FUND).setHave(true);
        this.fundEmpPayment = fundEmpPayment;
    }

    public String getHavePension() {
        return havePension;
    }

    public void setHavePension(String havePension) {
        if("参保".equals(havePension))
            this.spsEmpActualdetailMap.get(PENSION).setHave(true);
        this.havePension = havePension;
    }

    public String getHaveMedicine() {
        return haveMedicine;
    }

    public void setHaveMedicine(String haveMedicine) {
        if("参保".equals(haveMedicine))
            this.spsEmpActualdetailMap.get(MEDICINE).setHave(true);
        this.haveMedicine = haveMedicine;
    }

    public String getHaveInjury() {
        return haveInjury;
    }

    public void setHaveInjury(String haveInjury) {
        if("参保".equals(haveInjury))
            this.spsEmpActualdetailMap.get(INJURY).setHave(true);
        this.haveInjury = haveInjury;
    }

    public String getHaveUnemployment() {
        return haveUnemployment;
    }

    public void setHaveUnemployment(String haveUnemployment) {
        if("参保".equals(haveUnemployment))
            this.spsEmpActualdetailMap.get(UNEMPLOYMENT).setHave(true);
        this.haveUnemployment = haveUnemployment;
    }

    public String getHaveMaternity() {
        return haveMaternity;
    }

    public void setHaveMaternity(String haveMaternity) {
        if("参保".equals(haveMaternity))
            this.spsEmpActualdetailMap.get(MATERNITY).setHave(true);
        this.haveMaternity = haveMaternity;
    }

    public String getHaveFund() {
        return haveFund;
    }

    public void setHaveFund(String haveFund) {
        if("参保".equals(haveFund))
            this.spsEmpActualdetailMap.get(FUND).setHave(true);
        this.haveFund = haveFund;
    }



    public String getFundAreaName() {
        return fundAreaName;
    }

    public void setFundAreaName(String fundAreaName) {
        this.fundAreaName = fundAreaName;
    }

    public Integer getFundAccid() {
        return fundAccid;
    }

    public void setFundAccid(Integer fundAccid) {
        this.fundAccid = fundAccid;
    }

    public Integer getInsuranceAccid() {
        return insuranceAccid;
    }

    public void setInsuranceAccid(Integer insuranceAccid) {
        this.insuranceAccid = insuranceAccid;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getInsuredMonth() {
        return insuredMonth;
    }

    public void setInsuredMonth(String insuredMonth)throws ParseException{
        //对输入的日期格式化如2016-7，格式化成2016-07
        if(!StringUtils.isBlank(insuredMonth)){
            insuredMonth = insuredMonth.replace("汇缴","");
            if(insuredMonth.indexOf("-") > 0){
                Date date = new SimpleDateFormat("yyyy-MM").parse(insuredMonth);
                this.insuredMonth = new SimpleDateFormat("yyyy-MM").format(date);
            }else{
                Date date = new SimpleDateFormat("yyyyMM").parse(insuredMonth);
                this.insuredMonth = new SimpleDateFormat("yyyy-MM").format(date);
            }
        }else{
            this.insuredMonth = insuredMonth;
        }
    }

    public String getInsuranceLivingType() {
        return insuranceLivingType;
    }

    public void setInsuranceLivingType(String insuranceLivingType) {
        this.insuranceLivingType = insuranceLivingType;
    }

    public String getInsuranceState() {
        return insuranceState;
    }

    public void setInsuranceState(String insuranceState) {
        this.insuranceState = insuranceState;
    }

    public String getFundState() {
        return fundState;
    }

    public void setFundState(String fundState) {
        this.fundState = fundState;
    }

    public Map<String,SpsEmpActualdetail> queryActualDetail(){
        return spsEmpActualdetailMap;
    }

    public void setActualDetail(Map<String,SpsEmpActualdetail> actualdetailMap){
        this.spsEmpActualdetailMap = actualdetailMap;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }
}
