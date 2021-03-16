package cn.linter.learning.course.service;

import cn.linter.learning.course.entity.Category;

import java.util.List;

/**
 * 分类服务接口
 *
 * @author wangxiaoyang
 * @since 2021/02/16
 */
public interface CategoryService {

    /**
     * 通过ID查询单个分类
     *
     * @param id 分类ID
     * @return 单个分类
     */
    Category queryById(Integer id);

    /**
     * 查询所有分类
     *
     * @return 分类列表
     */
    List<Category> list();

    /**
     * 新增分类
     *
     * @param category 分类实例
     * @return 分类实例
     */
    Category create(Category category);

    /**
     * 更新分类
     *
     * @param category 分类实例
     * @return 分类实例
     */
    Category update(Category category);

    /**
     * 删除分类
     *
     * @param id 分类ID
     * @return 是否成功
     */
    boolean delete(Integer id);

}
