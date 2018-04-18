package com.xfs.business.service;

import com.xfs.business.dto.InsuranceCalcDto;
import com.xfs.business.dto.InsuranceTypeRatio;
import com.xfs.business.dto.PsnInsuCalcDto;
import com.xfs.common.PageInfo;
import com.xfs.common.mongo.vo.Search;
import com.xfs.common.mongo.vo.SearchField;
import com.xfs.common.page.PageModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 社保计算
 * Created by yangf on 2016/11/28.
 */
public interface InsuranceCalcSerivice {
    /**
     *  @param   areaId 地区id
     *  @param   wages 工资
     *  @param	 livingType 户口性质
     *  @param   isFund 是否缴纳公积金
     *  @param	 period 期间
     * @return    : com.xfs.business.dto.InsuranceCalcDto
     *  @createDate   : 2016/11/28 12:03
     *  @author          : konglc@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2016/11/28 12:03
     *  @updateAuthor  :
     */
	InsuranceCalcDto insuranceCalc(InsuranceCalcDto insuranceCalcDto);

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
    public InsuranceCalcDto insuranceCalc(PsnInsuCalcDto psnInsuCalcDto);
    /**
     * 批量在mongo中保存数据
     *  @param   tableName, list   mongo表明  存储数据
     * @return    : java.lang.Boolean
     *  @createDate   : 2016/11/28 20:52
     *  @author          : konglc@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2016/11/28 20:52
     *  @updateAuthor  :
     */
    public Boolean saveMongoList(String tableName, List list);

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
    public PageModel findMongoDataPage(PageInfo info, String tableName, Search search);
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
    public List<Map<String,Object>> findAllMongoData(String tableName, List<SearchField> searchFields);
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
    public Map<String,Object> findOneBySearch(String tableName,Search search);

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
    public Boolean saveMongoModel(String tableName,Object model);

    /**
     * 通过id 修改数据字段
     *  @param   tableName, fields, id]
     * @return    : java.lang.Boolean
     *  @createDate   : 2016/12/3 14:45
     *  @author          : yangfw@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2016/12/3 14:45
     *  @updateAuthor  :
     */
    public Boolean updateMongoModelByPk(String tableName,List<SearchField> fields,String id);/**
     * 通过查询条件修改对象
     *  @param   tableName, query, fields
     * @return    : java.lang.Boolean
     *  @createDate   : 2016/12/3 14:58
     *  @author          : yangfw@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2016/12/3 14:58
     *  @updateAuthor  :
     */
    public Boolean updateMongoModel(String tableName,List<SearchField> query,List<SearchField> fields);

    /**
     * 通过id查询实体
     * @param tableName
     * @param query
     * @param c
     * @return
     * @throws Exception
     */
    public <T> List<T> findMongoByQuer(String tableName,List<SearchField> query,Class<T> c) throws Exception;


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
    public <T> List<T> findAllMongoData(String tableName, List<SearchField> searchFields,Class<T> c);
}
