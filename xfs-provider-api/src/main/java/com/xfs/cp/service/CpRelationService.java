package com.xfs.cp.service;
import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.cp.model.CpRelation;

/**
 * CpRelationService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/09/08 19:24:41
 */
public interface CpRelationService {
	
	public int save(ContextInfo cti, CpRelation vo);
	public int insert(ContextInfo cti, CpRelation vo);
	public int remove(ContextInfo cti, CpRelation vo);
	public int update(ContextInfo cti, CpRelation vo);
	public PageModel findPage(PageInfo pi, CpRelation vo);
	public List<CpRelation> findAll(CpRelation vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/09/08 19:24:41
	
	/**
	 * 
	 * @method comments: 建立关系
	 * @author   name  : wangchao  
	 * @create   time  : 2016年9月12日 上午11:52:31 
	 * @param request
	 * @param cpRelation
	 * @param result
	 * @return
	 * @throws
	 * @modify   list  : 修改时间和内容
	 * 2016年9月12日 上午11:52:31 wangchao 创建
	 *
	 */
	public Result linkRelation(ContextInfo user, CpRelation cpRelation, Result result);

	/**
	 *
	 * @method comments: 获取关系byPK
	 * @author   name  : wuzhe
	 * @create   time  : 2016年9月12日
	 * @param cpRelation
	 * @return
	 *
	 */
	public CpRelation getCpRelationById(CpRelation cpRelation);

	public PageModel getBSpListPage(PageInfo pi, CpRelation vo);
	public PageModel getASpListPage(PageInfo pi, CpRelation vo);

	public PageModel FindManage(PageInfo pi,CpRelation vo);

	public Integer findPerson(CpRelation vo);

	public Integer finTask(CpRelation vo);
}

