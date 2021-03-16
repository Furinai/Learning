package cn.linter.learning.course.dao;

import cn.linter.learning.course.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 分类数据库访问层
 *
 * @author wangxiaoyang
 * @since 2021/02/16
 */
@Mapper
public interface CategoryDao {

    /**
     * 通过ID查询单个分类
     *
     * @param id 分类ID
     * @return 单个分类
     */
    Category selectById(Integer id);

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
    int insert(Category category);

    /**
     * 更新分类
     *
     * @param category 分类实例
     * @return 影响行数
     */
    int update(Category category);

    /**
     * 删除分类
     *
     * @param id 分类ID
     * @return 影响行数
     */
    int delete(Integer id);

}
