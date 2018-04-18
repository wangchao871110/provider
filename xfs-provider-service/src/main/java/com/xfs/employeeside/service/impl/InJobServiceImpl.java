package com.xfs.employeeside.service.impl;/**
 * @author hongjie
 * @date 2017/4/7.17:05
 */

import com.xfs.base.dao.BdBusinessfieldDao;
import com.xfs.base.dao.BsAreaDao;
import com.xfs.base.dao.SysDictitemDAO;
import com.xfs.base.dto.BusinessField;
import com.xfs.base.model.BsArea;
import com.xfs.base.model.SysDictitem;
import com.xfs.business.enums.BsType;
import com.xfs.common.Result;
import com.xfs.employeeside.service.InJobService;
import com.xfs.sp.dao.SpsSchemeDao;
import com.xfs.sp.dao.SpsSchemeEmpDao;
import com.xfs.sp.model.SpsScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 在职服务
 *
 * @author
 * @create 2017-04-07 17:05
 **/
@Service
public class InJobServiceImpl implements InJobService {

    @Autowired
    SpsSchemeEmpDao spsSchemeEmpDao;

    @Autowired
    SpsSchemeDao spsSchemeDao;

    @Autowired
    BdBusinessfieldDao bdBusinessfieldDao;

    @Autowired
    private SysDictitemDAO sysDictitemDAO;


    @Autowired
    private BsAreaDao bsAreaDao;

    @Override
    public Result getNeedFieldByBsTypeName(Integer empId, String bsTypeName) {
        Result result = Result.createResult().setSuccess(true);
        // 根据 员工id   获取员工所在区域
        SpsScheme spsScheme = spsSchemeDao.findSchemeByEmpId(empId);
        if (spsScheme == null) {
            result.setError("员工对应的方案不存在！");
            result.setSuccess(true);
            return result;
        }
        Integer areaId = spsScheme.getAreaId();
        Integer[] types = new Integer[1];
        types[0] = BsType.getCodeByName(bsTypeName);
        List<BusinessField> bdBusinessfieldList = bdBusinessfieldDao.queryBusinessFields(areaId, types);
        // 户口性质 证件号码 证件类型   这些字段必须都要显示
        for (BusinessField businessField : bdBusinessfieldList) {
            if (businessField.getDefaultValue() == null) {
                businessField.setDefaultValue("");
            }
            if (businessField.getType().equals("PULL")) {
                List<SysDictitem> sysDictitemList = sysDictitemDAO.findByDictNameAndArea(businessField.getCode() + "_doc", areaId);
                businessField.setOptions(sysDictitemList);
            }
        }
        result.setDataValue("businessfield", bdBusinessfieldList);
        return result;
    }

    @Override
    public Result getWorkGuide(Integer empId, String bsTypeName) {
        Result result = Result.createResult().setSuccess(true);
        // 根据 员工id   获取员工所在区域
        SpsScheme spsScheme = spsSchemeDao.findSchemeByEmpId(empId);
        if (spsScheme == null) {
            result.setError("员工对应的方案不存在！");
            result.setSuccess(true);
            return result;
        }
        Integer areaId = spsScheme.getAreaId();
        // 根据地区id 获取地区名称
        BsArea vo = new BsArea();
        vo.setAreaId(areaId);
        vo = bsAreaDao.findByPK(vo);
        if (vo == null) {
            result.setError("对应的城市不存在！");
            result.setSuccess(true);
            return result;
        }

        // 发起http 调用 请求


        result.setDataValue("businessfield", "");
        return result;

    }
}
