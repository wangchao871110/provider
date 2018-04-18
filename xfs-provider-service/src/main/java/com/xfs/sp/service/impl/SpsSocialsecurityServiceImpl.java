package com.xfs.sp.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.base.model.SysUploadfile;
import com.xfs.base.service.SysUploadfileService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.crawler.BJSocialReportCrawler;
import com.xfs.common.page.PageModel;
import com.xfs.common.redies.RedisUtil;
import com.xfs.common.util.Config;
import com.xfs.sp.dao.SpsSocialsecurityDao;
import com.xfs.sp.model.SpsSocialsecurity;
import com.xfs.sp.service.SpCmRelationService;
import com.xfs.sp.service.SpsSocialsecurityService;
import com.xfs.ts.model.SpsTsPersonFlow;
import com.xfs.ts.service.SpsTsPersonFlowService;


@Service
public class SpsSocialsecurityServiceImpl implements SpsSocialsecurityService {

	private static final Logger log = Logger.getLogger(SpsSocialsecurityServiceImpl.class);
	
	@Autowired
	private SpsSocialsecurityDao spsSocialsecurityDao;

	@Autowired
	private SysUploadfileService sysUploadfileService;
	
	@Autowired
	private SpsTsPersonFlowService spsTsPersonFlowService;

	@Autowired
	SpCmRelationService spCmRelationService;

