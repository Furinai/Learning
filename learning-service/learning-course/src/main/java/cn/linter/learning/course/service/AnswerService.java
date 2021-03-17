package cn.linter.learning.course.service;

import cn.linter.learning.course.entity.Answer;
import com.github.pagehelper.PageInfo;

/**
 * 答案服务接口
 *
 * @author wangxiaoyang
 * @since 2021/03/04
 */
public interface AnswerService {

    /**
     * 通过ID查询单个答案
     *
     * @param id 答案ID
     * @return 单个答案
     */
    Answer queryById(Long id);

    /**
     * 通过问题ID分页查询所有答案
     *
     * @param pageNum    页号
     * @param pageSize   页大小
     * @param questionId 问题ID
     * @return 答案列表
     */
    PageInfo<Answer> listByQuestionId(int pageNum, int pageSize, Long questionId);

    /**
     * 分页查询所有答案
     *
     * @param pageNum  页号
     * @param pageSize 页大小
     * @return 答案列表
     */
    PageInfo<Answer> list(int pageNum, int pageSize);

    /**
     * 新增答案
     *
     * @param answer   答案实例
     * @param username 用户名
     * @return 答案实例
     */
    Answer create(Answer answer, String username);

    /**
     * 更新答案
     *
     * @param answer 答案实例
     * @return 答案实例
     */
    Answer update(Answer answer);

    /**
     * 删除答案
     *
     * @param id 答案ID
     * @return 是否成功
     */
    boolean delete(Long id);

}
