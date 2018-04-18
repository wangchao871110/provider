package com.xfs.sps.utils;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xfs.base.model.SysUploadfile;
import com.xfs.common.util.Config;
import com.xfs.common.util.FileUtil;
import com.xfs.common.util.ZipUtil;

import net.lingala.zip4j.exception.ZipException;

/**
 * Created by konglc on 2016-05-04. 导入ExcelUtil
 */
public class ImportExcelUtil {

	/**
	 * 读取Excel列表标题
	 *
	 * @param uploadFile
	 *            文件对象
	 * @return Map<String,List<String>> key : sheet名称 value : 标题列表
	 */
	public static Map<String, List<String>> readExcelTitle(SysUploadfile uploadFile) throws Exception {
		String fileRootPath = Config.getProperty("fileRootPath");// 文件根目录
		if (null == uploadFile) {
			return null;
		}
		String filePath = fileRootPath + File.separator + uploadFile.getFilepath() + File.separator
				+ uploadFile.getSavename();
		// uploadFile.getSavename()

		return readExcelTitle(filePath);
	}

	/**
	 * 读取Excel列表标题
	 *
	 * @param filePath
	 *            文件路径
	 * @return Map<String,List<String>> key : sheet名称 value : 标题列表
	 */
	public static Map<String, List<String>> readExcelTitle(String filePath) throws Exception {
		Map<String, List<String>> result = new LinkedHashMap<String, List<String>>();
		// 待读取excel文件集合
		List<File> excelFiles = new ArrayList<File>();
		getAllExcelFile(filePath, excelFiles);
		// 构造 Workbook 对象，strPath 传入文件路径
		Workbook xwb = null;
		for (File excelFile : excelFiles) {
			FileInputStream fin = new FileInputStream(excelFile);
			// 构造 Workbook 对象，strPath 传入文件路径
			xwb = new XSSFWorkbook(fin);
			// 读取表格内容
			for (int i = 0; i < xwb.getNumberOfSheets(); i++) {
				List<String> titleList = new ArrayList<String>();
				// 遇到隐藏的sheet页 不读取
				boolean sheethide = xwb.isSheetHidden(i);
				if (sheethide) {
					continue;
				}
				Sheet sheet = xwb.getSheetAt(i);
				// 定义 row、cell
				// 循环输出表格中的内容
				Row row = sheet.getRow(0);
				if (null != row) {// row可能为空
					if (row.getPhysicalNumberOfCells() > 1) {
						for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {
							String cellContent = null == row.getCell(j) ? "" : row.getCell(j).toString();
							if (StringUtils.isNotBlank(cellContent)) {
								titleList.add(cellContent);
							}
						}
						if (null != titleList && titleList.size() > 0) {
							result.put(sheet.getSheetName(), titleList);
						}

					}

				}

			}
			fin.close();
		}
		return result;
	}