	public int save(ContextInfo cti, SpsSocialsecurity vo ){
		return spsSocialsecurityDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsSocialsecurity vo ){
		return spsSocialsecurityDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsSocialsecurity vo ){
		return spsSocialsecurityDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsSocialsecurity vo ){
		return spsSocialsecurityDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsSocialsecurity vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsSocialsecurityDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsSocialsecurity> datas = spsSocialsecurityDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsSocialsecurity> findAll(SpsSocialsecurity vo){
		
		List<SpsSocialsecurity> datas = spsSocialsecurityDao.freeFind(vo);
		return datas;
		
	}
	
	

	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/04/20 21:09:23


	/**
	 * 每月执行一次查询社保明细
	 *
	 * @return
	 */
	public List<Map<String, Object>> onceFindForSS(String cityCode, String insuranceFundType) {
		return spsSocialsecurityDao.onceFindForSS(cityCode, insuranceFundType);
	}


	/**
	 * 根据条件查询社保报表
	 *
	 * @param map
	 * @return
	 */
	@Override
	public PageModel findPage(PageInfo pi, Map<String, Object> map) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsSocialsecurityDao.countFreeFind(map);
		pm.setTotal(total);
		List<Map<String, Object>> datas = spsSocialsecurityDao.freeFind(map, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}

	/**
	 * 查询待处理或者异常任务
	 *
	 * @return
	 */
	@Override
	public List<Map<String, Object>> freeFindUnSuccessReg() {
		return spsSocialsecurityDao.freeFindUnSuccessReg();
	}

	String lock = "query_social_report_synchronized_lock";

	@Override
	public  boolean queryMadeSocialReport() {
		String fileRootPath = Config.getProperty("fileRootPath");//文件根目录
		String curDate = getDateStrByMonth();
		String lastDate = getLastMonth();
		String filePath = fileRootPath + File.separator + curDate+File.separator;
		String saveName = "报表.zip";
		File localFile = new File(filePath);
		if (!localFile.exists()) {
			localFile.mkdirs();
		}
		while(RedisUtil.getLock(lock, 60000L) != 1){//获取当前锁
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
		 }
		log.info("我正在执行查询社保报表");
		String areaCode = "10";
		List<Map<String, Object>> task_list = onceFindForSS(areaCode, "INSURANCE");//code=10，为北京所有企业
		if(null != task_list && !task_list.isEmpty()) {
			for (Map<String, Object> task : task_list) {
				BJSocialReportCrawler report = new BJSocialReportCrawler();
				Map<String,String> params = new HashMap<String,String>();
				params.put("j_username",String.valueOf(task.get("reg_num")));
				params.put("j_password",String.valueOf(task.get("reg_numpass")));
				params.put("type","0");
				params.put("flag","2");
				params.put("x","80");
				params.put("y","24");
				if("110107019934".equals(String.valueOf(task.get("reg_num")))){
					System.out.println("sss");
				}else{
					continue;
				}
				if(report.login(params,"safecode")){
					String ssyb_zip_name = generateFileName(saveName,String.valueOf(task.get("cp_id")),String.valueOf(task.get("cp_name")));//汇总月报zip名称
					boolean is_ssyb_down_ok = report.downLoadQueryReport(lastDate,String.valueOf(task.get("reg_num")),"1",filePath,ssyb_zip_name);
					boolean is_made_ok = report.madeQueryReport(lastDate);
					//保存到数据库
					if(is_ssyb_down_ok){
						SysUploadfile ssybUploadfile = new SysUploadfile();
						ssybUploadfile.setFilename(ssyb_zip_name);
						ssybUploadfile.setSavename(ssyb_zip_name);
						ssybUploadfile.setFilepath(curDate);
						sysUploadfileService.save(null,ssybUploadfile);
						int fileId = ssybUploadfile.getId();
						/**
						 *  增加报表
						 */
						SpsSocialsecurity socialsecurity = new SpsSocialsecurity();
						socialsecurity.setStatus(3);
						socialsecurity.setBusinessType(1);
						socialsecurity.setRegNum(String.valueOf(task.get("reg_num")));
						socialsecurity.setQueryDate(lastDate);
						socialsecurity.setFiles(String.valueOf(fileId));
						socialsecurity.setSpId(Integer.parseInt(String.valueOf(task.get("sp_id"))));
						socialsecurity.setCpId(Integer.parseInt(String.valueOf(task.get("cp_id"))));
						socialsecurity.setMadeTime(new Date());
						socialsecurity.setCreateDt(new Date());
						insert(null, socialsecurity);
					}

					//保存到数据库
					if(is_made_ok){
						SpsSocialsecurity socialsecurity = new SpsSocialsecurity();
						socialsecurity.setStatus(1);
						socialsecurity.setBusinessType(0);
						socialsecurity.setRegNum(String.valueOf(task.get("reg_num")));
						socialsecurity.setSpId(Integer.parseInt(String.valueOf(task.get("sp_id"))));
						socialsecurity.setCpId(Integer.parseInt(String.valueOf(task.get("cp_id"))));
						socialsecurity.setQueryDate(lastDate);
						socialsecurity.setMadeTime(new Date());
						socialsecurity.setCreateDt(new Date());
						insert(null, socialsecurity);
					}
				}
			}
		}

		log.info("正在执行查询定制社保报表");
		task_list = freeFindUnSuccessReg();
		if(null != task_list && !task_list.isEmpty()){
			for(Map<String,Object> task : task_list){
				BJSocialReportCrawler report = new BJSocialReportCrawler();
				Map<String,String> params = new HashMap<String,String>();
				params.put("j_username",String.valueOf(task.get("reg_num")));
				params.put("j_password",String.valueOf(task.get("reg_numpass")));
				params.put("type","0");
				params.put("flag","2");
				params.put("x","80");
				params.put("y","24");
				if("110107019934".equals(String.valueOf(task.get("reg_num")))){
					System.out.println("sss");
				}else{
					continue;
				}
				if(report.login(params,"safecode")){
					String dzyb_zip_name = generateFileName(saveName,String.valueOf(task.get("cp_id")),String.valueOf(task.get("cp_name")));//定制查询明细zip名称
					boolean is_dzyb_down_ok = report.downLoadMadeQueryReport(lastDate,String.valueOf(task.get("reg_num")),"0",filePath,dzyb_zip_name);
					if(is_dzyb_down_ok){
						SysUploadfile dzybUploadfile = new SysUploadfile();
						dzybUploadfile.setFilename(dzyb_zip_name);
						dzybUploadfile.setSavename(dzyb_zip_name);
						dzybUploadfile.setFilepath(curDate);
						sysUploadfileService.save(null,dzybUploadfile);
						int fileid = dzybUploadfile.getId();
						
						/**
						 * 解析并储存数据库
						 * @author quanjh
						 */
						try {
							String spid = String.valueOf(task.get("sp_id"));
							String accid = String.valueOf(task.get("acc_id"));
							savePersonFlow(null, report, fileid, String.valueOf(task.get("cp_name")), spid, accid, areaCode);
						} catch (Exception e) {
							log.error(e.getMessage(),e);
						}
						
						/**
						 *  修改任务状态--下载地址
						 */
						SpsSocialsecurity socialsecurity = new SpsSocialsecurity();
						socialsecurity.setSsId(Integer.parseInt(String.valueOf(task.get("ss_id"))));
						socialsecurity.setFiles(String.valueOf(fileid));
						socialsecurity.setStatus(3);//完成
						socialsecurity.setModifyDt(new Date());
						update(null, socialsecurity);
						
					}

				}
			}
		}
        RedisUtil.delLock(lock); //释放锁
		return true;
	}

	/**
	 * 得到年月yyyyMM格式的字符串，供生成生成上传目录使用
	 *
	 * @return
	 */
	public static String getDateStrByMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		return format.format(Calendar.getInstance().getTime());
	}

