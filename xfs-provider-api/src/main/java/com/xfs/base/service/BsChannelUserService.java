package com.xfs.base.service;

import java.util.List;

import com.xfs.base.model.BsChannelUser;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * BsChannelService 渠道用户服务业务类接口
 * @author 	: wangxs@xinfushe.com
 * @date 	: 2016-11-15 15:18
 * @version 	: V1.0
 */
public interface BsChannelUserService {
	
	public int save(ContextInfo cti, BsChannelUser vo);
	public int insert(ContextInfo cti, BsChannelUser vo);
	public int remove(ContextInfo cti, BsChannelUser vo);
	public int update(ContextInfo cti, BsChannelUser vo);
	public PageModel findPage(PageInfo pi, BsChannelUser vo);
	public List<BsChannelUser> findAll(BsChannelUser vo);
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/26 11:15:55

    /**
     *  根据ID获取数据
     *  @param    ：  channelUser 用户渠道实体
     *  @return    :   BsChannelUser
     *  @createDate   : 16-11-15 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-15 上午10:47
     *  @updateAuthor  :
     */
	public BsChannelUser findByPK(BsChannelUser channelUser);

    /**
     *  渠道商查看员工详情
     *  @param   :  vo  用户渠道实体
     *  @return    :  PageModel
     *  @createDate   : 16-11-15 上午10:38
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-15 上午10:38
     *  @updateAuthor  :
     */
	public PageModel findPage1(PageInfo pi, BsChannelUser vo);
}
