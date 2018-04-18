package com.xfs.mall.customer.service;

import com.alibaba.fastjson.JSONObject;
import com.xfs.common.ContextInfo;
import com.xfs.common.Result;
import com.xfs.common.mongo.MongoDao;
import com.xfs.common.util.StringUtils;
import com.xfs.mall.dto.customMadeCalc.CustomMadeCalcDTO;
import com.xfs.mall.dto.customMadeCalc.CustomMadeCalcEmpDTO;
import com.xfs.mall.dto.customMadeCalc.CustomMadeCalcServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/29.
 */
@Service
public class CustomMadeCalcServiceImpl implements CustomMadeCalcService {

    @Autowired
    private MongoDao mongoDao;

    /**
     * 从mongodb中获取数据
     *
     * @param id
     * @return
     */
    public CustomMadeCalcDTO getCustomMadeCalcData(String id) {
        Map<String, Object> data = mongoDao.getDataByPrimaryKey(TABLE_NAME, id);
        return JSONObject.parseObject(JSONObject.toJSONString(data), CustomMadeCalcDTO.class);
    }

    /**
     * 保存信息
     *
     * @param emps
     * @param months
     * @param insuServices
     * @param ciType
     * @param ciServices
     * @param welfareType
     * @param welfareServices
     * @param residentType
     * @param residentService
     * @param otherServices
     * @param traditionalHR
     * @param traditionalCorpCost
     * @param customCutsourcing
     * @param customCorpCost
     * @return
     */
    public Result setCustomMadeCalcData(ContextInfo cti, String id, String emps, Integer months, String insuServices, Integer ciType, String ciServices,
                                        Integer welfareType, String welfareServices, Integer residentType, String residentService, String otherServices,
                                        BigDecimal traditionalHR, BigDecimal traditionalCorpCost, BigDecimal customCutsourcing, BigDecimal customCorpCost) {
        Result result = Result.createResult().setSuccess(false);
        if (StringUtils.isBlank(emps) || StringUtils.isBlank(insuServices)) {
            result.setMsg("参数有问题");
            return result;
        }
        CustomMadeCalcDTO dto = new CustomMadeCalcDTO();
        if (cti != null) {
            dto.setCpId(cti.getOrgId());
        }
        dto.set_id(id);
        List<CustomMadeCalcEmpDTO> empList = new ArrayList<>();
        for (String emp: emps.split(",")) {
            if (StringUtils.isBlank(emp)) {
                continue;
            }
            CustomMadeCalcEmpDTO empDTO = new CustomMadeCalcEmpDTO();
            String[] arr = emp.split("_");
            empDTO.setAreaId(Integer.parseInt(arr[0]));
            empDTO.setAreaName(arr[1]);
            empDTO.setEmpNum(Integer.parseInt(arr[2]));
            empDTO.setWage(new BigDecimal(arr[3]));
            empDTO.setCorpCost(new BigDecimal(arr[4]));
            empList.add(empDTO);
        }
        dto.setEmps(empList);
        dto.setMonths(months);
        List<CustomMadeCalcServiceDTO> insuList = new ArrayList<>();
        for (String insuService: insuServices.split(",")) {
            if (StringUtils.isBlank(insuService)) {
                continue;
            }
            CustomMadeCalcServiceDTO insuDTO = new CustomMadeCalcServiceDTO();
            String[] arr = insuService.split("_");
            insuDTO.setName(arr[0]);
            insuDTO.setPrice(new BigDecimal(arr[1]));
            insuList.add(insuDTO);
        }
        dto.setInsuServices(insuList);
        dto.setCiType(ciType);
        if (StringUtils.isNotBlank(ciServices)) {
            List<CustomMadeCalcServiceDTO> ciList = new ArrayList<>();
            for (String ciService: ciServices.split(",")) {
                if (StringUtils.isBlank(ciService)) {
                    continue;
                }
                CustomMadeCalcServiceDTO ciDTO = new CustomMadeCalcServiceDTO();
                String[] arr = ciService.split("_");
                ciDTO.setName(arr[0]);
                ciDTO.setPrice(new BigDecimal(arr[1]));
                ciList.add(ciDTO);
            }
            dto.setCiServices(ciList);
        }
        dto.setWelfareType(welfareType);
        if (StringUtils.isNotBlank(welfareServices)) {
            List<CustomMadeCalcServiceDTO> welfareList = new ArrayList<>();
            for (String welfareService: welfareServices.split(",")) {
                if (StringUtils.isBlank(welfareService)) {
                    continue;
                }
                CustomMadeCalcServiceDTO welfareDTO = new CustomMadeCalcServiceDTO();
                String[] arr = welfareService.split("_");
                welfareDTO.setName(arr[0]);
                welfareDTO.setPrice(new BigDecimal(arr[1]));
                welfareList.add(welfareDTO);
            }
            dto.setWelfareServices(welfareList);
        }
        dto.setResidentType(residentType);
        if (StringUtils.isNotBlank(residentService)) {
            CustomMadeCalcServiceDTO residentDTO = new CustomMadeCalcServiceDTO();
            String[] arr = residentService.toString().split("_");
            residentDTO.setNum(Integer.parseInt(arr[0]));
            residentDTO.setPrice(new BigDecimal(arr[1]));
            dto.setResidentService(residentDTO);
        }
        if (StringUtils.isNotBlank(otherServices)) {
            List<CustomMadeCalcServiceDTO> otherList = new ArrayList<>();
            for (String otherService: otherServices.split(",")) {
                if (StringUtils.isBlank(otherService)) {
                    continue;
                }
                CustomMadeCalcServiceDTO otherDTO = new CustomMadeCalcServiceDTO();
                otherDTO.setName(otherService);
                otherList.add(otherDTO);
            }
            dto.setOtherServices(otherList);
        }
        dto.setTraditionalHR(traditionalHR);
        dto.setTraditionalCorpCost(traditionalCorpCost);
        dto.setCustomCutsourcing(customCutsourcing);
        dto.setCustomCorpCost(customCorpCost);
        if (!setCustomMadeCalcData(dto)) {
            result.setMsg("保存失败");
            return result;
        }
        return result.setSuccess(true);
    }

    /**
     * 设置数据到mongodb
     *
     * @param dto
     */
    public boolean setCustomMadeCalcData(CustomMadeCalcDTO dto) {
        mongoDao.deleteByPrimaryKey(TABLE_NAME, dto.get_id());
        return mongoDao.insert(TABLE_NAME, JSONObject.toJSONString(dto));
    }

}
