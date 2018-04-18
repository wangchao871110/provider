package com.xfs.business.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.xfs.business.dto.PsnInsuCalcDto;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.xfs.business.dto.InsuranceCalcDetailDto;
import com.xfs.business.dto.InsuranceCalcDto;
import com.xfs.business.dto.InsuranceTypeRatio;
import com.xfs.business.model.BdInsurance;
import com.xfs.business.model.BsArearatio;
import com.xfs.business.service.BdInsuranceService;
import com.xfs.business.service.BsArearatioService;
import com.xfs.business.service.InsuranceCalcSerivice;
import com.xfs.common.PageInfo;
import com.xfs.common.mongo.MongoDao;
import com.xfs.common.mongo.vo.Search;
import com.xfs.common.mongo.vo.SearchField;
import com.xfs.common.page.PageModel;
import com.xfs.common.util.Arith;

/**
 * 社保计算实现
 * Created by yangf on 2016/11/28.
 */
@Service
public class InsuranceCalcServiceImpl implements InsuranceCalcSerivice {
    @Autowired
    private MongoDao mongoDao;
    @Autowired
    private BsArearatioService bsArearatioService;
    @Autowired
    private BdInsuranceService bdInsuranceService;
    
    @Override
    public InsuranceCalcDto insuranceCalc(InsuranceCalcDto insuranceCalcDto) {
    	insuranceCalcDto.setWages(insuranceCalcDto.getWages());
    	List<BdInsurance> ins = bdInsuranceService.findInsurancesByAreaId(insuranceCalcDto.getAreaId());
    	insuranceCalcDto.setDetails(new ArrayList<InsuranceCalcDetailDto>());
    	for(BdInsurance i : ins) {
    		if("INSURANCE".equals(i.getInsuranceFundType()) || insuranceCalcDto.getIsFund()) {
	    		BsArearatio ratio = bsArearatioService.findDefaultRatio(insuranceCalcDto.getAreaId(), i.getInsuranceId(), insuranceCalcDto.getLivingType(), insuranceCalcDto.getPeriod());
	    		if (null != ratio) {
		    		InsuranceCalcDetailDto dto = new InsuranceCalcDetailDto();
		    		dto.setInsuranceId(i.getInsuranceId());
		    		dto.setRatioId(ratio.getRatioId());
		    		dto.setCorpPaybase(insuranceCalcDto.getWages());
		    		dto.setEmpPaybase(insuranceCalcDto.getWages());
		    		bsArearatioService.calc(null, dto, insuranceCalcDto.getPeriod(),null);
		    		insuranceCalcDto.getDetails().add(dto);
		    		if("FUND".equals(i.getInsuranceFundType())) {
		    			insuranceCalcDto.setCorpFund(Arith.add(insuranceCalcDto.getCorpFund(),dto.getCorpPayment()));
		    			insuranceCalcDto.setPsnFund(Arith.add(insuranceCalcDto.getPsnFund(),dto.getEmpPayment()));
		    		} else {
		    			insuranceCalcDto.setCorpInsu(Arith.add(insuranceCalcDto.getCorpInsu(),dto.getCorpPayment()));
		    			insuranceCalcDto.setPsnInsu(Arith.add(insuranceCalcDto.getPsnInsu(),dto.getEmpPayment()));
		    		}
	    		}
    		}
    	}
        return insuranceCalcDto;
    }
    /**
     * 社保计算器 计算人员缴纳详细
     *  @param   psnInsuCalcDto
     * @return    : com.xfs.business.dto.InsuranceCalcDto
     *  @createDate   : 2016/12/3 18:56
     *  @author          : yangfw@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2016/12/3 18:56
     *  @updateAuthor  :
     */
    @Override
    public InsuranceCalcDto insuranceCalc(PsnInsuCalcDto psnInsuCalcDto) {
    	InsuranceCalcDto insuranceCalcDto = new InsuranceCalcDto();
    	List<BdInsurance> ins = bdInsuranceService.findInsurancesByAreaId(psnInsuCalcDto.getAreaId());
    	Map<Integer, BdInsurance> insMap = new HashMap<>();
    	for(BdInsurance i : ins) {
    		insMap.put(i.getInsuranceId(), i);
    	}
    	insuranceCalcDto.setDetails(new ArrayList<InsuranceCalcDetailDto>());
    	for(InsuranceTypeRatio i : psnInsuCalcDto.getInsuranceRatios()) {
    		InsuranceCalcDetailDto dto = new InsuranceCalcDetailDto();
    		dto.setInsuranceId(i.getInsuranceId());
    		dto.setRatioId(i.getRatioId());
            dto.setInsuarnaceName(i.getInsuarnaceName());
    		if("FUND".equals(insMap.get(i.getInsuranceId()).getInsuranceFundType())) {
	    		dto.setCorpPaybase(psnInsuCalcDto.getFundBase());
	    		dto.setEmpPaybase(psnInsuCalcDto.getFundBase());
	    		bsArearatioService.calc(null, dto, psnInsuCalcDto.getBeginMonth(),null);
    			insuranceCalcDto.setCorpFund(Arith.add(insuranceCalcDto.getCorpFund(),dto.getCorpPayment()));
    			insuranceCalcDto.setPsnFund(Arith.add(insuranceCalcDto.getPsnFund(),dto.getEmpPayment()));
    		} else {
	    		dto.setCorpPaybase(psnInsuCalcDto.getInsuranceBase());
	    		dto.setEmpPaybase(psnInsuCalcDto.getInsuranceBase());
	    		bsArearatioService.calc(null, dto, psnInsuCalcDto.getBeginMonth(),null);
    			insuranceCalcDto.setCorpInsu(Arith.add(insuranceCalcDto.getCorpInsu(),dto.getCorpPayment()));
    			insuranceCalcDto.setPsnInsu(Arith.add(insuranceCalcDto.getPsnInsu(),dto.getEmpPayment()));
    		}
    		insuranceCalcDto.getDetails().add(dto);
    	}
        return insuranceCalcDto;
    }
    /**
     * 批量在mongo中保存数据
     *  @param   tableName, list   mongo表明  存储数据
     * @return    : java.lang.Boolean
     *  @createDate   : 2016/11/28 20:52
     *  @author          : yangfw@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2016/11/28 20:52
     *  @updateAuthor  :
     */
    @Override
    public Boolean saveMongoList(String tableName, List list){
        if(CollectionUtils.isEmpty(list)){
            return false;
        }
        List<String> jsons = new ArrayList<>();
        for(Object o :list){
            jsons.add(JSON.toJSONString(o));
        }
        return mongoDao.insertAll(tableName,jsons);
    }
    /**  
     *  保存mongo对象
     *  @param
     * @return    : 
     *  @createDate   : 2016/12/1 17:22
     *  @author          : gaoyf@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2016/12/1 17:22
     *  @updateAuthor  :
     */
    @Override
    public Boolean saveMongoModel(String tableName,Object model){
        if(model == null){
            return false;
        }
        String json = JSON.toJSONString(model);
        return mongoDao.save(tableName,json);
    }
    /**
     * 分页查询mongo
     *  @param   info, tableName, search  分页参数，表名，查询对象
     * @return    : com.xfs.common.page.PageModel
     *  @createDate   : 2016/11/28 21:16
     *  @author          : yangfw@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2016/11/28 21:16
     *  @updateAuthor  :
     */
    @Override
    public PageModel findMongoDataPage(PageInfo info,String tableName,Search search){
        PageModel pm = new PageModel(info);
        String query = "{";
        for(SearchField field:search.getAndSearchField()){
            query += field.getFieldName() + ":"+field.getFieldValue() +",";
        }
        query = query.substring(0,query.length()-1);
        query += "}";
        Long count = mongoDao.count(tableName,query);
        pm.setTotal(count.intValue());
        search.setPage((info.getOffset()/info.getPagesize())+1);
        search.setPageSize(info.getPagesize());
        List data = mongoDao.query(tableName,query, search.getPage(), search.getPageSize());
        pm.setDatas(data);
        return pm;
    }


