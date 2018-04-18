package com.xfs.openlist.service;

import java.util.List;
import java.util.Map;

import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import com.xfs.openlist.model.SpsOlAreaauth;

/**
 * SpsOlAreaauthService
 * @author:quanjiahua<quanjiahua@163.com>
 * @date:2016/04/09 16:45:55
 */
public interface SpsOlAreaauthService {
	
	public int save(ContextInfo cti, SpsOlAreaauth vo);
	public int insert(ContextInfo cti, SpsOlAreaauth vo);
	public int remove(ContextInfo cti, SpsOlAreaauth vo);
	public int update(ContextInfo cti, SpsOlAreaauth vo);
	public PageModel findPage(PageInfo pi, SpsOlAreaauth vo);
	public List<SpsOlAreaauth> findAll(SpsOlAreaauth vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/04/09 16:45:55

    /**
     *  获取权限区域
     *  @param    ：vo  权限区域实体
     *  @return    :  List<Map<String, Object>>
     *  @createDate   : 16-11-13 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-13 上午10:47
     *  @updateAuthor  :
     */
	List<Map<String, Object>> findAuthAreas(SpsOlAreaauth vo);

    /**
     *  增加权限区域
     *  @param    ：vo  权限区域实体
     *  @return    :  int
     *  @createDate   : 16-11-13 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-13 上午10:47
     *  @updateAuthor  :
     */
	public int addSpAreaAuth(ContextInfo cti,SpsOlAreaauth vo);


    /**
     *  获取所有权限区域列表
     *  @param    ：
     *  @return    :  int
     *  @createDate   : 16-11-13 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-13 上午10:47
     *  @updateAuthor  :
     */
	public Map<Integer,SpsOlAreaauth> queryAllOIAreaauthList();

}
