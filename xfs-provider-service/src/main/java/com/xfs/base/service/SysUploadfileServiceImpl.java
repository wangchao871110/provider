package com.xfs.base.service;

import com.xfs.base.dao.SysUploadfileDAO;
import com.xfs.base.model.SysUploadfile;
import com.xfs.common.ContextInfo;
import com.xfs.common.PageInfo;
import com.xfs.common.aliyun.AliyunImageUtil;
import com.xfs.common.page.PageModel;
import com.xfs.common.tencent.CosTencentUtils;
import com.xfs.common.util.Config;
import com.xfs.common.util.CustomizedPropertyPlaceholderConfigurer;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * 上传文件服务类
 *
 * @author : konglc@xinfushe.com
 * @version : V1.0
 * @date : 2016-11-11 11:41
 */
@Service
public class SysUploadfileServiceImpl implements SysUploadfileService {

    private static final Logger log = Logger.getLogger(SysUploadfileServiceImpl.class);
    @Autowired
    private SysUploadfileDAO sysUploadfileDAO;

    public void save(ContextInfo cti, SysUploadfile vo) {
        sysUploadfileDAO.save(cti, vo);
    }

    public void insert(ContextInfo cti, SysUploadfile vo) {
        sysUploadfileDAO.insert(cti, vo);
    }

    public int remove(ContextInfo cti, SysUploadfile vo) {
        return sysUploadfileDAO.remove(cti, vo);
    }

    public void update(ContextInfo cti, SysUploadfile vo) {
        sysUploadfileDAO.update(cti, vo);
    }

    public PageModel findPage(PageInfo pi, SysUploadfile vo) {

        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = sysUploadfileDAO.countFreeFind(vo);
        pm.setTotal(total);
        List<SysUploadfile> datas = sysUploadfileDAO.freeFind(vo, pageIndex, pageSize);
        pm.setDatas(datas);
        return pm;

    }

    public PageModel findPage(PageInfo pi, SysUploadfile vo, String orderByColName) {
        PageModel pm = new PageModel(pi);
        int pageIndex = pi.getOffset();
        int pageSize = pi.getPagesize();
        Integer total = sysUploadfileDAO.countFreeFind(vo);
        pm.setTotal(total);
        List<SysUploadfile> datas = sysUploadfileDAO.freeFind(vo, pageIndex, pageSize, orderByColName);
        pm.setDatas(datas);
        return pm;
    }

    public List<SysUploadfile> findAll(SysUploadfile vo) {

        List<SysUploadfile> datas = sysUploadfileDAO.freeFind(vo);
        return datas;

    }

    public SysUploadfile findByPK(SysUploadfile vo) {
        return sysUploadfileDAO.findByPK(vo);
    }


    /**
     * 根据mysql中的fileid获取mongo中文件
     *
     * @param mysqlfileid
     * @return : com.mongodb.gridfs.GridFSDBFile
     * @createDate : 2016-11-11 11:43
     * @author : konglc@xinfushe.com
     * @version : v1.0
     * @updateDate : 2016-11-11 11:43
     * @updateAuthor :
     */
    @Override
    public File getFileById(Integer mysqlfileid) {
        SysUploadfile vo = new SysUploadfile();
        vo.setId(mysqlfileid);
        SysUploadfile uploadFile = findByPK(vo);
        //文件不存在
        if (null == uploadFile) {
            return null;
        }
        return getFileByPreviewUrl(uploadFile.getSavename());
    }

