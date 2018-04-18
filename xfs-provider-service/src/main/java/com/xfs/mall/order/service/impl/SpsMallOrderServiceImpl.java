package com.xfs.mall.order.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.bs.dto.AppraiseOrder;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.common.redies.RedisUtil;
import com.xfs.common.util.DateUtil;
import com.xfs.insurance.dto.CiOrder;
import com.xfs.insurance.dto.MyCiOrder;
import com.xfs.insurance.dto.MyOrder;
import com.xfs.mall.order.dao.SpsMallOrderDao;
import com.xfs.mall.order.model.SpsMallOrder;
import com.xfs.mall.order.service.SpsMallOrderService;
import com.xfs.sp.model.CmContract;
import com.xfs.sp.service.CmContractService;
import com.xfs.user.model.SysRole;
import com.xfs.user.service.SysUserRoleService;

@Service
public class SpsMallOrderServiceImpl implements SpsMallOrderService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsMallOrderServiceImpl.class);
	
	@Autowired
	private SpsMallOrderDao spsMallOrderDao;
	
	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	@Autowired
	private CmContractService cmContractService;

	public int save(ContextInfo cti, SpsMallOrder vo ){
		return spsMallOrderDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsMallOrder vo ){
		return spsMallOrderDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsMallOrder vo ){
		return spsMallOrderDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsMallOrder vo ){
		return spsMallOrderDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsMallOrder vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsMallOrderDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsMallOrder> datas = spsMallOrderDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsMallOrder> findAll(SpsMallOrder vo){
		List<SpsMallOrder> datas = spsMallOrderDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/15 16:41:09
    /**
     * 查询企业订单列表
     * @param spName
     * @param spsMallOrder
     * @return
     */
    @Override
    public PageModel queryAppraiseOrderList(PageInfo pi, String spName, SpsMallOrder spsMallOrder) {
        PageModel pm = new PageModel(pi);
        Integer pageIndex = pi.getOffset();
        Integer pageSize = pi.getPagesize();
        Integer total = spsMallOrderDao.queryAppraiseOrderListCount(spName, spsMallOrder);
        pm.setTotal(total);
        List<AppraiseOrder> datas = spsMallOrderDao.queryAppraiseOrderList(spName, spsMallOrder, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;
    }

    @Override
    public SpsMallOrder findByOrderId(SpsMallOrder spsMallOrder) {
        List<SpsMallOrder> list = spsMallOrderDao.freeFind(spsMallOrder);
        if (list.size() > 0)
            return list.get(0);
        return null;
    }

	@Override
	public PageModel findCiOrderPage(PageInfo pi, CiOrder ciOrder) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsMallOrderDao.countCiOrder(ciOrder);
		pm.setTotal(total);
		List<CiOrder> datas = spsMallOrderDao.queryCiOrder(ciOrder, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}
	@Override
	public PageModel queryorderList(PageInfo pi, Map map) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsMallOrderDao.countMallOrder(map);
		pm.setTotal(total);
		List<Map<String, Object>> datas = spsMallOrderDao.queryMallOrder(map, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}
	@Override
	public Map<String, Object> queryLIST(Map map) {
		Map<String,Object> datas = spsMallOrderDao.queryLIST(map);
		return datas;
	}
	@Override
	public PageModel queryitem(PageInfo pi, Map map) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsMallOrderDao.countitem(map);
		pm.setTotal(total);
		List<Map<String, Object>> datas = spsMallOrderDao.queryitem(map, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}
	
	
	/**
	 * 查询企业订单列表
	 * @param spName
	 * @param spsMallOrder
     * @return
     */
	@Override
	public PageModel queryMyOrderList(PageInfo pi, String spName, SpsMallOrder spsMallOrder) {
		PageModel pm = new PageModel(pi);
		Integer pageIndex = pi.getOffset();
		Integer pageSize = pi.getPagesize();
		Integer total = spsMallOrderDao.queryMyOrderListCount(spName, spsMallOrder);
		pm.setTotal(total);
		List<MyOrder> datas = spsMallOrderDao.queryMyOrderList(spName, spsMallOrder, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}

	/**
	 * 获取订单号
	 *
	 * @return
	 */
	public String getOrderNo() {
		return getOrderNo("sps_mall_order_");
	}

	/**
	 * 获取订单号
	 *
	 * @return
	 */
	public String getOrderNo(String key) {
		String day = DateUtil.getDateStr(new Date(), "yyyyMMdd");
		Object no = RedisUtil.get(key + day);
		if (no == null) {
			no = day + "000";
		}
		Long orderNo = Long.parseLong(no.toString()) + 1;
		RedisUtil.set(key + day, orderNo, 60 * 60 * 24); // 一天的有效期
		return orderNo.toString();
	}

    @Override
    public MyOrder getOrderDetail(SpsMallOrder spsMallOrder) {
        return spsMallOrderDao.findOrderById(spsMallOrder);
    }

	/**
	 * 获取商保订单详情
	 * @param spsMallOrder
	 * @return
     */
	public MyCiOrder getCiOrderDetail(SpsMallOrder spsMallOrder){
		MyCiOrder ciOrder = spsMallOrderDao.findCiOrderById(spsMallOrder);
		if(ciOrder == null){
			ciOrder = new MyCiOrder();
		}
		ciOrder.setPayType("月缴");
		ciOrder.setGuaranteePeriod("一年");
		ciOrder.setCorpLicenseType("企统一社会信用代码");
		return  ciOrder;
	}
	
	
	/**
	 * 自定义分页查询
	 *
	 * @author lifq
	 *
	 * 2016年6月8日  下午7:10:14
	 */
	public PageModel customfindPage(PageInfo pi, SpsMallOrder vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsMallOrderDao.customCountFreeFind(vo);
		pm.setTotal(total);
		List<SpsMallOrder> datas = spsMallOrderDao.customFreeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	/**
	 * 根据orderId查询 订单及产品信息
	 *
	 * @author lifq
	 *
	 * 2016年6月8日  下午8:13:46
	 */
	public SpsMallOrder findOrderByOrderId(SpsMallOrder obj) {
		return spsMallOrderDao.findOrderByOrderId(obj);
	}
	
	/**
	 * 查询 订单明细 项目信息
	 *
	 * @author lifq
	 *
	 * 2016年6月11日  下午1:13:46
	 */
	public List<Map<String, Object>> findOrderItems(SpsMallOrder vo){
		return spsMallOrderDao.findOrderItems(vo);
	}
	
	/**
	 * @throws Exception 
	 * 
	* @Title: updateState 
	* @Description: 通过审核更改订单状态及合同状态
	* @param @param request
	* @param @param vo
	* @param @return    设定文件 
	* @return Result    返回类型 
	* @throws
	 */
	public Result updateState(ContextInfo cti, HttpServletRequest request, SpsMallOrder vo) {
		Result result = Result.createResult().setSuccess(false);
		if ("SIGN".equals(vo.getOrderState()) 
				&& "WAITSIGN".equals(vo.getOrderState())) {
			result.setError("请正确审核");
			return result;
		}
		CmContract cc = new CmContract();
		cc.setState("USE");
		cc.setOrderId(vo.getOrderId());
		List<CmContract> cmContracts = cmContractService.findAll(cc);
		if (cmContracts == null || cmContracts.size() != 1) {
			result.setError("合同不存在");
			return result;
		}
		cc.setContractId(cmContracts.get(0).getContractId());
		cc.setSpId(cmContracts.get(0).getSpId());
		cc.setCpId(cmContracts.get(0).getCpId());
		if ("SIGN".equals(vo.getOrderState())) {
			cc.setContractState(3);
		} else if("WAITSIGN".equals(vo.getOrderState())) {
			cc.setContractState(1);
		}
		try {
			cmContractService.signContract(cti, cc, vo, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
