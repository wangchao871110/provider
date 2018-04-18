package com.xfs.cp.service;
import java.util.List;
import com.xfs.common.page.PageModel;
import com.xfs.cp.model.CpBusinessIntroduction;


/**
 * CpBusinessIntroductionService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2017/01/05 11:47:25
 */
public interface CpBusinessIntroductionService {
	
	public int save( CpBusinessIntroduction vo );
	public int insert( CpBusinessIntroduction vo );
	public int remove( CpBusinessIntroduction vo );
	public int update( CpBusinessIntroduction vo );
	public PageModel findPage(CpBusinessIntroduction vo);
	public List<CpBusinessIntroduction> findAll(CpBusinessIntroduction vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/01/05 11:47:25
	public CpBusinessIntroduction findByPK(CpBusinessIntroduction cpBusinessIntroduction);
	
	
}
