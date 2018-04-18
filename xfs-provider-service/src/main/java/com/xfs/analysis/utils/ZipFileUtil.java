package com.xfs.analysis.utils;

import com.xfs.common.util.UUIDUtil;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipFileUtil {
	private static Logger logger = LoggerFactory.getLogger(ZipFileUtil.class);

	/**
	 * 把一个ZIP包解压到指定目录下
	 * 
	 * @param zipfilename
	 *            要解压的文件
	 * @param outputdir
	 *            指定解压位置
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public static List<String> unZipToFolder(String zipfilename,
			String outputdir) throws IOException {
		List<String> fileNameList = new ArrayList<String>();
		File fileDir = new File(outputdir);
		InputStream in = null;
		OutputStream out = null;
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		File zipFile = new File(zipfilename);
		if (zipFile.exists()) {
			ZipFile zip = new ZipFile(zipFile, ZipFile.OPEN_READ, Charset.forName("gbk"));
			for (Enumeration entries = zip.entries(); entries.hasMoreElements();) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				String zipEntryName = entry.getName();
				String ext = FilenameUtils.getExtension(zipEntryName);
				if (null==ext||"".equals(ext)) {
					continue;
				}
				String fileName = UUIDUtil.randomUUID()+"."+ext;
				in = zip.getInputStream(entry);
				String outPath = (outputdir+ fileName).replaceAll("\\*","/");
				
				// 判断路径是否存在,不存在则创建文件路径
				File file = new File(outPath.substring(0,outPath.lastIndexOf('/')));
				if (!file.exists()) {
					file.mkdirs();
				}
				// 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
				if (new File(outPath).isDirectory()) {
					continue;
				}
				if(zipEntryName.indexOf(".")!=0 && zipEntryName.indexOf("_")!=0){
					if (zipEntryName.indexOf("/")>-1) {
						zipEntryName = zipEntryName.substring(zipEntryName.indexOf("/")+1);
						zipEntryName = zipEntryName.substring(0,zipEntryName.indexOf("."));
					}
					fileNameList.add(zipEntryName+"#"+fileName);
				}
				out = new FileOutputStream(outPath);
				byte[] buf1 = new byte[1024];
				int len;
				while ((len = in.read(buf1)) > 0) {
					out.write(buf1, 0, len);
				}
				zipFile = null;
				in.close();
				out.flush();
				out.close();
			}
		} else {
			throw new IOException("指定的解压文件不存在：\t" + zipfilename);
		}
		return fileNameList;
	}

	public static void main2(String[] args) {
		String zipfilename = "D:\\tupian.zip";
		String outputdir = "D:\\img\\";
		try {
			ZipFileUtil.unZipToFolder(zipfilename, outputdir);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	

}
