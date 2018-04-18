package com.xfs.openlist.service;

import java.util.List;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.sp.model.SpsAreamaterial;

/**
 * SpsAreamaterialService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/04/09 16:45:54
 */
public interface SpsAreamaterialService {
	
	public int save(ContextInfo cti, SpsAreamaterial vo);
	public int insert(ContextInfo cti, SpsAreamaterial vo);
	public int remove(ContextInfo cti, SpsAreamaterial vo);
	public int update(ContextInfo cti, SpsAreamaterial vo);
	public PageModel findPage(PageInfo pi, SpsAreamaterial vo);
	public List<SpsAreamaterial> findAll(SpsAreamaterial vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/04/09 16:45:54

    /**
     *  根据区域和材料名称获取区域材料
     *  @param    ：  vo 区域材料实体
     *  @return    :  SpsAreamaterial
     *  @createDate   : 16-11-13 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-13 上午10:47
     *  @updateAuthor  :
     */
	SpsAreamaterial findByAraeAndName(SpsAreamaterial vo);

    /**
     *  根据主键id查询区域材料
     *  @param    ：  obj 区域材料实体
     *  @return    :  SpsAreamaterial
     *  @createDate   : 16-11-13 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-13 上午10:47
     *  @updateAuthor  :
     */
	SpsAreamaterial findByPK(SpsAreamaterial obj);

    /**
     *  根据区域材料实体查询区域材料（含排序）
     *  @param    ：  vo 区域材料实体
     *  @param   ：    orderByColName 按什么字段排序
     *  @return    :  List<SpsAreamaterial>
     *  @createDate   : 16-11-13 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-13 上午10:47
     *  @updateAuthor  :
     */
	List<SpsAreamaterial> findAll(SpsAreamaterial vo, String orderByColName);

    /**
     *  根据区openlistid跟材料类型体查询区域材料
     *  @param    ：  olid openlistid
     *  @param   ：    materialType 材料类型
     *  @return    :   List<SpsAreamaterial>
     *  @createDate   : 16-11-13 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-13 上午10:47
     *  @updateAuthor  :
     */
	public List<SpsAreamaterial> findOlMaterialList(Integer olid, Integer materialType);
	

}
