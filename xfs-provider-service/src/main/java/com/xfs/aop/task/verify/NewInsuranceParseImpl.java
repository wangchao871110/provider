package com.xfs.aop.task.verify;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.Config;
import org.apache.commons.lang.StringUtils;

import com.xfs.base.model.SysUploadfile;
import com.xfs.base.service.SysUploadfileService;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.DateUtil;
import com.xfs.common.util.SpringContextUtil;

/**
 * 新参保
 * @author lifq
 *
 * 2016年4月27日  下午4:33:52
 */
public class NewInsuranceParseImpl extends BaseTaskDataParse {
	
	public static SysUploadfileService sysUploadfileService;
	private synchronized static void init() {
		sysUploadfileService = (SysUploadfileService) SpringContextUtil
				.getBean(SysUploadfileService.class);
	}
	static {
		init();
	}
	
	


	/**
	 * 导入前 校验
	 *
	 * @author lifq
	 *
	 * 2016年4月20日  下午8:49:08
	 */
	@Override
	public String checkBeforeImport(ContextInfo cti, Map<String,String> curMap, Map<String,Object> paraMap) {
		String err = super.checkBeforeImport(cti,curMap, paraMap);//必录项 校验
		for (Map.Entry<String, String> entry : curMap.entrySet()) {
			if ("workdate".equals(entry.getKey())) {
				if (!StringUtils.isBlank(entry.getValue())) {
					if(!DateUtil.isValidDate(curMap.get("workdate"))){
						err += "参加工作日期格式错误，请录入正确的格式！例如:2015-05-10！";
					}
				}
			}
		}
		
		Integer areaId = (Integer)paraMap.get("areaId");
		if(areaId == 2){ //北京时候 校验
			
			//校验 照片是否存在
			boolean isExistsPhoto = false;
			if(StringUtils.isNotBlank(curMap.get("identity_code")) && StringUtils.isNotBlank(curMap.get("name"))){
				String cardNO = curMap.get("identity_code");
				if(cardNO.length()>4){
					String photoName = curMap.get("name") + cardNO.substring(cardNO.length()-4) +".jpg";
					if(null!=paraMap.get("fileLists")){
						for(File file:(List<File>)paraMap.get("fileLists")){
							if(photoName.equals(file.getName())){
								isExistsPhoto =true;
								//存在  校验图片大小和 像素
								try {
									FileInputStream fis = new FileInputStream(file);
									if(fis.available()<9*1024 ||  fis.available()>20*1024){
										err += "照片应该不小于9KB，不大于20KB！";
									}
									BufferedImage bi = ImageIO.read(file);
									int width = bi.getWidth();
									int height = bi.getHeight();
									if(width!=358 || height!=441){
										err += "照片应满足 宽度：358像素，高度：441像素！";
									}
									fis.close();
									bi = null;
								} catch (IOException e) {
									e.printStackTrace();
								}
								break;
							}
							
						}
					}
				}
			}
			if(!isExistsPhoto){
				err += "照片信息不存在，请与照片一起打zip包导入，照片命名规则为：姓名+证件号码后4位！";
			}
		}
		
		
		return err;
	}

	private String generateFileName(String fileName) {
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String formatDate = format.format(new Date());
		int random = new Random().nextInt(10000);
		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);
		return formatDate + random + extension;
	}

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
	 * 保存前 处理map对象
	 *
	 * @author lifq
	 *
	 * 2016年4月22日  下午5:07:13
	 */
	@Override
	public void delBeforeSave(ContextInfo cti, Map<String,String> curMap, Map<String,Object> paraMap){
		try {
			String fileRootPath = Config.getProperty("fileRootPath");
			Integer areaId = (Integer)paraMap.get("areaId");
			if(areaId == 2){//北京时 校验  照片
				//此时图片肯定存在
				String cardNO = curMap.get("identity_code");
				if(cardNO.length()>4){
					String photoName = curMap.get("name") + cardNO.substring(cardNO.length()-4) +".jpg";
					for(File file:(List<File>)paraMap.get("fileLists")){
						if(photoName.equals(file.getName())){
							String saveName = generateFileName(file.getName());
							File localFile = new File(fileRootPath, saveName);
							if (!localFile.exists()) {
								localFile.getParentFile().mkdirs();
							} else {
								localFile = getUniqueFile(fileRootPath, saveName);
								photoName = localFile.getName();
							}
                            file.renameTo(localFile);
                            saveName = sysUploadfileService.uploadFile(localFile, "images/bs/");
							SysUploadfile sysUploadfile = new SysUploadfile();
							sysUploadfile.setFilename(photoName);
							sysUploadfile.setSavename(saveName);//mongodb id
							String month  = null == paraMap.get("month") ? "201606" : paraMap.get("month").toString().replace("-","");
							sysUploadfile.setFilepath(month);
							sysUploadfile.setFilesize(String.valueOf(file.length()));
							sysUploadfileService.save(cti,sysUploadfile);
							curMap.put("photo", sysUploadfile.getId()+"");
							break;
						}
						
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存前转换json
	 *
	 * @author wuzhe
	 *
	 * 2016年10月21日
	 */
	@Override
	public String parseJson(ContextInfo cti,Map<String,String> curMap,Map<String,Object> paraMap){
		super.parseInsurance(curMap,paraMap);
		super.parseHospital(curMap,paraMap);
		return super.parseJson(cti,curMap,paraMap);
	}

}
