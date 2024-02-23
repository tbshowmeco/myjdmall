package com.showmeco.myjdmall.product.service;

import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 说明:
 *
 * @Author: @showmeco
 * @Date: 2024/2/7 9:09
 */
public interface MinioService {
    String uploadFile(MultipartFile file) throws IOException, ServerException, InsufficientDataException, ErrorResponseException,
                                                 NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException,
                                                 XmlParserException, InternalException;

	boolean removeObject(String logoUrl) throws ServerException, InsufficientDataException, ErrorResponseException, IOException,
	                                            NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException,
	                                            XmlParserException, InternalException;
}
