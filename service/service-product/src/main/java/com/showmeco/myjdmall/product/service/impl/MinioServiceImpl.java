package com.showmeco.myjdmall.product.service.impl;

import com.showmeco.myjdmall.product.config.MinIoClientConfig;
import com.showmeco.myjdmall.product.service.MinioService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 说明:
 *
 * @Author: @showmeco
 * @Date: 2024/2/7 9:10
 */

@Service
@Slf4j
public class MinioServiceImpl implements MinioService {


	@Autowired
	MinIoClientConfig minIoClientConfig;


	@Autowired
	MinioClient minioClient;


	/**
	 * 上传文件到minio
	 *
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws ServerException
	 * @throws InsufficientDataException
	 * @throws ErrorResponseException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws InvalidResponseException
	 * @throws XmlParserException
	 * @throws InternalException
	 */
	@Override
	public String uploadFile(MultipartFile file) throws IOException, ServerException, InsufficientDataException,
	                                                    ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException,
	                                                    InvalidResponseException, XmlParserException, InternalException {


		//拼接文件名
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String originalFilename = file.getOriginalFilename();
		String filename = date + "/" + UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
		minioClient.putObject(PutObjectArgs.builder()
		                                   .bucket(minIoClientConfig.getBucketName())
		                                   .object(filename)
		                                   .contentType(file.getContentType())
		                                   .stream(file.getInputStream(), file.getSize(), -1)
		                                   .build());
		log.info("品牌图片上传成功");
		return "http://192.168.200.33:9000/myjdmall/" + filename;
	}


	/**
	 * 从minio中删除对象
	 *
	 * @param logoUrl
	 * @return
	 */

	@Override
	public boolean removeObject(String logoUrl) throws ServerException, InsufficientDataException, ErrorResponseException,
	                                                   IOException, NoSuchAlgorithmException, InvalidKeyException,
	                                                   InvalidResponseException, XmlParserException, InternalException {

		//http://172.77.0.2:9000/myjdmall/2024-02-07/9f7fde62-f220-4f44-b603-3ed2afbd45d0.png
		String objectName = logoUrl.split(minIoClientConfig.getBucketName() + "/")[1];

		minioClient.removeObject(RemoveObjectArgs.builder()
		                                         .bucket(minIoClientConfig.getBucketName())
		                                         .object(objectName)
		                                         .build());
		return true;
	}


}
