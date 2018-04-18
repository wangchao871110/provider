package com.xfs.business.service;

import com.xfs.business.model.BdBstypeFile;
import com.xfs.common.ContextInfo;

import java.util.List;
import java.util.Map;

/**
 * BdBstypeFileService 服务层接口
 *
 * @date 2017/04/11 15:17:15
 */
public interface BdBstypeFileService {

    /**
     * 查询总数
     *
     * @return 记录数
     */
    public int countFindAll();

    /**
     * 根据条件查询
     *
     * @param obj 业务文件描述表实体
     * @return 业务文件描述表列表
     */
    public List<BdBstypeFile> freeFind(BdBstypeFile obj);

    /**
     * 根据条件查询的数量
     *
     * @param obj 业务文件描述表实体
     * @return 返回查询到的数量
     */
    public int countFreeFind(BdBstypeFile obj);

    /**
     * 根据主键查询对象
     *
     * @param obj 业务文件描述表实体
     * @return 业务文件描述表实体
     */
    public BdBstypeFile findByPK(BdBstypeFile obj);

    /**
     * 添加对象
     *
     * @param obj 业务文件描述表实体
     * @return 影响的记录数
     */
    public int insert(ContextInfo cti, BdBstypeFile obj);

    /**
     * 根据主键修改对象
     *
     * @param obj 业务文件描述表实体
     * @return 影响的记录数
     */
    public int update(ContextInfo cti, BdBstypeFile obj);

    /**
     * 根据主键删除对象
     *
     * @param obj 业务文件描述表实体
     * @return 影响的记录数
     */
    public int remove(ContextInfo cti, BdBstypeFile obj);

    /**
     * 获取业务指南
     *
     * @param contentId
     * @return
     */
    public Map getWorkGuide(Integer contentId);


}
