package cn.linter.learning.course.service;

import cn.linter.learning.course.entity.Question;
import com.github.pagehelper.PageInfo;

/**
 * 问题服务接口
 *
 * @author wangxiaoyang
 * @since 2021/03/04
 */
public interface QuestionService {

    /**
     * 通过ID查询单个问题
     *
     * @param id 问题ID
     * @return 单个问题
     */
    Question queryById(Long id);

    /**
     * 通过课程ID分页查询所有问题
     *
     * @param pageNum  页号
     * @param pageSize 页大小
     * @param courseId 课程ID
     * @param orderBy  排序
     * @return 问题列表
     */
    PageInfo<Question> listByCourseId(int pageNum, int pageSize, Long courseId, String orderBy);

    /**
     * 分页查询所有问题
     *
     * @param pageNum  页号
     * @param pageSize 页大小
     * @return 问题列表
     */
    PageInfo<Question> list(int pageNum, int pageSize);

    /**
     * 新增问题
     *
     * @param question 问题实例
     * @param username 用户名
     * @return 问题实例
     */
    Question create(Question question, String username);

    /**
     * 更新问题
     *
     * @param question 问题实例
     * @return 问题实例
     */
    Question update(Question question);

    /**
     * 删除问题
     *
     * @param id 问题ID
     * @return 是否成功
     */
    boolean delete(Long id);

}