	/**
	 * 建立excel和系统字段之间的对应关系
	 *
	 * @param filePath
	 *            文件对象
	 * @param sysfields
	 *            系统字段---汉字显示字段
	 * @param ormFieldName
	 *            映射字段名 --- excel显示字段
	 * @param orm
	 *            已存在对应关系
	 * @param ormFieldName
	 *            已存在对应关系-excel标题映射字段名
	 * @param <T>
	 *            业务项目vo
	 * @return Map<String,Map<String, Map<String, Object>>> key ----sheetName
	 *         value( key----al_match_orm key----un_match_sysfield
	 *         key----un_match_exceltitle value(key---excelTitle
	 *         value---sysTitle))
	 */
	public static <T, E> Map<String, Map<String, Map<String, Object>>> readExcelTitle(String filePath,
																					  List<T> sysfields, String sysFieldName, List<E> orm, String ormFieldName, String sheetName)
			throws Exception {
		Map<String, List<String>> titleMaps = readExcelTitle(filePath);
		if (null == titleMaps || titleMaps.isEmpty())
			return null;
		Map<String, Map<String, Map<String, Object>>> result = new HashMap<String, Map<String, Map<String, Object>>>();
		// 建立各个sheet页面之间的关系
		for (Map.Entry<String, List<String>> entry : titleMaps.entrySet()) {
			if (null != sheetName && !"".equals(sheetName) && !sheetName.equals(entry.getKey()))
				continue;
			Map<String, Map<String, Object>> child_result = new HashMap<>();
			Map<String, Object> al_match_orm = new HashMap<String, Object>();// 已匹配的关系集合
			Map<String, Object> un_match_exceltitle = new HashMap<String, Object>();// 未匹配的excel标题字段
			child_result.put("al_match_orm", al_match_orm);
			child_result.put("un_match_exceltitle", un_match_exceltitle);
			result.put(entry.getKey(), child_result);

			/***
			 * 构造系统字段对应
			 */
			Map<String, Object> sysfieldMap = new HashMap<String, Object>();
			for (T t : sysfields) {
				String key = JSON.toJSONString(t);
				if (t instanceof String) {
					key = String.valueOf(t);
				} else if (t instanceof Map) {
					Map<String, String> fileMap = (Map) t;
					for (Map.Entry<String, String> e_entry : fileMap.entrySet()) {
						key = e_entry.getKey();
						sysfieldMap.put(key, e_entry.getKey());
					}
					continue;
				}
				if (null != sysFieldName && !"".equals(sysFieldName)) {
					key = String.valueOf(JSON.parseObject(JSON.toJSONString(t)).get(String.valueOf(sysFieldName)));
				}
				sysfieldMap.put(key, t);
			}
			/**
			 * 构造已有关系字段对应
			 */
			Map<String, Object> ormfieldMap = new HashMap<String, Object>();
			if (null != orm && !orm.isEmpty()) {
				for (E e : orm) {
					String key = String
							.valueOf(JSON.parseObject(JSON.toJSONString(e)).get(String.valueOf(ormFieldName)));
					ormfieldMap.put(key, JSON.parseObject(JSON.toJSONString(e), new TypeReference<T>() {
					}));
				}

			}
			// 判断已存在对应关系
			for (String exceltitle : entry.getValue()) {
				if (null != ormfieldMap.get(exceltitle)) {// 在已存在的对应关系中查找
					al_match_orm.put(exceltitle, ormfieldMap.get(exceltitle));
					sysfieldMap.remove(String.valueOf(JSON.parseObject(JSON.toJSONString(ormfieldMap.get(exceltitle)))
							.get(String.valueOf(sysFieldName))));
				} else {
					if (null != sysfieldMap.get(exceltitle)) {// 在系统项目字段中查找匹配
						al_match_orm.put(exceltitle, sysfieldMap.get(exceltitle));
						sysfieldMap.remove(exceltitle);
					} else {// 为找到excel标题所对应的字段
						un_match_exceltitle.put(exceltitle, exceltitle);
					}
				}
			}
			child_result.put("un_match_sysfield", sysfieldMap);// 未匹配的系统项目字段
		}
		return result;
	}

