package com.xfs.finance.service.impl;

import com.google.common.base.MoreObjects;
import com.xfs.analysis.utils.JxcelUtil;
import com.xfs.base.service.SysUploadfileService;
import com.xfs.business.model.BdInsurance;
import com.xfs.business.service.BdInsuranceService;
import com.xfs.finance.dao.SpsReconciliationInfoDao;
import com.xfs.finance.model.SpsReconciliationDetail;
import com.xfs.finance.model.SpsReconciliationInfo;
import com.xfs.finance.service.SpsReconciliationInfoService;
import com.xfs.finance.vo.SpsReconciliationInfoVo;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;


/**
 * @Project: xfs-sps
 * <p></p>
 * @Author: Run Main
 * @Date: 2017-04-05
 * @Copyright: 2017 www.xinfushe.com Inc. All rights reserved.
 */
@Service
public class SpsReconciliationInfoServiceImpl implements SpsReconciliationInfoService {
    //@Autowired
    //SpsReconciliationInfoDao reconciliationInfoDao;
    @Autowired
    SysUploadfileService sysUploadfileService;

    @Autowired
    BdInsuranceService bdInsuranceService;

    @Override
    public int deleteByPrimaryKey(Long id) {
        //return reconciliationInfoDao.deleteByPrimaryKey(id);
    	return 1;
    }

    @Override
    public int insert(SpsReconciliationInfo record) {
//        return reconciliationInfoDao.insert(record);
        return 1;
    }

    @Override
    public SpsReconciliationInfo selectByPrimaryKey(Long id) {
//        return reconciliationInfoDao.selectByPrimaryKey(id);
    	return null;
    }

    @Override
    public List<SpsReconciliationInfo> selectAll() {
        //return reconciliationInfoDao.selectAll();
        return null;
    }

    @Override
    public int updateByPrimaryKey(SpsReconciliationInfo record) {
//        return reconciliationInfoDao.updateByPrimaryKey(record);
    	return 1;
    }

    //=====================================================================================

    @Override
    public List<SpsReconciliationInfo> selectInfoByRecords(List<Long> ids) {
//        if (!ids.isEmpty()) {
//
//            return reconciliationInfoDao.selectInfoByrecords(ids);
//        }
        return new ArrayList<>(0);
    }

    @Override
    public void deleteByKeys(List<Long> list) {
//        if (list != null && !list.isEmpty()) {
//            reconciliationInfoDao.deleteByKeys(list);
//        }
    }

