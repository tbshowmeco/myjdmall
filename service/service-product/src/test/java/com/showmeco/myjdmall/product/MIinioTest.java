package com.showmeco.myjdmall.product;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.MinioException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 说明:
 *
 * @Author: @showmeco
 * @Date: 2024/2/6 21:30
 */
@SpringBootTest
public class MIinioTest {

    @Test
    public void testMinio() {
        try {
            // Create a minioClient with the MinIO server playground, its access key and secret key.
            MinioClient minioClient = MinioClient.builder().endpoint("http://192.168.200.33:9000").credentials("admin", "admin123456").build();

            // Make 'asiatrip' bucket if not exist.
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket("myjdmall").build());
            if (!found) {
                // Make a new bucket called 'asiatrip'.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("myjdmall").build());
            } else {
                System.out.println("桶已经存在");
            }

            // Upload '/home/user/Photos/asiaphotos.zip' as object name 'asiaphotos-2015.zip' to bucket
            // 'asiatrip'.
            minioClient.uploadObject(UploadObjectArgs.builder().bucket("myjdmall").object("1dfb558c4e6efc4f0eee498d213022e5.jpg").filename("D:\\图片\\Saved Pictures\\1dfb558c4e6efc4f0eee498d213022e5.jpg").build());
            System.out.println("上传成功");
        } catch (MinioException e) {
            System.err.println("Error occurred: " + e);
            System.err.println("HTTP trace: " + e.httpTrace());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

}
