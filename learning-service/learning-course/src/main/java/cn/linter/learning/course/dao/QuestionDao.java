package cn.linter.learning.course.dao;

import cn.linter.learning.course.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 问题数据库访问层
 *
 * @author wangxiaoyang
 * @since 2021/03/04
 */
@Mapper
public interface QuestionDao {

    /**
     * 通过ID查询单个问题
     *
     * @param id 问题ID
     * @return 单个问题
     */
    Question selectById(Long id);

    /**
     * 查询所有问题
     *
     * @return 问题列表
     */
    List<Question> list();

    /**
     * 通过课程ID查询所有问题
     *
     * @param courseId 课程ID
     * @param orderBy  排序
     * @return 问题列表
     */
    List<Question> listByCourseId(@Param("courseId") Long courseId, @Param("orderBy") String orderBy);

    /**
     * 新增问题
     *
     * @param question 问题实例
     * @return 问题实例
     */
    int insert(Question question);

    /**
     * 更新问题
     *
     * @param question 问题实例
     * @return 影响行数
     */
    int update(Question question);

    /**
     * 删除问题
     *
     * @param id 问题ID
     * @return 影响行数
     */
    int delete(Long id);

    /**
     * 通过问题ID增长答案数量
     *
     * @param questionId 问题ID
     * @return 影响行数
     */
    int increaseAnswerCountByQuestionId(Long questionId);

    /**
     * 通过答案ID减少答案数量
     *
     * @param answerId 答案ID
     * @return 影响行数
     */
    int decreaseAnswerCountByAnswerId(Long answerId);

}
