package com.xfs.business.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.business.dao.BdBstypefieldDao;
import com.xfs.business.model.BdBstypefield;
import com.xfs.business.service.BdBstypefieldService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

/**
 * 业务字段服务类
 * @author 	: konglc@xinfushe.com
 * @date 	: 2016-11-11 14:53
 * @version 	: V1.0
 */
@Service
public class BdBstypefieldServiceImpl implements BdBstypefieldService {

    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(BdBstypefieldServiceImpl.class);

    @Autowired
    private BdBstypefieldDao bdBstypefieldDao;

    public int save(ContextInfo cti, BdBstypefield vo) {
        return bdBstypefieldDao.save(cti, vo);
    }

    public int insert(ContextInfo cti, BdBstypefield vo) {
        return bdBstypefieldDao.insert(cti, vo);
    }

    public int remove(ContextInfo cti, BdBstypefield vo) {
        return bdBstypefieldDao.remove(cti, vo);
    }

    public int update(ContextInfo cti, BdBstypefield vo) {
        return bdBstypefieldDao.update(cti, vo);
    }

    public PageModel findPage(PageInfo pi, BdBstypefield vo) {

        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = bdBstypefieldDao.countFreeFind(vo);
        pm.setTotal(total);
        List<BdBstypefield> datas = bdBstypefieldDao.freeFind(vo, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;

    }

    public List<BdBstypefield> findAll(BdBstypefield vo) {

        List<BdBstypefield> datas = bdBstypefieldDao.freeFind(vo);
        return datas;

    }


    //温馨提示：
    //以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    //2016/05/24 11:25:06

    /**
     *  获取业务字段信息
     *  @param   vo
     *	@return 			: com.xfs.business.model.BdBstypefield
     *  @createDate  	: 2016-11-11 14:53
     *  @author         	: konglc@xinfushe.com
     *  @version        	: v1.0
     *  @updateDate    	: 2016-11-11 14:53
     *  @updateAuthor  :
     */
    @Override
    public BdBstypefield findBdBstypefield(BdBstypefield vo) {
        return bdBstypefieldDao.findBdBstypefield(vo);
    }
}
