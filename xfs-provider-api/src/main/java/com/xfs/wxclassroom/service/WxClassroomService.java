package com.xfs.wxclassroom.service;

import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.wxclassroom.dto.WxClassroom;


/**
 * WxClassroomService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/08/29 15:20:43
 */
public interface WxClassroomService {

	public int save(ContextInfo cti, WxClassroom vo);

	public int insert(ContextInfo cti, WxClassroom vo);

	public int remove(ContextInfo cti, WxClassroom vo);

	public int update(ContextInfo cti, WxClassroom vo);

	public PageModel findPage(PageInfo pi, WxClassroom vo);

	public List<WxClassroom> findAll(WxClassroom vo);


	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/29 15:20:43

//	public List<WxClassroom> FreeFindAll(WxClassroom vo);

	/**
	 *全部查询课程表（已丢弃）
	 * @param vo WxClassroom
	 * @return List<Map<String, Object>>
     */
	public List<Map<String, Object>> FreeFindAll(WxClassroom vo);

	/**
	 * 通过课程ＩＤ查询课程
	 * @param i 课程ID
	 * @return WxClassroom
     */
	public WxClassroom findByClassPK(int i);

	/**
	 * 通过分享次数查询课程
	 * @param vo WxClassroom
	 * @return PageModel
     */

	public PageModel findByTimePage(PageInfo pi, WxClassroom vo);

}