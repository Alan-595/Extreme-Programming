package com.phone.modules.service.impl;

import com.phone.commons.utils.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 文件列表 服务实现类
 * </p>
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class FilesService {
    @Autowired
    private FileUtils fileUtils;

    /**
     * oss桶名称
     */
    @Value("${ali.oss.bucketName}")
    private String bucketName;

    /**
     * 外部访问连接开头
     */
    @Value("${ali.oss.address}")
    private String ossAddress;


    /**
     * @param file
     * @return java.lang.Object
     * @description 上传单个文件
     */
    public String uploadSingleFile(MultipartFile file) {
        // 获取第三方id
        String thirdNo = FileUtils.getThirdId();
        // 文件上传
        String filePath = fileUtils.uploadFile(file, "phone_manage/"+ thirdNo, bucketName);
        //上传成功
        if (StringUtils.isNotEmpty(filePath)) {
            return ossAddress + filePath;
        }
        //上传失败
        return null;
    }

}
