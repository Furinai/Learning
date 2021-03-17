package cn.linter.learning.course.service;

import cn.linter.learning.course.entity.Evaluation;
import com.github.pagehelper.PageInfo;

/**
 * 评价服务接口
 *
 * @author wangxiaoyang
 * @since 2021/03/07
 */
public interface EvaluationService {

    /**
     * 通过ID查询单个评价
     *
     * @param id 评价ID
     * @return 单个评价
     */
    Evaluation queryById(Long id);

    /**
     * 分页查询所有评价
     *
     * @param pageNum  页号
     * @param pageSize 页大小
     * @return 评价列表
     */
    PageInfo<Evaluation> list(int pageNum, int pageSize);

    /**
     * 通过课程ID分页查询所有评价
     *
     * @param pageNum  页号
     * @param pageSize 页大小
     * @param courseId 课程ID
     * @return 评价列表
     */
    PageInfo<Evaluation> listByCourseId(int pageNum, int pageSize, Long courseId);

    /**
     * 新增评价
     *
     * @param evaluation 评价实例
     * @param username   用户名
     * @return 评价实例
     */
    Evaluation create(Evaluation evaluation, String username);

    /**
     * 更新评价
     *
     * @param evaluation 评价实例
     * @return 评价实例
     */
    Evaluation update(Evaluation evaluation);

    /**
     * 删除评价
     *
     * @param id 评价ID
     * @return 是否成功
     */
    boolean delete(Long id);

}
