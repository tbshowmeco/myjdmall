package com.showmeco.myjdmall.product.controller;

import com.showmeco.myjdmall.common.result.Result;
import com.showmeco.myjdmall.product.service.MinioService;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 说明:
 *
 * @Author: @showmeco
 * @Date: 2024/2/6 23:27
 */
@RestController
public class FileUploadRestController {


    @Autowired
    MinioService minioService;


    /**
     * 上传图片,成功后返回图片浏览路径
     * @param file
     * @return
     */
    @PostMapping("admin/product/fileUpload")
    public Result uploadFile(@RequestPart("file") MultipartFile file) throws ServerException, InsufficientDataException,
                                                                             ErrorResponseException, IOException,
                                                                             NoSuchAlgorithmException, InvalidKeyException,
                                                                             InvalidResponseException, XmlParserException,
                                                                             InternalException {
        String path = minioService.uploadFile(file);
        return  Result.ok(path);
    }
}
