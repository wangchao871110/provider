package com.xfs.op.dao;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.op.model.OpHeadlineShortUrl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author : luyong@xinfushe.com
 * @version : V1.0
 * @date : 2017/4/18 11:55
 */
@Repository
public class OpHeadlineShortUrlDao extends BaseSqlMapDao {


    /**
     * 根据主键查询
     * @param shortId
     * @return
     */
    public OpHeadlineShortUrl selectByPrimaryKey(String shortId){
        Object ret = queryForObject("OP_HEADLINE_SHORT_URL.selectByPrimaryKey",shortId);
        if(ret !=null)
            return (OpHeadlineShortUrl)ret;
        else
            return null;
    }

    /**
     * 根据主键删除
     * @param shortId
     * @return
     */
    public Integer deleteByPrimaryKey(ContextInfo contextInfo , String shortId){
     return update(contextInfo ,"OP_HEADLINE_SHORT_URL.deleteByPrimaryKey",shortId);
    }

    /**
     * 新增
     * @param contextInfo
     * @param opHeadlineShortUrl
     * @return
     */
    public Integer insertSelective(ContextInfo contextInfo , OpHeadlineShortUrl opHeadlineShortUrl){
        return insert(contextInfo, "OP_HEADLINE_SHORT_URL.insertSelective", opHeadlineShortUrl);
    }

    /**
     * 修改
     * @param contextInfo
     * @param opHeadlineShortUrl
     * @return
     */
    public Integer updateByPrimaryKeySelective(ContextInfo contextInfo , OpHeadlineShortUrl opHeadlineShortUrl){
        return update(contextInfo, "OP_HEADLINE_SHORT_URL.updateByPrimaryKeySelective", opHeadlineShortUrl);
    }


    /**
     * 查询Page
     * @param
     * @param
     * @return
     */
    public List<OpHeadlineShortUrl> findList(Map<String, Object> param
                                              ,Integer offset,Integer pagesize){
        return getPaginatedList("OP_HEADLINE_SHORT_URL.findList", param,offset,pagesize);
    }

    /**
     * countlist
     * @param
     * @param
     * @return
     */
    public Integer countList(Map<String, Object> param){
        Integer retInt=(Integer)queryForObject("OP_HEADLINE_SHORT_URL.countList", param);
        return retInt == null?0 :retInt;
    }

    /**
     * updateTimes
     * @param
     * @param
     * @return
     */
    public Integer updateTimes(ContextInfo contextInfo ,OpHeadlineShortUrl opHeadlineShortUrl){
        if(opHeadlineShortUrl.getUseTime() == null || opHeadlineShortUrl.getUseTime() < 1)
            return 0;
        return update(contextInfo,"OP_HEADLINE_SHORT_URL.updateTimes",opHeadlineShortUrl);
    }



}
