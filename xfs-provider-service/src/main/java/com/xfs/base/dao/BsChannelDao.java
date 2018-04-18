package com.xfs.base.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xfs.base.model.BsChannel;
import com.xfs.bs.dto.QueryChannelCorpDto;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;

/**
 * BsChannelDao
 * @author:wangchao
 * @date:2016/07/26 11:15:54
 */
@Repository
public class BsChannelDao extends BaseSqlMapDao {
	
	public int countFindAll() {
        Integer ret = (Integer) queryForObject("BS_CHANNEL.CountFindAllBS_CHANNEL", null );
        return ret.intValue();
	}

	public BsChannel findByPK(BsChannel obj) {
		Object ret = queryForObject("BS_CHANNEL.FindByPK", obj );
		if(ret !=null)
			return (BsChannel)ret;
		else 
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<BsChannel> freeFind(BsChannel obj) {
		return queryForList("BS_CHANNEL.FreeFindBS_CHANNEL", obj );
	}

	public int countFreeFind(BsChannel obj) {
        Integer ret = (Integer) queryForObject("BS_CHANNEL.CountFreeFindBS_CHANNEL", obj );
        return ret.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<BsChannel> freeFind(BsChannel obj, int pageIndex, int pageSize) {
		return getPaginatedList("BS_CHANNEL.FreeFindBS_CHANNEL", obj, pageIndex, pageSize );
	}
	
	@SuppressWarnings("unchecked")
	public List<BsChannel> freeFind(BsChannel obj, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsChannel){
	    	_hashmap = ((BsChannel)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return queryForList("BS_CHANNEL.FreeFindBS_CHANNELOrdered", _hashmap);
	}
	
	@SuppressWarnings("unchecked")
	public List<BsChannel> freeFind(BsChannel obj, int pageIndex, int pageSize, String orderByColName) {
	    HashMap<String,Object> _hashmap = new HashMap<String,Object>();
	    if(obj instanceof BsChannel){
	    	_hashmap = ((BsChannel)obj).toHashMap();
	    }
        _hashmap.put("_orderBy", orderByColName );
        
		return getPaginatedList("BS_CHANNEL.FreeFindBS_CHANNELOrdered", _hashmap, pageIndex, pageSize );
	}

	public int saveAll(ContextInfo cti, Collection<BsChannel> objColl) {
		int i = 0;
        if ( objColl != null ) {
            Iterator<BsChannel> it = objColl.iterator();
            while ( it.hasNext() ) {
            	BsChannel oneObj = (BsChannel)it.next();
				i += save(cti, oneObj);
            }
        }
        return i;
	}

	public int save(ContextInfo cti, BsChannel vo) {
        BsChannel obj = (BsChannel) vo;

        if(exists( obj ) ) {
            return update(cti, obj);
        } else {
            return insert(cti, obj);
        }
	}

	public int insert(ContextInfo cti, BsChannel obj) {

		obj.fixup();
        return insert(cti, "BS_CHANNEL.InsertBS_CHANNEL", obj );
	}

	public int update(ContextInfo cti, BsChannel obj) {

		obj.fixup();
		return update(cti, "BS_CHANNEL.UpdateBS_CHANNEL", obj );

	}
	
    // remove children, then the obj
    // here we just remove, do not load obj tree.
    // removeObjectTree() function load obj tree first
	public int remove(ContextInfo cti, BsChannel obj) {

        if ( obj == null ) { 
            return 0; 
        }
		
		obj.fixup();

        return delete(cti, "BS_CHANNEL.DeleteBS_CHANNEL", obj );

	}

	public int removeObjectTree(ContextInfo cti, BsChannel obj) {

        obj.fixup();
        BsChannel oldObj = ( BsChannel )queryForObject("BS_CHANNEL.FindByPK", obj );
        if ( oldObj == null ) { 
            return 0; 
        }


        return delete(cti, "BS_CHANNEL.DeleteBS_CHANNEL", oldObj );

	}

	public boolean exists(BsChannel vo) {
		
        boolean keysFilled = true;
        boolean ret = false;
        BsChannel obj = (BsChannel) vo;

        keysFilled = keysFilled && ( null != obj.getId() );

        if ( keysFilled ) {
            Integer retInt=(Integer)queryForObject("BS_CHANNEL.CountBS_CHANNEL", obj );
            if ( retInt != null && retInt.intValue() > 0 ) {
                ret = true;
            }
        }

        return ret;
	}

	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/07/26 11:15:54

	public BsChannel findbyid(BsChannel obj) {
		Object ret = queryForObject("BS_CHANNEL.Findbyid", obj );
		if(ret !=null)
			return (BsChannel)ret;
		else
			return null;
	}

	/**
	 * 分页查询渠道企业
	 * @param channelCorpDto
	 * @param pageIndex
	 * @param pageSize
     * @return
     */
	public List<Map> queryPageByChannelCorp(QueryChannelCorpDto channelCorpDto,Integer pageIndex,Integer pageSize){
		return getPaginatedList("BS_CHANNEL.FreeFindBS_CHANNEL_CORP", channelCorpDto, pageIndex, pageSize );
	}


	/**
	 * 分页查询渠道企业总数
	 * @param channelCorpDto
	 * @return
	 */
	public Integer queryPageByChannelCorpCount(QueryChannelCorpDto channelCorpDto){
		return (Integer)queryForObject("BS_CHANNEL.FreeFindBS_CHANNEL_CORP_COUNT",channelCorpDto);
	}

	/**
	 * 渠道企业详情
	 * @param cpId
	 * @return
     */
	public Map queryChannelCorpByCpId(Integer orgId,Integer cpId){
		Map query = new HashMap<String,Object>();
		query.put("orgId",orgId);
		query.put("cpId",cpId);
		return (Map) queryForObject("BS_CHANNEL.FreeFindBS_CHANNEL_CORP_DETAIL",query);
	}
	
}
