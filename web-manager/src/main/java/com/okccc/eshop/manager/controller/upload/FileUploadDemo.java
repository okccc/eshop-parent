package com.okccc.eshop.manager.controller.upload;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;

import java.io.FileInputStream;

/**
 * @Author: okccc
 * @Date: 2024/9/12 15:47:45
 * @Desc: 上传文件到minio服务器
 */
public class FileUploadDemo {
    public static void main(String[] args) throws Exception {
        // 1.创建minio客户端对象
        MinioClient minioClient = MinioClient.builder()
                .endpoint("http://127.0.0.1:9000")
                .credentials("minioadmin", "minioadmin")
                .build();

        // 2.判断桶是否存在,bucket是minio服务器存放数据的地方
        boolean exist = minioClient.bucketExists(
                BucketExistsArgs.builder().bucket("eshop-bucket").build());
        if (!exist) {
            // 不存在就创建该桶
            minioClient.makeBucket(
                    MakeBucketArgs.builder().bucket("eshop-bucket").build());
        } else {
            System.out.println("eshop-bucket already exists.");
        }

        // 3.通过IO流上传文件
        FileInputStream fis = new FileInputStream("/Users/okc/Documents/avatar.jpg") ;
        PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                .bucket("eshop-bucket")
                .stream(fis, fis.available(), -1)
                .object("avatar.jpg")
                .build();
        minioClient.putObject(putObjectArgs);

        // 文件在minio服务器上的完整路径
        String fileUrl = "http://127.0.0.1:9000/eshop-bucket/avatar.jpg" ;
        System.out.println(fileUrl);
    }

}