	/**
	 * 根据建立好的对应关系读取Excel文件
	 *
	 * @param filePath
	 *            文件路径
	 * @param sysfields
	 *            系统属性 英文/汉字转换
	 * @param sheet_orm
	 *            对应关系 key---sheetName value(key:Excel标题 --- value:系统属性汉字表示)
	 * @return Map<String,Map<Integer,String>> key--sheet名称 value: key--行号 --
	 *         value行的json串
	 */
	public static Map<Integer, Map<String, String>> importExcel(String filePath, Map<String, String> sysfields,
																Map<String, Map<String, String>> sheet_orm) throws Exception {
		/**
		 * 获取文件路径
		 */
		Map<Integer, Map<String, String>> result = new LinkedHashMap<Integer, Map<String, String>>();
		// 待读取excel文件集合
		List<File> excelFiles = new ArrayList<File>();
		// 判断filePath文件类型： 压缩包或单独文件
		getAllExcelFile(filePath, excelFiles);
		// 构造 Workbook 对象，strPath 传入文件路径
		Workbook xwb = null;
		for (File excelFile : excelFiles) {
			FileInputStream fin = new FileInputStream(excelFile);
			// 构造 Workbook 对象，strPath 传入文件路径
			OPCPackage pkg = OPCPackage.open(fin);
			xwb = new XSSFWorkbook(pkg);
			for (Map.Entry<String, Map<String, String>> entry : sheet_orm.entrySet()) {
				// 读取表格内容
				Map<String, String> orm = entry.getValue();
				Sheet sheet = xwb.getSheet(entry.getKey());
				// 获取excel对应的关系
				Row row = null;
				int total_row = sheet.getLastRowNum();
				int total_colum = sheet.getRow(0).getLastCellNum();
				Map<Integer, String> colNumMap = new HashMap<Integer, String>();
				for (int r = 0; r <= total_row; r++) {
					Map<String, String> curMap = new LinkedHashMap<String, String>();
					/**
					 * 获取excel列表标题
					 */
					row = sheet.getRow(r);
					if (null == row) {// 处理row为空的情况
						continue;
					}
					if (r == 0) {
						for (int j = row.getFirstCellNum(); j < total_colum; j++) {
							String titleContent = null == row.getCell(j) ? "" : row.getCell(j).toString();
							if (StringUtils.isNotBlank(titleContent) && orm.containsKey(titleContent)) {
								String filename = titleContent;
								if (null != orm && !orm.isEmpty()) {
									filename = sysfields.get(orm.get(titleContent));
								}
								colNumMap.put(j, filename);
							}
						}
						continue;
					}
					for (Map.Entry<Integer, String> c_entry : colNumMap.entrySet()) {
						String value = null == row.getCell(c_entry.getKey()) ? ""
								: row.getCell(c_entry.getKey()).toString().trim();
						curMap.put(c_entry.getValue(), value);
					}

					// 判断 当前行数据 是否全部为空
					boolean isRealData = false;
					for (Map.Entry<String, String> curMapEntry : curMap.entrySet()) {
						if (StringUtils.isNotBlank(curMapEntry.getValue())) {
							isRealData = true;
							break;
						}
					}
					if (isRealData) {
						result.put(r + 1, curMap);
					}
					// if (StringUtils.isNotBlank(curMap.get("identity_code"))
					// && StringUtils.isNotBlank(curMap.get("name"))) {
					// result.put(r + 1, curMap);
					// }

				}
			}
			pkg.close();
			fin.close();
		}
		return result;
	}
	/**
	 * 根据建立好的对应关系读取多个sheel 页Excel文件
	 *
	 * @param filePath
	 *            文件路径
	 * @param sysfields
	 *            系统属性 英文/汉字转换
	 * @param sheet_orm
	 *            对应关系 key---sheetName value(key:Excel标题 --- value:系统属性汉字表示)
	 * @return Map<String,Map<Integer,String>> key--sheet名称 value: key--行号 --
	 *         value行的json串
	 */
	public static Map<String,Map<Integer, Map<String, String>>> importSheelExcel(String filePath, Map<String, String> sysfields,
																				 Map<String, Map<String, String>> sheet_orm) throws Exception {
		/**
		 * 获取文件路径
		 */
		Map<String,Map<Integer, Map<String, String>>> sheelMap = new LinkedHashMap<>();
		// 待读取excel文件集合
		List<File> excelFiles = new ArrayList<File>();
		// 判断filePath文件类型： 压缩包或单独文件
		getAllExcelFile(filePath, excelFiles);
		// 构造 Workbook 对象，strPath 传入文件路径
		Workbook xwb = null;
		for (File excelFile : excelFiles) {
			FileInputStream fin = new FileInputStream(excelFile);
			// 构造 Workbook 对象，strPath 传入文件路径
			OPCPackage pkg = OPCPackage.open(fin);
			xwb = new XSSFWorkbook(pkg);
			for (Map.Entry<String, Map<String, String>> entry : sheet_orm.entrySet()) {
				// 读取表格内容
				Map<Integer, Map<String, String>> result = new LinkedHashMap<Integer, Map<String, String>>();
				Map<String, String> orm = entry.getValue();
				Sheet sheet = xwb.getSheet(entry.getKey());
				// 获取excel对应的关系
				Row row = null;
				int total_row = sheet.getLastRowNum();
				int total_colum = sheet.getRow(0).getLastCellNum();
				Map<Integer, String> colNumMap = new HashMap<Integer, String>();
				for (int r = 0; r <= total_row; r++) {
					Map<String, String> curMap = new LinkedHashMap<String, String>();
					/**
					 * 获取excel列表标题
					 */
					row = sheet.getRow(r);
					if (null == row) {// 处理row为空的情况
						continue;
					}
					if (r == 0) {
						for (int j = row.getFirstCellNum(); j < total_colum; j++) {
							String titleContent = null == row.getCell(j) ? "" : row.getCell(j).toString();
							if (StringUtils.isNotBlank(titleContent) && orm.containsKey(titleContent)) {
								String filename = titleContent;
								if (null != orm && !orm.isEmpty()) {
									filename = sysfields.get(orm.get(titleContent));
								}
								colNumMap.put(j, filename);
							}
						}
						continue;
					}
					for (Map.Entry<Integer, String> c_entry : colNumMap.entrySet()) {
						String value = "";
						if(row.getCell(c_entry.getKey()) != null) {
							if(row.getCell(c_entry.getKey()).getCellType() == Cell.CELL_TYPE_NUMERIC){
								row.getCell(c_entry.getKey()).setCellType(Cell.CELL_TYPE_STRING);
								value = row.getCell(c_entry.getKey()).getStringCellValue();
							}else {
								value = row.getCell(c_entry.getKey()).toString();
							}
						}
						curMap.put(c_entry.getValue(), value);
					}

					// 判断 当前行数据 是否全部为空
					boolean isRealData = false;
					for (Map.Entry<String, String> curMapEntry : curMap.entrySet()) {
						if (StringUtils.isNotBlank(curMapEntry.getValue())) {
							isRealData = true;
							break;
						}
					}
					if (isRealData) {
						result.put(r + 1, curMap);
					}
					// if (StringUtils.isNotBlank(curMap.get("identity_code"))
					// && StringUtils.isNotBlank(curMap.get("name"))) {
					// result.put(r + 1, curMap);
					// }

				}
				if(!result.isEmpty()){
					sheelMap.put(entry.getKey(),result);
				}
			}
			pkg.close();
			fin.close();
		}
		return sheelMap;
	}
	/**
	 * importExcel导入衍生 数据管理处使用
	 *
	 * @param filePath
	 * @param sheet_orm
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Map<String, Map<String, String>>> importExcels(String filePath,
																			 Map<String, Map<String, String>> sheet_orm) throws Exception {
		Map<String, Map<String, Map<String, String>>> result = new LinkedHashMap<String, Map<String, Map<String, String>>>();
		// 待读取excel文件集合
		List<File> excelFiles = new ArrayList<File>();
		// 判断filePath文件类型： 压缩包或单独文件
		getAllExcelFile(filePath, excelFiles);
		// 构造 Workbook 对象，strPath 传入文件路径
		Workbook xwb = null;
		for (File excelFile : excelFiles) {
			FileInputStream fin = new FileInputStream(excelFile);
			// 构造 Workbook 对象，strPath 传入文件路径
			xwb = new XSSFWorkbook(fin);
			// 读取表格内容
			for (int i = 0; i < xwb.getNumberOfSheets(); i++) {
				// 遇到隐藏的sheet页 不读取
				boolean sheethide = xwb.isSheetHidden(i);
				if (sheethide) {
					continue;
				}
				Sheet sheet = xwb.getSheetAt(i);
				Map<String, Map<String, String>> sheetMap = new LinkedHashMap<String, Map<String, String>>();
				// 获取excel对应的关系
				Map<String, String> orm = null;
				if (null != sheet_orm && !sheet_orm.isEmpty())
					orm = sheet_orm.get(sheet.getSheetName());
				Row row = null;
				int total_row = sheet.getLastRowNum();// 总行数
				if (null == sheet.getRow(0)) {
					// 当标题列为空的时候直接跳出循环
					Map<String, String> orms = new HashMap<String, String>();
					orms.put("impError", "标题列为空！");
					sheetMap.put("impError", orms);
					result.put("error", sheetMap);
					break;
				}
				int total_colum = sheet.getRow(0).getLastCellNum();// 总列数
				// colNumMap <索引，单元格的值>
				Map<Integer, String> colNumMap = new LinkedHashMap<Integer, String>();
				for (int r = 0; r <= total_row; r++) {
					row = sheet.getRow(r);
					if (null == row) {
						continue;
					}
					Map<String, String> curMap = new LinkedHashMap<String, String>();
					for (int j = 0; j < total_colum; j++) {
						// row.getFirstCellNum()
						String titleContent = "";
						Cell oneCell = row.getCell(j);
						if (null != oneCell) {
							switch (oneCell.getCellType()) {
								case XSSFCell.CELL_TYPE_STRING:
									titleContent = oneCell.getRichStringCellValue().toString();
									break;
								case XSSFCell.CELL_TYPE_BOOLEAN:
									titleContent = oneCell.toString();
									break;
								case XSSFCell.CELL_TYPE_FORMULA:
									try {
										titleContent = String.valueOf(oneCell.getNumericCellValue());
									} catch (IllegalStateException e) {
										try {
											titleContent = String.valueOf(oneCell.getRichStringCellValue());
										} catch (Exception e1) {
											titleContent = "";
										}
									}
									titleContent = oneCell.toString();
									break;
								case HSSFCell.CELL_TYPE_NUMERIC:// 数字类型
									short dateFormat = oneCell.getCellStyle().getDataFormat();
									SimpleDateFormat sdf = null;
									if (oneCell.getCellStyle().getDataFormatString().indexOf("%") != -1) {// 百分数
										titleContent = oneCell.getNumericCellValue() * 100 + "%";
									} else if (dateFormat == 14 || dateFormat == 31 || dateFormat == 57 || dateFormat == 58
											|| dateFormat == 20 || dateFormat == 32) { // ---------------------------日期类型
										if (dateFormat == 14 || dateFormat == 31 || dateFormat == 57 || dateFormat == 58) {
											// 日期
											sdf = new SimpleDateFormat("yyyy-MM-dd");
										} else if (dateFormat == 20 || dateFormat == 32) {
											// 时间
											sdf = new SimpleDateFormat("HH:mm");
										}
										double value = oneCell.getNumericCellValue();
										Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
										titleContent = sdf.format(date);
									} else {
										double value = oneCell.getNumericCellValue();
										CellStyle style = oneCell.getCellStyle();
										DecimalFormat format = new DecimalFormat();
										String temp = style.getDataFormatString();
										// 单元格设置成常规
										if ("General".equals(temp)) {
											format.applyPattern("#");
										}
										titleContent = format.format(value);
									}
									break;
								case XSSFCell.CELL_TYPE_BLANK:
									titleContent = "";
									break;
								default:
									titleContent = "";
									break;
							}
						} else {
							titleContent = "";
						}

						if (r == 0) {
							colNumMap.put(j, titleContent);
						}
						if (StringUtils.isNotBlank(titleContent)) {
							String filename = titleContent;
							if (null != orm && !orm.isEmpty())
								filename = orm.get(titleContent);
							curMap.put(colNumMap.get(j), filename);
						} else {
							curMap.put(colNumMap.get(j), "");
						}
						sheetMap.put("row" + r + "", curMap);
					}
				}
				result.put(sheet.getSheetName(), sheetMap);
			}
			fin.close();
		}
		return result;
	}

	/**
	 * 获取所有Excel文件
	 *
	 * @param filePath
	 * @param excelFiles
	 * @throws ZipException
	 */
	private static void getAllExcelFile(String filePath, List<File> excelFiles) throws ZipException {
		// 判断filePath文件类型： 压缩包或单独文件
		if (filePath.indexOf("zip") > -1) {
			String excelPath = filePath.substring(0, filePath.length() - 4);
			File subFiles = new File(excelPath);
			List<File> fileLists = null;
			if (subFiles.isDirectory()) {
				fileLists = FileUtil.getSubFiles(subFiles);
			} else {
				// 解压
				ZipUtil.unzip(filePath, filePath.substring(0, filePath.length() - 4), null);
				fileLists = FileUtil.getSubFiles(subFiles);
			}

			if (null != fileLists && fileLists.size() > 0) {
				for (File curFile : fileLists) {
					if (null != curFile && curFile.exists()
							&& (curFile.getName().indexOf("xlsx") > -1 || curFile.getName().indexOf("xls") > -1)) {
						excelFiles.add(curFile);
					}
				}
			}
		} else {
			excelFiles.add(new File(filePath));
		}
	}

