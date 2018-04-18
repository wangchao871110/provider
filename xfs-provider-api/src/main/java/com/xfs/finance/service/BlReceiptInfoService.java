package com.xfs.finance.service;

import com.xfs.common.ContextInfo;
import com.xfs.common.IContextInfo;
import com.xfs.common.page.PageModel;
import com.xfs.finance.model.BlReceiptInfo;
import com.xfs.finance.vo.AccountDetailParam;
import com.xfs.finance.vo.BlReceiptDetailVo;
import com.xfs.finance.vo.BlReceiptFormRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * BlReceiptInfoService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/12/27 14:51:15
 */
public interface BlReceiptInfoService {
	
	public int save(IContextInfo info, BlReceiptInfo vo);
	public int insert(IContextInfo info,BlReceiptInfo vo);
	public int remove(IContextInfo info,BlReceiptInfo vo);
	public int update(IContextInfo info,BlReceiptInfo vo);
	public PageModel findPage(BlReceiptInfo vo);
	public List<BlReceiptInfo> findAll(BlReceiptInfo vo);
	public void batchInsert(IContextInfo info, List list);
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/12/27 14:51:15

	/**
	 * 表单查询流水号
	 * @param formRequest
	 * @return
	 */
	public List<BlReceiptInfo> findSerialsNoByVo(BlReceiptFormRequest formRequest);

	public List<BlReceiptInfo> findSerialsByIds(List<Integer> ids);


	int updateReceiptInfByCheck(ContextInfo contextInfo, BlReceiptInfo receiptInfo);

	public List<BlReceiptDetailVo> findAccountDetails(AccountDetailParam param,List<Integer> items);
	public Integer findAccountDetailsTotal(AccountDetailParam param);
	public BigDecimal getTotalAmount();
	public List<Map> queryAllUnchecked();
	public List<Map> queryAmountNoMatch();
	public List<Map> queryAccountNoMatch();
	public List<BlReceiptInfo> queryByTradeCode(List<String> list);
	public List<Integer> findAccountIds(AccountDetailParam param, Integer offset, Integer pageSize);
	public Integer findAccountIdsTotal(AccountDetailParam param, Integer offset, Integer pageSize);
}
