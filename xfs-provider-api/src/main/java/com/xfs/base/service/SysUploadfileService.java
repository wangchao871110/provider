package com.xfs.base.service;

import com.xfs.base.model.SysUploadfile;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.page.PageModel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 文件上传服务类
 *
 * @author : konglc@xinfushe.com
 * @version : V1.0
 * @date : 2016-11-11 11:31
 */
public interface SysUploadfileService {

    public void save(ContextInfo cti, SysUploadfile vo);

    public void insert(ContextInfo cti, SysUploadfile vo);

    public int remove(ContextInfo cti, SysUploadfile vo);

    public void update(ContextInfo cti, SysUploadfile vo);

    public PageModel findPage(PageInfo pi, SysUploadfile vo);

    public PageModel findPage(PageInfo pi, SysUploadfile vo, String orderByColName);

    public List<SysUploadfile> findAll(SysUploadfile vo);

    public SysUploadfile findByPK(SysUploadfile vo);


    /**
     * 根据mysql文件ID获取文件下载到当前服务器，作为缓存
     *
     * @param id mysql文件ID
     * @return : java.lang.String
     * @createDate : 2016-11-11 11:44
     * @author : konglc@xinfushe.com
     * @version : v1.0
     * @updateDate : 2016-11-11 11:44
     * @updateAuthor :
     */
    public String getPhysicsFile(Integer id);


    /**
     * 根据文件ID获取文件信息
     *
     * @param vo
     * @return : com.xfs.base.model.SysUploadfile
     * @createDate : 2016-11-11 11:46
     * @author : konglc@xinfushe.com
     * @version : v1.0
     * @updateDate : 2016-11-11 11:46
     * @updateAuthor :
     */
    public SysUploadfile findSysUploadfile(SysUploadfile vo);

    /**
     * 根据mysql文件ID获取文件信息列表
     *
     * @param ids
     * @return : java.util.List<com.xfs.base.model.SysUploadfile>
     * @createDate : 2016-11-11 11:46
     * @author : konglc@xinfushe.com
     * @version : v1.0
     * @updateDate : 2016-11-11 11:46
     * @updateAuthor :
     */
    public List<SysUploadfile> freeFindByIds(List<String> ids);

    /**
     * 获取文件的全路径
     *
     * @param savename
     * @return
     */
    String getPreviewUrl(String savename);

    /**
     * 文件下载
     *
     * @param url
     * @return
     */
    InputStream downloadFile(String url) throws Exception;


    String uploadFile(File file, String imagePath) throws InterruptedException, IOException;

    String uploadPicture(File file) throws InterruptedException, IOException;

//    @Deprecated
//    String uploadLocalPicToC(File file, String path) throws InterruptedException, IOException;
//
//    @Deprecated
//    String uploadPicture(MultipartFile file) throws InterruptedException, IOException;
//
//    @Deprecated
//    String uploadPicture(MultipartFile file, String path) throws InterruptedException, IOException;
//
//    @Deprecated
//    String uploadPictureToC(MultipartFile file, String path) throws InterruptedException, IOException;
    @Deprecated
    public File getFileById(Integer mysqlfileid);
    @Deprecated
    public File getFileByPreviewUrl(String savename);

    public SysUploadfile findSysUploadfileByFilePath(String filePath);
}
