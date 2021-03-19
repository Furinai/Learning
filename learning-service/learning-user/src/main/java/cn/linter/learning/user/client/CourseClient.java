package cn.linter.learning.user.client;

import cn.linter.learning.common.entity.Page;
import cn.linter.learning.common.entity.Result;
import cn.linter.learning.user.entity.Course;
import cn.linter.learning.user.entity.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 课程服务接口
 *
 * @author wangxiaoyang
 * @since 2021/3/9
 */
@FeignClient("course-service")
public interface CourseClient {

    /**
     * 通过学会用户名查询课程
     *
     * @param pageNum     页码
     * @param pageSize    页大小
     * @param studentName 学生用户名
     * @return 课程列表
     */
    @GetMapping("courses")
    Result<Page<Course>> listCoursesByStudentName(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam String studentName);

    /**
     * 通过用户名查询笔记
     *
     * @param pageNum  页码
     * @param pageSize 页大小
     * @param username 用户名
     * @return 笔记列表
     */
    @GetMapping("notes")
    Result<Page<Note>> listNotesByUsername(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam String username);

}
