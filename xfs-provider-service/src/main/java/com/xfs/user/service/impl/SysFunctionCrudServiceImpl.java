package com.xfs.user.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.common.redies.RedisUtil;
import com.xfs.common.redies.keychain.IRedisKeys;
import com.xfs.user.dao.SysFunctionCrudDao;
import com.xfs.user.model.SysFunctionCrud;
import com.xfs.user.service.SysFunctionCrudService;


@Service
public class SysFunctionCrudServiceImpl implements SysFunctionCrudService,IRedisKeys {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SysFunctionCrudServiceImpl.class);
	
	@Autowired
	private SysFunctionCrudDao sysFunctionCrudDao;
	
	public int save(ContextInfo cti, SysFunctionCrud vo ){
		return sysFunctionCrudDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SysFunctionCrud vo ){
		return sysFunctionCrudDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SysFunctionCrud vo ){
		return sysFunctionCrudDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SysFunctionCrud vo ){
		return sysFunctionCrudDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SysFunctionCrud vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = sysFunctionCrudDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SysFunctionCrud> datas = sysFunctionCrudDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SysFunctionCrud> findAll(SysFunctionCrud vo){
		
		List<SysFunctionCrud> datas = sysFunctionCrudDao.freeFind(vo);
		return datas;
		
	}


	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/13 17:28:22

    /** 根据URL获取受控角色
     *  @param    : currUrl 当前url地址
     *  @return    : String
     *  @createDate   : 16-11-14 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-14 上午10:47
     *  @updateAuthor  :
     */
	@Override
	public String queryRoleIdsByUrl(String currUrl) {
		String key = SAAS_USER_CRUD_ROLE_LIST + "_" + currUrl;
		RedisUtil.delete(key);
		if(null == RedisUtil.get(key)){
			String roleIds = sysFunctionCrudDao.queryRoleIdsByUrl(currUrl);
			RedisUtil.set(key,roleIds,0);
		}
		return (String)RedisUtil.get(key);
	}

    /** 根据URL获取受控角色（Cs）
     *  @param    : currUrl 当前url地址
     *  @return    : String
     *  @createDate   : 16-11-14 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-14 上午10:47
     *  @updateAuthor  :
     */
    @Override
    public String queryRoleIdsByUrlWithCs(ContextInfo cti,String currUrl) {
        String key = MALL_USER_CRUD_ROLE_LIST + "_" + currUrl;
		RedisUtil.delete(key);
        if(null == RedisUtil.get(key)){
            String roleIds = sysFunctionCrudDao.queryRoleIdsByUrlWithCs(cti,currUrl);
            RedisUtil.set(key,roleIds,0);
        }
        return String.valueOf(RedisUtil.get(key));
    }


	/**
	 *  根据角色查询无权限访问按钮
	 *  @param   roleIds
	 *	@return 			: java.lang.String
	 *  @createDate  	: 2017-06-19 18:10
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017-06-19 18:10
	 *  @updateAuthor  :
	 */
	@Override
	public List<SysFunctionCrud> queryNoAuthUrlByRoleIds(String roleIds){
		return sysFunctionCrudDao.queryNoAuthUrlByRoleIds(roleIds);
	}


    /** 根据URL获取受控角色（Bs）
     *  @param    : currUrl 当前url地址
     *  @return    : String
     *  @createDate   : 16-11-14 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-14 上午10:47
     *  @updateAuthor  :
     */
    @Override
    public String queryRoleIdsByUrlWithBs(String currUrl) {
        String key = MALL_USER_CRUD_ROLE_LIST + "_" + currUrl;
		RedisUtil.delete(key);
        if(null == RedisUtil.get(key)){
            String roleIds = sysFunctionCrudDao.queryRoleIdsByUrlWithBs(currUrl);
            RedisUtil.set(key,roleIds,0);
        }
        return String.valueOf(RedisUtil.get(key));
    }

    /** 根据URL获取受控角色（Cooperation）
     *  @param    : currUrl 当前url地址
     *  @return    : String
     *  @createDate   : 16-11-14 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-14 上午10:47
     *  @updateAuthor  :
     */
    @Override
    public String queryRoleIdsByUrlWithCoop(String currUrl) {
        String key = MALL_USER_CRUD_ROLE_LIST + "_" + currUrl;
		RedisUtil.delete(key);
        if(null == RedisUtil.get(key)){
            String roleIds = sysFunctionCrudDao.queryRoleIdsByUrlWithCoop(currUrl);
            RedisUtil.set(key,roleIds,0);
        }
        return String.valueOf(RedisUtil.get(currUrl));
    }

    public SysFunctionCrud findByPK(SysFunctionCrud obj) {

        return sysFunctionCrudDao.findByPK(obj);
    }

}
