package com.xfs.mall.customer.service;

import java.util.Date;

import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * Created by aixin duan on 2016/7/27.
 */
public interface IMMsgService {

    /**
     * 保存IM消息
     *
     * @param jsonStr
     */
    public void saveMassage(String jsonStr);

    /**
     * IM消息列表
     *
     * @param fromUserId
     * @param toUserId
     * @param beginTime
     * @param endTime
     * @return
     */
    public PageModel findMassages(PageInfo pi, Integer fromUserId, Integer toUserId, String beginTime, String endTime, String searchWord);
   
    public PageModel findAll(PageInfo pi);
    
    public PageModel findmsg(PageInfo pi, String name, String targetName, Date beginTime, Date endTime);
}
