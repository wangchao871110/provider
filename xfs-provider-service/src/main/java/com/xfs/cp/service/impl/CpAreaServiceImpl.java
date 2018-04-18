package com.xfs.cp.service.impl;

import static com.xfs.common.redies.keychain.IRedisKeys.CP_AREA;
import static com.xfs.common.redies.keychain.IRedisKeys.EXPIRE_DAY;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.common.redies.RedisUtil;
import com.xfs.common.util.StringUtils;
import com.xfs.cp.dao.CpAreaDao;
import com.xfs.cp.model.CpArea;
import com.xfs.cp.service.CpAreaService;

@Service
public class CpAreaServiceImpl implements CpAreaService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(CpAreaServiceImpl.class);
	
	@Autowired
	private CpAreaDao cpAreaDao;
	
	public int save(ContextInfo cti, CpArea vo ){
		return cpAreaDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  CpArea vo ){
		return cpAreaDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  CpArea vo ){
		return cpAreaDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  CpArea vo ){
		return cpAreaDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, CpArea vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cpAreaDao.countFreeFind(vo);
		pm.setTotal(total);
		List<CpArea> datas = cpAreaDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<CpArea> findAll(CpArea vo){
		
		List<CpArea> datas = cpAreaDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/10 16:19:46
	
	/**
	 * 获取发包城市
	 */
	@Override
	public List<CpArea> findCityByPackage(CpArea cpArea) {
		List<CpArea> datas = cpAreaDao.findCityByPackage(cpArea);
		return datas;
	}
	
	/**
	 * 根据拼音获取类型
	 *
	 * @param pinyin
	 * @return
	 */
	public CpArea getAreaByPinyin(String pinyin) {
		if (StringUtils.isBlank(pinyin)) {
			return null;
		}
		Object o = RedisUtil.get(CP_AREA);
		if (o != null && o instanceof Map) {
			return (CpArea)((Map) o).get(pinyin);
		}
		CpArea vo = new CpArea();
		vo.setDr(0);
		List<CpArea> areas = findAll(vo);
		if (areas == null || areas.isEmpty()) {
			return null;
		}
		Map<String, CpArea> areaMap = new HashMap<>();
		for (CpArea area: areas) {
			if (StringUtils.isBlank(area.getPinyin())) {
				continue;
			}
			areaMap.put(area.getPinyin(), area);
		}
		RedisUtil.set(CP_AREA, areaMap, EXPIRE_DAY);
		return areaMap.get(pinyin);
	}
	
}
