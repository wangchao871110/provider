package com.xfs.base.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.base.dao.SecWxDao;
import com.xfs.base.model.SecWx;
import com.xfs.common.ContextInfo;
//import com.xfs.bs.service.impl.SecWxServiceImpl;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

@Service
public class SecWxServiceImpl implements SecWxService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SecWxServiceImpl.class);

	@Autowired
	private SecWxDao secWxDao;

	/**
	 * 
	 * @param vo   SecWx对象
	 * @return 
	 * @createDate   : 2016年11月15日 上午10:49:03
	 * @author 		 : zhengdan@xinfushe.com
	 * @version 	 : v1.0
	 * @updateDate   : 2016年11月15日 上午10:49:03
	 * @updateAuthor :
	 */
	public void save(ContextInfo cti, SecWx vo) {
		secWxDao.save(cti, vo);
	}

	/**
	 * 
	 * @param   vo
	 * @return       : java.lang.Integer
	 * @createDate   : 2016年11月15日 上午10:50:18
	 * @author       : zhengdan@xinfushe.com
	 * @version      : v1.0
	 * @updateDate   : 2016年11月15日 上午10:50:18
	 * @updateAuthor :
	 */
	public int save1(ContextInfo cti, SecWx vo) {
		return secWxDao.save1(cti, vo);
	}

	/**
	 * 
	 * @param    vo  SecWx对象
	 * @return   
	 * @createDate   : 2016年11月15日 上午10:51:30
	 * @author 		 : zhengdan@xinfushe.com
	 * @version      : v1.0
	 * @updateDate   : 2016年11月15日 上午10:51:30
	 * @updateAuthor :
	 */
	public void insert(ContextInfo cti, SecWx vo) {
		secWxDao.insert(cti, vo);
	}

	public int insert1(ContextInfo cti, SecWx vo) {
		return secWxDao.insert1(cti, vo);
	}

	public void remove(ContextInfo cti, SecWx vo) {
		secWxDao.remove(cti, vo);
	}

	public int remove1(ContextInfo cti, SecWx vo) {
		return secWxDao.remove1(cti, vo);
	}

	public void update(ContextInfo cti, SecWx vo) {
		secWxDao.update(cti, vo);
	}

	public int update1(ContextInfo cti, SecWx vo) {
		return secWxDao.update1(cti, vo);
	}

	public PageModel findPage(PageInfo pi, SecWx vo) {

		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = secWxDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SecWx> datas = secWxDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;

	}

	public List<SecWx> findAll(SecWx vo) {

		List<SecWx> datas = secWxDao.freeFind(vo);
		return datas;

	}

	@Override
	public SecWx findByPK(SecWx vo) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 根据id查询 微信号管理表
	 * 
	 *  @param vo
	 *  @return         : com.xfs.base.modle.SecWx
	 *  @createDate  	: 2016年11月15日 上午10:53:21
	 *  @author         : zhengdan@xinfushe.com
	 *  @version        : v1.0
	 *  @updateDate    	: 2016年11月15日 上午10:53:21
	 *  @updateAuthor  	:
	 */
	@Override
	public SecWx findByPK1(SecWx vo) {
		return secWxDao.findByPK(vo);
	}

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/01/25 15:21:28

}
