package com.xfs.openlist.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.openlist.dao.SpsAreamaterialDao;
import com.xfs.openlist.service.SpsAreamaterialService;
import com.xfs.sp.model.SpsAreamaterial;


@Service
public class SpsAreamaterialServiceImpl implements SpsAreamaterialService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsAreamaterialServiceImpl.class);
	
	@Autowired
	private SpsAreamaterialDao spsAreamaterialDao;
	
	public int save(ContextInfo cti, SpsAreamaterial vo ){
		return spsAreamaterialDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsAreamaterial vo ){
		return spsAreamaterialDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsAreamaterial vo ){
		return spsAreamaterialDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsAreamaterial vo ){
		return spsAreamaterialDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsAreamaterial vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsAreamaterialDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsAreamaterial> datas = spsAreamaterialDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsAreamaterial> findAll(SpsAreamaterial vo){
		
		List<SpsAreamaterial> datas = spsAreamaterialDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/04/09 16:45:54

    /**
     *  根据区域和材料名称获取区域材料
     *  @param    ：  vo 区域材料实体
     *  @return    :  SpsAreamaterial
     *  @createDate   : 16-11-13 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-13 上午10:47
     *  @updateAuthor  :
     */
	@Override
	public SpsAreamaterial findByAraeAndName(SpsAreamaterial vo) {
		return spsAreamaterialDao.findByAraeAndName(vo);
	}

    /**
     *  根据主键id查询区域材料
     *  @param    ：  obj 区域材料实体
     *  @return    :  SpsAreamaterial
     *  @createDate   : 16-11-13 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-13 上午10:47
     *  @updateAuthor  :
     */
	public SpsAreamaterial findByPK(SpsAreamaterial obj) {
		return spsAreamaterialDao.findByPK(obj);
	}

    /**
     *  根据区域材料实体查询区域材料（含排序）
     *  @param    ：  vo 区域材料实体
     *  @param   ：    orderByColName 按什么字段排序
     *  @return    :  List<SpsAreamaterial>
     *  @createDate   : 16-11-13 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-13 上午10:47
     *  @updateAuthor  :
     */
	public List<SpsAreamaterial> findAll(SpsAreamaterial vo, String orderByColName) {
		List<SpsAreamaterial> datas = spsAreamaterialDao.freeFind(vo, orderByColName);
		return datas;

	}

    /**
     *  根据区openlistid跟材料类型体查询区域材料
     *  @param    ：  olid openlistid
     *  @param   ：    materialType 材料类型
     *  @return    :   List<SpsAreamaterial>
     *  @createDate   : 16-11-13 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-13 上午10:47
     *  @updateAuthor  :
     */
	public List<SpsAreamaterial> findOlMaterialList(Integer olid,Integer materialType){
		return spsAreamaterialDao.findOlMaterialList(olid,materialType);
	}

}
