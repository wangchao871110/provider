package com.xfs.analysis.utils;

import org.apache.commons.lang3.StringUtils;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Map;


/**
 * Title:ImageUtil Description: 用im4java对图片进行处理
 *
 * @Create_by:yinsy
 * @Create_date:2013-10-7
 * @Last_Edit_By:
 * @Edit_Description:
 * @version:baoogu 1.0
 */
public class ImageUtil {

	/**
	 * ImageMagick的路径
	 */
	public static String imageMagickPath;//= CommonConstants.SYSTEM_PROPS.getProperty("imageMagick.path");
	private static Logger log = LoggerFactory.getLogger(ImageUtil.class);

	static {
		imageMagickPath = "C:\\Program Files\\ImageMagick-7.0.5-Q16";
		if (!System.getProperty("os.name").toLowerCase().contains("windows")) {
			imageMagickPath = null;
		}
    }

	/**
	 * 根据坐标裁剪默认尺寸的图片
	 *
	 * @Create_by:yinsy
	 * @Create_date:2013-10-7
	 * @param fromPath
	 *            要裁剪图片的路径
	 * @param toPath
	 *            裁剪图片后的路径
	 * @param xStr
	 *            起始横坐标
	 * @param yStr
	 *            起始纵坐标
	 * @param wStr
	 *            宽度
	 * @param hStr
	 *            高度
	 * @throws Exception
	 * @Last_Edit_By:
	 * @Edit_Description:
	 * @Create_Version:baoogu 1.0
	 */
	public static void cutImage(String fromPath, String toPath, String xStr,
			String yStr, String wStr, String hStr) throws Exception {
		cutImage(fromPath, toPath, xStr, yStr, wStr, hStr, 0, 0);
	}

	/**
	 * 根据坐标裁剪图片
	 *
	 * @Create_by:yinsy
	 * @Create_date:2013-10-7
	 * @param fromPath
	 *            要裁剪图片的路径
	 * @param toPath
	 *            裁剪图片后的路径
	 * @param xStr
	 *            起始横坐标
	 * @param yStr
	 *            起始纵坐标
	 * @param wStr
	 *            宽度
	 * @param hStr
	 *            高度
	 * @param _width
	 *            剪裁的宽度
	 * @param _hight
	 *            剪裁的高度
	 * @throws Exception
	 * @Last_Edit_By:
	 * @Edit_Description:
	 * @Create_Version:baoogu 1.0
	 */
	public static void cutImage(String fromPath, String toPath, String xStr,
			String yStr, String wStr, String hStr, int _width, int _hight)
			throws Exception {
		int x = 0, y = 0, w = 0, h = 0;
		if (StringUtils.isNotBlank(xStr)) {
			x = Integer.parseInt(xStr);
		}
		if (StringUtils.isNotBlank(yStr)) {
			y = Integer.parseInt(yStr);
		}
		if (StringUtils.isNotBlank(wStr)) {
			w = Integer.parseInt(wStr);
		}
		if (StringUtils.isNotBlank(hStr)) {
			h = Integer.parseInt(hStr);
		}
		IMOperation op = new IMOperation();
		op.addImage(fromPath);
		/**
		 * width： 裁剪的宽度 height： 裁剪的高度 x： 裁剪的横坐标 y： 裁剪的挫坐标
		 */
		op.crop(w, h, x, y);
		op.p_repage(); // gif清空图片以外的空白
		if (_width > 0) {
			op.resize(_width, _hight);
		}
		op.addImage(toPath);
		ConvertCmd convert = new ConvertCmd();
		// linux下不要设置此值，不然会报错
		if (StringUtils.isNotBlank(imageMagickPath)) {
			convert.setSearchPath(imageMagickPath);
		}
		convert.run(op);
	}

