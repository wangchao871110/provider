package com.xfs.aop.task.verify;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.xfs.base.dao.SysUploadfileDAO;
import com.xfs.base.model.SysUploadfile;
import com.xfs.base.service.SysUploadfileService;
import com.xfs.common.ContextInfo;
import com.xfs.common.Result;
import com.xfs.common.util.Config;
import com.xfs.common.util.FileUtil;
import com.xfs.common.util.SpringContextUtil;
import com.xfs.sp.model.SpService;
import com.xfs.sps.service.QssFileService;

/**
 * C端文件处理
 *
 * @author liuyuan
 * @date 2016/7/18
 */
@Service
public class QssFileServiceImpl implements QssFileService {

    private static final Logger log = Logger.getLogger(QssFileServiceImpl.class);

    @Autowired
	private SysUploadfileService sysUploadfileService;

    @Autowired
	private SysUploadfileDAO sysUploadfileDAO;
    


    private MongoDbFactory mongoDbFactory;;
    
    /**
     * 保存文件
     * 
     * @param file
     *            要存储的文件
     * @return mongodb中附件的id
     */
    public String saveToMongo(File file) {
		mongoDbFactory= SpringContextUtil.getBean("qssMongoDbFactory");
        // 文件操作是在DB的基础上实现的，与表和文档没有关系
        GridFS gridFS = null;
        gridFS = new GridFS(mongoDbFactory.getDb());
        GridFSInputFile mongofile = null;
        try {
            mongofile = gridFS.createFile(file);
        } catch (IOException e) {
            log.info("附件保存失败!");
            throw new RuntimeException(e);
        }
        mongofile.put("filename", file.getName());
        // 保存
        mongofile.save();
        return mongofile.getId().toString();
    }

