package com.xfs.activity.service;

import java.util.List;
import java.util.Map;

import com.xfs.activity.model.MoonUserTicket;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * MoonUserTicketService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/06 11:20:21
 */
public interface MoonUserTicketService {

	public int save(ContextInfo cti, MoonUserTicket vo);
	public int insert(ContextInfo cti, MoonUserTicket vo);
	public int remove(ContextInfo cti, MoonUserTicket vo);
	public int update(ContextInfo cti, MoonUserTicket vo);
	public PageModel findPage(PageInfo pi, MoonUserTicket vo);
	public List<MoonUserTicket> findAll(MoonUserTicket vo);


	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/06 11:20:21
	
	/**
	 * 
	* @Title: updateState 
	* @Description: 保存中奖活动状态 
	* @param @param vo
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int updateState(ContextInfo cti, MoonUserTicket vo);
	
	/**
	 * 
	* @Title: insertUser 
	* @Description: 保存中奖后的用户
	* @param @param vo
	* @param @param code1
	* @param @param code2
	* @param @param code3
	* @param @param code4
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int insertUser(ContextInfo cti, MoonUserTicket vo, String code1, String code2, String code3, String code4);
	/**
	 * 获取用户优惠券列表
	 *
	 * @param userId
	 * @param ticketType
	 * @return
	 */
	public List<Map<String, Object>> findMoonUserTickets(Integer userId, String ticketType);
}
