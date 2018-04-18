package com.xfs.sps.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xfs.base.dao.SysDictitemDAO;
import com.xfs.base.model.BdBusinessfield;
import com.xfs.base.model.SysDictitem;
import com.xfs.base.model.SysUploadfile;
import com.xfs.base.service.SysUploadfileService;
import com.xfs.business.model.BdBstype;
import com.xfs.common.util.Config;
import com.xfs.common.util.StringUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 导出 excelUtil
 * 
 * @author lifq
 *
 * 2016年3月17日  下午4:05:42
 */
public class ExportExcelUtil<T> {


    /**
     * 导出功能
     *
     * @author lifq
     *
     * 2016年3月17日  下午4:12:47
     * @throws IOException
     */
    public void exportExcel(String sheetTitle,OutputStream out ,List<Map<String, Object>> mapResult ,SysUploadfileService sysUploadfileService) throws Exception {

//    	String pattern = "yyyy-MM-dd";
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(sheetTitle);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(15);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style2.setFont(font2);

        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // 定义注释的大小和位置,详见文档
//        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
//                0, 0, 0, (short) 4, 2, (short) 6, 5));
        // 设置注释内容
//        comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
//        comment.setAuthor("leno");



        Map<String, Object> firstObj = mapResult.get(0);

        List<String> usedkeyList = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : firstObj.entrySet()) {
        	if("_id".equals(entry.getKey()) || "template_id".equals(entry.getKey()) || "data_html".equals(entry.getKey())){
        		continue;
        	}else{
        		usedkeyList.add(entry.getKey());
        	}

        }
        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (int j = 0; j < usedkeyList.size(); j++) {
            HSSFCell cell = row.createCell(j);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(usedkeyList.get(j));
            cell.setCellValue(text);
        }

