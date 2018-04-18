package com.xfs.op.service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.op.model.OpHeadlineShortUrl;

import java.util.List;
import java.util.Map;

/**
 * @author : luyong@xinfushe.com
 * @version : V1.0
 * @date : 2017/4/18 14:22
 */
public interface OpHeadlineShortUrlService {

    /**
     * 根据主键查询
     * @param shortId
     * @return
     */
    public OpHeadlineShortUrl selectByPrimaryKey(String shortId);
    /**
     * 根据主键删除
     * @param shortId
     * @return
     */
    public Integer deleteByPrimaryKey(ContextInfo contextInfo , String shortId);

    /**
     * 新增
     * @param contextInfo
     * @param opHeadlineShortUrl
     * @return
     */
    public Integer insertSelective(ContextInfo contextInfo , OpHeadlineShortUrl opHeadlineShortUrl);

    /**
     * 修改
     * @param contextInfo
     * @param opHeadlineShortUrl
     * @return
     */
    public Integer updateByPrimaryKeySelective(ContextInfo contextInfo , OpHeadlineShortUrl opHeadlineShortUrl);


    /**
     * 查询Page
     * @param
     * @param
     * @return
     */
    public PageModel findList(PageInfo pageInfo, Map<String, Object> query);
    /**
     * countlist
     * @param
     * @param
     * @return
     */
    public Integer countList(Map<String, Object> param);

    /**
     * updateTimes
     * @param
     * @param
     * @return
     */
    public Integer updateTimes(ContextInfo contextInfo ,OpHeadlineShortUrl opHeadlineShortUrl);

}