    @Override
    public List<SpsReconciliationInfo> resolveExcel(Long fileId) {
        ArrayList<SpsReconciliationInfo> infos = new ArrayList<>();
        String filePath = sysUploadfileService.getPhysicsFile(fileId.intValue());
//        String filePath = "/home/runmain/xfsdoc/fws1.xlsx";
//        String filePath = "/home/runmain/xfsdoc/fws.xlsx";
        try (FileInputStream fin = new FileInputStream(new File(filePath)); Workbook workbook = WorkbookFactory.create(fin)) {
            Sheet sheet = workbook.getSheetAt(0);
            if (sheet != null) {
                boolean getHeader = false;
                HashMap<String, Integer> infoMap = null;
                Map<String, HashMap<String, Integer>> details = new LinkedHashMap<>();
                Map<String, Long> insuranceMap = getAllInsuranceMap();
                for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    //判断非空
                    if (row != null && !JxcelUtil.IsEmptyRow(row)) {
                        //必选先获取header
                        if (!getHeader) {
                            if (Objects.equals("序号", JxcelUtil.getCellValue(row.getCell(0))) || Objects.equals("姓名", JxcelUtil.getCellValue(row.getCell(0))) || Objects.equals("姓名", JxcelUtil.getCellValue(row.getCell(1)))) {
                                infoMap = new HashMap<>();
                                for (int j = 0; j <= row.getLastCellNum(); j++) {
                                    String cellValue = JxcelUtil.getCellValue(row.getCell(j));
                                    if (cellValue != null) {
                                        switch (cellValue.trim()) {
                                            case "序号":
                                            case "账单月":
                                            case "账单月份":
                                                break;
                                            case "姓名":
                                                infoMap.put("name", j);
                                                break;
                                            case "证件号码":
                                                infoMap.put("idNo", j);
                                                break;
                                            case "所属月份":
                                                infoMap.put("attachMonth", j);
                                                break;
                                            case "社保缴纳城市":
                                                infoMap.put("socialCity", j);
                                                break;
                                            case "公积金缴纳城市":
                                                infoMap.put("fundCity", j);
                                                break;
                                            case "社保小计":
                                                j = socialMap(sheet, infoMap, i, j);
                                                break;
                                            case "公积金小计":
                                            case "社保公积金小计":
                                                j = fundMap(sheet, infoMap, i, j);
                                                break;
                                            case "企业合计":
                                                infoMap.put("corpTotal", j);
                                                break;
                                            case "个人合计":
                                                infoMap.put("perTotal", j);
                                                break;
                                            case "服务费":
                                                infoMap.put("serviceFee", j);
                                                break;
                                            case "总计":
                                                infoMap.put("total", j);
                                                break;
                                            case "备注":
                                                infoMap.put("remark", j);
                                                break;
                                            default:
                                                if (cellValue.contains("险") || cellValue.contains("金") || cellValue.contains("医")) {
                                                    //险种解析
                                                    HashMap<String, Integer> detailMap = new HashMap<>();
                                                    j = detailMap(sheet, i, j, detailMap);
                                                    details.put(cellValue, detailMap);
                                                }
                                        }
                                    }
                                }
                                i++;
                                getHeader = true;
                            }
                        } else {
                            try {
                                addRecordInfo(infos, infoMap, details, row, insuranceMap);
                            } catch (IllegalArgumentException e) {
                                throw new IllegalArgumentException("第" + (i + 1) + e.getMessage() + "行数据格式不对");
                            } catch (Exception e) {
                                throw new IllegalArgumentException("第" + (i + 1) + "行数据格式不对");
                            }
                        }
                    }
                }
            }

        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("文件找不到");
        } catch (InvalidFormatException e) {
            throw new IllegalArgumentException("文件格式不对");
        } catch (IOException e) {
            throw new IllegalArgumentException("文件格式不对");
        }
        return infos;
    }


    @Override
    public List<SpsReconciliationInfoVo> queryByPage(Long typeId, Integer lType, Integer rType, String attachMonth, String billMonth, String socialCity, String fundCity, String diffType, String empName, String idNo ,int offset, int limit) {
        //return reconciliationInfoDao.queryByPage(typeId, lType, rType, attachMonth, billMonth, socialCity, fundCity, diffType, empName,idNo, offset, limit);
    	return null;
    }

    @Override
    public int queryTotal(Long typeId, Integer lType, Integer rType, String attachMonth, String billMonth, String socialCity, String fundCity, String diffType, String empName,String idNo) {
        //return reconciliationInfoDao.queryTotal(typeId, lType, rType, attachMonth, billMonth, socialCity, fundCity, diffType, empName, idNo);
    	return 1;
    }

    @Override
    public Float sumTotalByQuery(Long typeId, Integer lType, Integer rType, String attachMonth, String billMonth, String socialCity, String fundCity, String diffType, String empName,String idNo) {
        //return reconciliationInfoDao.sumTotalByQuery(typeId, lType, rType, attachMonth, billMonth, socialCity, fundCity, diffType, empName, idNo);
    	return null;
    }

    @Override
    public Float sumTotalByTypeId(Long typeId, Integer lType, Integer rType) {
        //return reconciliationInfoDao.sumTotalByQuery(typeId, lType, rType, null, null, null, null, null, null,null);
    	return null;
    }

    @Override
    public Float sumTotal() {
        //return reconciliationInfoDao.sumTotal();
    	return null;
    }

    @Override
    public List<String> querySocialCity(Integer type) {
        //return reconciliationInfoDao.querySocialCity(type);
    	return null;
    }

    @Override
    public List<String> queryFundCity(Integer type) {
        //return reconciliationInfoDao.queryFundCity(type);
    	return null;
    }

    /**
     * 添加一行数据
     *
     * @param infos
     * @param infoMap
     * @param details
     * @param row
     * @param insuranceMap
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private void addRecordInfo(ArrayList<SpsReconciliationInfo> infos, HashMap<String, Integer> infoMap, Map<String, HashMap<String, Integer>> details, Row row, Map<String, Long> insuranceMap) throws NoSuchFieldException, IllegalAccessException {
        SpsReconciliationInfo info = new SpsReconciliationInfo();
        List<SpsReconciliationDetail> detailList = new ArrayList<>();
        Class<SpsReconciliationInfo> reflect = SpsReconciliationInfo.class;
        //获取基本info信息
        for (Map.Entry<String, Integer> entry : infoMap.entrySet()) {
            String cellValue = JxcelUtil.getCellValue(row.getCell(entry.getValue()));
            Field field = reflect.getDeclaredField(entry.getKey());
            field.setAccessible(true);
            Class<?> type = field.getType();
            try {
                if (type.isAssignableFrom(Float.class)) {
                    field.set(info, Float.valueOf(MoreObjects.firstNonNull(cellValue, "0")));
                } else if (type.isAssignableFrom(Integer.class)) {
                    field.set(info, Integer.valueOf(MoreObjects.firstNonNull(cellValue, "0")));
                } else if (type.isAssignableFrom(Long.class)) {
                    field.set(info, Long.valueOf(MoreObjects.firstNonNull(cellValue, "0")));
                } else if (type.isAssignableFrom(String.class)) {
                    field.set(info, cellValue);
                } else if (type.isAssignableFrom(Double.class)) {
                    field.set(info, Double.valueOf(MoreObjects.firstNonNull(cellValue, "0")));
                }
            } catch (Exception e) {
                throw new IllegalArgumentException("第" + (entry.getValue() + 1) + "列");
            }
        }
        //险种记录
        for (Map.Entry<String, HashMap<String, Integer>> entry : details.entrySet()) {
            SpsReconciliationDetail detail = new SpsReconciliationDetail();
            detail.setInsuranceName(entry.getKey());
            detail.setInsuranceId(insuranceMap.get(entry.getKey()));
            for (Map.Entry<String, Integer> tem : entry.getValue().entrySet()) {
                Class<SpsReconciliationDetail> detailClass = SpsReconciliationDetail.class;
                String cellValue = JxcelUtil.getCellValue(row.getCell(tem.getValue()));
                Field field = detailClass.getDeclaredField(tem.getKey());
                field.setAccessible(true);
                Class<?> type = field.getType();
                try {
                    if (type.isAssignableFrom(Float.class)) {
                        field.set(detail, Float.valueOf(MoreObjects.firstNonNull(cellValue, "0")));
                    } else if (type.isAssignableFrom(Integer.class)) {
                        field.set(detail, Integer.valueOf(MoreObjects.firstNonNull(cellValue, "0")));
                    } else if (type.isAssignableFrom(Long.class)) {
                        field.set(detail, Long.valueOf(MoreObjects.firstNonNull(cellValue, "0")));
                    } else if (type.isAssignableFrom(String.class)) {
                        field.set(detail, cellValue);
                    } else if (type.isAssignableFrom(Double.class)) {
                        field.set(detail, Double.valueOf(MoreObjects.firstNonNull(cellValue, "0")));
                    }
                } catch (Exception e) {
                    throw new IllegalArgumentException("第" + (tem.getValue() + 1) + "列");
                }
            }
            detailList.add(detail);
        }
        info.setDetailList(detailList);
        infos.add(info);
    }

    private int detailMap(Sheet sheet, int i, int j, HashMap<String, Integer> detailMap) {
        Row nextRow = sheet.getRow(i + 1);
        int start = j;
        int end = j;
        if (JxcelUtil.inMergedRegion(sheet, i, j)) {
            CellRangeAddress currentMergeRegion = JxcelUtil.getCurrentMergeRegion(sheet, i, j);
            start = currentMergeRegion.getFirstColumn();
            end = currentMergeRegion.getLastColumn();
        }
        while (start <= end) {
            String secondRowValue = JxcelUtil.getCellValue(nextRow.getCell(start));
            if (secondRowValue != null) {
                switch (secondRowValue.trim()) {


                    case "企业基数":
                        detailMap.put("corpBase", start);
                        break;
                    case "企业比例":
                        detailMap.put("corpRatio", start);
                        break;
                    case "企业附加金额":
                        detailMap.put("corpExtra", start);
                        break;
                    case "企业金额":
                        detailMap.put("corpFee", start);
                        break;
                    case "个人基数":
                        detailMap.put("perBase", start);
                        break;
                    case "个人比例":
                        detailMap.put("perRatio", start);
                        break;
                    case "个人附加金额":
                        detailMap.put("perExtra", start);
                        break;
                    case "个人金额":
                        detailMap.put("perFee", start);
                        break;
                    case "合计":
                        detailMap.put("total", start);
                        break;
                }
            }
            start++;
        }
        return end;
    }

    private int socialMap(Sheet sheet, HashMap<String, Integer> map, int i, int j) {
        Row nextRow = sheet.getRow(i + 1);
        int start = i;
        int end = i;
        if (JxcelUtil.inMergedRegion(sheet, i, j)) {
            CellRangeAddress currentMergeRegion = JxcelUtil.getCurrentMergeRegion(sheet, i, j);
            start = currentMergeRegion.getFirstColumn();
            end = currentMergeRegion.getLastColumn();
        }
        while (start <= end) {
            String secondRowValue = JxcelUtil.getCellValue(nextRow.getCell(start));
            if (secondRowValue != null) {
                switch (secondRowValue.trim()) {
                    case "企业":
                        map.put("socialCorp", start);
                        break;
                    case "个人":
                        map.put("socailPer", start);
                        break;
                    case "合计":
                        map.put("socialTotal", start);
                        break;
                }
            }
            start++;
        }
        return end;
    }

    private int fundMap(Sheet sheet, HashMap<String, Integer> map, int i, int j) {
        Row nextRow = sheet.getRow(i + 1);
        int start = i;
        int end = i;
        if (JxcelUtil.inMergedRegion(sheet, i, j)) {
            CellRangeAddress currentMergeRegion = JxcelUtil.getCurrentMergeRegion(sheet, i, j);
            start = currentMergeRegion.getFirstColumn();
            end = currentMergeRegion.getLastColumn();
        }
        while (start <= end) {
            String secondRowValue = JxcelUtil.getCellValue(nextRow.getCell(start));
            if (secondRowValue != null) {
                switch (secondRowValue.trim()) {
                    case "企业":
                        map.put("fundCorp", start);
                        break;
                    case "个人":
                        map.put("fundPer", start);
                        break;
                    case "合计":
                        map.put("fundTotal", start);
                        break;
                }
            }
            start++;
        }
        return end;
    }

    private Map<String, Long> getAllInsuranceMap() {
        HashMap<String, Long> map = new HashMap<>();
        for (BdInsurance insurance : bdInsuranceService.findAll(new BdInsurance())) {
            map.put(insurance.getName(), Long.valueOf(insurance.getInsuranceId()));
        }
        return map;
    }
}
