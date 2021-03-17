package cn.linter.learning.course.controller;

import cn.linter.learning.common.entity.Page;
import cn.linter.learning.common.entity.Result;
import cn.linter.learning.common.entity.ResultStatus;
import cn.linter.learning.common.utils.JwtUtil;
import cn.linter.learning.course.entity.Evaluation;
import cn.linter.learning.course.service.EvaluationService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

/**
 * 评价控制器
 *
 * @author wangxiaoyang
 * @since 2021/03/07
 */
@RestController
@RequestMapping("evaluations")
public class EvaluationController {

    private final EvaluationService evaluationService;

    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @GetMapping("{id}")
    public Result<Evaluation> queryEvaluation(@PathVariable("id") Long id) {
        return Result.of(ResultStatus.SUCCESS, evaluationService.queryById(id));
    }

    @GetMapping
    public Result<Page<Evaluation>> listEvaluation(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize) {
        PageInfo<Evaluation> pageInfo = evaluationService.list(pageNum, pageSize);
        return Result.of(ResultStatus.SUCCESS, Page.of(pageInfo.getList(), pageInfo.getTotal()));
    }

    @PostMapping
    public Result<Evaluation> createEvaluation(@RequestBody Evaluation evaluation, @RequestHeader("Authorization") String token) {
        return Result.of(ResultStatus.SUCCESS, evaluationService.create(evaluation, JwtUtil.getUsername(token)));
    }

    @PutMapping
    public Result<Evaluation> updateEvaluation(@RequestBody Evaluation evaluation) {
        return Result.of(ResultStatus.SUCCESS, evaluationService.update(evaluation));
    }

    @DeleteMapping("{id}")
    public ResultStatus deleteEvaluation(@PathVariable("id") Long id) {
        evaluationService.delete(id);
        return ResultStatus.SUCCESS;
    }

}
