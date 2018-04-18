package com.xfs.sps.service;

import java.io.File;

import com.xfs.common.ContextInfo;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mongodb.gridfs.GridFSDBFile;
import com.xfs.common.ContextInfo;
import com.xfs.common.Result;
import com.xfs.sp.model.SpService;
import com.xfs.user.model.SysUser;

/**
 * c端文件处理
 *
 * @author liuyuan
 * @date 2016/7/18
 */
public interface QssFileService {
    /**
     * 将文件保存到mongo
     *
     * @param file  文件
     * @return  mongo中图片id
     */
    public String saveToMongo(File file);

    /**
     * 通过id获取mongo中文件
     * @param id    mongo中文件id
     * @return  mongo文件类型
     */
    public GridFSDBFile getFileByPK(String id);

	public Result uploadImage(SpService spService, ContextInfo cti, MultipartHttpServletRequest multiRequest);

	public Result cutImage(String fileId, int x, int y, int width, int height, ContextInfo cti);

	public GridFSDBFile readFileById(String fileId);

}
