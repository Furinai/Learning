package cn.linter.learning.file.controller;

import cn.linter.learning.common.entity.Result;
import cn.linter.learning.common.entity.ResultStatus;
import cn.linter.learning.common.utils.JwtUtil;
import cn.linter.learning.file.service.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件控制器
 *
 * @author wangxiaoyang
 * @date 2020/11/15
 */
@RestController
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("videos")
    public Result<String> uploadVideo(@RequestParam MultipartFile multipartFile) throws Exception {
        return Result.of(ResultStatus.SUCCESS, fileService.uploadVideo(multipartFile));
    }

    @PostMapping("profile-pictures")
    public Result<String> uploadProfilePicture(@RequestParam MultipartFile multipartFile, @RequestHeader("Authorization") String token) throws Exception {
        return Result.of(ResultStatus.SUCCESS, fileService.uploadProfilePicture(multipartFile, JwtUtil.getUsername(token)));
    }

}
