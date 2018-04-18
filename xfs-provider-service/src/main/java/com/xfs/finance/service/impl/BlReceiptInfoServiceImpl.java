package com.xfs.finance.service.impl;

import com.xfs.common.ContextInfo;
import com.xfs.common.IContextInfo;
import com.xfs.common.page.PageModel;
import com.xfs.common.page.SystemContext;
import com.xfs.finance.dao.BlReceiptInfoDao;
import com.xfs.finance.model.BlReceiptInfo;
import com.xfs.finance.service.BlReceiptInfoService;
import com.xfs.finance.vo.AccountDetailParam;
import com.xfs.finance.vo.BlReceiptDetailVo;
import com.xfs.finance.vo.BlReceiptFormRequest;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class BlReceiptInfoServiceImpl implements BlReceiptInfoService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BlReceiptInfoServiceImpl.class);
	
	@Autowired
	private BlReceiptInfoDao blReceiptInfoDao;
	
	public int save(IContextInfo info, BlReceiptInfo vo ){
		return blReceiptInfoDao.save(info,vo);
	}
	public int insert(IContextInfo info, BlReceiptInfo vo ){
		return blReceiptInfoDao.insert(info,vo);
	}
	public int remove(IContextInfo info, BlReceiptInfo vo ){
		return blReceiptInfoDao.remove(info,vo);
	}
	public int update(IContextInfo info, BlReceiptInfo vo ){
		return blReceiptInfoDao.update(info,vo);
	}
	public PageModel findPage(BlReceiptInfo vo){
		
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		Integer total = blReceiptInfoDao.countFreeFind(vo);
		pm.setPagesize(pageSize);
		pm.setTotal(total);
		List<BlReceiptInfo> datas = blReceiptInfoDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BlReceiptInfo> findAll(BlReceiptInfo vo){
		
		List<BlReceiptInfo> datas = blReceiptInfoDao.freeFind(vo);
		return datas;
		
	}

	@Override
	public void batchInsert(IContextInfo info, List list) {
		blReceiptInfoDao.batchInsert(info,list);
	}


	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/12/27 14:51:15


	@Override
	public List<BlReceiptInfo> findSerialsNoByVo(BlReceiptFormRequest formRequest) {
		return blReceiptInfoDao.queryForList("BL_RECEIPT_INFO.findSerialsNoByVo", formRequest);
	}

	@Override
	public List<BlReceiptDetailVo> findAccountDetails(AccountDetailParam param,List<Integer> items) {
		Map map = beanToMap(param);
		map.put("items",items);
		return blReceiptInfoDao.findAccountDetails(map);
	}

	@Override
	public Integer findAccountDetailsTotal(AccountDetailParam param) {
		return blReceiptInfoDao.findAccountDetailsTotal(beanToMap(param));
	}

	@Override
	public BigDecimal getTotalAmount() {
		return blReceiptInfoDao.getTotalAmount();
	}

	public Map<String, Object> beanToMap(Object bean){
		Map<String, Object> map = new HashMap<String, Object>(0);
		try {
			PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
			PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(bean);
			for (int i = 0; i < descriptors.length; i++) {
				String name = descriptors[i].getName();
				if (!"class".equals(name)) {
					Object object = propertyUtilsBean.getNestedProperty(bean, name);
					if(object != null && !object.equals(""))
						map.put(name, object);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}


	@Override
	public List<BlReceiptInfo> findSerialsByIds(List<Integer> ids) {
		return blReceiptInfoDao.queryForList("BL_RECEIPT_INFO.findSerialsByIds", ids);
	}

    @Override
    public int updateReceiptInfByCheck(ContextInfo contextInfo, BlReceiptInfo receiptInfo) {
        return blReceiptInfoDao.updateReceiptInfByCheck(contextInfo,receiptInfo);
    }

	@Override
	public List<Map> queryAllUnchecked() {
		return blReceiptInfoDao.queryAllUnchecked();
	}

	@Override
	public List<Map> queryAmountNoMatch() {
		return blReceiptInfoDao.queryAmountNoMatch();
	}

	@Override
	public List<Map> queryAccountNoMatch() {
		return blReceiptInfoDao.queryAccountNoMatch();
	}

	@Override
	public List<BlReceiptInfo> queryByTradeCode(List<String> list) {
		return blReceiptInfoDao.queryByTradeCode(list);
	}

	@Override
	public List<Integer> findAccountIds(AccountDetailParam param, Integer offset, Integer pageSize) {
		Map map = beanToMap(param);
		map.put("offset",offset);
		map.put("pageSize",pageSize);
		return blReceiptInfoDao.findAccountIds(map);
	}

	@Override
	public Integer findAccountIdsTotal(AccountDetailParam param, Integer offset, Integer pageSize) {
		Map map = beanToMap(param);
		map.put("offset",offset);
		map.put("pageSize",pageSize);
		return blReceiptInfoDao.findAccountIdsTotal(map);
	}
}
