package com.xfs.bs.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xfs.base.model.SysDictitem;
import com.xfs.base.service.SysDictitemService;
import com.xfs.common.taglibs.service.TagDateService;
import com.xfs.common.taglibs.vo.TagDate;

/**
 * Created by admin on 2016/3/29.
 */
@Service
public class TagDateServiceImpl implements TagDateService{
    @Autowired
    SysDictitemService sysDictitemService;
    @Override
    public List<TagDate> getTagDate(String name) {
        List<SysDictitem> dictItems = sysDictitemService.findByDictName(name);
        List<TagDate> list=new ArrayList<TagDate>();
        if(null!=dictItems && dictItems.size()>0){
            for(int i=0;i<dictItems.size();i++){
                SysDictitem item = dictItems.get(i);
                TagDate tagDate=new TagDate();
                tagDate.setId(item.getId());
                tagDate.setCode(item.getCode());
                tagDate.setName(item.getName());
                list.add(tagDate);
            }
        }
        return list;
    }
}
