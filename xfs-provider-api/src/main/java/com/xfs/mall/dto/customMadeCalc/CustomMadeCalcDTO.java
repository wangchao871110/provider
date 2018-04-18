package com.xfs.mall.dto.customMadeCalc;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.collections.map.HashedMap;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/29.
 */
public class CustomMadeCalcDTO {

    private Integer cpId; // 企业id

    @JSONField(name="_id")
    private String _id; // id
    private Integer months; // 月份总数
    private List<CustomMadeCalcEmpDTO> emps; // 员工信息
    private List<CustomMadeCalcServiceDTO> insuServices; // 社保服务

    private Integer ciType; // 商保服务类型
    private List<CustomMadeCalcServiceDTO> ciServices; // 商保服务
    private Integer welfareType; // 福利产品类型
    private List<CustomMadeCalcServiceDTO> welfareServices; // 福利产品
    private Integer residentType; // 驻场客服类型
    private CustomMadeCalcServiceDTO residentService; // 驻场客服服务
    private List<CustomMadeCalcServiceDTO> otherServices; // 其他服务

    private BigDecimal traditionalHR; // 传统方式HR人力成本
    private BigDecimal traditionalCorpCost; // 传统方式企业成本
    private BigDecimal customCutsourcing; // 定制方式外包服务费
    private BigDecimal customCorpCost; // 定制方式企业成本

    public Integer getCpId() {
        return cpId;
    }

    public void setCpId(Integer cpId) {
        this.cpId = cpId;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }

    public List<CustomMadeCalcEmpDTO> getEmps() {
        return emps;
    }

    public void setEmps(List<CustomMadeCalcEmpDTO> emps) {
        this.emps = emps;
    }

    public List<CustomMadeCalcServiceDTO> getInsuServices() {
        return insuServices;
    }

    public void setInsuServices(List<CustomMadeCalcServiceDTO> insuServices) {
        this.insuServices = insuServices;
    }

    public Integer getCiType() {
        return ciType;
    }

    public void setCiType(Integer ciType) {
        this.ciType = ciType;
    }

    public List<CustomMadeCalcServiceDTO> getCiServices() {
        return ciServices;
    }

    public void setCiServices(List<CustomMadeCalcServiceDTO> ciServices) {
        this.ciServices = ciServices;
    }

    public Integer getWelfareType() {
        return welfareType;
    }

    public void setWelfareType(Integer welfareType) {
        this.welfareType = welfareType;
    }

    public List<CustomMadeCalcServiceDTO> getWelfareServices() {
        return welfareServices;
    }

    public void setWelfareServices(List<CustomMadeCalcServiceDTO> welfareServices) {
        this.welfareServices = welfareServices;
    }

    public Integer getResidentType() {
        return residentType;
    }

    public void setResidentType(Integer residentType) {
        this.residentType = residentType;
    }

    public CustomMadeCalcServiceDTO getResidentService() {
        return residentService;
    }

    public void setResidentService(CustomMadeCalcServiceDTO residentService) {
        this.residentService = residentService;
    }

    public List<CustomMadeCalcServiceDTO> getOtherServices() {
        return otherServices;
    }

    public void setOtherServices(List<CustomMadeCalcServiceDTO> otherServices) {
        this.otherServices = otherServices;
    }

    public BigDecimal getTraditionalHR() {
        return traditionalHR;
    }

    public void setTraditionalHR(BigDecimal traditionalHR) {
        this.traditionalHR = traditionalHR;
    }

    public BigDecimal getTraditionalCorpCost() {
        return traditionalCorpCost;
    }

    public void setTraditionalCorpCost(BigDecimal traditionalCorpCost) {
        this.traditionalCorpCost = traditionalCorpCost;
    }

    public BigDecimal getCustomCutsourcing() {
        return customCutsourcing;
    }

    public void setCustomCutsourcing(BigDecimal customCutsourcing) {
        this.customCutsourcing = customCutsourcing;
    }

    public BigDecimal getCustomCorpCost() {
        return customCorpCost;
    }

    public void setCustomCorpCost(BigDecimal customCorpCost) {
        this.customCorpCost = customCorpCost;
    }

    /**
     * 获取员工总数
     *
     * @return
     */
    public int getTotalEmpNum() {
        int total = 0;
        if (this.emps == null) {
            return total;
        }
        for (CustomMadeCalcEmpDTO emp: emps) {
            total += emp.getEmpNum();
        }
        return total;
    }

    /**
     * 获取服务Map
     *
     * @return
     */
    public Map<String, CustomMadeCalcServiceDTO> getServiceMap() {
        Map<String, CustomMadeCalcServiceDTO> map = new HashedMap();
        if (this.getInsuServices() != null) {
            for (CustomMadeCalcServiceDTO serviceDTO : this.getInsuServices()) {
                map.put(serviceDTO.getName(), serviceDTO);
            }
        }
        if (this.getCiServices() != null) {
            for (CustomMadeCalcServiceDTO serviceDTO : this.getCiServices()) {
                map.put(serviceDTO.getName(), serviceDTO);
            }
        }
        if (this.getWelfareServices() != null) {
            for (CustomMadeCalcServiceDTO serviceDTO : this.getWelfareServices()) {
                map.put(serviceDTO.getName(), serviceDTO);
            }
        }
        if (this.getOtherServices() != null) {
            for (CustomMadeCalcServiceDTO serviceDTO : this.getOtherServices()) {
                map.put(serviceDTO.getName(), serviceDTO);
            }
        }
        return map;
    }

}
