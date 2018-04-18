package com.xfs.analysis.utils;

import com.xfs.analysis.model.Cell;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Project: xfs-recommender
 * <p></p>
 * @Author: Run Main
 * @Date: 2017-03-05
 * @Copyright: 2017 www.xinfushe.com Inc. All rights reserved.
 */
public class JxcelUtil {

    private static Logger log = LoggerFactory.getLogger(JxcelUtil.class);


    public static String getCellValue(org.apache.poi.ss.usermodel.Cell cell) {
        String value = null;
        if (cell == null) {
            return value;
        }
        switch (cell.getCellTypeEnum().getCode()) {
            case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK:
                break;
            case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN:
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_ERROR:
                break;
            case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_FORMULA:
//                Workbook wb = cell.getSheet().getWorkbook();
//                CreationHelper crateHelper = wb.getCreationHelper();
//                FormulaEvaluator evaluator = crateHelper.createFormulaEvaluator();
//                value = getCellValue(evaluator.evaluateInCell(cell));
                value = null;
                break;
            case org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    String dataFormatString = cell.getCellStyle().getDataFormatString().contains("h") ? "yyyy-MM-dd HH:mm:ss" : "yyyy-MM-dd";
                    value = new SimpleDateFormat(dataFormatString).format(cell.getDateCellValue());
                } else {
//                    short format = cell.getCellStyle().getDataFormat();
//                    if (format == 14 || format == 31 || format == 57 || format == 58f || format == 176 || format == 177) {
//                        //日期
//                        value = new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue());
//                    } else if (format == 20 || format == 32) {
//                        //时间
//                        value = new SimpleDateFormat("HH:mm").format(cell.getDateCellValue());
//                    } else {
//
//                        value = NumberToTextConverter.toText(cell.getNumericCellValue());
//                    }
                    String formatString = cell.getCellStyle().getDataFormatString();
                    if (formatString.contains(";")) {
                        formatString = StringUtils.substringBefore(formatString, ";");
                    }
                    if (formatString != null && (formatString.contains("#"))) {
                        value = new DecimalFormat("#,##").format(cell.getNumericCellValue());
                    } else if (formatString != null && (formatString.contains("y") || formatString.contains("m") || formatString.contains("d") || formatString.contains("h") || formatString.contains("mm"))) {
                        try {
                            formatString = formatString.contains(";") ? StringUtils.substringBefore(formatString, ";") : formatString;
                            value = new SimpleDateFormat(formatString.contains("h") ? "yyyy-MM-dd HH:mm:ss" : "yyyy-MM-dd").format(cell.getDateCellValue());
                        } catch (Exception e) {
                            System.out.println("解析cell内容失败");
                            log.error("can not resolve cell type ", e);

                        }
                    } else {
                        value = NumberToTextConverter.toText(cell.getNumericCellValue());
                    }
                }
                break;
            default:
                break;
        }
        return value;
    }

    /**
     * 判断改行是否有合并单元格
     *
     * @param sheet
     * @param rowNum
     * @return
     */
    public static boolean existMergeCell(Sheet sheet, int rowNum) {
        boolean exist = false;
        for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
            if (rowNum >= sheet.getMergedRegion(i).getFirstRow() && rowNum <= sheet.getMergedRegion(i).getLastRow()) {
                exist = true;
                break;
            }
        }
        return exist;
    }

    public static Map<String, Object> getMergeRequet(Sheet sheet, int rowNumber) {
        List<Cell> request = new ArrayList<>();
        Map<String, Object> result = new HashMap<>();
        int maxRowNumber = rowNumber;
        for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
            CellRangeAddress mergedRegion = sheet.getMergedRegion(i);
            int firstRow = mergedRegion.getFirstRow();
            int lastRow = mergedRegion.getLastRow();
            if (rowNumber == firstRow && rowNumber <= lastRow) {
                if (maxRowNumber < lastRow) {
                    maxRowNumber = lastRow;
                }
            }
        }
        result.put("start", maxRowNumber);
        if (maxRowNumber == rowNumber) {
            resolveSingleMergeRow(sheet, rowNumber, sheet.getRow(rowNumber).getFirstCellNum(), sheet.getRow(rowNumber).getLastCellNum(), request);
        } else {
            resolveMultiRow(sheet, rowNumber, maxRowNumber, request);
        }
        result.put("request", request);
        return result;
    }

    //跨行解析单元格 嵌套格式不解析
    private static List<Cell> resolveMultiRow(Sheet sheet, int startRow, int endRow, List<Cell> request) {
        Row row = sheet.getRow(startRow);
        int firstCellNum = row.getFirstCellNum();
        int lastCellNum = row.getLastCellNum();
        for (; firstCellNum < lastCellNum; firstCellNum++) {
            if (!inMergedRegion(sheet, startRow, firstCellNum)) {
                singleColumnConcat(sheet, startRow, endRow, firstCellNum, request);
            } else {
                CellRangeAddress regin = getCurrentMergeRegion(sheet, startRow, firstCellNum);
                multiColumnConcat(sheet, startRow, endRow, regin.getFirstColumn(), regin.getLastColumn(), request);
                firstCellNum = regin.getLastColumn();
            }
        }
        return request;
    }

    private static void multiColumnConcat(Sheet sheet, int startRow, int endRow, int firstColumn, int lastColumn, List<Cell> request) {
        Map<Integer, Cell> tem = new HashMap<>();
        for (; startRow <= endRow; startRow++) {
            for (int start = firstColumn; start <= lastColumn; start++) {
                if (inMergedRegion(sheet, startRow, start)) {
                    CellRangeAddress currentMergeRegion = getCurrentMergeRegion(sheet, startRow, start);
                    int currentMergeRegionFirstColumn = currentMergeRegion.getFirstColumn();
                    Cell cell = tem.get(start);
                    if (cell == null) {
                        cell = Cell.creatCell(start, "");
                        tem.put(start, cell);
                    }
//                    String cellValue = getCellValue(sheet.getRow(currentMergeRegion.getFirstRow()).getCell(currentMergeRegion.getFirstColumn()));
                    String cellValue = getCellValue(sheet.getRow(startRow).getCell(start));
                    if (StringUtils.isNotBlank(cellValue) && !cell.getText().contains(cellValue.trim())) {
                        cell.setText(cell.getText() + cellValue);
                    } else if (start != currentMergeRegionFirstColumn && StringUtils.isBlank(cellValue)) {
                        cell.setText(tem.get(currentMergeRegionFirstColumn).getText());
                    }

//                    start = currentMergeRegion.getLastColumn();
                } else {
                    Cell cell = tem.get(start);
                    if (cell == null) {
                        cell = Cell.creatCell(start, "");
                        tem.put(start, cell);
                    }
                    String cellValue = getCellValue(sheet.getRow(startRow).getCell(start));
                    if (StringUtils.isNotBlank(cellValue) && !cell.getText().contains(cellValue.trim())) {
                        cell.setText(cell.getText() + cellValue.trim());
                    }
                }
            }
        }
        for (Map.Entry<Integer, Cell> entry : tem.entrySet()) {
            Cell value = entry.getValue();
            if (value != null && StringUtils.isNotBlank(value.getText())) {
                request.add(value);
            }
        }
    }

    //获取单元格所在的合并范围
    public static CellRangeAddress getCurrentMergeRegion(Sheet sheet, int startRow, int column) {
        CellRangeAddress cellRangeAddress = null;
        for (int i = 0; i < sheet.getNumMergedRegions(); i++) {

            CellRangeAddress mergedRegion = sheet.getMergedRegion(i);

            int firstRow = mergedRegion.getFirstRow();
            int lastRow = mergedRegion.getLastRow();
            if (startRow >= firstRow && startRow <= lastRow) {
                if (column >= mergedRegion.getFirstColumn() && column <= mergedRegion.getLastColumn()) {
                    cellRangeAddress = mergedRegion;
                    break;
                }
            }

        }
        return cellRangeAddress;
    }

    //纵向单列拼接
    private static void singleColumnConcat(Sheet sheet, int startRow, int endRow, int targetColumn, List<Cell> request) {
        Cell cell = Cell.creatCell(targetColumn, "");
        for (; startRow <= endRow; startRow++) {
            String cellValue = getCellValue(sheet.getRow(startRow).getCell(targetColumn));
            if (StringUtils.isNotBlank(cellValue) && !cell.getText().contains(cellValue.trim())) {
                cell.setText(cell.getText() + cellValue.trim());
            }
        }
        if (StringUtils.isNotBlank(cell.getText())) {
            request.add(cell);
        }

    }

    //判断该列是否在合并单元格区域内
    public static boolean inMergedRegion(Sheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if (row >= firstRow && row <= lastRow) {
                if (column >= firstColumn && column <= lastColumn) {
                    return true;
                }
            }
        }
        return false;
    }

    // 单行合并解析
    private static List<Cell> resolveSingleMergeRow(Sheet sheet, int rowNumber, int firstColumn, int lastColumn, List<Cell> request) {
        if (sheet != null) {
            Row row = sheet.getRow(rowNumber);
            if (row != null) {
                for (; firstColumn < lastColumn; firstColumn++) {
                    if (inMergedRegion(sheet, rowNumber, firstColumn)) {
                        CellRangeAddress currentMergeRegion = getCurrentMergeRegion(sheet, rowNumber, firstColumn);
                        org.apache.poi.ss.usermodel.Cell cell = sheet.getRow(rowNumber).getCell(currentMergeRegion.getFirstColumn());
                        String cellValue = getCellValue(cell);
                        if (cell != null && StringUtils.isNotBlank(cellValue)) {
                            request.add(Cell.creatCell(firstColumn, cellValue.trim()));
                        }
                        firstColumn = currentMergeRegion.getLastColumn();
                    } else {
                        org.apache.poi.ss.usermodel.Cell cell = row.getCell(firstColumn);
                        String cellValue = getCellValue(cell);
                        if (cell != null && StringUtils.isNotBlank(cellValue)) {
                            request.add(Cell.creatCell(firstColumn, cellValue.trim()));
                        }
                    }

                }
            }
        }
        return request;
    }

    public static boolean IsEmptyRow(Row row) {
        short firstCellNum = row.getFirstCellNum();
        short lastCellNum = row.getLastCellNum();
        while (firstCellNum <= lastCellNum) {
            String cellValue = getCellValue(row.getCell(firstCellNum++));
            if (StringUtils.isNotBlank(cellValue)) {
                return false;
            }
        }
        return true;
    }

}
