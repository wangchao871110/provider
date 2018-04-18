package com.xfs.user.service.impl;

import java.util.List;

import com.xfs.user.model.SysUser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.common.util.StringUtils;
import com.xfs.user.dao.SysFunctionDataDao;
//import com.xfs.sps.dao.SysFunctionDataDao;
//import com.xfs.sps.service.SysFunctionDataService;
import com.xfs.user.model.SysFunctionData;
import com.xfs.user.service.SysFunctionDataService;

@Service
public class SysFunctionDataServiceImpl implements SysFunctionDataService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SysFunctionDataServiceImpl.class);

	@Autowired
	private SysFunctionDataDao sysFunctionDataDao;

	public int save(ContextInfo cti, SysFunctionData vo ){
		return sysFunctionDataDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SysFunctionData vo ){
		return sysFunctionDataDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SysFunctionData vo ){
		return sysFunctionDataDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SysFunctionData vo ){
		return sysFunctionDataDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SysFunctionData vo){

		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = sysFunctionDataDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SysFunctionData> datas = sysFunctionDataDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;

	}
	public List<SysFunctionData> findAll(SysFunctionData vo){

		List<SysFunctionData> datas = sysFunctionDataDao.freeFind(vo);
		return datas;

	}


	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/23 10:46:32

    /** 获取用户数据权限
     *  @param    : userId  用户ID
     *  @param   ：userType 用户类型
     *  @param   ：tableName 数据库表名称
     *  @return    : String
     *  @createDate   : 16-11-14 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-14 上午10:47
     *  @updateAuthor  :
     */
	public String getDataAuthorityByUser(Integer userId,String userType,String tableName){
		SysFunctionData functionData = new SysFunctionData();
		functionData.setUserId(userId);
		functionData.setDataTypeEq(tableName);
		functionData.setUserTypeEq(userType);
		List<SysFunctionData> list = sysFunctionDataDao.freeFind(functionData);
		if (list.size() == 1 && list.get(0).getIsAll().equals("Y")){
			return "ALL";
		}else{
			String data = sysFunctionDataDao.getAuthorityByUserIdAndType(functionData);
			if(StringUtils.isNotBlank(data)){
				return data;
			}else{
				return "'NO'";
			}
		}
	}

	public int deleteByUserId(ContextInfo cti, Integer userId){
		return sysFunctionDataDao.deleteByUserId(cti, userId);
	}

}
