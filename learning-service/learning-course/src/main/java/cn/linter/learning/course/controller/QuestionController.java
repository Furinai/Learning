package cn.linter.learning.course.controller;

import cn.linter.learning.common.entity.Page;
import cn.linter.learning.common.entity.Result;
import cn.linter.learning.common.entity.ResultStatus;
import cn.linter.learning.common.utils.JwtUtil;
import cn.linter.learning.course.entity.Answer;
import cn.linter.learning.course.entity.Question;
import cn.linter.learning.course.service.AnswerService;
import cn.linter.learning.course.service.QuestionService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

/**
 * 问题控制器
 *
 * @author wangxiaoyang
 * @since 2021/03/04
 */
@RestController
@RequestMapping("questions")
public class QuestionController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    public QuestionController(QuestionService questionService, AnswerService answerService) {
        this.questionService = questionService;
        this.answerService = answerService;
    }

    @GetMapping("{id}")
    public Result<Question> queryQuestion(@PathVariable("id") Long id) {
        return Result.of(ResultStatus.SUCCESS, questionService.queryById(id));
    }

    @GetMapping("{id}/answers")
    public Result<Page<Answer>> listAnswersOfQuestion(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize,
                                                      @PathVariable("id") Long id) {
        PageInfo<Answer> pageInfo = answerService.listByQuestionId(pageNum, pageSize, id);
        return Result.of(ResultStatus.SUCCESS, Page.of(pageInfo.getList(), pageInfo.getTotal()));
    }

    @GetMapping
    public Result<Page<Question>> listQuestion(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize) {
        PageInfo<Question> pageInfo = questionService.list(pageNum, pageSize);
        return Result.of(ResultStatus.SUCCESS, Page.of(pageInfo.getList(), pageInfo.getTotal()));
    }

    @PostMapping
    public Result<Question> createQuestion(@RequestBody Question question, @RequestHeader("Authorization") String token) {
        return Result.of(ResultStatus.SUCCESS, questionService.create(question, JwtUtil.getUsername(token)));
    }

    @PutMapping
    public Result<Question> updateQuestion(@RequestBody Question question) {
        return Result.of(ResultStatus.SUCCESS, questionService.update(question));
    }

    @DeleteMapping("{id}")
    public ResultStatus deleteQuestion(@PathVariable("id") Long id) {
        questionService.delete(id);
        return ResultStatus.SUCCESS;
    }

}
