package com.xfs.openlist.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.openlist.dao.SpsOlAreaauthDao;
import com.xfs.openlist.model.SpsOlAreaauth;
import com.xfs.openlist.service.SpsOlAreaauthService;

@Service
public class SpsOlAreaauthServiceImpl implements SpsOlAreaauthService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsOlAreaauthServiceImpl.class);
	
	@Autowired
	private SpsOlAreaauthDao spsOlAreaauthDao;
	
	public int save(ContextInfo cti, SpsOlAreaauth vo ){
		return spsOlAreaauthDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsOlAreaauth vo ){
		return spsOlAreaauthDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsOlAreaauth vo ){
		return spsOlAreaauthDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsOlAreaauth vo ){
		return spsOlAreaauthDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsOlAreaauth vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsOlAreaauthDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsOlAreaauth> datas = spsOlAreaauthDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsOlAreaauth> findAll(SpsOlAreaauth vo){
		
		List<SpsOlAreaauth> datas = spsOlAreaauthDao.freeFind(vo);
		return datas;
		
	}


	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/04/09 16:45:55

    /**
     *  获取权限区域
     *  @param    ：vo  权限区域实体
     *  @return    :  List<Map<String, Object>>
     *  @createDate   : 16-11-13 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-13 上午10:47
     *  @updateAuthor  :
     */
	@Override
	public List<Map<String, Object>> findAuthAreas(SpsOlAreaauth vo) {
		return spsOlAreaauthDao.findAuthAreas(vo);
	}

    /**
     *  增加权限区域
     *  @param    ：vo  权限区域实体
     *  @return    :  int
     *  @createDate   : 16-11-13 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-13 上午10:47
     *  @updateAuthor  :
     */
	@Override
	public int addSpAreaAuth(ContextInfo cti,SpsOlAreaauth vo) {
		//判断当前已存在权限个数
		List<SpsOlAreaauth> areaauthList = findAll(vo);
		if(areaauthList.size()  > 2)
			return -1;
		return save(cti,vo);
	}

    /**
     *  获取所有权限区域列表
     *  @param    ：
     *  @return    :  int
     *  @createDate   : 16-11-13 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-13 上午10:47
     *  @updateAuthor  :
     */
	@Override
	public Map<Integer,SpsOlAreaauth> queryAllOIAreaauthList(){
		SpsOlAreaauth areaauth = new SpsOlAreaauth();
		List<SpsOlAreaauth> allAreaauthList = findAll(areaauth);
		Map<Integer,SpsOlAreaauth> areaauthMap = new HashMap<Integer,SpsOlAreaauth>();
		for(SpsOlAreaauth olAreaauth : allAreaauthList){
			areaauthMap.put(olAreaauth.getAreaId(),olAreaauth);
		}
		return areaauthMap;
	}

}
