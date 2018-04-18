package com.xfs.base.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.base.dao.BdBusinessfieldDao;
import com.xfs.base.dao.BsAreaDao;
import com.xfs.base.dto.BusinessField;
import com.xfs.base.model.BdBusinessfield;
import com.xfs.base.model.BsArea;
import com.xfs.base.model.SysDictitem;
import com.xfs.business.dao.BdBstypeareafieldDao;
import com.xfs.business.model.BdBstypeareafield;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.enums.BusinessFieldType;

/**
 * 地区业务字段配置服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-11 14:25
 * @version 	: V1.0
 */
@Service
public class BdBusinessfieldServiceImpl implements BdBusinessfieldService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BdBusinessfieldServiceImpl.class);
	
	@Autowired
	private BdBusinessfieldDao bdBusinessfieldDao;

	@Autowired
	private BdBstypeareafieldDao bdBstypeareafieldDao;

	@Autowired
	private SysDictitemService sysDictitemService;

	@Autowired
	private BsAreaDao bsAreaDao;
	
	public int save(ContextInfo cti, BdBusinessfield vo ){
		return bdBusinessfieldDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BdBusinessfield vo ){
		return bdBusinessfieldDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BdBusinessfield vo ){
		return bdBusinessfieldDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BdBusinessfield vo ){
		return bdBusinessfieldDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BdBusinessfield vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bdBusinessfieldDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BdBusinessfield> datas = bdBusinessfieldDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}

	public PageModel findAllList(PageInfo pi, BdBusinessfield vo){
		PageModel pm = new PageModel(pi);
		Integer total = bdBusinessfieldDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BdBusinessfield> datas = bdBusinessfieldDao.freeFind(vo);
		pm.setDatas(datas);
		return pm;

	}
	public List<BdBusinessfield> findAll(BdBusinessfield vo){
		
		List<BdBusinessfield> datas = bdBusinessfieldDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/05/24 11:25:08

	/**
	 * 获取业务字段信息
	 *  @param   vo
	 *	@return 			: com.xfs.base.model.BdBusinessfield
	 *  @createDate  	: 2016-11-11 14:22
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:22
	 *  @updateAuthor  :
	 */
	@Override
	public BdBusinessfield findBdBussinessFieldByKey(BdBusinessfield vo) {
		return bdBusinessfieldDao.findByPK(vo);
	}


	/**
	 *  分页获取地区业务类型字段
	 *  @param   areaId, bstypeId
	 *	@return 			: com.xfs.common.page.PageModel
	 *  @createDate  	: 2016-11-11 14:21
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:21
	 *  @updateAuthor  :
	 */
	@Override
	public PageModel findbsTypeAreaFieldPage(PageInfo pi, int areaId,int bstypeId){

		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		BdBstypeareafield bdBstypeareafield = new BdBstypeareafield();
		bdBstypeareafield.setAreaId(areaId);
		bdBstypeareafield.setBstypeId(bstypeId);
		Integer total = bdBstypeareafieldDao.countFreeFind(bdBstypeareafield);
		pm.setTotal(total);
		List<BdBusinessfield> datas = bdBusinessfieldDao.findbsTypeAreaFieldPage(areaId,bstypeId, pageIndex, pageSize);

        for (BdBusinessfield bdBusinessfield: datas) {
            if ("PULL".equals(bdBusinessfield.getType())) {
                if (StringUtils.isNotBlank(bdBusinessfield.getDefaultValue())) {
                    List<SysDictitem> options =  sysDictitemService.findByDictNameAndArea(bdBusinessfield.getCode()+"_doc",bdBusinessfield.getAreaId());
                    for (SysDictitem sysDictitem: options) {
                        if (sysDictitem.getCode().equals(bdBusinessfield.getDefaultValue())) {
                            bdBusinessfield.setDefaultValue(sysDictitem.getName());
                        }
                    }
                }
            }
        }
//		pm.setTotal(datas.size());
		pm.setDatas(datas);
		return pm;

	}

	/**
	 *  根据地区/业务获取动态控件组
	 *  @param   areaId, bsTypeId
	 *	@return 			: java.util.List<com.xfs.base.dto.BusinessField>
	 *  @createDate  	: 2016-11-11 14:17
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:17
	 *  @updateAuthor  :
	 */
	public List<BusinessField> queryBusinessFields(Integer areaId, Integer[] bsTypeId){
		BsArea area = new BsArea();
		area.setAreaId(areaId);
		area = bsAreaDao.findByPK(area);
		if(area == null){
			return null;
		}
		List<BusinessField> businessFields =  bdBusinessfieldDao.queryBusinessFields(area.getBelongCity(),bsTypeId);
		if(null != businessFields && !businessFields.isEmpty()){
			//合并同类code
			List<BusinessField> handleList = new ArrayList<>();
			Map<String,BusinessField> businessFieldMap = new HashMap<>();
			for(BusinessField businessField : businessFields){
				if(businessFieldMap.containsKey(businessField.getCode())){
					BusinessField old = businessFieldMap.get(businessField.getCode());
					old.setInsuranceFundType(old.getInsuranceFundType()+"-" +businessField.getInsuranceFundType());
				}else{
					businessFieldMap.put(businessField.getCode(),businessField);
					handleList.add(businessField);
				}
			}
			businessFields = handleList;
			//循环获取下拉框控件中的options
			for(BusinessField businessField : businessFields){
				if(BusinessFieldType.SELECT.toString().equals(businessField.getType())){
					//获取下拉框控件中的options
					businessField.setOptions(sysDictitemService.findByDictNameAndArea(businessField.getCode()+"_doc",areaId));
				}
			}
		}
		return businessFields;
	}

	/**
	 *  根据地区/业务类型获取动态控件组
	 *  @param   areaId, bsTypeId
	 *	@return 			: java.util.Map<java.lang.String,com.xfs.base.dto.BusinessField>
	 *  @createDate  	: 2016-11-11 14:20
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:20
	 *  @updateAuthor  :
	 */
	public Map<String,BusinessField> queryBusinessFields(Integer areaId, Integer bsTypeId){
		BsArea area = new BsArea();
		area.setAreaId(areaId);
		area = bsAreaDao.findByPK(area);
		if(area == null){
			return null;
		}
		List<BusinessField> businessFields =  bdBusinessfieldDao.queryBusinessFields(area.getBelongCity(),new Integer[]{bsTypeId});

		//循环获取下拉框控件中的options
		Map<String,BusinessField> businessFieldMap = new HashMap<>();
		if(null != businessFields && !businessFields.isEmpty()){
			for(BusinessField businessField : businessFields){
				businessFieldMap.put(businessField.getCode(),businessField);
				if(BusinessFieldType.SELECT.toString().equals(businessField.getType())){
					//获取下拉框控件中的options
					businessField.setOptions(sysDictitemService.findByDictNameAndArea(businessField.getCode()+"_doc",areaId));
				}
			}
		}
		return businessFieldMap;
	}


	/**
	 *  特殊查询 增员必须的参保类型
	 *  @param   areaId
	 *	@return 			: com.xfs.base.dto.BusinessField
	 *  @createDate  	: 2016-11-11 14:16
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:16
	 *  @updateAuthor  :
	 */
	public BusinessField findLivingType(Integer areaId){
		BdBusinessfield query  = new BdBusinessfield();
		query.setCodeEq("insuranceLiveType");
		query.setSourceEq("TASK");
		List<BdBusinessfield> list = bdBusinessfieldDao.freeFind(query);
		BusinessField businessField = new BusinessField();
		if(CollectionUtils.isNotEmpty(list)){
			BeanUtils.copyProperties(list.get(0),businessField);
			businessField.setOptions(sysDictitemService.findByDictNameAndArea(businessField.getCode()+"_doc",areaId));
		}else{
			return null;
		}
		return businessField;
	}

	/**
	 *  根据地区/业务类型获取动态控件组
	 *  @param   areaId, bstypeId
	 *	@return 			: java.util.List<com.xfs.base.model.BdBusinessfield>
	 *  @createDate  	: 2016-11-11 14:23
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:23
	 *  @updateAuthor  :
	 */
	@Override
	public List<BdBusinessfield> bdBusinessFieldList(Integer areaId, Integer bstypeId) {
		return bdBusinessfieldDao.bdBusinessFieldList(areaId,bstypeId);
	}



    public List<BdBusinessfield> queryBusinessFieldsBySource(BdBusinessfield vo) {
        return bdBusinessfieldDao.queryBusinessFieldsBySource(vo);

    }
	@Override
	public BdBusinessfield findNameByCode(String key) {
		BdBusinessfield vo  = new BdBusinessfield();
		vo.setCode(key);
		return bdBusinessfieldDao.findNameByCode(vo);
	}

	
}