	/**
	 * 缩放图片
	 *
	 * @Create_by:yinsy
	 * @Create_date:2013-10-7
	 * @param fromPath
	 *            源图片路径
	 * @param toPath
	 *            缩放后图片的路径
	 * @param width
	 *            缩放后的图片宽度
	 * @param height
	 *            缩放后的图片高度
	 * @param flag
	 *            true：按原图比例缩放，false：按指定大小缩放
	 * @throws Exception
	 * @Last_Edit_By:
	 * @Edit_Description:
	 * @Create_Version:baoogu 1.0
	 */
	public static void cutImage(String fromPath, String toPath, int width,
			int height, boolean flag) throws Exception {
		// ProcessStarter.setGlobalSearchPath("/bin/sh /c");
		FileUtil.createDirectory(toPath);
		IMOperation op = new IMOperation();

		op.addImage(fromPath);
		op.coalesce(); // gif图片需要
		if (width == 0) { // 根据高度缩放图片
			op.resize(null, height);
		} else if (height == 0) { // 根据宽度缩放图片
			op.resize(width, null);
		} else {
			if (flag) {
				op.resize(width, height);
			} else {
				op.resize(width, height, "!");
			}
		}
		op.addImage(toPath);
		ConvertCmd convert = new ConvertCmd();
		// linux下不要设置此值，不然会报错
		if (StringUtils.isNotBlank(imageMagickPath)) {
			convert.setSearchPath(imageMagickPath);
		}
		ImageUtil.exec("convert " + op.toString());
		convert.run(op);
	}

