package com.xfs.sp.service.impl;

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
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;

import com.xfs.common.constant.IStaticVarConstant;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xfs.base.dao.SysUploadfileDAO;
import com.xfs.base.model.BsArea;
import com.xfs.base.model.BsCompanyInfo;
import com.xfs.base.model.BsEcontractApply;
import com.xfs.base.model.BsSppayrecord;
import com.xfs.base.model.SysUploadfile;
import com.xfs.base.service.BsCompanyInfoService;
import com.xfs.base.service.SysUploadfileService;
import com.xfs.bill.dao.SpsBillTempBusinessFieldDao;
import com.xfs.bill.model.SpsBillTemplate;
import com.xfs.bill.service.SpsBillTemplateService;
import com.xfs.bs.service.BsEcontractApplyService;
import com.xfs.bs.service.BsSppayrecordService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.aliyun.AliyunImageUtil;
import com.xfs.common.fadada.util.FddClientUtil;
import com.xfs.common.image.ImageMarkLogoUtil;
import com.xfs.common.mongo.MongoDao;
import com.xfs.common.page.PageModel;
import com.xfs.common.sms.SmsUtil;
import com.xfs.common.util.Config;
import com.xfs.common.util.DateUtil;
import com.xfs.common.util.FileUtil;
import com.xfs.common.util.SessionUtil;
import com.xfs.mall.info.model.SpsMallInfo;
import com.xfs.mall.info.model.SpsMallServicerange;
import com.xfs.mall.info.model.SpsMallSuccesscase;
import com.xfs.mall.info.service.SpsMallInfoService;
import com.xfs.mall.info.service.SpsMallServicerangeService;
import com.xfs.mall.info.service.SpsMallSuccesscaseService;
import com.xfs.mall.product.model.SpsMallProduct;
import com.xfs.serviceBill.dto.ServiceBillVo;
import com.xfs.sp.dao.SpServiceDao;
import com.xfs.sp.dao.SpsServiceareaDao;
import com.xfs.sp.model.SpService;
import com.xfs.sp.model.SpsChangedoc;
import com.xfs.sp.model.SpsServicearea;
import com.xfs.sp.service.SpServiceService;
import com.xfs.sp.service.SpsChangedocService;
import com.xfs.user.dao.SysUserRoleDao;
import com.xfs.user.model.SysRole;
import com.xfs.user.model.SysUser;
import com.xfs.user.model.SysUserRole;
import com.xfs.user.service.SysUserService;

import sun.misc.BASE64Decoder;

@Service
public class SpServiceServiceImpl implements SpServiceService , IStaticVarConstant{

	private static final Logger log = Logger.getLogger(SpServiceServiceImpl.class);

	@Autowired
	private SpServiceDao spServiceDao;
	
	@Autowired
	private SpsServiceareaDao spsServiceareaDao;

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private MongoDao mongoDao;
	@Autowired
	private SysUploadfileDAO sysUploadfileDAO;

	@Autowired
	private BsCompanyInfoService bsCompanyInfoService;
	@Autowired
	private SysUploadfileService sysUploadfileService;

	@Autowired
	private BsSppayrecordService bsSppayrecordService;

	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	
	@Autowired
	private SpsMallInfoService mallInfoService;
	
	@Autowired 
	private SpsChangedocService changedocService;
	
	@Autowired
	private SpsMallServicerangeService spsMallServicerangeService;
	
	@Autowired
	private SpsMallSuccesscaseService spsMallSuccesscaseService;
	
	@Autowired
	private BsEcontractApplyService bsEcontractApplyService;

    @Autowired
    private SpsBillTemplateService spsBillTemplateService;

    @Autowired
    private SpsBillTempBusinessFieldDao  spsBillTempBusinessFieldDao;






	public int save(ContextInfo cti, SpService vo) {
		return spServiceDao.save(cti, vo);
	}

	public int insert(ContextInfo cti, SpService vo) {
		vo.setCreateDt(new Date());
		return spServiceDao.insert(cti, vo);
	}

	public int remove(ContextInfo cti, SpService vo) {
		return spServiceDao.remove(cti, vo);
	}

	public int update(ContextInfo cti, SpService vo) {
		return spServiceDao.update(cti, vo);
	}

	public PageModel findPage(PageInfo pi, SpService vo) {

		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spServiceDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpService> datas = spServiceDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;

	}

	public List<SpService> findAll(SpService vo) {

		List<SpService> datas = spServiceDao.freeFind(vo);
		return datas;

	}

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/04/11 15:31:40
	@Override
	public SpService findByPk(Integer spId) {
		SpService sp = new SpService();
		sp.setSpId(spId);
		return spServiceDao.findByPK(sp);
	}

	@Override
	public SpService findOLContacts(Integer spId) {
		return spServiceDao.findOLContacts(spId);
	}

	@Override
	public SpService findOLContactsByCmPerson(String mobile) {
		return spServiceDao.findOLContactsByCmPerson(mobile);
	}

	// @Override
	// public Result spJoin(String spname, String mobile) {
	// Result r;
	// SysUser user;
	// r = checkForBase(spname, mobile);
	//
	// SpService spservice = new SpService();
	// spservice.setSpName(spname);
	// spservice.setMobile(mobile);
	// spservice.setServiceState("UNSUBMITTED");
	// insert(spservice);
	//
	// user = new SysUser();
	// user.setOrgId(spservice.getSpId());
	// user.setMobile(mobile);
	// user.setUserType("SERVICE");
	// r = sysUserService.addOrEdit(user);
	// if (r.isSuccess())
	// r.setDataValue("user", user);
	// return r;
	// }

	private Result checkForBase(String spname, String mobile) {
		Result r = Result.createResult();
		r.setSuccess(false);

		if (null == spname || spname.length() == 0) {
			r.setError("请提供公司名称！");
			return r;
		}
		SpService qry = new SpService();
		qry.setSpName(spname);
		List<SpService> datas = spServiceDao.find(qry);
		if (null != datas && datas.size() > 0) {
			for (SpService data : datas) {
				// if (!data.getSpId().equals(spid)) {
				r.setError("提交失败，公司名称已存在!");
				return r;
				// }
			}
		}
		if (null == mobile || mobile.length() == 0) {
			r.setError("请提供联系方式！");
			return r;
		}
		if (sysUserService.existMobile(mobile, CMCORPTYPE_SERVICE)) {
			// if (!user.getUserId().equals(userid)) {
			r.setError("提交失败，联系方式已存在!");
			return r;
			// }
		}
		r.setSuccess(true);
		return r;
	}

