package com.xfs.finance.service.impl;

import com.xfs.common.IContextInfo;
import com.xfs.common.page.PageModel;
import com.xfs.common.page.SystemContext;
import com.xfs.finance.dao.BlReceiptWriteOffInfoDao;
import com.xfs.finance.model.BlReceiptWriteOffInfo;
import com.xfs.finance.service.BlReceiptWriteOffInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class BlReceiptWriteOffInfoServiceImpl implements BlReceiptWriteOffInfoService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BlReceiptWriteOffInfoServiceImpl.class);
	
	@Autowired
	private BlReceiptWriteOffInfoDao blReceiptWriteOffInfoDao;
	
	public int save(IContextInfo info, BlReceiptWriteOffInfo vo ){
		return blReceiptWriteOffInfoDao.save(info,vo);
	}
	public int insert(IContextInfo info, BlReceiptWriteOffInfo vo ){
		return blReceiptWriteOffInfoDao.insert(info,vo);
	}
	public int remove(IContextInfo info, BlReceiptWriteOffInfo vo ){
		return blReceiptWriteOffInfoDao.remove(info,vo);
	}
	public int update(IContextInfo info, BlReceiptWriteOffInfo vo ){
		return blReceiptWriteOffInfoDao.update(info,vo);
	}
	public PageModel findPage(BlReceiptWriteOffInfo vo){
		
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		Integer total = blReceiptWriteOffInfoDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BlReceiptWriteOffInfo> datas = blReceiptWriteOffInfoDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BlReceiptWriteOffInfo> findAll(BlReceiptWriteOffInfo vo){
		
		List<BlReceiptWriteOffInfo> datas = blReceiptWriteOffInfoDao.freeFind(vo);
		return datas;
		
	}

    @Override
    public List<Map> checkRecordDetail(BlReceiptWriteOffInfo vo) {
        return blReceiptWriteOffInfoDao.checkRecordDetail(vo);
    }


    //温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/12/27 14:51:16
	
	
	
}
