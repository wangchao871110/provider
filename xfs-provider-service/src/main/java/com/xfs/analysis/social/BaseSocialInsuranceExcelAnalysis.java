package com.xfs.analysis.social;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.xfs.acc.dto.*;
import com.xfs.analysis.AbstractExcelAnalysis;
import com.xfs.analysis.model.AnalysisConfig;
import com.xfs.analysis.model.AnalysisData;
import com.xfs.analysis.model.AnalysisResult;
import com.xfs.analysis.model.AnalysisTitle;
import com.xfs.analysis.utils.DateFormatUtil;
import com.xfs.analysis.utils.ImageUtil;
import com.xfs.aop.task.cs.TaskBotApi;
import com.xfs.base.model.BsArea;
import com.xfs.base.service.BsAreaService;
import com.xfs.base.service.SysUploadfileService;
import com.xfs.business.dto.BsTypeAreaFiledDto;
import com.xfs.business.enums.BsType;
import com.xfs.business.model.SpsFixedpointhospital;
import com.xfs.business.model.SpsTask;
import com.xfs.business.service.BdBsareatypeService;
import com.xfs.business.service.SpsFixedpointhospitalService;
import com.xfs.common.ContextInfo;
import com.xfs.common.aliyun.AliyunImageUtil;
import com.xfs.common.redies.RedisUtil;
import com.xfs.common.redies.keychain.IRedisKeys;
import com.xfs.common.util.DateUtil;
import com.xfs.common.util.StringUtils;
import com.xfs.corp.service.CmCorpService;
import com.xfs.corp.service.CmEmployeeService;
import com.xfs.sp.dto.HospitalDto;
import com.xfs.sp.model.SpsScheme;
import com.xfs.sp.service.SpsAccountService;
import com.xfs.sp.service.SpsSchemeService;
import com.xfs.ts.service.SpsTsCitycodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2017-03-22 16:48
 */
public abstract class BaseSocialInsuranceExcelAnalysis extends AbstractExcelAnalysis implements SocialInsurance , IRedisKeys {

    private static Logger log = LoggerFactory.getLogger(BaseSocialInsuranceExcelAnalysis.class);

    @Autowired
    SpsSchemeService spsSchemeService;

    @Autowired
    BdBsareatypeService bdBsareatypeService;

    @Autowired
    CmEmployeeService cmEmployeeService;

    @Autowired
    SocialInsuranceConfig socialInsuranceConfig;

    @Autowired
    BsAreaService bsAreaService;

    @Autowired
    SpsAccountService spsAccountService;

    @Autowired
    CmCorpService cmCorpService;

    @Autowired
    SysUploadfileService sysUploadfileService;

    @Autowired
    SpsTsCitycodeService spsTsCitycodeService;

    @Autowired
    SpsFixedpointhospitalService spsFixedpointhospitalService;

    /**
     * 类内部常量
     */
    /* 解析类别 -- 子类解析必须确定解析范围 */
    public static final String SOCIAL = "SOCIAL";

    /* 内存上下文变量Mapkey */
    public static final String cache_key_initContext = "initContext";

    public static final String cache_key_bstypeId = "bstypeId";

    public static final String cache_key_bstype_id = "bstype_id";

    public static final String cache_key_areaName = "areaName";

    public static final String cache_key_index = "index";

    public static final String cache_key_insuranceLiveType = "insuranceLiveType";

    public static final String cache_key_identityType = "identityType";

    public static final String cache_key_Name = "Name";

    public static final String cache_key_name = "name";

    public static final String cache_key_areaId = "areaId";

    public static final String cache_key_NOTASK = "NOTASK";

    public static final String cache_key_taskJson = "taskJson";

    public static final String cache_key_json = "json";

    public static final String cache_key_beginDate = "beginDate";

    public static final String cache_key_schemeList = "schemeList";

    public static final String cache_key_bstypefileds = "bstypefileds";

    public static final String cache_key_spsTsCitycodeMap = "spsTsCitycodeMap";

    public static final String cache_key_spsTsCityBsTypeMap = "spsTsCityBsTypeMap";

    public static final String cache_key_area_id = "area_id";

    public static final String cache_key_hosiptalNames = "hosiptalNames";

    public static final String cache_key_hosiptalFullNames = "hosiptalFullNames";

    public static final String cache_key_identityCode = "identityCode";

    public static final String cache_key_insuranceStateName = "insuranceStateName";

    public static final String cache_key_insuranceState = "insuranceState";

    public static final String cache_key_identityTypeName = "identityTypeName";

    public static final String cache_key_insuranceAccountId = "insuranceAccountId";

    public static final String cache_key_fundAccountId = "fundAccountId";

    public static final String cache_key_insurancePeriod = "insurancePeriod";

    public static final String cache_key_fundPeriod = "fundPeriod";

    public static final String cache_key_fundState = "fundState";

    public static final String cache_key_fundStateName = "fundStateName";

    public static final String cache_key_insurancebase = "insurancebase";

    public static final String cache_key_insuranceSalary = "insuranceSalary";

    public static final String cache_key_fundSalary = "fundSalary";

    public static final String cache_key_supplementarybegindate = "supplementarybegindate";

    public static final String cache_key_supplementaryenddate = "supplementaryenddate";

    public static final String cache_key_insuranceEndDate = "insuranceEndDate";

    public static final String cache_key_fundEndDate = "fundEndDate";

    public static final String cache_key_beforeSalary = "beforeSalary";

    /**
     * 实现类注册事件
     */
    @PostConstruct
    public void registEvent(){
        socialInsuranceConfig.addEventObject(this);
    }

    /**
     *  解析类别 -- 子类解析必须确定解析范围
     *	@return 			: String
     *  @createDate  	: 2017-03-21 14:28
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-03-21 14:28
     *  @updateAuthor  :
     */
    @Override
    public String analysisType() {
        return SOCIAL;
    }


    /**
     *  当前业务类型
     *  @param
     *	@return 			: java.util.List<com.xfs.business.enums.BsType>
     *  @createDate  	: 2017-04-21 17:39
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-04-21 17:39
     *  @updateAuthor  :
     */
    public abstract List<BsType> supportBsTypes();


    /**
     *  获取任务单执行时间
     *  @param   cti, bsTypeId, analysisDataMap
     *	@return 			: com.xfs.business.model.SpsTask
     *  @createDate  	: 2017-04-21 17:43
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-04-21 17:43
     *  @updateAuthor  :
     */
    public abstract String queryBeginPeriodBsTypeTask(ContextInfo cti, Integer bsTypeId,Map<String,AnalysisData> analysisDataMap);

