package cn.linter.learning.file.service.impl;

import cn.linter.learning.file.service.FileService;
import io.minio.*;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.UUID;

/**
 * 文件服务实现类
 *
 * @author wangxiaoyang
 * @date 2020/11/15
 */
@Service
public class FileServiceImpl implements FileService {

    private final MinioClient minioClient;

    @Value("${gateway.address}")
    private String gatewayAddress;
    @Value("${minio.video-bucket-name}")
    private String videoBucketName;
    @Value("${minio.profile-picture-bucket-name}")
    private String profilePictureBucketName;

    public FileServiceImpl(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
    public String uploadVideo(MultipartFile multipartFile) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        makeBucket(videoBucketName);
        String fileName = multipartFile.getOriginalFilename();
        String fileType = Objects.requireNonNull(fileName).substring(fileName.lastIndexOf(".") + 1);
        String randomName = UUID.randomUUID().toString();
        minioClient.putObject(PutObjectArgs.builder().bucket(profilePictureBucketName).object(randomName + "." + fileType)
                .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1)
                .contentType(multipartFile.getContentType()).build());
        return gatewayAddress + "/dfs/" + videoBucketName + "/" + randomName + "." + fileType;
    }

    @Override
    public String uploadProfilePicture(MultipartFile multipartFile, String username) throws IOException, InvalidKeyException, InvalidResponseException,
            InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        makeBucket(profilePictureBucketName);
        String fileName = multipartFile.getOriginalFilename();
        String fileType = Objects.requireNonNull(fileName).substring(fileName.lastIndexOf(".") + 1);
        minioClient.putObject(PutObjectArgs.builder().bucket(profilePictureBucketName).object(username + "." + fileType)
                .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1)
                .contentType(multipartFile.getContentType()).build());
        return gatewayAddress + "/dfs/" + profilePictureBucketName + "/" + username + "." + fileType;
    }

    private void makeBucket(String bucketName) throws IOException, InvalidKeyException, InvalidResponseException,
            InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        boolean isBucketExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        if (!isBucketExist) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            String policy = "{\"Statement\":[{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:GetBucketLocation\",\"s3:ListBucket\"],\"Resource\":[\"arn:aws:s3:::" + bucketName + "\"]}," +
                    "{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:GetObject\"],\"Resource\":[\"arn:aws:s3:::" + bucketName + "/*\"]}],\"Version\":\"2012-10-17\"}";
            minioClient.setBucketPolicy(
                    SetBucketPolicyArgs.builder().bucket(bucketName).config(policy).build());
        }
    }

}
