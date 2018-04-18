package com.xfs.business.dao;

import com.xfs.business.model.BdBstypeFile;
import com.xfs.common.ContextInfo;
import com.xfs.common.util.BaseSqlMapDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 业务文件描述表Dao 持久层
 *
 * @date 2017/04/11 15:17:15
 */
@Repository
public class BdBstypeFileDao extends BaseSqlMapDao {

    /**
     * 查询总数
     *
     * @return 记录数
     */
    public int countFindAll() {
        Integer ret = (Integer) queryForObject("bd_bstype_file.countFindAll", null);
        return ret.intValue();
    }

    /**
     * 根据条件查询
     *
     * @param obj 业务文件描述表实体
     * @return 业务文件描述表列表
     */
    public List<BdBstypeFile> freeFind(BdBstypeFile obj) {
        return queryForList("bd_bstype_file.freeFind", obj);
    }

    /**
     * 根据条件查询的数量
     *
     * @param obj 业务文件描述表实体
     * @return 返回查询到的数量
     */
    public int countFreeFind(BdBstypeFile obj) {
        Integer ret = (Integer) queryForObject("bd_bstype_file.countFreeFind", obj);
        return ret.intValue();
    }

    /**
     * 根据主键查询对象
     *
     * @param obj 业务文件描述表实体
     * @return 业务文件描述表实体
     */
    public BdBstypeFile findByPK(BdBstypeFile obj) {
        Object ret = queryForObject("bd_bstype_file.findByPK", obj);
        if (ret != null)
            return (BdBstypeFile) ret;
        else
            return null;
    }

    /**
     * 添加对象
     *
     * @param obj 业务文件描述表实体
     * @return 影响的记录数
     */
    public int insert(ContextInfo cti, BdBstypeFile obj) {
        return insert(cti, "bd_bstype_file.insert", obj);
    }

    /**
     * 根据主键修改对象
     *
     * @param obj 业务文件描述表实体
     * @return 影响的记录数
     */
    public int update(ContextInfo cti, BdBstypeFile obj) {
        return update(cti, "bd_bstype_file.update", obj);
    }

    /**
     * 根据主键删除对象
     *
     * @param obj 业务文件描述表实体
     * @return 影响的记录数
     */
    public int remove(ContextInfo cti, BdBstypeFile obj) {
        return delete(cti, "bd_bstype_file.remove", obj);
    }


    public Map getWorkGuide(Integer contentId) {
        Map obj = new HashMap();
        obj.put("contentId", contentId);
        Map ret = (Map) queryForObject("bd_bstype_file.getWorkGuide", obj);
        return ret;
    }

}