    /**
     * 根据附件id获取附件
     * 
     * @param id
     *            附件id
     * @return mongodb附件实体
     */
    public GridFSDBFile getFileByPK(String id) {
		mongoDbFactory= SpringContextUtil.getBean("qssMongoDbFactory");
        GridFS gridFs = new GridFS(mongoDbFactory.getDb());
        GridFSDBFile gridDBFile = gridFs.findOne(new ObjectId(id));
        return gridDBFile;
    }
    

 
	@Override
	public Result uploadImage(SpService spService, ContextInfo user, MultipartHttpServletRequest multiRequest) {
		Result result = Result.createResult().setSuccess(false);
		String rootPath = Config.getProperty("fileRootPath");
		String curDate = getDateStrByMonth();
		String filePath = rootPath + File.separator + curDate;
		// 第一步 上传 图片
		Iterator<String> iter = multiRequest.getFileNames();
		while (iter.hasNext()) {
			MultipartFile onefile = multiRequest.getFile(iter.next());// 由CommonsMultipartFile继承而来,拥有上面的方法.
			if (onefile != null) {
				String saveName = onefile.getOriginalFilename();
				String fileName = saveName;
				saveName = generateFileName(saveName);// 获取一个时间格式的名称（微信中为简易解决中文名称问题）
				File localFile = new File(filePath, saveName);
				if (!localFile.exists()) {
					localFile.mkdirs();
				} else {
					localFile = getUniqueFile(filePath, saveName);// 校验文件名重复
					saveName = localFile.getName();
				}
				if (!localFile.getName().endsWith(".jpg") && !localFile.getName().endsWith(".png")) {
					result.setError("请上传正确格式图片");
					return result;
				}
				try {
					// 将上传的文件写入新建的文件中
					onefile.transferTo(localFile);
					BufferedImage oneImg = ImageIO.read(new FileInputStream(localFile));
					// 原图片的宽和高
					int width = oneImg.getWidth(null);
					int height = oneImg.getHeight(null);
					// 第一次上传原图 存mongo 返回ID
					String imageString = this.uploadFile(localFile);
					if (StringUtils.isBlank(imageString)) {
						result.setError("图片上传失败！");
						break;
					}
					FileUtil f = new FileUtil();
					String fileSize = f.getFileSizes(localFile);
					// 保存到数据库
					SysUploadfile sysUploadfile = new SysUploadfile();
					sysUploadfile.setFilename(fileName);
					sysUploadfile.setSavename(imageString);
					sysUploadfile.setFilepath(curDate);
					sysUploadfile.setFilesize(fileSize);
					sysUploadfile.setCreateuser(user.getUserId());
					sysUploadfileService.save(user,sysUploadfile);
					// 原图的ID 暂时不返回到页面
					result.setDataValue("imageSavename", imageString);
					result.setDataValue("imageid", sysUploadfile.getId());
					//result.setDataValue("imageid", sysUploadfile.getId());
					// 创建一张白色图片 格式为png
					int bgw = width * 2;
					int bgh = height * 2;
					BufferedImage bgimages = new BufferedImage(bgw, bgh, BufferedImage.TYPE_INT_RGB);
					String bgPathFileName = filePath + File.separator + "whiteimage" + UUID.randomUUID() + ".jpg";
					Graphics2D bgg = bgimages.createGraphics();
					bgg.setColor(Color.white);
					bgg.fillRect(0, 0, bgw, bgh);
					bgg.drawRect(0, 0, bgw, bgh);
					bgg.dispose();
					// 保存白色背景大图
					File bgFile = new File(bgPathFileName);
					ImageIO.write(bgimages, "jpg", bgFile);
					// 背景图片转换为JPEG、JPG文件
					// FileOutputStream bgOut = new FileOutputStream(bgFile);
					// JPEGImageEncoder encoder =
					// JPEGCodec.createJPEGEncoder(bgOut);
					// encoder.encode(bgimages);
					// bgOut.close();

					// 原图片转换为JPEG、JPG文件
					// FileOutputStream oneOut = new
					// FileOutputStream(localFile);
					// JPEGImageEncoder oneencoder =
					// JPEGCodec.createJPEGEncoder(oneOut);
					// oneencoder.encode(onesImg);
					// oneOut.close();

					// x,y 表示原图 在背景图中原图的坐标位置
					int x = (int) (bgw * 0.5);
					int y = (int) (bgh * 0.5);
					// BufferedImage markImage = ImageIO.read(bgFile);
					int newx = (int) (width * 0.5);
					int newy = (int) (height * 0.5);
					Graphics2D g = bgimages.createGraphics();
					g.drawImage(oneImg, x - newx, y - newy, width, height, null);
					g.dispose();
					String newPathFileName = filePath + File.separator + "newimage" + curDate + ".jpg";
					FileOutputStream out = new FileOutputStream(newPathFileName);
					out.close();
					File newFile = new File(newPathFileName);
					ImageIO.write(bgimages, "jpg", newFile);

					// 上传处理后的新图 存mongo 返回 mongodb的ID
					String newimageString = this.uploadFile(newFile);
					if (StringUtils.isBlank(newimageString)) {
						// 保存失败
						result.setError("图片上传失败！");
						break;
					}
					FileUtil f2 = new FileUtil();
					String newfileSize = f2.getFileSizes(newFile);
					// 保存到数据库
					SysUploadfile newsysUploadfile = new SysUploadfile();
					newsysUploadfile.setFilename(fileName);
					newsysUploadfile.setSavename(newimageString);
					newsysUploadfile.setFilepath(curDate);
					newsysUploadfile.setFilesize(newfileSize);
					newsysUploadfile.setCreateuser(user.getUserId());
					sysUploadfileService.save(user,newsysUploadfile);
					//result.setDataValue("newimageid", newsysUploadfile.getId());
					result.setDataValue("newimageid", newimageString);
					result.setSuccess(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				result.setError("上传失败！");
			}
		}
		return result;
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
	
	/**
	 * 获得一个以时间格式的新名称
	 * 
	 * @param fileName
	 *            原图名称
	 * @return
	 */
	private String generateFileName(String fileName) {
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String formatDate = format.format(new Date());
		int random = new Random().nextInt(10000);
		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);
		return formatDate + random + extension;
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
	 * 保存图片 到mongo
	 */
	public String uploadFile(File file) {

		String imageString = saveToMongo(file);
		return imageString;
	}
	@Override
	public Result cutImage(String fileId, int x, int y, int width, int height, ContextInfo cti ) {
		Result result = Result.createResult().setSuccess(false);
		if (!StringUtils.isEmpty(fileId)) {
			String oldFilePic = this.getPhysicsFileBySaveName(fileId);
			String newFilePath = oldFilePic.substring(0, oldFilePic.lastIndexOf(File.separator));
			String lastfilePath = newFilePath + UUID.randomUUID() + ".jpg";

			// 图片转换为JPEG、JPG文件
			try {
				File file = new File(oldFilePic);
				File destFile = new File(lastfilePath);
				if (!destFile.getParentFile().exists()) {
					destFile.getParentFile().mkdir();
				}
				BufferedImage src = ImageIO.read(file); // 读入文件
				Image image = src.getScaledInstance(src.getWidth(), src.getHeight(), Image.SCALE_DEFAULT);
				BufferedImage tag = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB);
				Graphics g = tag.getGraphics();
				g.drawImage(image, 0, 0, null); // 绘制缩小后的图
				g.dispose();
				ImageIO.write(tag, "jpg", new FileOutputStream(destFile));// 输出到文件流
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 根据ID 取图片 实体
			// 去剪裁图片 返回图片名字
			String fileName = cutImage(lastfilePath, newFilePath, x, y, width, height);
			// 本地创建图片
			File file = new File(newFilePath, fileName);
			FileUtil f = new FileUtil();
			String fileSize;
			try {
				fileSize = f.getFileSizes(file);
				String curDate = getDateStrByMonth();

				// 存mongo 返回 mongodb的ID
				String imageString = this.uploadFile(file);
				// 保存到数据库
				SysUploadfile sysUploadfile = new SysUploadfile();
				sysUploadfile.setFilename(fileName);
				sysUploadfile.setSavename(imageString);
				sysUploadfile.setFilepath(curDate);
				sysUploadfile.setFilesize(fileSize);
				sysUploadfile.setCreateuser(cti.getUserId());
				sysUploadfileService.save(cti,sysUploadfile);
				file.delete();
				// oldfile.delete();
				result.setSuccess(true);
				//result.setDataValue("imageId", sysUploadfile.getId());
				result.setDataValue("imageId", imageString);
			} catch (Exception e) {
				result.setError("图片保存失败！");
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public SysUploadfile findBySaveName(SysUploadfile vo){
		return sysUploadfileDAO.findBySaveName(vo);
	}
	
	public String getPhysicsFileBySaveName(String saveName) {
		SysUploadfile vo = new SysUploadfile();
		//vo.setId(id);
		vo.setSavename(saveName);
		SysUploadfile uploadFile = this.findBySaveName(vo);
		String fileRootPath = Config.getProperty("fileRootPath");// 文件根目录
		String filePath = fileRootPath + File.separator + uploadFile.getFilepath();
		String suffix = uploadFile.getFilename().substring(uploadFile.getFilename().lastIndexOf("."));
		File localFile = new File(filePath, uploadFile.getSavename()+suffix);
		if (localFile.exists()) {
			long lastUpLoadTime;
			if (uploadFile.getUpdatets() != null)
				lastUpLoadTime = uploadFile.getUpdatets().getTime();
			else
				lastUpLoadTime = uploadFile.getCreatets().getTime();
			//文件最后修改时间和文件服务记录的上传时间比较，如新，则直接返回目录中的文件，如旧重新获取
			if (localFile.lastModified() >= lastUpLoadTime) {
				return localFile.getPath();
			}
			else {
				localFile.delete();
			}
		}

		GridFSDBFile dbfile = readFileById(uploadFile.getSavename());
		if (dbfile == null )
			return null;
		InputStream is = dbfile.getInputStream();
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(localFile);
			byte[] b = new byte[1024];
			while((is.read(b)) != -1){
				fos.write(b);
			}
			is.close();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		return localFile.getPath();
	}
	
	public String cutImage(String srcImg, String destImg, int x, int y, int width, int height) {
		return cutImage(new File(srcImg), destImg, new java.awt.Rectangle(x, y, width, height));
	}
	
	public String cutImage(File srcImg, String destImgPath, java.awt.Rectangle rect) {
		File destImg = new File(destImgPath);
		String DEFAULT_CUT_PREVFIX = "cut";
		String newpicFilePath = "";
		if (destImg.exists()) {
			String p = destImg.getPath();
			try {
				if (!destImg.isDirectory())
					p = destImg.getParent();
				if (!p.endsWith(File.separator))
					p = p + File.separator;
				// 拿到剪裁后的图片路径 不要根目录
				newpicFilePath = DEFAULT_CUT_PREVFIX + "_" + new Date().getTime() + "_" + srcImg.getName();
				cutImage(srcImg, new FileOutputStream(
						p + DEFAULT_CUT_PREVFIX + "_" + new Date().getTime() + "_" + srcImg.getName()), rect);
				//
			} catch (FileNotFoundException e) {
				// log.warn("the dest image is not exist.");
				newpicFilePath = "";
			}
		}
		return newpicFilePath;
	}

	public static void cutImage(File srcImg, OutputStream output, java.awt.Rectangle rect) {
		if (srcImg.exists()) {
			FileInputStream fis = null;
			ImageInputStream iis = null;
			try {

				fis = new FileInputStream(srcImg);
				// ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png,
				// PNG,JPEG, WBMP, GIF, gif]
				String types = Arrays.toString(ImageIO.getReaderFormatNames()).replace("]", ",");
				String suffix = null;
				// 获取图片后缀 img_path.substring(img_path.lastIndexOf(".") +
				// 1).trim().toLowerCase();
				if (srcImg.getName().indexOf(".") > -1) {
					suffix = srcImg.getName().substring(srcImg.getName().lastIndexOf(".") + 1);
				} // 类型和图片后缀全部小写，然后判断后缀是否合法
				if (suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase() + ",") < 0) {
					return;
				}
				// 将FileInputStream 转换为ImageInputStream
				iis = ImageIO.createImageInputStream(fis);
				// 根据图片类型获取该种类型的ImageReader
				ImageReader reader = ImageIO.getImageReadersBySuffix(suffix).next();
				reader.setInput(iis, true);
				ImageReadParam param = reader.getDefaultReadParam();
				param.setSourceRegion(rect);
				// TODO 读取 异常
				BufferedImage bi = reader.read(0, param);
				ImageIO.write(bi, suffix, output);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (fis != null)
						fis.close();
					if (iis != null)
						iis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			log.warn("the src image is not exist.");
		}
	}
	
	/**
	 * 从mongo查询出来图片 查看图片
	 */
	public GridFSDBFile readFileById(String fileId) {
		GridFSDBFile dbFile = getFileByPK(fileId);
		return dbFile;
	}
	
	
	
	
}
