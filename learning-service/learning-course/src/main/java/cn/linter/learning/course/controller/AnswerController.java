package cn.linter.learning.course.controller;

import cn.linter.learning.common.entity.Page;
import cn.linter.learning.common.entity.Result;
import cn.linter.learning.common.entity.ResultStatus;
import cn.linter.learning.common.utils.JwtUtil;
import cn.linter.learning.course.entity.Answer;
import cn.linter.learning.course.service.AnswerService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

/**
 * 答案控制器
 *
 * @author wangxiaoyang
 * @since 2021/03/04
 */
@RestController
@RequestMapping("answers")
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping("{id}")
    public Result<Answer> queryAnswer(@PathVariable("id") Long id) {
        return Result.of(ResultStatus.SUCCESS, answerService.queryById(id));
    }

    @GetMapping
    public Result<Page<Answer>> listAnswer(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize) {
        PageInfo<Answer> pageInfo = answerService.list(pageNum, pageSize);
        return Result.of(ResultStatus.SUCCESS, Page.of(pageInfo.getList(), pageInfo.getTotal()));
    }

    @PostMapping
    public Result<Answer> createAnswer(@RequestBody Answer answer, @RequestHeader("Authorization") String token) {
        return Result.of(ResultStatus.SUCCESS, answerService.create(answer, JwtUtil.getUsername(token)));
    }

    @PutMapping
    public Result<Answer> updateAnswer(@RequestBody Answer answer) {
        return Result.of(ResultStatus.SUCCESS, answerService.update(answer));
    }

    @DeleteMapping("{id}")
    public ResultStatus deleteAnswer(@PathVariable("id") Long id) {
        answerService.delete(id);
        return ResultStatus.SUCCESS;
    }

}
