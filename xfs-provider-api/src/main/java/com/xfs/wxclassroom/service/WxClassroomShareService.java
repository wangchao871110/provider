package com.xfs.wxclassroom.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.wxclassroom.dto.WxClassroomShare;

/**
 * WxClassroomShareService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/29 15:20:42
 */
public interface WxClassroomShareService {
	
	public int save(ContextInfo cti, WxClassroomShare vo);
	public int insert(ContextInfo cti, WxClassroomShare vo);
	public int remove(ContextInfo cti, WxClassroomShare vo);
	public int update(ContextInfo cti, WxClassroomShare vo);
	public PageModel findPage(PageInfo pi, WxClassroomShare vo);
	public List<WxClassroomShare> findAll(WxClassroomShare vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/29 15:20:42

	/**
	 * 根据课程分享表ID查询课程分享
	 * @param i 课程ID
	 * @return List<WxClassroomShare>
     */

	public List<WxClassroomShare> findByClassroomPK(int i);

	/**
	 * 根据课程分享次数 ID更新分享次数
	 * @param vo WxClassroomShare
	 * @param shareTimes 分享次数
	 * @param shareId 分享ID
     * @return int
     */

	public int updateTimes(ContextInfo cti, WxClassroomShare vo, int shareTimes, int shareId);

	/**
	 * 根据课程分享的openid和classId 查询
	 * @param obj WxClassroomShare
	 * @return WxClassroomShare
     */

	public WxClassroomShare findByClassIdOpenid(WxClassroomShare obj);

	/**
	 * 分页查询分享表总数
	 * @param vo WxClassroomShare
	 * @return PageModel
     */
	public PageModel _findPage(PageInfo pi, WxClassroomShare vo);
}
