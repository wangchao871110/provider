package com.xfs.fastlist.service;

import java.awt.print.Book;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.xfs.base.service.SysUploadfileService;
import com.xfs.common.aliyun.AliyunImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.email.util.MailUtil;
import com.xfs.common.email.vo.Email;
import com.xfs.common.mongo.MongoDao;
import com.xfs.common.mongo.vo.SearchField;
import com.xfs.common.util.ZipUtil;
import com.xfs.sps.utils.ExportExcelUtil;

@Service
public class FastListServiceImpl implements FastListService {
	// mongoDao
	@Autowired
	MongoDao mongoDao;

	@Autowired
	SysUploadfileService sysUploadfileService;

	/**
	 * 打包excel并 发送邮件
	 *
	 * @author lifq
	 *
	 *         2016年3月17日 下午2:58:07
	 */
	public void sendMail(String template_id, String email, String fileRootPath,String creator) throws Exception {

		File dir = new File(fileRootPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		SearchField field = new SearchField("template_id", template_id);

		List<SearchField> searchFields = new ArrayList<SearchField>();
		searchFields.add(field);
		// 根据template_id 查询 信息
		List<Map<String, Object>> mapResult = mongoDao.query("Form_Data", searchFields);

		// 导出
		ExportExcelUtil<Book> obj = new ExportExcelUtil<Book>();

		// BufferedInputStream bis1 = new BufferedInputStream(
		// new
		// FileInputStream("C:\\Users\\lifq\\Desktop\\QQ截图20160301092847.jpg"));
		// byte[] buf1 = new byte[bis1.available()];
		// while ((bis1.read(buf1)) != -1) {
		// }
		//
		// BufferedInputStream bis2 = new BufferedInputStream(
		// new FileInputStream("C:\\Users\\lifq\\Desktop\\java编程思想.jpg"));
		// byte[] buf2 = new byte[bis2.available()];
		// while ((bis2.read(buf2)) != -1) {
		// }

		// dataset.add(new Book(1, "jsp", "leno", 300.33f, "1234567",
		// "清华出版社", buf1));
		// dataset.add(new Book(2, "java编程思想", "brucl", 300.33f, "1234567",
		// "阳光出版社", buf2));
		// dataset.add(new Book(3, "DOM艺术", "lenotang", 300.33f, "1234567",
		// "清华出版社", buf1));
		// dataset.add(new Book(4, "c++经典", "leno", 400.33f, "1234567",
		// "清华出版社", buf1));
		// dataset.add(new Book(5, "c#入门", "leno", 300.33f, "1234567",
		// "汤春秀出版社", buf1));
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String ex_date=dateFormat.format(new Date());
		
		String fileUUID =creator+"_"+ex_date;// UUIDUtil.randomUUID();
		OutputStream out = new FileOutputStream(fileRootPath + File.separator + fileUUID + ".xls");
		obj.exportExcel("sheet1", out, mapResult, sysUploadfileService);
		out.close();

		ZipUtil.zip(fileRootPath + File.separator + fileUUID + ".xls",
				fileRootPath + File.separator + fileUUID + ".zip", null);

		File zipFile = new File(fileRootPath + File.separator + fileUUID + ".zip");
		// 删除excel
		File excelFile = new File(fileRootPath + File.separator + fileUUID + ".xls");
		if (null != excelFile && excelFile.exists()) {
			excelFile.delete();
		}
		// 发送邮件
		Email emailObj = new Email();
		List<String> toAddress = new ArrayList<String>();
		toAddress.add(email);
		emailObj.setToAddress(toAddress);
		emailObj.setSubject("FastList统计表");
		emailObj.setContent("        请查收excel统计表。");
		List<File> attachments = new ArrayList<File>();
		attachments.add(zipFile);
		emailObj.setAttachments(attachments);
		MailUtil.sendMail(emailObj);
		// 删除 zip
		if (null != zipFile && zipFile.exists()) {
			zipFile.delete();
		}

		
		
		
		
	}

	/**
	 * 上传图片
	 */
	public String uploadFile(File file) {
		try {
			return AliyunImageUtil.uploadPicture(file, "images/fl");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public boolean publishForm(String jsonobj) {
		boolean isSuccess = mongoDao.insert("Form_Template", jsonobj);
		return isSuccess;
	}

	@Override
	public boolean submitForm(String htmljson) {
		boolean isSuccess = mongoDao.insert("Form_Data", htmljson);
		return isSuccess;

	}

	@Override
	public List<Map<String, Object>> queryTplView(String _id) {
		SearchField templateid = new SearchField("_id", _id);
		List<SearchField> searchFields = new ArrayList<SearchField>();
		searchFields.add(templateid);
		// 根据creator_id 查询 信息
		List<Map<String, Object>> mapResult = mongoDao.query("Form_Template", searchFields);
		return mapResult;
	}

	@Override
	public List<Map<String, Object>> queryDataView(String _id) {
		// // TODO 带分页查询的查询方法
		// SearchField creatorid = new SearchField("creator_id", creator_id);
		// SearchField templateid = new SearchField("template_id", template_id);
		// List<SearchField> searchFields = new ArrayList<SearchField>();
		// // 查询页码
		// int page = 1;
		// // 查询
		// int pageSize = 10;
		// Search search = new Search();
		// return null;
		SearchField templateid = new SearchField("template_id", _id);
		List<SearchField> searchFields = new ArrayList<SearchField>();
		searchFields.add(templateid);
		// 根据creator_id 查询 信息
		List<Map<String, Object>> mapResult = mongoDao.query("Form_Data", searchFields);
		return mapResult;
	}
}
