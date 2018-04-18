package com.xfs.user.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.user.dao.SysRoleDao;
//import com.xfs.sps.dao.SysRoleDao;
//import com.xfs.sps.service.SysRoleService;
import com.xfs.user.model.SysRole;
import com.xfs.user.service.SysRelRoleFuncService;
import com.xfs.user.service.SysRoleService;

@Service
public class SysRoleServiceImpl implements SysRoleService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SysRoleServiceImpl.class);

	@Autowired
	private SysRoleDao sysRoleDao;

    @Autowired
    private SysRelRoleFuncService relRoleFuncService;

	public int save(ContextInfo cti, SysRole vo) {
		return sysRoleDao.save(cti, vo);
	}

	public int insert(ContextInfo cti, SysRole vo) {
		return sysRoleDao.insert(cti, vo);
	}

	public int remove(ContextInfo cti, SysRole vo) {
		return sysRoleDao.remove(cti, vo);
	}

	public int update(ContextInfo cti, SysRole vo) {
		return sysRoleDao.update(cti, vo);
	}

	public PageModel findPage(PageInfo pi, SysRole vo) {

		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = sysRoleDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SysRole> datas = sysRoleDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;

	}

	public List<SysRole> findAll(SysRole vo) {

		List<SysRole> datas = sysRoleDao.freeFind(vo);
		return datas;

	}

	// 温馨提示：
	// 以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	// 2016/07/13 17:28:23

    /**
     * 根据项目记号查询角色
     *  @param    : Scode 区分项目的记号
     *  @return    : List<Map<String,Object>>
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
	@Override
	public List<Map<String,Object>> findRoleByScode(String Scode) {
		List<Map<String,Object>> datas = sysRoleDao.findRoleByScode(Scode);
		return datas;
	}

	/**
	 * 根据条件分页查询
	 * 
	 *  @param   role   SysRole对象
	 *  @return 		: PageModel
	 *  @createDate  	: 2016年11月15日 上午11:14:44
	 *  @author         : zhengdan@xinfushe.com
	 *  @version        : v1.0
	 *  @updateDate    	: 2016年11月15日 上午11:14:44
	 *  @updateAuthor  	:
	 */
    public PageModel getRoleList(PageInfo pi, SysRole role){

        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = sysRoleDao.countFreeFind(role);
        pm.setTotal(total);
        List<SysRole> datas = sysRoleDao.freeFind(role, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;
    }


   /**
    * 查询全部
    * 
    *  @return 			:List
    *  @createDate  	: 2016年11月15日 上午11:15:50
    *  @author         	: zhengdan@xinfushe.com
    *  @version        	: v1.0
    *  @updateDate    	: 2016年11月15日 上午11:15:50
    *  @updateAuthor  	:
    */
    @Override
    public List getAllRoleList(){
        return sysRoleDao.freeFind(new SysRole());
    }

    /**
     * 根据RoleId删除数据
     * 
     *  @param     vo   SysRole对象
     *  @return         :
     *  @createDate  	: 2016年11月15日 上午11:17:13
     *  @author         : zhengdan@xinfushe.com
     *  @version        : v1.0
     *  @updateDate    	: 2016年11月15日 上午11:17:13
     *  @updateAuthor  	:
     */
    @Override
    public void delete(ContextInfo cti, SysRole vo) {
        sysRoleDao.remove(cti, vo);
        relRoleFuncService.deleteByRoleId(cti, vo.getRoleId());
        //deal with users associated with this role

    }

    /**
     * 根据条件查询
     *  @param     vo    SysRole对象  
     *  @return 		：SysRole
     *  @createDate  	: 2016年11月15日 上午11:17:57
     *  @author         : zhengdan@xinfushe.com
     *  @version        : v1.0
     *  @updateDate    	: 2016年11月15日 上午11:17:57
     *  @updateAuthor  	:
     */
    public SysRole findByPK(SysRole vo) {
        return sysRoleDao.findByPK(vo);

    }

	public List<SysRole> queryAllRolesByUserId(Integer userId){
    	return sysRoleDao.queryAllRolesByUserId(userId);
	}


	/**
	 * 获取角色信息
	 * @param vo
	 * @return
	 */
	public List<SysRole> findAllByAppCodes(SysRole vo) {
		List<SysRole> datas = sysRoleDao.freeFindByAppCodes(vo);
		return datas;

	}
}
