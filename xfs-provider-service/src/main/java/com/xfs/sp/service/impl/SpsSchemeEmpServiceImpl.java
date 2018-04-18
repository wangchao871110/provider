package com.xfs.sp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.bill.model.SpsBill;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.sp.dao.SpsSchemeEmpDao;
import com.xfs.sp.model.SpsSchemeEmp;
import com.xfs.sp.service.SpsSchemeEmpService;

@Service
public class SpsSchemeEmpServiceImpl implements SpsSchemeEmpService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsSchemeEmpServiceImpl.class);
	
	@Autowired
	private SpsSchemeEmpDao spsSchemeEmpDao;
	
	public int save(ContextInfo cti, SpsSchemeEmp vo ){
		return spsSchemeEmpDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsSchemeEmp vo ){
		return spsSchemeEmpDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsSchemeEmp vo ){
		return spsSchemeEmpDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsSchemeEmp vo ){
		return spsSchemeEmpDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsSchemeEmp vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsSchemeEmpDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsSchemeEmp> datas = spsSchemeEmpDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsSchemeEmp> findAll(SpsSchemeEmp vo){
		
		List<SpsSchemeEmp> datas = spsSchemeEmpDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/03 16:26:38
	
	/**
	 * 根据主键查询 实体
	 *
	 * @author lifq
	 *
	 * 2016年8月4日  上午9:58:03
	 */
	public SpsSchemeEmp findByPK(SpsSchemeEmp vo){
		return spsSchemeEmpDao.findByPK(vo);
	}
	
	@Override
	public List<SpsSchemeEmp> findSchemeEmpByEmpIds(Integer spId, List<Integer> empIds) {
		return spsSchemeEmpDao.findSchemeEmpByEmpIds(spId,empIds);
	}

    @Override
    public int removeByEmp(ContextInfo cti, SpsSchemeEmp vo) {
        return spsSchemeEmpDao.removeByEmpId(cti, vo);
    }
	/**
	 * 通过员工id与方案id修改状态
	 * @param vo
	 * @return
	 */
	@Override
	public int updateStateByEmpAndScheme(ContextInfo cti, SpsSchemeEmp vo){
		return spsSchemeEmpDao.updateStateByEmpAndScheme(cti, vo);
	}
	
	@Override
	public void addSchemeEmp(ContextInfo cti, Integer empid, Integer schemeId) {
		SpsSchemeEmp schemeEmp = new SpsSchemeEmp();
		schemeEmp.setEmpId(empid);
		schemeEmp.setSchemeId(schemeId);
		schemeEmp.setState("USE");
		schemeEmp.setDr(0);
		this.insert(cti, schemeEmp);

	}

	public int updateByEmp(ContextInfo cti, SpsSchemeEmp obj) {
		return spsSchemeEmpDao.updateByEmp(cti, obj);
	}
	/**
	 * 根据empIds 更新状态
	 *
	 * @author lifq
	 *
	 * 2017年8月1日  下午5:16:07
	 */
	public int updateStateByEmpIds(ContextInfo cti,Integer cpId,String empIds){
		return spsSchemeEmpDao.updateStateByEmpIds(cti,cpId, empIds);
	}
	
	/**
	 * 根据数据权限获取方案下的服务人员
	 *  @param cti
	 *  @param cpId
	 *  @param spId
	 *  @return 
	 *  @createDate  	: 2017年8月9日 上午10:32:10
	 *  @author         	: wangchao
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年8月9日 上午10:32:10
	 *  @updateAuthor  	:
	 */
	@Override
	public List<SpsSchemeEmp> findEmpByScheme(ContextInfo cti, Integer cpId, Integer spId) {
		return spsSchemeEmpDao.findEmpByScheme(cti, cpId, spId);
	}
}
