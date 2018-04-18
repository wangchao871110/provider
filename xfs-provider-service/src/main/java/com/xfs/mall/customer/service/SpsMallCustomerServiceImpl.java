package com.xfs.mall.customer.service;

import static com.xfs.common.redies.keychain.IRedisKeys.BUSINESS_CUSTOMER_LIST;
import static com.xfs.common.redies.keychain.IRedisKeys.EXPIRE_HOUR;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.xfs.common.aliyun.AliyunImageUtil;
import com.xfs.common.constant.IStaticVarConstant;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.base.model.SysUploadfile;
import com.xfs.base.service.SysUploadfileService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.exception.BusinessException;
import com.xfs.common.mongo.MongoDao;
import com.xfs.common.page.PageModel;
import com.xfs.common.redies.RedisUtil;
import com.xfs.common.sms.SmsUtil;
import com.xfs.common.util.Config;
import com.xfs.common.util.DateUtil;
import com.xfs.common.util.FileUtil;
import com.xfs.mall.customer.dao.SpsMallCustomerDao;
import com.xfs.mall.customer.model.SpsMallCustomer;
import com.xfs.sps.util.vo.Md5Util;
import com.xfs.user.dao.SysUserRoleDao;
import com.xfs.user.model.SysRole;
import com.xfs.user.model.SysUser;
import com.xfs.user.model.SysUserRole;
import com.xfs.user.service.SysUserService;

import sun.misc.BASE64Decoder;

