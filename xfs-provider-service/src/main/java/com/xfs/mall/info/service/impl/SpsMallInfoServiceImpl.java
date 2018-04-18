package com.xfs.mall.info.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.mall.info.dao.SpsMallInfoDao;
import com.xfs.mall.info.model.SpsMallInfo;
import com.xfs.mall.info.model.SpsMallServicerange;
import com.xfs.mall.info.model.SpsMallSuccesscase;
import com.xfs.mall.info.service.SpsMallInfoService;
import com.xfs.mall.info.service.SpsMallServicerangeService;
import com.xfs.mall.info.service.SpsMallSuccesscaseService;
import com.xfs.sp.dao.SpServiceDao;
import com.xfs.sp.model.SpService;
import com.xfs.user.model.SysUser;

@Service
public class SpsMallInfoServiceImpl implements SpsMallInfoService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsMallInfoServiceImpl.class);
	
	@Autowired
	private SpsMallInfoDao spsMallInfoDao;
	
	@Autowired
	private SpServiceDao spServiceDao;
	
	@Autowired
	private SpsMallServicerangeService servicerangeService;

	@Autowired
	private SpsMallSuccesscaseService successcaseService;
	
	public int save(ContextInfo cti, SpsMallInfo vo ){
		return spsMallInfoDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsMallInfo vo ){
		return spsMallInfoDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsMallInfo vo ){
		return spsMallInfoDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsMallInfo vo ){
		return spsMallInfoDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsMallInfo vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsMallInfoDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsMallInfo> datas = spsMallInfoDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsMallInfo> findAll(SpsMallInfo vo){
		
		List<SpsMallInfo> datas = spsMallInfoDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/06/07 12:08:18

	//cs
	/**
	 * 商铺首页详情
	 *
	 * @param vo
	 * @return
	 */
	public Map<String, Object> findMallInfo(SpsMallInfo vo) {
		return spsMallInfoDao.findMallInfo(vo);
	}

	/**
	 * 机构评分详情
	 *
	 * @param vo
	 * @return
	 */
	public List<Map<String, Object>> findMallScoreDetai(SpsMallInfo vo) {
		return spsMallInfoDao.findMallScoreDetai(vo);
	}
	
	//bs
	@Override
	public List<SpsMallInfo> findBySpId(SpsMallInfo vo) {
		List<SpsMallInfo> list = spsMallInfoDao.freeFind(vo);
		return list;
	}

	@Override
	public SpsMallInfo findByPK(SpsMallInfo vo) {
		SpsMallInfo spsMallInfo = spsMallInfoDao.findByPK(vo);
		return spsMallInfo;
	}

	@Override
	public Result saveData(ContextInfo cti, SpsMallInfo spsMallInfo, SpService spService, String flag, SysUser sys) {
		Result result = Result.createResult().setSuccess(false);
		Date date = new Date();
		SpService spServices = spServiceDao.findByPK(spService);
		if (null != spsMallInfo) {
			// 提交签约
			if (StringUtils.equals(flag, "Y")) {
				// SaaS提交签约 验证条件
				if (StringUtils.equals(spsMallInfo.getSignSaas(), "Y")
						&& StringUtils.equals(spServices.getServiceState(), "AUDITED")) {
					// SAAS 设置为 SIGNING
					spService.setServiceState("SIGNING");
					// Mall 提交签约 验证条件
					if (StringUtils.equals(spsMallInfo.getSignMall(), "Y")) {
						spService.setEnabledMall("SIGNING");
					}

				}
				// SaaS已签约 验证条件
				if (StringUtils.equals(spsMallInfo.getSignSaas(), "Y")
						&& StringUtils.equals(spServices.getServiceState(), "SIGNED")) {
					// 签约Mall
					if (StringUtils.equals(spsMallInfo.getSignMall(), "Y")) {
						spService.setEnabledMall("SIGNING");
					}
				}
				spService.setModifyBy(sys.getUserId());
				spService.setModifyDt(date);
				spServiceDao.update(cti, spService);
			}
			// 修改
			if (null != spsMallInfo.getId()) {
				// Sa 已经OK MA未提交
				if (StringUtils.equals("Y", spsMallInfo.getSignSaas())
						&& StringUtils.equals("N", spsMallInfo.getSignMall())) {
					/*if (StringUtils.isBlank(spsMallInfo.getCertificate())) {
						result.setError("请添加公司资质证书！");
						return result;
					}*/
				}
				// Sa 已经OK MA已经OK
				if (StringUtils.equals("Y", spsMallInfo.getSignSaas())
						&& StringUtils.equals("Y", spsMallInfo.getSignMall())) {
					/*if (StringUtils.isBlank(spsMallInfo.getCertificate())) {
						result.setError("请添加公司资质证书！");
						return result;
					}*/
					if (StringUtils.isBlank(spService.getLogo() + "")) {
						result.setError("请添加公司LOGO！");
						return result;
					}
					if (StringUtils.isBlank(spsMallInfo.getCompanyIntroduction())) {
						result.setError("请输入公司介绍！");
						return result;
					}
					if (StringUtils.isBlank(spsMallInfo.getServiceIntroduction())) {
						result.setError("请输入公司服务优势！");
						return result;
					}
				}
				spsMallInfo.setModifyBy(sys.getUserId());
				spsMallInfo.setModifyDt(date);
				Integer upd = this.update(cti, spsMallInfo);
				if (0 < upd) {
					result.setSuccess(true);
				} else {
					result.setError("保存失败!请联系管理员!");
				}
			} else {
				// 新增
				if (StringUtils.equals("Y", spsMallInfo.getSignSaas())
						&& StringUtils.equals("N", spsMallInfo.getSignMall())) {
//					if (StringUtils.isBlank(spsMallInfo.getCertificate())) {
//						result.setError("请添加公司资质证书！");
//						return result;
//					}
				}
				if (StringUtils.equals("Y", spsMallInfo.getSignSaas())
						&& StringUtils.equals("Y", spsMallInfo.getSignMall())) {

					/*if (StringUtils.isBlank(spsMallInfo.getCertificate())) {
						result.setError("请添加公司资质证书！");
						return result;
					}*/
					if (StringUtils.isBlank(spService.getLogo() + "")) {
						result.setError("请添加公司LOGO！");
						return result;
					}
					if (StringUtils.isBlank(spsMallInfo.getCompanyIntroduction())) {
						result.setError("请输入公司介绍！");
						return result;
					}
					if (StringUtils.isBlank(spsMallInfo.getServiceIntroduction())) {
						result.setError("请输入公司服务优势！");
						return result;
					}
				}
				spsMallInfo.setCreateBy(sys.getUserId());
				spsMallInfo.setCreateDt(date);
				spsMallInfo.setDr(0);
				Integer ins = this.insert(cti, spsMallInfo);
				if (0 < ins) {
					result.setSuccess(true);

				} else {
					result.setError("新增失败!请联系管理员!");
				}
			}
		} else {
			result.setSuccess(false);
			result.setError("保存失败！请联系管理员！");
		}
		return result;
	}

	/**
	 * 点击取消签约
	 */
	@Override
	public Result cannelsign(ContextInfo cti, SpsMallInfo spsMallInfo, SpService spService) {
		Result result = Result.createResult().setSuccess(false);

		Date date = new Date();

		spsMallInfo.setModifyBy(cti.getUserId());
		spsMallInfo.setModifyDt(date);

		spService.setModifyBy(cti.getUserId());
		spService.setModifyDt(date);

		SpsMallInfo SpsMallInfoed = new SpsMallInfo();
		SpsMallInfoed = this.findByPK(spsMallInfo);
		SpService spServiced = new SpService();
		spServiced = spServiceDao.findByPK(spService);
		Integer upsInfo = 0;
		Integer upsSpSe = 0;
		// SA 签约中 SA MA 都取消
		if (StringUtils.equals(spServiced.getServiceState(), "SIGNING")) {
			spsMallInfo.setSignSaas("N");
			spsMallInfo.setSignMall("N");
			upsInfo = this.update(cti, spsMallInfo);

			spService.setEnabledMall("UNSIGN");
			spService.setServiceState("AUDITED");
			upsSpSe = spServiceDao.update(cti, spService);
			if (upsInfo > 0 && upsSpSe > 0) {
				result.setSuccess(true);
			} else {
				result.setError("提交取消申请失败！");
			}
		}
		// SA 已签约 只取消MA
		if (StringUtils.equals(spServiced.getServiceState(), "SIGNED")) {
			spsMallInfo.setSignMall("N");
			upsInfo = this.update(cti, spsMallInfo);
			spService.setEnabledMall("UNSIGN");
			upsSpSe = spServiceDao.update(cti, spService);
			if (upsInfo > 0 && upsSpSe > 0) {
				result.setSuccess(true);
			} else {
				result.setError("提交取消申请失败！");
			}
		}
		return result;
	}
	
	//sps
	@Override
	public Result saveData(ContextInfo cti, SpsMallInfo spsMallInfo, SpService spService, String flag, SysUser sys, String[] categoryId, String[] cpName, String[] successCase) {
		Result result = Result.createResult().setSuccess(false);
		Date date = new Date();
		SpService spServices = spServiceDao.findByPK(spService);
		if (null != spsMallInfo || spServices == null) {
			// ModifyBy zhangxiyan 服务商商城这边Dr设置成0 状态 2016-06-30
			spsMallInfo.setDr(0);
			// 提交签约
			if (StringUtils.equals(flag, "Y")) {
				// SA提交 && State==AUDITED
				if (StringUtils.equals(spServices.getServiceState(), "AUDITED")
						&& StringUtils.equals(spsMallInfo.getSignSaas(), "Y")) {
					// SAAS 设置为 SIGNING
					spService.setServiceState("SIGNING");
					// Mall 提交签约 验证条件
					if (StringUtils.equals(spsMallInfo.getSignMall(), "Y")) {
						spService.setEnabledMall("SIGNING");
					}
				}
				// SaaS已签约 && signsaas==Y
				if (StringUtils.equals(spServices.getServiceState(), "SIGNED")
						&& StringUtils.equals(spsMallInfo.getSignSaas(), "Y")) {
					// 签约Mall
					if (StringUtils.equals(spsMallInfo.getSignMall(), "Y")) {
						spService.setEnabledMall("SIGNING");
					}
				}
				// 修改
				if (null != spsMallInfo.getId()) {
					// SA 已提交 MA 未提交
					if (StringUtils.equals("Y", spsMallInfo.getSignSaas())
							&& StringUtils.equals("N", spsMallInfo.getSignMall())) {
						/*if (StringUtils.isBlank(spsMallInfo.getHumanCertification())) {
							result.setError("请添加人力资源证书！");
							return result;
						}
						if (StringUtils.isBlank(spsMallInfo.getLaborCertification())) {
							result.setError("请添加劳务派遣证书！");
							return result;
						}
						if (StringUtils.isBlank(spsMallInfo.getCertificate())) {
							result.setError("请添加公司资质证书！");
							return result;
						}*/
					}
					// SA 已提交 MA 已提交
					if (StringUtils.equals("Y", spsMallInfo.getSignSaas())
							&& StringUtils.equals("Y", spsMallInfo.getSignMall())) {
						/*if (StringUtils.isBlank(spsMallInfo.getHumanCertification())) {
							result.setError("请添加人力资源证书！");
							return result;
						}
						if (StringUtils.isBlank(spsMallInfo.getLaborCertification())) {
							result.setError("请添加劳务派遣证书！");
							return result;
						}
						if (StringUtils.isBlank(spsMallInfo.getCertificate())) {
							result.setError("请添加公司资质证书！");
							return result;
						}*/
						if (StringUtils.isBlank(spService.getLogo() + "")) {
							result.setError("请添加公司LOGO！");
							return result;
						}
						if (StringUtils.isBlank(spsMallInfo.getCompanyIntroduction())) {
							result.setError("请输入公司介绍！");
							return result;
						}
						if (StringUtils.isBlank(spsMallInfo.getServiceIntroduction())) {
							result.setError("请输入公司服务优势！");
							return result;
						}
						// 服务范围判断
						if (categoryId == null || categoryId.length == 0) {
							result.setError("请选择服务范围！");
							return result;
						}
					}
					spService.setModifyBy(sys.getUserId());
					spService.setModifyDt(date);
					Integer upsser = spServiceDao.update(cti, spService);// LOGO
																	// shortname

					spsMallInfo.setModifyBy(sys.getUserId());
					spsMallInfo.setModifyDt(date);
					Integer upd = this.update(cti, spsMallInfo);
					if (0 < upsser && 0 < upd) {
						result.setSuccess(true);
					} else {
						result.setError("保存失败!请联系管理员!");
					}
				} else {
					// 新增
					if (StringUtils.equals("Y", spsMallInfo.getSignSaas())
							&& StringUtils.equals("N", spsMallInfo.getSignMall())) {
						/*if (StringUtils.isBlank(spsMallInfo.getHumanCertification())) {
							result.setError("请添加人力资源证书！");
							return result;
						}
						if (StringUtils.isBlank(spsMallInfo.getLaborCertification())) {
							result.setError("请添加劳务派遣证书！");
							return result;
						}
						if (StringUtils.isBlank(spsMallInfo.getCertificate())) {
							result.setError("请添加公司资质证书！");
							return result;
						}*/
					}
					if (StringUtils.equals("Y", spsMallInfo.getSignSaas())
							&& StringUtils.equals("Y", spsMallInfo.getSignMall())) {
						/*if (StringUtils.isBlank(spsMallInfo.getHumanCertification())) {
							result.setError("请添加人力资源证书！");
							return result;
						}
						if (StringUtils.isBlank(spsMallInfo.getLaborCertification())) {
							result.setError("请添加劳务派遣证书！");
							return result;
						}
						if (StringUtils.isBlank(spsMallInfo.getCertificate())) {
							result.setError("请添加公司资质证书！");
							return result;
						}*/
						if (StringUtils.isBlank(spService.getLogo() + "")) {
							result.setError("请添加公司LOGO！");
							return result;
						}
						if (StringUtils.isBlank(spsMallInfo.getCompanyIntroduction())) {
							result.setError("请输入公司介绍！");
							return result;
						}
						if (StringUtils.isBlank(spsMallInfo.getServiceIntroduction())) {
							result.setError("请输入公司服务优势！");
							return result;
						}
						// 服务范围判断
						if (categoryId == null || categoryId.length == 0) {
							result.setError("请选择服务范围！");
							return result;
						}
					}
					spService.setModifyBy(sys.getUserId());
					spService.setModifyDt(date);
					Integer upsser = spServiceDao.update(cti, spService);// LOGO
																	// shortname

					spsMallInfo.setCreateBy(sys.getUserId());
					spsMallInfo.setCreateDt(date);
					spsMallInfo.setDr(0);
					/*if (StringUtils.equals(spsMallInfo.getCertificate(), null)) {
						spsMallInfo.setCertificate("");
					}*/
					Integer ins = this.insert(cti, spsMallInfo);
					if (0 < upsser && 0 < ins) {
						result.setSuccess(true);
					} else {
						result.setError("新增失败!请联系管理员!");
					}
				}
				if (StringUtils.equals("Y", spsMallInfo.getSignSaas())
						&& StringUtils.equals("Y", spsMallInfo.getSignMall())) {
					// 保存服务范围 先删除、再新增
					SpsMallServicerange range = new SpsMallServicerange();
					range.setSpId(spService.getSpId());
					servicerangeService.remove(cti, range);
					for (String categoryid: categoryId) {
						SpsMallServicerange servicerange = new SpsMallServicerange();
						servicerange.setSpId(spService.getSpId());
						servicerange.setCategoryId(Integer.parseInt(categoryid));
						servicerange.setDr(0);
						servicerange.setCreateDt(date);
						servicerangeService.save(cti, servicerange);
					}
					// 保存成功案例 先删除、再新增
					SpsMallSuccesscase scase = new SpsMallSuccesscase();
					scase.setSpId(spService.getSpId());
					successcaseService.remove(cti, scase);
					if (cpName != null && successCase != null) {
						for (int i = 0; i < cpName.length; i++) {
							SpsMallSuccesscase successcase = new SpsMallSuccesscase();
							successcase.setSpId(spService.getSpId());
							successcase.setCpName(cpName[i]);
							successcase.setCaseImg(successCase[i]);
							successcase.setDr(0);
							successcase.setCreateDt(date);
							successcaseService.save(cti, successcase);
						}
					}
				}
			}
			// SA已提交签约 MA暂存 只保存商城信息 只保存 不做校验
			if (StringUtils.equals(flag, "K")) {
				if (spsMallInfo.getId() != null) {
					/*if (StringUtils.equals(spsMallInfo.getCertificate(), null)) {
						spsMallInfo.setCertificate("");
					}*/
					Integer upd = this.update(cti, spsMallInfo);

					spService.setModifyBy(sys.getUserId());
					spService.setModifyDt(date);
					Integer upsser = spServiceDao.update(cti, spService);// LOGO
																	// shortname
					if (0 < upsser && 0 < upd) {
						result.setSuccess(true);
					} else {
						result.setError("保存失败!请联系管理员!");
					}
				} else {
					spService.setModifyBy(sys.getUserId());
					spService.setModifyDt(date);
					Integer upsser = spServiceDao.update(cti, spService);// LOGO
					/*if (StringUtils.equals(spsMallInfo.getCertificate(), null)) {
						spsMallInfo.setCertificate("");
					}*/
					// shortname
					Integer ins = this.insert(cti, spsMallInfo);
					if (0 < upsser && 0 < ins) {
						result.setSuccess(true);
					} else {
						result.setError("新增失败!请联系管理员!");
					}
				}
			}
			// SA未提交签约 MA未提交签约 保存 SA MA信息 只保存不做校验
			if (StringUtils.equals(flag, "N")) {
				spService.setModifyBy(sys.getUserId());
				spService.setModifyDt(date);
				Integer upsser = spServiceDao.update(cti, spService);// LOGO
																// shortname
				if (spsMallInfo.getId() != null) {
					/*if (StringUtils.equals(spsMallInfo.getCertificate(), null)) {
						spsMallInfo.setCertificate("");
					}*/
					Integer upd = this.update(cti, spsMallInfo);
					if (0 < upsser && 0 < upd) {
						result.setSuccess(true);
					} else {
						result.setError("保存失败!请联系管理员!");
					}
				} else {
					/*if (StringUtils.equals(spsMallInfo.getCertificate(), null)) {
						spsMallInfo.setCertificate("");
					}*/
					Integer ins = this.insert(cti, spsMallInfo);
					if (0 < upsser && 0 < ins) {
						result.setSuccess(true);
					} else {
						result.setError("新增失败!请联系管理员!");
					}
				}
			}
			// 保存服务范围
		} else {
			result.setSuccess(false);
			result.setError("保存失败！请联系管理员！");
		}
		return result;
	}

}
