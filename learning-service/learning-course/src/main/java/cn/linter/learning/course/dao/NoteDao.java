package cn.linter.learning.course.dao;

import cn.linter.learning.course.entity.Note;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 笔记数据库访问层
 *
 * @author wangxiaoyang
 * @since 2021/03/06
 */
@Mapper
public interface NoteDao {

    /**
     * 通过ID查询单个笔记
     *
     * @param id 笔记ID
     * @return 单个笔记
     */
    Note selectById(Long id);

    /**
     * 查询所有笔记
     *
     * @return 笔记列表
     */
    List<Note> list();

    /**
     * 通过课程ID查询所有笔记
     *
     * @param courseId 课程ID
     * @return 笔记列表
     */
    List<Note> listByCourseId(Long courseId);

    /**
     * 通过用户名查询所有笔记
     *
     * @param username 用户名
     * @return 笔记列表
     */
    List<Note> listByUsername(String username);

    /**
     * 通过课程ID和用户名查询所有笔记
     *
     * @param courseId 课程ID
     * @param username 用户名
     * @return 笔记列表
     */
    List<Note> listByCourseIdAndUsername(@Param("courseId") Long courseId, @Param("username") String username);

    /**
     * 新增笔记
     *
     * @param note 笔记实例
     * @return 笔记实例
     */
    int insert(Note note);

    /**
     * 更新笔记
     *
     * @param note 笔记实例
     * @return 影响行数
     */
    int update(Note note);

    /**
     * 删除笔记
     *
     * @param id 笔记ID
     * @return 影响行数
     */
    int delete(Long id);

}
