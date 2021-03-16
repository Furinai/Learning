package cn.linter.learning.course.controller;

import cn.linter.learning.common.entity.Result;
import cn.linter.learning.common.entity.ResultStatus;
import cn.linter.learning.course.entity.Category;
import cn.linter.learning.course.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类控制器
 *
 * @author wangxiaoyang
 * @since 2021/02/16
 */
@RestController
@RequestMapping("categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("{id}")
    public Result<Category> queryCategory(@PathVariable("id") Integer id) {
        return Result.of(ResultStatus.SUCCESS, categoryService.queryById(id));
    }

    @GetMapping
    public Result<List<Category>> listCategory() {
        return Result.of(ResultStatus.SUCCESS, categoryService.list());
    }

    @PostMapping
    public Result<Category> createCategory(@RequestBody Category category) {
        return Result.of(ResultStatus.SUCCESS, categoryService.create(category));
    }

    @PutMapping
    public Result<Category> updateCategory(@RequestBody Category category) {
        return Result.of(ResultStatus.SUCCESS, categoryService.update(category));
    }

    @DeleteMapping("{id}")
    public ResultStatus deleteCategory(@PathVariable("id") Integer id) {
        categoryService.delete(id);
        return ResultStatus.SUCCESS;
    }

}
