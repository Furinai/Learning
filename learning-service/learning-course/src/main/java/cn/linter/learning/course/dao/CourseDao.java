package cn.linter.learning.course.dao;

import cn.linter.learning.course.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 课程数据库访问层
 *
 * @author wangxiaoyang
 * @since 2021/02/16
 */
@Mapper
public interface CourseDao {

    /**
     * 通过ID查询单个课程
     *
     * @param id 课程ID
     * @return 单个课程
     */
    Course selectById(Long id);

    /**
     * 查询所有课程
     *
     * @return 课程列表
     */
    List<Course> list();

    /**
     * 通过分类ID或教师用户名或学生用户名分页查询所有课程
     *
     * @param categoryId  分类ID
     * @param teacherName 教师用户名
     * @param studentName 学生用户名
     * @param orderBy     排序
     * @return 课程列表
     */
    List<Course> listByCategoryIdOrTeacherNameOrStudentName(@Param("categoryId") Integer categoryId, @Param("teacherName") String teacherName,
                                                            @Param("studentName") String studentName, @Param("orderBy") String orderBy);

    /**
     * 新增课程
     *
     * @param course 课程实例
     * @return 课程实例
     */
    int insert(Course course);

    /**
     * 更新课程
     *
     * @param course 课程实例
     * @return 影响行数
     */
    int update(Course course);

    /**
     * 删除课程
     *
     * @param id 课程ID
     * @return 影响行数
     */
    int delete(Long id);

    /**
     * 通过用户名和课程ID查询关系
     *
     * @param username 用户名
     * @param courseId 课程ID
     * @return 是否存在
     */
    Boolean selectRelationByUsernameAndCourseId(@Param("username") String username, @Param("courseId") Long courseId);

}
