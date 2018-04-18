package com.xfs.base.dao;

import com.xfs.base.model.SysMessage;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.msg.dto.MessageVo;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @author xiyanzhang
 * @version 创建时间：2016年11月10日 下午5:12:39
 * @Email: zhangxiyan@xinfushe.com
 */
@Repository
public class SysMessageDao extends BaseSqlMapDao {
    /**
     * 消息通知 阅读消息
     *
     * @param obj
     * @return : Integer
     * @createDate : 2016年11月14日 下午4:42:21
     * @author : xiyanzhang
     * @version : v1.0
     * @updateDate : 2016年11月14日 下午4:42:21
     * @updateAuthor :
     */
    public Integer UpdateMessageState(ContextInfo cti, SysMessage obj) {
        return update(cti, "SYS_MESSAGE.UpdateMessageState", obj);
    }

    /**
     * 消息通知 数据列表
     *
     * @param obj
     * @return : List<HashMap<String,Object>>
     * @createDate : 2016年11月14日 下午4:43:08
     * @author : xiyanzhang
     * @version : v1.0
     * @updateDate : 2016年11月14日 下午4:43:08
     * @updateAuthor :
     */
    @SuppressWarnings("unchecked")
    public List<HashMap<String, Object>> freeShortFind(SysMessage obj) {
        return queryForList("SYS_MESSAGE.CS_FreeFindSYS_MESSAGEByUserId", obj);
    }

    /**
     * 消息通知 查询通知消息总条数
     *
     * @param obj
     * @return : int
     * @createDate : 2016年11月14日 下午4:51:37
     * @author : xiyanzhang
     * @version : v1.0
     * @updateDate : 2016年11月14日 下午4:51:37
     * @updateAuthor :
     */
    public int countFreeShortFind(SysMessage obj) {
        Integer ret = (Integer) queryForObject("SYS_MESSAGE.CS_CountFreeFindSYS_MESSAGEByUserId", obj);
        return ret.intValue();
    }

    /**
     * 根据条件统计数量
     *
     * @param obj
     * @return
     */
    public int countFind(SysMessage obj) {
        Integer ret = (Integer) queryForObject("SYS_MESSAGE.CountFreeFindSYS_MESSAGE", obj);
        return ret.intValue();
    }


    /**
     * 消息通知 数据列表
     *
     * @param obj
     * @param pageIndex
     * @param pageSize
     * @return : List<SysMessage>
     * @createDate : 2016年11月14日 下午4:51:51
     * @author : xiyanzhang
     * @version : v1.0
     * @updateDate : 2016年11月14日 下午4:51:51
     * @updateAuthor :
     */
    @SuppressWarnings("unchecked")
    public List<SysMessage> CS_freeFind(SysMessage obj, int pageIndex, int pageSize) {
        return getPaginatedList("SYS_MESSAGE.CS_FreeFindSYS_MESSAGEByUserId", obj, pageIndex, pageSize);
    }

    /**
     * 消息通知 查询通知消息总条数
     *
     * @param obj
     * @return : int
     * @createDate : 2016年11月14日 下午4:52:02
     * @author : xiyanzhang
     * @version : v1.0
     * @updateDate : 2016年11月14日 下午4:52:02
     * @updateAuthor :
     */
    public int CS_countFreeFind(SysMessage obj) {
        Integer ret = (Integer) queryForObject("SYS_MESSAGE.CS_CountFreeFindSYS_MESSAGEByUserId", obj);
        return ret.intValue();
    }

    /**
     * @param obj
     * @return : int
     * @createDate : 2016年11月14日 下午4:52:14
     * @author : xiyanzhang
     * @version : v1.0
     * @updateDate : 2016年11月14日 下午4:52:14
     * @updateAuthor :
     */
    public int insert(ContextInfo cti, SysMessage obj) {

        obj.fixup();
        return insert(cti, "SYS_MESSAGE.InsertSYS_MESSAGE", obj);
    }

    /**
     * @return : int
     * @createDate : 2016年11月14日 下午4:52:22
     * @author : xiyanzhang
     * @version : v1.0
     * @updateDate : 2016年11月14日 下午4:52:22
     * @updateAuthor :
     */
    public int countFindAll() {
        Integer ret = (Integer) queryForObject("SYS_MESSAGE.CountFindAllSYS_MESSAGE", null);
        return ret.intValue();
    }