	public static String getLastMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH,-1);
		return format.format(calendar.getTime());
	}
	/**
	 * 获得一个以时间格式的新名称
	 *
	 * @param fileName 原图名称
	 * @return
	 */
	public static String generateFileName(String fileName,String cp_id,String cp_name)
	{
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String formatDate = format.format(new Date());
		int random = new Random().nextInt(10000);
		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);
		return cp_id + "_" + cp_name + "_" + formatDate + random + extension;
	}

	/**
	 * 如果上传文件名重复，处理文件名
	 *
	 * @param path
	 * @param fileName
	 * @return
	 */
	public static File getUniqueFile(String path, String fileName) {
		String pname = fileName.substring(0, fileName.lastIndexOf('.'));
		String suffix = fileName.substring(fileName.lastIndexOf('.'));
		int i = 1;
		while (true) {
			String tempName = pname + i + suffix;
			File tf = new File(path, tempName);
			if (!tf.exists())
				return tf;
			i++;
		}
	}
	
	/**
	 * 解析excel并保存下行数据
	 * @param fileid 文件id
	 * @author quanjh
	 * @throws Exception
	 */
	private void savePersonFlow(ContextInfo cti, BJSocialReportCrawler report, int fileid, String cname, String spId, String accId, String areaCode) throws Exception {
		
		String fileRootPath = Config.getProperty("fileRootPath");// 文件根目录
		SysUploadfile vo = new SysUploadfile();
		vo.setId(Integer.valueOf(fileid));
		SysUploadfile uploadFile = sysUploadfileService.findByPK(vo);
		if (null == uploadFile) {
			return;
		}
		String filePath = fileRootPath + File.separator + uploadFile.getFilepath() + File.separator + uploadFile.getSavename();
		String outFile = fileRootPath + File.separator + uploadFile.getFilepath();
		
		List<String> excelList = copyZipFile(filePath, outFile);
		
		List<List<Map<String,String>>> sheetList = new ArrayList<List<Map<String,String>>>();
		for (int i = 0; i < excelList.size(); i++) {
			
			String path = outFile + "\\" + excelList.get(i);
			sheetList.add(readXls(path, excelList.get(i)));
			
		}
		
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        int num = sheetList.size();
        if (num==2) {//根据“身份证”合并两个excel
			
        	List<Map<String,String>> sheet = sheetList.get(0);
        	List<Map<String,String>> sheet2 = sheetList.get(1);
        	int row = sheet.size();
        	int row2 = sheet2.size();
        	for (int i = 0; i < row; i++) {
        		
        		for (int j = 0; j < row2; j++) {
        		
        			if (sheet.get(i).get("公民身份号码").equals(sheet2.get(j).get("公民身份号码"))) {
        				sheet.get(i).putAll(sheet2.get(j));
        				break;
					}
					
				}
        		
			}
        	
        	list = sheet;
        	
		}else if(num==1){
			list = sheetList.get(0);
		}else if(num > 2){
			list = sheetList.get(0);
		}
        
        int pnum = list.size();
        
        for (int i = 0; i < pnum; i++) {
        	
        	String name = list.get(i).get("姓名");
        	String idCard = list.get(i).get("公民身份号码");
        	try {
				String result = report.personInfo(name, idCard);//获取个人基本信息
				personInfo(cti, name, idCard, result, cname, spId, accId, areaCode);
			} catch (Exception e1) {
				e1.printStackTrace();
				log.error(e1.getMessage(),e1);
			}
        	
        	BigDecimal total = BigDecimal.ZERO;
        	try {
				BigDecimal ylc = com.xfs.common.util.StringUtils.toBigDecimal(list.get(i).get("养老单位缴费"));
				BigDecimal ylp = com.xfs.common.util.StringUtils.toBigDecimal(list.get(i).get("养老个人缴费"));
				BigDecimal syc = com.xfs.common.util.StringUtils.toBigDecimal(list.get(i).get("失业单位缴费"));
				BigDecimal syp = com.xfs.common.util.StringUtils.toBigDecimal(list.get(i).get("失业个人缴费"));
				BigDecimal jbylc = com.xfs.common.util.StringUtils.toBigDecimal(list.get(i).get("基本医疗保险单位应缴金额"));
				BigDecimal jbylp = com.xfs.common.util.StringUtils.toBigDecimal(list.get(i).get("基本医疗保险个人应缴金额"));
				BigDecimal gs = com.xfs.common.util.StringUtils.toBigDecimal(list.get(i).get("工伤单位缴费"));
				BigDecimal sy = com.xfs.common.util.StringUtils.toBigDecimal(list.get(i).get("生育单位缴费"));
				total = ylc.add(ylp).add(syc).add(syp).add(jbylc).add(jbylp).add(gs).add(sy);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage(),e);
			}
        	
        	String json = JSON.toJSONString(list.get(i));//GsonUtil.Serialize();
        	
        	SpsTsPersonFlow spsTsPersonFlow = new SpsTsPersonFlow();
        	spsTsPersonFlow.setCname(cname);
        	spsTsPersonFlow.setCreateDt(new Date());
        	spsTsPersonFlow.setIdCard(idCard);
        	spsTsPersonFlow.setMonth(list.get(i).get("月份"));
        	spsTsPersonFlow.setName(name);
        	spsTsPersonFlow.setType("1");
        	spsTsPersonFlow.setTotal(total);
        	spsTsPersonFlow.setJson(json);
        	spsTsPersonFlow.setSpId(Integer.parseInt(spId));
        	spsTsPersonFlow.setAccId(Integer.parseInt(accId));
        	spsTsPersonFlow.setAreaCode(areaCode);
        	spsTsPersonFlow.setServiceType("2");
        	spsTsPersonFlowService.save(cti,spsTsPersonFlow);
        	
        	list.get(i).remove("公民身份号码");
        	list.get(i).remove("姓名");
        	list.get(i).remove("月份");
        	
        	
		}
	}
	
	/**
	 * 解析excel
	 * @param path 文件路径
	 * @param filename 文件名称
	 * @return 响应解析数据
	 * @author quanjh
	 * @throws Exception
	 */
	private List<Map<String,String>> readXls(String path, String filename) throws Exception {
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		
		int count = hssfSheet!=null ? hssfSheet.getLastRowNum():0;
		if (filename != null && filename.indexOf("缴费月报人员明细过录（四险）") > -1) {
			
			for (int rowNum = 3; rowNum < count; rowNum++) {
			
	            HSSFRow hssfRow = hssfSheet.getRow(rowNum);
	            if (hssfRow != null) {
	            	Map<String,String> map = new HashMap<String,String>();
	            	
	                String idCard = hssfRow.getCell(1).toString();//公民身份号码
	                String computer = hssfRow.getCell(2).toString();//电脑序号
	                String name = hssfRow.getCell(3).toString();//姓名
	                String pensionBase = hssfRow.getCell(4).toString();//养老基数
	                String losejobBase = hssfRow.getCell(5).toString();//失业基数
	                String injuryjobBase = hssfRow.getCell(6).toString();//工伤基数
	                String birthBase = hssfRow.getCell(7).toString();//生育基数
	                
	                String pensionCompany = hssfRow.getCell(8).toString();//养老单位缴费
	                String pensionPerson = hssfRow.getCell(9).toString();//养老个人缴费
	                String pensionOverall = hssfRow.getCell(10).toString();//养老统筹基金
	                String pensionCompanyTransfer = hssfRow.getCell(11).toString();//养老单位划转
	                String losejobCompany = hssfRow.getCell(12).toString();//失业单位缴费
	                String losejobPerson = hssfRow.getCell(13).toString();//失业个人缴费
	                String injuryjob = hssfRow.getCell(14).toString();//工伤单位缴费 
	                String birth = hssfRow.getCell(15).toString();//生育单位缴费 
	                
	                map.put("公民身份号码", idCard);
	                map.put("电脑序号", computer);
	                map.put("姓名", name);
	                map.put("养老基数", pensionBase);
	                map.put("失业基数", losejobBase);
	                map.put("工伤基数", injuryjobBase);
	                map.put("生育基数", birthBase);
	                map.put("养老单位缴费", pensionCompany);
	                map.put("养老个人缴费", pensionPerson);
	                map.put("养老统筹基金", pensionOverall);
	                map.put("养老单位划转", pensionCompanyTransfer);
	                map.put("失业单位缴费", losejobCompany);
	                map.put("失业个人缴费", losejobPerson);
	                map.put("工伤单位缴费", injuryjob);
	                map.put("生育单位缴费", birth);
	                
	                list.add(map);
	            }
	            
			}
			
		}else if (filename != null && filename.indexOf("基本医疗保险缴费明细查询") > -1) {	
			for (int rowNum = 4; rowNum < count - 1; rowNum++) {
			
	            HSSFRow hssfRow = hssfSheet.getRow(rowNum);
	            if (hssfRow != null) {
	            	Map<String,String> map = new HashMap<String,String>();
	            	
	            	String name = hssfRow.getCell(2).toString();//姓名
	                String idCard = hssfRow.getCell(3).toString();//公民身份号码
	                String month = hssfRow.getCell(4).toString();//业务年度月份
	                String top5 = hssfRow.getCell(5).toString();//基本医疗保险单位应缴金额
	                String top6 = hssfRow.getCell(6).toString();//基本医疗保险个人应缴金额
	                String top7 = hssfRow.getCell(7).toString();//大额互助资金单位应缴金额
	                String top8 = hssfRow.getCell(8).toString();//大额互助资金个人应缴金额
	                String top9 = hssfRow.getCell(9).toString();//公费医疗补助金额
	                
	                map.put("姓名", name);
	                map.put("公民身份号码", idCard);
	                map.put("月份", month);
	                map.put("基本医疗保险单位应缴金额", top5);
	                map.put("基本医疗保险个人应缴金额", top6);
	                map.put("大额互助资金单位应缴金额", top7);
	                map.put("大额互助资金个人应缴金额", top8);
	                map.put("公费医疗补助金额", top9);
	                
	                list.add(map);
	            }
	            
			}
			
		}
		
		return list;
		
	}
	
	
	/**
	 * 解压缩文件
	 * @param zip 压缩包全路径
	 * @param outFile 输出路径
	 * @return 文件集合
	 * @author quanjh
	 * @throws Exception
	 */
	private static List<String> copyZipFile(String zip, String outFile) throws Exception {
		
		List<String> list = new ArrayList<String>();
		
		long startTime = System.currentTimeMillis();
		ZipInputStream Zin = new ZipInputStream(new FileInputStream(zip),Charset.forName("GBK"));// 输入源zip路径
		BufferedInputStream Bin = new BufferedInputStream(Zin);
		String Parent = outFile; // 输出路径（文件夹目录）
		File Fout = null;
		ZipEntry entry;
		while ((entry = Zin.getNextEntry()) != null && !entry.isDirectory()) {
			Fout = new File(Parent, entry.getName());
			list.add(entry.getName());
			if (!Fout.exists()) {
				(new File(Fout.getParent())).mkdirs();
			}
			FileOutputStream out = new FileOutputStream(Fout);
			BufferedOutputStream Bout = new BufferedOutputStream(out);
			int b;
			while ((b = Bin.read()) != -1) {
				Bout.write(b);
			}
			Bout.close();
			out.close();
		}
		Bin.close();
		Zin.close();
		long endTime = System.currentTimeMillis();

		if(log.isDebugEnabled()){
			log.debug("解压缩完成，共耗费时间： " + (endTime - startTime) + " ms");
		}
		return list;
	}
	
	public void personInfo(ContextInfo cti, String name, String idCard, String result, String cname, String spId, String accId, String areaCode){
        try{
            Document doc = Jsoup.parse(result);
            
            Element tab1 = doc.getElementsByTag("table").first().getElementsByTag("tr").first().getElementsByTag("td").first();
            String tabStr = tab1.text();
            String dwmc = tabStr.substring(tabStr.indexOf("单位名称：") + 5, tabStr.indexOf("组织机构代码")).trim().replace(" ", "");//单位名称
            String zzjgdm = tabStr.substring(tabStr.indexOf("组织机构代码：") + 7, tabStr.indexOf("社会保险登记证编号")).trim().replace(" ", "");//组织机构代码
            String djzbh = tabStr.substring(tabStr.indexOf("社会保险登记证编号：") + 10, tabStr.indexOf("所属区县")).trim().replace(" ", "").replace(" ", "");//社会保险登记证编号
            String ssqx = tabStr.substring(tabStr.indexOf("所属区县：") + 5, tabStr.length()).trim().replace(" ", "");//所属区县
            
            Element tab2 = doc.getElementsByTag("table").get(1);
            
            String cbrdh = tab2.getElementsByTag("tr").get(10).getElementsByTag("td").get(1).text();//参保人电话
            String cbrsj = tab2.getElementsByTag("tr").get(10).getElementsByTag("td").get(3).text();//参保人手机
            String pay = tab2.getElementsByTag("tr").get(10).getElementsByTag("td").get(5).text().trim().replace(" ", "");//申报月均工资收入（元）
            String jfrylb = tab2.getElementsByTag("tr").get(13).getElementsByTag("td").get(1).text();//缴费人员类别
            String elcbrylb = tab2.getElementsByTag("tr").get(13).getElementsByTag("td").get(3).text();//医疗参保人员类别
            
            String photoUrl = "";
			String sex = "";
			String birthday = "";
			String mz = "";
			String gj = "";
			String grsf = "";
			String cjgzrq = "";
			String hkszqxjx = "";
			String hkxz = "";
			String hkszddz = "";
			String hkszdyzbm = "";
			String jzddz = "";
			String jzdyzbm = "";
			String dzdfs = "";
			String whcd = "";
			String wtdfyhmc = "";
			String wtdfyhzh = "";
			String ltxlb = "";
			String ltxrq = "";
			String yy1 = "";
			String yy2 = "";
			String yy3 = "";
			String yy4 = "";
			String yy5 = "";
			String tsbh = "";
			
			try {
				photoUrl = tab2.getElementsByTag("tr").first().getElementsByTag("td").last().getElementsByTag("img").first().attr("src");
				photoUrl = "http://www.bjrbj.gov.cn" + photoUrl;//社保照片地址
				
				sex = tab2.getElementsByTag("tr").get(2).getElementsByTag("td").get(1).text();
				birthday = tab2.getElementsByTag("tr").get(2).getElementsByTag("td").get(3).text().trim().replace(" ", "");
				
				mz = tab2.getElementsByTag("tr").get(3).getElementsByTag("td").get(1).text();
				gj = tab2.getElementsByTag("tr").get(3).getElementsByTag("td").get(3).text().trim().replace(" ", "");
				
				grsf = tab2.getElementsByTag("tr").get(4).getElementsByTag("td").get(1).text();
				cjgzrq = tab2.getElementsByTag("tr").get(4).getElementsByTag("td").get(3).text().trim().replace(" ", "");
				
				hkszqxjx = tab2.getElementsByTag("tr").get(5).getElementsByTag("td").get(1).text();
				hkxz = tab2.getElementsByTag("tr").get(5).getElementsByTag("td").get(3).text().trim().replace(" ", "");
				
				hkszddz = tab2.getElementsByTag("tr").get(6).getElementsByTag("td").get(1).text();
				hkszdyzbm = tab2.getElementsByTag("tr").get(6).getElementsByTag("td").get(3).text().trim().replace(" ", "");
				
				jzddz = tab2.getElementsByTag("tr").get(7).getElementsByTag("td").get(1).text();
				jzdyzbm = tab2.getElementsByTag("tr").get(7).getElementsByTag("td").get(3).text().trim().replace(" ", "");
				
				dzdfs = tab2.getElementsByTag("tr").get(9).getElementsByTag("td").get(1).text();
				whcd = tab2.getElementsByTag("tr").get(9).getElementsByTag("td").get(5).text().trim().replace(" ", "");
				
				wtdfyhmc = tab2.getElementsByTag("tr").get(12).getElementsByTag("td").get(1).text();
				wtdfyhzh = tab2.getElementsByTag("tr").get(12).getElementsByTag("td").get(3).text();
				
				ltxlb = tab2.getElementsByTag("tr").get(14).getElementsByTag("td").get(1).text();
				ltxrq = tab2.getElementsByTag("tr").get(14).getElementsByTag("td").get(3).text();
				
				yy1 = tab2.getElementsByTag("tr").get(15).getElementsByTag("td").get(1).text();
				yy2 = tab2.getElementsByTag("tr").get(15).getElementsByTag("td").get(3).text();
				yy3 = tab2.getElementsByTag("tr").get(16).getElementsByTag("td").get(1).text();
				yy4 = tab2.getElementsByTag("tr").get(16).getElementsByTag("td").get(3).text();
				yy5 = tab2.getElementsByTag("tr").get(17).getElementsByTag("td").get(1).text();
				tsbh = tab2.getElementsByTag("tr").get(17).getElementsByTag("td").get(3).text();
				
			} catch (Exception e) {//若有非关键字段异常则不影响数据导入
				e.printStackTrace();
				log.error("字段异常："+e.getMessage(), e);
			}
            
            Map<String, String> map = new HashMap<String, String>();
            map.put("缴交单位名称", dwmc);
            map.put("组织机构代码", zzjgdm);
            map.put("社会保险登记证编号", djzbh);
            map.put("所属区县", ssqx);
            map.put("社保照片地址", photoUrl);
            map.put("性别", sex);
            map.put("出生日期", birthday);
            map.put("名族", mz);
            map.put("国家/地区", gj);
            map.put("个人身份", grsf);
            map.put("参加工作日期", cjgzrq);
            map.put("户口所在区县街乡", hkszqxjx);
            map.put("户口性质", hkxz);
            map.put("户口所在地地址", hkszddz);
            map.put("户口所在地邮政编码", hkszdyzbm);
            map.put("居住地(联系)地址", jzddz);
            map.put("居住地（联系）邮政编码", jzdyzbm);
            map.put("获取对账单方式", dzdfs);
            map.put("文化程度", whcd);
            map.put("参保人电话", cbrdh);
            map.put("参保人手机", cbrsj);
            map.put("申报月均工资收入（元）", pay);
            map.put("委托代发银行名称", wtdfyhmc);
            map.put("委托代发银行账号", wtdfyhzh);
            map.put("缴费人员类别", jfrylb);
            map.put("医疗参保人员类别", elcbrylb);
            map.put("离退休类别", ltxlb);
            map.put("离退休日期", ltxrq);
            map.put("定点医疗机构1", yy1);
            map.put("定点医疗机构2", yy2);
            map.put("定点医疗机构3", yy3);
            map.put("定点医疗机构4", yy4);
            map.put("定点医疗机构5", yy5);
            map.put("特殊病患", tsbh);
            
            String json = JSON.toJSONString(map);//GsonUtil.Serialize(map);
            System.out.println(json);
            
            SpsTsPersonFlow spsTsPersonFlow = new SpsTsPersonFlow();
        	spsTsPersonFlow.setCname(cname);
        	spsTsPersonFlow.setCreateDt(new Date());
        	spsTsPersonFlow.setIdCard(idCard);
        	spsTsPersonFlow.setMonth(bartDateFormat(new Date())+"");
        	spsTsPersonFlow.setName(name);
        	spsTsPersonFlow.setType("1");
        	spsTsPersonFlow.setJson(JSON.toJSONString(map));
        	spsTsPersonFlow.setSpId(Integer.parseInt(spId));
        	spsTsPersonFlow.setAccId(Integer.parseInt(accId));
        	spsTsPersonFlow.setAreaCode(areaCode);
        	spsTsPersonFlow.setServiceType("1");
        	spsTsPersonFlowService.save(cti,spsTsPersonFlow);
            
            
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage(),e);
        }
    }
	
	private static Integer bartDateFormat(Date date){
		SimpleDateFormat bartDateFormat =  new SimpleDateFormat("yyyyMM");
		return Integer.parseInt(bartDateFormat.format(date).toString());
	}
	
	
//	public static void main(String[] args) {
//        BJSocialReportCrawler report = new BJSocialReportCrawler();
//        Map<String,String> params = new HashMap<String,String>();
//        params.put("j_username","110108092946");//110108091375
//        params.put("j_password","88888888");//93917802
//        params.put("type","0");
//        params.put("flag","2");
//        params.put("x","80");
//        params.put("y","24");
//        
//        if(report.login(params,"safecode")){
//        	
//        	//report.personInfo("李东亮", "142202198911080973");//report.personInfo("侯春燕", "130531198909292947");
//        	String result = report.personInfo("李丽", "142332199207025629");
//        	personInfo("", "", result, "", "", "", "");
//        	
//        }
//
//    }
	
}
