package cn.linter.learning.course.controller;

import cn.linter.learning.common.entity.Page;
import cn.linter.learning.common.entity.Result;
import cn.linter.learning.common.entity.ResultStatus;
import cn.linter.learning.course.entity.Category;
import cn.linter.learning.course.service.CategoryService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

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
    public Result<Category> queryCategory(@PathVariable("id") Long id) {
        return Result.of(ResultStatus.SUCCESS, categoryService.queryById(id));
    }

    @GetMapping
    public Result<Page<Category>> listCategory(@RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        PageInfo<Category> pageInfo = categoryService.list(pageNumber, pageSize);
        return Result.of(ResultStatus.SUCCESS, Page.of(pageInfo.getList(), pageInfo.getTotal()));
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
    public ResultStatus deleteCategory(@PathVariable("id") Long id) {
        categoryService.delete(id);
        return ResultStatus.SUCCESS;
    }

}
