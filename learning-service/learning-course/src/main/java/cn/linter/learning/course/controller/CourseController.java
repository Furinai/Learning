package cn.linter.learning.course.controller;

import cn.linter.learning.common.entity.Page;
import cn.linter.learning.common.entity.Result;
import cn.linter.learning.common.entity.ResultStatus;
import cn.linter.learning.course.entity.Chapter;
import cn.linter.learning.course.entity.Course;
import cn.linter.learning.course.entity.Question;
import cn.linter.learning.course.service.ChapterService;
import cn.linter.learning.course.service.CourseService;
import cn.linter.learning.course.service.QuestionService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 课程控制器
 *
 * @author wangxiaoyang
 * @since 2021/02/16
 */
@RestController
@RequestMapping("courses")
public class CourseController {

    private final CourseService courseService;
    private final ChapterService chapterService;
    private final QuestionService questionService;

    public CourseController(CourseService courseService, ChapterService chapterService,
                            QuestionService questionService) {
        this.courseService = courseService;
        this.chapterService = chapterService;
        this.questionService = questionService;
    }

    @GetMapping("{id}")
    public Result<Course> queryCourse(@PathVariable("id") Long id) {
        return Result.of(ResultStatus.SUCCESS, courseService.queryById(id));
    }

    @GetMapping("{id}/chapters")
    public Result<List<Chapter>> listChaptersOfCourse(@PathVariable("id") Long id) {
        return Result.of(ResultStatus.SUCCESS, chapterService.listByCourseId(id));
    }

    @GetMapping("{id}/questions")
    public Result<Page<Question>> listQuestionsOfCourse(@RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "10") int pageSize,
                                                        @PathVariable("id") Long id, @RequestParam(defaultValue = "create_time") String orderBy) {
        PageInfo<Question> pageInfo = questionService.listByCourseId(pageNumber, pageSize, id, orderBy);
        return Result.of(ResultStatus.SUCCESS, Page.of(pageInfo.getList(), pageInfo.getTotal()));
    }

    @GetMapping
    public Result<Page<Course>> listCourse(@RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "10") int pageSize,
                                           @RequestParam(required = false) Integer categoryId, @RequestParam(defaultValue = "create_time") String orderBy) {
        PageInfo<Course> pageInfo = courseService.listByCategoryId(pageNumber, pageSize, categoryId, orderBy);
        return Result.of(ResultStatus.SUCCESS, Page.of(pageInfo.getList(), pageInfo.getTotal()));
    }

    @PostMapping
    public Result<Course> createCourse(@RequestBody Course course) {
        return Result.of(ResultStatus.SUCCESS, courseService.create(course));
    }

    @PutMapping
    public Result<Course> updateCourse(@RequestBody Course course) {
        return Result.of(ResultStatus.SUCCESS, courseService.update(course));
    }

    @DeleteMapping("{id}")
    public ResultStatus deleteCourse(@PathVariable("id") Long id) {
        courseService.delete(id);
        return ResultStatus.SUCCESS;
    }

}
