package com.xfs.cp.service.impl;

import static com.xfs.common.redies.keychain.IRedisKeys.COMMON_XFS_MESSAGE_QUEUE;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xfs.common.constant.IStaticVarConstant;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.base.model.SysMessage;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.common.redies.RedisUtil;
import com.xfs.common.sms.SmsUtil;
import com.xfs.common.util.TransactionUtil;
import com.xfs.cp.dao.CpOrderDao;
import com.xfs.cp.dao.CpOrderLogDao;
import com.xfs.cp.dto.SaaSCpBillDto;
import com.xfs.cp.model.CpOrder;
import com.xfs.cp.model.CpOrderLog;
import com.xfs.cp.model.CpRelation;
import com.xfs.cp.service.CpOrderService;
import com.xfs.cp.service.CpRelationService;
import com.xfs.enums.OrderStatusType;
import com.xfs.enums.OrderType;
@Service
public class CpOrderServiceImpl implements CpOrderService,IStaticVarConstant {

	private static final Logger log = Logger.getLogger(CpOrderServiceImpl.class);
	@Autowired
	private CpOrderDao cpOrderDao;
	@Autowired
	private CpOrderLogDao cpOrderLogDao;
	@Autowired
	private CpRelationService cpRelationService;

	public int save(ContextInfo cti, CpOrder vo ){
		return cpOrderDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  CpOrder vo ){
		return cpOrderDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  CpOrder vo ){
		return cpOrderDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  CpOrder vo ){
		return cpOrderDao.update(cti, vo);
	}
	public int updateByOrderId(ContextInfo cti,  CpOrder vo ){
		return cpOrderDao.updateByOrderId(cti, vo);
	}
	public PageModel findPage(PageInfo pi, CpOrder vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cpOrderDao.countFreeFind(vo);
		pm.setTotal(total);
		List<CpOrder> datas = cpOrderDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<CpOrder> findAll(CpOrder vo){
		
		List<CpOrder> datas = cpOrderDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 19:24:41
	@Override
	public PageModel findASpOrder(PageInfo pi,CpOrder vo){
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = 10;
		Integer total = cpOrderDao.findASpOrderCount(vo);
		pm.setTotal(total);
		List<CpOrder> datas = cpOrderDao.findASpOrder(vo, pageIndex, pageSize);
		for(CpOrder c : datas){
			CpOrderLog logVo = new CpOrderLog();
			logVo.setCpOrderId(c.getOrderId());
			logVo.setStage(c.getStage());
			List<CpOrderLog> logs = cpOrderLogDao.freeFind(logVo);
			if(logs!=null&&logs.size()>0){
				c.setCurrentStatus(logs.get(0).getStatus());
			}
		}
		pm.setDatas(datas);
		return pm;

	}

	@Override
	public PageModel findBSpOrder(PageInfo pi,CpOrder vo){
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = 10;
		Integer total = cpOrderDao.findBSpOrderCount(vo);
		pm.setTotal(total);
		List<CpOrder> datas = cpOrderDao.findBSpOrder(vo, pageIndex, pageSize);
		for(CpOrder c : datas){
			CpOrderLog logVo = new CpOrderLog();
			logVo.setCpOrderId(c.getOrderId());
			logVo.setStage(c.getStage());
			List<CpOrderLog> logs = cpOrderLogDao.freeFind(logVo);
			if(logs!=null&&logs.size()>0){
				c.setCurrentStatus(logs.get(0).getStatus());
			}
		}
		pm.setDatas(datas);
		return pm;
	}

	@Override
	public CpOrder findByOrderId(CpOrder obj) {
		return cpOrderDao.findByOrderId(obj);
	}

    /**
     * 服务商查询自己未完成且未查看的派/接单任务的数量
     * @since 2016-10-19 luyong update
     * @param sysUser
     * @return
     */
	@Override
	public Integer getCoopTipCount(ContextInfo sysUser) {
		Map<String,Integer> asp = getActOrderMapByUser(null,sysUser,true);
		Map<String,Integer> bsp = getActOrderMapByUser(null,sysUser,false);
        /*CpOrder vo = new CpOrder();
        vo.setASpId(user.getOrgId());
        vo.setBSpId(user.getOrgId());*/
		return asp.size() + bsp.size();
	}

	@Override
	public Map<String,Integer> getActOrderMapByUser(Integer relationId, ContextInfo user, boolean isASp) {
		CpOrder vo = new CpOrder();
		if(isASp){
			vo.setASpId(user.getOrgId());
		}else{
			vo.setBSpId(user.getOrgId());
		}
		if(relationId!=null){
			vo.setCpRelationId(relationId);
		}
		List<CpOrder> list = cpOrderDao.findNotFinishOrder(vo);
		Map<String,Integer> ret = new HashMap<>(0);
		for(CpOrder o : list){
			if(isASp){
				if(o.getCurrentStatus().intValue() == OrderStatusType.CREATE_ORDER_EDIT.getValue()
					|| o.getCurrentStatus().intValue() == OrderStatusType.WAIT_BILL_SEND.getValue()
					|| o.getCurrentStatus().intValue() == OrderStatusType.WAIT_VOUCHER_SEND.getValue()){
					ret.put(o.getOrderId(),o.getCpRelationId());
				}
			}else{
				if(o.getCurrentStatus().intValue() == OrderStatusType.WAIT_BILL_EDIT.getValue()
						|| o.getCurrentStatus().intValue() == OrderStatusType.WAIT_VOUCHER_EDIT.getValue()
						|| o.getCurrentStatus().intValue() == OrderStatusType.WAIT_RESULT_EDIT.getValue()){
					ret.put(o.getOrderId(),o.getCpRelationId());
				}
			}
		}
		return ret;
	}



	@Override
	public CpOrder findOrderNumByBSpId(Integer id) {
		CpOrder vo = new CpOrder();
		vo.setBSpId(id);
		return cpOrderDao.findOrderNumByBSpId(vo);
	}

    @Override
    public PageModel FindManage(PageInfo pi,CpOrder vo) {
        vo.setIspay(1);
        vo.setCurrentStatus(OrderStatusType.WAIT_VOUCHER_SEND.getValue());
        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = cpOrderDao.FindManageCount(vo);
        pm.setTotal(total);
        List<CpOrder> cpOrders=cpOrderDao.FindManage(vo,pageIndex,pageSize);
        pm.setDatas(cpOrders);
        return pm;
    }

    @Override
    public Map<String, java.lang.Object> queryExpAmount(CpOrder cpOrder) {
        return cpOrderDao.queryExpAmount(cpOrder);
    }

    @Override
    public Map<String, java.lang.Object> queryPayAmount(CpOrder cpOrder) {
        return cpOrderDao.queryPayAmount(cpOrder);
    }

    @Override
    public Map<String, java.lang.Object> queryNotPayAmount(CpOrder cpOrder){
        return cpOrderDao.queryNotPayAmount(cpOrder);
    }

    @Override
    public boolean paySpExpAmount(PageInfo pi,ContextInfo cti, CpOrder cpOrder1,Result result,Integer user_id,String sms) {
        CpOrder cpOrder2 = new CpOrder();
        cpOrder2.setOrderId(cpOrder1.getOrderId());
        cpOrder2.setCurrentStatus(OrderStatusType.WAIT_VOUCHER_PAY.getValue());
        PageModel pm = FindManage(pi,cpOrder2);
        CpOrder cpOrder = (CpOrder)pm.getDatas().get(0);
        Date now = new Date();
        cpOrder.setPleaseBy(user_id);
        cpOrder.setPleaseAuditDt(now);
        cpOrder.setPleaseContent(cpOrder1.getPleaseContent());
        cpOrder.setPleaseStatus(cpOrder1.getPleaseStatus());
        int i = cpOrderDao.update(cti,cpOrder);
        if(i>0){
        	sendSysMessage(cpOrder, user_id, 1, "");
        	return true;
        }
        return false;
    }



    /**
     * 发送请款成系统消息
     */
    private void sendSysMessage(CpOrder curr_bill,Integer user_id,Integer pay,String expend){
        try{
            SmsUtil.sendSms(curr_bill.getBmobile(),"【用友薪福社】尊敬的客户您好，用友薪福社已将来自合作伙伴"+curr_bill.getCsaname()+"服务机构的钱打到您的账户，请查收。");
            SysMessage sysMessage = new SysMessage();
            sysMessage.setContent("【用友薪福社】尊敬的客户您好，用友薪福社已将来自合作伙伴"+curr_bill.getCsaname()+"服务机构的钱打到您的账户，请查收。");
            sysMessage.setTitle("【用友薪福社】尊敬的客户您好，用友薪福社已将来自合作伙伴"+curr_bill.getCsaname()+"服务机构的钱打到您的账户，请查收。");
            sysMessage.setState("TODO");
            sysMessage.setTodoUserType(CMCORPTYPE_SERVICE);
            sysMessage.setTodoOrg(curr_bill.getBSpId());
            sysMessage.setSendUser(user_id);
            sysMessage.setSendUserType("BUSINESS");
            sysMessage.setType("pleaseStatus");
            sysMessage.setSendOrg(curr_bill.getBSpId());
            sysMessage.setDataId(curr_bill.getBSpId());
            sysMessage.setSendTime(new Date());
            RedisUtil.push(COMMON_XFS_MESSAGE_QUEUE,sysMessage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //收款
    @Override
    public Map<String, java.lang.Object> queryVoucherOffLineAmount(CpOrder cpOrder) {
        return cpOrderDao.queryVoucherOffLineAmount(cpOrder);
    }



    @Override
    public Map<String, java.lang.Object> queryVoucherOnlineAmount(CpOrder cpOrder) {
        return cpOrderDao.queryVoucherOnLineAmount(cpOrder);
    }

    /**
     * 查询支付凭证列表
     * @param vo
     * @return
     */
    @Override
    public PageModel queryVoucherList(PageInfo pi,CpOrder vo) {
        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = cpOrderDao.queryVoucherCount(vo);
        pm.setTotal(total);
        List<CpOrder> cpOrders = cpOrderDao.queryVoucherList(vo, pageIndex, pageSize);
        pm.setDatas(cpOrders);
        return pm;
    }
    
    @Override
    public CpOrder queryVoucherInfoById(CpOrder vo) {
        return cpOrderDao.findByPK(vo);
    }

	@Override
	public CpOrder findByOrderId(String orderId) {
		CpOrder cpOrder = new CpOrder();
		cpOrder.setOrderId(orderId);
		return cpOrderDao.findByOrderId(cpOrder);
	}
	@Override
	public void sendMessage(CpOrder order) {
		try {
			SmsUtil.sendSms(order.getAmobile(),"【用友薪福社】尊敬的客户您好，用友薪福社已收到您的来款。");
			SysMessage sysMessage = new SysMessage();
	        sysMessage.setContent("【用友薪福社】尊敬的客户您好，用友薪福社已收到您的来款。");
	        sysMessage.setTitle("【用友薪福社】尊敬的客户您好，用友薪福社已收到您的来款。");
	        sysMessage.setState("TODO");
	        sysMessage.setTodoUserType(CMCORPTYPE_SERVICE);
	        sysMessage.setTodoOrg(order.getASpId());
	        sysMessage.setSendUser(order.getASpId());
	        sysMessage.setSendUserType("BUSINESS");
	        sysMessage.setType("pleaseStatus");
	        sysMessage.setSendTime(new Date());
	        RedisUtil.push(COMMON_XFS_MESSAGE_QUEUE,sysMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

    public List<Map<String, java.lang.Object>> findAllNews(CpOrder vo){
        return  cpOrderDao.findAllNews(vo);
    }



	public PageModel findByCpId(PageInfo pi, CpOrder vo){

		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cpOrderDao.countFindByCpId(vo);
		pm.setTotal(total);
		List<CpOrder> datas = cpOrderDao.findByCpId(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;

	}

	/**
	 * SAAS回传账单实现
	 *  @param cti
	 *  @param saaSCpBillDto
	 *  @return
	 *	@return			: Result
	 *  @createDate		: 2016年12月26日 下午4:46:54
	 *  @author			: wangchao
	 *  @version		: v1.0
	 *  @updateDate		: 2016年12月26日 下午4:46:54
	 *  @updateAuthor  	:
	 */
	public Result SaaSCpBill(ContextInfo cti, SaaSCpBillDto saaSCpBillDto){
		Result result = Result.createResult().setSuccess(false);
		result.setMsg("发送账单失败");
		try {
			// 根据发包方和接包方获取所有未上传账单的数据
			CpOrder vo = new CpOrder();
			vo.setASpId(saaSCpBillDto.getASpId());
			vo.setBSpId(saaSCpBillDto.getBSpId());
			List<Map<String, Object>> list = cpOrderDao.findByASpIdAndBSpId(vo);
			String fileId = "";
			Integer cpRelationId = null;
			Integer personNum = 0;
			int num = 0;
			for (int i = 0; i < list.size(); i++) {
				// 订单ID
				Integer id = Integer.parseInt(list.get(i).get("id").toString());
				// 订单编号
				String orderId = list.get(i).get("order_id").toString();
				// 文件ID
				fileId += !"".equals(list.get(i).get("file_id").toString())?(list.get(i).get("file_id").toString()+","):"";
				// 人数
				personNum += Integer.parseInt(list.get(i).get("person_num").toString());
				// 根据ID更新订单为已删除
				vo = new CpOrder();
				vo.setId(id);
				vo.setDr(1);
				cpOrderDao.update(cti, vo);
				// 根据订单更新订单LOG为已删除
				CpOrderLog cpOrderLog = new CpOrderLog();
				cpOrderLog.setCpOrderId(orderId);
				cpOrderLog.setDr(1);
				cpOrderLogDao.updateByCpOrderLogId(cti, cpOrderLog);
			}
			// 生成新的订单
			String orderIdStr = TransactionUtil.getCooperationNumber("order");
			Date newDate = new Date();
			//生成订单信息
			CpOrder order = new CpOrder();
			order.setOrderId(orderIdStr);
			// 关系ID
			CpRelation cpRelation = new CpRelation();
			cpRelation.setASpId(saaSCpBillDto.getASpId());
			cpRelation.setBSpId(saaSCpBillDto.getBSpId());
			cpRelation.setDr(0);
			cpRelationId = cpRelationService.findAll(cpRelation).get(0).getId();
			order.setCpRelationId(cpRelationId);
			order.setASpId(saaSCpBillDto.getASpId());
			order.setBSpId(saaSCpBillDto.getBSpId());
			order.setOperationTime(newDate);
			order.setCreateBy(cti.getUserId());
			order.setCreateDt(newDate);
			order.setModifyBy(cti.getUserId());
			order.setModifyDt(newDate);
			order.setBillNum(saaSCpBillDto.getBillNum());
			order.setPrice(saaSCpBillDto.getPrice());
			order.setServicePrice(saaSCpBillDto.getServicePrice());
			order.setPublicPrice(saaSCpBillDto.getPublicPrice());
			if(list.size() > 0){
				order.setCreateType(1);//创建类型1:派单方创建2:接单方创建
			}else{
				order.setCreateType(2);//创建类型1:派单方创建2:接单方创建
			}
			order.setPersonNum(personNum);
			cpOrderDao.save(cti,order);
			
			//生成 11--创建订单记录log
			CpOrderLog log = new CpOrderLog();
			log.setStatus(OrderStatusType.CREATE_ORDER_EDIT.getValue());
			log.setCpOrderId(order.getOrderId());
			log.setOperationTime(order.getOperationTime());
			log.setCreateBy(cti.getUserId());
			log.setCreateDt(newDate);
			log.setModifyBy(cti.getUserId());
			log.setModifyDt(newDate);
			log.setStage(OrderType.CREATE_ORDER.getValue());
			log.setFileId(!"".equals(fileId)?fileId.substring(0, fileId.lastIndexOf(",")):null);
			cpOrderLogDao.save(cti,log);
			
			//生成 12--创建订单发送log
			log = new CpOrderLog();
			log.setStatus(OrderStatusType.CREATE_ORDER_SEND.getValue());
			log.setCpOrderId(order.getOrderId());
			log.setOperationTime(newDate);
			log.setCreateBy(cti.getUserId());
			log.setCreateDt(newDate);
			log.setModifyBy(cti.getUserId());
			log.setModifyDt(newDate);
			log.setStage(OrderType.CREATE_ORDER.getValue());
			cpOrderLogDao.save(cti,log);
			
			//生成 21--创建账单编辑log
			log = new CpOrderLog();
			log.setStatus(OrderStatusType.WAIT_BILL_EDIT.getValue());
			log.setCpOrderId(order.getOrderId());
			log.setOperationTime(newDate);
			log.setCreateBy(cti.getUserId());
			log.setCreateDt(newDate);
			log.setModifyBy(cti.getUserId());
			log.setModifyDt(newDate);
			log.setStage(OrderType.WAIT_BILL.getValue());
			log.setFileId(saaSCpBillDto.getFileId());
			cpOrderLogDao.save(cti,log);
			
			//生成 22--创建账单发送log
			log = new CpOrderLog();
			log.setStatus(OrderStatusType.WAIT_BILL_SEND.getValue());
			log.setCpOrderId(order.getOrderId());
			log.setOperationTime(newDate);
			log.setCreateBy(cti.getUserId());
			log.setCreateDt(newDate);
			log.setModifyBy(cti.getUserId());
			log.setModifyDt(newDate);
			log.setStage(OrderType.WAIT_BILL.getValue());
			cpOrderLogDao.save(cti,log);
			
			// 更新订单信息
			order.setStage(OrderType.WAIT_BILL.getValue());
			order.setCurrentStatus(OrderStatusType.WAIT_BILL_SEND.getValue());
            order.setASpRead("0");
            order.setBSpRead("1");
	        order.setIsRejected(0);
	        order.setIsViewRejected(1);
	        order.setModifyBy(cti.getUserId());
			order.setModifyDt(newDate);
	        num = cpOrderDao.update(cti,order);
			if(num > 0){
				result.setSuccess(true);
				result.setMsg("账单发送成功！");
			}
		} catch (Exception e) {
			result.setMsg("账单发送产生异常！");
			log.error(e);
		}
		return result;
	}
	//官费，服务费
	@Override
	public Map<String, java.lang.Object> servicePrice(CpOrder cpOrder){
		return cpOrderDao.servicePrice(cpOrder);
	}
}