	/**
	 * 读取 excel 第一列 业务类型字段
	 *
	 * @author lifq
	 *
	 *         2016年5月23日 下午2:36:18
	 */
	public static List<String> readExcelBsType(String filePath, String sheetName) throws Exception {
		List<String> bsTypeList = new ArrayList<String>();
		// 待读取excel文件集合
		File excelFile = null;
		if (filePath.indexOf("zip") > 0) {
			String excelPath = filePath.substring(0, filePath.length() - 4);
			File subFiles = new File(excelPath);
			List<File> fileLists = null;
			if (subFiles.isDirectory()) {
				fileLists = FileUtil.getSubFiles(subFiles);
			} else {
				// 解压
				ZipUtil.unzip(filePath, filePath.substring(0, filePath.length() - 4), null);
				fileLists = FileUtil.getSubFiles(subFiles);
			}
			if (null != fileLists && fileLists.size() > 0) {
				for (File file : fileLists) {
					if (file.getName().indexOf("xlsx") > 0) {
						excelFile = file;
						break;
					}
				}
			}
		} else {
			excelFile = new File(filePath);
		}
		if (null != excelFile) {
			FileInputStream fin = new FileInputStream(excelFile);
			// 构造 Workbook 对象，strPath 传入文件路径
			Workbook xwb = new XSSFWorkbook(fin);
			Sheet sheet = xwb.getSheet(sheetName);
			if (null == sheet) {
				return null;
			}
			// 定义 row、cell
			// 循环输出表格中的内容
			Row row = sheet.getRow(0);
			if (null == row) {
				return null;
			}
			String titleContent = null == row.getCell(0) ? "" : row.getCell(0).toString();

			if (!"业务类型".equals(titleContent)) {
				return null;
			}
			// 处理内容行
			for (int r = 1; r <= sheet.getLastRowNum(); r++) {
				row = sheet.getRow(r);
				if (null == row) {
					continue;
				}
				String content = null == row.getCell(0) ? "" : row.getCell(0).toString().trim();
				if (!bsTypeList.contains(content)) {
					bsTypeList.add(content);
				}
			}
			fin.close();
		}
		return bsTypeList;
	}
	/**
	 * 建立excel和系统字段之间的对应关系
	 *
	 * @param filePath
	 *            文件对象
	 * @param sysfields
	 *            系统字段---汉字显示字段
	 * @param ormFieldName
	 *            映射字段名 --- excel显示字段
	 * @param orm
	 *            已存在对应关系
	 * @param ormFieldName
	 *            已存在对应关系-excel标题映射字段名
	 * @param <T>
	 *            业务项目vo
	 * @ignoreStr
	 *           忽略匹配前缀
	 * @return Map<String,Map<String, Map<String, Object>>> key ----sheetName
	 *         value( key----al_match_orm key----un_match_sysfield
	 *         key----un_match_exceltitle value(key---excelTitle
	 *         value---sysTitle))
	 */
	public static <T, E> Map<String, Map<String, Map<String, Object>>> readExcelTitle(String filePath,List<T> sysfields, String sysFieldName, List<E> orm, String ormFieldName, String sheetName,String ignoreStr)
			throws Exception {
		Map<String, List<String>> titleMaps = readExcelTitle(filePath);
		if (null == titleMaps || titleMaps.isEmpty())
			return null;
		Map<String, Map<String, Map<String, Object>>> result = new HashMap<String, Map<String, Map<String, Object>>>();
		// 建立各个sheet页面之间的关系
		for (Map.Entry<String, List<String>> entry : titleMaps.entrySet()) {
			if (null != sheetName && !"".equals(sheetName) && !sheetName.equals(entry.getKey()))
				continue;
			Map<String, Map<String, Object>> child_result = new HashMap<>();
			Map<String, Object> al_match_orm = new HashMap<String, Object>();// 已匹配的关系集合
			Map<String, Object> un_match_exceltitle = new HashMap<String, Object>();// 未匹配的excel标题字段
			child_result.put("al_match_orm", al_match_orm);
			child_result.put("un_match_exceltitle", un_match_exceltitle);
			result.put(entry.getKey(), child_result);

			/***
			 * 构造系统字段对应
			 */
			Map<String, Object> sysfieldMap = new HashMap<String, Object>();
			for (T t : sysfields) {
				String key = JSON.toJSONString(t);
				if (t instanceof String) {
					key = String.valueOf(t);
				} else if (t instanceof Map) {
					Map<String, String> fileMap = (Map) t;
					for (Map.Entry<String, String> e_entry : fileMap.entrySet()) {
						key = e_entry.getKey();
						sysfieldMap.put(key, e_entry.getKey());
					}
					continue;
				}
				if (null != sysFieldName && !"".equals(sysFieldName)) {
					key = String.valueOf(JSON.parseObject(JSON.toJSONString(t)).get(String.valueOf(sysFieldName)));
				}
				sysfieldMap.put(key, t);
			}
			/**
			 * 构造已有关系字段对应
			 */
			Map<String, Object> ormfieldMap = new HashMap<String, Object>();
			if (null != orm && !orm.isEmpty()) {
				for (E e : orm) {
					String key = String
							.valueOf(JSON.parseObject(JSON.toJSONString(e)).get(String.valueOf(ormFieldName)));
					ormfieldMap.put(key, JSON.parseObject(JSON.toJSONString(e), new TypeReference<T>() {
					}));
				}

			}
			boolean titleIgnore = false;
			if(StringUtils.isNotBlank(ignoreStr)){
				titleIgnore = true;
			}
			// 判断已存在对应关系
			for (String exceltitle : entry.getValue()) {
				String newTitle = null;
				if(titleIgnore){
					newTitle = exceltitle.replace(ignoreStr,"").trim();
				}
				if (null != ormfieldMap.get(exceltitle)) {// 在已存在的对应关系中查找
					al_match_orm.put(exceltitle, ormfieldMap.get(exceltitle));
					sysfieldMap.remove(String.valueOf(JSON.parseObject(JSON.toJSONString(ormfieldMap.get(exceltitle)))
							.get(String.valueOf(sysFieldName))));
				} else {
					if(titleIgnore){
						if (null != sysfieldMap.get(newTitle)) {// 在系统项目字段中查找匹配
							al_match_orm.put(exceltitle, sysfieldMap.get(newTitle));
							sysfieldMap.remove(newTitle);
						} else {// 为找到excel标题所对应的字段
							un_match_exceltitle.put(exceltitle, exceltitle);
						}
					}else {
						if (null != sysfieldMap.get(exceltitle)) {// 在系统项目字段中查找匹配
							al_match_orm.put(exceltitle, sysfieldMap.get(exceltitle));
							sysfieldMap.remove(exceltitle);
						} else {// 为找到excel标题所对应的字段
							un_match_exceltitle.put(exceltitle, exceltitle);
						}
					}
				}
			}
			child_result.put("un_match_sysfield", sysfieldMap);// 未匹配的系统项目字段
		}
		return result;
	}