	private Result checkForAll(SpService sp) {
		Result r = checkForBase(sp.getSpName(), sp.getMobile());
		if (!r.isSuccess())
			return r;
		r.setSuccess(false);
		// if (null == sp.getAddress() || sp.getAddress().length() == 0) {
		// r.setError("请提供公司地址！");
		// return r;
		// }
		if (null == sp.getAreaId() || sp.getAreaId() <= 0) {
			r.setError("请提供公司地址！");
			return r;
		}
		if (null == sp.getContactor() || sp.getContactor().length() == 0) {
			r.setError("请提供联系人！");
			return r;
		}
		if (null == sp.getBusiLicenseNum() || sp.getBusiLicenseNum().length() == 0) {
			r.setError("请提供统一社会信用代码！");
			return r;
		}
		/*if (null == sp.getBusiLicenseFile() || sp.getBusiLicenseFile() <= 0) {
			r.setError("请提供营业执照！");
			return r;
		}*/
		/*if (null == sp.getServiceNum() || sp.getServiceNum() < 0) {
			r.setError("请提供服务员工数！");
			return r;
		}*/
		r.setSuccess(true);
		return r;
	}

	private Result checkByQiXin1(SpService sp) {
		Result r = Result.createResult();
		r.setSuccess(false);
		// 信用代码、公司名称不匹配 提示错误
		BsCompanyInfo info;
		try {
			info = bsCompanyInfoService.queryCompanyInfoAllByName(sp.getSpName());
		} catch (Exception e) {
			log.error("查询启信API异常", e);
			r.setError("信息检查失败，请联系客服!");
			return r;
		}

		if (info == null) {
			r.setError(String.format("未检索到%s，请填写正确的公司名称!", sp.getSpName()));
			return r;
		}
		if (!Objects.equals(sp.getBusiLicenseNum(), info.getCreditNo())) {
			r.setError(String.format("信用代码与实际不符，请正确填写!", sp.getBusiLicenseNum(), sp.getSpName()));
			return r;
		}
		// // 有负面记录
		// if (info.getCompanyInfoDetails() != null &&
		// info.getCompanyInfoDetails().size() > 0) {
		// sp.setServiceState("AUDITING");
		// } else { // 无负面记录
		// sp.setServiceState("AUDITED");
		// }
		if (DateUtil.isValidDate(info.getStart_date()))
			sp.setEstablishedTime(DateUtil.parseDate(info.getStart_date(), "yyyy-MM-dd"));
		sp.setRegisteredCapital(info.getRegisteredCapital());
		sp.setRegisteredGov(info.getRegisteredGov());
		sp.setBusiScope(info.getBusiScope());
		sp.setComanyType(info.getCompanyType());


		r.setSuccess(true);
		return r;

	}

	@Override
	public Result spJoinSubmit(ContextInfo cti, SpService sp, String password) {
		Result r = new Result();
        sp.setServiceState(SpService.SP_SERVICE_STATE_REGISTERED);
        sp.setEnabledMall("UNSIGN");
		r = checkForAll(sp);
		if (r.isSuccess()) {
			r = checkByQiXin1(sp);
			if (r.isSuccess()) {
				/** 法大大企业认证   王超  2016年8月12号 sta*/
				// 企业自动认证 返回数据格式：{result:返回结果, code:结果代码,msg:描述,customer_id:客户编号}
				// result:success和error, 
				// code:1000是成功，1001是客户已存在,法大大审核中,2001是参数缺失或不合法,2002是客户已存在,信息不一致，2003是其他错误,请联系法大大
				// msg:描述
				// customer_id:客户编号  起鑫宝
				
				String memo="启信宝认证通过；";
				String syncCompanyAuto = FddClientUtil.invokeSyncCompanyAuto(sp.getSpName(), sp.getSpEmail(), sp.getBusiLicenseNum(), sp.getMobile());
				if(null != syncCompanyAuto){
					JSONObject syncCompanyAutoJson = JSONObject.parseObject(syncCompanyAuto);
					if("success".equals(syncCompanyAutoJson.get("result")) && "1000".equals(syncCompanyAutoJson.get("code"))){
						sp.setCustomerNum(syncCompanyAutoJson.getString("customer_id"));
						memo+="法大大认证通过";
					}else{
						memo+="法大大未认证通过";
					}
				}
				/** 法大大企业认证   王超  2016年8月12号 end*/
				if( null != sp.getCustomerNum()){
					sp.setServiceState(SpService.SP_SERVICE_STATE_AUDITED);
				}
				sp.setMemo(memo);
			}else{
				sp.setMemo("启信宝未认证通过");
			}

            insert(cti,sp);
            /*服务商业务城市*/
            SpsServicearea spsServicearea=new SpsServicearea();
            spsServicearea.setRelType("INNER");
            spsServicearea.setSpId(sp.getSpId());
            spsServicearea.setAreaId(sp.getAreaId());
            spsServicearea.setDr(0);  
            spsServiceareaDao.insert(cti,spsServicearea);
            
            /*1 添加合作企业城市*/ 
    		Integer parentSp=sp.getParentSp();
    		if(null != parentSp)
    		{
    			SpsServicearea sp2=new SpsServicearea();
    			sp2.setSpId(sp.getParentSp());
    			sp2.setRelType("INNER");
    			
    			List<SpsServicearea> listb= spsServiceareaDao.freeFind(sp2);
    			for(int i=0;i<listb.size();i++)
    			{
    				SpsServicearea spsServiceareaA=new SpsServicearea();
    				spsServiceareaA.setSpId(sp.getSpId());
    				spsServiceareaA.setRelType("GROUP");
    				spsServiceareaA.setAreaId(listb.get(i).getAreaId());
    				spsServiceareaA.setDr(0);
    				spsServiceareaDao.insert(cti,spsServiceareaA);
    				
    			}
    			
    				SpsServicearea spsServiceareaB=new SpsServicearea();
    				spsServiceareaB.setSpId(sp.getParentSp());
    				spsServiceareaB.setRelType("GROUP");
    				spsServiceareaB.setAreaId(sp.getAreaId());
    				spsServiceareaB.setDr(0);
    				spsServiceareaDao.insert(cti,spsServiceareaB);
    				
    				/*1 协作集团相互添加spid
    	    		CpCooperation cpCooperationA = new CpCooperation();
    	    		cpCooperationA.setSpId(sp.getSpId());
    	    		cpCooperationA.setPartnerSpId(sp.getParentSp());
    	    		cpCooperationA.setState("SIGNED");
    	    		cpCooperationA.setType("BRANCH");
    	    		cpCooperationDao.insert(cpCooperationA);
    	    		
    	    		CpCooperation cpCooperationB = new CpCooperation();
    	    		cpCooperationB.setSpId(sp.getParentSp());
    	    		cpCooperationB.setPartnerSpId(sp.getSpId());
    	    		cpCooperationB.setState("SIGNED");
    	    		cpCooperationB.setType("BRANCH");
    	    		cpCooperationDao.insert(cpCooperationB);
    	    		*/
    				
    				
    		}
    		/*2*/
            
    		
            BsSppayrecord record = new BsSppayrecord();
            record.setSpId(sp.getSpId());
            record.setBeginDate(new Date());
            record.setEndDate(DateUtils.addDays(new Date(), 90));
            record.setServiceNum(sp.getServiceNum());
            record.setUnit("TRIAL");
            bsSppayrecordService.insert(cti,record);


            addDefaultTemp(cti, sp);

            SysUser user = new SysUser();
            user.setOrgId(sp.getSpId());
            user.setRealName(sp.getContactor());
            user.setMobile(sp.getMobile());
            user.setUserType(CMCORPTYPE_SERVICE);
            user.setPassword(password);
            /*1添加邮箱字段*/
            user.setEmail(sp.getSpEmail());
            /*2*/
            //第一个系统管理员 给所有客户权限
            r = sysUserService.addOrEditWithDataFunc(cti,user,"Y",new Integer[0]);
            if (r.isSuccess()) {
                r.setDataValue("user", user);
                r.setDataValue("sp", sp);
				initSpRole(cti,user.getUserId());
            }
		}
		return r;
	}

