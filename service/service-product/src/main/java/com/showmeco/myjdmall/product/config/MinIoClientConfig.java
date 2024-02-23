package com.showmeco.myjdmall.product.config;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 说明:
 *
 * @Author: @showmeco
 * @Date: 2024/2/7 0:02
 */
@Data
@Component
public class MinIoClientConfig {
	@Value("${minio.endpoint}")
	private String endpoint;
	@Value("${minio.accessKey}")
	private String accessKey;
	@Value("${minio.secretKey}")
	private String secretKey;

	@Value("${minio.bucketName}")
	private String bucketName;


	@Bean
	public MinioClient MinioClient(MinIoClientConfig minIoClientConfig) throws ServerException,
	                                                                                       InsufficientDataException,
	                                                                                       ErrorResponseException, IOException,
	                                                                                       NoSuchAlgorithmException,
	                                                                                       InvalidKeyException,
	                                                                                       InvalidResponseException,
	                                                                                       XmlParserException,
	                                                                                       InternalException {
		//获取minio客户端
		MinioClient minioClient = MinioClient.builder()
		                                     .endpoint(minIoClientConfig.getEndpoint())
		                                     .credentials(minIoClientConfig.getAccessKey(), minIoClientConfig.getSecretKey())
		                                     .build();

		//判断是否存在这个桶,
		boolean found = minioClient.bucketExists(BucketExistsArgs.builder()
		                                                         .bucket(minIoClientConfig.getBucketName())
		                                                         .build());
		if (!found) {
			minioClient.makeBucket(MakeBucketArgs.builder()
			                                     .bucket(minIoClientConfig.getBucketName())
			                                     .build());
		}


		return minioClient;
	}
}
