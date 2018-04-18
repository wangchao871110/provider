package com.xfs.op.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.xfs.common.PageInfo;
import com.xfs.common.mongo.MongoDao;
import com.xfs.common.mongo.enums.SearchType;
import com.xfs.common.mongo.vo.SearchField;
import com.xfs.common.page.PageModel;
import com.xfs.common.util.TimeUtils;
import com.xfs.op.dto.ContentQuery;
import com.xfs.op.dto.SysCrawlerResult;
import com.xfs.op.service.HeadlineContentService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by yangf on 2017/3/11.
 */
@Service("headlineContentService")
public class HeadlineContentServiceImpl implements HeadlineContentService{
    static Logger log = Logger.getLogger(HeadlineContentServiceImpl.class);
    @Autowired
    private MongoDao mongoDao;

    //发送日志的生产者
    ProducerBean producerBean;
    private static final String CONTENT_TABLE_NAME = "SysCrawlerResult";

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
    @Override
    public PageModel findModelPage(PageInfo info, ContentQuery query){
        PageModel pm = new PageModel();
        StringBuilder queryBuilder = new StringBuilder("{");
        if(query.getTagId() != null){
            if(query.getTagId() == 0){
                queryBuilder.append("'$or':[{tagId:{$exists:false}},{tagId:'0'}],");
            }else {
                queryBuilder.append("tagId:'"+query.getTagId()+"',");
            }
        }
        if(query.getRecState() != null){
            if(query.getRecState() == 0){
                queryBuilder.append("'$or':[{recState:{$exists:false}},{recState:0}],");
            }else {
                queryBuilder.append("recState:"+query.getRecState()+",");
            }
        }
        if(query.getRelState() != null){
            if(query.getRelState() == 0){
                queryBuilder.append("'$or':[{relState:{$exists:false}},{relState:0}],");
            }else {
                queryBuilder.append("relState:"+query.getRelState()+",");
            }
        }

        if(query.getUpdateState() != null){
            if(query.getUpdateState() == 0){
                queryBuilder.append("'$or':[{updateState:{$exists:false}},{updateState:0}],");
            }else {
                queryBuilder.append("updateState:"+query.getUpdateState()+",");
            }
        }
        if(StringUtils.isNoneBlank(query.getModifyName())){
            queryBuilder.append("modifyName:/"+query.getModifyName()+"/,");
        }
        if(StringUtils.isNoneBlank(query.getCreateName())){
            queryBuilder.append("createName:/"+query.getCreateName()+"/,");
        }
        String between = "";
        if(StringUtils.isNoneBlank(query.getStartCreateTime()) ||
                StringUtils.isNoneBlank(query.getEndCreateTime())){
            if(StringUtils.isNoneBlank(query.getStartCreateTime())){
                between="pubDate:{$gte:\'"+query.getStartCreateTime() +"\'";
            }
            if(StringUtils.isNoneBlank(query.getEndCreateTime())){
                if(StringUtils.isBlank(between)){
                    between="pubDate:{$lte:\'"+query.getEndCreateTime() +"\'";
                }else {
                    between+=",$lte:\'"+query.getEndCreateTime() + "\'";
                }
            }
            between += "},";
            queryBuilder.append(between);
        }
        if(StringUtils.isNoneBlank(query.getStartModTime()) ||
                StringUtils.isNoneBlank(query.getEndModTime())){
             between = "";
            if(StringUtils.isNoneBlank(query.getStartModTime())){
                between="modefyDt:{$gte:\'"+query.getStartModTime() +"\'";
            }
            if(StringUtils.isNoneBlank(query.getEndModTime())){
                if(StringUtils.isBlank(between)){
                    between="modefyDt:{$lte:\'"+query.getEndModTime() +"\'";
                }else {
                    between+=",$lte:\'"+query.getEndModTime() + "\'";
                }
            }
            between += "},";
            queryBuilder.append(between);
        }
        if(StringUtils.isNotBlank(query.getTitle())){
            queryBuilder.append("title:/"+query.getTitle()+"/,");
        }
        String queryStr = queryBuilder.length() > 1 ? queryBuilder.substring(0,queryBuilder.length()-1) :queryBuilder.toString();
        queryStr += "}";
        String sort = "{'createDt':-1}";
        pm.setPagesize(info.getPagesize());
        Long count = mongoDao.count(CONTENT_TABLE_NAME,queryStr);
        pm.setTotal(count.intValue());
        Integer pageNum = (info.getOffset()/info.getPagesize())+1;
        List data = mongoDao.query(CONTENT_TABLE_NAME,queryStr, pageNum, info.getPagesize(),sort);
        pm.setDatas(data);
        return pm;
    }