@Service
public class SpsMallCustomerServiceImpl implements SpsMallCustomerService , IStaticVarConstant{

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsMallCustomerServiceImpl.class);
	
	@Autowired
	private SpsMallCustomerDao spsMallCustomerDao;
	@Autowired
	private MongoDao mongoDao;
	@Autowired
	private SysUploadfileService sysUploadfileService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	
	public int save(ContextInfo cti, SpsMallCustomer vo ){
		return spsMallCustomerDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsMallCustomer vo ){
		return spsMallCustomerDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsMallCustomer vo ){
		return spsMallCustomerDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsMallCustomer vo ){
		return spsMallCustomerDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsMallCustomer vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsMallCustomerDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsMallCustomer> datas = spsMallCustomerDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsMallCustomer> findAll(SpsMallCustomer vo){
		
		List<SpsMallCustomer> datas = spsMallCustomerDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/07 11:06:21
	
	/**
	 * 根据主键查询 实体
	 *
	 * @author lifq
	 *
	 * 2016年6月8日  下午3:07:55
	 */
	public SpsMallCustomer findByPK(SpsMallCustomer vo){
		return spsMallCustomerDao.findByPK(vo);
	}
	
	@Override
	public SpsMallCustomer findByIMUserid(Integer userid){
		return spsMallCustomerDao.findByIMUserid(userid);
	}
	@Override
	public Map findByMyUserid(Integer userid){
		return  spsMallCustomerDao.findByMyUserid(userid);
	}
	/**
	 * 上传截图后图片
	 *
	 * @author lifq
	 *
	 * 2016年6月21日  上午10:53:27
	 */
	public Result uploadImage(ContextInfo cti, String base64Code){
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
			String curDate = DateUtil.getYearMonthStr();
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
			String imageString = sysUploadfileService.uploadFile(file, "images/sps/");
			// 保存到数据库
			SysUploadfile sysUploadfile = new SysUploadfile();
			sysUploadfile.setFilename(fileName);
			sysUploadfile.setSavename(imageString);
			sysUploadfile.setFilepath(curDate);
			sysUploadfile.setFilesize(fileSize);
			sysUploadfile.setCreateuser(cti.getUserId());
			sysUploadfileService.save(cti, sysUploadfile);
			file.delete();
			result.setSuccess(true);
			result.setDataValue("imageid", sysUploadfile.getId());

		} catch (Exception e) {
			result.setSuccess(false);
			result.setError("服务器连接失败");
		}
		return result;
	}
	/**
	 * 保存
	 *
	 * @author lifq
	 *
	 * 2016年7月15日  上午10:30:41
	 */
	public Result save(ContextInfo cti,SpsMallCustomer customer,String mobile){
		Result result = Result.createResult();
		Integer spId = 0;
		if(cti!=null){
            spId = cti.getOrgId();
            if(spId==null||spId==0){
                throw new BusinessException("用户orgId="+spId);
            }
        }
        customer.setSpId(spId);
        customer.setDr(0);
		if(null!=customer){
			if(null == customer.getCustomerId()){
				//dataMap
				Map<String,String> dataMap = new HashMap<String,String>();
				SysUser customerUser = dealSysUser(cti, customer, mobile,dataMap);
				if(null == customerUser){
					result.setError(dataMap.get("message"));
					result.setSuccess(false);
					return result;
				}
				//处理 客服 默认状态
				dealCustomer(cti, customer);
				customer.setUserId(customerUser.getUserId());
				customer.setCreateBy(cti.getUserId());
				customer.setCreateDt(new Date());
				save(cti, customer);
				//顺序号 保存为跟id相同 modify by lifq 20160613
				customer.setOrderby(customer.getCustomerId());
				update(cti, customer);
				result.setData(dataMap);
				if(dataMap.isEmpty()){
					result.setDataValue("message", "保存成功！");
				}
			}else{
				//dataMap
				Map<String,String> dataMap = new HashMap<String,String>();
				SysUser customerUser = dealSysUser(cti, customer, mobile,dataMap);
				//处理 客服 默认状态
				dealCustomer(cti, customer);
				if(null == customerUser){
					result.setError(dataMap.get("message"));
					result.setSuccess(false);
					return result;
				}
				customer.setUserId(customerUser.getUserId());
				customer.setModifyBy(cti.getUserId());
				customer.setModifyDt(new Date());
				spsMallCustomerDao.update(cti, customer);
				result.setDataValue("message", "保存成功！");
			}
		}
		return result;
	}
	/**
	 * 处理用户表
	 *
	 * @author lifq
	 *
	 * 2016年6月15日  下午3:24:22
	 */
	private SysUser dealSysUser(ContextInfo cti, SpsMallCustomer customer, String mobile,Map<String,String> dataMap) {
		//处理 用户表
		SysUser sysUser = null;
		if(null!=customer.getUserId()){
			SysUser obj = new SysUser();
			obj.setUserId(customer.getUserId());
			sysUser = sysUserService.findByPK(obj);
		}else{
			sysUser = sysUserService.findByMobile(mobile, CMCORPTYPE_SERVICE, cti.getOrgId());
		}
		if(null!=sysUser){
			//查询 该用户是否被其他客服绑定
			SpsMallCustomer customerVo = new SpsMallCustomer();
			if(null!=customer){
				if(null!=customer.getCustomerId()){
					customerVo.setCustomerId(customer.getCustomerId());
				}
			}
			customerVo.setUserId(sysUser.getUserId());
			customerVo.setSpId(cti.getOrgId());
			List<SpsMallCustomer> customers = findAllNotCurrent(customerVo);
			if(null!=customers && customers.size()>0){
				dataMap.put("message", "该手机号已绑定其他客服账号，请输入其他手机号！");
				return null;
			}
			sysUser.setMobile(mobile);
			sysUserService.update(cti, sysUser);
			return sysUser;
		}else{
			//user 查询 系统该手机号是否存在
			SysUser user = sysUserService.findByMobile(mobile, CMCORPTYPE_SERVICE, null);
			if(null!=user){
				dataMap.put("message", "该手机号对应的账户已存在，请输入其他手机号！");
				return null;
			}
			
			SysUser vo = new SysUser();
			vo.setMobile(mobile);
			String pwd = com.xfs.common.util.StringUtils.getRandomSixString();
			vo.setPassword(Md5Util.md5(pwd));
			vo.setOrgId(cti.getOrgId());
			vo.setUserType(CMCORPTYPE_SERVICE);
			vo.setUserName(customer.getCustomerName());
			vo.setEmail(mobile);//账户中心时 用户名 显示该字段
			vo.setEnabled(1);
			vo.setRealName(customer.getCustomerName());
			vo.setCreateBy(cti.getUserId()+"");
			vo.setCreateDt(new Date());
			sysUserService.save(cti, vo);

			/**
			 * 默认服务商角色
			 */
			SysUserRole sysUserRole = new SysUserRole();
			sysUserRole.setUserId(vo.getUserId());
			sysUserRole.setRoleId(SysRole.SAAS_C_SERVCIE);
			sysUserRoleDao.insert(cti, sysUserRole);

			String smsContent = "尊敬的客服，您的帐号已开通，同时为您开通了SAAS平台帐号，请使用手机号登陆，密码为："
				    +pwd+"，请点击http://t.cn/R5Tt2lL 下载app，即刻开启您的客服之旅。【用友薪福社】";
//			if("Y".equals(customer.getDefaultb()) || "Y".equals(customer.getDefaultc())){
//				smsContent = "尊敬的客服，您的帐号已开通，同时为您开通了SAAS平台帐号，请使用手机号登陆，密码为："
//			    +pwd+"，请点击http://t.cn/Rq8pqrs下载app，即刻开启您的客服之旅。【薪福社】";
//			}else{
//				smsContent = "您的客服账号已经开通，快来进入吧！请使用手机号登陆，密码为："+pwd+"【薪福社】";
//			}
			boolean smsresult = false;
			try {
				smsresult =  SmsUtil.sendSms(mobile, smsContent);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(smsresult){
				dataMap.put("message", "账号创建成功，密码已发送到该手机号上！");
			}
			
			return vo;
		}
	}
	/**
	 * 处理 客服表，置默认标识
	 *
	 * @author lifq
	 *
	 * 2016年6月17日  下午5:34:58
	 */
	private void dealCustomer(ContextInfo cti, SpsMallCustomer customer){
		if("Y".equals(customer.getDefaultb())){
			SpsMallCustomer vo = new SpsMallCustomer();
			vo.setSpId(customer.getSpId());
			vo.setDefaultb("Y");
			List<SpsMallCustomer> list = findAll(vo);
			if(null!=list && list.size()>0){
				SpsMallCustomer curVo = list.get(0);
				curVo.setDefaultb("N");
				update(cti, curVo);
			}
		}else{
			customer.setDefaultb("N");
		}
		
		if("Y".equals(customer.getDefaultc())){
			SpsMallCustomer vo = new SpsMallCustomer();
			vo.setSpId(customer.getSpId());
			vo.setDefaultc("Y");
			List<SpsMallCustomer> list = findAll(vo);
			if(null!=list && list.size()>0){
				SpsMallCustomer curVo = list.get(0);
				curVo.setDefaultc("N");
				update(cti, curVo);
			}
		}else{
			customer.setDefaultc("N");
		}
	}
	/**
	 * 上下移动
	 *
	 * @author lifq
	 *
	 * 2016年7月15日  上午10:30:41
	 */
	public Result updateOrderBy(ContextInfo cti,Integer customerId,Integer orderby,Integer customerIdNew,Integer orderbyNew){
		Result result = Result.createResult();
		Integer spId = 0;
        if(cti!=null){
            spId = cti.getOrgId();
            if(spId==null||spId==0){
                throw new BusinessException("用户orgId="+spId);
            }
            SpsMallCustomer vo = new SpsMallCustomer();
        	vo.setCustomerId(customerId);
        	vo.setSpId(spId);
        	SpsMallCustomer customerVo = findByPK(vo);
        	
        	SpsMallCustomer voNew = new SpsMallCustomer();
        	voNew.setCustomerId(customerIdNew);
        	voNew.setSpId(spId);
        	SpsMallCustomer customerVoNew = findByPK(voNew);
        	
        	customerVo.setOrderby(orderbyNew);
        	customerVoNew.setOrderby(orderby);
        	
        	update(cti, customerVo);
        	update(cti, customerVoNew);
        }
		return result;
	}
	
	/**
	 * 查询 客服记录
	 *
	 * @author lifq
	 *
	 * 2016年8月1日  下午4:40:37
	 */
	public List<SpsMallCustomer> findAllNotCurrent(SpsMallCustomer vo){
		List<SpsMallCustomer> datas = spsMallCustomerDao.findAllNotCurrent(vo);
		return datas;
	}
	
	/**
	 * 商铺客服列表
	 *
	 * @param vo
	 * @return
	 */
	public List<SpsMallCustomer> findMallCustomers(SpsMallCustomer vo) {
		return spsMallCustomerDao.findMallCustomers(vo);
	}

	/**
	 * 薪福社运营客服
	 *
	 * @return
     */
	public List<SpsMallCustomer> findBusinessCustomers() {
		Object o = RedisUtil.get(BUSINESS_CUSTOMER_LIST);
		if (o != null && o instanceof List) {
			return (List)o;
		}
		List<SpsMallCustomer> customers = spsMallCustomerDao.findBusinessCustomers();
		if (customers != null && !customers.isEmpty()) {
			RedisUtil.set(BUSINESS_CUSTOMER_LIST, customers, EXPIRE_HOUR); // 一个小时的缓存
		}
		return customers;
	}

	/**
	 * 查询客服的手机号
	 *
	 * @param vo
	 * @return
	 */
	public List<Map<String, Object>> findCustomerMobile(SpsMallCustomer vo) {
		return spsMallCustomerDao.findCustomerMobile(vo);
	}
	
}
