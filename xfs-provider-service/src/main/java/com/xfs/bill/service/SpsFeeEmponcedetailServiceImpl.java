package com.xfs.bill.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.bill.dao.SpsFeeEmponcedetailDao;
import com.xfs.bill.model.SpsFeeEmponcedetail;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * 人员单次费用详情服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-10 14:58
 * @version 	: V1.0
 */
@Service
public class SpsFeeEmponcedetailServiceImpl implements SpsFeeEmponcedetailService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsFeeEmponcedetailServiceImpl.class);
	
	@Autowired
	private SpsFeeEmponcedetailDao spsFeeEmponcedetailDao;
	
	public int save(ContextInfo cti, SpsFeeEmponcedetail vo ){
		return spsFeeEmponcedetailDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsFeeEmponcedetail vo ){
		return spsFeeEmponcedetailDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsFeeEmponcedetail vo ){
		return spsFeeEmponcedetailDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsFeeEmponcedetail vo ){
		return spsFeeEmponcedetailDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsFeeEmponcedetail vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsFeeEmponcedetailDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsFeeEmponcedetail> datas = spsFeeEmponcedetailDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsFeeEmponcedetail> findAll(SpsFeeEmponcedetail vo){
		
		List<SpsFeeEmponcedetail> datas = spsFeeEmponcedetailDao.freeFind(vo);
		return datas;
		
	}


	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/04 11:35:50

	/**
	 *  获取员工补缴信息
	 *  @param   sp_id	服务商ID
	 *  @param   cp_id	企业ID
	 *  @param   emp_id	员工ID
	 *	@return 			: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 *  @createDate  	: 2016-11-09 15:45
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 15:45
	 *  @updateAuthor  :
	 */
	@Override
	public List<Map<String, Object>> queryEmpBackInusInfo(Integer sp_id, Integer cp_id, Integer emp_id) {
		return spsFeeEmponcedetailDao.queryEmpBackInusInfo(sp_id,cp_id,emp_id);
	}

	/**
	 *  获取员工补缴详细信息
	 *  @param   emp_fee_id
	 *	@return 			: java.util.List<com.xfs.bill.model.SpsFeeEmponcedetail>
	 *  @createDate  	: 2016-11-09 15:50
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 15:50
	 *  @updateAuthor  :
	 */
	@Override
	public List<SpsFeeEmponcedetail> queryEmpBackInusInfoList(Integer emp_fee_id) {
		return spsFeeEmponcedetailDao.queryEmpBackInusInfoList(emp_fee_id);
	}
	
	/**
	 * 查看补缴信息
	 *  @param sourceid
	 *  @param empId
	 *  @return 
	 *  @createDate  	: 2016年11月30日 下午2:39:02
	 *  @author         	: NDD
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016年11月30日 下午2:39:02
	 *  @updateAuthor  	:
	 */
	@Override
	public List<HashMap<String, Object>> findEmpBackInusInfo(Integer sourceid, Integer empId) {
		// TODO Auto-generated method stub
		return spsFeeEmponcedetailDao.findEmpBackInusInfo(sourceid, empId);
	}
	/**
	 * 查看汇缴信息
	 *  @param sourceid
	 *  @param empId
	 *  @return 
	 *  @createDate  	: 2016年11月30日 下午2:39:02
	 *  @author         	: NDD
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016年11月30日 下午2:39:02
	 *  @updateAuthor  	:
	 */
	@Override
	public List<LinkedHashMap<String, Object>> findEmpBackInusInfoHJ(String bstypeId,String taskId, Integer empId) {
		return spsFeeEmponcedetailDao.findEmpBackInusInfoHJ(bstypeId,taskId, empId);
	}
}
