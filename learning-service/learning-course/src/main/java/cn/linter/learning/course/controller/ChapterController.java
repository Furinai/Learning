package cn.linter.learning.course.controller;

import cn.linter.learning.common.entity.Result;
import cn.linter.learning.common.entity.ResultStatus;
import cn.linter.learning.course.entity.Chapter;
import cn.linter.learning.course.service.ChapterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 章节控制器
 *
 * @author wangxiaoyang
 * @since 2021/03/02
 */
@RestController
@RequestMapping("chapters")
public class ChapterController {

    private final ChapterService chapterService;

    public ChapterController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @GetMapping("{id}")
    public Result<Chapter> queryChapter(@PathVariable("id") Long id) {
        return Result.of(ResultStatus.SUCCESS, chapterService.queryById(id));
    }

    @GetMapping
    public Result<List<Chapter>> listChapter() {
        return Result.of(ResultStatus.SUCCESS, chapterService.list());
    }

    @PostMapping
    public Result<Chapter> createChapter(@RequestBody Chapter chapter) {
        return Result.of(ResultStatus.SUCCESS, chapterService.create(chapter));
    }

    @PutMapping
    public Result<Chapter> updateChapter(@RequestBody Chapter chapter) {
        return Result.of(ResultStatus.SUCCESS, chapterService.update(chapter));
    }

    @DeleteMapping("{id}")
    public ResultStatus deleteChapter(@PathVariable("id") Long id) {
        chapterService.delete(id);
        return ResultStatus.SUCCESS;
    }

}
