package cn.linter.learning.course.service;

import cn.linter.learning.course.entity.Course;
import com.github.pagehelper.PageInfo;

/**
 * 课程服务接口
 *
 * @author wangxiaoyang
 * @since 2021/02/16
 */
public interface CourseService {

    /**
     * 通过ID查询单个课程
     *
     * @param id 课程ID
     * @return 单个课程
     */
    Course queryById(Long id);

    /**
     * 分页查询所有课程
     *
     * @param pageNumber 页号
     * @param pageSize   页大小
     * @return 课程列表
     */
    PageInfo<Course> list(int pageNumber, int pageSize);

    /**
     * 通过分类ID分页查询所有课程
     *
     * @param pageNumber 页号
     * @param pageSize   页大小
     * @param categoryId 分类ID
     * @param orderBy    排序
     * @return 课程列表
     */
    PageInfo<Course> listByCategoryId(int pageNumber, int pageSize, Integer categoryId, String orderBy);

    /**
     * 新增课程
     *
     * @param course 课程实例
     * @return 课程实例
     */
    Course create(Course course);

    /**
     * 更新课程
     *
     * @param course 课程实例
     * @return 课程实例
     */
    Course update(Course course);

    /**
     * 删除课程
     *
     * @param id 课程ID
     * @return 是否成功
     */
    boolean delete(Long id);

}
