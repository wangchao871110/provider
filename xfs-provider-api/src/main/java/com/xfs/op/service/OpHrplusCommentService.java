package com.xfs.op.service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.op.dto.OpHrPlushDto;
import com.xfs.op.dto.OpHrplusCommentDto;
import com.xfs.op.model.OpHrplusComment;

import java.util.List;
import java.util.Map;

/**
 * OpHrplusCommentService
 *
 * @author:yangfw@xinfushe.com
 * @date:2017/01/05 20:57:18
 */
public interface OpHrplusCommentService {

    public int save(ContextInfo cti, OpHrplusComment vo);

    public int insert(ContextInfo cti, OpHrplusComment vo);

    public int remove(ContextInfo cti, OpHrplusComment vo);

    public int update(ContextInfo cti, OpHrplusComment vo);

    public PageModel findPage(OpHrplusComment vo);

    public List<OpHrplusComment> findAll(OpHrplusComment vo);


    //温馨提示：
    //以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    //2017/01/05 20:57:18
    public OpHrplusComment findByPk(OpHrplusComment comment);

    /**
     * 查询文章评论dto类
     *
     * @param obj
     * @return : java.util.List<com.xfs.op.dto.OpHrplusCommentDto>
     * @createDate : 2017/1/6 17:36
     * @author : yangfw@xinfushe.com
     * @version : v1.0
     * @updateDate : 2017/1/6 17:36
     * @updateAuthor :
     */
    public List<OpHrplusCommentDto> freeFindDto(OpHrplusComment obj);

    /**
     * 保存文章消息
     *
     * @param cti, contentId 文章信息, todoUser 作者, type 类型：攻略/问答, commentId 回复id
     * @return : java.lang.Integer
     * @createDate : 2017/1/9 16:08
     * @author : yangfw@xinfushe.com
     * @version : v1.0
     * @updateDate : 2017/1/9 16:08
     * @updateAuthor :
     */
    public Integer saveContentMessage(ContextInfo cti, Integer contentId, Integer todoUser, String type, Integer commentId);

    /**
     * 保存系统消息
     *
     * @param cti, contentId, todoUser, sysType, msg
     * @return : java.lang.Integer
     * @createDate : 2017/1/9 19:58
     * @author : yangfw@xinfushe.com
     * @version : v1.0
     * @updateDate : 2017/1/9 19:58
     * @updateAuthor :
     */
    public Integer saveSysMessage(ContextInfo cti, Integer contentId, Integer todoUser, Integer sysType, String msg);

    /**
     * 评论管理 批量查询
     *
     * @param pageInfo, query
     * @return : com.xfs.common.page.PageModel
     * @createDate : 2017/1/12 14:21
     * @author : yangfw@xinfushe.com
     * @version : v1.0
     * @updateDate : 2017/1/12 14:21
     * @updateAuthor :
     */
    public PageModel findManagePage(PageInfo pageInfo, Map<String, Object> query);

    /**
     * 分页查询文章评论
     *
     * @param pageInfo, obj]
     * @return : com.xfs.common.page.PageModel
     * @createDate : 2017/1/18 11:48
     * @author : yangfw@xinfushe.com
     * @version : v1.0
     * @updateDate : 2017/1/18 11:48
     * @updateAuthor :
     */
    public PageModel freeFindDtoPage(PageInfo pageInfo, OpHrplusComment obj);

    /**
     * 增加点赞数
     *
     * @param commentId
     * @return 返回 点赞后的赞数量
     */
    public Integer updateUpvoteNum(Integer commentId);


    /**
     * 分页查询 文章评论
     *
     * @param mongoArticleId
     * @param pageInfo
     * @return
     * @createDt 2017年4月26日
     */
    public PageModel findCommentPageByMongoArticleId(String mongoArticleId, PageInfo pageInfo);


    /**
     * 分页查询 文章评论 op 使用
     * @param obj
     * @param pageInfo
     * @return
     */
    public PageModel findCommentPageByQuery(Map<String,Object> obj, PageInfo pageInfo);

    /**
     * 分页查询 文章评论
     *
     * @param commentId
     * @return
     * @createDt 2017年4月26日
     */
    List<OpHrPlushDto> findCommentBycommentId(Integer commentId);


    /**
     * 递归删除所有的评论
     *
     * @param cti
     * @param commentId
     * @return
     * @createDate 2017年4月26日
     */
    public void deleteCommentByCommentId(ContextInfo cti, Integer commentId);


}