        //遍历 所有人员
        for(int i=0;i<mapResult.size();i++){
        	row = sheet.createRow(i+1);
        	Map<String, Object> curMap = mapResult.get(i);
        	//遍历信息项
        	for(int k = 0;k<usedkeyList.size();k++){
        		HSSFCell cell = row.createCell(k);
                cell.setCellStyle(style2);
                String val = (String)curMap.get(usedkeyList.get(k));
                // 有图片时
                if(null!=val && val.startsWith("image_")){
                	//读取图片

                    InputStream is = sysUploadfileService.downloadFile(val.substring(6));
//                	GridFSDBFile curFile = mongoDao.readFileById(val.substring(6));
//                	String uuid = UUIDUtil.randomUUID();
//                	File file = new File("d:/"+uuid +".jpg");
//                	curFile.writeTo(file);
//        			 BufferedInputStream bis1 = new BufferedInputStream(new
//        			     FileInputStream(file));
//        			 byte[] buf1 = new byte[bis1.available()];
//        			 while ((bis1.read(buf1)) != -1) {
//        			 }
                	byte[] buf1 = IOUtils.toByteArray(is);
                	// 有图片时，设置行高为60px;
                    row.setHeightInPoints(60);
                    // 设置图片所在列宽度为80px,注意这里单位的一个换算
                    sheet.setColumnWidth(1, (short) (35.7 * 80));
                    // sheet.autoSizeColumn(i);
                    byte[] bsValue = (byte[]) buf1;
                    HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
                            1023, 255, (short) (k), i+1, (short) (k), i+1);
                    anchor.setAnchorType(2);
                    patriarch.createPicture(anchor, workbook.addPicture(
                            bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));

                }else{
                	cell.setCellValue(val);
                }

        	}
        }

        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    /**
     * 将数据导出到excel
     * @param sheetTitle
     * @param out
     * @param objResult
     * @throws IOException
     */
    /**
     * 将数据导出到excel
     * @param sheetTitle
     * @param out
     * @param objResult
     * @throws IOException
     */
    public void exportExcelFromMySql(String sheetTitle, OutputStream out , List<Map<String,Object>> objResult, SysUploadfileService sysUploadfileService) throws  Exception {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(sheetTitle);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(15);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style2.setFont(font2);

        // 生成并设置另一个样式
        HSSFCellStyle style3 = workbook.createCellStyle();
        style3.setFillForegroundColor(HSSFColor.RED.index);
        style3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style3.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style3.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        HSSFFont font3 = workbook.createFont();
        font3.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style3.setFont(font3);

        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

        try{
            Object ob=objResult.get(0);
            String [] objArr={"sp_name","corp_name","json","bstype_id","type","errmsg","pic_id","user_name","modify_dt"};

            // 产生表格标题行
            HSSFRow row = sheet.createRow(0);
            for (int j = 0; j < objArr.length+2; j++) {
                HSSFCell cell = row.createCell(j);
                cell.setCellStyle(style);
            }

            row.getCell(0).setCellValue("服务商名称");
            row.getCell(1).setCellValue("公司名称");
            row.getCell(2).setCellValue("人员姓名");
            row.getCell(3).setCellValue("身份证号");
            row.getCell(4).setCellValue("手机号");
            row.getCell(5).setCellValue("业务类型");
            row.getCell(6).setCellValue("操作结果");
            row.getCell(7).setCellValue("错误信息");
            row.getCell(8).setCellValue("操作图片");
            row.getCell(9).setCellValue("操作人");
            row.getCell(10).setCellValue("操作时间");

            //遍历 所有人员
            for(int i=0;i<objResult.size();i++){
                row = sheet.createRow(i+1);
                Map<String, Object> curMap = objResult.get(i);
                //遍历信息项
                for(int z=0;z<objArr.length+2;z++) {
                    HSSFCell cell = row.createCell(z);
                    cell.setCellStyle(style2);
                }
                for (int k = 0; k < objArr.length; k++) {

                    String val = null;
                    if (curMap.get(objArr[k]) != null) {
                        val = curMap.get(objArr[k]).toString();
                    }
                    // 有图片时
                    if (null != val && objArr[k] == "pic_id") {
                        //读取图片
                        String id = val;
                        String fileRootPath = Config.getProperty("fileRootPath");//文件根目录

                        SysUploadfile vo = new SysUploadfile();
                        vo.setId(Integer.valueOf(id));
                        SysUploadfile uploadFile = sysUploadfileService.findByPK(vo);

                        if (null == uploadFile) {
                            row.getCell(k+2).setCellValue("");
                        }
                        InputStream is =  sysUploadfileService.downloadFile(uploadFile.getSavename());
                        String filePath = fileRootPath + File.separator + uploadFile.getFilepath() + File.separator + uploadFile.getFilename();
                        try {

                            if(is==null){
                                row.getCell(k+2).setCellValue("");
                                continue;
                            }
                            byte[] buf1 = IOUtils.toByteArray(is);
                            // 有图片时，设置行高为60px;
                            row.setHeightInPoints(60);
                            // 设置图片所在列宽度为80px,注意这里单位的一个换算
                            sheet.setColumnWidth(1, (short) (35.7 * 80));
                            // sheet.autoSizeColumn(i);
                            byte[] bsValue = (byte[]) buf1;
                            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
                                    1023, 255, (short) (k + 2), i + 1, (short) (k + 2), i + 1);
                            anchor.setAnchorType(2);
                            patriarch.createPicture(anchor, workbook.addPicture(
                                    bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    } else if (val != null && objArr[k] == "bstype_id") {
                        val = getType(val);
                        row.getCell(k+2).setCellValue(val);
                    } else if(val != null && objArr[k] == "json"){
                        JSONObject json= JSON.parseObject(val);
                        row.getCell(k).setCellValue(json.getString("name"));
                        row.getCell(k+1).setCellValue(json.getString("cardNO"));
                        row.getCell(k+2).setCellValue(json.getString("mobile"));
                    }else if(val != null && objArr[k].endsWith("dt")){
//                        System.out.print(val);
                        val=val.replace(".0","");
//                    val=changeTypeData(val);
                        row.getCell(k+2).setCellValue(val);
                    }else if(val != null && objArr[k].equals("type")){
                        if(val.equals("ERROR")){
                            for(int typeInt=0;typeInt<objArr.length+2;typeInt++){
                                row.getCell(typeInt).setCellStyle(style3);
                            }
                        }
                        val=changeType(val);
                        row.getCell(k+2).setCellValue(val);
                    } else if(val != null && objArr[k].equals("sp_name")){
                        row.getCell(k).setCellValue(val);
                    }else if(val != null && objArr[k].equals("corp_name")){
                        row.getCell(k).setCellValue(val);
                    }else {
                        row.getCell(k+2).setCellValue(val);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if(obj == null){
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }
        return map;
    }

    /**
     * 业务类型转换
     * @param type
     * @return
     */
    public String getType(String type){
        String bussType=null;
        if(type.equals("1")){
            bussType="修改手机号";
        }else if(type.equals("2")){
            bussType="新参保";
        }else if(type.equals("3")){
            bussType="转入业务";
        }else if(type.equals("4")){
            bussType="减员业务";
        }else if(type.equals("5")){
            bussType="查询个人报表业务";
        }else if(type.equals("6")){
            bussType="查询企业报表";
        }else if(type.equals("7")) {
            bussType = "定点医院修改";
        }
        return  bussType;
    }


    public String changeTypeData(String data) throws Exception{
        SimpleDateFormat sdf =/* new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");*/
                new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.UK);
        Date dateString = sdf.parse(data);
        String format=new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateString);
        System.out.print(format);
        return format;
    }

    public String changeType(String type){
        String bussType;
        if(type.equals("TODO")){
            bussType="待处理";
        }else if(type.equals("ERROR")){
            bussType="异常";
        }else if(type.equals("CLOSED")){
            bussType="已关闭";
        }else if(type.equals("COMPLETED")){
            bussType="已处理";
        }else {
            bussType="";
        }
        return  bussType;
    }
    
    /**
    *
    * @param bdBstypes
    * @param out
    * @param map
    * @param sysDictitemDAO
    * @throws Exception
    */
   public void exportTaskBotExcelFromMySql(List<BdBstype> bdBstypes,Integer areaId, OutputStream out,Map<String,List<BdBusinessfield>> map, SysDictitemDAO sysDictitemDAO) throws  Exception {
       // 声明一个工作薄
       XSSFWorkbook workbook = new XSSFWorkbook();
       // 生成一个样式
       XSSFCellStyle style = workbook.createCellStyle();
       XSSFDataFormat format = workbook.createDataFormat();

       style.setDataFormat(format.getFormat("@"));
       // 设置这些样式
       style.setFillForegroundColor(HSSFColor.WHITE.index);
       style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
       style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
       style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
       style.setBorderRight(HSSFCellStyle.BORDER_THIN);
       style.setBorderTop(HSSFCellStyle.BORDER_THIN);
       style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

       // 生成一个字体
       XSSFFont font = workbook.createFont();
       font.setColor(HSSFColor.BLACK.index);
       font.setFontHeightInPoints((short) 12);
       font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
       // 把字体应用到当前的样式
       style.setFont(font);
       try{
           for(BdBstype bbs:bdBstypes){

               XSSFSheet sheet = workbook.createSheet(bbs.getName());
               List<BdBusinessfield> bdBusinessfields = map.get(bbs.getName());
               // 设置表格默认列宽度为多少个字节
               sheet.setDefaultColumnWidth(15);
               XSSFRow row = sheet.createRow(0);
               int clum = bdBusinessfields.size();
               int index = 0;
               for (int j = 0; j <clum ; j++) {
                   if(StringUtils.isBlank(bdBusinessfields.get(j).getModelIsHidden()) || "SHOW".equals(bdBusinessfields.get(j).getModelIsHidden())){
                       XSSFCell cell = row.createCell(index);
                       cell.setCellStyle(style);
                       cell.setCellValue(bdBusinessfields.get(j).getName());
                       //设置样式为下拉框
                       if(bdBusinessfields.get(j).getType()!=null && bdBusinessfields.get(j).getType().equals("PULL")){
                           CellRangeAddressList regions = new CellRangeAddressList(1, 1000, index, index);
                           List<SysDictitem> sysDictitems =sysDictitemDAO.findSysDictitem(bdBusinessfields.get(j).getCode()+"_doc",areaId);
                           List<String> sysDictitemNames = new ArrayList<>();
                           for(SysDictitem sd:sysDictitems){
                               sysDictitemNames.add(sd.getName());
                           }

                           if(sysDictitemNames.size()>0) {
                               XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
                               XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper
                                       .createExplicitListConstraint(sysDictitemNames.toArray(new String[sysDictitemNames.size()]));
                               CellRangeAddressList addressList = null;
                               XSSFDataValidation validation = null;
                               validation = (XSSFDataValidation) dvHelper.createValidation(
                                       dvConstraint, regions);
                               sheet.addValidationData(validation);
                           }


                       /*
                       String[] sysDicitemArr =sysDictitemNames.toArray(new String[sysDictitemNames.size()]);
                       // 生成下拉框内容
                       DVConstraint constraint = DVConstraint.createExplicitListConstraint(sysDicitemArr);
                       // 绑定下拉框和作用区域
                       HSSFDataValidation data_validation = new HSSFDataValidation(regions,constraint);
                       // 对sheet页生效
                       sheet.addValidationData(data_validation);*/
                       }
                       index++;
                   }
               }

               for(int x = 1;x<1000;x++){
                   XSSFRow rowx = sheet.createRow(x);
                   for (int y = 0;y<index;y++){
                       XSSFCell cell = rowx.createCell(y);
                       cell.setCellStyle(style);
                   }
               }
           }
       }catch(Exception e){
           e.printStackTrace();
       }
       try {
           workbook.write(out);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

}





