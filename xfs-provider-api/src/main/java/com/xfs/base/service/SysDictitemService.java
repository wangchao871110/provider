package com.xfs.base.service;

import java.util.List;
import java.util.Map;

import com.xfs.base.model.SysDictitem;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;

 
/**
 * SysDictitemService
 *
 * @author:quanjiahua<quanjiahua@163.com>
 * 
 * @date:2015/12/10 17:19:51
 */
public interface SysDictitemService {

	public void save(ContextInfo cti, SysDictitem vo);

	public void insert(ContextInfo cti, SysDictitem vo);

	public void remove(ContextInfo cti, SysDictitem vo);

	public void update(ContextInfo cti, SysDictitem vo);

	public PageModel findPage(PageInfo pi, SysDictitem vo);

	public List<SysDictitem> findAll(SysDictitem vo);

	public void deleteByDictionary(ContextInfo cti, SysDictitem vo);

	public List<SysDictitem> findByDictName(String dictName);

	/**
	 * 根据主键查询
	 *
	 * @param id
	 * @return
	 */
	public SysDictitem findByPK(Integer id);

	/**
	 * 根据code获取数据字典
	 * 
	 * @param code
	 * @return
	 */
	public SysDictitem findByCode(String code);

	/**
	 * 根据 区域表中code 反查社保缴交地 areaid上一级
	 * 
	 * @param areacode
	 * @return
	 */
	public List<SysDictitem> findByAreaCode(String areacode);
    
    /**
     * 根据 areaId查询 字典信息
     *
     * @author lifq
     *
     * 2016年6月1日  下午4:25:47
     */
    public List<Map<String, String>> findDictItemsByAreaId(Integer areaId);
	public List<SysDictitem> findByDictNameAndArea(String dictName,Integer areaId);

	public List<SysDictitem> findSysDicitem(SysDictitem sysDictitem);
	/**
	 * 根据区域id查询 社保参保类型
	 *
	 * @author lifq
	 *
	 * 2016年9月25日  下午4:02:16
	 */
	public List<Map<String, Object>> findLiveTypeByAreaId(Map<String,Object> paraMap);
	/**
	 * 获取所有地区去重后的参保类型
	 *
	 * @author yangfw
	 *
	 * 2016年12月20日  下午4:02:16
	 */
	public List<Map<String, Object>> findLiveTypeDISTINCT();

	/**
	 * 重置地区户口性质
	 *  @param   cti, areaId, dictitems]
	 * @return    : com.xfs.common.Result
	 *  @createDate   : 2016/12/20 16:53
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/12/20 16:53
	 *  @updateAuthor  :
	 */
	public Result resetAreaLivingType(ContextInfo cti, Integer areaId, List<SysDictitem> dictitems);
	
	/**
	 * 根据城市id 获取户口性质
	 *  @param area_ids
	 *  @return 
	 *	@return 			: List<SysDictitem> 
	 *  @createDate  	: 2016年12月27日 下午4:07:31
	 *  @author         	: songby 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016年12月27日 下午4:07:31
	 *  @updateAuthor  :
	 */
	public List<String> getSysDictitem(Map<String, Object> areacode);
}
