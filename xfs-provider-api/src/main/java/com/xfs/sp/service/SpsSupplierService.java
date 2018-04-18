package com.xfs.sp.service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.xfs.acc.dto.SpsSchemeDto;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.sp.model.SpService;
import com.xfs.sp.model.SpsSupplier;

/**
 * SpsSupplierService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2017/07/11 16:01:43
 */
public interface SpsSupplierService {
	
	public int save(ContextInfo cti,SpsSupplier vo );
	public int insert(ContextInfo cti, SpsSupplier vo );
	public int remove(ContextInfo cti, SpsSupplier vo );
	public int update(ContextInfo cti, SpsSupplier vo );
	public PageModel findPage(PageInfo pi, SpsSupplier vo);
	public List<SpsSupplier> findAll(SpsSupplier vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/07/11 16:01:43
	/**
	 * 查询服务商信息 分页
	 *
	 * @author lifq
	 *
	 * 2017年7月13日  下午3:01:45
	 */
	public PageModel findSpServicePage(PageInfo pi, Map<String,Object> paraMap);
	/**
	 * 查询 该企业下 所有方案信息
	 *
	 * @author lifq
	 *
	 * 2017年7月14日  下午4:29:15
	 */
	public List<Map<String,Object>> findAllScheme(Map<String,Object> paraMap);
	
	/**
	 * 查询 企业下方案中 城市信息
	 *
	 * @author lifq
	 *
	 * 2017年7月17日  上午11:13:27
	 */
	public List<Map<String,Object>> findAllSchemeCity(Map<String,Object> paraMap);
	
	/**
	 * 保存服务商&方案
	 *
	 * @author lifq
	 *
	 * 2017年7月19日  上午11:37:38
	 */
	public Result saveSpAndScheme(ContextInfo cti,SpService spService,List<SpsSchemeDto> schemeDtos,Integer billDate,Integer endDate,BigDecimal price);
	
	/**
	 * 删除 服务商
	 *
	 * @author lifq
	 *
	 * 2017年7月25日  下午4:33:57
	 */
	public Result delSpservice(ContextInfo cti,String spIds);
	/**
	 * 删除 方案
	 *
	 * @author lifq
	 *
	 * 2017年7月26日  下午3:53:55
	 */
	public Result delScheme(ContextInfo cti,Integer schemeId);
	
}
