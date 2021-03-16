package cn.linter.learning.file.service;

import io.minio.errors.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 文件服务接口
 *
 * @author wangxiaoyang
 * @date 2020/11/15
 */
public interface FileService {

    /**
     * 上传视频
     *
     * @param multipartFile 二进制文件
     * @return 视频地址
     * @throws IOException               S3操作I/O错误
     * @throws InvalidKeyException       缺少HMAC SHA-256库
     * @throws InvalidResponseException  S3服务器返回无效响应
     * @throws InsufficientDataException 输入流没有足够的有效数据
     * @throws NoSuchAlgorithmException  缺少MD5或SHA-256摘要库
     * @throws ServerException           HTTP服务器错误
     * @throws InternalException         内部库错误
     * @throws XmlParserException        XML解析错误
     * @throws ErrorResponseException    S3服务器返回错误响应
     */
    String uploadVideo(MultipartFile multipartFile) throws IOException, InvalidKeyException, InvalidResponseException,
            InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException;

    /**
     * 上传封面
     *
     * @param multipartFile 二进制文件
     * @return 封面地址
     * @throws IOException               S3操作I/O错误
     * @throws InvalidKeyException       缺少HMAC SHA-256库
     * @throws InvalidResponseException  S3服务器返回无效响应
     * @throws InsufficientDataException 输入流没有足够的有效数据
     * @throws NoSuchAlgorithmException  缺少MD5或SHA-256摘要库
     * @throws ServerException           HTTP服务器错误
     * @throws InternalException         内部库错误
     * @throws XmlParserException        XML解析错误
     * @throws ErrorResponseException    S3服务器返回错误响应
     */
    String uploadCoverPicture(MultipartFile multipartFile) throws IOException, InvalidKeyException, InvalidResponseException,
            InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException;

    /**
     * 上传头像
     *
     * @param multipartFile 二进制文件
     * @return 头像地址
     * @throws IOException               S3操作I/O错误
     * @throws InvalidKeyException       缺少HMAC SHA-256库
     * @throws InvalidResponseException  S3服务器返回无效响应
     * @throws InsufficientDataException 输入流没有足够的有效数据
     * @throws NoSuchAlgorithmException  缺少MD5或SHA-256摘要库
     * @throws ServerException           HTTP服务器错误
     * @throws InternalException         内部库错误
     * @throws XmlParserException        XML解析错误
     * @throws ErrorResponseException    S3服务器返回错误响应
     */
    String uploadProfilePicture(MultipartFile multipartFile) throws IOException, InvalidKeyException, InvalidResponseException,
            InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException;

}
