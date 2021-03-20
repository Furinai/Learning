package cn.linter.learning.trade.client;

import cn.linter.learning.common.entity.Result;
import cn.linter.learning.trade.entity.Course;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 课程服务接口
 *
 * @author wangxiaoyang
 * @since 2021/3/19
 */
@FeignClient("course-service")
public interface CourseClient {

    /**
     * 通过ID查询单个课程
     *
     * @param id 课程ID
     * @return 单个课程
     */
    @GetMapping("courses/{id}")
    Result<Course> queryCourse(@PathVariable("id") Long id);

    /**
     * 更新用户-课程关系
     *
     * @param course   课程ID
     * @param username 用户名
     * @return 单个课程
     */
    @PutMapping("courses")
    Result<Course> updateCourse(@RequestBody Course course, @RequestParam String username);

}
