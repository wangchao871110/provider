package com.xfs.op.service.impl;
import java.util.List;
import java.util.Map;

import com.xfs.common.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.page.PageModel;
import com.xfs.common.page.SystemContext;
import com.xfs.common.ContextInfo;

import com.xfs.op.service.OpUserAncillaryService;
import com.xfs.op.dao.OpUserAncillaryDao;
import com.xfs.op.model.OpUserAncillary;

@Service
public class OpUserAncillaryServiceImpl implements OpUserAncillaryService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(OpUserAncillaryServiceImpl.class);
	
	@Autowired
	private OpUserAncillaryDao opUserAncillaryDao;
	
	public int save(ContextInfo cti, OpUserAncillary vo ){
		return opUserAncillaryDao.save(cti,vo);
	}
	public int insert(ContextInfo cti, OpUserAncillary vo ){
		return opUserAncillaryDao.insert(cti,vo);
	}
	public int remove(ContextInfo cti, OpUserAncillary vo ){
		return opUserAncillaryDao.remove(cti,vo);
	}
	public int update(ContextInfo cti, OpUserAncillary vo ){
		return opUserAncillaryDao.update(cti,vo);
	}
	public PageModel findPage(OpUserAncillary vo){
		
		PageModel pm = new PageModel();
		int pageIndex = SystemContext.getOffset();
		int pageSize = SystemContext.getPagesize();
		Integer total = opUserAncillaryDao.countFreeFind(vo);
		pm.setTotal(total);
		List<OpUserAncillary> datas = opUserAncillaryDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<OpUserAncillary> findAll(OpUserAncillary vo){
		
		List<OpUserAncillary> datas = opUserAncillaryDao.freeFind(vo);
		return datas;
		
	}
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/01/05 20:57:18
	/**  查询用户信息 如果昵称为空则取企业名称
	 *  @param   obj
	 * @return    : java.util.Map<java.lang.String,java.lang.Object>
	 *  @createDate   : 2017/1/8 14:34
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2017/1/8 14:34
	 *  @updateAuthor  :
	 */
	@Override
	public Map<String,Object> getUserInfo(OpUserAncillary obj){
		return opUserAncillaryDao.getUserInfo(obj);
	}

	/**
	 * 保存用户信息
	 *  @param   cti, obj]
	 * @return    : java.lang.Integer
	 *  @createDate   : 2017/1/8 15:00
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2017/1/8 15:00
	 *  @updateAuthor  :
	 */
	@Override
	public Integer saveUserInfo(ContextInfo cti,OpUserAncillary obj){
		OpUserAncillary query = new OpUserAncillary();
		query.setUserId(cti.getUserId());
		Map<String,Object> userInfo = opUserAncillaryDao.getUserInfo(query);
		if(userInfo == null || userInfo.get("id") == null){
			return opUserAncillaryDao.insert(cti,obj);
		}else {
			obj.setId((Integer) userInfo.get("id"));
			return opUserAncillaryDao.update(cti,obj);
		}
	}
	/**
	 * 保存昵称
	 *  @param   cti, nickName]
	 * @return    : com.xfs.common.Result
	 *  @createDate   : 2017/1/17 16:23
	 *  @author          : yangfw@xinfushe.com
	 *  @version         : v1.0
	 *  @updateDate     : 2017/1/17 16:23
	 *  @updateAuthor  :
	 */
	@Override
	public Result saveNickName(ContextInfo cti,String nickName){
		Result result = new Result();
		OpUserAncillary query = new OpUserAncillary();
		query.setUserId(cti.getUserId());
		query.setNickName(nickName);
		if(opUserAncillaryDao.checkNickNameAlread(query)>0){
			result.setSuccess(false).setError("昵称已存在");
			return result;
		}
		OpUserAncillary ancillary = new OpUserAncillary();
		ancillary.setNickName(nickName);
		ancillary.setUserId(cti.getUserId());
		if(this.saveUserInfo(cti,ancillary)<1){
			result.setSuccess(false).setError("保存失败");
		}
		return result;
	}
}