    /**
     * 查询mongo
     *  @param    tableName, search  查询对象
     * @return    : com.xfs.common.page.PageModel
     *  @createDate   : 2016/11/28 21:16
     *  @author          : yangfw@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2016/11/28 21:16
     *  @updateAuthor  :
     */
    @Override
    public List<Map<String,Object>> findAllMongoData(String tableName, List<SearchField> searchFields){
        List<Map<String,Object>> data = mongoDao.query(tableName, searchFields);
        return data;
    }

    /**
     * 查询mongo
     *  @param    tableName, search  查询对象
     * @return    : com.xfs.common.page.PageModel
     *  @createDate   : 2016/11/28 21:16
     *  @author          : yangfw@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2016/11/28 21:16
     *  @updateAuthor  :
     */
    @Override
    public <T> List<T> findAllMongoData(String tableName, List<SearchField> searchFields,Class<T> c){
        List<Map<String,Object>> data = mongoDao.query(tableName, searchFields);
        Gson gson=new Gson();
        if(CollectionUtils.isNotEmpty(data)){
            List<T> rsList = new ArrayList<>();
            for(Map<String,Object> map:data){
                rsList.add(gson.fromJson(JSON.toJSONString(map),c));
            }
            return rsList;
        }
        return null;
    }

    /**
     * 通过条件查询唯一数据
     *  @param
     * @return    :
     *  @createDate   : 2016/12/1 17:42
     *  @author          : yangfw@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2016/12/1 17:42
     *  @updateAuthor  :
     */
    @Override
    public Map<String,Object> findOneBySearch(String tableName,Search search){
        String query = "{";
        for(SearchField field:search.getAndSearchField()){
            query += field.getFieldName() + ":'"+field.getFieldValue() +"',";
        }
        query = query.substring(0,query.length()-1);
        query += "}";
        List<Map<String,Object>> list = mongoDao.query(tableName,query);
        return list == null ? null : list.get(0);
    }

