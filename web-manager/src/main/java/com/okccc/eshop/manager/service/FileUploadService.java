package com.okccc.eshop.manager.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: okccc
 * @Date: 2024/9/12 17:01:11
 * @Desc:
 */
public interface FileUploadService {

    // 上传文件
    String fileUpload(MultipartFile file);
}
