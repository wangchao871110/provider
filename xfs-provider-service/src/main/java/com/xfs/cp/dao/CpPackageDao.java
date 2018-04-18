package com.xfs.cp.dao;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.cp.model.CpPackage;

/**
 * CpPackageDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/08 10:55:22
 */
@Repository
public class CpPackageDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CP_PACKAGE.CountFindAllCP_PACKAGE", null );
        return ret.intValue();
	}

	public CpPackage findByPK(CpPackage obj) {
		Object ret = queryForObject("CP_PACKAGE.FindByPK", obj );
		if(ret !=null)
			return (CpPackage)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CpPackage> freeFind(CpPackage obj) {
		return queryForList("CP_PACKAGE.FreeFindCP_PACKAGE", obj );
	}

	public int countFreeFind(CpPackage obj) {
        Integer ret = (Integer) queryForObject("CP_PACKAGE.CountFreeFindCP_PACKAGE", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<CpPackage> freeFind(CpPackage obj, int pageIndex, int pageSize) {
		return getPaginatedList("CP_PACKAGE.FreeFindCP_PACKAGE", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<CpPackage> freeFind(CpPackage obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CpPackage){
	    	_hashmap = ((CpPackage)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CP_PACKAGE.FreeFindCP_PACKAGEOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<CpPackage> freeFind(CpPackage obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CpPackage){
	    	_hashmap = ((CpPackage)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CP_PACKAGE.FreeFindCP_PACKAGEOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<CpPackage> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<CpPackage> it = objColl.iterator();
            while ( it.hasNext() ) {
            	CpPackage oneObj = it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, CpPackage vo) {
        CpPackage obj = (CpPackage) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, CpPackage obj) {

		obj.fixup();
        return insert(cti, "CP_PACKAGE.InsertCP_PACKAGE", obj );
	}

	public int update(ContextInfo cti, CpPackage obj) {

		obj.fixup();
		return update(cti, "CP_PACKAGE.UpdateCP_PACKAGE", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, CpPackage obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "CP_PACKAGE.DeleteCP_PACKAGE", obj );

	}

	public int removeObjectTree(ContextInfo cti, CpPackage obj) {

        obj.fixup();
        CpPackage oldObj = ( CpPackage )queryForObject("CP_PACKAGE.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "CP_PACKAGE.DeleteCP_PACKAGE", oldObj );

	}

	public boolean exists(CpPackage vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CpPackage obj = (CpPackage) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CP_PACKAGE.CountCP_PACKAGE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 10:55:22

	public List<CpPackage> findASpList(CpPackage obj,int pageIndex, int pageSize) {
		return getPaginatedList("CP_PACKAGE.findASpList", obj, pageIndex, pageSize );
	}

	public int findASpListCount(CpPackage obj) {
		Integer ret = (Integer) queryForObject("CP_PACKAGE.findASpListCount", obj );
		return ret.intValue();
	}

	/**
	 * 
	 * @method comments: 获取包列表总数
	 * @author   name  : wangchao  
	 * @create   time  : 2016年9月20日 下午3:59:10 
	 * @param cpPackage
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月20日 下午3:59:10 wangchao 创建
	 *
	 */
	public Integer findPackagePageCount(CpPackage cpPackage) {
		Integer ret = (Integer) queryForObject("CP_PACKAGE.findPackagePageCount", cpPackage );
        return ret.intValue();
	}

	/**
	 * 
	 * @method comments: 获取包列表
	 * @author   name  : wangchao  
	 * @create   time  : 2016年9月20日 下午3:58:58 
	 * @param cpPackage
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月20日 下午3:58:58 wangchao 创建
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<CpPackage> findPackagePage(CpPackage cpPackage, int pageIndex, int pageSize) {
		return getPaginatedList("CP_PACKAGE.findPackagePage", cpPackage, pageIndex-1, pageSize );
	}

	/**
	 * 
	 * @method comments: 发包管理 查询包个数
	 * @author   name  : baoyu  
	 * @create   time  : 2016年9月24日 下午7:11:28 
	 * @param cpPackage
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月24日 下午7:11:28 baoyu 创建
	 *
	 */
	public Integer queryPackagePageCount(CpPackage cpPackage) {
		return (Integer) queryForObject("CP_PACKAGE.queryPackagePageCount", cpPackage);
	}

	/**
	 * 
	 * @method comments: 发包管理 查询包
	 * @author   name  : baoyu  
	 * @create   time  : 2016年9月24日 下午7:11:42 
	 * @param cpPackage
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月24日 下午7:11:42 baoyu 创建
	 *
	 */
	public List<Map<String, Object>> queryPackagePage(CpPackage cpPackage, Integer pageIndex, Integer pageSize) {
		return getPaginatedList("CP_PACKAGE.queryPackagePage",cpPackage,pageIndex,pageSize);
	}

	/**
	 * 获取发包数量
	 *  @param cpPackage
	 *  @return 
	 *	@return			: int 
	 *  @createDate		: 2016年11月8日 下午3:37:12
	 *  @author			: wangchao 
	 *  @version		: v1.0
	 *  @updateDate		: 2016年11月8日 下午3:37:12
	 *  @updateAuthor  	:
	 */
	public int findPackageNumber(CpPackage cpPackage) {
		Integer ret = (Integer) queryForObject("CP_PACKAGE.findPackageNumber", cpPackage );
        return ret.intValue();
	}

	/**
	 * 根据包编号获取包信息
	 *  @param obj
	 *  @return 
	 *	@return			: CpPackage 
	 *  @createDate		: 2016年12月1日 下午2:27:31
	 *  @author			: wangchao 
	 *  @version		: v1.0
	 *  @updateDate		: 2016年12月1日 下午2:27:31
	 *  @updateAuthor  	:
	 */
	public CpPackage findByPackageNum(CpPackage obj) {
		Object ret = queryForObject("CP_PACKAGE.findByPackageNum", obj );
		if(ret !=null)
			return (CpPackage)ret;
		else 
			return null;
	}

	/**
	 * 修改包状态根据包id和发包的服务商id（防止其他用户修改此包）
	 *  @param sysUser
	 *  @param cpPackage
	 *  @return 
	 *	@return 			: int 
	 *  @createDate  	: 2016年12月12日 下午8:13:30
	 *  @author         	: songby 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016年12月12日 下午8:13:30
	 *  @updateAuthor  :
	 */
	public int updateByIdAndSpId(ContextInfo sysUser, CpPackage cpPackage) {
		return update(sysUser, "CP_PACKAGE.updateByIdAndSpId", cpPackage);
	}

	/**
	 * 未发布删除
	 *  @param sysUser
	 *  @param cpPackage
	 *  @return 
	 *	@return 			: int 
	 *  @createDate  	: 2016年12月13日 下午2:36:41
	 *  @author         	: songby 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016年12月13日 下午2:36:41
	 *  @updateAuthor  :
	 */
	public int delNPul(ContextInfo sysUser, CpPackage cpPackage) {
		return update(sysUser, "CP_PACKAGE.UpdateCP_PACKAGE_idAndSpId", cpPackage);
	}
}
