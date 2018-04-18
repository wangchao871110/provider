package com.xfs.base.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xfs.base.model.SysMessage;
import com.xfs.business.model.SpsTask;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.corp.model.CmEmployee;
import com.xfs.msg.dto.UserBusinessVo;
import com.xfs.wx.message.dto.WxMessage;

/**
 * @author xiyanzhang
 * @Email: zhangxiyan@xinfushe.com
 * @version 创建时间：2016年11月10日 下午5:21:25
 */
public interface SysMessageService {
	/**
	 * 消息通知列表short
	 */
	public List<HashMap<String, Object>> findShortPage(ContextInfo cti, SysMessage vo);

	/**
	 * 消息通知列表完整
	 */
	public PageModel findPage(PageInfo pi, SysMessage vo, ContextInfo cti);

	/**
	 * 消息通知列表总数
	 */
	public Integer messageCount(SysMessage vo);

	/**
	 * 阅读消息通知
	 */
	public Result readMessageState(ContextInfo cti, SysMessage vo, String flag);

	public int save(ContextInfo cti, SysMessage vo);

	public int insert(ContextInfo cti, SysMessage vo);

	public int remove(ContextInfo cti, SysMessage vo);

	public int update(ContextInfo cti, SysMessage vo);

	public List<SysMessage> findAll(SysMessage vo);
	
	public SysMessage findByPk(SysMessage vo);

	/**
	 * 查询待办事项
	 * 
	 * @param cti
	 * @return
	 */
	public HashMap<String, Object> messageList(ContextInfo cti);

	/**
	 * 增员业务 社保新参 2、社保转入 3、公积金增加 10
	 * 
	 * @param cti
	 * @return
	 */
	public HashMap<String, Object> insFundAdd(ContextInfo cti, HashMap<String, Object> parameterMap);

	/**
	 * 减员业务 社保减员4 、公积金减少 11
	 * 
	 * @param cti
	 * @return
	 */
	public HashMap<String, Object> insFundReduce(ContextInfo cti, HashMap<String, Object> parameterMap);

	/**
	 * 补缴业务 社保补缴 29、公积金补缴30
	 * 
	 * @param cti
	 * @return
	 */
	public HashMap<String, Object> insFundSupple(ContextInfo cti, HashMap<String, Object> parameterMap);

	/**
	 * 消息通知增加
	 */
	public Result addMessage(ContextInfo cti, SysMessage vo);



    public Map findByVersionMessage(ContextInfo cti);


	/**
	 * 查询薪福加文章消息
	 *  @param   cti, pageInfo, type]
	 * @return    : com.xfs.common.page.PageModel
	 *  @createDate   : 2017/1/10 10:56
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2017/1/10 10:56
	 *  @updateAuthor  :
	 */
	public PageModel findXfjContentMsgPage(ContextInfo cti,PageInfo pageInfo,String type);

	/**
	 * 查询薪福加系统消息
	 *  @param   cti, pageInfo, type
	 * @return    : com.xfs.common.page.PageModel
	 *  @createDate   : 2017/1/10 10:56
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2017/1/10 10:56
	 *  @updateAuthor  :
	 */
	public PageModel findXfjSysMsgPage(ContextInfo cti,PageInfo pageInfo);

	/**
	 * 获取消息数
	 *  @param   message
	 * @return    : java.lang.Integer
	 *  @createDate   : 2017/1/12 15:57
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2017/1/12 15:57
	 *  @updateAuthor  :
	 */
	public Integer countMsgNum(SysMessage message);

	/**
	 * 查询消息信息列表
	 *  @param pageInfo
	 *  @param vo
	 *  @return 
	 *	@return 			: PageModel 
	 *  @createDate  	: 2017年3月15日 下午5:33:58
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年3月15日 下午5:33:58
	 *  @updateAuthor  :
	 */
	public PageModel findMessageList(PageInfo pageInfo, SysMessage vo);

	/**
	 * 获取入职人员信息列表
	 *  @param pageInfo
	 *  @param vo
	 *  @return 
	 *	@return 			: PageModel 
	 *  @createDate  	: 2017年3月16日 下午2:40:39
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年3月16日 下午2:40:39
	 *  @updateAuthor  :
	 */
	public PageModel messageUserList(ContextInfo cti,PageInfo pageInfo, SysMessage vo);

