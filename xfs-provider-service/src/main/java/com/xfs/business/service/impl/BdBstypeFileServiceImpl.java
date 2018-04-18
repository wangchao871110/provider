package com.xfs.business.service.impl;

import com.xfs.business.dao.BdBstypeFileDao;
import com.xfs.business.model.BdBstypeFile;
import com.xfs.business.service.BdBstypeFileService;
import com.xfs.common.ContextInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * BdBstypeFileServiceImpl 服务层接口实现类
 * @date:2017/04/11 15:17:15
 */
@Service
public class BdBstypeFileServiceImpl implements BdBstypeFileService {
	
	@Autowired
	private BdBstypeFileDao bdBstypeFileDao;

    @Override
	public int countFindAll(){
		return bdBstypeFileDao.countFindAll();
	}

	@Override
	public List<BdBstypeFile> freeFind(BdBstypeFile obj){
		return bdBstypeFileDao.freeFind(obj);
	}

	@Override
	public int countFreeFind(BdBstypeFile obj){
		return bdBstypeFileDao.countFreeFind(obj);
	}

	@Override
	public BdBstypeFile findByPK(BdBstypeFile obj){
		return bdBstypeFileDao.findByPK(obj);
	}

	@Override
	public int insert(ContextInfo cti, BdBstypeFile obj){
		return bdBstypeFileDao.insert(cti,obj);
	}

	@Override
	public int update(ContextInfo cti,BdBstypeFile obj){
		return bdBstypeFileDao.update(cti,obj);
	}

	@Override
	public int remove(ContextInfo cti,BdBstypeFile obj){
		return bdBstypeFileDao.remove(cti,obj);
	}

	public Map getWorkGuide(Integer contentId){
		return bdBstypeFileDao.getWorkGuide(contentId);
	}

}
