package com.xfs.mall.customer.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xfs.common.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.xfs.common.mongo.MongoDao;
import com.xfs.common.page.PageModel;
import com.xfs.common.util.DateUtil;
import com.xfs.common.util.StringUtils;

/**
 * Created by aixin duan on 2016/7/27.
 */
@Service
public class IMMsgServiceImpl implements IMMsgService {

	private final String TABLENAME = "xfs.rong_im_message";

	@Autowired
	private MongoDao mongoDao;

	/**
	 * 保存IM消息
	 *
	 * @param jsonStr
	 */
	@Override
	public void saveMassage(String jsonStr) {
		mongoDao.insert(TABLENAME, jsonStr);
	}

	/**
	 * IM消息列表
	 *
	 * @param fromUserId
	 * @param toUserId
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	@Override
	public PageModel findMassages(PageInfo pi, Integer fromUserId, Integer toUserId, String beginTime, String endTime,
								  String searchWord) {
		PageModel pm = new PageModel(pi);
		pi.setPagesize(20); // 默认20条
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();

		pageIndex = pageIndex / pageSize + 1;

		String json = getConditionJson(fromUserId.toString(), toUserId.toString(), beginTime, endTime, searchWord);
		pm.setTotal((int) mongoDao.count(TABLENAME, json));
		mongoDao.query(TABLENAME, json, pageIndex, pageSize);
		List<Map<String, Object>> list = mongoDao.query(TABLENAME, json, pageIndex, pageSize);
		if (list != null) {
			for (Map<String, Object> map : list) {
				Long msgTimestamp = (Long) map.get("msgTimestamp");
				if (msgTimestamp != null) {
					map.put("msgTimestamp", new Date(msgTimestamp));
				}
			}
		}
		pm.setDatas(list);
		return pm;
	}

	/**
	 * mongodb conditionJson
	 *
	 * @param fromUserId
	 * @param toUserId
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public String getConditionJson(String fromUserId, String toUserId, String beginTime, String endTime,
			String content) {
		if (!toUserId.startsWith("U")) {
			toUserId = "U" + toUserId;
		}
		Map<String, String> map0 = new HashMap<>();
		map0.put("fromUserId", fromUserId);
		map0.put("toUserId", toUserId);
		Map<String, String> map1 = new HashMap<>();
		map1.put("fromUserId", toUserId);
		map1.put("toUserId", fromUserId);
		List<Map<String, String>> orList = new ArrayList<>();
		orList.add(map0);
		orList.add(map1);
		// {"$or":[{"fromUserId": fromUserId, "toUserId": toUserId},
		// {"fromUserId": toUserId, "toUserId": fromUserId}]}
		Map<String, Object> map = new HashMap<>();
		map.put("$or", orList);
		// {"time": {"$gte": begintime, "$lte": endtime}}
		Map<String, Object> map2 = new HashMap<>();
		if (!StringUtils.isBlank(beginTime)) {
			map2.put("$gte", DateUtil.parseDate(beginTime, "yyyy-MM-dd HH:mm:ss").getTime());
		}
		if (!StringUtils.isBlank(endTime)) {
			map2.put("$lte", DateUtil.parseDate(endTime, "yyyy-MM-dd HH:mm:ss").getTime());
		}
		if (!map2.isEmpty()) {
			map.put("msgTimestamp", map2);
		}
		// {"content.content": {"$regex":".*content.*"}}
		if (!StringUtils.isBlank(content)) {
			Map<String, String> map3 = new HashMap<>();
			map3.put("$regex", ".*" + content + ".*");
			map.put("content.content", map3);
		}
		return JSONArray.toJSONString(map);
	}

	/**
	 * mongodb conditionJson
	 *
	 * 
	 * @return json
	 */

	@Override
	public PageModel findAll(PageInfo pi) {
		PageModel pm = new PageModel(pi);
		// pi.setPagesize(20); // 默认20条
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();

		pageIndex = pageIndex / pageSize + 1;
		List<Map<String, Object>> list = mongoDao.query(TABLENAME, "{}", pageIndex, pageSize);
		pm.setTotal((int) mongoDao.count(TABLENAME, "{}"));
		pm.setDatas(list);
		return pm;
	}

	@Override
	public PageModel findmsg(PageInfo pi, String name, String targetName, Date beginTime, Date endTime) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();

		pageIndex = pageIndex / pageSize + 1;

		String json = getJson(name, targetName, beginTime, endTime);
		List<Map<String, Object>> list = mongoDao.query(TABLENAME, json, pageIndex, pageSize);
		pm.setTotal((int) mongoDao.count(TABLENAME, json));
		if (list != null) {
			for (Map<String, Object> map : list) {
				Long msgTimestamp = (Long) map.get("msgTimestamp");
				if (msgTimestamp != null) {
					map.put("msgTimestamp", new Date(msgTimestamp));
				}
			}
		}
		pm.setDatas(list);
		return pm;
	}

	public String getJson(String name, String targetName, Date beginTime, Date endTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		Map<String, String> map0 = new HashMap<String, String>();
		map0.put("content.extra.name", name);
		map0.put("content.extra.targetName", targetName);
		
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("content.extra.name", targetName);
		map1.put("content.extra.targetName", name);
		
		List<Map<String, String>> orList = new ArrayList<Map<String, String>>();
		orList.add(map0);
		orList.add(map1);
		
		map.put("$or", orList);
		
		Map<String, Object> map2 = new HashMap<>();
		map2.put("$gte", beginTime.getTime());
		map2.put("$lte", endTime.getTime());

		if (!map2.isEmpty()) {
		}
		return JSONArray.toJSONString(map);
	}
}
