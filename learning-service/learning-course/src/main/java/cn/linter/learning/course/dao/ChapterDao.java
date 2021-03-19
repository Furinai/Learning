package cn.linter.learning.course.dao;

import cn.linter.learning.course.entity.Chapter;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 章节数据库访问层
 *
 * @author wangxiaoyang
 * @since 2021/03/02
 */
@Mapper
public interface ChapterDao {

    /**
     * 通过ID查询单个章节
     *
     * @param id 章节ID
     * @return 单个章节
     */
    Chapter selectById(Long id);

    /**
     * 查询所有章节
     *
     * @return 章节列表
     */
    List<Chapter> list();

    /**
     * 通过课程ID查询所有章节
     *
     * @param courseId 课程ID
     * @return 章节列表
     */
    List<Chapter> listByCourseId(Long courseId);

    /**
     * 通过课程ID查询所有章节信息
     *
     * @param courseId 课程ID
     * @return 章节列表
     */
    List<Chapter> listInfoByCourseId(Long courseId);

    /**
     * 新增章节
     *
     * @param chapter 章节实例
     * @return 章节实例
     */
    int insert(Chapter chapter);

    /**
     * 更新章节
     *
     * @param chapter 章节实例
     * @return 影响行数
     */
    int update(Chapter chapter);

    /**
     * 删除章节
     *
     * @param id 章节ID
     * @return 影响行数
     */
    int delete(Long id);

}
