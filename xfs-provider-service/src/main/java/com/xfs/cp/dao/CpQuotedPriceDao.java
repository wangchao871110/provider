package com.xfs.cp.dao;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.cp.model.CpQuotedPrice;

/**
 * CpQuotedPriceDao
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/08 19:24:41
 */
@Repository
public class CpQuotedPriceDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("CP_QUOTED_PRICE.CountFindAllCP_QUOTED_PRICE", null );
        return ret.intValue();
	}

	public CpQuotedPrice findByPK(CpQuotedPrice obj) {
		Object ret = queryForObject("CP_QUOTED_PRICE.FindByPK", obj );
		if(ret !=null)
			return (CpQuotedPrice)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<CpQuotedPrice> freeFind(CpQuotedPrice obj) {
		return queryForList("CP_QUOTED_PRICE.FreeFindCP_QUOTED_PRICE", obj );
	}

	public int countFreeFind(CpQuotedPrice obj) {
        Integer ret = (Integer) queryForObject("CP_QUOTED_PRICE.CountFreeFindCP_QUOTED_PRICE", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<CpQuotedPrice> freeFind(CpQuotedPrice obj, int pageIndex, int pageSize) {
		return getPaginatedList("CP_QUOTED_PRICE.FreeFindCP_QUOTED_PRICE", obj, pageIndex, pageSize);
	}
	
	@SuppressWarnings("unchecked")
	public List<CpQuotedPrice> freeFind(CpQuotedPrice obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CpQuotedPrice){
	    	_hashmap = ((CpQuotedPrice)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("CP_QUOTED_PRICE.FreeFindCP_QUOTED_PRICEOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<CpQuotedPrice> freeFind(CpQuotedPrice obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof CpQuotedPrice){
	    	_hashmap = ((CpQuotedPrice)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("CP_QUOTED_PRICE.FreeFindCP_QUOTED_PRICEOrdered", _hashmap, pageIndex, pageSize);
	}

	public int saveAll(ContextInfo cti, Collection<CpQuotedPrice> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<CpQuotedPrice> it = objColl.iterator();
            while ( it.hasNext() ) {
            	CpQuotedPrice oneObj = (CpQuotedPrice)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, CpQuotedPrice vo) {
        CpQuotedPrice obj = (CpQuotedPrice) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, CpQuotedPrice obj) {

		obj.fixup();
        return insert(cti, "CP_QUOTED_PRICE.InsertCP_QUOTED_PRICE", obj );
	}

	public int update(ContextInfo cti, CpQuotedPrice obj) {

		obj.fixup();
		return update(cti, "CP_QUOTED_PRICE.UpdateCP_QUOTED_PRICE", obj);

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, CpQuotedPrice obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "CP_QUOTED_PRICE.DeleteCP_QUOTED_PRICE", obj);

	}

	public int removeObjectTree(ContextInfo cti, CpQuotedPrice obj) {

        obj.fixup();
        CpQuotedPrice oldObj = (CpQuotedPrice)queryForObject("CP_QUOTED_PRICE.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "CP_QUOTED_PRICE.DeleteCP_QUOTED_PRICE", oldObj );

	}

	public boolean exists(CpQuotedPrice vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        CpQuotedPrice obj = (CpQuotedPrice) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("CP_QUOTED_PRICE.CountCP_QUOTED_PRICE", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 19:24:41
	public List<CpQuotedPrice> findPageList(CpQuotedPrice obj,int pageIndex, int pageSize) {
		return getPaginatedList("CP_QUOTED_PRICE.findPageList", obj, pageIndex, pageSize );
	}

	public int findPageListCount(CpQuotedPrice obj) {
		Integer ret = (Integer) queryForObject("CP_QUOTED_PRICE.findPageListCount", obj );
		return ret.intValue();
	}

	public int updateStatus(ContextInfo cti, CpQuotedPrice obj) {

		obj.fixup();
		return update(cti, "CP_QUOTED_PRICE.updateStatus", obj);

	}
	public int updateIsRead(ContextInfo cti, CpQuotedPrice obj) {

		obj.fixup();
		return update(cti, "CP_QUOTED_PRICE.updateIsRead", obj);

	}

	public Map<String, Object> findQuotedSp(CpQuotedPrice obj) {
		return (Map<String, Object>) queryForObject("CP_QUOTED_PRICE.findQuotedSp", obj);
	}

	public List<Map<String, Object>> findQuotedPriceCp(int packageId) {
		return queryForList("CP_QUOTED_PRICE.findQuotedPriceCp", packageId);
	}

	public Map<String, Object> getQuotedPriceCpInfo(Map<String, Object> whereMap) {
		return (Map<String, Object>) queryForObject("CP_QUOTED_PRICE.getQuotedPriceCpInfo", whereMap);
	}


    public Integer findQuotedPriceCpCount(int packageId){
        return (Integer) queryForObject("CP_QUOTED_PRICE.findQuotedPriceCpCount",packageId);
    }

    /**
     * 修改包状态根据包id和发包的服务商id（防止其他用户修改此包）
     *  @param sysUser
     *  @param cpQuotedPrice
     *  @return 
     *	@return 			: int 
     *  @createDate  	: 2016年12月12日 下午9:28:13
     *  @author         	: songby 
     *  @version        	: v1.0
     *  @updateDate    	: 2016年12月12日 下午9:28:13
     *  @updateAuthor  :
     */
	public int updateByIdAndSpId(ContextInfo sysUser, CpQuotedPrice cpQuotedPrice) {
		return update(sysUser, "CP_QUOTED_PRICE.updateByIdAndSpId",cpQuotedPrice);
	}

	/**
	 * 根据服务商ID获取接包数量
	 *  @param cpQuotedPrice
	 *  @return 
	 *	@return 			: int 
	 *  @createDate  	: 2017年2月14日 下午4:35:28
	 *  @author         	: wangchao 
	 *  @version        	: v1.0
	 *  @updateDate    	: 2017年2月14日 下午4:35:28
	 *  @updateAuthor  :
	 */
	public int findPackageNumber(CpQuotedPrice cpQuotedPrice) {
		Integer ret = (Integer) queryForObject("CP_QUOTED_PRICE.findPackageNumber", cpQuotedPrice );
		return ret.intValue();
	}
}
