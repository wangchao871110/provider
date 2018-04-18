package com.xfs.activity.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.activity.dao.MoonUserTicketDao;
import com.xfs.activity.model.MoonUserTicket;
import com.xfs.activity.service.MoonUserTicketService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

@Service
public class MoonUserTicketServiceImpl implements MoonUserTicketService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(MoonUserTicketServiceImpl.class);
	
	@Autowired
	private MoonUserTicketDao moonUserTicketDao;
	
	public int save(ContextInfo cti, MoonUserTicket vo ){
		return moonUserTicketDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  MoonUserTicket vo ){
		return moonUserTicketDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  MoonUserTicket vo ){
		return moonUserTicketDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  MoonUserTicket vo ){
		return moonUserTicketDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, MoonUserTicket vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = moonUserTicketDao.countFreeFind(vo);
		pm.setTotal(total);
		List<MoonUserTicket> datas = moonUserTicketDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<MoonUserTicket> findAll(MoonUserTicket vo){
		
		List<MoonUserTicket> datas = moonUserTicketDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/06 11:20:21

	public int updateState(ContextInfo cti, MoonUserTicket vo ){
		return moonUserTicketDao.updateState(cti, vo);
	}
	public int insertUser(ContextInfo cti, MoonUserTicket vo ,String code1,String code2,String code3,String code4){
		return moonUserTicketDao.insertAll(cti, vo,code1,code2,code3,code4);
	}
	
	/**
	 * 获取用户优惠券列表
	 *
	 * @param userId
	 * @param ticketType
	 * @return
	 */
	public List<Map<String, Object>> findMoonUserTickets(Integer userId, String ticketType) {
		return moonUserTicketDao.findMoonUserTickets(userId, ticketType);
	}
}
