package com.xfs.base.dao;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.base.model.SysDictitem;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * SysDictitemDAO
 * @author:quanjiahua<quanjiahua@163.com>
 * 
 * @date:2015/12/10 17:19:51
 */
@Repository
public class SysDictitemDAO extends BaseSqlMapDao {
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public int countFindAll() {
		Integer ret = (Integer) queryForObject("SYS_DICTITEM.CountFindAllSYS_DICTITEM", null);
		return ret.intValue();
	}

	public SysDictitem findByPK(SysDictitem obj) {
		Object ret = queryForObject("SYS_DICTITEM.FindByPK", obj);
		if (ret != null)
			return (SysDictitem) ret;
		else
			return null;
	}

	public List freeFind(SysDictitem obj) {
		return queryForList("SYS_DICTITEM.FreeFindSYS_DICTITEM", obj);
	}

	public int countFreeFind(SysDictitem obj) {
		Integer ret = (Integer) queryForObject("SYS_DICTITEM.CountFreeFindSYS_DICTITEM", obj);
		return ret.intValue();
	}

	public List freeFind(SysDictitem obj, int pageIndex, int pageSize) {
		return getPaginatedList("SYS_DICTITEM.FreeFindSYS_DICTITEM", obj, pageIndex, pageSize);
	}

	public List freeFind(SysDictitem obj, String orderByColName) {
		HashMap _hashmap = new HashMap();
		if (obj instanceof SysDictitem) {
			_hashmap = ((SysDictitem) obj).toHashMap();
		}
		_hashmap.put("_orderBy", orderByColName);

		return queryForList("SYS_DICTITEM.FreeFindSYS_DICTITEMOrdered", _hashmap);
	}

	public List freeFind(SysDictitem obj, int pageIndex, int pageSize, String orderByColName) {
		HashMap _hashmap = new HashMap();
		if (obj instanceof SysDictitem) {
			_hashmap = ((SysDictitem) obj).toHashMap();
		}
		_hashmap.put("_orderBy", orderByColName);

		return getPaginatedList("SYS_DICTITEM.FreeFindSYS_DICTITEMOrdered", _hashmap, pageIndex, pageSize);
	}

	public void saveAll(ContextInfo cti, Collection<SysDictitem> objColl) {
		if (objColl != null) {
			Iterator it = objColl.iterator();
			while (it.hasNext()) {
				SysDictitem oneObj = (SysDictitem) it.next();
				save(cti,oneObj);
			}
		}
	}

	public void save(ContextInfo cti, SysDictitem vo) {
		SysDictitem obj = (SysDictitem) vo;

		if (exists(obj)) {

			update(cti,obj);
		} else {
			obj.setCreateTime(format.format(new Date()));
			insert(cti,obj);
		}
	}

	public void insert(ContextInfo cti, SysDictitem obj) {

		obj.fixup();
		insert(cti,"SYS_DICTITEM.InsertSYS_DICTITEM", obj);
	}

	public void update(ContextInfo cti, SysDictitem obj) {

		obj.fixup();
		update(cti,"SYS_DICTITEM.UpdateSYS_DICTITEM", obj);

	}

	// remove children, then the obj
	// here we just remove, do not load obj tree.
	// removeObjectTree() function load obj tree first
	public void remove(ContextInfo cti, SysDictitem obj) {

		if (obj == null) {
			return;
		}

		obj.fixup();

		delete(cti,"SYS_DICTITEM.DeleteSYS_DICTITEM", obj);

	}

	public void removeObjectTree(ContextInfo cti, SysDictitem obj) {

		obj.fixup();
		SysDictitem oldObj = (SysDictitem) queryForObject("SYS_DICTITEM.FindByPK", obj);
		if (oldObj == null) {
			return;
		}

		delete(cti,"SYS_DICTITEM.DeleteSYS_DICTITEM", oldObj);

	}

	public boolean exists(SysDictitem vo) {

		boolean keysFilled = true;
		boolean ret = false;
		SysDictitem obj = (SysDictitem) vo;

		keysFilled = keysFilled && (null != obj.getId());

		if (keysFilled) {
			Integer retInt = (Integer) queryForObject("SYS_DICTITEM.CountSYS_DICTITEM", obj);
			if (retInt != null && retInt.intValue() > 0) {
				ret = true;
			}
		}

		return ret;
	}

