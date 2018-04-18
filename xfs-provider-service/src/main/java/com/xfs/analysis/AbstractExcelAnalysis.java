package com.xfs.analysis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.xfs.analysis.dto.SysAnalysisBustype;
import com.xfs.analysis.model.*;
import com.xfs.analysis.utils.JxcelUtil;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.FileUtil;
import com.xfs.common.util.StringUtils;
import com.xfs.common.util.ZipUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * 解析Excel基础类
 * @author : konglc@xinfuseh.com
 * @version : V1.0
 * @date : 2017-03-21 14:08
 */
public abstract class AbstractExcelAnalysis {

    @Autowired
    ExcelAnalysisConfigService excelAnalysisConfigService;

    /**
     *
     *  @param   file 要解析的文件
     *	@return 			: com.xfs.cs.util.AnalysisResult
     *  @createDate  	: 2017-03-21 14:29
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-03-21 14:29
     *  @updateAuthor  :
     */
    public AnalysisResult analysis(ContextInfo cti,File file,Map<String,Object> ext){
        try{
            return analysis(cti,file.getPath(),file.getName(),ext,null);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     *  解析Excel主要实现
     *  @param   filePath
     *	@return 			: com.xfs.cs.util.AnalysisResult
     *  @createDate  	: 2017-03-21 14:33
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-03-21 14:33
     *  @updateAuthor  :
     */
    public AnalysisResult analysis(ContextInfo cti,String filePath,String fileName){
       return analysis(cti,filePath,fileName,null,null);
    }

    /**
     *  解析Excel主要实现
     *  @param   filePath
     *	@return 			: com.xfs.cs.util.AnalysisResult
     *  @createDate  	: 2017-03-21 14:33
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-03-21 14:33
     *  @updateAuthor  :
     */
    public AnalysisResult analysis(ContextInfo cti,String filePath,String fileName,Map<String,Object> ext){
        return analysis(cti,filePath,fileName,ext,null);
    }

    /**
     *  解析Excel主要实现
     *  @param   filePath
     *	@return 			: com.xfs.cs.util.AnalysisResult
     *  @createDate  	: 2017-03-21 14:33
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-03-21 14:33
     *  @updateAuthor  :
     */
    public AnalysisResult analysis(ContextInfo cti,String filePath,String fileName,Map<String,Object> ext,AnalysisConfig analysisConfig){
        AnalysisResult result = new AnalysisResult();
        /**
         * 检查当前文件是否为压缩文件
         */
        List<File> allExcelFiles = new ArrayList<>();
        List<File> notExcelFiles = new ArrayList<>();
        /**
         * 解压压缩包,获取对应文件
         */
        try{
            unzipAndFilterExcel(filePath,allExcelFiles,notExcelFiles);
        }catch (Exception e){

        }
        if(null == analysisConfig){
            /**
             * 获取解析文件所需要的配置参数
             */
            analysisConfig = excelAnalysisConfigService.analysisConfig(analysisType());
        }
        for(File file : allExcelFiles){
            /**
             * 根据数据业务类型/解析数据信息
             */
            try{
                InputStream fileInput = new FileInputStream(file);
                analysisData(fileInput,fileName,analysisConfig,result);
            }catch (Exception e){

            }
        }
        /**
         * 做业务数据清洗
         */
        analysisBusiness(cti,analysisConfig,result,notExcelFiles,ext);
        return result;
    }

    /**
     *  解析JSON
     *  @param   cti, jsonArray optName 操作业务名称
     *	@return 			: com.xfs.analysis.model.AnalysisResult
     *  @createDate  	: 2017-06-09 10:30
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-06-09 10:30
     *  @updateAuthor  :
     */
    public AnalysisResult analysis(ContextInfo cti, JSONArray jsonArray,String optName){
        return analysis(cti,jsonArray,optName,null,null);
    }

    /**
     *  解析JSON
     *  @param   cti, jsonArray optName 操作业务名称
     *	@return 			: com.xfs.analysis.model.AnalysisResult
     *  @createDate  	: 2017-06-09 10:30
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-06-09 10:30
     *  @updateAuthor  :
     */
    public AnalysisResult analysis(ContextInfo cti, JSONArray jsonArray,String optName,Map<String,Object> ext){
        return analysis(cti,jsonArray,optName,ext,null);
    }

    /**
     *  解析JSON
     *  @param   cti, jsonArray optName 操作业务名称 ext 扩展字段
     *	@return 			: com.xfs.analysis.model.AnalysisResult
     *  @createDate  	: 2017-06-09 10:30
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-06-09 10:30
     *  @updateAuthor  :
     */
    public AnalysisResult analysis(ContextInfo cti, JSONArray jsonArray,String optName,Map<String,Object> ext,AnalysisConfig analysisConfig){
        AnalysisResult result = new AnalysisResult();
        if(null != jsonArray && jsonArray.size() > 0){
            if(null == analysisConfig){
                /**
                 * 获取解析文件所需要的配置参数
                 */
                analysisConfig = excelAnalysisConfigService.analysisConfigSuggester(analysisType());
            }
            /**
             * 根据数据业务类型/解析数据信息
             */
            analysisData(jsonArray,analysisConfig,result,optName);
            /**
             * 做业务数据清洗
             */
            analysisBusiness(cti,analysisConfig,result,null,ext);
        }
        return result;
    }

    /**
     *  根据文件名/sheet/列头/数据 判断当前数据业务操作类型
     *  @param   config cells
     *	@return 			: com.xfs.analysis.dto.SysAnalysisBustype
     *  @createDate  	: 2017-03-21 16:54
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-03-21 16:54
     *  @updateAuthor  :
     */
    public SysAnalysisBustype analysisBustype(AnalysisConfig config,AnalysisResult analysisResult,List<Cell> cells){
        return analysisBustype(config,analysisResult,cells,false);
    }
    /**
     *  根据文件名/sheet/列头/数据 判断当前数据业务操作类型
     *  @param   config cells
     *	@return 			: com.xfs.analysis.dto.SysAnalysisBustype
     *  @createDate  	: 2017-03-21 16:54
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-03-21 16:54
     *  @updateAuthor  :
     */
    public SysAnalysisBustype analysisBustype(AnalysisConfig config,AnalysisResult analysisResult,List<Cell> cells,boolean mustMacth) {
        boolean isHead = false;
        /**
         * 判断当前业务类型 1：根据数据字段解析
         */
        for (Cell cell : cells) {
            for (SysAnalysisBustype sysAnalysisBustype : config.getSysAnalysisBustype()) {
                for (String s : sysAnalysisBustype.getSimilar().split(",")) {
                    if (cell.getText().indexOf(s) > -1) {
                        analysisResult.setOpt(sysAnalysisBustype);
                        //if (null == analysisResult.getTitles() || (null != config.getTitles().get(sysAnalysisBustype.getBusType()) && analysisResult.getTitles().size() < config.getTitles().get(sysAnalysisBustype.getBusType()).size()))
                        List<AnalysisTitle> new_titles = new ArrayList<>();
                        if(null != config.getTitles().get(sysAnalysisBustype.getBusType()) && !config.getTitles().get(sysAnalysisBustype.getBusType()).isEmpty()){
                            for(AnalysisTitle t : config.getTitles().get(sysAnalysisBustype.getBusType())){
                                new_titles.add(new AnalysisTitle());
                            }
                            Collections.copy(new_titles,config.getTitles().get(sysAnalysisBustype.getBusType()));
                            analysisResult.setTitles(new_titles);
                        }
                    }
                }
            }
        }

        /**
         * 匹配标题头
         */
        int exits = 0;
        for (Cell cell : cells) {
            if (cell.getText() != null && !"".equals(cell.getText().trim())) {
                List<String> recTags = config.getSuggester().suggest(cell.getText().trim(), 1);
                if (null != config.getSplitWordMap().get(recTags.get(0)) && !config.getSplitWordMap().get(recTags.get(0)).equals("other")) {
                    exits++;
                }
                if(cell.getText().indexOf("姓名")>-1){
                    isHead = true;
                }
            }
        }


        if(isHead){
            List<String> notMatch = new ArrayList<>();
            Map<String, String> singMap = new HashMap();
            analysisResult.getMatchHeader().clear();
            for (Cell cell : cells) {
                if (cell.getText() != null && !"".equals(cell.getText().trim())) {
                    List<String> recTags = config.getSuggester().suggest(cell.getText().trim(), 1);
                    cell.setTargetText(config.getSplitWordMap().get(recTags.get(0)));
                    if(mustMacth){
                        if (!cell.getTargetText().equals("other") && cell.getText().equals(recTags.get(0))) {
                            if (!singMap.containsKey(cell.getTargetText())) {
                                singMap.put(cell.getTargetText(), cell.getTargetText());
                                analysisResult.getMatchHeader().add(cell);
                            } else {
                                notMatch.add(cell.getText());
                            }
                        } else {
                            notMatch.add(cell.getText());
                        }
                    }else{
                        if (!cell.getTargetText().equals("other")) {
                            if (!singMap.containsKey(cell.getTargetText())) {
                                singMap.put(cell.getTargetText(), cell.getTargetText());
                                analysisResult.getMatchHeader().add(cell);
                            } else {
                                notMatch.add(cell.getText());
                            }
                        } else {
                            notMatch.add(cell.getText());
                        }
                    }
                }
            }
            if (notMatch.size() > 0) {
                StringBuffer sb = new StringBuffer();
                sb.append(" [" + org.apache.commons.lang.StringUtils.join(notMatch.toArray(), ",") + "]");
                analysisResult.setExtraHeader(sb.toString());
            }
        }

        /**
         * 判断是否为标题头
         */
        if(null == analysisResult.getOpt() && isHead){
            double bl = 0;
            for (SysAnalysisBustype sysAnalysisBustype : config.getSysAnalysisBustype()) {
                int requisite = 0;
                int eq = 0;
                for(AnalysisTitle analysisTitle : config.getTitles().get(sysAnalysisBustype.getBusType())){
                    if (analysisTitle.getRequisite().equals(1)) {
                        requisite++;
                        for (Cell cell : analysisResult.getMatchHeader()){
                            if(analysisTitle.getName().equals(cell.getTargetText())){
                                eq++;
                            }
                        }
                    }

                }
                double curllbl = eq / (0 == requisite ? 100 : requisite);
                if (eq > 0 && eq == requisite) {
                    analysisResult.setOpt(sysAnalysisBustype);
                    List<AnalysisTitle> new_titles = new ArrayList<>();
                    for(AnalysisTitle t : config.getTitles().get(sysAnalysisBustype.getBusType())){
                        new_titles.add(new AnalysisTitle());
                    }
                    Collections.copy(new_titles,config.getTitles().get(sysAnalysisBustype.getBusType()));
                    analysisResult.setTitles(new_titles);
                    break;
                }else if(bl < curllbl){
                    bl = curllbl;
                    analysisResult.setOpt(sysAnalysisBustype);
                    List<AnalysisTitle> new_titles = new ArrayList<>();
                    for(AnalysisTitle t : config.getTitles().get(sysAnalysisBustype.getBusType())){
                        new_titles.add(new AnalysisTitle());
                    }
                    Collections.copy(new_titles,config.getTitles().get(sysAnalysisBustype.getBusType()));
                    analysisResult.setTitles(new_titles);
                }
            }
        }

        analysisResult.setHeader(isHead);
        return analysisResult.getOpt();
    }

    /**
     *  根据对应的业务操作，解析数据
     *  @param   analysisConfig, sysAnalysisBustype
     *	@return 			: com.xfs.analysis.model.AnalysisResult
     *  @createDate  	: 2017-03-21 17:09
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-03-21 17:09
     *  @updateAuthor  :
     */
    public void analysisData(InputStream stream, String fileFullName,AnalysisConfig analysisConfig,AnalysisResult result){
        /**
         * 解析Excel文件
         */
        SysAnalysisBustype sysAnalysisBustype;
        Workbook workbook = null;
        try {
            /**
             * 获取到当前数据业务类型
             */
            sysAnalysisBustype = analysisBustype(analysisConfig,result,Collections.singletonList(Cell.creatCell(-1, fileFullName)));
            workbook = WorkbookFactory.create(stream);
            int sheets = workbook.getNumberOfSheets();
            for (int i = 0; i < sheets; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                if (sheet != null) {
                    List<Cell> headRequest = new ArrayList<Cell>();//获取header 标题内容
                    List<Cell> matchCells = new ArrayList<Cell>();// 获取匹配到的单元格
                    String errorColunms = null;//未匹配的数据单元格 纯文本形式
                    //根据sheet名判断增减员
                    String sheetName = sheet.getSheetName();
                    sysAnalysisBustype = analysisBustype(analysisConfig,result,Collections.singletonList(Cell.creatCell(-1, sheetName)));
                    for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                        Row row = sheet.getRow(j);
                        if (row == null) {
                            continue;
                            //row = sheet.getRow(j + 1);
                        }
                        /**
                         * 当前行中所有列值
                         */
                        headRequest.clear();
                        if (sheet.getNumMergedRegions() == 0 || !JxcelUtil.existMergeCell(sheet, j)) { //没有合并单元格的情况下 或者该行不存在合并单元格
                            int firstCellNum = row.getFirstCellNum();
                            int lastCellNum = row.getLastCellNum();
                            getSingleLineHeadRequest(headRequest, row, firstCellNum, lastCellNum);
                        } else {
                            Map<String, Object> mergeRequet = JxcelUtil.getMergeRequet(sheet, j);
                            j = (int) mergeRequet.get("start");
                            headRequest = (List<Cell>) mergeRequet.get("request");
                        }
                        /**
                         * 解析当前行所属业务类型
                         */
                        sysAnalysisBustype = analysisBustype(analysisConfig,result,headRequest);
                        if (result.isHeader()) {
                            matchCells = result.getMatchHeader();
                            errorColunms = result.getExtraHeader();

                            if ((errorColunms != null || !matchCells.isEmpty())) {
                                StringBuffer stringBuilder = new StringBuffer().append("name:").append(sheetName).append(" ,解析业务类型:").append(com.google.common.base.Objects.firstNonNull(sysAnalysisBustype.getBusType(), "无"));
                                if (!matchCells.isEmpty()) {
                                    stringBuilder.append(",已匹配标题: ").append(Lists.transform(matchCells, new Function<Cell, String>() {
                                        @Override
                                        public String apply(Cell cell) {
                                            return cell.getText();
                                        }
                                    }));
                                }
                                if (errorColunms != null) {
                                    stringBuilder.append(",未匹配标题: ").append(errorColunms);
                                }
                            }
                        }else{
                            //在获取到title的情况下开始读取数据直到空行
                            List<Map<String, AnalysisData>> employeeInfoList = (List<Map<String, AnalysisData>>) result.getDataList();
                            if (employeeInfoList == null) {
                                employeeInfoList = new ArrayList<>();
                                result.setDataList(employeeInfoList);
                            }
                            Map<String, AnalysisData> employeeInfo = new LinkedHashMap<>();
                            for (Cell cell : matchCells) {
                                String cellValue = JxcelUtil.getCellValue(row.getCell(cell.getColunm()));
                                if (StringUtils.isNotBlank(cellValue)) {
                                    employeeInfo.put(cell.getTargetText(), new AnalysisData(cell.getTargetText(),cellValue.trim(),false));
                                }else{
                                    employeeInfo.put(cell.getTargetText(), new AnalysisData(cell.getTargetText(),"",false));
                                }
                            }
                            if (!employeeInfo.isEmpty() && null != employeeInfo.get("name") && StringUtils.isNotBlank(employeeInfo.get("name").getAnalysisValue())) {
                                employeeInfo.put("bstypeId", new AnalysisData("bstypeId",sysAnalysisBustype.getBusType()));
                                employeeInfoList.add(employeeInfo);
                                employeeInfo.put("index",new AnalysisData("index",String.valueOf(j)));
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *  根据对应的业务操作，解析数据
     *  @param   jsonArray, analysisConfig, result , optName 操作业务名称
     *	@return 			: void
     *  @createDate  	: 2017-06-06 18:11
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-06-06 18:11
     *  @updateAuthor  :
     */
    public void analysisData(JSONArray jsonArray, AnalysisConfig analysisConfig, AnalysisResult result,String optName){
        List<Cell> headRequest = new ArrayList<Cell>();//获取header 标题内容
        getSingleLineHeadRequest(headRequest,jsonArray.getJSONObject(0));
        SysAnalysisBustype sysAnalysisBustype = null;
        if(StringUtils.isNotBlank(optName)){
            sysAnalysisBustype = analysisBustype(analysisConfig,result,Collections.singletonList(Cell.creatCell(-1, optName)));
        }
        sysAnalysisBustype = analysisBustype(analysisConfig,result,headRequest,true);
        //在获取到title的情况下开始读取数据直到空行
        List<Map<String, AnalysisData>> employeeInfoList = (List<Map<String, AnalysisData>>) result.getDataList();
        if (employeeInfoList == null) {
            employeeInfoList = new ArrayList<>();
            result.setDataList(employeeInfoList);
        }

        for (int i = 0;i<jsonArray.size();i++) {
            try{
                Map<String, AnalysisData> employeeInfo = new LinkedHashMap<>();
                JSONObject data = jsonArray.getJSONObject(i);
                for (Cell cell : result.getMatchHeader()) {
                    String cellValue = data.getString(cell.getText());
                    if (StringUtils.isNotBlank(cellValue)) {
                        employeeInfo.put(cell.getTargetText(), new AnalysisData(cell.getTargetText(), cellValue.trim(), false));
                    } else {
                        employeeInfo.put(cell.getTargetText(), new AnalysisData(cell.getTargetText(), "", false));
                    }
                }
                if (!employeeInfo.isEmpty() && null != employeeInfo.get("name") && StringUtils.isNotBlank(employeeInfo.get("name").getAnalysisValue())) {
                    employeeInfo.put("bstypeId", new AnalysisData("bstypeId",sysAnalysisBustype.getBusType()));
                    employeeInfo.put("index", new AnalysisData("index",String.valueOf(i)));
                    employeeInfoList.add(employeeInfo);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

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
    public abstract void analysisBusiness(ContextInfo cti,AnalysisConfig analysisConfig,AnalysisResult analysisResult,List<File> notExcelFiles,Map<String,Object> ext);

    /**
     *  解析类别 -- 子类解析必须确定解析范围
     *	@return 			: String
     *  @createDate  	: 2017-03-21 14:28
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-03-21 14:28
     *  @updateAuthor  :
     */
    public abstract String analysisType();

    /**
     *  获取每行数据
     *  @param   headRequest, row, firstCellNum, lastCellNum
     *	@return 			: void
     *  @createDate  	: 2017-03-22 09:22
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-03-22 09:22
     *  @updateAuthor  :
     */
    private static void getSingleLineHeadRequest(List<Cell> headRequest, Row row, int firstCellNum, int lastCellNum) {
        for (; firstCellNum < lastCellNum; firstCellNum++) {
            org.apache.poi.ss.usermodel.Cell cell = row.getCell(firstCellNum);
            if (cell != null) {
                String cellValue = JxcelUtil.getCellValue(cell);
                if (org.apache.commons.lang.StringUtils.isNotBlank(cellValue)) {
                    Cell e = Cell.creatCell(firstCellNum, cellValue.trim());
                    headRequest.add(e);
                }
            }
        }
    }

    /**
     *  获取每行数据
     *  @param   headRequest, row, firstCellNum, lastCellNum
     *	@return 			: void
     *  @createDate  	: 2017-03-22 09:22
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-03-22 09:22
     *  @updateAuthor  :
     */
    private static void getSingleLineHeadRequest(List<Cell> headRequest,JSONObject jsonObject){
        for(Map.Entry<String,Object> entry : jsonObject.entrySet()){
            String key = entry.getKey();
            String value = String.valueOf(entry.getValue());
            int i = testIsArrayORObject(value);
            if(i == 0){
                Cell e = Cell.creatCell(1, key);
                headRequest.add(e);
            }else if( i == 1){
                Cell e = Cell.creatCell(1, key);
                headRequest.add(e);
                getSingleLineHeadRequest(headRequest,JSON.parseObject(value));
            }else if( i == 2){
                Cell e = Cell.creatCell(1, key);
                headRequest.add(e);
                JSONArray arrays = JSON.parseArray(value);
                for(int k =0;k<arrays.size();k++){
                    getSingleLineHeadRequest(headRequest,arrays.getJSONObject(k));
                }
            }
        }
    }

    private static int testIsArrayORObject(String sJSON){
        /*
         * return 0:既不是array也不是object
         * return 1：是object
         * return 2 ：是Array
         */
        try{
            JSONArray array = JSON.parseArray(sJSON);
            if(null != array)
                return 2;
        }catch (Exception e){
            try{
                JSONObject object = JSON.parseObject(sJSON);
                if(null != object)
                    return 1;
            }catch (Exception e1){
                return 0;
            }
        }
        return 0;
    }

    /**
     *  @param   filePath , excelFile, notExcelFile
     *	@return 			: void
     *  @createDate  	: 2017-04-24 10:46
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2017-04-24 10:46
     *  @updateAuthor  :
     */
    private void unzipAndFilterExcel(String filePath,List<File> excelFiles,List<File> notExcelFile) throws Exception{
        // 判断filePath文件类型： 压缩包或单独文件
        if (filePath.toLowerCase().endsWith(".rar") || filePath.toLowerCase().endsWith(".zip")) {
            String excelPath = filePath.substring(0, filePath.length() - 4);
            File subFiles = new File(excelPath);
            List<File> fileLists = null;
            if (subFiles.isDirectory()) {
                fileLists = FileUtil.getSubFiles(subFiles);
            } else {
                // 解压
                if(filePath.toLowerCase().endsWith(".zip"))
                    ZipUtil.unzip(filePath, filePath.substring(0, filePath.length() - 4), null);
                else if (filePath.toLowerCase().endsWith(".rar") )
                    ZipUtil.unrar(filePath);
                fileLists = FileUtil.getSubFiles(subFiles);
            }
            if (null != fileLists && fileLists.size() > 0) {
                for (File curFile : fileLists) {
                    if (null != curFile && curFile.exists() && (curFile.getName().indexOf("xlsx") > -1 || curFile.getName().indexOf("xls") > -1)) {
                        excelFiles.add(curFile);
                    }else{
                        notExcelFile.add(curFile);
                    }
                }
            }
        } else {
            excelFiles.add(new File(filePath));
        }
    }

    public static void main(String[] args) {
        try{
            String filePath = "C:\\Users\\Administrator\\Desktop\\新建文件夹\\7168\\7168.zip";
            ZipUtil.unzip(filePath, filePath.substring(0, filePath.length() - 4), null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}