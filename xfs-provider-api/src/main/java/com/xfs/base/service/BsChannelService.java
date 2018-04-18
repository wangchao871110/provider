package com.xfs.base.service;

import java.util.List;
import java.util.Map;

import com.xfs.base.model.BsChannel;
import com.xfs.base.model.CsQuestionnaire;
import com.xfs.bs.dto.QueryChannelCorpDto;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.corp.model.CmCorp;

/**
 * BsChannelService 渠道服务业务类接口
 * @author 	: wangxs@xinfushe.com
 * @date 	: 2016-11-11 15:18
 * @version 	: V1.0
 */
public interface BsChannelService {
	public int save(ContextInfo cti, BsChannel vo);
	public int insert(ContextInfo cti, BsChannel vo);
	public int remove(ContextInfo cti, BsChannel vo);
	public int update(ContextInfo cti, BsChannel vo);
	public PageModel findPage(PageInfo pi, BsChannel vo);
	public List<BsChannel> findAll(BsChannel vo);
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/26 11:15:54

    /**
     *  根据ID获取数据
     *  @param    ：  channel 渠道实体
     *  @return    :   BsChannel
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
	public BsChannel findByPK(BsChannel channel);

    /**
     *  获取所有渠道列表
     *  @param    ：  channel 渠道实体
     *  @return    :   PageModel
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
	public PageModel findAllPage(PageInfo pi, BsChannel channel);

    /**
     *  获取区与组
     *  @param    ：  channel 渠道实体
     *  @return    :   BsChannel
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
	public BsChannel findbyid(BsChannel channel);

    /**
     *  获取所有渠道列表
     *  @param    ：  channel 渠道实体
     *  @return    :   PageModel
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
	public PageModel findCmCorpList(PageInfo pi, CmCorp corp);

    /**
     *  根据渠道跟企业查询调查问卷的公司信息
     *  @param    ：  map 渠道实体
     *  @return    :   PageModel
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
	public PageModel findRegitCorpMsg(PageInfo pi, Map map);

    /**
     *  查找答案
     *  @param    ：  vo 答案实体
     *  @return    :   CsQuestionnaire
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
	public CsQuestionnaire findAnswer(CsQuestionnaire vo);

    /**
     *  创建用户渠道
     *  @param    ：  channel 渠道实体
     *  @return    :   Result
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
	public Result createChannelUser(ContextInfo cti, BsChannel channel);

    /**
     *  查询渠道商下企业
     *  @param    ：  channelCorpDto 渠道企业页面视图dto
     *  @return    :   PageModel
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
	public PageModel findChannelCorpPage(PageInfo pi, QueryChannelCorpDto channelCorpDto);

    /**
     *  查询渠道企业详情
     *  @param    ：  orgId 服务商id
     *  @param    ：  cpId   企业id
     *  @return    :   PageModel
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
	public Map queryChannelCorpByCpId(Integer orgId, Integer cpId);
}
