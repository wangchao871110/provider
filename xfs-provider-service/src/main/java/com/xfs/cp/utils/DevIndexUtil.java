package com.xfs.cp.utils;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.StringUtils;
import com.xfs.cp.model.CpBusinessIntroduction;
import com.xfs.cp.model.CpService;
import com.xfs.cp.service.CpBusinessIntroductionService;
import com.xfs.cp.service.CpServiceService;


/**
 * 
 * @class  comments: 发展指数工具类
 * @project  name  : cooperation
 * @author   name  : wangchao 
 * @create   time  : 2016年9月10日 下午6:04:46  
 * @class    name  : com.xfs.cp.util.DevIndexUtil
 * @modify   list  : 修改时间和内容   
 * 2016年9月10日 下午6:04:46 wangchao 创建
 *
 */
public class DevIndexUtil {
	@Autowired
	private CpBusinessIntroductionService cpBusinessIntroductionService;
	
	private CpServiceService cpServiceService; // 协作服务商

	private Double devIndex = 0.0;
	
	public DevIndexUtil(CpServiceService cpServiceService) {
		this.cpServiceService=cpServiceService;
	}

	public Double getDevIndex() {
		return devIndex;
	}

	public void setDevIndex(Double devIndex) {
		this.devIndex = devIndex;
	}

	/**
	 * 
	 * @method comments: 获取发展指数
	 * @author   name  : wangchao  
	 * @create   time  : 2016年9月10日 下午6:09:59 
	 * @param cpId
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月10日 下午6:09:59 wangchao 创建
	 *
	 */
	public void getCpServiceDevIndex(ContextInfo ci,Integer cpId) {
		companyInformation(ci,cpId);
	}

    /**
     * 
     * @method comments: 工商信息验证（40%）
     * @author   name  : wangchao  
     * @create   time  : 2016年9月10日 下午6:13:07 
     * @param cpId
     * @return
     * @throws
     * @modify   list  : 修改时间和内容
     * 2016年9月10日 下午6:13:07 wangchao 创建
     *
     */
    private void companyInformation(ContextInfo ci,Integer cpId) {
    	CpService cpService = new CpService();
    	cpService.setId(cpId);
    	cpService = cpServiceService.getCpServiceById(cpService);

    	//企业名称
    	/*if (!StringUtils.isBlank(cpService.getName())) {
			devIndex += 1;
		}*/
    	//企业简称
		if (!StringUtils.isBlank(cpService.getShortName())) {
			devIndex += 1;
		}
		// 总部地址
		if (!StringUtils.isBlank(cpService.getCpAddress())) {
			devIndex += 2;
		}
		// 公司详细地址
		if (!StringUtils.isBlank(cpService.getAddressDetail())) {
			devIndex += 2;
		}
		// 员工数量
		if (cpService.getEmpNum()!=null) {
			devIndex += 1;
		}
		// 服务人数
		if (cpService.getServiceNum()!=null) {
			devIndex += 1;
		}
		// 座机号
		if (!StringUtils.isBlank(cpService.getTelephone())) {
			devIndex += 1;
		}
		// 联系人手机
		/*if (!StringUtils.isBlank(cpService.getMobile())) {
			devIndex += 1;
		}*/
		// 服务邮箱
		if (!StringUtils.isBlank(cpService.getEmail())) {
			devIndex += 1;
		}
		// 公司传真
		if (!StringUtils.isBlank(cpService.getFax())) {
			devIndex += 1;
		}

		// 公司LOGO
		if (cpService.getServiceLogo()!=null) {
			devIndex += 3;
		}
		// 公司介绍
		if (!StringUtils.isBlank(cpService.getDescription())) {
			devIndex += 3;
		}
		// 服务优势
		if (!StringUtils.isBlank(cpService.getAdvantage())) {
			devIndex += 3;
		}
		// 服务客户类型
		if (!StringUtils.isBlank(cpService.getCustomerType())) {
			devIndex += 2;
		}
		// 资质证书
			//营业执照
			if (cpService.getBusiLicenseFile()!=null) {
				devIndex += 3;
			}
			//人力资源证书
			if (cpService.getDispatchCa()!=null) {
				devIndex += 2;
			}
			//劳务派遣证书
			if (cpService.getHrCa()!=null) {
				devIndex += 2;
			}
			//其他资质证书(上传数有可能超过3张)
			/*if (!StringUtils.isBlank(cpService.getCertificate())) {
				devIndex += cpService.getCertificate().split(",").length>3?3:cpService.getCertificate().split(",").length;
			}*/
		// 官网网址
		if (!StringUtils.isBlank(cpService.getWeburl())) {
			devIndex += 1;
		}

		// 服务地区
		if (!StringUtils.isBlank(cpService.getServiceAreaName()) || !StringUtils.isBlank(cpService.getCpAddress())) {
			devIndex += 3;
		}
		// 主营业务
		if (cpServiceService.getBusinessAById(cpService).size()>0) {
			devIndex += 3;
		}
		// 特色业务
		/*if (cpServiceService.spSpecialBusinessList(cpService).size()>0) {
			devIndex += 2;
		}*/

		// 法人介绍
			//法人照片
			if (cpService.getCeoLogo()!=null) {
				devIndex += 1;
			}
			//法人姓名
			if (!StringUtils.isBlank(cpService.getCeoName())) {
				devIndex += 1;
			}
			//法人简介
			if (!StringUtils.isBlank(cpService.getCeoDescription())) {
				devIndex += 1;
			}
		// 办公环境照片
		if (!StringUtils.isBlank(cpService.getOfficeImg())) {
			String[] officeImgs=cpService.getOfficeImg().split(",");
			if(officeImgs.length>0){
				if(!StringUtils.isBlank(officeImgs[0])){
					devIndex += 1;
				}
			}
			if(officeImgs.length>1){
				if(!StringUtils.isBlank(officeImgs[1])){
					devIndex += 1;
				}
			}
		}


    	cpBusinessIntroduction(cpId);

    	//保存发展指数
    	DecimalFormat df = new DecimalFormat("######0.00");
    	CpService modifyDevIndex = new CpService();
    	modifyDevIndex.setId(cpId);
    	modifyDevIndex.setDevIndex(Double.parseDouble(df.format(devIndex+cpService.getCustomIndex())));//新发展指数
    	modifyDevIndex.setDevIndexOld(cpService.getDevIndex());//上一次的发展指数
    	cpServiceService.update(ci,modifyDevIndex);
    }
    
