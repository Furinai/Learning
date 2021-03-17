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
     * 通过ID查询单个课程
     *
     * @param id       课程ID
     * @param username 用户名
     * @return 单个课程
     */
    Course queryById(Long id, String username);

    /**
     * 分页查询所有课程
     *
     * @param pageNum  页号
     * @param pageSize 页大小
     * @return 课程列表
     */
    PageInfo<Course> list(int pageNum, int pageSize);

    /**
     * 通过分类ID或教师用户名或学生用户名分页查询所有课程
     *
     * @param pageNum     页号
     * @param pageSize    页大小
     * @param categoryId  分类ID
     * @param teacherName 教师用户名
     * @param studentName 学生用户名
     * @param approved    审核通过
     * @param orderBy     排序
     * @return 课程列表
     */
    PageInfo<Course> listByCategoryIdOrTeacherNameOrStudentName(int pageNum, int pageSize, Integer categoryId,
                                                                String teacherName, String studentName, Boolean approved, String orderBy);

    /**
     * 新增课程
     *
     * @param course   课程实例
     * @param username 用户名
     * @return 课程实例
     */
    Course create(Course course, String username);

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
