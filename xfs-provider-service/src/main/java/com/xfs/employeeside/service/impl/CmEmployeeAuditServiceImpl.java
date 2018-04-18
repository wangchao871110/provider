package com.xfs.employeeside.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.base.dao.BsSysStateReportDao;
import com.xfs.base.dao.SysMessageDao;
import com.xfs.base.model.BsSysStateReport;
import com.xfs.base.model.SysMessage;
import com.xfs.common.ContextInfo;
import com.xfs.common.IContextInfo;
import com.xfs.common.redies.keychain.IRedisKeys;
import com.xfs.employeeside.dao.CmEmployeeAuditDao;
import com.xfs.employeeside.model.CmEmployeeAudit;
import com.xfs.employeeside.service.CmEmployeeAuditService;


/**
 * CmEmployeeAuditServiceImpl 服务层接口实现类
 *
 * @date:2017/03/20 10:34:21
 */
@Service
public class CmEmployeeAuditServiceImpl implements CmEmployeeAuditService, IRedisKeys {

    @Autowired
    private SysMessageDao sysMessageDao;

    @Autowired
    private CmEmployeeAuditDao cmEmployeeAuditDao;

    @Autowired
    private BsSysStateReportDao bsSysStateReportDao;

    @Override
    public int countFindAll() {
        return cmEmployeeAuditDao.countFindAll();
    }

    @Override
    public List<CmEmployeeAudit> freeFind(CmEmployeeAudit obj) {
        return cmEmployeeAuditDao.freeFind(obj);
    }

    @Override
    public int countFreeFind(CmEmployeeAudit obj) {
        return cmEmployeeAuditDao.countFreeFind(obj);
    }

    @Override
    public CmEmployeeAudit findByPK(CmEmployeeAudit obj) {
        return cmEmployeeAuditDao.findByPK(obj);
    }

    @Override
    public int insert(ContextInfo info, CmEmployeeAudit obj) {

        int res = cmEmployeeAuditDao.insert(info, obj);
        return res;
    }

    public SysMessage sendMessage(ContextInfo cti, CmEmployeeAudit obj) {
    	SysMessage sysMessage = new SysMessage();
        try {
            CmEmployeeAudit employeeAudit = cmEmployeeAuditDao.findByPK(obj);
            sysMessage.setContent("员工入职");
            sysMessage.setTitle("来自「" + employeeAudit.getName() + "」的参保申请");
            sysMessage.setState("TODO");
            sysMessage.setTodoUserType("CUSTOMER");
            sysMessage.setTodoOrg(employeeAudit.getCpId());
            sysMessage.setSendUserType("EMPLOYEE");
            sysMessage.setSendOrg(employeeAudit.getCpId());
            sysMessage.setType("HELPER");

            sysMessage.setDataId(employeeAudit.getEmpId());
            sysMessage.setDr(0);
            sysMessage.setTodoUser(employeeAudit.getUserId()==null?null:employeeAudit.getUserId());

            List<SysMessage> datas = sysMessageDao.freeFind(sysMessage);
            if (datas.size()==0){
                sysMessage.setSendTime(new Date());
                sysMessageDao.insert(cti, sysMessage);
                BsSysStateReport bsSysStateReport = new BsSysStateReport();
                bsSysStateReport.setCpId(obj.getCpId());
                bsSysStateReport.setAreaId(obj.getAreaId());
                bsSysStateReport.setDr(0);
                bsSysStateReport.setOwnerType("CUSTOMER");
                bsSysStateReport.setAttributeName("JIONUSERMESSAGE");
                bsSysStateReport.setAuthority("ALL");
                List<BsSysStateReport> stateReports = bsSysStateReportDao.findIsAlert(bsSysStateReport);
                if (CollectionUtils.isNotEmpty(stateReports)) {
                    bsSysStateReport = stateReports.get(0);
                    bsSysStateReport.setAttributeValue("1");
                    bsSysStateReportDao.updateVersionMessage(cti, bsSysStateReport);
                } else {
                    bsSysStateReport = new BsSysStateReport();
                    bsSysStateReport.setCpId(obj.getCpId());
                    bsSysStateReport.setAreaId(obj.getAreaId());
                    bsSysStateReport.setDr(0);
                    bsSysStateReport.setOwnerType("CUSTOMER");
                    bsSysStateReport.setAttributeName("JIONUSERMESSAGE");
                    bsSysStateReport.setAttributeValue("1");
                    bsSysStateReportDao.insertVersionMessage(cti, bsSysStateReport);
                }
            }else{
            	sysMessage = datas.get(0);
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
        return sysMessage;
    }

    @Override
    public int updateOpenIdByOpenId(IContextInfo cti, CmEmployeeAudit obj) {
        return 0;
    }

    @Override
    public int update(ContextInfo info, CmEmployeeAudit obj) {
        return cmEmployeeAuditDao.update(info, obj);
    }

    @Override
    public int remove(ContextInfo info, CmEmployeeAudit obj) {
        return cmEmployeeAuditDao.remove(info, obj);
    }

    @Override
    public CmEmployeeAudit getCmEmployeeAuditByOpenId(String openId) {
        return cmEmployeeAuditDao.getCmEmployeeAuditByOpenId(openId);
    }


    public CmEmployeeAudit getCmEmployeeAuditByLastFour(CmEmployeeAudit obj){

        return cmEmployeeAuditDao.findByFree(obj);
    }






}
