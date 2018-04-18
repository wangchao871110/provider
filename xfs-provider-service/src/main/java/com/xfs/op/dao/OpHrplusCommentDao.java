package com.xfs.op.dao;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.op.dto.OpHrPlushDto;
import com.xfs.op.dto.OpHrplusCommentDto;
import com.xfs.op.model.OpHrplusComment;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * OpHrplusCommentDao
 *
 * @author:yangfw@xinfushe.com
 * @date:2017/01/05 20:57:18
 */
@Repository
public class OpHrplusCommentDao extends BaseSqlMapDao {

    public int countFindAll() {
        Integer ret = (Integer) queryForObject("OP_HRPLUS_COMMENT.CountFindAllOP_HRPLUS_COMMENT", null);
        return ret.intValue();
    }

    public OpHrplusComment findByPK(OpHrplusComment obj) {
        Object ret = queryForObject("OP_HRPLUS_COMMENT.FindByPK", obj);
        if (ret != null)
            return (OpHrplusComment) ret;
        else
            return null;
    }

    @SuppressWarnings("unchecked")
    public List<OpHrplusComment> freeFind(OpHrplusComment obj) {
        return queryForList("OP_HRPLUS_COMMENT.FreeFindOP_HRPLUS_COMMENT", obj);
    }

    public int countFreeFind(OpHrplusComment obj) {
        Integer ret = (Integer) queryForObject("OP_HRPLUS_COMMENT.CountFreeFindOP_HRPLUS_COMMENT", obj);
        return ret.intValue();
    }

    @SuppressWarnings("unchecked")
    public List<OpHrplusComment> freeFind(OpHrplusComment obj, int pageIndex, int pageSize) {
        return getPaginatedList("OP_HRPLUS_COMMENT.FreeFindOP_HRPLUS_COMMENT", obj, pageIndex, pageSize);
    }

    @SuppressWarnings("unchecked")
    public List<OpHrplusComment> freeFind(OpHrplusComment obj, String orderByColName) {
        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        if (obj instanceof OpHrplusComment) {
            _hashmap = ((OpHrplusComment) obj).toHashMap();
        }
        _hashmap.put("_orderBy", orderByColName);

        return queryForList("OP_HRPLUS_COMMENT.FreeFindOP_HRPLUS_COMMENTOrdered", _hashmap);
    }

    @SuppressWarnings("unchecked")
    public List<OpHrplusComment> freeFind(OpHrplusComment obj, int pageIndex, int pageSize, String orderByColName) {
        HashMap<String, Object> _hashmap = new HashMap<String, Object>();
        if (obj instanceof OpHrplusComment) {
            _hashmap = ((OpHrplusComment) obj).toHashMap();
        }
        _hashmap.put("_orderBy", orderByColName);

        return getPaginatedList("OP_HRPLUS_COMMENT.FreeFindOP_HRPLUS_COMMENTOrdered", _hashmap, pageIndex, pageSize);
    }

    public int saveAll(ContextInfo cti, Collection<OpHrplusComment> objColl) {
        int i = 0;
        if (objColl != null) {
            Iterator<OpHrplusComment> it = objColl.iterator();
            while (it.hasNext()) {
                OpHrplusComment oneObj = (OpHrplusComment) it.next();
                i += save(cti, oneObj);
            }
        }
        return i;
    }

    public int save(ContextInfo cti, OpHrplusComment vo) {
        OpHrplusComment obj = (OpHrplusComment) vo;

        if (exists(obj)) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
    }

    public int insert(ContextInfo cti, OpHrplusComment obj) {

        obj.fixup();
        return insert(cti, "OP_HRPLUS_COMMENT.InsertOP_HRPLUS_COMMENT", obj);
    }

    public int update(ContextInfo cti, OpHrplusComment obj) {

        obj.fixup();
        return update(cti, "OP_HRPLUS_COMMENT.UpdateOP_HRPLUS_COMMENT", obj);

    }

    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
    public int remove(ContextInfo cti, OpHrplusComment obj) {

        if (obj == null) {
            return 0;
        }

        obj.fixup();

        return delete(cti, "OP_HRPLUS_COMMENT.DeleteOP_HRPLUS_COMMENT", obj);

    }

