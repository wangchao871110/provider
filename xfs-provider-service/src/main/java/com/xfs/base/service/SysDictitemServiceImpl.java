package com.xfs.base.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.base.dao.SysDictitemDAO;
import com.xfs.base.model.SysDictitem;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;

@Service
public class SysDictitemServiceImpl implements SysDictitemService {

	private static final Logger log = Logger.getLogger(SysDictitemServiceImpl.class);
	@Autowired
	private SysDictitemDAO sysDictitemDAO;

	public void save(ContextInfo cti, SysDictitem vo) {
		sysDictitemDAO.save(cti, vo);
	}

	public void insert(ContextInfo cti, SysDictitem vo) {
		sysDictitemDAO.insert(cti, vo);
	}

	public void remove(ContextInfo cti, SysDictitem vo) {
		sysDictitemDAO.remove(cti, vo);
	}

	public void update(ContextInfo cti, SysDictitem vo) {
		sysDictitemDAO.update(cti, vo);
	}

	public PageModel findPage(PageInfo pi, SysDictitem vo) {

		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = sysDictitemDAO.countFreeFind(vo);
		pm.setTotal(total);
		List<SysDictitem> datas = sysDictitemDAO.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;

	}

	public List<SysDictitem> findAll(SysDictitem vo) {

		List<SysDictitem> datas = sysDictitemDAO.freeFind(vo);
		return datas;

	}

	@Override
	public void deleteByDictionary(ContextInfo cti, SysDictitem vo) {
		sysDictitemDAO.deleteByDictionary(cti, vo);
	}

	@Override
	public List<SysDictitem> findByDictName(String dictName) {
		return sysDictitemDAO.findByDictName(dictName);
	}

	@Override
	public List<SysDictitem> findByDictNameAndArea(String dictName,Integer areaId) {
		return sysDictitemDAO.findByDictNameAndArea(dictName,areaId);
	}

	@Override
	public SysDictitem findByPK(Integer id) {
		SysDictitem sysDictitem = new SysDictitem();
		sysDictitem.setId(id);
		return sysDictitemDAO.findByPK(sysDictitem);
	}

	@Override
	public SysDictitem findByCode(String code) {
		SysDictitem sysDictitem = new SysDictitem();
		sysDictitem.setCodeEq(code);
		List<SysDictitem> datas = sysDictitemDAO.freeFind(sysDictitem);
		if (null != datas && datas.size() != 0)
			return datas.get(0);
		else
			return null;
	}

	@Override
	public List<SysDictitem> findByAreaCode(String areacode) {
		List<SysDictitem> datas = sysDictitemDAO.findByAreaCode(areacode);
		return datas;
	}
    /**
     * 根据 areaId查询 字典信息
     *
     * @author lifq
     *
     * 2016年6月1日  下午4:25:47
     */
   public List<Map<String, String>> findDictItemsByAreaId(Integer areaId){
    	return sysDictitemDAO.findDictItemsByAreaId(areaId);
    }

	@Override
	public List<SysDictitem> findSysDicitem(SysDictitem sysDictitem) {
		return sysDictitemDAO.findSysDicitem(sysDictitem);
	}
	/**
	 * 根据区域id查询 社保参保类型
	 *
	 * @author lifq
	 *
	 * 2016年9月25日  下午4:02:16
	 */
	public List<Map<String, Object>> findLiveTypeByAreaId(Map<String,Object> paraMap){
		return sysDictitemDAO.findLiveTypeByAreaId(paraMap);
	}

	/**
	 * 获取所有地区去重后的参保类型
	 *
	 * @author yangfw
	 *
	 * 2016年12月20日  下午4:02:16
	 */
	public List<Map<String, Object>> findLiveTypeDISTINCT(){
		return sysDictitemDAO.findLiveTypeDISTINCT();
	}

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
	public Result resetAreaLivingType(ContextInfo cti,Integer areaId, List<SysDictitem> dictitems){
		Result result = new Result();
		SysDictitem query = new SysDictitem();
		query.setAreaId(areaId);
		query.setDictionary(93);
		sysDictitemDAO.deleteByDictionary(cti,query);
		if(CollectionUtils.isNotEmpty(dictitems)){
			sysDictitemDAO.saveAll(cti,dictitems);
		}
		return result;
	}

	@Override
	public List<String> getSysDictitem(Map<String, Object> areacode) {
		return sysDictitemDAO.getSysDictitem(areacode);
	}
}
