package cn.linter.learning.user.controller;

import cn.linter.learning.common.entity.Page;
import cn.linter.learning.common.entity.Result;
import cn.linter.learning.common.entity.ResultStatus;
import cn.linter.learning.user.client.CourseClient;
import cn.linter.learning.user.entity.Course;
import cn.linter.learning.user.entity.Note;
import cn.linter.learning.user.entity.User;
import cn.linter.learning.user.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器
 *
 * @author wangxiaoyang
 * @since 2020/11/01
 */
@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final CourseClient courseClient;

    public UserController(UserService userService, CourseClient courseClient) {
        this.userService = userService;
        this.courseClient = courseClient;
    }

    @GetMapping("{username}")
    public Result<User> queryUser(@PathVariable String username) {
        User user = userService.queryByUsername(username);
        return Result.of(ResultStatus.SUCCESS, user);
    }

    @GetMapping("{username}/courses")
    public Result<Page<Course>> listCoursesOfUser(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize,
                                                  @PathVariable("username") String studentName) {
        return courseClient.listCoursesByStudentName(pageNum, pageSize, studentName);
    }

    @GetMapping("{username}/notes")
    public Result<Page<Note>> listNotesOfUser(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize,
                                              @PathVariable String username) {
        return courseClient.listNotesByUsername(pageNum, pageSize, username);
    }

    @GetMapping
    public Result<Page<User>> listUser(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize) {
        PageInfo<User> pageInfo = userService.list(pageNum, pageSize);
        return Result.of(ResultStatus.SUCCESS, Page.of(pageInfo.getList(), pageInfo.getTotal()));
    }

    @PostMapping
    public Result<User> createUser(@RequestBody @Validated({User.Create.class}) User user) {
        return Result.of(ResultStatus.SUCCESS, userService.create(user));
    }

    @PutMapping
    public Result<User> updateUser(@RequestBody @Validated({User.Update.class}) User user) {
        User updatedUser = userService.update(user);
        return Result.of(ResultStatus.SUCCESS, updatedUser);
    }

    @DeleteMapping("{username}")
    public ResultStatus deleteUser(@PathVariable String username) {
        userService.delete(username);
        return ResultStatus.SUCCESS;
    }

}