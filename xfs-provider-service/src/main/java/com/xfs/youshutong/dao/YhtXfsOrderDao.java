package com.xfs.youshutong.dao;

import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import com.xfs.youshutong.model.YhtXfsOrder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 订单表-和友互通对接Dao 持久层
 * @date 2017/05/22 15:53:46
 */
@Repository
public class YhtXfsOrderDao  extends BaseSqlMapDao {
	
	/**
	 * 查询总数
	 * @return 记录数
	 */
	public int countFindAll(){
		Integer ret = (Integer) queryForObject("yht_xfs_order.countFindAll", null);
		return ret.intValue();
	};
	
	/**
	 * 根据条件查询
	 * @param 	obj  订单表-和友互通对接实体
	 * @return   订单表-和友互通对接列表
	 */
	public List<YhtXfsOrder> freeFind(YhtXfsOrder obj){
		return queryForList("yht_xfs_order.freeFind", obj);
	};
	
	/**
	 * 根据条件查询的数量
	 * @param 	obj  订单表-和友互通对接实体
	 * @return 	返回查询到的数量
	 */
	public int countFreeFind(YhtXfsOrder obj){
		Integer ret = (Integer) queryForObject("yht_xfs_order.countFreeFind", obj);
		return ret.intValue();
	};
	
	/**
	 * 根据主键查询对象
	 * @param 	obj  订单表-和友互通对接实体
	 * @return   订单表-和友互通对接实体
	 */
	public YhtXfsOrder findByPK(YhtXfsOrder obj){
		Object ret = queryForObject("yht_xfs_order.findByPK", obj);
		if (ret != null)
			return (YhtXfsOrder) ret;
		else
			return null;
	};
	
	/**
	 * 添加对象
	 * @param 	obj  订单表-和友互通对接实体
	 * @return   影响的记录数
	 */
	public int insert(ContextInfo cti, YhtXfsOrder obj){
		return insert(cti, "yht_xfs_order.insert", obj);
	};
	
	/**
	 * 根据主键修改对象
	 * @param 	obj  订单表-和友互通对接实体
	 * @return   影响的记录数
	 */
	public int update(ContextInfo cti, YhtXfsOrder obj){
		return update(cti, "yht_xfs_order.update", obj);
	};
	
	/**
	 * 根据主键删除对象
	 * @param 	obj  订单表-和友互通对接实体
	 * @return   影响的记录数
	 */
	public int remove(ContextInfo cti, YhtXfsOrder obj){
		if (obj == null) {
			return 0;
		}

		return delete(cti, "yht_xfs_order.remove", obj);
	}


	public List<YhtXfsOrder> findOrderByCondition(Map obj){
		return queryForList("yht_xfs_order.findOrderByCondition", obj);
	}

	/**
	 * 根据 服务商ID spId 服务类型，审核类型 查询当前服务商下所有 用户
	 */
	@SuppressWarnings("unchecked")
	public List<YhtXfsOrder> findYhtXfsOrder(YhtXfsOrder obj, int pageIndex, int pageSize) {
		return getPaginatedList("yht_xfs_order.freeFind", obj, pageIndex, pageSize);
	}

	
}
