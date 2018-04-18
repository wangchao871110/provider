package com.xfs.op.service.impl;
import java.util.List;
import java.util.Map;

import com.xfs.common.PageInfo;
import com.xfs.op.dao.OpHrplusAdvertDao;
import com.xfs.op.model.OpHrplusAdvert;
import com.xfs.op.service.OpHrplusAdvertService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.page.PageModel;
import com.xfs.common.page.SystemContext;
import com.xfs.common.ContextInfo;


@Service
public class OpHrplusAdvertServiceImpl implements OpHrplusAdvertService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(OpHrplusAdvertServiceImpl.class);
	
	@Autowired
	private OpHrplusAdvertDao opHrplusAdvertDao;
	
	public int save(ContextInfo cti, OpHrplusAdvert vo ){
		return opHrplusAdvertDao.save(cti,vo);
	}
	public int insert(ContextInfo cti, OpHrplusAdvert vo ){
		return opHrplusAdvertDao.insert(cti,vo);
	}
	public int remove(ContextInfo cti, OpHrplusAdvert vo ){
		return opHrplusAdvertDao.remove(cti,vo);
	}
	public int update(ContextInfo cti, OpHrplusAdvert vo ){
		return opHrplusAdvertDao.update(cti,vo);
	}
	public PageModel findPage(OpHrplusAdvert vo){
		
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		Integer total = opHrplusAdvertDao.countFreeFind(vo);
		pm.setTotal(total);
		List<OpHrplusAdvert> datas = opHrplusAdvertDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<OpHrplusAdvert> findAll(OpHrplusAdvert vo){
		
		List<OpHrplusAdvert> datas = opHrplusAdvertDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/03/09 10:56:40


    /**
     * 使用主键
     * @param obj
     * @return
     */
    public OpHrplusAdvert findByPK(OpHrplusAdvert obj){
       return opHrplusAdvertDao.findByPK(obj);
    }

    /**
     *  广告 批量查询
     *  @param   pageInfo, query
     * @return    : com.xfs.common.page.PageModel
     *  @createDate   : 2017/1/12 14:21
     *  @author          : yangfw@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2017/1/12 14:21
     *  @updateAuthor  :
     */
    public PageModel findManagePage(PageInfo pageInfo, Map<String, Object> query){
        PageModel pm = new PageModel(pageInfo);
        Integer total = opHrplusAdvertDao.finManageAdvertListCount(query);
        pm.setTotal(total);
        pm.setDatas(opHrplusAdvertDao.finManageAdvertList(query,pageInfo.getOffset(),pageInfo.getPagesize()));
        return pm;
    }
	
	
	
}
