package com.blog.cms.fileInfo.service.impl;

import java.util.List;

import cn.xuyanwu.spring.file.storage.FileInfo;
import cn.xuyanwu.spring.file.storage.FileStorageService;
import com.blog.cms.fileInfo.domain.FileInfoVO;
import com.blog.common.config.RuoYiConfig;
import com.blog.common.constant.Constants;
import com.blog.common.utils.DateUtils;
import com.blog.common.utils.StringUtils;
import com.blog.common.utils.file.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blog.cms.fileInfo.mapper.SysFileInfoMapper;
import com.blog.cms.fileInfo.domain.SysFileInfo;
import com.blog.cms.fileInfo.service.ISysFileInfoService;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 文件管理Service业务层处理
 * 
 * @author blog
 * @date 2021-12-29
 */
@Service
public class SysFileInfoServiceImpl implements ISysFileInfoService 
{
    @Autowired
    private SysFileInfoMapper sysFileInfoMapper;

    /**
     * 文件存储服务
     */
//    @Autowired
//    private FileStorageService fileStorageService;

    @Override
    public SysFileInfo selectSysFileInfoByFileObjectName(String fileObjectName) {
        return sysFileInfoMapper.selectSysFileInfoByFileObjectName(fileObjectName);
    }

    /**
     * 上传
     *
     * @return {@link FileInfoVO}
     */
//    @Override
//    public FileInfoVO upload(MultipartFile file) {
//        FileInfo upload = fileStorageService.of(file).setPlatform("local-1").upload();
//        FileInfoVO fileInfoVO = new FileInfoVO();
//        BeanUtils.copyProperties(upload,fileInfoVO);
//        return fileInfoVO;
//    }

    /**
     * 查询文件管理
     * 
     * @param fileId 文件管理主键
     * @return 文件管理
     */
    @Override
    public SysFileInfo selectSysFileInfoByFileId(Long fileId)
    {
        return sysFileInfoMapper.selectSysFileInfoByFileId(fileId);
    }

    /**
     * 查询文件管理列表
     * 
     * @param sysFileInfo 文件管理
     * @return 文件管理
     */
    @Override
    public List<SysFileInfo> selectSysFileInfoList(SysFileInfo sysFileInfo)
    {
        return sysFileInfoMapper.selectSysFileInfoList(sysFileInfo);
    }

    /**
     * 新增文件管理
     * 
     * @param sysFileInfo 文件管理
     * @return 结果
     */
    @Override
    public int insertSysFileInfo(SysFileInfo sysFileInfo)
    {
        sysFileInfo.setCreateTime(DateUtils.getNowDate());
        return sysFileInfoMapper.insertSysFileInfo(sysFileInfo);
    }

    /**
     * 修改文件管理
     * 
     * @param sysFileInfo 文件管理
     * @return 结果
     */
    @Override
    public int updateSysFileInfo(SysFileInfo sysFileInfo)
    {
        sysFileInfo.setUpdateTime(DateUtils.getNowDate());
        return sysFileInfoMapper.updateSysFileInfo(sysFileInfo);
    }

    /**
     * 批量删除文件管理
     * 
     * @param fileIds 需要删除的文件管理主键
     * @return 结果
     */
    @Override
    public int deleteSysFileInfoByFileIds(Long[] fileIds)
    {
        for (int i = 0; i<fileIds.length; i++){
            Long fileId = fileIds[i];
            SysFileInfo sysFileInfo = sysFileInfoMapper.selectSysFileInfoByFileId(fileId);
            String filePath = RuoYiConfig.getProfile() + StringUtils.substringAfter(sysFileInfo.getFilePath(), Constants.RESOURCE_PREFIX);
            FileUtils.deleteFile(filePath);
        }
        return sysFileInfoMapper.deleteSysFileInfoByFileIds(fileIds);
    }

    /**
     * 删除文件管理信息
     * 
     * @param fileId 文件管理主键
     * @return 结果
     */
    @Override
    public int deleteSysFileInfoByFileId(Long fileId)
    {
        SysFileInfo sysFileInfo = sysFileInfoMapper.selectSysFileInfoByFileId(fileId);
        String filePath = RuoYiConfig.getProfile() + StringUtils.substringAfter(sysFileInfo.getFilePath(), Constants.RESOURCE_PREFIX);
        FileUtils.deleteFile(filePath);
        return sysFileInfoMapper.deleteSysFileInfoByFileId(fileId);
    }

    @Override
    public int deleteSysFileInfoByFileObjectName(String fileObjectName) {
        SysFileInfo sysFileInfo = sysFileInfoMapper.selectSysFileInfoByFileObjectName(fileObjectName);
        String filePath = RuoYiConfig.getProfile() + StringUtils.substringAfter(sysFileInfo.getFilePath(), Constants.RESOURCE_PREFIX);
        FileUtils.deleteFile(filePath);
        return sysFileInfoMapper.deleteSysFileInfoByFileObjectName(fileObjectName);
    }
}