    private void addDefaultTemp(ContextInfo cti, SpService sp) {
        SpsBillTemplate spsBillTemplate = new SpsBillTemplate();
        spsBillTemplate.setDr(0);
        spsBillTemplate.setSpId(sp.getSpId());
        spsBillTemplate.setName("默认模板");
        spsBillTemplate.setIsEffect("Y");
        spsBillTemplate.setIsDefault("Y");
        spsBillTemplateService.insert(cti, spsBillTemplate);
        spsBillTempBusinessFieldDao.insertBusinessFieldByDicitem(cti, spsBillTemplate);
    }

    @Override
	public void initSpRole(ContextInfo cti,Integer user_id) {
		/**
		 * 默认服务商角色
		 */
		SysUserRole sysUserRole = new SysUserRole();
		sysUserRole.setUserId(user_id);
		sysUserRole.setRoleId(SysRole.SAAS_ADMIN_ROLE_ID);
		sysUserRoleDao.insert(cti,sysUserRole);
		
		sysUserRole.setId(null);
		sysUserRole.setRoleId(SysRole.SAAS_BILL_ROLE_ID);
		sysUserRoleDao.insert(cti,sysUserRole);

		sysUserRole.setId(null);
		sysUserRole.setRoleId(SysRole.SAAS_F_ROLE_ID);
		sysUserRoleDao.insert(cti,sysUserRole);

		sysUserRole.setId(null);
		sysUserRole.setRoleId(SysRole.SAAS_MALL_ROLE_ID);
		sysUserRoleDao.insert(cti,sysUserRole);

		sysUserRole.setId(null);
		sysUserRole.setRoleId(SysRole.SAAS_C_SERVCIE);
		sysUserRoleDao.insert(cti,sysUserRole);

		sysUserRole.setId(null);
		sysUserRole.setRoleId(SysRole.SAAS_OP_SERVCIE);
		sysUserRoleDao.insert(cti,sysUserRole);
		
		sysUserRole.setId(null);
		sysUserRole.setRoleId(SysRole.SAAS_CP_OP_ID);
		sysUserRoleDao.insert(cti,sysUserRole);
	}

