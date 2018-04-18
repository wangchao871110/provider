package com.xfs.op.service;

import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.op.dto.ContentQuery;
import com.xfs.op.dto.SysCrawlerResult;

/**
 * 文章管理
 * Created by yangf on 2017/3/11.
 */
public interface HeadlineContentService {
    /**
     * 分页查询文章
     *  @param   info, query
     * @return    : com.xfs.common.page.PageModel
     *  @createDate   : 2017/3/11 15:34
     *  @author          : yangfw@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2017/3/11 15:34
     *  @updateAuthor  :
     */
    public PageModel findModelPage(PageInfo info, ContentQuery query);/**
     * 推荐文章
     *  @param   id
     * @return    : java.lang.Boolean
     *  @createDate   : 2017/3/11 16:47
     *  @author          : yangfw@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2017/3/11 16:47
     *  @updateAuthor  :
     */
    public Boolean relContent(String id,Integer state);

    /**
     * 插入数据
     *
     * @param crawlerResult
     * @return
     */
    public boolean insert(SysCrawlerResult crawlerResult);

    /**
     * 根据id查询详情
     *
     * @param id
     * @return
     */
    public SysCrawlerResult findCrawlerResult(String id);

    /**
     * 编辑数据
     *
     * @param crawlerResult
     * @return
     */
    public boolean update(SysCrawlerResult crawlerResult);

    /**
     * 广告
     *
     * @param crawlerResult
     * @return
     */
    public boolean updateAdv(SysCrawlerResult crawlerResult);
    /**
     * 定时发布文章
     *  @param
     * @return    : java.lang.Boolean
     *  @createDate   : 2017/3/11 21:41
     *  @author          : yangfw@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2017/3/11 21:41
     *  @updateAuthor  :
     */
    public Boolean timeReleaseContent();


    /**
     * 删除
     * @param id
     * @return
     */
    public Boolean delContent(String id);


    /**
     * 更新发布文章
     *  @param
     * @return    : java.lang.Boolean
     *  @createDate   : 2017/3/11 21:41
     *  @author          : yangfw@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2017/3/11 21:41
     *  @updateAuthor  :
     */
    public Boolean updateRelContent(String contentId);
}