	/**
	 * 根据建立好的对应关系读取Excel文件,如果有合并列则取最后一列值
	 *
	 * @param filePath
	 *            文件路径
	 * @param sysfields
	 *            系统属性 英文/汉字转换
	 * @param sheet_orm
	 *            对应关系 key---sheetName value(key:Excel标题 --- value:系统属性汉字表示)
	 * @return Map<String,Map<Integer,String>> key--sheet名称 value: key--行号 --
	 *         value行的json串
	 */
	public static Map<Integer, Map<String, String>> importExcelGetLastCell(String filePath, Map<String, String> sysfields,
																		   Map<String, Map<String, String>> sheet_orm) throws Exception {
		/**
		 * 获取文件路径
		 */
		Map<Integer, Map<String, String>> result = new LinkedHashMap<Integer, Map<String, String>>();
		// 待读取excel文件集合
		List<File> excelFiles = new ArrayList<File>();
		// 判断filePath文件类型： 压缩包或单独文件
		getAllExcelFile(filePath, excelFiles);
		// 构造 Workbook 对象，strPath 传入文件路径
		Workbook xwb = null;
		for (File excelFile : excelFiles) {
			FileInputStream fin = new FileInputStream(excelFile);
			// 构造 Workbook 对象，strPath 传入文件路径
			OPCPackage pkg = OPCPackage.open(fin);
			xwb = new XSSFWorkbook(pkg);
			XSSFCell cell = null;
			for (Map.Entry<String, Map<String, String>> entry : sheet_orm.entrySet()) {
				// 读取表格内容
				Map<String, String> orm = entry.getValue();
				Sheet sheet = xwb.getSheet(entry.getKey());
				// 获取excel对应的关系
				Row row = null;
				int total_row = sheet.getLastRowNum();
				int total_colum = sheet.getRow(0).getLastCellNum();
				Map<Integer, String> colNumMap = new HashMap<Integer, String>();
				for (int r = 0; r <= total_row; r++) {
					Map<String, String> curMap = new LinkedHashMap<String, String>();
//					 cell = (XSSFCell) row.getCell(r);
//				     // 设置字段为字符类型
//				     cell.setCellType(XSSFCell.CELL_TYPE_STRING);
					/**
					 * 获取excel列表标题
					 */
					row = sheet.getRow(r);
					if (null == row) {// 处理row为空的情况
						continue;
					}
					if (r == 0) {
						for (int j = row.getFirstCellNum(); j < total_colum; j++) {
							String titleContent = null == row.getCell(j) ? "" : row.getCell(j) .toString().trim();
							if (StringUtils.isNotBlank(titleContent) && orm.containsKey(titleContent)) {
								String filename = titleContent;
								//取合并单元格最后一列列号
								Integer valnum = getMergedRegionLastCellNum(sheet,row.getCell(j));
								if (null != orm && !orm.isEmpty()) {
									filename = sysfields.get(orm.get(titleContent));
								}
								if(valnum != null){
									colNumMap.put(valnum, filename);
								}else {
									colNumMap.put(j, filename);
								}
							}
						}
						continue;
					}
					for (Map.Entry<Integer, String> c_entry : colNumMap.entrySet()) {
						Cell cells = row.getCell(c_entry.getKey());
						if(null != row.getCell(c_entry.getKey())){
							cells.setCellType(XSSFCell.CELL_TYPE_STRING);
						}
						String value = null ==cells  ? "": cells.toString().trim();
						curMap.put(c_entry.getValue(), value);
					}

					// 判断 当前行数据 是否全部为空
					boolean isRealData = false;
					for (Map.Entry<String, String> curMapEntry : curMap.entrySet()) {
						if (StringUtils.isNotBlank(curMapEntry.getValue())) {
							isRealData = true;
							break;
						}
					}
					if (isRealData) {
						result.put(r + 1, curMap);
					}
					// if (StringUtils.isNotBlank(curMap.get("identity_code"))
					// && StringUtils.isNotBlank(curMap.get("name"))) {
					// result.put(r + 1, curMap);
					// }

				}
			}
			pkg.close();
			fin.close();
		}
		return result;
	}