    /**
     *  做业务解析
     *  @param   analysisResult
     *	@return 			: void
     *  @createDate  	: 2017-03-21 17:36
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-03-21 17:36
     *  @updateAuthor  :
     */
    @Override
    public void analysisBusiness(ContextInfo cti, AnalysisConfig analysisConfig, AnalysisResult analysisResult, List<File> notExcelFiles,Map<String,Object> ext) {
        //参数上下文
        Map<String,Object> context;
        if(null != ext && null != ext.get(cache_key_initContext)){
            context = (Map<String,Object>)ext.get(cache_key_initContext);
        }else{
            context = new HashMap<>();
            initContext(cti,context);
        }
        //扩展字段
        if(null != ext && !ext.isEmpty())
            context.putAll(ext);
        if(null == analysisResult.getDataList() || analysisResult.getDataList().isEmpty())
            return;
        if(null == ext){
            ext = new HashMap<>();
        }
        for(Map<String, AnalysisData> analysisDataMap : analysisResult.getDataList()){
            /**
             * 判断需要使用的解析类
             */
            String key = analysisDataMap.get(cache_key_bstypeId).getAnalysisValue();
            SocialInsurance socialInsuranceHandle = null;
            if(null != ext.get(key)){
                socialInsuranceHandle = (SocialInsurance)ext.get(key);
            }else{
                for(SocialInsurance socialInsurance : socialInsuranceConfig.queryEventList()){
                    if(!analysisDataMap.isEmpty() && socialInsurance.checkBsType(analysisDataMap)){
                        socialInsuranceHandle = socialInsurance;
                        ext.put(key,socialInsurance);
                        break;
                    }
                }
            }
            if(analysisDataMap.isEmpty() || null == socialInsuranceHandle)
                continue;
            try{
                /**
                 * 校验业务数据合法性
                 */
                socialInsuranceHandle.checkDataVerify(cti,analysisConfig,analysisDataMap,context,analysisResult.getTitles());
                if(analysisDataMap.isEmpty())
                    continue;
                /**
                 * 清洗业务数据
                 */
                socialInsuranceHandle.handleSocialInsurance(cti,analysisDataMap,context,analysisResult.getTitles(),notExcelFiles);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        /**
         * 剔除空值
         */
        Iterator<Map<String, AnalysisData>> iterator = analysisResult.getDataList().iterator();
        while (iterator.hasNext()){
            Map<String, AnalysisData> analysisDataMap = iterator.next();
            if(null == analysisDataMap || analysisDataMap.isEmpty())
                iterator.remove();
        }

        /**
         * 排序
         */
        Collections.sort(analysisResult.getDataList(),new Comparator<Map<String,AnalysisData>>() {
            @Override
            public int compare(Map<String, AnalysisData> o1, Map<String, AnalysisData> o2) {
            	if(null == o1 || null == o2 || null==o2.get(cache_key_areaName) || StringUtils.isBlank(o2.get(cache_key_areaName).getAnalysisValue()) || null==o1.get(cache_key_areaName) || StringUtils.isBlank(o1.get(cache_key_areaName).getAnalysisValue())){
            		return -1;
            	}
                //这里按照名字排序
                if(String.valueOf(o2.get(cache_key_areaName).getAnalysisValue()).compareTo(String.valueOf(o1.get(cache_key_areaName).getAnalysisValue())) == 0){
                    return String.valueOf(o2.get(cache_key_index).getAnalysisValue()).compareTo(String.valueOf(o1.get(cache_key_index).getAnalysisValue()));
                }else{
                    Collator instance = Collator.getInstance(Locale.CHINA);
                    return instance.compare(o1.get(cache_key_areaName).getAnalysisValue(),o2.get(cache_key_areaName).getAnalysisValue());
                }
            }
        });

        for(AnalysisTitle title : analysisResult.getTitles()){
            if(title.getName().equals(cache_key_insuranceLiveType)){
                title.setName(title.getName()+cache_key_Name);
            }
            if(title.getName().equals(cache_key_identityType)){
                title.setName(title.getName()+cache_key_Name);
            }
        }
    }


    /**
     *  处理单条数据业务逻辑
     *  @param   cti, analysisDataMap
     *	@return 			: void
     *  @createDate  	: 2017-03-23 14:18
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-03-23 14:18
     *  @updateAuthor  :
     */
    @Override
    public void handleSocialInsurance(ContextInfo cti, Map<String, AnalysisData> analysisDataMap,Map<String,Object> context,List<AnalysisTitle> titles,List<File> notExcelFiles) {
        //处理解析对应的标准模版数据、员工属性数据、任务单共有属性数据
        handleBaseFileds(cti,analysisDataMap,context);
        if(null == analysisDataMap || analysisDataMap.isEmpty() || null == analysisDataMap.get(cache_key_areaName)) {
            analysisDataMap.clear();
            return;
        }
        //构建任务单社保增员、公积金增员
        List<SpsTask> spsTaskList = new ArrayList<>();
        Map<String,Object> data = new HashMap<>();
        for(Map.Entry<String,AnalysisData> analysis : analysisDataMap.entrySet()){
            data.put(analysis.getKey(),analysis.getValue().getAnalysisValue());
        }
        Map<String,Object> spsTsCitycodeMap = (Map<String,Object>)context.get("spsTsCitycodeMap");
        for(BsType  bsType : supportBsTypes()){
            //加if根据bsType判断该城市是否支持业务类型   context中初始化所有支持城市业务类型（社保、公积金）map<areaId, insurance_fund>
            if(null == analysisDataMap.get(cache_key_areaId) || StringUtils.isBlank(analysisDataMap.get(cache_key_areaId).getAnalysisValue()))
                continue;
            Object cityCodeObj = spsTsCitycodeMap.get(analysisDataMap.get(cache_key_areaId).getAnalysisValue());
            String cityCode = cityCodeObj!=null ? cityCodeObj.toString() : null;

            if(("THREE".equals(cityCode) || bsType.getInsurance_fund_type().equals(cityCode) || cityCode.contains(bsType.getInsurance_fund_type()))){
                Map<String,Object> taskJsonMap = handleBsTypeTaskJson(cti,analysisDataMap,context,bsType.code(),titles,notExcelFiles);
                //序列化实体
                TypeReference<SpsTask> taskRef = new TypeReference<SpsTask>(){};
                String json = JSON.toJSONString(data);
                SpsTask task = JSON.parseObject(json,taskRef);
                task.setBstypeId(bsType.code());
                String taskJson = JSON.toJSONString(taskJsonMap);
                if(StringUtils.isNotBlank(cti.getLoginXfb()) && cache_key_NOTASK.equals(cti.getLoginXfb()))
                    analysisDataMap.put(cache_key_json,new AnalysisData(cache_key_json,taskJson));
                if(null != taskJsonMap && !taskJsonMap.isEmpty() && StringUtils.isNotBlank(taskJson)){
                    setEmpBsState(cti,analysisDataMap,context,bsType);
                    task.setJson(taskJson);
                    spsTaskList.add(task);
                    task.setBeginDate(String.valueOf(taskJsonMap.get(cache_key_beginDate)));
                }
            }
        }
        if(StringUtils.isNotBlank(cti.getLoginXfb()) && cache_key_NOTASK.equals(cti.getLoginXfb()))
            return;
        //序列化json
        analysisDataMap.put(cache_key_taskJson,new AnalysisData(cache_key_taskJson,JSON.toJSONString(spsTaskList)));
    }


    /**
     *  初始化上下文
     *  @param   cti
     *	@return 			: java.util.Map<java.lang.String,java.lang.Object>
     *  @createDate  	: 2017-03-24 09:30
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-03-24 09:30
     *  @updateAuthor  :
     */
    @Override
    public void initContext(ContextInfo cti,Map<String,Object> context){
        //获取当前用户下的企业方案列表
        List<SpsScheme> schemeList = spsSchemeService.findSchemeByCpId(cti);
        context.put(cache_key_schemeList,schemeList);
        //获取当前企业下所有用户
//        if(null != cti && null != cti.getOrgId()){
//            Map<String,CmEmployee> corpEmps = cmEmployeeService.queryCorpAllEmps(cti);
//            context.put("corpEmps",corpEmps);
//        }
        //业务参保taskbot所需字段
        Object ob = RedisUtil.get(SOCIAL_ANALYSIS_INITCONTEXT_AREATYPE_FILEDS_G);
        if(null != ob){
            context.put(cache_key_bstypefileds,(Map<String,List<BsTypeAreaFiledDto>>)ob);
        }else{
            List<BsTypeAreaFiledDto> bsTypeFiledsMap = bdBsareatypeService.findBdBsAreaTypeByAreaId();
            Map<String,List<BsTypeAreaFiledDto>> areaBsTypeMap = new HashMap<>();
            for(BsTypeAreaFiledDto bsTypeAreaFiledDto : bsTypeFiledsMap){
                String key = bsTypeAreaFiledDto.getBstypeId() + "_" + bsTypeAreaFiledDto.getAreaId();
                if(null == areaBsTypeMap.get(key))
                    areaBsTypeMap.put(key,new ArrayList<BsTypeAreaFiledDto>());
                areaBsTypeMap.get(key).add(bsTypeAreaFiledDto);
            }

            for(Map.Entry<String,List<BsTypeAreaFiledDto>> entry : areaBsTypeMap.entrySet()){
                String key = entry.getKey();
                List<BsTypeAreaFiledDto> bsTypeFiledsMaps = entry.getValue();
                if(null != bsTypeFiledsMaps && !bsTypeFiledsMaps.isEmpty()) {
                    BsTypeAreaFiledDto dto = new BsTypeAreaFiledDto();
                    dto.setAreaId(key.split("_")[1]);
                    dto.setCode(cache_key_insuranceLiveType);
                    dto.setName("户口性质");
                    dto.setType("PULL");
                    if(key.split("_")[0].equals(String.valueOf(BsType.INSUR_PAYBASE.getCode()))){
                        dto.setBstypeId(String.valueOf(BsType.INSUR_BACK.getCode()));
                        bsTypeFiledsMaps.add(dto);
                    }
                    if(key.split("_")[0].equals(String.valueOf(BsType.FUND_PAYBASE.getCode()))){
                        dto.setBstypeId(String.valueOf(BsType.FUND_PAYBASE.getCode()));
                        bsTypeFiledsMaps.add(dto);
                    }
                    if(key.split("_")[0].equals(String.valueOf(BsType.INSUR_BACK.getCode()))){
                        dto.setBstypeId(String.valueOf(BsType.INSUR_BACK.getCode()));
                        bsTypeFiledsMaps.add(dto);
                    }
                    if(key.split("_")[0].equals(String.valueOf(BsType.FUND_BACK.getCode()))){
                        dto.setBstypeId(String.valueOf(BsType.FUND_BACK.getCode()));
                        bsTypeFiledsMaps.add(dto);
                    }
                    if(key.split("_")[0].equals(String.valueOf(BsType.REDUCE_INSURANCE.getCode()))){
                        dto.setBstypeId(String.valueOf(BsType.REDUCE_INSURANCE.getCode()));
                        bsTypeFiledsMaps.add(dto);
                    }
                    if(key.split("_")[0].equals(String.valueOf(BsType.QUIT_INSURANCE.getCode()))){
                        dto.setBstypeId(String.valueOf(BsType.QUIT_INSURANCE.getCode()));
                        bsTypeFiledsMaps.add(dto);
                    }
                    if(key.split("_")[0].equals(String.valueOf(BsType.ADD_INSURANCE.getCode()))){
                        dto.setBstypeId(String.valueOf(BsType.ADD_INSURANCE.getCode()));
                        bsTypeFiledsMaps.add(dto);
                    }
                }
            }
            context.put(cache_key_bstypefileds,areaBsTypeMap);
            RedisUtil.set(SOCIAL_ANALYSIS_INITCONTEXT_AREATYPE_FILEDS_G,areaBsTypeMap,7200);
        }

        //context中初始化所有支持城市业务类型（社保、公积金）map<areaId, insurance_fund>
        Object cityCodes = RedisUtil.get(SOCIAL_ANALYSIS_INITCONTEXT_AREATYPE_TSCITYCODES);
        if(null != cityCodes){
            context.put(cache_key_spsTsCitycodeMap,(Map<String,Object>)cityCodes);
        }else{
            List<Map<String,Object>> spsTsCitycodes = spsTsCitycodeService.getSpsTsCitycode();
            Map<String,Object> spsTsCitycodeMap = new HashMap<>();
            int spsTsCityCodeCount = spsTsCitycodes!=null ? spsTsCitycodes.size() : 0;
            for (int i = 0; i < spsTsCityCodeCount; i++) {
                if(spsTsCitycodes.get(i).get(cache_key_area_id) == null)
                    continue;
                spsTsCitycodeMap.put(spsTsCitycodes.get(i).get(cache_key_area_id).toString(), spsTsCitycodes.get(i).get("ukey_type"));
            }
            context.put(cache_key_spsTsCitycodeMap,spsTsCitycodeMap);
            RedisUtil.set(SOCIAL_ANALYSIS_INITCONTEXT_AREATYPE_TSCITYCODES,spsTsCitycodeMap,3600);
        }


        //T支持任务业务类型
        /**
         * 检查当前T是否支持此地区业务
         */
        Object tob = RedisUtil.get(SOCIAL_ANALYSIS_INITCONTEXT_AREATYPE_TSCITYSUPPORT);
        Map<String,JSONObject> spsTsCityBsTypeMap = new HashMap<>();
        if(null == tob){
            Object bsTypeJson = TaskBotApi.getBstypeList();
            if (bsTypeJson != null && org.apache.commons.lang.StringUtils.isNotBlank(bsTypeJson.toString())) {
                JSONArray cityList = JSON.parseObject(bsTypeJson.toString()).getJSONArray("cityList");
                int count = cityList != null ? cityList.size() : 0;
                for (int i = 0; i < count; i++) {
                    JSONObject jb = cityList.getJSONObject(i);
                    String key = jb.getInteger(cache_key_bstype_id) + "_" + jb.getInteger(cache_key_area_id);
                    spsTsCityBsTypeMap.put(key,jb);
                }
                RedisUtil.set(SOCIAL_ANALYSIS_INITCONTEXT_AREATYPE_TSCITYSUPPORT,spsTsCityBsTypeMap,3600);
            }
        }else{
            spsTsCityBsTypeMap = (Map<String,JSONObject>)tob;
        }
        context.put(cache_key_spsTsCityBsTypeMap,spsTsCityBsTypeMap);

        //初始化医院
        Object hosiptalOb = RedisUtil.get(SOCIAL_ANALYSIS_INITCONTEXT_AREAT_HOSIPTAL_NAME);
        Object fullHosiptalOb = RedisUtil.get(SOCIAL_ANALYSIS_INITCONTEXT_AREAT_HOSIPTAL_FULL_NAME);
        if(null != hosiptalOb){
            context.put(cache_key_hosiptalNames, (Map<String, SpsFixedpointhospital>)hosiptalOb);
            context.put(cache_key_hosiptalFullNames, (Map<String, SpsFixedpointhospital>)fullHosiptalOb);
        }else{
            Map<String, SpsFixedpointhospital> hosiptalNameMap = new HashMap<String, SpsFixedpointhospital>(0);
            Map<String, SpsFixedpointhospital> hosiptalFullNameMap = new HashMap<String, SpsFixedpointhospital>(0);
            SpsFixedpointhospital vo = new SpsFixedpointhospital();
            vo.setAreaId(2);
            List<SpsFixedpointhospital> list = spsFixedpointhospitalService.findAll(vo);
            if (null != list && list.size() > 0) {
                for (SpsFixedpointhospital obj : list) {
                    hosiptalFullNameMap.put(obj.getFullName(), obj);
                    hosiptalNameMap.put(obj.getAbbreviation(), obj);
                }
            }
            context.put(cache_key_hosiptalNames, hosiptalNameMap);
            context.put(cache_key_hosiptalFullNames, hosiptalFullNameMap);
            RedisUtil.set(SOCIAL_ANALYSIS_INITCONTEXT_AREAT_HOSIPTAL_NAME,hosiptalNameMap,3600);
            RedisUtil.set(SOCIAL_ANALYSIS_INITCONTEXT_AREAT_HOSIPTAL_FULL_NAME,hosiptalFullNameMap,3600);
        }
    }

    /**
     *  校验业务数据的合法性
     *  @param   cti, analysisDataMap, context, titles
     *	@return 			: void
     *  @createDate  	: 2017-04-21 16:08
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-04-21 16:08
     *  @updateAuthor  :
     */
    public void checkDataVerify(ContextInfo cti,AnalysisConfig analysisConfig,Map<String,AnalysisData> analysisDataMap,Map<String,Object> context,List<AnalysisTitle> titles){
        /**
         * 合并所有涉及的业务titles
         */
        List<AnalysisTitle> bsTypeTitles = analysisConfig.getTitles().get(analysisDataMap.get(cache_key_bstypeId).getAnalysisValue());
        if(null != bsTypeTitles && !bsTypeTitles.isEmpty()){
            titles.removeAll(bsTypeTitles);
            titles.addAll(bsTypeTitles);
        }
        /**
         * titles排序
         */
        Collections.sort(titles,new Comparator<AnalysisTitle>(){
            @Override
            public int compare(AnalysisTitle o1, AnalysisTitle o2) {
                return o1.getRank().compareTo(o2.getRank());
            }
        });
        /**
         * 校验数据合法性
         */
        //校验数据合法性
        if(null == analysisDataMap.get(cache_key_name) || StringUtils.isBlank( analysisDataMap.get(cache_key_name).getAnalysisValue()))
            analysisDataMap.put(cache_key_name,new AnalysisData(cache_key_name,"",true,2));
        if( null == analysisDataMap.get(cache_key_identityCode) || StringUtils.isBlank(analysisDataMap.get(cache_key_identityCode).getAnalysisValue()))
            analysisDataMap.put(cache_key_identityCode,new AnalysisData(cache_key_identityCode,"",true,2));
    }


    /**
     *  处理任务单对应的taskbot 业务处理JSON
     *  @param   cti, analysisDataMap, context
     *	@return 			: void
     *  @createDate  	: 2017-03-24 09:51
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-03-24 09:51
     *  @updateAuthor  :
     */
    public Map<String,Object> handleBsTypeTaskJson(ContextInfo cti, Map<String,AnalysisData> analysisDataMap,Map<String,Object> context,Integer bsTypeId,List<AnalysisTitle> titles,List<File> notExcelFiles){
        Map<String,Object> taskJson = new HashMap<>();
        Map<String,List<BsTypeAreaFiledDto>> areaBsTypeMap = (Map<String,List<BsTypeAreaFiledDto>>)context.get(cache_key_bstypefileds);
        List<BsTypeAreaFiledDto> bsTypeFiledsMap =areaBsTypeMap.get(String.valueOf(bsTypeId+"_"+analysisDataMap.get(cache_key_areaId).getAnalysisValue()));

        if((null == bsTypeFiledsMap || bsTypeFiledsMap.isEmpty()) && analysisDataMap.get(cache_key_bstypeId).getAnalysisValue().equals(String.valueOf(BsType.INTO_INSURANCE.code()))){
            bsTypeFiledsMap = areaBsTypeMap.get(String.valueOf(BsType.NEW_INSURANCE.code()+"_"+analysisDataMap.get(cache_key_areaId).getAnalysisValue()));
            analysisDataMap.put(cache_key_bstypeId,new AnalysisData(cache_key_bstypeId,String.valueOf(BsType.NEW_INSURANCE.code()),false));
            analysisDataMap.put(cache_key_insuranceStateName,new AnalysisData(cache_key_insuranceState,"新参保",false));
            bsTypeId = BsType.NEW_INSURANCE.code();
        }

        if((null == bsTypeFiledsMap || bsTypeFiledsMap.isEmpty()) && analysisDataMap.get(cache_key_bstypeId).getAnalysisValue().equals(String.valueOf(BsType.NEW_INSURANCE.code()))){
            bsTypeFiledsMap = areaBsTypeMap.get(String.valueOf(BsType.INTO_INSURANCE.code()+"_"+analysisDataMap.get(cache_key_areaId).getAnalysisValue()));
            analysisDataMap.put(cache_key_bstypeId,new AnalysisData(cache_key_bstypeId,String.valueOf(BsType.INTO_INSURANCE.code()),false));
            analysisDataMap.put(cache_key_insuranceStateName,new AnalysisData(cache_key_insuranceState,"转入",false));
            bsTypeId = BsType.INTO_INSURANCE.code();
        }

        analysisDataMap.put("source",new AnalysisData("source","导入",false));//记录任务单来源

        /**
         * 当前是否支持地区网申
         */
        Map<String,JSONObject> spsTsCityBsTypeMap = (Map<String,JSONObject>)context.get(cache_key_spsTsCityBsTypeMap);
        String support_key = bsTypeId + "_" + analysisDataMap.get(cache_key_areaId).getAnalysisValue();
        if(null == bsTypeFiledsMap || null == spsTsCityBsTypeMap.get(support_key))
            return taskJson;

        for(BsTypeAreaFiledDto filed : bsTypeFiledsMap){
            if("PULL".equals(filed.getType())){
                //处理下拉
                String key = "DVALUE_"+ cti.getOrgId()+"_"+filed.getCode()+"_"+Integer.parseInt(analysisDataMap.get("schemeId").getAnalysisValue());
                Object dataValues = RedisUtil.get(key);
                List<Map<String, Object>> bsTypeFiledsValueMap;
                if(null != dataValues) {
                    bsTypeFiledsValueMap = (List<Map<String, Object>>) dataValues;
                }else {
                    bsTypeFiledsValueMap = bdBsareatypeService.findBdBsAreaTypeFiledBySchemeAreaId(cti.getOrgId(), filed.getCode(), Integer.parseInt(analysisDataMap.get("schemeId").getAnalysisValue()));
                    RedisUtil.set(key,bsTypeFiledsValueMap,3600);
                }
                int index = 0;
                String value_key = key + "_VALUE" + ((null == analysisDataMap.get(humpToLine(filed.getCode())) || StringUtils.isBlank(analysisDataMap.get(humpToLine(filed.getCode())).getAnalysisValue())) ? "_NULL" : analysisDataMap.get(humpToLine(filed.getCode())).getAnalysisValue());
                String value_key_name = key + "_VALUE" + ((null == analysisDataMap.get(humpToLine(filed.getCode())) || StringUtils.isBlank(analysisDataMap.get(humpToLine(filed.getCode())).getAnalysisValue())) ? "_NULL" : analysisDataMap.get(humpToLine(filed.getCode())).getAnalysisValue()) + "_NAME";
                Object value = context.get(value_key) == null ? RedisUtil.get(value_key) : context.get(value_key);
                Object value_name = context.get(value_key_name) == null ? RedisUtil.get(value_key_name) : context.get(value_key_name);
                if(null != value){
                    AnalysisData cacheAnalysisData = (AnalysisData)value;
                    AnalysisData cacheAnalysisDataName = (AnalysisData)value_name;
                    context.put(value_key,cacheAnalysisData);
                    context.put(value_key_name,cacheAnalysisDataName);
                    taskJson.put(filed.getCode(),cacheAnalysisData.getAnalysisValue());
                    taskJson.put(filed.getCode()+ "_" + cache_key_name,cacheAnalysisDataName.getAnalysisValue());
                    analysisDataMap.put(humpToLine(filed.getCode()),cacheAnalysisData);
                    analysisDataMap.put(humpToLine(filed.getCode())+cache_key_Name,cacheAnalysisDataName);
                }else{
                    for (Map<String, Object> filedValue : bsTypeFiledsValueMap){
                        if((null == analysisDataMap.get(humpToLine(filed.getCode())) || StringUtils.isBlank(analysisDataMap.get(humpToLine(filed.getCode())).getAnalysisValue())) && StringUtils.isNotBlank(filed.getDefaultValue())){
                            if(String.valueOf(filedValue.get(filed.getCode())).equals(filed.getDefaultValue())){
                                taskJson.put(filed.getCode(),filed.getDefaultValue());
                                taskJson.put(filed.getCode()+ "_" + cache_key_name,filedValue.get(filed.getCode()+ "_" + cache_key_name));
                                analysisDataMap.put(humpToLine(filed.getCode()),new AnalysisData(filed.getCode(),filed.getDefaultValue(),true));
                                analysisDataMap.put(humpToLine(filed.getCode())+cache_key_Name,new AnalysisData(humpToLine(filed.getCode())+cache_key_Name,String.valueOf(filedValue.get(filed.getCode()+ "_" + cache_key_name)),true));
                                RedisUtil.set(value_key,new AnalysisData(filed.getCode(),filed.getDefaultValue(),true),3600);
                                RedisUtil.set(value_key_name,new AnalysisData(humpToLine(filed.getCode())+cache_key_Name,String.valueOf(filedValue.get(filed.getCode()+ "_" + cache_key_name)),true),3600);
                                break;
                            }
                        }else{
                            if(null != analysisDataMap.get(humpToLine(filed.getCode())) && StringUtils.isNotBlank(analysisDataMap.get(humpToLine(filed.getCode())).getAnalysisValue())){
                                String sysValue = String.valueOf(filedValue.get(filed.getCode()+ "_" + cache_key_name));
                                if(sysValue.contains(analysisDataMap.get(humpToLine(filed.getCode())).getAnalysisValue()) || (null != analysisDataMap.get(humpToLine(filed.getCode())+cache_key_Name) && sysValue.contains(analysisDataMap.get(humpToLine(filed.getCode())+cache_key_Name).getAnalysisValue()))){
                                    taskJson.put(filed.getCode(),filedValue.get(filed.getCode()));
                                    analysisDataMap.put(humpToLine(filed.getCode()),new AnalysisData(filed.getCode(),String.valueOf(filedValue.get(filed.getCode())),false));
                                    analysisDataMap.put(humpToLine(filed.getCode())+cache_key_Name,new AnalysisData(humpToLine(filed.getCode())+cache_key_Name,String.valueOf(filedValue.get(filed.getCode()+ "_" + cache_key_name)),false));
                                    taskJson.put(filed.getCode()+ "_" + cache_key_name,filedValue.get(filed.getCode()+ "_" + cache_key_name));

                                    RedisUtil.set(value_key,new AnalysisData(filed.getCode(),String.valueOf(filedValue.get(filed.getCode())),false),3600);
                                    RedisUtil.set(value_key_name,new AnalysisData(humpToLine(filed.getCode())+cache_key_Name,String.valueOf(filedValue.get(filed.getCode()+ "_" + cache_key_name)),false),3600);

                                    break;
                                }
                                index++;
                                if(index < bsTypeFiledsValueMap.size())
                                    continue;
                                else if(index == bsTypeFiledsValueMap.size()){
                                    taskJson.put(filed.getCode(),analysisDataMap.get(humpToLine(filed.getCode())).getAnalysisValue());
                                    analysisDataMap.put(humpToLine(filed.getCode()),new AnalysisData(filed.getCode(),analysisDataMap.get(humpToLine(filed.getCode())).getAnalysisValue(),true,2));
                                    analysisDataMap.put(humpToLine(filed.getCode())+cache_key_Name,new AnalysisData(humpToLine(filed.getCode())+cache_key_Name,analysisDataMap.get(humpToLine(filed.getCode())).getAnalysisValue(),true,2));
                                    taskJson.put(filed.getCode()+"_" + cache_key_name,analysisDataMap.get(humpToLine(filed.getCode())).getAnalysisValue());

                                    RedisUtil.set(value_key,new AnalysisData(filed.getCode(),analysisDataMap.get(humpToLine(filed.getCode())).getAnalysisValue(),true,2),3600);
                                    RedisUtil.set(value_key_name,new AnalysisData(humpToLine(filed.getCode())+cache_key_Name,analysisDataMap.get(humpToLine(filed.getCode())).getAnalysisValue(),true,2),3600);

                                }
                            }else{
                                taskJson.put(filed.getCode(),filedValue.get(filed.getCode()));
                                taskJson.put(filed.getCode()+ "_" + cache_key_name,filedValue.get(filed.getCode()+ "_" + cache_key_name));
                                analysisDataMap.put(humpToLine(filed.getCode()),new AnalysisData(filed.getCode(),String.valueOf(filedValue.get(filed.getCode())),true));
                                analysisDataMap.put(humpToLine(filed.getCode())+cache_key_Name,new AnalysisData(humpToLine(filed.getCode())+cache_key_Name,String.valueOf(filedValue.get(filed.getCode()+ "_" + cache_key_name)),true));

                                RedisUtil.set(value_key,new AnalysisData(filed.getCode(),String.valueOf(filedValue.get(filed.getCode())),true),3600);
                                RedisUtil.set(value_key_name,new AnalysisData(humpToLine(filed.getCode())+cache_key_Name,String.valueOf(filedValue.get(filed.getCode()+ "_" + cache_key_name)),true),3600);

                                break;
                            }
                        }
                    }
                }

            }else if("FILE".equals(filed.getType())){
                if(filed.getCode().equals("photo")){
                    try {
                        String previewUrl = null;
                        if(null != analysisDataMap.get(cache_key_identityCode) && StringUtils.isNotBlank(analysisDataMap.get(cache_key_identityCode).getAnalysisValue()) && null != analysisDataMap.get(cache_key_name) && StringUtils.isNotBlank(analysisDataMap.get(cache_key_name).getAnalysisValue())){
                            String cardNO = analysisDataMap.get(cache_key_identityCode).getAnalysisValue();
                            List<String> fileNames = new ArrayList<>();
                            if(cardNO.length()>4){
                                String photoName = analysisDataMap.get(cache_key_name).getAnalysisValue() + cardNO.substring(cardNO.length()-4) +".jpg";
                                String photoName2 = cardNO +".jpg";
                                fileNames.add(photoName);
                                fileNames.add(photoName2);
                            }
                            String photoName1 = analysisDataMap.get(cache_key_name).getAnalysisValue() +".jpg";
                            fileNames.add(photoName1);
                            for(String fileName : fileNames){
                                for(File file:notExcelFiles){
                                    log.info("=============================图片路径"+file.getPath());
                                    if(fileName.equals(file.getName()) && null == taskJson.get(filed.getCode()) && StringUtils.isBlank(previewUrl)){
                                        log.info("=============================图片与名称已经相互匹配");
                                        ImageUtil.compressZoomImage(file.getPath(),0.7,358,441);
                                        File newFile = new File(file.getPath());
                                        File photo = new File(file.getParent()+File.separator+System.currentTimeMillis()+".jpg");
                                        newFile.renameTo(photo);
                                        String fileId = AliyunImageUtil.uploadLocalPic(photo, "images/cs/");
                                        previewUrl = sysUploadfileService.getPreviewUrl(fileId);
                                        taskJson.put(filed.getCode(),previewUrl);
                                        analysisDataMap.put(filed.getCode(),new AnalysisData(filed.getCode(),previewUrl,false));
                                        break;
                                    }
                                }
                            }

                        }
                        if((null == analysisDataMap.get(filed.getCode()) || StringUtils.isBlank(analysisDataMap.get(filed.getCode()).getAnalysisValue())) && !"NOTASK".equals(cti.getLoginXfb())) {//不生产任务单，只是人员导入，不校验头像
                            analysisDataMap.get(cache_key_name).setDefault(true);
                            analysisDataMap.get(cache_key_name).setErrorLevel(2);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }else if("DATE".equals(filed.getType())){
                String sysValue = "";
                if(null != analysisDataMap.get(humpToLine(filed.getCode())) && StringUtils.isNotBlank(analysisDataMap.get(humpToLine(filed.getCode())).getAnalysisValue())){
                    sysValue = StringUtils.isBlank(analysisDataMap.get(humpToLine(filed.getCode())).getAnalysisValue()) ? DateUtil.getCurYearMonthStr() : DateFormatUtil.FormatDate(analysisDataMap.get(humpToLine(filed.getCode())).getAnalysisValue());
                    analysisDataMap.put(filed.getCode(),new AnalysisData(filed.getCode(),sysValue,false));
                }else if(null != analysisDataMap.get(humpToLine(filed.getCode())) && !analysisDataMap.get(humpToLine(filed.getCode())).isDefault()){
                    analysisDataMap.put(filed.getCode(),new AnalysisData(filed.getCode(),DateUtil.getCurYearMonthStr(),true));
                }
                taskJson.put(filed.getCode(),sysValue);
            }else {
                if(filed.getCode().equals("hospital")){
                    Map<String, SpsFixedpointhospital> hosiptalNameMap = (Map<String, SpsFixedpointhospital>)context.get(cache_key_hosiptalNames);
                    Map<String, SpsFixedpointhospital> hosiptalFullNameMap = (Map<String, SpsFixedpointhospital>)context.get(cache_key_hosiptalFullNames);
                    parseHospital(taskJson,analysisDataMap,hosiptalNameMap,hosiptalFullNameMap);
                }else{
                    String sysValue = "";
                    if(null != analysisDataMap.get(humpToLine(filed.getCode()))){
                        sysValue = StringUtils.isBlank(analysisDataMap.get(humpToLine(filed.getCode())).getAnalysisValue()) ? "" : analysisDataMap.get(humpToLine(filed.getCode())).getAnalysisValue();
                    }
                    taskJson.put(filed.getCode(),sysValue);
                }
            }
        }
        handleResultTitles(titles,bsTypeFiledsMap);
        return taskJson;
    }

    /**
     *  处理解析对应的标准模版数据、员工属性数据、任务单共有属性数据
     *  @param   cti, analysisDataMap, context
     *	@return 			: void
     *  @createDate  	: 2017-03-24 09:49
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-03-24 09:49
     *  @updateAuthor  :
     */
    public void handleBaseFileds(ContextInfo cti, Map<String,AnalysisData> analysisDataMap,Map<String,Object> context){
        //获取企业ID、服务商ID、方案ID、
        SpsScheme currScheme = queryEmpScheme(cti,analysisDataMap,context);//当前方案

        if(null == currScheme) {
            if(null != analysisDataMap && !analysisDataMap.isEmpty()){
                /**
                 * 没有解析正确地区
                 */
                analysisDataMap.get(cache_key_areaName).setDefault(true);
                analysisDataMap.get(cache_key_areaName).setErrorLevel(2);
            }
            return;
        }

        analysisDataMap.put("schemeId",new AnalysisData("schemeId",String.valueOf(currScheme.getSchemeId())));
        analysisDataMap.put("cpId",new AnalysisData("cpId",String.valueOf(currScheme.getCpId())));
        analysisDataMap.put("spId",new AnalysisData("spId",String.valueOf(currScheme.getSpId())));
        analysisDataMap.put("type",new AnalysisData("type","TODO"));
        analysisDataMap.put(cache_key_areaId,new AnalysisData(cache_key_areaId,String.valueOf(currScheme.getAreaId())));

        if(null != currScheme.getFundAccountId())
            analysisDataMap.put(cache_key_fundAccountId,new AnalysisData(cache_key_fundAccountId,String.valueOf(currScheme.getFundAccountId())));
        if(null != currScheme.getInsuranceAccountId())
            analysisDataMap.put(cache_key_insuranceAccountId,new AnalysisData(cache_key_insuranceAccountId,String.valueOf(currScheme.getInsuranceAccountId())));
        analysisDataMap.put(cache_key_identityTypeName,new AnalysisData(cache_key_identityTypeName,"身份证",false));
        analysisDataMap.put(cache_key_identityType,new AnalysisData(cache_key_identityType,"IDCard",false));

        //获取参保时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        String insCurrentPeriod = dateFormat.format(date);
        String fundCurrentPeriod = dateFormat.format(date);

        if(null != analysisDataMap.get(cache_key_insurancePeriod) && (null == analysisDataMap.get(cache_key_fundPeriod) || StringUtils.isBlank(analysisDataMap.get(cache_key_fundPeriod).getAnalysisValue()))){
            analysisDataMap.put(cache_key_fundPeriod,new AnalysisData(cache_key_fundPeriod,analysisDataMap.get(cache_key_insurancePeriod).getAnalysisValue()));
        }

        if(null != analysisDataMap.get(cache_key_fundPeriod) && (null == analysisDataMap.get(cache_key_insurancePeriod)  || StringUtils.isBlank(analysisDataMap.get(cache_key_insurancePeriod).getAnalysisValue()))){
            analysisDataMap.put(cache_key_insurancePeriod,new AnalysisData(cache_key_insurancePeriod,analysisDataMap.get(cache_key_fundPeriod).getAnalysisValue()));
        }

        if(null != analysisDataMap.get(cache_key_insurancePeriod) && StringUtils.isNotBlank(analysisDataMap.get(cache_key_insurancePeriod).getAnalysisValue())){
            if(analysisDataMap.get(cache_key_insurancePeriod).getAnalysisValue().length() <= 4){
                analysisDataMap.put(cache_key_insurancePeriod,new AnalysisData(cache_key_insurancePeriod,analysisDataMap.get(cache_key_insurancePeriod).getAnalysisValue(),true,2));
            }else{
                String period = DateFormatUtil.FormatDate(analysisDataMap.get(cache_key_insurancePeriod).getAnalysisValue());
                if(StringUtils.isBlank(period)){
                    analysisDataMap.get(cache_key_insurancePeriod).setDefault(true);
                    analysisDataMap.get(cache_key_insurancePeriod).setErrorLevel(2);
                }else{
                    analysisDataMap.put(cache_key_insurancePeriod,new AnalysisData(cache_key_insurancePeriod,period,false));
                }
            }
        }else{
            analysisDataMap.put(cache_key_insurancePeriod,new AnalysisData(cache_key_insurancePeriod,insCurrentPeriod));
        }

        if(null != analysisDataMap.get(cache_key_fundPeriod) && StringUtils.isNotBlank(analysisDataMap.get(cache_key_fundPeriod).getAnalysisValue())){
            if(analysisDataMap.get(cache_key_fundPeriod).getAnalysisValue().length() <= 4){
                analysisDataMap.put(cache_key_fundPeriod,new AnalysisData(cache_key_fundPeriod,analysisDataMap.get(cache_key_fundPeriod).getAnalysisValue(),true,2));
            }else{
                String period = DateFormatUtil.FormatDate(analysisDataMap.get(cache_key_fundPeriod).getAnalysisValue());
                if(StringUtils.isBlank(period)){
                    analysisDataMap.get(cache_key_fundPeriod).setDefault(true);
                    analysisDataMap.get(cache_key_fundPeriod).setErrorLevel(2);
                }else{
                    analysisDataMap.put(cache_key_fundPeriod,new AnalysisData(cache_key_fundPeriod,period,false));
                }
            }
        }else{
            analysisDataMap.put(cache_key_fundPeriod,new AnalysisData(cache_key_fundPeriod,fundCurrentPeriod));
        }
    }


    /**
     *  获取当前人员所属方案
     *  @param   cti, analysisDataMap, context
     *	@return 			: com.xfs.sp.model.SpsScheme
     *  @createDate  	: 2017-04-06 15:35
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-04-06 15:35
     *  @updateAuthor  :
     */
    private SpsScheme queryEmpScheme(ContextInfo cti,Map<String,AnalysisData> analysisDataMap,Map<String,Object> context){
        SpsScheme currScheme = null;
        List<SpsScheme> schemeList = (List<SpsScheme>)context.get(cache_key_schemeList);
        /**
         * 判断当前用户是否存在方案
         */
        if(null != analysisDataMap.get("schemeId") && StringUtils.isNotBlank(analysisDataMap.get("schemeId").getAnalysisValue())){
            for(SpsScheme spsScheme : schemeList){
                String schemeId = analysisDataMap.get("schemeId").getAnalysisValue();
                    if(schemeId.equals(String.valueOf(spsScheme.getSchemeId()))){
                        currScheme = spsScheme;
                        analysisDataMap.put(cache_key_areaName,new AnalysisData(cache_key_areaName,String.valueOf(currScheme.getAreaname()),false));
                    }
                }
        }else {
            /**
             * 已存在用户使用已有方案
             */

            /**
             * 指定操作地区
             */
            if(null != context.get(cache_key_areaName) && (null == analysisDataMap.get(cache_key_areaName) || StringUtils.isBlank(analysisDataMap.get(cache_key_areaName).getAnalysisValue())))
                analysisDataMap.put(cache_key_areaName,new AnalysisData(cache_key_areaName,String.valueOf(context.get(cache_key_areaName)),false));

            /**
             * 获取当前用户下所有方案列表
             */
            for(SpsScheme spsScheme : schemeList){
                if(null != analysisDataMap.get(cache_key_areaName) && StringUtils.isNotBlank(analysisDataMap.get(cache_key_areaName).getAnalysisValue())){
                    if(spsScheme.getAreaname().contains(String.valueOf(analysisDataMap.get(cache_key_areaName).getAnalysisValue()))){
                        currScheme = spsScheme;
                        analysisDataMap.put(cache_key_areaName,new AnalysisData(cache_key_areaName,String.valueOf(currScheme.getAreaname()),false));
                    }
                }
            }
            /**
             * 自助用户无对应方案市--并且已经解析出对应的地区
             */
            if(null == currScheme && null != analysisDataMap.get(cache_key_areaName) && StringUtils.isNotBlank(analysisDataMap.get(cache_key_areaName).getAnalysisValue())){
                BsArea bsArea = new BsArea();
                bsArea.setAreaType("CITY");
                bsArea.setName(analysisDataMap.get(cache_key_areaName).getAnalysisValue());
                List<BsArea> areaList = bsAreaService.getBsAreaByName(bsArea);
                if(null != areaList && !areaList.isEmpty()){
                    SpsScheme scheme = new SpsScheme();
                    scheme.setCpId(cti.getOrgId());
                    scheme.setAreaId(areaList.get(0).getAreaId());
                    scheme.setAuthority("ALL");
                    scheme.setSpId(-999);
                    SpsScheme exitsScheme = spsSchemeService.findMinSchemeByCityIdAndCpId(cti,scheme);
                    if(null != exitsScheme){
                        //不能创建方案，无权限，清空解析数据
                        analysisDataMap.clear();
                        return currScheme;
                    }
                    currScheme = spsAccountService.createDefaultScheme(cti,areaList.get(0).getAreaId());
                    currScheme.setAreaname(areaList.get(0).getName());
                    analysisDataMap.put(cache_key_areaName,new AnalysisData(cache_key_areaName,String.valueOf(currScheme.getAreaname()),false));
                    schemeList.add(currScheme);
                    context.put(cache_key_schemeList,schemeList);
                }
            }
            if(null == currScheme && (null == analysisDataMap.get(cache_key_areaName) || StringUtils.isBlank(analysisDataMap.get(cache_key_areaName).getAnalysisValue()))) {
                currScheme = schemeList.get(0);
                analysisDataMap.put(cache_key_areaName,new AnalysisData(cache_key_areaName,String.valueOf(currScheme.getAreaname())));
            }
        }
        return currScheme;
    }


    /**
     *  修改展示值
     *  @param   titles, bsTypeFiledsMap
     *	@return 			: void
     *  @createDate  	: 2017-03-28 18:43
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-03-28 18:43
     *  @updateAuthor  :
     */
    public void handleResultTitles(List<AnalysisTitle> titles,List<BsTypeAreaFiledDto> bsTypeFiledsMap){
        for(BsTypeAreaFiledDto filed : bsTypeFiledsMap) {
            if ("PULL".equals(filed.getType())) {
                for(AnalysisTitle title : titles){
                    if(title.getName().equals(humpToLine(filed.getCode()))){
                        title.setName(title.getName()+cache_key_Name);
                    }
                }
            }
        }
    }


    private static Pattern linePattern = Pattern.compile("_(\\w)");
    /**
     *  下划线转驼峰
     *  @param   str
     *	@return 			: java.lang.String
     *  @createDate  	: 2017-03-24 11:31
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-03-24 11:31
     *  @updateAuthor  :
     */
    public static String humpToLine(String str){
        if(str.indexOf("_") > 0){
            str = str.toLowerCase();
            Matcher matcher = linePattern.matcher(str);
            StringBuffer sb = new StringBuffer();
            while(matcher.find()){
                matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
            }
            matcher.appendTail(sb);
            return sb.toString();
        }
        return str;
    }


    private void parseHospital(Map<String,Object> curMap,Map<String,AnalysisData> analysisDataMap,Map<String, SpsFixedpointhospital> hosiptalNameMap,Map<String, SpsFixedpointhospital> hosiptalFullNameMap){
        List<HospitalDto> hospitalList = new ArrayList<HospitalDto>();
        //遍历
        for (Map.Entry<String, AnalysisData> entry : analysisDataMap.entrySet()) {
            // 校验医院
            if (null != entry.getKey() && entry.getKey().startsWith("hospital") && StringUtils.isNotBlank(entry.getValue().getAnalysisValue())) {
                SpsFixedpointhospital hospital = null;
                String hospitalName = entry.getValue().getAnalysisValue();
                if(hosiptalNameMap.containsKey(hospitalName)){
                    hospital = hosiptalNameMap.get(hospitalName);
                }
                if(hosiptalFullNameMap.containsKey(hospitalName)){
                    hospital = hosiptalFullNameMap.get(hospitalName);
                }
                if(hospital!=null){
                    HospitalDto hospitalDto = new HospitalDto();
                    hospitalDto.setName(hospital.getFullName());
                    hospitalDto.setCode(hospital.getHospitalCode());
                    hospitalDto.setLevel(hospital.getHospitalLevel());
                    hospitalDto.setAddress(hospital.getHospitalAddress());
                    hospitalDto.setRegion(hospital.getRegion());
                    hospitalList.add(hospitalDto);
                }
                curMap.remove(entry.getKey());
            }
        }
        if(!hospitalList.isEmpty()){
            curMap.put("hospital",JSON.toJSONString(hospitalList));
            analysisDataMap.put("hospital",new AnalysisData("hospital",JSON.toJSONString(hospitalList)));
        }else{
            analysisDataMap.put("hospital",new AnalysisData("hospital","",true,2));
        }
    }

}
