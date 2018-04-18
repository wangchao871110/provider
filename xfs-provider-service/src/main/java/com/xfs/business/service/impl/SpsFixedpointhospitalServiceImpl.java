package com.xfs.business.service.impl;

import com.xfs.business.dao.SpsFixedpointhospitalDao;
import com.xfs.business.model.SpsFixedpointhospital;
import com.xfs.business.service.SpsFixedpointhospitalService;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpsFixedpointhospitalServiceImpl implements SpsFixedpointhospitalService {

    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(SpsFixedpointhospitalServiceImpl.class);

    @Autowired
    private SpsFixedpointhospitalDao spsFixedpointhospitalDao;

    public int save(ContextInfo cti, SpsFixedpointhospital vo) {
        return spsFixedpointhospitalDao.save(cti, vo);
    }

    public int insert(ContextInfo cti, SpsFixedpointhospital vo) {
        return spsFixedpointhospitalDao.insert(cti, vo);
    }

    public int remove(ContextInfo cti, SpsFixedpointhospital vo) {
        return spsFixedpointhospitalDao.remove(cti, vo);
    }

    public int update(ContextInfo cti, SpsFixedpointhospital vo) {
        return spsFixedpointhospitalDao.update(cti, vo);
    }

    public PageModel findPage(PageInfo pi, SpsFixedpointhospital vo) {

        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = spsFixedpointhospitalDao.countFreeFind(vo);
        pm.setTotal(total);
        List<SpsFixedpointhospital> datas = spsFixedpointhospitalDao.freeFind(vo, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;

    }

    public List<SpsFixedpointhospital> findAll(SpsFixedpointhospital vo) {

        List<SpsFixedpointhospital> datas = spsFixedpointhospitalDao.freeFind(vo);
        return datas;

    }


    //温馨提示：
    //以上为自动生成代码，请勿改动或删除。如有新“代码”添加请在下方区域内填写
    //2016/04/20 12:05:11

    public List<SpsFixedpointhospital> findtenfive() {
        return spsFixedpointhospitalDao.freetenfive();
    }


    public PageModel findPageHos(PageInfo pi, SpsFixedpointhospital vo) {

        PageModel pm = new PageModel(pi);
        int pageIndex = 0;
        int pageSize = 50;
        Integer total = spsFixedpointhospitalDao.countFreeFindOr(vo);
        pm.setTotal(total);
        List<SpsFixedpointhospital> datas = spsFixedpointhospitalDao.freeFindOr(vo, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;

    }

    public List<SpsFixedpointhospital> getFixHosptialByHospitalCodes(List<String> hospitalCodes) {
        return spsFixedpointhospitalDao.getFixHosptialByHospitalCodes(hospitalCodes);
    }

    @Override
    public PageModel getHospitalByAreaIdAndType(PageInfo pageInfo,String condition,Integer reginCode,Integer areaCode, String hosptialType) {
        PageModel pm = new PageModel(pageInfo);
        List<SpsFixedpointhospital> spsFixedpointhospitals=  spsFixedpointhospitalDao.getHospitalByAreaIdAndType(condition,reginCode,areaCode,hosptialType,pageInfo.getOffset(),pageInfo.getPagesize());

        Integer total = spsFixedpointhospitalDao.getHospitalByAreaIdAndTypeCount(condition,reginCode,areaCode,hosptialType);
        pm.setTotal(total.intValue());
        pm.setDatas(spsFixedpointhospitals);
        return  pm;
       // return spsFixedpointhospitalDao.getHospitalByAreaIdAndType(condition,reginCode,areaCode,hosptialType,pageInfo.getOffset(),pageInfo.getPagesize());
    }

    @Override
    public List<SpsFixedpointhospital> getHospitalByCondition(String condition) {
        return spsFixedpointhospitalDao.getHospitalByCondition(condition);
    }

    public static void main(String[] args) {
        List df = new ArrayList();
        df.add("123");
        df.add("123");
        df.add("");
        df.remove("");
        System.out.println(df.size());
    }
}