    @Override
    public File getFileByPreviewUrl(String savename) {
        String path = getPreviewUrl(savename);
        File file = new File(path);
        return file;
    }

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
    @Override
    public String getPhysicsFile(Integer id) {

        SysUploadfile vo = new SysUploadfile();
        vo.setId(id);
        SysUploadfile uploadFile = findByPK(vo);
        String fileRootPath = Config.getProperty("fileRootPath");// 文件根目录
        String filePath = fileRootPath + File.separator + uploadFile.getFilepath();
        //String suffix = uploadFile.getFilename().substring(uploadFile.getFilename().lastIndexOf("."));
        File localFile = new File(filePath, uploadFile.getSavename());
        if (localFile.exists()) {
            long lastUpLoadTime;
            if (uploadFile.getUpdatets() != null)
                lastUpLoadTime = uploadFile.getUpdatets().getTime();
            else
                lastUpLoadTime = uploadFile.getCreatets().getTime();
            //文件最后修改时间和文件服务记录的上传时间比较，如新，则直接返回目录中的文件，如旧重新获取
            if (localFile.lastModified() >= lastUpLoadTime) {
                return localFile.getPath();
            } else {
                localFile.delete();
            }
        } else {
            localFile.getParentFile().mkdirs();
        }
        InputStream is = null;
        try {
            is = downloadFile(uploadFile.getSavename());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (null == is) {
            return null;
        }
        try {
            FileUtils.copyInputStreamToFile(is, localFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return localFile.getPath();
    }


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
    @Override
    public SysUploadfile findSysUploadfile(SysUploadfile vo) {
        return sysUploadfileDAO.findSysUploadfile(vo);
    }

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
    @Override
    public List<SysUploadfile> freeFindByIds(List<String> ids) {
        return sysUploadfileDAO.freeFindByIds(ids);
    }

    @Override
    public String getPreviewUrl(String savename) {
        if (savename.contains("http") || savename.contains("https")) {
            return savename;
        }
        if (savename.startsWith(CosTencentUtils.BASE_PATH) || savename.contains(CosTencentUtils.BASE_PATH)) {
            if (savename.contains("http")) {
                return savename;
            } else {
                if  (CosTencentUtils.getUseXfsDomin()){
                    return CosTencentUtils.getXfsDommin() + savename;
                }else{
                    String tencentUrl = CosTencentUtils.getTencentHost();
                    String bucketName = CosTencentUtils.getBucketName();
                    tencentUrl = "http://" + bucketName + "-" + tencentUrl;
                    return tencentUrl + savename;
                }
            }

        } else {
            String aliUrl = AliyunImageUtil.getAliyunHost();
            String host = CustomizedPropertyPlaceholderConfigurer.getContextProperty("oss.bucket") != null ? CustomizedPropertyPlaceholderConfigurer.getContextProperty("oss.bucket").toString() : null;
            if (host != null && !aliUrl.contains(host)) {
                aliUrl = "https://" + host + "." + aliUrl;
            }
            return aliUrl + savename;
        }
    }

    @Override
    public InputStream downloadFile(String url) throws Exception {

        if (url.startsWith(CosTencentUtils.BASE_PATH) || url.contains(CosTencentUtils.BASE_PATH)) {
            return CosTencentUtils.downloadFile(url);
        } else {
            url = getPreviewUrl(URLEncoder.encode(url, "UTF-8"));
            return AliyunImageUtil.getObject(url);
        }

    }

    @Override
    public String uploadFile(File file, String imagePath) throws InterruptedException, IOException {
        return CosTencentUtils.uploadFile(file, imagePath);
        //return AliyunImageUtil.uploadLocalPic(file, imagePath);

    }

    @Override
    public String uploadPicture(File file) throws InterruptedException, IOException {
        return CosTencentUtils.uploadFile(file);
        //   return AliyunImageUtil.uploadPicture(file);
    }


    public String uploadLocalPicToC(File file, String path) throws InterruptedException, IOException {
        return AliyunImageUtil.uploadLocalPicToC(file, path);
    }


    public String uploadPicture(MultipartFile file) throws InterruptedException, IOException {
        return AliyunImageUtil.uploadPicture(file);
    }


    public String uploadPicture(MultipartFile file, String path) throws InterruptedException, IOException {
        return AliyunImageUtil.uploadPicture(file, path);
    }


    public String uploadPictureToC(MultipartFile file, String path) throws InterruptedException, IOException {
        return AliyunImageUtil.uploadPictureToC(file, path);
    }

    @Override
    public SysUploadfile findSysUploadfileByFilePath(String filePath) {
        return sysUploadfileDAO.findSysUploadfileByFilePath(filePath);
    }
}
