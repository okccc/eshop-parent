package com.okccc.eshop.manager.service.impl;

import cn.hutool.core.date.DateUtil;
import com.okccc.eshop.manager.handler.MyRuntimeException;
import com.okccc.eshop.manager.properties.MinioProperties;
import com.okccc.eshop.manager.result.ResultCodeEnum;
import com.okccc.eshop.manager.service.FileUploadService;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

/**
 * @Author: okccc
 * @Date: 2024/9/12 17:01:23
 * @Desc:
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private MinioProperties minioProperties;

    @Override
    public String fileUpload(MultipartFile file) {
        try {
            // 1.创建minio客户端对象
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(minioProperties.getEndpoint())
                    .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                    .build();

            // 2.判断桶是否存在
            boolean exist = minioClient.bucketExists(
                    BucketExistsArgs.builder().bucket(minioProperties.getBucketName()).build());
            if (!exist) {
                // 不存在就创建该桶
                minioClient.makeBucket(
                        MakeBucketArgs.builder().bucket(minioProperties.getBucketName()).build());
            } else {
                System.out.println(minioProperties.getBucketName() + " already exists.");
            }

            // 3.设置上传文件的名称,根据日期分组,且文件名唯一
            String dateDir = DateUtil.format(new Date(), "yyyyMMdd");
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String originalFilename = file.getOriginalFilename();  // avatar.jpg
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));  // .jpg
            String fileName = dateDir + "/" + uuid + extension;  // 20240912/a9b9ba2fcc384f3a8bf3c041c437f95e.jpg
            System.out.println(fileName);

            // 4.通过IO流上传文件至minio服务器
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(minioProperties.getBucketName())
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .object(fileName)
                    .build();
            minioClient.putObject(putObjectArgs);

            // 5.返回上传文件的完整路径
            return minioProperties.getEndpoint() + "/" + minioProperties.getBucketName() + "/" + fileName;

        } catch (Exception e) {
            // 上传失败
            throw new MyRuntimeException(ResultCodeEnum.FAIL);
        }
    }

}
