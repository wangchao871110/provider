package com.xfs.bill.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.bill.dao.SpsFeeCorponceDao;
import com.xfs.bill.model.SpsFeeCorponce;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.Result;
import com.xfs.common.page.PageModel;
import com.xfs.user.model.SysUser;

/**
 * 企业单次费用服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-10 14:58
 * @version 	: V1.0
 */
@Service
public class SpsFeeCorponceServiceImpl implements SpsFeeCorponceService {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SpsFeeCorponceServiceImpl.class);
	
	@Autowired
	private SpsFeeCorponceDao spsFeeCorponceDao;
	
	public int save(ContextInfo cti, SpsFeeCorponce vo ){
		return spsFeeCorponceDao.save(cti, vo);
	}
	public int insert(ContextInfo cti,  SpsFeeCorponce vo ){
		return spsFeeCorponceDao.insert(cti, vo);
	}
	public int remove(ContextInfo cti,  SpsFeeCorponce vo ){
		return spsFeeCorponceDao.remove(cti, vo);
	}
	public int update(ContextInfo cti,  SpsFeeCorponce vo ){
		return spsFeeCorponceDao.update(cti, vo);
	}
	public PageModel findPage(PageInfo pi, SpsFeeCorponce vo){
		
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsFeeCorponceDao.countFreeFind(vo);
		pm.setTotal(total);
		List<SpsFeeCorponce> datas = spsFeeCorponceDao.freeFind(vo, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
		
	}
	public List<SpsFeeCorponce> findAll(SpsFeeCorponce vo){
		
		List<SpsFeeCorponce> datas = spsFeeCorponceDao.freeFind(vo);
		return datas;
		
	}


	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/04 11:35:45

	/**
	 *  获取单次费用列表
	 *  @param   corp_name	企业名称
	 *  @param	 period		月份
	 *  @param	 sp_id	服务商ID
	 *  @param	 user	用户信息
	 *	@return 			: com.xfs.common.page.PageModel
	 *  @createDate  	: 2016-11-09 16:48
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 16:48
	 *  @updateAuthor  :
	 */
	@Override
	public PageModel queryOnceFeeList(PageInfo pi, String corp_name, String period,Integer sp_id,ContextInfo cti) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsFeeCorponceDao.queryOnceFeeCount(corp_name,period,sp_id,cti);
		pm.setTotal(total);
		List<Map<String,Object>> datas = spsFeeCorponceDao.queryOnceFeeList(corp_name,period,sp_id,pageIndex,pageSize,cti);
		pm.setDatas(datas);
		return pm;
	}

	/**
	 *  获取企业单次费用列表
	 *  @param   sp_id	服务商ID
	 *  @param	 cp_id  企业ID
	 *  @param   shceme_id 方案ID
	 *  @param   period  月份
	 *	@return 			:PageModel
	 *  @createDate  	: 2016-11-09 16:51
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 16:51
	 *  @updateAuthor  :
	 */
	@Override
	public PageModel queryCropOnceFeeList(PageInfo pi, Integer sp_id, Integer cp_id, Integer shceme_id, String period) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsFeeCorponceDao.queryCorpOnceFeeCount(sp_id,cp_id,shceme_id,period);
		pm.setTotal(total);
		List<Map<String,Object>> datas = spsFeeCorponceDao.queryCorpOnceFeeList(sp_id,cp_id,shceme_id,period, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}

	/**
	 *  获取员工单次费用列表
	 *  @param sp_id   服务商ID
	 *  @param cp_id  企业ID
	 *  @param shceme_id 方案ID
	 *  @param area_id 地区
	 *  @param period  月份
	 *	@return 			: PageModel
	 *  @createDate  	: 2016-11-09 16:52
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 16:52
	 *  @updateAuthor  :
	 */
	@Override
	public PageModel queryEmpOnceFeeList(PageInfo pi, Integer sp_id, Integer cp_id, Integer shceme_id, Integer area_id, String period,String search_word) {
		PageModel pm = new PageModel(pi);
		int pageIndex = pi.getOffset();
		int pageSize = pi.getPagesize();
		Integer total = spsFeeCorponceDao.queryEmpOnceFeeCount(sp_id,cp_id,shceme_id,area_id,period,search_word);
		pm.setTotal(total);
		List<Map<String,Object>> datas = spsFeeCorponceDao.queryEmpOnceFeeList(sp_id,cp_id,shceme_id,area_id,period,search_word, pageIndex, pageSize);
		pm.setDatas(datas);
		return pm;
	}

	/**
	 *  获取服务商下的方案列表
	 *  @param   sp_id  服务商ID
	 *	@return 			: List<Map<String,Object>>
	 *  @createDate  	: 2016-11-09 16:55
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 16:55
	 *  @updateAuthor  :
	 */
	@Override
	public List<Map<String, Object>> querySchemesBySpId(Integer sp_id,Integer cp_id) {
		return spsFeeCorponceDao.querySchemeList(cp_id,sp_id);
	}

	/**
	 *  查询员工一次性费用调整记录
	 *  @param   corponce 企业单次费用
	 *	@return 			: List<SpsFeeCorponce>
	 *  @createDate  	: 2016-11-09 16:55
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 16:55
	 *  @updateAuthor  :
	 */
	@Override
	public List<SpsFeeCorponce> queryCorpOnceItems(SpsFeeCorponce corponce) {
		return spsFeeCorponceDao.queryCorpOnceItems(corponce);
	}

	/**
	 *  逻辑删除费用列表
	 *  @param   corponce
	 *	@return 			: int
	 *  @createDate  	: 2016-11-09 16:56
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 16:56
	 *  @updateAuthor  :
	 */
	@Override
	public int delCorpOnceItems(ContextInfo cti, SpsFeeCorponce corponce) {
		return spsFeeCorponceDao.delCorpOnceItems(cti, corponce);
	}

	/**
	 *  创建员工费用跳转列表
	 *  @param spId	服务商ID
	 *  @param corponces 企业单次费用
	 *	@return 			: Result
	 *  @createDate  	: 2016-11-09 16:57
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 16:57
	 *  @updateAuthor  :
	 */
	@Override
	public Result createCorpItems(ContextInfo cti, Integer spId, SpsFeeCorponce[] corponces) {
		if(null != corponces){
			for (SpsFeeCorponce item : corponces){
				//替换空格，中英文的分号冒号
				item.setItem(item.getItem().replaceAll("[\\s:;：；]",""));
				
				if(null != item.getOfficialFee()){
					item.setFeeType("OFFICE");
				}
				item.setSpId(spId);
				if(null != item.getId()){
					item.setModifyDt(new Date());
					spsFeeCorponceDao.update(cti, item);
				}else{
					item.setSource(2);
					item.setDr(0);
					item.setCreateDt(new Date());
					item.setModifyDt(new Date());
					spsFeeCorponceDao.insert(cti, item);
				}
			}
		}
		return Result.createResult().setSuccess(true);
	}

	/**
	 *  获取子账单调整项目
	 *  @param sp_id	服务商
	 *  @param p_sp_id	分包服务商
	 *  @param cp_id	企业ID
	 *  @param period	月份
	 *	@return 			: List<SpsFeeCorponce>
	 *  @createDate  	: 2016-11-09 16:57
	 *  @author         	: konglc@xinfushe.com
	 *  @version        	: v1.0
	 *  @updateDate    	: 2016-11-09 16:57
	 *  @updateAuthor  :
	 */
	@Override
	public List<SpsFeeCorponce> queryChildBillCorpOnceItems(Integer sp_id, String p_sp_id, Integer cp_id, String period) {
		return spsFeeCorponceDao.queryChildBillCorpOnceItems(sp_id,p_sp_id,cp_id,period);
	}

}
