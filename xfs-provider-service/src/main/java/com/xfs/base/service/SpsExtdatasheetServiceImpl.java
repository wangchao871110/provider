package com.xfs.base.service;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.base.dao.SpsExtdataheadDao;
import com.xfs.base.dao.SpsExtdatasheetDao;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.sp.model.SpsExtdatahead;
import com.xfs.sp.model.SpsExtdatasheet;

@Service
public class SpsExtdatasheetServiceImpl implements SpsExtdatasheetService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsExtdatasheetServiceImpl.class);

	@Autowired
	private SpsExtdatasheetDao spsExtdatasheetDao;
	@Autowired
	private SpsExtdataheadDao spsExtdataheadDao;

	public int save(ContextInfo cti, SpsExtdatasheet vo) {
		return spsExtdatasheetDao.save(cti, vo);
	}

	public int insert(ContextInfo cti, SpsExtdatasheet vo) {
		return spsExtdatasheetDao.insert(cti, vo);
	}

	public int remove(ContextInfo cti, SpsExtdatasheet vo) {
		return spsExtdatasheetDao.remove(cti, vo);
	}

	public int update(ContextInfo cti, SpsExtdatasheet vo) {
		return spsExtdatasheetDao.update(cti, vo);
	}

	public PageModel findPage(PageInfo pi, SpsExtdatasheet vo) {

		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsExtdatasheetDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsExtdatasheet> datas = spsExtdatasheetDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;

	}

	public List<SpsExtdatasheet> findAll(SpsExtdatasheet vo) {

		List<SpsExtdatasheet> datas = spsExtdatasheetDao.freeFind(vo);
		return datas;

	}

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/05/17 19:55:26
	@Override
	public LinkedHashMap<SpsExtdatasheet, Object> findSheetByExcelId(Integer excelId) {
		SpsExtdatahead headModel = new SpsExtdatahead();
		List<SpsExtdatasheet> sheetList = spsExtdatasheetDao.findSheetByExcelId(excelId);
		LinkedHashMap<SpsExtdatasheet, Object> dataMap = new LinkedHashMap<>();
		for (int i = 0; i < sheetList.size(); i++) {
			// headModel.setExttabId(sheetList.get(i).getExttabId());// excelId
			headModel.setSheetId(sheetList.get(i).getId());// sheetId
			headModel.setName(sheetList.get(i).getName());// sheetName
			List<SpsExtdatahead> lists = spsExtdataheadDao.findHeadBySheetIdExttabIdName(headModel);
			dataMap.put(sheetList.get(i), lists);
		}
		return dataMap;
	}
}
