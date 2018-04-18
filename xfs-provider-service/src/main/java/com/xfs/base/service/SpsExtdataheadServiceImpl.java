package com.xfs.base.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.base.dao.SpsExtdataheadDao;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.sp.model.SpsExtdatahead;

@Service
public class SpsExtdataheadServiceImpl implements SpsExtdataheadService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsExtdataheadServiceImpl.class);

	@Autowired
	private SpsExtdataheadDao spsExtdataheadDao;

	public int save(ContextInfo cti, SpsExtdatahead vo) {
		return spsExtdataheadDao.save(cti, vo);
	}

	public int insert(ContextInfo cti, SpsExtdatahead vo) {
		return spsExtdataheadDao.insert(cti, vo);
	}

	public int remove(ContextInfo cti, SpsExtdatahead vo) {
		return spsExtdataheadDao.remove(cti, vo);
	}

	public int update(ContextInfo cti, SpsExtdatahead vo) {
		return spsExtdataheadDao.update(cti, vo);
	}

	public PageModel findPage(PageInfo pi, SpsExtdatahead vo) {

		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsExtdataheadDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsExtdatahead> datas = spsExtdataheadDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;

	}

	public List<SpsExtdatahead> findAll(SpsExtdatahead vo) {

		List<SpsExtdatahead> datas = spsExtdataheadDao.freeFind(vo);
		return datas;

	}

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/05/13 11:44:25
	@Override
	public List<SpsExtdatahead> findHeadBySheetIdExttabIdName(SpsExtdatahead vo) {
		List<SpsExtdatahead> datas = spsExtdataheadDao.findHeadBySheetIdExttabIdName(vo);
		return datas;
	}
}