	public Result uploadImage(ContextInfo cti,SpService spService, MultipartHttpServletRequest multiRequest) {
		Result result = Result.createResult().setSuccess(false);
		String rootPath = Config.getProperty("fileRootPath");
		String curDate = getDateStrByMonth();
		String filePath = rootPath + File.separator + curDate;
		// 第一步 上传 图片
		Iterator<String> iter = multiRequest.getFileNames();
		while (iter.hasNext()) {
			MultipartFile onefile = multiRequest.getFile(iter.next());// 由CommonsMultipartFile继承而来,拥有上面的方法.
			if (onefile != null) {
				String saveName = onefile.getOriginalFilename();
				String fileName = saveName;
				saveName = generateFileName(saveName);// 获取一个时间格式的名称（微信中为简易解决中文名称问题）
				File localFile = new File(filePath, saveName);
				if (!localFile.exists()) {
					localFile.getParentFile().mkdirs();
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
					sysUploadfile.setCreateuser(cti.getUserId());
					sysUploadfileService.save(cti,sysUploadfile);
					// 原图的ID 暂时不返回到页面
					result.setDataValue("imageid", sysUploadfile.getId());
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
					newsysUploadfile.setCreateuser(cti.getUserId());
					sysUploadfileService.save(cti,newsysUploadfile);
					result.setDataValue("newimageid", newsysUploadfile.getId());
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

	public Result uploadImage(ContextInfo cti,String base64Code, SpService spService, SysUser user) {
		Result result = Result.createResult().setSuccess(false);
		try {

			if (base64Code == null) {
				result.setSuccess(false);
				return result;
			}
			String[] baseArr = base64Code.split(",");// 截掉前段字符
			if (baseArr.length < 2) {
				result.setSuccess(false);
				return result;
			}
			String fileRootPath = Config.getProperty("fileRootPath");
			String curDate = getDateStrByMonth();
			String baseStr = baseArr[1];
			baseStr = baseStr.replace(" ", "+");

			BASE64Decoder decoder = new BASE64Decoder();
			byte[] b = decoder.decodeBuffer(baseStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			if (!new File(fileRootPath).exists()) {
				new File(fileRootPath).mkdirs();
			}
			String fileName = "image" + UUID.randomUUID() + ".jpg";
			// 本地创建图片
			File file = new File(fileRootPath, fileName);
			FileUtil f = new FileUtil();
			String fileSize = f.getFileSizes(file);
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(b);
			fos.flush();
			fos.close();

			// 存mongo 返回 mongodb的ID
			String imageString = this.uploadFile(file);
			// 保存到数据库
			SysUploadfile sysUploadfile = new SysUploadfile();
			sysUploadfile.setFilename(fileName);
			sysUploadfile.setSavename(imageString);
			sysUploadfile.setFilepath(curDate);
			sysUploadfile.setFilesize(fileSize);
			sysUploadfile.setCreateuser(user.getUserId());
			sysUploadfileService.save(cti,sysUploadfile);
			file.delete();
			result.setSuccess(true);
			result.setDataValue("imageid", sysUploadfile.getId());

		} catch (Exception e) {
			result.setSuccess(false);
			result.setError("服务器连接失败");
		}
		return result;
	}



	public Result cutImage(ContextInfo cti,String fileId, int x, int y, int width, int height,SysUser user,boolean flag) {
		Result result = Result.createResult().setSuccess(false);
		if (!StringUtils.isEmpty(fileId)) {
			String oldFilePic = sysUploadfileService.getPhysicsFile(Integer.parseInt(fileId));
			String newFilePath = oldFilePic.substring(0, oldFilePic.lastIndexOf(File.separator));
			String lastfilePath = newFilePath + UUID.randomUUID() + ".jpg";
			String marklastfile = newFilePath + UUID.randomUUID() + "mark.jpg";

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
			String fileRootPath = Config.getProperty("fileRootPath");// 文件根目录
			// String newFilePath = fileRootPath + File.separator;
			// 去剪裁图片 返回图片名字
			String fileName = cutImage(lastfilePath, newFilePath, x, y, width, height);
			// 本地创建图片
			File file ;
			//TODO 加水印
			if (flag) {
				String src = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "watermark" + File.separator + "watermark.png";
				ImageMarkLogoUtil.markImageByIcon(src, newFilePath+ File.separator +fileName, marklastfile, 0, 0);
				file = new File(marklastfile);
			}else{
				file = new File(newFilePath, fileName);
			}
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
				sysUploadfile.setCreateuser(user.getUserId());
				sysUploadfileService.save(cti,sysUploadfile);
				// file.delete();
				// oldfile.delete();
				result.setSuccess(true);
				result.setDataValue("imageId", sysUploadfile.getId());
			} catch (Exception e) {
				result.setError("图片保存失败！");
				e.printStackTrace();
			}
		}
		return result;
	}

    @Override
    public boolean findSpServiceByName(SpService spService) {
        List<SpService> spServices = spServiceDao.find(spService);
        if (spServices.size() > 0) {
            return true;
        }
        return false;
    }

    public String cutImage(String srcImg, String destImg, int x, int y, int width, int height) {
		return cutImage(new File(srcImg), destImg, new java.awt.Rectangle(x, y, width, height));
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
					// log.error("Sorry, the image suffix is illegal. the
					// standard image suffix is {}." + types);
					//
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
			// log.warn("the src image is not exist.");
		}
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

    public List<String> findAllSpServiceName(SpService spService) {
        return spServiceDao.findAllSpServiceName(spService);


    }

	@Override  
	public Map findAreaIdBySpId(String spId){
		return spServiceDao.findAreaIdBySpId(spId);
	}
	
	@Override
	public List<SpService> freeFindSpidSpName(SpService spService) {
		return spServiceDao.freeFindSpidSpName(spService);
	}

    /**
     * 查询优质服务商列表
     *
     * @return
     */
    public List<SpService> findMallSpServies(BsArea area) {
        return spServiceDao.findMallSpServies(area);
    }

    /**
     * 查询服务机构列表
     *
     * @param areaIds
     * @return
     */
    public List<Map<String, Object>> findMallSpServices(String areaIds) {
        return spServiceDao.findMallSpServices(areaIds);
    }

    public Map<String, Object> findMallSpServiceByProductId(SpsMallProduct vo){
        return spServiceDao.findMallSpServiceByProductId(vo);
    }

    /**
     * 服务机构列表
     *
     * @param areaId
     * @param categoryId
     * @param certificationType
     * @param searchWord
     * @return
     */
    public PageModel findServicesByCondition(PageInfo pi,String areaId, String categoryId, String certificationType, String yearRange, String searchWord) {
        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        String beginYear = null, endYear = null;
        if (!com.xfs.common.util.StringUtils.isBlank(yearRange) && yearRange.contains("-")) {
            String[] arr = yearRange.split("-", 2);
            beginYear = arr[0];
            endYear = arr[1];
        }
        pm.setTotal(spServiceDao.findCountByCondition(areaId, categoryId, certificationType, beginYear, endYear, searchWord));
        pm.setDatas(spServiceDao.findListByCondition(areaId, categoryId, certificationType, beginYear, endYear, searchWord, pageIndex, pageSize));
        return pm;
    }

    public SpService findByPK(SpService vo){
        return spServiceDao.findByPK(vo);
    }

    @Override
    public PageModel findPageOrder(PageInfo pi, SpService vo) {
        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = spServiceDao.countFreeFind(vo);
        pm.setTotal(total);
        List<SpService> datas = spServiceDao.freeFind(vo, pageIndex, pageSize,"service_state asc,sp_id desc");
        pm.setDatas(datas);
        return pm;
    }

    @Override
    public PageModel findAndareaPageOrder(PageInfo pi, SpService vo) {
        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = spServiceDao.countFreeFind(vo);
        pm.setTotal(total);
        List<SpService> datas = spServiceDao.freeFindAndarea(vo, pageIndex, pageSize,"service_state asc,sp_id desc");
        pm.setDatas(datas);
        return pm;
    }
    @Override
    public PageModel findSpServiceByState(PageInfo pi,String serviceState,String enabledMall,String spname) {
        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = spServiceDao.countSpServiceByState(serviceState,enabledMall, spname);
        pm.setTotal(total);
        List<SpService> datas = spServiceDao.findSpServiceByState(serviceState,enabledMall, spname, pageIndex, pageSize," case when service_state = 'SIGNING' OR enabled_mall='SIGNING' then 0 else 1 end,sp_id desc");
        pm.setDatas(datas);
        return pm;
    }

    @Override
    public SpService findByPK(Integer spid){
        SpService sp = new SpService();
        sp.setSpId(spid);
        return findByPK(sp);
    }

    @Override
    public Result activeSpService(ContextInfo cti,SpService sp) {
        Result r = Result.createResult();
        if (sp.getServiceState().equals("STOPED")) {
            sp.setServiceState("SIGNED");
            update(cti,sp);
        } else {
            r.setError("服务商状态非停用，不能启用，请检查");
            r.setSuccess(false);
        }
        return r;

    }
    @Override
    public Result stopSpService(ContextInfo cti,SpService sp) {
        Result r = Result.createResult();
        if (sp.getServiceState().equals("SIGNED")) {
            sp.setServiceState("STOPED");
            update(cti,sp);
        } else {
            r.setError("服务商状态非签约，不能停用，请检查");
            r.setSuccess(false);
        }
        return r;

    }
    @Override
    public Result activeMall(ContextInfo cti,SpService sp) {
        Result r = Result.createResult();
        if (sp.getEnabledMall().equals("STOPED")) {
            sp.setEnabledMall("SIGNED");
            update(cti,sp);
        } else {
            r.setError("商城状态非停用，不能启用，请检查");
            r.setSuccess(false);
        }
        return r;

    }
    @Override
    public Result stopMall(ContextInfo cti,SpService sp) {
        Result r = Result.createResult();
        if (sp.getEnabledMall().equals("SIGNED")) {
            sp.setEnabledMall("STOPED");
            update(cti,sp);
        } else {
            r.setError("商城状态非签约，不能停用，请检查");
            r.setSuccess(false);
        }
        return r;

    }

    @Override
    public Result passAudit(ContextInfo cti,SpService sp) {
        Result r = Result.createResult();
        sp.setServiceState("AUDITED");
        update(cti,sp);
        return r;
    }

    @Override
    public Result noPassAudit(ContextInfo cti,SpService sp) {
        Result r = Result.createResult();
        sp.setServiceState("UNAUDITED");
        update(cti,sp);
        return r;
    }


    @Override
    public Result signSpService(ContextInfo cti,SpService sp) {
        Result r = Result.createResult();
        if (sp.getServiceState().equals("SIGNING") || sp.getEnabledMall().equals("SIGNING")) {
            if (sp.getServiceState().equals("SIGNING"))
                sp.setServiceState("SIGNED");
            if (sp.getEnabledMall().equals("SIGNING"))
                sp.setEnabledMall("SIGNED");
            sp.setUnauditReason("");
            update(cti,sp);
        } else {
            r.setError("服务商未提交签约申请，不能签约，请检查");
            r.setSuccess(false);
        }
        return r;

    }
    @Override
    public int countFreeFindByStatus() {
        return spServiceDao.countFreeFindByStatus();
    }

    @Override
    public Result refuseSpService(ContextInfo cti,SpService sp, String reason) {
        Result r = Result.createResult();
        if (sp.getServiceState().equals("SIGNING") || sp.getEnabledMall().equals("SIGNING")) {
            if (sp.getServiceState().equals("SIGNING"))
                sp.setServiceState("AUDITED");
            if (sp.getEnabledMall().equals("SIGNING"))
                sp.setEnabledMall("UNSIGN");
            sp.setUnauditReason(reason);
            update(cti,sp);
        } else {
            r.setError("服务商未提交签约申请，请检查");
            r.setSuccess(false);
        }
        return r;
    }

    /**
     * 保存图片 到mongo
     */
    public String uploadFile(File file) {
		try {
			 return  AliyunImageUtil.uploadPicture(file);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return null;
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

    public Result cutImage(ContextInfo cti,String fileId, int x, int y, int width, int height) {
        Result result = Result.createResult().setSuccess(false);
        if (!StringUtils.isEmpty(fileId)) {
            String oldFilePic = sysUploadfileService.getPhysicsFile(Integer.parseInt(fileId));
            // SysUploadfile sysupload = new SysUploadfile();
            // sysupload.setId(Integer.parseInt(fileId));
            // // 根据ID 取图片 实体
            // SysUploadfile sysup = sysUploadfileDAO.findByPK(sysupload);
            String fileRootPath = Config.getProperty("fileRootPath");// 文件根目录
            // String suffix =
            // sysup.getFilename().substring(sysup.getFilename().lastIndexOf(".")
            // + 1);
            // // 上传到服务器的图片路径 + 文件全称
            // String oldFilePic = fileRootPath + File.separator +
            // sysup.getFilepath() + File.separator
            // + sysup.getSavename() + "." + suffix;

            String newFilePath = fileRootPath + File.separator;
            // String newFilePath = fileRootPath + File.separator +
            // sysup.getFilepath();
            // 去剪裁图片 返回图片名字
            String fileName = cutImage(oldFilePic, newFilePath, x, y, width, height);
            // 本地创建图片
            File file = new File(newFilePath, fileName);
//			File oldfile = new File(oldFilePic);
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
                // file.delete();
                // oldfile.delete();
                result.setSuccess(true);
                result.setDataValue("imageId", sysUploadfile.getId());
            } catch (Exception e) {
                result.setError("图片保存失败！");
                e.printStackTrace();
            }
        }
        return result;
    }

    //加水印
    public Result cutImage(ContextInfo cti,String fileId, int x, int y, int width, int height,boolean flag) {
        Result result = Result.createResult().setSuccess(false);
        if (!StringUtils.isEmpty(fileId)) {
            String oldFilePic = sysUploadfileService.getPhysicsFile(Integer.parseInt(fileId));
            String newFilePath = oldFilePic.substring(0, oldFilePic.lastIndexOf(File.separator));
            String lastfilePath = newFilePath + UUID.randomUUID() + ".jpg";
            String marklastfile = newFilePath + UUID.randomUUID() + "mark.jpg";

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
            String fileRootPath = Config.getProperty("fileRootPath");// 文件根目录
            // String newFilePath = fileRootPath + File.separator;
            // 去剪裁图片 返回图片名字
            String fileName = cutImage(lastfilePath, newFilePath, x, y, width, height);
            // 本地创建图片
            File file ;
            //TODO 加水印
            if (flag) {
                String src = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "watermark" + File.separator + "watermark.png";
                ImageMarkLogoUtil.markImageByIcon(src, newFilePath+ File.separator +fileName, marklastfile, 0, 0);
                file = new File(marklastfile);
            }else{
                file = new File(newFilePath, fileName);
            }
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
                if(null != cti){
                    sysUploadfile.setCreateuser(cti.getUserId());
                }
                sysUploadfileService.save(cti,sysUploadfile);
                // file.delete();
                // oldfile.delete();
                result.setSuccess(true);
                result.setDataValue("imageId", sysUploadfile.getId());
            } catch (Exception e) {
                result.setError("图片保存失败！");
                e.printStackTrace();
            }
        }
        return result;
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
                newpicFilePath = DEFAULT_CUT_PREVFIX + "_" + new java.util.Date().getTime() + "_" + srcImg.getName();
                cutImage(srcImg, new FileOutputStream(
                        p + DEFAULT_CUT_PREVFIX + "_" + new java.util.Date().getTime() + "_" + srcImg.getName()), rect);
                //
            } catch (FileNotFoundException e) {
                // log.warn("the dest image is not exist.");
                newpicFilePath = "";
            }
        }
        return newpicFilePath;
    }

    @Override
    public PageModel findSpServiceByRecom(PageInfo pi, String spName) {
        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = spServiceDao.countSpServiceByRecom(spName);
        pm.setTotal(total);
        List<SpService> datas = spServiceDao.findSpServiceByRecom(spName, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;
    }

    private Result checkByQiXin(SpService sp) {
        Result r = Result.createResult();
        r.setSuccess(false);
        // 信用代码、公司名称不匹配 提示错误
        BsCompanyInfo info;
        try {
            info = bsCompanyInfoService.queryCompanyInfoAllByName(sp.getSpName());
        } catch (Exception e) {
            log.error("查询启信API异常", e);
            r.setError("信息检查失败，请联系客服!");
            return r;
        }

        if (info == null) {
            r.setError(String.format("未检索到%s，请填写正确的公司名称!", sp.getSpName()));
            return r;
        }
        if (!sp.getBusiLicenseNum().equals(info.getCreditNo())) {
            r.setError(String.format("信用代码与实际不符，请正确填写!", sp.getBusiLicenseNum(), sp.getSpName()));
            return r;
        }
        // // 有负面记录
        // if (info.getCompanyInfoDetails() != null &&
        // info.getCompanyInfoDetails().size() > 0) {
        // sp.setServiceState("AUDITING");
        // } else { // 无负面记录
        // sp.setServiceState("AUDITED");
        // }
        if (DateUtil.isValidDate(info.getStart_date()))
            sp.setEstablishedTime(DateUtil.parseDate(info.getStart_date(), "yyyy-MM-dd"));
        sp.setRegisteredCapital(info.getRegisteredCapital());
        sp.setRegisteredGov(info.getRegisteredGov());
        sp.setBusiScope(info.getBusiScope());
        sp.setComanyType(info.getCompanyType());


        r.setSuccess(true);
        return r;

    }

    @Override
    public Result spJoinSubmit(ContextInfo cti,SpService sp) {
//        Result r = new Result();
//        sp.setServiceState(SpService.SP_SERVICE_STATE_REGISTERED);
//        sp.setEnabledMall("UNSIGN");
//        r = checkByQiXin(sp);
//        if (r.isSuccess()) {
//            /** 法大大企业认证   王超  2016年8月12号 sta*/
//            // 企业自动认证 返回数据格式：{result:返回结果, code:结果代码,msg:描述,customer_id:客户编号}
//            // result:success和error,
//            // code:1000是成功，1001是客户已存在,法大大审核中,2001是参数缺失或不合法,2002是客户已存在,信息不一致，2003是其他错误,请联系法大大
//            // msg:描述
//            // customer_id:客户编号  起鑫宝
//
//            String memo="启信宝认证通过；";
//            String syncCompanyAuto = FddClientUtil.invokeSyncCompanyAuto(sp.getSpName(), sp.getSpEmail(), sp.getBusiLicenseNum(), sp.getMobile());
//            if(null != syncCompanyAuto){
//                JSONObject syncCompanyAutoJson = JSONObject.parseObject(syncCompanyAuto);
//                if("success".equals(syncCompanyAutoJson.get("result")) && "1000".equals(syncCompanyAutoJson.get("code"))){
//                    sp.setCustomerNum(syncCompanyAutoJson.getString("customer_id"));
//                    memo+="法大大认证通过";
//                }else{
//                    memo+="法大大未认证通过";
//                }
//            }
//            /** 法大大企业认证   王超  2016年8月12号 end*/
//            if( null != sp.getCustomerNum()){
//                sp.setServiceState(SpService.SP_SERVICE_STATE_AUDITED);
//            }
//            sp.setMemo(memo);
//        }else{
//        //	sp.setServiceState(SpService.SP_SERVICE_STATE_UNAUDITED);
//            sp.setMemo("启信宝未认证通过");
//        }
//
//        update(cti,sp);
//
		Result r = Result.createResult().setSuccess(false);
        sp.setServiceState(SpService.SP_SERVICE_STATE_REGISTERED);
        sp.setEnabledMall("UNSIGN");
        //本地校验企业名称
//		r = checkForAll(sp);
        //启信宝校验
		r = checkByQiXin1(sp);
		if (r.isSuccess()) {
			/** 法大大企业认证   王超  2016年8月12号 sta*/
			// 企业自动认证 返回数据格式：{result:返回结果, code:结果代码,msg:描述,customer_id:客户编号}
			// result:success和error, 
			// code:1000是成功，1001是客户已存在,法大大审核中,2001是参数缺失或不合法,2002是客户已存在,信息不一致，2003是其他错误,请联系法大大
			// msg:描述
			// customer_id:客户编号  起鑫宝
			String memo="启信宝认证通过；";
			String syncCompanyAuto = FddClientUtil.invokeSyncCompanyAuto(sp.getSpName(), sp.getSpEmail(), sp.getBusiLicenseNum(), sp.getMobile());
			if(null != syncCompanyAuto){
				JSONObject syncCompanyAutoJson = JSONObject.parseObject(syncCompanyAuto);
				if("success".equals(syncCompanyAutoJson.get("result")) && "1000".equals(syncCompanyAutoJson.get("code"))){
					sp.setCustomerNum(syncCompanyAutoJson.getString("customer_id"));
					memo+="法大大认证通过";
				}else{
					memo+="法大大未认证通过";
				}
			}
			/** 法大大企业认证   王超  2016年8月12号 end*/
			if( null != sp.getCustomerNum()){
				sp.setServiceState(SpService.SP_SERVICE_STATE_AUDITED);
			}
			sp.setMemo(memo);

            update(cti,sp);
            /*服务商业务城市*/
            SpsServicearea spsServicearea=new SpsServicearea();
            spsServicearea.setRelType("INNER");
            spsServicearea.setSpId(sp.getSpId());
            spsServicearea.setAreaId(sp.getAreaId());
            spsServicearea.setDr(0);  
            spsServiceareaDao.insert(cti,spsServicearea);
            
            /*1 添加合作企业城市*/ 
	    		Integer parentSp=sp.getParentSp();
	    		if(null != parentSp)
	    		{
	    			SpsServicearea sp2=new SpsServicearea();
	    			sp2.setSpId(sp.getParentSp());
	    			sp2.setRelType("INNER");
	    			
	    			List<SpsServicearea> listb= spsServiceareaDao.freeFind(sp2);
	    			for(int i=0;i<listb.size();i++)
	    			{
	    				SpsServicearea spsServiceareaA=new SpsServicearea();
	    				spsServiceareaA.setSpId(sp.getSpId());
	    				spsServiceareaA.setRelType("GROUP");
	    				spsServiceareaA.setAreaId(listb.get(i).getAreaId());
	    				spsServiceareaA.setDr(0);
	    				spsServiceareaDao.insert(cti,spsServiceareaA);
	    				
	    			}
	    			
	    				SpsServicearea spsServiceareaB=new SpsServicearea();
	    				spsServiceareaB.setSpId(sp.getParentSp());
	    				spsServiceareaB.setRelType("GROUP");
	    				spsServiceareaB.setAreaId(sp.getAreaId());
	    				spsServiceareaB.setDr(0);
	    				spsServiceareaDao.insert(cti,spsServiceareaB);
	    				
	    				/*1 协作集团相互添加spid
	    	    		CpCooperation cpCooperationA = new CpCooperation();
	    	    		cpCooperationA.setSpId(sp.getSpId());
	    	    		cpCooperationA.setPartnerSpId(sp.getParentSp());
	    	    		cpCooperationA.setState("SIGNED");
	    	    		cpCooperationA.setType("BRANCH");
	    	    		cpCooperationDao.insert(cpCooperationA);
	    	    		
	    	    		CpCooperation cpCooperationB = new CpCooperation();
	    	    		cpCooperationB.setSpId(sp.getParentSp());
	    	    		cpCooperationB.setPartnerSpId(sp.getSpId());
	    	    		cpCooperationB.setState("SIGNED");
	    	    		cpCooperationB.setType("BRANCH");
	    	    		cpCooperationDao.insert(cpCooperationB);
	    	    		*/
	    		}
    		/*2*/
            BsSppayrecord record = new BsSppayrecord();
            record.setSpId(sp.getSpId());
            record.setBeginDate(new Date());
            record.setEndDate(DateUtils.addDays(new Date(), 90));
            record.setServiceNum(sp.getServiceNum());
            record.setUnit("TRIAL");
            bsSppayrecordService.insert(cti,record);
            SysUser user = sysUserService.findByMobile(sp.getMobile(), CMCORPTYPE_SERVICE, cti.getOrgId());
            r = new Result();
            if (r.isSuccess()) {
                r.setDataValue("user", user);
                r.setDataValue("sp", sp);
				initSpRole(cti,user.getUserId());
				//更新服务商状态
				spServiceDao.update(cti,sp);
            }

		}else{
			sp.setServiceState(SpService.SP_SERVICE_STATE_REGISTERED);
	        sp.setEnabledMall("UNSIGN");
	        r.setDataValue("sp", sp);
	      //更新服务商状态
			spServiceDao.update(cti,sp);
			sp.setMemo("启信宝未认证通过");
		}
        return r;
    }

    public Map<String,Object> CountFreeInfoBySpId(Integer spId){
        return spServiceDao.CountFreeInfoBySpId(spId);
    };

    public void updateSpService(ContextInfo cti, SpService vo ){
        spServiceDao.updateSpService(cti,vo);
    }

    /**
     * 根据ID获取服务商信息
     */
    @Override
    public SpService findSpServiceById(Integer saasSpId) {
        return spServiceDao.findSpServiceById(saasSpId);
    }
    
    /**
    * @Title: saveSpORMallinfo 
    * @Description: 服务商编辑页面保存
    * @param @param request
    * @param @param vo
    * @param @param spsMallInfo
    * @param @return    设定文件 
    * @return Result    返回类型 
    * @throws
     */
    public Result saveSpORMallinfo(HttpServletRequest request,SpService vo, SpsMallInfo spsMallInfo){
    	Result result = Result.createResult().setSuccess(true);
    	Map<String,	Object> map = new HashMap<String,Object>();
		if(vo.getHumanCertification() != null){
			map.put("humanCertification", vo.getHumanCertification());
		}
		if(vo.getLaborCertification() != null){
			map.put("laborCertification", vo.getLaborCertification());
		}
		if(vo.getCertificate() != null || !"".equals(vo.getCertificate())){
			map.put("certificate", vo.getCertificate());
		}
		SpsChangedoc doc = new SpsChangedoc();
		doc.setSpId(vo.getSpId());
		doc.setOrderType("SAAS"); 
		SpsChangedoc doc1 =  changedocService.findByPK(doc);
		doc.setSSpService(JSON.toJSONString(map));
		List<SpsMallInfo> pk = mallInfoService.findAll(spsMallInfo);
		int flag ;
		if(pk==null){
			flag = mallInfoService.save(SessionUtil.getContextInfo(request.getSession()), spsMallInfo);
		} else {
			flag = mallInfoService.update(SessionUtil.getContextInfo(request.getSession()), spsMallInfo);
		}
		if(flag <= 0) {
			result.setError("保存失败");
		}
		if (null == doc1){
			int flag1 = changedocService.save(SessionUtil.getContextInfo(request.getSession()),doc);
			if(flag1 > 0){
				result.setSuccess(true);
			} else {
				result.setError("保存失败");
			}
		} else {
			int flag2 = changedocService.update(SessionUtil.getContextInfo(request.getSession()),doc);
			if(flag2 > 0){
				result.setSuccess(true);
			}else {
				result.setError("保存失败");
			}
		}
		updateSpService(SessionUtil.getContextInfo(request.getSession()),vo);
    	return result;
    }

	@Override
	public Result saasspsUpdate(HttpServletRequest request, SpService vo, SpsChangedoc spsChangedoc,
			BsEcontractApply apply, SpsMallInfo spsMallInfo, String state, String successCaseName, String successCase,
			String orderType,String busiLicenseFile1) {
		Result result = Result.createResult().setSuccess(false);
		SpsChangedoc doc = new SpsChangedoc();
		doc.setSpId(vo.getSpId());
		List<SpsChangedoc> list = changedocService.findAll(doc);
		SpService service = new SpService();
		service.setSpId(vo.getSpId());
		service = findAll(service).get(0);
		if (StringUtils.isNotBlank(state)) {
			if ("SAAS".equals(orderType)) {
				if ("AUDITED".equals(state)) {
					String l = "";
					for (SpsChangedoc s : list) {
						if ("SAAS".equals(s.getOrderType())) {
							l = s.getSSpService();
						}
					}
					String syncCompanyAuto = FddClientUtil.invokeSyncCompanyAuto(service.getSpName(),
							service.getSpEmail(), service.getBusiLicenseNum(), service.getMobile());
					JSONObject syncCompanyAutoJson = JSONObject.parseObject(syncCompanyAuto);
					if (syncCompanyAutoJson == null) {
						result.setError("认证未通过！");
						result.setSuccess(false);
						return result;
					}
					if ("success".equals(syncCompanyAutoJson.get("result"))
							&& "1000".equals(syncCompanyAutoJson.get("code"))) {
						apply.setCustomerNum(syncCompanyAutoJson.getString("customer_id"));
						/** 法大大企业认证 王超 2016年8月12号 end */
						JSONObject obj = JSON.parseObject(l);
						vo.setCertificate(obj.getString("certificate"));
						vo.setHumanCertification(obj.getString("humanCertification"));
						vo.setLaborCertification(obj.getString("laborCertification"));
						vo.setServiceState("SIGNED");
						update(SessionUtil.getContextInfo(request.getSession()), vo);
						apply.setOrgId(vo.getSpId());
						BsEcontractApply bsEcontractApply = new BsEcontractApply();
						bsEcontractApply.setOrgId(vo.getSpId());
						List<BsEcontractApply> a = bsEcontractApplyService.findAll(bsEcontractApply);
						if(a != null && a.size()>0){
							apply.setId(a.get(0).getId());
							bsEcontractApplyService.save(SessionUtil.getContextInfo(request.getSession()), apply);
						} else {
							bsEcontractApplyService.insert(SessionUtil.getContextInfo(request.getSession()), apply);
						}
						int flag = changedocService.update(SessionUtil.getContextInfo(request.getSession()),
								spsChangedoc);
						if (flag > 0) {
							 if (service != null && !StringUtils.isBlank(service.getMobile())) {
					                try {
										SmsUtil.sendSms(service.getMobile(), "恭喜您！您的签约申请已经通过，请及时登录查看。【用友薪福社】");
									} catch (Exception e) {
										e.printStackTrace();
									}
					            }
							result.setSuccess(true);
						}
					} else {
						result.setError(syncCompanyAutoJson.get("msg").toString());
					}

				} else if ("REFUSE".equals(state)) {
					spsChangedoc.setState("REJECTED");
					changedocService.update(SessionUtil.getContextInfo(request.getSession()), spsChangedoc);
					vo.setServiceState("AUDITED");
					update(SessionUtil.getContextInfo(request.getSession()), vo);
					result.setSuccess(true);
				} else {
					changedocService.update(SessionUtil.getContextInfo(request.getSession()), spsChangedoc);
					result.setSuccess(true);
				}
			} else if ("MALL".equals(orderType)) {
				if ("AUDITED".equals(state)) {
					changedocService.update(SessionUtil.getContextInfo(request.getSession()), spsChangedoc);
					apply.setOrgId(vo.getSpId());
					BsEcontractApply bsEcontractApply = new BsEcontractApply();
					bsEcontractApply.setOrgId(vo.getSpId());
					List<BsEcontractApply> apps =  bsEcontractApplyService.findAll(bsEcontractApply);
					apply.setOrgType(state);
					apply.setBusiLicenseFile(busiLicenseFile1);
					if(apps != null && apps.size() > 0){
						apply.setId(apps.get(0).getId());
						bsEcontractApplyService.update(SessionUtil.getContextInfo(request.getSession()), apply);
					}else{
						bsEcontractApplyService.insert(SessionUtil.getContextInfo(request.getSession()), apply);
					}
					String range = "";
					String mall = "";
					String success = "";
					for (SpsChangedoc s : list) {
						if ("MALL".equals(s.getOrderType())) {
							range = s.getMServiceRange();
							mall = s.getMMallInfo();
							success = s.getMSuccessCase();
						}
					}
					if (success == null || "{}".equals(success) || "{\"caseImg\":\"\"}".equals(success)) {
					} else {
						JSONObject obj = JSON.parseObject(success);
						String caseDescribe = obj.getString("caseDescribe");
						String cpName = obj.getString("cpName");
						String caseImg = obj.getString("caseImg");
						String[] caseDescribes = caseDescribe.split(",");
						String[] cpNames = cpName.split(",");
						String[] caseImgs = null;
						if(null != caseImg){
							 caseImgs = caseImg.split(",");
						}
						for (int i = 0; i < caseDescribes.length; i++) {
							SpsMallSuccesscase spsMallSuccesscase = new SpsMallSuccesscase();
							spsMallSuccesscase.setSpId(vo.getSpId());
							spsMallSuccesscase.setCaseDescribe(caseDescribes[i]);
							spsMallSuccesscase.setCpName(cpNames[i]);
							if (null != caseImgs){
								spsMallSuccesscase.setCaseImg(caseImgs[i]);
							}
							spsMallSuccesscase.setDr(0);
							spsMallSuccesscaseService.save(SessionUtil.getContextInfo(request.getSession()),
									spsMallSuccesscase);
						}
					}
					if (mall == null || mall == "{}") {
					} else {
						JSONObject obj = JSON.parseObject(mall);
						spsMallInfo.setSpId(vo.getSpId());
						List<SpsMallInfo> l = mallInfoService.findBySpId(spsMallInfo);
						SpsMallInfo mallInfo = null;
						if (CollectionUtils.isEmpty(l)) {
							mallInfo = new SpsMallInfo();
							mallInfo.setSpId(vo.getSpId());
							mallInfo.setDr(0);
							mallInfo.setCompanyIntroduction(obj.getString("companyIntroduction"));
							mallInfo.setServiceIntroduction(obj.getString("serviceIntroduction"));
							if (null == obj.getString("logo") || "".equals(obj.getString("logo"))) {

							} else {
								vo.setLogo(Integer.parseInt(obj.getString("logo")));
							}
							mallInfoService.save(SessionUtil.getContextInfo(request.getSession()), mallInfo);
						} else {
							spsMallInfo = l.get(0);
							spsMallInfo.setSpId(vo.getSpId());
							spsMallInfo.setCompanyIntroduction(obj.getString("companyIntroduction"));
							spsMallInfo.setServiceIntroduction(obj.getString("serviceIntroduction"));
							if (null == obj.getString("logo") || "".equals(obj.getString("logo"))) {

							} else {
								vo.setLogo(Integer.parseInt(obj.getString("logo")));
							}
							int i = mallInfoService.update(SessionUtil.getContextInfo(request.getSession()), spsMallInfo);
							if (i < 0) {
								result.setError("更新失败");
								return result;
							}
						}
						String s[] = busiLicenseFile1.split("-");
						vo.setBusiLicenseFile(Integer.parseInt(s[1]));
						vo.setEnabledMall("SIGNED");
						update(SessionUtil.getContextInfo(request.getSession()), vo);

					}
					if (range != null && range != "{}") {
						String[] array = range.split(",");
						for (String s : array) {
							SpsMallServicerange mallServicerange = new SpsMallServicerange();
							mallServicerange.setCategoryId(Integer.parseInt(s));
							mallServicerange.setSpId(spsChangedoc.getSpId());
							mallServicerange.setDr(0);
							spsMallServicerangeService.insert(SessionUtil.getContextInfo(request.getSession()),
									mallServicerange);
						}
					}
					if (service != null && !StringUtils.isBlank(service.getMobile())) {
		                try {
							SmsUtil.sendSms(service.getMobile(), "恭喜您！您的签约申请已经通过，请及时登录查看。【用友薪福社】");
						} catch (Exception e) {
							e.printStackTrace();
						}
		            }
					result.setSuccess(true);
				} else if ("REFUSE".equals(state)) {
					spsChangedoc.setState("REJECTED");
					changedocService.update(SessionUtil.getContextInfo(request.getSession()), spsChangedoc);
					vo.setEnabledMall("UNSIGN");
					update(SessionUtil.getContextInfo(request.getSession()), vo);
					result.setSuccess(true);
				} else {
					changedocService.update(SessionUtil.getContextInfo(request.getSession()), spsChangedoc);
					result.setSuccess(true);
				}
			}
		}
		// TODO Auto-generated method stub
		return result;
	}

	/**
	 *  根据企业ID获取对应的服务商ID
	 *  @param   inventCpId
	 *	@return 			: com.xfs.sp.model.SpService
	 *  @createDate  	: 2016-12-27 13:23
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-12-27 13:23
	 *  @updateAuthor  :
	 */
	public SpService findSpByInventedCorp(Integer inventCpId){
		return spServiceDao.findSpByInventedCorp(inventCpId);
	}
	
	/**
	 * 获取服务商对账列表
	 *  @param vo
	 *  @param pageIndex
	 *  @param pageSize
	 *  @return 
	 *  @createDate  	: 2017年7月10日 下午3:35:41
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年7月10日 下午3:35:41
	 *  @updateAuthor  	:
	 */
	@Override
	public List<ServiceBillVo> findServiceBillList(ServiceBillVo vo, int pageIndex, int pageSize) {
		return spServiceDao.findServiceBillList(vo, pageIndex, pageSize);
	}

	/**
	 * 获取服务商对账总数
	 *  @param vo
	 *  @return 
	 *  @createDate  	: 2017年7月10日 下午3:35:44
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年7月10日 下午3:35:44
	 *  @updateAuthor  	:
	 */
	@Override
	public Integer findServiceBillListCount(ServiceBillVo vo) {
		return (Integer) spServiceDao.findServiceBillListCount(vo);
	}

	/**
	 * 获取服务商列表
	 *  @param vo
	 *  @return 
	 *  @createDate  	: 2017年7月12日 下午2:15:00
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年7月12日 下午2:15:00
	 *  @updateAuthor  	:
	 */
	@Override
	public List<ServiceBillVo> findServiceBillCount(ServiceBillVo vo) {
		return spServiceDao.findServiceBillCount(vo);
	}
	
	/**
	 * 根据 服务商名称 查询，进行重复校验
	 *
	 * @author lifq
	 *
	 * 2017年8月7日  下午1:51:52
	 */
	public List<Map<String,Object>> findSpByName(Map<String,Object> paraMap){
		return spServiceDao.findSpByName(paraMap);
	}
}
