package com.xfs.business.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.base.model.BdBusinessfield;
import com.xfs.base.model.BsArea;
import com.xfs.base.model.SysDictitem;
import com.xfs.base.service.BdBusinessfieldService;
import com.xfs.base.service.BsAreaService;
import com.xfs.base.service.SysDictitemService;
import com.xfs.business.dao.BdBsareatypeDao;
import com.xfs.business.dto.BsAreaTypeVo;
import com.xfs.business.dto.BsTypeAreaFiledDto;
import com.xfs.business.model.BdBsareatype;
import com.xfs.business.model.BdBstype;
import com.xfs.business.model.BdBstypeareafield;
import com.xfs.business.model.BdBstypefield;
import com.xfs.business.model.SpsTask;
import com.xfs.business.service.BdBsareatypeService;
import com.xfs.business.service.BdBstypeService;
import com.xfs.business.service.BdBstypeareafieldService;
import com.xfs.business.service.BdBstypefieldService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.ts.model.SpsTsCitycode;
import com.xfs.ts.service.SpsTsCitycodeService;

/**
 * 地区业务类型
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-11 14:30
 * @version 	: V1.0
 */
@Service
public class BdBsareatypeServiceImpl implements BdBsareatypeService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BdBsareatypeServiceImpl.class);
	
	@Autowired
	private BdBsareatypeDao bdBsareatypeDao;

	@Autowired
	BsAreaService BsAreaService;

	@Autowired
	private BsAreaService bsAreaService;

	//业务类型
	@Autowired
	private BdBstypeService bdBstypeService;

	//业务字段表
	@Autowired
	private BdBstypefieldService bdBstypefieldService;

	//地区业务字段表
	@Autowired
	private BdBstypeareafieldService bdBstypeareafieldService;

	//城市字段配置表
	@Autowired
	private SpsTsCitycodeService spsTsCitycodeService;

    //字段配置表
    @Autowired
    private BdBusinessfieldService bdBusinessfieldService;

	@Autowired
	private SysDictitemService sysDictitemService;


	public int save(ContextInfo cti, BdBsareatype vo ){
		return bdBsareatypeDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BdBsareatype vo ){
		return bdBsareatypeDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BdBsareatype vo ){
		return bdBsareatypeDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BdBsareatype vo ){
		return bdBsareatypeDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BdBsareatype vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bdBsareatypeDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BsAreaTypeVo> datas = bdBsareatypeDao.freeFindVo(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BdBsareatype> findAll(BdBsareatype vo){
		
		List<BdBsareatype> datas = bdBsareatypeDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/05/24 11:25:00

	/**
	 *  根据条件获取地区业务信息
	 *  @param   bdBsareatype
	 *	@return 			: com.xfs.business.model.BdBsareatype
	 *  @createDate  	: 2016-11-11 14:41
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:41
	 *  @updateAuthor  :
	 */
	@Override
	public BdBsareatype findBdBsAreaType(BdBsareatype bdBsareatype) {
		return bdBsareatypeDao.findBdBsAreaType(bdBsareatype);
	}

	/**
	 *  保存城市与业务关系
	 *  @param   bsTypes, areaId
	 *	@return 			: void
	 *  @createDate  	: 2016-11-11 14:41
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:41
	 *  @updateAuthor  :
	 */
	@Override
	public void saveCityBsTypeRelation(ContextInfo cti, String bsTypes,  Integer areaId){
		List<BdBsareatype> bbs = new ArrayList<>();
		String insurancetypes = null;
		String fund = null;
		SpsTsCitycode spsTsCitycode = spsTsCitycodeService.getSpsTsCitycodeByAreaId(areaId);
		if(spsTsCitycode!=null){
			insurancetypes = spsTsCitycode.getInsurancetypes();
			fund = spsTsCitycode.getFundtypes();
		}
		BdBsareatype btype ;
		bsTypes = bsTypes.substring(0,bsTypes.length()-1);
		String[] bsType = bsTypes.split(",");
		int bsTypeLength = bsType.length;
		for(int i = 0;i<bsTypeLength;i++){
			btype=new BdBsareatype();
			btype.setBstypeId(Integer.parseInt(bsType[i]));
			btype.setAreaId(areaId);

			BdBsareatype bdBsareatype = findBdBsAreaType(btype);
			if(bdBsareatype==null){
				BdBstype bstype = new BdBstype();
				bstype.setBstypeId(Integer.parseInt(bsType[i]));
				BdBstype bd = bdBstypeService.findBdBstypeByKey(bstype);

				if(bd.getInsuranceFundType().equals("FUND")){
					if(fund==null){
						fund=bsType[i];
					}else {
						fund = match(fund,bsType[i],false);
					}
				}else {
					if(insurancetypes==null){
						insurancetypes=bsType[i];
					}else {
						insurancetypes = match(insurancetypes,bsType[i],false);
					}
				}
				insert(cti, btype);
			}
		}
		BsArea bsArea = new BsArea();
		bsArea.setAreaId(areaId);
		BsArea bs = bsAreaService.findbyPK(bsArea);
		if(bs!=null) {
			if(spsTsCitycode!=null){
				spsTsCitycode.setFundtypes(fund);
				spsTsCitycode.setInsurancetypes(insurancetypes);
				spsTsCitycodeService.update(cti, spsTsCitycode);
			}else {
				SpsTsCitycode sps = new SpsTsCitycode();
				String codeStr = bs.getCode().trim();
				sps.setAreaId(codeStr);
				sps.setFundtypes(fund);
				sps.setInsurancetypes(insurancetypes);
				spsTsCitycodeService.insert(cti, sps);
			}
		}
	}



	public void saveBsTypeAreaField(ContextInfo cti, Integer areaId, Integer bstypeId, String fieldIds) {
		fieldIds = fieldIds.substring(0,fieldIds.length()-1);
		String[] fieldIdList =fieldIds.split(",");
		int length = fieldIdList.length;
		BdBstypeareafield bbta;
		BdBstypefield bdBstypefield;
		for(int i = 0;i<length;i++){
			String[] fieldStr = fieldIdList[i].split("_");
			bbta = new BdBstypeareafield();
			bdBstypefield = new BdBstypefield();
			bbta.setAreaId(areaId);
			bbta.setBstypeId(bstypeId);
			bbta.setFieldId(Integer.parseInt(fieldStr[0]));
			bdBstypefield.setBstypeId(bstypeId);
			bdBstypefield.setFieldId(Integer.parseInt(fieldStr[0]));
			//插入业务地区字段关系，业务字段关系
			BdBstypeareafield bdBstypeareafield = bdBstypeareafieldService.findBdBstypeareafield(bbta);
            BdBusinessfield bdBusinessfield = new BdBusinessfield();
            bdBusinessfield.setFieldId(Integer.parseInt(fieldStr[0]));
            BdBusinessfield businessfield = bdBusinessfieldService.findBdBussinessFieldByKey(bdBusinessfield);
            if (businessfield.getType().equals("FILE")) {
                bbta.setModelIsHidden("HIDDEN");
            }

			if(bdBstypeareafield==null){
				bbta.setRequired(fieldStr[1]);
				bdBstypeareafieldService.insert(cti, bbta);
			}else{
				bdBstypeareafield.setRequired(fieldStr[1]);
				bdBstypeareafieldService.update(cti, bdBstypeareafield);
			}
			BdBstypefield bbf = bdBstypefieldService.findBdBstypefield(bdBstypefield);
			if(bbf==null){
				bdBstypefieldService.save(cti, bdBstypefield);
			}
		}
	}

	/**
	 * 	预制地区业务数据
	 *  @param   areaId
	 *	@return 			: void
	 *  @createDate  	: 2016-11-11 14:42
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:42
	 *  @updateAuthor  :
	 */
	public void resetData(ContextInfo cti, Integer areaId) {
		BsArea bsArea= new BsArea();
		bsArea.setAreaId(areaId);
		BsArea bb = bsAreaService.findbyPK(bsArea);
		String funds=null;
		String instances=null;
		SpsTsCitycode spsTsCitycode = new SpsTsCitycode();
		spsTsCitycode.setAreaId(areaId+"");
		SpsTsCitycode stc = spsTsCitycodeService.getSpsTsCitycodeByAreaId(areaId);


		if(stc!=null){
			funds= stc.getFundtypes();
			instances = spsTsCitycode.getInsurancetypes();
		}
		BdBsareatype bdBsareatype = new BdBsareatype();
		bdBsareatype.setAreaId(-99);
		List<BdBsareatype> bdBstypeList = findAll(bdBsareatype);
		for(BdBsareatype bs:bdBstypeList){
			bs.setId(null);
			bs.setAreaId(areaId);
			BdBsareatype bbt =findBdBsAreaType(bs);
			BdBstypeareafield bbaf;
			if(bbt ==null){
				insert(cti,bs);
				//以北京的参数为模板
				bbaf= new BdBstypeareafield();
				bbaf.setAreaId(-99);
				bbaf.setBstypeId(bs.getBstypeId());
				List<BdBstypeareafield> bdBstypeareafieldList = bdBstypeareafieldService.findBdBstypeareafieldList(bbaf);

				for(BdBstypeareafield bf:bdBstypeareafieldList){
					bf.setId(null);
					bf.setBstypeId(bs.getBstypeId());
					bf.setAreaId(areaId);
					BdBstypeareafield bbs = bdBstypeareafieldService.findBdBstypeareafield(bf);
					if(bbs==null){
						bf.setCreateDt(new Date());
						bdBstypeareafieldService.insert(cti, bf);
						BdBstypefield bty = new BdBstypefield();
						bty.setFieldId(bf.getFieldId());
						bty.setBstypeId(bs.getBstypeId());
						BdBstypefield bdBstypefield = bdBstypefieldService.findBdBstypefield(bty);
						if(bdBstypefield==null){
							bdBstypefieldService.insert(cti, bty);
						}
					}
				}
			}
		}

		//预制字典表
		SysDictitem dictitem = new SysDictitem();
		dictitem.setAreaId(-99);
		List<SysDictitem> sysDictitems = sysDictitemService.findAll(dictitem);
		for (SysDictitem dic : sysDictitems){
			dic.setId(null);
			dic.setCreateTime(null);
			dic.setAreaId(areaId);
			sysDictitemService.insert(cti,dic);
		}

		if(stc!=null) {
			stc.setInsurancetypes(instances);
			stc.setFundtypes(funds);
			spsTsCitycodeService.update(cti, stc);
		}else {
			SpsTsCitycode spsTc = new SpsTsCitycode();
			spsTc.setAreaId(bb.getCode());
			spsTc.setInsurancetypes(instances);
			spsTc.setFundtypes(funds);
			spsTsCitycodeService.insert(cti, spsTc);
		}
	}

	public  String match(String types, String type, boolean delete){
		String str = null;
		String[] typeArr = types.split(",");
		List<String> stringList = Arrays.asList(typeArr);
		if(delete) {
			if (stringList.contains(type)) {
				types = types.replace(type,"");
				if(types.startsWith(",")){
					types = types.substring(1,types.length());
				}else if(types.endsWith(",")){
					types = types.substring(0,types.length()-1);
				}
				str = types;
			}
		}else {
			if (!stringList.contains(type)) {
				if(types.isEmpty()){
					types=type;
				}else{
					types=types+","+type;
				}
				str= types;
			}
		}
		return str;
	}

	/**
	 *  根据城市id 查询 业务类型信息
	 *  @param   vo
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate  	: 2016-11-11 14:40
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:40
	 *  @updateAuthor  :
	 */
	public List<Map<String,Object>> findBstypeByCity(BdBsareatype vo){
		return bdBsareatypeDao.findBstypeByCity(vo);
	}

	/**
	 *  根据城市id查询业务类型、待处理总数
	 *  @param   spId, vo, bstype
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate  	: 2016-11-11 14:39
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:39
	 *  @updateAuthor  :
	 */
	public List<Map<String,Object>> getBstypeByArea(Integer spId, BdBsareatype vo, BdBstype bstype){
		return bdBsareatypeDao.getBstypeByArea(spId, vo, bstype);
	}

	/**
	 *  通过城市与类型查询参保方式
	 *  @param   areaId, type
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate  	: 2016-11-11 14:39
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:39
	 *  @updateAuthor  :
	 */
	@Override
	public List<Map<String, Object>> findAddBstypeByCityAndType(Integer areaId,String type) {
		if(areaId == null || StringUtils.isBlank(type)){
			return null;
		}
		String typeIn = null;
		if("INSURANCE".equals(type)){
			typeIn = "2,3,22,23";
		}else {
			typeIn =  "10,15,28";
		}
		BsArea area = new BsArea();
		area.setAreaId(areaId);
		area = BsAreaService.findByPK(area);
		if(area == null){
			return null;
		}
		return bdBsareatypeDao.findBstypeByCityAndType(area.getBelongCity(),type,typeIn);
	}

	/**
	 *  查询其他类型业务
	 *  @param   areaId
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate  	: 2016-11-11 14:38
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:38
	 *  @updateAuthor  :
	 */
	public List<Map<String, Object>> findOtherTaskBstype(Integer areaId) {
		if(areaId == null){
			return null;
		}
		Integer[] typeNotIn = new Integer[]{2,3,4,22,23,24,10,11,15,16,28};

		BsArea area = new BsArea();
		area.setAreaId(areaId);
		area = BsAreaService.findByPK(area);
		if(area == null){
			return null;
		}
		return bdBsareatypeDao.findBstypeByCityAndType(area.getBelongCity(),null,"1,7");
	}

	@Override
	public List<Map<String, Object>> getBstypeByArea2(Integer spId, SpsTask task, BdBstype bstype) {
		return bdBsareatypeDao.getBstypeByArea2(spId, task, bstype);
	}

	/**
	 *  根据企业方案所在地区获取业务对应的字段信息
	 *  @param   cpId
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate  	: 2017-03-23 15:53
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-03-23 15:53
	 *  @updateAuthor  :
	 */
	public List<BsTypeAreaFiledDto> findBdBsAreaTypeBySchemeAreaId(Integer cpId, String bstypeId){
		return bdBsareatypeDao.findBdBsAreaTypeBySchemeAreaId(cpId,bstypeId);
	}

	public List<BsTypeAreaFiledDto> findBdBsAreaTypeByAreaId(){
		return bdBsareatypeDao.findBdBsAreaTypeByAreaId();
	}

	/**
	 *  获取当前企业对应方案地区下业务字段值信息
	 *  @param   cpId, code
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate  	: 2017-03-23 15:52
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-03-23 15:52
	 *  @updateAuthor  :
	 */
	public List<Map<String, Object>> findBdBsAreaTypeFiledBySchemeAreaId(Integer cpId, String code,Integer schemeId){
		return bdBsareatypeDao.findBdBsAreaTypeFiledBySchemeAreaId(cpId,code,schemeId);
	}

	/**
	 * 根据网申结束日期获取用户信息
	 * @param endDays 结束日期
	 * @return
	 */
	public List<Map<String, Object>> findUserByEndDay(String endDays){
		if(StringUtils.isBlank(endDays))
			return null;
		return bdBsareatypeDao.findUserByEndDay(endDays);
	}
	
	/**
	 * 根据城市和类型获取地区业务类型
	 *  @param areaId
	 *  @param bstypeId
	 *  @return 
	 *  @createDate  	: 2017年4月24日 下午2:13:13
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年4月24日 下午2:13:13
	 *  @updateAuthor  	:
	 */
	@Override
	public BdBsareatype findBdBstypeByCityAndType(Integer areaId, Integer bstypeId) {
		BdBsareatype vo = new BdBsareatype();
		vo.setAreaId(areaId);
		vo.setBstypeId(bstypeId);
		List<BdBsareatype> datas = bdBsareatypeDao.freeFind(vo);
		if(datas.size() > 0){
			return datas.get(0);
		}
		return null;
	}

}