    public int removeObjectTree(ContextInfo cti, OpHrplusComment obj) {

        obj.fixup();
        OpHrplusComment oldObj = (OpHrplusComment) queryForObject("OP_HRPLUS_COMMENT.FindByPK", obj);
        if (oldObj == null) {
            return 0;
        }


        return delete(cti, "OP_HRPLUS_COMMENT.DeleteOP_HRPLUS_COMMENT", oldObj);

    }

    public boolean exists(OpHrplusComment vo) {

        boolean keysFilled = true;
        boolean ret = false;
        OpHrplusComment obj = (OpHrplusComment) vo;

        keysFilled = keysFilled && (null != obj.getId());

        if (keysFilled) {
            Integer retInt = (Integer) queryForObject("OP_HRPLUS_COMMENT.CountOP_HRPLUS_COMMENT", obj);
            if (retInt != null && retInt.intValue() > 0) {
                ret = true;
            }
        }

        return ret;
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
    public List<OpHrplusCommentDto> freeFindDto(OpHrplusComment obj) {

        return queryForList("OP_HRPLUS_COMMENT.FreeFindOP_HRPLUS_COMMENT_DTO_Ordered", obj);
    }

    /**
     * 查询文章评论总数
     *
     * @param obj
     * @return : java.util.List<com.xfs.op.dto.OpHrplusCommentDto>
     * @createDate : 2017/1/6 17:36
     * @author : yangfw@xinfushe.com
     * @version : v1.0
     * @updateDate : 2017/1/6 17:36
     * @updateAuthor :
     */
    public Integer countFreeFindDto(OpHrplusComment obj) {

        return (Integer) queryForObject("OP_HRPLUS_COMMENT.Count_OP_HRPLUS_COMMENT_DTO", obj);
    }

    /**
     * 分页查询文章评论dto类
     *
     * @param obj
     * @return : java.util.List<com.xfs.op.dto.OpHrplusCommentDto>
     * @createDate : 2017/1/6 17:36
     * @author : yangfw@xinfushe.com
     * @version : v1.0
     * @updateDate : 2017/1/6 17:36
     * @updateAuthor :
     */
    public List<OpHrplusCommentDto> freeFindDtoPage(OpHrplusComment obj, Integer offset, Integer pageSize) {

        return getPaginatedList("OP_HRPLUS_COMMENT.FreeFindOP_HRPLUS_COMMENT_DTO_Ordered", obj, offset, pageSize);
    }

    /**
     * 查询文章信息
     *
     * @param contentId 文章id
     * @return : java.util.Map<java.lang.String,java.lang.String>
     * @createDate : 2017/1/9 15:43
     * @author : yangfw@xinfushe.com
     * @version : v1.0
     * @updateDate : 2017/1/9 15:43
     * @updateAuthor :
     */
    public Map<String, String> findContentInfo(Integer contentId) {
        return (Map<String, String>) queryForObject("OP_HRPLUS_COMMENT.FindContentInfo", contentId);
    }


    /**
     * 评论管理 总数查询
     *
     * @param obj
     * @return : int
     * @createDate : 2017/1/12 10:01
     * @author : yangfw@xinfushe.com
     * @version : v1.0
     * @updateDate : 2017/1/12 10:01
     * @updateAuthor :
     */
    public int countManageFreeFind(Map<String, Object> obj) {
        Integer ret = (Integer) queryForObject("OP_HRPLUS_COMMENT.finManageComentListCount", obj);
        return ret.intValue();
    }

    /**
     * 评论管理 分页查询
     *
     * @param obj, offset, pagesize
     * @return : java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @createDate : 2017/1/12 10:01
     * @author : yangfw@xinfushe.com
     * @version : v1.0
     * @updateDate : 2017/1/12 10:01
     * @updateAuthor :
     */
    public List<Map<String, Object>> findManagePage(Map<String, Object> obj, Integer offset, Integer pagesize) {
        return getPaginatedList("OP_HRPLUS_COMMENT.finManageComentList", obj, offset, pagesize);
    }

    /**
     * 根据文章id  和 页数 获取 评论
     *
     * @param mongoArticleId
     * @param offset
     * @param pagesize
     * @return
     * @createDate 2017年4月26日
     */
    public List<OpHrPlushDto> findCommentPageByMongoArticleId(String mongoArticleId, Integer offset, Integer pagesize) {
        Map obj = new HashMap();
        obj.put("mongoArticleId", mongoArticleId);
        return getPaginatedList("OP_HRPLUS_COMMENT.findCommentPageByMongoArticleId", obj, offset, pagesize);
    }

    /**
     *   和 页数 获取 评论 opduan
     *
     * @param obj
     * @param offset
     * @param pagesize
     * @return
     * @createDate 2017年4月26日
     */
    public List<OpHrPlushDto> findCommentPageByQuery(Map<String,Object> obj, Integer offset, Integer pagesize) {
        return getPaginatedList("OP_HRPLUS_COMMENT.findCommentPageByQuery", obj, offset, pagesize);
    }

    /**
     *   和 页数 获取 评论 opduan count
     *
     * @param obj
     * @return
     * @createDate 2017年4月26日
     */
    public int countCommentPageByQuery(Map<String,Object> obj) {
        Integer ret = (Integer) queryForObject("OP_HRPLUS_COMMENT.countCommentPageByQuery", obj);
        return ret.intValue();
    }



    /**
     * 根据文章id  和 页数 获取 评论
     *
     * @param commentId
     * @return
     * @createDate 2017年4月26日
     */
    public List<OpHrPlushDto> findCommentByCommentId(Integer commentId) {
        Map obj = new HashMap();
        obj.put("commentId", commentId);
        return queryForList("OP_HRPLUS_COMMENT.findCommentByCommentId", obj);
    }

    /**
     * 增加点赞数
     *
     * @param id
     * @param cti 用户上下文
     * @return 返回 点赞后的赞数量
     * @createDate 2017年4月26日
     */
    public Integer updateUpvoteNum(ContextInfo cti, Integer id) {
        Map obj = new HashMap();
        obj.put("id", id);
        update(cti, "OP_HRPLUS_COMMENT.updateUpvoteNum", obj);
        return getUpvoteNumById(id);

    }

    /**
     * 根据id 获取 点赞数量
     *
     * @param id
     * @return
     * @createDate 2017年4月26日
     */
    public Integer getUpvoteNumById(Integer id) {
        Map obj = new HashMap();
        obj.put("id", id);
        Integer retInt = (Integer) queryForObject("OP_HRPLUS_COMMENT.getUpvoteNumById", obj);
        return retInt;
    }


    /**
     * 递归删除所有的评论
     *
     * @param cti
     * @param parentComment
     * @return
     * @createDate 2017年4月26日
     */
    public Integer deleteCommentByCommentId(ContextInfo cti, Integer parentComment) {

        Map obj = new HashMap();
        obj.put("id", parentComment);
        Integer res = (Integer) delete(cti, "OP_HRPLUS_COMMENT.deleteCommentByCommentId", obj);
        return res;
//        List<OpHrplusComment> res = queryForList("OP_HRPLUS_COMMENT.deleteCommentByCommentId", obj);
//        if (CollectionUtils.isNotEmpty(res)) {
//            for (OpHrplusComment vo : res) {
//                if (vo.getParentComment() != null) {
//                    deleteCommentByCommentId(cti, vo.getParentComment());
//                    Map objPar = new HashMap();
//                    objPar.put("id", vo.getParentComment());
//                    Integer retInt = (Integer) delete(cti, "OP_HRPLUS_COMMENT.deleteCommentByCommentId", objPar);
//                } else {
//                    Map objPar = new HashMap();
//                    objPar.put("id", vo.getParentComment());
//                    Integer retInt = (Integer) delete(cti, "OP_HRPLUS_COMMENT.deleteCommentByCommentId", objPar);
//                }
//
//            }
//        }
    }


}
