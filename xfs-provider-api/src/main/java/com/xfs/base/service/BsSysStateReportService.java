package com.xfs.base.service;

import java.util.List;
import java.util.Map;

import com.xfs.base.model.BsSysStateReport;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * @Author: quanjiahua@xinfushe.com
 * @Date: Created in 2017-05-03 10:34
 */
public interface BsSysStateReportService {
	
	public int save(ContextInfo cti, BsSysStateReport vo);
	public int insert(ContextInfo cti, BsSysStateReport vo);
	public int remove(ContextInfo cti, BsSysStateReport vo);
	public int update(ContextInfo cti, BsSysStateReport vo);
	public PageModel findPage(PageInfo pi, BsSysStateReport vo);
	public List<BsSysStateReport> findAll(BsSysStateReport vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/23 14:33:27
	
	public BsSysStateReport findByPK(BsSysStateReport vo);

    public Map findBsSysStateReport(BsSysStateReport obj);
    public int insertVersionMessage(ContextInfo cti, BsSysStateReport obj);
    public int updateVersionMessage(ContextInfo cti, BsSysStateReport obj);
}
