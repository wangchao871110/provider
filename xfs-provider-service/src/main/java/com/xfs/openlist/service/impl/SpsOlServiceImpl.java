package com.xfs.openlist.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.base.model.SecWx;
import com.xfs.base.service.SecWxService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.openlist.dao.SpsOlDao;
import com.xfs.openlist.model.SpsOl;
import com.xfs.openlist.service.SpsOlService;
//import com.xfs.sps.service.SecWxService;
import com.xfs.sps.util.vo.OAuthInfo;
import com.xfs.sps.utils.WeixinUtil;

@Service
public class SpsOlServiceImpl implements SpsOlService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsOlServiceImpl.class);

	@Autowired
	private SpsOlDao spsOlDao;

	@Autowired
	SecWxService secWxService;
	
	public int save(ContextInfo cti, SpsOl vo ){
		return spsOlDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsOl vo ){
		return spsOlDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsOl vo ){
		return spsOlDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsOl vo ){
		return spsOlDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsOl vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsOlDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsOl> datas = spsOlDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsOl> findAll(SpsOl vo){
		
		List<SpsOl> datas = spsOlDao.freeFind(vo);
		return datas;
		
	}


	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/04/09 16:45:55

    /**
     * 分页条件搜索openlist
     *  @param    : vo openlist实体
     *  @param    ： searchWord 搜索词
     *  @return    :  areaids  多条地区id（以逗号分隔）
     *  @createDate   : 16-11-13 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-13 上午10:47
     *  @updateAuthor  :
     */
	@Override
	public PageModel customFind(PageInfo pi,SpsOl vo, String searchWord,String areaids) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		pm.setTotal(spsOlDao.countCustomFind(vo, searchWord,areaids));
		List<Map<String, Object>> datas = spsOlDao.customFind(vo, searchWord,areaids,pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}

	@Override
	public PageModel queryMyOlList(PageInfo pi,SpsOl vo, String searchWord,String areaids) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		pm.setTotal(spsOlDao.queryMyOlListCount(vo, searchWord,areaids));
		List<Map<String, Object>> datas = spsOlDao.queryMyOlList(vo, searchWord,areaids,pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}

    /**
     * openlist实体id查找对象
     *  @param    : vo openlist实体
     *  @return    :  SpsOl
     *  @createDate   : 16-11-13 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-13 上午10:47
     *  @updateAuthor  :
     */
	@Override
	public SpsOl findByPK(SpsOl vo) {
		return spsOlDao.findByPK(vo);
	}


    /**
     * 根据业务类型和区域查询
     *  @param    : vo openlist实体
     *  @return    :  SpsOl
     *  @createDate   : 16-11-13 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-13 上午10:47
     *  @updateAuthor  :
     */
	@Override
	public SpsOl findByBstypeAndArea(SpsOl vo) {
		return spsOlDao.findByBstypeAndArea(vo);
	}

	/**
	 * findOlByPK
	 *
	 * @param obj
	 * @return
     */

    /**
     * openlist实体id查找对象
     *  @param    : vo openlist实体
     *  @return    :  Map<String, Object>
     *  @createDate   : 16-11-13 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-13 上午10:47
     *  @updateAuthor  :
     */
	@Override
	public Map<String, Object> findOlByPK(SpsOl obj) {
		return spsOlDao.findOlByPK(obj);
	}

	@Override
	public List<Map<String, Object>> findSpOIUsedCount(int spId) {

		return  spsOlDao.findSpOIUsedCount(spId);
	}

	@Override
	public String getVisitWxOpenId(SecWx wx, String code)
	{
		if(null != wx && null != code){
			OAuthInfo authInfo = WeixinUtil.getOAuthOpenId(wx.getAppid(),wx.getAppsecret(),code);
			if(null != authInfo)
				return authInfo.getOpenId();
			return null;
		}
		return null;
	}

	@Override
	public SecWx getSpWxConfig(String wx_id) {
		SecWx wx = new SecWx();
		wx.setWxId(wx_id);
		List<SecWx> wxList = secWxService.findAll(wx);
		if(null != wxList && !wxList.isEmpty()) {
			return wxList.get(0);
		}
		return null;
	}

	@Override
	public SpsOl findByPK(Integer olid) {
		SpsOl ol = new SpsOl();
		ol.setOlId(olid);
		return spsOlDao.findByPK(ol);
	}

	@Override
	public Map<String,List<Map<String,Object>>> queryAllOLByMateialType(SpsOl ol){
		return spsOlDao.queryAllOLByMateialType(ol);
	}

	@Override
	public Map<String,Object> queryOLById(Integer olId){
		return spsOlDao.queryOLById(olId);
	}

    /**
     * 获取区域下的业务材料列表
     *  @param    : areaId 地区id
     *  @return    :  List<Map<String, Object>>
     *  @createDate   : 16-11-13 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-13 上午10:47
     *  @updateAuthor  :
     */
	@Override
	public List<Map<String, Object>> queryAreaOlList(Integer areaId) {
		return spsOlDao.queryAreaOlList(areaId);
	}


}