    /**
     * 业务说明（8%）
     *  @param cpId
     *  @param saasSpId 
     *	@return 			: void 
     *  @createDate  	: 2017年1月9日 下午3:48:21
     *  @author         	: songby 
     *  @version        	: v1.0
     *  @updateDate    	: 2017年1月9日 下午3:48:21
     *  @updateAuthor  :
     */
    public void cpBusinessIntroduction(Integer cpId) {
    	CpBusinessIntroduction cpBusinessIntroduction=new CpBusinessIntroduction();
    	cpBusinessIntroduction.setCpId(cpId);
    	cpBusinessIntroduction=cpBusinessIntroductionService.findByPK(cpBusinessIntroduction);
    	System.out.println(cpBusinessIntroduction.getSocialAddDate());
    	
    	
    	
    	
    	cooperativeRelationshi(cpId);
    }
    
    
    /**
     * 
     * @method comments: 保证金（10%）
     * @author   name  : wangchao  
     * @create   time  : 2016年9月10日 下午6:36:54 
     * @param cpId
     * @param saasSpId
     * @return
     * @throws
     * @modify   list  : 修改时间和内容
     * 2016年9月10日 下午6:36:54 wangchao 创建
     *
     */
    public void cooperativeRelationshi(Integer cpId) {
    	//保证金 10%
    	Double riskMargin = cpServiceService.getRiskMarginOfSpsMallInfo(cpId);
    	if(0<riskMargin && riskMargin<=15000){
    		devIndex+=2;
    	}else if (riskMargin>15000 && riskMargin<=25000) {
    		devIndex+=4;
		}else if (riskMargin>25000 && riskMargin<=35000) {
			devIndex+=6;
		}else if (riskMargin>35000 && riskMargin<=45000) {
			devIndex+=8;
		}else if (riskMargin>45000) {
			devIndex+=10;
		}

    	transaction(cpId);
    }


