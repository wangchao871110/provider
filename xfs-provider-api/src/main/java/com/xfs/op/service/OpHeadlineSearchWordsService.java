package com.xfs.op.service;
import java.util.List;
import com.xfs.common.page.PageModel;
import com.xfs.common.ContextInfo;
import com.xfs.op.model.OpHeadlineSearchWords;

/**
 * OpHeadlineSearchWordsService
 * @author:yangfw@xinfushe.com
 * @date:2017/04/19 16:29:44
 */
public interface OpHeadlineSearchWordsService {
	
	public int save(ContextInfo cti, OpHeadlineSearchWords vo);
	public int insert(ContextInfo cti, OpHeadlineSearchWords vo);
	public int remove(ContextInfo cti, OpHeadlineSearchWords vo);
	public int update(ContextInfo cti, OpHeadlineSearchWords vo);
	public PageModel findPage(OpHeadlineSearchWords vo);
	public List<OpHeadlineSearchWords> findAll(OpHeadlineSearchWords vo);
	
	
	//温馨提示：
	//以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
	//2017/04/19 16:29:44


    /**
     * 更新搜索词的统计数据
     * @param searchWord
     * @return
     */
    public boolean updateOrAddSearchWord(ContextInfo contextInfo ,String searchWord);

	
}