	/**
	 * 根据城市ID获取参保月份
	 *  @param areaId 
	 *	@return 			: void 
	 *  @createDate  	: 2017年3月17日 上午9:49:35
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年3月17日 上午9:49:35
	 *  @updateAuthor  :
	 */
	public List<Map<String, String>> findInsuranceFundDate(ContextInfo cti,Integer areaId);

	/**
	 * 获取入职人员文件ID信息
	 *  @param vo 
	 *	@return 			: void 
	 *  @createDate  	: 2017年3月17日 下午4:52:12
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年3月17日 下午4:52:12
	 *  @updateAuthor  :
	 */
	public List<Integer> messageUserDetailsFile(SysMessage vo);

	/**
	 * 保存员工
	 *  @param cti
	 *  @param cmEmployee
	 *  @param json 
	 *	@return 			: void 
	 *  @createDate  	: 2017年3月20日 上午10:28:46
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年3月20日 上午10:28:46
	 *  @updateAuthor  :
	 */
	public void saveEmp(ContextInfo cti, CmEmployee cmEmployee, SpsTask spsTask, SysMessage sysMessage,String msgType,Integer isIns,Integer isFund);

	/**
	 * 获取未读消息通知数
	 *  @param cti 
	 *	@return 			: Integer 
	 *  @createDate  	: 2017年3月21日 上午10:00:48
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年3月21日 上午10:00:48
	 *  @updateAuthor  :
	 */
	public Integer findMessageNumber(ContextInfo cti);

	/**
	 * 更新消息状态
	 *  @param cti 
	 *	@return 			: void 
	 *  @createDate  	: 2017年3月21日 上午10:25:25
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年3月21日 上午10:25:25
	 *  @updateAuthor  :
	 */
	public void updateMessageState(ContextInfo cti);

	/**
	 * 获取系统消息
	 *  @param cti
	 *  @param pageInfo
	 *  @param vo
	 *  @return 
	 *	@return 			: PageModel 
	 *  @createDate  	: 2017年3月21日 上午11:30:34
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年3月21日 上午11:30:34
	 *  @updateAuthor  :
	 */
	public PageModel findMessageSysDetails(ContextInfo cti, PageInfo pageInfo, SysMessage vo);

	/**
	 * 查询是否弹出消息框
	 *  @param cti
	 *  @return 
	 *	@return 			: Integer 
	 *  @createDate  	: 2017年4月17日 上午11:19:22
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年4月17日 上午11:19:22
	 *  @updateAuthor  :
	 */
	public Integer findIsAlert(ContextInfo cti);

	/**
	 * 查询用户办理业务详细信息
	 *  @param cti
	 *  @param vo 
	 *	@return 			: void 
	 *  @createDate  	: 2017年4月25日 上午10:00:24
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年4月25日 上午10:00:24
	 *  @updateAuthor  :
	 */
	public UserBusinessVo findMessageUserBusinessDetails(ContextInfo cti, SysMessage vo);

	/**
	 * 设置一个信息为已读
	 *  @param cti
	 *  @param msgId 
	 *	@return 			: void 
	 *  @createDate  	: 2017年5月22日 下午1:51:22
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年5月22日 下午1:51:22
	 *  @updateAuthor  :
	 */
	public void updateOneMessageState(ContextInfo cti, Integer msgId);

	/**
	 * @Title: 更新消息状态， 将该消息之前的同类消息设置为已读
	 * @param   
	 * @return 
	 * @createDate 2017/6/22 16:45
	 * @Auther:zhanghongjie【hongjievip6688@163.com】
	 * @version        : v1.0
	 * @updateDate    	: 
	 * @updateAuthor  :
	*/
	Integer updateMessageState(SysMessage vo, Integer bstypeId,Integer empId);



	List<SysMessage> getMessgeByTodoUser(SysMessage vo);

	/**
	 * 查询消息待办提醒列表
	 *  @param vo
	 *  @return 
	 *	@return 			: List<SysMessage> 
	 *  @createDate  	: 2017年8月31日 下午2:25:54
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年8月31日 下午2:25:54
	 *  @updateAuthor  :
	 */
	public List<SysMessage> findTodoRemindList(SysMessage vo);

	/**
	 * 消息推送
	 *  @param list 
	 *  @param code 社保saas：xfs_ss，工资条：xfs_pay
	 *	@return 			: String 
	 *  @createDate  	: 2017年10月11日 下午3:13:17
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年10月11日 下午3:13:17
	 *  @updateAuthor  :
	 */
	public String sendMessage(List<WxMessage> list,String code);




}
