package com.xfs.employeeside.service;

import com.xfs.common.ContextInfo;
import com.xfs.common.Result;
import com.xfs.corp.model.CmEmployee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * CmEmployeeService 服务层接口
 *
 * @date 2017/03/13 14:37:24
 */
public interface CmEmployeeService {

    /**
     * 登录
     *
     * @param cpId             企业id
     * @param headimgurl       头像地址
     * @param name             名称
     * @param lastFourOfIdCard 身份证号 后四位
     * @param openId           唯一表示
     * @return
     */
    Result login(Integer cpId, String headimgurl, String name, String lastFourOfIdCard, String openId);


    /**
     * 退出
     *
     * @param empId
     * @param openId
     * @return
     */
    Result logout(Integer empId, String openId);

    /**
     * 查询总数
     *
     * @return 记录数
     */
    public int countFindAll();

    /**
     * 根据条件查询
     *
     * @param obj 员工表实体
     * @return 员工表列表
     */
    public List<CmEmployee> freeFind(CmEmployee obj);

    /**
     * 根据条件查询的数量
     *
     * @param obj 员工表实体
     * @return 返回查询到的数量
     */
    public int countFreeFind(CmEmployee obj);

    /**
     * 根据主键查询对象
     *
     * @param obj 员工表实体
     * @return 员工表实体
     */
    public CmEmployee findByPK(CmEmployee obj);

    /**
     * 添加对象
     *
     * @param obj 员工表实体
     * @return 影响的记录数
     */
    public int insert(ContextInfo cti, CmEmployee obj);

    /**
     * 根据主键修改对象
     *
     * @param obj 员工表实体
     * @return 影响的记录数
     */
    public int update(ContextInfo cti, CmEmployee obj);

    /**
     * 根据主键删除对象
     *
     * @param obj 员工表实体
     * @return 影响的记录数
     */
    public int remove(ContextInfo cti, CmEmployee obj);


    /**
     * 根据 员工id 获取区id
     *
     * @param empId
     * @return
     */
    public Integer getEmpAreaIdByEmpId(Integer empId);


    /**
     * 根据员工id 获取员工信息和所有的附件信息
     *
     * @param empId
     * @return
     */
    Result getCmEmployeeAndAttach(Integer empId);


    /**
     * 根据openId 登录
     *
     * @param openId
     * @param cpId
     * @return
     */
    Result loginByOpenId(HttpServletRequest request, HttpServletResponse response, String openId, Integer cpId);

    /**
     * @Title: 根据 友人才的 id 进行登录
     * @param   
     * @return 
     * @createDate 2017/8/9 16:28
     * @Auther:zhanghongjie【hongjievip6688@163.com】
     * @version        : v1.0
     * @updateDate    	: 
     * @updateAuthor  :
    */
    Result loginByYcrToken(HttpServletRequest request, HttpServletResponse response, String ycrToken, Integer cpId);


    /**
     * 根据 姓名和身份证后四位查询
     *
     * @param cpId
     * @param name
     * @param lastFourOfIdCard
     * @param openId
     * @return
     */
    Result loginByUserInfo(HttpServletRequest request, HttpServletResponse response,Integer cpId, String name, String lastFourOfIdCard, String openId);


}
