package com.xfs.analysis.utils;

import com.xfs.common.util.DateUtil;
import com.xfs.common.util.UUIDUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * 文件处理工具类
 * @Project 	: 9t-framelib
 * @Program Name: com.framelib.utils.FileUtil.java
 * @ClassName	: FileUtil
 * @Author 		: zhangyan
 * @CreateDate  : 2014-9-25 下午2:36:04
 */
public class FileUtil {

	/**
	 *
	 *  @Method_Name    : saveFile
	 *  @param filePath
	 *  @param fileByte
	 *  @throws IOException
	 *	@return 		: void
	 *  @Creation Date  : 2014-9-25 下午2:37:00
	 *  @version        : v1.00
	 *  @Author         : zhangyan
	 *  @Update Date    :
	 *  @Update Author  :
	 */
	public static void saveFile(String filePath,byte[] fileByte) {
		File file = new File(filePath);
		File parent = file.getParentFile();
		FileOutputStream fos=null;
		if(parent!=null&&!parent.exists()){
			parent.mkdirs();
		}
		try{
			file.createNewFile();
			fos = new FileOutputStream(file);
			fos.write(fileByte);
			fos.flush();
			fos.close();
		}catch (Exception e) {
			throw new RuntimeException();
		}
		finally{
		    if (fos!=null) {
		        try {
		            fos.close();
		        } catch (IOException e) {
		            System.err.println("文件流关闭失败");
		        }
		    }
	    }
	}

	/**
	 * 保存固定编码文件
	 * @Create_by:yinsy
	 * @Create_date:2015年8月29日
	 * @param filePath
	 * @param resource
	 * @param charsetName
	 * @Last_Edit_By:
	 * @Edit_Description:
	 * @Create_Version:gblw-framelib 1.0
	 */
	public static void saveFile(String filePath,String resource,String charsetName){
		File file = new File(filePath);
		File parent = file.getParentFile();
		OutputStreamWriter writer = null;
		if(parent!=null&&!parent.exists()){
			parent.mkdirs();
		}
		if (charsetName==null||"".equals(charsetName)) {
			charsetName = "UTF-8";
		}
		try{
			writer = new OutputStreamWriter(new FileOutputStream(file),charsetName);
			writer.write(resource);
		}catch (Exception e) {
			throw new RuntimeException();
		}
		finally{
		    if (writer!=null) {
				try {
					writer.close();
				} catch (IOException e) {
					System.err.println("文件流关闭失败");
				}
			}
	    }

	}

	/**
	 * 保存文件，base64格式的文件流
	 *  @Method_Name    : saveFileByBase64
	 *  @param filePath
	 *	@return 		: void
	 *  @Creation Date  : 2014-9-25 下午3:47:52
	 *  @version        : v1.00
	 *  @Author         : zhangyan
	 *  @Update Date    :
	 *  @Update Author  :
	 */
	public static void saveFileByBase64(String filePath,String base64fileStr){
//		saveFile(filePath, Encodes.decodeBase64(base64fileStr));
	}

	/**
	 * 保存文件
	 *  @Method_Name    : saveFile
	 *  @param avatarFile
	 *  @param tempFile
	 *  @throws IOException
	 *	@return 		: void
	 *  @Creation Date  : 2014-9-28 下午12:04:33
	 *  @version        : v1.00
	 *  @Author         : zhangyan
	 *  @Update Date    :
	 *  @Update Author  :
	 */
	public static void saveFile(MultipartFile avatarFile, String tempFile) throws IOException {
//		String zipName = avatarFile.getOriginalFilename();
//		if(zipName.indexOf("zip")==-1){
//			throw new RuntimeException("上传的文件不合法！");
//		}
		File dest = new File(tempFile);
		File parent = dest.getParentFile();
		if(parent!=null&&!parent.exists()){
			 parent.mkdirs();
		}
		dest.createNewFile();
		avatarFile.transferTo(dest);
	}

	/**
	 * 上传并解压缩zip包
	 *  @Method_Name    : saveAndUnZip
	 *  @param rootPath	：服务器根路径
	 *  @param avatarZipFile ：压缩包文件
	 *  @param outputdir		：解压路径
	 *  @param deleteZipFile	：是否删除压缩包，true 删除
	 *	@return 		: List<String>  解压后的文件名集合
	 *  @Creation Date  : 2014-9-28 上午11:55:40
	 *  @version        : v1.00
	 *  @Author         : zhangyan
	 *  @Update Date    :
	 *  @Update Author  :
	 */
	public static List<String> saveAndUnZip(String rootPath,MultipartFile avatarZipFile,String outputdir,boolean deleteZipFile){
		//1接收上传的zip包 放到temp下
		String zip_rename = UUIDUtil.randomUUID()+".zip";
		String tempFile = rootPath+"tmp"+File.separator+ DateUtil.getYearMonthStr()+File.separator+zip_rename;

		String zipName = avatarZipFile.getOriginalFilename();

		if(!FilenameUtils.getExtension(zipName).equals("zip")){
			throw new RuntimeException();
		}
		try {
			FileUtil.saveFile(avatarZipFile, tempFile);
		} catch (IOException e) {
			throw new RuntimeException();
		}

		//2 解压zip文件把响应的头像放到客户头像所在的文件夹下;同时得到文件的名称也是就是客户表主键
		List<String> fileNameList = null;
		try {
			fileNameList = ZipFileUtil.unZipToFolder(tempFile, rootPath+"/"+outputdir);
		} catch (IOException e) {
			throw new RuntimeException();
		}
		//3删除上传的临时zip文件
		FileUtils.deleteQuietly(new File(tempFile));
		return fileNameList;
	}
	
	/**
	 * 创建文件夹
	 * @param filepaht
	 */
	public static void  createDirectory(String filepaht){
		if(filepaht!=null){
			String outPath = filepaht.replaceAll("\\\\","/");
			File file = new File(outPath.substring(0,outPath.lastIndexOf('/')));
			if (!file.exists()) {
				file.mkdirs();
			} 
		}
		
	}
	
	/**
	 * 创建目录
	 * @param path 目录
	 */
	public static void markDirs(String path){
		File file = new File(path);
		if(file!=null && !file.exists()){
			file.mkdirs();
		}
	}
}
