package cn.linter.learning.course.controller;

import cn.linter.learning.common.entity.Page;
import cn.linter.learning.common.entity.Result;
import cn.linter.learning.common.entity.ResultStatus;
import cn.linter.learning.common.utils.JwtUtil;
import cn.linter.learning.course.entity.*;
import cn.linter.learning.course.service.*;
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
    private final NoteService noteService;
    private final EvaluationService evaluationService;

    public CourseController(CourseService courseService, ChapterService chapterService,
                            QuestionService questionService, NoteService noteService, EvaluationService evaluationService) {
        this.courseService = courseService;
        this.chapterService = chapterService;
        this.questionService = questionService;
        this.noteService = noteService;
        this.evaluationService = evaluationService;
    }

    @GetMapping("{id}")
    public Result<Course> queryCourse(@PathVariable("id") Long id, @RequestHeader(value = "Authorization", required = false) String token) {
        if (token == null) {
            return Result.of(ResultStatus.SUCCESS, courseService.queryById(id));
        }
        return Result.of(ResultStatus.SUCCESS, courseService.queryById(id, JwtUtil.getUsername(token)));
    }

    @GetMapping("{id}/chapters")
    public Result<List<Chapter>> listChaptersOfCourse(@PathVariable("id") Long id) {
        return Result.of(ResultStatus.SUCCESS, chapterService.listByCourseId(id));
    }

    @GetMapping("{id}/questions")
    public Result<Page<Question>> listQuestionsOfCourse(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize,
                                                        @PathVariable("id") Long id, @RequestParam(defaultValue = "create_time") String orderBy) {
        PageInfo<Question> pageInfo = questionService.listByCourseId(pageNum, pageSize, id, orderBy);
        return Result.of(ResultStatus.SUCCESS, Page.of(pageInfo.getList(), pageInfo.getTotal()));
    }

    @GetMapping("{id}/notes")
    public Result<Page<Note>> listNotesOfCourse(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize,
                                                @PathVariable("id") Long id, @RequestParam(defaultValue = "false") Boolean onlyOwn,
                                                @RequestHeader(value = "Authorization", required = false) String token) {
        String username = null;
        if (onlyOwn && token != null) {
            username = JwtUtil.getUsername(token);
        }
        PageInfo<Note> pageInfo = noteService.listByCourseIdOrUsername(pageNum, pageSize, id, username);
        return Result.of(ResultStatus.SUCCESS, Page.of(pageInfo.getList(), pageInfo.getTotal()));
    }

    @GetMapping("{id}/evaluations")
    public Result<Page<Evaluation>> listEvaluationsOfCourse(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize,
                                                            @PathVariable("id") Long id) {
        PageInfo<Evaluation> pageInfo = evaluationService.listByCourseId(pageNum, pageSize, id);
        return Result.of(ResultStatus.SUCCESS, Page.of(pageInfo.getList(), pageInfo.getTotal()));
    }

    @GetMapping
    public Result<Page<Course>> listCourse(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize,
                                           @RequestParam(required = false) Integer categoryId, @RequestParam(required = false) String teacherName,
                                           @RequestParam(required = false) String studentName, @RequestParam(defaultValue = "false") Boolean approved,
                                           @RequestParam(defaultValue = "create_time") String orderBy) {
        PageInfo<Course> pageInfo = courseService.listByCategoryIdOrTeacherNameOrStudentName(pageNum, pageSize,
                categoryId, teacherName, studentName, approved, orderBy);
        return Result.of(ResultStatus.SUCCESS, Page.of(pageInfo.getList(), pageInfo.getTotal()));
    }

    @PostMapping
    public Result<Course> createCourse(@RequestBody Course course, @RequestHeader("Authorization") String token) {
        return Result.of(ResultStatus.SUCCESS, courseService.create(course, JwtUtil.getUsername(token)));
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
