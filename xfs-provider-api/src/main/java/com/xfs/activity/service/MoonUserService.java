package com.xfs.activity.service;

import com.xfs.activity.model.MoonUser;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

import java.util.List;
/**
 * 
* @ClassName: MoonUserService 
* @Description: 中秋注册人员
* @author duanax@xinfushe.com
* @date 2016年11月15日 上午11:29:31 
*
 */
public interface MoonUserService {

	public int save(ContextInfo cti, MoonUser vo);
	public int insert(ContextInfo cti, MoonUser vo);
	public int remove(ContextInfo cti, MoonUser vo);
	public int update(ContextInfo cti, MoonUser vo);
	public PageModel findPage(PageInfo pi, MoonUser vo);
	public List<MoonUser> findAll(MoonUser vo);


	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/06 11:20:20
	
	/**
	 * 
	* @Title: queryPic 
	* @Description: 查找用户照片
	* @param @param vo
	* @param @return    设定文件 
	* @return MoonUser    返回类型 
	* @throws
	 */
	public MoonUser queryPic(MoonUser vo);
	/**
	 * 
	* @Title: updateUserState 
	* @Description: 更新用户状态
	* @param @param vo
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int updateUserState(ContextInfo cti,MoonUser vo);
	public int updateRemark(ContextInfo cti,MoonUser vo);

	/**
	 * 上传图片
	 */
//	public String uploadFile(File file);// {
	/**
	 * 
	* @Title: readFileById 
	* @Description: 根据文件id读文件
	* @param @param fileId
	* @param @return    设定文件 
	* @return GridFSDBFile    返回类型 
	* @throws
	 */
//	public GridFSDBFile readFileById(String fileId);

}
