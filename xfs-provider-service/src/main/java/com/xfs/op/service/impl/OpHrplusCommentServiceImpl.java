package com.xfs.op.service.impl;

import com.xfs.base.model.SysMessage;
import com.xfs.base.service.SysMessageService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.common.page.SystemContext;
import com.xfs.common.util.HtmlUtil;
import com.xfs.common.util.StringUtils;
import com.xfs.op.dao.OpHrplusCommentDao;
import com.xfs.op.dto.OpHrPlushDto;
import com.xfs.op.dto.OpHrplusCommentDto;
import com.xfs.op.model.OpHrplusComment;
import com.xfs.op.service.OpHrplusCommentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OpHrplusCommentServiceImpl implements OpHrplusCommentService {

    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(OpHrplusCommentServiceImpl.class);

    @Autowired
    private OpHrplusCommentDao opHrplusCommentDao;
    @Autowired
    private SysMessageService sysMessageService;

    public int save(ContextInfo cti, OpHrplusComment vo) {
        return opHrplusCommentDao.save(cti, vo);
    }

    public int insert(ContextInfo cti, OpHrplusComment vo) {
        return opHrplusCommentDao.insert(cti, vo);
    }

    public int remove(ContextInfo cti, OpHrplusComment vo) {
        return opHrplusCommentDao.remove(cti, vo);
    }

    public int update(ContextInfo cti, OpHrplusComment vo) {
        return opHrplusCommentDao.update(cti, vo);
    }

    public PageModel findPage(OpHrplusComment vo) {

        PageModel pm = new PageModel();
        int pageIndex = SystemContext.getOffset();
        int pageSize = SystemContext.getPagesize();
        Integer total = opHrplusCommentDao.countFreeFind(vo);
        pm.setTotal(total);
        List<OpHrplusComment> datas = opHrplusCommentDao.freeFind(vo, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;

    }

    public List<OpHrplusComment> findAll(OpHrplusComment vo) {

        List<OpHrplusComment> datas = opHrplusCommentDao.freeFind(vo);
        return datas;

    }


    //温馨提示：
    //以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    //2017/01/05 20:57:18


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
    @Override
    public List<OpHrplusCommentDto> freeFindDto(OpHrplusComment obj) {
        return opHrplusCommentDao.freeFindDto(obj);
    }

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
    @Override
    public Integer saveContentMessage(ContextInfo cti, Integer contentId, Integer todoUser, String type, Integer commentId) {
        Map<String, String> contentInfo = opHrplusCommentDao.findContentInfo(contentId);
        SysMessage message = new SysMessage();
        message.setTodoUserType("XINFUJIA");
        message.setTodoUser(todoUser);
        message.setTodoOrg(-1);
        if (contentInfo != null && !contentInfo.isEmpty()) {
            message.setTitle(contentInfo.get("title"));
            String content = contentInfo.get("txt");
            if (StringUtils.isNotBlank(content)) {
                content = HtmlUtil.getTextFromHtml(content);
                message.setContent(content.length() > 150 ? content.substring(0, 150) + "..." : content);
            }
            message.setType(type);
            message.setState("TODO");
            message.setSendUser(cti.getUserId());
            message.setSendUserType("XINFUJIA");
            message.setSendOrg(-1);
            message.setSendTime(new Date());
            message.setDataId(commentId);
            message.setDr(0);
            return sysMessageService.save(cti, message);
        }
        return 0;
    }

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
    @Override
    public Integer saveSysMessage(ContextInfo cti, Integer contentId, Integer todoUser, Integer sysType, String msg) {
        Map<String, String> contentInfo = opHrplusCommentDao.findContentInfo(contentId);
        SysMessage message = new SysMessage();
        message.setTodoUserType("XINFUJIA");
        message.setTodoUser(todoUser);
        message.setTodoOrg(-1);
        if (contentInfo != null && !contentInfo.isEmpty()) {
            message.setTitle(contentInfo.get("title"));
            message.setContent(msg);
            message.setType("SYS");
            message.setState("TODO");
            message.setSendUser(-1);
            message.setSendUserType("XINFUJIA");
            message.setSendOrg(-1);
            message.setSendTime(new Date());
            message.setDataId(contentId);
            message.setDealUser(sysType);//1.审核通过 2.审核失败 3.问答评论删除 4.攻略评论删除
            message.setDr(0);
            return sysMessageService.save(cti, message);
        }
        return 0;
    }

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
    @Override
    public PageModel findManagePage(PageInfo pageInfo, Map<String, Object> query) {
        PageModel pm = new PageModel(pageInfo);
        Integer total = opHrplusCommentDao.countManageFreeFind(query);
        pm.setTotal(total);
        pm.setDatas(opHrplusCommentDao.findManagePage(query, pageInfo.getOffset(), pageInfo.getPagesize()));
        return pm;
    }

    @Override
    public OpHrplusComment findByPk(OpHrplusComment comment) {
        return opHrplusCommentDao.findByPK(comment);
    }

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
    @Override
    public PageModel freeFindDtoPage(PageInfo pageInfo, OpHrplusComment obj) {
        PageModel pm = new PageModel(pageInfo);
        Integer total = opHrplusCommentDao.countFreeFindDto(obj);
        pm.setTotal(total);
        pm.setDatas(opHrplusCommentDao.freeFindDtoPage(obj, pageInfo.getOffset(), pageInfo.getPagesize()));
        return pm;
    }

    @Override
    public PageModel findCommentPageByMongoArticleId(String mongoArticleId, PageInfo pageInfo) {
        PageModel pm = new PageModel(pageInfo);
        OpHrplusComment obj = new OpHrplusComment();
        obj.setMongoArticleId(mongoArticleId);
        Integer total = opHrplusCommentDao.countFreeFindDto(obj);
        pm.setTotal(total);
        pm.setDatas(opHrplusCommentDao.findCommentPageByMongoArticleId(mongoArticleId, pageInfo.getOffset(), pageInfo.getPagesize()));
        return pm;
    }

    @Override
    public PageModel findCommentPageByQuery(Map<String,Object> obj, PageInfo pageInfo) {
        PageModel pm = new PageModel(pageInfo);
        Integer total = opHrplusCommentDao.countCommentPageByQuery(obj);
        pm.setTotal(total);
        pm.setDatas(opHrplusCommentDao.findCommentPageByQuery(obj, pageInfo.getOffset(), pageInfo.getPagesize()));
        return pm;
    }

    @Override
    public List<OpHrPlushDto> findCommentBycommentId(Integer commentId) {
        return opHrplusCommentDao.findCommentByCommentId(commentId);
    }

    /**
     * 删除评论
     *
     * @param cti
     * @param commentId
     */
    @Override
    public void deleteCommentByCommentId(ContextInfo cti, Integer commentId) {
        opHrplusCommentDao.deleteCommentByCommentId(cti, commentId);
    }

    /**
     * 更新点赞数
     *
     * @param commentId
     * @return 当前点赞数量
     */
    @Override
    public Integer updateUpvoteNum(Integer commentId) {

        return opHrplusCommentDao.updateUpvoteNum(null, commentId);
    }


}