	public static void exec(String command) {
		Process proc;
		InputStream is = null;
		System.out.println("命令为：" + command);
		String baseCmd = "";
		String c = "";
		if (System.getProperty("os.name").indexOf("Window") != -1) {
			baseCmd = "cmd.exe";
			c = "-c";
		} else {
			baseCmd = "/bin/sh";
			c = "-c";
		}

		String[] cmd = { baseCmd, c, command };
		try {
			proc = Runtime.getRuntime().exec(cmd);
			int result = proc.waitFor();
			System.out.println("Process result:" + result);
			is = proc.getInputStream();
			byte[] b = new byte[1024];
			is.read(b);
			System.out.println("b:" + b);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据指定尺寸缩放图片
	 *
	 * @Create_by:yinsy
	 * @Create_date:2013-10-7
	 * @param fromPath
	 *            源图片路径
	 * @param toPath
	 *            缩放后图片的路径
	 * @param width
	 *            缩放后的图片宽度
	 * @param height
	 *            缩放后的图片高度
	 * @throws Exception
	 * @Last_Edit_By:
	 * @Edit_Description:
	 * @Create_Version:baoogu 1.0
	 */
	public static void cutImage(String fromPath, String toPath, int width,
			int height) throws Exception {
		cutImage(fromPath, toPath, width, height, false);
	}

	/**
	 * 给图片加水印
	 *
	 * @param srcPath
	 *            源图片路径
	 */
	public static void addImgText(String srcPath) throws Exception {
		IMOperation op = new IMOperation();
		op.font("宋体").gravity("southeast").pointsize(18).fill("#BCBFC8")
				.draw("text 5,5 juziku.com");
		op.addImage();
		op.addImage();
		System.out.println(op.toString());
		ConvertCmd convert = new ConvertCmd();
		// linux下不要设置此值，不然会报错
        // linux下不要设置此值，不然会报错
        if (StringUtils.isNotBlank(imageMagickPath)) {
            convert.setSearchPath(imageMagickPath);
        }
		convert.run(op, srcPath, srcPath);
	}

	/**
	 * 根据尺寸缩放图片[等比例缩放:参数height为null,按宽度缩放比例缩放;参数width为null,按高度缩放比例缩放]
	 *
	 * @param imagePath
	 *            源图片路径
	 * @param newPath
	 *            处理后图片路径
	 * @param width
	 *            缩放后的图片宽度
	 * @param height
	 *            缩放后的图片高度
	 * @return 返回true说明缩放成功,否则失败
	 */
	public static boolean zoomImage(String imagePath, String newPath, Integer width,
	        Integer height) {
		FileUtil.createDirectory(newPath);
	    boolean flag = false;
	    try {
	        IMOperation op = new IMOperation();
	        op.addImage(imagePath);
	        if (width == null) {// 根据高度缩放图片
	            op.resize(null, height);
	        } else if (height == null) {// 根据宽度缩放图片O
	            op.resize(width);
	        } else {
	            op.resize(width, height);
	        }
	        op.addImage(newPath);
	        ConvertCmd convert = new ConvertCmd();
	        // linux下不要设置此值，不然会报错
	        if (StringUtils.isNotBlank(imageMagickPath)) {
	            convert.setSearchPath(imageMagickPath);
			}
			exec(op.toString());
	        convert.run(op);
	        flag = true;
	    } catch (IOException e) {
	        System.out.println("文件读取错误!");
			log.error("文件读取错误!" + e.getMessage(), e);
	        flag = false;
	    } catch (InterruptedException e) {
			log.error("文件读取错误!" + e.getMessage(), e);
	        flag = false;
	    } catch (IM4JavaException e) {
			log.error("文件读取错误!" + e.getMessage(), e);
	        flag = false;
	    }
	    return flag;
	}

	/**
	 * 根据尺寸缩放图片[等比例缩放:参数height为null,按宽度缩放比例缩放;参数width为null,按高度缩放比例缩放]
	 *
	 * @param imagePath
	 *            源图片路径
	 * @param newPath
	 *            处理后图片路径
	 * @param width
	 *            缩放后的图片宽度
	 * @param height
	 *            缩放后的图片高度
	 * @return 返回true说明缩放成功,否则失败
	 */
	public static boolean zoomImage(String imagePath, String newPath, String width,
	        String height) {
		return zoomImage(imagePath, newPath, width.equals("0") ? null :Integer.parseInt(width)
				, height.equals("0") ? null :Integer.parseInt(height));
	}

	/**
	 *  @Method_Name    : zoomImage
	 *  @param srcRelativePath  图片源文件相对路径
	 *  @param toRelativePath   处理后图片相对路径
	 *  @param sizeMap
	 *	@return 		: void
	 *  @Creation Date  : 2014-10-31 上午11:09:51
	 *  @version        : v1.00
	 *  @Author         : wenhao
	 *  @Update Date    :
	 *  @Update Author  :
	 */
	public static void zoomImage(String srcRelativePath,String toRelativePath , Map<String,String> sizeMap){
		if(sizeMap!=null){
			for(String sizeName:sizeMap.keySet()){
				String [] sizeWH = sizeMap.get(sizeName).split("\\*");
				ImageUtil.zoomImage(CommonConstants.IMAGE_BASE_PATH + srcRelativePath,
						CommonConstants.IMAGE_BASE_PATH
						+ sizeName + "/"
						+ toRelativePath,
						sizeWH[0], sizeWH[1]);
			}
		}
	}



	public static void main(String[] args) throws Exception {
//		zoomImage("D:\\rr.png", "D:\\22rr.jpg",null,400);
//		cutImage("d:/555.png", "d:/122333.png","50","100","500","200");
//		int [] a = getImageSize("D:\\积累文档\\私人文档\\IMG_20160317_105019.jpg");
//		System.out.println(a[0]+";"+a[1]);

//		compress("D:\\aa.jpg",0.7);

//		jdkResize("D:\\aa.jpg",0.7f);

		cutImage("D:\\aa.jpg","D:\\aa.jpg",358,441 ,false);
	}

	/**
	 * 压缩并缩放
	 * @param filePath
	 * @param p
	 * @param width
     * @param height
     */
	public static void compressZoomImage(String filePath, double p,Integer width, Integer height){
		try {
			jdkResize(filePath, (float) p);
			cutImage(filePath, filePath, width, height ,false);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 获取图片的宽和高
	 *  @Method_Name    : getImageSize
	 *  @param filePath
	 *  @throws FileNotFoundException
	 *  @throws IOException
	 *	@return 		: Integer[]  下标0：宽；下标1：高
	 *  @Creation Date  : 2014-10-24 下午6:08:38
	 *  @version        : v1.00
	 *  @Author         : zhangyan
	 *  @Update Date    :
	 *  @Update Author  :
	 */
	public static int[] getImageSize (String filePath) throws FileNotFoundException, IOException {
		int [] size = null;
		try{
			File picture = new File(filePath);
			BufferedImage sourceImg =ImageIO.read(new FileInputStream(picture));
			size = new int[2];
			size[0] = sourceImg.getWidth();
			size[1] = sourceImg.getHeight();
		}catch (Exception e) {
		}
		return size;
	 }


	  /**
	    * @author yangzs
	    * @Title: watermark
	    * @Description: 添加水印
	    * @param @param src
	    * @param @param waterImg
	    * @param @throws Exception
	    * @return void
	    * @throws
	    */
	    public static void watermark(String src,String waterImg) throws Exception{
	        IMOperation op = new IMOperation();
	        op.addImage(src);
	        op.addImage(waterImg);
	        op.gravity("southeast");
	        op.composite().addImage(src);
	        ConvertCmd convert = new ConvertCmd();
	        setSoftPath(convert);
	        convert.run(op);
	    }

	    /**
	    * @Title: compress
	    * @Description: 压缩
	    * @param src
	    * @throws IOException
	    * @throws InterruptedException
	    * @throws IM4JavaException
	    * @return void
	    * @throws
	    * @Create_by:zongshan.yang
	    */
	    public static void compress(String src,double p) {
	        IMOperation compressOp = new IMOperation();
	        compressOp.strip();
	        compressOp.quality(p);
	        compressOp.addImage(src);
	        compressOp.addImage(src);
	        ConvertCmd convert = new ConvertCmd();
	        setSoftPath(convert);
	        try {
				convert.run(compressOp);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage(), e);
			}
	    }

	    private static void setSoftPath(ConvertCmd convert) {
            // linux下不要设置此值，不然会报错
            if (StringUtils.isNotBlank(imageMagickPath)) {
                convert.setSearchPath(imageMagickPath);
			}
		}

	/**
	 * jdk压缩图片-质量压缩
	 *
	 * @param destImagePath 被压缩文件路径
	 * @param quality 压缩质量比例
	 * @return
	 * @throws Exception
	 */
	public static void jdkResize(String destImagePath, float quality) throws Exception {
		// 目标文件
		File resizedFile = new File(destImagePath);
		// 压缩
		Image targetImage = ImageIO.read(resizedFile);
		int width = targetImage.getWidth(null);
		int height = targetImage.getHeight(null);
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.createGraphics();
		g.drawImage(targetImage, 0, 0, width, height, null);
		g.dispose();

		String formatName = destImagePath.substring(destImagePath.lastIndexOf(".") + 1);
		ImageIO.write(image, formatName, new File(destImagePath));

//		String ext = getFileType(resizedFile.getName());
//		if (ext.equals("jpg") || ext.equals("jpeg")) { // 如果是jpg
		// jpeg格式的对输出质量进行设置
//		FileOutputStream out = new FileOutputStream(resizedFile);
//		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//		JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(image);
//		jep.setQuality(quality, false);
//		encoder.setJPEGEncodeParam(jep);
//		encoder.encode(image);
//		out.close();
//		} else {
//			ImageIO.write(image, ext, resizedFile);
//		}

	}
	/**
	 * 通过文件名获取文件类型
	 *
	 * @param fileName 文件名
	 */
	private static String getFileType(String fileName) {
		if (fileName == null || "".equals(fileName.trim()) || fileName.lastIndexOf(".") == -1) {
			return null;
		}
		String type = fileName.substring(fileName.lastIndexOf(".") + 1);
		return type.toLowerCase();
	}
}