    /**
     * @param obj
     * @return : SysMessage
     * @createDate : 2016年11月14日 下午4:52:29
     * @author : xiyanzhang
     * @version : v1.0
     * @updateDate : 2016年11月14日 下午4:52:29
     * @updateAuthor :
     */
    public SysMessage findByPK(SysMessage obj) {
        Object ret = queryForObject("SYS_MESSAGE.FindByPK", obj);
        if (ret != null)
            return (SysMessage) ret;
        else
            return null;
    }

    /**
     * @param obj
     * @return : List<SysMessage>
     * @createDate : 2016年11月14日 下午4:52:34
     * @author : xiyanzhang
     * @version : v1.0
     * @updateDate : 2016年11月14日 下午4:52:34
     * @updateAuthor :
     */
    @SuppressWarnings("unchecked")
    public List<SysMessage> freeFind(SysMessage obj) {
        return queryForList("SYS_MESSAGE.FreeFindSYS_MESSAGE", obj);
    }

    /**
     * @param obj
     * @param orderByColName
     * @return : List<SysMessage>
     * @createDate : 2016年11月14日 下午4:52:38
     * @author : xiyanzhang
     * @version : v1.0
     * @updateDate : 2016年11月14日 下午4:52:38
     * @updateAuthor :
     */
    @SuppressWarnings("unchecked")
    public List<SysMessage> freeFind(SysMessage obj, String orderByColName) {
        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        if (obj instanceof SysMessage) {
            _hashmap = ((SysMessage) obj).toHashMap();
        }
        _hashmap.put("_orderBy", orderByColName);

        return queryForList("SYS_MESSAGE.FreeFindSYS_MESSAGEOrdered", _hashmap);
    }

    /**
     * @param obj
     * @param pageIndex
     * @param pageSize
     * @param orderByColName
     * @return : List<SysMessage>
     * @createDate : 2016年11月14日 下午4:52:43
     * @author : xiyanzhang
     * @version : v1.0
     * @updateDate : 2016年11月14日 下午4:52:43
     * @updateAuthor :
     */
    @SuppressWarnings("unchecked")
    public List<SysMessage> freeFind(SysMessage obj, int pageIndex, int pageSize, String orderByColName) {
        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        if (obj instanceof SysMessage) {
            _hashmap = ((SysMessage) obj).toHashMap();
        }
        _hashmap.put("_orderBy", orderByColName);

        return getPaginatedList("SYS_MESSAGE.FreeFindSYS_MESSAGEOrdered", _hashmap, pageIndex, pageSize);
    }

    /**
     * @param objColl
     * @return : int
     * @createDate : 2016年11月14日 下午4:52:50
     * @author : xiyanzhang
     * @version : v1.0
     * @updateDate : 2016年11月14日 下午4:52:50
     * @updateAuthor :
     */
    public int saveAll(ContextInfo cti, Collection<SysMessage> objColl) {
        int i = 0;
        if (objColl != null) {
            Iterator<SysMessage> it = objColl.iterator();
            while (it.hasNext()) {
                SysMessage oneObj = (SysMessage) it.next();
                i += save(cti, oneObj);
            }
        }
        return i;
    }

    /**
     * @param vo
     * @return : int
     * @createDate : 2016年11月14日 下午4:52:55
     * @author : xiyanzhang
     * @version : v1.0
     * @updateDate : 2016年11月14日 下午4:52:55
     * @updateAuthor :
     */
    public int save(ContextInfo cti, SysMessage vo) {
        SysMessage obj = (SysMessage) vo;

        if (exists(obj)) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
    }

    /**
     * @param obj
     * @return : int
     * @createDate : 2016年11月14日 下午4:53:00
     * @author : xiyanzhang
     * @version : v1.0
     * @updateDate : 2016年11月14日 下午4:53:00
     * @updateAuthor :
     */
    public int update(ContextInfo cti, SysMessage obj) {

        obj.fixup();
        return update(cti, "SYS_MESSAGE.UpdateSYS_MESSAGE", obj);

    }

    /**
     * @param obj
     * @return : int
     * @createDate : 2016年11月14日 下午4:53:04
     * @author : xiyanzhang
     * @version : v1.0
     * @updateDate : 2016年11月14日 下午4:53:04
     * @updateAuthor :
     */
    public int remove(ContextInfo cti, SysMessage obj) {

        if (obj == null) {
            return 0;
        }

        obj.fixup();

        return delete(cti, "SYS_MESSAGE.DeleteSYS_MESSAGE", obj);

    }

