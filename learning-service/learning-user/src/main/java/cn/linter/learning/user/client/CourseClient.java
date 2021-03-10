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
     * 查询用户
     *
     * @param pageNumber 页码
     * @param pageSize   页大小
     * @param username   用户名
     * @return 用户
     */
    @GetMapping("courses")
    Result<Page<Course>> listCoursesByUsername(@RequestParam int pageNumber, @RequestParam int pageSize, @RequestParam String username);

    /**
     * 查询用户
     *
     * @param pageNumber 页码
     * @param pageSize   页大小
     * @param username   用户名
     * @return 用户
     */
    @GetMapping("notes")
    Result<Page<Note>> listNotesByUsername(@RequestParam int pageNumber, @RequestParam int pageSize, @RequestParam String username);

}