    /**
     * 通过id 修改数据字段  会自动在key上加 单引号
     *  @param   tableName, fields, id]
     * @return    : java.lang.Boolean
     *  @createDate   : 2016/12/3 14:45
     *  @author          : yangfw@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2016/12/3 14:45
     *  @updateAuthor  :
     */
    @Override
    public Boolean updateMongoModelByPk(String tableName,List<SearchField> fields,String id){
        String json = "{";
        for(SearchField field:fields){
            json += "'"+field.getFieldName()+"':";
            if(field.getFieldValue() instanceof String){
                json += "'"+ field.getFieldValue()+"',";
            }else if(field.getFieldValue() instanceof Double || field.getFieldValue() instanceof Integer || field.getFieldValue() instanceof BigDecimal ){
                json += field.getFieldValue().toString()+",";
            }else {
                json += JSON.toJSONString(field.getFieldValue())+",";
            }
        }
        json = json.substring(0,json.length() -1);
        json += "}";
        return mongoDao.updateByPrimaryKey(tableName,json,id);
    }

    /**
     * 通过查询条件修改对象
     *  @param   tableName, query, fields
     * @return    : java.lang.Boolean
     *  @createDate   : 2016/12/3 14:58
     *  @author          : yangfw@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2016/12/3 14:58
     *  @updateAuthor  :
     */
    @Override
    public Boolean updateMongoModel(String tableName,List<SearchField> query,List<SearchField> fields){
        String json = "{";
        for(SearchField field:fields){
            json += "'"+field.getFieldName()+"':";
            if(field.getFieldValue() instanceof String){
                json += "'"+ field.getFieldValue()+"',";
            }else if(field.getFieldValue() instanceof Double || field.getFieldValue() instanceof Integer || field.getFieldValue() instanceof BigDecimal ){
                json += field.getFieldValue().toString()+",";
            }else {
                json += JSON.toJSONString(field.getFieldValue())+",";
            }
        }
        json = json.substring(0,json.length() -1);
        json += "}";
        return mongoDao.update(tableName,query,json);
    }

    /**
     * 通过id查询实体
     * @param tableName
     * @param id
     * @param c
     * @return
     * @throws Exception
     */
    public T findMongoByPk(String tableName,String id,Class<T> c) throws Exception{
        String json = "{'_id':'" + id+"'}";
        Gson gson=new Gson();
        List<Map<String,Object>> list = mongoDao.query(tableName,json);
        if(CollectionUtils.isNotEmpty(list)){
//            Object o = c.newInstance();
//            BeanUtils.copyProperties(o,list.get(0));
            return gson.fromJson(JSON.toJSONString(list.get(0)),c);//(T)o;
        }
        return null;
    }
    /**
     * 通过id查询实体
     * @param tableName
     * @param query
     * @param c
     * @return
     * @throws Exception
     */
    public <T> List<T> findMongoByQuer(String tableName,List<SearchField> query,Class<T> c) throws Exception{

        String json = "{";
        for(SearchField field:query){
            json += "'"+field.getFieldName()+"':";
            if(field.getFieldValue() instanceof String){
                json += "'"+ field.getFieldValue()+"',";
            }else {
                json += field.getFieldValue().toString()+",";
            }
        }
        json = json.substring(0,json.length() -1);
        json += "}";

        Gson gson=new Gson();
        List<Map<String,Object>> list = mongoDao.query(tableName,json);
        if(CollectionUtils.isNotEmpty(list)){
            List<T> rsList = new ArrayList<>();
            for(Map<String,Object> map:list){
//                Object o = c.newInstance();
//                BeanUtils.copyProperties(o,map);
                rsList.add(gson.fromJson(JSON.toJSONString(map),c));
            }
            return rsList;
        }
        return null;
    }
}