    /**
     * @param obj
     * @return : int
     * @createDate : 2016年11月14日 下午4:53:10
     * @author : xiyanzhang
     * @version : v1.0
     * @updateDate : 2016年11月14日 下午4:53:10
     * @updateAuthor :
     */
    public int removeObjectTree(ContextInfo cti, SysMessage obj) {

        obj.fixup();
        SysMessage oldObj = (SysMessage) queryForObject("SYS_MESSAGE.FindByPK", obj);
        if (oldObj == null) {
            return 0;
        }

        return delete(cti, "SYS_MESSAGE.DeleteSYS_MESSAGE", oldObj);

    }

    /**
     * @param vo
     * @return : boolean
     * @createDate : 2016年11月14日 下午4:53:14
     * @author : xiyanzhang
     * @version : v1.0
     * @updateDate : 2016年11月14日 下午4:53:14
     * @updateAuthor :
     */
    public boolean exists(SysMessage vo) {

        boolean keysFilled = true;
        boolean ret = false;
        SysMessage obj = (SysMessage) vo;

        keysFilled = keysFilled && (null != obj.getMessageId());

        if (keysFilled) {
            Integer retInt = (Integer) queryForObject("SYS_MESSAGE.CountSYS_MESSAGE", obj);
            if (retInt != null && retInt.intValue() > 0) {
                ret = true;
            }
        }

        return ret;
    }

    /**
     * 增员业务 社保新参 2、社保转入 3、公积金增加 10
     *
     * @param parameterMap
     * @return : HashMap<String,Object>
     * @createDate : 2016年11月14日 下午4:53:23
     * @author : xiyanzhang
     * @version : v1.0
     * @updateDate : 2016年11月14日 下午4:53:23
     * @updateAuthor :
     */
    public HashMap<String, Object> insFundAdd(HashMap<String, Object> parameterMap) {
        @SuppressWarnings("unchecked")
        HashMap<String, Object> insFundAddMap = (HashMap<String, Object>) queryForObject("SYS_MESSAGE.findinsFundAdd",
                parameterMap);
        return insFundAddMap;
    }

    /**
     * 减员业务 社保减员4 、公积金减少 11
     *
     * @param parameterMap
     * @return : HashMap<String,Object>
     * @createDate : 2016年11月14日 下午4:53:47
     * @author : xiyanzhang
     * @version : v1.0
     * @updateDate : 2016年11月14日 下午4:53:47
     * @updateAuthor :
     */
    public HashMap<String, Object> insFundReduce(HashMap<String, Object> parameterMap) {
        @SuppressWarnings("unchecked")
        HashMap<String, Object> iinsFundReduceMap = (HashMap<String, Object>) queryForObject(
                "SYS_MESSAGE.findinsFundReduce", parameterMap);
        return iinsFundReduceMap;
    }

    /**
     * 补缴业务 社保补缴 29、公积金补缴30
     *
     * @param parameterMap
     * @return : HashMap<String,Object>
     * @createDate : 2016年11月14日 下午4:54:09
     * @author : xiyanzhang
     * @version : v1.0
     * @updateDate : 2016年11月14日 下午4:54:09
     * @updateAuthor :
     */
    public HashMap<String, Object> insFundSupple(HashMap<String, Object> parameterMap) {
        @SuppressWarnings("unchecked")
        HashMap<String, Object> insFundSuppleMap = (HashMap<String, Object>) queryForObject(
                "SYS_MESSAGE.findinsFundSupple", parameterMap);
        return insFundSuppleMap;
    }

    /**
     * 合作伙伴
     *
     * @param parameterMap
     * @return : HashMap<String,Object>
     * @createDate : 2016年11月14日 下午4:54:24
     * @author : xiyanzhang
     * @version : v1.0
     * @updateDate : 2016年11月14日 下午4:54:24
     * @updateAuthor :
     */
    public HashMap<String, Object> findPartner(HashMap<String, Object> parameterMap) {
        @SuppressWarnings("unchecked")
        HashMap<String, Object> findPartnerMap = (HashMap<String, Object>) queryForObject("SYS_MESSAGE.findPartner",
                parameterMap);
        return findPartnerMap;
    }

    /**
     * 根据传入的url 查 function role userid 的list
     *
     * @param obj
     * @return : List<HashMap<String,Object>>
     * @createDate : 2016年11月14日 下午4:54:33
     * @author : xiyanzhang
     * @version : v1.0
     * @updateDate : 2016年11月14日 下午4:54:33
     * @updateAuthor :
     */
    @SuppressWarnings("unchecked")
    public List<HashMap<String, Object>> findUserIdListByUrl(SysMessage obj) {
        List<HashMap<String, Object>> list = queryForList("SYS_MESSAGE.findUserIdListByUrl", obj);
        return list;
    }

