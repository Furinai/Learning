package cn.linter.learning.course.dao;

import cn.linter.learning.course.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 数据库访问层
 *
 * @author wangxiaoyang
 * @since 2021/02/16
 */
@Mapper
public interface CategoryDao {

    /**
     * 通过ID查询单个
     *
     * @param id ID
     * @return 单个
     */
    Category selectById(Long id);

    /**
     * 查询所有
     *
     * @return 列表
     */
    List<Category> list();

    /**
     * 通过实例查询所有
     *
     * @param category
     * @return 列表
     */
    List<Category> listByEntity(Category category);

    /**
     * 新增
     *
     * @param category 实例
     * @return 实例
     */
    int insert(Category category);

    /**
     * 更新
     *
     * @param category 实例
     * @return 影响行数
     */
    int update(Category category);

    /**
     * 删除
     *
     * @param id ID
     * @return 影响行数
     */
    int delete(Long id);

}
