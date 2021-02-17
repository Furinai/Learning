package cn.linter.learning.course.service;

import cn.linter.learning.course.entity.Category;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 服务接口
 *
 * @author wangxiaoyang
 * @since 2021/02/16
 */
public interface CategoryService {

    /**
     * 通过ID查询单个
     *
     * @param id ID
     * @return 单个
     */
    Category queryById(Long id);

    /**
     * 分页查询所有
     *
     * @param pageNumber 页号
     * @param pageSize   页大小
     * @return 列表
     */
    PageInfo<Category> list(int pageNumber, int pageSize);

    /**
     * 新增
     *
     * @param category 实例
     * @return 实例
     */
    Category create(Category category);

    /**
     * 更新
     *
     * @param category 实例
     * @return 实例
     */
    Category update(Category category);

    /**
     * 删除
     *
     * @param id ID
     * @return 是否成功
     */
    boolean delete(Long id);

}
