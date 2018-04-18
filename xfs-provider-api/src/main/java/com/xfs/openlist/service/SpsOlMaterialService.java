package com.xfs.openlist.service;

import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.openlist.model.SpsOl;
import com.xfs.openlist.model.SpsOlMaterial;

/**
 * SpsOlMaterialService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/04/09 16:45:56
 */
public interface SpsOlMaterialService {
	
	public int save(ContextInfo cti, SpsOlMaterial vo);
	public int insert(ContextInfo cti, SpsOlMaterial vo);
	public int remove(ContextInfo cti, SpsOlMaterial vo);
	public int update(ContextInfo cti, SpsOlMaterial vo);
	public PageModel findPage(PageInfo pi, SpsOlMaterial vo);
	public List<SpsOlMaterial> findAll(SpsOlMaterial vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/04/09 16:45:56

    /**
     * 根据业务类型id删除业务材料
     *  @param    : olId openlistid
     *  @return    :  int
     *  @createDate   : 16-11-13 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-13 上午10:47
     *  @updateAuthor  :
     */
	int removeByOlId(ContextInfo cti,Integer olId);

    /**
     * 通过openlist查询相关材料
     *  @param    : vo openlist对象
     *  @return    :  List<Map<String, Object>>
     *  @createDate   : 16-11-13 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-13 上午10:47
     *  @updateAuthor  :
     */
	List<Map<String, Object>> findByOlId(SpsOl vo);

    /**
     * 检查材料是否被使用
     *  @param    : vo 业务材料实体
     *  @return    :  int
     *  @createDate   : 16-11-13 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-13 上午10:47
     *  @updateAuthor  :
     */
	int checkUesed(SpsOlMaterial vo);

}
