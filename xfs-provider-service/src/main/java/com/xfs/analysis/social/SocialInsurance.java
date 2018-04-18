package com.xfs.analysis.social;

import com.xfs.analysis.model.AnalysisConfig;
import com.xfs.analysis.model.AnalysisData;
import com.xfs.analysis.model.AnalysisTitle;
import com.xfs.business.enums.BsType;
import com.xfs.common.ContextInfo;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 社保业务接口列
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2017-03-23 11:50
 */
public interface SocialInsurance {

    /**
     *  校验当前操作类型是否属于此业务
     *  @param   analysisDataMap
     *	@return 			: boolean
     *  @createDate  	: 2017-03-23 11:56
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-03-23 11:56
     *  @updateAuthor  :
     */
    public boolean checkBsType(Map<String,AnalysisData> analysisDataMap);

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
    public void handleSocialInsurance(ContextInfo cti,Map<String,AnalysisData> analysisDataMap,Map<String,Object> context,List<AnalysisTitle> titles,List<File> notExcelFiles);


    /**
     *  获取业务类型
     *  @param
     *	@return 			: java.lang.String
     *  @createDate  	: 2017-03-24 09:25
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-03-24 09:25
     *  @updateAuthor  :
     */
    public void initContext(ContextInfo cti,Map<String,Object> context);


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
    public void checkDataVerify(ContextInfo cti, AnalysisConfig analysisConfig,Map<String, AnalysisData> analysisDataMap, Map<String,Object> context, List<AnalysisTitle> titles);

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
    public Map<String,Object> handleBsTypeTaskJson(ContextInfo cti, Map<String,AnalysisData> analysisDataMap,Map<String,Object> context,Integer bsTypeId,List<AnalysisTitle> titles,List<File> notExcelFiles);

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
    public void handleBaseFileds(ContextInfo cti, Map<String,AnalysisData> analysisDataMap,Map<String,Object> context);

    /**
     *  设置人员状态
     *  @param   cti, analysisDataMap, context, bsType
     *	@return 			: void
     *  @createDate  	: 2017-05-22 10:20
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-05-22 10:20
     *  @updateAuthor  :
     */
    public void setEmpBsState(ContextInfo cti, Map<String,AnalysisData> analysisDataMap, Map<String,Object> context, BsType bsType);

}