	/**
	 * 获取该合并单元格最后列号
	 * @param sheet
	 * @param cell
	 * @return
	 */
	public static Integer getMergedRegionLastCellNum(Sheet sheet, Cell cell) {
		// 得到一个sheet中有多少个合并单元格
		int sheetmergerCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetmergerCount; i++) {
			// 得出具体的合并单元格
			CellRangeAddress ca = sheet.getMergedRegion(i);
			// 得到合并单元格的起始行, 结束行, 起始列, 结束列
			int firstC = ca.getFirstColumn();
			int lastC = ca.getLastColumn();
			int firstR = ca.getFirstRow();
			int lastR = ca.getLastRow();
			// 判断该单元格是否在合并单元格范围之内, 如果是, 则返回 true
			if (cell.getColumnIndex() <= lastC && cell.getColumnIndex() >= firstC) {
				if (cell.getRowIndex() <= lastR && cell.getRowIndex() >= firstR) {
					return lastC;
				}
			}
		}
		return null;
	}

	/**
	 * 获取excel sheel名称集合
	 * @return
	 */
	public static List<String> getSheelNames(String filePath)throws Exception{
		// 待读取excel文件集合
		List<File> excelFiles = new ArrayList<File>();
		// 判断filePath文件类型： 压缩包或单独文件
		getAllExcelFile(filePath, excelFiles);
		// 构造 Workbook 对象，strPath 传入文件路径
		Workbook xwb = null;
		List<String> sheelNames = new ArrayList<>();
		for (File excelFile : excelFiles) {
			FileInputStream fin = new FileInputStream(excelFile);
			// 构造 Workbook 对象，strPath 传入文件路径
			OPCPackage pkg = OPCPackage.open(fin);
			xwb = new XSSFWorkbook(pkg);
			for(int i=0;i<xwb.getNumberOfSheets();i++){
				sheelNames.add(xwb.getSheetName(i));
			}
		}
		return sheelNames;
	}

	/**
	 *  只读取的一个sheel页 按数据起始行读取数据  默认标题行为 起始行-1
	 *  @param   [filePath, sysfields, sheet_orm, dataBeginIndex]
	 * @return    : java.util.Map<java.lang.String,java.util.Map<java.lang.Integer,java.util.Map<java.lang.String,java.lang.String>>>
	 *  @createDate   : 2016/12/27 13:35
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/12/27 13:35
	 *  @updateAuthor  :
	 */
	public static Map<Integer, Map<String, String>> importSheelExcel(String filePath, Map<String, String> sysfields,
																	 Integer dataBeginIndex) throws Exception {
		/**
		 * 获取文件路径
		 */
		Map<String,Map<Integer, Map<String, String>>> sheelMap = new LinkedHashMap<>();
		// 待读取excel文件集合
		List<File> excelFiles = new ArrayList<File>();
		// 判断filePath文件类型： 压缩包或单独文件
		getAllExcelFile(filePath, excelFiles);
		// 构造 Workbook 对象，strPath 传入文件路径
		Workbook xwb = null;
		// 读取表格内容
		Map<Integer, Map<String, String>> result = new LinkedHashMap<Integer, Map<String, String>>();
		for (File excelFile : excelFiles) {
			FileInputStream fin = new FileInputStream(excelFile);
			// 构造 Workbook 对象，strPath 传入文件路径
			OPCPackage pkg = OPCPackage.open(fin);
			xwb = new XSSFWorkbook(pkg);
			Sheet sheet = xwb.getSheetAt(0);
			// 获取excel对应的关系
			Row row = null;
			int total_row = sheet.getLastRowNum();
			int total_colum = sheet.getRow(dataBeginIndex-1).getLastCellNum();
			Map<Integer, String> colNumMap = new HashMap<Integer, String>();
			for (int r = dataBeginIndex-1; r <= total_row; r++) {
				Map<String, String> curMap = new LinkedHashMap<String, String>();
				/**
				 * 获取excel列表标题
				 */
				row = sheet.getRow(r);
				if (null == row) {// 处理row为空的情况
					continue;
				}
				if (r == dataBeginIndex-1) {
					for (int j = row.getFirstCellNum(); j < total_colum; j++) {
						String titleContent = null == row.getCell(j) ? "" : row.getCell(j).toString().trim();
						if (StringUtils.isNotBlank(titleContent) && sysfields.containsKey(titleContent)) {
							String filename = sysfields.get(titleContent);
							colNumMap.put(j, filename);
						}
					}
					continue;
				}
				for (Map.Entry<Integer, String> c_entry : colNumMap.entrySet()) {
					String value = "";
					if(row.getCell(c_entry.getKey()) != null) {
						if(row.getCell(c_entry.getKey()).getCellType() == Cell.CELL_TYPE_NUMERIC){
							row.getCell(c_entry.getKey()).setCellType(Cell.CELL_TYPE_STRING);
							value = row.getCell(c_entry.getKey()).getStringCellValue();
						}else {
							value = row.getCell(c_entry.getKey()).toString();
						}
					}
					curMap.put(c_entry.getValue(), value);
				}

				// 判断 当前行数据 是否全部为空
				boolean isRealData = false;
				for (Map.Entry<String, String> curMapEntry : curMap.entrySet()) {
					if (StringUtils.isNotBlank(curMapEntry.getValue())) {
						isRealData = true;
						break;
					}
				}
				if (isRealData) {
					result.put(r + 1, curMap);
				}
				// if (StringUtils.isNotBlank(curMap.get("identity_code"))
				// && StringUtils.isNotBlank(curMap.get("name"))) {
				// result.put(r + 1, curMap);
				// }

			}

			pkg.close();
			fin.close();
		}
		return result;
	}
}
