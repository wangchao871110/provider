package com.xfs.business.service;

import com.xfs.business.model.SpsFixedpointhospital;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

import java.util.List;

/**
 * SpsFixedpointhospitalService
 *
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/04/20 12:05:11
 */
public interface SpsFixedpointhospitalService {

    public int save(ContextInfo cti, SpsFixedpointhospital vo);

    public int insert(ContextInfo cti, SpsFixedpointhospital vo);

    public int remove(ContextInfo cti, SpsFixedpointhospital vo);

    public int update(ContextInfo cti, SpsFixedpointhospital vo);

    public PageModel findPage(PageInfo pi, SpsFixedpointhospital vo);

    public List<SpsFixedpointhospital> findAll(SpsFixedpointhospital vo);


    //温馨提示：
    //以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    //2016/04/20 12:05:11

    public List<SpsFixedpointhospital> findtenfive();

    public PageModel findPageHos(PageInfo pi, SpsFixedpointhospital vo);


    public List<SpsFixedpointhospital> getFixHosptialByHospitalCodes(List<String> hospitalCodes);


    /**
     * @param areaCode     地区类型
     * @param hosptialType 医院类型
     * @return
     * @Title: 根据地区id， 和 医院类型， 查看医院
     * @createDate 2017/6/12 11:57
     * @Auther:zhanghongjie【hongjievip6688@163.com】
     * @version : v1.0
     * @updateDate :
     * @updateAuthor :
     */
    public PageModel getHospitalByAreaIdAndType(PageInfo pageInfo,String condition,Integer reginCode,Integer areaCode, String hosptialType);


    /**
     * @param condition 查询条件
     * @return
     * @Title: 根据条件查询
     * @createDate 2017/6/12 11:59
     * @Auther:zhanghongjie【hongjievip6688@163.com】
     * @version : v1.0
     * @updateDate :
     * @updateAuthor :
     */
    public List<SpsFixedpointhospital> getHospitalByCondition(String condition);

}