    /**
     * 推荐文章
     *  @param   id
     * @return    : java.lang.Boolean
     *  @createDate   : 2017/3/11 16:47
     *  @author          : yangfw@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 2017/3/11 16:47
     *  @updateAuthor  :
     */
    @Override
    public Boolean relContent(String id,Integer state){
        List<SearchField> fields = new ArrayList<>();
        SearchField field = new SearchField("_id",id, SearchType.Equal);
        fields.add(field);
        String set = "{'recState':"+state+"}";
        return mongoDao.update(CONTENT_TABLE_NAME,fields,set);
    }


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
    @Override
    public Boolean timeReleaseContent(){
        Date date = new Date();
        String time = TimeUtils.convertDateToString(date,"yyyy-MM-dd");
        String query = "{'pubDate':{'$lte':'"+time+"'},'relState':1}";
        List<Map<String,Object>> data = mongoDao.query(CONTENT_TABLE_NAME,query);
        if(CollectionUtils.isNotEmpty(data)){
            Map<String, String> mq = new HashMap();
            List<SearchField> fields = new ArrayList<>();
            SearchField field = new SearchField("_id","", SearchType.Equal);
            fields.add(field);
            for(Map m: data){
                String id =  m.get("_id").toString();
                mq.put("objectId", id);
                try {
                    Message message = new Message(producerBean.getProperties().getProperty("Topic"), "crawler", JSON.toJSONString(mq).getBytes());
                    producerBean.sendOneway(message);
                    log.info(TimeUtils.convertDateToString(date,"yyyy-MM-dd HH:mm:ss") + " Send mq message success! Topic is:" + producerBean.getProperties().getProperty("Topic") + "msgId is: ");

                } catch (Exception e) {
                    log.error("**************** Headline mq发送异常 **************** id:"+id, e);
                    continue;
                }
                field.setFieldValue(id);
                String set = "{'relState':2}";
                mongoDao.update(CONTENT_TABLE_NAME,fields,set);
            }
        }
        return true;
    }


    public boolean insert(SysCrawlerResult crawlerResult) {
        return mongoDao.insert(CONTENT_TABLE_NAME, JSONObject.toJSONString(crawlerResult));
    }

    public SysCrawlerResult findCrawlerResult(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        Map<String, Object> resultMap = mongoDao.getDataByPrimaryKey(CONTENT_TABLE_NAME, id);
        if (resultMap == null || resultMap.isEmpty()) {
            return null;
        }
        SysCrawlerResult result = JSONObject.parseObject(JSONObject.toJSONString(resultMap), SysCrawlerResult.class);
        if (result == null) {
            return null;
        }
        result.set_id(id);
        return result;
    }

    public boolean update(SysCrawlerResult crawlerResult) {
        List<SearchField> fields = new ArrayList<>();
        SearchField field = new SearchField("_id", crawlerResult.get_id());
        fields.add(field);
        return mongoDao.update(CONTENT_TABLE_NAME, fields, JSONObject.toJSONString(crawlerResult));
    }
    public boolean updateAdv(SysCrawlerResult crawlerResult) {
        List<SearchField> fields = new ArrayList<>();
        SearchField field = new SearchField("relState", 2);
        fields.add(field);
        return mongoDao.update(CONTENT_TABLE_NAME, fields, JSONObject.toJSONString(crawlerResult));
    }



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
    @Override
    public Boolean updateRelContent(String contentId){
        if(StringUtils.isBlank(contentId)){
            return false;
        }
        Date date = new Date();
        Map<String, String> mq = new HashMap();
        mq.put("objectId", contentId);
        mq.put("action", "2");
        try {
            Message message = new Message(producerBean.getProperties().getProperty("Topic"), "crawler", JSON.toJSONString(mq).getBytes());
            producerBean.sendOneway(message);
            log.info(TimeUtils.convertDateToString(date,"yyyy-MM-dd HH:mm:ss") + " Send mq message success! Topic is:" + producerBean.getProperties().getProperty("Topic") + "msgId is: ");

        } catch (Exception e) {
            log.error("**************** Headline update mq发送异常 **************** id:"+contentId, e);
            return false;
        }
        return true;
    }


    /**
     * 删除
     * @param id
     * @return
     */
    public Boolean delContent(String id){
       return mongoDao.deleteByPrimaryKey(CONTENT_TABLE_NAME,id);
    }
}
