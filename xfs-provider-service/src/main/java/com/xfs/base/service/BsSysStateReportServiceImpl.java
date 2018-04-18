package com.xfs.base.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.base.dao.BsSysStateReportDao;
import com.xfs.base.model.BsSysStateReport;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * @Author: quanjiahua@xinfushe.com
 * @Date: Created in 2017-05-03 10:38
 */
@Service
public class BsSysStateReportServiceImpl implements BsSysStateReportService {

    @Autowired
    BsSysStateReportDao bsSysStateReportDao;
    
    public int save(ContextInfo cti, BsSysStateReport vo ){
		return bsSysStateReportDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BsSysStateReport vo ){
		return bsSysStateReportDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BsSysStateReport vo ){
		return bsSysStateReportDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BsSysStateReport vo ){
		return bsSysStateReportDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BsSysStateReport vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bsSysStateReportDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BsSysStateReport> datas = bsSysStateReportDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BsSysStateReport> findAll(BsSysStateReport vo){
		
		List<BsSysStateReport> datas = bsSysStateReportDao.freeFind(vo);
		return datas;
		
	}
	@Override
	public BsSysStateReport findByPK(BsSysStateReport vo) {
		return bsSysStateReportDao.findByPK(vo);
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/23 14:33:27

    public Map findBsSysStateReport(BsSysStateReport obj){
        obj.setDr(0);
        return bsSysStateReportDao.findBsSysStateReport(obj);
    }


    public int insertVersionMessage(ContextInfo cti, BsSysStateReport obj) {

        return bsSysStateReportDao.insertVersionMessage(cti, obj);
    }

    public int updateVersionMessage(ContextInfo cti, BsSysStateReport obj) {
        return bsSysStateReportDao.updateVersionMessage(cti, obj);
    }
}
