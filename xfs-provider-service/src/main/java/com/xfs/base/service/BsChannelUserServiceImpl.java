package com.xfs.base.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.base.dao.BsChannelUserDao;
import com.xfs.base.dao.CsRegistHistoryDao;
import com.xfs.base.model.BsChannelUser;
import com.xfs.base.model.CsRegistHistory;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * BsChannelUserServiceImpl 渠道用户服务业务实现类
 * @author 	: wangxs@xinfushe.com
 * @date 	: 2016-11-15 15:18
 * @version 	: V1.0
 */
@Service
public class BsChannelUserServiceImpl implements BsChannelUserService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BsChannelUserServiceImpl.class);
	
	@Autowired
	private BsChannelUserDao bsChannelUserDao;
	@Autowired
	private CsRegistHistoryDao registHistoryDao;
	
	public int save(ContextInfo cti, BsChannelUser vo ){
		return bsChannelUserDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BsChannelUser vo ){
		return bsChannelUserDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BsChannelUser vo ){
		return bsChannelUserDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BsChannelUser vo ){
		return bsChannelUserDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BsChannelUser vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bsChannelUserDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BsChannelUser> datas = bsChannelUserDao.freeFind(vo, pageIndex, pageSize," create_dt desc");
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BsChannelUser> findAll(BsChannelUser vo){
		
		List<BsChannelUser> datas = bsChannelUserDao.freeFind(vo);
		return datas;
		
	}
	
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
	@Override
	public BsChannelUser findByPK(BsChannelUser channelUser) {

		return bsChannelUserDao.findByPK(channelUser);
	}

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
	public PageModel findPage1(PageInfo pi, BsChannelUser vo){
		BsChannelUser query = new BsChannelUser();
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		query.setChannelId(vo.getChannelId());;
		if(StringUtils.isNotBlank(vo.getStatus())){
			query.setStatus(vo.getStatus());
		}
		if(StringUtils.isNotBlank(vo.getName())){
			query.setName(vo.getName().trim());
		}
		Integer total = bsChannelUserDao.countFreeFind1(query);
		pm.setTotal(total);
		List<Map> datas = bsChannelUserDao.freeFind1(query, pageIndex, pageSize);

		if(CollectionUtils.isNotEmpty(datas)){
			for(Map val : datas){
				CsRegistHistory registHistory = new CsRegistHistory();
				registHistory.setChannel(vo.getCodeEq());
				registHistory.setSname(val.get("code").toString());
				registHistory.setAnswerDt(null);
				// 注册人数
				val.put("register_count",registHistoryDao.findRegisterCount(registHistory));
				registHistory.setAnswerDt(new Date());
				// 回答问卷人数
				val.put("answer_count",registHistoryDao.findRegisterCount(registHistory));
			}
		}
		pm.setDatas(datas);
		return pm;

	}
}
