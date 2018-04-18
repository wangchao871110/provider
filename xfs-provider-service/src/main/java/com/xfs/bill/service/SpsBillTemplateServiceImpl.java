package com.xfs.bill.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.bill.dao.SpsBillTempBusinessFieldDao;
import com.xfs.bill.dao.SpsBillTempCorpDao;
import com.xfs.bill.dao.SpsBillTemplateDao;
import com.xfs.bill.model.SpsBillTempBusinessField;
import com.xfs.bill.model.SpsBillTempCorp;
import com.xfs.bill.model.SpsBillTemplate;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.common.page.SystemContext;

import net.sf.json.JSONObject;


@Service
public class SpsBillTemplateServiceImpl implements SpsBillTemplateService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsBillTemplateServiceImpl.class);

	@Autowired
	private SpsBillTemplateDao spsBillTemplateDao;

    @Autowired
    private SpsBillTempCorpDao spsBillTempCorpDao;

    @Autowired
    private SpsBillTempBusinessFieldDao spsBillTempBusinessFieldDao;


	public int save(ContextInfo cti, SpsBillTemplate vo ){
		return spsBillTemplateDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,SpsBillTemplate vo ){
		return spsBillTemplateDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti, SpsBillTemplate vo ){
		return spsBillTemplateDao.remove(cti, vo);
	}
	public int update(ContextInfo cti, SpsBillTemplate vo ){
		return spsBillTemplateDao.update(cti, vo);
	}
	public PageModel findPage(SpsBillTemplate vo){

		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		Integer total = spsBillTemplateDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsBillTemplate> datas = spsBillTemplateDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;

	}
	public List<SpsBillTemplate> findAll(SpsBillTemplate vo){

		List<SpsBillTemplate> datas = spsBillTemplateDao.freeFind(vo);
		return datas;

	}


	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/12/27 14:06:22

    public PageModel findSpsBillTemplateList(PageInfo pi, SpsBillTemplate vo, ContextInfo cti) {
        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        vo.setSpId(cti.getOrgId());
        // vo.setDr(0);// 0为有效员工
        // 数据权限
       /* String authority = sysFunctionDataService.getDataAuthorityByUser(cti.getUserId(), cti.getUserType(),
                Constant.DATATYPE);*/
        Integer total = spsBillTemplateDao.findBillTempCount(vo, null);
        pm.setTotal(total);
        List<Map<String, Object>> datas = spsBillTemplateDao.findBillTempList(vo, pageIndex, pageSize, null);

        if (datas != null && datas.size() > 0) {
           for (Map<String, Object> map: datas) {
               Integer id = Integer.parseInt(map.get("id").toString());
               Integer sp_id = Integer.parseInt(map.get("sp_id").toString());
               SpsBillTempCorp spsBillTempCorp = new SpsBillTempCorp();
               spsBillTempCorp.setTempId(id);
               spsBillTempCorp.setSpId(sp_id);
               spsBillTempCorp.setDr(0);
               Integer count = spsBillTempCorpDao.countFreeFind(spsBillTempCorp);
               map.put("corp_count", count);
           }
        }

        pm.setDatas(datas);
        return pm;
    }


    public Integer updateBillTemp(ContextInfo ci, SpsBillTemplate vo) {
      //  Result result = new Result();
        if ("Y".equals(vo.getIsDefault())) {
            SpsBillTemplate spsBillTemplate = new SpsBillTemplate();
            spsBillTemplate.setDr(0);
          //  spsBillTemplate.setId(vo.getId());
            spsBillTemplate.setSpId(ci.getOrgId());
            spsBillTemplate.setIsDefault("Y");
            SpsBillTemplate billTemp = null;
            List<SpsBillTemplate> billTempList = spsBillTemplateDao.freeFind(spsBillTemplate);
            if (billTempList.size() > 0) {
                billTemp = billTempList.get(0);
            }
            if (billTemp != null) {
                billTemp.setIsDefault("N");
                SpsBillTemplate billTemplateParam = new SpsBillTemplate();
                billTemplateParam.setDr(0);
                billTemplateParam.setIsDefault("Y");
                billTemplateParam.setSpId(ci.getOrgId());
                SpsBillTemplate defaultTemp = spsBillTemplateDao.freeFind(billTemplateParam).size() > 0 ? spsBillTemplateDao.freeFind(billTemplateParam).get(0): null;
                if (defaultTemp != null) {
                    defaultTemp.setIsDefault("N");
                    spsBillTemplateDao.update(ci, defaultTemp);
                }
                spsBillTemplateDao.update(ci, billTemp);
            }
        }
        int flag  = -1;
        SpsBillTemplate bt = spsBillTemplateDao.findByPK(vo);
        if (bt != null) {
            flag = spsBillTemplateDao.update(ci, vo);
        }

        return flag;
    }

    public int removeLogic(ContextInfo cti, SpsBillTemplate vo) {
        int flag  = -1;
        SpsBillTemplate bt = spsBillTemplateDao.findByPK(vo);
        if (bt != null) {
            flag = spsBillTemplateDao.removeLogic(cti, vo);
        }

        return flag;

    }


    public Result saveBillTemplate(ContextInfo cti, JSONObject paraObj) {
        Result result = new Result();
        String name = paraObj.getString("name");
        net.sf.json.JSONArray fieldIds = paraObj.getJSONArray("selectFields");
        net.sf.json.JSONArray existDataIds = paraObj.getJSONArray("existDataIds");

       // ArrayList<Integer> exist = (ArrayList<Integer>) (JSONArray.toArray(fieldIds, ArrayList.class));
        String isDefault = paraObj.getString("isDefault");
        List<Integer> existData = new ArrayList<Integer>();

        for (int i = 0; i < existDataIds.size(); i++) {
            String insuranceString = existDataIds.getString(i);
            existData.add(Integer.parseInt(insuranceString));
        }

        //保存账单模板
        SpsBillTemplate spsBillTemplate = new SpsBillTemplate();

        //是否是修改数据
        if (paraObj.containsKey("temp_id")) {
            Integer id = paraObj.getInt("temp_id");
            spsBillTemplate.setId(id);
            SpsBillTemplate existTemp = spsBillTemplateDao.findByPK(spsBillTemplate);
            spsBillTemplate.setIsEffect(existTemp.getIsEffect());

        } else {
            //新增则设置为有效
            spsBillTemplate.setIsEffect("Y");
        }

        if ("Y".equals(isDefault)) {

            SpsBillTemplate defaultBillTemplate = new SpsBillTemplate();
            defaultBillTemplate.setDr(0);
            defaultBillTemplate.setSpId(cti.getOrgId());
            List<SpsBillTemplate> list = spsBillTemplateDao.freeFind(defaultBillTemplate);
            if(list.size() > 0) {
                defaultBillTemplate = list.get(0);
                defaultBillTemplate.setIsDefault("N");
                spsBillTemplateDao.update(cti, defaultBillTemplate);
            }

        }

        spsBillTemplate.setName(name);
        spsBillTemplate.setIsDefault(isDefault);
        spsBillTemplate.setDr(0);
        spsBillTemplate.setSpId(cti.getOrgId());
        int isSuccess = spsBillTemplateDao.save(cti, spsBillTemplate);


        isSuccess = spsBillTempBusinessFieldDao.logicDelSpsBillTempField(cti, spsBillTemplate.getId());

        for (int i = 0; i < fieldIds.size(); i++) {
            Integer fieldId = Integer.parseInt(fieldIds.getString(i));
            SpsBillTempBusinessField spsBillTempBusinessField = new SpsBillTempBusinessField();
            spsBillTempBusinessField.setDr(0);
            spsBillTempBusinessField.setFieldId(fieldId);
            spsBillTempBusinessField.setTempId(spsBillTemplate.getId());
            spsBillTempBusinessFieldDao.insert(cti,spsBillTempBusinessField);
        }

        if (isSuccess > -1) {

            isSuccess = spsBillTempCorpDao.logicRemove(cti,spsBillTemplate.getId(), existData);
            if(existData.size() > 0) {
                isSuccess = spsBillTempCorpDao.logicRemoveSelf(cti,existData);
            }

            if (isSuccess > -1) {
                for (Integer cpId : existData) {
                    SpsBillTempCorp spsBillTempCorp = new SpsBillTempCorp();
                    spsBillTempCorp.setDr(0);
                    spsBillTempCorp.setSpId(cti.getOrgId());
                    spsBillTempCorp.setCpId(cpId);
                    spsBillTempCorp.setTempId(spsBillTemplate.getId());
                    spsBillTempCorpDao.insert(cti, spsBillTempCorp);
                }
            }
        }

        if (isSuccess > -1) {
            result.setSuccess(true);
        } else {
            result.setSuccess(false);
        }
        return result;
    }


    public Result checkBillTemplate(SpsBillTemplate billTemplate) {
        Result result = new Result().setSuccess(true);
        if (StringUtils.isBlank(billTemplate.getName())) {
            result.setSuccess(false).setError("模板名称不能为空");
            return result;
        }


        billTemplate.setNameEq(billTemplate.getName());
        billTemplate.setName(null);
        List<SpsBillTemplate> list = spsBillTemplateDao.freeFind(billTemplate);
        if (list.size() > 0) {
            result.setSuccess(false).setError("该模板已存在");
            return result;
        }

        return result;
    }

    public Result findBillTempInfo(SpsBillTemplate billTemplate) {
        Result result = new Result().setSuccess(true);
        SpsBillTemplate spsBillTemplate = spsBillTemplateDao.findByPK(billTemplate);
        result.setDataValue("billTemp", spsBillTemplate);
        SpsBillTempCorp spsBillTempCorp = new SpsBillTempCorp();
        spsBillTempCorp.setTempId(spsBillTemplate.getId());
        spsBillTempCorp.setSpId(billTemplate.getSpId());
        spsBillTempCorp.setDr(0);

        List<String> list = spsBillTempCorpDao.FreeFindSPS_BILL_TEMP_CORP_Ids(spsBillTempCorp);
        result.setDataValue("allNumList", list);
        SpsBillTempBusinessField spsBillTempBusinessField = new SpsBillTempBusinessField();
        spsBillTempBusinessField.setDr(0);
        spsBillTempBusinessField.setTempId(spsBillTemplate.getId());
        List<String>  fieldIds = spsBillTempBusinessFieldDao.FreeFindSPS_BILL_TEMP_FIELD_Ids(spsBillTempBusinessField);
        result.setDataValue("fieldIds", fieldIds);
        return result;
    }
    /**
     * 选择模板
     */
	@Override
	public List<Map<String,Object>> checkModelTemplete(Integer spId) {
		return spsBillTemplateDao.findBillTempListBySpId(spId);
	}
	/**
	 * 保存企业模板
	 */
	@Override
	public Result saveCustTemp(ContextInfo cti,SpsBillTempCorp spsBillTempCorp) {
		Result result = Result.createResult().setSuccess(false);
		spsBillTempCorp.setDr(0);
		SpsBillTempCorp spsBillTempCorps = spsBillTempCorpDao.dataExisits(spsBillTempCorp);
		if(null != spsBillTempCorps){
			spsBillTempCorp.setId(spsBillTempCorps.getId());
			spsBillTempCorpDao.update(cti, spsBillTempCorp);
			result.setSuccess(true);
		}else{
			spsBillTempCorpDao.insert(cti, spsBillTempCorp);
			result.setSuccess(true);
		}
		return result;
	}
	  public PageModel findPageUsedCorp(ContextInfo cti, PageInfo pi, String name, Integer tempId) {
	        PageModel pm = new PageModel(pi);
	        int pageIndex = pi.getOffset();
	        int pageSize = pi.getPagesize();
	      //  vo.setSpId(cti.getOrgId());
	        // vo.setDr(0);// 0为有效员工
	        // 数据权限
	       /* String authority = sysFunctionDataService.getDataAuthorityByUser(cti.getUserId(), cti.getUserType(),
	                Constant.DATATYPE);*/
	        Integer total = spsBillTemplateDao.Find_USED_List_By_Condition_Count(cti, name, tempId, null);
	        pm.setTotal(total);
	        List<Map<String, Object>> datas = spsBillTemplateDao.Find_USED_List_By_Condition(cti, name, tempId, pageIndex, pageSize, null);
	        pm.setDatas(datas);
	        return pm;
	    }
}
