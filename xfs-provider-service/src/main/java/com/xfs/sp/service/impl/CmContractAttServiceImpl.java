package com.xfs.sp.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.common.util.StringUtils;
import com.xfs.sp.dao.CmContractAttDao;
import com.xfs.sp.dao.CmContractAttitemDao;
import com.xfs.sp.model.CmContract;
import com.xfs.sp.model.CmContractAtt;
import com.xfs.sp.model.CmContractAttitem;
import com.xfs.sp.service.CmContractAttService;
import com.xfs.user.model.SysUser;
import com.xfs.sp.service.CmContractService;

@Service
public class CmContractAttServiceImpl implements CmContractAttService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(CmContractAttServiceImpl.class);
	
	@Autowired
	private CmContractAttDao cmContractAttDao;
	@Autowired
	private CmContractAttitemDao cmContractAttItemDao;
	@Autowired
	private CmContractService cmContractService;
	public int save(ContextInfo cti, CmContractAtt vo ) {
		return cmContractAttDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  CmContractAtt vo ){
		return cmContractAttDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  CmContractAtt vo ){
		return cmContractAttDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  CmContractAtt vo ){
		return cmContractAttDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, CmContractAtt vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = cmContractAttDao.countFreeFind(vo);
		pm.setTotal(total);
		List<CmContractAtt> datas = cmContractAttDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<CmContractAtt> findAll(CmContractAtt vo){
		
		List<CmContractAtt> datas = cmContractAttDao.freeFind(vo);
		return datas;
		
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/02 10:14:24
	
	@Override
	public void saveContractAtt(ContextInfo cti,CmContractAtt contractAtt, String collaborator, Integer[] contractAttItemId, String[] contractAttItemType, BigDecimal[] contractAttItemPrice) {
		try {
			if(contractAtt.getContractAttId() == null || "".equals(contractAtt.getContractAttId())){
	    		contractAtt.setCreateBy(cti.getUserId());
	    		contractAtt.setCreateDt(new Date());
	    		contractAtt.setDr(0);
	    		cmContractAttDao.save(cti,contractAtt);
	    		for(int i=0;i<contractAttItemId.length;i++){
	    			CmContractAttitem vo = new CmContractAttitem();
	    			vo.setContractAttId(contractAtt.getContractAttId());
	    			vo.setItemId(contractAttItemId[i]);
	    			vo.setFeetype(contractAttItemType[i]);
	    			vo.setPrice(contractAttItemPrice[i]);
	    			vo.setDr(0);
	    			vo.setCreateBy(cti.getUserId());
	    			vo.setCreateDt(new Date());
	    			cmContractAttItemDao.save(cti, vo);
	    		}
	    		
	    	}else{
	    		contractAtt.setModifyBy(cti.getUserId());
	    		contractAtt.setModifyDt(new Date());
	    		cmContractAttDao.update(cti,contractAtt);
	    		cmContractAttItemDao.deleteByContractAttId(cti,contractAtt.getContractAttId());
	    		for(int i=0;i<contractAttItemId.length;i++){
	    			CmContractAttitem vo = new CmContractAttitem();
	    			vo.setContractAttId(contractAtt.getContractAttId());
	    			vo.setItemId(contractAttItemId[i]);
	    			vo.setFeetype(contractAttItemType[i]);
	    			vo.setPrice(contractAttItemPrice[i]);
	    			vo.setDr(0);
	    			vo.setCreateBy(cti.getUserId());
	    			vo.setCreateDt(new Date());
	    			cmContractAttItemDao.save(cti, vo);
	    		}
	    	}
			/**
			 * 设置客服专员
			 */
			if (StringUtils.isBlank(collaborator)) {
				return;
			}
			CmContract contract = new CmContract();
			contract.setContractId(contractAtt.getContractId());
	    	contract = cmContractService.findByPK(contract);
			if (contract == null || (contract.getCollaborator() != null && contract.getCollaborator().toString().equals(collaborator))) {
				return;
			}
			CmContract cmContract = new CmContract();
			cmContract.setContractId(contract.getContractId());
			cmContract.setCollaborator(Integer.parseInt(collaborator));
			cmContractService.update(cti,cmContract);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
