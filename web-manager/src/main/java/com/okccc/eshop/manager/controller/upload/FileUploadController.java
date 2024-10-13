package com.okccc.eshop.manager.controller.upload;

import com.okccc.eshop.manager.result.Result;
import com.okccc.eshop.manager.service.FileUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: okccc
 * @Date: 2024/9/12 15:24:37
 * @Desc:
 */
@Tag(name = "文件上传接口")
@RestController
@RequestMapping(value = "/admin/system")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @Operation(summary = "上传文件")
    @PostMapping(value = "/fileUpload")
    public Result<String> fileUpload(MultipartFile file) {
        String minioUrl = fileUploadService.fileUpload(file);
        return Result.ok(minioUrl);
    }
}
