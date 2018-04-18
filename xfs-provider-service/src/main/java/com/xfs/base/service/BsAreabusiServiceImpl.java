package com.xfs.base.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.base.dao.BsAreabusiDao;
import com.xfs.base.model.BsAreabusi;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

@Service
public class BsAreabusiServiceImpl implements BsAreabusiService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BsAreabusiServiceImpl.class);
	
	@Autowired
	private BsAreabusiDao bsAreabusiDao;
	
	public int save(ContextInfo cti, BsAreabusi vo ){
		return bsAreabusiDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BsAreabusi vo ){
		return bsAreabusiDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BsAreabusi vo ){
		return bsAreabusiDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BsAreabusi vo ){
		return bsAreabusiDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BsAreabusi vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bsAreabusiDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BsAreabusi> datas = bsAreabusiDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BsAreabusi> findAll(BsAreabusi vo){
		
		List<BsAreabusi> datas = bsAreabusiDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/11 16:39:16
  
	/**
	 * 查询开通城市列表
	 * 
	 *  @return 		: java.util.List<com.xfs.base.model.BsAreabusi>
	 *  @createDate  	: 2016年11月15日 上午11:21:47
	 *  @author         : zhengdan@xinfushe.com
	 *  @version        : v1.0
	 *  @updateDate    	: 2016年11月15日 上午11:21:47
	 *  @updateAuthor  	:
	 */
	@Override
	public List<BsAreabusi> findOpenCityAll() {
		List<BsAreabusi> datas = bsAreabusiDao.findOpenCityAll();
		return datas;
	}

	/**
	 *  根据条件查询bsareabusi表
	 * 
	 *  @param   bsAreabusi  BsAreabusi对象
	 *  @return 		: com.xfs.base.modle.BsAreabusi
	 *  @createDate  	: 2016年11月15日 上午11:22:41
	 *  @author         : zhengdan@xinfushe.com
	 *  @version        : v1.0
	 *  @updateDate    	: 2016年11月15日 上午11:22:41
	 *  @updateAuthor  	:
	 */
	@Override
	public BsAreabusi getBsAreabusi(BsAreabusi bsAreabusi) {
		return bsAreabusiDao.getBsAreabusi(bsAreabusi);
	}

	@Override
	public BsAreabusi findBsAreabusiMax() {
		return bsAreabusiDao.findBsAreabusiMax();
	}
	
	/**
	 * 通过业务类型查询城市列表
	 * 
	 *  @param     vo     BsAreabusi对象    
	 *  @return 		: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate  	: 2016年11月15日 上午11:23:42
	 *  @author         : zhengdan@xinfushe.com
	 *  @version        : v1.0
	 *  @updateDate    	: 2016年11月15日 上午11:23:42
	 *  @updateAuthor  	:
	 */
	public List<Map<String, Object>> findAreasByBusi(BsAreabusi vo) {
		return bsAreabusiDao.findAreasByBusi(vo);
	}
	
}
