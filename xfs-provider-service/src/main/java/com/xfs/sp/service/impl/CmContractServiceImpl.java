package com.xfs.sp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.itextpdf.text.pdf.BaseFont;
import com.xfs.base.dao.BsAreaDao;
import com.xfs.base.model.BsArea;
import com.xfs.base.model.BsCompanyInfo;
import com.xfs.base.service.BsAreaService;
import com.xfs.base.service.BsCompanyInfoService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.constant.IStaticVarConstant;
import com.xfs.common.exception.BusinessException;
import com.xfs.common.fadada.util.FddClientUtil;
import com.xfs.common.page.PageModel;
import com.xfs.common.sms.SmsUtil;
import com.xfs.common.util.BusinessCode;
import com.xfs.common.util.Config;
import com.xfs.common.util.StringUtils;
import com.xfs.common.util.TransactionUtil;
import com.xfs.corp.dao.CmCorpDao;
import com.xfs.corp.model.CmCorp;
import com.xfs.corp.service.CmCorpService;
import com.xfs.mall.order.dao.SpsMallOrderDao;
import com.xfs.mall.order.model.SpsMallOrder;
import com.xfs.mall.order.service.SpsMallOrderService;
import com.xfs.mall.product.service.SpsMallProductItemService;
import com.xfs.sp.dao.*;
import com.xfs.sp.dto.ContractDto;
import com.xfs.sp.model.*;
import com.xfs.sp.service.CmContractAttService;
import com.xfs.sp.service.CmContractService;
import com.xfs.sp.service.CmContractSignService;
import com.xfs.sp.service.SpServiceService;
import com.xfs.user.model.SysRole;
import com.xfs.user.service.SysUserRoleService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class CmContractServiceImpl implements CmContractService,IStaticVarConstant {

	private static final Logger log = Logger.getLogger(CmContractServiceImpl.class);

	@Autowired
	private SpsMallOrderDao orderDao;

	@Autowired
	private SpCmRelationDao relationDao;

	@Autowired
	private CmContractAttDao contractAttDao;

	@Autowired
	private CmContractAttitemDao contractAttitemDao;

	@Autowired
	private BsAreaDao areaDao;

	@Autowired
	private SpsSchemeDao schemeDao;

	@Autowired
	private SpsSchemeItemDao schemeItemDao;

	@Autowired
	private SpServiceDao spServiceDao;

	@Autowired
	private CmCorpDao cmCorpDao;
	@Autowired
	private CmContractDao cmContractDao;
	@Autowired
	private BsAreaService areaService;//区域
	@Autowired
	private CmContractAttService contractAttService;// 合同附件
    @Autowired
	private SpsMallProductItemService productItemService;//商品服务项
	@Autowired
	private CmContractAttitemDao cmContractAttItemDao;
	@Autowired
	private CmContractSignService cmContractSignService;
	@Autowired
	private CmCorpService corpService;// 企业
	@Autowired
	private SpServiceService spServiceService;// 服务
	@Autowired
	private SpsMallOrderService orderService;//订单
	@Autowired
	private BsCompanyInfoService bsCompanyInfoService;

	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	public int save(ContextInfo cti, CmContract vo ){
		return cmContractDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  CmContract vo ){
		return cmContractDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  CmContract vo ){
		return cmContractDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  CmContract vo ){
		return cmContractDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, CmContract vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cmContractDao.countFreeFind(vo);
		pm.setTotal(total);
		List<CmContract> datas = cmContractDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<CmContract> findAll(CmContract vo){
		
		List<CmContract> datas = cmContractDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/02 15:23:53
	
	public PageModel cs_findPage(PageInfo pi,CmContract vo) {

        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = cmContractDao.countFreeFind(vo);
        pm.setTotal(total);
        List<CmContract> datas = cmContractDao.freeFind(vo, pageIndex, pageSize);
        pm.setDatas(convert(datas));
        return pm;

    }

	/**
	 * 签署合同
	 *
	 * @param cti
	 * @param contract
	 * @param order
	 * @param result
	 * @return
	 * @throws Exception
	 */
    public Result signContract(ContextInfo cti, CmContract contract, SpsMallOrder order, Result result) throws Exception {
		result = result != null ? result : Result.createResult().setSuccess(false);
		// 更新合同签署状态
		int flag = update(cti, contract);
		if (flag <= 0) {
			result.setMsg("修改合同失败");
			throw new BusinessException("修改合同失败");
		}
		// 更新意向单状态
		flag = orderDao.update(cti, order);
		if (flag <= 0) {
			result.setMsg("意向单合同失败");
			throw new BusinessException("意向单合同失败");
		}
		// 非签约成功，直接返回
		if (order.getOrderState() == null || !order.getOrderState().equals("SIGN")) {
			return result.setSuccess(true);
		}
		// 处理 服务商、企业关系
		SpCmRelation rel = new SpCmRelation();
		rel.setSpId(contract.getSpId());
		rel.setCpId(contract.getCpId());
		rel.setContractType(3); // 三方合同
		List<SpCmRelation> relList = relationDao.freeFind(rel);
		if(null == relList || relList.isEmpty()){
			rel.setSource("MALL");
			flag = relationDao.save(cti, rel);
			if (flag <= 0) {
				result.setMsg("服务机构与企业关系不成功");
				throw new BusinessException("服务机构与企业关系不成功");
			}
		} else {
			rel.setId(relList.get(0).getId());
			relationDao.update(cti, rel);
		}
		// 客服专员
		if (contract.getCollaborator() != null) {
			CmCorp corp = new CmCorp();
			corp.setCpId(contract.getCpId());
			corp.setCollaborator(contract.getCollaborator());
			cmCorpDao.update(cti, corp);
			// 给客服专员增加权限
			sysUserRoleService.addOrEdit(null, contract.getCollaborator(), new Integer[]{SysRole.SAAS_C_SERVCIE});
		}
		// 创建方案
		CmContractAtt contractAtt = new CmContractAtt();
		contractAtt.setContractId(contract.getContractId());
		contractAtt.setDr(0);
		List<CmContractAtt> contractAtts = contractAttDao.freeFind(contractAtt);
		if (contractAtts == null || contractAtts.isEmpty()) {
			return result.setSuccess(true);
		}
		for (CmContractAtt att: contractAtts) {
			BsArea area = new BsArea();
			area.setAreaId(att.getAreaId());
			area = areaDao.findByPK(area);
			if (area == null) {
				continue;
			}
			SpsScheme scheme = new SpsScheme();
			scheme.setSpId(contract.getSpId());
			scheme.setCpId(contract.getCpId());
			scheme.setName(area.getName() + "的方案");
			scheme.setAreaId(att.getAreaId());
			scheme.setSchemeType("DIY"); // 自己做
			scheme.setState("USE"); // 使用
			scheme.setPrice(att.getPrice());
			scheme.setBillType(att.getBillType());
			scheme.setEndDate(att.getEndDate());
			scheme.setBillType(att.getBillDateType());
			scheme.setBillDate(att.getBillDate());
			scheme.setPayDateType(att.getPayDateType());
			scheme.setPayDate(att.getPayDate());
			scheme.setAfterpayRule(att.getAfterpayRule());
			scheme.setIsdefault("N");
			scheme.setIspackage("N");
			scheme.setDr(0);
			flag = schemeDao.insert(cti, scheme);
			if (flag <= 0) {
				continue;
			}
			// 合同附件项目表
			CmContractAttitem contractAttitem = new CmContractAttitem();
			contractAttitem.setContractAttId(att.getContractAttId());
			contractAttitem.setDr(0);
//			List<CmContractAttitem> items = contractAttitemDao.freeFind(contractAttitem);
            // 只获取五险一金的服务项目
            List<CmContractAttitem> items = contractAttitemDao.findInsFundContractAttItem(contractAttitem);
            if (items == null || items.isEmpty()) {
				continue;
			}
			for (CmContractAttitem item: items) {
				SpsSchemeItem schemeItem = new SpsSchemeItem();
				schemeItem.setSchemeId(scheme.getSchemeId());
				schemeItem.setItemId(item.getItemId());
				schemeItem.setFeetype(item.getFeetype());
				schemeItem.setPrice(item.getPrice());
				schemeItem.setDr(0);
				schemeItemDao.save(cti,schemeItem);
			}
		}
		return result.setSuccess(true);
	}

    public List<ContractDto> convert(List<CmContract> list){
        List<ContractDto> dtoList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(list)){
            for(CmContract contract:list){
                ContractDto dto = new ContractDto();
                BeanUtils.copyProperties(contract,dto);

                SpService spService=new SpService();
                spService.setSpId( contract.getSpId());
                SpService spService1=spServiceDao.findByPK(spService);
                if(spService1.getContactor()!=null){
                    dto.setContactor(spService1.getContactor());
                }

                CmCorp cmCorp=new CmCorp();
                cmCorp.setCpId(contract.getCpId());
                CmCorp cmCorp1=cmCorpDao.findByPK(cmCorp);
                dto.setContactPsn(cmCorp1.getContactPsn());

                dtoList.add(dto);
            }
        }
        return dtoList;
    }
	
	/**
	 * 查询 合同信息
	 *
	 * @author lifq
	 *
	 * 2016年8月2日  下午9:02:31
	 */
	public List<Map<String,Object>> findContracts(Integer sp_id,Integer cp_id){
		List<Map<String,Object>> datas = cmContractDao.findContracts(sp_id,cp_id);
        return datas;
	}
	/**
	 * 保存 附件合同
	 *
	 * @author lifq
	 *
	 * 2016年7月29日  下午3:47:04
	 */
	public Result saveContract(ContextInfo cti, CmContract contract){
		Result result = Result.createResult().setSuccess(false);
		contract.setCreateBy(cti.getUserId());
		contract.setCreateDt(new Date());
		int res = insert(cti,contract);
		if(res>0){
			result.setSuccess(true);
		}
		return result;
	}
	/**
	 * 删除合同记录
	 *
	 * @author lifq
	 *
	 * 2016年8月4日  上午9:55:23
	 */
	public Result delContract(ContextInfo cti, Integer contract_id){
		Result result = Result.createResult().setSuccess(false);
		CmContract vo = new CmContract();
		vo.setContractId(contract_id);
		CmContract contract = cmContractDao.findByPK(vo);
		//逻辑删除
		contract.setDr(1);//1: 已删除
		int res = cmContractDao.update(cti,contract);
		if(res>0){
			result.setSuccess(true);
		}
		return result;
	}
	/**
	 * 根据主键查询 实体
	 *
	 * @author lifq
	 *
	 * 2016年8月4日  上午9:58:03
	 */
	public CmContract findByPK(CmContract vo){
		return cmContractDao.findByPK(vo);
	}
	
	@Override
	public Result saveConteact(ContextInfo cti, CmContract contrat,Result result) {
		try {
			if(contrat.getContractId() == null || "".equals(contrat.getContractId())){
	    		contrat.setCreateBy(cti.getUserId());
	    		contrat.setCreateDt(new Date());
	    		contrat.setState("USE");
	    		contrat.setContractState(0);
	    		// 获取甲方信息（企业)cpId
	    		CmCorp cmCorp = corpService.findByPK(contrat.getCpId());
	    		if(null == cmCorp){
	    			result.setError("企业不存在！请确认");
	    	        result.setSuccess(false);
	    	        return result;
	    		}
	    		// 获取丙方信息（服务）spId
	        	SpService spService = spServiceService.findByPk(contrat.getSpId());
	        	if(null == spService){
	    			result.setError("服务方不存在！请确认");
	    	        result.setSuccess(false);
	    	        return result;
	    		}
	    		contrat.setContractName(cmCorp.getCorpName()+"与"+spService.getSpName()+"服务合同");
				// 设置客户经理
				if (cti != null) {
					contrat.setCollaborator(cti.getUserId());
				}
	    		contrat.setDr(0);
	    		cmContractDao.save(cti,contrat);
	    		// 产品区域DeleteByContractId 
	        	List<BsArea> areaList = areaService.findAreaByOrderId(contrat.getOrderId());
	        	for(int i=0;i<areaList.size();i++){
	        		CmContractAtt att = new CmContractAtt();
	        		att.setContractId(contrat.getContractId());
	        		att.setAreaId(areaList.get(i).getAreaId());
	        		att.setDr(0);
	        		contractAttService.save(cti,att);
	        		// 产品区域服务项目
	            	List<Map<String, Object>> list = productItemService.findAllByProductIdAndAreaId(contrat.getProductId(),areaList.get(i).getAreaId());
	            	BigDecimal price = new BigDecimal(0); 
	            	for(int j=0;j<list.size();j++){
	            		Map<String, Object> data = list.get(j);
	            		CmContractAttitem vo = new CmContractAttitem();
	        			vo.setContractAttId(att.getContractAttId());
	        			vo.setItemId((Integer) data.get("item_id"));
	        			vo.setFeetype((String) data.get("feetype"));
	        			vo.setPrice((BigDecimal) data.get("price")); 
	        			vo.setDr(0);
	        			vo.setCreateBy(cti.getUserId());
	        			vo.setCreateDt(new Date());
	        			cmContractAttItemDao.save(cti, vo);
	        			if("PER_MONTH".equals((String) data.get("feetype"))){
	        				price = price.add((BigDecimal) data.get("price"));
	        			}
	            	}
	            	att.setPrice(price);
	            	contractAttService.update(cti,att);
	        	}
	    	}else{
	    		contrat.setModifyBy(cti.getUserId());
	    		contrat.setModifyDt(new Date());
	    		cmContractDao.update(cti,contrat);
	    	}
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.setDataValue("contrat", contrat);
        result.setSuccess(true);
        return result;
	}
	@Override
	public Result startContract(ContextInfo cti,SpsMallOrder order, Integer contractId,Result result,String outputtemplate) {
        try {
        	CmContract contract = new CmContract();
            contract.setContractId(contractId);
            contract = cmContractDao.findByPK(contract);
            // 获取丙方信息（服务）
            SpService spService = spServiceService.findByPk(contract.getSpId());
            if(null == spService.getCustomerNum()){
            	result.setSuccess(false);
            	result.setError("您还没未取得有效的电子签章证书，具体问题请拨打热线电话：4000-355-937");
            	return result;
            }
    		//String template = request.getServletContext().getRealPath(File.separator) + "WEB-INF"
    			//	+ File.separator+ "jsp"+ File.separator+ "contract"+ File.separator+ "contractTemplate.html";
			StringBuffer sb = new StringBuffer();
			sb = getHtml(sb);
            /*String tempStr = "";
            FileInputStream fis = new FileInputStream(template);//读取模块文件
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            while ((tempStr = br.readLine()) != null){
            	sb.append(tempStr) ;
            }
            fis.close();
            br.close();*/
            CmContract contrat = new CmContract();
            contrat.setContractId(contractId);
            contrat = cmContractDao.findByPK(contrat);                 
            // 获取甲方信息（企业)cpId 
        	CmCorp cmCorp = corpService.findByPK(contrat.getCpId());
        	BsCompanyInfo cp= bsCompanyInfoService.queryCompanyInfoAllByName(cmCorp.getCorpName());
        	if(null == cp){
        		result.setSuccess(false);
        		result.setError("该企业不存在，请确认");
    			return result;
        	}
        	JSONObject jsondatacp = JSONObject.parseObject(cp.getContactJson());
        	String templateString = sb.toString();
        	templateString = templateString.replace("${cpName}",cp.getName());
        	templateString = templateString.replace("${cpOperName}",cp.getOper_name());
        	templateString = templateString.replace("${cpAddress}",jsondatacp.get("address").toString());
        	templateString = templateString.replace("${cpMobile}",cmCorp.getMobile());
        	// 获取丙方信息（服务）spId
        	BsCompanyInfo sp = bsCompanyInfoService.queryCompanyInfoAllByName(spService.getSpName());
        	JSONObject jsondatasp = JSONObject.parseObject(sp.getContactJson());
        	templateString = templateString.replace("${spName}",sp.getName());
        	templateString = templateString.replace("${spOperName}",sp.getOper_name());
        	templateString = templateString.replace("${spAddress}",jsondatasp.get("address").toString());
        	templateString = templateString.replace("${spMobile}",spService.getMobile());
    		CmContractAtt contractAtt = new CmContractAtt();
    		contractAtt.setContractId(contractId);
    		// 合同区域
    		List<CmContractAtt> attList = contractAttService.findAll(contractAtt);
    		String dataHtml = "";
    		// 最终显示
    		for(int i=0;i<attList.size();i++){
    			StringBuffer att = new StringBuffer();
    			
    			CmContractAtt a = attList.get(i);
    			BsArea area = new BsArea();
    			area.setAreaId(a.getAreaId());
    			area = areaService.findbyPK(area);
    			if(i == attList.size()-1){
    				att.append("<div style=''><div class='add'>附件"+(i+1)+"--"+area.getName()+"</div>");
    			}else{
    				att.append("<div style='min-height: 1030px;'><div class='add'>附件"+(i+1)+"--"+area.getName()+"</div>");
    			}
    			// 人月
    			StringBuffer monthBuffer = new StringBuffer();
    			// 次
    			StringBuffer once = new StringBuffer();
    			//商品服务项目
            	List<Map<String, Object>> list = cmContractAttItemDao.findAllByContractAttIdAndAreaId(a.getContractAttId(),area.getAreaId());
	            	
            	monthBuffer.append("<table style='width: 100%;border-collapse: collapse;'>");
            	once.append("<table style='width: 100%;border-collapse: collapse;'>");
            	int rowsNumber = 0;
            	BigDecimal price = new BigDecimal(0.00);
				for(int j=0;j<list.size();j++){
					Map<String, Object> data = list.get(j);
					if("PER_MONTH".equals(data.get("feetype"))){
						monthBuffer.append("<tr>");
						monthBuffer.append("<td class='xh'>"+(j+1)+"</td>");
						monthBuffer.append("<td class='items'>"+data.get("item_name")+"</td>");
						monthBuffer.append("<td class='code'>人月</td>");
						if(rowsNumber == 0){
							monthBuffer.append("<td class='price'  rowspan='${rowspanprice}'>${price}元</td>");
						}
						price = price.add(new BigDecimal(String.valueOf(data.get("price"))));
						monthBuffer.append("</tr>");
						rowsNumber = rowsNumber+1;
					}else{
						once.append("<tr>");
						once.append("<td class='xh'>"+(j+1)+"</td>");
						once.append("<td class='items'>"+data.get("item_name")+"</td>");
						once.append("<td class='code'>次</td>");
						once.append("<td class='price'>"+data.get("price")+"元</td>");
						once.append("</tr>");
					}
				}
				monthBuffer.append("</table>");
				String month = monthBuffer.toString().replace("${rowspanprice}", String.valueOf(rowsNumber)).replace("${price}", String.valueOf(price));
				once.append("</table>");
				
				att.append("<div class='col-L'>");
        		att.append("	<p class='table-title'>服务内容</p>");
        		att.append("	<table class='table' style='width: 100%;border-collapse: collapse;'>");
        		att.append("		<thead><tr>");
        		att.append("			<th class='xh'>序号</th>");
        		att.append("			<th class='items'>服务项目</th>");
        		att.append("			<th class='code'>计费方式</th>");
        		att.append("			<th class='price'>价格</th>");
        		att.append("		</tr></thead>");
        		att.append("		<tbody>");
        		if(!"<table></table>".equals(month.toString())){
        			att.append("			<tr><td colspan='5'>"+month+"</td></tr>");
        		}
        		if(!"<table></table>".equals(once.toString())){
        			att.append("			<tr><td colspan='5'>"+once+"</td></tr>");
        		}
        		att.append("		</tbody>");
        		att.append("	</table>");
        		att.append("</div>");
        		att.append("<div class='col-R'>");
        		att.append("	<p class='table-title'>服务约定</p>");
        		/*att.append("	<div class='dan-row'>");
        		att.append("		<span>增减规则：</span>");
        		att.append("	</div>");*/
        		att.append("	<div class='dan-row'>");
        		att.append("		<span>当月申报业务截止日期：</span>"+(a.getEndDate()==null?"":a.getEndDate())+"号");
        		att.append("	</div>");
        		
        		att.append("	<div class='dan-row'>");
        		/*if("CURRENT".equals(a.getBillDateType())){
        			att.append("		<span>账单日：</span>当月"+(a.getBillDate()==null?"":a.getBillDate())+"号");
        		}else{
        			att.append("		<span>账单日：</span>次月"+(a.getBillDate()==null?"":a.getBillDate())+"号");
        		}*/
        		att.append("		<span>账单日：</span>"+(a.getBillDate()==null?"":a.getBillDate())+"号");
        		att.append("	</div>");
        		att.append("	<div class='dan-row'>");
        		if("MONTH".equals(a.getBillType())){
        			att.append("		<span>账单类型：</span>月报账单");
        		}else{
        			att.append("		<span>账单类型：</span>预付款账单号");
        		}
        		att.append("	</div>");
        		att.append("	<div class='dan-row'>");
        		/*if("CURRENT".equals(a.getBillType())){
        			att.append("		<span>付款日：</span>当月"+(a.getPayDate()==null?"":a.getPayDate())+"号");
        		}else{
        			att.append("		<span>付款日：</span>次月"+(a.getPayDate()==null?"":a.getPayDate())+"号");
        		} */
        		att.append("		<span>付款日：</span>"+(a.getPayDate()==null?"":a.getPayDate())+"号");
        		att.append("	</div>");
        		/*att.append("	<div class='dan-row'>");
        		att.append("		<span>补缴规则：</span>");
        		att.append("	</div>");*/
        		att.append("	<div class='dan-row' style='word-wrap:break-word; width:300px;word-break: break-all;'>");
        		att.append("		<span>备注：</span>"+(a.getMemo()==null?"":a.getMemo())+"");
        		att.append("	</div>");
        		att.append("</div>");
        		att.append("</div>");
        		dataHtml += att.toString();
			}
			org.jsoup.nodes.Document doc = Jsoup.parse(dataHtml);
    		templateString = templateString.replace("${contractAtt}",doc.toString());
			File f = new File(outputtemplate+"tempalte.html");
			if (!f.exists()) {
				f.createNewFile();
			}
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f),"UTF-8");
			BufferedWriter writer=new BufferedWriter(write); 
			writer.write(templateString);
			writer.close();
			String inputFile = outputtemplate+"tempalte.html";
			String outputFile = outputtemplate+contrat.getContractName()+".pdf";
			String url = new File(inputFile).toURI().toURL().toString();  
	        OutputStream os = new FileOutputStream(outputFile);  
	        ITextRenderer renderer = new ITextRenderer();
	        renderer.setDocument(url);
	        // 解决中文问题
	        ITextFontResolver fontResolver = renderer.getFontResolver();
	        fontResolver.addFont(outputtemplate+"/WEB-INF/SIMSUN.TTC",BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
	        renderer.layout();
	        renderer.createPDF(os);
	        os.close();  
        	//String url = "/work/薪福社人力资源平台社保服务三方协议(2016-8-2).docx";
        	result = fadada(cti,result,outputFile,inputFile,contract,spService.getCustomerNum());
    	} catch (Exception e) {
    		e.printStackTrace();
    		log.error(e);
    		result.setError("发起失败");
    		return result;
    	}
        return result;
	}

	/**
	 * 获取合同模板HTML
	 *
	 * @param contract
	 * @return
	 */
	public String getContractTemplateHtml(CmContract contract) {
		String templateHtml = "";
		contract = cmContractDao.findByPK(contract);
		// 获取丙方信息（服务）
		SpService spService = spServiceService.findByPk(contract.getSpId());
		if (spService == null) {
			return "服务机构不存在";
		}
		CmCorp cmCorp = corpService.findByPK(contract.getCpId());
		if (cmCorp == null) {
			return "企业信息不存在";
		}
		templateHtml = getHtml(new StringBuffer()).toString();
		BsCompanyInfo sp = bsCompanyInfoService.queryCompanyInfoAllByName(spService.getSpName());
		templateHtml = templateHtml.replace("${spName}", sp != null ? sp.getName() : spService.getSpName());
		templateHtml = templateHtml.replace("${spOperName}", sp != null ? sp.getOper_name() : "");
		templateHtml = templateHtml.replace("${spMobile}", spService.getMobile());
		if (sp != null) {
			JSONObject jsondatasp = JSONObject.parseObject(sp.getContactJson());
			templateHtml = templateHtml.replace("${spAddress}",
					jsondatasp != null && jsondatasp.get("address") != null ? jsondatasp.get("address").toString() : "");
		} else {
			templateHtml = templateHtml.replace("${spAddress}", "");
		}
		BsCompanyInfo cp = bsCompanyInfoService.queryCompanyInfoAllByName(cmCorp.getCorpName());
		templateHtml = templateHtml.replace("${cpName}", cp != null ? cp.getName() : cmCorp.getCorpName());
		templateHtml = templateHtml.replace("${cpOperName}", cp != null ? cp.getOper_name() : "");
		templateHtml = templateHtml.replace("${cpMobile}",cmCorp.getMobile());
		if (cp != null) {
			JSONObject jsondatacp = JSONObject.parseObject(cp.getContactJson());
			templateHtml = templateHtml.replace("${cpAddress}",
					jsondatacp != null && jsondatacp.get("address") != null ? jsondatacp.get("address").toString() : "");
		} else {
			templateHtml = templateHtml.replace("${cpAddress}", "");
		}
		// 合同区域
		CmContractAtt contractAtt = new CmContractAtt();
		contractAtt.setContractId(contract.getContractId());
		List<CmContractAtt> attList = contractAttService.findAll(contractAtt);
		String attHtml = "";
		for(int i = 0; i < attList.size(); i++) {
			CmContractAtt a = attList.get(i);
			BsArea area = new BsArea();
			area.setAreaId(a.getAreaId());
			area = areaService.findbyPK(area);
			// 人月
			StringBuffer monthSb = new StringBuffer("<table style='width: 100%; border-collapse: collapse;'>");
			// 次
			StringBuffer onceSb = new StringBuffer("<table style='width: 100%; border-collapse: collapse;'>");
			//商品服务项目
			List<Map<String, Object>> list = cmContractAttItemDao.findAllByContractAttIdAndAreaId(a.getContractAttId(), area.getAreaId());
			int rowsNumber = 0;
			BigDecimal price = new BigDecimal(0.00);
			for (int j = 0; j < list.size(); j++) {
				Map<String, Object> data = list.get(j);
				if("PER_MONTH".equals(data.get("feetype"))){
					monthSb.append("<tr>");
					monthSb.append("	<td class='xh'>").append(j + 1).append("</td>");
					monthSb.append("	<td class='items'>").append(data.get("item_name")).append("</td>");
					monthSb.append("	<td class='code'>人月</td>");
					if(rowsNumber == 0){
						monthSb.append("	<td class='price' rowspan='${rowspanprice}'>${price}元</td>");
					}
					price = price.add(new BigDecimal(String.valueOf(data.get("price"))));
					monthSb.append("</tr>");
					rowsNumber = rowsNumber + 1;
				} else {
					onceSb.append("<tr>");
					onceSb.append("	<td class='xh'>").append(j+1).append("</td>");
					onceSb.append("	<td class='items'>").append(data.get("item_name")).append("</td>");
					onceSb.append("	<td class='code'>次</td>");
					onceSb.append("	<td class='price'>").append(data.get("price")).append("元</td>");
					onceSb.append("</tr>");
				}
			}
			monthSb.append("</table>");
			String monthHtml = monthSb.toString().replace("${rowspanprice}", String.valueOf(rowsNumber));
            monthHtml = monthHtml.replace("${price}", String.valueOf(price));
			onceSb.append("</table>");

			StringBuffer attSb = new StringBuffer();
			if(i == attList.size() - 1){
				attSb.append("<div><div class='add'>附件").append(i + 1).append("--").append(area.getName()).append("</div>");
			} else {
				attSb.append("<div style='min-height: 1030px;'><div class='add'>附件").append(i + 1).append("--").append(area.getName()).append("</div>");
			}
			attSb.append("<div class='col-L'>");
			attSb.append("	<p class='table-title'>服务内容</p>");
			attSb.append("	<table class='table' style='width: 100%;border-collapse: collapse;'>");
			attSb.append("		<thead><tr>");
			attSb.append("			<th class='xh'>序号</th>");
			attSb.append("			<th class='items'>服务项目</th>");
			attSb.append("			<th class='code'>计费方式</th>");
			attSb.append("			<th class='price'>价格</th>");
			attSb.append("		</tr></thead>");
			attSb.append("		<tbody>");
			if (!"<table></table>".equals(monthHtml)) {
				attSb.append("			<tr><td colspan='5'>"+monthHtml+"</td></tr>");
			}
			if (!"<table></table>".equals(onceSb.toString())) {
				attSb.append("			<tr><td colspan='5'>"+onceSb+"</td></tr>");
			}
			attSb.append("		</tbody>");
			attSb.append("	</table>");
			attSb.append("</div>");
			attSb.append("<div class='col-R'>");
			attSb.append("	<p class='table-title'>服务约定</p>");
			attSb.append("	<div class='dan-row'>");
			attSb.append("		<span>当月申报业务截止日期：</span>"+(a.getEndDate() == null ? "" : a.getEndDate()) + "号");
			attSb.append("	</div>");
			attSb.append("	<div class='dan-row'>");
			attSb.append("		<span>账单日：</span>"+(a.getBillDate() == null ? "" : a.getBillDate()) + "号");
			attSb.append("	</div>");
			attSb.append("	<div class='dan-row'>");
			if("MONTH".equals(a.getBillType())){
				attSb.append("		<span>账单类型：</span>月报账单");
			}else{
				attSb.append("		<span>账单类型：</span>预付款账单号");
			}
			attSb.append("	</div>");
			attSb.append("	<div class='dan-row'>");
			attSb.append("		<span>付款日：</span>" + (a.getPayDate() == null ? "" : a.getPayDate()) + "号");
			attSb.append("	</div>");
			attSb.append("	<div class='dan-row' style='word-wrap:break-word; width:300px;word-break: break-all;'>");
			attSb.append("		<span>备注：</span>"+(a.getMemo() == null ? "" : a.getMemo()) + "");
			attSb.append("	</div>");
			attSb.append("</div>");
			attSb.append("</div>");
			attHtml += attSb.toString();
		}
		org.jsoup.nodes.Document doc = Jsoup.parse(attHtml);
		templateHtml = templateHtml.replace("${contractAtt}", doc.toString());
		return templateHtml;
	}

	/**
	 * 合同模板
	 * @param sb
	 * @return
	 */
	private StringBuffer getHtml(StringBuffer sb){
		sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"> ");   
		sb.append("<html xmlns='http://www.w3.org/1999/xhtml'>");
		sb.append("<head> ");
		sb.append("<title>要生成的合同文件</title>  ");
		sb.append("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />  ");
		sb.append("<style mce_bogus='1' type='text/css'>");
		sb.append("body {");
		sb.append("font-family: SimSun;");
		sb.append("background: none;");
		sb.append("margin-left: auto;");
		sb.append("margin-right: auto;");
		sb.append("}");
		sb.append("body, html, div, p {");
		sb.append("font-size: 14px;");
		sb.append("margin: 0px;");
		sb.append("padding: 0px;");
		sb.append("}");
		
		sb.append(".pull-left {");
		sb.append("float: left;");
		sb.append("}");
		
		sb.append(".pull-right {");
		sb.append("float: right;");
		sb.append("}");
		
		sb.append(".gray-mark {");
		sb.append("clear: both;");
		sb.append("height: auto;");
		sb.append("width: auto;");
		sb.append("}");
		sb.append(".clearfix {");
		sb.append("display: table;");
		sb.append("}");
		sb.append(".clearfix:after {");
		sb.append("content: '';");
		sb.append("}");
		
		sb.append(".col-L {");
		sb.append("width: 40%;");
		sb.append("min-height: 350px;");
		sb.append("background-color: #fff;");
		sb.append("float: left;");
		sb.append("padding: 1% 4%;");
		sb.append("margin-left: 2%;");
		sb.append("}");
		
		sb.append(".table-title {");
		sb.append("font-size: 15px;");
		sb.append("color: #3c3c3c;");
		sb.append("padding-bottom: 12px;");
		sb.append("border-bottom: 1px solid #e8e8e8;");
		sb.append("}");
		
		sb.append(".table tr {");
		sb.append("height: 42px;");
		sb.append("}");
		
		sb.append(".table tbody tr {");
		sb.append("background-color: #fff;");
		sb.append("}");
		
		sb.append(".xh {");
		sb.append("width: 10%;");
		sb.append("text-align: center;");
		sb.append("border-bottom: 1px solid #e8e8e8;");
		sb.append("}");
		
		sb.append(".items {");
		sb.append("width: 30%;");
		sb.append("text-align: center;");
		sb.append("border-bottom: 1px solid #e8e8e8;");
		sb.append("}");
		
		sb.append(".code {");
		sb.append("width: 30%;");
		sb.append("text-align: center;");
		sb.append("border-bottom: 1px solid #e8e8e8;");
		sb.append("}");
		
		sb.append(".price {");
		sb.append("width: 30%;");
		sb.append("text-align: center;");
		sb.append("border-bottom: 1px solid #e8e8e8;");
		sb.append("}");
		
		sb.append(".col-R {");
		sb.append("display: inline-block;");
		sb.append("width: 40%;");
		sb.append("min-height: 350px;");
		sb.append("background-color: #fff;");
		sb.append("/* float: right; */");
		sb.append("padding: 1% 4%;");
		sb.append("}");
		
		sb.append(".dan-row {");
		sb.append("font-size: 14px;");
		sb.append("line-height: 42px;");
		sb.append("position: relative;");
		sb.append("border-bottom: 1px solid #e8e8e8;");
		sb.append("}");
		
		sb.append(".dan-row p {");
		sb.append("display: inline;");
		sb.append("font-style: normal;");
		sb.append("position: absolute;");
		sb.append("right: 45%;");
		sb.append("}");
		
		sb.append(".title-buttons {");
		sb.append("clear: both;");
		sb.append("}");
		
		sb.append(".no {");
		sb.append("border-bottom: none;");
		sb.append("}");
		
		sb.append(".tab {");
		sb.append("text-indent: 2em;");
		sb.append("font-size: 14px;");
		sb.append("}");
		
		sb.append(".add {");
		sb.append("margin: 0 60px;");
		sb.append("width: 89%;");
		sb.append("line-height: 24px;");
		sb.append("background-color: #ccc;");
		sb.append("padding-left: 20px;");
		sb.append("}");
		
		sb.append(".table thead {");
		sb.append("background-color: #f8f8f8;");
		sb.append("}");
		sb.append(".yin{");
		sb.append("margin-top: 40px;");
		sb.append("}");
		sb.append(".yin div{");
		sb.append("padding-top: 40px;");
		sb.append(" }");
		sb.append(".yin h6{");
		sb.append("font-size: 18px;");
		sb.append(" line-height: 34px;");
		sb.append("-webkit-margin-before: 0;");
		sb.append("-webkit-margin-after: 0;");
		sb.append("}");
		sb.append(".yin h6 small{");
		sb.append("font-weight: normal;");
		sb.append("font-size: 6px;");
		sb.append("transform:scale(0.6);");
		sb.append("}");
		sb.append(".cen{");
		sb.append("text-align: center;");
		sb.append("}");
		sb.append(".cen span{");
		sb.append("font-style: normal;");
		sb.append("font-weight: normal;");
		sb.append(" }");
		sb.append("</style>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<div class='gray-mark' style='min-height: 3100px;'>");
		sb.append("<div class='sps-ui-box' style='padding-top: 20px;margin: 0px 20px 0;'>");
		sb.append("<div class='sps-filter' style='padding: 20px 20px; margin: 0 20px 20px 20px;'>");
		sb.append("    <div style='text-align: center;font-size: 20px;'>用友薪福社人力资源平台</div>");
		sb.append("    <div style='text-align: center;font-size: 20px;'>社保服务三方协议</div>");
		
		sb.append("    <div style='font-size: 18px; line-height:20px;'>");
		sb.append("       <div>甲 方：${cpName}</div>");
		sb.append("       <div>法定代表人：${cpOperName}</div>");
		sb.append(" <div>地址：${cpAddress}</div>");
		sb.append("<div>联系电话：${cpMobile}</div>");
			            
		sb.append(" <div>乙 方：北京用友薪福社云科技有限公司</div>");
		sb.append(" <div>法定代表人：王文京</div>");
		sb.append(" <div>地址：北京市海淀区北清路68号院B座2层101-210室</div>");
		sb.append("<div>联系电话：010-84922353</div>");
		
		sb.append("<div>丙 方：${spName}</div>");
		sb.append("<div>法定代表人：${spOperName}</div>");
		sb.append("<div>地址：${spAddress}</div>");
		sb.append("<div>联系电话：${spMobile}</div>");
		
		sb.append("<div>本协议是甲、乙、丙三方之间就向甲方提供【社保/住房公积金代缴】居间服务所订立的契约。");
		sb.append(" 当甲方在用友薪福社人力资源平台选择本协议所约定的服务，并成功支付服务费后，");
		sb.append(" 本协议即构成对双方有约束力的法律文件。 </div>");
		sb.append(" <div>1.协议主体</div>");
		sb.append("<div class='tab'>1.1“甲方”指客户，如无特别约定，本合同中“甲方”包括甲方及甲方的关联公司。");
		sb.append("   甲方可以为甲方及其关联公司委托服务，甲方承认其关联公司履行本合同的所有责任由甲方承担。</div>");
		sb.append("<div class='tab'>1.2“乙方“指用友薪福社人力资源平台（下称“用友薪福社平台”）的运营方“北京用友薪福社云");
		sb.append("	科技有限公司”。</div>");
		sb.append("<div class='tab'>1.3“丙方”指经甲方在用友薪福社平台上选定的具有合法资质、信誉良好的人力资源服务机构");
		sb.append("    ，如无特别约定，本合同中“丙方”包括丙方及丙方的关联公司、签约服务供应商。丙方可以通");
		sb.append("    过丙方的关联公司、签约服务供应商为甲方及其关联公司提供服务，丙方承认其关联公司、签约");
		sb.append("    服务供应商履行本合同的所有责任由丙方承担。</div>");
		        
		sb.append(" <div>2. 服务模式 </div>");
		sb.append("<div class='tab'>2.1 乙方居间介绍丙方为甲方提供社保/住房公积金代缴服务，包括但不限于社会保险/住房");
		sb.append("    公积金下文中将“住房公积金”简称为“公积金”）代理、相关政策咨询等服务，并收取相关费用。");
		sb.append("    具体服务项目和收费标准以丙方在用友薪福社平台商城页面所展示的服务内容为准。 </div>");
		sb.append(" <div class='tab'>2.2 乙方有权通过用友薪福社平台替丙方先行向甲方收取社保/住房公积金官费与服务费，上述");
		sb.append("    费用（扣除乙方收取的相应费用后）由乙方按乙、丙双方另行约定的付款期限支付给丙方。 </div>");
		sb.append("<div class='tab'>2.3 为了更好地为甲方提供服务，乙方有义务监督丙方的服务，确保丙方按照用友薪福社平台上");
		sb.append("    公示的规则或者本协议约定的要求完成相应的服务。  </div>");
		
		sb.append("<div>3. 甲方的权利义务 </div>");
		sb.append("<div class='tab'>3.1 甲方应通过用友薪福社平台选定具有合法资质的社保服务商作为本协议丙方提供代缴社保");
		sb.append("    /住房公积金服务。</div>");
		sb.append("<div class='tab'>3.2 甲方有权随时了解社保/住房公积金代缴事项的实际进度。 </div>");
		sb.append("<div class='tab'>3.3 甲方不得要求乙方及丙方从事任何违反法律法规的事宜。 </div>");
		sb.append("<div class='tab'>3.4 甲方应积极配合丙方工作，及时向丙方提供办理业务所需要的材料和相关信息等，否则");
		sb.append("    由此产生的不利后果由甲方承担，丙方不承担任何责任。 </div>");
		sb.append("<div class='tab'>3.5 甲方应当及时向丙方提供代理服务所需有效的信息资料，并按约定时间将委托服务");
		sb.append("    事项的应缴费用支付给方，否则如果造成代办事项贻误，由甲方自行承担相应责任。</div> ");
		sb.append("<div class='tab'>3.6 甲方应保证向乙方及丙方所提供证据材料的真实合法性，如因甲方提供虚假证据材料");
		sb.append("    等原因导致本协议不能履行，乙方及丙方不承担任何责任。 </div>");
		sb.append("<div class='tab'>3.7 甲方须按社保/公积金业务主管政府机关规定的标准缴纳社保/住房公积金，主管机关");
		sb.append("    规定的标准发生变化时，缴纳的标准也随之变化。 </div>");
		sb.append("<div class='tab'>3.8 如因甲方自身过错导致本协议项下约定的服务事项不能执行、完成，乙方及丙方不承担任何责任。</div> ");
		sb.append("<div class='tab'>3.9 为了提高社保/住房公积金代缴服务的效率，甲方同意办理过程中文件提交、参保人数的数");
		sb.append("    据归集等事宜应通过用友薪福社平台来进行。 </div>");
		sb.append("<div class='tab'>3.10 如果甲方因自身需求需要停止当月服务，则必须提前以书面形式告知乙方及丙方详情。");
		sb.append("    三方确认无误后，代理服务方可终止。 </div>");
		sb.append("<div class='tab'>3.11 甲方与丙方是委托与代理的关系，不具有行政隶属关系，甲方人员与丙方所在单位不存在任何劳动关系。</div> ");
		sb.append("<div class='tab'>3.12 在甲方在用友薪福社平台选择丙方提供社保/公积金代缴服务时，需事先与丙方沟通具体的费");
		sb.append("    用交纳截止时间，如甲方已在上述截止时间前于用友薪福社平台支付了代缴社保/公积金的服务费以");
		sb.append("    及社保/公积金的官方费用，则当月丙方即为甲方办理代缴业务。如果甲方在与丙方沟通确定的");
		sb.append("    每月截止日期之后完成上述服务费及官费的支付，则乙方及丙方不承诺当月代理服务成功，由此");
		sb.append("    可能给甲方造成的损失，需要由甲方自行承担。 </div>");
		sb.append("<div class='tab'>3.13 丙方为甲方提供正常的社保/公积金代缴服务，鉴于丙方或乙方与甲方员工不存在任何");
		sb.append("    	形式的劳动关系，丙方不提供或出具任何与甲方员工存在劳动关系的证明，如：劳动合同、");
		sb.append("    收入证明、离职证明等。 </div>");
		sb.append("<div class='tab'>3.14 丙方与甲方不存在任何劳动关系，因此丙方不为甲方承担任何雇主责任。 </div>");
		sb.append("<div class='tab'>3.15 如甲方员工满足特定社保/公积金待遇申领条件，丙方可以协助甲方完成相");
		sb.append("    关事务性工作，但丙方不垫付任何费用，也不承担任何雇主责任。</div>");
			                            
		sb.append("<div>4. 乙方的权利义务 </div>");
		sb.append("<div class='tab'>4.1 乙方有义务以谨慎、勤勉、尽职的原则和合法途径进行社保/公积金代缴的居间服务，");
		sb.append("    并最大限度的维护甲方的合法权益。 </div>");
		sb.append(" <div class='tab'>4.2 乙方有权替丙方向甲方收取服务费和服务所需官方费用。 </div>");
		sb.append("<div class='tab'>4.3 乙方有义务要求丙方及时准确地根据甲方所购买的服务内容为甲方缴纳社会保险/");
		sb.append("    公积金，办理其它委托的服务项目，并维护甲方的利益。</div>");
		sb.append("<div class='tab'>4.4 乙方应督促丙方制定相关的工作流程和程序，同时指导甲方配合丙方进行相应的操作。 </div>");
		sb.append("<div class='tab'>4.5 当丙方严重侵犯甲方的合法权益，或者违反用友薪福社平台的服务承诺时，甲方有权终止本协议。 </div>");
		sb.append("<div class='tab'>4.6 如国家规定的社会保障政策进行调整，乙方与丙方应及时以书面形式将政策调整情况");
		sb.append("    以及相关服务项目费用调整情况通知甲方，以便甲方及时以书面形式予以确认，");
		sb.append("    此确认文件为本协议不可分割部分。 </div>");
		sb.append("<div class='tab'>4.7 如因丙方工作失误导致未能或无法按照本协议约定的内容完成社保/公积金代");
		sb.append("    缴服务，丙方有义务向甲方退还已收取的失误订单所对应的全部费用，如果丙方不予");
		sb.append("    退还，乙方有义务协助甲方向丙方进行追偿。 </div>");
		sb.append("<div class='tab'>4.8 社保、公积金代缴居间服务费用会随时调整，甲方支付的服务费用以下单时丙方");
		sb.append("    在用友薪福社平台商城页面确定的数额为准。 </div>");
		
		sb.append("<div>5. 费用支付 </div>");
		sb.append("<div class='tab'>5.1 甲方在用友薪福社平台购买的丙方社保/公积金代缴服务须通过用友薪福社平台");
		sb.append("    所提供的支付方式（网银、汇款或其他第三方支付工具）进行支付，费用标准以丙方在");
		sb.append("    用友薪福社平台商城页面确定的价格为准。甲方应在收到丙方发送的付款账单后的___个工");
		sb.append("    作日内将全额款项支付至乙方账户，上述费用（扣除乙方收取的相应费用后）由乙方按");
		sb.append("    乙、丙双方另行约定的付款期限支付给丙方，乙方应向甲方开具等额合格发票。</div>");
		sb.append("<div class='tab'>5.2 乙方与丙方均不垫付费用（包括服务费与官费）。如甲方未按约偿付造成代办");
		sb.append("    事项贻误，由甲方自行承担由此导致的任何责任和损失。</div>");
		sb.append("<div class='tab'>5.3 乙方银行账户信息：</div>");
		sb.append("<div>银行账号：北京用友薪福社云科技有限公司       </div>  ")                    ;
		sb.append("<div>银行户名：招商银行清华园支行          </div>    ")     ;         
		sb.append("<div>开 户 行：110920736010802           </div> ")      ;           
		
		sb.append("<div>6. 合同期限</div>");
		sb.append("<div>本协议有效期自三方盖章之日起壹（1）年内有效。协议期满前壹个月内，甲、乙、");
		sb.append("    丙任一方未提出书面异议的，本协议期满后自动顺延壹年，依此类推。</div>");
		
		sb.append(" <div>7. 保密条款 </div>");
		sb.append("<div>乙方及丙方会对与甲方合作中了解到的相关保密信息承担保密义务，保密期限不限于本服务期限，");
		sb.append("    不因本服务的终止或解除而失效。 </div>");
		
		sb.append("<div>8. 争议解决 </div>");
		sb.append("<div>基于本协议的任何争议，甲、乙、丙三方协商解决；协商不成的，");
		sb.append("    任一方均可向乙方所在地人民法院提起诉讼。</div>");
		
		sb.append("<div>9. 其他 </div>");
		sb.append("<div class='tab'>9.1 本协议自三方签字盖章之日起生效，未尽事宜可以另行约定补充协议，");
		sb.append("    补充协议与本协议具有同等法律效力。本协议一式叁份，甲乙丙三方各执壹份。 </div>");
		sb.append("<div class='tab'>9.2 本协议履行过程中，任何“通知”、“告知”、“确认”、“同意”、");
		sb.append("    “服务请求”均应通过书面形式作出，“书面形式”限于任一方以中文形式通过用友薪福社平");
		sb.append("    台提交或通过甲方指定联系人的企业邮箱（域名后缀应与公司官方网站网址一致）");
		sb.append("    发送电子邮件、信函（邮政、快递或专人派送）。</div>");
		sb.append(" </div>");
		sb.append("<div style='margin-top:15px;'>");
		sb.append("（以下无正文）");
		sb.append("</div>");
		sb.append("<div class='yin'>");
		sb.append(" <div>");
		sb.append("<h6>各方签署：</h6>");
		sb.append("<h6>甲方（印章）：<small> 甲方印章处</small></h6>");
		sb.append("</div>");
		sb.append("<div>");
		sb.append(" <h6>乙方（印章）：<small> 乙方印章处</small></h6>");
		sb.append(" </div>");
		sb.append(" <div>");
		sb.append("<h6>丙方（印章）：<small> 丙方印章处</small></h6>");
		sb.append(" </div>");
		sb.append("<div class='cen'>");
		sb.append("<h6>签约地点：<span>北京市海淀区</span></h6>");
		sb.append("</div>");
		sb.append("</div>");
		sb.append("</div>");
		sb.append("</div>");
		sb.append("</div>");
		sb.append("${contractAtt}");
		
		sb.append("</body>");
		sb.append("</html>");
		return sb;
	}
	
	 /**
     * 对接法大大签章
     * @param result
     * @return
     */
    public Result fadada(ContextInfo cti,Result result,String pdfUrl,String htmlUrl,CmContract contract,String customerNum){
    	try {
    		// 文件上传 合同编号
    		String contract_id = TransactionUtil.getTransactionNumber("contractNumber",BusinessCode.CONTRACT.getValue());// 合同编号
    		String uploadStatus = FddClientUtil.invokeUploadDocs(contract_id, new File(pdfUrl),".pdf");
    		if(null == uploadStatus){
    			result.setError("合同上传失败！");
				return result;
    		}
    		JSONObject uploadStatusJson = JSONObject.parseObject(uploadStatus);
    		if("success".equals(uploadStatusJson.get("result"))){
    			File file = new File(pdfUrl);  
    			// 判断是否为文件  
    		   	if (file.isFile()) 
    		    	file.delete();
    		    file = new File(htmlUrl);  
    		    if (file.isFile()) 
    		    	file.delete();
    			// 自动签章
    			String transaction_id = TransactionUtil.getTransactionNumber("transactionNumber",BusinessCode.TRANSACTION.getValue());// 交易号
    			String extSignAutoStatus = FddClientUtil.invokeExtSignAuto(transaction_id, Config.getProperty("fadada.YONGYOU_CUSTOMERNUM"), contract_id, contract.getContractName(), "乙方印章处");
    			if(null == extSignAutoStatus){
        			result.setError("自动签章失败！");
    				return result;
        		}
    			JSONObject extSignAutoStatusJson = JSONObject.parseObject(extSignAutoStatus);
    			// 薪福社：自动签章成功
    			if("success".equals(extSignAutoStatusJson.get("result"))){
    				CmContract c = new CmContract();
    	    		// 跟合同ID更新合同编号、下载地址、查看地址
    				c.setContractId(contract.getContractId());
    				c.setContractNumber(contract_id);
    				c.setDownUrl(extSignAutoStatusJson.getString("download_url"));
    				c.setViewUrl(extSignAutoStatusJson.getString("viewpdf_url"));
    				c.setModifyBy(cti.getUserId());
    				c.setModifyDt(new Date());
    				cmContractDao.update(cti,c);
    				// 添加交易记录---薪福社
    				CmContractSign contractSign = new CmContractSign();
    				contractSign.setTransactionId(transaction_id);
    				contractSign.setContractId(contract.getContractId());
    				contractSign.setContractNumber(contract_id);
    				contractSign.setCompanyId(cti.getOrgId());
    				contractSign.setCompanyType("PLATFORM");
    				contractSign.setCreateBy(cti.getUserId());
    				contractSign.setCreateDt(new Date());
    				contractSign.setDr(0);
    				cmContractSignService.save(cti,contractSign);
    				// 服务商签约
    				transaction_id = TransactionUtil.getTransactionNumber("transactionNumber",BusinessCode.TRANSACTION.getValue());// 交易号
    				String extSignUrl = FddClientUtil.invokeExtSign(transaction_id, contract_id, Config.getProperty("xfs_sps_host")+"/mall/order/list/", customerNum, contract.getContractName(),  Config.getProperty("xfs_sps_host")+"/contract/fadadaCallback/", "丙方印章处");
    				result.setDataValue("extSignUrl", extSignUrl);
    				// 添加交易记录---服务方
    				contractSign = new CmContractSign();
    				contractSign.setTransactionId(transaction_id);
    				contractSign.setContractId(contract.getContractId());
    				contractSign.setContractNumber(contract_id);
    				contractSign.setCompanyId(contract.getCpId());
    				contractSign.setCompanyType(CMCORPTYPE_SERVICE);
    				contractSign.setCreateBy(cti.getUserId());
    				contractSign.setCreateDt(new Date());
    				contractSign.setDr(0);
    				if(cmContractSignService.save(cti,contractSign) > 0){
    					// 更新代理
    					CmCorp corp = new CmCorp();
    					corp.setCpId(contract.getCpId());
    					corp.setCollaborator(cti.getUserId());
    					corpService.update(cti,corp);
    				}
    				// 更新企业客户经理
    				CmCorp cmCorp = new CmCorp();
    				cmCorp.setCpId(contract.getCpId());
    				cmCorp.setCollaborator(cti.getUserId());
    				corpService.update(cti,cmCorp);
    			}else{
    				result.setError(extSignAutoStatusJson.getString("msg"));
    				return result;
    			}
    		}else{
    			result.setError(uploadStatusJson.getString("msg"));
    			return result;
    		}
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		} catch (Error e) {
			result.setError("合同上传失败！");
			e.printStackTrace();
			return result;
		}
    	result.setSuccess(true);
		return result;
	}
    
    /**
	 * <b>概要：</b>
	 * 获取当前时间戳
	 * <b>作者：</b>zhouxw </br>
	 * <b>日期：</b>2015年12月17日 </br>
	 * @return 当前时间：'yyyyMMddHHmmss'格式
	 */
	public static String getTimeStamp(){
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		DateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(ts);
	}
	@Override
	public boolean fadadaCallback(ContextInfo cti,String result_code,String transaction_id,String download_url,String viewpdf_url,String result_desc) {
		try {
			// 3000(签章成功)3001(签章失败)
			if("3000".equals(result_code)){
				CmContractSign sign = new CmContractSign();
				sign.setTransactionId(transaction_id);
				// 根据交易号获取合同ID
				List<CmContractSign> signList = cmContractSignService.findAll(sign);
				if(signList.size() > 0){
					sign = signList.get(0);
					CmContract contract = new CmContract();
					contract.setContractId(sign.getContractId());
					contract.setContractState(1);
					contract.setDownUrl(download_url);
					contract.setViewUrl(viewpdf_url);
					// 更新合同签署状态
					cmContractDao.update(cti,contract);
					// 根据合同ID获取订单ID
					contract = new CmContract();
					contract.setContractId(sign.getContractId());
					contract = cmContractDao.findByPK(contract);
					SpsMallOrder order = new SpsMallOrder();
					order.setOrderId(contract.getOrderId());
					order.setOrderState("WAITSIGN");
					// 更新订单状态
					orderService.update(cti,order);
				}
			}else{

				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Override
	public void getContract(ContextInfo cti, Model model, CmContract contract) {
		try {
			contract.setSpId(cti.getOrgId());
			contract.setContractState(0);
        	List<CmContract> list = cmContractDao.freeFind(contract);
        	if(list.size() > 0){
        		int productId = contract.getProductId();
        		contract = list.get(0);
        		contract.setProductId(productId);
        	}
        	model.addAttribute("contrat", contract);
        	// 获取甲方信息（企业)cpId
        	CmCorp cmCorp = corpService.findByPK(contract.getCpId());
			if (cmCorp != null) {
	        	model.addAttribute("cpName", cmCorp.getCorpName());
        		model.addAttribute("cpMobile", cmCorp.getMobile());
				BsCompanyInfo cp= bsCompanyInfoService.queryCompanyInfoAllByName(cmCorp.getCorpName());
				if (cp != null) {
					JSONObject jsondatacp = JSONObject.parseObject(cp.getContactJson());
					if (jsondatacp != null) {
						model.addAttribute("cpAddress", jsondatacp.get("address"));
					}
					model.addAttribute("cpOperName", cp.getOper_name());
				}
			}
        	// 获取丙方信息（服务）spId
        	SpService spService = spServiceService.findByPk(contract.getSpId());
			if (spService != null) {
	        	model.addAttribute("spName", spService.getSpName());
				model.addAttribute("spMobile", spService.getMobile());
				BsCompanyInfo sp = bsCompanyInfoService.queryCompanyInfoAllByName(spService.getSpName());
				if (sp != null) {
					JSONObject jsondatasp = JSONObject.parseObject(sp.getContactJson());
					if (jsondatasp != null) {
						model.addAttribute("spAddress", jsondatasp.get("address"));
					}
					model.addAttribute("spOperName", sp.getOper_name());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	@Override
	public Result cancelContract(ContextInfo cti, Model model, CmContract contract, Result result) {
		try {
			// 更新订单状态
			SpsMallOrder order = new SpsMallOrder();
			order.setOrderId(contract.getOrderId());
			order.setOrderState("SUBMIT");
            order.setOrderOnlineType(SpsMallOrder.orderOnlineType_ONLINE);
			orderService.update(cti,order);
			// 更新合同已关闭
			// contract.setContractState(1);
			contract.setSpId(cti.getOrgId());
			contract.setState("USE");
			List<CmContract> list = cmContractDao.freeFind(contract);
			for(int i=0;i<list.size();i++){
				CmContract c = new CmContract();
				c.setContractId(list.get(i).getContractId());
				c.setContractState(9);
				c.setState("STOP");
				cmContractDao.update(cti,c);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}

	/**
	 * 保存线下合同
	 *
	 * @param cti
	 * @param contract
	 * @param orderState
	 * @param result
	 * @return
	 * @throws Exception
	 */
	public Result saveOfflineContract(ContextInfo cti, CmContract contract, String orderState, Result result) throws Exception {
		CmContract cmContract = new CmContract();
		contract.setContractId(contract.getContractId());
		contract.setState("USE");
		cmContract = findByPK(contract);
		if (cmContract == null) {
			result.setMsg("合同不存在");
			return result;
		}
		contract.setOnlineType(CmContract.onlineType_OFFLINE);
		if ("WAITAUDIT".equals(orderState)) {
			contract.setServiceState(2);
			contract.setCompanyState(2);
		}
		// 先修改合同
		update(cti, contract);

		// 再修改意向单
		SpsMallOrder order = new SpsMallOrder();
		order.setOrderId(cmContract.getOrderId());
		order.setOrderOnlineType(SpsMallOrder.orderOnlineType_OFFLINE);
		order.setOrderState(orderState);
		orderService.update(cti, order);

		// 给运营发短信
		if ("WAITAUDIT".equals(orderState)) {
			String businessPhones = Config.getProperty("business.phone");
			if (!StringUtils.isBlank(businessPhones)) {
				for(String phone: businessPhones.split("-")) {
					SmsUtil.sendSms(phone, "您收到一条线下合同信息需审核，请及时登录op平台处理【用友薪福社商城】");
				}
			}
		}
		return result.setSuccess(true);
	}
	
}
