package cn.linter.learning.course.dao;

import cn.linter.learning.course.entity.Evaluation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 评价数据库访问层
 *
 * @author wangxiaoyang
 * @since 2021/03/07
 */
@Mapper
public interface EvaluationDao {

    /**
     * 通过ID查询单个评价
     *
     * @param id 评价ID
     * @return 单个评价
     */
    Evaluation selectById(Long id);

    /**
     * 查询所有评价
     *
     * @return 评价列表
     */
    List<Evaluation> list();

    /**
     * 通过课程ID查询所有评价
     *
     * @param courseId 课程ID
     * @return 评价列表
     */
    List<Evaluation> listByCourseId(Long courseId);

    /**
     * 新增评价
     *
     * @param evaluation 评价实例
     * @return 评价实例
     */
    int insert(Evaluation evaluation);

    /**
     * 更新评价
     *
     * @param evaluation 评价实例
     * @return 影响行数
     */
    int update(Evaluation evaluation);

    /**
     * 删除评价
     *
     * @param id 评价ID
     * @return 影响行数
     */
    int delete(Long id);

    /**
     * 删除评价
     *
     * @param courseId 课程ID
     * @return 平均评分
     */
    Integer selectAverageScoreByCourseId(Long courseId);

}