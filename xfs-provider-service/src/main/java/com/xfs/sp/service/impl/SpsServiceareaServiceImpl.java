package com.xfs.sp.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.xfs.base.model.BsAreabusi;
import com.xfs.base.service.BsAreabusiService;
import com.xfs.common.Result;
import com.xfs.common.util.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.sp.dao.SpsServiceareaDao;
import com.xfs.sp.model.SpsServicearea;
import com.xfs.sp.service.SpsServiceareaService;

@Service
public class SpsServiceareaServiceImpl implements SpsServiceareaService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsServiceareaServiceImpl.class);

	@Autowired
	private SpsServiceareaDao spsServiceareaDao;

	@Autowired
	private BsAreabusiService bsAreabusiService;

	public int save(ContextInfo cti, SpsServicearea vo) {
		return spsServiceareaDao.save(cti, vo);
	}

	public int insert(ContextInfo cti, SpsServicearea vo) {
		return spsServiceareaDao.insert(cti, vo);
	}

	public int remove(ContextInfo cti, SpsServicearea vo) {
		return spsServiceareaDao.remove(cti, vo);
	}

	public int update(ContextInfo cti, SpsServicearea vo) {
		return spsServiceareaDao.update(cti, vo);
	}

	public PageModel findPage(PageInfo pi, SpsServicearea vo) {

		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsServiceareaDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsServicearea> datas = spsServiceareaDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;

	}

	public List<SpsServicearea> findAll(SpsServicearea vo) {

		List<SpsServicearea> datas = spsServiceareaDao.freeFind(vo);
		return datas;

	}

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/08/09 14:08:08

	/**
	 * 通过spId查找地区
	 * 
	 * @param spId
	 * @return
	 * @createDate : 2016年11月14日 下午4:12:20
	 * @author : 
	 * @version : v1.0
	 * @updateDate : 2016年11月14日 下午4:12:20
	 * @updateAuthor :
	 */
	public List<Map> FreeFindAreaBySpId(Integer spId) {
		List<Map> datas = spsServiceareaDao.FreeFindAreaBySpId(spId);
		return datas;
	}

	
	public List<Map> freeFindGROUP(String spId) {
		List<Map> datas = spsServiceareaDao.freeFindGROUP(spId);
		return datas;
	}

	public List<Map> freeFindCOOPAndINNER(String spId) {
		List<Map> datas = spsServiceareaDao.freeFindCOOPAndINNER(spId);
		return datas;
	}

	/**
	 * 服务商的服务区域
	 *
	 * @param vo
	 * @return
	 */
	public List<Map<String, String>> findServiceAreas(SpsServicearea vo) {
		return spsServiceareaDao.findServiceAreas(vo);
	}

	/**
	 * 查找服务商的服务区域通过spId
	 * 
	 * @param spId
	 * @return
	 * @createDate : 2016年11月14日 下午4:13:40
	 * @author :
	 * @version : v1.0
	 * @updateDate : 2016年11月14日 下午4:13:40
	 * @updateAuthor :
	 */
	public List<Map<String, Object>> findServiceAreasBySpId(String spId) {
		return spsServiceareaDao.findServiceAreasBySpId(spId);
	}


	@Override
	public List<Map> freeFindSpsServiceArea(SpsServicearea vo) {
		return spsServiceareaDao.freeFindSpsServiceArea(vo);
	}

	/**
	 *  保存服务商地区
	 *  @param
	 * @return    :
	 *  @createDate   : 2016/12/22 15:30
	 *  @author          : gaoyf@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2016/12/22 15:30
	 *  @updateAuthor  :
	 */
	@Override
	public Result saveSpsServiceArea(ContextInfo cti,SpsServicearea vo){
		Result result = Result.createResult().setSuccess(false);
		List<Map> ssaList = freeFindSpsServiceArea(vo);
		if(ssaList.size()>0){
			vo.setModifyDt(new Date());
			vo.setId(Integer.parseInt(ssaList.get(0).get("id")+""));
			result.setSuccess(update(cti,vo)>0);
		}else {
			SpsServicearea spsServicearea = spsServiceareaDao.getSpsServiceArea(vo);
			if(spsServicearea!=null){
				spsServicearea.setModifyDt(new Date());
				spsServicearea.setDr(0);
				update(cti,spsServicearea);
				result.setSuccess(update(cti,spsServicearea)>0);
			}else {

				BsAreabusi bsAreabusi = new BsAreabusi();
				bsAreabusi.setAreaId(vo.getAreaId());
				bsAreabusi.setBusi("MALL");
				BsAreabusi bab = bsAreabusiService.getBsAreabusi(bsAreabusi);
				if(bab == null){
					BsAreabusi babMax = bsAreabusiService.findBsAreabusiMax();
					if(babMax!=null){
						bsAreabusi.setOrderby(babMax.getOrderby()+1);
					}
					bsAreabusiService.insert(cti,bsAreabusi);
				}
				if(StringUtils.isBlank(vo.getRelType())){
					vo.setRelType("INNER");
				}
				vo.setDr(0);
				vo.setCreateDt(new Date());
				result.setSuccess(insert(cti, vo)>0);
			}
		}
		return result;
	}


	@Override
	public int removeSpsServiceArea(ContextInfo cti, String spId, String areaId) {
		return spsServiceareaDao.removeSpsServiceArea(cti, spId, areaId);
	}

	@Override
	public Map<String,String> findSpsServicearea(SpsServicearea vo) {
		return spsServiceareaDao.findSpsServicearea(vo);
	}
}
