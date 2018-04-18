package com.xfs.op.service.impl;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.op.dao.OpHeadlineShortUrlDao;
import com.xfs.op.model.OpHeadlineShortUrl;
import com.xfs.op.service.OpHeadlineShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author : luyong@xinfushe.com
 * @version : V1.0
 * @date : 2017/4/18 14:23
 */
@Service
public class OpHeadlineShortUrlServiceImpl implements OpHeadlineShortUrlService {


    @Autowired
    private OpHeadlineShortUrlDao opHeadlineShortUrlDao;

    /**
     * 根据主键查询
     * @param shortId
     * @return
     */
    public OpHeadlineShortUrl selectByPrimaryKey(String shortId){
       return opHeadlineShortUrlDao.selectByPrimaryKey(shortId);
    }

    /**
     * 根据主键删除
     * @param shortId
     * @return
     */
    public Integer deleteByPrimaryKey(ContextInfo contextInfo , String shortId){
        return opHeadlineShortUrlDao.deleteByPrimaryKey(contextInfo, shortId);
    }

    /**
     * 新增
     * @param contextInfo
     * @param opHeadlineShortUrl
     * @return
     */
    public Integer insertSelective(ContextInfo contextInfo , OpHeadlineShortUrl opHeadlineShortUrl){
        return opHeadlineShortUrlDao.insertSelective(contextInfo, opHeadlineShortUrl);
    }

    /**
     * 修改
     * @param contextInfo
     * @param opHeadlineShortUrl
     * @return
     */
    public Integer updateByPrimaryKeySelective(ContextInfo contextInfo , OpHeadlineShortUrl opHeadlineShortUrl){
        return opHeadlineShortUrlDao.updateByPrimaryKeySelective(contextInfo, opHeadlineShortUrl);
    }


    /**
     * 查询Page
     * @param
     * @param
     * @return
     */
    public PageModel findList(PageInfo pageInfo, Map<String, Object> query){
        PageModel pm = new PageModel(pageInfo);
        Integer total = opHeadlineShortUrlDao.countList(query);
        pm.setTotal(total);
        pm.setDatas(opHeadlineShortUrlDao.findList(query, pageInfo.getOffset(), pageInfo.getPagesize()));
        return pm;
    }
    /**
     * countlist
     * @param
     * @param
     * @return
     */
    public Integer countList(Map<String, Object> param){
        return opHeadlineShortUrlDao.countList( param);
    }


    /**
     * updateTimes
     * @param
     * @param
     * @return
     */
    public Integer updateTimes(ContextInfo contextInfo ,OpHeadlineShortUrl opHeadlineShortUrl){
        return opHeadlineShortUrlDao.updateTimes(contextInfo,opHeadlineShortUrl);
    }
}
