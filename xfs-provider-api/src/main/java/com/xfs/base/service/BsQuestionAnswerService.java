package com.xfs.base.service;

import java.util.List;

import com.xfs.base.model.BsQuestionAnswer;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * BsQuestionAnswerService 问题回答服务业务类
 * @author 	: wangxs@xinfushe.com
 * @date 	: 2016-11-11 15:18
 * @version 	: V1.0
 */
public interface BsQuestionAnswerService {
	
	public int save(ContextInfo cti, BsQuestionAnswer vo);
	public int insert(ContextInfo cti, BsQuestionAnswer vo);
	public int remove(ContextInfo cti, BsQuestionAnswer vo);
	public int update(ContextInfo cti, BsQuestionAnswer vo);
	public PageModel findPage(PageInfo pi, BsQuestionAnswer vo);
	public List<BsQuestionAnswer> findAll(BsQuestionAnswer vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2016/08/23 14:33:28

    /**
     *  获取回答
     *  @param    ：  vo 问题回答实体
     *  @return    :   PageModel
     *  @createDate   : 16-11-11 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-11 上午10:47
     *  @updateAuthor  :
     */
	public PageModel findAnswers(PageInfo pi, BsQuestionAnswer vo);

    /**
     *  根据主键查找问题答案
     *  @param    ：  vo 问题回答实体
     *  @return    :   BsQuestionAnswer
     *  @createDate   : 16-11-14 上午10:47
     *  @author          : wangxs@xinfushe.com
     *  @version         : v1.0
     *  @updateDate     : 16-11-14 上午10:47
     *  @updateAuthor  :
     */
	public BsQuestionAnswer findByPK(BsQuestionAnswer vo);

}
