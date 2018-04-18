package com.xfs.sp.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.base.service.SysUploadfileService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.exception.BusinessException;
import com.xfs.common.page.PageModel;
import com.xfs.common.util.DateUtil;
import com.xfs.sp.dao.SpsAccountinfoDao;
import com.xfs.sp.model.SpsAccountinfo;
import com.xfs.sp.service.SpsAccountinfoService;
import com.xfs.user.model.SysUser;

@Service
public class SpsAccountinfoServiceImpl implements SpsAccountinfoService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsAccountinfoServiceImpl.class);

	@Autowired
	private SpsAccountinfoDao spsAccountinfoDao;
	@Autowired
	private SysUploadfileService sysUploadfileService;

	public int save(ContextInfo cti, SpsAccountinfo vo) {
		return spsAccountinfoDao.save(cti, vo);
	}

	public int insert(ContextInfo cti, SpsAccountinfo vo) {
		return spsAccountinfoDao.insert(cti, vo);
	}

	public int remove(ContextInfo cti, SpsAccountinfo vo) {
		return spsAccountinfoDao.remove(cti, vo);
	}

	public int update(ContextInfo cti, SpsAccountinfo vo) {
		return spsAccountinfoDao.update(cti, vo);
	}

	public PageModel findPage(PageInfo pi, SpsAccountinfo vo) throws BusinessException {

		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		if (StringUtils.isNotEmpty(vo.getTimeStar())) {
			vo.setDealDateFrom(DateUtil.parseDate(vo.getTimeStar() + " 00:00:00", "yyyy-MM-dd HH:mm:ss"));
		}
		if (StringUtils.isNotEmpty(vo.getTimeEnd())) {
			vo.setDealDateTo(DateUtil.parseDate(vo.getTimeEnd() + " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
		}
		Integer total = spsAccountinfoDao.countFreeFindBySpId(vo);
		pm.setTotal(total);
		Integer totalMoney = spsAccountinfoDao.countMoneyFindBySpId(vo);
		pm.setTotalMoney(totalMoney);
		List<SpsAccountinfo> datas = spsAccountinfoDao.freeFindBySpId(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;

	}

	public List<SpsAccountinfo> findAll(SpsAccountinfo vo) {

		List<SpsAccountinfo> datas = spsAccountinfoDao.freeFind(vo);
		return datas;

	}

	@Override
	public Result importUpdateAccExcel(ContextInfo cti, Integer fileId, String selectContent) throws BusinessException {

		Result result = Result.createResult().setSuccess(false);
		if (null == fileId) {
			result.setError("fileId不能为空！");
			result.setSuccess(false);
			return result;
		}
		String filePath = sysUploadfileService.getPhysicsFile(fileId);
		if (null == filePath || "".equals(filePath)) {
			result.setError("文件不存在！");
			result.setSuccess(false);
			return result;
		}
		if (StringUtils.isEmpty(selectContent)) {
			result.setSuccess(false);
			result.setError("对应关系不能为空！");
			return result;
		}

		// colsMap 姓名=personName
		Map<String, String> colNameMap = new HashMap<String, String>();
		Map<String, String> colNamesMap = new HashMap<String, String>();
		String selectArr[] = selectContent.split(",");
		if (null != selectArr) {
			for (int i = 0; i < selectArr.length; i++) {
				String itemStr = selectArr[i];
				String[] itemArr = itemStr.split("=");
				if (null != itemArr) {
					colNameMap.put(itemArr[0], itemArr[1]);
					colNamesMap.put(itemArr[1], itemArr[0]);
				}
			}
		}
		// 构造 XSSFWorkbook 对象，strPath 传入文件路径
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		List<Map<String, String>> errorDataList = new ArrayList<Map<String, String>>();
		Date date = new Date();
		boolean isError = false;
		OPCPackage pkg;
		XSSFWorkbook xwb = null;
		try {
			pkg = OPCPackage.open(filePath);
			xwb = new XSSFWorkbook(pkg);
			// colNumMap 0=personName
			Map<Integer, String> colNumMap = new HashMap<Integer, String>();
			// 读取表格内容
			XSSFSheet sheet = xwb.getSheetAt(0);
			XSSFRow row = null;
			// 处理标题行
			row = sheet.getRow(0);
			for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {
				String titleContent = row.getCell(j).toString();
				if (StringUtils.isNotBlank(titleContent)) {
					if (colNameMap.containsKey(titleContent)) {
						String fieldName = colNameMap.get(titleContent);
						colNumMap.put(j, fieldName);
					}
				}
			}
			// 根据银行名称 日期(y-M-d h:m:s)校验导入账款重复数据
			Map<String, String> outBankNameDate = new HashMap<String, String>();
			Map<String, String> inBankNameDate = new HashMap<String, String>();
			List<SpsAccountinfo> list = spsAccountinfoDao.findBankNameDateBySpId(cti.getOrgId());
			for (SpsAccountinfo spsAccountinfo : list) {
				outBankNameDate.put(spsAccountinfo.getPayBank(),
						DateUtil.getDateStr(spsAccountinfo.getDealDate(), "yyyyMMddHHmmss"));
			}
			// 校验 并 处理内容行
			for (int r = 1; r <= sheet.getLastRowNum(); r++) {
				row = sheet.getRow(r);
				if (null == row) {
					continue;
				}
				// 处理列
				Map<String, String> curMap = new HashMap<String, String>();
				String error = "";
				curMap.put("row", r + 1 + "");

				for (Map.Entry<Integer, String> entry : colNumMap.entrySet()) {
					if (null != row.getCell(entry.getKey()) && !"".equals(row.getCell(entry.getKey()).toString())) {
						curMap.put(entry.getValue(), row.getCell(entry.getKey()).toString());
					}
				}
				if (StringUtils.isEmpty(curMap.get("付款账户")))
					error += "付款账户为空!<br>";
				if (StringUtils.isEmpty(curMap.get("付款账号")))
					error += "付款账号为空!<br>";
				if (StringUtils.isEmpty(curMap.get("付款银行")))
					error += "付款银行为空!<br>";
				if (StringUtils.isEmpty(curMap.get("交易日期"))) {
					error += "交易日期为空!<br>";
				} else {
					if (!StringUtils.isEmpty(curMap.get("交易时间"))) {
						try {
							SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMddHHmmss");
							timeFormat.setLenient(false);// 严格校验
							timeFormat.parse(curMap.get("交易日期") + "" + curMap.get("交易时间"));
						} catch (Exception e) {
							e.printStackTrace();
							error += "交易日期格式错误!<br>";
						}
					} else {
						error += "交易时间为空!<br>";
					}
				}
				if (StringUtils.isEmpty(curMap.get("交易金额"))) {
					error += "交易金额为空!<br>";
				}
				if (!StringUtils.isEmpty(curMap.get("交易日期")) && !StringUtils.isEmpty(curMap.get("交易时间"))
						&& !StringUtils.isEmpty(curMap.get("付款银行"))) {

					if ((curMap.get("交易日期") + curMap.get("交易时间")).equals(outBankNameDate.get(curMap.get("付款银行")))) {
						error += "导入数据已存在!<br>";
					} else if ((curMap.get("交易日期") + curMap.get("交易时间"))
							.equals(inBankNameDate.get(curMap.get("付款银行")))) {
						error += "导入数据重复!<br>";
					} else {
						inBankNameDate.put(curMap.get("付款银行"), curMap.get("交易日期") + curMap.get("交易时间"));
					}

				}
				if (!"".equals(error)) {
					curMap.put("error", error);
					errorDataList.add(curMap);
					isError = true;
				}
				dataList.add(curMap);
			}
			// 关闭
			pkg.close();
		} catch (Exception e) {
			e.printStackTrace();
			result.setError("excel读取出错！");
			result.setSuccess(false);
			throw new BusinessException("excel读取出错！");
		}
		if (isError) {
			result.setError("导入数据出错！");
			result.setSuccess(false);
			result.setDataValue("errorDataList", errorDataList);
			result.setDataValue("errorNum", errorDataList.size());
		} else {
			// 数据校验通过后 开始导入
			if (null != dataList && dataList.size() > 0) {
				SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				for (int i = 0; i < dataList.size(); i++) {
					Map<String, String> curMap = dataList.get(i);
					curMap.remove("row");
					SpsAccountinfo spsAcc = new SpsAccountinfo();
					spsAcc.setSpId(cti.getOrgId());
					spsAcc.setPayTeller(curMap.get("付款账户"));
					spsAcc.setPayAccount(curMap.get("付款账号"));
					String datas = curMap.get("交易日期");
					String times = curMap.get("交易时间");
					String datass = datas.substring(0, 4) + "-" + datas.substring(4, 6) + "-" + datas.substring(6, 8);
					String timess = times.substring(0, 2) + ":" + times.substring(2, 4) + ":" + times.substring(4, 6);
					String datasss = datass + " " + timess;
					try {
						spsAcc.setDealDate(timeFormat.parse(datasss));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					spsAcc.setPayBank(curMap.get("付款银行"));
					spsAcc.setDealMoney(curMap.get("交易金额"));
					spsAcc.setMemo(curMap.get("备注"));
					spsAcc.setCreateBy(cti.getUserId());
					spsAcc.setCreateDt(date);
					spsAccountinfoDao.insert(cti,spsAcc);
				}
				result.setSuccess(true);
				result.setDataValue("successNum", dataList.size());
			} else {
				result.setError("Excel模板中无数据！");
				result.setSuccess(false);
			}
		}
		return result;
	}

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/04/19 15:34:17

	@Override
	public Result readExcelTitle(Integer fileId, ContextInfo cti) {
		Result result = Result.createResult().setSuccess(false);
		if (null == fileId) {
			result.setError("fileId不能为空！");
			return result;
		}
		String filePath = sysUploadfileService.getPhysicsFile(fileId);
		if (null == filePath || "".equals(filePath)) {
			result.setError("文件不存在！");
			return result;
		}
		XSSFWorkbook xwb = null;
		OPCPackage pkg;
		try {
			pkg = OPCPackage.open(filePath);
			xwb = new XSSFWorkbook(pkg);

			// 读取表格内容
			List<String> titleList = new ArrayList<String>();
			XSSFSheet sheet = xwb.getSheetAt(0);
			// 定义 row、cell
			XSSFRow row;
			// 循环输出表格中的内容
			row = sheet.getRow(0);
			for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {
				String cellContent = row.getCell(j).toString();
				if (StringUtils.isNotBlank(cellContent)) {
					titleList.add(cellContent);
				}
			}
			// 关闭
			pkg.close();
			result.setSuccess(true);
			result.setDataValue("titleList", titleList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
