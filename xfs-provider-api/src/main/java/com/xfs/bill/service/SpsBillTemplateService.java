package com.xfs.bill.service;

import java.util.List;
import java.util.Map;

import com.xfs.bill.model.SpsBillTempCorp;
import com.xfs.bill.model.SpsBillTemplate;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;

import net.sf.json.JSONObject;

/**
 * SpsBillTemplateService
 * @author:wangxs<wangxs@163.com>
 * @date:2016/12/27 14:06:22
 */
public interface SpsBillTemplateService {
	
	public int save(ContextInfo cti, SpsBillTemplate vo);
	public int insert(ContextInfo cti, SpsBillTemplate vo);
	public int remove(ContextInfo cti, SpsBillTemplate vo);
	public int update(ContextInfo cti, SpsBillTemplate vo);
	public PageModel findPage(SpsBillTemplate vo);
	public List<SpsBillTemplate> findAll(SpsBillTemplate vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/12/27 14:06:22
    public PageModel findSpsBillTemplateList(PageInfo pi, SpsBillTemplate vo,
                                             ContextInfo cti);


    public Integer  updateBillTemp(ContextInfo ci, SpsBillTemplate vo);

    public int removeLogic(ContextInfo cti, SpsBillTemplate vo);

    Result saveBillTemplate(ContextInfo cti, JSONObject paraOb);

    Result checkBillTemplate(SpsBillTemplate billTemplate);

    Result findBillTempInfo(SpsBillTemplate billTemplate);
    /**
     * 根据服务商 spId查询服务商模板
     * @param spId
     * @return
     */
    List<Map<String,Object>> checkModelTemplete(Integer spId);
    /**
     * 保存企业模板
     */
    Result saveCustTemp(ContextInfo cti,SpsBillTempCorp spsBillTempCorp);

    PageModel findPageUsedCorp(ContextInfo cti, PageInfo pageInfo, String name, Integer tempId);
}
