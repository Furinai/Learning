package cn.linter.learning.course.service;

import cn.linter.learning.course.entity.Course;
import com.github.pagehelper.PageInfo;

/**
 * 服务接口
 *
 * @author wangxiaoyang
 * @since 2021/02/16
 */
public interface CourseService {

    /**
     * 通过ID查询单个
     *
     * @param id ID
     * @return 单个
     */
    Course queryById(Long id);

    /**
     * 分页查询所有
     *
     * @param pageNumber 页号
     * @param pageSize   页大小
     * @return 列表
     */
    PageInfo<Course> list(int pageNumber, int pageSize);

    /**
     * 新增
     *
     * @param course 实例
     * @return 实例
     */
    Course create(Course course);

    /**
     * 更新
     *
     * @param course 实例
     * @return 实例
     */
    Course update(Course course);

    /**
     * 删除
     *
     * @param id ID
     * @return 是否成功
     */
    boolean delete(Long id);

}