	/**
	 * new qjh 2015/12/12 新增子集查询：findForItemByPK
	 */
	public void deleteByDictionary(ContextInfo cti, SysDictitem obj) {

		if (obj == null) {
			return;
		}

		obj.fixup();
		delete(cti,"SYS_DICTITEM.DeleteSYS_DICTITEMByDictionary", obj);

	}

	/**
	 * 根据字典名称 查看 字典项
	 *
	 * @return List<SysDictitem>
	 * @author lifq
	 * @date 2015-12-14 下午2:57:39
	 */
	public List<SysDictitem> findByDictName(String dictName) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("dictName", dictName);
		return queryForList("SYS_DICTITEM.findByDictName", map);
	}


	/**
	 * 根据字典名称与地区 查看 字典项
	 *
	 * @return List<SysDictitem>
	 * @author lifq
	 * @date 2015-12-14 下午2:57:39
	 */
	public List<SysDictitem> findByDictNameAndArea(String dictName,Integer areaId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dictName", dictName);
		map.put("areaId", areaId);
		return queryForList("SYS_DICTITEM.findByDictNameAndArea", map);
	}
	/**
     * 根据 areaId查询 字典信息
     *
     * @author lifq
     *
     * 2016年6月1日  下午4:25:47
     */
	public List<Map<String, String>> findDictItemsByAreaId(Integer areaId){
		Map<String,String> map = new HashMap<String,String>();
		map.put("areaId", areaId+"" ); 
		return queryForList("SYS_DICTITEM.findDictItemsByAreaId", map);
    }

	/**
	 * 根据传来的参数 查询社保缴交地区上级区域Id
	 */
	@SuppressWarnings("unchecked")
	public List<SysDictitem> findByAreaCode(String areacode) {
		return queryForList("SYS_DICTITEM.findByAreaCode", areacode);
	}

	public List<SysDictitem> findSysDictitem(String fieldName,Integer areaId) {
		HashMap<String,Object> map = new HashMap<String ,Object>();
		map.put("name",fieldName);
		map.put("areaId",areaId);
		return queryForList("SYS_DICTITEM.findSysDictitem", map);
	}

    /**
     * 
     * 查询员工证件类型
     */
    @SuppressWarnings("unchecked")
	public List<HashMap<String,Object>> findEmpDocType(){
    	return queryForList("SYS_DICTITEM.findEmpDocTypeMap", null);
    }

	/**
	 * 通过code查询livingType
	 * @param code
	 * @return
     */
	public List<SysDictitem> findLivingTypeByCode(String code) {
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("code", code);
		return queryForList("SYS_DICTITEM.FindSYS_DICTITEMByLivingCode", map);
	}

	public List<SysDictitem> findSysDicitem(SysDictitem  sysDictitem){
			return queryForList("SYS_DICTITEM.findSysDicitem", sysDictitem);
	}
	/**
	 * 根据区域id查询 社保参保类型
	 *
	 * @author lifq
	 *
	 * 2016年9月25日  下午4:02:16
	 */
	public List<Map<String, Object>> findLiveTypeByAreaId(Map<String,Object> paraMap){
		return queryForList("SYS_DICTITEM.findLiveTypeByAreaId", paraMap);
	}



	/**
	 * 获取所有地区去重后的参保类型
	 *
	 * @author yangfw
	 *
	 * 2016年12月20日  下午4:02:16
	 */
	public List<Map<String, Object>> findLiveTypeDISTINCT(){
		return queryForList("SYS_DICTITEM.findLiveTypeDISTINCT", null);
	}

	/**
	 * 根据城市id 获取户口性质
	 *  @param string
	 *  @param area_ids
	 *  @return 
	 *	@return 			: List<SysDictitem> 
	 *  @createDate  	: 2016年12月27日 下午4:23:17
	 *  @author         	: songby 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016年12月27日 下午4:23:17
	 *  @updateAuthor  :
	 */
	public List<String> getSysDictitem(Map<String, Object> areacode) {
		return queryForList("SYS_DICTITEM.getSysDictitem", areacode);
	}
}
