package com.xfs.employeeside.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.xfs.base.service.SysMessageService;
import com.xfs.business.dao.SpsTaskHistoryDao;
import com.xfs.business.enums.BsType;
import com.xfs.business.enums.TaskStateFiled;
import com.xfs.business.model.SpsTaskHistory;
import com.xfs.common.ContextInfo;
import com.xfs.employeeside.dao.SpsTaskDao;
import com.xfs.employeeside.model.CmEmployeeAudit;
import com.xfs.employeeside.model.SpsTask;
import com.xfs.employeeside.service.SpsTaskService;
import com.xfs.user.model.SysRole;
import com.xfs.user.model.SysUser;
import com.xfs.user.model.SysUserRole;
import com.xfs.user.service.SysUserRoleService;
import com.xfs.user.service.SysUserService;
import com.xfs.wx.message.dto.NewText;
import com.xfs.wx.message.dto.NewTextMessage;
import com.xfs.wx.message.dto.WxMessage;

/**
 * SpsTaskServiceImpl 服务层接口实现类
 *
 * @auth zhanghj@xinfushe.com
 * @date:2017/03/13 14:37:25
 */
@Service
public class SpsTaskServiceImpl implements SpsTaskService {

    @Autowired
    private SpsTaskDao spsTaskDao;
    @Autowired
    private SpsTaskHistoryDao spsTaskHistoryDao;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysMessageService sysMessageService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public int countFindAll() {
        return spsTaskDao.countFindAll();
    }

    @Override
    public List<SpsTask> freeFind(SpsTask obj) {
        return spsTaskDao.freeFind(obj);
    }

    @Override
    public int countFreeFind(SpsTask obj) {
        return spsTaskDao.countFreeFind(obj);
    }

    @Override
    public SpsTask findByPK(SpsTask obj) {
        return spsTaskDao.findByPK(obj);
    }

    @Override
    public int insert(ContextInfo cti, SpsTask obj) {
        return spsTaskDao.insert(cti, obj);
    }

    @Override
    public int update(ContextInfo cti, SpsTask obj) {
        return spsTaskDao.update(cti, obj);
    }

    @Override
    public int remove(ContextInfo cti, SpsTask obj) {
        return spsTaskDao.remove(cti, obj);
    }

	@Override
	public int addEntryTask(ContextInfo cti,CmEmployeeAudit cmEmployeeAudit, Integer messageId) {
        SpsTask spsTask = new SpsTask();
        spsTask.setCpId(cmEmployeeAudit.getCpId());
        spsTask.setSpId(-999);
        spsTask.setBstypeId(BsType.INCREMENT_INSUR_ENTRY.getCode());
        spsTask.setEmpId(null != cti.getUserId() ? cti.getUserId() : cmEmployeeAudit.getEmpId());
        spsTask.setType(TaskStateFiled.TODO_DSH_APPLICATION.getTaskType());
        spsTask.setDr(0);
        List<SpsTask> spsTasks = spsTaskDao.freeFind(spsTask);
        if(spsTasks.size() == 0){
        	spsTask.setStateFiledId(TaskStateFiled.TODO_DSH_APPLICATION.getStateFiledId());
            spsTask.setStateFiledName(TaskStateFiled.TODO_DSH_APPLICATION.getStateFiledName());
            JSONObject jsonObject = JSONObject.parseObject(cmEmployeeAudit.getJson());
            jsonObject.put("message_id", messageId);
            jsonObject.put("audit_id", cmEmployeeAudit.getEmpId());
            // 判断是否工作过  1：未工作过属于新参； 0：工作过属于转入
            if (cmEmployeeAudit.getIsWorked() == 1) {
                jsonObject.put("bstypeId", BsType.NEW_INSURANCE.getCode());
            } else if (cmEmployeeAudit.getIsWorked() == 0) {
                jsonObject.put("bstypeId", BsType.INTO_INSURANCE.getCode());
            }
            jsonObject.put("insuranceFundDate", cmEmployeeAudit.getInsuranceFundDate());
            jsonObject.put("userId", null != cti.getUserId() ? cti.getUserId() : cmEmployeeAudit.getEmpId());
            spsTask.setJson(jsonObject.toJSONString());
            spsTask.setAreaId(cmEmployeeAudit.getAreaId());
            spsTask.setName(cmEmployeeAudit.getName());
            spsTask.setMobile(cmEmployeeAudit.getMobile());
            spsTask.setIdentityCode(cmEmployeeAudit.getIdentityCode());
            spsTask.setDr(0);
            spsTask.setEmpId(null != cti.getUserId() ? cti.getUserId() : cmEmployeeAudit.getEmpId());
            int flag = spsTaskDao.insert(cti, spsTask);
            if(flag > 0){
            	SysUser sysUser = new SysUser();
                sysUser.setUserType("CUSTOMER");
                sysUser.setOrgId(cti.getOrgId());
                List<SysUser> sysUsers = sysUserService.findAll(sysUser);
                List<WxMessage> messagelist = new ArrayList<>();
                NewTextMessage textMessage = null;
                for(SysUser u : sysUsers){
                	List<SysUserRole> sysUserRoles = sysUserRoleService.findUserRole(u.getUserId());
                	boolean isExits = false;
                	for(SysUserRole userRole : sysUserRoles){
                		if(SysRole.CUSTOMER_SUPER_ROLE_ID == userRole.getRoleId() 
                    			|| SysRole.CUSTOMER_BUSSINES_BOSS == userRole.getRoleId() 
                    			|| SysRole.CUSTOMER_BUSSINES_MANAGER == userRole.getRoleId()){
                            isExits = true;
                        }
                	}
                	if(isExits && StringUtils.isNotBlank(u.getCoreUserId())){
	            		// 给HR发信息收集消息
                		textMessage = new NewTextMessage();
                		textMessage.setTouser(u.getCoreUserId());
                		NewText newText = new NewText();
						newText.setContent("您有一条新任务，"+spsTask.getName()+"的参保任务，请及时登录社保后台到任务中心进行处理！");
						textMessage.setText(newText);
						messagelist.add(textMessage);
                	}
                }
                // 消息推送
				if(messagelist.size() > 0){
					System.out.println("============="+messagelist.toString());
					String  mString = sysMessageService.sendMessage(messagelist,"xfs_ss");
					System.out.println("============="+mString);
				}
            	//记录历史任务单
                //spstask表更新成功 写入历史记录表一条
                SpsTaskHistory spsTaskHistory = new SpsTaskHistory();
                BeanUtils.copyProperties(spsTask, spsTaskHistory);
                spsTaskHistory.setOperate("将任务单状态处理为" + spsTask.getType());
                spsTaskHistory.setDr(0);
                flag = spsTaskHistoryDao.insert(cti, spsTaskHistory);
                return flag;
            }
        }
        return -1;
	}
}
