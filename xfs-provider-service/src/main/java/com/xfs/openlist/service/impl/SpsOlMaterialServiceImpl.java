package com.xfs.openlist.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.openlist.dao.SpsOlMaterialDao;
import com.xfs.openlist.model.SpsOl;
import com.xfs.openlist.model.SpsOlMaterial;
import com.xfs.openlist.service.SpsOlMaterialService;

@Service
public class SpsOlMaterialServiceImpl implements SpsOlMaterialService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsOlMaterialServiceImpl.class);
	
	@Autowired
	private SpsOlMaterialDao spsOlMaterialDao;
	
	public int save(ContextInfo cti, SpsOlMaterial vo ){
		return spsOlMaterialDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsOlMaterial vo ){
		return spsOlMaterialDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsOlMaterial vo ){
		return spsOlMaterialDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsOlMaterial vo ){
		return spsOlMaterialDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsOlMaterial vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsOlMaterialDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsOlMaterial> datas = spsOlMaterialDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsOlMaterial> findAll(SpsOlMaterial vo){
		
		List<SpsOlMaterial> datas = spsOlMaterialDao.freeFind(vo);
		return datas;
		
	}


	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/04/09 16:45:56

    /**
     * 根据业务类型id删除业务材料
     *  @param    : olId openlistid
     *  @return    :  int
     *  @createDate   : 16-11-13 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-13 上午10:47
     *  @updateAuthor  :
     */
    public int removeByOlId(ContextInfo cti,Integer olId) {
        SpsOlMaterial obj = new SpsOlMaterial();
        obj.setOlId(olId);
        return spsOlMaterialDao.removeByOlId(cti,obj);
    }

    /**
     * 通过openlist查询相关材料
     *  @param    : vo openlist对象
     *  @return    :  List<Map<String, Object>>
     *  @createDate   : 16-11-13 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-13 上午10:47
     *  @updateAuthor  :
     */
	@Override
	public List<Map<String, Object>> findByOlId(SpsOl vo) {
		return spsOlMaterialDao.findByOlId(vo);
	}

    /**
     * 检查材料是否被使用
     *  @param    : vo 业务材料实体
     *  @return    :  int
     *  @createDate   : 16-11-13 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-13 上午10:47
     *  @updateAuthor  :
     */
	public int checkUesed(SpsOlMaterial vo) {
		return spsOlMaterialDao.checkUesed(vo);
	}

}
