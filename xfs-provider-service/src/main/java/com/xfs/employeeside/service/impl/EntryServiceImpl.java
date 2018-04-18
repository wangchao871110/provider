package com.xfs.employeeside.service.impl;/**
 * @author hongjie
 * @date 2017/3/15.18:10
 */

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.google.common.collect.Lists;
import com.xfs.base.dao.BdBusinessfieldDao;
import com.xfs.base.dao.BsAreaDao;
import com.xfs.base.dao.SysDictionaryDAO;
import com.xfs.base.dao.SysDictitemDAO;
import com.xfs.base.dto.BusinessField;
import com.xfs.base.model.BsArea;
import com.xfs.base.model.SysDictitem;
import com.xfs.business.dao.BdBsareatypeDao;
import com.xfs.business.model.BdBsareatype;
import com.xfs.common.Result;
import com.xfs.corp.model.CmEmployeeConfig;
import com.xfs.corp.service.CmEmployeeConfigService;
import com.xfs.employeeside.service.EntryService;
import com.xfs.sp.dao.SpsSchemeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 入职服务接口
 *
 * @author
 * @create 2017-03-15 18:10
 **/
@Service
public class EntryServiceImpl implements EntryService {
    @Autowired
    private SpsSchemeDao spsSchemeDao;
    @Autowired
    private BsAreaDao bsAreaDao;

    @Autowired
    private BdBusinessfieldDao bdBusinessfieldDao;

    @Autowired
    private SysDictionaryDAO sysDictionaryDAO;
    @Autowired
    private SysDictitemDAO sysDictitemDAO;

    @Autowired
    private BdBsareatypeDao bdBsareatypeDao;

    @Autowired
    CmEmployeeConfigService cmEmployeeConfigService;
    private final String BASEPATH = "EMPLOYEE:";

    private final String AREAID = "AREAID:";

    private final String BSTYPEID = "BSTYPEID:";

    @Override
    public Result getCityList(Integer cpId) {
        Result result = Result.createResult().setSuccess(true);
        List<Integer> spsSchemeList = spsSchemeDao.findSchemeListByCpId(cpId);
        List<BsArea> areaList = new ArrayList<>();
        List<Integer> areaIds= getEmpCityList(cpId);
        for (Integer areaId : spsSchemeList) {
            if (areaIds.contains(areaId)){
                BsArea bsArea = bsAreaDao.getBsAreaListByAreaId(areaId);
                areaList.add(bsArea);
            }
        }
        result.setDataValue("bsArea", areaList);
        return result;
    }

    /**
     * @Title: 根据公司id 获取开通了 员工端的 城市列表
     * @param   
     * @return 
     * @createDate 2017/6/21 16:07
     * @Auther:zhanghongjie【hongjievip6688@163.com】
     * @version        : v1.0
     * @updateDate    	: 
     * @updateAuthor  :
    */
    private List<Integer> getEmpCityList(Integer cpId) {
        List<Integer> areaIds = Lists.newArrayList();
        CmEmployeeConfig config = new CmEmployeeConfig();
        config.setIsEmp(1);
        config.setDr(0);
        config.setCpId(cpId);
        List<CmEmployeeConfig> cmEmployeeConfigs = cmEmployeeConfigService.findAll(config);
        if (CollectionUtils.isNotEmpty(cmEmployeeConfigs)) {
            for (CmEmployeeConfig cmEmployeeConfig : cmEmployeeConfigs) {
                areaIds.add(cmEmployeeConfig.getAreaId());
            }
        }
        return areaIds;
    }

    @Override
    public Result getNeedField(Integer areaId, Integer bsTypeId) {
        Result result = Result.createResult().setSuccess(true);
        Integer[] types = new Integer[1];
        types[0] = bsTypeId;
        //   if (null == RedisUtil.get(BASEPATH + AREAID + areaId + BSTYPEID + bsTypeId)) {
        List<BusinessField> bdBusinessfieldList = bdBusinessfieldDao.queryBusinessFields(areaId, types);
        // 户口性质 证件号码 证件类型   这些字段必须都要显示
        //  insuranceLiveType  identity_code    identity_type
        for (BusinessField businessField : bdBusinessfieldList) {
            if (businessField.getDefaultValue() == null) {
                businessField.setDefaultValue("");
            }
            if (isNeedShow(businessField.getCode())) {
                businessField.setPageIsHidden("SHOW");
            }
            if (isNeedHide(businessField.getCode())) {
                businessField.setPageIsHidden("HIDDEN");
                businessField.setRequired("N");
            }
            if (businessField.getType().equals("PULL")) {
                List<SysDictitem> sysDictitemList = sysDictitemDAO.findByDictNameAndArea(businessField.getCode() + "_doc", areaId);
                businessField.setOptions(sysDictitemList);
            }
        }
        result.setDataValue("businessfield", bdBusinessfieldList);
        return result;
    }

    String[] items = {"insuranceLiveType", "identity_code", "identity_type"};

    private boolean isNeedShow(String item) {
        for (String str : items) {
            if (str.equals(item)) {
                return true;
            }
        }
        return false;
    }

    String[] hiddenItems = {"beginDate", "endDate", "fundSalary", "insuranceSalary"};

    private boolean isNeedHide(String item) {
        for (String str : hiddenItems) {
            if (str.equals(item)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public Result getInsuranceType(Integer areaId) {

        Result result = Result.createResult().setSuccess(true);
        BdBsareatype bsareatype = new BdBsareatype();
        bsareatype.setAreaId(areaId);
        List insuranceType = new ArrayList();
        List<Map<String, Object>> tempMap = bdBsareatypeDao.findBstypeByCity(bsareatype);
        for (Map<String, Object> map : tempMap) {
            Map resultMap = new HashMap();
            if (map.containsKey("bstype_id")
                    && (map.get("bstype_id").toString().equals("2") ||
                    map.get("bstype_id").toString().equals("3"))) {
                resultMap.put("bstype_id", map.get("bstype_id").toString());
                resultMap.put("name", map.get("name").toString());
                insuranceType.add(resultMap);
            }
        }
        // 对应 前端显示
        Collections.reverse(insuranceType);
        result.setDataValue("insuranceType", insuranceType);
        return result;
    }
}