    /**
     * 消息通知 数据列表
     *
     * @param obj
     * @param pageIndex
     * @param pageSize
     * @return : List<SysMessage>
     * @createDate : 2016年11月14日 下午4:55:22
     * @author : xiyanzhang
     * @version : v1.0
     * @updateDate : 2016年11月14日 下午4:55:22
     * @updateAuthor :
     */
    @SuppressWarnings("unchecked")
    public List<SysMessage> freeFind(SysMessage obj, int pageIndex, int pageSize) {
        return getPaginatedList("SYS_MESSAGE.FreeFindSYS_MESSAGEByUserId", obj, pageIndex, pageSize);
    }

    /**
     * 消息通知 查询通知消息总条数
     *
     * @param obj
     * @return : int
     * @createDate : 2016年11月14日 下午4:56:00
     * @author : xiyanzhang
     * @version : v1.0
     * @updateDate : 2016年11月14日 下午4:56:00
     * @updateAuthor :
     */
    public int countFreeFind(SysMessage obj) {
        Integer ret = (Integer) queryForObject("SYS_MESSAGE.CountFreeFindSYS_MESSAGEByUserId", obj);
        return ret.intValue();
    }

    /**
     * @param obj
     * @return : List<HashMap<String,Object>>
     * @createDate : 2016年11月14日 下午4:56:12
     * @author : xiyanzhang
     * @version : v1.0
     * @updateDate : 2016年11月14日 下午4:56:12
     * @updateAuthor :
     */
    @SuppressWarnings("unchecked")
    public List<HashMap<String, Object>> userIdList(SysMessage obj) {
        return queryForList("SYS_MESSAGE.FindUserIdByDataIdTaskIdCpId", obj);
    }


    /**
     * 分页查询薪福加文章消息
     *
     * @param msg, offset, pageSize
     * @return : java.util.Map<java.lang.String,java.lang.String>
     * @createDate : 2017/1/10 10:35
     * @author : yangfw@xinfushe.com
     * @version : v1.0
     * @updateDate : 2017/1/10 10:35
     * @updateAuthor :
     */
    public List<Map<String, Object>> findPageContentMsg(SysMessage msg, Integer offset, Integer pageSize) {
        return (List<Map<String, Object>>) getPaginatedList("SYS_MESSAGE.FindContentMsgList", msg, offset, pageSize);
    }

    /**
     * 查询薪福加文章总数
     *
     * @param msg,
     * @return : java.util.Map<java.lang.String,java.lang.String>
     * @createDate : 2017/1/10 10:35
     * @author : yangfw@xinfushe.com
     * @version : v1.0
     * @updateDate : 2017/1/10 10:35
     * @updateAuthor :
     */
    public Integer findPageContentMsgCount(SysMessage msg) {
        return (Integer) queryForObject("SYS_MESSAGE.FindContentMsgListCount", msg);
    }

    /**
     * 分页查询薪福加系统消息
     *
     * @param msg, offset, pageSize
     * @return : java.util.Map<java.lang.String,java.lang.String>
     * @createDate : 2017/1/10 10:35
     * @author : yangfw@xinfushe.com
     * @version : v1.0
     * @updateDate : 2017/1/10 10:35
     * @updateAuthor :
     */
    public List<SysMessage> findPageSysMsg(SysMessage msg, Integer offset, Integer pageSize) {
        return (List<SysMessage>) getPaginatedList("SYS_MESSAGE.FreeFindXFJ_SYS_MESSAGEOrdered", msg, offset, pageSize);
    }

    /**
     * 查询消息信息列表总数
     *
     * @param vo
     * @return : Integer
     * @createDate : 2017年3月16日 下午2:42:48
     * @author : wangchao
     * @version : v1.0
     * @updateDate : 2017年3月16日 下午2:42:48
     * @updateAuthor :
     */
    public Integer findMessageListCount(SysMessage vo) {
        return (Integer) queryForObject("SYS_MESSAGE.findMessageListCount", vo);
    }

    /**
     * 查询消息信息列表
     *
     * @param vo
     * @param pageIndex
     * @param pageSize
     * @return : List<SysMessage>
     * @createDate : 2017年3月16日 下午2:42:45
     * @author : wangchao
     * @version : v1.0
     * @updateDate : 2017年3月16日 下午2:42:45
     * @updateAuthor :
     */
    public List<SysMessage> findMessageList(SysMessage vo, int pageIndex, int pageSize) {
        return getPaginatedList ("SYS_MESSAGE.findMessageList", vo, pageIndex, pageSize);
    }

