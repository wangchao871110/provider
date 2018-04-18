package com.xfs.finance.service;

import com.xfs.common.IContextInfo;
import com.xfs.common.page.PageModel;
import com.xfs.finance.model.BlReceiptWriteOffInfo;

import java.util.List;
import java.util.Map;

/**
 * BlReceiptWriteOffInfoService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/12/27 14:51:16
 */
public interface BlReceiptWriteOffInfoService {
	
	public int save(IContextInfo info, BlReceiptWriteOffInfo vo);
	public int insert(IContextInfo info,BlReceiptWriteOffInfo vo);
	public int remove(IContextInfo info,BlReceiptWriteOffInfo vo);
	public int update(IContextInfo info,BlReceiptWriteOffInfo vo);
	public PageModel findPage(BlReceiptWriteOffInfo vo);
	public List<BlReceiptWriteOffInfo> findAll(BlReceiptWriteOffInfo vo);

    List<Map> checkRecordDetail(BlReceiptWriteOffInfo blReceiptWriteOffInfo);


    //温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/12/27 14:51:16


}