    /**
     * 
     * @method comments: 平台成交情况（30%）
     * @author   name  : wangchao  
     * @create   time  : 2016年9月10日 下午6:18:28 
     * @param cpId
     * @return
     * @throws
     * @modify   list  : 修改时间和内容
     * 2016年9月10日 下午6:18:28 wangchao 创建
     *
     */
    public void transaction(Integer cpId) {
    	
    	//派单 30% 派单次数/最高派单数*100
    	double rateCreatPackageCount=cpServiceService.getRateCreatPackageCount(cpId);

    	devIndex+=30*0.4*rateCreatPackageCount;

    	//咨询量（服务商-免费电话咨询的个数） 10% [1,2)30分，[2,4)40分，[4,6)50分，[6,10)60分，[10,20)70分，[20，40）80分，[40，60)90分，[60,-)100分
    	Integer cpCustomerPhoneCount=cpServiceService.getCpCustomerPhoneCount(cpId);
    	if(cpCustomerPhoneCount<60){
    		devIndex+=(30*0.1/60)*cpCustomerPhoneCount;
    	}else{
    		devIndex+=30*0.1;
    	}

    	//成交总金额 20% [0,5000）40分，[5000,10000）50分，[10000,20000）60分，[20000,50000）70分，[50000,100000）80分，[100000,200000）90分，[200000,-）100分
    	double transactionPriceSum=cpServiceService.getTransactionPriceByCpId(cpId);
    	if(transactionPriceSum<200000){
    		devIndex+=(30*0.2/200000)*transactionPriceSum;
    	}else{
    		devIndex+=30*0.2;
    	}
    	
    	//接单总数 15% [1,2)30分，[2,4)40分，[4,10）60分，[10,20）70分，[20,50）80分，[50,100）90分，[100,-）100分
    	int reciveOrder=cpServiceService.getReciveOrderByCpId(cpId);
    	if (reciveOrder<100) {
    		devIndex+=(30*0.15/100)*reciveOrder;
    	}else{
    		devIndex+=30*0.15;
    	}

    	//接单成功率 20% 成功接单数/接单数*100
    	double reciveOrderRate=cpServiceService.getReciveOrderRateByCpId(cpId);

    	devIndex+=30*0.15*reciveOrderRate;


    	evaluate(cpId);
    }
    
    /**
     * 
     * @method comments: 服务评价（5%）
     * @author   name  : wangchao  
     * @create   time  : 2016年9月10日 下午6:18:50 
     * @param cpId
     * @return
     * @throws
     * @modify   list  : 修改时间和内容
     * 2016年9月10日 下午6:18:50 wangchao 创建
     *
     */
    public void evaluate(Integer cpId) {
    	
    	//服务效率 20%  评价结果*10
    	double efficiencyNum=cpServiceService.getEfficiencyNumByCpId(cpId);
    	devIndex+=5*0.2*efficiencyNum;

    	//服务态度 20%  评价结果*10
    	double attitudeNum=cpServiceService.getAttitudeNumByCpId(cpId);
    	devIndex+=5*0.2*attitudeNum;
    	
    	//服务能力 20%  评价结果*10
    	double powerNum=cpServiceService.getPowerNumByCpId(cpId);
    	devIndex+=5*0.2*powerNum;
    	
    	//服务专业度 20%  评价结果*10
    	double professionNum=cpServiceService.getProfessionNumByCpId(cpId);
    	devIndex+=5*0.2*professionNum;
    	
    	//投诉情况 20%  评价结果*20（减项）(允许投诉5次，每次减0.4分)
    	int complainNum=cpServiceService.getComplainNumByCpId(cpId);
    	if(complainNum<5){
    		devIndex-=(5*0.2/5)*complainNum;
    	}else{
    		devIndex-=2;
    	}
    	
    	concern(cpId);
    }
    
    /**
     * 
     * @method comments: 被关注度（15%）
     * @author   name  : wangchao  
     * @create   time  : 2016年9月10日 下午6:19:04 
     * @param cpId
     * @return
     * @throws
     * @modify   list  : 修改时间和内容
     * 2016年9月10日 下午6:19:04 wangchao 创建
     *
     */
    public void concern(Integer cpId) {
    	//浏览次数 30% 浏览次数/最高浏览次数*100
    	double rateBrowseNum=cpServiceService.getRateBrowseNumByCpId(cpId);
    	devIndex+=15*0.3*rateBrowseNum;
    	
    	//关注人数 30% 关注人数/最高关注人数*100
    	double rateAttentionNum=cpServiceService.getRateAttentionNumByCpId(cpId);
    	devIndex+=15*0.3*rateAttentionNum;

    	//同业认证 40% 同业认证数/最高认证数*100
    	double rateIndustryNum=cpServiceService.getRateIndustryNumByCpId(cpId);
    	if(rateIndustryNum<60){
    		devIndex+=(15*0.4/60)*rateIndustryNum;
    	}else{
    		devIndex+=15*0.4;
    	}
    	
    }

}