    /**
     * 获取入职人员信息总数
     *
     * @param vo
     * @return : Integer
     * @createDate : 2017年3月16日 下午2:42:17
     * @author : wangchao
     * @version : v1.0
     * @updateDate : 2017年3月16日 下午2:42:17
     * @updateAuthor :
     */
    public Integer findMessageUserListCount(SysMessage vo) {
        return (Integer) queryForObject("SYS_MESSAGE.findMessageUserListCount", vo);
    }

    /**
     * 获取入职人员信息列表
     *
     * @param vo
     * @param pageIndex
     * @param pageSize
     * @return : List<SysMessage>
     * @createDate : 2017年3月16日 下午2:42:21
     * @author : wangchao
     * @version : v1.0
     * @updateDate : 2017年3月16日 下午2:42:21
     * @updateAuthor :
     */
    public List<MessageVo> messageUserList(SysMessage vo, int pageIndex, int pageSize) {
        return getPaginatedList("SYS_MESSAGE.messageUserList", vo, pageIndex, pageSize);
    }

    /**
     * 获取未读消息通知数
     *
     * @param vo
     * @return : Integer
     * @createDate : 2017年3月21日 上午10:07:51
     * @author : wangchao
     * @version : v1.0
     * @updateDate : 2017年3月21日 上午10:07:51
     * @updateAuthor :
     */
    public Integer findMessageNumber(SysMessage vo) {
        return (Integer) queryForObject("SYS_MESSAGE.findMessageNumber", vo);
    }

    /**
     * 更新消息状态
     *
     * @param vo
     * @return : void
     * @createDate : 2017年3月21日 上午10:26:17
     * @author : wangchao
     * @version : v1.0
     * @updateDate : 2017年3月21日 上午10:26:17
     * @updateAuthor :
     */
    public void updateMessageState(ContextInfo cti, SysMessage vo) {
        update(cti, "SYS_MESSAGE.UpdateMessageState", vo);
    }

    /**
     * 获取系统消息总数
     *
     * @param vo
     * @return : Integer
     * @createDate : 2017年3月21日 上午11:33:40
     * @author : wangchao
     * @version : v1.0
     * @updateDate : 2017年3月21日 上午11:33:40
     * @updateAuthor :
     */
    public Integer findMessageSysDetailsCount(SysMessage vo) {
        return (Integer) queryForObject("SYS_MESSAGE.findMessageSysDetailsCount", vo);
    }

    /**
     * 获取系统消息列表
     *
     * @param vo
     * @param pageIndex
     * @param pageSize
     * @return : List<SysMessage>
     * @createDate : 2017年3月21日 上午11:33:43
     * @author : wangchao
     * @version : v1.0
     * @updateDate : 2017年3月21日 上午11:33:43
     * @updateAuthor :
     */
    public List<SysMessage> findMessageSysDetailsList(SysMessage vo, int pageIndex, int pageSize) {
        return getPaginatedList("SYS_MESSAGE.findMessageSysDetailsList", vo, pageIndex, pageSize);
    }


    /**
     * @param
     * @return
     * @Title: 更新消息状态， 将该消息之前的同类消息设置为已读
     * @createDate 2017/6/22 17:07
     * @Auther:zhanghongjie【hongjievip6688@163.com】
     * @version : v1.0
     * @updateDate :
     * @updateAuthor :
     */
    public Integer updateMessageStateByMessageId(SysMessage vo, Integer bstypeId, Integer empId) {
        vo.setDataId(bstypeId);
        vo.setTodoUser(empId);
//        Map obj = new HashMap();
//        obj.put("isRead", vo.getIsRead());
//        obj.put("state", vo.getState());
//        obj.put("dateId", bstypeId);
//        obj.put("empId", empId);
//        obj.put("messageId", vo.getMessageId());
        return update(null, "SYS_MESSAGE.updateMessageStateByMessageId", vo);
    }


    public List<SysMessage> getMessgeByTodoUser(SysMessage vo) {
        return queryForList("SYS_MESSAGE.getMessgeByTodoUser", vo);
    }

    /**
     * 查询消息待办提醒列表
     *  @param vo
     *  @return 
     *	@return 			: List<SysMessage> 
     *  @createDate  	: 2017年8月31日 下午2:27:28
     *  @author         	: wangchao 
     *  @version        	: v1.0
     *  @updateDate    	: 2017年8月31日 下午2:27:28
     *  @updateAuthor  :
     */
	public List<SysMessage> findTodoRemindList(SysMessage vo) {
		return queryForList("SYS_MESSAGE.findTodoRemindList", vo);
	}

}
