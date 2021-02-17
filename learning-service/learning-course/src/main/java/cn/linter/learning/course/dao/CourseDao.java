package cn.linter.learning.course.dao;

import cn.linter.learning.course.entity.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 数据库访问层
 *
 * @author wangxiaoyang
 * @since 2021/02/16
 */
@Mapper
public interface CourseDao {

    /**
     * 通过ID查询单个
     *
     * @param id ID
     * @return 单个
     */
    Course selectById(Long id);

    /**
     * 查询所有
     *
     * @return 列表
     */
    List<Course> list();

    /**
     * 通过实例查询所有
     *
     * @param course
     * @return 列表
     */
    List<Course> listByEntity(Course course);

    /**
     * 新增
     *
     * @param course 实例
     * @return 实例
     */
    int insert(Course course);

    /**
     * 更新
     *
     * @param course 实例
     * @return 影响行数
     */
    int update(Course course);

    /**
     * 删除
     *
     * @param id ID
     * @return 影响行数
     */
    int delete(Long id);

}
