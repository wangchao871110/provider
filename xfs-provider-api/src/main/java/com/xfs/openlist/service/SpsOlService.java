package com.xfs.openlist.service;


import java.util.List;
import java.util.Map;

import com.xfs.base.model.SecWx;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.openlist.model.SpsOl;

/**
 * SpsOlService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/04/09 16:45:55
 */
public interface SpsOlService {
	
	public int save(ContextInfo cti, SpsOl vo);
	public int insert(ContextInfo cti, SpsOl vo);
	public int remove(ContextInfo cti, SpsOl vo);
	public int update(ContextInfo cti, SpsOl vo);
	public PageModel findPage(PageInfo pi, SpsOl vo);
	public List<SpsOl> findAll(SpsOl vo);
	public SpsOl findByPK(Integer olid);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/04/09 16:45:55

	/**
	 * 未发布状态
	 */
	int STATUS_UNPUBLISH = 0;

	/**
	 * 发布状态
	 */
	int STATUS_PUBLISHED = 1;

	/**
	 *   分页条件搜索openlist
	 *
	 * @param vo
	 * @param searchWord
	 * @return
     */
	PageModel customFind(PageInfo pi,SpsOl vo, String searchWord, String areaids);

	/**
	 * 查询自己的openlist
	 * @param vo
	 * @param searchWord
	 * @param areaids
     * @return
     */
	public PageModel queryMyOlList(PageInfo pi,SpsOl vo, String searchWord, String areaids);

	/**
	 * 根据id或者openlist详情
	 *
	 * @param vo
	 * @return
	 */
	SpsOl findByPK(SpsOl vo);

	/**
	 * 根据业务类型和区域查询
	 *
	 * @param vo
	 * @return
     */
	SpsOl findByBstypeAndArea(SpsOl vo);

	/**
	 * findOlByPK
	 *
	 * @param obj
	 * @return
	 */
	Map<String, Object> findOlByPK(SpsOl obj);

	public List<Map<String,Object>> findSpOIUsedCount(int spid);

	/**
	 * 获取当前微信用户openId
	 * @return
     */
	public String getVisitWxOpenId(SecWx wx, String code);

    /**
     * 获取服务商微信配置
     *  @param   : wx_id 微信id
     *  @return    :   Map<String,Object>
     *  @createDate   : 16-11-11 下午2:30
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 下午2:30
     *  @updateAuthor  :
     */
	public SecWx getSpWxConfig(String wx_id);

    /**
     * 根据模板类型查询OpenList模板，携带城市和材料总数
     *  @param   : ol 义务规则实体y
     *  @return    :   Map<String,Object>
     *  @createDate   : 16-11-11 下午2:30
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 下午2:30
     *  @updateAuthor  :
     */
	public Map<String,List<Map<String,Object>>> queryAllOLByMateialType(SpsOl ol);


    /**
     * 根据ID获取模板详情
     *  @param   : olId
     *  @return    :   Map<String,Object>
     *  @createDate   : 16-11-11 下午2:30
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 下午2:30
     *  @updateAuthor  :
     */
	public Map<String,Object> queryOLById(Integer olId);

    /**
     * 根据地区ID获取区域下的业务材料列表
     *  @param   : areaId 地区id
     *  @return    :   List<Map<String,Object>>
     *  @createDate   : 16-11-11 下午2:30
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 下午2:30
     *  @updateAuthor  :
     */
	public List<Map<String,Object>> queryAreaOlList(Integer areaId);

}
