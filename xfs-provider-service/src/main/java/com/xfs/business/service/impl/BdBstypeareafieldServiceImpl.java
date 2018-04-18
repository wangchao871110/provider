package com.xfs.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.xfs.base.dao.BdBusinessfieldDao;
import com.xfs.base.dto.BsBussinessFieldVo;
import com.xfs.business.dao.BdBsareatypeDao;
import com.xfs.business.dao.BdBstypeareafieldDao;
import com.xfs.business.dto.BsAreaTypeVo;
import com.xfs.business.model.BdBsareatype;
import com.xfs.business.model.BdBstypeareafield;
import com.xfs.business.service.BdBstypeareafieldService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;


/**
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-11 14:46
 * @version 	: V1.0
 */
@Service
public class BdBstypeareafieldServiceImpl implements BdBstypeareafieldService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(BdBstypeareafieldServiceImpl.class);

	@Autowired
	private BdBstypeareafieldDao bdBstypeareafieldDao;
	@Autowired
	private BdBsareatypeDao bdBsareatypeDao;
	@Autowired
	private BdBusinessfieldDao bdBusinessfieldDao;
	
	public int save(ContextInfo cti, BdBstypeareafield vo ){
		return bdBstypeareafieldDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  BdBstypeareafield vo ){
		return bdBstypeareafieldDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  BdBstypeareafield vo ){
		return bdBstypeareafieldDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  BdBstypeareafield vo ){
		return bdBstypeareafieldDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, BdBstypeareafield vo){

		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = bdBstypeareafieldDao.countFreeFind(vo);
		pm.setTotal(total);
		List<BdBstypeareafield> datas = bdBstypeareafieldDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<BdBstypeareafield> findAll(BdBstypeareafield vo){
		
		List<BdBstypeareafield> datas = bdBstypeareafieldDao.freeFind(vo);
		return datas;
		
	}



	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/05/24 11:25:05

	/**
	 *  通过城市类型id查询字段范围
	 *  @param   areaTypeId
	 *	@return 			: com.xfs.common.Result
	 *  @createDate  	: 2016-11-11 14:49
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:49
	 *  @updateAuthor  :
	 */
	@Override
	public Result finAllFieldByAreaType(Integer areaTypeId) {
		Result result = new Result();
		BdBsareatype modelQuery = new BdBsareatype();
		modelQuery.setId(areaTypeId);
		BsAreaTypeVo bsareatype = new BsAreaTypeVo();
		bsareatype = bdBsareatypeDao.findVoByPK(modelQuery);
		if(bsareatype == null){
			result.setSuccess(false);
			result.setError("该模板不存在");
			return result;
		}
		List<BsBussinessFieldVo> fields = bdBusinessfieldDao.freeFindByType(bsareatype.getBstypeId());
		if(CollectionUtils.isEmpty(fields)){
			result.setSuccess(false);
			result.setError("该模板类型下不存在字段");
			return result;
		}
		BdBstypeareafield query = new BdBstypeareafield();
		query.setAreaId(bsareatype.getAreaId());
		query.setBstypeId(bsareatype.getBstypeId());
		List<BdBstypeareafield> bstypeareafields = bdBstypeareafieldDao.freeFind(query);
		if(CollectionUtils.isNotEmpty(bstypeareafields)){
			for (BsBussinessFieldVo vo: fields){
				for (BdBstypeareafield field: bstypeareafields){
					if(vo.getFieldId().equals(field.getFieldId())){
						vo.setIsEnable("Y");
						vo.setRequired(field.getRequired());
					}
				}
			}
		}
		result.setDataValue("fieldList",fields);
		result.setDataValue("templateInfo",bsareatype);
		return result;
	}

	/**
	 *  保存模板设置字段
	 *  @param   areaTypeId, fields
	 *	@return 			: com.xfs.common.Result
	 *  @createDate  	: 2016-11-11 14:49
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:49
	 *  @updateAuthor  :
	 */
	@Override
	public Result saveBsTypeAreaField(ContextInfo cti, Integer areaTypeId,String fields){
		Result result = new Result();
		List<Map> list = JSON.parseArray(fields, Map.class);
		List<BdBstypeareafield> bstypeareafields = new ArrayList<BdBstypeareafield>();
		BdBsareatype bdBsareatype = new BdBsareatype();
		bdBsareatype.setId(areaTypeId);
		bdBsareatype = bdBsareatypeDao.findByPK(bdBsareatype);
		if(bdBsareatype == null){
			result.setSuccess(false);
			result.setError("该模板不存在");
			return result;
		}
		if(CollectionUtils.isNotEmpty(list)){
			for(Map m: list){
				if(!StringUtils.isEmpty(m.get("isEnable").toString()) && m.get("isEnable").toString().equals("Y")){
					BdBstypeareafield bdBstypeareafield = new BdBstypeareafield();
					bdBstypeareafield.setBstypeId(bdBsareatype.getBstypeId());
					bdBstypeareafield.setAreaId(bdBsareatype.getAreaId());
					bdBstypeareafield.setFieldId(Integer.valueOf(m.get("fieldId").toString()));
					bdBstypeareafield.setRequired(m.get("required").toString());
					bstypeareafields.add(bdBstypeareafield);
				}
			}
		}
		BdBstypeareafield query = new BdBstypeareafield();
		query.setAreaId(bdBsareatype.getAreaId());
		query.setBstypeId(bdBsareatype.getBstypeId());
		Integer delNum = bdBstypeareafieldDao.removeObjectByAreaAndType(cti, query);
		if(CollectionUtils.isNotEmpty(bstypeareafields)){
			Integer addNum = bdBstypeareafieldDao.saveAll(cti, bstypeareafields);
			if(addNum < 1){
				result.setSuccess(false);
				result.setError("新增失败");
				return result;
			}
		}
		return result;
	}

	/**
	 *  获取业务字段详情
	 *  @param   vo
	 *	@return 			: com.xfs.business.model.BdBstypeareafield
	 *  @createDate  	: 2016-11-11 14:50
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:50
	 *  @updateAuthor  :
	 */
	@Override
	public BdBstypeareafield findBdBstypeareafield(BdBstypeareafield vo) {
		return bdBstypeareafieldDao.findBdBstypeareafield(vo);
	}

	/**
	 *  删除业务字段
	 *  @param   vo
	 *	@return 			: int
	 *  @createDate  	: 2016-11-11 14:50
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:50
	 *  @updateAuthor  :
	 */
	@Override
	public int deleteBdBstypeareafields(ContextInfo cti, BdBstypeareafield vo) {
		return bdBstypeareafieldDao.delete(cti, vo);
	}

	/**
	 *  获取业务字段列表
	 *  @param   vo
	 *	@return 			: java.util.List<com.xfs.business.model.BdBstypeareafield>
	 *  @createDate  	: 2016-11-11 14:50
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:50
	 *  @updateAuthor  :
	 */
	@Override
	public List<BdBstypeareafield> findBdBstypeareafieldList(BdBstypeareafield vo) {
		return bdBstypeareafieldDao.findBdBstypeareafieldList(vo);
	}

	/**
	 *  查询 某个业务、某个区域下 字段信息
	 *  @param   vo
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.String>>
	 *  @createDate  	: 2016-11-11 14:48
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:48
	 *  @updateAuthor  :
	 */
	public List<Map<String,String>> findBsTypeField(BdBstypeareafield vo){
		return bdBstypeareafieldDao.findBsTypeField(vo);
	}

	/**
	 *  根据 类型名称 查询 业务类型字段
	 *  @param   vo
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.String>>
	 *  @createDate  	: 2016-11-11 14:47
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:47
	 *  @updateAuthor  :
	 */
	public List<Map<String,String>> findBsTypeFieldByName(BdBstypeareafield vo){
		return bdBstypeareafieldDao.findBsTypeFieldByName(vo);
	}

	/**
	 *  根据编码获取业务字段列表
	 *  @param   code
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.String>>
	 *  @createDate  	: 2016-11-11 14:47
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-11 14:47
	 *  @updateAuthor  :
	 */
	@Override
	public List<Map<String,String>> findBsTypeFieldList(String code){
		return bdBstypeareafieldDao.findBsTypeFieldList(code);
	}
}
