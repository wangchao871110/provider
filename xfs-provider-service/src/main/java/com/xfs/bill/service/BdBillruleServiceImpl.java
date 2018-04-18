package com.xfs.bill.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.base.model.BsArea;
import com.xfs.base.service.BsAreaService;
import com.xfs.bill.dao.BdBillruleDao;
import com.xfs.bill.model.BdBillrule;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

@Service
public class BdBillruleServiceImpl implements BdBillruleService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BdBillruleServiceImpl.class);

	@Autowired
	private BdBillruleDao bdBillruleDao;

	@Autowired
	private BsAreaService bsAreaService;

	public int save(ContextInfo cti, BdBillrule vo) {
		return bdBillruleDao.save(cti, vo);
	}

	public int insert(ContextInfo cti, BdBillrule vo) {
		return bdBillruleDao.insert(cti, vo);
	}

	public int remove(ContextInfo cti, BdBillrule vo) {
		return bdBillruleDao.remove(cti, vo);
	}

	public int update(ContextInfo cti, BdBillrule vo) {
		return bdBillruleDao.update(cti, vo);
	}

	public PageModel findPage(PageInfo pi, BdBillrule vo) {

		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bdBillruleDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BdBillrule> datas = bdBillruleDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;

	}

	public List<BdBillrule> findAll(BdBillrule vo) {

		List<BdBillrule> datas = bdBillruleDao.freeFind(vo);
		return datas;

	}

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/09/09 11:34:52

	@Override
	public BdBillrule findByAreaId(Integer areaId) {
		// TODO Auto-generated method stub
		List<BdBillrule> result;
		BdBillrule qry = new BdBillrule();
		qry.setAreaId(areaId);
		result = findAll(qry);
		if (result == null) {
			BsArea qryarea = new BsArea();
			qryarea.setAreaId(areaId);
			BsArea area = bsAreaService.findbyPK(qryarea);
			qry.setAreaId(area.getBelongCity());
			result = findAll(qry);
		}
		if (result != null && result.size() > 0) {
			return result.get(0);
		}
		//如果获取不到，给默认当月缴当月规则
		BdBillrule dft = new BdBillrule();
		dft.setFundRule(dft.fundRule_CURMONTH);
		dft.setInsuranceRule(dft.insuranceRule_CURMONTH);
		return dft;
	}

}
