package cn.linter.learning.course.service;

import cn.linter.learning.course.entity.Note;
import com.github.pagehelper.PageInfo;

/**
 * 笔记服务接口
 *
 * @author wangxiaoyang
 * @since 2021/03/06
 */
public interface NoteService {

    /**
     * 通过ID查询单个笔记
     *
     * @param id 笔记ID
     * @return 单个笔记
     */
    Note queryById(Long id);

    /**
     * 通过课程ID分页查询所有笔记
     *
     * @param pageNum  页号
     * @param pageSize 页大小
     * @param courseId 课程ID
     * @return 笔记列表
     */
    PageInfo<Note> listByCourseId(int pageNum, int pageSize, Long courseId);

    /**
     * 通过用户名分页查询所有笔记
     *
     * @param pageNum  页号
     * @param pageSize 页大小
     * @param username 用户名
     * @return 笔记列表
     */
    PageInfo<Note> listByUsername(int pageNum, int pageSize, String username);

    /**
     * 通过课程ID和用户名分页查询所有笔记
     *
     * @param pageNum  页号
     * @param pageSize 页大小
     * @param courseId 课程ID
     * @param username 用户名
     * @return 笔记列表
     */
    PageInfo<Note> listByCourseIdAndUsername(int pageNum, int pageSize, Long courseId, String username);

    /**
     * 分页查询所有笔记
     *
     * @param pageNum  页号
     * @param pageSize 页大小
     * @return 笔记列表
     */
    PageInfo<Note> list(int pageNum, int pageSize);

    /**
     * 新增笔记
     *
     * @param note     笔记实例
     * @param username 用户名
     * @return 笔记实例
     */
    Note create(Note note, String username);

    /**
     * 更新笔记
     *
     * @param note 笔记实例
     * @return 笔记实例
     */
    Note update(Note note);

    /**
     * 删除笔记
     *
     * @param id 笔记ID
     * @return 是否成功
     */
    boolean delete(Long id);

}
